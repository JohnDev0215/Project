package com.amplifyframework.auth;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthUser {
    private String userId;
    private String username;

    public AuthUser(@NonNull String str, @NonNull String str2) {
        Objects.requireNonNull(str);
        this.userId = str;
        Objects.requireNonNull(str2);
        this.username = str2;
    }

    @NonNull
    public String getUserId() {
        return this.userId;
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    public int hashCode() {
        return ObjectsCompat.hash(getUserId(), getUsername());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthUser.class != obj.getClass()) {
            return false;
        }
        AuthUser authUser = (AuthUser) obj;
        return ObjectsCompat.equals(getUserId(), authUser.getUserId()) && ObjectsCompat.equals(getUsername(), authUser.getUsername());
    }

    public String toString() {
        return "AuthUser{userId='" + this.userId + "', username='" + this.username + "'}";
    }
}
