package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NetworkOperatorAidl implements Parcelable {
    public static final Parcelable.Creator<NetworkOperatorAidl> CREATOR = new Parcelable.Creator<NetworkOperatorAidl>() { // from class: com.ts.ota.otasdkmgr.NetworkOperatorAidl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkOperatorAidl createFromParcel(Parcel source) {
            return new NetworkOperatorAidl(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkOperatorAidl[] newArray(int size) {
            return new NetworkOperatorAidl[size];
        }
    };
    private int enable;
    private String mccMnc;
    private int sigLevel;

    public String getMccMnc() {
        return this.mccMnc;
    }

    public void setMccMnc(String mccMnc) {
        this.mccMnc = mccMnc;
    }

    public int getEnable() {
        return this.enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getSigLevel() {
        return this.sigLevel;
    }

    public void setSigLevel(int sigLevel) {
        this.sigLevel = sigLevel;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mccMnc);
        dest.writeInt(this.enable);
        dest.writeInt(this.sigLevel);
    }

    public NetworkOperatorAidl() {
    }

    protected NetworkOperatorAidl(Parcel in) {
        this.mccMnc = in.readString();
        this.enable = in.readInt();
        this.sigLevel = in.readInt();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("NetworkOperatorAidl{");
        sb.append("mccMnc='");
        sb.append(this.mccMnc);
        sb.append('\'');
        sb.append(", enable=");
        sb.append(this.enable);
        sb.append(", sigLevel=");
        sb.append(this.sigLevel);
        sb.append('}');
        return sb.toString();
    }
}
