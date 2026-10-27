package defpackage;

import android.view.View;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vw implements View.OnSystemUiVisibilityChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ vw(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(int i) {
        int i2 = this.e;
        Object obj = this.f;
        switch (i2) {
            case 0:
                yj1 yj1Var = CompositorViewHolder.b0;
                ((CompositorViewHolder) obj).k();
                break;
            default:
                ((lx0) obj).a(3000);
                break;
        }
    }
}
