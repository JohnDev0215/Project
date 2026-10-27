package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes5.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new Parcelable.Creator<BtCallInfo>() { // from class: com.byd.info.BtCallInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BtCallInfo createFromParcel(Parcel parcel) {
            return new BtCallInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BtCallInfo[] newArray(int i) {
            return new BtCallInfo[i];
        }
    };
    private String date;

    @Column(unique = true)
    private long id;
    private boolean idle;

    @Column(defaultValue = "未知")
    private String name;
    private String number;
    private int state;
    private int time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BtCallInfo() {
        this.name = "unknown";
        this.number = "unknown";
        this.state = -1;
        this.time = 0;
        this.idle = true;
        this.date = "";
    }

    public BtCallInfo(Parcel parcel) {
        this.name = "unknown";
        this.number = "unknown";
        this.state = -1;
        this.time = 0;
        this.idle = true;
        this.date = "";
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.number = parcel.readString();
        this.state = parcel.readInt();
        this.time = parcel.readInt();
        this.idle = parcel.readBoolean();
        this.date = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.number);
        parcel.writeInt(this.state);
        parcel.writeInt(this.time);
        parcel.writeBoolean(this.idle);
        parcel.writeString(this.date);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.number = parcel.readString();
        this.state = parcel.readInt();
        this.time = parcel.readInt();
        this.idle = parcel.readBoolean();
        this.date = parcel.readString();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public boolean isIdle() {
        return this.idle;
    }

    public void setIdle(boolean z) {
        this.idle = z;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BtCallInfo) {
            BtCallInfo btCallInfo = (BtCallInfo) obj;
            if (getName().equals(btCallInfo.getName()) && getNumber().equals(btCallInfo.getNumber())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "BtCallInfo{id=" + this.id + ", name='" + this.name + DateFormat.QUOTE + ", number='" + this.number + DateFormat.QUOTE + ", state=" + this.state + ", time=" + this.time + ", idle=" + this.idle + ", date='" + this.date + DateFormat.QUOTE + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(BtCallInfo btCallInfo) {
        long time = btCallInfo.getTime() - getTime();
        if (time > 0) {
            return 1;
        }
        return time == 0 ? 0 : -1;
    }
}
