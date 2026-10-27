package com.byd.music.protocol.model.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.music.protocol.ProtocolResultCode;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolErrorModel extends ProtocolBaseModel implements Parcelable {
    public static final Parcelable.Creator<ProtocolErrorModel> CREATOR = new Parcelable.Creator<ProtocolErrorModel>() { // from class: com.byd.music.protocol.model.base.ProtocolErrorModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolErrorModel createFromParcel(Parcel parcel) {
            return new ProtocolErrorModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolErrorModel[] newArray(int i) {
            return new ProtocolErrorModel[i];
        }
    };
    private int errorCode;
    private String errorMessage;

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel
    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel
    public String getErrorMessage() {
        if (this.errorCode > 0) {
            this.errorMessage = ProtocolResultCode.RESULT_MESSAGE.get(Integer.valueOf(this.errorCode));
        }
        return this.errorMessage;
    }

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel
    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public ProtocolErrorModel(int i, int i2, int i3, String str, long j) {
        this.errorCode = 0;
        this.errorMessage = "";
        setErrorCode(i);
        setProtocolID(i2);
        setCallbackId(i3);
        setPackageName(str);
        setTimeStamp(j);
    }

    @Override // com.byd.music.protocol.model.base.ProtocolBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.errorMessage);
    }

    protected ProtocolErrorModel(Parcel parcel) {
        super(parcel);
        this.errorCode = 0;
        this.errorMessage = "";
        this.errorCode = parcel.readInt();
        this.errorMessage = parcel.readString();
    }
}
