package resource;

import com.google.gson.Gson;
import operations.AccountOpImpl;
import operations.AccountOps;
import org.slf4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by alperul on 10/9/2017.
 */
@Path("userOperations")
public class IngenicoWebResource {

    private final Logger log = getLogger(getClass());

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "json/application; charset=utf-8";

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(String requestJson) {

        CreateAccountRequest request;
        Response restResponse;

        try {
            Gson gson = new Gson();
            request = gson.fromJson(requestJson, CreateAccountRequest.class);

            AccountOps accountOp = new AccountOpImpl();
            accountOp.createAccount(request);

            Response.ResponseBuilder rb = Response.status(Response.Status.OK);
            restResponse = rb.header(CONTENT_TYPE,
                    CONTENT_TYPE_VALUE).build();
        } catch (Exception e) {
            Response.ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            restResponse = rb.header(CONTENT_TYPE,
                    CONTENT_TYPE_VALUE).build();

            log.error("Error in createAccount", e);
        }
        return restResponse;
    }


    @POST
    @Path("transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferMoney(String requestJson) {

        TransferReq request;
        Response restResponse;

        try {
            Gson gson = new Gson();
            request = gson.fromJson(requestJson, TransferReq.class);

            AccountOps accountOp = new AccountOpImpl();
            accountOp.transferMoney(request);

            Response.ResponseBuilder rb = Response.status(Response.Status.OK);
            restResponse = rb.header(CONTENT_TYPE,
                    CONTENT_TYPE_VALUE).build();
        } catch (Exception e) {

            Response.ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            restResponse = rb.header(CONTENT_TYPE,
                    CONTENT_TYPE_VALUE).build();
            log.error("Error in transferMoney", e);
        }
        return restResponse;
    }
}
