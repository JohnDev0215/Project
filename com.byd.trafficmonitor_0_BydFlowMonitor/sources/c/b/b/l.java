package c.b.b;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes.dex */
public final class l extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f4171a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4172b;

    public l(Boolean bool) {
        u(bool);
    }

    public static boolean q(l lVar) {
        Object obj = lVar.f4172b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public static boolean s(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : f4171a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.f4172b == null) {
            return lVar.f4172b == null;
        }
        if (q(this) && q(lVar)) {
            return n().longValue() == lVar.n().longValue();
        }
        Object obj2 = this.f4172b;
        if (!(obj2 instanceof Number) || !(lVar.f4172b instanceof Number)) {
            return obj2.equals(lVar.f4172b);
        }
        double dDoubleValue = n().doubleValue();
        double dDoubleValue2 = lVar.n().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f4172b == null) {
            return 31;
        }
        if (q(this)) {
            jDoubleToLongBits = n().longValue();
        } else {
            Object obj = this.f4172b;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(n().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public boolean i() {
        return p() ? j().booleanValue() : Boolean.parseBoolean(o());
    }

    public Boolean j() {
        return (Boolean) this.f4172b;
    }

    public double k() {
        return r() ? n().doubleValue() : Double.parseDouble(o());
    }

    public int l() {
        return r() ? n().intValue() : Integer.parseInt(o());
    }

    public long m() {
        return r() ? n().longValue() : Long.parseLong(o());
    }

    public Number n() {
        Object obj = this.f4172b;
        return obj instanceof String ? new LazilyParsedNumber((String) this.f4172b) : (Number) obj;
    }

    public String o() {
        return r() ? n().toString() : p() ? j().toString() : (String) this.f4172b;
    }

    public boolean p() {
        return this.f4172b instanceof Boolean;
    }

    public boolean r() {
        return this.f4172b instanceof Number;
    }

    public boolean t() {
        return this.f4172b instanceof String;
    }

    public void u(Object obj) {
        if (obj instanceof Character) {
            this.f4172b = String.valueOf(((Character) obj).charValue());
        } else {
            c.b.b.r.a.a((obj instanceof Number) || s(obj));
            this.f4172b = obj;
        }
    }

    public l(Number number) {
        u(number);
    }

    public l(String str) {
        u(str);
    }
}
