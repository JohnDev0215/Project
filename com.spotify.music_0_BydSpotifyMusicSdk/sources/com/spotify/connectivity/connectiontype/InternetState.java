package com.spotify.connectivity.connectiontype;

import com.spotify.connectivity.connectiontype.AutoValue_InternetState;

/* JADX INFO: loaded from: classes.dex */
public abstract class InternetState {

    public interface Builder {
        InternetState build();

        Builder connectionType(ConnectionType connectionType);

        Builder flightModeEnabled(boolean z);

        Builder mobileDataDisabled(boolean z);
    }

    public static Builder builder() {
        return new AutoValue_InternetState.Builder().connectionType(ConnectionType.CONNECTION_TYPE_UNKNOWN).flightModeEnabled(false).mobileDataDisabled(false);
    }

    public static InternetState create(ConnectionType connectionType, boolean z, boolean z2) {
        return new AutoValue_InternetState.Builder().connectionType(connectionType).flightModeEnabled(z).mobileDataDisabled(z2).build();
    }

    public abstract ConnectionType connectionType();

    public abstract boolean flightModeEnabled();

    public abstract boolean mobileDataDisabled();

    public abstract Builder toBuilder();
}
