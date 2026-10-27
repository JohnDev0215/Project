package p;

import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class k30 implements j30 {
    public static final yn c = new yn((Object) null);
    public final j80 a;
    public final AtomicReference b = new AtomicReference(null);

    public k30(j80 j80Var) {
        this.a = j80Var;
        ((zq1) j80Var).a(new af2(10, this));
    }

    @Override // p.j30
    public final im1 a(String str) {
        j30 j30Var = (j30) this.b.get();
        return j30Var == null ? c : j30Var.a(str);
    }

    @Override // p.j30
    public final boolean b() {
        j30 j30Var = (j30) this.b.get();
        return j30Var != null && j30Var.b();
    }

    @Override // p.j30
    public final void c(String str, String str2, long j, ri riVar) {
        Log.isLoggable("FirebaseCrashlytics", 2);
        ((zq1) this.a).a(new hr2(str, str2, j, riVar, 3));
    }

    @Override // p.j30
    public final boolean d(String str) {
        j30 j30Var = (j30) this.b.get();
        return j30Var != null && j30Var.d(str);
    }
}
