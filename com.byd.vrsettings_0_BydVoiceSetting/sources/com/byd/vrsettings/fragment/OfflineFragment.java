package com.byd.vrsettings.fragment;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.R;
import com.byd.vrsettings.adapter.CustomViewpagerAdapter;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.CustomViewpagerView;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.PropertiesUtil;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSkillsPopupView;
import com.chad.library.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class OfflineFragment extends BaseFragment {
    private List<CustomViewpagerView> acList;
    private CustomSkillsPopupView cspv_ac;
    private CustomSkillsPopupView cspv_dial;
    private CustomSkillsPopupView cspv_other_operations;
    private CustomSkillsPopupView cspv_volume;
    private CustomSkillsPopupView cspv_windows;
    private ConstraintLayout custom_offline;
    private List<CustomViewpagerView> dialList;
    private List<Integer> listAc;
    private List<Integer> listDial;
    private List<String> listOtherOperations;
    private List<Integer> listVolume;
    private List<String> listWindows;
    private List<CustomViewpagerView> otherList;
    private ConstraintSet set;
    private CustomScrollView sv_offline;
    private View view;
    private List<CustomViewpagerView> volumeList;
    private List<CustomViewpagerView> windowsList;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_offline, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_offline);
        this.sv_offline = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.OfflineFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.cspv_ac = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_ac);
        this.custom_offline = (ConstraintLayout) this.view.findViewById(R.id.custom_offline);
        this.cspv_windows = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_windows);
        this.cspv_volume = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_volume);
        this.cspv_dial = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_dial);
        this.cspv_other_operations = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_other_operations);
        this.acList = new ArrayList();
        this.windowsList = new ArrayList();
        this.volumeList = new ArrayList();
        this.dialList = new ArrayList();
        this.otherList = new ArrayList();
        this.listAc = new ArrayList();
        this.listWindows = new ArrayList();
        this.listVolume = new ArrayList();
        this.listDial = new ArrayList();
        this.listOtherOperations = new ArrayList();
        initIdList();
        initSkillsPopup();
    }

    private void initIdList() {
        this.listAc.add(Integer.valueOf(R.string.text_turn_on_ac_offline));
        this.listAc.add(Integer.valueOf(R.string.text_turn_on_external_circulation_offline));
        this.listAc.add(Integer.valueOf(R.string.text_too_hot_offline));
        this.listAc.add(Integer.valueOf(R.string.text_turn_down_fan_offline));
        this.listAc.add(Integer.valueOf(R.string.text_set_fan_speed_offline));
        this.listAc.add(Integer.valueOf(R.string.text_switch_on_defrost_offline));
        this.listAc.add(Integer.valueOf(R.string.text_turn_on_ventilation_offline));
        this.listWindows = Arrays.asList(getResources().getStringArray(R.array.offline_window_array));
        this.listVolume.add(Integer.valueOf(R.string.text_turn_up_volume));
        this.listVolume.add(Integer.valueOf(R.string.text_turn_down_volume));
        this.listVolume.add(Integer.valueOf(R.string.text_mute));
        this.listVolume.add(Integer.valueOf(R.string.text_set_volume));
        this.listDial.add(Integer.valueOf(R.string.text_call_jack));
        this.listDial.add(Integer.valueOf(R.string.text_dial_jack_number));
        this.listOtherOperations = Arrays.asList(getResources().getStringArray(R.array.offline_other_operations_array));
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setViewpagerAdapterByProp();
        setTitleByProp();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_ac.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_windows.setSmallIcon(R.mipmap.ic_skills_two_night);
            this.cspv_volume.setSmallIcon(R.mipmap.ic_skills_three_night);
            this.cspv_dial.setSmallIcon(R.mipmap.ic_skills_four_night);
            this.cspv_other_operations.setSmallIcon(R.mipmap.ic_skills_five_night);
            return;
        }
        this.cspv_ac.setSmallIcon(R.mipmap.ic_skills_one);
        this.cspv_windows.setSmallIcon(R.mipmap.ic_skills_two);
        this.cspv_volume.setSmallIcon(R.mipmap.ic_skills_three);
        this.cspv_dial.setSmallIcon(R.mipmap.ic_skills_four);
        this.cspv_other_operations.setSmallIcon(R.mipmap.ic_skills_five);
    }

    private void setTitle() {
        this.cspv_ac.setTitle(R.string.text_ac_offline);
        this.cspv_windows.setTitle(R.string.text_windows_control_offline);
        this.cspv_volume.setTitle(R.string.text_volume_offline);
        this.cspv_dial.setTitle(R.string.text_dial_offline);
        this.cspv_other_operations.setTitle(R.string.text_other_operations);
    }

    public void setLandScape() {
        this.set.clone(this.custom_offline);
        this.set.connect(R.id.cspv_ac, 7, R.id.cspv_windows, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_windows, 3, R.id.cspv_ac, 3);
        this.set.connect(R.id.cspv_windows, 6, R.id.cspv_ac, 7);
        this.set.connect(R.id.cspv_volume, 7, R.id.cspv_dial, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_volume, 3, R.id.cspv_windows, 4, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_dial, 6, R.id.cspv_volume, 7);
        this.set.connect(R.id.cspv_dial, 3, R.id.cspv_volume, 3);
        this.set.connect(R.id.cspv_other_operations, 3, R.id.cspv_dial, 4, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.custom_offline);
    }

    public void setPortrait() {
        this.set.clone(this.custom_offline);
        this.set.clear(R.id.cspv_ac, 7);
        this.set.connect(R.id.cspv_ac, 7, 0, 7);
        this.set.connect(R.id.cspv_windows, 3, R.id.cspv_ac, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_windows, 6, 0, 6);
        this.set.clear(R.id.cspv_volume, 7);
        this.set.connect(R.id.cspv_volume, 7, 0, 7);
        this.set.connect(R.id.cspv_volume, 3, R.id.cspv_windows, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_dial, 3, R.id.cspv_volume, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_dial, 6, R.id.cspv_volume, 6);
        this.set.connect(R.id.navigation_volume, 3, R.id.cspv_map_control, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.custom_offline);
    }

    private void setViewpagerAdapter() {
        this.cspv_ac.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listAc, this.acList)));
        this.cspv_windows.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listWindows, this.windowsList)));
        this.cspv_volume.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listVolume, this.volumeList)));
        this.cspv_dial.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listDial, this.dialList)));
        this.cspv_other_operations.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listOtherOperations, this.otherList)));
    }

    public void setTitleByProp() {
        this.cspv_ac.setTitle(PropertiesUtil.getProperty("offline_ac"));
        this.cspv_windows.setTitle(PropertiesUtil.getProperty("offline_windows"));
        this.cspv_volume.setTitle(PropertiesUtil.getProperty("offline_volume"));
        this.cspv_dial.setTitle(PropertiesUtil.getProperty("dial"));
        this.cspv_other_operations.setTitle(PropertiesUtil.getProperty("app_otheroperations"));
    }

    private void setViewpagerAdapterByProp() {
        this.cspv_ac.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("offline_ac_group"), this.acList, BuildConfig.FLAVOR)));
        this.cspv_windows.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("offline_windows_group"), this.windowsList, BuildConfig.FLAVOR)));
        this.cspv_volume.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("offline_volume_group"), this.volumeList, BuildConfig.FLAVOR)));
        this.cspv_dial.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("phone_call_group"), this.dialList, BuildConfig.FLAVOR)));
        this.cspv_other_operations.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("other_operations_group"), this.otherList, BuildConfig.FLAVOR)));
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
    }

    private void setNight() {
        setSmallIcon();
        this.cspv_ac.setNight();
        this.cspv_windows.setNight();
        this.cspv_volume.setNight();
        this.cspv_dial.setNight();
        this.cspv_other_operations.setNight();
        for (int i = 0; i < this.acList.size(); i++) {
            this.acList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.windowsList.size(); i2++) {
            this.windowsList.get(i2).setNight();
        }
        for (int i3 = 0; i3 < this.volumeList.size(); i3++) {
            this.volumeList.get(i3).setNight();
        }
        for (int i4 = 0; i4 < this.dialList.size(); i4++) {
            this.dialList.get(i4).setNight();
        }
        for (int i5 = 0; i5 < this.otherList.size(); i5++) {
            this.otherList.get(i5).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_offline.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_ac.setLight();
        this.cspv_windows.setLight();
        this.cspv_volume.setLight();
        this.cspv_dial.setLight();
        this.cspv_other_operations.setLight();
        for (int i = 0; i < this.acList.size(); i++) {
            this.acList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.windowsList.size(); i2++) {
            this.windowsList.get(i2).setLight();
        }
        for (int i3 = 0; i3 < this.volumeList.size(); i3++) {
            this.volumeList.get(i3).setLight();
        }
        for (int i4 = 0; i4 < this.dialList.size(); i4++) {
            this.dialList.get(i4).setLight();
        }
        for (int i5 = 0; i5 < this.otherList.size(); i5++) {
            this.otherList.get(i5).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_offline.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE") && PropertiesUtil.loadLanguage()) {
            setTitleByProp();
            setViewpagerAdapterByProp();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
