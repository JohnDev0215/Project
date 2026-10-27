package p;

import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: loaded from: classes.dex */
public final class t3 implements View.OnClickListener {
    public final /* synthetic */ int c = 0;
    public final Object e;
    public final /* synthetic */ Object k;

    public t3(ActionBarContextView actionBarContextView, l4 l4Var) {
        this.k = actionBarContextView;
        this.e = l4Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((l4) obj).a();
                break;
            default:
                hm2 hm2Var = (hm2) this.k;
                Window.Callback callback = hm2Var.k;
                if (callback != null && hm2Var.l) {
                    callback.onMenuItemSelected(0, (z3) obj);
                    break;
                }
                break;
        }
    }

    public t3(hm2 hm2Var) {
        this.k = hm2Var;
        this.e = new z3(hm2Var.a.getContext(), hm2Var.h);
    }
}
