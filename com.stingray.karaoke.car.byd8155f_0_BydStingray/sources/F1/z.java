package F1;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class z implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public V f2352a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f2353b;
    public final /* synthetic */ InterfaceC0255n c;

    public z(View view, InterfaceC0255n interfaceC0255n) {
        this.f2353b = view;
        this.c = interfaceC0255n;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        V vC = V.c(windowInsets, view);
        int i7 = Build.VERSION.SDK_INT;
        InterfaceC0255n interfaceC0255n = this.c;
        if (i7 < 30) {
            A.a(windowInsets, this.f2353b);
            if (vC.equals(this.f2352a)) {
                return interfaceC0255n.x(view, vC).b();
            }
        }
        this.f2352a = vC;
        V vX = interfaceC0255n.x(view, vC);
        if (i7 >= 30) {
            return vX.b();
        }
        int[] iArr = H.f2303a;
        AbstractC0265y.b(view);
        return vX.b();
    }
}
