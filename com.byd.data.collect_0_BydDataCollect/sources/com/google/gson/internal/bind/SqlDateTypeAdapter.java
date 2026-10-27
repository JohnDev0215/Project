package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.r;
import com.google.gson.t;
import com.google.gson.w.c;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4488a = new t() { // from class: com.google.gson.internal.bind.SqlDateTypeAdapter.1
        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new SqlDateTypeAdapter();
            }
            return null;
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DateFormat f4489b = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Date read(com.google.gson.w.a aVar) {
        if (aVar.j0() == com.google.gson.w.b.NULL) {
            aVar.f0();
            return null;
        }
        try {
            return new Date(this.f4489b.parse(aVar.h0()).getTime());
        } catch (ParseException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized void write(c cVar, Date date) {
        cVar.l0(date == null ? null : this.f4489b.format((java.util.Date) date));
    }
}
