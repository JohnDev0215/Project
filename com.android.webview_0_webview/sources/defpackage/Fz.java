package defpackage;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Fz {
    public final Xt a;
    public C1406qu b;
    public boolean c;
    public InterfaceC1351pu d;

    public Fz(Context context, Xt xt, Bundle bundle) {
        this.a = xt;
        Dz dz = new Dz(this);
        Xt xt2 = this.a;
        this.b = xt2.c(context, bundle, new Rt(xt2, dz));
    }

    public static void a(Fz fz) {
        fz.b = null;
        fz.c = false;
    }
}
