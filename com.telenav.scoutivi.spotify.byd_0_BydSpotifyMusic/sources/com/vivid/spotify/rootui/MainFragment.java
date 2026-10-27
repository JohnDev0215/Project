package com.vivid.spotify.rootui;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.b0;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.vivid.spotify.MainActivity;
import com.vivid.spotify.MainViewModel;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.category.CategoryFragment;
import d4.f1;
import d4.t2;
import d4.t4;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainFragment extends k {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private MainUIViewModel f5419p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    f4.m f5420q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    BottomSheetBehavior f5421r0;

    class a extends BottomSheetBehavior.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ NowPlayingViewModel f5422a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5423b;

        a(NowPlayingViewModel nowPlayingViewModel, int i7) {
            this.f5422a = nowPlayingViewModel;
            this.f5423b = i7;
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void a(View view, float f7) {
            h3.i.b("MainFragment").g("onSlide :" + f7);
            ((f1) MainFragment.this.f5218j0).H.setAlpha(f7);
            ((f1) MainFragment.this.f5218j0).I.setElevation(((float) this.f5423b) * f7);
            float f8 = 1.0f - (f7 * 9.0f);
            if (f8 < 0.0f) {
                f8 = 0.0f;
            }
            ((f1) MainFragment.this.f5218j0).G.G().setAlpha(f8);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.g
        public void b(View view, int i7) {
            h3.i.b("MainFragment").g("onStateChanged :" + i7);
            if (i7 == 4) {
                if (this.f5422a.observeShowPage().e().booleanValue()) {
                    this.f5422a.showPage(false);
                }
            } else if (i7 == 3) {
                if (!this.f5422a.observeShowPage().e().booleanValue()) {
                    this.f5422a.showPage(true);
                }
                ((f1) MainFragment.this.f5218j0).H.setAlpha(1.0f);
            }
            if (i7 == 3) {
                if (MainFragment.this.f5419p0.isNowPlayingBarShow.q()) {
                    MainFragment.this.f5419p0.isNowPlayingBarShow.r(false);
                }
            } else {
                if (MainFragment.this.f5419p0.isNowPlayingBarShow.q()) {
                    return;
                }
                MainFragment.this.f5419p0.isNowPlayingBarShow.r(true);
            }
        }
    }

    public class b extends RecyclerView.g<C0060b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<x> f5425c;

        class a extends f.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f5427a;

            a(List list) {
                this.f5427a = list;
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean a(int i7, int i8) {
                return ((x) b.this.f5425c.get(i7)).equals(this.f5427a.get(i8));
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean b(int i7, int i8) {
                return i7 == i8;
            }

            @Override // androidx.recyclerview.widget.f.b
            public int d() {
                return this.f5427a.size();
            }

            @Override // androidx.recyclerview.widget.f.b
            public int e() {
                return b.this.f5425c.size();
            }
        }

        /* JADX INFO: renamed from: com.vivid.spotify.rootui.MainFragment$b$b, reason: collision with other inner class name */
        class C0060b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final t4 f5429t;

            C0060b(t4 t4Var) {
                super(t4Var.G());
                this.f5429t = t4Var;
            }
        }

        public b(List<x> list, int i7) {
            ArrayList arrayList = new ArrayList();
            this.f5425c = arrayList;
            if (list == null || list.isEmpty()) {
                return;
            }
            arrayList.clear();
            arrayList.addAll(list);
            MainFragment.this.f5419p0.selectedIndex.m(Integer.valueOf(i7));
        }

        private boolean F(View view) {
            NavController navControllerA = androidx.navigation.q.a(view);
            if (navControllerA != null) {
                boolean zV = navControllerA.v(com.vivid.spotify.t.rootGridFragment, false);
                if (!zV) {
                    zV = navControllerA.v(com.vivid.spotify.t.categoryFragment, false);
                }
                return !zV ? navControllerA.v(com.vivid.spotify.t.searchFragment, false) : zV;
            }
            h3.i.b("MainFragment").d("navController is null + e = " + MainFragment.this.f5419p0.selectedIndex.e(), new Object[0]);
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void G(com.vivid.spotify.common.ui.e eVar, Integer num) {
            eVar.f5234a.r(num.intValue() == this.f5425c.indexOf(eVar.a()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void H(com.vivid.spotify.common.ui.e eVar, View view) {
            if (MainFragment.this.j() instanceof MainActivity) {
                ((MainActivity) MainFragment.this.j()).exitNowPlayingPage(view);
            }
            int iIndexOf = this.f5425c.indexOf(eVar.a());
            h3.i.b("MainFragment").d("c = " + MainFragment.this.f5419p0.selectedIndex.e() + "   target = " + iIndexOf, new Object[0]);
            if (iIndexOf != MainFragment.this.f5419p0.selectedIndex.e().intValue()) {
                ((f1) MainFragment.this.f5218j0).M.j(iIndexOf, false);
                MainFragment.this.f5419p0.selectedIndex.m(Integer.valueOf(iIndexOf));
                return;
            }
            View viewFindViewById = ((f1) MainFragment.this.f5218j0).M.getChildAt(0).findViewById(com.vivid.spotify.t.nav_host_fragment);
            if (viewFindViewById == null) {
                h3.i.b("MainFragment").d("navHost is null + = " + MainFragment.this.f5419p0.selectedIndex.e(), new Object[0]);
                return;
            }
            boolean zF = F(viewFindViewById);
            h3.i.b("MainFragment").g("d = " + zF);
            if (zF) {
                return;
            }
            if (1 == this.f5425c.get(MainFragment.this.f5419p0.selectedIndex.e().intValue()).c()) {
                M(viewFindViewById);
            } else {
                N(viewFindViewById);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void I(ViewPager2 viewPager2) {
            ((CategoryFragment.c) viewPager2.getAdapter()).X(0);
            View childAt = ((f1) MainFragment.this.f5218j0).M.getChildAt(0);
            if (childAt != null) {
                L(childAt);
            } else {
                h3.i.b("MainFragment").b("viewPager view is null", new Object[0]);
            }
        }

        private void L(View view) {
            RecyclerView recyclerView = (RecyclerView) view.findViewWithTag("tag_list");
            if (recyclerView == null || !recyclerView.canScrollVertically(-1)) {
                return;
            }
            h3.i.b("MainFragment").g("top");
            recyclerView.r1(0);
        }

        private void M(View view) {
            View viewFindViewById = view.findViewById(com.vivid.spotify.t.search_layout);
            if (viewFindViewById == null) {
                h3.i.b("MainFragment").d("searchLayout is null", new Object[0]);
                return;
            }
            t2 t2Var = (t2) androidx.databinding.g.f(viewFindViewById);
            if (t2Var != null) {
                t2Var.d0().resetData();
            } else {
                h3.i.b("MainFragment").d("FragmentSearchBinding is null", new Object[0]);
            }
        }

        private void N(View view) {
            final ViewPager2 viewPager2 = (ViewPager2) view.findViewWithTag("tag_viewpager");
            if (viewPager2 != null) {
                viewPager2.j(0, false);
                viewPager2.post(new Runnable() { // from class: com.vivid.spotify.rootui.p
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5509e.I(viewPager2);
                    }
                });
                return;
            }
            h3.i.b("MainFragment").g("viewPager is null");
            View childAt = ((f1) MainFragment.this.f5218j0).M.getChildAt(0);
            if (childAt != null) {
                L(childAt);
            } else {
                h3.i.b("MainFragment").b("viewPager view is null", new Object[0]);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
        public void q(C0060b c0060b, int i7) {
            com.vivid.spotify.common.ui.e eVarD0 = c0060b.f5429t.d0();
            x xVar = this.f5425c.get(i7);
            eVarD0.b(xVar);
            MediaBrowserCompat.MediaItem mediaItemB = xVar.b();
            eVarD0.f5236c.r(i3.c.g(mediaItemB));
            Drawable drawableA = xVar.a(MainFragment.this.j());
            if (drawableA != null) {
                eVarD0.f5238e.r(drawableA);
            } else {
                eVarD0.f5237d.r(Uri.parse(i3.c.a(mediaItemB)));
            }
            eVarD0.f5234a.r(i7 == MainFragment.this.f5419p0.selectedIndex.e().intValue());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
        public C0060b s(ViewGroup viewGroup, int i7) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            final com.vivid.spotify.common.ui.e eVar = new com.vivid.spotify.common.ui.e();
            t4 t4VarE0 = t4.e0(layoutInflaterFrom, viewGroup, false);
            if (MainFragment.this.f5419p0.isRightDriver.q()) {
                t4VarE0.H.setBackground(y.b.b(MainFragment.this.N(), com.vivid.spotify.s.right_driver_root_menu_item_bg, MainFragment.this.s().getTheme()));
            } else {
                t4VarE0.H.setBackground(y.b.b(MainFragment.this.N(), com.vivid.spotify.s.root_menu_item_bg, MainFragment.this.s().getTheme()));
            }
            MainFragment.this.f5419p0.selectedIndex.f(MainFragment.this.X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.o
                @Override // androidx.lifecycle.u
                public final void c(Object obj) {
                    this.f5507a.G(eVar, (Integer) obj);
                }
            });
            t4VarE0.g0(eVar);
            t4VarE0.G().setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.rootui.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5505e.H(eVar, view);
                }
            });
            return new C0060b(t4VarE0);
        }

        public void O(List<x> list) {
            if (list.size() <= MainFragment.this.f5419p0.selectedIndex.e().intValue()) {
                MainFragment.this.f5419p0.selectedIndex.m(0);
            }
            f.c cVarA = androidx.recyclerview.widget.f.a(new a(list));
            this.f5425c.clear();
            this.f5425c.addAll(list);
            cVarA.e(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5425c.size();
        }
    }

    public class c extends FragmentStateAdapter {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final List<x> f5431k;

        class a extends f.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f5433a;

            a(List list) {
                this.f5433a = list;
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean a(int i7, int i8) {
                if (((x) c.this.f5431k.get(i7)).c() != ((x) this.f5433a.get(i8)).c()) {
                    return false;
                }
                if (((x) c.this.f5431k.get(i7)).c() == 0) {
                    return TextUtils.equals(((x) c.this.f5431k.get(i7)).b().j(), ((x) this.f5433a.get(i7)).b().j());
                }
                return true;
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean b(int i7, int i8) {
                return i7 == i8;
            }

            @Override // androidx.recyclerview.widget.f.b
            public int d() {
                return this.f5433a.size();
            }

            @Override // androidx.recyclerview.widget.f.b
            public int e() {
                return c.this.f5431k.size();
            }
        }

        public c(FragmentManager fragmentManager, androidx.lifecycle.i iVar, List<x> list) {
            super(fragmentManager, iVar);
            ArrayList arrayList = new ArrayList();
            this.f5431k = arrayList;
            if (list == null || list.isEmpty()) {
                return;
            }
            arrayList.addAll(list);
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public Fragment D(int i7) {
            return this.f5431k.get(i7).c() == 0 ? s.P1(this.f5431k.get(i7).b()) : s.O1(this.f5431k.get(i7).c());
        }

        public void W(List<x> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new a(list));
            this.f5431k.clear();
            this.f5431k.addAll(list);
            cVarA.e(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5431k.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return i7;
        }
    }

    private RecyclerView b2() {
        if (this.f5419p0.isRightDriver.q()) {
            T t6 = this.f5218j0;
            if (((f1) t6).J != null) {
                return ((f1) t6).J;
            }
        }
        return ((f1) this.f5218j0).L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c2(List list) {
        if (list != null) {
            RecyclerView.g adapter = ((f1) this.f5218j0).M.getAdapter();
            if (adapter != null) {
                ((c) adapter).W(list);
            } else {
                ((f1) this.f5218j0).M.setAdapter(new c(r(), X().a(), list));
            }
            if (list.isEmpty()) {
                ((f1) this.f5218j0).M.setAdapter(null);
                h3.i.b("MainFragment").b("a", new Object[0]);
            }
            RecyclerView.g adapter2 = b2().getAdapter();
            if (adapter2 != null) {
                ((b) adapter2).O(list);
                int size = list.size() != 0 ? list.size() : 0;
                if (size > 0) {
                    ((GridLayoutManager) b2().getLayoutManager()).f3(size);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d2(Boolean bool) {
        if (bool.booleanValue()) {
            if (this.f5421r0.e0() != 3) {
                this.f5421r0.v0(3);
            }
        } else if (this.f5421r0.e0() != 4) {
            this.f5421r0.v0(4);
        }
    }

    public static void e2(RecyclerView recyclerView, boolean z6) {
    }

    public static void f2(ImageView imageView, boolean z6) {
        int iA = u2.a.a(imageView.getContext(), com.vivid.spotify.p.tab_text_color_selected, -1);
        int iA2 = u2.a.a(imageView.getContext(), com.vivid.spotify.p.tab_text_color_unselected, -1);
        if (!z6) {
            iA = iA2;
        }
        imageView.setColorFilter(iA);
    }

    public static void g2(TextView textView, boolean z6) {
        int iA = u2.a.a(textView.getContext(), com.vivid.spotify.p.tab_text_color_selected, -1);
        int iA2 = u2.a.a(textView.getContext(), com.vivid.spotify.p.tab_text_color_unselected, -1);
        Typeface typefaceDefaultFromStyle = Typeface.defaultFromStyle(1);
        Typeface typefaceDefaultFromStyle2 = Typeface.defaultFromStyle(0);
        if (!z6) {
            iA = iA2;
        }
        textView.setTextColor(iA);
        if (!z6) {
            typefaceDefaultFromStyle = typefaceDefaultFromStyle2;
        }
        textView.setTypeface(typefaceDefaultFromStyle);
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        T t6 = this.f5218j0;
        if (t6 != 0) {
            ((f1) t6).M.setAdapter(null);
        }
        this.f5419p0.rootMenus.l(X());
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        this.f5419p0.refreshRoot(false);
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5218j0 = f1.d0(layoutInflater, viewGroup, false);
        this.f5419p0 = (MainUIViewModel) new b0(j()).a(MainUIViewModel.class);
        NowPlayingViewModel nowPlayingViewModel = (NowPlayingViewModel) new b0(j()).a(NowPlayingViewModel.class);
        ((f1) this.f5218j0).g0(nowPlayingViewModel);
        ((f1) this.f5218j0).h0(this.f5419p0);
        ((f1) this.f5218j0).f0((MainViewModel) new b0(j()).a(MainViewModel.class));
        b2().setAdapter(new b(this.f5419p0.rootMenus.e(), this.f5419p0.selectedIndex.e().intValue()));
        int size = (this.f5419p0.rootMenus.e() == null || this.f5419p0.rootMenus.e().size() == 0) ? 0 : this.f5419p0.rootMenus.e().size();
        if (size > 0) {
            ((GridLayoutManager) b2().getLayoutManager()).f3(size);
        }
        ((f1) this.f5218j0).M.setAdapter(new c(r(), X().a(), this.f5419p0.rootMenus.e()));
        ((f1) this.f5218j0).M.setUserInputEnabled(false);
        View childAt = ((f1) this.f5218j0).M.getChildAt(0);
        if (childAt != null && (childAt instanceof RecyclerView)) {
            ((RecyclerView) childAt).setItemViewCacheSize(5);
        }
        this.f5419p0.rootMenus.f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.m
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5504a.c2((List) obj);
            }
        });
        int dimensionPixelSize = N().getDimensionPixelSize(com.vivid.spotify.r.shadow_elevation);
        BottomSheetBehavior bottomSheetBehaviorC0 = BottomSheetBehavior.c0(((f1) this.f5218j0).K);
        this.f5421r0 = bottomSheetBehaviorC0;
        bottomSheetBehaviorC0.S(new a(nowPlayingViewModel, dimensionPixelSize));
        nowPlayingViewModel.observeShowPage().f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.l
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5503a.d2((Boolean) obj);
            }
        });
        if (nowPlayingViewModel.observeShowPage().e().booleanValue()) {
            ((f1) this.f5218j0).G.G().setAlpha(0.0f);
            ((f1) this.f5218j0).H.setAlpha(1.0f);
            ((f1) this.f5218j0).I.setElevation(dimensionPixelSize);
        } else {
            ((f1) this.f5218j0).I.setElevation(0.0f);
            ((f1) this.f5218j0).G.G().setAlpha(1.0f);
            ((f1) this.f5218j0).H.setAlpha(0.0f);
        }
        h3.i.b("MainFragment").g("mViewModel.b:" + this.f5419p0.isNowPlayingBarShow.q());
        ((f1) this.f5218j0).W(this);
        return ((f1) this.f5218j0).G();
    }
}
