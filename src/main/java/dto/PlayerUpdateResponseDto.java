package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerUpdateResponseDto{

    private Integer age;
    private String gender;
    private Integer id;
    private String login;
    private String role;
    private String screenName;
}
