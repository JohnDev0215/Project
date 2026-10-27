package com.byd.hvac.view.accontrol;

import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import b.a.a.g.d;
import com.byd.hvac.AcPagerAdapter;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseFragment;
import com.byd.hvac.model.AcControlViewModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AcControlFragment extends BaseFragment<d> implements b.a.a.d.a {
    public TabLayout g;
    public volatile ViewPager2 h;
    public ArrayList<String> i = new ArrayList<>();
    public volatile AcPagerAdapter j;
    public int k;
    public AcControlViewModel l;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements TabLayout.OnTabSelectedListener {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            AcControlFragment.this.F0(true, tab);
            if (AcControlFragment.this.j != null) {
                int position = tab.getPosition();
                BydLogHelper.logD("onTabSelected position = " + position);
                if (AcControlFragment.this.l != null) {
                    AcControlFragment.this.l.b(position);
                }
                AcControlFragment.this.h.setCurrentItem(position, false);
                ArrayList<Fragment> arrayListB = AcControlFragment.this.j.b();
                if (position < arrayListB.size()) {
                    for (int i = 0; i < arrayListB.size(); i++) {
                        Fragment fragment = arrayListB.get(position);
                        if (fragment != null) {
                            fragment.onResume();
                            return;
                        }
                    }
                }
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            AcControlFragment.this.F0(false, tab);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void H0(TabLayout.Tab tab, int i) {
        tab.setText(this.i.get(i));
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        BydLogHelper.logD("loadData = ");
        this.i.add(getString(R.string.ac_front));
        if (((d) this.f).h() == 2) {
            this.i.add(getString(R.string.ac_rear));
        } else {
            this.g.setVisibility(4);
        }
        this.j = new AcPagerAdapter(getChildFragmentManager(), getLifecycle(), this.i.size());
        this.h.setAdapter(this.j);
        new TabLayoutMediator(this.g, this.h, new TabLayoutMediator.TabConfigurationStrategy() { // from class: b.a.a.i.g.a
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                this.f268a.H0(tab, i);
            }
        }).attach();
        this.g.removeAllTabs();
        int i = 0;
        while (i < this.i.size()) {
            TabLayout.Tab customView = this.g.newTab().setCustomView(R.layout.tabitem_contenview);
            View customView2 = customView.getCustomView();
            if (customView2 != null) {
                ((TextView) customView2.findViewById(R.id.text)).setText(this.i.get(i));
            }
            F0(i == 0, customView);
            this.g.addTab(customView);
            i++;
        }
        this.g.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new a());
    }

    public final void F0(boolean z, TabLayout.Tab tab) {
        if (tab.getCustomView() == null) {
            tab.setCustomView(R.layout.tabitem_contenview);
        }
        ((TextView) tab.getCustomView().findViewById(R.id.text)).setTextAppearance(z ? R.style.TabLayoutTextSelected : R.style.TabLayoutTextUnSelected);
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.i.clear();
        if (this.j != null) {
            this.j.a();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        BydLogHelper.logD("onHiddenChanged  = " + z);
        if (this.j != null) {
            ArrayList<Fragment> arrayListB = this.j.b();
            for (int i = 0; i < arrayListB.size(); i++) {
                Fragment fragment = arrayListB.get(i);
                if (fragment != null) {
                    fragment.onHiddenChanged(z);
                }
            }
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.g != null) {
            AcControlViewModel acControlViewModel = this.l;
            if (acControlViewModel != null) {
                this.k = acControlViewModel.a();
            }
            int i = this.k;
            if (i < 0 || i >= this.g.getTabCount() || this.g.getTabAt(this.k) == null) {
                return;
            }
            this.g.getTabAt(this.k).select();
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return ((d) this.f).g() == 1 ? R.layout.fragment_ac_control_right_rudder : R.layout.fragment_ac_control;
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.l = (AcControlViewModel) new ViewModelProvider(requireActivity()).get(AcControlViewModel.class);
        this.g = (TabLayout) view.findViewById(R.id.tl_ac_control);
        this.h = (ViewPager2) view.findViewById(R.id.vp_ac_control);
        if (this.h.getChildAt(0) != null) {
            this.h.getChildAt(0).setOverScrollMode(2);
        }
        this.h.setUserInputEnabled(false);
        A0();
    }
}
