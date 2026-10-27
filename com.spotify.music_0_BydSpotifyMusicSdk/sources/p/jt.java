package p;

/* JADX INFO: loaded from: classes.dex */
public final class jt extends kt {
    public final String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jt(String str) {
        super("/remote-config/device-model");
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
        return (obj instanceof jt) && v03.b(this.c, ((jt) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("DeviceModel(policyValue="), this.c, ')');
    }
}
