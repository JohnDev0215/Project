package com.android.internal.compat;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public final class OverrideAllowedState implements Parcelable {
    public static final int ALLOWED = 0;
    public static final Parcelable.Creator<OverrideAllowedState> CREATOR = new Parcelable.Creator<OverrideAllowedState>() { // from class: com.android.internal.compat.OverrideAllowedState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverrideAllowedState createFromParcel(Parcel parcel) {
            return new OverrideAllowedState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverrideAllowedState[] newArray(int i) {
            return new OverrideAllowedState[i];
        }
    };
    public static final int DISABLED_NON_TARGET_SDK = 2;
    public static final int DISABLED_NOT_DEBUGGABLE = 1;
    public static final int DISABLED_TARGET_SDK_TOO_HIGH = 3;
    public static final int LOGGING_ONLY_CHANGE = 5;
    public static final int PACKAGE_DOES_NOT_EXIST = 4;
    public final int appTargetSdk;
    public final int changeIdTargetSdk;
    public final int state;

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private OverrideAllowedState(Parcel parcel) {
        this.state = parcel.readInt();
        this.appTargetSdk = parcel.readInt();
        this.changeIdTargetSdk = parcel.readInt();
    }

    public OverrideAllowedState(int i, int i2, int i3) {
        this.state = i;
        this.appTargetSdk = i2;
        this.changeIdTargetSdk = i3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.state);
        parcel.writeInt(this.appTargetSdk);
        parcel.writeInt(this.changeIdTargetSdk);
    }

    public void enforce(long j, String str) throws SecurityException {
        int i = this.state;
        if (i == 1) {
            throw new SecurityException("Cannot override a change on a non-debuggable app and user build.");
        }
        if (i == 2) {
            throw new SecurityException("Cannot override a default enabled/disabled change on a user build.");
        }
        if (i == 3) {
            throw new SecurityException(String.format("Cannot override %1$d for %2$s because the app's targetSdk (%3$d) is above the change's targetSdk threshold (%4$d)", Long.valueOf(j), str, Integer.valueOf(this.appTargetSdk), Integer.valueOf(this.changeIdTargetSdk)));
        }
        if (i == 4) {
            throw new SecurityException(String.format("Cannot override %1$d for %2$s because the package does not exist, and the change is targetSdk gated.", Long.valueOf(j), str));
        }
        if (i == 5) {
            throw new SecurityException(String.format("Cannot override %1$d because it is marked as a logging-only change.", Long.valueOf(j)));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OverrideAllowedState)) {
            return false;
        }
        OverrideAllowedState overrideAllowedState = (OverrideAllowedState) obj;
        return this.state == overrideAllowedState.state && this.appTargetSdk == overrideAllowedState.appTargetSdk && this.changeIdTargetSdk == overrideAllowedState.changeIdTargetSdk;
    }

    private String stateName() {
        int i = this.state;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN" : "LOGGING_ONLY_CHANGE" : "PACKAGE_DOES_NOT_EXIST" : "DISABLED_TARGET_SDK_TOO_HIGH" : "DISABLED_NON_TARGET_SDK" : "DISABLED_NOT_DEBUGGABLE" : "ALLOWED";
    }

    public String toString() {
        return "OverrideAllowedState(state=" + stateName() + "; appTargetSdk=" + this.appTargetSdk + "; changeIdTargetSdk=" + this.changeIdTargetSdk + ")";
    }
}
