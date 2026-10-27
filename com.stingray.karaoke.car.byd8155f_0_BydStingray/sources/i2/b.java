package i2;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b extends d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f15670n;
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f15671p;

    public b(long j3, int i7) {
        super(i7);
        this.f15670n = j3;
        this.o = new ArrayList();
        this.f15671p = new ArrayList();
    }

    public final b f(int i7) {
        ArrayList arrayList = this.f15671p;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            b bVar = (b) arrayList.get(i10);
            if (bVar.f15673m == i7) {
                return bVar;
            }
        }
        return null;
    }

    public final c i(int i7) {
        ArrayList arrayList = this.o;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            c cVar = (c) arrayList.get(i10);
            if (cVar.f15673m == i7) {
                return cVar;
            }
        }
        return null;
    }

    @Override // i2.d
    public final String toString() {
        return d.c(this.f15673m) + " leaves: " + Arrays.toString(this.o.toArray()) + " containers: " + Arrays.toString(this.f15671p.toArray());
    }
}
