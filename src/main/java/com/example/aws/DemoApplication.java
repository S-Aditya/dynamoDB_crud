package com.example.aws;

import com.example.aws.entity.Person;
import com.example.aws.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private PersonRepository repository;

	@PostMapping("/savePerson") //url mapping
	public Person savePerson(@RequestBody Person person){
		return repository.addPerson(person);
	}

	@GetMapping("/getPerson/{personId}") //url mapping
	public Person findPerson(@PathVariable String personId){
		return repository.findPersonByPersonID(personId);
	}

	@DeleteMapping("/deletePerson")
	public String deletePerson(@RequestBody Person person){
		return repository.deletePerson(person);
	}

	@PutMapping("/editPerson")
	public String updatePerson(@RequestBody Person person){
		return repository.editPerson(person);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
