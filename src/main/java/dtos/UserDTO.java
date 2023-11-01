package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "active", "email", "firstName", "lastName", "password", "username", "picture"})
public class UserDTO {
    private int id;
    private boolean active;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String picture;
}
