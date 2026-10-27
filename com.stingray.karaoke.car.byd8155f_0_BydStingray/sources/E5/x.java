package E5;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes.dex */
public abstract class x implements Closeable, Flushable {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f1836m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f1837n;
    public int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1838p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1839q;

    public abstract w b();

    public abstract w g();

    public abstract w i();

    public final String j() {
        return M.d(this.l, this.f1836m, this.f1837n, this.o);
    }

    public abstract w k(String str);

    public abstract w m();

    public final int o() {
        int i7 = this.l;
        if (i7 != 0) {
            return this.f1836m[i7 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public abstract w q(long j3);

    public abstract w v(String str);
}
