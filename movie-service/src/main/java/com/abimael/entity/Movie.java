package com.abimael.entity;

import com.abimael.domain.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    private Integer id;
    private String title;
    private Integer releaseYear;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
