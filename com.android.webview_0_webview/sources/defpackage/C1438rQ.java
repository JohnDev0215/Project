package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* JADX INFO: renamed from: rQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1438rQ extends QN {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public CP f33J;
    public C1546tO K;

    public C1438rQ(Context context, CP cp) {
        super(context);
        this.f33J = cp;
        AN.b = new HN(Q());
        this.C = new C1384qQ(this.f33J);
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final WeakReference Q() {
        return new WeakReference(this.f33J.c());
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final C1546tO T() {
        return this.K;
    }

    @Override // defpackage.QN
    public final boolean a0(Intent intent, int i) {
        CP cp = this.f33J;
        Objects.requireNonNull(cp);
        try {
            return ((TR) cp.a).i(new BinderC0998jS(intent), i, new BinderC0998jS(null));
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }
}
