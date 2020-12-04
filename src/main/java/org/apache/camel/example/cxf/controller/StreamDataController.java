package org.apache.camel.example.cxf.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.camel.Body;

public class StreamDataController {

       @POST
       @Consumes("text/plain")
       @Produces("text/plain")
       public Response getStreamData(@Body String data) {
              return null;
       }

}

