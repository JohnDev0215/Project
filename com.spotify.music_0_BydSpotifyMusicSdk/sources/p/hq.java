package p;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hq implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ hq(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                lq lqVar = (lq) obj;
                if (lqVar.a()) {
                    ArrayList arrayList = lqVar.q;
                    if (arrayList.size() > 0 && !((kq) arrayList.get(0)).a.F) {
                        View view = lqVar.x;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((kq) it.next()).a.c();
                            }
                        } else {
                            lqVar.dismiss();
                        }
                        break;
                    }
                }
                break;
            case 1:
                vf2 vf2Var = (vf2) obj;
                if (vf2Var.a()) {
                    mj1 mj1Var = vf2Var.q;
                    if (!mj1Var.F) {
                        View view2 = vf2Var.v;
                        if (view2 != null && view2.isShown()) {
                            mj1Var.c();
                        } else {
                            vf2Var.dismiss();
                        }
                    }
                }
                break;
            case 2:
                t9 t9Var = (t9) obj;
                if (!t9Var.getInternalPopup().a()) {
                    t9Var.n.k(l9.b(t9Var), l9.a(t9Var));
                }
                ViewTreeObserver viewTreeObserver = t9Var.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    k9.a(viewTreeObserver, this);
                }
                break;
            default:
                q9 q9Var = (q9) obj;
                t9 t9Var2 = q9Var.N;
                WeakHashMap weakHashMap = zt2.a;
                if (lt2.b(t9Var2) && t9Var2.getGlobalVisibleRect(q9Var.L)) {
                    q9Var.s();
                    q9Var.c();
                } else {
                    q9Var.dismiss();
                }
                break;
        }
    }
}
