package n8;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class e extends h6.e {
    public final String c;
    public final String d;

    public e(String str, String str2) {
        m.f(str, "name");
        m.f(str2, "desc");
        this.c = str;
        this.d = str2;
    }

    @Override // h6.e
    public final String e() {
        return this.c + this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return m.a(this.c, eVar.c) && m.a(this.d, eVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + (this.c.hashCode() * 31);
    }
}
