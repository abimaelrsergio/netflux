package com.abimael.repository;

import com.abimael.domain.*;
import com.abimael.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByGenre(Genre genre);

}
