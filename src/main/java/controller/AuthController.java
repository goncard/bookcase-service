package controller;

import dto.AuthRequest;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.AuthService;

@Path("session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @POST
    @PermitAll
    public Response login(@Valid final AuthRequest authRequest) {
        try {
            return Response.ok(this.authService.login(authRequest)).build();
        } catch (UnauthorizedException unauthorizedException) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(unauthorizedException.getMessage()).build();
        } catch (NotFoundException notFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundException.getMessage()).build();
        }
    }
}
