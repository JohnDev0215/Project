package spi;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e3 extends nb<Date> {
    public static final a b = new a();
    public final List<DateFormat> a;

    /* JADX INFO: compiled from: DateTypeAdapter.java */
    public static class a implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            if (vbVar.a == Date.class) {
                return new e3();
            }
            return null;
        }
    }

    public e3() {
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (d5.a()) {
            arrayList.add(e9.a(2, 2));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
    
        r3 = spi.s4.b(r3, new java.text.ParsePosition(0));
     */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<java.text.DateFormat>] */
    @Override // spi.nb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Date a(spi.o5 r3) throws java.io.IOException {
        /*
            r2 = this;
            spi.s5 r0 = r3.v()
            spi.s5 r1 = spi.s5.NULL
            if (r0 != r1) goto Ld
            r3.r()
            r2 = 0
            goto L36
        Ld:
            java.lang.String r3 = r3.t()
            monitor-enter(r2)
            java.util.List<java.text.DateFormat> r0 = r2.a     // Catch: java.lang.Throwable -> L3e
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L3e
        L18:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L2a
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
            java.text.DateFormat r1 = (java.text.DateFormat) r1     // Catch: java.lang.Throwable -> L3e
            java.util.Date r3 = r1.parse(r3)     // Catch: java.text.ParseException -> L18 java.lang.Throwable -> L3e
            monitor-exit(r2)
            goto L35
        L2a:
            java.text.ParsePosition r0 = new java.text.ParsePosition     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
            r1 = 0
            r0.<init>(r1)     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
            java.util.Date r3 = spi.s4.b(r3, r0)     // Catch: java.text.ParseException -> L37 java.lang.Throwable -> L3e
            monitor-exit(r2)
        L35:
            r2 = r3
        L36:
            return r2
        L37:
            r0 = move-exception
            spi.r5 r1 = new spi.r5     // Catch: java.lang.Throwable -> L3e
            r1.<init>(r3, r0)     // Catch: java.lang.Throwable -> L3e
            throw r1     // Catch: java.lang.Throwable -> L3e
        L3e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.e3.a(spi.o5):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<java.text.DateFormat>] */
    @Override // spi.nb
    public final void b(v5 v5Var, Date date) {
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                v5Var.i();
            } else {
                v5Var.q(((DateFormat) this.a.get(0)).format(date2));
            }
        }
    }
}
