package p;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class m23 {
    public static final tz2 e = new tz2("AssetPackManager", 0);
    public final com.google.android.play.core.assetpacks.c a;
    public final f13 b;
    public final com.google.android.play.core.assetpacks.b c;
    public final f13 d;

    public m23(com.google.android.play.core.assetpacks.c cVar, f13 f13Var, com.google.android.play.core.assetpacks.b bVar, f13 f13Var2) {
        new Handler(Looper.getMainLooper());
        this.a = cVar;
        this.b = f13Var;
        this.c = bVar;
        this.d = f13Var2;
    }

    public final void a(boolean z) {
        o8 o8Var;
        com.google.android.play.core.assetpacks.b bVar = this.c;
        synchronized (bVar) {
            o8Var = bVar.e;
        }
        boolean z2 = o8Var != null;
        com.google.android.play.core.assetpacks.b bVar2 = this.c;
        synchronized (bVar2) {
            bVar2.f = z;
            bVar2.b();
        }
        if (!z || z2) {
            return;
        }
        ((Executor) ((h13) this.d).a()).execute(new rv(15, this));
    }
}
