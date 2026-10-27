package spi;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class h implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ h(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        switch (this.a) {
            case 0:
                return i.a((i) this.b, view, windowInsets);
            default:
                return com.android.car.ui.toolbar.f.a((com.android.car.ui.toolbar.f) this.b, view, windowInsets);
        }
    }
}
