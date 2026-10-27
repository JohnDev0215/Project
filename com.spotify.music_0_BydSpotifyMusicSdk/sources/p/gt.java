package p;

/* JADX INFO: loaded from: classes.dex */
public final class gt extends kt {
    public final String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gt(String str) {
        super("/remote-config/device-brand");
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
        return (obj instanceof gt) && v03.b(this.c, ((gt) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("DeviceBrand(policyValue="), this.c, ')');
    }
}
