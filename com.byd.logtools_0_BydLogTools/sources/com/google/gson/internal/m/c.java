package com.google.gson.internal.m;

import com.google.gson.p;
import com.google.gson.q;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: compiled from: SqlTimestampTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class c extends p<Timestamp> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final q f4141a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p<Date> f4142b;

    /* JADX INFO: compiled from: SqlTimestampTypeAdapter.java */
    class a implements q {
        a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            a aVar2 = null;
            if (aVar.c() == Timestamp.class) {
                return new c(dVar.l(Date.class), aVar2);
            }
            return null;
        }
    }

    /* synthetic */ c(p pVar, a aVar) {
        this(pVar);
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Timestamp b(com.google.gson.stream.a aVar) throws IOException {
        Date dateB = this.f4142b.b(aVar);
        if (dateB != null) {
            return new Timestamp(dateB.getTime());
        }
        return null;
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(com.google.gson.stream.b bVar, Timestamp timestamp) throws IOException {
        this.f4142b.d(bVar, timestamp);
    }

    private c(p<Date> pVar) {
        this.f4142b = pVar;
    }
}
