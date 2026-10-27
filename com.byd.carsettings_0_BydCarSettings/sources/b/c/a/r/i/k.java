package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k extends o<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f2283a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2284b = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    public static class a implements p {
        @Override // b.c.a.p
        public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            if (aVar.d() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(b.c.a.t.a aVar) throws IOException {
        if (aVar.E() == JsonToken.NULL) {
            aVar.z();
            return null;
        }
        try {
            return new Time(this.f2284b.parse(aVar.B()).getTime());
        } catch (ParseException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.c.a.t.b bVar, Time time) throws IOException {
        bVar.G(time == null ? null : this.f2284b.format((Date) time));
    }
}
