package f.b0.e;

import f.b0.g.e;
import f.q;
import f.w;
import f.y;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f5029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y f5030b;

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f5031a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final w f5032b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final y f5033c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Date f5034d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f5035e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Date f5036f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f5037g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Date f5038h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f5039i;
        public long j;
        public String k;
        public int l;

        public a(long j, w wVar, y yVar) {
            this.l = -1;
            this.f5031a = j;
            this.f5032b = wVar;
            this.f5033c = yVar;
            if (yVar != null) {
                this.f5039i = yVar.X();
                this.j = yVar.V();
                q qVarS = yVar.S();
                int iF = qVarS.f();
                for (int i2 = 0; i2 < iF; i2++) {
                    String strC = qVarS.c(i2);
                    String strG = qVarS.g(i2);
                    if ("Date".equalsIgnoreCase(strC)) {
                        this.f5034d = f.b0.g.d.b(strG);
                        this.f5035e = strG;
                    } else if ("Expires".equalsIgnoreCase(strC)) {
                        this.f5038h = f.b0.g.d.b(strG);
                    } else if ("Last-Modified".equalsIgnoreCase(strC)) {
                        this.f5036f = f.b0.g.d.b(strG);
                        this.f5037g = strG;
                    } else if ("ETag".equalsIgnoreCase(strC)) {
                        this.k = strG;
                    } else if ("Age".equalsIgnoreCase(strC)) {
                        this.l = e.d(strG, -1);
                    }
                }
            }
        }

        public static boolean e(w wVar) {
            return (wVar.c("If-Modified-Since") == null && wVar.c("If-None-Match") == null) ? false : true;
        }

        public final long a() {
            Date date = this.f5034d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i2 = this.l;
            if (i2 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i2));
            }
            long j = this.j;
            return jMax + (j - this.f5039i) + (this.f5031a - j);
        }

        public final long b() {
            if (this.f5033c.z().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.f5038h != null) {
                Date date = this.f5034d;
                long time = this.f5038h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f5036f == null || this.f5033c.W().h().y() != null) {
                return 0L;
            }
            Date date2 = this.f5034d;
            long time2 = (date2 != null ? date2.getTime() : this.f5039i) - this.f5036f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        public c c() {
            c cVarD = d();
            return (cVarD.f5029a == null || !this.f5032b.b().j()) ? cVarD : new c(null, null);
        }

        public final c d() {
            if (this.f5033c == null) {
                return new c(this.f5032b, null);
            }
            if (this.f5032b.e() && this.f5033c.P() == null) {
                return new c(this.f5032b, null);
            }
            if (!c.a(this.f5033c, this.f5032b)) {
                return new c(this.f5032b, null);
            }
            f.d dVarB = this.f5032b.b();
            if (dVarB.h() || e(this.f5032b)) {
                return new c(this.f5032b, null);
            }
            long jA = a();
            long jB = b();
            if (dVarB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(dVarB.d()));
            }
            long millis = 0;
            long millis2 = dVarB.f() != -1 ? TimeUnit.SECONDS.toMillis(dVarB.f()) : 0L;
            f.d dVarZ = this.f5033c.z();
            if (!dVarZ.g() && dVarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarB.e());
            }
            if (!dVarZ.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    y.a aVarU = this.f5033c.U();
                    if (j >= jB) {
                        aVarU.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && f()) {
                        aVarU.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarU.c());
                }
            }
            String str = this.k;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f5036f != null) {
                str = this.f5037g;
            } else {
                if (this.f5034d == null) {
                    return new c(this.f5032b, null);
                }
                str = this.f5035e;
            }
            q.a aVarD = this.f5032b.d().d();
            f.b0.a.f5010a.b(aVarD, str2, str);
            return new c(this.f5032b.g().e(aVarD.d()).b(), this.f5033c);
        }

        public final boolean f() {
            return this.f5033c.z().d() == -1 && this.f5038h == null;
        }
    }

    public c(w wVar, y yVar) {
        this.f5029a = wVar;
        this.f5030b = yVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.z().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(f.y r3, f.w r4) {
        /*
            int r0 = r3.O()
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
            java.lang.String r0 = r3.Q(r0)
            if (r0 != 0) goto L5a
            f.d r0 = r3.z()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            f.d r0 = r3.z()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            f.d r0 = r3.z()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            f.d r3 = r3.z()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            f.d r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: f.b0.e.c.a(f.y, f.w):boolean");
    }
}
