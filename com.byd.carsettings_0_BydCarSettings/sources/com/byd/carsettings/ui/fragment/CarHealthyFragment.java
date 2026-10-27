package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import b.b.g.q.d.m;
import com.byd.carsettings.MainActivity;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.CarHealthyAdapter;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.widget.DepthPageTransformer;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public class CarHealthyFragment extends XFragment<b.b.g.n.m.a> implements b.b.o.b.a.a {
    public TabLayout m;
    public ViewPager2 n;
    public int[] o = {R.string.carhealthy_maintain_service, R.string.carhealthy_overhaul, R.string.vehiclehealth_vehicle_information};
    public CarHealthyAdapter p;
    public ViewPager2.OnPageChangeCallback q;

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
            CarHealthyFragment.this.m.selectTab(CarHealthyFragment.this.m.getTabAt(i));
            CarHealthyFragment.this.J(i);
            MainActivity.p[1] = i;
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
            CarHealthyFragment.this.N(true, tab);
            CarHealthyFragment.this.n.setCurrentItem(tab.getPosition(), false);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            CarHealthyFragment.this.N(false, tab);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (TabLayout) this.f3717c.findViewById(R.id.car_tablayout);
        this.n = (ViewPager2) this.f3717c.findViewById(R.id.car_viewpager);
    }

    public final void N(boolean z, TabLayout.Tab tab) {
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.text);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setTextColor(ContextCompat.getColor(getContext(), b.b.a.h.b.a(getContext(), z ? R.attr.carsetting_tabitem_selected : R.attr.carsetting_tabitem_unselected)));
        View viewFindViewById = tab.getCustomView().findViewById(R.id.view_label);
        if (z) {
            viewFindViewById.setScaleX(0.5f);
            viewFindViewById.setAlpha(0.0f);
            viewFindViewById.animate().scaleX(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getSharp()).start();
            viewFindViewById.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            return;
        }
        viewFindViewById.setScaleX(1.0f);
        viewFindViewById.setAlpha(1.0f);
        viewFindViewById.animate().scaleX(0.5f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getSharp()).start();
        viewFindViewById.animate().alpha(0.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
    }

    public final void O() {
        this.m.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new b());
        this.m.removeAllTabs();
        for (int i = 0; i < this.o.length; i++) {
            TabLayout.Tab customView = this.m.newTab().setCustomView(R.layout.tabitem_contenview);
            TextView textView = (TextView) customView.getCustomView().findViewById(R.id.text);
            N(false, customView);
            textView.setText(this.o[i]);
            this.m.addTab(customView);
        }
    }

    public final void P() {
        CarHealthyAdapter carHealthyAdapter = new CarHealthyAdapter(getChildFragmentManager(), getLifecycle());
        this.p = carHealthyAdapter;
        this.n.setAdapter(carHealthyAdapter);
        this.n.setUserInputEnabled(false);
        this.n.setPageTransformer(new DepthPageTransformer());
        if (this.n.getChildAt(0) != null) {
            this.n.getChildAt(0).setOverScrollMode(2);
        }
        ViewPager2 viewPager2 = this.n;
        a aVar = new a();
        this.q = aVar;
        viewPager2.registerOnPageChangeCallback(aVar);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.m.a newP() {
        return new b.b.g.n.m.a();
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.fragment_car_common;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.fragment_car_common;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.fragment_car_common_byd;
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        CarHealthyAdapter carHealthyAdapter = this.p;
        if (carHealthyAdapter != null) {
            carHealthyAdapter.a();
            this.p = null;
        }
        this.m.removeAllTabs();
        this.n.setAdapter(null);
        this.n.removeAllViews();
        this.m.clearOnTabSelectedListeners();
        this.n.unregisterOnPageChangeCallback(this.q);
        this.m = null;
        this.n = null;
        m.b();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        int[] iArr = MainActivity.p;
        if (iArr[1] == -1 || iArr[1] == this.g) {
            return;
        }
        int[] iArr2 = MainActivity.p;
        if (iArr2[0] == 5) {
            TabLayout tabLayout = this.m;
            tabLayout.selectTab(tabLayout.getTabAt(iArr2[1]), true);
            this.n.setCurrentItem(MainActivity.p[1]);
            MainActivity.p[1] = -1;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume-----subindex = " + i);
        if (i >= this.m.getTabCount() || i < 0) {
            return;
        }
        TabLayout tabLayout = this.m;
        tabLayout.selectTab(tabLayout.getTabAt(i), true);
        this.n.setCurrentItem(i);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void v() {
        super.v();
        b.b.m.e.i.b.b(".carhealthy.");
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
