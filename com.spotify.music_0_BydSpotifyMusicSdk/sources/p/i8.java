package p;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i8 extends xu2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ i8(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.wu2
    public final void a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                f8 f8Var = (f8) obj;
                f8Var.e.D.setAlpha(1.0f);
                s8 s8Var = f8Var.e;
                s8Var.G.d(null);
                s8Var.G = null;
                break;
            case 1:
                s8 s8Var2 = (s8) obj;
                s8Var2.D.setAlpha(1.0f);
                s8Var2.G.d(null);
                s8Var2.G = null;
                break;
            default:
                j8 j8Var = (j8) obj;
                j8Var.e.D.setVisibility(8);
                s8 s8Var3 = j8Var.e;
                PopupWindow popupWindow = s8Var3.E;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (s8Var3.D.getParent() instanceof View) {
                    View view = (View) s8Var3.D.getParent();
                    WeakHashMap weakHashMap = zt2.a;
                    mt2.c(view);
                }
                s8Var3.D.e();
                s8Var3.G.d(null);
                s8Var3.G = null;
                ViewGroup viewGroup = s8Var3.I;
                WeakHashMap weakHashMap2 = zt2.a;
                mt2.c(viewGroup);
                break;
        }
    }

    @Override // p.xu2, p.wu2
    public final void c() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((f8) obj).e.D.setVisibility(0);
                break;
            case 1:
                s8 s8Var = (s8) obj;
                s8Var.D.setVisibility(0);
                if (s8Var.D.getParent() instanceof View) {
                    View view = (View) s8Var.D.getParent();
                    WeakHashMap weakHashMap = zt2.a;
                    mt2.c(view);
                }
                break;
        }
    }
}
