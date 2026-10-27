package defpackage;

import java.util.HashSet;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabUsageTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class g53 implements kr2, v80, hy1 {
    public final q2 h;
    public final y13 i;
    public a j;
    public b k;
    public final HashSet e = new HashSet();
    public int f = 0;
    public int g = 0;
    public boolean l = false;

    /* JADX INFO: compiled from: TabUsageTracker.java */
    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void g() {
            g53 g53Var = g53.this;
            g53Var.f = g53Var.i.p();
        }
    }

    /* JADX INFO: compiled from: TabUsageTracker.java */
    public class b extends h23 {
        public b(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            g53.this.e.add(Integer.valueOf(tab.getId()));
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            g53.this.g++;
        }
    }

    public g53(r2 r2Var, a23 a23Var) {
        this.i = a23Var;
        this.h = r2Var;
        r2Var.f(this);
    }

    @Override // defpackage.hy1
    public final void f() {
        y13 y13Var = this.i;
        if (y13Var.t()) {
            this.f = y13Var.p();
        } else {
            a aVar = new a();
            this.j = aVar;
            y13Var.m(aVar);
        }
        Tab tabAt = y13Var.z().getTabAt(y13Var.A());
        if (tabAt != null) {
            this.e.add(Integer.valueOf(tabAt.getId()));
        }
        this.k = new b(y13Var);
        this.l = true;
    }

    @Override // defpackage.kr2
    public final void l() {
        if (this.l) {
            int i = this.f + this.g;
            HashSet hashSet = this.e;
            oj2.Z(Math.round((hashSet.size() / i) * 100.0f), "Android.ActivityStop.PercentageOfTabsUsed");
            oj2.Q(hashSet.size(), "Android.ActivityStop.NumberOfTabsUsed");
            hashSet.clear();
            this.g = 0;
            this.f = 0;
            this.k.a();
            this.l = false;
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.h.e(this);
        this.i.l(this.j);
    }

    @Override // defpackage.hy1
    public final void h() {
    }

    @Override // defpackage.kr2
    public final void j() {
    }
}
