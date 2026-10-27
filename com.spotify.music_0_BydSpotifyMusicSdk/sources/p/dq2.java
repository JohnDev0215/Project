package p;

/* JADX INFO: loaded from: classes.dex */
public final class dq2 extends f60 {
    public final String h;

    public dq2(String str) {
        v03.j(str, "destination");
        this.h = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof dq2) && v03.b(this.h, ((dq2) obj).h);
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("ShowFromUri(destination="), this.h, ')');
    }
}
