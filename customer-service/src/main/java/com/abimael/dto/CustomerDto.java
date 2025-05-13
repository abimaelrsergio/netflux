package com.abimael.dto;

import com.abimael.domain.*;

import java.util.*;

public record CustomerDto(Integer id,
                          String name,
                          Genre favoriteGenre,
                          List<MovieDto> recommendedMovies) {
}
