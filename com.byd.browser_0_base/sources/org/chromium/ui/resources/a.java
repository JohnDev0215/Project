package org.chromium.ui.resources;

import J.N;
import android.graphics.Bitmap;
import android.util.SparseArray;
import defpackage.k61;
import defpackage.pb2;

/* JADX INFO: compiled from: ResourceLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    public final int a;
    public final InterfaceC0158a b;

    /* JADX INFO: renamed from: org.chromium.ui.resources.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ResourceLoader.java */
    public interface InterfaceC0158a {
    }

    public a(int i, InterfaceC0158a interfaceC0158a) {
        this.a = i;
        this.b = interfaceC0158a;
    }

    public abstract void a(int i);

    public final void b(int i, pb2 pb2Var) {
        InterfaceC0158a interfaceC0158a = this.b;
        if (interfaceC0158a != null) {
            int i2 = this.a;
            ResourceManager resourceManager = (ResourceManager) interfaceC0158a;
            resourceManager.getClass();
            if (pb2Var == null) {
                return;
            }
            Bitmap bitmapB = pb2Var.b();
            if (bitmapB == null) {
                if (pb2Var.a()) {
                    long j = resourceManager.d;
                    if (j != 0) {
                        N.MxwZmAzJ(j, resourceManager, i2, i);
                        return;
                    }
                    return;
                }
                return;
            }
            SparseArray<SparseArray<k61>> sparseArray = resourceManager.b;
            SparseArray<k61> sparseArray2 = sparseArray.get(i2);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray<>();
                sparseArray.put(i2, sparseArray2);
            }
            sparseArray2.put(i, new k61(resourceManager.c, pb2Var));
            long j2 = resourceManager.d;
            if (j2 == 0) {
                return;
            }
            N.MM7E4tBk(j2, resourceManager, i2, i, bitmapB, pb2Var.d().width(), pb2Var.d().height(), pb2Var.c());
        }
    }

    public abstract void c(int i);
}
