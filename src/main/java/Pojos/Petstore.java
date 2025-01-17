package Pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Petstore {
    private int id;
    private String username;
    private String firstName;

    private String  lastName;
    private String   email;

    private String password;
    private String phone;
    private int userStatus;

}
