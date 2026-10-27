package s7;

import A7.m;
import h8.AbstractC1386c;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import q7.InterfaceC2189c;
import r7.EnumC2303a;

/* JADX INFO: renamed from: s7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2408a implements InterfaceC2189c, InterfaceC2411d, Serializable {
    private final InterfaceC2189c completion;

    public AbstractC2408a(InterfaceC2189c interfaceC2189c) {
        this.completion = interfaceC2189c;
    }

    public InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        m.f(interfaceC2189c, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.completion;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    public final InterfaceC2189c getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        InterfaceC2412e interfaceC2412e = (InterfaceC2412e) getClass().getAnnotation(InterfaceC2412e.class);
        String str = null;
        if (interfaceC2412e == null) {
            return null;
        }
        int iV = interfaceC2412e.v();
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
        int i7 = iIntValue >= 0 ? interfaceC2412e.l()[iIntValue] : -1;
        m9.i iVar = f.f22373b;
        m9.i iVar2 = f.f22372a;
        if (iVar == null) {
            try {
                m9.i iVar3 = new m9.i(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                f.f22373b = iVar3;
                iVar = iVar3;
            } catch (Exception unused2) {
                f.f22373b = iVar2;
                iVar = iVar2;
            }
        }
        if (iVar != iVar2 && (method = iVar.f19347a) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = iVar.f19348b) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
            Method method3 = iVar.c;
            Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
            if (objInvoke3 instanceof String) {
                str = (String) objInvoke3;
            }
        }
        if (str == null) {
            strC = interfaceC2412e.c();
        } else {
            strC = str + '/' + interfaceC2412e.c();
        }
        return new StackTraceElement(strC, interfaceC2412e.m(), interfaceC2412e.f(), i7);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        ?? r22 = this;
        while (true) {
            AbstractC2408a abstractC2408a = (AbstractC2408a) r22;
            InterfaceC2189c interfaceC2189c = abstractC2408a.completion;
            m.c(interfaceC2189c);
            try {
                obj = abstractC2408a.invokeSuspend(obj);
                if (obj == EnumC2303a.l) {
                    return;
                }
            } catch (Throwable th) {
                obj = AbstractC1386c.f(th);
            }
            abstractC2408a.releaseIntercepted();
            if (!(interfaceC2189c instanceof AbstractC2408a)) {
                interfaceC2189c.resumeWith(obj);
                return;
            }
            r22 = interfaceC2189c;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
