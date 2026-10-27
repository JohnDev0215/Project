package defpackage;

import J.N;
import defpackage.ap3;
import defpackage.ch1;
import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: Connector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kx implements gh1, vt0<ch1> {
    public final a e = new a();
    public final ch1 f;
    public final ap3 g;
    public gh1 h;
    public fx i;

    /* JADX INFO: compiled from: Connector.java */
    public class a implements ap3.a {
        public a() {
        }

        @Override // ap3.a
        public final void a(int i) {
            ResultAnd<Boolean> resultAndT;
            kx kxVar = kx.this;
            kxVar.getClass();
            if (i != 0) {
                kxVar.q(new ki1(i));
                return;
            }
            do {
                try {
                    resultAndT = kx.t(kxVar.f, kxVar.h);
                } catch (ki1 e) {
                    kxVar.q(e);
                    return;
                }
            } while (resultAndT.b.booleanValue());
            int i2 = resultAndT.a;
            if (i2 != 17) {
                kxVar.q(new ki1(i2));
            }
        }
    }

    public kx(ch1 ch1Var, WatcherImpl watcherImpl) {
        this.f = ch1Var;
        this.g = watcherImpl;
    }

    public static ResultAnd<Boolean> t(ch1 ch1Var, gh1 gh1Var) {
        ch1.c cVar = ch1.c.c;
        ResultAnd resultAndU = ch1Var.u();
        int i = resultAndU.a;
        if (i != 0) {
            return new ResultAnd<>(i, Boolean.FALSE);
        }
        ch1.d dVar = (ch1.d) resultAndU.b;
        if (gh1Var == null) {
            return new ResultAnd<>(i, Boolean.FALSE);
        }
        try {
            return new ResultAnd<>(resultAndU.a, Boolean.valueOf(gh1Var.a(new gq0(ByteBuffer.wrap(dVar.a), dVar.c))));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override // defpackage.gh1
    public final boolean a(gq0 gq0Var) {
        try {
            ch1 ch1Var = this.f;
            ByteBuffer byteBuffer = (ByteBuffer) gq0Var.a;
            List list = (List) gq0Var.b;
            ch1.e eVar = ch1.e.c;
            ch1Var.l(byteBuffer, list);
            return true;
        } catch (ki1 e) {
            q(e);
            return false;
        }
    }

    @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        WatcherImpl watcherImpl = (WatcherImpl) this.g;
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
        this.f.close();
        gh1 gh1Var = this.h;
        if (gh1Var != null) {
            this.h = null;
            gh1Var.close();
        }
    }

    @Override // defpackage.vt0
    public final ch1 f0() {
        WatcherImpl watcherImpl = (WatcherImpl) this.g;
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
        ch1 ch1VarP0 = this.f.P0();
        gh1 gh1Var = this.h;
        if (gh1Var != null) {
            gh1Var.close();
        }
        return ch1VarP0;
    }

    public final void q(ki1 ki1Var) {
        close();
        fx fxVar = this.i;
        if (fxVar != null) {
            try {
                fxVar.q(ki1Var);
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }
}
