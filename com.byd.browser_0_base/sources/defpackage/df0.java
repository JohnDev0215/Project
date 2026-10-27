package defpackage;

import J.N;
import android.util.SparseArray;
import org.chromium.base.Callback;
import org.chromium.ui.resources.ResourceManager;
import org.chromium.ui.resources.a;

/* JADX INFO: compiled from: DynamicResourceLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class df0 extends org.chromium.ui.resources.a {
    public final SparseArray<a> c;

    /* JADX INFO: compiled from: DynamicResourceLoader.java */
    public static class a {
        public final cf0 a;
        public final Callback<pb2> b;

        public a(cf0 cf0Var, tb1 tb1Var) {
            this.a = cf0Var;
            this.b = tb1Var;
            cf0Var.a(tb1Var);
        }
    }

    public df0(int i, a.InterfaceC0158a interfaceC0158a) {
        super(i, interfaceC0158a);
        this.c = new SparseArray<>();
    }

    @Override // org.chromium.ui.resources.a
    public final void a(int i) {
        a aVar = this.c.get(i);
        if (aVar == null) {
            return;
        }
        aVar.a.b();
    }

    public final void d(int i, cf0 cf0Var) {
        this.c.put(i, new a(cf0Var, new tb1(i, 2, this)));
    }

    public final void e(int i) {
        SparseArray<a> sparseArray = this.c;
        a aVar = sparseArray.get(i);
        if (aVar == null) {
            return;
        }
        sparseArray.remove(i);
        aVar.a.c(aVar.b);
        a.InterfaceC0158a interfaceC0158a = this.b;
        if (interfaceC0158a != null) {
            ResourceManager resourceManager = (ResourceManager) interfaceC0158a;
            int i2 = this.a;
            if (i2 == 2 || i2 == 1) {
                long j = resourceManager.d;
                if (j == 0) {
                    return;
                }
                N.MxwZmAzJ(j, resourceManager, i2, i);
            }
        }
    }

    @Override // org.chromium.ui.resources.a
    public final void c(int i) {
    }
}
