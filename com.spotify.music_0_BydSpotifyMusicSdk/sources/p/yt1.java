package p;

import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class yt1 extends zu {
    public final String i;
    public final boolean j;

    public yt1(String str) {
        str.getClass();
        this.i = str;
        this.j = false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof yt1)) {
            return false;
        }
        yt1 yt1Var = (yt1) obj;
        return yt1Var.j == this.j && yt1Var.i.equals(this.i);
    }

    public final int hashCode() {
        return Boolean.valueOf(this.j).hashCode() + (this.i.hashCode() * 31);
    }

    @Override // p.zu
    public final Single q0(kh0 kh0Var, kh0 kh0Var2, kh0 kh0Var3, kh0 kh0Var4, kh0 kh0Var5, kh0 kh0Var6) {
        return kh0Var2.a(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Pause{featureIdentifier=");
        sb.append(this.i);
        sb.append(", systemInitiated=");
        return u4.n(sb, this.j, '}');
    }
}
