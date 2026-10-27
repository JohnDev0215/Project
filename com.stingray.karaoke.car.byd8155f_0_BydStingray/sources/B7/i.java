package b7;

import a7.C0929a;
import h7.AbstractC1383a;
import io.sentry.C1489n1;
import j7.AbstractC1554a;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class i extends A9.b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final Logger f12288D = Logger.getLogger(i.class.getName());

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f12289A;
    public final ConcurrentHashMap B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f12290C;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f12291n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f12292p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f12293q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f12294r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0929a f12295s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final long f12296t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final URI f12297u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f12298v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LinkedList f12299w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C1000a f12300x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public h f12301y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C8.d f12302z;

    public i(URI uri, C1000a c1000a) {
        super(5);
        if (c1000a.f13538b == null) {
            c1000a.f13538b = "/socket.io";
        }
        if (c1000a.f13543i == null) {
            c1000a.f13543i = null;
        }
        if (c1000a.f13544j == null) {
            c1000a.f13544j = null;
        }
        this.f12300x = c1000a;
        this.B = new ConcurrentHashMap();
        this.f12299w = new LinkedList();
        this.f12291n = true;
        this.f12294r = Integer.MAX_VALUE;
        C0929a c0929a = this.f12295s;
        if (c0929a != null) {
            c0929a.f11199a = 1000L;
        }
        if (c0929a != null) {
            c0929a.f11200b = 5000L;
        }
        if (c0929a != null) {
            c0929a.c = 0.5d;
        }
        C0929a c0929a2 = new C0929a();
        c0929a2.f11199a = 1000L;
        c0929a2.f11200b = 5000L;
        if (0.5d < 0.0d || 0.5d >= 1.0d) {
            throw new IllegalArgumentException("jitter must be between 0 and 1");
        }
        c0929a2.c = 0.5d;
        this.f12295s = c0929a2;
        this.f12296t = 20000L;
        this.f12290C = 1;
        this.f12297u = uri;
        this.f12293q = false;
        this.f12298v = new ArrayList();
        this.f12302z = new C8.d(25);
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(27, false);
        cVar.f16569m = null;
        this.f12289A = cVar;
    }

    public final void a1() {
        f12288D.fine("cleanup");
        while (true) {
            k kVar = (k) this.f12299w.poll();
            if (kVar == null) {
                break;
            } else {
                kVar.a();
            }
        }
        io.sentry.internal.debugmeta.c cVar = this.f12289A;
        cVar.f16570n = null;
        this.f12298v.clear();
        this.f12293q = false;
        C1489n1 c1489n1 = (C1489n1) cVar.f16569m;
        if (c1489n1 != null) {
            c1489n1.f16624m = null;
            c1489n1.f16625n = new ArrayList();
        }
        cVar.f16570n = null;
    }

    public final void b1(j7.d dVar) {
        Level level = Level.FINE;
        Logger logger = f12288D;
        if (logger.isLoggable(level)) {
            logger.fine("writing packet " + dVar);
        }
        if (this.f12293q) {
            this.f12298v.add(dVar);
            return;
        }
        this.f12293q = true;
        g gVar = new g(this);
        this.f12302z.getClass();
        int i7 = dVar.f17066a;
        if ((i7 == 2 || i7 == 3) && AbstractC1383a.a(dVar.d)) {
            dVar.f17066a = dVar.f17066a == 2 ? 5 : 6;
        }
        Logger logger2 = j7.c.f17065a;
        if (logger2.isLoggable(level)) {
            logger2.fine("encoding packet " + dVar);
        }
        int i10 = dVar.f17066a;
        if (5 != i10 && 6 != i10) {
            gVar.a(new String[]{C8.d.o(dVar)});
            return;
        }
        Logger logger3 = AbstractC1554a.f17064a;
        ArrayList arrayList = new ArrayList();
        dVar.d = AbstractC1554a.a(dVar.d, arrayList);
        dVar.f17068e = arrayList.size();
        byte[][] bArr = (byte[][]) arrayList.toArray(new byte[arrayList.size()][]);
        String strO = C8.d.o(dVar);
        ArrayList arrayList2 = new ArrayList(Arrays.asList(bArr));
        arrayList2.add(0, strO);
        gVar.a(arrayList2.toArray());
    }

    public final void c1() {
        if (this.f12292p || this.o) {
            return;
        }
        C0929a c0929a = this.f12295s;
        int i7 = c0929a.d;
        int i10 = this.f12294r;
        Logger logger = f12288D;
        if (i7 >= i10) {
            logger.fine("reconnect failed");
            c0929a.d = 0;
            N0("reconnect_failed", new Object[0]);
            this.f12292p = false;
            return;
        }
        BigInteger bigIntegerValueOf = BigInteger.valueOf(c0929a.f11199a);
        BigInteger bigIntegerValueOf2 = BigInteger.valueOf(2);
        int i11 = c0929a.d;
        c0929a.d = i11 + 1;
        BigInteger bigIntegerMultiply = bigIntegerValueOf.multiply(bigIntegerValueOf2.pow(i11));
        if (c0929a.c != 0.0d) {
            double dRandom = Math.random();
            BigInteger bigInteger = BigDecimal.valueOf(dRandom).multiply(BigDecimal.valueOf(c0929a.c)).multiply(new BigDecimal(bigIntegerMultiply)).toBigInteger();
            bigIntegerMultiply = (((int) Math.floor(dRandom * 10.0d)) & 1) == 0 ? bigIntegerMultiply.subtract(bigInteger) : bigIntegerMultiply.add(bigInteger);
        }
        long jLongValue = bigIntegerMultiply.min(BigInteger.valueOf(c0929a.f11200b)).max(BigInteger.valueOf(c0929a.f11199a)).longValue();
        logger.fine(String.format("will wait %dms before reconnect attempt", Long.valueOf(jLongValue)));
        this.f12292p = true;
        Timer timer = new Timer();
        timer.schedule(new H5.f(this, 2), jLongValue);
        this.f12299w.add(new C1005f(timer, 1));
    }
}
