package p;

/* JADX INFO: loaded from: classes.dex */
public final class vp2 {
    public final String a;

    public vp2(String str) {
        v03.j(str, "uri");
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof vp2) && v03.b(this.a, ((vp2) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("UbiParentInfo(uri="), this.a, ')');
    }
}
