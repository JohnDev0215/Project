package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class jd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Long f400a;
    public final String b;

    public jd(Long l, String str) {
        this.f400a = l;
        this.b = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof jd)) {
            return false;
        }
        jd jdVar = (jd) obj;
        String str = this.b;
        if (str == null ? jdVar.b == null : str.equals(jdVar.b)) {
            return this.f400a == null ? jdVar.b == null : this.b.equals(jdVar.b);
        }
        return false;
    }

    public int hashCode() {
        String str = this.b;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        Long l = this.f400a;
        return iHashCode + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        Long l = this.f400a;
        objArr[0] = l == null ? "None" : l.toString();
        objArr[1] = this.b;
        return String.format("Version: %s, ComponentId: %s", objArr);
    }
}
