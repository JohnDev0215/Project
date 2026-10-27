package kotlinx.coroutines;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.c00;
import p.e20;
import p.f20;
import p.fz0;
import p.ha0;
import p.jq2;
import p.kq2;
import p.py0;
import p.qo;
import p.s70;
import p.vr2;
import p.w10;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.lang.Object] */
    public static final y10 a(y10 y10Var, y10 y10Var2, final boolean z) {
        Boolean bool = Boolean.FALSE;
        CoroutineContextKt$hasCopyableElements$1 coroutineContextKt$hasCopyableElements$1 = CoroutineContextKt$hasCopyableElements$1.c;
        boolean zBooleanValue = ((Boolean) y10Var.c(bool, coroutineContextKt$hasCopyableElements$1)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) y10Var2.c(bool, coroutineContextKt$hasCopyableElements$1)).booleanValue();
        if (!zBooleanValue && !zBooleanValue2) {
            return y10Var.o0(y10Var2);
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = y10Var2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.c;
        y10 y10Var3 = (y10) y10Var.c(emptyCoroutineContext, new fz0() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$folded$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                return ((y10) obj).o0((w10) obj2);
            }
        });
        if (zBooleanValue2) {
            ref$ObjectRef.element = ((y10) ref$ObjectRef.element).c(emptyCoroutineContext, new fz0() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$1
                @Override // p.fz0
                public final Object g(Object obj, Object obj2) {
                    return ((y10) obj).o0((w10) obj2);
                }
            });
        }
        return y10Var3.o0((y10) ref$ObjectRef.element);
    }

    public static final y10 b(y10 y10Var, y10 y10Var2) {
        return !((Boolean) y10Var2.c(Boolean.FALSE, CoroutineContextKt$hasCopyableElements$1.c)).booleanValue() ? y10Var.o0(y10Var2) : a(y10Var, y10Var2, false);
    }

    public static final y10 c(e20 e20Var, y10 y10Var) {
        y10 y10VarA = a(e20Var.j(), y10Var, true);
        s70 s70Var = ha0.a;
        return (y10VarA == s70Var || y10VarA.E0(qo.l) != null) ? y10VarA : y10VarA.o0(s70Var);
    }

    public static Object d(py0 py0Var, c00 c00Var) {
        return vr2.y(EmptyCoroutineContext.c, new InterruptibleKt$runInterruptible$2(py0Var, null), c00Var);
    }

    public static final jq2 e(c00 c00Var, y10 y10Var, Object obj) {
        jq2 jq2Var = null;
        if (!(c00Var instanceof f20)) {
            return null;
        }
        if (y10Var.E0(kq2.c) != null) {
            f20 f20VarF = (f20) c00Var;
            while (true) {
                if ((f20VarF instanceof d) || (f20VarF = f20VarF.f()) == null) {
                    break;
                }
                if (f20VarF instanceof jq2) {
                    jq2Var = (jq2) f20VarF;
                    break;
                }
            }
            if (jq2Var != null) {
                jq2Var.c0(y10Var, obj);
            }
        }
        return jq2Var;
    }
}
