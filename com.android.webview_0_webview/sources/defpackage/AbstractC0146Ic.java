package defpackage;

import android.os.Looper;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: Ic, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class AbstractC0146Ic {
    public static final Set a = Collections.newSetFromMap(new WeakHashMap());

    public abstract void d();

    public abstract void e();

    public abstract AbstractC0343Tc f(AbstractC0343Tc abstractC0343Tc);

    public InterfaceC1882zc g(AbstractC1662vc abstractC1662vc) {
        throw new UnsupportedOperationException();
    }

    public abstract Looper h();

    public abstract boolean i();
}
