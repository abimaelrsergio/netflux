package com.abimael;

import com.abimael.client.*;
import com.abimael.domain.*;
import com.abimael.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.*;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.*;
import org.springframework.web.client.*;

import java.net.*;
import java.util.*;

@Import(TestcontainersConfiguration.class)
@MockitoBean(types = {RestClient.class, MovieClient.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

	@Autowired
	private TestRestTemplate template;

	@Autowired
	private MovieClient movieClient;

	@Test
	void health() {
		var responseEntity = this.template.getForEntity("/actuator/health", Object.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}

	@Test
	void customerWithMovies(){
		// mock recommended movies
		Mockito.when(movieClient.getMovies(Mockito.any(Genre.class))).thenReturn(List.of(
				new MovieDto(1, "movie-1", 1990, Genre.ACTION),
				new MovieDto(2, "movie-2", 1991, Genre.ACTION)
		));

		var responseEntity = this.template.getForEntity("/api/customers/1", CustomerDto.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		var customerDto = responseEntity.getBody();
		Assertions.assertNotNull(customerDto);
		Assertions.assertEquals("sam", customerDto.name());
		Assertions.assertEquals(2, customerDto.recommendedMovies().size());
	}

	@Test
	void customerNotFound() {
		var responseEntity = this.template.getForEntity("/api/customers/10", ProblemDetail.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
		var problemDetail = responseEntity.getBody();
		log.info("problem detail: {}", problemDetail);
		Assertions.assertNotNull(problemDetail);
		Assertions.assertEquals("Customer Not Found", problemDetail.getTitle());
	}

	@Test
	void updateGenre() {
		var genreUpdateRequest = new GenreUpdateRequest(Genre.DRAMA);
		var requestEntity = new RequestEntity<>(genreUpdateRequest, HttpMethod.PATCH, URI.create("/api/customers/1/genre"));
		var responseEntity = this.template.exchange(requestEntity, Void.class);
		Assertions.assertEquals(204, responseEntity.getStatusCode().value());
	}
}
