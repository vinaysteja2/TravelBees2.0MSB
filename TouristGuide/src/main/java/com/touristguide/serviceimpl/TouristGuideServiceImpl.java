package com.touristguide.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touristguide.clients.TourClient;
import com.touristguide.dto.TouristGuideDto;
import com.touristguide.entity.TouristGuide;
import com.touristguide.exception.TouristGuideException;
import com.touristguide.external.Tour;
import com.touristguide.mapper.TouristGuideMapper;
import com.touristguide.repository.TouristGuideRepository;
import com.touristguide.service.ITouristGuideService;

@Service
public class TouristGuideServiceImpl implements ITouristGuideService {
	
	@Autowired
    private  TouristGuideRepository touristGuideRepository;

	@Autowired
	private TourClient tourClient;
   

	 public TouristGuide addTouristGuide(TouristGuide touristGuide) {
	        if (touristGuideRepository.existsByEmail(touristGuide.getEmail())) {
	            throw new TouristGuideException("Email is already in use");
	        }
	        return touristGuideRepository.save(touristGuide);
	    }
	
	 
	 
	  public TouristGuideDto getTouristGuideById(Long id) {
		  if(!touristGuideRepository.existsById(id)) {
	    	
	    		throw new TouristGuideException("Provided TouristGuide is not present");
	    		
	    	}
		  TouristGuide touristGuide= touristGuideRepository.findById(id).get();
	    	return this.convertToDto( touristGuide);
	    
	  }
	  
    private TouristGuideDto convertToDto(TouristGuide touristGuide) {
	
	List<Tour> tours= tourClient.getTours(touristGuide.getId());
   TouristGuideDto touristGuideDto=TouristGuideMapper.mapToTouristGuideDto(touristGuide,tours);
     return touristGuideDto;
}
	    	
    public TouristGuide getTouristGuideById1(Long id) {
		  if(!touristGuideRepository.existsById(id)) {
	    	
	    		throw new TouristGuideException("Provided TouristGuide is not present");
	    		
	    	}
		  TouristGuide touristGuide= touristGuideRepository.findById(id).get();
	    	return touristGuide;
	    
	  }
	  
    
    public void updateTouristGuide(Long touristGuideId, TouristGuide updatedTouristGuide) {
        
        		  if(!touristGuideRepository.existsById(touristGuideId)) {
        		    	
      	    		throw new TouristGuideException("Provided TouristGuide is not present");
      	    		
      	    	}
        TouristGuide existingTouristGuide = touristGuideRepository.findById(touristGuideId).get();
        
        existingTouristGuide.setName(updatedTouristGuide.getName());
        existingTouristGuide.setLocation(updatedTouristGuide.getLocation());
        existingTouristGuide.setLanguages(updatedTouristGuide.getLanguages());
        existingTouristGuide.setDescription(updatedTouristGuide.getDescription());
        existingTouristGuide.setEmail(updatedTouristGuide.getEmail());
        existingTouristGuide.setAverageRating(updatedTouristGuide.getAverageRating());
        existingTouristGuide.setPhoto(updatedTouristGuide.getPhoto());
        existingTouristGuide.setContactNumber(updatedTouristGuide.getContactNumber());

       
        touristGuideRepository.save(existingTouristGuide);
    }

    public void deleteTouristGuide(Long touristGuideId) {
    	
    	  if(!touristGuideRepository.existsById(touristGuideId)) {
		    	
	    		throw new TouristGuideException("Provided TouristGuide is not present");
	    		
	    	}
    	
        TouristGuide existingTouristGuide = touristGuideRepository.findById(touristGuideId).get();
        tourClient.deleteTours(touristGuideId); 
        touristGuideRepository.delete(existingTouristGuide);
    }

    public List<TouristGuide> searchTouristGuides(String location) {
    	
    	if(!touristGuideRepository.existsByLocation(location)) {
	    	
    		throw new TouristGuideException("Provided TouristGuide location is not present");
    		
    	}
        return touristGuideRepository.findByLocationContainingIgnoreCase(location);
    }
    

    
    public List<TouristGuide> getAllTouristGuides() {
        
        List<TouristGuide> touristGuides = touristGuideRepository.findAll();
        
        if(touristGuides.isEmpty()) {
        	
        	throw new TouristGuideException("There are no touristGuides Present");
        }
        	
        else {
        	return touristGuides;
        }}
    
    public List<String> getAllLocations() {
        List<TouristGuide> touristGuides = touristGuideRepository.findAll();
         if(touristGuides.isEmpty()) {
        	
        	throw new TouristGuideException("There are no touristGuides Present in this location");
        }
        	
        else {
        return touristGuides.stream()
                .map(TouristGuide::getLocation)
                .distinct()
                .collect(Collectors.toList());
    }
}
}
