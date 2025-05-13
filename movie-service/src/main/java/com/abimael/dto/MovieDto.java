package com.abimael.dto;

import com.abimael.domain.*;

public record MovieDto(Integer id,
                       String title,
                       Integer releaseYear,
                       Genre genre) {
}
