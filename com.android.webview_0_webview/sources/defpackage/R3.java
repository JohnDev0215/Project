package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class R3 extends P3 {
    public final Activity a;
    public final Context b;
    public final Handler c;
    public final LayoutInflaterFactory2C0974j4 d;

    public R3(O3 o3) {
        Handler handler = o3.t;
        this.d = new LayoutInflaterFactory2C0974j4();
        this.a = o3;
        this.b = o3;
        if (handler == null) {
            throw new NullPointerException("handler == null");
        }
        this.c = handler;
    }

    public abstract void d(K3 k3, Intent intent, int i, Bundle bundle);
}
