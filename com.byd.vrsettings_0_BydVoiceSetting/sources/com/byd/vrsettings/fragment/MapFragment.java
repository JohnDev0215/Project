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
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class MapFragment extends BaseFragment {
    private CustomSkillsPopupView cspv_destination;
    private CustomSkillsPopupView cspv_map_control;
    private CustomSkillsPopupView cspv_navigating;
    private CustomSkillsPopupView cspv_nearby;
    private ConstraintLayout customSkills;
    private List<CustomViewpagerView> destinationList;
    private List<Integer> listDestination;
    private List<Integer> listMapControl;
    private List<Integer> listNavigating;
    private List<Integer> listNavigationVolume;
    private List<Integer> listNearby;
    private List<CustomViewpagerView> mapControlList;
    private List<CustomViewpagerView> navigatingList;
    private List<CustomViewpagerView> navigationVolumeList;
    private CustomSkillsPopupView navigation_volume;
    private List<CustomViewpagerView> nearbyList;
    private ConstraintSet set;
    private CustomScrollView sv_map;
    private View view;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_map, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_map);
        this.sv_map = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.MapFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.customSkills = (ConstraintLayout) this.view.findViewById(R.id.customSkills);
        this.cspv_destination = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_destination);
        this.cspv_navigating = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_navigating);
        this.cspv_nearby = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_nearby);
        this.cspv_map_control = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_map_control);
        this.navigation_volume = (CustomSkillsPopupView) this.view.findViewById(R.id.navigation_volume);
        this.destinationList = new ArrayList();
        this.navigatingList = new ArrayList();
        this.nearbyList = new ArrayList();
        this.mapControlList = new ArrayList();
        this.navigationVolumeList = new ArrayList();
        this.listDestination = new ArrayList();
        this.listNavigating = new ArrayList();
        this.listNearby = new ArrayList();
        this.listMapControl = new ArrayList();
        this.listNavigationVolume = new ArrayList();
        initIdList();
        initSkillsPopup();
    }

    private void initIdList() {
        this.listDestination.add(Integer.valueOf(R.string.text_go_home));
        this.listDestination.add(Integer.valueOf(R.string.text_to_work));
        this.listDestination.add(Integer.valueOf(R.string.text_to_library));
        this.listDestination.add(Integer.valueOf(R.string.text_to_train));
        this.listNavigating.add(Integer.valueOf(R.string.text_how_long));
        this.listNavigating.add(Integer.valueOf(R.string.text_view_entire_trip));
        this.listNavigating.add(Integer.valueOf(R.string.text_distance_destination));
        this.listNearby.add(Integer.valueOf(R.string.text_parking_space));
        this.listNearby.add(Integer.valueOf(R.string.text_charge_station));
        this.listNearby.add(Integer.valueOf(R.string.text_supermarket_nearby));
        this.listNearby.add(Integer.valueOf(R.string.text_toilet_nearby));
        this.listMapControl.add(Integer.valueOf(R.string.text_zoom_in));
        this.listMapControl.add(Integer.valueOf(R.string.text_zoom_out));
        this.listMapControl.add(Integer.valueOf(R.string.text_exit_navigation));
        this.listMapControl.add(Integer.valueOf(R.string.text_heading_up));
        this.listMapControl.add(Integer.valueOf(R.string.text_head_up_mode));
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setTitle();
        setViewpagerAdapterByProp();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_destination.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_navigating.setSmallIcon(R.mipmap.ic_skills_two_night);
            this.cspv_nearby.setSmallIcon(R.mipmap.ic_skills_three_night);
            this.cspv_map_control.setSmallIcon(R.mipmap.ic_skills_four_night);
            this.navigation_volume.setSmallIcon(R.mipmap.ic_skills_five_night);
            return;
        }
        this.cspv_destination.setSmallIcon(R.mipmap.ic_skills_one);
        this.cspv_navigating.setSmallIcon(R.mipmap.ic_skills_two);
        this.cspv_nearby.setSmallIcon(R.mipmap.ic_skills_three);
        this.cspv_map_control.setSmallIcon(R.mipmap.ic_skills_four);
        this.navigation_volume.setSmallIcon(R.mipmap.ic_skills_five);
    }

    private void setTitle() {
        this.cspv_destination.setTitle(R.string.text_destination);
        this.cspv_navigating.setTitle(R.string.text_navigating);
        this.cspv_nearby.setTitle(R.string.text_nearby);
        this.cspv_map_control.setTitle(R.string.text_map_control);
    }

    private void setViewpagerAdapter() {
        this.cspv_destination.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listDestination, this.destinationList)));
        this.cspv_navigating.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listNavigating, this.navigatingList)));
        this.cspv_nearby.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listNearby, this.nearbyList)));
        this.cspv_map_control.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listMapControl, this.mapControlList)));
        this.navigation_volume.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listNavigationVolume, this.navigationVolumeList)));
    }

    private void setTitleByProp() {
        this.cspv_destination.setTitle(PropertiesUtil.getProperty("map_destination"));
        this.cspv_navigating.setTitle(PropertiesUtil.getProperty("map_navigating"));
        this.cspv_nearby.setTitle(PropertiesUtil.getProperty("map_nearby"));
        this.cspv_map_control.setTitle(PropertiesUtil.getProperty("map_control"));
        this.navigation_volume.setTitle(PropertiesUtil.getProperty("map_navigation_volume"));
    }

    private void setViewpagerAdapterByProp() {
        this.cspv_destination.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("map_navigation_group"), this.destinationList, BuildConfig.FLAVOR)));
        this.cspv_navigating.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("map_navigating_group"), this.navigatingList, BuildConfig.FLAVOR)));
        this.cspv_nearby.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("map_nearby_group"), this.nearbyList, BuildConfig.FLAVOR)));
        this.cspv_map_control.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("map_control_group"), this.mapControlList, BuildConfig.FLAVOR)));
        this.navigation_volume.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("map_navigation_volume_group"), this.navigationVolumeList, BuildConfig.FLAVOR)));
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
        this.cspv_destination.setNight();
        this.cspv_navigating.setNight();
        this.cspv_nearby.setNight();
        this.cspv_map_control.setNight();
        this.navigation_volume.setNight();
        for (int i = 0; i < this.destinationList.size(); i++) {
            this.destinationList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.navigatingList.size(); i2++) {
            this.navigatingList.get(i2).setNight();
        }
        for (int i3 = 0; i3 < this.nearbyList.size(); i3++) {
            this.nearbyList.get(i3).setNight();
        }
        for (int i4 = 0; i4 < this.mapControlList.size(); i4++) {
            this.mapControlList.get(i4).setNight();
        }
        for (int i5 = 0; i5 < this.navigationVolumeList.size(); i5++) {
            this.navigationVolumeList.get(i5).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_map.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_destination.setLight();
        this.cspv_navigating.setLight();
        this.cspv_nearby.setLight();
        this.cspv_map_control.setLight();
        this.navigation_volume.setLight();
        for (int i = 0; i < this.destinationList.size(); i++) {
            this.destinationList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.navigatingList.size(); i2++) {
            this.navigatingList.get(i2).setLight();
        }
        for (int i3 = 0; i3 < this.nearbyList.size(); i3++) {
            this.nearbyList.get(i3).setLight();
        }
        for (int i4 = 0; i4 < this.mapControlList.size(); i4++) {
            this.mapControlList.get(i4).setLight();
        }
        for (int i5 = 0; i5 < this.navigationVolumeList.size(); i5++) {
            this.navigationVolumeList.get(i5).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_map.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
    }

    public void setLandScape() {
        this.set.clone(this.customSkills);
        this.set.connect(R.id.cspv_destination, 7, R.id.cspv_navigating, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_navigating, 3, R.id.cspv_destination, 3);
        this.set.connect(R.id.cspv_navigating, 6, R.id.cspv_destination, 7);
        this.set.connect(R.id.cspv_nearby, 3, R.id.cspv_navigating, 4, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_nearby, 7, R.id.cspv_map_control, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_map_control, 6, R.id.cspv_nearby, 7);
        this.set.connect(R.id.cspv_map_control, 3, R.id.cspv_nearby, 3);
        this.set.connect(R.id.navigation_volume, 3, R.id.cspv_map_control, 4, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.customSkills);
    }

    public void setPortrait() {
        this.set.clone(this.customSkills);
        this.set.clear(R.id.cspv_destination, 7);
        this.set.connect(R.id.cspv_destination, 7, 0, 7);
        this.set.connect(R.id.cspv_navigating, 3, R.id.cspv_destination, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_navigating, 6, 0, 6);
        this.set.clear(R.id.cspv_nearby, 7);
        this.set.connect(R.id.cspv_nearby, 7, 0, 7);
        this.set.connect(R.id.cspv_nearby, 3, R.id.cspv_navigating, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_map_control, 3, R.id.cspv_nearby, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_map_control, 6, R.id.cspv_nearby, 6);
        this.set.connect(R.id.navigation_volume, 3, R.id.cspv_map_control, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.customSkills);
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
