package com.byd.vrsettings.fragment;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class VehicleFragment extends BaseFragment {
    private static final String TAG = "NEU_VehicleFragment";
    private List<CustomViewpagerView> acList;
    private List<CustomViewpagerView> airpurList;
    private List<CustomViewpagerView> ambientList;
    private CustomSkillsPopupView cspv_ac;
    private CustomSkillsPopupView cspv_air_pur;
    private CustomSkillsPopupView cspv_ambient_lights;
    private CustomSkillsPopupView cspv_read_light;
    private CustomSkillsPopupView cspv_seat;
    private CustomSkillsPopupView cspv_sunshade_control;
    private CustomSkillsPopupView cspv_windows_sunroof;
    private ConstraintLayout custom_vehicle;
    private List<String> listAc;
    private List<Integer> listAmbientLights;
    private List<String> listSeat;
    private List<Integer> listShowView;
    private List<String> listSunshadeControl;
    private List<Integer> listWindowsSunroof;
    private List<CustomViewpagerView> readlightslist;
    private List<CustomViewpagerView> seatList;
    private ConstraintSet set;
    private List<CustomViewpagerView> sunroofList;
    private List<CustomViewpagerView> sunshadeList;
    private CustomScrollView sv_vehicle;
    private View view;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_vehicle, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        this.custom_vehicle = (ConstraintLayout) this.view.findViewById(R.id.custom_vehicle);
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_vehicle);
        this.sv_vehicle = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.VehicleFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.cspv_ac = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_ac);
        this.cspv_air_pur = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_air_pur);
        this.cspv_seat = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_seat);
        this.cspv_ambient_lights = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_ambient_lights);
        this.cspv_windows_sunroof = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_windows_sunroof);
        this.cspv_sunshade_control = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_sunshade_control);
        this.cspv_read_light = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_read_light);
        this.acList = new ArrayList();
        this.airpurList = new ArrayList();
        this.seatList = new ArrayList();
        this.ambientList = new ArrayList();
        this.sunroofList = new ArrayList();
        this.sunshadeList = new ArrayList();
        this.readlightslist = new ArrayList();
        this.listAc = new ArrayList();
        this.listSeat = new ArrayList();
        this.listAmbientLights = new ArrayList();
        this.listWindowsSunroof = new ArrayList();
        this.listSunshadeControl = new ArrayList();
        this.listShowView = new ArrayList();
        initSkillsPopup();
    }

    private void initIdList() {
        this.listAc = Arrays.asList(getResources().getStringArray(R.array.control_ac_array));
        this.listSeat = Arrays.asList(getResources().getStringArray(R.array.control_seat_array));
        this.listAmbientLights.add(Integer.valueOf(R.string.text_turn_off_ambient_lights));
        this.listAmbientLights.add(Integer.valueOf(R.string.text_turn_on_ambient_lights));
        this.listAmbientLights.add(Integer.valueOf(R.string.text_change_ambient_lights_red));
        this.listAmbientLights.add(Integer.valueOf(R.string.text_set_ambient_lights_purple));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_open_windows));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_open_front_passenger_window));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_open_sunroof_halfway));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_close_sunroof));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_open_driver_side_window));
        this.listWindowsSunroof.add(Integer.valueOf(R.string.text_close_all_windows));
        this.listSunshadeControl = Arrays.asList(getResources().getStringArray(R.array.control_sunshade_array));
    }

    public void setLandScape() {
        this.set.clone(this.custom_vehicle);
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
        this.set.applyTo(this.custom_vehicle);
    }

    public void setPortrait() {
        this.set.clone(this.custom_vehicle);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics());
        for (int i = 0; i < this.listShowView.size(); i++) {
            this.set.clear(this.listShowView.get(i).intValue(), 7);
            this.set.clear(this.listShowView.get(i).intValue(), 3);
            this.set.clear(this.listShowView.get(i).intValue(), 6);
        }
        for (int i2 = 0; i2 < this.listShowView.size(); i2++) {
            this.set.connect(this.listShowView.get(i2).intValue(), 6, 0, 6);
            this.set.connect(this.listShowView.get(i2).intValue(), 7, 0, 7);
            if (i2 == 0) {
                this.set.connect(this.listShowView.get(i2).intValue(), 3, 0, 3);
            } else {
                this.set.connect(this.listShowView.get(i2).intValue(), 3, this.listShowView.get(i2 - 1).intValue(), 4, iApplyDimension);
            }
        }
        this.set.applyTo(this.custom_vehicle);
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setViewpagerAdapterByProp();
        setTitle();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_ac.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_air_pur.setSmallIcon(R.mipmap.ic_skills_two_night);
            this.cspv_seat.setSmallIcon(R.mipmap.ic_skills_three_night);
            this.cspv_ambient_lights.setSmallIcon(R.mipmap.ic_skills_four_night);
            this.cspv_windows_sunroof.setSmallIcon(R.mipmap.ic_skills_five_night);
            this.cspv_sunshade_control.setSmallIcon(R.mipmap.ic_skills_six_night);
            this.cspv_read_light.setSmallIcon(R.mipmap.ic_skills_one_night);
            return;
        }
        this.cspv_ac.setSmallIcon(R.mipmap.ic_skills_one);
        this.cspv_air_pur.setSmallIcon(R.mipmap.ic_skills_two);
        this.cspv_seat.setSmallIcon(R.mipmap.ic_skills_three);
        this.cspv_ambient_lights.setSmallIcon(R.mipmap.ic_skills_four);
        this.cspv_windows_sunroof.setSmallIcon(R.mipmap.ic_skills_five);
        this.cspv_sunshade_control.setSmallIcon(R.mipmap.ic_skills_six);
        this.cspv_read_light.setSmallIcon(R.mipmap.ic_skills_one);
    }

    private void setTitle() {
        this.cspv_ac.setTitle(R.string.text_ac);
        this.cspv_air_pur.setTitle(R.string.text_air_pur);
        this.cspv_seat.setTitle(R.string.text_seat);
        this.cspv_ambient_lights.setTitle(R.string.text_adjust_ambient_lights);
        this.cspv_windows_sunroof.setTitle(R.string.text_windows_and_sunroof_controls);
        this.cspv_sunshade_control.setTitle(R.string.text_sunshade_control);
        this.cspv_read_light.setTitle(R.string.text_read_light);
    }

    private void setViewpagerAdapter() {
        this.cspv_ac.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listAc, this.acList)));
        this.cspv_seat.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listSeat, this.seatList)));
        this.cspv_ambient_lights.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listAmbientLights, this.ambientList)));
        this.cspv_windows_sunroof.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listWindowsSunroof, this.sunroofList)));
        this.cspv_sunshade_control.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithStrings(this.listSunshadeControl, this.sunshadeList)));
    }

    private void setTitleByProp() {
        this.cspv_ac.setTitle(PropertiesUtil.getProperty("vehicle_ac"));
        this.cspv_seat.setTitle(PropertiesUtil.getProperty("vehicle_seat"));
        this.cspv_ambient_lights.setTitle(PropertiesUtil.getProperty("ambientlights"));
        this.cspv_windows_sunroof.setTitle(PropertiesUtil.getProperty("vehicle_windowsunroof"));
        this.cspv_sunshade_control.setTitle(PropertiesUtil.getProperty("vehicle_sunshade"));
    }

    private void setViewpagerAdapterByProp() {
        this.listShowView.clear();
        CustomViewpagerAdapter customViewpagerAdapter = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("vehicle_ac_group"), this.acList, Constant.KEY_EXIST_AC));
        if (customViewpagerAdapter.getViewListCount() == 0) {
            this.cspv_ac.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_ac));
            this.cspv_ac.setVisibility(0);
            this.cspv_ac.setCustomAdapter(customViewpagerAdapter);
        }
        Map<String, String> propertyGroup = PropertiesUtil.getPropertyGroup("vehicle_air_group");
        Log.d(TAG, "air_purGroup.SIZE == " + propertyGroup.size());
        CustomViewpagerAdapter customViewpagerAdapter2 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(propertyGroup, this.airpurList, Constant.KEY_EXIST_RAPID_PURIFICATION));
        Log.d(TAG, "air_purAdapter.SIZE == " + customViewpagerAdapter2.getViewListCount());
        if (customViewpagerAdapter2.getViewListCount() == 0) {
            this.cspv_air_pur.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_air_pur));
            this.cspv_air_pur.setVisibility(0);
            this.cspv_air_pur.setCustomAdapter(customViewpagerAdapter2);
        }
        CustomViewpagerAdapter customViewpagerAdapter3 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("vehicle_seat_group"), this.seatList, Constant.KEY_EXIST_DRIVER_SEAT_HEATING));
        if (customViewpagerAdapter3.getViewListCount() == 0) {
            this.cspv_seat.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_seat));
            this.cspv_seat.setVisibility(0);
            this.cspv_seat.setCustomAdapter(customViewpagerAdapter3);
        }
        CustomViewpagerAdapter customViewpagerAdapter4 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("vehicle_ambient_group"), this.ambientList, Constant.KEY_EXIST_ATMOSPHERE_LIGHT));
        if (customViewpagerAdapter4.getViewListCount() == 0) {
            this.cspv_ambient_lights.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_ambient_lights));
            this.cspv_ambient_lights.setVisibility(0);
            this.cspv_ambient_lights.setCustomAdapter(customViewpagerAdapter4);
        }
        CustomViewpagerAdapter customViewpagerAdapter5 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("vehicle_window_group"), this.sunroofList, Constant.KEY_EXIST_CAR_DORMANT_WINDOW));
        if (customViewpagerAdapter5.getViewListCount() == 0) {
            this.cspv_windows_sunroof.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_windows_sunroof));
            this.cspv_windows_sunroof.setVisibility(0);
            this.cspv_windows_sunroof.setCustomAdapter(customViewpagerAdapter5);
        }
        CustomViewpagerAdapter customViewpagerAdapter6 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("vehicle_sunshade_group"), this.sunshadeList, Constant.KEY_EXIST_SUNSHADE));
        if (customViewpagerAdapter6.getViewListCount() == 0) {
            this.cspv_sunshade_control.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_sunshade_control));
            this.cspv_sunshade_control.setVisibility(0);
            this.cspv_sunshade_control.setCustomAdapter(customViewpagerAdapter6);
        }
        Map<String, String> propertyGroup2 = PropertiesUtil.getPropertyGroup("vehicle_readlight_group");
        Log.d(TAG, "readlightsGroup.SIZE == " + propertyGroup2.size());
        Log.d(TAG, "readlightslist.SIZE == " + this.readlightslist.size());
        CustomViewpagerAdapter customViewpagerAdapter7 = new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(propertyGroup2, this.readlightslist, Constant.KEY_EXIST_READING_LIGHT));
        if (customViewpagerAdapter7.getViewListCount() == 0) {
            this.cspv_read_light.setVisibility(8);
        } else {
            this.listShowView.add(Integer.valueOf(R.id.cspv_read_light));
            this.cspv_read_light.setVisibility(0);
            this.cspv_read_light.setCustomAdapter(customViewpagerAdapter7);
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
        this.cspv_ac.setNight();
        this.cspv_air_pur.setNight();
        this.cspv_seat.setNight();
        this.cspv_ambient_lights.setNight();
        this.cspv_windows_sunroof.setNight();
        this.cspv_sunshade_control.setNight();
        this.cspv_read_light.setNight();
        for (int i = 0; i < this.acList.size(); i++) {
            this.acList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.airpurList.size(); i2++) {
            this.airpurList.get(i2).setNight();
        }
        for (int i3 = 0; i3 < this.seatList.size(); i3++) {
            this.seatList.get(i3).setNight();
        }
        for (int i4 = 0; i4 < this.ambientList.size(); i4++) {
            this.ambientList.get(i4).setNight();
        }
        for (int i5 = 0; i5 < this.sunroofList.size(); i5++) {
            this.sunroofList.get(i5).setNight();
        }
        for (int i6 = 0; i6 < this.sunshadeList.size(); i6++) {
            this.sunshadeList.get(i6).setNight();
        }
        for (int i7 = 0; i7 < this.readlightslist.size(); i7++) {
            this.readlightslist.get(i7).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_vehicle.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_ac.setLight();
        this.cspv_air_pur.setLight();
        this.cspv_seat.setLight();
        this.cspv_ambient_lights.setLight();
        this.cspv_windows_sunroof.setLight();
        this.cspv_sunshade_control.setLight();
        this.cspv_read_light.setLight();
        for (int i = 0; i < this.acList.size(); i++) {
            this.acList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.airpurList.size(); i2++) {
            this.airpurList.get(i2).setLight();
        }
        for (int i3 = 0; i3 < this.seatList.size(); i3++) {
            this.seatList.get(i3).setLight();
        }
        for (int i4 = 0; i4 < this.ambientList.size(); i4++) {
            this.ambientList.get(i4).setLight();
        }
        for (int i5 = 0; i5 < this.sunroofList.size(); i5++) {
            this.sunroofList.get(i5).setLight();
        }
        for (int i6 = 0; i6 < this.sunshadeList.size(); i6++) {
            this.sunshadeList.get(i6).setLight();
        }
        for (int i7 = 0; i7 < this.readlightslist.size(); i7++) {
            this.readlightslist.get(i7).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_vehicle.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE") && PropertiesUtil.loadLanguage()) {
            RLog.d(TAG, "changeLanguage: start setViewpagerAdapterByProp");
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
