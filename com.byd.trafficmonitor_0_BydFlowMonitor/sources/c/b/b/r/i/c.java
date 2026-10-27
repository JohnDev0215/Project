package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f4228a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f4229b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f4230c = DateFormat.getDateTimeInstance(2, 2);

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements p {
        @Override // c.b.b.p
        public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
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
                    return this.f4229b.parse(str);
                }
            } catch (ParseException unused2) {
                return c.b.b.r.i.o.a.c(str, new ParsePosition(0));
            }
        } catch (ParseException e2) {
            throw new JsonSyntaxException(str, e2);
        }
        return this.f4230c.parse(str);
    }

    @Override // c.b.b.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(c.b.b.t.a aVar) throws IOException {
        if (aVar.k0() != JsonToken.NULL) {
            return e(aVar.i0());
        }
        aVar.g0();
        return null;
    }

    @Override // c.b.b.o
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(c.b.b.t.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.Z();
        } else {
            bVar.m0(this.f4229b.format(date));
        }
    }
}
