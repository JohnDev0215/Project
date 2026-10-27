package spi;

import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n5 extends i5 {
    public static final Class<?>[] b = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object a;

    public n5(Boolean bool) {
        f(bool);
    }

    public static boolean e(n5 n5Var) {
        Object obj = n5Var.a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final boolean b() {
        Object obj = this.a;
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(d());
    }

    public final Number c() {
        Object obj = this.a;
        return obj instanceof String ? new x5((String) obj) : (Number) obj;
    }

    public final String d() {
        Object obj = this.a;
        return obj instanceof Number ? c().toString() : obj instanceof Boolean ? ((Boolean) obj).toString() : (String) obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n5.class != obj.getClass()) {
            return false;
        }
        n5 n5Var = (n5) obj;
        if (this.a == null) {
            return n5Var.a == null;
        }
        if (e(this) && e(n5Var)) {
            return c().longValue() == n5Var.c().longValue();
        }
        Object obj2 = this.a;
        if (!(obj2 instanceof Number) || !(n5Var.a instanceof Number)) {
            return obj2.equals(n5Var.a);
        }
        double dDoubleValue = c().doubleValue();
        double dDoubleValue2 = n5Var.c().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof java.lang.Character
            if (r0 == 0) goto L11
            java.lang.Character r8 = (java.lang.Character) r8
            char r8 = r8.charValue()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r7.a = r8
            goto L3d
        L11:
            boolean r0 = r8 instanceof java.lang.Number
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L37
            boolean r0 = r8 instanceof java.lang.String
            if (r0 == 0) goto L1d
        L1b:
            r0 = r2
            goto L35
        L1d:
            java.lang.Class r0 = r8.getClass()
            java.lang.Class<?>[] r3 = spi.n5.b
            r4 = 16
            r5 = r1
        L26:
            if (r5 >= r4) goto L34
            r6 = r3[r5]
            boolean r6 = r6.isAssignableFrom(r0)
            if (r6 == 0) goto L31
            goto L1b
        L31:
            int r5 = r5 + 1
            goto L26
        L34:
            r0 = r1
        L35:
            if (r0 == 0) goto L38
        L37:
            r1 = r2
        L38:
            spi.a.a(r1)
            r7.a = r8
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.n5.f(java.lang.Object):void");
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        if (this.a == null) {
            return 31;
        }
        if (e(this)) {
            jDoubleToLongBits = c().longValue();
        } else {
            Object obj = this.a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(c().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public n5(Number number) {
        f(number);
    }

    public n5(String str) {
        f(str);
    }

    public n5(Character ch) {
        f(ch);
    }

    public n5(Object obj) {
        f(obj);
    }
}
