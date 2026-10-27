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
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.CustomViewpagerView;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.PropertiesUtil;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSkillsPopupView;
import com.chad.library.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SystemFragment extends BaseFragment {
    private static final String TAG = "NEU_SystemFragment";
    private List<CustomViewpagerView> appList;
    private CustomSkillsPopupView cspv_application_operations;
    private CustomSkillsPopupView cspv_other_operations;
    private CustomSkillsPopupView cspv_rotate;
    private CustomSkillsPopupView cspv_settings_operations;
    private CustomSkillsPopupView cspv_volume;
    private ConstraintLayout custom_system;
    private List<String> listApplicationOperations;
    private List<String> listOtherOperations;
    private List<String> listRotate;
    private List<Integer> listShowView;
    private List<Integer> listVolume;
    private List<CustomViewpagerView> otherList;
    private List<CustomViewpagerView> rotateList;
    private ConstraintSet set;
    private List<CustomViewpagerView> settingsList;
    private CustomScrollView sv_system;
    private View view;
    private List<CustomViewpagerView> volumeList;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_system, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        this.custom_system = (ConstraintLayout) this.view.findViewById(R.id.custom_system);
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_system);
        this.sv_system = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.SystemFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.cspv_volume = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_volume);
        this.cspv_rotate = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_rotate);
        this.cspv_application_operations = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_application_operations);
        this.cspv_other_operations = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_other_operations);
        this.cspv_settings_operations = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_settings_operations);
        this.volumeList = new ArrayList();
        this.rotateList = new ArrayList();
        this.appList = new ArrayList();
        this.otherList = new ArrayList();
        this.settingsList = new ArrayList();
        this.listVolume = new ArrayList();
        this.listRotate = new ArrayList();
        this.listApplicationOperations = new ArrayList();
        this.listOtherOperations = new ArrayList();
        this.listShowView = new ArrayList();
        initIdList();
        initSkillsPopup();
    }

    public void setLandScape() {
        this.set.clone(this.custom_system);
        RLog.d(TAG, "changeLanguage: start setLandScape listShowView.size()==" + this.listShowView.size());
        int iApplyDimension = (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics());
        int iApplyDimension2 = (int) TypedValue.applyDimension(1, 542.0f, getResources().getDisplayMetrics());
        for (int i = 0; i < this.listShowView.size(); i++) {
            this.set.clear(this.listShowView.get(i).intValue(), 7);
            this.set.clear(this.listShowView.get(i).intValue(), 3);
            this.set.clear(this.listShowView.get(i).intValue(), 6);
            this.set.clear(this.listShowView.get(i).intValue(), 4);
        }
        for (int i2 = 0; i2 < this.listShowView.size(); i2++) {
            if (i2 % 2 == 0) {
                if (i2 == 0) {
                    this.set.connect(this.listShowView.get(i2).intValue(), 3, 0, 3);
                    if (this.listShowView.size() == 1) {
                        this.set.connect(this.listShowView.get(i2).intValue(), 7, 0, 7, iApplyDimension2);
                    } else {
                        this.set.connect(this.listShowView.get(i2).intValue(), 7, this.listShowView.get(i2 + 1).intValue(), 6);
                    }
                } else {
                    this.set.connect(this.listShowView.get(i2).intValue(), 3, this.listShowView.get(i2 - 2).intValue(), 4, iApplyDimension);
                    this.set.connect(this.listShowView.get(i2).intValue(), 7, this.listShowView.get(0).intValue(), 7);
                }
                this.set.connect(this.listShowView.get(i2).intValue(), 6, 0, 6);
            } else {
                int i3 = i2 - 1;
                this.set.connect(this.listShowView.get(i2).intValue(), 3, this.listShowView.get(i3).intValue(), 3);
                this.set.connect(this.listShowView.get(i2).intValue(), 6, this.listShowView.get(i3).intValue(), 7, iApplyDimension);
                this.set.connect(this.listShowView.get(i2).intValue(), 7, 0, 7);
            }
        }
        this.set.applyTo(this.custom_system);
    }

    public void setPortrait() {
        this.set.clone(this.custom_system);
        for (int i = 0; i < this.listShowView.size(); i++) {
            this.set.clear(this.listShowView.get(i).intValue(), 7);
            this.set.clear(this.listShowView.get(i).intValue(), 3);
            this.set.clear(this.listShowView.get(i).intValue(), 6);
            this.set.connect(this.listShowView.get(i).intValue(), 6, 0, 6);
            this.set.connect(this.listShowView.get(i).intValue(), 7, 0, 7);
            if (i == 0) {
                this.set.connect(this.listShowView.get(i).intValue(), 3, 0, 3);
            } else {
                this.set.connect(this.listShowView.get(i).intValue(), 3, this.listShowView.get(i - 1).intValue(), 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
            }
        }
        this.set.applyTo(this.custom_system);
    }

    private void initIdList() {
        this.listVolume.add(Integer.valueOf(R.string.text_turn_up_volume));
        this.listVolume.add(Integer.valueOf(R.string.text_turn_down_volume));
        this.listVolume.add(Integer.valueOf(R.string.text_mute));
        this.listVolume.add(Integer.valueOf(R.string.text_set_volume));
        this.listRotate = Arrays.asList(getResources().getStringArray(R.array.rotate_screen_arrays));
        this.listApplicationOperations = Arrays.asList(getResources().getStringArray(R.array.system_app_operations));
        this.listOtherOperations = Arrays.asList(getResources().getStringArray(R.array.system_other_operations_array));
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setViewpagerAdapterByProp();
        setTitle();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_volume.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_rotate.setSmallIcon(R.mipmap.ic_skills_two_night);
            this.cspv_application_operations.setSmallIcon(R.mipmap.ic_skills_three_night);
            this.cspv_settings_operations.setSmallIcon(R.mipmap.ic_skills_four_night);
            this.cspv_other_operations.setSmallIcon(R.mipmap.ic_skills_five_night);
            return;
        }
        this.cspv_volume.setSmallIcon(R.mipmap.ic_skills_one);
        this.cspv_rotate.setSmallIcon(R.mipmap.ic_skills_two);
        this.cspv_application_operations.setSmallIcon(R.mipmap.ic_skills_three);
        this.cspv_settings_operations.setSmallIcon(R.mipmap.ic_skills_four);
        this.cspv_other_operations.setSmallIcon(R.mipmap.ic_skills_five);
    }

    private void setTitle() {
        this.cspv_volume.setTitle(R.string.text_volume);
        this.cspv_rotate.setTitle(R.string.text_rotate_screen);
        this.cspv_application_operations.setTitle(R.string.text_application_operations);
        this.cspv_settings_operations.setTitle(R.string.text_system_settings_operations);
        this.cspv_other_operations.setTitle(R.string.text_other_operations);
    }

    private void setViewpagerAdapter() {
        this.cspv_volume.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listVolume, this.volumeList)));
        this.cspv_rotate.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listRotate, this.rotateList)));
        this.cspv_application_operations.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listApplicationOperations, this.appList)));
        this.cspv_other_operations.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listOtherOperations, this.otherList)));
    }

    private void setTitleByProp() {
        this.cspv_volume.setTitle(PropertiesUtil.getProperty("system_volume"));
        this.cspv_rotate.setTitle(PropertiesUtil.getProperty("rotate"));
        this.cspv_application_operations.setTitle(PropertiesUtil.getProperty("app_operations"));
        this.cspv_other_operations.setTitle(PropertiesUtil.getProperty("app_otheroperations"));
    }

    private void setViewpagerAdapterByProp() {
        this.listShowView.clear();
        CustomViewpagerAdapter customViewpagerAdapter = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("system_volume_group"), this.volumeList, Constant.KEY_CSPV_VOLUME));
        if (customViewpagerAdapter.getViewListCount() == 0) {
            this.cspv_volume.setVisibility(8);
        } else {
            this.cspv_volume.setVisibility(0);
            this.cspv_volume.setCustomAdapter(customViewpagerAdapter);
            this.listShowView.add(Integer.valueOf(R.id.cspv_volume));
        }
        CustomViewpagerAdapter customViewpagerAdapter2 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("system_rotate_group"), this.rotateList, Constant.KEY_EXIST_ROTATION));
        if (customViewpagerAdapter2.getViewListCount() == 0) {
            this.cspv_rotate.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_rotate));
            this.cspv_rotate.setVisibility(0);
            this.cspv_rotate.setCustomAdapter(customViewpagerAdapter2);
        }
        CustomViewpagerAdapter customViewpagerAdapter3 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("system_appOperation_group"), this.appList, Constant.KEY_EXIST_CAR_SETTINGS));
        if (customViewpagerAdapter3.getViewListCount() == 0) {
            this.cspv_application_operations.setVisibility(8);
        } else {
            this.cspv_application_operations.setVisibility(0);
            this.cspv_application_operations.setCustomAdapter(customViewpagerAdapter3);
            this.listShowView.add(Integer.valueOf(R.id.cspv_application_operations));
        }
        Map<String, String> propertyGroup = PropertiesUtil.getPropertyGroup("system_settingOperation_group");
        RLog.d(TAG, "settingsGroup.SIZE == " + propertyGroup.size());
        RLog.d(TAG, "settingsList.SIZE == " + this.settingsList.size());
        CustomViewpagerAdapter customViewpagerAdapter4 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(propertyGroup, this.settingsList, Constant.KEY_CSPV_SETTINGS_OPERATIONS));
        if (customViewpagerAdapter4.getViewListCount() == 0) {
            this.cspv_settings_operations.setVisibility(8);
        } else {
            this.cspv_settings_operations.setVisibility(0);
            this.cspv_settings_operations.setCustomAdapter(customViewpagerAdapter4);
            this.listShowView.add(Integer.valueOf(R.id.cspv_settings_operations));
        }
        CustomViewpagerAdapter customViewpagerAdapter5 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("system_otherOperation_group"), this.otherList, BuildConfig.FLAVOR));
        if (customViewpagerAdapter5.getViewListCount() == 0) {
            this.cspv_other_operations.setVisibility(8);
        } else {
            this.cspv_other_operations.setVisibility(0);
            this.cspv_other_operations.setCustomAdapter(customViewpagerAdapter5);
            this.listShowView.add(Integer.valueOf(R.id.cspv_other_operations));
        }
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
        this.cspv_volume.setNight();
        this.cspv_rotate.setNight();
        this.cspv_application_operations.setNight();
        this.cspv_other_operations.setNight();
        this.cspv_settings_operations.setNight();
        for (int i = 0; i < this.volumeList.size(); i++) {
            this.volumeList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.rotateList.size(); i2++) {
            this.rotateList.get(i2).setNight();
        }
        for (int i3 = 0; i3 < this.appList.size(); i3++) {
            this.appList.get(i3).setNight();
        }
        for (int i4 = 0; i4 < this.otherList.size(); i4++) {
            this.otherList.get(i4).setNight();
        }
        for (int i5 = 0; i5 < this.settingsList.size(); i5++) {
            this.settingsList.get(i5).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_system.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_volume.setLight();
        this.cspv_rotate.setLight();
        this.cspv_application_operations.setLight();
        this.cspv_other_operations.setLight();
        this.cspv_settings_operations.setLight();
        for (int i = 0; i < this.volumeList.size(); i++) {
            this.volumeList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.rotateList.size(); i2++) {
            this.rotateList.get(i2).setLight();
        }
        for (int i3 = 0; i3 < this.appList.size(); i3++) {
            this.appList.get(i3).setLight();
        }
        for (int i4 = 0; i4 < this.otherList.size(); i4++) {
            this.otherList.get(i4).setLight();
        }
        for (int i5 = 0; i5 < this.settingsList.size(); i5++) {
            this.settingsList.get(i5).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_system.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE") && PropertiesUtil.loadLanguage()) {
            setViewpagerAdapterByProp();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
        setTitle();
    }
}
