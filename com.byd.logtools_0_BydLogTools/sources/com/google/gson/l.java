package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes.dex */
public final class l extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f4151a;

    public l(Boolean bool) {
        this.f4151a = com.google.gson.internal.a.b(bool);
    }

    private static boolean o(l lVar) {
        Object obj = lVar.f4151a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.f4151a == null) {
            return lVar.f4151a == null;
        }
        if (o(this) && o(lVar)) {
            return l().longValue() == lVar.l().longValue();
        }
        Object obj2 = this.f4151a;
        if (!(obj2 instanceof Number) || !(lVar.f4151a instanceof Number)) {
            return obj2.equals(lVar.f4151a);
        }
        double dDoubleValue = l().doubleValue();
        double dDoubleValue2 = lVar.l().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public boolean h() {
        return n() ? ((Boolean) this.f4151a).booleanValue() : Boolean.parseBoolean(m());
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f4151a == null) {
            return 31;
        }
        if (o(this)) {
            jDoubleToLongBits = l().longValue();
        } else {
            Object obj = this.f4151a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(l().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public double i() {
        return p() ? l().doubleValue() : Double.parseDouble(m());
    }

    public int j() {
        return p() ? l().intValue() : Integer.parseInt(m());
    }

    public long k() {
        return p() ? l().longValue() : Long.parseLong(m());
    }

    public Number l() {
        Object obj = this.f4151a;
        return obj instanceof String ? new LazilyParsedNumber((String) this.f4151a) : (Number) obj;
    }

    public String m() {
        return p() ? l().toString() : n() ? ((Boolean) this.f4151a).toString() : (String) this.f4151a;
    }

    public boolean n() {
        return this.f4151a instanceof Boolean;
    }

    public boolean p() {
        return this.f4151a instanceof Number;
    }

    public boolean q() {
        return this.f4151a instanceof String;
    }

    public l(Number number) {
        this.f4151a = com.google.gson.internal.a.b(number);
    }

    public l(String str) {
        this.f4151a = com.google.gson.internal.a.b(str);
    }
}
