package com.cdac.smp.section.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cdac.smp.section.dao.SectionDao;
import com.cdac.smp.section.model.Section;

@Service
public class SectionService {
	private SectionDao sectionDao ;
	
	public SectionService(SectionDao sectionDao) {
		this.sectionDao = sectionDao ;
	}
	
	public List<Section> getAllSection() {
		return sectionDao.getAllSection() ;	
	}
	
	public int createSection(Section section) {
		
		return sectionDao.createSection(section);
	}
	
	public int deleteSection(String sectionCode) {
		return sectionDao.deleteSection(sectionCode);
	}
	
	public int updateSection(Section section) {
		return sectionDao.updateSection(section);
	}
	
	public Section getSectionByCode(String sectionCode) {
	     return sectionDao.getSectionByCode(sectionCode);
	}
}
