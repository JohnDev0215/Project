package p;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class jv implements nf2 {
    public final /* synthetic */ int a;
    public int b;
    public final Object c;
    public final Object d;

    public jv(wg1 wg1Var, no1 no1Var) {
        this.a = 4;
        this.b = -1;
        this.c = wg1Var;
        this.d = no1Var;
    }

    @Override // p.nf2
    public final StackTraceElement[] a(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr.length <= this.b) {
            return stackTraceElementArr;
        }
        StackTraceElement[] stackTraceElementArrA = stackTraceElementArr;
        for (nf2 nf2Var : (nf2[]) this.c) {
            if (stackTraceElementArrA.length <= this.b) {
                break;
            }
            stackTraceElementArrA = nf2Var.a(stackTraceElementArr);
        }
        return stackTraceElementArrA.length > this.b ? ((hk1) this.d).a(stackTraceElementArrA) : stackTraceElementArrA;
    }

    public final boolean equals(Object obj) {
        switch (this.a) {
            case 1:
                if (obj == this) {
                    return true;
                }
                if (vs.e((Class) this.c, obj) && Array.getLength(obj) == this.b) {
                    for (int i = 0; i < this.b; i++) {
                        Object obj2 = Array.get(this.d, i);
                        Object obj3 = Array.get(obj, i);
                        if (obj2 == obj3 || obj2 == null || obj2.equals(obj3)) {
                        }
                    }
                    return true;
                }
                return false;
            default:
                return super.equals(obj);
        }
    }

    public jv(nf2[] nf2VarArr) {
        this.a = 3;
        this.b = 1024;
        this.c = nf2VarArr;
        this.d = new hk1(1024, 0);
    }
}
