package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.signin.internal.zzm;
import java.util.Set;

/* JADX INFO: renamed from: ke, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1059ke extends AbstractBinderC0841gj implements InterfaceC0110Gc, InterfaceC0128Hc {
    public static AbstractC1552tc h = AbstractC1230nj.c;
    public final Context a;
    public final Handler b;
    public final AbstractC1552tc c;
    public Set d;
    public C0381Ve e;
    public InterfaceC1395qj f;
    public InterfaceC1115le g;

    public BinderC1059ke(Context context, Handler handler, C0381Ve c0381Ve, AbstractC1552tc abstractC1552tc) {
        this.a = context;
        this.b = handler;
        Cif.g(c0381Ve, "ClientSettings must not be null");
        this.e = c0381Ve;
        this.d = c0381Ve.b;
        this.c = abstractC1552tc;
    }

    @Override // defpackage.InterfaceC0785fj
    public final void a(zzm zzmVar) {
        this.b.post(new RunnableC1170me(this, zzmVar));
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void d(int i) {
        ((BaseGmsClient) this.f).disconnect();
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void f(Bundle bundle) {
        ((C0674dj) this.f).v(this);
    }

    @Override // defpackage.InterfaceC0947id
    public final void i(ConnectionResult connectionResult) {
        ((C0451Zc) this.g).b(connectionResult);
    }
}
