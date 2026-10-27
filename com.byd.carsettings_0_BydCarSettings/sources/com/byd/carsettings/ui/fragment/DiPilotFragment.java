package com.byd.carsettings.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import b.b.a.h.f;
import b.b.g.k.i;
import b.b.g.n.m.e;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.impl.StudyConfigImpl;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.DiPilotPagerAdapter;
import com.byd.carsettings.databinding.FragmentDipilotBinding;
import com.byd.carsettings.ui.dialog.adas.AdasCommonDialog;
import com.byd.dipilot.viewmodel.AdasViewModel;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.widget.DepthPageTransformer;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.google.android.material.tabs.TabLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DiPilotFragment extends XFragment<e> implements View.OnClickListener, b.b.k.a.a {
    public RelativeLayout m;
    public TabLayout n;
    public ViewPager2 o;
    public DiPilotPagerAdapter p;
    public ViewPager2.OnPageChangeCallback q;
    public int[] r = {R.string.dipilot_tablayout_title_driving_assist, R.string.dipilot_tablayout_title_active_safety, R.string.dipilot_tablayout_title_parking_assist};
    public FragmentDipilotBinding s;
    public int t;

    public class a implements TabLayout.OnTabSelectedListener {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            DiPilotFragment.this.P(true, tab);
            DiPilotFragment.this.o.setCurrentItem(tab.getPosition(), false);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            DiPilotFragment.this.P(false, tab);
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
            DiPilotFragment.this.n.selectTab(DiPilotFragment.this.n.getTabAt(i), true);
            DiPilotFragment.this.J(i);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.s = (FragmentDipilotBinding) DataBindingUtil.bind(this.f3717c);
        this.n = (TabLayout) this.f3717c.findViewById(R.id.adas_car_tablayout);
        this.o = (ViewPager2) this.f3717c.findViewById(R.id.adas_car_viewpager);
        RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.adas_parent_car_common);
        this.m = relativeLayout;
        relativeLayout.setOnClickListener(this);
    }

    public final void N(IStudyConfig iStudyConfig, int i) {
        this.p = new DiPilotPagerAdapter(getChildFragmentManager(), getLifecycle(), i);
        Q(iStudyConfig, i);
        R();
        int i2 = this.t;
        if (i2 > 0 && i2 < this.n.getTabCount()) {
            TabLayout tabLayout = this.n;
            tabLayout.selectTab(tabLayout.getTabAt(this.t), false);
            this.o.setCurrentItem(this.t);
        }
        this.t = 0;
    }

    public final void O(int i) {
        TabLayout.Tab customView = this.n.newTab().setCustomView(R.layout.tabitem_contenview);
        TextView textView = (TextView) customView.getCustomView().findViewById(R.id.text);
        P(false, customView);
        textView.setText(this.r[i]);
        this.n.addTab(customView);
    }

    public final void P(boolean z, TabLayout.Tab tab) {
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

    public final void Q(IStudyConfig iStudyConfig, int i) {
        BydLogHelper.logD("initTabs mTabLayout:" + this.n);
        this.n.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new a());
        this.n.removeAllTabs();
        if (1 == i) {
            this.n.setTabMode(1);
        }
        for (int i2 = 0; i2 < i; i2++) {
            O(iStudyConfig.i(i2));
        }
    }

    public final void R() {
        this.o.setAdapter(this.p);
        this.o.setUserInputEnabled(false);
        this.o.setPageTransformer(new DepthPageTransformer());
        if (this.o.getChildAt(0) != null) {
            this.o.getChildAt(0).setOverScrollMode(2);
        }
        ViewPager2 viewPager2 = this.o;
        b bVar = new b();
        this.q = bVar;
        viewPager2.registerOnPageChangeCallback(bVar);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public e newP() {
        return new e();
    }

    public final void T(boolean z, RelativeLayout.LayoutParams layoutParams) {
        if (!z) {
            layoutParams.width = -2;
        }
        setViewGroupRtl(this.n, false);
    }

    public final void U(int i, b.b.k.a.a aVar) {
        NoConfigDialogModel noConfigDialogModel = (NoConfigDialogModel) new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NoConfigDialogModel.class);
        if (noConfigDialogModel.e() != null) {
            return;
        }
        AdasCommonDialog adasCommonDialog = new AdasCommonDialog();
        noConfigDialogModel.i(0, i, 0, aVar, adasCommonDialog, null);
        noConfigDialogModel.q(false);
        noConfigDialogModel.r(true);
        noConfigDialogModel.s((byte) 0);
        noConfigDialogModel.p(false);
        adasCommonDialog.show(requireActivity().getSupportFragmentManager(), getClass().getCanonicalName());
    }

    @Override // b.b.k.a.a
    public void f(boolean z) {
        FragmentDipilotBinding fragmentDipilotBinding = this.s;
        if (fragmentDipilotBinding != null && fragmentDipilotBinding.b().c()) {
            b.b.g.n.q.e.j().O(1);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_dipilot;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.s == null || f.b(view, 500L)) {
            return;
        }
        AdasViewModel adasViewModelB = this.s.b();
        if (R.id.adas_parent_car_common == view.getId() && adasViewModelB.c()) {
            U(R.string.adas_itac_tip, this);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.m.setOnClickListener(null);
        if (this.p != null) {
            this.p = null;
        }
        this.n.removeAllTabs();
        this.o.setAdapter(null);
        this.o.removeAllViews();
        this.n.clearOnTabSelectedListeners();
        this.o.unregisterOnPageChangeCallback(this.q);
        this.n = null;
        this.o = null;
        this.m = null;
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.m, true);
        T(z, (RelativeLayout.LayoutParams) this.f3717c.findViewById(R.id.adas_damping_horizontal_scrollview).getLayoutParams());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(StudyConfigImpl.InitCompleteMsg initCompleteMsg) {
        BydLogHelper.logW(this.f3715a, "onMessageEvent load complete, show UI");
        IStudyConfig iStudyConfigF = Router.b().f();
        N(iStudyConfigF, iStudyConfigF.g());
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f3717c.findViewById(R.id.adas_damping_horizontal_scrollview).getLayoutParams();
        layoutParams.setMarginEnd(layoutParams.getMarginStart());
        layoutParams.setMarginStart(0);
        T(z, layoutParams);
        layoutParams.addRule(9, -1);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume-----subindex = " + i);
        this.t = 0;
        int tabCount = this.n.getTabCount();
        if (i < 0 || i >= tabCount) {
            if (tabCount <= 0) {
                this.t = i;
            }
        } else {
            TabLayout tabLayout = this.n;
            tabLayout.selectTab(tabLayout.getTabAt(i), false);
            this.o.setCurrentItem(i);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void v() {
        super.v();
        b.b.m.e.i.b.b(".dipilot.");
        b.b.m.e.i.b.d(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        b.b.m.c.a.a(this.f3715a, "----initData-----context = " + context);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        AdasViewModel adasViewModel = (AdasViewModel) new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdasViewModel.class);
        adasViewModel.e(b.b.g.n.q.e.j().z());
        this.s.c(adasViewModel);
        if (adasViewModel.b() == null) {
            adasViewModel.d(new i());
        }
        Router.b().d().b();
        BydLogHelper.logI(this.f3715a, "initLazyData!");
        IStudyConfig iStudyConfigF = Router.b().f();
        if (iStudyConfigF.a()) {
            N(iStudyConfigF, iStudyConfigF.g());
            BydLogHelper.logW(this.f3715a, "initLazyData show UI immediately");
        } else {
            EventBus.getDefault().register(this);
            iStudyConfigF.b();
            BydLogHelper.logW(this.f3715a, "initLazyData waiting for load complete");
        }
    }
}
