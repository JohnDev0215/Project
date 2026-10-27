package m5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1844v extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        ArrayList arrayList = new ArrayList();
        c2297a.b();
        while (c2297a.v()) {
            try {
                arrayList.add(Integer.valueOf(c2297a.G()));
            } catch (NumberFormatException e10) {
                throw new j5.p(e10);
            }
        }
        c2297a.k();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i7 = 0; i7 < size; i7++) {
            atomicIntegerArray.set(i7, ((Integer) arrayList.get(i7)).intValue());
        }
        return atomicIntegerArray;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.g();
        int length = ((AtomicIntegerArray) obj).length();
        for (int i7 = 0; i7 < length; i7++) {
            c2298b.I(r5.get(i7));
        }
        c2298b.k();
    }
}
