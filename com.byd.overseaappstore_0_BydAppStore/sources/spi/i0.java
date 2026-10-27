package spi;

import android.content.Context;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class i0 implements ll {
    public final h0 a;

    public i0(h0 h0Var) {
        this.a = h0Var;
    }

    public static Context a(h0 h0Var) {
        Context context = h0Var.a;
        Objects.requireNonNull(context, "Cannot return null from a non-@Nullable @Provides method");
        return context;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a);
    }
}
