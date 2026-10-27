package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import p.c00;
import p.e60;
import p.f20;
import p.he1;
import p.v03;
import p.v4;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseContinuationImpl implements c00<Object>, f20, Serializable {
    private final c00<Object> completion;

    public BaseContinuationImpl(c00 c00Var) {
        this.completion = c00Var;
    }

    public c00 a(Object obj, c00 c00Var) {
        v03.j(c00Var, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public c00 c(c00 c00Var) {
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public final c00 d() {
        return this.completion;
    }

    @Override // p.f20
    public f20 f() {
        c00<Object> c00Var = this.completion;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    public StackTraceElement h() {
        int iIntValue;
        String strC;
        e60 e60Var = (e60) getClass().getAnnotation(e60.class);
        String str = null;
        if (e60Var == null) {
            return null;
        }
        int iV = e60Var.v();
        if (iV > 1) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i = iIntValue >= 0 ? e60Var.l()[iIntValue] : -1;
        v4 v4Var = he1.d;
        v4 v4Var2 = he1.c;
        if (v4Var == null) {
            try {
                v4 v4Var3 = new v4(Class.class.getDeclaredMethod("getModule", new Class[0]), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]), 24);
                he1.d = v4Var3;
                v4Var = v4Var3;
            } catch (Exception unused2) {
                he1.d = v4Var2;
                v4Var = v4Var2;
            }
        }
        if (v4Var != v4Var2) {
            Method method = (Method) v4Var.e;
            Object objInvoke = method != null ? method.invoke(getClass(), new Object[0]) : null;
            if (objInvoke != null) {
                Method method2 = (Method) v4Var.k;
                Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, new Object[0]) : null;
                if (objInvoke2 != null) {
                    Method method3 = (Method) v4Var.l;
                    Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
                    if (objInvoke3 instanceof String) {
                        str = (String) objInvoke3;
                    }
                }
            }
        }
        if (str == null) {
            strC = e60Var.c();
        } else {
            strC = str + '/' + e60Var.c();
        }
        return new StackTraceElement(strC, e60Var.m(), e60Var.f(), i);
    }

    public abstract Object i(Object obj);

    public void j() {
    }

    @Override // p.c00
    public final void k(Object obj) {
        c00<Object> c00Var = this;
        while (true) {
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) c00Var;
            c00<Object> c00Var2 = baseContinuationImpl.completion;
            v03.h(c00Var2);
            try {
                obj = baseContinuationImpl.i(obj);
                if (obj == CoroutineSingletons.c) {
                    return;
                }
            } catch (Throwable th) {
                obj = a.b(th);
            }
            baseContinuationImpl.j();
            if (!(c00Var2 instanceof BaseContinuationImpl)) {
                c00Var2.k(obj);
                return;
            }
            c00Var = c00Var2;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object objH = h();
        if (objH == null) {
            objH = getClass().getName();
        }
        sb.append(objH);
        return sb.toString();
    }
}
