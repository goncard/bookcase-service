package mapper;

import dto.SignUpRequest;
import infrastructure.database.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(SignUpRequest signUpRequest);
}
