package defpackage;

import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: CachingUmaRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class fl implements yh3 {
    public final ReentrantReadWriteLock a = new ReentrantReadWriteLock(false);
    public HashMap b = new HashMap();
    public final AtomicInteger c = new AtomicInteger();
    public ArrayList d = new ArrayList();
    public int e;
    public yh3 f;

    /* JADX INFO: compiled from: CachingUmaRecorder.java */
    public static class a {
        public final int a;
        public final String b;
        public final int c;
        public final int d;
        public final int e;
        public final ArrayList f = new ArrayList(1);

        public a(int i, int i2, int i3, int i4, String str) {
            this.a = i;
            this.b = str;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        public final synchronized boolean a(int i) {
            if (this.f.size() >= 256) {
                return false;
            }
            this.f.add(Integer.valueOf(i));
            return true;
        }
    }

    /* JADX INFO: compiled from: CachingUmaRecorder.java */
    public static class b {
        public final String a;
        public final long b;

        public b(String str, long j) {
            this.a = str;
            this.b = j;
        }
    }

    @Override // defpackage.yh3
    public final void a(String str, boolean z) {
        f(1, str, z ? 1 : 0, 0, 0, 0);
    }

    @Override // defpackage.yh3
    public final void b(long j, String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = this.a;
        reentrantReadWriteLock.readLock().lock();
        try {
            yh3 yh3Var = this.f;
            if (yh3Var != null) {
                yh3Var.b(j, str);
                return;
            }
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (this.f == null) {
                    if (this.d.size() < 256) {
                        this.d.add(new b(str, j));
                    } else {
                        this.e++;
                    }
                } else {
                    reentrantReadWriteLock.readLock().lock();
                    try {
                        this.f.b(j, str);
                    } finally {
                    }
                }
            } finally {
                reentrantReadWriteLock.writeLock().unlock();
            }
        } finally {
        }
    }

    @Override // defpackage.yh3
    public final void c(int i, int i2, int i3, int i4, String str) {
        f(2, str, i, i2, i3, i4);
    }

    @Override // defpackage.yh3
    public final void d(int i, String str) {
        f(4, str, i, 0, 0, 0);
    }

    @Override // defpackage.yh3
    public final void e(int i, int i2, int i3, int i4, String str) {
        f(3, str, i, i2, i3, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0073 A[Catch: all -> 0x00a0, TRY_LEAVE, TryCatch #0 {all -> 0x00a0, blocks: (B:17:0x0041, B:19:0x0045, B:21:0x004f, B:23:0x0059, B:24:0x005d, B:25:0x006d, B:27:0x0073, B:30:0x007e), top: B:44:0x0041 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r10, java.lang.String r11, int r12, int r13, int r14, int r15) {
        /*
            r9 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r9.a
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            r1.lock()
            yh3 r1 = r9.f     // Catch: java.lang.Throwable -> La9
            java.util.concurrent.atomic.AtomicInteger r2 = r9.c
            if (r1 == 0) goto L13
            r9.i(r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La9
            goto L2f
        L13:
            java.util.HashMap r1 = r9.b     // Catch: java.lang.Throwable -> La9
            java.lang.Object r1 = r1.get(r11)     // Catch: java.lang.Throwable -> La9
            fl$a r1 = (fl.a) r1     // Catch: java.lang.Throwable -> La9
            if (r1 != 0) goto L26
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            r1.unlock()
            r1 = 0
            goto L37
        L26:
            boolean r1 = r1.a(r12)     // Catch: java.lang.Throwable -> La9
            if (r1 != 0) goto L2f
            r2.incrementAndGet()     // Catch: java.lang.Throwable -> La9
        L2f:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            r1.unlock()
            r1 = 1
        L37:
            if (r1 == 0) goto L3a
            return
        L3a:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r0.writeLock()
            r1.lock()
            yh3 r1 = r9.f     // Catch: java.lang.Throwable -> La0
            if (r1 != 0) goto L7e
            java.util.HashMap r1 = r9.b     // Catch: java.lang.Throwable -> La0
            java.lang.Object r1 = r1.get(r11)     // Catch: java.lang.Throwable -> La0
            fl$a r1 = (fl.a) r1     // Catch: java.lang.Throwable -> La0
            if (r1 != 0) goto L6d
            java.util.HashMap r1 = r9.b     // Catch: java.lang.Throwable -> La0
            int r1 = r1.size()     // Catch: java.lang.Throwable -> La0
            r3 = 256(0x100, float:3.59E-43)
            if (r1 < r3) goto L5d
            r2.incrementAndGet()     // Catch: java.lang.Throwable -> La0
            goto L76
        L5d:
            fl$a r1 = new fl$a     // Catch: java.lang.Throwable -> La0
            r3 = r1
            r4 = r10
            r5 = r13
            r6 = r14
            r7 = r15
            r8 = r11
            r3.<init>(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> La0
            java.util.HashMap r9 = r9.b     // Catch: java.lang.Throwable -> La0
            r9.put(r11, r1)     // Catch: java.lang.Throwable -> La0
        L6d:
            boolean r9 = r1.a(r12)     // Catch: java.lang.Throwable -> La0
            if (r9 != 0) goto L76
            r2.incrementAndGet()     // Catch: java.lang.Throwable -> La0
        L76:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r9 = r0.writeLock()
            r9.unlock()
            return
        L7e:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()     // Catch: java.lang.Throwable -> La0
            r1.lock()     // Catch: java.lang.Throwable -> La0
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r0.writeLock()
            r1.unlock()
            r9.i(r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> L97
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r9 = r0.readLock()
            r9.unlock()
            return
        L97:
            r9 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r10 = r0.readLock()
            r10.unlock()
            throw r9
        La0:
            r9 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r10 = r0.writeLock()
            r10.unlock()
            throw r9
        La9:
            r9 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r10 = r0.readLock()
            r10.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fl.f(int, java.lang.String, int, int, int, int):void");
    }

    public final void g(int i, Map map) {
        int size;
        int size2 = map.size();
        int i2 = 0;
        for (a aVar : map.values()) {
            yh3 yh3Var = this.f;
            synchronized (aVar) {
                int i3 = aVar.a;
                if (i3 == 1) {
                    for (int i4 = 0; i4 < aVar.f.size(); i4++) {
                        yh3Var.a(aVar.b, ((Integer) aVar.f.get(i4)).intValue() != 0);
                    }
                } else if (i3 == 2) {
                    for (int i5 = 0; i5 < aVar.f.size(); i5++) {
                        yh3Var.c(((Integer) aVar.f.get(i5)).intValue(), aVar.c, aVar.d, aVar.e, aVar.b);
                    }
                } else if (i3 == 3) {
                    for (int i6 = 0; i6 < aVar.f.size(); i6++) {
                        yh3Var.e(((Integer) aVar.f.get(i6)).intValue(), aVar.c, aVar.d, aVar.e, aVar.b);
                    }
                } else if (i3 == 4) {
                    for (int i7 = 0; i7 < aVar.f.size(); i7++) {
                        yh3Var.d(((Integer) aVar.f.get(i7)).intValue(), aVar.b);
                    }
                }
                size = aVar.f.size();
                aVar.f.clear();
            }
            i2 += size;
        }
        fa1.h("CachingUmaRecorder", "Flushed %d samples from %d histograms.", Integer.valueOf(i2), Integer.valueOf(size2));
        this.f.c(i, 1, 1000000, 50, "UMA.JavaCachingRecorder.DroppedHistogramSampleCount");
        this.f.c(size2, 1, 100000, 50, "UMA.JavaCachingRecorder.FlushedHistogramCount");
        this.f.c(i2 + i, 1, 1000000, 50, "UMA.JavaCachingRecorder.InputHistogramSampleCount");
    }

    public final void h(int i, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            this.f.b(bVar.b, bVar.a);
        }
        this.f.c(i, 1, 1000, 50, "UMA.JavaCachingRecorder.DroppedUserActionCount");
        this.f.c(list.size() + i, 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 50, "UMA.JavaCachingRecorder.InputUserActionCount");
    }

    public final void i(int i, String str, int i2, int i3, int i4, int i5) {
        if (i == 1) {
            this.f.a(str, i2 != 0);
            return;
        }
        if (i == 2) {
            this.f.c(i2, i3, i4, i5, str);
        } else if (i == 3) {
            this.f.e(i2, i3, i4, i5, str);
        } else {
            if (i != 4) {
                throw new UnsupportedOperationException(gq2.e("Unknown histogram type ", i));
            }
            this.f.d(i2, str);
        }
    }
}
