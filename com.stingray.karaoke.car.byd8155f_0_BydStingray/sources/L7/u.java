package L7;

import K7.A0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class u implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f4064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4065b;
    public final P c;

    /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public u(L7.e r9, Q7.InterfaceC0666t r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L7.u.<init>(L7.e, Q7.t, boolean):void");
    }

    @Override // L7.e
    public final Type p() {
        return this.f4064a.p();
    }

    @Override // L7.e
    public final Object q(Object[] objArr) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke;
        P p10 = this.c;
        G7.d dVar = (G7.d) p10.f18537m;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        A7.m.e(objArrCopyOf, "copyOf(this, size)");
        int i7 = dVar.l;
        int i10 = dVar.f2712m;
        if (i7 <= i10) {
            while (true) {
                Method method = ((Method[]) p10.f18538n)[i7];
                Object objE = objArr[i7];
                if (method != null) {
                    if (objE != null) {
                        objE = method.invoke(objE, null);
                    } else {
                        Class<?> returnType = method.getReturnType();
                        A7.m.e(returnType, "method.returnType");
                        objE = A0.e(returnType);
                    }
                }
                objArrCopyOf[i7] = objE;
                if (i7 == i10) {
                    break;
                }
                i7++;
            }
        }
        Object objQ = this.f4064a.q(objArrCopyOf);
        Method method2 = (Method) p10.o;
        return (method2 == null || (objInvoke = method2.invoke(null, objQ)) == null) ? objQ : objInvoke;
    }

    @Override // L7.e
    public final List r() {
        return this.f4064a.r();
    }

    @Override // L7.e
    public final Member s() {
        return this.f4064a.s();
    }
}
