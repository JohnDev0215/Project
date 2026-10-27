package defpackage;

import com.google.android.gms.signin.internal.zzm;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: Gd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0111Gd extends AbstractBinderC0841gj {
    public final WeakReference a;

    public BinderC0111Gd(C1773xd c1773xd) {
        this.a = new WeakReference(c1773xd);
    }

    @Override // defpackage.InterfaceC0785fj
    public final void a(zzm zzmVar) {
        C1773xd c1773xd = (C1773xd) this.a.get();
        if (c1773xd == null) {
            return;
        }
        C0272Pd c0272Pd = c1773xd.a;
        c0272Pd.e.sendMessage(c0272Pd.e.obtainMessage(1, new C0093Fd(c1773xd, c1773xd, zzmVar)));
    }
}
