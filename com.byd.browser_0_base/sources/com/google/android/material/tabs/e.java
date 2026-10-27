package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TabLayoutMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public final TabLayout a;
    public final ViewPager2 b;
    public final b c;
    public RecyclerView.e<?> d;
    public boolean e;

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    public class a extends RecyclerView.g {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            e.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void b() {
            e.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void c(int i, int i2, Object obj) {
            e.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            e.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void e(int i, int i2) {
            e.this.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            e.this.b();
        }
    }

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    public interface b {
        void h(TabLayout.g gVar, int i);
    }

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    public static class c extends ViewPager2.g {
        public final WeakReference<TabLayout> a;
        public int c = 0;
        public int b = 0;

        public c(TabLayout tabLayout) {
            this.a = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void a(int i) {
            this.b = this.c;
            this.c = i;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void b(int i, int i2, float f) {
            TabLayout tabLayout = this.a.get();
            if (tabLayout != null) {
                int i3 = this.c;
                tabLayout.r(i3 != 2 || this.b == 1, i, (i3 == 2 && this.b == 0) ? false : true, f);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void c(int i) {
            TabLayout tabLayout = this.a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.c;
            tabLayout.p(tabLayout.k(i), i2 == 0 || (i2 == 2 && this.b == 0));
        }
    }

    public e(TabLayout tabLayout, ViewPager2 viewPager2, b bVar) {
        this.a = tabLayout;
        this.b = viewPager2;
        this.c = bVar;
    }

    public final void a() {
        if (this.e) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        ViewPager2 viewPager2 = this.b;
        RecyclerView.e<?> adapter = viewPager2.getAdapter();
        this.d = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.e = true;
        TabLayout tabLayout = this.a;
        viewPager2.g.a.add(new c(tabLayout));
        tabLayout.addOnTabSelectedListener((TabLayout.d) new d(viewPager2, true));
        this.d.A(new a());
        b();
        tabLayout.r(true, viewPager2.getCurrentItem(), true, 0.0f);
    }

    public final void b() {
        TabLayout tabLayout = this.a;
        tabLayout.n();
        RecyclerView.e<?> eVar = this.d;
        if (eVar != null) {
            int iK = eVar.k();
            for (int i = 0; i < iK; i++) {
                TabLayout.g gVarL = tabLayout.l();
                this.c.h(gVarL, i);
                tabLayout.d(gVarL, false);
            }
            if (iK > 0) {
                int iMin = Math.min(this.b.getCurrentItem(), tabLayout.getTabCount() - 1);
                if (iMin != tabLayout.getSelectedTabPosition()) {
                    tabLayout.p(tabLayout.k(iMin), true);
                }
            }
        }
    }

    /* JADX INFO: compiled from: TabLayoutMediator.java */
    public static class d implements TabLayout.d {
        public final ViewPager2 e;
        public final boolean f;

        public d(ViewPager2 viewPager2, boolean z) {
            this.e = viewPager2;
            this.f = z;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            this.e.c(gVar.e, this.f);
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
        }
    }
}
