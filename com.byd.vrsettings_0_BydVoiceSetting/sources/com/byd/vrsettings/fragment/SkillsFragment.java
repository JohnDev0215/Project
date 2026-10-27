package com.byd.vrsettings.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.FastClickUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.PropertiesUtil;
import com.byd.vrsettings.utils.RLog;
import com.chad.library.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SkillsFragment extends BaseFragment implements View.OnClickListener {
    public static final String MAP_FRAGMENT = "mao_Fragment";
    public static final String Offline_FRAGMENT = "Offline_Fragment";
    public static final String Phone_FRAGMENT = "Phone_Fragment";
    public static final String Player_FRAGMENT = "Player_Fragment";
    public static final String System_FRAGMENT = "system_Fragment";
    public static final String Vehicle_FRAGMENT = "vehicle_Fragment";
    public static final String map_FRAGMENT = "map_Fragment";
    private View bugView;
    private ConstraintLayout cl_item_voice_skills;
    private FrameLayout fl_skills;
    private ImageView iv_voice_skills_map;
    private ImageView iv_voice_skills_phone;
    private ImageView iv_voice_skills_player;
    private ImageView iv_voice_skills_system;
    private ImageView iv_voice_skills_vehicle;
    private List<ImageView> listImageView;
    private List<Integer> listInteger;
    private List<Integer> listIntegerNight;
    private MyViewModel mViewModel;
    private MapFragment mapFragment;
    private PhoneFragment phoneFragment;
    private PlayerFragment playerFragment;
    private ConstraintSet set;
    private SystemFragment systemFragment;
    private TextView tv_voice_skills_map;
    private TextView tv_voice_skills_phone;
    private TextView tv_voice_skills_player;
    private TextView tv_voice_skills_system;
    private TextView tv_voice_skills_vehicle;
    private View v_map;
    private View v_map_click;
    private View v_phone;
    private View v_phone_click;
    private View v_player;
    private View v_player_click;
    private View v_system;
    private View v_system_click;
    private View v_vehicle;
    private View v_vehicle_click;
    private VehicleFragment vehicleFragment;
    private View view;
    private final String TAG = "NEU_SkillsFragment AppVersion=S.8.12.2";
    private int buttonPressed = 0;
    private View currentSelectView = null;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
        RLog.i("NEU_SkillsFragment", "onCreateView: begin");
        this.view = layoutInflater.inflate(R.layout.item_voice_skills, viewGroup, false);
        PropertiesUtil.loadLanguage();
        initView();
        this.buttonPressed = this.mViewModel.skillsItemValue;
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: onResume");
        if (this.mViewModel.skillFragmentOnResume && this.mViewModel.skillFragmentOnResumeforHELP) {
            this.mViewModel.skillFragmentOnResume = false;
            this.mViewModel.skillFragmentOnResumeforHELP = false;
            RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: onResume return");
            return;
        }
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_NAVI_AVAILABLE);
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: isNAVI_AVAILABLEFlag==" + this.mViewModel.isNAVI_AVAILABLEFlag + ",isNAVI_AVAILABLE==" + zIsInstallWithKey);
        initMapVehicleView();
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        this.mViewModel.isNAVI_AVAILABLEFlag = zIsInstallWithKey;
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: onResume isNAVI_AVAILABLEFlag ==" + this.mViewModel.isNAVI_AVAILABLEFlag);
    }

    public void initMapVehicleView() {
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_NAVI_AVAILABLE);
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: initMapVehicleView isNAVI_AVAILABLE==" + zIsInstallWithKey);
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: initMapVehicleView mViewModel.skillsItemValue==" + this.mViewModel.skillsItemValue);
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: initMapVehicleView buttonPressed==" + this.buttonPressed);
        if (zIsInstallWithKey && Common.ifSupportVehicle()) {
            if (this.mViewModel.skillsItemValue == 0) {
                this.buttonPressed = 0;
            }
            this.v_map.setVisibility(0);
            this.iv_voice_skills_map.setVisibility(0);
            this.tv_voice_skills_map.setVisibility(0);
            this.v_vehicle.setVisibility(0);
            this.iv_voice_skills_vehicle.setVisibility(0);
            this.tv_voice_skills_vehicle.setVisibility(0);
            initMapFragment();
            return;
        }
        if (!zIsInstallWithKey && Common.ifSupportVehicle()) {
            if (this.mViewModel.skillsItemValue == 0) {
                this.buttonPressed = 1;
                this.mViewModel.skillsItemValue = 1;
            }
            this.v_vehicle.setVisibility(0);
            this.iv_voice_skills_vehicle.setVisibility(0);
            this.tv_voice_skills_vehicle.setVisibility(0);
            hideMapNavigationView();
            return;
        }
        if (zIsInstallWithKey && !Common.ifSupportVehicle()) {
            if (this.mViewModel.skillsItemValue == 1) {
                this.buttonPressed = 0;
                this.mViewModel.skillsItemValue = 0;
            }
            RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: has map no vehicle");
            this.v_map.setVisibility(0);
            this.iv_voice_skills_map.setVisibility(0);
            this.tv_voice_skills_map.setVisibility(0);
            this.v_vehicle.setVisibility(8);
            this.iv_voice_skills_vehicle.setVisibility(8);
            this.tv_voice_skills_vehicle.setVisibility(8);
            hideViewConstraint_getmap_novehicle();
            initMapFragment();
            return;
        }
        if (this.mViewModel.skillsItemValue == 1 || this.mViewModel.skillsItemValue == 0) {
            this.buttonPressed = 2;
            this.mViewModel.skillsItemValue = 2;
        }
        RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: no map no vehicle");
        this.v_map.setVisibility(8);
        this.iv_voice_skills_map.setVisibility(8);
        this.tv_voice_skills_map.setVisibility(8);
        this.v_vehicle.setVisibility(8);
        this.iv_voice_skills_vehicle.setVisibility(8);
        this.tv_voice_skills_vehicle.setVisibility(8);
        hideViewConstraint_nomap_novehicle();
        initPhoneFragment();
    }

    private void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        this.set = new ConstraintSet();
        this.cl_item_voice_skills = (ConstraintLayout) this.view.findViewById(R.id.cl_item_voice_skills);
        this.fl_skills = (FrameLayout) this.view.findViewById(R.id.fl_skills);
        this.v_map = this.view.findViewById(R.id.v_map);
        this.v_vehicle = this.view.findViewById(R.id.v_vehicle);
        this.v_phone = this.view.findViewById(R.id.v_phone);
        this.v_player = this.view.findViewById(R.id.v_player);
        this.v_system = this.view.findViewById(R.id.v_system);
        this.bugView = this.view.findViewById(R.id.bugView);
        this.v_map_click = this.view.findViewById(R.id.v_map_click);
        this.v_vehicle_click = this.view.findViewById(R.id.v_vehicle_click);
        this.v_phone_click = this.view.findViewById(R.id.v_phone_click);
        this.v_player_click = this.view.findViewById(R.id.v_player_click);
        this.v_system_click = this.view.findViewById(R.id.v_system_click);
        this.v_map_click.setOnClickListener(this);
        Log.d("NEU_SkillsFragment AppVersion=S.8.12.2", "v_map_click setOnClickListener");
        this.v_vehicle_click.setOnClickListener(this);
        this.v_phone_click.setOnClickListener(this);
        this.v_player_click.setOnClickListener(this);
        this.v_system_click.setOnClickListener(this);
        this.bugView.setOnClickListener(this);
        this.iv_voice_skills_map = (ImageView) this.view.findViewById(R.id.iv_voice_skills_map);
        this.iv_voice_skills_vehicle = (ImageView) this.view.findViewById(R.id.iv_voice_skills_vehicle);
        this.iv_voice_skills_phone = (ImageView) this.view.findViewById(R.id.iv_voice_skills_phone);
        this.iv_voice_skills_player = (ImageView) this.view.findViewById(R.id.iv_voice_skills_player);
        this.iv_voice_skills_system = (ImageView) this.view.findViewById(R.id.iv_voice_skills_system);
        this.tv_voice_skills_map = (TextView) this.view.findViewById(R.id.tv_voice_skills_map);
        this.tv_voice_skills_vehicle = (TextView) this.view.findViewById(R.id.tv_voice_skills_vehicle);
        this.tv_voice_skills_phone = (TextView) this.view.findViewById(R.id.tv_voice_skills_phone);
        this.tv_voice_skills_player = (TextView) this.view.findViewById(R.id.tv_voice_skills_player);
        this.tv_voice_skills_system = (TextView) this.view.findViewById(R.id.tv_voice_skills_system);
        ArrayList arrayList = new ArrayList();
        this.listImageView = arrayList;
        arrayList.add(this.iv_voice_skills_map);
        this.listImageView.add(this.iv_voice_skills_vehicle);
        this.listImageView.add(this.iv_voice_skills_phone);
        this.listImageView.add(this.iv_voice_skills_player);
        this.listImageView.add(this.iv_voice_skills_system);
        ArrayList arrayList2 = new ArrayList();
        this.listInteger = arrayList2;
        arrayList2.add(Integer.valueOf(R.mipmap.ic_map_n));
        this.listInteger.add(Integer.valueOf(R.mipmap.ic_vehicle_n));
        this.listInteger.add(Integer.valueOf(R.mipmap.ic_phone_n));
        this.listInteger.add(Integer.valueOf(R.mipmap.ic_player_n));
        this.listInteger.add(Integer.valueOf(R.mipmap.ic_system_n));
        ArrayList arrayList3 = new ArrayList();
        this.listIntegerNight = arrayList3;
        arrayList3.add(Integer.valueOf(R.mipmap.ic_map_n_night));
        this.listIntegerNight.add(Integer.valueOf(R.mipmap.ic_vehicle_n_night));
        this.listIntegerNight.add(Integer.valueOf(R.mipmap.ic_phone_n_night));
        this.listIntegerNight.add(Integer.valueOf(R.mipmap.ic_player_n_night));
        this.listIntegerNight.add(Integer.valueOf(R.mipmap.ic_system_n_night));
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            RLog.i("NEU_SkillsFragment", "onHiddenChanged: show  showshowshowshowshow=====");
            Settings.System.putString(((FragmentActivity) Objects.requireNonNull(getActivity())).getContentResolver(), Constant.KEY_SETTING_TAG, "skills");
        } else {
            RLog.i("NEU_SkillsFragment", "onHiddenChanged: show  hide hide hidehidehidehide=====");
            Settings.System.putString(((FragmentActivity) Objects.requireNonNull(getActivity())).getContentResolver(), Constant.KEY_SETTING_TAG, BuildConfig.FLAVOR);
        }
    }

    private void hideMapNavigationView() {
        RLog.i("NEU_SkillsFragment", "begin: hideMapNavigationView=====");
        this.v_map.setVisibility(8);
        this.iv_voice_skills_map.setVisibility(8);
        this.tv_voice_skills_map.setVisibility(8);
        hideViewConstraint_nomap_getvehicle();
        initVehicleFragment();
    }

    private void hideViewConstraint_nomap_getvehicle() {
        this.set.clone(this.cl_item_voice_skills);
        this.set.clear(R.id.v_vehicle, 6);
        if (getResources().getConfiguration().orientation == 2) {
            if (Common.isRightDriver()) {
                this.set.connect(R.id.v_vehicle, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 43.0f, getResources().getDisplayMetrics()));
            } else {
                this.set.connect(R.id.v_vehicle, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            }
        } else {
            this.set.connect(R.id.v_vehicle, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        }
        this.set.applyTo(this.cl_item_voice_skills);
    }

    private void hideViewConstraint_getmap_novehicle() {
        RLog.d("NEU_SkillsFragment", "begin: hideViewConstraint_getmap_novehicle=====");
        this.set.clone(this.cl_item_voice_skills);
        this.set.clear(R.id.v_phone, 6);
        if (getResources().getConfiguration().orientation == 2) {
            if (Common.isRightDriver()) {
                this.set.connect(R.id.v_phone, 6, R.id.v_map, 6, (int) TypedValue.applyDimension(1, 43.0f, getResources().getDisplayMetrics()));
            } else {
                this.set.connect(R.id.v_phone, 6, R.id.v_map, 6, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            }
        } else {
            this.set.connect(R.id.v_phone, 6, R.id.v_map, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        }
        this.set.applyTo(this.cl_item_voice_skills);
    }

    private void hideViewConstraint_nomap_novehicle() {
        RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "begin: hideViewConstraint_nomap_novehicle=====");
        this.set.clone(this.cl_item_voice_skills);
        this.set.clear(R.id.v_phone, 6);
        if (getResources().getConfiguration().orientation == 2) {
            if (Common.isRightDriver()) {
                this.set.connect(R.id.v_phone, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 43.0f, getResources().getDisplayMetrics()));
            } else {
                this.set.connect(R.id.v_phone, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            }
        } else {
            this.set.connect(R.id.v_phone, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        }
        this.set.applyTo(this.cl_item_voice_skills);
    }

    private void initMapFragment() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        hideFragment(fragmentTransactionBeginTransaction);
        MapFragment mapFragment = (MapFragment) childFragmentManager.findFragmentByTag(MAP_FRAGMENT);
        if (mapFragment == null) {
            MapFragment mapFragment2 = new MapFragment();
            this.mapFragment = mapFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_skills, mapFragment2, MAP_FRAGMENT);
        } else {
            this.mapFragment = mapFragment;
            VehicleFragment vehicleFragment = (VehicleFragment) childFragmentManager.findFragmentByTag(Vehicle_FRAGMENT);
            PhoneFragment phoneFragment = (PhoneFragment) childFragmentManager.findFragmentByTag(Phone_FRAGMENT);
            PlayerFragment playerFragment = (PlayerFragment) childFragmentManager.findFragmentByTag(Player_FRAGMENT);
            SystemFragment systemFragment = (SystemFragment) childFragmentManager.findFragmentByTag(System_FRAGMENT);
            if (vehicleFragment != null) {
                this.vehicleFragment = vehicleFragment;
            }
            if (phoneFragment != null) {
                this.phoneFragment = phoneFragment;
            }
            if (playerFragment != null) {
                this.playerFragment = playerFragment;
            }
            if (systemFragment != null) {
                this.systemFragment = systemFragment;
            }
            this.buttonPressed = this.mViewModel.skillsItemValue;
        }
        RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "initMapFragment buttonPressed====" + this.buttonPressed);
        setSelectLineLocation(getViewId(this.buttonPressed));
        setSelectItemIcon(this.buttonPressed);
        fragmentTransactionBeginTransaction.commit();
        onclickTab();
    }

    private void initVehicleFragment() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        hideFragment(fragmentTransactionBeginTransaction);
        VehicleFragment vehicleFragment = (VehicleFragment) childFragmentManager.findFragmentByTag(Vehicle_FRAGMENT);
        if (vehicleFragment == null) {
            VehicleFragment vehicleFragment2 = new VehicleFragment();
            this.vehicleFragment = vehicleFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_skills, vehicleFragment2, Vehicle_FRAGMENT);
        } else {
            this.vehicleFragment = vehicleFragment;
            MapFragment mapFragment = (MapFragment) childFragmentManager.findFragmentByTag(MAP_FRAGMENT);
            PhoneFragment phoneFragment = (PhoneFragment) childFragmentManager.findFragmentByTag(Phone_FRAGMENT);
            PlayerFragment playerFragment = (PlayerFragment) childFragmentManager.findFragmentByTag(Player_FRAGMENT);
            SystemFragment systemFragment = (SystemFragment) childFragmentManager.findFragmentByTag(System_FRAGMENT);
            if (mapFragment != null) {
                this.mapFragment = mapFragment;
            }
            if (phoneFragment != null) {
                this.phoneFragment = phoneFragment;
            }
            if (playerFragment != null) {
                this.playerFragment = playerFragment;
            }
            if (systemFragment != null) {
                this.systemFragment = systemFragment;
            }
            this.buttonPressed = this.mViewModel.skillsItemValue;
        }
        setSelectLineLocation(getViewId(this.buttonPressed));
        setSelectItemIcon(this.buttonPressed);
        fragmentTransactionBeginTransaction.commit();
        onclickTab();
    }

    private void initPhoneFragment() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        hideFragment(fragmentTransactionBeginTransaction);
        PhoneFragment phoneFragment = (PhoneFragment) childFragmentManager.findFragmentByTag(Phone_FRAGMENT);
        if (phoneFragment == null) {
            PhoneFragment phoneFragment2 = new PhoneFragment();
            this.phoneFragment = phoneFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_skills, phoneFragment2, Phone_FRAGMENT);
        } else {
            this.phoneFragment = phoneFragment;
            MapFragment mapFragment = (MapFragment) childFragmentManager.findFragmentByTag(MAP_FRAGMENT);
            PlayerFragment playerFragment = (PlayerFragment) childFragmentManager.findFragmentByTag(Player_FRAGMENT);
            SystemFragment systemFragment = (SystemFragment) childFragmentManager.findFragmentByTag(System_FRAGMENT);
            if (mapFragment != null) {
                this.mapFragment = mapFragment;
            }
            if (playerFragment != null) {
                this.playerFragment = playerFragment;
            }
            if (systemFragment != null) {
                this.systemFragment = systemFragment;
            }
            this.buttonPressed = this.mViewModel.skillsItemValue;
        }
        setSelectLineLocation(getViewId(this.buttonPressed));
        setSelectItemIcon(this.buttonPressed);
        fragmentTransactionBeginTransaction.commit();
        onclickTab();
    }

    private void onclickTab() {
        try {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "onclickTab buttonPressed====" + this.buttonPressed);
            int i = this.buttonPressed;
            if (i == 0) {
                this.v_map_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m50lambda$onclickTab$1$combydvrsettingsfragmentSkillsFragment();
                    }
                }, 100L);
            } else if (i == 1) {
                this.v_vehicle_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m52lambda$onclickTab$3$combydvrsettingsfragmentSkillsFragment();
                    }
                }, 100L);
            } else if (i == 2) {
                this.v_phone_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m54lambda$onclickTab$5$combydvrsettingsfragmentSkillsFragment();
                    }
                }, 100L);
            } else if (i == 3) {
                this.v_player_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m56lambda$onclickTab$7$combydvrsettingsfragmentSkillsFragment();
                    }
                }, 100L);
            } else if (i == 4) {
                this.v_system_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m58lambda$onclickTab$9$combydvrsettingsfragmentSkillsFragment();
                    }
                }, 100L);
            }
        } catch (Exception e) {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "onclickTab Exception====" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$1$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m50lambda$onclickTab$1$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_map_click.callOnClick();
        } else {
            this.v_map_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m49lambda$onclickTab$0$combydvrsettingsfragmentSkillsFragment();
                }
            }, 100L);
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$0$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m49lambda$onclickTab$0$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_map_click.callOnClick();
        } else {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "Fragment is not attached yet.");
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$3$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m52lambda$onclickTab$3$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_vehicle_click.callOnClick();
        } else {
            this.v_vehicle_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m51lambda$onclickTab$2$combydvrsettingsfragmentSkillsFragment();
                }
            }, 100L);
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$2$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m51lambda$onclickTab$2$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_vehicle_click.callOnClick();
        } else {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "Fragment is not attached yet.");
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$5$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m54lambda$onclickTab$5$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_phone_click.callOnClick();
        } else {
            this.v_phone_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m53lambda$onclickTab$4$combydvrsettingsfragmentSkillsFragment();
                }
            }, 100L);
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$4$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m53lambda$onclickTab$4$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_phone_click.callOnClick();
        } else {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "Fragment is not attached yet.");
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$7$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m56lambda$onclickTab$7$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_player_click.callOnClick();
        } else {
            this.v_player_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m55lambda$onclickTab$6$combydvrsettingsfragmentSkillsFragment();
                }
            }, 100L);
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$6$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m55lambda$onclickTab$6$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_player_click.callOnClick();
        } else {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "Fragment is not attached yet.");
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$9$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m58lambda$onclickTab$9$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_system_click.callOnClick();
        } else {
            this.v_system_click.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SkillsFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m57lambda$onclickTab$8$combydvrsettingsfragmentSkillsFragment();
                }
            }, 100L);
        }
    }

    /* JADX INFO: renamed from: lambda$onclickTab$8$com-byd-vrsettings-fragment-SkillsFragment, reason: not valid java name */
    /* synthetic */ void m57lambda$onclickTab$8$combydvrsettingsfragmentSkillsFragment() {
        if (isAdded()) {
            this.v_system_click.callOnClick();
        } else {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "Fragment is not attached yet.");
        }
    }

    private View getViewId(int i) {
        if (i == 0) {
            return this.v_map;
        }
        if (i == 1) {
            return this.v_vehicle;
        }
        if (i == 2) {
            return this.v_phone;
        }
        if (i == 3) {
            return this.v_player;
        }
        if (i == 4) {
            return this.v_system;
        }
        return this.v_map;
    }

    private void setSelectItemIcon(int i) {
        initIcon();
        if (i == 0) {
            this.iv_voice_skills_map.setImageResource(R.mipmap.ic_map_p);
            this.tv_voice_skills_map.setTextColor(getResources().getColor(R.color.skills_selectLine_8155, null));
            return;
        }
        if (i == 1) {
            this.iv_voice_skills_vehicle.setImageResource(R.mipmap.ic_vehicle_p);
            this.tv_voice_skills_vehicle.setTextColor(getResources().getColor(R.color.skills_selectLine_8155, null));
            return;
        }
        if (i == 2) {
            this.iv_voice_skills_phone.setImageResource(R.mipmap.ic_phone_p);
            this.tv_voice_skills_phone.setTextColor(getResources().getColor(R.color.skills_selectLine_8155, null));
        } else if (i == 3) {
            this.iv_voice_skills_player.setImageResource(R.mipmap.ic_player_p);
            this.tv_voice_skills_player.setTextColor(getResources().getColor(R.color.skills_selectLine_8155, null));
        } else {
            if (i != 4) {
                return;
            }
            this.iv_voice_skills_system.setImageResource(R.mipmap.ic_system_p);
            this.tv_voice_skills_system.setTextColor(getResources().getColor(R.color.skills_selectLine_8155, null));
        }
    }

    private void setLandScape() {
        this.set.clone(this.cl_item_voice_skills);
        if (Common.isRightDriver()) {
            this.set.connect(R.id.layout_tab, 6, 0, 6, (int) TypedValue.applyDimension(1, 43.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.layout_tab, 7, 0, 7, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.fl_skills, 6, 0, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.fl_skills, 7, 0, 7, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        } else {
            this.set.connect(R.id.v_map, 6, R.id.layout_tab, 6, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.v_system, 7, R.id.layout_tab, 7, (int) TypedValue.applyDimension(1, 43.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.fl_skills, 6, 0, 6, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.fl_skills, 7, 0, 7, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        }
        this.set.connect(R.id.layout_tab, 3, 0, 3, (int) TypedValue.applyDimension(1, 38.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.fl_skills, 3, R.id.layout_tab, 4, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.cl_item_voice_skills);
        MapFragment mapFragment = this.mapFragment;
        if (mapFragment != null) {
            mapFragment.setLandScape();
        }
        VehicleFragment vehicleFragment = this.vehicleFragment;
        if (vehicleFragment != null) {
            vehicleFragment.setLandScape();
        }
        PhoneFragment phoneFragment = this.phoneFragment;
        if (phoneFragment != null) {
            phoneFragment.setLandScape();
        }
        PlayerFragment playerFragment = this.playerFragment;
        if (playerFragment != null) {
            playerFragment.setLandScape();
        }
        SystemFragment systemFragment = this.systemFragment;
        if (systemFragment != null) {
            systemFragment.setLandScape();
        }
    }

    private void setPortrait() {
        this.set.clone(this.cl_item_voice_skills);
        this.set.connect(R.id.layout_tab, 6, 0, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.layout_tab, 7, 0, 7, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.fl_skills, 6, 0, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.fl_skills, 7, 0, 7, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.layout_tab, 3, 0, 3, (int) TypedValue.applyDimension(1, 49.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.fl_skills, 3, R.id.layout_tab, 4, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.cl_item_voice_skills);
        MapFragment mapFragment = this.mapFragment;
        if (mapFragment != null) {
            mapFragment.setPortrait();
        }
        VehicleFragment vehicleFragment = this.vehicleFragment;
        if (vehicleFragment != null) {
            vehicleFragment.setPortrait();
        }
        PhoneFragment phoneFragment = this.phoneFragment;
        if (phoneFragment != null) {
            phoneFragment.setPortrait();
        }
        PlayerFragment playerFragment = this.playerFragment;
        if (playerFragment != null) {
            playerFragment.setPortrait();
        }
        SystemFragment systemFragment = this.systemFragment;
        if (systemFragment != null) {
            systemFragment.setPortrait();
        }
    }

    private void setTabLandScapeLayoutParams(View view) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) TypedValue.applyDimension(1, 151.0f, getResources().getDisplayMetrics());
        layoutParams.height = (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics());
        view.setLayoutParams(layoutParams);
    }

    private void setTabPortraitLayoutParams(View view) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) TypedValue.applyDimension(1, 128.0f, getResources().getDisplayMetrics());
        layoutParams.height = (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics());
        view.setLayoutParams(layoutParams);
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        if (configuration.orientation == 2) {
            setLandScape();
        } else if (configuration.orientation == 1) {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_NAVI_AVAILABLE);
        if (!zIsInstallWithKey && Common.ifSupportVehicle()) {
            hideViewConstraint_nomap_getvehicle();
            return;
        }
        if (zIsInstallWithKey && !Common.ifSupportVehicle()) {
            RLog.i("NEU_SkillsFragment", "onConfigurationChanged  has map no vehicle");
            hideViewConstraint_getmap_novehicle();
        } else {
            if (zIsInstallWithKey || Common.ifSupportVehicle()) {
                return;
            }
            RLog.i("NEU_SkillsFragment", "onConfigurationChanged  no map no vehicle");
            hideViewConstraint_nomap_novehicle();
        }
    }

    private void setNight() {
        setSelectItemIcon(this.buttonPressed);
        View view = this.currentSelectView;
        if (view != null) {
            view.setBackground(getResources().getDrawable(R.mipmap.skills_select_tab_night, null));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    private void setLight() {
        setSelectItemIcon(this.buttonPressed);
        View view = this.currentSelectView;
        if (view != null) {
            view.setBackground(getResources().getDrawable(R.mipmap.skills_select_tab, null));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.bugView || FastClickUtils.isFastClick()) {
                return;
            }
            FragmentManager childFragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
            hideFragment(fragmentTransactionBeginTransaction);
            switch (view.getId()) {
                case R.id.v_map_click /* 2131296891 */:
                    MapFragment mapFragment = (MapFragment) childFragmentManager.findFragmentByTag(map_FRAGMENT);
                    if (mapFragment == null) {
                        MapFragment mapFragment2 = new MapFragment();
                        this.mapFragment = mapFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_skills, mapFragment2, map_FRAGMENT);
                    } else {
                        this.mapFragment = mapFragment;
                        fragmentTransactionBeginTransaction.show(mapFragment);
                    }
                    this.buttonPressed = 0;
                    this.mViewModel.skillsItemValue = 0;
                    setSelectLineLocation(this.v_map);
                    break;
                case R.id.v_phone_click /* 2131296895 */:
                    PhoneFragment phoneFragment = (PhoneFragment) childFragmentManager.findFragmentByTag(Phone_FRAGMENT);
                    if (phoneFragment == null) {
                        PhoneFragment phoneFragment2 = new PhoneFragment();
                        this.phoneFragment = phoneFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_skills, phoneFragment2, Phone_FRAGMENT);
                    } else {
                        this.phoneFragment = phoneFragment;
                        fragmentTransactionBeginTransaction.show(phoneFragment);
                    }
                    this.buttonPressed = 2;
                    this.mViewModel.skillsItemValue = 2;
                    setSelectLineLocation(this.v_phone);
                    break;
                case R.id.v_player_click /* 2131296898 */:
                    PlayerFragment playerFragment = (PlayerFragment) childFragmentManager.findFragmentByTag(Player_FRAGMENT);
                    if (playerFragment == null) {
                        PlayerFragment playerFragment2 = new PlayerFragment();
                        this.playerFragment = playerFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_skills, playerFragment2, Player_FRAGMENT);
                    } else {
                        this.playerFragment = playerFragment;
                        fragmentTransactionBeginTransaction.show(playerFragment);
                    }
                    this.buttonPressed = 3;
                    this.mViewModel.skillsItemValue = 3;
                    setSelectLineLocation(this.v_player);
                    break;
                case R.id.v_system_click /* 2131296903 */:
                    SystemFragment systemFragment = (SystemFragment) childFragmentManager.findFragmentByTag(System_FRAGMENT);
                    if (systemFragment == null) {
                        SystemFragment systemFragment2 = new SystemFragment();
                        this.systemFragment = systemFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_skills, systemFragment2, System_FRAGMENT);
                    } else {
                        this.systemFragment = systemFragment;
                        fragmentTransactionBeginTransaction.show(systemFragment);
                    }
                    this.buttonPressed = 4;
                    this.mViewModel.skillsItemValue = 4;
                    setSelectLineLocation(this.v_system);
                    break;
                case R.id.v_vehicle_click /* 2131296907 */:
                    VehicleFragment vehicleFragment = (VehicleFragment) childFragmentManager.findFragmentByTag(Vehicle_FRAGMENT);
                    if (vehicleFragment == null) {
                        VehicleFragment vehicleFragment2 = new VehicleFragment();
                        this.vehicleFragment = vehicleFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_skills, vehicleFragment2, Vehicle_FRAGMENT);
                    } else {
                        this.vehicleFragment = vehicleFragment;
                        fragmentTransactionBeginTransaction.show(vehicleFragment);
                    }
                    this.buttonPressed = 1;
                    this.mViewModel.skillsItemValue = 1;
                    setSelectLineLocation(this.v_vehicle);
                    break;
            }
            RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "after:  onclick mViewModel.skillsItemValue==" + this.mViewModel.skillsItemValue);
            RLog.i("NEU_SkillsFragment AppVersion=S.8.12.2", "after: onclick  buttonPressed==" + this.buttonPressed);
            setSelectItemIcon(this.buttonPressed);
            if (isStateSaved() || !isAdded()) {
                return;
            }
            fragmentTransactionBeginTransaction.commit();
        } catch (Exception e) {
            RLog.d("NEU_SkillsFragment AppVersion=S.8.12.2", "onClick Exception====" + e.getMessage());
        }
    }

    private void setSelectLineLocation(View view) {
        View view2 = this.currentSelectView;
        if (view2 != null) {
            view2.setBackground(null);
        }
        if (ModeUtils.isNightMode(getContext())) {
            view.setBackground(getResources().getDrawable(R.mipmap.skills_select_tab_night, null));
        } else {
            view.setBackground(getResources().getDrawable(R.mipmap.skills_select_tab, null));
        }
        this.currentSelectView = view;
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        MapFragment mapFragment = this.mapFragment;
        if (mapFragment != null) {
            fragmentTransaction.hide(mapFragment);
        }
        VehicleFragment vehicleFragment = this.vehicleFragment;
        if (vehicleFragment != null) {
            fragmentTransaction.hide(vehicleFragment);
        }
        PhoneFragment phoneFragment = this.phoneFragment;
        if (phoneFragment != null) {
            fragmentTransaction.hide(phoneFragment);
        }
        PlayerFragment playerFragment = this.playerFragment;
        if (playerFragment != null) {
            fragmentTransaction.hide(playerFragment);
        }
        SystemFragment systemFragment = this.systemFragment;
        if (systemFragment != null) {
            fragmentTransaction.hide(systemFragment);
        }
    }

    private void initIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.iv_voice_skills_map.setImageResource(R.mipmap.ic_map_n_night);
            this.iv_voice_skills_vehicle.setImageResource(R.mipmap.ic_vehicle_n_night);
            this.iv_voice_skills_phone.setImageResource(R.mipmap.ic_phone_n_night);
            this.iv_voice_skills_player.setImageResource(R.mipmap.ic_player_n_night);
            this.iv_voice_skills_system.setImageResource(R.mipmap.ic_system_n_night);
            this.tv_voice_skills_map.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.tv_voice_skills_vehicle.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.tv_voice_skills_phone.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.tv_voice_skills_player.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.tv_voice_skills_system.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            return;
        }
        this.iv_voice_skills_map.setImageResource(R.mipmap.ic_map_n);
        this.iv_voice_skills_vehicle.setImageResource(R.mipmap.ic_vehicle_n);
        this.iv_voice_skills_phone.setImageResource(R.mipmap.ic_phone_n);
        this.iv_voice_skills_player.setImageResource(R.mipmap.ic_player_n);
        this.iv_voice_skills_system.setImageResource(R.mipmap.ic_system_n);
        this.tv_voice_skills_map.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_voice_skills_vehicle.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_voice_skills_phone.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_voice_skills_player.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_voice_skills_system.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        this.mapFragment = null;
        this.vehicleFragment = null;
        this.phoneFragment = null;
        this.playerFragment = null;
        this.systemFragment = null;
        RLog.i("NEU_SkillsFragment", "onDestroyView  Constant.application_OnconfigChange ===========" + Constant.application_OnconfigChangeforSkill);
        if (!Constant.application_OnconfigChangeforSkill) {
            this.mViewModel.skillsItemValue = 0;
            this.buttonPressed = 0;
        }
        Constant.application_OnconfigChangeforSkill = false;
        RLog.d("NEU_SkillsFragment", "onDestroyView");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}
