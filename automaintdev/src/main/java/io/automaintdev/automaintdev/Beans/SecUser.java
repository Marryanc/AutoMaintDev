package io.automaintdev.automaintdev.Beans;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SecUser {
    private Long userId;
    @NonNull
    private String email;
    @NonNull
    private String encryptedPassword;
    @NonNull
    private Boolean enabled;
    // Added this to hold the value of there roles when in admin pannel
    private List<SecRole> roles = null;
}
