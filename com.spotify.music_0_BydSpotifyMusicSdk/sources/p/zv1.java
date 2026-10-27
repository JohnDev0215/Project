package p;

/* JADX INFO: loaded from: classes.dex */
public final class zv1 {
    public final String a;

    public zv1(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zv1) && v03.b(this.a, ((zv1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Publisher(name="), this.a, ')');
    }
}
