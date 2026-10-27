package p;

import com.spotify.automotive.ubi.Referrer;

/* JADX INFO: loaded from: classes.dex */
public final class g82 {
    public final Referrer a;
    public int b;

    public g82(Referrer referrer) {
        v03.j(referrer, "referrer");
        this.a = referrer;
        this.b = 0;
    }

    public final int a() {
        int i = this.b;
        this.b = i + 1;
        return i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g82)) {
            return false;
        }
        g82 g82Var = (g82) obj;
        return this.a == g82Var.a && this.b == g82Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaIdParams(referrer=");
        sb.append(this.a);
        sb.append(", pos=");
        return u4.k(sb, this.b, ')');
    }
}
