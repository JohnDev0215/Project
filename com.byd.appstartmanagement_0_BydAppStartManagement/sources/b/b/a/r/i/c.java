package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f440a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f441b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f442c = DateFormat.getDateTimeInstance(2, 2);

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements p {
        @Override // b.b.a.p
        public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public final synchronized Date e(String str) {
        try {
            try {
                try {
                } catch (ParseException unused) {
                    return this.f441b.parse(str);
                }
            } catch (ParseException unused2) {
                return b.b.a.r.i.o.a.c(str, new ParsePosition(0));
            }
        } catch (ParseException e) {
            throw new JsonSyntaxException(str, e);
        }
        return this.f442c.parse(str);
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(b.b.a.t.a aVar) throws IOException {
        if (aVar.y() != JsonToken.NULL) {
            return e(aVar.w());
        }
        aVar.u();
        return null;
    }

    @Override // b.b.a.o
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.b.a.t.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.n();
        } else {
            bVar.A(this.f441b.format(date));
        }
    }
}
