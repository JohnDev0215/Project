package k3;

import android.os.Looper;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: renamed from: k3.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1652k0 implements InterfaceC1656m0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18011m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d5.d0 f18012n;

    public /* synthetic */ C1652k0(int i7, d5.d0 d0Var, int i10) {
        this.l = i10;
        this.f18011m = i7;
        this.f18012n = d0Var;
    }

    @Override // k3.InterfaceC1656m0
    public final void e(C1628b0 c1628b0) {
        switch (this.l) {
            case 0:
                if (c1628b0.v()) {
                    d5.d0 d0Var = c1628b0.f17917r;
                    d5.d0 d0Var2 = this.f18012n;
                    c1628b0.f17916q = d5.I.w(d0Var2);
                    d5.d0 d0VarR1 = C1628b0.r1(d0Var2, c1628b0.f17915p, c1628b0.f17918s, c1628b0.f17921v);
                    c1628b0.f17917r = d0VarR1;
                    boolean zEquals = Objects.equals(d0VarR1, d0Var);
                    C1672v c1672v = c1628b0.f17904a;
                    c1672v.getClass();
                    AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
                    InterfaceC1668t interfaceC1668t = c1672v.d;
                    interfaceC1668t.getClass();
                    h5.v vVarV = InterfaceC1668t.v();
                    if (!zEquals) {
                        interfaceC1668t.n();
                    }
                    c1628b0.u1(this.f18011m, vVarV);
                    break;
                }
                break;
            default:
                if (c1628b0.v()) {
                    d5.d0 d0Var3 = c1628b0.f17917r;
                    d5.d0 d0Var4 = this.f18012n;
                    c1628b0.f17915p = d5.I.w(d0Var4);
                    d5.d0 d0VarR12 = C1628b0.r1(c1628b0.f17916q, d0Var4, c1628b0.f17918s, c1628b0.f17921v);
                    c1628b0.f17917r = d0VarR12;
                    boolean zEquals2 = Objects.equals(d0VarR12, d0Var3);
                    C1672v c1672v2 = c1628b0.f17904a;
                    c1672v2.getClass();
                    AbstractC1346c.j(Looper.myLooper() == c1672v2.f18137e.getLooper());
                    InterfaceC1668t interfaceC1668t2 = c1672v2.d;
                    interfaceC1668t2.getClass();
                    h5.v vVarV2 = InterfaceC1668t.v();
                    if (!zEquals2) {
                        interfaceC1668t2.n();
                    }
                    c1628b0.u1(this.f18011m, vVarV2);
                    break;
                }
                break;
        }
    }
}
