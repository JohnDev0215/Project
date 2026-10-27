package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ai0 extends p00 {
    public long f;
    public boolean g;
    public o8 h;

    public final void j1() {
        long j = this.f - 4294967296L;
        this.f = j;
        if (j <= 0 && this.g) {
            shutdown();
        }
    }

    public final void k1(va0<?> va0Var) {
        o8 o8Var = this.h;
        if (o8Var == null) {
            o8Var = new o8();
            this.h = o8Var;
        }
        Object[] objArr = (Object[]) o8Var.c;
        int i = o8Var.b;
        objArr[i] = va0Var;
        int length = (i + 1) & (objArr.length - 1);
        o8Var.b = length;
        int i2 = o8Var.a;
        if (length == i2) {
            int length2 = objArr.length;
            Object[] objArr2 = new Object[length2 << 1];
            r8.r0(objArr, objArr2, 0, i2, 0, 10);
            Object[] objArr3 = (Object[]) o8Var.c;
            int length3 = objArr3.length;
            int i3 = o8Var.a;
            r8.r0(objArr3, objArr2, length3 - i3, 0, i3, 4);
            o8Var.c = objArr2;
            o8Var.a = 0;
            o8Var.b = length2;
        }
    }

    public final boolean l1() throws IllegalAccessException, InvocationTargetException {
        o8 o8Var = this.h;
        if (o8Var == null) {
            return false;
        }
        int i = o8Var.a;
        Object obj = null;
        if (i != o8Var.b) {
            Object[] objArr = (Object[]) o8Var.c;
            Object obj2 = objArr[i];
            objArr[i] = null;
            o8Var.a = (i + 1) & (objArr.length - 1);
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
            }
            obj = obj2;
        }
        va0 va0Var = (va0) obj;
        if (va0Var == null) {
            return false;
        }
        va0Var.run();
        return true;
    }

    public void shutdown() {
    }
}
