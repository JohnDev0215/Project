package com.spotify.connectivity.sessionstate;

import android.os.Parcelable;
import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import com.spotify.connectivity.sessionstate.C$AutoValue_SessionState;

/* JADX INFO: loaded from: classes.dex */
public abstract class SessionState implements Parcelable {

    @Deprecated
    public static final String PRODUCT_TYPE_FREE = "free";

    @Deprecated
    public static final String PRODUCT_TYPE_PREMIUM = "premium";

    public static abstract class Builder {
        public abstract SessionState build();

        public abstract Builder canStream(boolean z);

        public abstract Builder connected(boolean z);

        public abstract Builder countryCode(String str);

        public abstract Builder currentAccountType(int i);

        public abstract Builder currentUser(String str);

        public abstract Builder loggedIn(boolean z);

        public abstract Builder loggingIn(boolean z);

        public abstract Builder loggingOut(boolean z);

        public abstract Builder logoutReason(AuthLogoutReason authLogoutReason);

        public abstract Builder paymentState(PaymentState paymentState);

        public abstract Builder productType(String str);
    }

    public static final class PaymentStateAdapter extends OptimizedParcelableTypeAdapter<PaymentState> {
        public PaymentStateAdapter() {
            super(PaymentState.CREATOR);
        }
    }

    public static Builder builder() {
        return new C$AutoValue_SessionState.Builder().currentUser("").loggedIn(false).loggingIn(false).loggingOut(false).currentAccountType(0).countryCode("").connected(false).canStream(false).paymentState(new PaymentState((String) null)).logoutReason(null).productType("");
    }

    public static SessionState create(String str, boolean z, boolean z2, boolean z3, int i, AuthLogoutReason authLogoutReason, String str2, boolean z4, boolean z5, String str3, String str4) {
        return new AutoValue_SessionState(str, z, z2, z3, i, authLogoutReason, str2, z4, z5, new PaymentState(str3), str4);
    }

    @Deprecated
    public abstract boolean canStream();

    @Deprecated
    public abstract boolean connected();

    @Deprecated
    public abstract String countryCode();

    public abstract int currentAccountType();

    public abstract String currentUser();

    public abstract boolean loggedIn();

    public abstract boolean loggingIn();

    public abstract boolean loggingOut();

    public abstract AuthLogoutReason logoutReason();

    @Deprecated
    public abstract PaymentState paymentState();

    @Deprecated
    public abstract String productType();

    public abstract Builder toBuilder();
}
