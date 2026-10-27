package c.b.a;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes.dex */
public final class n extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f2913a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f2914b;

    public n(Boolean bool) {
        t(bool);
    }

    public static boolean p(n nVar) {
        Object obj = nVar.f2914b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public static boolean r(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : f2913a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // c.b.a.k
    public String d() {
        return q() ? n().toString() : o() ? j().toString() : (String) this.f2914b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        if (this.f2914b == null) {
            return nVar.f2914b == null;
        }
        if (p(this) && p(nVar)) {
            return n().longValue() == nVar.n().longValue();
        }
        Object obj2 = this.f2914b;
        if (!(obj2 instanceof Number) || !(nVar.f2914b instanceof Number)) {
            return obj2.equals(nVar.f2914b);
        }
        double dDoubleValue = n().doubleValue();
        double dDoubleValue2 = nVar.n().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f2914b == null) {
            return 31;
        }
        if (p(this)) {
            jDoubleToLongBits = n().longValue();
        } else {
            Object obj = this.f2914b;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(n().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public boolean i() {
        return o() ? j().booleanValue() : Boolean.parseBoolean(d());
    }

    public Boolean j() {
        return (Boolean) this.f2914b;
    }

    public double k() {
        return q() ? n().doubleValue() : Double.parseDouble(d());
    }

    public int l() {
        return q() ? n().intValue() : Integer.parseInt(d());
    }

    public long m() {
        return q() ? n().longValue() : Long.parseLong(d());
    }

    public Number n() {
        Object obj = this.f2914b;
        return obj instanceof String ? new LazilyParsedNumber((String) this.f2914b) : (Number) obj;
    }

    public boolean o() {
        return this.f2914b instanceof Boolean;
    }

    public boolean q() {
        return this.f2914b instanceof Number;
    }

    public boolean s() {
        return this.f2914b instanceof String;
    }

    public void t(Object obj) {
        if (obj instanceof Character) {
            this.f2914b = String.valueOf(((Character) obj).charValue());
        } else {
            c.b.a.t.a.a((obj instanceof Number) || r(obj));
            this.f2914b = obj;
        }
    }

    public n(Number number) {
        t(number);
    }

    public n(String str) {
        t(str);
    }
}
