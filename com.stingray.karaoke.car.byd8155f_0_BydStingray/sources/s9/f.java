package s9;

import A7.m;
import e0.C1183w;
import h6.r;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import m7.n;
import n0.C1886o;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import p1.C2078f;
import r9.AbstractC2312b;
import r9.H;
import r9.J;
import r9.o;
import r9.u;
import r9.v;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class f extends o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f22389e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ClassLoader f22390b;
    public final o c;
    public final n d;

    static {
        String str = z.f21951m;
        f22389e = C1886o.i("/", false);
    }

    public f(ClassLoader classLoader) {
        v vVar = o.f21939a;
        m.f(vVar, "systemFileSystem");
        this.f22390b = classLoader;
        this.c = vVar;
        this.d = r.o(new C1183w(this, 10));
    }

    @Override // r9.o
    public final void b(z zVar) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // r9.o
    public final void c(z zVar) throws IOException {
        m.f(zVar, "path");
        throw new IOException(this + " is read-only");
    }

    @Override // r9.o
    public final List f(z zVar) throws FileNotFoundException {
        m.f(zVar, "dir");
        z zVar2 = f22389e;
        zVar2.getClass();
        String strR = c.b(zVar2, zVar, true).d(zVar2).l.r();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z6 = false;
        for (m7.i iVar : (List) this.d.getValue()) {
            o oVar = (o) iVar.l;
            z zVar3 = (z) iVar.f19259m;
            try {
                List listF = oVar.f(zVar3.e(strR));
                ArrayList<z> arrayList = new ArrayList();
                for (Object obj : listF) {
                    if (m6.b.l((z) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
                for (z zVar4 : arrayList) {
                    m.f(zVar4, "<this>");
                    arrayList2.add(zVar2.e(Q8.o.w0(Q8.h.S0(zVar4.l.r(), zVar3.l.r()), '\\', '/')));
                }
                AbstractC2004s.L(linkedHashSet, arrayList2);
                z6 = true;
            } catch (IOException unused) {
            }
        }
        if (z6) {
            return AbstractC1998m.z0(linkedHashSet);
        }
        throw new FileNotFoundException("file not found: " + zVar);
    }

    @Override // r9.o
    public final C2078f h(z zVar) {
        m.f(zVar, "path");
        if (!m6.b.l(zVar)) {
            return null;
        }
        z zVar2 = f22389e;
        zVar2.getClass();
        String strR = c.b(zVar2, zVar, true).d(zVar2).l.r();
        for (m7.i iVar : (List) this.d.getValue()) {
            C2078f c2078fH = ((o) iVar.l).h(((z) iVar.f19259m).e(strR));
            if (c2078fH != null) {
                return c2078fH;
            }
        }
        return null;
    }

    @Override // r9.o
    public final u i(z zVar) throws FileNotFoundException {
        if (!m6.b.l(zVar)) {
            throw new FileNotFoundException("file not found: " + zVar);
        }
        z zVar2 = f22389e;
        zVar2.getClass();
        String strR = c.b(zVar2, zVar, true).d(zVar2).l.r();
        Iterator it = ((List) this.d.getValue()).iterator();
        while (it.hasNext()) {
            m7.i iVar = (m7.i) it.next();
            try {
                return ((o) iVar.l).i(((z) iVar.f19259m).e(strR));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException("file not found: " + zVar);
    }

    @Override // r9.o
    public final H j(z zVar) throws IOException {
        m.f(zVar, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // r9.o
    public final J k(z zVar) throws IOException {
        m.f(zVar, "file");
        if (!m6.b.l(zVar)) {
            throw new FileNotFoundException("file not found: " + zVar);
        }
        z zVar2 = f22389e;
        zVar2.getClass();
        URL resource = this.f22390b.getResource(c.b(zVar2, zVar, false).d(zVar2).l.r());
        if (resource == null) {
            throw new FileNotFoundException("file not found: " + zVar);
        }
        URLConnection uRLConnectionOpenConnection = resource.openConnection();
        if (uRLConnectionOpenConnection instanceof JarURLConnection) {
            ((JarURLConnection) uRLConnectionOpenConnection).setUseCaches(false);
        }
        InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
        m.e(inputStream, "getInputStream(...)");
        return AbstractC2312b.j(inputStream);
    }
}
