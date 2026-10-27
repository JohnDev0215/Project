package com.amazon.identity.auth.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AccountStatusPolicy implements Parcelable {
    public static final Parcelable.Creator<AccountStatusPolicy> CREATOR = new Parcelable.Creator<AccountStatusPolicy>() { // from class: com.amazon.identity.auth.device.api.AccountStatusPolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountStatusPolicy createFromParcel(Parcel parcel) {
            return new AccountStatusPolicy(Policy.values()[parcel.readInt()]);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountStatusPolicy[] newArray(int i) {
            return new AccountStatusPolicy[i];
        }
    };
    private final Policy mPolicy;

    /* JADX INFO: compiled from: DCP */
    public enum Policy {
        DEFAULT("DEFAULT"),
        P1("P1");

        private final String mValue;

        Policy(String str) {
            this.mValue = str;
        }

        @FireOsSdk
        public String getValue() {
            return this.mValue;
        }
    }

    @FireOsSdk
    public AccountStatusPolicy(Policy policy) {
        this.mPolicy = policy;
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public int describeContents() {
        return 0;
    }

    public Policy getAccountStatusPolicy() {
        return this.mPolicy;
    }

    @Override // android.os.Parcelable
    @FireOsSdk
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPolicy.ordinal());
    }
}
