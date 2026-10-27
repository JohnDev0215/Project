package p;

import com.google.gson.internal.LazilyParsedNumber;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class r71 extends i71 {
    public final Serializable c;

    public r71(Boolean bool) {
        bool.getClass();
        this.c = bool;
    }

    public static boolean c(r71 r71Var) {
        Serializable serializable = r71Var.c;
        if (!(serializable instanceof Number)) {
            return false;
        }
        Number number = (Number) serializable;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final Number a() {
        Serializable serializable = this.c;
        return serializable instanceof String ? new LazilyParsedNumber((String) serializable) : (Number) serializable;
    }

    public final String b() {
        Serializable serializable = this.c;
        return serializable instanceof Number ? a().toString() : serializable instanceof Boolean ? ((Boolean) serializable).toString() : (String) serializable;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r71.class != obj.getClass()) {
            return false;
        }
        r71 r71Var = (r71) obj;
        Serializable serializable = this.c;
        Serializable serializable2 = r71Var.c;
        if (serializable == null) {
            return serializable2 == null;
        }
        if (c(this) && c(r71Var)) {
            return a().longValue() == r71Var.a().longValue();
        }
        if (!(serializable instanceof Number) || !(serializable2 instanceof Number)) {
            return serializable.equals(serializable2);
        }
        double dDoubleValue = a().doubleValue();
        double dDoubleValue2 = r71Var.a().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        Serializable serializable = this.c;
        if (serializable == null) {
            return 31;
        }
        if (c(this)) {
            jDoubleToLongBits = a().longValue();
        } else {
            if (!(serializable instanceof Number)) {
                return serializable.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(a().doubleValue());
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public r71(Number number) {
        number.getClass();
        this.c = number;
    }

    public r71(String str) {
        str.getClass();
        this.c = str;
    }
}
