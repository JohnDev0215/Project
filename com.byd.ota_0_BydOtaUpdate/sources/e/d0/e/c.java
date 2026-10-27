package e.d0.e;

import e.a0;
import e.d0.g.e;
import e.s;
import e.y;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public final y f3350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    public final a0 f3351b;

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f3352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final y f3353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final a0 f3354c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Date f3355d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3356e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Date f3357f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f3358g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Date f3359h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f3360i;
        public long j;
        public String k;
        public int l;

        public a(long j, y yVar, a0 a0Var) {
            this.l = -1;
            this.f3352a = j;
            this.f3353b = yVar;
            this.f3354c = a0Var;
            if (a0Var != null) {
                this.f3360i = a0Var.P();
                this.j = a0Var.N();
                s sVarJ = a0Var.J();
                int iF = sVarJ.f();
                for (int i2 = 0; i2 < iF; i2++) {
                    String strC = sVarJ.c(i2);
                    String strG = sVarJ.g(i2);
                    if ("Date".equalsIgnoreCase(strC)) {
                        this.f3355d = e.d0.g.d.b(strG);
                        this.f3356e = strG;
                    } else if ("Expires".equalsIgnoreCase(strC)) {
                        this.f3359h = e.d0.g.d.b(strG);
                    } else if ("Last-Modified".equalsIgnoreCase(strC)) {
                        this.f3357f = e.d0.g.d.b(strG);
                        this.f3358g = strG;
                    } else if ("ETag".equalsIgnoreCase(strC)) {
                        this.k = strG;
                    } else if ("Age".equalsIgnoreCase(strC)) {
                        this.l = e.d(strG, -1);
                    }
                }
            }
        }

        public static boolean e(y yVar) {
            return (yVar.c("If-Modified-Since") == null && yVar.c("If-None-Match") == null) ? false : true;
        }

        public final long a() {
            Date date = this.f3355d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i2 = this.l;
            if (i2 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i2));
            }
            long j = this.j;
            return jMax + (j - this.f3360i) + (this.f3352a - j);
        }

        public final long b() {
            if (this.f3354c.d().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.f3359h != null) {
                Date date = this.f3355d;
                long time = this.f3359h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f3357f == null || this.f3354c.O().h().z() != null) {
                return 0L;
            }
            Date date2 = this.f3355d;
            long time2 = (date2 != null ? date2.getTime() : this.f3360i) - this.f3357f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        public c c() {
            c cVarD = d();
            return (cVarD.f3350a == null || !this.f3353b.b().j()) ? cVarD : new c(null, null);
        }

        public final c d() {
            if (this.f3354c == null) {
                return new c(this.f3353b, null);
            }
            if (this.f3353b.e() && this.f3354c.v() == null) {
                return new c(this.f3353b, null);
            }
            if (!c.a(this.f3354c, this.f3353b)) {
                return new c(this.f3353b, null);
            }
            e.d dVarB = this.f3353b.b();
            if (dVarB.h() || e(this.f3353b)) {
                return new c(this.f3353b, null);
            }
            long jA = a();
            long jB = b();
            if (dVarB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(dVarB.d()));
            }
            long millis = 0;
            long millis2 = dVarB.f() != -1 ? TimeUnit.SECONDS.toMillis(dVarB.f()) : 0L;
            e.d dVarD = this.f3354c.d();
            if (!dVarD.g() && dVarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarB.e());
            }
            if (!dVarD.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    a0.a aVarM = this.f3354c.M();
                    if (j >= jB) {
                        aVarM.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && f()) {
                        aVarM.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarM.c());
                }
            }
            String str = this.k;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f3357f != null) {
                str = this.f3358g;
            } else {
                if (this.f3355d == null) {
                    return new c(this.f3353b, null);
                }
                str = this.f3356e;
            }
            s.a aVarD = this.f3353b.d().d();
            e.d0.a.f3334a.b(aVarD, str2, str);
            return new c(this.f3353b.g().e(aVarD.d()).b(), this.f3354c);
        }

        public final boolean f() {
            return this.f3354c.d().d() == -1 && this.f3359h == null;
        }
    }

    public c(y yVar, a0 a0Var) {
        this.f3350a = yVar;
        this.f3351b = a0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.d().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(e.a0 r3, e.y r4) {
        /*
            int r0 = r3.s()
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
            java.lang.String r0 = r3.H(r0)
            if (r0 != 0) goto L5a
            e.d r0 = r3.d()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            e.d r0 = r3.d()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            e.d r0 = r3.d()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            e.d r3 = r3.d()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            e.d r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: e.d0.e.c.a(e.a0, e.y):boolean");
    }
}
