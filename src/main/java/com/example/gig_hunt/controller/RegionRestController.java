package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Region;
import com.example.gig_hunt.service.impl.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/regions")
public class RegionRestController {

    private final RegionServiceImpl regionService;

    @Autowired
    public RegionRestController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "")
    public List<Region> getAllRegions() {
        return regionService.getAll();
    }

    @GetMapping(value = "/{regionId}")
    public Region getRegionById(@PathVariable Long regionId) {
        return regionService.readById(regionId);
    }

    @PostMapping(value = "/")
    public void createRegion(@RequestBody Region region) {
        regionService.createOrUpdate(region);
    }

    @PutMapping(value = "/{regionId}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long regionId, @RequestBody Region region) {
        Region updatedRegion = regionService.readById(regionId);
        updatedRegion.setName(region.getName());
        regionService.createOrUpdate(updatedRegion);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping(value = "/{regionId}")
    public void deleteRegion(@PathVariable Long regionId) {
        regionService.delete(regionId);
    }

}
