package com.application.bot.controllers;


import com.application.bot.models.Message;
import com.application.bot.tools.UpdatesParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/updates")
public class UpdatesControllers {


    @PostMapping
    void doUpdates(@RequestBody String result) throws JsonProcessingException {
        List<Message> list = UpdatesParser.parseToListOfMessenger(result);
    }

}
