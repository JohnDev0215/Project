package defpackage;

import android.graphics.RectF;
import android.view.ViewTreeObserver;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;
import org.chromium.chrome.browser.tasks.tab_management.c0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class vz2 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ vz2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                TabGridDialogView tabGridDialogView = (TabGridDialogView) obj;
                Callback<RectF> callback = TabGridDialogView.R;
                tabGridDialogView.getClass();
                if (!t41.f.f(tabGridDialogView.e, tabGridDialogView)) {
                    tabGridDialogView.K = tabGridDialogView.m.getWidth();
                    tabGridDialogView.f78J = tabGridDialogView.m.getHeight();
                    break;
                }
                break;
            default:
                ((c0) obj).m();
                break;
        }
    }
}
