package f;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: EventListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f5501a = new a();

    /* JADX INFO: compiled from: EventListener.java */
    class a extends t {
        a() {
        }
    }

    /* JADX INFO: compiled from: EventListener.java */
    public interface b {
        t a(i iVar);
    }

    static b k(final t tVar) {
        return new b() { // from class: f.d
            @Override // f.t.b
            public final t a(i iVar) {
                t tVar2 = this.f5036a;
                t.l(tVar2, iVar);
                return tVar2;
            }
        };
    }

    static /* synthetic */ t l(t tVar, i iVar) {
        return tVar;
    }

    public void a(i iVar) {
    }

    public void b(i iVar, IOException iOException) {
    }

    public void c(i iVar) {
    }

    public void d(i iVar, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable c0 c0Var) {
    }

    public void e(i iVar, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable c0 c0Var, IOException iOException) {
    }

    public void f(i iVar, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void g(i iVar, m mVar) {
    }

    public void h(i iVar, m mVar) {
    }

    public void i(i iVar, String str, List<InetAddress> list) {
    }

    public void j(i iVar, String str) {
    }

    public void m(i iVar, long j) {
    }

    public void n(i iVar) {
    }

    public void o(i iVar, IOException iOException) {
    }

    public void p(i iVar, e0 e0Var) {
    }

    public void q(i iVar) {
    }

    public void r(i iVar, long j) {
    }

    public void s(i iVar) {
    }

    public void t(i iVar, IOException iOException) {
    }

    public void u(i iVar, g0 g0Var) {
    }

    public void v(i iVar) {
    }

    public void w(i iVar, @Nullable v vVar) {
    }

    public void x(i iVar) {
    }
}
