package com.android.bluetooth.hfp;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class HeadsetCallState extends HeadsetMessageObject {
    int mCallState;
    String mName;
    int mNumActive;
    int mNumHeld;
    String mNumber;
    int mType;

    HeadsetCallState(int numActive, int numHeld, int callState, String number, int type, String name) {
        this.mNumActive = numActive;
        this.mNumHeld = numHeld;
        this.mCallState = callState;
        this.mNumber = number;
        this.mType = type;
        this.mName = name;
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getClass().getSimpleName());
        builder.append("[numActive=");
        builder.append(this.mNumActive);
        builder.append(", numHeld=");
        builder.append(this.mNumHeld);
        builder.append(", callState=");
        builder.append(this.mCallState);
        builder.append(", number=");
        if (this.mNumber == null) {
            builder.append("null");
        } else {
            builder.append("***");
        }
        builder.append(", type=");
        builder.append(this.mType);
        builder.append(", name=");
        if (this.mName == null) {
            builder.append("null");
        } else {
            builder.append("***");
        }
        builder.append("]");
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HeadsetCallState)) {
            return false;
        }
        HeadsetCallState that = (HeadsetCallState) object;
        return this.mNumActive == that.mNumActive && this.mNumHeld == that.mNumHeld && this.mCallState == that.mCallState && Objects.equals(this.mNumber, that.mNumber) && this.mType == that.mType && Objects.equals(this.mName, that.mName);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mNumActive), Integer.valueOf(this.mNumHeld), Integer.valueOf(this.mCallState), this.mNumber, Integer.valueOf(this.mType), this.mName);
    }
}
