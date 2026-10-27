package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import l5.AbstractC1781i;
import n5.AbstractC1959a;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1830g extends j5.y {
    public static final C1828e c = new C1828e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1829f f19222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f19223b;

    public C1830g() {
        C1829f c1829f = C1829f.f19221a;
        ArrayList arrayList = new ArrayList();
        this.f19223b = arrayList;
        this.f19222a = c1829f;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (AbstractC1781i.f18646a >= 9) {
            arrayList.add(new SimpleDateFormat("MMM d, yyyy h:mm:ss a", locale));
        }
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        Date dateB;
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        synchronized (this.f19223b) {
            try {
                Iterator it = this.f19223b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        try {
                            dateB = AbstractC1959a.b(strO, new ParsePosition(0));
                            break;
                        } catch (ParseException e10) {
                            StringBuilder sbJ = AbstractC1347d.j("Failed parsing '", strO, "' as Date; at path ");
                            sbJ.append(c2297a.q(true));
                            throw new j5.p(sbJ.toString(), e10);
                        }
                    }
                    DateFormat dateFormat = (DateFormat) it.next();
                    TimeZone timeZone = dateFormat.getTimeZone();
                    try {
                        try {
                            dateB = dateFormat.parse(strO);
                            break;
                        } finally {
                            dateFormat.setTimeZone(timeZone);
                        }
                    } catch (ParseException unused) {
                        dateFormat.setTimeZone(timeZone);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f19222a.getClass();
        return dateB;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        String str;
        Date date = (Date) obj;
        if (date == null) {
            c2298b.v();
            return;
        }
        DateFormat dateFormat = (DateFormat) this.f19223b.get(0);
        synchronized (this.f19223b) {
            str = dateFormat.format(date);
        }
        c2298b.L(str);
    }

    public final String toString() {
        DateFormat dateFormat = (DateFormat) this.f19223b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) dateFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + dateFormat.getClass().getSimpleName() + ')';
    }
}
