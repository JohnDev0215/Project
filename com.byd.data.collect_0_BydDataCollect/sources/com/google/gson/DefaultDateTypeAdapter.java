package com.google.gson;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class DefaultDateTypeAdapter extends TypeAdapter<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<? extends Date> f4405a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<DateFormat> f4406b;

    DefaultDateTypeAdapter(Class<? extends Date> cls, String str) {
        ArrayList arrayList = new ArrayList();
        this.f4406b = arrayList;
        this.f4405a = c(cls);
        Locale locale = Locale.US;
        arrayList.add(new SimpleDateFormat(str, locale));
        if (Locale.getDefault().equals(locale)) {
            return;
        }
        arrayList.add(new SimpleDateFormat(str));
    }

    private Date a(String str) {
        synchronized (this.f4406b) {
            Iterator<DateFormat> it = this.f4406b.iterator();
            while (it.hasNext()) {
                try {
                    return it.next().parse(str);
                } catch (ParseException unused) {
                }
            }
            try {
                return com.google.gson.internal.bind.c.a.c(str, new ParsePosition(0));
            } catch (ParseException e2) {
                throw new r(str, e2);
            }
        }
    }

    private static Class<? extends Date> c(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Date read(com.google.gson.w.a aVar) throws IOException {
        if (aVar.j0() == com.google.gson.w.b.NULL) {
            aVar.f0();
            return null;
        }
        Date dateA = a(aVar.h0());
        Class<? extends Date> cls = this.f4405a;
        if (cls == Date.class) {
            return dateA;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(dateA.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(dateA.getTime());
        }
        throw new AssertionError();
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public void write(com.google.gson.w.c cVar, Date date) throws IOException {
        if (date == null) {
            cVar.Y();
            return;
        }
        synchronized (this.f4406b) {
            cVar.l0(this.f4406b.get(0).format(date));
        }
    }

    public String toString() {
        DateFormat dateFormat = this.f4406b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) dateFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + dateFormat.getClass().getSimpleName() + ')';
    }

    public DefaultDateTypeAdapter(Class<? extends Date> cls, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        this.f4406b = arrayList;
        this.f4405a = c(cls);
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(i2, i3, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(i2, i3));
        }
        if (com.google.gson.internal.d.e()) {
            arrayList.add(com.google.gson.internal.i.c(i2, i3));
        }
    }
}
