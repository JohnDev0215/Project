package com.google.gson;

import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes.dex */
public final class o extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Class<?>[] f4597a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Object f4598b;

    public o(Boolean bool) {
        t(bool);
    }

    private static boolean p(o oVar) {
        Object obj = oVar.f4598b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    private static boolean r(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : f4597a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.gson.j
    public boolean a() {
        return o() ? m().booleanValue() : Boolean.parseBoolean(h());
    }

    @Override // com.google.gson.j
    public double b() {
        return q() ? n().doubleValue() : Double.parseDouble(h());
    }

    @Override // com.google.gson.j
    public int c() {
        return q() ? n().intValue() : Integer.parseInt(h());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f4598b == null) {
            return oVar.f4598b == null;
        }
        if (p(this) && p(oVar)) {
            return n().longValue() == oVar.n().longValue();
        }
        Object obj2 = this.f4598b;
        if (!(obj2 instanceof Number) || !(oVar.f4598b instanceof Number)) {
            return obj2.equals(oVar.f4598b);
        }
        double dDoubleValue = n().doubleValue();
        double dDoubleValue2 = oVar.n().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    @Override // com.google.gson.j
    public long g() {
        return q() ? n().longValue() : Long.parseLong(h());
    }

    @Override // com.google.gson.j
    public String h() {
        return q() ? n().toString() : o() ? m().toString() : (String) this.f4598b;
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f4598b == null) {
            return 31;
        }
        if (p(this)) {
            jDoubleToLongBits = n().longValue();
        } else {
            Object obj = this.f4598b;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(n().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    Boolean m() {
        return (Boolean) this.f4598b;
    }

    public Number n() {
        Object obj = this.f4598b;
        return obj instanceof String ? new com.google.gson.internal.f((String) this.f4598b) : (Number) obj;
    }

    public boolean o() {
        return this.f4598b instanceof Boolean;
    }

    public boolean q() {
        return this.f4598b instanceof Number;
    }

    public boolean s() {
        return this.f4598b instanceof String;
    }

    void t(Object obj) {
        if (obj instanceof Character) {
            this.f4598b = String.valueOf(((Character) obj).charValue());
        } else {
            com.google.gson.internal.a.a((obj instanceof Number) || r(obj));
            this.f4598b = obj;
        }
    }

    public o(Number number) {
        t(number);
    }

    public o(String str) {
        t(str);
    }
}
