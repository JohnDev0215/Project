package com.spotify.connectivity.sessionstate;

import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import com.spotify.connectivity.sessionstate.SessionState;
import p.u4;

/* JADX INFO: renamed from: com.spotify.connectivity.sessionstate.$AutoValue_SessionState, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
abstract class C$AutoValue_SessionState extends SessionState {
    private final boolean canStream;
    private final boolean connected;
    private final String countryCode;
    private final int currentAccountType;
    private final String currentUser;
    private final boolean loggedIn;
    private final boolean loggingIn;
    private final boolean loggingOut;
    private final AuthLogoutReason logoutReason;
    private final PaymentState paymentState;
    private final String productType;

    /* JADX INFO: renamed from: com.spotify.connectivity.sessionstate.$AutoValue_SessionState$Builder */
    public static class Builder extends SessionState.Builder {
        private Boolean canStream;
        private Boolean connected;
        private String countryCode;
        private Integer currentAccountType;
        private String currentUser;
        private Boolean loggedIn;
        private Boolean loggingIn;
        private Boolean loggingOut;
        private AuthLogoutReason logoutReason;
        private PaymentState paymentState;
        private String productType;

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState build() {
            String strH = this.currentUser == null ? " currentUser" : "";
            if (this.loggedIn == null) {
                strH = u4.h(strH, " loggedIn");
            }
            if (this.loggingIn == null) {
                strH = u4.h(strH, " loggingIn");
            }
            if (this.loggingOut == null) {
                strH = u4.h(strH, " loggingOut");
            }
            if (this.currentAccountType == null) {
                strH = u4.h(strH, " currentAccountType");
            }
            if (this.countryCode == null) {
                strH = u4.h(strH, " countryCode");
            }
            if (this.connected == null) {
                strH = u4.h(strH, " connected");
            }
            if (this.canStream == null) {
                strH = u4.h(strH, " canStream");
            }
            if (this.paymentState == null) {
                strH = u4.h(strH, " paymentState");
            }
            if (this.productType == null) {
                strH = u4.h(strH, " productType");
            }
            if (strH.isEmpty()) {
                return new AutoValue_SessionState(this.currentUser, this.loggedIn.booleanValue(), this.loggingIn.booleanValue(), this.loggingOut.booleanValue(), this.currentAccountType.intValue(), this.logoutReason, this.countryCode, this.connected.booleanValue(), this.canStream.booleanValue(), this.paymentState, this.productType);
            }
            throw new IllegalStateException("Missing required properties:".concat(strH));
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder canStream(boolean z) {
            this.canStream = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder connected(boolean z) {
            this.connected = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder countryCode(String str) {
            if (str == null) {
                throw new NullPointerException("Null countryCode");
            }
            this.countryCode = str;
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder currentAccountType(int i) {
            this.currentAccountType = Integer.valueOf(i);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder currentUser(String str) {
            if (str == null) {
                throw new NullPointerException("Null currentUser");
            }
            this.currentUser = str;
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder loggedIn(boolean z) {
            this.loggedIn = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder loggingIn(boolean z) {
            this.loggingIn = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder loggingOut(boolean z) {
            this.loggingOut = Boolean.valueOf(z);
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder logoutReason(AuthLogoutReason authLogoutReason) {
            this.logoutReason = authLogoutReason;
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder paymentState(PaymentState paymentState) {
            if (paymentState == null) {
                throw new NullPointerException("Null paymentState");
            }
            this.paymentState = paymentState;
            return this;
        }

        @Override // com.spotify.connectivity.sessionstate.SessionState.Builder
        public SessionState.Builder productType(String str) {
            if (str == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = str;
            return this;
        }

        public Builder() {
        }

        private Builder(SessionState sessionState) {
            this.currentUser = sessionState.currentUser();
            this.loggedIn = Boolean.valueOf(sessionState.loggedIn());
            this.loggingIn = Boolean.valueOf(sessionState.loggingIn());
            this.loggingOut = Boolean.valueOf(sessionState.loggingOut());
            this.currentAccountType = Integer.valueOf(sessionState.currentAccountType());
            this.logoutReason = sessionState.logoutReason();
            this.countryCode = sessionState.countryCode();
            this.connected = Boolean.valueOf(sessionState.connected());
            this.canStream = Boolean.valueOf(sessionState.canStream());
            this.paymentState = sessionState.paymentState();
            this.productType = sessionState.productType();
        }
    }

    public C$AutoValue_SessionState(String str, boolean z, boolean z2, boolean z3, int i, AuthLogoutReason authLogoutReason, String str2, boolean z4, boolean z5, PaymentState paymentState, String str3) {
        if (str == null) {
            throw new NullPointerException("Null currentUser");
        }
        this.currentUser = str;
        this.loggedIn = z;
        this.loggingIn = z2;
        this.loggingOut = z3;
        this.currentAccountType = i;
        this.logoutReason = authLogoutReason;
        if (str2 == null) {
            throw new NullPointerException("Null countryCode");
        }
        this.countryCode = str2;
        this.connected = z4;
        this.canStream = z5;
        if (paymentState == null) {
            throw new NullPointerException("Null paymentState");
        }
        this.paymentState = paymentState;
        if (str3 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = str3;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    @Deprecated
    public boolean canStream() {
        return this.canStream;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    @Deprecated
    public boolean connected() {
        return this.connected;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    @Deprecated
    public String countryCode() {
        return this.countryCode;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public int currentAccountType() {
        return this.currentAccountType;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public String currentUser() {
        return this.currentUser;
    }

    public boolean equals(Object obj) {
        AuthLogoutReason authLogoutReason;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionState)) {
            return false;
        }
        SessionState sessionState = (SessionState) obj;
        return this.currentUser.equals(sessionState.currentUser()) && this.loggedIn == sessionState.loggedIn() && this.loggingIn == sessionState.loggingIn() && this.loggingOut == sessionState.loggingOut() && this.currentAccountType == sessionState.currentAccountType() && ((authLogoutReason = this.logoutReason) != null ? authLogoutReason.equals(sessionState.logoutReason()) : sessionState.logoutReason() == null) && this.countryCode.equals(sessionState.countryCode()) && this.connected == sessionState.connected() && this.canStream == sessionState.canStream() && this.paymentState.equals(sessionState.paymentState()) && this.productType.equals(sessionState.productType());
    }

    public int hashCode() {
        int iHashCode = (((((((((this.currentUser.hashCode() ^ 1000003) * 1000003) ^ (this.loggedIn ? 1231 : 1237)) * 1000003) ^ (this.loggingIn ? 1231 : 1237)) * 1000003) ^ (this.loggingOut ? 1231 : 1237)) * 1000003) ^ this.currentAccountType) * 1000003;
        AuthLogoutReason authLogoutReason = this.logoutReason;
        return ((((((((((iHashCode ^ (authLogoutReason == null ? 0 : authLogoutReason.hashCode())) * 1000003) ^ this.countryCode.hashCode()) * 1000003) ^ (this.connected ? 1231 : 1237)) * 1000003) ^ (this.canStream ? 1231 : 1237)) * 1000003) ^ this.paymentState.hashCode()) * 1000003) ^ this.productType.hashCode();
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public boolean loggedIn() {
        return this.loggedIn;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public boolean loggingIn() {
        return this.loggingIn;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public boolean loggingOut() {
        return this.loggingOut;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public AuthLogoutReason logoutReason() {
        return this.logoutReason;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    @Deprecated
    public PaymentState paymentState() {
        return this.paymentState;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    @Deprecated
    public String productType() {
        return this.productType;
    }

    @Override // com.spotify.connectivity.sessionstate.SessionState
    public SessionState.Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SessionState{currentUser=");
        sb.append(this.currentUser);
        sb.append(", loggedIn=");
        sb.append(this.loggedIn);
        sb.append(", loggingIn=");
        sb.append(this.loggingIn);
        sb.append(", loggingOut=");
        sb.append(this.loggingOut);
        sb.append(", currentAccountType=");
        sb.append(this.currentAccountType);
        sb.append(", logoutReason=");
        sb.append(this.logoutReason);
        sb.append(", countryCode=");
        sb.append(this.countryCode);
        sb.append(", connected=");
        sb.append(this.connected);
        sb.append(", canStream=");
        sb.append(this.canStream);
        sb.append(", paymentState=");
        sb.append(this.paymentState);
        sb.append(", productType=");
        return u4.m(sb, this.productType, "}");
    }
}
