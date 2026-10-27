package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Qe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0291Qe extends AbstractBinderC0614cf {
    public BaseGmsClient a;
    public final int b;

    public BinderC0291Qe(BaseGmsClient baseGmsClient, int i) {
        this.a = baseGmsClient;
        this.b = i;
    }

    public final void v(int i, IBinder iBinder, Bundle bundle) {
        Cif.g(this.a, "onPostInitComplete can be called only once per call to getRemoteService");
        BaseGmsClient baseGmsClient = this.a;
        int i2 = this.b;
        Handler handler = baseGmsClient.i;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new C0309Re(baseGmsClient, i, iBinder, bundle)));
        this.a = null;
    }
}
