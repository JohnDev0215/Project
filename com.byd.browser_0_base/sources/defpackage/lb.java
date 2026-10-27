package defpackage;

import android.animation.AnimatorSet;
import android.content.ComponentCallbacks;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import java.util.ArrayList;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lb implements PopupWindow.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ lb(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                new Handler().postDelayed((Runnable) obj2, 200L);
                xz.a.unregisterComponentCallbacks((ComponentCallbacks) obj);
                break;
            default:
                x6 x6Var = (x6) obj2;
                View view = (View) obj;
                oj2.Y(SystemClock.elapsedRealtime() - x6Var.r, "Mobile.AppMenu.TimeToTakeAction.".concat(x6Var.s ? "SelectedItem" : "Abandoned"));
                if (view instanceof ImageButton) {
                    ((ImageButton) view).setSelected(false);
                }
                AnimatorSet animatorSet = x6Var.q;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                i7 i7Var = x6Var.m;
                g7 g7Var = i7Var.f;
                if (g7Var.a.j.isShowing()) {
                    g7Var.b(0, 0, 2);
                }
                g7Var.c.cancel();
                o7 o7Var = (o7) i7Var.k;
                o7Var.a = null;
                if (o7Var.k) {
                    hj3 hj3VarA = hj3.a();
                    hj3VarA.getClass();
                    Choreographer.getInstance().postFrameCallback(new s5(1, hj3VarA));
                    hj3.a().a.c(o7Var.s);
                    o7Var.k = false;
                    o7Var.s = null;
                }
                int i2 = 0;
                while (true) {
                    ArrayList arrayList = i7Var.h;
                    if (i2 >= arrayList.size()) {
                        x6Var.j = null;
                        x6Var.l = null;
                        x6Var.k = null;
                        x6Var.n = null;
                        x6Var.q = null;
                    } else {
                        ((l7) arrayList.get(i2)).a(false);
                        i2++;
                    }
                    break;
                }
                break;
        }
    }
}
