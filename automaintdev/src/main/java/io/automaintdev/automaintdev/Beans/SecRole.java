package io.automaintdev.automaintdev.Beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SecRole {
    private Long roleId;
    @NonNull
    private String roleName;
}
