package p;

import android.content.Context;
import android.view.View;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class c4 extends hj1 {
    public final /* synthetic */ int m = 0;
    public final /* synthetic */ g4 n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c4(g4 g4Var, Context context, si1 si1Var, f4 f4Var) {
        super(R.attr.actionOverflowMenuStyle, 0, context, f4Var, si1Var, true);
        this.n = g4Var;
        this.g = 8388613;
        p01 p01Var = g4Var.E;
        this.i = p01Var;
        ej1 ej1Var = this.j;
        if (ej1Var != null) {
            ej1Var.j(p01Var);
        }
    }

    @Override // p.hj1
    public final void c() {
        int i = this.m;
        g4 g4Var = this.n;
        switch (i) {
            case 0:
                g4Var.B = null;
                super.c();
                break;
            default:
                si1 si1Var = g4Var.k;
                if (si1Var != null) {
                    si1Var.c(true);
                }
                g4Var.A = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c4(g4 g4Var, Context context, qg2 qg2Var, View view) {
        super(R.attr.actionOverflowMenuStyle, 0, context, view, qg2Var, false);
        this.n = g4Var;
        if (!qg2Var.A.f()) {
            View view2 = g4Var.q;
            this.f = view2 == null ? (View) g4Var.f122p : view2;
        }
        p01 p01Var = g4Var.E;
        this.i = p01Var;
        ej1 ej1Var = this.j;
        if (ej1Var != null) {
            ej1Var.j(p01Var);
        }
    }
}
