package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OpeningHours implements Parcelable {
    public static final Parcelable.Creator<OpeningHours> CREATOR = new Parcelable.Creator<OpeningHours>() { // from class: com.neusoft.na.ocservice.OpeningHours.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningHours createFromParcel(Parcel parcel) {
            return new OpeningHours(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpeningHours[] newArray(int i) {
            return new OpeningHours[i];
        }
    };
    private int endDayOfWeek;
    private List<OpeningTime> openingTime;
    private int priceOpeningHoursIndex;
    private int startDayOfWeek;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OpeningHours() {
    }

    public OpeningHours(int i, int i2, List<OpeningTime> list, int i3) {
        this.startDayOfWeek = i;
        this.endDayOfWeek = i2;
        this.openingTime = list;
        this.priceOpeningHoursIndex = i3;
    }

    public int getStartDayOfWeek() {
        return this.startDayOfWeek;
    }

    public void setStartDayOfWeek(int i) {
        this.startDayOfWeek = i;
    }

    public int getEndDayOfWeek() {
        return this.endDayOfWeek;
    }

    public void setEndDayOfWeek(int i) {
        this.endDayOfWeek = i;
    }

    public List<OpeningTime> getOpeningTime() {
        return this.openingTime;
    }

    public void setOpeningTime(List<OpeningTime> list) {
        this.openingTime = list;
    }

    public int getPriceOpeningHoursIndex() {
        return this.priceOpeningHoursIndex;
    }

    public void setPriceOpeningHoursIndex(int i) {
        this.priceOpeningHoursIndex = i;
    }

    protected OpeningHours(Parcel parcel) {
        this.startDayOfWeek = parcel.readInt();
        this.endDayOfWeek = parcel.readInt();
        this.openingTime = parcel.createTypedArrayList(OpeningTime.CREATOR);
        this.priceOpeningHoursIndex = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.startDayOfWeek);
        parcel.writeInt(this.endDayOfWeek);
        parcel.writeTypedList(this.openingTime);
        parcel.writeInt(this.priceOpeningHoursIndex);
    }
}
