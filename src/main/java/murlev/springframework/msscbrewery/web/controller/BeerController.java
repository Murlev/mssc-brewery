package murlev.springframework.msscbrewery.web.controller;

import lombok.AllArgsConstructor;
import murlev.springframework.msscbrewery.web.model.BeerDto;
import murlev.springframework.msscbrewery.web.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto) {
        BeerDto savedDto = beerService.saveNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@RequestBody BeerDto beerDto){
        beerService.updateBear(beerId,beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }
}
