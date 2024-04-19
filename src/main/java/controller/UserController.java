package controller;

import dto.SignUpRequest;
import infrastructure.exception.UserAlreadyExistsException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.UserService;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response signUp(@Valid final SignUpRequest signUpRequest) {
        try {
            userService.signUp(signUpRequest);
        } catch (final UserAlreadyExistsException userAlreadyExistsException) {
            return Response.status(Response.Status.CONFLICT).entity(userAlreadyExistsException.getMessage()).build();
        }

        return Response.status(Response.Status.CREATED).entity().build();
    }
}
