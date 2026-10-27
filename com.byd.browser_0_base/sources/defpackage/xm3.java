package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes.dex */
@c70(c = "androidx.core.view.ViewKt$allViews$1", f = "View.kt", l = {414, 416}, m = "invokeSuspend")
public final class xm3 extends ub2 implements nr0<ni2<? super View>, b00<? super vi3>, Object> {
    public int g;
    public /* synthetic */ Object h;
    public final /* synthetic */ View i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xm3(View view, b00<? super xm3> b00Var) {
        super(b00Var);
        this.i = view;
    }

    @Override // defpackage.nr0
    public final Object d(ni2<? super View> ni2Var, b00<? super vi3> b00Var) {
        return ((xm3) e(ni2Var, b00Var)).h(vi3.a);
    }

    @Override // defpackage.ed
    public final b00<vi3> e(Object obj, b00<?> b00Var) {
        xm3 xm3Var = new xm3(this.i, b00Var);
        xm3Var.h = obj;
        return xm3Var;
    }

    @Override // defpackage.ed
    public final Object h(Object obj) throws Throwable {
        t00 t00Var = t00.COROUTINE_SUSPENDED;
        int i = this.g;
        View view = this.i;
        if (i == 0) {
            a8.i0(obj);
            ni2 ni2Var = (ni2) this.h;
            this.h = ni2Var;
            this.g = 1;
            ni2Var.b(view, this);
            return t00Var;
        }
        if (i == 1) {
            ni2 ni2Var2 = (ni2) this.h;
            a8.i0(obj);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                i21.e(viewGroup, "<this>");
                tm3 tm3Var = new tm3(viewGroup, null);
                this.h = null;
                this.g = 2;
                ni2Var2.getClass();
                mi2 mi2Var = new mi2();
                mi2Var.h = oj2.g(mi2Var, mi2Var, tm3Var);
                Object objD = ni2Var2.d(mi2Var, this);
                if (objD != t00Var) {
                    objD = vi3.a;
                }
                if (objD == t00Var) {
                    return t00Var;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a8.i0(obj);
        }
        return vi3.a;
    }
}
