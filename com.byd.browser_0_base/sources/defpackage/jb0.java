package defpackage;

import androidx.slidingpanelayout.widget.b;

/* JADX INFO: compiled from: Distinct.kt */
/* JADX INFO: loaded from: classes.dex */
public final class jb0<T> implements Flow1<T> {
    public final Flow1<T> a;
    public final jr0<T, Object> b;
    public final nr0<Object, Object, Boolean> c;

    /* JADX INFO: compiled from: Distinct.kt */
    public static final class a<T> implements xn0 {
        public final /* synthetic */ xn0<T> a;

        public a(xn0 xn0Var) {
            this.a = xn0Var;
        }
    }

    public jb0(b.C0029b c0029b) {
        zn0 zn0Var = zn0.f;
        yn0 yn0Var = yn0.f;
        this.a = c0029b;
        this.b = zn0Var;
        this.c = yn0Var;
    }

    @Override // defpackage.Flow1
    public final Object a(xn0<? super T> xn0Var, b00<? super vi3> b00Var) {
        Object objA = this.a.a(new a(xn0Var), b00Var);
        return objA == t00.COROUTINE_SUSPENDED ? objA : vi3.a;
    }
}
