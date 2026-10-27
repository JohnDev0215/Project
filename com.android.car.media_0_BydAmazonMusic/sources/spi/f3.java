package spi;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: DefaultDateTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f3 extends nb<Date> {
    public final Class<? extends Date> a;
    public final List<DateFormat> b;

    public f3(Class<? extends Date> cls) {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        c(cls);
        this.a = cls;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (d5.a()) {
            arrayList.add(e9.a(2, 2));
        }
    }

    public static Class<? extends Date> c(Class<? extends Date> cls) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            return cls;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        r4 = spi.s4.b(r4, new java.text.ParsePosition(0));
     */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<java.text.DateFormat>] */
    @Override // spi.nb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Date a(spi.o5 r4) throws java.io.IOException {
        /*
            r3 = this;
            spi.s5 r0 = r4.v()
            spi.s5 r1 = spi.s5.NULL
            if (r0 != r1) goto Ld
            r4.r()
            r3 = 0
            goto L5a
        Ld:
            java.lang.String r4 = r4.t()
            java.util.List<java.text.DateFormat> r0 = r3.b
            monitor-enter(r0)
            java.util.List<java.text.DateFormat> r1 = r3.b     // Catch: java.lang.Throwable -> L68
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L68
        L1a:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L2c
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L68
            java.text.DateFormat r2 = (java.text.DateFormat) r2     // Catch: java.lang.Throwable -> L68
            java.util.Date r4 = r2.parse(r4)     // Catch: java.text.ParseException -> L1a java.lang.Throwable -> L68
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L68
            goto L37
        L2c:
            java.text.ParsePosition r1 = new java.text.ParsePosition     // Catch: java.text.ParseException -> L61 java.lang.Throwable -> L68
            r2 = 0
            r1.<init>(r2)     // Catch: java.text.ParseException -> L61 java.lang.Throwable -> L68
            java.util.Date r4 = spi.s4.b(r4, r1)     // Catch: java.text.ParseException -> L61 java.lang.Throwable -> L68
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L68
        L37:
            java.lang.Class<? extends java.util.Date> r3 = r3.a
            java.lang.Class<java.util.Date> r0 = java.util.Date.class
            if (r3 != r0) goto L3f
            r3 = r4
            goto L5a
        L3f:
            java.lang.Class<java.sql.Timestamp> r0 = java.sql.Timestamp.class
            if (r3 != r0) goto L4d
            java.sql.Timestamp r3 = new java.sql.Timestamp
            long r0 = r4.getTime()
            r3.<init>(r0)
            goto L5a
        L4d:
            java.lang.Class<java.sql.Date> r0 = java.sql.Date.class
            if (r3 != r0) goto L5b
            java.sql.Date r3 = new java.sql.Date
            long r0 = r4.getTime()
            r3.<init>(r0)
        L5a:
            return r3
        L5b:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L61:
            r3 = move-exception
            spi.r5 r1 = new spi.r5     // Catch: java.lang.Throwable -> L68
            r1.<init>(r4, r3)     // Catch: java.lang.Throwable -> L68
            throw r1     // Catch: java.lang.Throwable -> L68
        L68:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L68
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.f3.a(spi.o5):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.ArrayList, java.util.List<java.text.DateFormat>] */
    @Override // spi.nb
    public final void b(v5 v5Var, Date date) throws IOException {
        Date date2 = date;
        if (date2 == null) {
            v5Var.i();
            return;
        }
        synchronized (this.b) {
            v5Var.q(((DateFormat) this.b.get(0)).format(date2));
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.ArrayList, java.util.List<java.text.DateFormat>] */
    public final String toString() {
        DateFormat dateFormat = (DateFormat) this.b.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            StringBuilder sbK = c.k("DefaultDateTypeAdapter(");
            sbK.append(((SimpleDateFormat) dateFormat).toPattern());
            sbK.append(')');
            return sbK.toString();
        }
        StringBuilder sbK2 = c.k("DefaultDateTypeAdapter(");
        sbK2.append(dateFormat.getClass().getSimpleName());
        sbK2.append(')');
        return sbK2.toString();
    }

    public f3(Class<? extends Date> cls, String str) {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        c(cls);
        this.a = cls;
        Locale locale = Locale.US;
        arrayList.add(new SimpleDateFormat(str, locale));
        if (Locale.getDefault().equals(locale)) {
            return;
        }
        arrayList.add(new SimpleDateFormat(str));
    }

    public f3(Class<? extends Date> cls, int i) {
        String str;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        c(cls);
        this.a = cls;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateInstance(i, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateInstance(i));
        }
        if (d5.a()) {
            if (i == 0) {
                str = "EEEE, MMMM d, y";
            } else if (i == 1) {
                str = "MMMM d, y";
            } else if (i == 2) {
                str = "MMM d, y";
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException(c.d("Unknown DateFormat style: ", i));
                }
                str = "M/d/yy";
            }
            arrayList.add(new SimpleDateFormat(str, locale));
        }
    }

    public f3(int i, int i2) {
        this(Date.class, i, i2);
    }

    public f3(Class<? extends Date> cls, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        c(cls);
        this.a = cls;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(i, i2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(i, i2));
        }
        if (d5.a()) {
            arrayList.add(e9.a(i, i2));
        }
    }
}
