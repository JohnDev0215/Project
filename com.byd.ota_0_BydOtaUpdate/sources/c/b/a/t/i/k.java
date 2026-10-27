package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends q<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2993a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2994b = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    public static class a implements r {
        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(c.b.a.v.a aVar) throws IOException {
        if (aVar.b0() == JsonToken.NULL) {
            aVar.X();
            return null;
        }
        try {
            return new Time(this.f2994b.parse(aVar.Z()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(c.b.a.v.b bVar, Time time) throws IOException {
        bVar.c0(time == null ? null : this.f2994b.format((Date) time));
    }
}
