package com.abimael.service;

import com.abimael.domain.*;
import com.abimael.dto.*;
import com.abimael.mapper.*;
import com.abimael.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    public List<MovieDto> getAll() {
        return this.repository.findAll()
                .stream()
                .map(EntityDtoMapper::toDto)
                .toList();
    }

    public List<MovieDto> getAll(Genre genre) {
        return this.repository.findByGenre(genre)
                .stream()
                .map(EntityDtoMapper::toDto)
                .toList();
    }

}
