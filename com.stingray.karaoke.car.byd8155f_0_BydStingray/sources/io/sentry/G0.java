package io.sentry;

import h2.AbstractC1347d;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class G0 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final ArrayList f15838A;
    public String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f15839C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f15840D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f15841E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public String f15842F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f15843G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public String f15844H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public String f15845I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String f15846J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public Date f15847K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final HashMap f15848L;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public ConcurrentHashMap f15850N;
    public final File l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Callable f15851m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15852n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f15853p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f15854q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f15855r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f15856s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f15857t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f15858u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f15859v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f15861x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f15862y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f15863z;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f15860w = new ArrayList();

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f15849M = null;
    public String o = Locale.getDefault().toString();

    public G0(File file, Date date, ArrayList arrayList, String str, String str2, String str3, String str4, int i7, String str5, Callable callable, String str6, String str7, String str8, Boolean bool, String str9, String str10, String str11, String str12, String str13, HashMap map) {
        this.l = file;
        this.f15847K = date;
        this.f15859v = str5;
        this.f15851m = callable;
        this.f15852n = i7;
        this.f15853p = str6 != null ? str6 : "";
        this.f15854q = str7 != null ? str7 : "";
        this.f15857t = str8 != null ? str8 : "";
        this.f15858u = bool != null ? bool.booleanValue() : false;
        this.f15861x = str9 != null ? str9 : "0";
        this.f15855r = "";
        this.f15856s = "android";
        this.f15862y = "android";
        this.f15863z = str10 != null ? str10 : "";
        this.f15838A = arrayList;
        this.B = str.isEmpty() ? "unknown" : str;
        this.f15839C = str4;
        this.f15840D = "";
        this.f15841E = str11 != null ? str11 : "";
        this.f15842F = str2;
        this.f15843G = str3;
        this.f15844H = UUID.randomUUID().toString();
        this.f15845I = str12 != null ? str12 : "production";
        this.f15846J = str13;
        if (!str13.equals("normal") && !this.f15846J.equals("timeout") && !this.f15846J.equals("backgrounded")) {
            this.f15846J = "normal";
        }
        this.f15848L = map;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("android_api_level");
        c1489n1.I(k10, Integer.valueOf(this.f15852n));
        c1489n1.w("device_locale");
        c1489n1.I(k10, this.o);
        c1489n1.w("device_manufacturer");
        c1489n1.L(this.f15853p);
        c1489n1.w("device_model");
        c1489n1.L(this.f15854q);
        c1489n1.w("device_os_build_number");
        c1489n1.L(this.f15855r);
        c1489n1.w("device_os_name");
        c1489n1.L(this.f15856s);
        c1489n1.w("device_os_version");
        c1489n1.L(this.f15857t);
        c1489n1.w("device_is_emulator");
        c1489n1.M(this.f15858u);
        c1489n1.w("architecture");
        c1489n1.I(k10, this.f15859v);
        c1489n1.w("device_cpu_frequencies");
        c1489n1.I(k10, this.f15860w);
        c1489n1.w("device_physical_memory_bytes");
        c1489n1.L(this.f15861x);
        c1489n1.w("platform");
        c1489n1.L(this.f15862y);
        c1489n1.w("build_id");
        c1489n1.L(this.f15863z);
        c1489n1.w("transaction_name");
        c1489n1.L(this.B);
        c1489n1.w("duration_ns");
        c1489n1.L(this.f15839C);
        c1489n1.w("version_name");
        c1489n1.L(this.f15841E);
        c1489n1.w("version_code");
        c1489n1.L(this.f15840D);
        ArrayList arrayList = this.f15838A;
        if (!arrayList.isEmpty()) {
            c1489n1.w("transactions");
            c1489n1.I(k10, arrayList);
        }
        c1489n1.w("transaction_id");
        c1489n1.L(this.f15842F);
        c1489n1.w("trace_id");
        c1489n1.L(this.f15843G);
        c1489n1.w("profile_id");
        c1489n1.L(this.f15844H);
        c1489n1.w("environment");
        c1489n1.L(this.f15845I);
        c1489n1.w("truncation_reason");
        c1489n1.L(this.f15846J);
        if (this.f15849M != null) {
            c1489n1.w("sampled_profile");
            c1489n1.L(this.f15849M);
        }
        c1489n1.w("measurements");
        c1489n1.I(k10, this.f15848L);
        c1489n1.w("timestamp");
        c1489n1.I(k10, this.f15847K);
        ConcurrentHashMap concurrentHashMap = this.f15850N;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f15850N, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
