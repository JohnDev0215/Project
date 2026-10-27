package com.google.gson.internal.k;

import com.google.gson.JsonSyntaxException;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends p<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f4061a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<DateFormat> f4062b;

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    class a implements q {
        a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        ArrayList arrayList = new ArrayList();
        this.f4062b = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (com.google.gson.internal.d.e()) {
            arrayList.add(com.google.gson.internal.g.c(2, 2));
        }
    }

    private synchronized Date e(String str) {
        Iterator<DateFormat> it = this.f4062b.iterator();
        while (it.hasNext()) {
            try {
                return it.next().parse(str);
            } catch (ParseException unused) {
            }
        }
        try {
            return com.google.gson.internal.k.o.a.c(str, new ParsePosition(0));
        } catch (ParseException e2) {
            throw new JsonSyntaxException(str, e2);
        }
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.x() != JsonToken.NULL) {
            return e(aVar.v());
        }
        aVar.t();
        return null;
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(com.google.gson.stream.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.m();
        } else {
            bVar.z(this.f4062b.get(0).format(date));
        }
    }
}
