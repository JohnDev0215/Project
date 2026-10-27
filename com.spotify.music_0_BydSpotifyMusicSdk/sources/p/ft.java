package p;

/* JADX INFO: loaded from: classes.dex */
public final class ft extends kt {
    public final String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ft(String str) {
        super("/remote-config/rc-client-version");
        v03.j(str, "policyValue");
        this.c = str;
    }

    @Override // p.kw1
    public final String b() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ft) && v03.b(this.c, ((ft) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("ClientVersion(policyValue="), this.c, ')');
    }
}
