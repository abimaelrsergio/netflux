package com.abimael.controller;

import com.abimael.domain.*;
import com.abimael.dto.*;
import com.abimael.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return service.getAll();
    }

    @GetMapping("{genre}")
    public List<MovieDto> getAll(@PathVariable Genre genre) {
        return service.getAll(genre);
    }

}
