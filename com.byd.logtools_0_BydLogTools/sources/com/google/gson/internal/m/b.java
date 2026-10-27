package com.google.gson.internal.m;

import com.google.gson.JsonSyntaxException;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: SqlTimeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends p<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final q f4139a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DateFormat f4140b;

    /* JADX INFO: compiled from: SqlTimeTypeAdapter.java */
    class a implements q {
        a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            a aVar2 = null;
            if (aVar.c() == Time.class) {
                return new b(aVar2);
            }
            return null;
        }
    }

    /* synthetic */ b(a aVar) {
        this();
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.x() == JsonToken.NULL) {
            aVar.t();
            return null;
        }
        try {
            return new Time(this.f4140b.parse(aVar.v()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(com.google.gson.stream.b bVar, Time time) throws IOException {
        bVar.z(time == null ? null : this.f4140b.format((Date) time));
    }

    private b() {
        this.f4140b = new SimpleDateFormat("hh:mm:ss a");
    }
}
