package net.likelion.backendschoolplus.entity;

import lombok.Getter;

public enum UserRole {
    ADMIN("관리자"),
    USER("사용자");

    public final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }
}
