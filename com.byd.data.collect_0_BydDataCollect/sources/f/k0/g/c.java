package f.k0.g;

import f.e0;
import f.g0;
import f.h;
import f.k0.i.e;
import f.w;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public final e0 f5148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    public final g0 f5149b;

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final long f5150a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final e0 f5151b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final g0 f5152c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Date f5153d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f5154e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Date f5155f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f5156g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Date f5157h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private long f5158i;
        private long j;
        private String k;
        private int l;

        public a(long j, e0 e0Var, g0 g0Var) {
            this.l = -1;
            this.f5150a = j;
            this.f5151b = e0Var;
            this.f5152c = g0Var;
            if (g0Var != null) {
                this.f5158i = g0Var.Z();
                this.j = g0Var.X();
                w wVarS = g0Var.S();
                int iH = wVarS.h();
                for (int i2 = 0; i2 < iH; i2++) {
                    String strE = wVarS.e(i2);
                    String strI = wVarS.i(i2);
                    if ("Date".equalsIgnoreCase(strE)) {
                        this.f5153d = f.k0.i.d.b(strI);
                        this.f5154e = strI;
                    } else if ("Expires".equalsIgnoreCase(strE)) {
                        this.f5157h = f.k0.i.d.b(strI);
                    } else if ("Last-Modified".equalsIgnoreCase(strE)) {
                        this.f5155f = f.k0.i.d.b(strI);
                        this.f5156g = strI;
                    } else if ("ETag".equalsIgnoreCase(strE)) {
                        this.k = strI;
                    } else if ("Age".equalsIgnoreCase(strE)) {
                        this.l = e.d(strI, -1);
                    }
                }
            }
        }

        private long a() {
            Date date = this.f5153d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i2 = this.l;
            if (i2 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i2));
            }
            long j = this.j;
            return jMax + (j - this.f5158i) + (this.f5150a - j);
        }

        private long b() {
            if (this.f5152c.d().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.f5157h != null) {
                Date date = this.f5153d;
                long time = this.f5157h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f5155f == null || this.f5152c.Y().i().z() != null) {
                return 0L;
            }
            Date date2 = this.f5153d;
            long time2 = (date2 != null ? date2.getTime() : this.f5158i) - this.f5155f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        private c d() {
            if (this.f5152c == null) {
                return new c(this.f5151b, null);
            }
            if (this.f5151b.e() && this.f5152c.y() == null) {
                return new c(this.f5151b, null);
            }
            if (!c.a(this.f5152c, this.f5151b)) {
                return new c(this.f5151b, null);
            }
            h hVarB = this.f5151b.b();
            if (hVarB.h() || e(this.f5151b)) {
                return new c(this.f5151b, null);
            }
            h hVarD = this.f5152c.d();
            long jA = a();
            long jB = b();
            if (hVarB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(hVarB.d()));
            }
            long millis = 0;
            long millis2 = hVarB.f() != -1 ? TimeUnit.SECONDS.toMillis(hVarB.f()) : 0L;
            if (!hVarD.g() && hVarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(hVarB.e());
            }
            if (!hVarD.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    g0.a aVarV = this.f5152c.V();
                    if (j >= jB) {
                        aVarV.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && f()) {
                        aVarV.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarV.c());
                }
            }
            String str = this.k;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f5155f != null) {
                str = this.f5156g;
            } else {
                if (this.f5153d == null) {
                    return new c(this.f5151b, null);
                }
                str = this.f5154e;
            }
            w.a aVarF = this.f5151b.d().f();
            f.k0.c.f5131a.b(aVarF, str2, str);
            return new c(this.f5151b.g().c(aVarF.f()).a(), this.f5152c);
        }

        private static boolean e(e0 e0Var) {
            return (e0Var.c("If-Modified-Since") == null && e0Var.c("If-None-Match") == null) ? false : true;
        }

        private boolean f() {
            return this.f5152c.d().d() == -1 && this.f5157h == null;
        }

        public c c() {
            c cVarD = d();
            return (cVarD.f5148a == null || !this.f5151b.b().j()) ? cVarD : new c(null, null);
        }
    }

    c(e0 e0Var, g0 g0Var) {
        this.f5148a = e0Var;
        this.f5149b = g0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.d().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(f.g0 r3, f.e0 r4) {
        /*
            int r0 = r3.j()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L5a
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L5a
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L5a
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L5a
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L5a
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L5a
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L31
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L5a
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L5a
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L5a
            switch(r0) {
                case 300: goto L5a;
                case 301: goto L5a;
                case 302: goto L31;
                default: goto L30;
            }
        L30:
            goto L59
        L31:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.C(r0)
            if (r0 != 0) goto L5a
            f.h r0 = r3.d()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            f.h r0 = r3.d()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            f.h r0 = r3.d()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            f.h r3 = r3.d()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            f.h r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: f.k0.g.c.a(f.g0, f.e0):boolean");
    }
}
