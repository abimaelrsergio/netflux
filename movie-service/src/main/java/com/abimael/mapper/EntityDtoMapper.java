package com.abimael.mapper;

import com.abimael.dto.*;
import com.abimael.entity.*;

public class EntityDtoMapper {

    public static MovieDto toDto(Movie movie) {
        return new MovieDto(movie.getId(),
                            movie.getTitle(),
                            movie.getReleaseYear(),
                            movie.getGenre()
                );
    }

}
