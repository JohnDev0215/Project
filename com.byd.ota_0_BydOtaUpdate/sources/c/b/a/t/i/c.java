package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
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
public final class c extends q<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2965a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2966b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f2967c = DateFormat.getDateTimeInstance(2, 2);

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements r {
        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() == Date.class) {
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
                return c.b.a.t.i.o.a.c(str, new ParsePosition(0));
            }
        } catch (ParseException unused2) {
            return this.f2966b.parse(str);
        }
        return this.f2967c.parse(str);
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(c.b.a.v.a aVar) throws IOException {
        if (aVar.b0() != JsonToken.NULL) {
            return e(aVar.Z());
        }
        aVar.X();
        return null;
    }

    @Override // c.b.a.q
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(c.b.a.v.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.P();
        } else {
            bVar.c0(this.f2966b.format(date));
        }
    }
}
