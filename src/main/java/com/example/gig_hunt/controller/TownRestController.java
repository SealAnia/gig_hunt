package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Town;
import com.example.gig_hunt.service.impl.TownServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/towns")
public class TownRestController {

    private final TownServiceImpl townService;

    @Autowired
    public TownRestController(TownServiceImpl townService) {
        this.townService = townService;
    }

    @GetMapping(value = "")
    public List<Town> getAllTowns() {
        return townService.getAll();
    }

    @GetMapping(value = "/{townId}")
    public Town getTownById(@PathVariable Long townId) {
        return townService.readById(townId);
    }

    @PostMapping(value = "/")
    public void createTown(@RequestBody Town town) {
        townService.createOrUpdate(town);
    }

    @PutMapping(value = "/{townId}")
    public ResponseEntity<Town> updateTown(@PathVariable Long townId, @RequestBody Town town) {
        Town updatedTown = townService.readById(townId);

        var region = town.getRegion();
        updatedTown.setRegion(region);

        updatedTown.setName(town.getName());
        townService.createOrUpdate(updatedTown);
        return ResponseEntity.ok(updatedTown);
    }

    @DeleteMapping(value = "/{townId}")
    public void deleteTown(@PathVariable Long townId) {
        townService.delete(townId);
    }

}
