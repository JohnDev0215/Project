package defpackage;

import android.app.Dialog;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b52 implements ViewTreeObserver.OnScrollChangedListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Dialog b;

    public /* synthetic */ b52(Dialog dialog, int i) {
        this.a = i;
        this.b = dialog;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        int i = this.a;
        Dialog dialog = this.b;
        switch (i) {
            case 0:
                c52 c52Var = (c52) dialog;
                ScrollView scrollView = c52Var.l;
                if (!scrollView.canScrollVertically(130)) {
                    c52Var.j.setVisibility(8);
                    c52Var.k.setVisibility(0);
                    scrollView.post(new a52(c52Var, 1));
                }
                break;
            default:
                d52 d52Var = (d52) dialog;
                ScrollView scrollView2 = d52Var.h;
                if (!scrollView2.canScrollVertically(130)) {
                    d52Var.f.setVisibility(8);
                    d52Var.g.setVisibility(0);
                    scrollView2.post(new qm0(24, d52Var));
                }
                break;
        }
    }
}
