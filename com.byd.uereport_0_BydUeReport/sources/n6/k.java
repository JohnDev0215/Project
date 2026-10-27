package n6;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: TimeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends o<Time> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f6600a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f6601b = new SimpleDateFormat("hh:mm:ss a");

    /* JADX INFO: compiled from: TimeTypeAdapter.java */
    public static class a implements p {
        @Override // k6.p
        public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
            if (aVar.d() == Time.class) {
                return new k();
            }
            return null;
        }
    }

    @Override // k6.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(q6.a aVar) throws IOException {
        if (aVar.R() == JsonToken.NULL) {
            aVar.M();
            return null;
        }
        try {
            return new Time(this.f6601b.parse(aVar.O()).getTime());
        } catch (ParseException e8) {
            throw new JsonSyntaxException(e8);
        }
    }

    @Override // k6.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(q6.b bVar, Time time) throws IOException {
        bVar.X(time == null ? null : this.f6601b.format((Date) time));
    }
}
