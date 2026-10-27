package com.vivid.spotify.rootui.category;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.b0;
import androidx.lifecycle.u;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.d;
import com.vivid.spotify.common.ui.BaseTabLayout;
import d4.g0;
import d4.l0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CategoryFragment extends r {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final String f5442p0 = "CategoryFragment";

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private CategoryViewModel f5443q0;

    class a extends androidx.constraintlayout.motion.widget.s {
        a() {
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
        public void c(MotionLayout motionLayout, int i7, int i8, float f7) {
            CategoryFragment.this.f5443q0.progress = motionLayout.getProgress();
        }

        @Override // androidx.constraintlayout.motion.widget.s, androidx.constraintlayout.motion.widget.MotionLayout.j
        public void d(MotionLayout motionLayout, int i7) {
            CategoryFragment.this.f5443q0.progress = motionLayout.getProgress();
        }
    }

    class b implements TabLayout.d {
        b() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.f fVar) {
            CategoryFragment.this.j2();
        }
    }

    public static class c extends FragmentStateAdapter {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final List<MediaBrowserCompat.MediaItem> f5446k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private final Map<Integer, Boolean> f5447l;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ androidx.viewpager2.adapter.a f5448a;

            a(androidx.viewpager2.adapter.a aVar) {
                this.f5448a = aVar;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
                if (((ViewGroup) this.f5448a.f2857a).getChildCount() > 0) {
                    this.f5448a.f2857a.removeOnLayoutChangeListener(this);
                    l0 l0Var = (l0) androidx.databinding.g.f(((ViewGroup) this.f5448a.f2857a).getChildAt(0));
                    l0Var.G.removeAllViews();
                    l0Var.G.requestLayout();
                }
            }
        }

        public c(FragmentManager fragmentManager, androidx.lifecycle.i iVar) {
            super(fragmentManager, iVar);
            this.f5446k = new ArrayList();
            this.f5447l = new HashMap();
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public Fragment D(int i7) {
            return i.g2(this.f5446k.get(i7));
        }

        public void V(List<MediaBrowserCompat.MediaItem> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new v3.b(this.f5446k, list));
            this.f5446k.clear();
            this.f5446k.addAll(list);
            cVarA.e(this);
            for (int i7 = 0; i7 < this.f5446k.size(); i7++) {
                this.f5447l.put(Integer.valueOf(i7), Boolean.FALSE);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public void r(androidx.viewpager2.adapter.a aVar, int i7, List<Object> list) {
            super.r(aVar, i7, list);
            if (this.f5447l.get(Integer.valueOf(i7)).booleanValue()) {
                aVar.f2857a.addOnLayoutChangeListener(new a(aVar));
            }
        }

        public void X(int i7) {
            int i8 = 0;
            while (i8 < this.f5446k.size()) {
                this.f5447l.put(Integer.valueOf(i8), Boolean.valueOf(i8 != i7));
                i8++;
            }
            j();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5446k.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f2() {
        int selectedTabPosition = ((g0) this.f5218j0).L.getSelectedTabPosition();
        BaseTabLayout baseTabLayout = ((g0) this.f5218j0).L;
        if (selectedTabPosition == -1) {
            selectedTabPosition = 0;
        }
        baseTabLayout.J(selectedTabPosition, 0.0f, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g2(List list, TabLayout.f fVar, int i7) {
        fVar.r(i3.c.g((MediaBrowserCompat.MediaItem) list.get(i7)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h2(TabLayout tabLayout) {
        int selectedTabPosition = tabLayout.getSelectedTabPosition();
        if (selectedTabPosition == -1) {
            selectedTabPosition = 0;
        }
        tabLayout.J(selectedTabPosition, 0.0f, true);
    }

    public static void i2(final TabLayout tabLayout, final List<MediaBrowserCompat.MediaItem> list, ViewPager2 viewPager2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        new com.google.android.material.tabs.d(tabLayout, viewPager2, false, false, new d.b() { // from class: com.vivid.spotify.rootui.category.b
            @Override // com.google.android.material.tabs.d.b
            public final void a(TabLayout.f fVar, int i7) {
                CategoryFragment.g2(list, fVar, i7);
            }
        }).a();
        int dimension = (int) tabLayout.getResources().getDimension(com.vivid.spotify.r.category_grid_rv_margin_start);
        for (int i7 = 0; i7 < tabLayout.getTabCount(); i7++) {
            View childAt = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            marginLayoutParams.setMarginEnd(dimension);
            childAt.setLayoutParams(marginLayoutParams);
        }
        if (list.isEmpty()) {
            return;
        }
        tabLayout.post(new Runnable() { // from class: com.vivid.spotify.rootui.category.c
            @Override // java.lang.Runnable
            public final void run() {
                CategoryFragment.h2(tabLayout);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j2() {
        View viewFindViewWithTag = ((g0) this.f5218j0).G.findViewWithTag("tag_list");
        if (viewFindViewWithTag instanceof RecyclerView) {
            ((RecyclerView) viewFindViewWithTag).j1(0);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        T t6 = this.f5218j0;
        if (t6 != 0) {
            ((g0) t6).G.setAdapter(null);
        }
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        ((g0) this.f5218j0).K.setProgress(this.f5443q0.progress);
        ((g0) this.f5218j0).L.post(new Runnable() { // from class: com.vivid.spotify.rootui.category.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f5456e.f2();
            }
        });
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final c cVar = new c(r(), X().a());
        CategoryViewModel categoryViewModel = (CategoryViewModel) new b0(this).a(CategoryViewModel.class);
        this.f5443q0 = categoryViewModel;
        categoryViewModel.observerCategory().f(X(), new u() { // from class: com.vivid.spotify.rootui.category.a
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                cVar.V((List) obj);
            }
        });
        Bundle bundleQ = q();
        if (bundleQ != null) {
            if (this.f5443q0.setParentItem((MediaBrowserCompat.MediaItem) bundleQ.getParcelable("media_item"))) {
                this.f5443q0.load();
            }
        }
        g0 g0VarD0 = g0.d0(layoutInflater, viewGroup, false);
        this.f5218j0 = g0VarD0;
        g0VarD0.g0(this.f5443q0);
        ((g0) this.f5218j0).f0(cVar);
        ((g0) this.f5218j0).W(X());
        ((g0) this.f5218j0).G.setTag("tag_viewpager");
        ((g0) this.f5218j0).K.setTag("tag_root");
        ((g0) this.f5218j0).K.Y(new a());
        View childAt = ((g0) this.f5218j0).G.getChildAt(0);
        if (childAt != null && (childAt instanceof RecyclerView)) {
            ((RecyclerView) childAt).setItemViewCacheSize(4);
        }
        ((g0) this.f5218j0).L.d(new b());
        return ((g0) this.f5218j0).G();
    }
}
