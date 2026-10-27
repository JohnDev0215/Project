package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f4256a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f4257b = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements p {
        @Override // c.b.b.p
        public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            if (aVar.d() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // c.b.b.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(c.b.b.t.a aVar) throws IOException {
        if (aVar.k0() == JsonToken.NULL) {
            aVar.g0();
            return null;
        }
        try {
            return new Date(this.f4257b.parse(aVar.i0()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // c.b.b.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(c.b.b.t.b bVar, Date date) throws IOException {
        bVar.m0(date == null ? null : this.f4257b.format((java.util.Date) date));
    }
}
