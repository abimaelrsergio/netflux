package com.abimael.mapper;

import com.abimael.dto.*;
import com.abimael.entity.*;

import java.util.*;

public class EntityDtoMapper {

    public static CustomerDto toDto(Customer customer, List<MovieDto> movies) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getFavoriteGenre(),
                movies
        );
    }

}
