package defpackage;

import android.util.SparseIntArray;
import java.util.ArrayList;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: BackPressManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class zb implements w80 {
    public static final SparseIntArray k;
    public final a e;
    public final vb[] f;
    public final Callback<Boolean>[] g;
    public final boolean[] h;
    public final Runnable i;
    public int j;

    /* JADX INFO: compiled from: BackPressManager.java */
    public class a extends xr1 {
        public a() {
            super(false);
        }

        @Override // defpackage.xr1
        public final void a() {
            Boolean bool;
            zb zbVar = zb.this;
            zbVar.getClass();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                vb[] vbVarArr = zbVar.f;
                if (i >= vbVarArr.length) {
                    zbVar.i.run();
                    if (arrayList.isEmpty()) {
                        return;
                    }
                    String.join(", ", arrayList);
                    return;
                }
                vb vbVar = vbVarArr[i];
                if (vbVar != null && (bool = vbVar.getHandleBackPressChangedSupplier().get()) != null && bool.booleanValue()) {
                    if (vbVar.o() != 1) {
                        zb.c(i);
                        if (arrayList.isEmpty()) {
                            return;
                        }
                        String.join(", ", arrayList);
                        return;
                    }
                    arrayList.add(i + "");
                    oj2.U(zb.k.get(i), 18, "Android.BackPress.Failure");
                }
                i++;
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(18);
        sparseIntArray.put(0, 0);
        sparseIntArray.put(1, 1);
        sparseIntArray.put(3, 3);
        sparseIntArray.put(4, 4);
        sparseIntArray.put(5, 5);
        sparseIntArray.put(6, 6);
        sparseIntArray.put(7, 7);
        sparseIntArray.put(8, 8);
        sparseIntArray.put(10, 9);
        sparseIntArray.put(11, 10);
        sparseIntArray.put(12, 11);
        sparseIntArray.put(14, 12);
        sparseIntArray.put(15, 13);
        sparseIntArray.put(16, 14);
        sparseIntArray.put(17, 15);
        sparseIntArray.put(13, 16);
        sparseIntArray.put(9, 17);
        sparseIntArray.put(2, 18);
        k = sparseIntArray;
    }

    public zb() {
        this.e = new a();
        this.f = new vb[18];
        this.g = new Callback[18];
        this.h = new boolean[18];
        this.i = new wz(6);
    }

    public static boolean b() {
        return bq.c.a();
    }

    public static void c(int i) {
        oj2.U(k.get(i), 18, "Android.BackPress.Intercept");
    }

    public final void a(vb vbVar, int i) {
        this.f[i] = vbVar;
        yb ybVar = new yb(i, 0, this);
        Callback<Boolean>[] callbackArr = this.g;
        callbackArr[i] = ybVar;
        vbVar.getHandleBackPressChangedSupplier().p(callbackArr[i]);
    }

    public final void d(int i) {
        vb[] vbVarArr = this.f;
        vb vbVar = vbVarArr[i];
        Boolean bool = vbVar.getHandleBackPressChangedSupplier().get();
        if (bool != null && bool.booleanValue()) {
            int i2 = this.j - 1;
            this.j = i2;
            this.h[i] = false;
            this.e.b(i2 != 0);
        }
        zp1<Boolean> handleBackPressChangedSupplier = vbVar.getHandleBackPressChangedSupplier();
        Callback<Boolean>[] callbackArr = this.g;
        handleBackPressChangedSupplier.d(callbackArr[i]);
        callbackArr[i] = null;
        vbVarArr[i] = null;
    }

    @Override // defpackage.w80
    public final void destroy() {
        int i = 0;
        while (true) {
            vb[] vbVarArr = this.f;
            if (i >= vbVarArr.length) {
                return;
            }
            if (vbVarArr[i] != null) {
                d(i);
            }
            i++;
        }
    }

    public zb(zo zoVar) {
        this.e = new a();
        this.f = new vb[18];
        this.g = new Callback[18];
        this.h = new boolean[18];
        this.i = zoVar;
    }
}
