package com.abimael.client;

import com.abimael.domain.*;
import com.abimael.dto.*;
import org.slf4j.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;

@Service
public class MovieClient {

    private static final Logger log = LoggerFactory.getLogger(MovieClient.class);
    private final RestClient client;

    public MovieClient(RestClient client) {
        this.client = client;
    }

    public List<MovieDto> getMovies(Genre genre){
        log.info("genre: {}", genre);
        var list = this.client.get()
                .uri("/api/movies/{genre}", genre)
                .retrieve()
                .body(new ParameterizedTypeReference<List<MovieDto>>(){});
        log.info("received movies: {}", list);
        return list;
    }
}
