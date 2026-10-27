package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Vt extends Xt {
    public final C1406qu[] j;
    public final ArrayList k;

    public Vt(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, int i, Rt rt) {
        super(handler, runnable, str, str2, z, z2, z3, null);
        this.j = new C1406qu[i];
        this.k = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.k.add(Integer.valueOf(i2));
        }
    }

    @Override // defpackage.Xt
    public C1406qu c(Context context, Bundle bundle, InterfaceC1351pu interfaceC1351pu) {
        if (this.k.isEmpty()) {
            return null;
        }
        int iIntValue = ((Integer) this.k.remove(0)).intValue();
        ComponentName componentName = new ComponentName(this.d, this.e + iIntValue);
        Tt tt = this.i;
        boolean z = this.f;
        boolean z2 = this.g;
        Objects.requireNonNull((Ut) tt);
        C1406qu c1406qu = new C1406qu(context, componentName, z, z2, bundle, null);
        this.j[iIntValue] = c1406qu;
        c1406qu.i(this.h, interfaceC1351pu);
        return c1406qu;
    }

    @Override // defpackage.Xt
    public void d(C1406qu c1406qu) {
        int iIndexOf = Arrays.asList(this.j).indexOf(c1406qu);
        if (iIndexOf == -1) {
            Ts.a("ChildConnAllocator", "Unable to find connection to free.", new Object[0]);
        } else {
            this.j[iIndexOf] = null;
            this.k.add(Integer.valueOf(iIndexOf));
        }
    }

    @Override // defpackage.Xt
    public int e() {
        return this.j.length;
    }
}
