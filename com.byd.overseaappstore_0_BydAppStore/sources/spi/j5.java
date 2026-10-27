package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.util.List;
import spi.m5;
import spi.n5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class j5 implements n5, m5.a<Object> {
    public final List<ig> a;
    public final v5<?> b;
    public final n5.a c;
    public int d;
    public ig e;
    public List<ti<File, ?>> f;
    public int g;
    public volatile ti.a<?> h;
    public File i;

    public j5(List<ig> list, v5<?> v5Var, n5.a aVar) {
        this.d = -1;
        this.a = list;
        this.b = v5Var;
        this.c = aVar;
    }

    public j5(v5<?> v5Var, n5.a aVar) {
        this(v5Var.a(), v5Var, aVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
    
        r8.h = null;
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
    
        if (r0 != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        if (r8.g >= r8.f.size()) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0026, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
    
        if (r3 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:
    
        r3 = r8.f;
        r4 = r8.g;
        r8.g = r4 + 1;
        r3 = r3.get(r4);
        r4 = r8.i;
        r5 = r8.b;
        r8.h = r3.b(r4, r5.e, r5.f, r5.i);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
    
        if (r8.h == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0059, code lost:
    
        if (r8.b.h(r8.h.c.a()) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
    
        r8.h.c.d(r8.b.o, r8);
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
    
        return r0;
     */
    @Override // spi.n5
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b() {
        /*
            r8 = this;
        L0:
            java.util.List<spi.ti<java.io.File, ?>> r0 = r8.f     // Catch: java.lang.Throwable -> Laa
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L69
            int r3 = r8.g     // Catch: java.lang.Throwable -> Laa
            int r0 = r0.size()     // Catch: java.lang.Throwable -> Laa
            if (r3 >= r0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            if (r0 != 0) goto L14
            goto L69
        L14:
            r0 = 0
            r8.h = r0     // Catch: java.lang.Throwable -> Laa
            r0 = r2
        L18:
            if (r0 != 0) goto L68
            int r3 = r8.g     // Catch: java.lang.Throwable -> Laa
            java.util.List<spi.ti<java.io.File, ?>> r4 = r8.f     // Catch: java.lang.Throwable -> Laa
            int r4 = r4.size()     // Catch: java.lang.Throwable -> Laa
            if (r3 >= r4) goto L26
            r3 = r1
            goto L27
        L26:
            r3 = r2
        L27:
            if (r3 == 0) goto L68
            java.util.List<spi.ti<java.io.File, ?>> r3 = r8.f     // Catch: java.lang.Throwable -> Laa
            int r4 = r8.g     // Catch: java.lang.Throwable -> Laa
            int r5 = r4 + 1
            r8.g = r5     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> Laa
            spi.ti r3 = (spi.ti) r3     // Catch: java.lang.Throwable -> Laa
            java.io.File r4 = r8.i     // Catch: java.lang.Throwable -> Laa
            spi.v5<?> r5 = r8.b     // Catch: java.lang.Throwable -> Laa
            int r6 = r5.e     // Catch: java.lang.Throwable -> Laa
            int r7 = r5.f     // Catch: java.lang.Throwable -> Laa
            spi.lk r5 = r5.i     // Catch: java.lang.Throwable -> Laa
            spi.ti$a r3 = r3.b(r4, r6, r7, r5)     // Catch: java.lang.Throwable -> Laa
            r8.h = r3     // Catch: java.lang.Throwable -> Laa
            spi.ti$a<?> r3 = r8.h     // Catch: java.lang.Throwable -> Laa
            if (r3 == 0) goto L18
            spi.v5<?> r3 = r8.b     // Catch: java.lang.Throwable -> Laa
            spi.ti$a<?> r4 = r8.h     // Catch: java.lang.Throwable -> Laa
            spi.m5<Data> r4 = r4.c     // Catch: java.lang.Throwable -> Laa
            java.lang.Class r4 = r4.a()     // Catch: java.lang.Throwable -> Laa
            boolean r3 = r3.h(r4)     // Catch: java.lang.Throwable -> Laa
            if (r3 == 0) goto L18
            spi.ti$a<?> r0 = r8.h     // Catch: java.lang.Throwable -> Laa
            spi.m5<Data> r0 = r0.c     // Catch: java.lang.Throwable -> Laa
            spi.v5<?> r3 = r8.b     // Catch: java.lang.Throwable -> Laa
            com.bumptech.glide.Priority r3 = r3.o     // Catch: java.lang.Throwable -> Laa
            r0.d(r3, r8)     // Catch: java.lang.Throwable -> Laa
            r0 = r1
            goto L18
        L68:
            return r0
        L69:
            int r0 = r8.d     // Catch: java.lang.Throwable -> Laa
            int r0 = r0 + r1
            r8.d = r0     // Catch: java.lang.Throwable -> Laa
            java.util.List<spi.ig> r1 = r8.a     // Catch: java.lang.Throwable -> Laa
            int r1 = r1.size()     // Catch: java.lang.Throwable -> Laa
            if (r0 < r1) goto L77
            return r2
        L77:
            java.util.List<spi.ig> r0 = r8.a     // Catch: java.lang.Throwable -> Laa
            int r1 = r8.d     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> Laa
            spi.ig r0 = (spi.ig) r0     // Catch: java.lang.Throwable -> Laa
            spi.k5 r1 = new spi.k5     // Catch: java.lang.Throwable -> Laa
            spi.v5<?> r3 = r8.b     // Catch: java.lang.Throwable -> Laa
            spi.ig r3 = r3.n     // Catch: java.lang.Throwable -> Laa
            r1.<init>(r0, r3)     // Catch: java.lang.Throwable -> Laa
            spi.v5<?> r3 = r8.b     // Catch: java.lang.Throwable -> Laa
            spi.g6 r3 = r3.b()     // Catch: java.lang.Throwable -> Laa
            java.io.File r1 = r3.b(r1)     // Catch: java.lang.Throwable -> Laa
            r8.i = r1     // Catch: java.lang.Throwable -> Laa
            if (r1 == 0) goto L0
            r8.e = r0     // Catch: java.lang.Throwable -> Laa
            spi.v5<?> r0 = r8.b     // Catch: java.lang.Throwable -> Laa
            com.bumptech.glide.c r0 = r0.c     // Catch: java.lang.Throwable -> Laa
            com.bumptech.glide.Registry r0 = r0.b     // Catch: java.lang.Throwable -> Laa
            java.util.List r0 = r0.f(r1)     // Catch: java.lang.Throwable -> Laa
            r8.f = r0     // Catch: java.lang.Throwable -> Laa
            r8.g = r2     // Catch: java.lang.Throwable -> Laa
            goto L0
        Laa:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.j5.b():boolean");
    }

    @Override // spi.m5.a
    public final void c(@NonNull Exception exc) {
        this.c.c(this.e, exc, this.h.c, DataSource.DATA_DISK_CACHE);
    }

    @Override // spi.n5
    public final void cancel() {
        ti.a<?> aVar = this.h;
        if (aVar != null) {
            aVar.c.cancel();
        }
    }

    @Override // spi.m5.a
    public final void f(Object obj) {
        this.c.a(this.e, obj, this.h.c, DataSource.DATA_DISK_CACHE, this.e);
    }
}
