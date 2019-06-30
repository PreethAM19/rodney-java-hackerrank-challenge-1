package com.hackerrank.github.controller;

import com.hackerrank.github.service.ActorService;
import com.hackerrank.github.service.EventService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubApiRestController {

    private final EventService eventService;
    private final ActorService actorService;

    public GithubApiRestController(EventService eventService, ActorService actorService) {
        this.eventService = eventService;
        this.actorService = actorService;
    }

}
