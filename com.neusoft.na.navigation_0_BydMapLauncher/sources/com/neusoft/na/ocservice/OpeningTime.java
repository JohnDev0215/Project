package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class OpeningTime implements Parcelable {
    public static final Parcelable.Creator<OpeningTime> CREATOR = new Parcelable.Creator<OpeningTime>() { // from class: com.neusoft.na.ocservice.OpeningTime.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningTime createFromParcel(Parcel parcel) {
            return new OpeningTime(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningTime[] newArray(int i) {
            return new OpeningTime[i];
        }
    };
    private int endHour;
    private int endMinute;
    private int startHour;
    private int startMinute;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OpeningTime() {
    }

    public OpeningTime(int i, int i2, int i3, int i4) {
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    protected OpeningTime(Parcel parcel) {
        this.startHour = parcel.readInt();
        this.startMinute = parcel.readInt();
        this.endHour = parcel.readInt();
        this.endMinute = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.startHour);
        parcel.writeInt(this.startMinute);
        parcel.writeInt(this.endHour);
        parcel.writeInt(this.endMinute);
    }
}
