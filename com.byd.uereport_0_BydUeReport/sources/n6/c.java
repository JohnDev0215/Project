package n6;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends o<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f6558a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f6559b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f6560c = DateFormat.getDateTimeInstance(2, 2);

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements p {
        @Override // k6.p
        public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
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
                    return this.f6559b.parse(str);
                }
            } catch (ParseException unused2) {
                return o6.a.c(str, new ParsePosition(0));
            }
        } catch (ParseException e8) {
            throw new JsonSyntaxException(str, e8);
        }
        return this.f6560c.parse(str);
    }

    @Override // k6.o
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(q6.a aVar) throws IOException {
        if (aVar.R() != JsonToken.NULL) {
            return e(aVar.O());
        }
        aVar.M();
        return null;
    }

    @Override // k6.o
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized void d(q6.b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.B();
        } else {
            bVar.X(this.f6559b.format(date));
        }
    }
}
