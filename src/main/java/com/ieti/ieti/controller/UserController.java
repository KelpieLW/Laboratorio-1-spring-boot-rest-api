package com.ieti.ieti.controller;

import com.ieti.ieti.data.User;
import com.ieti.ieti.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.ieti.ieti.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/v1/user" )
public class UserController
{
    private final UserService userService;

    public UserController(@Autowired UserService userService )
    {

        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> all()
    {
        return new ResponseEntity<List<User>>(userService.all(), HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById( @PathVariable String id )
    {
        User userFound=userService.findById(id);
        if(userFound==null){
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<User>(userFound, HttpStatus.FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto )
    {
       User createdUser=new User(UUID.randomUUID().toString(), userDto.getName(), userDto.getEmail(), userDto.getLastName());
       userService.create(createdUser);
       return new ResponseEntity<User>(createdUser, HttpStatus.OK);

    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {

        User userToUpdate=new User(id, userDto.getName(), userDto.getEmail(), userDto.getLastName());
        User updatedUser=userService.update(userToUpdate, id);
        if(userService==null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<User>(updatedUser,HttpStatus.ACCEPTED);
        }


    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        if(userService.findById(id)==null){
            return new ResponseEntity<Boolean> (false, HttpStatus.NOT_FOUND);

        }
        else{
            userService.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
        }
    }
}

