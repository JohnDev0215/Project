package defpackage;

import android.view.View;
import android.view.WindowInsets;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.Objects;

/* JADX INFO: renamed from: l3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnApplyWindowInsetsListenerC1085l3 implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ InterfaceC1029k3 a;

    public ViewOnApplyWindowInsetsListenerC1085l3(InterfaceC1029k3 interfaceC1029k3) {
        this.a = interfaceC1029k3;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        C1361q3 c1361q3 = windowInsets == null ? null : new C1361q3(windowInsets);
        CoordinatorLayout coordinatorLayout = ((C1743x1) this.a).a;
        if (!Objects.equals(coordinatorLayout.D, c1361q3)) {
            coordinatorLayout.D = c1361q3;
            boolean z = c1361q3 != null && c1361q3.a() > 0;
            coordinatorLayout.E = z;
            coordinatorLayout.setWillNotDraw(!z && coordinatorLayout.getBackground() == null);
            if (!((WindowInsets) c1361q3.a).isConsumed()) {
                int childCount = coordinatorLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (AbstractC1251o3.i(childAt)) {
                        Objects.requireNonNull((B1) childAt.getLayoutParams());
                    }
                }
            }
            coordinatorLayout.requestLayout();
        }
        return (WindowInsets) (c1361q3 != null ? c1361q3.a : null);
    }
}
