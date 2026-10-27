package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import b.b.g.n.m.d;
import b.b.g.q.d.m;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.DiLinkPagerAdapter;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.widget.DepthPageTransformer;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DiLinkFragment extends XFragment<d> {
    public ViewGroup m;
    public TabLayout n;
    public ViewPager2 o;
    public final int[] p = {R.string.link_dilink, R.string.sound_dilink, R.string.display_dilink, R.string.version_manage_dilink, R.string.system_dilink, R.string.app_manage_dilink};
    public DiLinkPagerAdapter q;
    public ViewPager2.OnPageChangeCallback r;

    public class a extends ViewPager2.OnPageChangeCallback {
        public a() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int i, float f2, int i2) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            DiLinkFragment.this.n.selectTab(DiLinkFragment.this.n.getTabAt(i));
            DiLinkFragment.this.J(i);
        }
    }

    public class b implements TabLayout.OnTabSelectedListener {
        public b() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            DiLinkFragment.this.N(true, tab);
            DiLinkFragment.this.o.setCurrentItem(tab.getPosition(), false);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            DiLinkFragment.this.N(false, tab);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3003a;

        public c(boolean z) {
            this.f3003a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.g.j.a.d(1, 3);
            DiLinkFragment.this.n.getTabAt(3).getCustomView().findViewById(R.id.iv_point_tab_item_red_dot).setVisibility(this.f3003a ? 0 : 8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ((d) mo7getP()).f();
        this.m = (ViewGroup) this.f3717c.findViewById(R.id.parent_car_common);
        this.n = (TabLayout) this.f3717c.findViewById(R.id.car_tablayout);
        this.o = (ViewPager2) this.f3717c.findViewById(R.id.car_viewpager);
    }

    public final void N(boolean z, TabLayout.Tab tab) {
        b.b.m.c.a.a(this.f3715a, "--initSelectTab--isSelected: " + z + ", tab.Position: " + tab.getPosition());
        ((TextView) tab.getCustomView().findViewById(R.id.text)).setTextColor(ContextCompat.getColor(getContext(), b.b.a.h.b.a(getContext(), z ? R.attr.carsetting_tabitem_selected : R.attr.carsetting_tabitem_unselected)));
        View viewFindViewById = tab.getCustomView().findViewById(R.id.view_label);
        if (z) {
            viewFindViewById.setScaleX(0.5f);
            viewFindViewById.setAlpha(0.0f);
            viewFindViewById.animate().scaleX(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getSharp()).start();
            viewFindViewById.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        } else {
            viewFindViewById.setScaleX(1.0f);
            viewFindViewById.setAlpha(1.0f);
            viewFindViewById.animate().scaleX(0.5f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getSharp()).start();
            viewFindViewById.animate().alpha(0.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        }
        viewFindViewById.setVisibility(z ? 0 : 4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void O() {
        this.n.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new b());
        this.n.removeAllTabs();
        for (int i = 0; i < this.p.length; i++) {
            TabLayout.Tab customView = this.n.newTab().setCustomView(R.layout.tabitem_contenview);
            TextView textView = (TextView) customView.getCustomView().findViewById(R.id.text);
            N(false, customView);
            textView.setText(this.p[i]);
            this.n.addTab(customView);
        }
        b.b.g.j.a.d(1, 3);
        this.n.getTabAt(3).getCustomView().findViewById(R.id.iv_point_tab_item_red_dot).setVisibility(((d) mo7getP()).e() ? 0 : 8);
    }

    public final void P() {
        DiLinkPagerAdapter diLinkPagerAdapter = new DiLinkPagerAdapter(getChildFragmentManager(), getLifecycle());
        this.q = diLinkPagerAdapter;
        this.o.setAdapter(diLinkPagerAdapter);
        this.o.setUserInputEnabled(false);
        this.o.setPageTransformer(new DepthPageTransformer());
        if (this.o.getChildAt(0) != null) {
            this.o.getChildAt(0).setOverScrollMode(2);
        }
        ViewPager2 viewPager2 = this.o;
        a aVar = new a();
        this.r = aVar;
        viewPager2.registerOnPageChangeCallback(aVar);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public d newP() {
        return new d();
    }

    public void R(boolean z) {
        b.b.m.c.a.a(this.f3715a, "--onUpdateFlag--flag = " + z);
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c(z));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_dilink;
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        DiLinkPagerAdapter diLinkPagerAdapter = this.q;
        if (diLinkPagerAdapter != null) {
            diLinkPagerAdapter.a();
            this.q = null;
        }
        TabLayout tabLayout = this.n;
        if (tabLayout != null) {
            int tabCount = tabLayout.getTabCount();
            if (tabCount > 0) {
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tabAt = this.n.getTabAt(i);
                    if (tabAt != null && tabAt.getCustomView() != null) {
                        tabAt.getCustomView().setOnTouchListener(null);
                    }
                }
            }
            this.n.removeAllTabs();
            this.n.clearOnTabSelectedListeners();
        }
        ViewPager2 viewPager2 = this.o;
        if (viewPager2 != null) {
            viewPager2.setAdapter(null);
            this.o.removeAllViews();
            this.o.unregisterOnPageChangeCallback(this.r);
        }
        this.n = null;
        this.o = null;
        m.b();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        b.b.m.c.a.a(this.f3715a, "----onLtrRudderR-----land: " + z);
        setViewGroupRtl(this.m, true);
        setViewGroupRtl(this.n, false);
        if (z) {
            return;
        }
        setViewGroupRtl(this.o, false);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.m, false);
        setViewGroupRtl(this.n, false);
        if (z) {
            return;
        }
        setViewGroupRtl(this.o, true);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume-----subindex = " + i);
        if (i >= this.n.getTabCount() || i < 0) {
            return;
        }
        TabLayout tabLayout = this.n;
        tabLayout.selectTab(tabLayout.getTabAt(i), true);
        this.o.setCurrentItem(i);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void v() {
        super.v();
        b.b.m.e.i.b.b(".dilink.");
        b.b.m.e.i.b.d(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initData-----context = " + context);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        O();
        P();
    }
}
