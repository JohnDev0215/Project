package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ApexSessionInfo implements Parcelable {
    public static final Parcelable.Creator<ApexSessionInfo> CREATOR = new Parcelable.Creator<ApexSessionInfo>() { // from class: android.apex.ApexSessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexSessionInfo createFromParcel(Parcel parcel) {
            ApexSessionInfo apexSessionInfo = new ApexSessionInfo();
            apexSessionInfo.readFromParcel(parcel);
            return apexSessionInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexSessionInfo[] newArray(int i) {
            return new ApexSessionInfo[i];
        }
    };
    public String crashingNativeProcess;
    public boolean isActivated;
    public boolean isActivationFailed;
    public boolean isRevertFailed;
    public boolean isRevertInProgress;
    public boolean isReverted;
    public boolean isStaged;
    public boolean isSuccess;
    public boolean isUnknown;
    public boolean isVerified;
    public int sessionId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.sessionId);
        parcel.writeInt(this.isUnknown ? 1 : 0);
        parcel.writeInt(this.isVerified ? 1 : 0);
        parcel.writeInt(this.isStaged ? 1 : 0);
        parcel.writeInt(this.isActivated ? 1 : 0);
        parcel.writeInt(this.isRevertInProgress ? 1 : 0);
        parcel.writeInt(this.isActivationFailed ? 1 : 0);
        parcel.writeInt(this.isSuccess ? 1 : 0);
        parcel.writeInt(this.isReverted ? 1 : 0);
        parcel.writeInt(this.isRevertFailed ? 1 : 0);
        parcel.writeString(this.crashingNativeProcess);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.sessionId = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                boolean z = true;
                this.isUnknown = parcel.readInt() != 0;
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.isVerified = parcel.readInt() != 0;
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.isStaged = parcel.readInt() != 0;
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.isActivated = parcel.readInt() != 0;
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.isRevertInProgress = parcel.readInt() != 0;
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.isActivationFailed = parcel.readInt() != 0;
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        this.isSuccess = parcel.readInt() != 0;
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.isReverted = parcel.readInt() != 0;
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                if (parcel.readInt() == 0) {
                                                    z = false;
                                                }
                                                this.isRevertFailed = z;
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                    this.crashingNativeProcess = parcel.readString();
                                                    if (parcel.dataPosition() - iDataPosition < i) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
