package p;

/* JADX INFO: loaded from: classes.dex */
public final class dr {
    public static final cr b = new cr();
    public final Object a;

    public /* synthetic */ dr(Object obj) {
        this.a = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dr) {
            return v03.b(this.a, ((dr) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.a;
        if (obj instanceof br) {
            return ((br) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
