package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class OpeningHour implements Parcelable {
    public static final Parcelable.Creator<OpeningHour> CREATOR = new Parcelable.Creator<OpeningHour>() { // from class: com.neusoft.na.ocservice.OpeningHour.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningHour createFromParcel(Parcel parcel) {
            return new OpeningHour(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningHour[] newArray(int i) {
            return new OpeningHour[i];
        }
    };
    int day;
    TimeSlot[] timeSlots;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public TimeSlot[] getTimeSlots() {
        return this.timeSlots;
    }

    public void setTimeSlots(TimeSlot[] timeSlotArr) {
        this.timeSlots = timeSlotArr;
    }

    protected OpeningHour(Parcel parcel) {
        this.day = 0;
        this.day = parcel.readInt();
        this.timeSlots = (TimeSlot[]) parcel.createTypedArray(TimeSlot.CREATOR);
    }

    public OpeningHour() {
        this.day = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.day);
        parcel.writeTypedArray(this.timeSlots, i);
    }
}
