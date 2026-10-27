package j5;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import l5.AbstractC1776d;

/* JADX INFO: loaded from: classes.dex */
public final class s extends o {
    public final Serializable l;

    public s(Boolean bool) {
        Objects.requireNonNull(bool);
        this.l = bool;
    }

    public static boolean m(s sVar) {
        Serializable serializable = sVar.l;
        if (!(serializable instanceof Number)) {
            return false;
        }
        Number number = (Number) serializable;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final BigInteger a() {
        Serializable serializable = this.l;
        if (serializable instanceof BigInteger) {
            return (BigInteger) serializable;
        }
        if (m(this)) {
            return BigInteger.valueOf(d().longValue());
        }
        String strL = l();
        AbstractC1776d.d(strL);
        return new BigInteger(strL);
    }

    public final Number d() {
        Serializable serializable = this.l;
        if (serializable instanceof Number) {
            return (Number) serializable;
        }
        if (serializable instanceof String) {
            return new l5.j((String) serializable);
        }
        throw new UnsupportedOperationException("Primitive is neither a number nor a string");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s.class != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        Serializable serializable = this.l;
        Serializable serializable2 = sVar.l;
        if (serializable == null) {
            return serializable2 == null;
        }
        if (m(this) && m(sVar)) {
            return ((serializable instanceof BigInteger) || (serializable2 instanceof BigInteger)) ? a().equals(sVar.a()) : d().longValue() == sVar.d().longValue();
        }
        if (!(serializable instanceof Number) || !(serializable2 instanceof Number)) {
            return serializable.equals(serializable2);
        }
        if ((serializable instanceof BigDecimal) && (serializable2 instanceof BigDecimal)) {
            return (serializable instanceof BigDecimal ? (BigDecimal) serializable : AbstractC1776d.i(l())).compareTo(serializable2 instanceof BigDecimal ? (BigDecimal) serializable2 : AbstractC1776d.i(sVar.l())) == 0;
        }
        double dDoubleValue = serializable instanceof Number ? d().doubleValue() : Double.parseDouble(l());
        double dDoubleValue2 = serializable2 instanceof Number ? sVar.d().doubleValue() : Double.parseDouble(sVar.l());
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        Serializable serializable = this.l;
        if (serializable == null) {
            return 31;
        }
        if (m(this)) {
            jDoubleToLongBits = d().longValue();
        } else {
            if (!(serializable instanceof Number)) {
                return serializable.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(d().doubleValue());
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public final String l() {
        Serializable serializable = this.l;
        if (serializable instanceof String) {
            return (String) serializable;
        }
        if (serializable instanceof Number) {
            return d().toString();
        }
        if (serializable instanceof Boolean) {
            return ((Boolean) serializable).toString();
        }
        throw new AssertionError("Unexpected value type: " + serializable.getClass());
    }

    public s(Number number) {
        Objects.requireNonNull(number);
        this.l = number;
    }

    public s(String str) {
        Objects.requireNonNull(str);
        this.l = str;
    }
}
