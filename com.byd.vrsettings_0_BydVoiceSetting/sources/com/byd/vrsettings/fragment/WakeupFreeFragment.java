package com.byd.vrsettings.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentManager;
import com.byd.vrassistant.hint.bean.WakeUpFreeWordsSwitch;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.eventbus.FinishWakeupFreeFragmentBean;
import com.byd.vrsettings.eventbus.HideFragmentBean;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.ShowFragmentBean;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.PropertiesFreeWakeupUtil;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.view.CustomSettingPopupView;
import com.chad.library.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class WakeupFreeFragment extends BaseFragment {
    private OnBackPressedCallback callback;
    private ConstraintLayout cl_wakeup_free;
    private CustomSettingPopupView cspv_ac_control;
    private CustomSettingPopupView cspv_home_command;
    private CustomSettingPopupView cspv_map_nav;
    private CustomSettingPopupView cspv_music_player;
    private CustomSettingPopupView cspv_tel_control;
    private CustomSettingPopupView cspv_volume_control;
    private ImageView iv_wakeup_free_back;
    private List<String> listKeyConstant;
    private List<String> listKeyUp;
    private List<CustomSettingPopupView> listPopupView;
    private ConstraintSet set;
    private ScrollView sv_wakeup_free;
    private TextView tv_wakeup_free_title;
    private View view;
    private final String TAG = "NEU_WakeupFreeFragment";
    private final boolean DEFAULT_SWITCH = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.callback = new OnBackPressedCallback(true) { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment.1
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                RLog.d("NEU_WakeupFreeFragment", "requireActivity onBackPressed");
                EventBus.getDefault().post(new FinishWakeupFreeFragmentBean());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.callback);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.layout_wakeup_free, viewGroup, false);
        RLog.i("NEU_WakeupFreeFragment", "onCreateView: begin");
        PropertiesFreeWakeupUtil.loadLanguage();
        initView();
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

    private void initView() {
        EventBus.getDefault().register(this);
        this.sv_wakeup_free = (ScrollView) this.view.findViewById(R.id.sv_wakeup_free);
        this.cl_wakeup_free = (ConstraintLayout) this.view.findViewById(R.id.cl_wakeup_free);
        ImageView imageView = (ImageView) this.view.findViewById(R.id.iv_wakeup_free_back);
        this.iv_wakeup_free_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_WakeupFreeFragment", "onClick: post close fragment");
                EventBus.getDefault().post(new FinishWakeupFreeFragmentBean());
            }
        });
        this.tv_wakeup_free_title = (TextView) this.view.findViewById(R.id.tv_wakeup_free_title);
        this.cspv_home_command = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_home_command);
        this.cspv_music_player = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_music_player);
        this.cspv_ac_control = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_ac_control);
        this.cspv_volume_control = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_volume_control);
        this.cspv_map_nav = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_map_nav);
        this.cspv_tel_control = (CustomSettingPopupView) this.view.findViewById(R.id.cspv_tel_control);
        this.set = new ConstraintSet();
        setPopUpView();
        setPopUpViewContent();
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_NAVI_AVAILABLE);
        RLog.i("NEU_WakeupFreeFragment", "isNAVI_AVAILABLE =" + zIsInstallWithKey);
        boolean zIsInstallWithKey2 = Common.isInstallWithKey(Constant.KEY_EXIST_AC);
        RLog.i("NEU_WakeupFreeFragment", "isInstallAC =" + zIsInstallWithKey2);
        if (!zIsInstallWithKey) {
            this.cspv_map_nav.setVisibility(8);
        }
        if (zIsInstallWithKey2) {
            return;
        }
        this.cspv_ac_control.setVisibility(8);
    }

    private void setPopUpViewContent() {
        RLog.d("NEU_WakeupFreeFragment", "setPopUpViewContent start");
        this.cspv_home_command.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("home_command1"));
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_EXIST_ROTATION);
        RLog.i("NEU_WakeupFreeFragment", "isInstallRotation =" + zIsInstallWithKey);
        if (zIsInstallWithKey) {
            this.cspv_home_command.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("home_command2"));
        } else {
            this.cspv_home_command.setCharSequenceTextCommand2(BuildConfig.FLAVOR);
        }
        this.cspv_music_player.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("play_command1"));
        this.cspv_music_player.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("play_command2"));
        this.cspv_music_player.setCharSequenceTextCommand3(PropertiesFreeWakeupUtil.getProperty("play_command3"));
        this.cspv_music_player.setCharSequenceTextCommand4(PropertiesFreeWakeupUtil.getProperty("play_command4"));
        this.cspv_ac_control.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("ac_control_command1"));
        this.cspv_ac_control.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("ac_control_command2"));
        this.cspv_volume_control.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("volume_control_command1"));
        this.cspv_volume_control.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("volume_control_command2"));
        this.cspv_map_nav.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("map_command1"));
        this.cspv_map_nav.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("map_command2"));
        this.cspv_map_nav.setCharSequenceTextCommand3(PropertiesFreeWakeupUtil.getProperty("map_command3"));
        this.cspv_map_nav.setCharSequenceTextCommand4(PropertiesFreeWakeupUtil.getProperty("map_command4"));
        this.cspv_tel_control.setCharSequenceTextCommand1(PropertiesFreeWakeupUtil.getProperty("tel_control_command1"));
        this.cspv_tel_control.setCharSequenceTextCommand2(PropertiesFreeWakeupUtil.getProperty("tel_control_command2"));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RLog.d("NEU_WakeupFreeFragment", "onResume");
        this.callback.setEnabled(true);
        setPopUpView();
        setPopUpViewContent();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        RLog.d("NEU_WakeupFreeFragment", "onPause");
        this.callback.setEnabled(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            RLog.d("NEU_WakeupFreeFragment", "onHiddenChanged  hide");
            this.callback.setEnabled(false);
        } else {
            RLog.d("NEU_WakeupFreeFragment", "onHiddenChanged  show");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopUpView() {
        int i;
        String str;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean freeWakeupWordsValue = getFreeWakeupWordsValue(Constant.KEY_HOME_COMMANDS);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue));
        boolean freeWakeupWordsValue2 = getFreeWakeupWordsValue(Constant.KEY_MUSIC_PLAYER);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue2));
        boolean freeWakeupWordsValue3 = getFreeWakeupWordsValue(Constant.KEY_AC_CONTROL);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue3));
        boolean freeWakeupWordsValue4 = getFreeWakeupWordsValue(Constant.KEY_VOLUME_CONTROL);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue4));
        boolean freeWakeupWordsValue5 = getFreeWakeupWordsValue(Constant.KEY_MAP_NAV);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue5));
        boolean freeWakeupWordsValue6 = getFreeWakeupWordsValue(Constant.KEY_TEL_CONTROL);
        arrayList.add(Boolean.valueOf(freeWakeupWordsValue6));
        ArrayList arrayList3 = new ArrayList();
        this.listKeyConstant = arrayList3;
        arrayList3.add(Constant.KEY_HOME_COMMANDS);
        this.listKeyConstant.add(Constant.KEY_MUSIC_PLAYER);
        this.listKeyConstant.add(Constant.KEY_AC_CONTROL);
        this.listKeyConstant.add(Constant.KEY_VOLUME_CONTROL);
        this.listKeyConstant.add(Constant.KEY_MAP_NAV);
        this.listKeyConstant.add(Constant.KEY_TEL_CONTROL);
        if (!((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.OPEN_WAKEUP_FIRST), false)).booleanValue()) {
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_HOME_COMMAND_UP_DOWN), arrayList2.get(0));
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue2));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MUSIC_PLAYER_UP_DOWN), arrayList2.get(0));
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue3));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_AC_CONTROL_UP_DOWN), arrayList2.get(0));
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue4));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_VOLUME_CONTROL_UP_DOWN), arrayList2.get(0));
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue5));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MAP_NAV_UP_DOWN), arrayList2.get(0));
            arrayList2.add(Boolean.valueOf(freeWakeupWordsValue6));
            SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_TEL_CONTROL_UP_DOWN), arrayList2.get(0));
            i = 0;
            str = SharedPreferencesUtils.KEY_HOME_COMMAND_UP_DOWN;
        } else {
            i = 0;
            Context context = getContext();
            str = SharedPreferencesUtils.KEY_HOME_COMMAND_UP_DOWN;
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(context, getFreeWakeupWordsKey(str), false)).booleanValue()));
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MUSIC_PLAYER_UP_DOWN), false)).booleanValue()));
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_AC_CONTROL_UP_DOWN), false)).booleanValue()));
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_VOLUME_CONTROL_UP_DOWN), false)).booleanValue()));
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MAP_NAV_UP_DOWN), false)).booleanValue()));
            arrayList2.add(Boolean.valueOf(((Boolean) SharedPreferencesUtils.getParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_TEL_CONTROL_UP_DOWN), false)).booleanValue()));
        }
        ArrayList arrayList4 = new ArrayList();
        this.listKeyUp = arrayList4;
        arrayList4.add(getFreeWakeupWordsKey(str));
        this.listKeyUp.add(getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MUSIC_PLAYER_UP_DOWN));
        this.listKeyUp.add(getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_AC_CONTROL_UP_DOWN));
        this.listKeyUp.add(getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_VOLUME_CONTROL_UP_DOWN));
        this.listKeyUp.add(getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_MAP_NAV_UP_DOWN));
        this.listKeyUp.add(getFreeWakeupWordsKey(SharedPreferencesUtils.KEY_TEL_CONTROL_UP_DOWN));
        ArrayList arrayList5 = new ArrayList();
        this.listPopupView = arrayList5;
        arrayList5.add(this.cspv_home_command);
        this.listPopupView.add(this.cspv_music_player);
        this.listPopupView.add(this.cspv_ac_control);
        this.listPopupView.add(this.cspv_volume_control);
        this.listPopupView.add(this.cspv_map_nav);
        this.listPopupView.add(this.cspv_tel_control);
        for (int i2 = i; i2 < arrayList.size(); i2++) {
            this.listPopupView.get(i2).setSwitchStatus(((Boolean) arrayList.get(i2)).booleanValue());
            this.listPopupView.get(i2).setSwitchClickListener(this.listKeyConstant.get(i2), this.listKeyUp.get(i2));
        }
        for (int i3 = i; i3 < arrayList2.size(); i3++) {
            this.listPopupView.get(i3).setUpDownStatus(((Boolean) arrayList2.get(i3)).booleanValue(), this.listKeyUp.get(i3));
            this.listPopupView.get(i3).setUpDownClickListener(this.listKeyUp.get(i3));
        }
        SharedPreferencesUtils.setParam(getContext(), getFreeWakeupWordsKey(SharedPreferencesUtils.OPEN_WAKEUP_FIRST), true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if ((privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE") || privacyPolicyEvent.getType().equals(com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS)) && PropertiesFreeWakeupUtil.loadLanguage()) {
            setPopUpView();
            setPopUpViewContent();
        }
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        super.onConfigurationChanged(configuration);
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSwitchPrivacy(PrivacyPolicyEvent privacyPolicyEvent) {
        String type = privacyPolicyEvent.getType();
        type.hashCode();
        switch (type) {
            case "KEY_TEL_CONTROL":
            case "KEY_HOME_COMMANDS":
            case "KEY_VOLUME_CONTROL":
            case "KEY_MAP_NAV":
            case "KEY_MUSIC_PLAYER":
            case "KEY_AC_CONTROL":
                new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment.3
                    @Override // java.lang.Runnable
                    public void run() {
                        WakeupFreeFragment.this.setPopUpView();
                    }
                }, 300L);
                break;
        }
    }

    private void setLandScape() {
        for (int i = 0; i < 6; i++) {
            this.listPopupView.get(i).setTextCommandSize(16.0f);
        }
        this.set.clone(this.cl_wakeup_free);
        if (Common.isRightDriver()) {
            this.set.connect(R.id.cspv_home_command, 6, 0, 6, (int) TypedValue.applyDimension(1, 35.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.cspv_home_command, 7, 0, 7);
        } else {
            this.set.connect(R.id.cspv_home_command, 6, 0, 6);
            this.set.connect(R.id.cspv_home_command, 7, 0, 7, (int) TypedValue.applyDimension(1, 35.0f, getResources().getDisplayMetrics()));
        }
        this.set.applyTo(this.cl_wakeup_free);
        setPopUpView();
    }

    private void setPortrait() {
        for (int i = 0; i < 6; i++) {
            this.listPopupView.get(i).setTextCommandSize(16.0f);
        }
        this.set.clone(this.cl_wakeup_free);
        this.set.connect(R.id.v_wakeup_free, 3, 0, 3, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_home_command, 6, 0, 6, (int) TypedValue.applyDimension(1, 35.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_home_command, 7, 0, 7, (int) TypedValue.applyDimension(1, 35.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.cl_wakeup_free);
        setPopUpView();
    }

    private void setNight() {
        this.iv_wakeup_free_back.setImageDrawable(getResources().getDrawable(R.mipmap.btn_back_n_night, null));
        this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        for (int i = 0; i < this.listPopupView.size(); i++) {
            this.listPopupView.get(i).setBackground(null);
            this.listPopupView.get(i).setNight(this.listKeyConstant.get(i));
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_wakeup_free.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
        RLog.e("NEU_WakeupFreeFragment", "setNight: ");
        setPopUpView();
    }

    private void setLight() {
        this.iv_wakeup_free_back.setImageDrawable(getResources().getDrawable(R.mipmap.btn_back_n, null));
        this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        for (int i = 0; i < this.listPopupView.size(); i++) {
            this.listPopupView.get(i).setBackground(null);
            this.listPopupView.get(i).setLight(this.listKeyConstant.get(i));
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_wakeup_free.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
        RLog.e("NEU_WakeupFreeFragment", "setLight: ");
        setPopUpView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hideFragment(HideFragmentBean hideFragmentBean) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().hide(this).commit();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showFragment(ShowFragmentBean showFragmentBean) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().show(this).commit();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
        this.cspv_home_command.reloadMessage();
        this.cspv_music_player.reloadMessage();
        this.cspv_ac_control.reloadMessage();
        this.cspv_volume_control.reloadMessage();
        this.cspv_map_nav.reloadMessage();
        this.cspv_tel_control.reloadMessage();
    }

    private String getPrefixKey() {
        String currentLanguageName = OtaDataManager.getCurrentLanguageName();
        RLog.e("NEU_WakeupFreeFragment", "getPrefixKey:" + currentLanguageName);
        if (TextUtils.isEmpty(currentLanguageName)) {
            return BuildConfig.FLAVOR;
        }
        String[] strArrSplit = currentLanguageName.replace("_", "-").split("-");
        if (strArrSplit.length != 3) {
            return BuildConfig.FLAVOR;
        }
        if (strArrSplit[1].equals("por") || strArrSplit[1].equals("spa")) {
            return strArrSplit[1] + "_" + strArrSplit[2];
        }
        return strArrSplit[1];
    }

    private String getFreeWakeupWordsKey(String str) {
        return getPrefixKey() + "-" + str;
    }

    public boolean getFreeWakeupWordsValue(final String str) {
        Optional optionalFindAny;
        Optional<WakeUpFreeWordsSwitch.SwitchStatus> optionalFindAny2;
        final String prefixKey = getPrefixKey();
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS);
        RLog.e("NEU_WakeupFreeFragment", "language:" + prefixKey + ",key:" + str + ",getFreeWakeupWordsValue:" + string);
        List list = (List) new Gson().fromJson(string, new TypeToken<ArrayList<WakeUpFreeWordsSwitch>>() { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment.4
        }.getType());
        if (list == null || (optionalFindAny = list.stream().filter(new Predicate() { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((WakeUpFreeWordsSwitch) obj).getLanguage().equals(prefixKey);
            }
        }).findAny()) == null || !optionalFindAny.isPresent() || (optionalFindAny2 = ((WakeUpFreeWordsSwitch) optionalFindAny.get()).getSwichList().stream().filter(new Predicate() { // from class: com.byd.vrsettings.fragment.WakeupFreeFragment$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((WakeUpFreeWordsSwitch.SwitchStatus) obj).getKey().equals(str);
            }
        }).findAny()) == null || !optionalFindAny2.isPresent()) {
            return false;
        }
        RLog.e("NEU_WakeupFreeFragment", "getFreeWakeupWordsValue:" + string + ",language:" + prefixKey);
        return optionalFindAny2.get().getStatus();
    }
}
