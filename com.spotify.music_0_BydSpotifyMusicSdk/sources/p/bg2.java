package p;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bg2 implements Runnable {
    public final /* synthetic */ int c = 1;
    public boolean e;
    public int k;
    public final Object l;
    public final Object m;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public bg2(qx1 qx1Var, xf2 xf2Var) {
        this(qx1Var, xf2Var, true, -512);
        v03.j(qx1Var, "processor");
    }

    @Override // java.lang.Runnable
    public final void run() {
        vy2 vy2VarB;
        switch (this.c) {
            case 0:
                if (!this.e) {
                    qx1 qx1Var = (qx1) this.l;
                    xf2 xf2Var = (xf2) this.m;
                    int i = this.k;
                    qx1Var.getClass();
                    String str = xf2Var.a.a;
                    synchronized (qx1Var.k) {
                        try {
                            if (qx1Var.f.get(str) != null) {
                                vc1.c().getClass();
                            } else {
                                Set set = (Set) qx1Var.h.get(str);
                                if (set != null && set.contains(xf2Var)) {
                                    qx1.d(qx1Var.b(str), i);
                                }
                            }
                        } finally {
                        }
                    }
                    break;
                } else {
                    qx1 qx1Var2 = (qx1) this.l;
                    xf2 xf2Var2 = (xf2) this.m;
                    int i2 = this.k;
                    qx1Var2.getClass();
                    String str2 = xf2Var2.a.a;
                    synchronized (qx1Var2.k) {
                        vy2VarB = qx1Var2.b(str2);
                        break;
                    }
                    qx1.d(vy2VarB, i2);
                }
                vc1 vc1VarC = vc1.c();
                vc1.d("StopWorkRunnable");
                String str3 = ((xf2) this.m).a.a;
                vc1VarC.getClass();
                return;
            default:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.m;
                du2 du2Var = bottomSheetBehavior.H;
                if (du2Var == null || !du2Var.f()) {
                    bottomSheetBehavior.A(this.k);
                } else {
                    View view = (View) this.l;
                    WeakHashMap weakHashMap = zt2.a;
                    it2.m(view, this);
                }
                this.e = false;
                return;
        }
    }

    public bg2(qx1 qx1Var, xf2 xf2Var, boolean z, int i) {
        v03.j(qx1Var, "processor");
        v03.j(xf2Var, "token");
        this.l = qx1Var;
        this.m = xf2Var;
        this.e = z;
        this.k = i;
    }

    public bg2(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
        this.m = bottomSheetBehavior;
        this.l = view;
        this.k = i;
    }
}
