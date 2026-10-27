package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TabLayoutMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TabLayout f4773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ViewPager2 f4774b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f4775c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f4776d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b f4777e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private RecyclerView.g<?> f4778f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4779g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private c f4780h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TabLayout.d f4781i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private RecyclerView.i f4782j;

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    private class a extends RecyclerView.i {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            d.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void b(int i7, int i8) {
            d.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i7, int i8, Object obj) {
            d.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i7, int i8) {
            d.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void e(int i7, int i8, int i9) {
            d.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void f(int i7, int i8) {
            d.this.b();
        }
    }

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    public interface b {
        void a(TabLayout.f fVar, int i7);
    }

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    private static class c extends ViewPager2.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<TabLayout> f4784a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4785b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4786c;

        c(TabLayout tabLayout) {
            this.f4784a = new WeakReference<>(tabLayout);
            d();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void a(int i7) {
            this.f4785b = this.f4786c;
            this.f4786c = i7;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void b(int i7, float f7, int i8) {
            TabLayout tabLayout = this.f4784a.get();
            if (tabLayout != null) {
                int i9 = this.f4786c;
                tabLayout.K(i7, f7, i9 != 2 || this.f4785b == 1, (i9 == 2 && this.f4785b == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i7) {
            TabLayout tabLayout = this.f4784a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i7 || i7 >= tabLayout.getTabCount()) {
                return;
            }
            int i8 = this.f4786c;
            tabLayout.H(tabLayout.x(i7), i8 == 0 || (i8 == 2 && this.f4785b == 0));
        }

        void d() {
            this.f4786c = 0;
            this.f4785b = 0;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.tabs.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TabLayoutMediator.java */
    private static class C0051d implements TabLayout.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ViewPager2 f4787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f4788b;

        C0051d(ViewPager2 viewPager2, boolean z6) {
            this.f4787a = viewPager2;
            this.f4788b = z6;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.f fVar) {
            this.f4787a.j(fVar.g(), this.f4788b);
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.f fVar) {
        }
    }

    public d(TabLayout tabLayout, ViewPager2 viewPager2, boolean z6, boolean z7, b bVar) {
        this.f4773a = tabLayout;
        this.f4774b = viewPager2;
        this.f4775c = z6;
        this.f4776d = z7;
        this.f4777e = bVar;
    }

    public void a() {
        if (this.f4779g) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        RecyclerView.g<?> adapter = this.f4774b.getAdapter();
        this.f4778f = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.f4779g = true;
        c cVar = new c(this.f4773a);
        this.f4780h = cVar;
        this.f4774b.g(cVar);
        C0051d c0051d = new C0051d(this.f4774b, this.f4776d);
        this.f4781i = c0051d;
        this.f4773a.d(c0051d);
        if (this.f4775c) {
            a aVar = new a();
            this.f4782j = aVar;
            this.f4778f.y(aVar);
        }
        b();
        this.f4773a.J(this.f4774b.getCurrentItem(), 0.0f, true);
    }

    void b() {
        this.f4773a.C();
        RecyclerView.g<?> gVar = this.f4778f;
        if (gVar != null) {
            int iE = gVar.e();
            for (int i7 = 0; i7 < iE; i7++) {
                TabLayout.f fVarZ = this.f4773a.z();
                this.f4777e.a(fVarZ, i7);
                this.f4773a.g(fVarZ, false);
            }
            if (iE > 0) {
                int iMin = Math.min(this.f4774b.getCurrentItem(), this.f4773a.getTabCount() - 1);
                if (iMin != this.f4773a.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.f4773a;
                    tabLayout.G(tabLayout.x(iMin));
                }
            }
        }
    }
}
