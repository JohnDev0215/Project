package p;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class np1 implements xp {
    public final gx0 c;
    public final /* synthetic */ androidx.activity.b e;

    public np1(androidx.activity.b bVar, gx0 gx0Var) {
        this.e = bVar;
        this.c = gx0Var;
    }

    @Override // p.xp
    public final void cancel() {
        androidx.activity.b bVar = this.e;
        gb gbVar = bVar.b;
        gx0 gx0Var = this.c;
        gbVar.remove(gx0Var);
        gx0Var.getClass();
        gx0Var.b.remove(this);
        if (Build.VERSION.SDK_INT >= 33) {
            gx0Var.c = null;
            bVar.c();
        }
    }
}
