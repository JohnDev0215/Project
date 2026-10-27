package r9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.Inflater;
import n0.C1886o;
import n7.AbstractC1998m;
import p1.C2078f;

/* JADX INFO: loaded from: classes.dex */
public final class M extends o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f21911e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f21912b;
    public final o c;
    public final LinkedHashMap d;

    static {
        String str = z.f21951m;
        f21911e = C1886o.i("/", false);
    }

    public M(z zVar, o oVar, LinkedHashMap linkedHashMap) {
        this.f21912b = zVar;
        this.c = oVar;
        this.d = linkedHashMap;
    }

    @Override // r9.o
    public final void b(z zVar) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // r9.o
    public final void c(z zVar) throws IOException {
        A7.m.f(zVar, "path");
        throw new IOException("zip file systems are read-only");
    }

    @Override // r9.o
    public final List f(z zVar) throws IOException {
        A7.m.f(zVar, "dir");
        z zVar2 = f21911e;
        zVar2.getClass();
        s9.g gVar = (s9.g) this.d.get(s9.c.b(zVar2, zVar, true));
        if (gVar != null) {
            return AbstractC1998m.z0(gVar.f22403q);
        }
        throw new IOException("not a directory: " + zVar);
    }

    @Override // r9.o
    public final C2078f h(z zVar) throws Throwable {
        Long lValueOf;
        Long lValueOf2;
        Long l;
        Long lValueOf3;
        Throwable th;
        Throwable th2;
        A7.m.f(zVar, "path");
        z zVar2 = f21911e;
        zVar2.getClass();
        s9.g gVarF = (s9.g) this.d.get(s9.c.b(zVar2, zVar, true));
        if (gVarF == null) {
            return null;
        }
        long j3 = gVarF.f22396h;
        if (j3 != -1) {
            u uVarI = this.c.i(this.f21912b);
            try {
                D dC = AbstractC2312b.c(uVarI.i(j3));
                try {
                    gVarF = s9.b.f(dC, gVarF);
                    A7.m.c(gVarF);
                    try {
                        dC.close();
                        th2 = null;
                    } catch (Throwable th3) {
                        th2 = th3;
                    }
                } catch (Throwable th4) {
                    try {
                        dC.close();
                    } catch (Throwable th5) {
                        h6.q.l(th4, th5);
                    }
                    th2 = th4;
                    gVarF = null;
                }
                if (th2 != null) {
                    throw th2;
                }
                try {
                    uVarI.close();
                    th = null;
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                if (uVarI != null) {
                    try {
                        uVarI.close();
                    } catch (Throwable th8) {
                        h6.q.l(th7, th8);
                    }
                }
                th = th7;
                gVarF = null;
            }
            if (th != null) {
                throw th;
            }
        }
        boolean z6 = gVarF.f22392b;
        boolean z10 = !z6;
        Long lValueOf4 = z6 ? null : Long.valueOf(gVarF.f22394f);
        Long l10 = gVarF.f22400m;
        if (l10 != null) {
            lValueOf = Long.valueOf((l10.longValue() / ((long) 10000)) - 11644473600000L);
        } else {
            Integer num = gVarF.f22402p;
            lValueOf = num != null ? Long.valueOf(((long) num.intValue()) * 1000) : null;
        }
        Long l11 = gVarF.f22399k;
        if (l11 != null) {
            lValueOf2 = Long.valueOf((l11.longValue() / ((long) 10000)) - 11644473600000L);
        } else {
            Integer num2 = gVarF.f22401n;
            if (num2 != null) {
                lValueOf2 = Long.valueOf(((long) num2.intValue()) * 1000);
            } else {
                int i7 = gVarF.f22398j;
                if (i7 == -1 || i7 == -1) {
                    lValueOf2 = null;
                } else {
                    int i10 = gVarF.f22397i;
                    int i11 = (i10 >> 5) & 15;
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.set(14, 0);
                    gregorianCalendar.set(((i10 >> 9) & 127) + 1980, i11 - 1, i10 & 31, (i7 >> 11) & 31, (i7 >> 5) & 63, (i7 & 31) << 1);
                    lValueOf2 = Long.valueOf(gregorianCalendar.getTime().getTime());
                }
            }
        }
        Long l12 = gVarF.l;
        if (l12 != null) {
            lValueOf3 = Long.valueOf((l12.longValue() / ((long) 10000)) - 11644473600000L);
        } else {
            Integer num3 = gVarF.o;
            if (num3 == null) {
                l = null;
                return new C2078f(z10, z6, null, lValueOf4, lValueOf, lValueOf2, l);
            }
            lValueOf3 = Long.valueOf(((long) num3.intValue()) * 1000);
        }
        l = lValueOf3;
        return new C2078f(z10, z6, null, lValueOf4, lValueOf, lValueOf2, l);
    }

    @Override // r9.o
    public final u i(z zVar) {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // r9.o
    public final H j(z zVar) throws IOException {
        A7.m.f(zVar, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // r9.o
    public final J k(z zVar) throws Throwable {
        Throwable th;
        D dC;
        A7.m.f(zVar, "file");
        z zVar2 = f21911e;
        zVar2.getClass();
        s9.g gVar = (s9.g) this.d.get(s9.c.b(zVar2, zVar, true));
        if (gVar == null) {
            throw new FileNotFoundException("no such file: " + zVar);
        }
        u uVarI = this.c.i(this.f21912b);
        try {
            dC = AbstractC2312b.c(uVarI.i(gVar.f22396h));
            try {
                uVarI.close();
                th = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            if (uVarI != null) {
                try {
                    uVarI.close();
                } catch (Throwable th4) {
                    h6.q.l(th3, th4);
                }
            }
            th = th3;
            dC = null;
        }
        if (th != null) {
            throw th;
        }
        A7.m.f(dC, "<this>");
        s9.b.f(dC, null);
        int i7 = gVar.f22395g;
        long j3 = gVar.f22394f;
        if (i7 == 0) {
            return new s9.e(dC, j3, true);
        }
        return new s9.e(new t(AbstractC2312b.c(new s9.e(dC, gVar.f22393e, true)), new Inflater(true)), j3, false);
    }
}
