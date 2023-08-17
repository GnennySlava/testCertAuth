package com.example.demoservercertauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/server")
@Slf4j
public class ServerController {

    static Map<String, User> db = new HashMap<>();

    @Autowired
    ObjectMapper objectMapper;


    @PostConstruct
    public void init() {
        User user = new User("12345","User1");
        db.put(user.getId(), user);
    }

    @GetMapping
    public String serverData() {
        return "Data from server";
    }

    @GetMapping("/search/{id}")
    public String serverData(final HttpServletResponse response, @PathVariable String id) throws JsonProcessingException {
        User usr= db.get(id);
        if (usr != null) {
            return objectMapper.writeValueAsString(usr);
        }
        return "User not found";
    }
}