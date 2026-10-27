package o0;

import k3.X0;

/* JADX INFO: renamed from: o0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2008d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f20180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f20181b;
    public static final C2021q c;
    public static final C2021q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2021q f20182e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2021q f20183f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2021q f20184g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C2021q f20185h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C2021q f20186i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C2021q f20187j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C2021q f20188k;
    public static final C2021q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2021q f20189m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2021q f20190n;
    public static final C2021q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C2021q f20191p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C2015k f20192q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C2015k f20193r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C2021q f20194s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C2016l f20195t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final AbstractC2007c[] f20196u;

    static {
        float[] fArr = {0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f};
        f20180a = fArr;
        float[] fArr2 = {0.67f, 0.33f, 0.21f, 0.71f, 0.14f, 0.08f};
        f20181b = fArr2;
        C2022r c2022r = new C2022r(2.4d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        C2022r c2022r2 = new C2022r(2.2d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        C2023s c2023s = AbstractC2014j.d;
        C2021q c2021q = new C2021q("sRGB IEC61966-2.1", fArr, c2023s, c2022r, 0);
        c = c2021q;
        C2021q c2021q2 = new C2021q("sRGB IEC61966-2.1 (Linear)", fArr, c2023s, 1.0d, 0.0f, 1.0f, 1);
        d = c2021q2;
        C2021q c2021q3 = new C2021q("scRGB-nl IEC 61966-2-2:2003", fArr, c2023s, null, new X0(24), new X0(25), -0.799f, 2.399f, c2022r, 2);
        f20182e = c2021q3;
        C2021q c2021q4 = new C2021q("scRGB IEC 61966-2-2:2003", fArr, c2023s, 1.0d, -0.5f, 7.499f, 3);
        f20183f = c2021q4;
        C2021q c2021q5 = new C2021q("Rec. ITU-R BT.709-5", new float[]{0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f}, c2023s, new C2022r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 4);
        f20184g = c2021q5;
        C2021q c2021q6 = new C2021q("Rec. ITU-R BT.2020-1", new float[]{0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f}, c2023s, new C2022r(2.2222222222222223d, 0.9096697898662786d, 0.09033021013372146d, 0.2222222222222222d, 0.08145d), 5);
        f20185h = c2021q6;
        C2021q c2021q7 = new C2021q("SMPTE RP 431-2-2007 DCI (P3)", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, new C2023s(0.314f, 0.351f), 2.6d, 0.0f, 1.0f, 6);
        f20186i = c2021q7;
        C2021q c2021q8 = new C2021q("Display P3", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, c2023s, c2022r, 7);
        f20187j = c2021q8;
        C2021q c2021q9 = new C2021q("NTSC (1953)", fArr2, AbstractC2014j.f20203a, new C2022r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 8);
        f20188k = c2021q9;
        C2021q c2021q10 = new C2021q("SMPTE-C RGB", new float[]{0.63f, 0.34f, 0.31f, 0.595f, 0.155f, 0.07f}, c2023s, new C2022r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 9);
        l = c2021q10;
        C2021q c2021q11 = new C2021q("Adobe RGB (1998)", new float[]{0.64f, 0.33f, 0.21f, 0.71f, 0.15f, 0.06f}, c2023s, 2.2d, 0.0f, 1.0f, 10);
        f20189m = c2021q11;
        C2021q c2021q12 = new C2021q("ROMM RGB ISO 22028-2:2013", new float[]{0.7347f, 0.2653f, 0.1596f, 0.8404f, 0.0366f, 1.0E-4f}, AbstractC2014j.f20204b, new C2022r(1.8d, 1.0d, 0.0d, 0.0625d, 0.031248d), 11);
        f20190n = c2021q12;
        C2023s c2023s2 = AbstractC2014j.c;
        C2021q c2021q13 = new C2021q("SMPTE ST 2065-1:2012 ACES", new float[]{0.7347f, 0.2653f, 0.0f, 1.0f, 1.0E-4f, -0.077f}, c2023s2, 1.0d, -65504.0f, 65504.0f, 12);
        o = c2021q13;
        C2021q c2021q14 = new C2021q("Academy S-2014-004 ACEScg", new float[]{0.713f, 0.293f, 0.165f, 0.83f, 0.128f, 0.044f}, c2023s2, 1.0d, -65504.0f, 65504.0f, 13);
        f20191p = c2021q14;
        C2015k c2015k = new C2015k(14, 1, AbstractC2006b.f20176b, "Generic XYZ");
        f20192q = c2015k;
        long j3 = AbstractC2006b.c;
        C2015k c2015k2 = new C2015k(15, 0, j3, "Generic L*a*b*");
        f20193r = c2015k2;
        C2021q c2021q15 = new C2021q("None", fArr, c2023s, c2022r2, 16);
        f20194s = c2021q15;
        C2016l c2016l = new C2016l("Oklab", j3, 17);
        f20195t = c2016l;
        f20196u = new AbstractC2007c[]{c2021q, c2021q2, c2021q3, c2021q4, c2021q5, c2021q6, c2021q7, c2021q8, c2021q9, c2021q10, c2021q11, c2021q12, c2021q13, c2021q14, c2015k, c2015k2, c2021q15, c2016l};
    }
}
