package e7;

import A7.k;
import A7.m;
import B5.RunnableC0079c;
import N0.q;
import c9.B;
import c9.C;
import c9.D;
import c9.p;
import c9.u;
import d7.o;
import g9.j;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;
import l3.P;
import l5.C1778f;
import l7.AbstractC1782a;
import n7.AbstractC1998m;
import n7.AbstractC2004s;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class g extends o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final Logger f14481A = Logger.getLogger(C1235e.class.getName());

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public q9.g f14482z;

    @Override // d7.o
    public final void a1() {
        q9.g gVar = this.f14482z;
        if (gVar != null) {
            gVar.b(1000, "");
            this.f14482z = null;
        }
    }

    @Override // d7.o
    public final void b1() {
        int i7 = 0;
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        N0("requestHeaders", treeMap);
        q qVar = new q();
        HashMap map = this.f13546p;
        if (map == null) {
            map = new HashMap();
        }
        String str = this.f13547q ? "wss" : "ws";
        int i10 = this.f13549s;
        String strE = (i10 <= 0 || ((!"wss".equals(str) || i10 == 443) && (!"ws".equals(str) || i10 == 80))) ? "" : android.hardware.bydauto.ac.tsimpl.a.e(i10, ":");
        if (this.f13548r) {
            map.put(this.f13552v, AbstractC1782a.b());
        }
        String strE2 = z.e(map);
        if (strE2.length() > 0) {
            strE2 = "?".concat(strE2);
        }
        String strI = this.f13551u;
        boolean zContains = strI.contains(":");
        StringBuilder sbL = k.l(str, "://");
        if (zContains) {
            strI = android.hardware.bydauto.ac.tsimpl.a.i("[", strI, "]");
        }
        sbL.append(strI);
        sbL.append(strE);
        sbL.append(this.f13550t);
        sbL.append(strE2);
        qVar.T(sbL.toString());
        for (Map.Entry entry : treeMap.entrySet()) {
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                qVar.o((String) entry.getKey(), (String) it.next());
            }
        }
        H0.b bVarR = qVar.r();
        f fVar = new f(this, i7);
        C c = (C) this.f13554x;
        c.getClass();
        q9.g gVar = new q9.g(f9.c.f14651h, bVarR, fVar, new Random(), 0, c.f12477K);
        if (((u) bVarR.o).d("Sec-WebSocket-Extensions") != null) {
            gVar.c(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"));
        } else {
            B b10 = new B();
            b10.f12444a = c.l;
            b10.f12445b = c.f12479m;
            AbstractC2004s.L(b10.c, c.f12480n);
            AbstractC2004s.L(b10.d, c.o);
            b10.f12446e = c.f12481p;
            b10.f12447f = c.f12482q;
            b10.f12448g = c.f12483r;
            b10.f12449h = c.f12484s;
            b10.f12450i = c.f12485t;
            b10.f12451j = c.f12486u;
            b10.f12452k = c.f12487v;
            b10.l = c.f12488w;
            b10.f12453m = c.f12489x;
            b10.f12454n = c.f12490y;
            b10.o = c.f12491z;
            b10.f12455p = c.f12468A;
            b10.f12456q = c.B;
            b10.f12457r = c.f12469C;
            b10.f12458s = c.f12470D;
            b10.f12459t = c.f12471E;
            b10.f12460u = c.f12472F;
            b10.f12461v = c.f12473G;
            b10.f12462w = c.f12474H;
            b10.f12463x = c.f12475I;
            b10.f12464y = c.f12476J;
            b10.f12465z = c.f12477K;
            b10.f12443A = c.f12478L;
            p pVar = c9.q.f12603a;
            byte[] bArr = d9.b.f13645a;
            b10.f12446e = new G2.a(pVar, 6);
            List list = q9.g.f21364w;
            m.f(list, "protocols");
            ArrayList arrayListA0 = AbstractC1998m.A0(list);
            D d = D.H2_PRIOR_KNOWLEDGE;
            if (!arrayListA0.contains(d) && !arrayListA0.contains(D.HTTP_1_1)) {
                throw new IllegalArgumentException(("protocols must contain h2_prior_knowledge or http/1.1: " + arrayListA0).toString());
            }
            if (arrayListA0.contains(d) && arrayListA0.size() > 1) {
                throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayListA0).toString());
            }
            if (arrayListA0.contains(D.HTTP_1_0)) {
                throw new IllegalArgumentException(("protocols must not contain http/1.0: " + arrayListA0).toString());
            }
            if (arrayListA0.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            arrayListA0.remove(D.SPDY_3);
            if (!arrayListA0.equals(b10.f12458s)) {
                b10.f12443A = null;
            }
            List listUnmodifiableList = Collections.unmodifiableList(arrayListA0);
            m.e(listUnmodifiableList, "unmodifiableList(protocolsCopy)");
            b10.f12458s = listUnmodifiableList;
            C c10 = new C(b10);
            q qVarL = bVarR.L();
            qVarL.G("Upgrade", "websocket");
            qVarL.G("Connection", "Upgrade");
            qVarL.G("Sec-WebSocket-Key", gVar.f21368f);
            qVarL.G("Sec-WebSocket-Version", "13");
            qVarL.G("Sec-WebSocket-Extensions", "permessage-deflate");
            H0.b bVarR2 = qVarL.r();
            j jVar = new j(c10, bVarR2, true);
            gVar.f21369g = jVar;
            jVar.e(new C1778f(gVar, 4, bVarR2));
        }
        this.f14482z = gVar;
    }

    @Override // d7.o
    public final void c1(f7.b[] bVarArr) {
        this.f13545n = false;
        RunnableC0079c runnableC0079c = new RunnableC0079c(this, 17);
        int[] iArr = {bVarArr.length};
        for (f7.b bVar : bVarArr) {
            int i7 = this.f13553w;
            if (i7 != 1 && i7 != 2) {
                return;
            }
            f7.d.b(bVar, new P(this, iArr, runnableC0079c, 23));
        }
    }
}
