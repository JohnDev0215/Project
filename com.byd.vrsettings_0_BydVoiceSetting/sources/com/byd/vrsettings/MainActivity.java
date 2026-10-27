package com.byd.vrsettings;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.vrsettings.activity.OtaUpgradeActivity;
import com.byd.vrsettings.eventbus.FinishUserImproveFragmentBean;
import com.byd.vrsettings.eventbus.FinishWakeupFreeFragmentBean;
import com.byd.vrsettings.eventbus.HideFragmentBean;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.ShowFragmentBean;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.fragment.SettingFragment;
import com.byd.vrsettings.fragment.SkillsFragment;
import com.byd.vrsettings.fragment.UserImproveFragment;
import com.byd.vrsettings.fragment.WakeupFreeFragment;
import com.byd.vrsettings.receiver.LogToolObserver;
import com.byd.vrsettings.service.DialogManager;
import com.byd.vrsettings.service.NotifyService;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.FastClickUtils;
import com.byd.vrsettings.utils.FragmentChangeBean;
import com.byd.vrsettings.utils.FragmentChangetoImproveBean;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.PrivacyPolicyModel;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SystemAbility;
import com.byd.vrsettings.view.CustomDialog;
import com.byd.vrsettings.view.FirstPrivacyView;
import com.byd.vrsettings.view.JoinUserImprovePrivacyView;
import com.byd.vrsettings.view.blankdoorAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button Es_btn;
    private Button Language_test_Btn;
    private Button No_btn;
    private Button Pt_btn;
    private Button VRInfoBtn;
    private TextView VRInfoText;
    private int barHeight;
    private View blankdoorBackView;
    private ConstraintLayout blankdoorBackView_layout;
    private View blankdoorView;
    private ImageView blankdoor_BtnX;
    private ConstraintLayout cl_setting;
    private Button de_btn;
    private FrameLayout fl_setting;
    private FrameLayout fl_setting_background;
    private FirstPrivacyView fv;
    private ImageView iv_appIcon;
    private ImageView iv_tab;
    private ImageView iv_voice_setting;
    private ImageView iv_voice_skills;
    private JoinUserImprovePrivacyView joinUserImprovePrivacyView;
    private TextView language_BackView;
    private LogToolObserver mLogToolReceiver;
    private RecyclerView mRecyclerView;
    private MyViewModel mViewModel;
    private View main_bottom;
    private Button privacyLogBtn;
    private String selectedItem;
    private ConstraintSet sets;
    private SettingFragment settingFragment;
    private Fragment skillsFragment;
    private Button soundRecordingBtn;
    private Button sound_begin_btn;
    private TextView tv_tabSubtitle;
    private TextView tv_voice_setting;
    private TextView tv_voice_skills;
    UserImproveFragment userImproveFragment;
    private View v_voice_setting;
    private View v_voice_skills;
    WakeupFreeFragment wakeupFreeFragment;
    private Button weatherBtn;
    private Button weatherSwitch;
    private final String TAG = "NEU_Setting_Main";
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    public final String SETTING_FRAGMENT = "setting_Fragment";
    public final String KILLS_FRAGMENT = "kills_Fragment";
    private long time = 0;
    private int count = 0;
    private int showDialogValueMain = 0;
    private Boolean isOpen = false;
    private Boolean isBeginSound = false;
    public final String Wakeup_FRAGMENT = SettingFragment.Wakeup_FRAGMENT;
    public final String UserImprove_FRAGMENT = "userImprove_Fragment";

    private void initSelect() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.DismissJoinWebViewOnChange();
            this.joinUserImprovePrivacyView.clearOnDetachJoinView();
        }
        RLog.d("NEU_Setting_Main", "Main onSaveInstanceState mViewModel.showUserImproveDialogValue==" + this.mViewModel.showUserImproveDialogValue);
        bundle.putInt("showDialogValueMain", this.mViewModel.showUserImproveDialogValue);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.byd.vrsettings.BaseActivity
    protected void reloadData() {
        RLog.e("NEU_Setting_Main", "reloadData");
        OtaDataManager.updateViewLanguage(findViewById(R.id.fl_setting_background));
        OtaDataManager.updateViewLanguage(findViewById(R.id.cl_version));
        OtaDataManager.updateViewLanguage(findViewById(R.id.cl_wakeup_phrases));
        FirstPrivacyView firstPrivacyView = this.fv;
        if (firstPrivacyView != null) {
            firstPrivacyView.updateView(this);
        }
        if (ModeUtils.isNightMode(this)) {
            RLog.i("NEU_Setting_Main", "reloadData set setNight ");
            setSubTitle(true);
        } else {
            RLog.i("NEU_Setting_Main", "reloadData set setLight ");
            setSubTitle(false);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        try {
            Thread.sleep(1000L);
            setTheme(R.style.Theme_BydVoice);
            if (bundle != null && bundle.containsKey("showDialogValueMain")) {
                this.showDialogValueMain = bundle.getInt("showDialogValueMain");
            }
            super.onCreate(bundle);
            RLog.d("NEU_Setting_Main", "Main onCreate showDialogValueMain==" + this.showDialogValueMain);
            this.mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
            getWindow().clearFlags(67108864);
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().getDecorView().setSystemUiVisibility(512);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(0);
            getWindow().clearFlags(134217728);
            getWindow().setNavigationBarColor(0);
            setContentView(R.layout.layout_setting);
            RLog.e("NEU_Setting_Main", "onCreate: ===========");
            requestPermission();
            initView();
            initSettingFragment();
            initOta();
            if (getResources().getConfiguration().orientation == 2) {
                setLandScape();
            } else {
                setPortrait();
            }
            if (ModeUtils.isNightMode(this)) {
                setNight();
            } else {
                setLight();
            }
            if (getIntent() != null) {
                String stringExtra = getIntent().getStringExtra("page");
                RLog.i("NEU_Setting_Main", "ccccccc bbbbb Value onCreate=" + stringExtra);
                if (stringExtra != null && stringExtra.equals("skill") && this.mViewModel.currentFragment == 1) {
                    this.v_voice_skills.performClick();
                    setIntent(new Intent());
                }
            }
            Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_SETTING_TAG, com.chad.library.BuildConfig.FLAVOR);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void initOta() {
        if (Common.getVehicleType().contains("Di3.0_3.0UI")) {
            return;
        }
        startForegroundService(new Intent(this, (Class<?>) NotifyService.class));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        startNeuVoiceService();
        if (OtaDataManager.isOtaShowGuide()) {
            startActivityForResult(new Intent(this, (Class<?>) OtaUpgradeActivity.class), 10001);
            return;
        }
        String string = Settings.System.getString(getContentResolver(), Constant.KEY_PRIVACY_POLICY);
        RLog.i("NEU_Setting_Main", "onResume  ===========" + string);
        if (this.fv == null) {
            if (string == null || string.length() == 0 || string.equals("false")) {
                FirstPrivacyView firstPrivacyView = new FirstPrivacyView(this, new FirstPrivacyView.clickDismiss() { // from class: com.byd.vrsettings.MainActivity.1
                    @Override // com.byd.vrsettings.view.FirstPrivacyView.clickDismiss
                    public void clickDismiss() {
                        RLog.i("NEU_Setting_Main", "Dismiss");
                        MainActivity.this.fv = null;
                    }

                    @Override // com.byd.vrsettings.view.FirstPrivacyView.clickDismiss
                    public void notAllow() {
                        EventBus.getDefault().post("SystemLanguagechange");
                    }
                });
                this.fv = firstPrivacyView;
                firstPrivacyView.show();
            } else {
                boolean zSupportNet = new SystemAbility().supportNet();
                RLog.i("NEU_Setting_Main", "new SystemAbility().supportNet():" + zSupportNet);
                RLog.i("NEU_Setting_Main", "NetUtils.isNetAvailable(getContext():" + NetUtils.isNetAvailable(BydVoiceSettingApplication.getContext()));
                RLog.i("NEU_Setting_Main", "isResumeShowNetDialog:" + DialogManager.get().isResumeShowNetDialog);
                if (zSupportNet && !NetUtils.isNetAvailable(BydVoiceSettingApplication.getContext()) && DialogManager.get().isResumeShowNetDialog) {
                    DialogManager.get().showNetTipDialog(true);
                }
            }
        }
        if (ModeUtils.isNightMode(this)) {
            RLog.i("NEU_Setting_Main", "onresume set setNight ");
            setNight();
        } else {
            RLog.i("NEU_Setting_Main", "onresume set setLight ");
            setLight();
        }
    }

    private void startNeuVoiceService() {
        RLog.d("NEU_Setting_Main", "onResume: =========== startNeuVoiceService");
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.NeuVoiceService"));
            startForegroundService(intent);
        } catch (Exception unused) {
            RLog.d("NEU_Setting_Main", "startNeuVoiceService ERROR");
        }
    }

    private void MaincreatejoinUserImprovePrivacyView() {
        RLog.d("NEU_Setting_Main", "main createjoinUserImprovePrivacyView  show join dialog");
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView == null) {
            JoinUserImprovePrivacyView joinUserImprovePrivacyView2 = new JoinUserImprovePrivacyView(this, new JoinUserImprovePrivacyView.clickConfirm() { // from class: com.byd.vrsettings.MainActivity.2
                @Override // com.byd.vrsettings.view.JoinUserImprovePrivacyView.clickConfirm
                public void clickConfirm() {
                    RLog.d("NEU_Setting_Main", "onclick   join dialog clickConfirm");
                    Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_USER_IMPROVEMENT, "true");
                    RLog.i("NEU_Setting_Main", "mainactivity  joinUserImprovePrivacy success");
                    MainActivity.this.joinUserImprovePrivacyView = null;
                }
            }, new JoinUserImprovePrivacyView.clickCancel() { // from class: com.byd.vrsettings.MainActivity.3
                @Override // com.byd.vrsettings.view.JoinUserImprovePrivacyView.clickCancel
                public void clickCancel() {
                    RLog.d("NEU_Setting_Main", "onclick   join dialog clickCancel");
                    MainActivity.this.joinUserImprovePrivacyView = null;
                }
            });
            this.joinUserImprovePrivacyView = joinUserImprovePrivacyView2;
            joinUserImprovePrivacyView2.show(1);
            return;
        }
        joinUserImprovePrivacyView.show(1);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        RLog.i("NEU_Setting_Main", "onNewIntent");
        setIntent(intent);
        String stringExtra = getIntent().getStringExtra("page");
        RLog.i("NEU_Setting_Main", "ccccccc aaaaa Value onNewIntent =" + stringExtra);
        if (stringExtra != null && stringExtra.equals("skill") && this.mViewModel.currentFragment == 1) {
            this.mViewModel.skillFragmentOnResumeforHELP = true;
            this.v_voice_skills.performClick();
            setIntent(new Intent());
        }
    }

    private void initLogTool() {
        RLog.i("NEU_Setting_Main", "initLogTool: ");
        this.mLogToolReceiver = new LogToolObserver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.action.OpenVRSettingsLogSwitch");
        intentFilter.addAction("bydlogtool.action.savelogresult");
        registerReceiver(this.mLogToolReceiver, intentFilter);
    }

    public void requestPermission() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.permissions) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // com.byd.vrsettings.BaseActivity
    protected void onConfigChangeUI(Configuration configuration) {
        RLog.i("NEU_Setting_Main", "onConfigurationChanged begin");
        if (configuration.orientation == 2) {
            RLog.i("NEU_Setting_Main", "Configuration.ORIENTATION_LANDSCAPE: ");
            setLandScape();
        } else if (configuration.orientation == 1) {
            RLog.i("NEU_Setting_Main", "Configuration.ORIENTATION_PORTRAIT ");
            setPortrait();
        }
        if (ModeUtils.isNightMode(this)) {
            RLog.i("NEU_Setting_Main", "Configuration.UI_MODE_NIGHT_YES");
            setNight();
        } else {
            RLog.i("NEU_Setting_Main", "Configuration.UI_MODE_NIGHT_NO");
            setLight();
        }
    }

    private void setLandScape() {
        this.fl_setting.setLayoutParams(new ConstraintLayout.LayoutParams(0, 0));
        this.iv_tab.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 249.0f, getResources().getDisplayMetrics()), 0));
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 208.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 64.0f, getResources().getDisplayMetrics()));
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 208.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 64.0f, getResources().getDisplayMetrics()));
        this.v_voice_setting.setLayoutParams(layoutParams);
        this.v_voice_skills.setLayoutParams(layoutParams2);
        if (this.selectedItem.equals("voice_setting")) {
            if (ModeUtils.isNightMode(this)) {
                if (Common.isRightDriver()) {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                } else {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                }
            } else if (Common.isRightDriver()) {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
            } else {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
            }
        }
        if (this.selectedItem.equals("voice_skills")) {
            if (ModeUtils.isNightMode(this)) {
                if (Common.isRightDriver()) {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                } else {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                }
            } else if (Common.isRightDriver()) {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
            } else {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
            }
        }
        this.sets.clone(this.cl_setting);
        this.sets.connect(R.id.iv_appIcon, 3, R.id.iv_tab, 3, (int) TypedValue.applyDimension(1, 17.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.iv_appIcon, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 62.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.iv_appIcon, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 91.0f, getResources().getDisplayMetrics()));
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.iv_appIcon.getLayoutParams();
        layoutParams3.width = (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics());
        layoutParams3.height = (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics());
        this.iv_appIcon.setLayoutParams(layoutParams3);
        this.sets.connect(R.id.tv_tabSubtitle, 3, R.id.iv_appIcon, 4, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_tabSubtitle, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 51.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_tabSubtitle, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 79.0f, getResources().getDisplayMetrics()));
        if (Common.isRightDriver()) {
            this.sets.connect(R.id.fl_setting, 7, R.id.iv_tab, 6);
            this.sets.connect(R.id.fl_setting, 3, R.id.cl_setting, 3);
            this.sets.connect(R.id.fl_setting, 6, 0, 6);
            this.sets.connect(R.id.fl_setting, 4, R.id.main_bottom, 3);
            this.sets.clear(R.id.iv_tab, 6);
            this.sets.connect(R.id.iv_tab, 7, 0, 7);
            this.sets.connect(R.id.iv_tab, 3, R.id.cl_setting, 3);
            this.sets.connect(R.id.iv_tab, 4, R.id.main_bottom, 3);
            this.sets.connect(R.id.v_voice_setting, 3, R.id.tv_tabSubtitle, 4, (int) TypedValue.applyDimension(1, 51.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.v_voice_setting, 7, R.id.iv_tab, 7);
            this.sets.connect(R.id.v_voice_skills, 3, R.id.v_voice_setting, 4, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.v_voice_skills, 7, R.id.iv_tab, 7);
            this.sets.clear(R.id.iv_voice_setting, 6);
            this.sets.connect(R.id.iv_voice_setting, 7, R.id.v_voice_setting, 7, (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_voice_setting, 3, R.id.v_voice_setting, 3);
            this.sets.connect(R.id.iv_voice_setting, 4, R.id.v_voice_setting, 4);
            this.sets.connect(R.id.tv_voice_setting, 6, R.id.v_voice_setting, 6, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_voice_setting, 3, R.id.iv_voice_setting, 3);
            this.sets.connect(R.id.tv_voice_setting, 4, R.id.iv_voice_setting, 4);
            this.sets.connect(R.id.tv_voice_setting, 7, R.id.iv_voice_setting, 6);
            this.sets.clear(R.id.iv_voice_skills, 6);
            this.sets.connect(R.id.iv_voice_skills, 7, R.id.v_voice_skills, 7, (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_voice_skills, 3, R.id.v_voice_skills, 3);
            this.sets.connect(R.id.iv_voice_skills, 4, R.id.v_voice_skills, 4);
            this.sets.connect(R.id.tv_voice_skills, 6, R.id.v_voice_skills, 6, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_voice_skills, 3, R.id.iv_voice_skills, 3);
            this.sets.connect(R.id.tv_voice_skills, 4, R.id.iv_voice_skills, 4);
            this.sets.connect(R.id.tv_voice_skills, 7, R.id.iv_voice_skills, 6);
            this.sets.connect(R.id.iv_appIcon, 3, R.id.iv_tab, 3, (int) TypedValue.applyDimension(1, 17.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 91.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 62.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 3, R.id.iv_appIcon, 4, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 79.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 51.0f, getResources().getDisplayMetrics()));
        } else {
            this.sets.connect(R.id.fl_setting, 6, R.id.iv_tab, 7);
            this.sets.connect(R.id.fl_setting, 3, R.id.cl_setting, 3);
            this.sets.connect(R.id.fl_setting, 7, 0, 7);
            this.sets.connect(R.id.fl_setting, 4, R.id.main_bottom, 3);
            this.sets.clear(R.id.iv_tab, 7);
            this.sets.connect(R.id.iv_tab, 6, 0, 6);
            this.sets.connect(R.id.iv_tab, 3, R.id.cl_setting, 3);
            this.sets.connect(R.id.iv_tab, 4, R.id.main_bottom, 3);
            this.sets.clear(R.id.v_voice_setting, 4);
            this.sets.connect(R.id.v_voice_setting, 3, R.id.tv_tabSubtitle, 4, (int) TypedValue.applyDimension(1, 51.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.v_voice_setting, 6, R.id.iv_tab, 6);
            this.sets.clear(R.id.v_voice_skills, 4);
            this.sets.connect(R.id.v_voice_skills, 3, R.id.v_voice_setting, 4, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.v_voice_skills, 6, R.id.iv_tab, 6);
            this.sets.connect(R.id.v_voice_skills, 7, R.id.v_voice_setting, 7);
            this.sets.clear(R.id.iv_voice_setting, 7);
            this.sets.connect(R.id.iv_voice_setting, 6, R.id.v_voice_setting, 6, (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_voice_setting, 3, R.id.v_voice_setting, 3);
            this.sets.connect(R.id.iv_voice_setting, 4, R.id.v_voice_setting, 4);
            this.sets.connect(R.id.tv_voice_setting, 6, R.id.iv_voice_setting, 7, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_voice_setting, 3, R.id.iv_voice_setting, 3);
            this.sets.connect(R.id.tv_voice_setting, 4, R.id.iv_voice_setting, 4);
            this.sets.connect(R.id.tv_voice_setting, 7, R.id.v_voice_setting, 7);
            this.sets.clear(R.id.iv_voice_skills, 7);
            this.sets.connect(R.id.iv_voice_skills, 6, R.id.v_voice_skills, 6, (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_voice_skills, 3, R.id.v_voice_skills, 3);
            this.sets.connect(R.id.iv_voice_skills, 4, R.id.v_voice_skills, 4);
            this.sets.connect(R.id.tv_voice_skills, 6, R.id.iv_voice_skills, 7, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_voice_skills, 3, R.id.iv_voice_skills, 3);
            this.sets.connect(R.id.tv_voice_skills, 4, R.id.iv_voice_skills, 4);
            this.sets.connect(R.id.tv_voice_skills, 7, R.id.v_voice_skills, 7);
            this.sets.connect(R.id.iv_appIcon, 3, R.id.iv_tab, 3, (int) TypedValue.applyDimension(1, 17.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 62.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 91.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 3, R.id.iv_appIcon, 4, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 51.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 79.0f, getResources().getDisplayMetrics()));
        }
        this.sets.applyTo(this.cl_setting);
        this.tv_voice_skills.setGravity(3);
        this.tv_voice_setting.setGravity(3);
    }

    private void setPortrait() {
        this.fl_setting.setLayoutParams(new ConstraintLayout.LayoutParams(-1, 0));
        this.iv_tab.setLayoutParams(new ConstraintLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 172.0f, getResources().getDisplayMetrics())));
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 169.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics()));
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 169.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 96.0f, getResources().getDisplayMetrics()));
        this.v_voice_setting.setLayoutParams(layoutParams);
        this.v_voice_skills.setLayoutParams(layoutParams2);
        if (this.selectedItem.equals("voice_setting")) {
            if (ModeUtils.isNightMode(this)) {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
            } else {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
            }
        }
        if (this.selectedItem.equals("voice_skills")) {
            if (ModeUtils.isNightMode(this)) {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
            } else {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
            }
        }
        this.sets.clone(this.cl_setting);
        if (Common.isRightDriver()) {
            this.sets.clear(R.id.iv_appIcon, 6);
            this.sets.connect(R.id.iv_appIcon, 3, R.id.iv_tab, 3, (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 7, R.id.iv_tab, 7, (int) TypedValue.applyDimension(1, 56.0f, getResources().getDisplayMetrics()));
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.iv_appIcon.getLayoutParams();
            layoutParams3.width = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
            layoutParams3.height = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
            this.iv_appIcon.setLayoutParams(layoutParams3);
            this.sets.clear(R.id.tv_tabSubtitle, 6);
            this.sets.connect(R.id.tv_tabSubtitle, 3, R.id.iv_appIcon, 3, (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 7, R.id.iv_appIcon, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        } else {
            this.sets.clear(R.id.iv_appIcon, 7);
            this.sets.connect(R.id.iv_appIcon, 3, R.id.iv_tab, 3, (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.iv_appIcon, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 56.0f, getResources().getDisplayMetrics()));
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) this.iv_appIcon.getLayoutParams();
            layoutParams4.width = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
            layoutParams4.height = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
            this.iv_appIcon.setLayoutParams(layoutParams4);
            this.sets.clear(R.id.tv_tabSubtitle, 7);
            this.sets.connect(R.id.tv_tabSubtitle, 3, R.id.iv_appIcon, 3, (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
            this.sets.connect(R.id.tv_tabSubtitle, 6, R.id.iv_appIcon, 7, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        }
        this.sets.connect(R.id.fl_setting, 3, R.id.v_voice_setting, 4);
        this.sets.connect(R.id.fl_setting, 6, 0, 6);
        this.sets.connect(R.id.fl_setting, 7, 0, 7);
        this.sets.connect(R.id.fl_setting, 4, R.id.main_bottom, 3);
        this.sets.clear(R.id.iv_tab, 4);
        this.sets.connect(R.id.iv_tab, 7, 0, 7);
        this.sets.connect(R.id.iv_tab, 3, R.id.cl_setting, 3, (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.iv_tab, 6, 0, 6);
        this.sets.clear(R.id.v_voice_setting, 3);
        this.sets.connect(R.id.v_voice_setting, 6, R.id.iv_tab, 6, (int) TypedValue.applyDimension(1, 151.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.v_voice_setting, 4, R.id.iv_tab, 4, (int) TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics()));
        this.sets.clear(R.id.iv_voice_setting, 4);
        this.sets.clear(R.id.iv_voice_setting, 6);
        this.sets.clear(R.id.iv_voice_setting, 3);
        this.sets.clear(R.id.iv_voice_setting, 7);
        this.sets.connect(R.id.iv_voice_setting, 6, R.id.v_voice_setting, 6);
        this.sets.connect(R.id.iv_voice_setting, 7, R.id.v_voice_setting, 7);
        this.sets.connect(R.id.iv_voice_setting, 3, R.id.v_voice_setting, 3);
        this.sets.clear(R.id.tv_voice_setting, 3);
        this.sets.clear(R.id.tv_voice_setting, 4);
        this.sets.connect(R.id.tv_voice_setting, 3, R.id.iv_voice_setting, 4, (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_voice_setting, 6, R.id.v_voice_setting, 6, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_voice_setting, 7, R.id.v_voice_setting, 7, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.tv_voice_setting.setGravity(17);
        this.sets.clear(R.id.v_voice_skills, 7);
        this.sets.connect(R.id.v_voice_skills, 6, R.id.v_voice_setting, 7, (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.v_voice_skills, 3, R.id.v_voice_setting, 3);
        this.sets.connect(R.id.v_voice_skills, 4, R.id.v_voice_setting, 4);
        this.sets.clear(R.id.iv_voice_skills, 4);
        this.sets.clear(R.id.iv_voice_skills, 6);
        this.sets.clear(R.id.iv_voice_skills, 3);
        this.sets.connect(R.id.iv_voice_skills, 6, R.id.v_voice_skills, 6);
        this.sets.connect(R.id.iv_voice_skills, 7, R.id.v_voice_skills, 7);
        this.sets.connect(R.id.iv_voice_skills, 3, R.id.v_voice_skills, 3);
        this.sets.clear(R.id.tv_voice_skills, 3);
        this.sets.clear(R.id.tv_voice_skills, 4);
        this.sets.connect(R.id.tv_voice_skills, 6, R.id.v_voice_skills, 6, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_voice_skills, 3, R.id.iv_voice_skills, 4, (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
        this.sets.connect(R.id.tv_voice_skills, 7, R.id.v_voice_skills, 7, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.tv_voice_skills.setGravity(17);
        this.sets.applyTo(this.cl_setting);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setSubTitle(boolean r11) {
        /*
            Method dump skipped, instruction units count: 653
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrsettings.MainActivity.setSubTitle(boolean):void");
    }

    private void setNight() {
        setSubTitle(true);
        if (getResources().getConfiguration().orientation == 2) {
            this.fl_setting_background.setBackground(getResources().getDrawable(R.mipmap.img_light_app_bg_h_night, null));
            if (this.selectedItem.equals("voice_setting")) {
                if (Common.isRightDriver()) {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                } else {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                }
                this.v_voice_skills.setBackground(null);
            }
            if (this.selectedItem.equals("voice_skills")) {
                if (Common.isRightDriver()) {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                } else {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                }
                this.v_voice_setting.setBackground(null);
            }
        } else {
            this.fl_setting_background.setBackground(getResources().getDrawable(R.mipmap.img_light_app_bg_h_vertical_night, null));
            if (this.selectedItem.equals("voice_setting")) {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
                this.v_voice_skills.setBackground(null);
            }
            if (this.selectedItem.equals("voice_skills")) {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
                this.v_voice_setting.setBackground(null);
            }
        }
        this.iv_appIcon.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144_night, null));
        if (this.selectedItem.equals("voice_setting")) {
            this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
            this.iv_voice_setting.setImageResource(R.mipmap.voice_setting_selected);
            this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
            this.iv_voice_skills.setImageResource(R.mipmap.voice_skills_night);
        } else {
            this.iv_voice_skills.setImageResource(R.mipmap.voice_skills_selected);
            this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
            this.iv_voice_setting.setImageResource(R.mipmap.voice_setting_night);
            this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
        }
        FirstPrivacyView firstPrivacyView = this.fv;
        if (firstPrivacyView != null) {
            firstPrivacyView.setNight();
        }
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setNight();
        }
        ConstraintLayout constraintLayout = this.blankdoorBackView_layout;
        if (constraintLayout != null) {
            constraintLayout.setBackgroundColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.dialog_backgroud_color_night, null));
        }
    }

    private void setLight() {
        setSubTitle(false);
        if (getResources().getConfiguration().orientation == 2) {
            this.fl_setting_background.setBackground(getResources().getDrawable(R.mipmap.img_light_app_bg_h, null));
            if (this.selectedItem.equals("voice_setting")) {
                if (Common.isRightDriver()) {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
                } else {
                    this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
                }
                this.v_voice_skills.setBackground(null);
            }
            if (this.selectedItem.equals("voice_skills")) {
                if (Common.isRightDriver()) {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
                } else {
                    this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
                }
                this.v_voice_setting.setBackground(null);
            }
        } else {
            this.fl_setting_background.setBackground(getResources().getDrawable(R.mipmap.img_light_app_bg_h_vertical, null));
            if (this.selectedItem.equals("voice_setting")) {
                this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
                this.v_voice_skills.setBackground(null);
            }
            if (this.selectedItem.equals("voice_skills")) {
                this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
                this.v_voice_setting.setBackground(null);
            }
        }
        this.iv_appIcon.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144, null));
        if (this.selectedItem.equals("voice_setting")) {
            this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_title85_8155, null));
            this.iv_voice_skills.setImageResource(R.mipmap.voice_skills);
            this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
            this.iv_voice_setting.setImageResource(R.mipmap.voice_setting_selected);
        } else {
            this.iv_voice_skills.setImageResource(R.mipmap.voice_skills_selected);
            this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
            this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_title85_8155, null));
            this.iv_voice_setting.setImageResource(R.mipmap.voice_setting);
        }
        FirstPrivacyView firstPrivacyView = this.fv;
        if (firstPrivacyView != null) {
            firstPrivacyView.setLight();
        }
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setLight();
        }
        ConstraintLayout constraintLayout = this.blankdoorBackView_layout;
        if (constraintLayout != null) {
            constraintLayout.setBackgroundColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.dialog_backgroud_color_day, null));
        }
    }

    private void initSettingFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        hideFragment(fragmentTransactionBeginTransaction);
        RLog.i("NEU_Setting_Main", "initSettingFragment: mViewModel.currentFragment" + this.mViewModel.currentFragment);
        if (this.mViewModel.currentFragment == 1) {
            RLog.i("NEU_Setting_Main", "initSettingFragment: mViewModel.isShowWakeupFragment" + this.mViewModel.isShowWakeupFragment);
            if (this.mViewModel.isShowWakeupFragment) {
                WakeupFreeFragment wakeupFreeFragment = (WakeupFreeFragment) supportFragmentManager.findFragmentByTag(SettingFragment.Wakeup_FRAGMENT);
                if (wakeupFreeFragment == null) {
                    WakeupFreeFragment wakeupFreeFragment2 = new WakeupFreeFragment();
                    this.wakeupFreeFragment = wakeupFreeFragment2;
                    fragmentTransactionBeginTransaction.add(R.id.fl_setting, wakeupFreeFragment2, SettingFragment.Wakeup_FRAGMENT);
                } else {
                    this.wakeupFreeFragment = wakeupFreeFragment;
                }
                fragmentTransactionBeginTransaction.show(this.wakeupFreeFragment);
            } else if (this.mViewModel.isShowUserImproveFragment) {
                UserImproveFragment userImproveFragment = (UserImproveFragment) supportFragmentManager.findFragmentByTag("userImprove_Fragment");
                if (userImproveFragment == null) {
                    UserImproveFragment userImproveFragment2 = new UserImproveFragment();
                    this.userImproveFragment = userImproveFragment2;
                    fragmentTransactionBeginTransaction.add(R.id.fl_setting, userImproveFragment2, "userImprove_Fragment");
                } else {
                    this.userImproveFragment = userImproveFragment;
                }
                fragmentTransactionBeginTransaction.show(this.userImproveFragment);
            } else {
                SettingFragment settingFragment = (SettingFragment) supportFragmentManager.findFragmentByTag("setting_Fragment");
                if (settingFragment == null) {
                    SettingFragment settingFragment2 = new SettingFragment();
                    this.settingFragment = settingFragment2;
                    fragmentTransactionBeginTransaction.add(R.id.fl_setting, settingFragment2, "setting_Fragment");
                    RLog.e("NEU_Setting_Main", "SettingFragment======transaction.add===new SettingFragment()");
                } else {
                    this.settingFragment = settingFragment;
                    fragmentTransactionBeginTransaction.show(settingFragment);
                    RLog.e("NEU_Setting_Main", "SettingFragment======transaction.add===findFragmentByTag");
                }
            }
        } else {
            Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag("kills_Fragment");
            if (fragmentFindFragmentByTag == null) {
                Fragment skillsFragment = new SkillsFragment();
                this.skillsFragment = skillsFragment;
                fragmentTransactionBeginTransaction.add(R.id.fl_setting, skillsFragment, "kills_Fragment");
            } else {
                this.skillsFragment = fragmentFindFragmentByTag;
                fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
            }
        }
        fragmentTransactionBeginTransaction.commit();
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.sets = new ConstraintSet();
        this.fl_setting = (FrameLayout) findViewById(R.id.fl_setting);
        this.cl_setting = (ConstraintLayout) findViewById(R.id.cl_setting);
        this.fl_setting_background = (FrameLayout) findViewById(R.id.fl_setting_background);
        this.main_bottom = findViewById(R.id.main_bottom);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.cl_setting.getLayoutParams();
        layoutParams.topMargin = 84;
        this.cl_setting.setLayoutParams(layoutParams);
        this.v_voice_setting = findViewById(R.id.v_voice_setting);
        this.iv_voice_setting = (ImageView) findViewById(R.id.iv_voice_setting);
        this.tv_voice_setting = (TextView) findViewById(R.id.tv_voice_setting);
        this.v_voice_skills = findViewById(R.id.v_voice_skills);
        this.iv_voice_skills = (ImageView) findViewById(R.id.iv_voice_skills);
        this.tv_voice_skills = (TextView) findViewById(R.id.tv_voice_skills);
        this.v_voice_setting.setOnClickListener(this);
        this.v_voice_skills.setOnClickListener(this);
        this.iv_tab = (ImageView) findViewById(R.id.iv_tab);
        ImageView imageView = (ImageView) findViewById(R.id.iv_appIcon);
        this.iv_appIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.continuousClick();
            }
        });
        this.tv_tabSubtitle = (TextView) findViewById(R.id.tv_tabSubtitle);
        if (this.mViewModel.tabItemValue == 0) {
            this.selectedItem = "voice_setting";
        } else {
            this.selectedItem = "voice_skills";
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (FastClickUtils.isFastClick()) {
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        hideFragment(fragmentTransactionBeginTransaction);
        initColor();
        switch (view.getId()) {
            case R.id.v_voice_setting /* 2131296910 */:
                if (this.mViewModel.isShowWakeupFragment) {
                    WakeupFreeFragment wakeupFreeFragment = (WakeupFreeFragment) supportFragmentManager.findFragmentByTag(SettingFragment.Wakeup_FRAGMENT);
                    if (wakeupFreeFragment == null) {
                        WakeupFreeFragment wakeupFreeFragment2 = new WakeupFreeFragment();
                        this.wakeupFreeFragment = wakeupFreeFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_setting, wakeupFreeFragment2, SettingFragment.Wakeup_FRAGMENT);
                    } else {
                        this.wakeupFreeFragment = wakeupFreeFragment;
                    }
                    fragmentTransactionBeginTransaction.show(this.wakeupFreeFragment);
                } else if (this.mViewModel.isShowUserImproveFragment) {
                    UserImproveFragment userImproveFragment = (UserImproveFragment) supportFragmentManager.findFragmentByTag("userImprove_Fragment");
                    if (userImproveFragment == null) {
                        UserImproveFragment userImproveFragment2 = new UserImproveFragment();
                        this.userImproveFragment = userImproveFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_setting, userImproveFragment2, "userImprove_Fragment");
                    } else {
                        this.userImproveFragment = userImproveFragment;
                    }
                    fragmentTransactionBeginTransaction.show(this.userImproveFragment);
                } else {
                    SettingFragment settingFragment = (SettingFragment) supportFragmentManager.findFragmentByTag("setting_Fragment");
                    if (settingFragment == null) {
                        SettingFragment settingFragment2 = new SettingFragment();
                        this.settingFragment = settingFragment2;
                        fragmentTransactionBeginTransaction.add(R.id.fl_setting, settingFragment2, "setting_Fragment");
                    } else {
                        this.settingFragment = settingFragment;
                        fragmentTransactionBeginTransaction.show(settingFragment);
                    }
                }
                this.mViewModel.currentFragment = 1;
                Settings.System.putString(((BydVoiceSettingApplication) Objects.requireNonNull(BydVoiceSettingApplication.getContext())).getContentResolver(), Constant.KEY_SETTING_TAG, com.chad.library.BuildConfig.FLAVOR);
                if (getResources().getConfiguration().orientation == 2) {
                    if (ModeUtils.isNightMode(this)) {
                        if (Common.isRightDriver()) {
                            this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                        } else {
                            this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                        }
                    } else if (Common.isRightDriver()) {
                        this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
                    } else {
                        this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
                    }
                } else if (getResources().getConfiguration().orientation == 1) {
                    if (ModeUtils.isNightMode(this)) {
                        this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
                    } else {
                        this.v_voice_setting.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
                    }
                }
                this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
                this.iv_voice_setting.setImageResource(R.mipmap.voice_setting_selected);
                EventBus.getDefault().post(new ShowFragmentBean());
                this.selectedItem = "voice_setting";
                this.mViewModel.tabItemValue = 0;
                break;
            case R.id.v_voice_skills /* 2131296911 */:
                Fragment fragment = (SkillsFragment) supportFragmentManager.findFragmentByTag("kills_Fragment");
                if (fragment == null) {
                    RLog.d("NEU_Setting_Main", "onclick skills fragment is null");
                    this.mViewModel.skillFragmentOnResume = false;
                    Fragment skillsFragment = new SkillsFragment();
                    this.skillsFragment = skillsFragment;
                    fragmentTransactionBeginTransaction.add(R.id.fl_setting, skillsFragment, "kills_Fragment");
                } else {
                    RLog.d("NEU_Setting_Main", "onclick skills fragment is not null");
                    this.mViewModel.skillFragmentOnResume = true;
                    this.skillsFragment = fragment;
                    fragmentTransactionBeginTransaction.show(fragment);
                }
                this.mViewModel.currentFragment = 2;
                Settings.System.putString(((BydVoiceSettingApplication) Objects.requireNonNull(BydVoiceSettingApplication.getContext())).getContentResolver(), Constant.KEY_SETTING_TAG, "skills");
                if (getResources().getConfiguration().orientation == 2) {
                    if (ModeUtils.isNightMode(this)) {
                        if (Common.isRightDriver()) {
                            this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night_right, null));
                        } else {
                            this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_night, null));
                        }
                    } else if (Common.isRightDriver()) {
                        this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_right, null));
                    } else {
                        this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected, null));
                    }
                } else if (getResources().getConfiguration().orientation == 1) {
                    if (ModeUtils.isNightMode(this)) {
                        this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical_night, null));
                    } else {
                        this.v_voice_skills.setBackground(getResources().getDrawable(R.mipmap.bg_primary_navigation_selected_vertical, null));
                    }
                }
                this.iv_voice_skills.setImageResource(R.mipmap.voice_skills_selected);
                this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_text_selected_8155, null));
                EventBus.getDefault().post(new HideFragmentBean());
                this.selectedItem = "voice_skills";
                this.mViewModel.tabItemValue = 1;
                break;
        }
        fragmentTransactionBeginTransaction.commit();
    }

    private void initColor() {
        if (ModeUtils.isNightMode(this)) {
            this.v_voice_setting.setBackground(null);
            this.iv_voice_setting.setImageResource(R.mipmap.voice_setting_night);
            this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.v_voice_skills.setBackground(null);
            this.iv_voice_skills.setImageResource(R.mipmap.voice_skills_night);
            this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            return;
        }
        this.v_voice_setting.setBackground(null);
        this.iv_voice_setting.setImageResource(R.mipmap.voice_setting);
        this.tv_voice_setting.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.v_voice_skills.setBackground(null);
        this.iv_voice_skills.setImageResource(R.mipmap.voice_skills);
        this.tv_voice_skills.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        SettingFragment settingFragment = this.settingFragment;
        if (settingFragment != null) {
            fragmentTransaction.hide(settingFragment);
        }
        Fragment fragment = this.skillsFragment;
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        RLog.i("NEU_Setting_Main", "onDestroy: begin");
        super.onDestroy();
        this.mViewModel.skillFragmentOnResumeforHELP = false;
        this.mViewModel.skillFragmentOnResume = false;
        this.settingFragment = null;
        this.skillsFragment = null;
        this.wakeupFreeFragment = null;
        this.blankdoorView = null;
        DialogManager.get().isResumeShowNetDialog = true;
        EventBus.getDefault().unregister(this);
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            if (joinUserImprovePrivacyView.isShowing()) {
                this.joinUserImprovePrivacyView.dismiss();
            }
            this.joinUserImprovePrivacyView = null;
        }
    }

    private void removeOldFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag("setting_Fragment");
        if (fragmentFindFragmentByTag != null) {
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
        }
        Fragment fragmentFindFragmentByTag2 = supportFragmentManager.findFragmentByTag("kills_Fragment");
        if (fragmentFindFragmentByTag2 != null) {
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag2);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continuousClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        RLog.i("NEU_Setting_Main", "continuousClick: 连续点击");
        if (jElapsedRealtime - this.time < 2000) {
            this.count++;
        } else {
            this.count = 0;
        }
        this.time = jElapsedRealtime;
        if (this.count >= 19) {
            RLog.i("NEU_Setting_Main", "continuousClick: 连续点击，开启暗门");
            createBlankdoorDialog();
        }
    }

    private void createBlankdoorDialog() {
        List arrayList;
        if (this.isOpen.booleanValue()) {
            return;
        }
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_privacy_blankdoor, (ViewGroup) null);
        this.blankdoorView = viewInflate;
        this.blankdoorBackView_layout = (ConstraintLayout) viewInflate.findViewById(R.id.blankdoorBackView_layout);
        final CustomDialog customDialog = new CustomDialog(this, this.blankdoorView);
        customDialog.setCanceledOnTouchOutside(false);
        this.blankdoorBackView = this.blankdoorView.findViewById(R.id.blankdoorBackView);
        this.blankdoor_BtnX = (ImageView) this.blankdoorView.findViewById(R.id.blankdoor_BtnX);
        this.mRecyclerView = (RecyclerView) this.blankdoorView.findViewById(R.id.mRecyclerView);
        this.weatherBtn = (Button) this.blankdoorView.findViewById(R.id.weatherBtn);
        this.weatherSwitch = (Button) this.blankdoorView.findViewById(R.id.weatherSwitch);
        this.privacyLogBtn = (Button) this.blankdoorView.findViewById(R.id.privacyLogBtn);
        this.soundRecordingBtn = (Button) this.blankdoorView.findViewById(R.id.soundRecordingBtn);
        this.sound_begin_btn = (Button) this.blankdoorView.findViewById(R.id.sound_begin_btn);
        this.VRInfoBtn = (Button) this.blankdoorView.findViewById(R.id.VRInfoBtn);
        this.VRInfoText = (TextView) this.blankdoorView.findViewById(R.id.VRInfoText);
        this.Language_test_Btn = (Button) this.blankdoorView.findViewById(R.id.Language_test_Btn);
        this.language_BackView = (TextView) this.blankdoorView.findViewById(R.id.language_BackView);
        this.de_btn = (Button) this.blankdoorView.findViewById(R.id.de_btn);
        this.No_btn = (Button) this.blankdoorView.findViewById(R.id.No_btn);
        this.Pt_btn = (Button) this.blankdoorView.findViewById(R.id.Pt_btn);
        this.Es_btn = (Button) this.blankdoorView.findViewById(R.id.Es_btn);
        this.privacyLogBtn.setSelected(true);
        this.sound_begin_btn.setSelected(false);
        this.weatherBtn.setSelected(false);
        this.weatherSwitch.setSelected(false);
        this.VRInfoBtn.setSelected(false);
        this.Language_test_Btn.setSelected(false);
        this.privacyLogBtn.setTextColor(getResources().getColor(R.color.black, null));
        this.soundRecordingBtn.setTextColor(getResources().getColor(R.color.bg_dialog_grey, null));
        this.weatherBtn.setTextColor(getResources().getColor(R.color.bg_dialog_grey, null));
        this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
        this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
        if (ModeUtils.isNightMode(this)) {
            RLog.i("NEU_Setting_Main", "Configuration.UI_MODE_NIGHT_YES");
            this.blankdoorBackView_layout.setBackgroundColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.dialog_backgroud_color_night, null));
        } else {
            RLog.i("NEU_Setting_Main", "Configuration.UI_MODE_NIGHT_NO");
            this.blankdoorBackView_layout.setBackgroundColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.dialog_backgroud_color_day, null));
        }
        this.privacyLogBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.mRecyclerView.setVisibility(0);
                MainActivity.this.sound_begin_btn.setVisibility(8);
                MainActivity.this.weatherSwitch.setVisibility(8);
                MainActivity.this.VRInfoText.setVisibility(8);
                MainActivity.this.language_BackView.setVisibility(8);
                view.setSelected(true);
                MainActivity.this.VRInfoBtn.setSelected(false);
                MainActivity.this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.privacyLogBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.black, null));
                MainActivity.this.weatherBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setSelected(false);
                MainActivity.this.Language_test_Btn.setSelected(false);
                MainActivity.this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.weatherBtn.setSelected(false);
                if (MainActivity.this.isBeginSound.booleanValue()) {
                    MainActivity.this.beginSoundRecording(false);
                }
            }
        });
        this.soundRecordingBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(true);
                MainActivity.this.weatherBtn.setSelected(false);
                MainActivity.this.privacyLogBtn.setSelected(false);
                MainActivity.this.VRInfoBtn.setSelected(false);
                MainActivity.this.Language_test_Btn.setSelected(false);
                MainActivity.this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.privacyLogBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.weatherBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.black, null));
                MainActivity.this.mRecyclerView.setVisibility(8);
                MainActivity.this.weatherSwitch.setVisibility(8);
                MainActivity.this.VRInfoText.setVisibility(8);
                MainActivity.this.language_BackView.setVisibility(8);
                MainActivity.this.sound_begin_btn.setVisibility(0);
            }
        });
        this.weatherBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_Setting_Main", "onClick: 天气");
                view.setSelected(true);
                MainActivity.this.privacyLogBtn.setSelected(false);
                MainActivity.this.privacyLogBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setSelected(false);
                MainActivity.this.VRInfoBtn.setSelected(false);
                MainActivity.this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.Language_test_Btn.setSelected(false);
                MainActivity.this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.weatherBtn.setTextColor(MainActivity.this.getResources().getColor(R.color.black, null));
                MainActivity.this.mRecyclerView.setVisibility(8);
                MainActivity.this.sound_begin_btn.setVisibility(8);
                MainActivity.this.VRInfoText.setVisibility(8);
                MainActivity.this.language_BackView.setVisibility(8);
                MainActivity.this.weatherSwitch.setVisibility(0);
                if (MainActivity.this.isBeginSound.booleanValue()) {
                    MainActivity.this.beginSoundRecording(false);
                }
            }
        });
        this.VRInfoBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(true);
                MainActivity.this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.black, null));
                MainActivity.this.privacyLogBtn.setSelected(false);
                MainActivity.this.privacyLogBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setSelected(false);
                MainActivity.this.soundRecordingBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.weatherBtn.setSelected(false);
                MainActivity.this.weatherBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.Language_test_Btn.setSelected(false);
                MainActivity.this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.mRecyclerView.setVisibility(8);
                MainActivity.this.weatherSwitch.setVisibility(8);
                MainActivity.this.sound_begin_btn.setVisibility(8);
                MainActivity.this.language_BackView.setVisibility(8);
                MainActivity.this.VRInfoText.setVisibility(0);
                RLog.i("NEU_Setting_Main", "VR loadLanguage: " + Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE"));
                String countryCode = BydVoiceSettingApplication.getCountryCode();
                RLog.i("NEU_Setting_Main", "VR countryCode: " + countryCode);
                String currentLanguageName = OtaDataManager.getCurrentLanguageName();
                String currentLanguagePath = OtaDataManager.getCurrentLanguagePath();
                String currentLanguageVersion = OtaDataManager.getCurrentLanguageVersion(currentLanguageName);
                String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_COMPATIBLE_PATH");
                String str = com.chad.library.BuildConfig.FLAVOR;
                if (string == null) {
                    string = com.chad.library.BuildConfig.FLAVOR;
                }
                String string2 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_VOICE_SUFFIX");
                if (string2 != null) {
                    str = string2;
                }
                RLog.i("NEU_Setting_Main", "VR languageName: " + currentLanguageName + " VR path: " + currentLanguagePath + " VR version: " + currentLanguageVersion + " KEY_COMPATIBLE_PATH: " + string + " KEY_VOICE_SUFFIX: " + str);
                MainActivity.this.VRInfoText.setText("VR countryCode: " + countryCode + "\nVR languageName: " + currentLanguageName + "\nVR path: " + currentLanguagePath + "\nVR_DP version: " + currentLanguageVersion + "\nCompatible_Path: " + string + "\nVOICE_SUFFIX: " + str);
                if (MainActivity.this.isBeginSound.booleanValue()) {
                    MainActivity.this.beginSoundRecording(false);
                }
            }
        });
        this.weatherSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if (view.isSelected()) {
                    MainActivity.this.weatherSwitch.setText("OFF");
                    Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY__WEOTHER_LOCK, "true");
                } else {
                    MainActivity.this.weatherSwitch.setText("ON");
                    Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY__WEOTHER_LOCK, "false");
                }
            }
        });
        this.Language_test_Btn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(true);
                MainActivity.this.Language_test_Btn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.black, null));
                MainActivity.this.privacyLogBtn.setSelected(false);
                MainActivity.this.privacyLogBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.soundRecordingBtn.setSelected(false);
                MainActivity.this.soundRecordingBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.weatherBtn.setSelected(false);
                MainActivity.this.weatherBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.VRInfoBtn.setSelected(false);
                MainActivity.this.VRInfoBtn.setTextColor(BydVoiceSettingApplication.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                MainActivity.this.mRecyclerView.setVisibility(8);
                MainActivity.this.weatherSwitch.setVisibility(8);
                MainActivity.this.sound_begin_btn.setVisibility(8);
                MainActivity.this.VRInfoText.setVisibility(8);
                MainActivity.this.language_BackView.setVisibility(0);
                String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "INIT_VOICE_RESULT_MSG");
                RLog.i("NEU_Setting_Main", " INIT_VOICE_RESULT_MSG: " + string);
                MainActivity.this.language_BackView.setText(string);
                if (MainActivity.this.isBeginSound.booleanValue()) {
                    MainActivity.this.beginSoundRecording(false);
                }
            }
        });
        this.sound_begin_btn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.beginSoundRecording(!view.isSelected());
            }
        });
        this.blankdoor_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.MainActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.isOpen = false;
                customDialog.dismiss();
            }
        });
        try {
            arrayList = Common.getPrivacyPolicyModel();
            RLog.i("NEU_Setting_Main", "createBlankdoorDialog: models =" + arrayList.size());
        } catch (Exception unused) {
            arrayList = new ArrayList();
            RLog.i("NEU_Setting_Main", "createBlankdoorDialog: models = 0 errer");
        }
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(new blankdoorAdapter(arrayList));
        customDialog.show();
        this.isOpen = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginSoundRecording(boolean z) {
        String str;
        if (z) {
            this.sound_begin_btn.setSelected(true);
            this.isBeginSound = true;
            str = "bydvoice.broadcast.record.start";
        } else {
            this.sound_begin_btn.setSelected(false);
            this.isBeginSound = false;
            str = "bydvoice.broadcast.record.stop";
        }
        RLog.i("NEU_Setting_Main", "sendBroadcast: " + z);
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.receiver.AutoRunReceiver"));
        intent.addFlags(32);
        sendBroadcast(intent, "com.byd.vrassistant.autorun.permission");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeFragment(FragmentChangeBean fragmentChangeBean) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        WakeupFreeFragment wakeupFreeFragment = (WakeupFreeFragment) supportFragmentManager.findFragmentByTag(SettingFragment.Wakeup_FRAGMENT);
        if (wakeupFreeFragment == null) {
            RLog.i("NEU_Setting_Main", "addView: wuFragment == null");
            WakeupFreeFragment wakeupFreeFragment2 = new WakeupFreeFragment();
            this.wakeupFreeFragment = wakeupFreeFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_setting, wakeupFreeFragment2, SettingFragment.Wakeup_FRAGMENT);
        } else {
            RLog.i("NEU_Setting_Main", "addView: wuFragment != null");
            this.wakeupFreeFragment = wakeupFreeFragment;
        }
        fragmentTransactionBeginTransaction.show(this.wakeupFreeFragment);
        fragmentTransactionBeginTransaction.commit();
        this.mViewModel.isShowWakeupFragment = true;
        SettingFragment settingFragment = this.settingFragment;
        if (settingFragment != null) {
            fragmentTransactionBeginTransaction.hide(settingFragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishFragment(FinishWakeupFreeFragmentBean finishWakeupFreeFragmentBean) {
        RLog.i("NEU_Setting_Main", "finishFragment: removeView(wakeupFreeFragment)");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        WakeupFreeFragment wakeupFreeFragment = (WakeupFreeFragment) supportFragmentManager.findFragmentByTag(SettingFragment.Wakeup_FRAGMENT);
        if (wakeupFreeFragment != null) {
            fragmentTransactionBeginTransaction.remove(wakeupFreeFragment).commit();
            this.wakeupFreeFragment = null;
            this.mViewModel.isShowWakeupFragment = false;
            SettingFragment settingFragment = (SettingFragment) supportFragmentManager.findFragmentByTag("setting_Fragment");
            this.settingFragment = settingFragment;
            if (settingFragment != null) {
                fragmentTransactionBeginTransaction.show(settingFragment);
                return;
            }
            SettingFragment settingFragment2 = new SettingFragment();
            this.settingFragment = settingFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_setting, settingFragment2, "setting_Fragment");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeFragmenttoImprove(FragmentChangetoImproveBean fragmentChangetoImproveBean) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        UserImproveFragment userImproveFragment = (UserImproveFragment) supportFragmentManager.findFragmentByTag("userImprove_Fragment");
        if (this.userImproveFragment == null) {
            RLog.i("NEU_Setting_Main", "addView: wuFragment == null");
            UserImproveFragment userImproveFragment2 = new UserImproveFragment();
            this.userImproveFragment = userImproveFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_setting, userImproveFragment2, "userImprove_Fragment");
        } else {
            RLog.i("NEU_Setting_Main", "addView: wuFragment != null");
            this.userImproveFragment = userImproveFragment;
        }
        fragmentTransactionBeginTransaction.show(this.userImproveFragment);
        fragmentTransactionBeginTransaction.commit();
        this.mViewModel.isShowUserImproveFragment = true;
        SettingFragment settingFragment = this.settingFragment;
        if (settingFragment != null) {
            fragmentTransactionBeginTransaction.hide(settingFragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishUserImproveFragment(FinishUserImproveFragmentBean finishUserImproveFragmentBean) {
        RLog.i("NEU_Setting_Main", "finishFragment: removeView(userimprovefragment)");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        UserImproveFragment userImproveFragment = (UserImproveFragment) supportFragmentManager.findFragmentByTag("userImprove_Fragment");
        if (userImproveFragment != null) {
            fragmentTransactionBeginTransaction.remove(userImproveFragment).commit();
            this.userImproveFragment = null;
            this.mViewModel.isShowUserImproveFragment = false;
            SettingFragment settingFragment = (SettingFragment) supportFragmentManager.findFragmentByTag("setting_Fragment");
            this.settingFragment = settingFragment;
            if (settingFragment != null) {
                fragmentTransactionBeginTransaction.show(settingFragment);
                return;
            }
            SettingFragment settingFragment2 = new SettingFragment();
            this.settingFragment = settingFragment2;
            fragmentTransactionBeginTransaction.add(R.id.fl_setting, settingFragment2, "setting_Fragment");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSwitchPrivacy(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType() == Constant.KEY_PRIVACY_POLICY) {
            if (privacyPolicyEvent.getValue().equals("false")) {
                RLog.i("NEU_Setting_Main", "set KEY_PRIVACY_POLICY: false");
                this.settingFragment.setSwitchPrivacy(false);
                Common.savePrivacyPolicyModel(new PrivacyPolicyModel("false", Long.toString(System.currentTimeMillis())));
            } else {
                RLog.i("NEU_Setting_Main", "set KEY_PRIVACY_POLICY: true");
                this.settingFragment.setSwitchPrivacy(true);
                Common.savePrivacyPolicyModel(new PrivacyPolicyModel("true", Long.toString(System.currentTimeMillis())));
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        RLog.d("NEU_Setting_Main", "keyCode " + keyEvent.getKeyCode());
        if (keyEvent.getKeyCode() == 4) {
            RLog.i("NEU_Setting_Main", "dispatchKeyEvent: " + this.mViewModel.showDialogValue);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == 100) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void SystemLanguagechange(String str) {
        if ("SystemLanguagechange".equals(str)) {
            RLog.d("NEU_Setting_Main", "SystemLanguagechange");
            finish();
            Process.killProcess(Process.myPid());
        }
    }
}
