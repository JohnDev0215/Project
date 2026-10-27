package coil.size;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import p.c00;
import p.ci1;
import p.h12;
import p.lq2;
import p.qd2;
import p.rd2;
import p.ry0;
import p.t90;
import p.u90;
import p.vr2;
import p.yu2;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public interface a extends rd2 {
    static ci1 b(int i, int i2, int i3) {
        if (i == -2) {
            return u90.f195p;
        }
        int i4 = i - i3;
        if (i4 > 0) {
            return new t90(i4);
        }
        int i5 = i2 - i3;
        if (i5 > 0) {
            return new t90(i5);
        }
        return null;
    }

    @Override // p.rd2
    default Object a(c00 c00Var) {
        qd2 qd2VarC = super.c();
        if (qd2VarC != null) {
            return qd2VarC;
        }
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        final ViewTreeObserver viewTreeObserver = ((h12) this).a.getViewTreeObserver();
        final yu2 yu2Var = new yu2(this, viewTreeObserver, zpVar);
        viewTreeObserver.addOnPreDrawListener(yu2Var);
        zpVar.t(new ry0() { // from class: coil.size.ViewSizeResolver$size$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                a aVar = this.this$0;
                ViewTreeObserver viewTreeObserver2 = viewTreeObserver;
                yu2 yu2Var2 = yu2Var;
                aVar.getClass();
                if (viewTreeObserver2.isAlive()) {
                    viewTreeObserver2.removeOnPreDrawListener(yu2Var2);
                } else {
                    ((h12) aVar).a.getViewTreeObserver().removeOnPreDrawListener(yu2Var2);
                }
                return lq2.a;
            }
        });
        return zpVar.q();
    }

    default qd2 c() {
        h12 h12Var = (h12) this;
        View view = h12Var.a;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int i = layoutParams != null ? layoutParams.width : -1;
        int width = view.getWidth();
        boolean z = h12Var.b;
        ci1 ci1VarB = b(i, width, z ? view.getPaddingRight() + view.getPaddingLeft() : 0);
        if (ci1VarB == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        ci1 ci1VarB2 = b(layoutParams2 != null ? layoutParams2.height : -1, view.getHeight(), z ? view.getPaddingBottom() + view.getPaddingTop() : 0);
        if (ci1VarB2 == null) {
            return null;
        }
        return new qd2(ci1VarB, ci1VarB2);
    }
}
