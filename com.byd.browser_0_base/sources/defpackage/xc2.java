package defpackage;

import J.N;
import android.annotation.SuppressLint;
import defpackage.k00;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: RouterImpl.java */
/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"UseSparseArrays"})
public final class xc2 implements vc2 {
    public final kx e;
    public hh1 f;
    public long g;
    public final HashMap h;
    public final Executor i;

    /* JADX INFO: compiled from: RouterImpl.java */
    public class a implements gh1 {
        public a() {
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            xc2 xc2Var = xc2.this;
            xc2Var.getClass();
            yg1 yg1Var = gq0Var.a().d;
            if (yg1Var.a(1)) {
                hh1 hh1Var = xc2Var.f;
                if (hh1Var != null) {
                    return hh1Var.R0(gq0Var, xc2Var.new b());
                }
                xc2Var.close();
            } else if (yg1Var.a(2)) {
                long j = yg1Var.d;
                HashMap map = xc2Var.h;
                gh1 gh1Var = (gh1) map.get(Long.valueOf(j));
                if (gh1Var != null) {
                    map.remove(Long.valueOf(j));
                    return gh1Var.a(gq0Var);
                }
            } else {
                hh1 hh1Var2 = xc2Var.f;
                if (hh1Var2 != null) {
                    return hh1Var2.a(gq0Var);
                }
            }
            return false;
        }

        @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            hh1 hh1Var = xc2.this.f;
            if (hh1Var != null) {
                hh1Var.close();
            }
        }
    }

    /* JADX INFO: compiled from: RouterImpl.java */
    public class b implements gh1 {
        public boolean e;

        public b() {
        }

        @Override // defpackage.gh1
        public final boolean a(gq0 gq0Var) {
            this.e = true;
            return xc2.this.a(gq0Var);
        }

        @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            xc2.this.close();
        }

        public final void finalize() throws Throwable {
            xc2 xc2Var;
            Executor executor;
            if (!this.e && (executor = (xc2Var = xc2.this).i) != null) {
                executor.execute(new wc2(xc2Var));
            }
            super.finalize();
        }
    }

    public xc2(ch1 ch1Var) {
        WatcherImpl watcherImpl;
        if (ch1Var.w0() != null) {
            ((CoreImpl) ch1Var.w0()).getClass();
            watcherImpl = new WatcherImpl();
        } else {
            watcherImpl = null;
        }
        this.g = 1L;
        this.h = new HashMap();
        kx kxVar = new kx(ch1Var, watcherImpl);
        this.e = kxVar;
        kxVar.h = new a();
        k00 k00VarW0 = ch1Var.w0();
        if (k00VarW0 != null) {
            this.i = ji0.a(k00VarW0);
        } else {
            this.i = null;
        }
    }

    @Override // defpackage.hh1
    public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
        wi2 wi2VarA = gq0Var.a();
        long j = this.g;
        long j2 = j + 1;
        this.g = j2;
        if (j == 0) {
            this.g = 1 + j2;
            j = j2;
        }
        HashMap map = this.h;
        if (map.containsKey(Long.valueOf(j))) {
            throw new IllegalStateException("Unable to find a new request identifier.");
        }
        ByteBuffer byteBuffer = (ByteBuffer) wi2VarA.a;
        yg1 yg1Var = wi2VarA.d;
        yg1Var.getClass();
        byteBuffer.putLong(24, j);
        yg1Var.d = j;
        if (!this.e.a(wi2VarA)) {
            return false;
        }
        map.put(Long.valueOf(j), gh1Var);
        return true;
    }

    @Override // defpackage.gh1
    public final boolean a(gq0 gq0Var) {
        return this.e.a(gq0Var);
    }

    @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.close();
    }

    @Override // defpackage.vt0
    public final ch1 f0() {
        kx kxVar = this.e;
        WatcherImpl watcherImpl = (WatcherImpl) kxVar.g;
        long j = watcherImpl.a;
        if (j != 0) {
            watcherImpl.b = null;
            N.MPTT407x(watcherImpl, j);
        }
        long j2 = watcherImpl.a;
        if (j2 != 0) {
            N.Mi32vqDA(watcherImpl, j2);
            watcherImpl.a = 0L;
        }
        ch1 ch1VarP0 = kxVar.f.P0();
        gh1 gh1Var = kxVar.h;
        if (gh1Var != null) {
            gh1Var.close();
        }
        return ch1VarP0;
    }

    public final void q() {
        kx kxVar = this.e;
        kxVar.getClass();
        ((WatcherImpl) kxVar.g).a(kxVar.f, k00.a.c, kxVar.e);
    }
}
