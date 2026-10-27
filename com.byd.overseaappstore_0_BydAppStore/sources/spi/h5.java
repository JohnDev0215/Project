package spi;

import androidx.lifecycle.SavedStateHandle;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class h5 implements gv {
    public final g5 a;
    public final a5 b;
    public SavedStateHandle c;
    public hv d;

    private h5(g5 g5Var, a5 a5Var) {
        this.a = g5Var;
        this.b = a5Var;
    }

    public /* synthetic */ h5(g5 g5Var, a5 a5Var, d dVar) {
        this(g5Var, a5Var);
    }

    @Override // spi.gv
    public final gv a(SavedStateHandle savedStateHandle) {
        Objects.requireNonNull(savedStateHandle);
        this.c = savedStateHandle;
        return this;
    }

    @Override // spi.gv
    public final gv b(hv hvVar) {
        this.d = hvVar;
        return this;
    }

    @Override // spi.gv
    public final fv build() {
        cl.a(this.c, SavedStateHandle.class);
        cl.a(this.d, hv.class);
        return new i5(this.a, this.b, this.c, this.d, null);
    }
}
