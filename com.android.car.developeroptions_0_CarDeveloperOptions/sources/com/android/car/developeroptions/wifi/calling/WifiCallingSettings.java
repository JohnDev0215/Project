package com.android.car.developeroptions.wifi.calling;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.network.SubscriptionUtil;
import com.android.car.developeroptions.search.actionbar.SearchMenuController;
import com.android.car.developeroptions.support.actionbar.HelpMenuController;
import com.android.car.developeroptions.support.actionbar.HelpResourceProvider;
import com.android.car.developeroptions.widget.RtlCompatibleViewPager;
import com.android.car.developeroptions.widget.SlidingTabLayout;
import com.android.ims.ImsManager;
import com.android.internal.util.CollectionUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiCallingSettings extends InstrumentedFragment implements HelpResourceProvider {
    private WifiCallingViewPagerAdapter mPagerAdapter;
    private List<SubscriptionInfo> mSil;
    private SlidingTabLayout mTabLayout;
    private RtlCompatibleViewPager mViewPager;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_wifi_calling;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 105;
    }

    private final class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        private InternalViewPagerListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            WifiCallingSettings.this.updateTitleForCurrentSub();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.wifi_calling_settings_tabs, viewGroup, false);
        this.mTabLayout = (SlidingTabLayout) viewInflate.findViewById(R.id.sliding_tabs);
        this.mViewPager = (RtlCompatibleViewPager) viewInflate.findViewById(R.id.view_pager);
        WifiCallingViewPagerAdapter wifiCallingViewPagerAdapter = new WifiCallingViewPagerAdapter(getChildFragmentManager(), this.mViewPager);
        this.mPagerAdapter = wifiCallingViewPagerAdapter;
        this.mViewPager.setAdapter(wifiCallingViewPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new InternalViewPagerListener());
        maybeSetViewForSubId();
        return viewInflate;
    }

    private void maybeSetViewForSubId() {
        Intent intent;
        if (this.mSil == null || (intent = getActivity().getIntent()) == null) {
            return;
        }
        int intExtra = intent.getIntExtra("android.provider.extra.SUB_ID", -1);
        if (SubscriptionManager.isValidSubscriptionId(intExtra)) {
            for (SubscriptionInfo subscriptionInfo : this.mSil) {
                if (intExtra == subscriptionInfo.getSubscriptionId()) {
                    this.mViewPager.setCurrentItem(this.mSil.indexOf(subscriptionInfo));
                    return;
                }
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        SearchMenuController.init(this);
        HelpMenuController.init(this);
        updateSubList();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        List<SubscriptionInfo> list = this.mSil;
        if (list != null && list.size() > 1) {
            this.mTabLayout.setViewPager(this.mViewPager);
        } else {
            this.mTabLayout.setVisibility(8);
        }
        updateTitleForCurrentSub();
    }

    final class WifiCallingViewPagerAdapter extends FragmentPagerAdapter {
        private final RtlCompatibleViewPager mViewPager;

        public WifiCallingViewPagerAdapter(FragmentManager fragmentManager, RtlCompatibleViewPager rtlCompatibleViewPager) {
            super(fragmentManager);
            this.mViewPager = rtlCompatibleViewPager;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return String.valueOf(((SubscriptionInfo) WifiCallingSettings.this.mSil.get(i)).getDisplayName());
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            Log.d("WifiCallingSettings", "Adapter getItem " + i);
            Bundle bundle = new Bundle();
            bundle.putBoolean("need_search_icon_in_action_bar", false);
            bundle.putInt("subId", ((SubscriptionInfo) WifiCallingSettings.this.mSil.get(i)).getSubscriptionId());
            WifiCallingSettingsForSub wifiCallingSettingsForSub = new WifiCallingSettingsForSub();
            wifiCallingSettingsForSub.setArguments(bundle);
            return wifiCallingSettingsForSub;
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            Log.d("WifiCallingSettings", "Adapter instantiateItem " + i);
            return super.instantiateItem(viewGroup, this.mViewPager.getRtlAwareIndex(i));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            if (WifiCallingSettings.this.mSil == null) {
                Log.d("WifiCallingSettings", "Adapter getCount null mSil ");
                return 0;
            }
            Log.d("WifiCallingSettings", "Adapter getCount " + WifiCallingSettings.this.mSil.size());
            return WifiCallingSettings.this.mSil.size();
        }
    }

    boolean isWfcEnabledByPlatform(SubscriptionInfo subscriptionInfo) {
        return ImsManager.getInstance(getActivity(), subscriptionInfo.getSimSlotIndex()).isWfcEnabledByPlatform();
    }

    private void updateSubList() {
        List<SubscriptionInfo> activeSubscriptions = SubscriptionUtil.getActiveSubscriptions((SubscriptionManager) getContext().getSystemService(SubscriptionManager.class));
        this.mSil = activeSubscriptions;
        if (activeSubscriptions == null) {
            return;
        }
        int i = 0;
        while (i < this.mSil.size()) {
            if (isWfcEnabledByPlatform(this.mSil.get(i))) {
                i++;
            } else {
                this.mSil.remove(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitleForCurrentSub() {
        if (CollectionUtils.size(this.mSil) > 1) {
            getActivity().getActionBar().setTitle(SubscriptionManager.getResourcesForSubId(getContext(), this.mSil.get(this.mViewPager.getCurrentItem()).getSubscriptionId()).getString(R.string.wifi_calling_settings_title));
        }
    }
}
