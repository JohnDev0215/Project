package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import spi.bd;

/* JADX INFO: loaded from: classes.dex */
public final class ad implements bd.a {
    public final w1 a;

    @Nullable
    public final m0 b;

    public ad(w1 w1Var) {
        this(w1Var, null);
    }

    public ad(w1 w1Var, @Nullable m0 m0Var) {
        this.a = w1Var;
        this.b = m0Var;
    }

    @NonNull
    public final byte[] a(int i) {
        m0 m0Var = this.b;
        return m0Var == null ? new byte[i] : (byte[]) m0Var.d(i, byte[].class);
    }
}
