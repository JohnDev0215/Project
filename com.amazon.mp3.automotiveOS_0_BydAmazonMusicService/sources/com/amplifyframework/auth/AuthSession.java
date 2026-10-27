package com.amplifyframework.auth;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes5.dex */
public class AuthSession {
    private final boolean isSignedIn;

    public AuthSession(boolean z) {
        this.isSignedIn = z;
    }

    @NonNull
    public boolean isSignedIn() {
        return this.isSignedIn;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isSignedIn()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(Boolean.valueOf(isSignedIn()), Boolean.valueOf(((AuthSession) obj).isSignedIn()));
    }

    public String toString() {
        return "AuthSession{isSignedIn=" + this.isSignedIn + '}';
    }
}
