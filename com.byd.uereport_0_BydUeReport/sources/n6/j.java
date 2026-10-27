package n6;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: SqlDateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f6598a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f6599b = new SimpleDateFormat("MMM d, yyyy");

    /* JADX INFO: compiled from: SqlDateTypeAdapter.java */
    public static class a implements p {
        @Override // k6.p
        public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
            if (aVar.d() == Date.class) {
                return new j();
            }
            return null;
        }
    }

    @Override // k6.o
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(q6.a aVar) throws IOException {
        if (aVar.R() == JsonToken.NULL) {
            aVar.M();
            return null;
        }
        try {
            return new Date(this.f6599b.parse(aVar.O()).getTime());
        } catch (ParseException e8) {
            throw new JsonSyntaxException(e8);
        }
    }

    @Override // k6.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public synchronized void d(q6.b bVar, Date date) throws IOException {
        bVar.X(date == null ? null : this.f6599b.format((java.util.Date) date));
    }
}
