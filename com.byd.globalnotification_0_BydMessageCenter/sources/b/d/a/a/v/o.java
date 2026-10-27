package b.d.a.a.v;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Comparable<o>, Parcelable {
    public static final Parcelable.Creator<o> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f2104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2106c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2107d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2108e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2109f;

    public static class a implements Parcelable.Creator<o> {
        @Override // android.os.Parcelable.Creator
        public o createFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            Calendar calendarE = w.e();
            calendarE.set(1, i);
            calendarE.set(2, i2);
            return new o(calendarE);
        }

        @Override // android.os.Parcelable.Creator
        public o[] newArray(int i) {
            return new o[i];
        }
    }

    public o(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarB = w.b(calendar);
        this.f2104a = calendarB;
        this.f2105b = calendarB.get(2);
        this.f2106c = this.f2104a.get(1);
        this.f2107d = this.f2104a.getMaximum(7);
        this.f2108e = this.f2104a.getActualMaximum(5);
        this.f2104a.getTimeInMillis();
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(o oVar) {
        return this.f2104a.compareTo(oVar.f2104a);
    }

    public int c() {
        int firstDayOfWeek = this.f2104a.get(7) - this.f2104a.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f2107d : firstDayOfWeek;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return this.f2105b == oVar.f2105b && this.f2106c == oVar.f2106c;
    }

    public String h(Context context) {
        if (this.f2109f == null) {
            long timeInMillis = this.f2104a.getTimeInMillis();
            this.f2109f = DateUtils.formatDateTime(context, timeInMillis - ((long) TimeZone.getDefault().getOffset(timeInMillis)), 36);
        }
        return this.f2109f;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2105b), Integer.valueOf(this.f2106c)});
    }

    public o i(int i) {
        Calendar calendarB = w.b(this.f2104a);
        calendarB.add(2, i);
        return new o(calendarB);
    }

    public int j(o oVar) {
        if (!(this.f2104a instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (oVar.f2105b - this.f2105b) + ((oVar.f2106c - this.f2106c) * 12);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2106c);
        parcel.writeInt(this.f2105b);
    }
}
