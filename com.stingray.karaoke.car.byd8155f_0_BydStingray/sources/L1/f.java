package l1;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f18421h = new String("FIXED_DIMENSION");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f18422i = new String("WRAP_DIMENSION");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f18423j = new String("SPREAD_DIMENSION");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f18424k = new String("PARENT_DIMENSION");
    public static final String l = new String("PERCENT_DIMENSION");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f18425m = new String("RATIO_DIMENSION");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f18429f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f18426a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18427b = Integer.MAX_VALUE;
    public float c = 1.0f;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f18428e = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f18430g = false;

    public f(String str) {
        this.f18429f = str;
    }

    public static f b(int i7) {
        f fVar = new f(f18421h);
        fVar.f18429f = null;
        fVar.d = i7;
        return fVar;
    }

    public static f c(String str) {
        f fVar = new f();
        fVar.f18426a = 0;
        fVar.f18427b = Integer.MAX_VALUE;
        fVar.c = 1.0f;
        fVar.d = 0;
        fVar.f18428e = null;
        fVar.f18429f = str;
        fVar.f18430g = true;
        return fVar;
    }

    public final void a(o1.d dVar, int i7) {
        String str = this.f18428e;
        if (str != null) {
            dVar.K(str);
        }
        String str2 = f18424k;
        String str3 = l;
        String str4 = f18422i;
        int i10 = 2;
        if (i7 == 0) {
            if (this.f18430g) {
                dVar.O(3);
                String str5 = this.f18429f;
                if (str5 == str4) {
                    i10 = 1;
                } else if (str5 != str3) {
                    i10 = 0;
                }
                dVar.P(i10, this.c, this.f18426a, this.f18427b);
                return;
            }
            int i11 = this.f18426a;
            if (i11 > 0) {
                if (i11 < 0) {
                    dVar.f20286c0 = 0;
                } else {
                    dVar.f20286c0 = i11;
                }
            }
            int i12 = this.f18427b;
            if (i12 < Integer.MAX_VALUE) {
                dVar.f20259D[0] = i12;
            }
            String str6 = this.f18429f;
            if (str6 == str4) {
                dVar.O(2);
                return;
            }
            if (str6 == str2) {
                dVar.O(4);
                return;
            } else {
                if (str6 == null) {
                    dVar.O(1);
                    dVar.S(this.d);
                    return;
                }
                return;
            }
        }
        if (this.f18430g) {
            dVar.Q(3);
            String str7 = this.f18429f;
            if (str7 == str4) {
                i10 = 1;
            } else if (str7 != str3) {
                i10 = 0;
            }
            dVar.R(i10, this.c, this.f18426a, this.f18427b);
            return;
        }
        int i13 = this.f18426a;
        if (i13 > 0) {
            if (i13 < 0) {
                dVar.f20287d0 = 0;
            } else {
                dVar.f20287d0 = i13;
            }
        }
        int i14 = this.f18427b;
        if (i14 < Integer.MAX_VALUE) {
            dVar.f20259D[1] = i14;
        }
        String str8 = this.f18429f;
        if (str8 == str4) {
            dVar.Q(2);
            return;
        }
        if (str8 == str2) {
            dVar.Q(4);
        } else if (str8 == null) {
            dVar.Q(1);
            dVar.N(this.d);
        }
    }
}
