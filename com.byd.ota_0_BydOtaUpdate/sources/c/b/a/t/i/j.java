package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends q<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2991a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2992b = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements r {
        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(c.b.a.v.a aVar) throws IOException {
        if (aVar.b0() == JsonToken.NULL) {
            aVar.X();
            return null;
        }
        try {
            return new Date(this.f2992b.parse(aVar.Z()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(c.b.a.v.b bVar, Date date) throws IOException {
        bVar.c0(date == null ? null : this.f2992b.format((java.util.Date) date));
    }
}
