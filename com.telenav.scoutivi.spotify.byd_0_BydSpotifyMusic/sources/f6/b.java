package f6;

import c6.b0;
import c6.d;
import c6.t;
import c6.z;
import i6.c;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import y5.p;

/* JADX INFO: compiled from: CacheStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f6408c = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final z f6409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b0 f6410b;

    /* JADX INFO: compiled from: CacheStrategy.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a(c6.b0 r5, c6.z r6) {
            /*
                r4 = this;
                java.lang.String r0 = "response"
                kotlin.jvm.internal.l.e(r5, r0)
                java.lang.String r0 = "request"
                kotlin.jvm.internal.l.e(r6, r0)
                int r0 = r5.I()
                r1 = 200(0xc8, float:2.8E-43)
                r2 = 0
                if (r0 == r1) goto L65
                r1 = 410(0x19a, float:5.75E-43)
                if (r0 == r1) goto L65
                r1 = 414(0x19e, float:5.8E-43)
                if (r0 == r1) goto L65
                r1 = 501(0x1f5, float:7.02E-43)
                if (r0 == r1) goto L65
                r1 = 203(0xcb, float:2.84E-43)
                if (r0 == r1) goto L65
                r1 = 204(0xcc, float:2.86E-43)
                if (r0 == r1) goto L65
                r1 = 307(0x133, float:4.3E-43)
                if (r0 == r1) goto L3b
                r1 = 308(0x134, float:4.32E-43)
                if (r0 == r1) goto L65
                r1 = 404(0x194, float:5.66E-43)
                if (r0 == r1) goto L65
                r1 = 405(0x195, float:5.68E-43)
                if (r0 == r1) goto L65
                switch(r0) {
                    case 300: goto L65;
                    case 301: goto L65;
                    case 302: goto L3b;
                    default: goto L3a;
                }
            L3a:
                return r2
            L3b:
                r0 = 2
                java.lang.String r1 = "Expires"
                r3 = 0
                java.lang.String r0 = c6.b0.U(r5, r1, r3, r0, r3)
                if (r0 != 0) goto L65
                c6.d r0 = r5.z()
                int r0 = r0.c()
                r1 = -1
                if (r0 != r1) goto L65
                c6.d r0 = r5.z()
                boolean r0 = r0.b()
                if (r0 != 0) goto L65
                c6.d r0 = r5.z()
                boolean r0 = r0.a()
                if (r0 != 0) goto L65
                return r2
            L65:
                c6.d r5 = r5.z()
                boolean r5 = r5.h()
                if (r5 != 0) goto L7a
                c6.d r5 = r6.b()
                boolean r5 = r5.h()
                if (r5 != 0) goto L7a
                r2 = 1
            L7a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: f6.b.a.a(c6.b0, c6.z):boolean");
        }
    }

    /* JADX INFO: renamed from: f6.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheStrategy.kt */
    public static final class C0076b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Date f6411a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f6412b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Date f6413c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f6414d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Date f6415e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f6416f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private long f6417g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f6418h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f6419i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final long f6420j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final z f6421k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private final b0 f6422l;

        public C0076b(long j7, z request, b0 b0Var) {
            l.e(request, "request");
            this.f6420j = j7;
            this.f6421k = request;
            this.f6422l = b0Var;
            this.f6419i = -1;
            if (b0Var != null) {
                this.f6416f = b0Var.h0();
                this.f6417g = b0Var.f0();
                t tVarV = b0Var.V();
                int size = tVarV.size();
                for (int i7 = 0; i7 < size; i7++) {
                    String strD = tVarV.d(i7);
                    String strF = tVarV.f(i7);
                    if (p.o(strD, "Date", true)) {
                        this.f6411a = c.a(strF);
                        this.f6412b = strF;
                    } else if (p.o(strD, "Expires", true)) {
                        this.f6415e = c.a(strF);
                    } else if (p.o(strD, "Last-Modified", true)) {
                        this.f6413c = c.a(strF);
                        this.f6414d = strF;
                    } else if (p.o(strD, "ETag", true)) {
                        this.f6418h = strF;
                    } else if (p.o(strD, "Age", true)) {
                        this.f6419i = d6.b.P(strF, -1);
                    }
                }
            }
        }

        private final long a() {
            Date date = this.f6411a;
            long jMax = date != null ? Math.max(0L, this.f6417g - date.getTime()) : 0L;
            int i7 = this.f6419i;
            if (i7 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i7));
            }
            long j7 = this.f6417g;
            return jMax + (j7 - this.f6416f) + (this.f6420j - j7);
        }

        private final b c() {
            if (this.f6422l == null) {
                return new b(this.f6421k, null);
            }
            if (this.f6421k.f() && this.f6422l.N() == null) {
                return new b(this.f6421k, null);
            }
            if (!b.f6408c.a(this.f6422l, this.f6421k)) {
                return new b(this.f6421k, null);
            }
            d dVarB = this.f6421k.b();
            if (dVarB.g() || e(this.f6421k)) {
                return new b(this.f6421k, null);
            }
            d dVarZ = this.f6422l.z();
            long jA = a();
            long jD = d();
            if (dVarB.c() != -1) {
                jD = Math.min(jD, TimeUnit.SECONDS.toMillis(dVarB.c()));
            }
            long millis = 0;
            long millis2 = dVarB.e() != -1 ? TimeUnit.SECONDS.toMillis(dVarB.e()) : 0L;
            if (!dVarZ.f() && dVarB.d() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarB.d());
            }
            if (!dVarZ.g()) {
                long j7 = millis2 + jA;
                if (j7 < millis + jD) {
                    b0.a aVarC0 = this.f6422l.c0();
                    if (j7 >= jD) {
                        aVarC0.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && f()) {
                        aVarC0.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new b(null, aVarC0.c());
                }
            }
            String str = this.f6418h;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f6413c != null) {
                str = this.f6414d;
            } else {
                if (this.f6411a == null) {
                    return new b(this.f6421k, null);
                }
                str = this.f6412b;
            }
            t.a aVarE = this.f6421k.e().e();
            l.c(str);
            aVarE.c(str2, str);
            return new b(this.f6421k.h().d(aVarE.d()).b(), this.f6422l);
        }

        private final long d() {
            b0 b0Var = this.f6422l;
            l.c(b0Var);
            if (b0Var.z().c() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.c());
            }
            Date date = this.f6415e;
            if (date != null) {
                Date date2 = this.f6411a;
                long time = date.getTime() - (date2 != null ? date2.getTime() : this.f6417g);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f6413c == null || this.f6422l.g0().i().m() != null) {
                return 0L;
            }
            Date date3 = this.f6411a;
            long time2 = date3 != null ? date3.getTime() : this.f6416f;
            Date date4 = this.f6413c;
            l.c(date4);
            long time3 = time2 - date4.getTime();
            if (time3 > 0) {
                return time3 / ((long) 10);
            }
            return 0L;
        }

        private final boolean e(z zVar) {
            return (zVar.d("If-Modified-Since") == null && zVar.d("If-None-Match") == null) ? false : true;
        }

        private final boolean f() {
            b0 b0Var = this.f6422l;
            l.c(b0Var);
            return b0Var.z().c() == -1 && this.f6415e == null;
        }

        public final b b() {
            b bVarC = c();
            return (bVarC.b() == null || !this.f6421k.b().i()) ? bVarC : new b(null, null);
        }
    }

    public b(z zVar, b0 b0Var) {
        this.f6409a = zVar;
        this.f6410b = b0Var;
    }

    public final b0 a() {
        return this.f6410b;
    }

    public final z b() {
        return this.f6409a;
    }
}
