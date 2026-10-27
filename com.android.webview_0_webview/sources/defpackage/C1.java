package defpackage;

import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ CoordinatorLayout s;

    public C1(CoordinatorLayout coordinatorLayout) {
        this.s = coordinatorLayout;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        this.s.l(0);
        return true;
    }
}
