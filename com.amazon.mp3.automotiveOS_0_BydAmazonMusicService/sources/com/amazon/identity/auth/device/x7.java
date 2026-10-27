package com.amazon.identity.auth.device;

import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x7<T> implements pa<x7<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f659a;
    public final Date b;
    public boolean c;
    public boolean d;

    public x7(T t, Date date, boolean z, boolean z2) {
        e.a(date, "dateTime");
        this.f659a = t;
        this.b = (Date) date.clone();
        this.c = z;
        this.d = z2;
    }

    @Override // com.amazon.identity.auth.device.pa
    public pa a() {
        try {
            return new x7(aa.a(this.f659a), (Date) this.b.clone(), this.c, this.d);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Date b() {
        return (Date) this.b.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x7.class != obj.getClass()) {
            return false;
        }
        x7 x7Var = (x7) obj;
        return this.d == x7Var.d && this.c == x7Var.c && b().equals(b()) && aa.a(this.f659a, x7Var.f659a);
    }

    public int hashCode() {
        Date date = this.b;
        int iHashCode = ((((((date == null ? 0 : date.hashCode()) + 31) * 31) + (this.d ? 1231 : 1237)) * 31) + (this.c ? 1231 : 1237)) * 31;
        T t = this.f659a;
        return iHashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        T t = this.f659a;
        objArr[0] = t != null ? t.toString() : "None";
        objArr[1] = Long.valueOf(this.b.getTime());
        objArr[2] = Boolean.toString(this.d);
        objArr[3] = Boolean.toString(this.c);
        return String.format(locale, "Value: %s, TimeStamp: %d, Deleted: %s, Dirty: %s", objArr);
    }
}
