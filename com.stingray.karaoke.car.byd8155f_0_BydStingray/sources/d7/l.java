package d7;

import G.i0;
import b7.C1003d;
import c7.InterfaceC1035a;
import c9.B;
import c9.C;
import c9.InterfaceC1047h;
import c9.L;
import e7.AbstractC1232b;
import e7.C1235e;
import h5.t;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public abstract class l extends A9.b {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final Logger f13510M = Logger.getLogger(l.class.getName());

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final AtomicInteger f13511N = new AtomicInteger();

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static C f13512O;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final ArrayList f13513A;
    public final HashMap B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ArrayList f13514C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final HashMap f13515D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final LinkedList f13516E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public o f13517F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ScheduledFuture f13518G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final L f13519H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final InterfaceC1047h f13520I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ScheduledExecutorService f13521J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final f f13522K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f13523L;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f13524n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f13525p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f13526q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f13527r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f13528s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f13529t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f13530u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f13531v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f13532w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final String f13533x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final String f13534y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final String f13535z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(URI uri, k kVar) {
        HashMap map;
        String strDecode;
        super(5);
        if (uri != null) {
            kVar = kVar == null ? new k() : kVar;
            kVar.l = uri.getHost();
            kVar.d = "https".equals(uri.getScheme()) || "wss".equals(uri.getScheme());
            kVar.f13540f = uri.getPort();
            String rawQuery = uri.getRawQuery();
            if (rawQuery != null) {
                kVar.f13509m = rawQuery;
            }
        }
        this.f13516E = new LinkedList();
        this.f13522K = new f(this, 0);
        String strSubstring = kVar.l;
        if (strSubstring != null) {
            if (strSubstring.split(":").length > 2) {
                int iIndexOf = strSubstring.indexOf(91);
                strSubstring = iIndexOf != -1 ? strSubstring.substring(iIndexOf + 1) : strSubstring;
                int iLastIndexOf = strSubstring.lastIndexOf(93);
                if (iLastIndexOf != -1) {
                    strSubstring = strSubstring.substring(0, iLastIndexOf);
                }
            }
            kVar.f13537a = strSubstring;
        }
        boolean z6 = kVar.d;
        this.f13524n = z6;
        if (kVar.f13540f == -1) {
            kVar.f13540f = z6 ? 443 : 80;
        }
        String str = kVar.f13537a;
        this.f13533x = str == null ? "localhost" : str;
        this.f13527r = kVar.f13540f;
        String str2 = kVar.f13509m;
        if (str2 != null) {
            map = new HashMap();
            for (String str3 : str2.split("&")) {
                String[] strArrSplit = str3.split("=");
                try {
                    String strDecode2 = URLDecoder.decode(strArrSplit[0], "UTF-8");
                    if (strArrSplit.length > 1) {
                        try {
                            strDecode = URLDecoder.decode(strArrSplit[1], "UTF-8");
                        } catch (UnsupportedEncodingException e10) {
                            throw new RuntimeException(e10);
                        }
                    } else {
                        strDecode = "";
                    }
                    map.put(strDecode2, strDecode);
                } catch (UnsupportedEncodingException e11) {
                    throw new RuntimeException(e11);
                }
            }
        } else {
            map = new HashMap();
        }
        this.f13515D = map;
        this.o = true;
        StringBuilder sb = new StringBuilder();
        String str4 = kVar.f13538b;
        sb.append((str4 == null ? "/engine.io" : str4).replaceAll("/$", ""));
        sb.append("/");
        this.f13534y = sb.toString();
        String str5 = kVar.c;
        this.f13535z = str5 == null ? "t" : str5;
        this.f13525p = kVar.f13539e;
        String[] strArr = kVar.f13508k;
        this.f13513A = new ArrayList(Arrays.asList(strArr == null ? new String[]{"polling", "websocket"} : strArr));
        this.B = new HashMap();
        int i7 = kVar.f13541g;
        this.f13528s = i7 == 0 ? 843 : i7;
        InterfaceC1047h interfaceC1047h = kVar.f13544j;
        interfaceC1047h = interfaceC1047h == null ? null : interfaceC1047h;
        this.f13520I = interfaceC1047h;
        L l = kVar.f13543i;
        L l10 = l != null ? l : null;
        this.f13519H = l10;
        if (interfaceC1047h == null) {
            this.f13520I = d1();
        }
        if (l10 == null) {
            this.f13519H = d1();
        }
    }

    public static void a1(l lVar, o oVar) {
        lVar.getClass();
        Level level = Level.FINE;
        Logger logger = f13510M;
        if (logger.isLoggable(level)) {
            logger.fine("setting transport " + oVar.o);
        }
        if (lVar.f13517F != null) {
            if (logger.isLoggable(level)) {
                logger.fine("clearing existing transport " + lVar.f13517F.o);
            }
            ((ConcurrentHashMap) lVar.f13517F.f476m).clear();
        }
        lVar.f13517F = oVar;
        oVar.T0("drain", new f(lVar, 4));
        oVar.T0("packet", new f(lVar, 3));
        oVar.T0("error", new f(lVar, 2));
        oVar.T0("close", new f(lVar, 1));
    }

    public static C d1() {
        if (f13512O == null) {
            B b10 = new B();
            TimeUnit timeUnit = TimeUnit.MINUTES;
            A7.m.f(timeUnit, "unit");
            byte[] bArr = d9.b.f13645a;
            long millis = timeUnit.toMillis(1L);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("timeout".concat(" too large.").toString());
            }
            if (millis == 0) {
                throw new IllegalArgumentException("timeout".concat(" too small.").toString());
            }
            b10.f12463x = (int) millis;
            f13512O = new C(b10);
        }
        return f13512O;
    }

    public final o b1(String str) {
        o c1235e;
        Level level = Level.FINE;
        Logger logger = f13510M;
        if (logger.isLoggable(level)) {
            logger.fine("creating transport '" + str + "'");
        }
        HashMap map = new HashMap(this.f13515D);
        map.put("EIO", String.valueOf(4));
        map.put("transport", str);
        String str2 = this.f13532w;
        if (str2 != null) {
            map.put("sid", str2);
        }
        n nVar = (n) this.B.get(str);
        n nVar2 = new n();
        nVar2.f13542h = map;
        nVar2.f13537a = nVar != null ? nVar.f13537a : this.f13533x;
        nVar2.f13540f = nVar != null ? nVar.f13540f : this.f13527r;
        nVar2.d = nVar != null ? nVar.d : this.f13524n;
        nVar2.f13538b = nVar != null ? nVar.f13538b : this.f13534y;
        nVar2.f13539e = nVar != null ? nVar.f13539e : this.f13525p;
        nVar2.c = nVar != null ? nVar.c : this.f13535z;
        nVar2.f13541g = nVar != null ? nVar.f13541g : this.f13528s;
        nVar2.f13544j = nVar != null ? nVar.f13544j : this.f13520I;
        nVar2.f13543i = nVar != null ? nVar.f13543i : this.f13519H;
        if ("websocket".equals(str)) {
            c1235e = new e7.g(nVar2);
            c1235e.o = "websocket";
        } else {
            if (!"polling".equals(str)) {
                throw new RuntimeException();
            }
            c1235e = new C1235e(nVar2);
            c1235e.o = "polling";
        }
        N0("transport", c1235e);
        return c1235e;
    }

    public final void c1() {
        if (this.f13523L == 4 || !this.f13517F.f13545n || this.f13526q) {
            return;
        }
        LinkedList linkedList = this.f13516E;
        if (linkedList.size() != 0) {
            Level level = Level.FINE;
            Logger logger = f13510M;
            if (logger.isLoggable(level)) {
                logger.fine(String.format("flushing %d packets in socket", Integer.valueOf(linkedList.size())));
            }
            this.f13529t = linkedList.size();
            o oVar = this.f13517F;
            f7.b[] bVarArr = (f7.b[]) linkedList.toArray(new f7.b[linkedList.size()]);
            oVar.getClass();
            C1707a.a(new t(19, oVar, bVarArr, false));
            N0("flush", new Object[0]);
        }
    }

    public final void e1(String str, Exception exc) {
        int i7 = this.f13523L;
        if (1 == i7 || 2 == i7 || 3 == i7) {
            Level level = Level.FINE;
            Logger logger = f13510M;
            if (logger.isLoggable(level)) {
                logger.fine("socket close with reason: ".concat(str));
            }
            ScheduledFuture scheduledFuture = this.f13518G;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.f13521J;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
            }
            ((ConcurrentHashMap) this.f13517F.f476m).remove("close");
            o oVar = this.f13517F;
            oVar.getClass();
            C1707a.a(new m(oVar, 1));
            ((ConcurrentHashMap) this.f13517F.f476m).clear();
            this.f13523L = 4;
            this.f13532w = null;
            N0("close", str, exc);
            this.f13516E.clear();
            this.f13529t = 0;
        }
    }

    public final void f1(Exception exc) {
        Level level = Level.FINE;
        Logger logger = f13510M;
        if (logger.isLoggable(level)) {
            logger.fine(String.format("socket error %s", exc));
        }
        N0("error", exc);
        e1("transport error", exc);
    }

    public final void g1(i0 i0Var) {
        int i7 = 2;
        int i10 = 1;
        int i11 = 0;
        N0("handshake", i0Var);
        String str = (String) i0Var.f2584n;
        this.f13532w = str;
        this.f13517F.f13546p.put("sid", str);
        List<String> listAsList = Arrays.asList((String[]) i0Var.o);
        ArrayList arrayList = new ArrayList();
        for (String str2 : listAsList) {
            if (this.f13513A.contains(str2)) {
                arrayList.add(str2);
            }
        }
        this.f13514C = arrayList;
        this.f13530u = i0Var.l;
        this.f13531v = i0Var.f2583m;
        Logger logger = f13510M;
        logger.fine("socket open");
        this.f13523L = 2;
        "websocket".equals(this.f13517F.o);
        N0("open", new Object[0]);
        c1();
        if (this.f13523L == 2 && this.o && (this.f13517F instanceof AbstractC1232b)) {
            logger.fine("starting upgrade probes");
            for (String str3 : this.f13514C) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("probing transport '" + str3 + "'");
                }
                o[] oVarArr = {b1(str3)};
                boolean[] zArr = new boolean[i10];
                zArr[0] = false;
                Runnable[] runnableArr = new Runnable[i10];
                i iVar = new i(zArr, str3, oVarArr, this, runnableArr);
                e eVar = new e(i7, zArr, oVarArr, runnableArr);
                j jVar = new j(oVarArr, eVar, str3, this);
                b bVar = new b(jVar, i11);
                b bVar2 = new b(jVar, i10);
                C1003d c1003d = new C1003d(oVarArr, eVar);
                runnableArr[0] = new c(oVarArr, iVar, jVar, bVar, this, bVar2, c1003d);
                oVarArr[0].U0("open", iVar);
                oVarArr[0].U0("error", jVar);
                oVarArr[0].U0("close", bVar);
                U0("close", bVar2);
                U0("upgrading", c1003d);
                o oVar = oVarArr[0];
                oVar.getClass();
                C1707a.a(new m(oVar, i11));
                i7 = 2;
                i10 = 1;
            }
        }
        if (4 == this.f13523L) {
            return;
        }
        h1();
        InterfaceC1035a interfaceC1035a = this.f13522K;
        R0("heartbeat", interfaceC1035a);
        T0("heartbeat", interfaceC1035a);
    }

    public final void h1() {
        ScheduledFuture scheduledFuture = this.f13518G;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        long j3 = this.f13530u + this.f13531v;
        ScheduledExecutorService scheduledExecutorService = this.f13521J;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            this.f13521J = Executors.newSingleThreadScheduledExecutor(new C1.m(2));
        }
        this.f13518G = this.f13521J.schedule(new d(this, 1), j3, TimeUnit.MILLISECONDS);
    }

    public final void i1(f7.b bVar) {
        int i7 = this.f13523L;
        if (3 == i7 || 4 == i7) {
            return;
        }
        N0("packetCreate", bVar);
        this.f13516E.offer(bVar);
        c1();
    }
}
