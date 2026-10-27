package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Gt implements Kt {
    public final ReentrantReadWriteLock a = new ReentrantReadWriteLock(false);
    public Map b = new HashMap();
    public AtomicInteger c = new AtomicInteger();
    public List d = new ArrayList();
    public int e;
    public Kt f;

    @Override // defpackage.Kt
    public void a(String str, int i) {
        f(4, str, i, 0, 0, 0);
    }

    @Override // defpackage.Kt
    public void b(String str, boolean z) {
        f(1, str, z ? 1 : 0, 0, 0, 0);
    }

    @Override // defpackage.Kt
    public void c(String str, long j) {
        this.a.readLock().lock();
        try {
            if (this.f != null) {
                this.f.c(str, j);
                return;
            }
            this.a.readLock().unlock();
            this.a.writeLock().lock();
            try {
                if (this.f == null) {
                    if (this.d.size() < 256) {
                        this.d.add(new Ft(str, j));
                    } else {
                        this.e++;
                    }
                } else {
                    this.a.readLock().lock();
                    try {
                        this.f.c(str, j);
                    } finally {
                    }
                }
            } finally {
                this.a.writeLock().unlock();
            }
        } finally {
        }
    }

    @Override // defpackage.Kt
    public void d(String str, int i, int i2, int i3, int i4) {
        f(3, str, i, i2, i3, i4);
    }

    @Override // defpackage.Kt
    public void e(String str, int i, int i2, int i3, int i4) {
        f(2, str, i, i2, i3, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[Catch: all -> 0x00b9, TRY_LEAVE, TryCatch #1 {all -> 0x00b9, blocks: (B:16:0x0045, B:18:0x0049, B:20:0x0053, B:22:0x005d, B:23:0x0063, B:24:0x0074, B:26:0x0080, B:29:0x008f), top: B:44:0x0045 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r10, java.lang.String r11, int r12, int r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Gt.f(int, java.lang.String, int, int, int, int):void");
    }

    public final void g(Map map, int i) {
        int size;
        int size2 = map.size();
        int i2 = 0;
        for (Et et : map.values()) {
            Kt kt = this.f;
            synchronized (et) {
                int i3 = et.a;
                if (i3 == 1) {
                    for (int i4 = 0; i4 < et.f.size(); i4++) {
                        kt.b(et.b, ((Integer) et.f.get(i4)).intValue() != 0);
                    }
                } else if (i3 == 2) {
                    for (int i5 = 0; i5 < et.f.size(); i5++) {
                        kt.e(et.b, ((Integer) et.f.get(i5)).intValue(), et.c, et.d, et.e);
                    }
                } else if (i3 == 3) {
                    for (int i6 = 0; i6 < et.f.size(); i6++) {
                        kt.d(et.b, ((Integer) et.f.get(i6)).intValue(), et.c, et.d, et.e);
                    }
                } else if (i3 == 4) {
                    for (int i7 = 0; i7 < et.f.size(); i7++) {
                        kt.a(et.b, ((Integer) et.f.get(i7)).intValue());
                    }
                }
                size = et.f.size();
                et.f.clear();
            }
            i2 += size;
        }
        Ts.d("CachingUmaRecorder", "Flushed %d samples from %d histograms.", Integer.valueOf(i2), Integer.valueOf(size2));
        this.f.e("UMA.JavaCachingRecorder.DroppedHistogramSampleCount", i, 1, 1000000, 50);
        this.f.e("UMA.JavaCachingRecorder.FlushedHistogramCount", size2, 1, 100000, 50);
        this.f.e("UMA.JavaCachingRecorder.InputHistogramSampleCount", i2 + i, 1, 1000000, 50);
    }

    public final void h(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Ft ft = (Ft) it.next();
            this.f.c(ft.a, ft.b);
        }
        this.f.e("UMA.JavaCachingRecorder.DroppedUserActionCount", i, 1, 1000, 50);
        this.f.e("UMA.JavaCachingRecorder.InputUserActionCount", list.size() + i, 1, 10000, 50);
    }

    public final void i(int i, String str, int i2, int i3, int i4, int i5) {
        if (i == 1) {
            this.f.b(str, i2 != 0);
            return;
        }
        if (i == 2) {
            this.f.e(str, i2, i3, i4, i5);
        } else if (i == 3) {
            this.f.d(str, i2, i3, i4, i5);
        } else {
            if (i != 4) {
                throw new UnsupportedOperationException(Z7.a("Unknown histogram type ", i));
            }
            this.f.a(str, i2);
        }
    }

    public Kt j(Kt kt) {
        Map map;
        int andSet;
        this.a.writeLock().lock();
        try {
            Kt kt2 = this.f;
            this.f = kt;
            List list = null;
            int i = 0;
            if (this.b.isEmpty()) {
                map = null;
                andSet = 0;
            } else {
                map = this.b;
                this.b = new HashMap();
                andSet = this.c.getAndSet(0);
            }
            if (!this.d.isEmpty()) {
                list = this.d;
                this.d = new ArrayList();
                int i2 = this.e;
                this.e = 0;
                i = i2;
            }
            this.a.readLock().lock();
            if (map != null) {
                try {
                    g(map, andSet);
                } catch (Throwable th) {
                    this.a.readLock().unlock();
                    throw th;
                }
            }
            if (list != null) {
                h(list, i);
            }
            this.a.readLock().unlock();
            return kt2;
        } finally {
            this.a.writeLock().unlock();
        }
    }
}
