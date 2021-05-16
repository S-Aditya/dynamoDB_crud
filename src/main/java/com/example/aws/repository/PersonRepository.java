package com.example.aws.repository;

import com.example.aws.entity.Person;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Person addPerson(Person person){
        mapper.save(person);
        return person;
    }

    public Person findPersonByPersonID(String personID){
        return mapper.load(Person.class, personID);
    }

    public String deletePerson(Person person){
        mapper.delete(person);
        return "person removed!!";
    }

    public String editPerson(Person person){
        mapper.save(person, buildExpression(person));
        return "record updated";
    }

    private DynamoDBSaveExpression buildExpression(Person person){

        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBSaveExpression.setExpected(expectedMap);

        return dynamoDBSaveExpression;
    }
}
