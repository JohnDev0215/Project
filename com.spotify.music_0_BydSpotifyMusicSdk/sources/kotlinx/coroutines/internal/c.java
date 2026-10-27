package kotlinx.coroutines.internal;

import p.fz0;
import p.jk2;
import p.mk2;
import p.tz2;
import p.u4;
import p.v03;
import p.w10;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static final tz2 a = new tz2("NO_THREAD_ELEMENTS", 3);
    public static final fz0 b = new fz0() { // from class: kotlinx.coroutines.internal.ThreadContextKt$countAll$1
        @Override // p.fz0
        public final Object g(Object obj, Object obj2) {
            w10 w10Var = (w10) obj2;
            if (!(w10Var instanceof jk2)) {
                return obj;
            }
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            int iIntValue = num != null ? num.intValue() : 1;
            return iIntValue == 0 ? w10Var : Integer.valueOf(iIntValue + 1);
        }
    };
    public static final fz0 c = new fz0() { // from class: kotlinx.coroutines.internal.ThreadContextKt$findOne$1
        @Override // p.fz0
        public final Object g(Object obj, Object obj2) {
            jk2 jk2Var = (jk2) obj;
            w10 w10Var = (w10) obj2;
            if (jk2Var != null) {
                return jk2Var;
            }
            if (w10Var instanceof jk2) {
                return (jk2) w10Var;
            }
            return null;
        }
    };
    public static final fz0 d = new fz0() { // from class: kotlinx.coroutines.internal.ThreadContextKt$updateState$1
        @Override // p.fz0
        public final Object g(Object obj, Object obj2) {
            return (mk2) obj;
        }
    };

    public static final void a(y10 y10Var, Object obj) {
        if (obj == a) {
            return;
        }
        if (!(obj instanceof mk2)) {
            Object objC = y10Var.c(null, c);
            v03.g(objC, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            u4.z(objC);
            throw null;
        }
        mk2 mk2Var = (mk2) obj;
        jk2[] jk2VarArr = mk2Var.b;
        int length = jk2VarArr.length - 1;
        if (length < 0) {
            return;
        }
        jk2 jk2Var = jk2VarArr[length];
        v03.h(null);
        Object obj2 = mk2Var.a[length];
        throw null;
    }

    public static final Object b(y10 y10Var) {
        Object objC = y10Var.c(0, b);
        v03.h(objC);
        return objC;
    }

    public static final Object c(y10 y10Var, Object obj) {
        if (obj == null) {
            obj = b(y10Var);
        }
        if (obj == 0) {
            return a;
        }
        if (obj instanceof Integer) {
            return y10Var.c(new mk2(y10Var, ((Number) obj).intValue()), d);
        }
        u4.z(obj);
        throw null;
    }
}
