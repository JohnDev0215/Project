package c.b.a;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: DefaultDateTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements p<Date>, j<Date> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateFormat f2884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateFormat f2885b;

    public a() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    @Override // c.b.a.j
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Date a(k kVar, Type type, i iVar) throws JsonParseException {
        if (!(kVar instanceof n)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date dateD = d(kVar);
        if (type == Date.class) {
            return dateD;
        }
        if (type == Timestamp.class) {
            return new Timestamp(dateD.getTime());
        }
        if (type == java.sql.Date.class) {
            return new java.sql.Date(dateD.getTime());
        }
        throw new IllegalArgumentException(a.class + " cannot deserialize to " + type);
    }

    public final Date d(k kVar) {
        Date date;
        synchronized (this.f2885b) {
            try {
                try {
                    try {
                        date = this.f2885b.parse(kVar.d());
                    } catch (ParseException unused) {
                        return this.f2884a.parse(kVar.d());
                    }
                } catch (ParseException e2) {
                    throw new JsonSyntaxException(kVar.d(), e2);
                }
            } catch (ParseException unused2) {
                return c.b.a.t.i.o.a.c(kVar.d(), new ParsePosition(0));
            }
        }
        return date;
    }

    @Override // c.b.a.p
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public k b(Date date, Type type, o oVar) {
        n nVar;
        synchronized (this.f2885b) {
            nVar = new n(this.f2884a.format(date));
        }
        return nVar;
    }

    public String toString() {
        return a.class.getSimpleName() + '(' + this.f2885b.getClass().getSimpleName() + ')';
    }

    public a(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public a(int i2, int i3) {
        this(DateFormat.getDateTimeInstance(i2, i3, Locale.US), DateFormat.getDateTimeInstance(i2, i3));
    }

    public a(DateFormat dateFormat, DateFormat dateFormat2) {
        this.f2884a = dateFormat;
        this.f2885b = dateFormat2;
    }
}
