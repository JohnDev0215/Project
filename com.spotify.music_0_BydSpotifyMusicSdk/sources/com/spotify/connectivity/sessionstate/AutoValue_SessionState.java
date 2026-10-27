package com.spotify.connectivity.sessionstate;

import android.os.Parcel;
import android.os.Parcelable;
import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import com.spotify.connectivity.sessionstate.SessionState;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_SessionState extends C$AutoValue_SessionState {
    private static final SessionState.PaymentStateAdapter PAYMENT_STATE_ADAPTER = new SessionState.PaymentStateAdapter();
    public static final Parcelable.Creator<AutoValue_SessionState> CREATOR = new Parcelable.Creator<AutoValue_SessionState>() { // from class: com.spotify.connectivity.sessionstate.AutoValue_SessionState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoValue_SessionState createFromParcel(Parcel parcel) {
            return new AutoValue_SessionState(parcel.readString(), parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt(), parcel.readInt() == 0 ? (AuthLogoutReason) Enum.valueOf(AuthLogoutReason.class, parcel.readString()) : null, parcel.readString(), parcel.readInt() == 1, parcel.readInt() == 1, AutoValue_SessionState.PAYMENT_STATE_ADAPTER.m193fromParcel(parcel), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoValue_SessionState[] newArray(int i) {
            return new AutoValue_SessionState[i];
        }
    };

    public AutoValue_SessionState(String str, boolean z, boolean z2, boolean z3, int i, AuthLogoutReason authLogoutReason, String str2, boolean z4, boolean z5, PaymentState paymentState, String str3) {
        super(str, z, z2, z3, i, authLogoutReason, str2, z4, z5, paymentState, str3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(currentUser());
        parcel.writeInt(loggedIn() ? 1 : 0);
        parcel.writeInt(loggingIn() ? 1 : 0);
        parcel.writeInt(loggingOut() ? 1 : 0);
        parcel.writeInt(currentAccountType());
        if (logoutReason() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(logoutReason().name());
        }
        parcel.writeString(countryCode());
        parcel.writeInt(connected() ? 1 : 0);
        parcel.writeInt(canStream() ? 1 : 0);
        PAYMENT_STATE_ADAPTER.toParcel(paymentState(), parcel);
        parcel.writeString(productType());
    }
}
