package com.spotify.connectivity.sessionstate;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import p.lc;

/* JADX INFO: loaded from: classes.dex */
public class PaymentState implements Parcelable {
    public static final Parcelable.Creator<PaymentState> CREATOR = new Parcelable.Creator<PaymentState>() { // from class: com.spotify.connectivity.sessionstate.PaymentState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentState createFromParcel(Parcel parcel) {
            return new PaymentState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentState[] newArray(int i) {
            return new PaymentState[i];
        }
    };
    private static final String CREDITCARD_FUNDS = "creditcard_funds";
    private static final String CREDITCARD_REFUSED = "creditcard_refused";
    private static final String MANAGED_GRACE_START = "managed_grace_start";
    private static final String MANAGED_PROVIDER_GPB = "GPB";
    private static final String OPT_IN_TRIAL = "opt-in-trial";
    private static final String PAYPAL_REFUSED = "paypal_refused";
    private final String[] mSegments;

    private void trimSegments() {
        int i = 0;
        while (true) {
            String[] strArr = this.mSegments;
            if (i >= strArr.length) {
                return;
            }
            strArr[i] = strArr[i].trim();
            i++;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PaymentState) {
            return Arrays.equals(this.mSegments, ((PaymentState) obj).mSegments);
        }
        return false;
    }

    public int getActiveOptInTrialDurationInHours() {
        if (!hasActiveOptInTrial()) {
            return -1;
        }
        try {
            return Math.round(Integer.parseInt(this.mSegments[1]) / 3600.0f);
        } catch (NumberFormatException e) {
            lc.e("Caught a NumberFormatException when parsing trial length", e);
            return -1;
        }
    }

    public long getActiveOptInTrialExpiryTimestamp() {
        if (!hasActiveOptInTrial()) {
            return -1L;
        }
        try {
            return Long.parseLong(this.mSegments[2]);
        } catch (NumberFormatException e) {
            lc.e("Caught a NumberFormatException when parsing trial expiry", e);
            return -1L;
        }
    }

    public int getPaymentFailureRetryNumber() {
        if (!hasPaymentFailure()) {
            return -1;
        }
        String[] strArr = this.mSegments;
        if (strArr.length < 2) {
            return -1;
        }
        try {
            return Integer.parseInt(strArr[1]);
        } catch (NumberFormatException e) {
            lc.e("Failed to parse retry number of '" + toString() + '\'', e);
            return -1;
        }
    }

    public boolean hasActiveOptInTrial() {
        String[] strArr = this.mSegments;
        return strArr.length == 3 && OPT_IN_TRIAL.equals(strArr[0]);
    }

    public boolean hasCreditCardInsufficientFunds() {
        String[] strArr = this.mSegments;
        return strArr.length == 2 && CREDITCARD_FUNDS.equals(strArr[0]);
    }

    public boolean hasCreditCardRefused() {
        String[] strArr = this.mSegments;
        return strArr.length == 2 && CREDITCARD_REFUSED.equals(strArr[0]);
    }

    public boolean hasGooglePayFailure() {
        String[] strArr = this.mSegments;
        return strArr.length == 2 && MANAGED_GRACE_START.equals(strArr[0]) && MANAGED_PROVIDER_GPB.equals(this.mSegments[1]);
    }

    public boolean hasPaymentFailure() {
        return hasCreditCardInsufficientFunds() || hasCreditCardRefused() || hasPaypalRefused() || hasGooglePayFailure();
    }

    public boolean hasPaypalRefused() {
        String[] strArr = this.mSegments;
        return strArr.length == 2 && PAYPAL_REFUSED.equals(strArr[0]);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mSegments);
    }

    public String toString() {
        return TextUtils.join(";", this.mSegments);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(this.mSegments);
    }

    public PaymentState(String str) {
        if (str == null || str.isEmpty()) {
            this.mSegments = new String[0];
        } else {
            this.mSegments = str.split(";");
            trimSegments();
        }
    }

    private PaymentState(Parcel parcel) {
        this.mSegments = parcel.createStringArray();
    }
}
