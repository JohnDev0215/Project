package m3;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import e2.C1206n;
import e2.T;
import e2.U;
import e2.W;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: m3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC1808h implements U, View.OnClickListener, PopupWindow.OnDismissListener {
    public final /* synthetic */ s l;

    public ViewOnClickListenerC1808h(s sVar) {
        this.l = sVar;
    }

    @Override // e2.U
    public final void E(T t10) {
        C1206n c1206n = t10.f14001a;
        boolean zA = c1206n.a(4, 5, 13);
        s sVar = this.l;
        if (zA) {
            sVar.m();
        }
        if (c1206n.a(4, 5, 7, 13)) {
            sVar.o();
        }
        if (c1206n.a(8, 13)) {
            sVar.p();
        }
        if (c1206n.a(9, 13)) {
            sVar.r();
        }
        if (c1206n.a(8, 9, 11, 0, 16, 17, 13)) {
            sVar.l();
        }
        if (c1206n.a(11, 0, 13)) {
            sVar.s();
        }
        if (c1206n.a(12, 13)) {
            sVar.n();
        }
        if (c1206n.a(2, 13)) {
            sVar.t();
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        s sVar = this.l;
        W w10 = sVar.f19137u0;
        if (w10 == null) {
            return;
        }
        x xVar = sVar.l;
        xVar.g();
        if (sVar.f19144y == view) {
            if (w10.R0(9)) {
                w10.C0();
                return;
            }
            return;
        }
        if (sVar.f19142x == view) {
            if (w10.R0(7)) {
                w10.M0();
                return;
            }
            return;
        }
        if (sVar.f19078A == view) {
            if (w10.e() == 4 || !w10.R0(12)) {
                return;
            }
            w10.E0();
            return;
        }
        if (sVar.B == view) {
            if (w10.R0(11)) {
                w10.H0();
                return;
            }
            return;
        }
        if (sVar.f19146z == view) {
            if (AbstractC1343A.P(w10, sVar.f19147z0)) {
                AbstractC1343A.A(w10);
                return;
            } else {
                if (w10.R0(1)) {
                    w10.b();
                    return;
                }
                return;
            }
        }
        if (sVar.f19084E == view) {
            if (w10.R0(15)) {
                int i7 = w10.i();
                int i10 = sVar.f19085E0;
                for (int i11 = 1; i11 <= 2; i11++) {
                    int i12 = (i7 + i11) % 3;
                    if (i12 != 0) {
                        if (i12 != 1) {
                            if (i12 != 2 || (i10 & 2) == 0) {
                            }
                        } else if ((i10 & 1) == 0) {
                        }
                    }
                    i7 = i12;
                }
                w10.h(i7);
                return;
            }
            return;
        }
        if (sVar.f19086F == view) {
            if (w10.R0(14)) {
                w10.F(!w10.x0());
                return;
            }
            return;
        }
        View view2 = sVar.f19095K;
        if (view2 == view) {
            xVar.f();
            sVar.d(sVar.f19128q, view2);
            return;
        }
        View view3 = sVar.f19097L;
        if (view3 == view) {
            xVar.f();
            sVar.d(sVar.f19130r, view3);
            return;
        }
        View view4 = sVar.f19098M;
        if (view4 == view) {
            xVar.f();
            sVar.d(sVar.f19134t, view4);
            return;
        }
        ImageView imageView = sVar.f19089H;
        if (imageView == view) {
            xVar.f();
            sVar.d(sVar.f19132s, imageView);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        s sVar = this.l;
        if (sVar.f19096K0) {
            sVar.l.g();
        }
    }
}
