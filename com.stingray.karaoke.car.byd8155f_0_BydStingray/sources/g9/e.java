package g9;

import B2.C0075o;
import M.t;
import c5.InterfaceC1031h;
import c9.G;
import c9.H;
import c9.I;
import c9.q;
import j9.z;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.net.Socket;
import java.util.HashMap;
import n2.C1914n;
import r9.AbstractC2312b;
import r9.C;
import r9.D;
import u2.InterfaceC2491B;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f15042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f15043b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f15044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f15045f;

    public e(j jVar, q qVar, f fVar, h9.c cVar) {
        A7.m.f(jVar, "call");
        A7.m.f(qVar, "eventListener");
        A7.m.f(fVar, "finder");
        this.f15043b = jVar;
        this.c = qVar;
        this.d = fVar;
        this.f15044e = cVar;
        this.f15045f = cVar.h();
    }

    public IOException a(long j3, boolean z6, boolean z10, IOException iOException) {
        if (iOException != null) {
            f(iOException);
        }
        q qVar = (q) this.c;
        j jVar = (j) this.f15043b;
        if (z10) {
            if (iOException != null) {
                qVar.s(jVar, iOException);
            } else {
                qVar.q(jVar, j3);
            }
        }
        if (z6) {
            if (iOException != null) {
                qVar.x(jVar, iOException);
            } else {
                qVar.v(jVar, j3);
            }
        }
        return jVar.i(this, z10, z6, iOException);
    }

    public InterfaceC1031h b(int i7) {
        InterfaceC1031h c1914n;
        final int i10 = 2;
        final int i11 = 1;
        final int i12 = 3;
        HashMap map = (HashMap) this.c;
        InterfaceC1031h interfaceC1031h = (InterfaceC1031h) map.get(Integer.valueOf(i7));
        if (interfaceC1031h != null) {
            return interfaceC1031h;
        }
        final t tVar = (t) this.f15044e;
        tVar.getClass();
        if (i7 == 0) {
            final GenericDeclaration genericDeclarationAsSubclass = Class.forName("androidx.media3.exoplayer.dash.DashMediaSource$Factory").asSubclass(InterfaceC2491B.class);
            final int i13 = 0;
            c1914n = new InterfaceC1031h() { // from class: u2.p
                @Override // c5.InterfaceC1031h
                public final Object get() {
                    switch (i13) {
                        case 0:
                            return C2514q.d((Class) genericDeclarationAsSubclass, tVar);
                        case 1:
                            return C2514q.d((Class) genericDeclarationAsSubclass, tVar);
                        case 2:
                            return C2514q.d((Class) genericDeclarationAsSubclass, tVar);
                        default:
                            return new V(tVar, (C0075o) ((g9.e) genericDeclarationAsSubclass).f15043b);
                    }
                }
            };
        } else if (i7 == 1) {
            final GenericDeclaration genericDeclarationAsSubclass2 = Class.forName("androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory").asSubclass(InterfaceC2491B.class);
            c1914n = new InterfaceC1031h() { // from class: u2.p
                @Override // c5.InterfaceC1031h
                public final Object get() {
                    switch (i11) {
                        case 0:
                            return C2514q.d((Class) genericDeclarationAsSubclass2, tVar);
                        case 1:
                            return C2514q.d((Class) genericDeclarationAsSubclass2, tVar);
                        case 2:
                            return C2514q.d((Class) genericDeclarationAsSubclass2, tVar);
                        default:
                            return new V(tVar, (C0075o) ((g9.e) genericDeclarationAsSubclass2).f15043b);
                    }
                }
            };
        } else if (i7 == 2) {
            final GenericDeclaration genericDeclarationAsSubclass3 = Class.forName("androidx.media3.exoplayer.hls.HlsMediaSource$Factory").asSubclass(InterfaceC2491B.class);
            c1914n = new InterfaceC1031h() { // from class: u2.p
                @Override // c5.InterfaceC1031h
                public final Object get() {
                    switch (i10) {
                        case 0:
                            return C2514q.d((Class) genericDeclarationAsSubclass3, tVar);
                        case 1:
                            return C2514q.d((Class) genericDeclarationAsSubclass3, tVar);
                        case 2:
                            return C2514q.d((Class) genericDeclarationAsSubclass3, tVar);
                        default:
                            return new V(tVar, (C0075o) ((g9.e) genericDeclarationAsSubclass3).f15043b);
                    }
                }
            };
        } else if (i7 == 3) {
            c1914n = new C1914n(Class.forName("androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory").asSubclass(InterfaceC2491B.class), i12);
        } else {
            if (i7 != 4) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Unrecognized contentType: "));
            }
            c1914n = new InterfaceC1031h() { // from class: u2.p
                @Override // c5.InterfaceC1031h
                public final Object get() {
                    switch (i12) {
                        case 0:
                            return C2514q.d((Class) this, tVar);
                        case 1:
                            return C2514q.d((Class) this, tVar);
                        case 2:
                            return C2514q.d((Class) this, tVar);
                        default:
                            return new V(tVar, (C0075o) ((g9.e) this).f15043b);
                    }
                }
            };
        }
        map.put(Integer.valueOf(i7), c1914n);
        return c1914n;
    }

    public l c() {
        j jVar = (j) this.f15043b;
        if (jVar.f15068v) {
            throw new IllegalStateException("Check failed.");
        }
        jVar.f15068v = true;
        jVar.f15063q.j();
        m mVarH = ((h9.c) this.f15044e).h();
        mVarH.getClass();
        Socket socket = mVarH.d;
        A7.m.c(socket);
        D d = mVarH.f15080h;
        A7.m.c(d);
        C c = mVarH.f15081i;
        A7.m.c(c);
        socket.setSoTimeout(0);
        mVarH.k();
        return new l(d, c, this);
    }

    public I d(H h10) throws IOException {
        h9.c cVar = (h9.c) this.f15044e;
        try {
            String strG = H.g(h10, "Content-Type");
            long jE = cVar.e(h10);
            return new I(strG, jE, AbstractC2312b.c(new d(this, cVar.f(h10), jE)), 1);
        } catch (IOException e10) {
            ((q) this.c).x((j) this.f15043b, e10);
            f(e10);
            throw e10;
        }
    }

    public G e(boolean z6) throws IOException {
        try {
            G g7 = ((h9.c) this.f15044e).g(z6);
            if (g7 != null) {
                g7.f12513m = this;
            }
            return g7;
        } catch (IOException e10) {
            ((q) this.c).x((j) this.f15043b, e10);
            f(e10);
            throw e10;
        }
    }

    public void f(IOException iOException) {
        this.f15042a = true;
        ((f) this.d).c(iOException);
        m mVarH = ((h9.c) this.f15044e).h();
        j jVar = (j) this.f15043b;
        synchronized (mVarH) {
            try {
                A7.m.f(jVar, "call");
                if (!(iOException instanceof z)) {
                    if (!(mVarH.f15079g != null) || (iOException instanceof j9.a)) {
                        mVarH.f15082j = true;
                        if (mVarH.f15084m == 0) {
                            m.d(jVar.l, mVarH.f15076b, iOException);
                            mVarH.l++;
                        }
                    }
                } else if (((z) iOException).l == 8) {
                    int i7 = mVarH.f15085n + 1;
                    mVarH.f15085n = i7;
                    if (i7 > 1) {
                        mVarH.f15082j = true;
                        mVarH.l++;
                    }
                } else if (((z) iOException).l != 9 || !jVar.f15058A) {
                    mVarH.f15082j = true;
                    mVarH.l++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public e(C0075o c0075o, io.sentry.hints.i iVar) {
        this.f15043b = c0075o;
        this.f15045f = iVar;
        this.c = new HashMap();
        this.d = new HashMap();
        this.f15042a = true;
    }
}
