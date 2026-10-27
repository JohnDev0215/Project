package c9;

import a.AbstractC0904a;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import n7.AbstractC1985B;
import r9.AbstractC2312b;
import r9.C2318h;
import r9.C2319i;
import r9.C2322l;

/* JADX INFO: renamed from: c9.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1043d {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f12547k;
    public static final String l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f12548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f12549b;
    public final String c;
    public final D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f12550e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f12551f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u f12552g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final t f12553h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f12554i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f12555j;

    static {
        l9.o oVar = l9.o.f18874a;
        l9.o.f18874a.getClass();
        f12547k = "OkHttp-Sent-Millis";
        l9.o.f18874a.getClass();
        l = "OkHttp-Received-Millis";
    }

    public C1043d(r9.J j3) throws IllegalAccessException, IOException, InvocationTargetException {
        w wVarC;
        A7.m.f(j3, "rawSource");
        try {
            r9.D dC = AbstractC2312b.c(j3);
            String strG = dC.G(Long.MAX_VALUE);
            try {
                v vVar = new v();
                vVar.f(null, strG);
                wVarC = vVar.c();
            } catch (IllegalArgumentException unused) {
                wVarC = null;
            }
            if (wVarC == null) {
                IOException iOException = new IOException("Cache corruption for ".concat(strG));
                l9.o oVar = l9.o.f18874a;
                l9.o.f18874a.getClass();
                l9.o.i("cache corruption", 5, iOException);
                throw iOException;
            }
            this.f12548a = wVarC;
            this.c = dC.G(Long.MAX_VALUE);
            A7.D d = new A7.D(5, false);
            int iY = w0.c.Y(dC);
            for (int i7 = 0; i7 < iY; i7++) {
                d.h(dC.G(Long.MAX_VALUE));
            }
            this.f12549b = d.m();
            B5.u uVarR = h6.m.r(dC.G(Long.MAX_VALUE));
            this.d = (D) uVarR.f736n;
            this.f12550e = uVarR.f735m;
            this.f12551f = (String) uVarR.o;
            A7.D d10 = new A7.D(5, false);
            int iY2 = w0.c.Y(dC);
            for (int i10 = 0; i10 < iY2; i10++) {
                d10.h(dC.G(Long.MAX_VALUE));
            }
            String str = f12547k;
            String strO = d10.o(str);
            String str2 = l;
            String strO2 = d10.o(str2);
            d10.x(str);
            d10.x(str2);
            this.f12554i = strO != null ? Long.parseLong(strO) : 0L;
            this.f12555j = strO2 != null ? Long.parseLong(strO2) : 0L;
            this.f12552g = d10.m();
            if (A7.m.a(this.f12548a.f12619a, "https")) {
                String strG2 = dC.G(Long.MAX_VALUE);
                if (strG2.length() > 0) {
                    throw new IOException("expected \"\" but was \"" + strG2 + '\"');
                }
                this.f12553h = new t(!dC.b() ? io.sentry.config.a.a0(dC.G(Long.MAX_VALUE)) : K.SSL_3_0, C1051l.f12571b.c(dC.G(Long.MAX_VALUE)), d9.b.x(a(dC)), new s(0, d9.b.x(a(dC))));
            } else {
                this.f12553h = null;
            }
            AbstractC1985B.c(j3, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AbstractC1985B.c(j3, th);
                throw th2;
            }
        }
    }

    public static List a(r9.D d) throws IOException {
        int iY = w0.c.Y(d);
        if (iY == -1) {
            return n7.u.l;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ArrayList arrayList = new ArrayList(iY);
            for (int i7 = 0; i7 < iY; i7++) {
                String strG = d.G(Long.MAX_VALUE);
                C2319i c2319i = new C2319i();
                C2322l c2322l = C2322l.o;
                C2322l c2322lN = m6.b.n(strG);
                if (c2322lN == null) {
                    throw new IOException("Corrupt certificate in cache entry");
                }
                c2319i.U(c2322lN);
                arrayList.add(certificateFactory.generateCertificate(new C2318h(c2319i, 0)));
            }
            return arrayList;
        } catch (CertificateException e10) {
            throw new IOException(e10.getMessage());
        }
    }

    public static void b(r9.C c, List list) throws IOException {
        try {
            c.E(list.size());
            c.s(10);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                byte[] encoded = ((Certificate) it.next()).getEncoded();
                C2322l c2322l = C2322l.o;
                A7.m.e(encoded, "bytes");
                c.D(m6.b.G0(encoded).a());
                c.s(10);
            }
        } catch (CertificateEncodingException e10) {
            throw new IOException(e10.getMessage());
        }
    }

    public final void c(A2.c cVar) {
        w wVar = this.f12548a;
        t tVar = this.f12553h;
        u uVar = this.f12552g;
        u uVar2 = this.f12549b;
        r9.C cB = AbstractC2312b.b(cVar.n(0));
        try {
            cB.D(wVar.f12624h);
            cB.s(10);
            cB.D(this.c);
            cB.s(10);
            cB.E(uVar2.size());
            cB.s(10);
            int size = uVar2.size();
            for (int i7 = 0; i7 < size; i7++) {
                cB.D(uVar2.l(i7));
                cB.D(": ");
                cB.D(uVar2.s(i7));
                cB.s(10);
            }
            D d = this.d;
            int i10 = this.f12550e;
            String str = this.f12551f;
            A7.m.f(d, "protocol");
            A7.m.f(str, "message");
            StringBuilder sb = new StringBuilder();
            if (d == D.HTTP_1_0) {
                sb.append("HTTP/1.0");
            } else {
                sb.append("HTTP/1.1");
            }
            sb.append(' ');
            sb.append(i10);
            sb.append(' ');
            sb.append(str);
            String string = sb.toString();
            A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
            cB.D(string);
            cB.s(10);
            cB.E(uVar.size() + 2);
            cB.s(10);
            int size2 = uVar.size();
            for (int i11 = 0; i11 < size2; i11++) {
                cB.D(uVar.l(i11));
                cB.D(": ");
                cB.D(uVar.s(i11));
                cB.s(10);
            }
            cB.D(f12547k);
            cB.D(": ");
            cB.E(this.f12554i);
            cB.s(10);
            cB.D(l);
            cB.D(": ");
            cB.E(this.f12555j);
            cB.s(10);
            if (A7.m.a(wVar.f12619a, "https")) {
                cB.s(10);
                A7.m.c(tVar);
                cB.D(tVar.f12610b.f12586a);
                cB.s(10);
                b(cB, tVar.a());
                b(cB, tVar.c);
                cB.D(tVar.f12609a.l);
                cB.s(10);
            }
            AbstractC1985B.c(cB, null);
        } finally {
        }
    }

    public C1043d(H h10) {
        u uVar;
        H0.b bVar = h10.l;
        this.f12548a = (w) bVar.f2765m;
        H h11 = h10.f12519s;
        A7.m.c(h11);
        u uVar2 = (u) h11.l.o;
        u uVar3 = h10.f12517q;
        Set setI0 = w0.c.i0(uVar3);
        if (setI0.isEmpty()) {
            uVar = d9.b.f13646b;
        } else {
            ArrayList arrayList = new ArrayList(20);
            int size = uVar2.size();
            for (int i7 = 0; i7 < size; i7++) {
                String strL = uVar2.l(i7);
                if (setI0.contains(strL)) {
                    String strS = uVar2.s(i7);
                    A7.m.f(strL, "name");
                    A7.m.f(strS, "value");
                    AbstractC0904a.t(strL);
                    AbstractC0904a.v(strS, strL);
                    arrayList.add(strL);
                    arrayList.add(Q8.h.g1(strS).toString());
                }
            }
            uVar = new u((String[]) arrayList.toArray(new String[0]));
        }
        this.f12549b = uVar;
        this.c = (String) bVar.f2766n;
        this.d = h10.f12514m;
        this.f12550e = h10.o;
        this.f12551f = h10.f12515n;
        this.f12552g = uVar3;
        this.f12553h = h10.f12516p;
        this.f12554i = h10.f12522v;
        this.f12555j = h10.f12523w;
    }
}
