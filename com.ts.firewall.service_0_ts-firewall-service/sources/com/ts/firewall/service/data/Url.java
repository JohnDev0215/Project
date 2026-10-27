package com.ts.firewall.service.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Url implements Parcelable {
    public static final Parcelable.Creator<Url> CREATOR = new Parcelable.Creator<Url>() { // from class: com.ts.firewall.service.data.Url.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Url createFromParcel(Parcel in) {
            Url item = new Url();
            item.mDomain = in.readString();
            item.mPort = in.readInt();
            return item;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };
    public String mDomain = null;
    public int mPort = 0;

    public void setPort(int port) {
        this.mPort = port;
    }

    public int getPort() {
        return this.mPort;
    }

    public void setDomain(String domain) {
        this.mDomain = domain;
    }

    public String getDomain() {
        return this.mDomain;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDomain);
        dest.writeInt(this.mPort);
    }
}
