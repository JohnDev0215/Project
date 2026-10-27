package com.spotify.connectivity.connectiontype;

import com.spotify.connectivity.connectiontype.InternetState;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_InternetState extends InternetState {
    private final ConnectionType connectionType;
    private final boolean flightModeEnabled;
    private final boolean mobileDataDisabled;

    public static final class Builder implements InternetState.Builder {
        private ConnectionType connectionType;
        private Boolean flightModeEnabled;
        private Boolean mobileDataDisabled;

        @Override // com.spotify.connectivity.connectiontype.InternetState.Builder
        public InternetState build() {
            String strH = this.connectionType == null ? " connectionType" : "";
            if (this.flightModeEnabled == null) {
                strH = u4.h(strH, " flightModeEnabled");
            }
            if (this.mobileDataDisabled == null) {
                strH = u4.h(strH, " mobileDataDisabled");
            }
            if (strH.isEmpty()) {
                return new AutoValue_InternetState(this.connectionType, this.flightModeEnabled.booleanValue(), this.mobileDataDisabled.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.connectivity.connectiontype.InternetState.Builder
        public InternetState.Builder connectionType(ConnectionType connectionType) {
            if (connectionType == null) {
                throw new NullPointerException("Null connectionType");
            }
            this.connectionType = connectionType;
            return this;
        }

        @Override // com.spotify.connectivity.connectiontype.InternetState.Builder
        public InternetState.Builder flightModeEnabled(boolean z) {
            this.flightModeEnabled = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.connectiontype.InternetState.Builder
        public InternetState.Builder mobileDataDisabled(boolean z) {
            this.mobileDataDisabled = Boolean.valueOf(z);
            return this;
        }

        public Builder() {
        }

        private Builder(InternetState internetState) {
            this.connectionType = internetState.connectionType();
            this.flightModeEnabled = Boolean.valueOf(internetState.flightModeEnabled());
            this.mobileDataDisabled = Boolean.valueOf(internetState.mobileDataDisabled());
        }
    }

    @Override // com.spotify.connectivity.connectiontype.InternetState
    public ConnectionType connectionType() {
        return this.connectionType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InternetState)) {
            return false;
        }
        InternetState internetState = (InternetState) obj;
        return this.connectionType.equals(internetState.connectionType()) && this.flightModeEnabled == internetState.flightModeEnabled() && this.mobileDataDisabled == internetState.mobileDataDisabled();
    }

    @Override // com.spotify.connectivity.connectiontype.InternetState
    public boolean flightModeEnabled() {
        return this.flightModeEnabled;
    }

    public int hashCode() {
        return ((((this.connectionType.hashCode() ^ 1000003) * 1000003) ^ (this.flightModeEnabled ? 1231 : 1237)) * 1000003) ^ (this.mobileDataDisabled ? 1231 : 1237);
    }

    @Override // com.spotify.connectivity.connectiontype.InternetState
    public boolean mobileDataDisabled() {
        return this.mobileDataDisabled;
    }

    @Override // com.spotify.connectivity.connectiontype.InternetState
    public InternetState.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "InternetState{connectionType=" + this.connectionType + ", flightModeEnabled=" + this.flightModeEnabled + ", mobileDataDisabled=" + this.mobileDataDisabled + "}";
    }

    private AutoValue_InternetState(ConnectionType connectionType, boolean z, boolean z2) {
        this.connectionType = connectionType;
        this.flightModeEnabled = z;
        this.mobileDataDisabled = z2;
    }
}
