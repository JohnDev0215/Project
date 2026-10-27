package com.android.bluetooth.hfp;

/* JADX INFO: loaded from: classes.dex */
class HeadsetClccResponse extends HeadsetMessageObject {
    int mDirection;
    int mIndex;
    int mMode;
    boolean mMpty;
    String mNumber;
    int mStatus;
    int mType;

    HeadsetClccResponse(int index, int direction, int status, int mode, boolean mpty, String number, int type) {
        this.mIndex = index;
        this.mDirection = direction;
        this.mStatus = status;
        this.mMode = mode;
        this.mMpty = mpty;
        this.mNumber = number;
        this.mType = type;
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getClass().getSimpleName());
        builder.append("[index=");
        builder.append(this.mIndex);
        builder.append(", direction=");
        builder.append(this.mDirection);
        builder.append(", status=");
        builder.append(this.mStatus);
        builder.append(", callMode=");
        builder.append(this.mMode);
        builder.append(", isMultiParty=");
        builder.append(this.mMpty);
        builder.append(", number=");
        if (this.mNumber == null) {
            builder.append("null");
        } else {
            builder.append("***");
        }
        builder.append(", type=");
        builder.append(this.mType);
        builder.append("]");
    }
}
