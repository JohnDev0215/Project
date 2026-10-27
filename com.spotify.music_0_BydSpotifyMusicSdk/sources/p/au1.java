package p;

import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class au1 extends zu {
    public final long i;

    public au1(long j) {
        this.i = j;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof au1) && ((au1) obj).i == this.i;
    }

    public final int hashCode() {
        return Long.valueOf(this.i).hashCode();
    }

    @Override // p.zu
    public final Single q0(kh0 kh0Var, kh0 kh0Var2, kh0 kh0Var3, kh0 kh0Var4, kh0 kh0Var5, kh0 kh0Var6) {
        return kh0Var6.a(this);
    }

    public final String toString() {
        return "SeekTo{ms=" + this.i + '}';
    }
}
