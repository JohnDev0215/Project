package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Wt extends Xt {
    public final int j;
    public final C1083l1 k;
    public int l;

    public Wt(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, int i, Rt rt) {
        super(handler, runnable, str, str2, z, z2, z3, null);
        this.k = new C1083l1();
        this.j = i;
    }

    @Override // defpackage.Xt
    public C1406qu c(Context context, Bundle bundle, InterfaceC1351pu interfaceC1351pu) {
        C1406qu c1406quF = f(context, bundle);
        if (c1406quF == null) {
            return null;
        }
        this.k.add(c1406quF);
        c1406quF.i(this.h, interfaceC1351pu);
        return c1406quF;
    }

    @Override // defpackage.Xt
    public void d(C1406qu c1406qu) {
        this.k.remove(c1406qu);
    }

    @Override // defpackage.Xt
    public int e() {
        return -1;
    }

    public final C1406qu f(Context context, Bundle bundle) {
        if (this.k.u >= this.j) {
            return null;
        }
        ComponentName componentName = new ComponentName(this.d, this.e);
        String string = Integer.toString(this.l);
        this.l++;
        Tt tt = this.i;
        boolean z = this.f;
        boolean z2 = this.g;
        Objects.requireNonNull((Ut) tt);
        return new C1406qu(context, componentName, z, z2, bundle, string);
    }
}
