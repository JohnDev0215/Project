package i8;

import A.s1;
import A7.m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import c9.G;
import c9.u;
import c9.w;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import r9.C;
import r9.D;
import r9.H;
import r9.J;

/* JADX INFO: renamed from: i8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1417b implements h9.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15783a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15784b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f15785e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f15786f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f15787g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f15788h;

    public C1417b(EnumC1416a enumC1416a, n8.f fVar, String[] strArr, String[] strArr2, String[] strArr3, String str, int i7) {
        m.f(enumC1416a, "kind");
        this.c = enumC1416a;
        this.d = fVar;
        this.f15785e = strArr;
        this.f15786f = strArr2;
        this.f15787g = strArr3;
        this.f15788h = str;
        this.f15784b = i7;
    }

    @Override // h9.c
    public H a(H0.b bVar, long j3) {
        m.f(bVar, "request");
        if ("chunked".equalsIgnoreCase(((u) bVar.o).d("Transfer-Encoding"))) {
            if (this.f15784b == 1) {
                this.f15784b = 2;
                return new i9.b(this);
            }
            throw new IllegalStateException(("state: " + this.f15784b).toString());
        }
        if (j3 == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.f15784b == 1) {
            this.f15784b = 2;
            return new i9.e(this);
        }
        throw new IllegalStateException(("state: " + this.f15784b).toString());
    }

    @Override // h9.c
    public void b(H0.b bVar) {
        m.f(bVar, "request");
        Proxy.Type type = ((g9.m) this.d).f15076b.f12529b.type();
        m.e(type, "connection.route().proxy.type()");
        StringBuilder sb = new StringBuilder();
        sb.append((String) bVar.f2766n);
        sb.append(' ');
        w wVar = (w) bVar.f2765m;
        if (wVar.f12625i || type != Proxy.Type.HTTP) {
            String strB = wVar.b();
            String strD = wVar.d();
            if (strD != null) {
                strB = strB + '?' + strD;
            }
            sb.append(strB);
        } else {
            sb.append(wVar);
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        m.e(string, "StringBuilder().apply(builderAction).toString()");
        j((u) bVar.o, string);
    }

    @Override // h9.c
    public void c() {
        ((C) this.f15786f).flush();
    }

    @Override // h9.c
    public void cancel() {
        Socket socket = ((g9.m) this.d).c;
        if (socket != null) {
            d9.b.d(socket);
        }
    }

    @Override // h9.c
    public void d() {
        ((C) this.f15786f).flush();
    }

    @Override // h9.c
    public long e(c9.H h10) {
        if (!h9.d.a(h10)) {
            return 0L;
        }
        if ("chunked".equalsIgnoreCase(c9.H.g(h10, "Transfer-Encoding"))) {
            return -1L;
        }
        return d9.b.k(h10);
    }

    @Override // h9.c
    public J f(c9.H h10) {
        if (!h9.d.a(h10)) {
            return i(0L);
        }
        if ("chunked".equalsIgnoreCase(c9.H.g(h10, "Transfer-Encoding"))) {
            w wVar = (w) h10.l.f2765m;
            if (this.f15784b == 4) {
                this.f15784b = 5;
                return new i9.c(this, wVar);
            }
            throw new IllegalStateException(("state: " + this.f15784b).toString());
        }
        long jK = d9.b.k(h10);
        if (jK != -1) {
            return i(jK);
        }
        if (this.f15784b == 4) {
            this.f15784b = 5;
            ((g9.m) this.d).k();
            return new i9.f(this);
        }
        throw new IllegalStateException(("state: " + this.f15784b).toString());
    }

    @Override // h9.c
    public G g(boolean z6) {
        s1 s1Var = (s1) this.f15787g;
        int i7 = this.f15784b;
        if (i7 != 1 && i7 != 2 && i7 != 3) {
            throw new IllegalStateException(("state: " + this.f15784b).toString());
        }
        try {
            String strG = ((D) s1Var.f267n).G(s1Var.f266m);
            s1Var.f266m -= (long) strG.length();
            B5.u uVarR = h6.m.r(strG);
            int i10 = uVarR.f735m;
            G g7 = new G();
            g7.f12505b = (c9.D) uVarR.f736n;
            g7.c = i10;
            g7.d = (String) uVarR.o;
            A7.D d = new A7.D(5, false);
            while (true) {
                String strG2 = ((D) s1Var.f267n).G(s1Var.f266m);
                s1Var.f266m -= (long) strG2.length();
                if (strG2.length() == 0) {
                    break;
                }
                d.h(strG2);
            }
            g7.c(d.m());
            if (z6 && i10 == 100) {
                return null;
            }
            if (i10 == 100) {
                this.f15784b = 3;
                return g7;
            }
            if (102 > i10 || i10 >= 200) {
                this.f15784b = 4;
                return g7;
            }
            this.f15784b = 3;
            return g7;
        } catch (EOFException e10) {
            throw new IOException("unexpected end of stream on ".concat(((g9.m) this.d).f15076b.f12528a.f12540h.g()), e10);
        }
    }

    @Override // h9.c
    public g9.m h() {
        return (g9.m) this.d;
    }

    public i9.d i(long j3) {
        if (this.f15784b == 4) {
            this.f15784b = 5;
            return new i9.d(this, j3);
        }
        throw new IllegalStateException(("state: " + this.f15784b).toString());
    }

    public void j(u uVar, String str) {
        m.f(str, "requestLine");
        if (this.f15784b != 0) {
            throw new IllegalStateException(("state: " + this.f15784b).toString());
        }
        C c = (C) this.f15786f;
        c.D(str);
        c.D("\r\n");
        int size = uVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            c.D(uVar.l(i7));
            c.D(": ");
            c.D(uVar.s(i7));
            c.D("\r\n");
        }
        c.D("\r\n");
        this.f15784b = 1;
    }

    public String toString() {
        switch (this.f15783a) {
            case 0:
                return ((EnumC1416a) this.c) + " version=" + ((n8.f) this.d);
            default:
                return super.toString();
        }
    }

    public C1417b(c9.C c, g9.m mVar, D d, C c10) {
        m.f(mVar, "connection");
        m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        m.f(c10, "sink");
        this.c = c;
        this.d = mVar;
        this.f15785e = d;
        this.f15786f = c10;
        this.f15787g = new s1(d);
    }
}
