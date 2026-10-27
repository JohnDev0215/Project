package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import p.a31;
import p.he1;
import p.lo2;
import p.o61;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class DateTypeAdapter extends b {
    public static final lo2 b = new lo2() { // from class: com.google.gson.internal.bind.DateTypeAdapter.1
        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            if (qo2Var.a == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };
    public final ArrayList a;

    public DateTypeAdapter() {
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (o61.a >= 9) {
            arrayList.add(he1.v(2, 2));
        }
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        if (t71Var.K0() == JsonToken.q) {
            t71Var.z0();
            return null;
        }
        String strU = t71Var.u();
        synchronized (this) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                try {
                    return ((DateFormat) it.next()).parse(strU);
                } catch (ParseException unused) {
                }
            }
            try {
                return a31.b(strU, new ParsePosition(0));
            } catch (ParseException e) {
                throw new JsonSyntaxException(strU, e);
            }
        }
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        Date date = (Date) obj;
        synchronized (this) {
            if (date == null) {
                z71Var.E0();
            } else {
                z71Var.L0(((DateFormat) this.a.get(0)).format(date));
            }
        }
    }
}
