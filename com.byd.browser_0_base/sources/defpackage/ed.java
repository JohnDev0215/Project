package defpackage;

import defpackage.ji1;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ed implements b00<Object>, u00, Serializable {
    public final b00<Object> e;

    public ed(b00<Object> b00Var) {
        this.e = b00Var;
    }

    @Override // defpackage.u00
    public final u00 a() {
        b00<Object> b00Var = this.e;
        if (b00Var instanceof u00) {
            return (u00) b00Var;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // defpackage.b00
    public final void c(Object obj) {
        ?? r2 = this;
        while (true) {
            ed edVar = (ed) r2;
            b00<Object> b00Var = edVar.e;
            i21.b(b00Var);
            try {
                obj = edVar.h(obj);
                if (obj == t00.COROUTINE_SUSPENDED) {
                    return;
                }
            } catch (Throwable th) {
                obj = a8.v(th);
            }
            edVar.k();
            if (!(b00Var instanceof ed)) {
                b00Var.c(obj);
                return;
            }
            r2 = b00Var;
        }
    }

    public b00<vi3> e(Object obj, b00<?> b00Var) {
        i21.e(b00Var, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public final StackTraceElement f() {
        int iIntValue;
        String strC;
        c70 c70Var = (c70) getClass().getAnnotation(c70.class);
        String str = null;
        if (c70Var == null) {
            return null;
        }
        int iV = c70Var.v();
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
        int i = iIntValue >= 0 ? c70Var.l()[iIntValue] : -1;
        ji1.a aVar = ji1.b;
        ji1.a aVar2 = ji1.a;
        if (aVar == null) {
            try {
                ji1.a aVar3 = new ji1.a(Class.class.getDeclaredMethod("getModule", new Class[0]), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
                ji1.b = aVar3;
                aVar = aVar3;
            } catch (Exception unused2) {
                ji1.b = aVar2;
                aVar = aVar2;
            }
        }
        if (aVar != aVar2) {
            Method method = aVar.a;
            Object objInvoke = method != null ? method.invoke(getClass(), new Object[0]) : null;
            if (objInvoke != null) {
                Method method2 = aVar.b;
                Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, new Object[0]) : null;
                if (objInvoke2 != null) {
                    Method method3 = aVar.c;
                    Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
                    if (objInvoke3 instanceof String) {
                        str = (String) objInvoke3;
                    }
                }
            }
        }
        if (str == null) {
            strC = c70Var.c();
        } else {
            strC = str + '/' + c70Var.c();
        }
        return new StackTraceElement(strC, c70Var.m(), c70Var.f(), i);
    }

    public abstract Object h(Object obj);

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object objF = f();
        if (objF == null) {
            objF = getClass().getName();
        }
        sb.append(objF);
        return sb.toString();
    }

    public void k() {
    }
}
