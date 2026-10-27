package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import defpackage.qk3;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();
    public final Calendar e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final long j;
    public String k;

    public class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        public final Month createFromParcel(Parcel parcel) {
            return Month.a(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Month[] newArray(int i) {
            return new Month[i];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarB = qk3.b(calendar);
        this.e = calendarB;
        this.f = calendarB.get(2);
        this.g = calendarB.get(1);
        this.h = calendarB.getMaximum(7);
        this.i = calendarB.getActualMaximum(5);
        this.j = calendarB.getTimeInMillis();
    }

    public static Month a(int i, int i2) {
        Calendar calendarD = qk3.d(null);
        calendarD.set(1, i);
        calendarD.set(2, i2);
        return new Month(calendarD);
    }

    public static Month f(long j) {
        Calendar calendarD = qk3.d(null);
        calendarD.setTimeInMillis(j);
        return new Month(calendarD);
    }

    public final String L() {
        if (this.k == null) {
            this.k = DateUtils.formatDateTime(null, this.e.getTimeInMillis(), 8228);
        }
        return this.k;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Month month) {
        return this.e.compareTo(month.e);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.f == month.f && this.g == month.g;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.g)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.g);
        parcel.writeInt(this.f);
    }
}
