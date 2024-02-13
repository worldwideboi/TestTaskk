    package dto;

    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class PlayerCreateResponseDto {
        private Integer age;
        private String gender;
        private Integer id;
        private String login;
        private String password;
        private String role;
        private String screenName;
    }
