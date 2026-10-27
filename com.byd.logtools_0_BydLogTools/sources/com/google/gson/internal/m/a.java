package com.google.gson.internal.m;

import com.google.gson.JsonSyntaxException;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class a extends p<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final q f4137a = new C0078a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DateFormat f4138b;

    /* JADX INFO: renamed from: com.google.gson.internal.m.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    class C0078a implements q {
        C0078a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            C0078a c0078a = null;
            if (aVar.c() == Date.class) {
                return new a(c0078a);
            }
            return null;
        }
    }

    /* synthetic */ a(C0078a c0078a) {
        this();
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.x() == JsonToken.NULL) {
            aVar.t();
            return null;
        }
        try {
            return new Date(this.f4138b.parse(aVar.v()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(com.google.gson.stream.b bVar, Date date) throws IOException {
        bVar.z(date == null ? null : this.f4138b.format((java.util.Date) date));
    }

    private a() {
        this.f4138b = new SimpleDateFormat("MMM d, yyyy");
    }
}
