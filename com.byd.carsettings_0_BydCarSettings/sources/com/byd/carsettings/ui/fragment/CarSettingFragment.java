package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import b.b.g.d.h;
import b.b.g.d.k;
import b.b.g.j.b;
import b.b.g.r.a0;
import b.b.g.r.u;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.BydCsFragmentAdapter;
import com.byd.carsettings.ui.fragment.carsettings.CarSetSeatFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.widget.DepthPageTransformer;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CarSettingFragment extends XFragment<b.b.g.n.m.b> implements b.b.o.b.a.a {
    public TabLayout m;
    public ViewPager2 n;
    public List<Integer> o = new ArrayList();
    public int[] p = {R.string.vehiclesettings_tablayout_title_smart_chassis, R.string.vehiclesettings_tablayout_title_outside_light, R.string.vehiclesettings_tablayout_title_usecar_comfortably, R.string.vehiclesettings_tablayout_magic_cube, R.string.vehiclesettings_tablayout_compression_refrigerator_door_state, R.string.vehiclesettings_tablayout_title__hud, R.string.vehiclesettings_tablayout_title_meter, R.string.vehiclesettings_tablayout_title_air_conditioning, R.string.vehiclesettings_tablayout_title_carseat, R.string.vehiclesettings_tablayout_title_door_window_lock, R.string.vehiclesettings_tablayout_title_reminder, R.string.vehiclesettings_tablayout_title_trailer, R.string.vehiclesettings_tablayout_title_cockpit_perception, R.string.vehiclesettings_tablayout_title_rear_view_mirror, R.string.can_test_fragment};
    public String[] q = {"aiChassis", "carLights", "comDrive", "diBar", "freezer", "hud", "instrument", "AirConditioner", "seat", "doorLocks", NotificationCompat.CATEGORY_REMINDER, "trailer", "cockpit", "mirror"};
    public BydCsFragmentAdapter r;
    public ViewPager2.OnPageChangeCallback s;

    public class a implements TabLayout.OnTabSelectedListener {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            b.b.m.c.a.a(CarSettingFragment.this.f3715a, "--onTabSelected--position = " + tab.getPosition());
            CarSettingFragment.this.S(true, tab);
            CarSettingFragment.this.n.setCurrentItem(tab.getPosition(), false);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            CarSettingFragment.this.S(false, tab);
            b.b.a.h.a.b(this, " onTabUnselected : " + tab.getPosition());
            b.b.a.c.a.j(new b.b.a.c.a(10007, ((Integer) CarSettingFragment.this.o.get(tab.getPosition())).intValue()));
        }
    }

    public class b extends ViewPager2.OnPageChangeCallback {
        public b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int i, float f2, int i2) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            b.b.m.c.a.a(CarSettingFragment.this.f3715a, "--onPageSelected--position = " + i);
            CarSettingFragment.this.w();
            CarSettingFragment carSettingFragment = CarSettingFragment.this;
            carSettingFragment.J(((Integer) carSettingFragment.o.get(i)).intValue());
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2995a;

        public c(int i) {
            this.f2995a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CarSettingFragment.this.m != null) {
                CarSettingFragment.this.m.getTabAt(this.f2995a).select();
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2997a;

        public d(String str) {
            this.f2997a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iP = CarSettingFragment.this.P(this.f2997a);
            b.b.m.e.i.a.b();
            if (iP != -1) {
                BydLogHelper.logD(CarSettingFragment.this.f3715a, "mTabLayout  post : realIndex " + iP);
                if (CarSettingFragment.this.m == null || iP < 0 || iP >= CarSettingFragment.this.m.getTabCount()) {
                    return;
                }
                CarSettingFragment.this.m.selectTab(CarSettingFragment.this.m.getTabAt(iP));
                CarSettingFragment.this.n.setCurrentItem(iP);
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        U();
        EventBus.getDefault().register(this);
    }

    public final int P(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.q;
            if (i >= strArr.length) {
                return 0;
            }
            if (strArr[i].equals(str)) {
                for (int i2 = 0; i2 < this.o.size(); i2++) {
                    if (this.o.get(i2).intValue() == i) {
                        return i2;
                    }
                }
                return 0;
            }
            i++;
        }
    }

    public final void Q() {
        this.r = new BydCsFragmentAdapter(getChildFragmentManager(), getLifecycle(), this.o);
    }

    public final void R() {
        List<Integer> list = this.o;
        if (list != null && list.size() > 0) {
            this.o.clear();
        }
        int i = 0;
        while (true) {
            String[] strArr = this.q;
            if (i >= strArr.length) {
                break;
            }
            if (k.a(strArr[i]).a()) {
                this.o.add(Integer.valueOf(i));
            }
            i++;
        }
        if (u.b(getContext(), "car_set_first_in", 0) == 0) {
            X();
        }
    }

    public final void S(boolean z, TabLayout.Tab tab) {
        T(z, tab, false);
    }

    public final void T(boolean z, TabLayout.Tab tab, boolean z2) {
        b.b.m.c.a.a(this.f3715a, "----initSelectTab-----isSelected = " + z + ", position = " + tab.getPosition() + " isInit : " + z2);
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.text);
        if (z2) {
            textView.setTypeface(Typeface.defaultFromStyle(1));
        }
        textView.setTextColor(ContextCompat.getColor(getContext(), b.b.a.h.b.a(getContext(), z ? R.attr.carsetting_tabitem_selected : R.attr.carsetting_tabitem_unselected)));
        View viewFindViewById = tab.getCustomView().findViewById(R.id.view_label);
        if (z2) {
            viewFindViewById.setAlpha(z ? 1.0f : 0.0f);
            viewFindViewById.setScaleX(z ? 1.0f : 0.5f);
        }
        if (z2) {
            return;
        }
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

    public final void U() {
        this.m = (TabLayout) this.f3717c.findViewById(R.id.car_setting_tablayout);
        this.n = (ViewPager2) this.f3717c.findViewById(R.id.car_setting_viewpager);
    }

    public final void V() {
        this.n.setAdapter(this.r);
        this.n.setUserInputEnabled(false);
        this.n.setPageTransformer(new DepthPageTransformer());
        if (this.n.getChildAt(0) != null) {
            this.n.getChildAt(0).setOverScrollMode(2);
        }
        if (this.m.getTabCount() > 0) {
            this.m.removeAllTabs();
        }
        int i = 0;
        while (i < this.o.size()) {
            TabLayout.Tab customView = this.m.newTab().setCustomView(R.layout.tabitem_contenview);
            TextView textView = (TextView) customView.getCustomView().findViewById(R.id.text);
            T(i == 0, customView, true);
            textView.setText(this.p[this.o.get(i).intValue()]);
            this.m.addTab(customView);
            i++;
        }
        this.m.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new a());
        ViewPager2 viewPager2 = this.n;
        b bVar = new b();
        this.s = bVar;
        viewPager2.registerOnPageChangeCallback(bVar);
        a0.a(this.n);
        int iP = P(b.b.m.e.i.a.g("moduleKey"));
        TabLayout tabLayout = this.m;
        tabLayout.selectTab(tabLayout.getTabAt(iP));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.m.b newP() {
        return new b.b.g.n.m.b();
    }

    public void X() {
        if (u.b(b.b.a.a.a.b().a(), "car_set_first_in", 0) == 0) {
            u.c(b.b.a.a.a.b().a(), "car_set_first_in", 1);
            b.C0048b.a().j(!new h().a(), new b.b.g.m.b(4, 4));
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.fragment_rtl_car_setting;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.fragment_car_setting;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.fragment_right_car_setting;
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        this.n.setAdapter(null);
        this.n.unregisterOnPageChangeCallback(this.s);
        TabLayout tabLayout = this.m;
        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            this.m = null;
        }
        BydCsFragmentAdapter bydCsFragmentAdapter = this.r;
        if (bydCsFragmentAdapter != null) {
            bydCsFragmentAdapter.a();
            this.r = null;
        }
        List<Integer> list = this.o;
        if (list != null && list.size() > 0) {
            this.o.clear();
        }
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        BydLogHelper.logD(this.f3715a, "--checkSubindexOnResume--subindex = " + i);
        if (i >= 0) {
            String[] strArr = this.q;
            if (i >= strArr.length) {
                return;
            }
            int iP = P(strArr[i]);
            TabLayout tabLayout = this.m;
            tabLayout.selectTab(tabLayout.getTabAt(iP));
            this.n.setCurrentItem(iP);
            this.m.post(new c(iP));
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void u(int i, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            CarSetSeatFragment.m = str2;
        }
        BydLogHelper.logD(this.f3715a, " checkSubindexOnResume :  subindex : " + i + " moduleKey : " + str + " moduleAction : " + str2);
        this.m.post(new d(str));
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void v() {
        super.v();
        b.b.m.e.i.b.b(".fragment.carsettings.");
        b.b.m.e.i.b.d(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initData-----context = " + context);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        R();
        Q();
        V();
    }
}
