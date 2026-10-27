package defpackage;

/* JADX INFO: compiled from: MerchantTrustMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wf1 {
    public boolean a;
    public boolean b;
    public long c;
    public long d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public long j;
    public long k;
    public long l;
    public long m;
    public int n;
    public double o;
    public String p;

    public final void a() {
        if (this.g || this.l == 0) {
            return;
        }
        this.g = true;
        oj2.Y((System.nanoTime() - this.l) / 1000000, "MerchantTrust.BottomSheet.DurationFullyOpened");
    }

    public final void b() {
        if (this.f || this.k == 0) {
            return;
        }
        this.f = true;
        oj2.Y((System.nanoTime() - this.k) / 1000000, "MerchantTrust.BottomSheet.DurationHalfOpened");
    }

    public final void c() {
        if (this.e || this.j == 0) {
            return;
        }
        this.e = true;
        oj2.Y((System.nanoTime() - this.j) / 1000000, "MerchantTrust.BottomSheet.DurationPeeked");
    }

    public final void d() {
        if (this.a || this.c == 0) {
            return;
        }
        this.a = true;
        oj2.Y((System.nanoTime() - this.c) / 1000000, "MerchantTrust.Message.DurationPrepared");
    }

    public final void e() {
        if (this.b || this.d == 0) {
            return;
        }
        this.b = true;
        oj2.Y((System.nanoTime() - this.d) / 1000000, "MerchantTrust.Message.DurationShown");
    }

    public final void f() {
        if (this.p != null) {
            long jNanoTime = (System.nanoTime() - this.m) / 1000000;
            oj2.T("MerchantTrust.MessageImpact.BrowsingTime", jNanoTime, 10L, 600000L, 50);
            oj2.T("MerchantTrust.MessageImpact.BrowsingTime" + g(), jNanoTime, 10L, 600000L, 50);
            oj2.Q(this.n, "MerchantTrust.MessageImpact.NavigationCount");
            oj2.Q(this.n, "MerchantTrust.MessageImpact.NavigationCount" + g());
        }
        this.m = 0L;
        this.n = 0;
        this.p = null;
        this.o = 0.0d;
    }

    public final String g() {
        double dRound = Math.round(this.o * 10.0d) / 10.0d;
        return ".Rating".concat(dRound >= 4.5d ? "AboveFourPointFive" : dRound >= 4.0d ? "AboveFour" : dRound >= 3.0d ? "AboveThree" : dRound >= 2.0d ? "AboveTwo" : "BelowTwo");
    }
}
