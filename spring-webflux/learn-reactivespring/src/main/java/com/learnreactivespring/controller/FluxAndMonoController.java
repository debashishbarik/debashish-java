package com.learnreactivespring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux(){

        return Flux.just(1,2,3,4)
               // .delayElements(Duration.ofSeconds(1))
                .log();

    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> returnFluxStream(){

        return Flux.interval(Duration.ofSeconds(1))
                .log();

    }

    @GetMapping("/mono")
    public Mono<Integer> returnMono(){

        return Mono.just(1)
                .log();

    }
/*
@Service
public class MovieServiceImpl implements MovieService {

 @Autowired
 private MovieRepository movieRepository;

@Override
    public Mono<Movie> update(String id, MovieRequest movieRequest) {

       return movieRepository.findById(id).flatMap(existingMovie -> {

           if(movieRequest.getDescription() != null){
               existingMovie.setDescription(movieRequest.getDescription());
           }
           if(movieRequest.getRating() != null){
               existingMovie.setRating(movieRequest.getRating());
           }
           if(movieRequest.getTitle() != null) {
               existingMovie.setTitle(movieRequest.getTitle());
           }

           return movieRepository.save(existingMovie);

       });
    }
}

@RestController
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @PutMapping("/movies/{movieId}")
    public Mono<ResponseEntity<Movie>> update(
            @PathVariable("movieId") final String movieId,
            @RequestBody final MovieRequest movieRequest) {

        return movieService.update(movieId, movieRequest)
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}*/
}
