package com.stekodyne.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by steffen on 12/21/16.
 */
@RestController
@EnableSwagger2
@RequestMapping("/map")
public class MapController extends RootController {

    static Logger log = LoggerFactory.getLogger(MapController.class.getName());

    @ApiOperation(value = "getKml", nickname = "getKml")
    @RequestMapping(method = RequestMethod.GET, path = "/getKml", produces = "application/vnd.google-earth.kml+xml")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<String> getKml() {
        ResponseEntity<String> response = null;

        try {
            response = new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
