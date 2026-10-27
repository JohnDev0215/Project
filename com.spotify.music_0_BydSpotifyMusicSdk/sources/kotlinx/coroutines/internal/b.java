package kotlinx.coroutines.internal;

import p.lq2;
import p.ry0;
import p.ux2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final ry0 a(final ry0 ry0Var, final Object obj, final y10 y10Var) {
        return new ry0() { // from class: kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj2) {
                ry0 ry0Var2 = ry0Var;
                Object obj3 = obj;
                y10 y10Var2 = y10Var;
                UndeliveredElementException undeliveredElementExceptionB = b.b(ry0Var2, obj3, null);
                if (undeliveredElementExceptionB != null) {
                    ux2.m(y10Var2, undeliveredElementExceptionB);
                }
                return lq2.a;
            }
        };
    }

    public static final UndeliveredElementException b(ry0 ry0Var, Object obj, UndeliveredElementException undeliveredElementException) {
        try {
            ry0Var.invoke(obj);
        } catch (Throwable th) {
            if (undeliveredElementException == null || undeliveredElementException.getCause() == th) {
                return new UndeliveredElementException("Exception in undelivered element handler for " + obj, th);
            }
            kotlin.a.a(undeliveredElementException, th);
        }
        return undeliveredElementException;
    }
}
