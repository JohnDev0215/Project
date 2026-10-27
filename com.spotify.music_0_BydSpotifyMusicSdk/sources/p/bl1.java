package p;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class bl1 implements Comparable, Parcelable {
    public static final Parcelable.Creator<bl1> CREATOR = new bg1(15);
    public final Calendar c;
    public final int e;
    public final int k;
    public final int l;
    public final int m;
    public final long n;
    public String o;

    public bl1(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarA = bs2.a(calendar);
        this.c = calendarA;
        this.e = calendarA.get(2);
        this.k = calendarA.get(1);
        this.l = calendarA.getMaximum(7);
        this.m = calendarA.getActualMaximum(5);
        this.n = calendarA.getTimeInMillis();
    }

    public static bl1 a(int i, int i2) {
        Calendar calendarC = bs2.c(null);
        calendarC.set(1, i);
        calendarC.set(2, i2);
        return new bl1(calendarC);
    }

    public static bl1 b(long j) {
        Calendar calendarC = bs2.c(null);
        calendarC.setTimeInMillis(j);
        return new bl1(calendarC);
    }

    public final int c() {
        Calendar calendar = this.c;
        int firstDayOfWeek = calendar.get(7) - calendar.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.l : firstDayOfWeek;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.c.compareTo(((bl1) obj).c);
    }

    public final String d(Context context) {
        if (this.o == null) {
            long timeInMillis = this.c.getTimeInMillis();
            this.o = DateUtils.formatDateTime(context, timeInMillis - ((long) TimeZone.getDefault().getOffset(timeInMillis)), 36);
        }
        return this.o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final int e(bl1 bl1Var) {
        if (!(this.c instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (bl1Var.e - this.e) + ((bl1Var.k - this.k) * 12);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bl1)) {
            return false;
        }
        bl1 bl1Var = (bl1) obj;
        return this.e == bl1Var.e && this.k == bl1Var.k;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.k)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.k);
        parcel.writeInt(this.e);
    }
}
