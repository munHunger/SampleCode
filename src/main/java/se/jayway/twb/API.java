package se.jayway.twb;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Path("/")
public class API
{
    public static List<Model> list = new ArrayList<Model>();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get()
    {

        return Response.status(200).entity(new Model("Hello world")).header("authentication", "token sadjahsdlashd").build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Model m)
    {
        try
        {
            Database.saveObject(m);
            return Response.status(HttpServletResponse.SC_NO_CONTENT).build();
        }
        catch (Exception e)
        {
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
