package p;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class m60 {
    public final Context a;
    public final l31 b;

    public m60(Application application, coil.b bVar) {
        this.a = application;
        this.b = bVar;
    }

    public final fu a(Comparable comparable) {
        o31 o31Var = new o31(this.a);
        o31Var.c = comparable;
        return new fu(this.b, o31Var);
    }
}
