package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f462a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f463b = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements p {
        @Override // b.b.a.p
        public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(b.b.a.t.a aVar) throws IOException {
        if (aVar.y() == JsonToken.NULL) {
            aVar.u();
            return null;
        }
        try {
            return new Date(this.f463b.parse(aVar.w()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.b.a.t.b bVar, Date date) throws IOException {
        bVar.A(date == null ? null : this.f463b.format((java.util.Date) date));
    }
}
