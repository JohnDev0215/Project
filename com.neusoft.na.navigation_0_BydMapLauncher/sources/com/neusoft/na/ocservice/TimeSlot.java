package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class TimeSlot implements Parcelable {
    public static final Parcelable.Creator<TimeSlot> CREATOR = new Parcelable.Creator<TimeSlot>() { // from class: com.neusoft.na.ocservice.TimeSlot.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeSlot createFromParcel(Parcel parcel) {
            return new TimeSlot(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeSlot[] newArray(int i) {
            return new TimeSlot[i];
        }
    };
    int from;
    int to;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getFrom() {
        return this.from;
    }

    public void setFrom(int i) {
        this.from = i;
    }

    public int getTo() {
        return this.to;
    }

    public void setTo(int i) {
        this.to = i;
    }

    protected TimeSlot(Parcel parcel) {
        this.from = 0;
        this.to = 0;
        this.from = parcel.readInt();
        this.to = parcel.readInt();
    }

    public TimeSlot() {
        this.from = 0;
        this.to = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.from);
        parcel.writeInt(this.to);
    }
}
