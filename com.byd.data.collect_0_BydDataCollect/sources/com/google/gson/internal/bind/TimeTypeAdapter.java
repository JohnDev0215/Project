package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.r;
import com.google.gson.t;
import com.google.gson.w.c;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class TimeTypeAdapter extends TypeAdapter<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4490a = new t() { // from class: com.google.gson.internal.bind.TimeTypeAdapter.1
        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            if (aVar.c() == Time.class) {
                return new TimeTypeAdapter();
            }
            return null;
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DateFormat f4491b = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Time read(com.google.gson.w.a aVar) {
        if (aVar.j0() == com.google.gson.w.b.NULL) {
            aVar.f0();
            return null;
        }
        try {
            return new Time(this.f4491b.parse(aVar.h0()).getTime());
        } catch (ParseException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public synchronized void write(c cVar, Time time) {
        cVar.l0(time == null ? null : this.f4491b.format((Date) time));
    }
}
