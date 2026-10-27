package defpackage;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: renamed from: rJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1431rJ implements InterfaceC1212nJ {
    public final OI s;
    public InterfaceC1157mJ t;
    public long u;
    public Map v;
    public final Executor w;

    public C1431rJ(VJ vj) {
        InterfaceC0656dK interfaceC0656dKA = JI.a(vj);
        this.u = 1L;
        this.v = new HashMap();
        OI oi = new OI(vj, interfaceC0656dKA);
        this.s = oi;
        oi.v = new C1322pJ(this, null);
        LJ ljZ = vj.z();
        if (ljZ == null) {
            this.w = null;
            return;
        }
        Executor xi = (Executor) YI.a.get();
        if (xi == null) {
            xi = new XI(ljZ);
            YI.a.set(xi);
        }
        this.w = xi;
    }

    public void A(InterfaceC1157mJ interfaceC1157mJ) {
        this.t = interfaceC1157mJ;
    }

    public void G() {
        OI oi = this.s;
        ((WatcherImpl) oi.u).a(oi.t, KJ.c, oi.s);
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.close();
    }

    @Override // defpackage.ZI
    public OJ e() {
        return this.s.e();
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        C1541tJ c1541tJA = c0989jJ.a();
        long j = this.u;
        long j2 = j + 1;
        this.u = j2;
        if (j == 0) {
            this.u = 1 + j2;
            j = j2;
        }
        if (this.v.containsKey(Long.valueOf(j))) {
            throw new IllegalStateException("Unable to find a new request identifier.");
        }
        C1045kJ c1045kJ = c1541tJA.d;
        ByteBuffer byteBuffer = c1541tJA.a;
        Objects.requireNonNull(c1045kJ);
        byteBuffer.putLong(24, j);
        c1045kJ.d = j;
        if (!this.s.q(c1541tJA)) {
            return false;
        }
        this.v.put(Long.valueOf(j), interfaceC1101lJ);
        return true;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        return this.s.q(c0989jJ);
    }

    public void x(LI li) {
        this.s.w = li;
    }
}
