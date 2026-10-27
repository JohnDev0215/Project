package spi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i4 extends nb<AtomicLongArray> {
    public final /* synthetic */ nb a;

    public i4(nb nbVar) {
        this.a = nbVar;
    }

    @Override // spi.nb
    public final AtomicLongArray a(o5 o5Var) throws IOException {
        ArrayList arrayList = new ArrayList();
        o5Var.a();
        while (o5Var.i()) {
            arrayList.add(Long.valueOf(((Number) this.a.a(o5Var)).longValue()));
        }
        o5Var.e();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    @Override // spi.nb
    public final void b(v5 v5Var, AtomicLongArray atomicLongArray) throws IOException {
        AtomicLongArray atomicLongArray2 = atomicLongArray;
        v5Var.b();
        int length = atomicLongArray2.length();
        for (int i = 0; i < length; i++) {
            this.a.b(v5Var, Long.valueOf(atomicLongArray2.get(i)));
        }
        v5Var.e();
    }
}
