package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.PlantException;
import com.masai.Repository.PlantDao;
import com.masai.Service.plantService;
import com.masai.model.Plant;

@RestController
public class PlantController {

	@Autowired
	private plantService pService;;
	
	@PostMapping("/plant")
	public ResponseEntity<Plant> addNewPlantHandler(@RequestBody Plant plant) throws PlantException{
		
		Plant savePlant = pService.addPlant(plant);
		return new ResponseEntity<Plant>(savePlant,HttpStatus.CREATED);
		
		
	}
	@PutMapping("/plant")
	public ResponseEntity<Plant> updatePlantHandler(@RequestBody Plant palnt) throws PlantException{
		
		Plant updatedPlant = pService.updatePlant(palnt);
		return new ResponseEntity<Plant>(updatedPlant,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/plant/{id}")
	public ResponseEntity<Plant> deletePlantByIdHandler(@PathVariable("id") Integer id) throws PlantException{
	  Plant plant=	pService.deletePlant(id);
	  return new ResponseEntity<Plant>(plant,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getPlantById/{id}")
	public ResponseEntity<Plant> viewPlantByIdHandler(@PathVariable("id") Integer id) throws PlantException{
		Plant plant = pService.viewPlantById(id);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
	}
	
	@GetMapping("/getPlantByName/{name}")
	public ResponseEntity<Plant> viewPlantByCommonNameHandler(@PathVariable("name") String name) throws PlantException{
		Plant plant = pService.viewPlantByCommonName(name);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
	}
	
	
	@GetMapping("/allPlants")
	public ResponseEntity<List<Plant>> viewAllPlantsHandler() throws PlantException{
		List<Plant> plist = pService.viewAllPlant();
		return new ResponseEntity<List<Plant>>(plist,HttpStatus.OK);
	}
	
	
	@GetMapping("/allPlantByType/{type}")
	public ResponseEntity<List<Plant>> viewAllPlantsByTypeOfPlant(@PathVariable("type") String type) throws PlantException{
		List<Plant> plist = pService.viewAllPlantByTypeOfPlant(type);
		return new ResponseEntity<List<Plant>>(plist,HttpStatus.OK);
	}
}