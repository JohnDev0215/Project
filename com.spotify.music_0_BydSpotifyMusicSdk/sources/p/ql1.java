package p;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class ql1 {
    public final String a;
    public final c61 b;
    public final Executor c;
    public int d;
    public final ol1 e;
    public x21 f;
    public final pl1 g;
    public final AtomicBoolean h;
    public final nl1 i;
    public final nl1 j;

    /* JADX WARN: Type inference failed for: r0v0, types: [p.nl1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [p.nl1] */
    public ql1(Context context, String str, Intent intent, c61 c61Var, Executor executor) {
        this.a = str;
        this.b = c61Var;
        this.c = executor;
        Context applicationContext = context.getApplicationContext();
        this.g = new pl1(this);
        final int i = 0;
        this.h = new AtomicBoolean(false);
        zf1 zf1Var = new zf1(this);
        this.i = new Runnable(this) { // from class: p.nl1
            public final /* synthetic */ ql1 e;

            {
                this.e = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                b61 b61Var;
                boolean z;
                i52 i52Var;
                nh2 nh2Var;
                switch (i) {
                    case 0:
                        ql1 ql1Var = this.e;
                        v03.j(ql1Var, "this$0");
                        try {
                            x21 x21Var = ql1Var.f;
                            if (x21Var != null) {
                                ql1Var.d = x21Var.G(ql1Var.g, ql1Var.a);
                                c61 c61Var2 = ql1Var.b;
                                ol1 ol1Var = ql1Var.e;
                                if (ol1Var != null) {
                                    c61Var2.a(ol1Var);
                                    return;
                                } else {
                                    v03.Q("observer");
                                    throw null;
                                }
                            }
                            return;
                        } catch (RemoteException unused) {
                            return;
                        }
                    default:
                        ql1 ql1Var2 = this.e;
                        v03.j(ql1Var2, "this$0");
                        c61 c61Var3 = ql1Var2.b;
                        ol1 ol1Var2 = ql1Var2.e;
                        if (ol1Var2 == null) {
                            v03.Q("observer");
                            throw null;
                        }
                        c61Var3.getClass();
                        synchronized (c61Var3.j) {
                            b61Var = (b61) c61Var3.j.b(ol1Var2);
                        }
                        if (b61Var != null) {
                            a61 a61Var = c61Var3.i;
                            int[] iArr = b61Var.b;
                            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
                            a61Var.getClass();
                            v03.j(iArrCopyOf, "tableIds");
                            synchronized (a61Var) {
                                z = false;
                                for (int i2 : iArrCopyOf) {
                                    long[] jArr = a61Var.a;
                                    long j = jArr[i2];
                                    jArr[i2] = j - 1;
                                    if (j == 1) {
                                        a61Var.d = true;
                                        z = true;
                                    }
                                }
                            }
                            if (z && (nh2Var = (i52Var = c61Var3.a).a) != null && nh2Var.isOpen()) {
                                c61Var3.d(i52Var.h().u0());
                                return;
                            }
                            return;
                        }
                        return;
                }
            }
        };
        final int i2 = 1;
        this.j = new Runnable(this) { // from class: p.nl1
            public final /* synthetic */ ql1 e;

            {
                this.e = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                b61 b61Var;
                boolean z;
                i52 i52Var;
                nh2 nh2Var;
                switch (i2) {
                    case 0:
                        ql1 ql1Var = this.e;
                        v03.j(ql1Var, "this$0");
                        try {
                            x21 x21Var = ql1Var.f;
                            if (x21Var != null) {
                                ql1Var.d = x21Var.G(ql1Var.g, ql1Var.a);
                                c61 c61Var2 = ql1Var.b;
                                ol1 ol1Var = ql1Var.e;
                                if (ol1Var != null) {
                                    c61Var2.a(ol1Var);
                                    return;
                                } else {
                                    v03.Q("observer");
                                    throw null;
                                }
                            }
                            return;
                        } catch (RemoteException unused) {
                            return;
                        }
                    default:
                        ql1 ql1Var2 = this.e;
                        v03.j(ql1Var2, "this$0");
                        c61 c61Var3 = ql1Var2.b;
                        ol1 ol1Var2 = ql1Var2.e;
                        if (ol1Var2 == null) {
                            v03.Q("observer");
                            throw null;
                        }
                        c61Var3.getClass();
                        synchronized (c61Var3.j) {
                            b61Var = (b61) c61Var3.j.b(ol1Var2);
                        }
                        if (b61Var != null) {
                            a61 a61Var = c61Var3.i;
                            int[] iArr = b61Var.b;
                            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
                            a61Var.getClass();
                            v03.j(iArrCopyOf, "tableIds");
                            synchronized (a61Var) {
                                z = false;
                                for (int i22 : iArrCopyOf) {
                                    long[] jArr = a61Var.a;
                                    long j = jArr[i22];
                                    jArr[i22] = j - 1;
                                    if (j == 1) {
                                        a61Var.d = true;
                                        z = true;
                                    }
                                }
                            }
                            if (z && (nh2Var = (i52Var = c61Var3.a).a) != null && nh2Var.isOpen()) {
                                c61Var3.d(i52Var.h().u0());
                                return;
                            }
                            return;
                        }
                        return;
                }
            }
        };
        Object[] array = c61Var.d.keySet().toArray(new String[0]);
        v03.g(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        this.e = new ol1(this, (String[]) array);
        applicationContext.bindService(intent, zf1Var, 1);
    }
}
