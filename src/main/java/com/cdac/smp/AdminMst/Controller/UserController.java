package com.cdac.smp.AdminMst.Controller;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdac.smp.AdminMst.CustomeException.ApiException;
import com.cdac.smp.AdminMst.Dao.AddressDao;
import com.cdac.smp.AdminMst.Model.User;
import com.cdac.smp.AdminMst.Service.LoginEntryService;
import com.cdac.smp.AdminMst.Service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AddressDao addressDao;
    private final LoginEntryService loginEntryService;
    
    public UserController(UserService userService,AddressDao addressDao,LoginEntryService loginEntryService) {
        this.userService = userService;
        this.addressDao=addressDao;
        this.loginEntryService=loginEntryService;
    }
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		return "AdminMst/userCreate";
    	}
    	
    	if (userService.existsByLoginId(user.getLoginId())) {
            model.addAttribute("errorMessage", "User with this Login ID already exists!");
            return "AdminMst/userCreate";  
        }

    	System.out.println("before User creatiion..................");
    	userService.createUser(user);
        System.out.println("after User creatiion..................");
        return "redirect:all";
    }
    
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("addresses", addressDao.getAllAddresses());
        return "AdminMst/userCreate";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable String id,Model model) {
    	Optional<User> user = userService.getUserById(id);
    	model.addAttribute("user", user);
    	return "AdminMst/edit";
    }

    //http://localhost:8080/user/all
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "AdminMst/users"; 
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute User user,BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "AdminMst/edit";
    	}
    	
    	System.out.println("Before update..");
    	userService.updateUser(user);
    	System.out.println("after update");
        return "redirect:all";
    }

    @PostMapping("/delete/{id}") 
    public String deleteUser(@PathVariable String id) {
    	System.out.println("Checking......");
        userService.deleteUser(id);
        return "redirect:/user/all";
    }
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "AdminMst/login";
    }
    
    //http://localhost:8080/user/login
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            return "AdminMst/login";
        }

        User user1 = userService.getUserByLoginId(user.getLoginId());
        if (user1 == null) {
            model.addAttribute("error", "Invalid Login ID!");   
            return "AdminMst/login";
        }

        String loginId = user.getLoginId();
        int val = userService.getflagValue(loginId);
//        if (val >= 3) {
//            model.addAttribute("error", "You have been blocked for 24 hours...");
//            return "AdminMst/login";
//        }
        if (!user.getPassword().equals(user1.getPassword())) {
        	if (val >= 3) {
                model.addAttribute("error", "You have been blocked for 24 hours...");
                return "AdminMst/login";
            }
            loginEntryService.recordFailedLogin(loginId);
            userService.setFlagByOne(loginId);
            model.addAttribute("error", "Wrong password! Attempt " + (val + 1) + " of 3.");
            return "AdminMst/login";
        }
        LocalDateTime lastLogin = loginEntryService.getLastLoginTime(loginId);
        

        if (lastLogin != null) {
            LocalDateTime now = LocalDateTime.now();
            long hoursBetween = ChronoUnit.HOURS.between(lastLogin, now);
            System.out.println("Hours of user last login block "+hoursBetween);
            if (hoursBetween < 24) {
                model.addAttribute("error", "You can only login once every 24 hours. Last login: " + lastLogin);
                return "AdminMst/login";
            }
        }
        
	     userService.setFlagByZero(loginId);
	
	     Optional<User> loggedInUser = userService.login(loginId, user.getPassword());
	     if (loggedInUser.isPresent()) {
	    	 loginEntryService.clearLoginEntries(loginId);
	         model.addAttribute("user", loggedInUser.get());
	         return "AdminMst/dashboard";
	     } else {
	         model.addAttribute("error", "Login failed — please try again later.");
	         return "AdminMst/login";
	     }
	     
    }

    
    @GetMapping("/{loginId}")
    public User getUserDetails(@PathVariable String loginId) throws ApiException {
    	User user=userService.getUserByLoginId(loginId);
    	if(user==null) {
    		throw new ApiException("login id is wrong!!");
    	}
    	return user;
    }

}
