package com.byd.music.protocol.model.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.music.protocol.ProtocolResultCode;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolBaseModel implements Parcelable {
    public static final Parcelable.Creator<ProtocolBaseModel> CREATOR = new Parcelable.Creator<ProtocolBaseModel>() { // from class: com.byd.music.protocol.model.base.ProtocolBaseModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolBaseModel createFromParcel(Parcel parcel) {
            return new ProtocolBaseModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolBaseModel[] newArray(int i) {
            return new ProtocolBaseModel[i];
        }
    };
    private int callbackId;
    private int errorCode;
    private String errorMessage;
    private String packageName;
    private int protocolID;
    private String protocolVersion;
    private long timeStamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getModelVersion() {
        return 0;
    }

    public final int getDataVersion() {
        try {
            return Integer.parseInt(this.protocolVersion);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getCallbackId() {
        return this.callbackId;
    }

    public void setCallbackId(int i) {
        this.callbackId = i;
    }

    public int getProtocolID() {
        return this.protocolID;
    }

    public void setProtocolID(int i) {
        this.protocolID = i;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getErrorMessage() {
        if (this.errorCode > 0) {
            this.errorMessage = ProtocolResultCode.RESULT_MESSAGE.get(Integer.valueOf(this.errorCode));
        }
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public ProtocolBaseModel() {
        this.callbackId = 0;
        this.packageName = "";
        this.protocolID = 0;
        this.protocolVersion = Integer.toString(getModelVersion());
        this.timeStamp = 0L;
        this.errorCode = 0;
        this.errorMessage = "";
    }

    public ProtocolBaseModel(int i, int i2, int i3, String str, long j, String str2) {
        this.callbackId = 0;
        this.packageName = "";
        this.protocolID = 0;
        this.protocolVersion = Integer.toString(getModelVersion());
        this.timeStamp = 0L;
        this.errorCode = 0;
        this.errorMessage = "";
        setErrorCode(i);
        setProtocolID(i2);
        setCallbackId(i3);
        setPackageName(str);
        setTimeStamp(j);
        setErrorMessage(str2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.protocolID);
        parcel.writeLong(this.timeStamp);
        parcel.writeInt(this.callbackId);
        parcel.writeString(Integer.toString(getModelVersion()));
        parcel.writeString(this.packageName);
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.errorMessage);
    }

    protected ProtocolBaseModel(Parcel parcel) {
        this.callbackId = 0;
        this.packageName = "";
        this.protocolID = 0;
        this.protocolVersion = Integer.toString(getModelVersion());
        this.timeStamp = 0L;
        this.errorCode = 0;
        this.errorMessage = "";
        this.protocolID = parcel.readInt();
        this.timeStamp = parcel.readLong();
        this.callbackId = parcel.readInt();
        this.protocolVersion = parcel.readString();
        this.packageName = parcel.readString();
        this.errorCode = parcel.readInt();
        this.errorMessage = parcel.readString();
    }
}
