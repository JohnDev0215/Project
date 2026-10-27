package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f2281a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2282b = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements p {
        @Override // b.c.a.p
        public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            if (aVar.d() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(b.c.a.t.a aVar) throws IOException {
        if (aVar.E() == JsonToken.NULL) {
            aVar.z();
            return null;
        }
        try {
            return new Date(this.f2282b.parse(aVar.B()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.c.a.t.b bVar, Date date) throws IOException {
        bVar.G(date == null ? null : this.f2282b.format((java.util.Date) date));
    }
}
