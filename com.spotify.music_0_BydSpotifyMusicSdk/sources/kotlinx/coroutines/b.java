package kotlinx.coroutines;

import kotlin.coroutines.EmptyCoroutineContext;
import p.b1;
import p.ba1;
import p.c1;
import p.f00;
import p.f60;
import p.qo;
import p.ry0;
import p.v03;
import p.w10;
import p.x10;
import p.y10;
import p.z10;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends b1 implements f00 {
    public static final z10 e = new z10(qo.l, new ry0() { // from class: kotlinx.coroutines.CoroutineDispatcher$Key$1
        @Override // p.ry0
        public final Object invoke(Object obj) {
            w10 w10Var = (w10) obj;
            if (w10Var instanceof b) {
                return (b) w10Var;
            }
            return null;
        }
    });

    public b() {
        super(qo.l);
    }

    @Override // p.b1, p.y10
    public final y10 E(x10 x10Var) {
        v03.j(x10Var, "key");
        if (x10Var instanceof c1) {
            c1 c1Var = (c1) x10Var;
            x10 x10Var2 = this.c;
            v03.j(x10Var2, "key");
            if ((x10Var2 == c1Var || c1Var.e == x10Var2) && ((w10) c1Var.c.invoke(this)) != null) {
                return EmptyCoroutineContext.c;
            }
        } else if (qo.l == x10Var) {
            return EmptyCoroutineContext.c;
        }
        return this;
    }

    @Override // p.b1, p.y10
    public final w10 E0(x10 x10Var) {
        v03.j(x10Var, "key");
        if (!(x10Var instanceof c1)) {
            if (qo.l == x10Var) {
                return this;
            }
            return null;
        }
        c1 c1Var = (c1) x10Var;
        x10 x10Var2 = this.c;
        v03.j(x10Var2, "key");
        if (x10Var2 != c1Var && c1Var.e != x10Var2) {
            return null;
        }
        w10 w10Var = (w10) c1Var.c.invoke(this);
        if (w10Var instanceof w10) {
            return w10Var;
        }
        return null;
    }

    public abstract void G0(y10 y10Var, Runnable runnable);

    public void H0(y10 y10Var, Runnable runnable) {
        G0(y10Var, runnable);
    }

    public boolean I0() {
        return !(this instanceof h);
    }

    public b J0(int i) {
        zu.p(i);
        return new ba1(this, i);
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + f60.s(this);
    }
}
