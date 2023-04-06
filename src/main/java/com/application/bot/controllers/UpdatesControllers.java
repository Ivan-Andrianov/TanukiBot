package com.application.bot.controllers;


import com.application.bot.model.Updates;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class UpdatesControllers {


    @PostMapping
    void doUpdates(@RequestBody Updates updates){

    }

}
