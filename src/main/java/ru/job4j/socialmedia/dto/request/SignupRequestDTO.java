package ru.job4j.socialmedia.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank
    @Length(min = 4,
            max = 10,
            message = "login должен быть не менее 6 и не более 10 символов")
    private String login;

    @NotBlank
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Length(min = 6,
            max = 10,
            message = "password должен быть не менее 6 и не более 10 символов")
    private String password;
}
