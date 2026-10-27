package p;

/* JADX INFO: loaded from: classes.dex */
public final class sm2 {
    public final boolean a;

    public sm2(boolean z) {
        this.a = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof sm2) && this.a == ((sm2) obj).a;
    }

    public final int hashCode() {
        boolean z = this.a;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public final String toString() {
        return u4.n(new StringBuilder("Playability(playable="), this.a, ')');
    }
}
