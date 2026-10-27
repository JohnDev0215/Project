package defpackage;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: MediaCaptureOverlayController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zc1 implements zi3 {
    public static final aj3<zc1> i = new aj3<>(zc1.class);
    public View f;
    public Tab h;
    public final a e = new a();
    public final SparseArray<Tab> g = new SparseArray<>();

    /* JADX INFO: compiled from: MediaCaptureOverlayController.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D(TabImpl tabImpl, int i, int i2, int i3, int i4, int i5) {
            zc1 zc1Var = zc1.this;
            if (tabImpl == zc1Var.h) {
                zc1Var.b();
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            zc1.this.a(tab);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void S0(Tab tab, boolean z) {
            zc1 zc1Var = zc1.this;
            if (z && tab != zc1Var.h) {
                zc1Var.h = tab;
                zc1Var.b();
                zc1Var.f.setVisibility(0);
            } else {
                if (z || tab != zc1Var.h) {
                    return;
                }
                zc1Var.f.setVisibility(8);
                zc1Var.h = null;
            }
        }
    }

    public zc1(g3 g3Var, View view) {
        this.f = view;
        i.a(g3Var.r, this);
    }

    public final void a(Tab tab) {
        int id = tab.getId();
        SparseArray<Tab> sparseArray = this.g;
        if (sparseArray.indexOfKey(id) < 0) {
            return;
        }
        tab.t(this.e);
        sparseArray.remove(id);
        if (tab == this.h) {
            this.f.setVisibility(8);
            this.h = null;
        }
    }

    public final void b() {
        Tab tab = this.h;
        if (tab == null) {
            return;
        }
        qy2 qy2VarY1 = qy2.y1(tab);
        if (qy2VarY1.k) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f.getLayoutParams();
            marginLayoutParams.topMargin = qy2VarY1.h;
            marginLayoutParams.bottomMargin = qy2VarY1.g;
            this.f.setLayoutParams(marginLayoutParams);
        }
    }
}
