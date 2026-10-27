package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.d;
import com.google.gson.internal.i;
import com.google.gson.r;
import com.google.gson.t;
import com.google.gson.w.c;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class DateTypeAdapter extends TypeAdapter<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4460a = new t() { // from class: com.google.gson.internal.bind.DateTypeAdapter.1
        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<DateFormat> f4461b;

    public DateTypeAdapter() {
        ArrayList arrayList = new ArrayList();
        this.f4461b = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (d.e()) {
            arrayList.add(i.c(2, 2));
        }
    }

    private synchronized Date a(String str) {
        Iterator<DateFormat> it = this.f4461b.iterator();
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

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Date read(com.google.gson.w.a aVar) throws IOException {
        if (aVar.j0() != com.google.gson.w.b.NULL) {
            return a(aVar.h0());
        }
        aVar.f0();
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public synchronized void write(c cVar, Date date) {
        if (date == null) {
            cVar.Y();
        } else {
            cVar.l0(this.f4461b.get(0).format(date));
        }
    }
}
