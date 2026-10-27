package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends o<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f464a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f465b = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    public static class a implements p {
        @Override // b.b.a.p
        public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(b.b.a.t.a aVar) throws IOException {
        if (aVar.y() == JsonToken.NULL) {
            aVar.u();
            return null;
        }
        try {
            return new Time(this.f465b.parse(aVar.w()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.b.a.t.b bVar, Time time) throws IOException {
        bVar.A(time == null ? null : this.f465b.format((Date) time));
    }
}
