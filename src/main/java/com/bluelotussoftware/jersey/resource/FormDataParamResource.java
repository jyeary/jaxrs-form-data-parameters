/*
 * Copyright 2017 Blue Lotus Software, LLC.
 * Copyright 2017 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotussoftware.jersey.resource;

import com.bluelotussoftware.jersey.model.ValueHolder;
import java.text.MessageFormat;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * A form parameter contained within a request entity body to a resource method
 * parameter example.
 *
 * @author John Yeary
 * @version 1.0.0
 */
@Path("frm")
public class FormDataParamResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FormDataParamResource
     */
    public FormDataParamResource() {
    }

    /**
     * Retrieves representation of an instance of
     * resources.FormDataParamResource
     *
     * @return an empty JSON String.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response get() {
        return Response.ok("{}", MediaType.APPLICATION_JSON).build();
    }

    /**
     * PUT method for updating or creating an instance of FormDataParamResource
     *
     * @param content representation for the resource
     * @return A 202 - Accepted response.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response put(String content) {
        return Response.accepted().build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA})
    public Response post(
            @FormParam(value = "name") String name,
            @FormParam(value = "value") String value) {
        try {
            ValueHolder vh = new ValueHolder(name, value);
            System.out.println(MessageFormat.format("Submitted values: {0}", vh));
            return Response.status(Response.Status.CREATED).entity(vh).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
