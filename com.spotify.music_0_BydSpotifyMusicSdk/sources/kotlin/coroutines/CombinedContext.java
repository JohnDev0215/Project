package kotlin.coroutines;

import java.io.Serializable;
import kotlin.jvm.internal.Ref$IntRef;
import p.fz0;
import p.hf2;
import p.lq2;
import p.v03;
import p.w10;
import p.x10;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class CombinedContext implements y10, Serializable {
    private final w10 element;
    private final y10 left;

    public static final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final y10[] elements;

        public Serialized(y10[] y10VarArr) {
            this.elements = y10VarArr;
        }

        private final Object readResolve() {
            y10[] y10VarArr = this.elements;
            y10 y10VarO0 = EmptyCoroutineContext.c;
            for (y10 y10Var : y10VarArr) {
                y10VarO0 = y10VarO0.o0(y10Var);
            }
            return y10VarO0;
        }
    }

    public CombinedContext(w10 w10Var, y10 y10Var) {
        v03.j(y10Var, "left");
        v03.j(w10Var, "element");
        this.left = y10Var;
        this.element = w10Var;
    }

    private final Object writeReplace() {
        int iA = a();
        final y10[] y10VarArr = new y10[iA];
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        c(lq2.a, new fz0() { // from class: kotlin.coroutines.CombinedContext.writeReplace.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                w10 w10Var = (w10) obj2;
                v03.j((lq2) obj, "<anonymous parameter 0>");
                v03.j(w10Var, "element");
                y10[] y10VarArr2 = y10VarArr;
                Ref$IntRef ref$IntRef2 = ref$IntRef;
                int i = ref$IntRef2.element;
                ref$IntRef2.element = i + 1;
                y10VarArr2[i] = w10Var;
                return lq2.a;
            }
        });
        if (ref$IntRef.element == iA) {
            return new Serialized(y10VarArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // p.y10
    public final y10 E(x10 x10Var) {
        v03.j(x10Var, "key");
        if (this.element.E0(x10Var) != null) {
            return this.left;
        }
        y10 y10VarE = this.left.E(x10Var);
        return y10VarE == this.left ? this : y10VarE == EmptyCoroutineContext.c ? this.element : new CombinedContext(this.element, y10VarE);
    }

    @Override // p.y10
    public final w10 E0(x10 x10Var) {
        v03.j(x10Var, "key");
        CombinedContext combinedContext = this;
        while (true) {
            w10 w10VarE0 = combinedContext.element.E0(x10Var);
            if (w10VarE0 != null) {
                return w10VarE0;
            }
            y10 y10Var = combinedContext.left;
            if (!(y10Var instanceof CombinedContext)) {
                return y10Var.E0(x10Var);
            }
            combinedContext = (CombinedContext) y10Var;
        }
    }

    public final int a() {
        int i = 2;
        CombinedContext combinedContext = this;
        while (true) {
            y10 y10Var = combinedContext.left;
            combinedContext = y10Var instanceof CombinedContext ? (CombinedContext) y10Var : null;
            if (combinedContext == null) {
                return i;
            }
            i++;
        }
    }

    @Override // p.y10
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return fz0Var.g(this.left.c(obj, fz0Var), this.element);
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) obj;
                if (combinedContext.a() == a()) {
                    CombinedContext combinedContext2 = this;
                    while (true) {
                        w10 w10Var = combinedContext2.element;
                        if (!v03.b(combinedContext.E0(w10Var.getKey()), w10Var)) {
                            break;
                        }
                        y10 y10Var = combinedContext2.left;
                        if (y10Var instanceof CombinedContext) {
                            combinedContext2 = (CombinedContext) y10Var;
                        } else {
                            v03.g(y10Var, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                            w10 w10Var2 = (w10) y10Var;
                            if (v03.b(combinedContext.E0(w10Var2.getKey()), w10Var2)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.element.hashCode() + this.left.hashCode();
    }

    @Override // p.y10
    public final y10 o0(y10 y10Var) {
        return a.a(this, y10Var);
    }

    public final String toString() {
        return hf2.d(new StringBuilder("["), (String) c("", new fz0() { // from class: kotlin.coroutines.CombinedContext.toString.1
            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                String str = (String) obj;
                w10 w10Var = (w10) obj2;
                v03.j(str, "acc");
                v03.j(w10Var, "element");
                if (str.length() == 0) {
                    return w10Var.toString();
                }
                return str + ", " + w10Var;
            }
        }), ']');
    }
}
