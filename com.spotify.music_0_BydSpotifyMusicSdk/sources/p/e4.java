package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class e4 extends nw0 {
    public final /* synthetic */ int r;
    public final /* synthetic */ Object s;
    public final /* synthetic */ View t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e4(View view, View view2, Object obj, int i) {
        super(view2);
        this.r = i;
        this.t = view;
        this.s = obj;
    }

    @Override // p.nw0
    public final jd2 b() {
        switch (this.r) {
            case 0:
                c4 c4Var = ((f4) this.t).l.A;
                if (c4Var == null) {
                    return null;
                }
                return c4Var.a();
            default:
                return (q9) this.s;
        }
    }

    @Override // p.nw0
    public final boolean c() {
        int i = this.r;
        View view = this.t;
        switch (i) {
            case 0:
                ((f4) view).l.l();
                break;
            default:
                t9 t9Var = (t9) view;
                if (!t9Var.getInternalPopup().a()) {
                    t9Var.n.k(l9.b(t9Var), l9.a(t9Var));
                }
                break;
        }
        return true;
    }

    @Override // p.nw0
    public final boolean d() {
        switch (this.r) {
            case 0:
                g4 g4Var = ((f4) this.t).l;
                if (g4Var.C == null) {
                    g4Var.c();
                    break;
                }
                break;
            default:
                super.d();
                break;
        }
        return true;
    }
}
