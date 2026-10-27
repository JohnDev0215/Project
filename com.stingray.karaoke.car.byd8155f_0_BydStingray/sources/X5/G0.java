package X5;

import V8.AbstractC0828q;
import com.stingray.shared.logs.segment.ActionOwner;

/* JADX INFO: loaded from: classes.dex */
public final class G0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V8.f0 f10171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V8.N f10172b;
    public S8.k0 c;
    public A3.g d;

    public G0() {
        V8.f0 f0VarC = AbstractC0828q.c(null);
        this.f10171a = f0VarC;
        this.f10172b = new V8.N(f0VarC);
    }

    public static final void a(G0 g02, long j3, ActionOwner actionOwner) {
        V8.f0 f0Var;
        Object value;
        ActionOwner actionOwner2;
        g02.getClass();
        int iFloor = (int) Math.floor(j3 / ((double) 1000));
        String strG = iFloor <= 0 ? "GO" : A7.k.g(" ", iFloor, " ");
        long j10 = j3 % ((long) 1000);
        float f10 = (50 > j10 || j10 >= 1000) ? 2.0f : 1.0f;
        float f11 = (50 > j10 || j10 >= 1000) ? 0.0f : 1.0f;
        do {
            f0Var = g02.f10171a;
            value = f0Var.getValue();
            C0857f c0857f = (C0857f) value;
            if (actionOwner == null) {
                ActionOwner actionOwner3 = c0857f != null ? c0857f.f10238e : null;
                if (actionOwner3 == null) {
                    actionOwner3 = ActionOwner.HOST;
                }
                actionOwner2 = actionOwner3;
            } else {
                actionOwner2 = actionOwner;
            }
        } while (!f0Var.k(value, new C0857f(j3, strG, f10, f11, actionOwner2)));
    }

    public final void b() {
        A3.g gVar = this.d;
        if (gVar != null) {
            gVar.a();
        }
        S8.k0 k0Var = this.c;
        if (k0Var != null) {
            k0Var.g(null);
        }
        this.c = null;
        this.f10171a.l(null);
        this.d = null;
    }
}
