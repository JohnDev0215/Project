package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import b.b.g.n.m.f;
import b.b.g.n.n.d;
import b.b.g.q.d.m;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.NewEnergyAdapter;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.widget.DepthPageTransformer;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public class NewEnergyFragment extends XFragment<f> implements b.b.o.b.a.a {
    public TabLayout m;
    public ViewPager2 n;
    public int[] o;
    public NewEnergyAdapter p;
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
            NewEnergyFragment.this.m.selectTab(NewEnergyFragment.this.m.getTabAt(i));
            NewEnergyFragment.this.J(i);
            b.b.m.c.a.a(NewEnergyFragment.this.f3715a, "[Memory]----[NewEnergyFragment]----onPageSelected-----");
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
            b.b.m.c.a.a(NewEnergyFragment.this.f3715a, "[Memory]----[NewEnergyFragment]----onTabSelected-----" + NewEnergyFragment.this.isResumed() + "----" + tab.getPosition());
            NewEnergyFragment.this.N(true, tab);
            NewEnergyFragment.this.n.setCurrentItem(tab.getPosition(), false);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            NewEnergyFragment.this.N(false, tab);
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
        if (this.m.getTabCount() == this.o.length) {
            return;
        }
        this.m.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new b());
        this.m.removeAllTabs();
        for (int i = 0; i < this.o.length; i++) {
            TabLayout.Tab customView = this.m.newTab().setCustomView(R.layout.tabitem_contenview);
            TextView textView = (TextView) customView.getCustomView().findViewById(R.id.text);
            N(false, customView);
            textView.setText(this.o[i]);
            this.m.addTab(customView);
        }
        this.m.invalidate();
    }

    public final void P() {
        NewEnergyAdapter newEnergyAdapter = new NewEnergyAdapter(getChildFragmentManager(), getLifecycle(), this.o.length);
        this.p = newEnergyAdapter;
        this.n.setAdapter(newEnergyAdapter);
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
    public f newP() {
        return new f();
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
        NewEnergyAdapter newEnergyAdapter = this.p;
        if (newEnergyAdapter != null) {
            newEnergyAdapter.a();
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
        b.b.m.e.i.b.b(".newenergy.");
        b.b.m.e.i.b.d(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initData-----context = " + context);
        d dVar = new d();
        dVar.g();
        boolean zI = dVar.i();
        b.b.m.c.a.d(this.f3715a, "NewEnergyFragment---getSupportiTAC ---" + zI);
        if (zI) {
            this.o = new int[]{R.string.newenergy_tablayout_label_itac, R.string.newenergy_tablayout_label_energy_management, R.string.newenergy_tablayout_label_charge_and_discharge, R.string.newenergy_tablayout_label_energy_consumption};
        } else {
            this.o = new int[]{R.string.newenergy_tablayout_label_energy_management, R.string.newenergy_tablayout_label_charge_and_discharge, R.string.newenergy_tablayout_label_energy_consumption};
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        O();
        P();
        b.b.m.c.a.a(this.f3715a, "----initData-----initLazyData");
    }
}
