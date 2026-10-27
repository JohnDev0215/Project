package g3;

import java.util.Set;

/* JADX INFO: renamed from: g3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1293b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f14816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f14817b;
    public Set c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f14818e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14819f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f14820g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f14821h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f14822i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f14823j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f14824k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14825m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f14826n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f14827p;

    public static int a(String str, String str2, int i7, int i10) {
        if (str.isEmpty() || i7 == -1) {
            return i7;
        }
        if (str.equals(str2)) {
            return i7 + i10;
        }
        return -1;
    }
}
