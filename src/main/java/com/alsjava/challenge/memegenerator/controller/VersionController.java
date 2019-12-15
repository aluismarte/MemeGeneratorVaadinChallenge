package com.alsjava.challenge.memegenerator.controller;

import com.alsjava.challenge.memegenerator.model.VersionAndBuild;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by aluis on 12/14/19.
 */
@RestController
public class VersionController {

    private final BuildProperties buildProperties;

    public VersionController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public ResponseEntity<VersionAndBuild> version() {
        return ResponseEntity.ok(new VersionAndBuild(buildProperties.getName(), buildProperties.getGroup(), buildProperties.getVersion(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.of("America/Santo_Domingo")).format(buildProperties.getTime())));
    }
}
