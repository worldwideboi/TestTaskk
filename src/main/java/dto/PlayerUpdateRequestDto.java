package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerUpdateRequestDto{

    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;
}
