package C8;

import A7.m;
import B2.C0073m;
import B2.InterfaceC0068h;
import B5.u;
import F0.E0;
import N5.f0;
import Q5.H1;
import S0.A;
import S0.w;
import T.C0754m0;
import T.C0761q;
import W2.g;
import Y2.j;
import Y2.l;
import android.graphics.Typeface;
import android.net.Uri;
import com.stingray.karaoke.car.player.PlayerService;
import com.stingray.kca.model.KaraokeSongResponseV2;
import d5.C1077F;
import d5.d0;
import e2.B;
import e2.C1192A;
import e2.C1208p;
import e2.C1212u;
import e2.C1214w;
import e2.C1215x;
import e2.C1216y;
import e2.C1217z;
import e2.F;
import e2.H;
import e2.I;
import e4.e;
import e4.f;
import h2.AbstractC1346c;
import i4.InterfaceC1408a;
import io.sentry.android.core.J;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import k6.t;
import l3.P;
import l5.o;
import n2.C1923x;
import u2.C2512o;
import u2.V;
import u2.W;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC0068h, B4.c, g, j, f, InterfaceC1408a, J, o {
    public final /* synthetic */ int l;

    public /* synthetic */ d(int i7) {
        this.l = i7;
    }

    public static Typeface m(String str, A a10, int i7) {
        if (w.a(i7, 0) && m.a(a10, A.f7800s) && (str == null || str.length() == 0)) {
            return Typeface.DEFAULT;
        }
        return Typeface.create(str == null ? Typeface.DEFAULT : Typeface.create(str, 0), a10.l, w.a(i7, 1));
    }

    public static C2512o n(f0 f0Var, long j3) {
        C1192A c1192a;
        String str = f0Var.d;
        Uri uriBuild = new Uri.Builder().scheme("custom").authority("placeholder").appendQueryParameter("mediaId", str).build();
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d0 d0Var = d0.f13415p;
        C1216y c1216y = new C1216y();
        B b10 = B.d;
        H h10 = new H();
        KaraokeSongResponseV2 karaokeSongResponseV2 = f0Var.f4691a;
        h10.f13892a = karaokeSongResponseV2.f13137b;
        h10.f13893b = karaokeSongResponseV2.c;
        String str2 = karaokeSongResponseV2.l;
        h10.f13901m = str2 != null ? Uri.parse(str2) : null;
        I i7 = new I(h10);
        str.getClass();
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uriBuild != null) {
            c1192a = new C1192A(uriBuild, null, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        } else {
            c1192a = null;
        }
        F f10 = new F(str, new C1214w(c1212u), c1192a, new C1217z(c1216y), i7, b10);
        V v10 = PlayerService.f13009v;
        if (v10 == null) {
            m.j("mediaSourceFactory");
            throw null;
        }
        W wD = v10.c(f10);
        long j10 = j3 + 4700;
        u uVar = new u(14, (byte) 0);
        Integer num = karaokeSongResponseV2.f13141h;
        uVar.b(wD, ((long) (num != null ? num.intValue() : 60)) * 1000);
        uVar.b(new u2.f0(j10 * 1000), -9223372036854775807L);
        uVar.o = f10;
        AbstractC1346c.b("Must add at least one source to the concatenation.", uVar.f735m > 0);
        if (((F) uVar.o) == null) {
            uVar.o = F.b(Uri.EMPTY);
        }
        return new C2512o((F) uVar.o, ((C1077F) uVar.f736n).h());
    }

    public static String o(j7.d dVar) {
        StringBuilder sb = new StringBuilder("" + dVar.f17066a);
        int i7 = dVar.f17066a;
        if (5 == i7 || 6 == i7) {
            sb.append(dVar.f17068e);
            sb.append("-");
        }
        String str = dVar.c;
        if (str != null && str.length() != 0 && !"/".equals(dVar.c)) {
            sb.append(dVar.c);
            sb.append(",");
        }
        int i10 = dVar.f17067b;
        if (i10 >= 0) {
            sb.append(i10);
        }
        Object obj = dVar.d;
        if (obj != null) {
            sb.append(obj);
        }
        Logger logger = j7.c.f17065a;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("encoded " + dVar + " as " + ((Object) sb));
        }
        return sb.toString();
    }

    public static InputStream p(String str) throws IOException {
        m.f(str, "path");
        ClassLoader classLoader = d.class.getClassLoader();
        if (classLoader == null) {
            return ClassLoader.getSystemResourceAsStream(str);
        }
        URL resource = classLoader.getResource(str);
        if (resource == null) {
            return null;
        }
        URLConnection uRLConnectionOpenConnection = resource.openConnection();
        uRLConnectionOpenConnection.setUseCaches(false);
        return uRLConnectionOpenConnection.getInputStream();
    }

    public static void q(int i7, C2512o c2512o) {
        C1923x c1923x = PlayerService.f13008u;
        if (c1923x == null) {
            m.j("player");
            throw null;
        }
        c1923x.A1();
        c1923x.a1(i7 + 1, Collections.singletonList(c2512o));
        C1923x c1923x2 = PlayerService.f13008u;
        if (c1923x2 != null) {
            c1923x2.v0(i7);
        } else {
            m.j("player");
            throw null;
        }
    }

    @Override // l5.o
    public Object H() {
        switch (this.l) {
            case 27:
                return new TreeSet();
            case 28:
                return new ArrayList();
            default:
                return new TreeMap();
        }
    }

    @Override // Y2.j
    public int a(C1208p c1208p) {
        return 1;
    }

    @Override // B4.c
    public void b(Object obj) {
    }

    @Override // Y2.j
    public l c(C1208p c1208p) {
        throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
    }

    @Override // i4.InterfaceC1408a
    public File d(e eVar) {
        return null;
    }

    @Override // e4.f
    public void e(byte[] bArr, Object obj, MessageDigest messageDigest) {
    }

    @Override // W2.g
    public B2.F f() {
        return new B2.w(-9223372036854775807L);
    }

    @Override // W2.g
    public long g(C0073m c0073m) {
        return -1L;
    }

    @Override // B2.InterfaceC0068h
    public long h(long j3) {
        return j3;
    }

    @Override // W2.g
    public void i(long j3) {
    }

    @Override // Y2.j
    public boolean j(C1208p c1208p) {
        return false;
    }

    public void k(int i7, C0761q c0761q, boolean z6) {
        int i10;
        c0761q.S(-712666252);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(this) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            t.f18297b.setValue(new a1.e(z6 ? m6.c.f19252a.R(c0761q) : 0));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H1(this, z6, i7, 2);
        }
    }

    @Override // i4.InterfaceC1408a
    public void l(e eVar, P p10) {
    }

    public d(W2.j jVar) {
        this.l = 13;
        new CopyOnWriteArrayList();
    }
}
