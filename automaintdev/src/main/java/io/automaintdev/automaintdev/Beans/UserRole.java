package io.automaintdev.automaintdev.Beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRole {
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private Long roleId;
}
