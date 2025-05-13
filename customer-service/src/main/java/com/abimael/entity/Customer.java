package com.abimael.entity;

import com.abimael.domain.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre favoriteGenre;
}
