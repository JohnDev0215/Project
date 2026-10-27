package org.chromium.base;

import J.N;
import defpackage.qe1;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes.dex */
public class MemoryPressureListener {
    public static b<qe1> a;

    public static void a(int i) {
        Object obj = ThreadUtils.a;
        b<qe1> bVar = a;
        if (bVar == null) {
            return;
        }
        Iterator<qe1> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qe1) aVar.next()).a(i);
            }
        }
    }

    @CalledByNative
    public static void addNativeCallback() {
        Object obj = ThreadUtils.a;
        qe1 qe1Var = new qe1() { // from class: re1
            @Override // defpackage.qe1
            public final void a(int i) {
                N.MZJzyjAa(i);
            }
        };
        if (a == null) {
            a = new b<>();
        }
        a.a(qe1Var);
    }
}
