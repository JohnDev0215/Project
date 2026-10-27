package com.amazon.music.account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class Device {
    private final AuthorizationState authorizationState;
    private final AvailableState availableState;
    private final Set<Capability> capabilities;

    public enum AuthorizationState {
        NEVER_AUTHORIZED,
        AUTHORIZED,
        DEAUTHORIZED
    }

    public enum AvailableState {
        AUTHORIZABLE,
        TRANSFERRABLE,
        DEAUTHORIZABLE,
        NONTRANSFERRABLE
    }

    public enum Capability {
        RETRIEVE_ROBIN_CONTENT,
        RETRIEVE_OWNED_CONTENT
    }

    public Device(@Nullable AuthorizationState authorizationState, @Nullable AvailableState availableState, @NonNull Set<Capability> set) {
        this.authorizationState = authorizationState;
        this.availableState = availableState;
        this.capabilities = set;
    }

    @Nullable
    public AuthorizationState getAuthorizationState() {
        return this.authorizationState;
    }

    @Nullable
    public AvailableState getAvailableState() {
        return this.availableState;
    }

    @NonNull
    public Set<Capability> getCapabilities() {
        return this.capabilities;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Device device = (Device) obj;
        return equals(this.authorizationState, device.authorizationState) && equals(this.availableState, device.availableState) && equals(this.capabilities, device.capabilities);
    }

    public int hashCode() {
        AuthorizationState authorizationState = this.authorizationState;
        int iHashCode = (authorizationState == null ? 0 : authorizationState.hashCode()) * 31;
        AvailableState availableState = this.availableState;
        return ((iHashCode + (availableState != null ? availableState.hashCode() : 0)) * 31) + this.capabilities.hashCode();
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
