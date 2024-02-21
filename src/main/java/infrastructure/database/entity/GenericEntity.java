package infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class GenericEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private long createdOn;
    private long updatedOn;
    private UUID createdBy;
    private UUID updatedBy;
}