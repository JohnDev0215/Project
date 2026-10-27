package p;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class mw2 extends xu2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ ow2 b;

    public /* synthetic */ mw2(ow2 ow2Var, int i) {
        this.a = i;
        this.b = ow2Var;
    }

    @Override // p.wu2
    public final void a() {
        View view;
        int i = this.a;
        ow2 ow2Var = this.b;
        switch (i) {
            case 0:
                if (ow2Var.w && (view = ow2Var.o) != null) {
                    view.setTranslationY(0.0f);
                    ow2Var.l.setTranslationY(0.0f);
                }
                ow2Var.l.setVisibility(8);
                ow2Var.l.setTransitioning(false);
                ow2Var.A = null;
                k4 k4Var = ow2Var.s;
                if (k4Var != null) {
                    k4Var.a(ow2Var.r);
                    ow2Var.r = null;
                    ow2Var.s = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = ow2Var.k;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = zt2.a;
                    mt2.c(actionBarOverlayLayout);
                }
                break;
            default:
                ow2Var.A = null;
                ow2Var.l.requestLayout();
                break;
        }
    }
}
