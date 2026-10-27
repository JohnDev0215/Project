package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f2256a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2257b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f2258c = DateFormat.getDateTimeInstance(2, 2);

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements p {
        @Override // b.c.a.p
        public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
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
                } catch (ParseException e2) {
                    throw new JsonSyntaxException(str, e2);
                }
            } catch (ParseException unused) {
                return b.c.a.r.i.o.a.c(str, new ParsePosition(0));
            }
        } catch (ParseException unused2) {
            return this.f2257b.parse(str);
        }
        return this.f2258c.parse(str);
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(b.c.a.t.a aVar) throws IOException {
        if (aVar.E() != JsonToken.NULL) {
            return e(aVar.B());
        }
        aVar.z();
        return null;
    }

    @Override // b.c.a.o
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(b.c.a.t.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.s();
        } else {
            bVar.G(this.f2257b.format(date));
        }
    }
}
