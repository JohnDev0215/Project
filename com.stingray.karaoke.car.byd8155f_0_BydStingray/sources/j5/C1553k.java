package j5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: j5.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1553k extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y f17019b;

    public /* synthetic */ C1553k(y yVar, int i7) {
        this.f17018a = i7;
        this.f17019b = yVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        switch (this.f17018a) {
            case 0:
                return new AtomicLong(((Number) this.f17019b.a(c2297a)).longValue());
            case 1:
                ArrayList arrayList = new ArrayList();
                c2297a.b();
                while (c2297a.v()) {
                    arrayList.add(Long.valueOf(((Number) this.f17019b.a(c2297a)).longValue()));
                }
                c2297a.k();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i7 = 0; i7 < size; i7++) {
                    atomicLongArray.set(i7, ((Long) arrayList.get(i7)).longValue());
                }
                return atomicLongArray;
            default:
                if (c2297a.Q() != 9) {
                    return this.f17019b.a(c2297a);
                }
                c2297a.M();
                return null;
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        switch (this.f17018a) {
            case 0:
                this.f17019b.b(c2298b, Long.valueOf(((AtomicLong) obj).get()));
                break;
            case 1:
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                c2298b.g();
                int length = atomicLongArray.length();
                for (int i7 = 0; i7 < length; i7++) {
                    this.f17019b.b(c2298b, Long.valueOf(atomicLongArray.get(i7)));
                }
                c2298b.k();
                break;
            default:
                if (obj == null) {
                    c2298b.v();
                } else {
                    this.f17019b.b(c2298b, obj);
                }
                break;
        }
    }
}
