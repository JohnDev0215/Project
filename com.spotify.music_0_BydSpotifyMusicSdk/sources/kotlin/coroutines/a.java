package kotlin.coroutines;

import p.f00;
import p.fz0;
import p.qo;
import p.v03;
import p.w10;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static y10 a(y10 y10Var, y10 y10Var2) {
        v03.j(y10Var2, "context");
        return y10Var2 == EmptyCoroutineContext.c ? y10Var : (y10) y10Var2.c(y10Var, new fz0() { // from class: kotlin.coroutines.CoroutineContext$plus$1
            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                CombinedContext combinedContext;
                y10 y10Var3 = (y10) obj;
                w10 w10Var = (w10) obj2;
                v03.j(y10Var3, "acc");
                v03.j(w10Var, "element");
                y10 y10VarE = y10Var3.E(w10Var.getKey());
                EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.c;
                if (y10VarE == emptyCoroutineContext) {
                    return w10Var;
                }
                qo qoVar = qo.l;
                f00 f00Var = (f00) y10VarE.E0(qoVar);
                if (f00Var == null) {
                    combinedContext = new CombinedContext(w10Var, y10VarE);
                } else {
                    y10 y10VarE2 = y10VarE.E(qoVar);
                    if (y10VarE2 == emptyCoroutineContext) {
                        return new CombinedContext(f00Var, w10Var);
                    }
                    combinedContext = new CombinedContext(f00Var, new CombinedContext(w10Var, y10VarE2));
                }
                return combinedContext;
            }
        });
    }
}
