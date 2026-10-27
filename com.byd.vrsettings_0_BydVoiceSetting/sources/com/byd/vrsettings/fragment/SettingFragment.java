package com.byd.vrsettings.fragment;

import android.car.media.CarAudioManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.byd.vrassistant.hint.ToolUtils;
import com.byd.vrdownload.model.LanguageName;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.activity.OtaUpgradeActivity;
import com.byd.vrsettings.dialog.DetachableDialogDismissListener;
import com.byd.vrsettings.dialog.LoadingDialog;
import com.byd.vrsettings.dialog.WakeupWordsFreeDialog;
import com.byd.vrsettings.eventbus.FinishOtaFragmentEvent;
import com.byd.vrsettings.eventbus.FinishVersionFragmentBean;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.eventbus.VoiceZoneUIEvent;
import com.byd.vrsettings.interf.OnBackPressInterface;
import com.byd.vrsettings.service.AidlServiceManager;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.CustomToast;
import com.byd.vrsettings.utils.EmojiExcludeFilter;
import com.byd.vrsettings.utils.FastClickUtils;
import com.byd.vrsettings.utils.FragmentChangeBean;
import com.byd.vrsettings.utils.FragmentChangetoImproveBean;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.utils.PropertiesUtil;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.utils.ToastUtils;
import com.byd.vrsettings.view.CustomDialog;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSettingCell;
import com.byd.vrsettings.view.blankdoorAdapter;
import com.chad.library.BuildConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    public static final String DATA_PATH = "/navi/BYDAssistant_CR/data";
    private static long DIFF = 1000;
    public static final String USA_DATA_PATH = "/system/etc/BydVoice/data/data_eng-USA";
    public static final String Wakeup_FRAGMENT = "wakeup_Fragment";
    private static long lastClickTime = 0;
    private static int volumeMin = 5;
    private CustomDialog allRegisterLockDialog;
    private ConstraintLayout all_register_lock_layout;
    private CustomDialog audioWakeupDialog;
    private ConstraintLayout audio_wakeUp_layout;
    private View audio_wakeup_line;
    private View blankdoorBackView;
    private CustomDialog blankdoorDialog;
    private View blankdoorView;
    private ImageView blankdoor_BtnX;
    private CustomDialog carModelDialog;
    CarAudioManager.CarVolumeCallback carVolumeCallback;
    private CustomDialog cerencePrivacyPolicyDialog;
    private View cerence_privacy;
    private ImageView cerence_privacy_BtnX;
    private View cerence_privacy_line;
    private MyWebView cerence_privacy_webView;
    private ConstraintLayout cl_cerence_privacy;
    private ConstraintLayout cl_guide;
    private ConstraintLayout cl_item_voice_setting;
    private View cl_version;
    private ConstraintLayout cl_wakeup_phrases;
    private ContentResolver contentResolver;
    private CustomDialog continue_dialog_Dialog;
    private ConstraintLayout continue_dialog_layout;
    private View continue_dialog_line;
    private int currentVolume;
    DetachableDialogDismissListener detachableDialogDismissListener_allRegisterLockDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_audioWakeupDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_cerencePrivacyPolicyDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_continueDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_disclaimerPrivacyDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_privacyPolicyDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_userDefineDialog;
    DetachableDialogDismissListener detachableDialogDismissListener_version;
    private CustomDialog disclaimerPrivacyDialog;
    private ImageView disclaimer_BtnX;
    private View disclaimer_line;
    private Button driver;
    private EditText editText;
    private FragmentManager fragmentManager;
    private ImageView ivEmpty;
    private ImageView iv_driver_wakeup_locked;
    private Button iv_front_left;
    private ImageView iv_front_passenger_wakeup_locked;
    private Button iv_front_right;
    private Button iv_rear_left;
    private Button iv_rear_right;
    private ImageView iv_register_lock;
    private ImageView iv_register_set_icon;
    private ImageView iv_smart_voice;
    private ImageView iv_switch_audio_wakeup;
    private ImageView iv_switch_contiunue_dialog;
    private ImageView iv_switch_privacy;
    private ImageView iv_tab_more_register_lock;
    private ImageView iv_tab_more_user_define;
    private ImageView iv_tab_more_wakeup_free;
    private ImageView iv_version_back;
    private ImageView iv_volume_move;
    private int iv_volume_move_width;
    private ImageView language_icon;
    private AudioManager mAudioManager;
    LoadingDialog.Builder mLoadingBuilder;
    private RecyclerView mRecyclerView;
    private MyViewModel mViewModel;
    WakeupWordsFreeDialog.Builder mWakeupWordsFreeDialogBuilder;
    private int mixVolume;
    private Fragment otaFragment;
    private Button passenger;
    private View privacy;
    private Button privacyLogBtn;
    private CustomDialog privacyPolicyDialog;
    private View privacy_line;
    private ConstraintLayout privacy_policy_layout;
    private CustomDialog registerLockDialog;
    private View register_background;
    private ConstraintLayout register_lock_layout;
    private View register_lock_line;
    private SeekBar sb_volume;
    private float sb_volumeX;
    private int sb_volume_width;
    private ConstraintSet set;
    private View setting_voice_line;
    private Button soundRecordingBtn;
    private Button sound_begin_btn;
    private ScrollView sv_guide;
    private CustomScrollView sv_setting;
    private TextView tv;
    private TextView tv3;
    private TextView tv4;
    private TextView tv_audio_registe_title;
    private TextView tv_audio_registe_title_tip;
    private TextView tv_audio_wakeup_title;
    private TextView tv_audio_wakeup_title_tip;
    private TextView tv_btn_cancel_privacy;
    private TextView tv_btn_disable_privacy;
    private TextView tv_btn_disclaimer;
    private TextView tv_btn_no_audio;
    private TextView tv_btn_no_audio_continue;
    private TextView tv_btn_no_register;
    private TextView tv_btn_no_wakeup;
    private TextView tv_btn_yes;
    private TextView tv_btn_yes_audio;
    private TextView tv_btn_yes_audio_continue;
    private TextView tv_btn_yes_register;
    private TextView tv_btn_yes_wakeup;
    private TextView tv_car_model;
    private TextView tv_content_audio;
    private TextView tv_content_continue_dialog;
    private TextView tv_content_register;
    private TextView tv_contiunue_dialog_title;
    private TextView tv_contiunue_dialog_title_tip;
    private TextView tv_copyright;
    private TextView tv_cur_language;
    private TextView tv_download_version;
    private TextView tv_driver_wakeup_locked;
    private TextView tv_driver_wakeup_locked_tip;
    private TextView tv_four_register_locked;
    private TextView tv_front_passenger_wakeup_locked;
    private TextView tv_front_passenger_wakeup_locked_tip;
    private TextView tv_language;
    private TextView tv_privacy_disclaimer;
    private TextView tv_privacy_policy;
    private TextView tv_register_lock_tip;
    private TextView tv_register_lock_title;
    private TextView tv_register_set_title;
    private TextView tv_register_set_title_tip;
    private TextView tv_settingSDK_version;
    private TextView tv_setting_version;
    private TextView tv_smart_voice;
    private TextView tv_smart_voice_version;
    private TextView tv_text_guide;
    private TextView tv_title;
    private TextView tv_title_audio;
    private TextView tv_title_cerence_privacy;
    private TextView tv_title_continue_dialog;
    private TextView tv_title_guide;
    private TextView tv_title_privacy;
    private TextView tv_title_register;
    private TextView tv_title_wakeup;
    private TextView tv_user_Imporvement_disclaimer;
    private TextView tv_user_define_tip;
    private TextView tv_user_define_title;
    private TextView tv_version_disclaimer;
    private TextView tv_version_title;
    private TextView tv_voice_broadcast_volume;
    private TextView tv_voice_setting_hello;
    private TextView tv_voice_setting_hello_tip;
    private TextView tv_volume;
    private TextView tv_volume_move;
    private int tv_volume_move_width;
    private TextView tv_wakeup;
    private TextView tv_wakeup_free_tip;
    private TextView tv_wakeup_free_title;
    private TextView tv_wakeup_phrases;
    private TextView tv_x;
    private CustomDialog userDefineDialog;
    private Fragment userImproveFragment;
    private View v_audio;
    private View v_audioBackground;
    private View v_background;
    private View v_card;
    private View v_cerence_privacyBackground;
    private View v_continueDialog;
    private View v_continue_Background;
    private CustomSettingCell v_contiunue_dialog;
    private View v_driver_register_lock;
    private View v_guide;
    private View v_guideBackground;
    private CustomSettingCell v_language;
    private CustomSettingCell v_privacy;
    private View v_privacyBackground;
    private View v_register_lock_hided;
    private CustomSettingCell v_register_set;
    private CustomSettingCell v_user_Imporvement;
    private ImageView v_user_Imporvement_icon;
    private CustomSettingCell v_version;
    private View v_versionBackground;
    private CustomSettingCell v_volume;
    private CustomSettingCell v_wakeup;
    private View v_wakeupBackground;
    private View v_wakeup_phrases;
    private CustomDialog versionDialog;
    private Fragment versionFragment;
    private View version_backView;
    private ImageView version_icon;
    private View view;
    private View viewCerencePrivacy;
    private View viewContinueDialog;
    private View viewDialogAllRegister;
    private View viewDialogAudio;
    private View viewDialogCarModel;
    private View viewDialogDisclaimerPrivacy;
    private View viewDialogRegister;
    private View viewDialogVersion;
    private View viewDialogWakePhrases;
    private View viewPrivacyPolicy;
    private View view_update_point;
    private View voice_setting_card_register_lock;
    private View voice_setting_card_user_define;
    private View voice_setting_card_wakeup_free;
    private Fragment wakeupFreeFragment;
    private View wakeup_line;
    private Button weatherBtn;
    private Button weatherSwitch;
    private final String TAG = "NEU_SettingFragment AppVersion=S.8.12.2";
    private final boolean DEFAULT_AUDIO_WAKEUP = true;
    private final boolean DEFAULT_PRIVACY_POLICY = false;
    private final boolean DEFAULT_REGISTER_LOCK = true;
    private final String DEFAULT_WAKE_WORD = BuildConfig.FLAVOR;
    private final String DEFAULT_LANGUAGE = LanguageName.ENG;
    private long time = 0;
    private int count = 0;
    private boolean isHidePortionView = false;
    private boolean isAutograph = true;
    private Handler mVolumeMiss = new Handler();
    private Handler mHandler = new Handler();
    private boolean onconfigchange = false;
    private Boolean isOpen = false;
    private Boolean isBeginSound = false;
    Runnable mLoadingRunnable = new Runnable() { // from class: com.byd.vrsettings.fragment.SettingFragment.52
        @Override // java.lang.Runnable
        public void run() {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "loading timeout...");
            SettingFragment.this.dismissLoading();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onCreate: begin");
        this.mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onCreate: register EventBus");
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("NEU_SettingFragment AppVersion=S.8.12.2", "onCreateView");
        this.view = layoutInflater.inflate(R.layout.item_voice_setting, viewGroup, false);
        this.isAutograph = true;
        initView();
        Log.d("NEU_SettingFragment AppVersion=S.8.12.2", "onCreateView BuildConfig.VersionType ==sop");
        if (!PropertiesUtil.IfSopLanguageIsEnableShowWakeUp()) {
            hidePortionView();
        }
        ChangeChineseYaTai();
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "onCreateView: true");
            setNight();
        } else {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "onCreateView: false");
            setLight();
        }
        int i = this.mViewModel.showDialogValue;
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onCreateView: dialogValue = " + this.mViewModel.showDialogValue);
        switch (i) {
            case 1:
                createUserDefineDialog();
                break;
            case 2:
                createAllRegisterLockDialog();
                break;
            case 3:
                createAudioWakeupDialog();
                break;
            case 4:
                createPrivacyPolicyDialog();
                break;
            case 5:
                createPrivacyPolicyDialog();
                this.mViewModel.showDialogValue = 5;
                createCerencePrivacyPolicyDialog();
                break;
            case 6:
                createVersionDialog();
                break;
            case 7:
                createVersionDialog();
                this.mViewModel.showDialogValue = 7;
                createDisclaimerPrivacyDialog();
                break;
            case 8:
                createVersionDialog();
                this.mViewModel.showDialogValue = 8;
                createDisclaimerPrivacyDialog();
                createCerencePrivacyPolicyDialog();
                break;
            case 9:
                createContinneDialog();
                break;
        }
        this.view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.iv_volume_move_width = settingFragment.iv_volume_move.getWidth();
                SettingFragment settingFragment2 = SettingFragment.this;
                settingFragment2.tv_volume_move_width = settingFragment2.tv_volume_move.getWidth();
                SettingFragment settingFragment3 = SettingFragment.this;
                settingFragment3.sb_volume_width = settingFragment3.sb_volume.getProgressDrawable().getBounds().width();
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onResume: X =" + SettingFragment.this.sb_volume.getX());
                float progress = SettingFragment.this.sb_volume.getProgress() / SettingFragment.this.sb_volume.getMax();
                float width = ((SettingFragment.this.sb_volume.getWidth() - SettingFragment.this.sb_volume_width) / 2) - (SettingFragment.this.iv_volume_move_width / 2);
                SettingFragment.this.iv_volume_move.setX(SettingFragment.this.sb_volume.getX() + width + (SettingFragment.this.sb_volume_width * progress));
                SettingFragment.this.tv_volume_move.setX(SettingFragment.this.sb_volume.getX() + ((SettingFragment.this.iv_volume_move_width - SettingFragment.this.tv_volume_move_width) / 2) + width + (SettingFragment.this.sb_volume_width * progress));
                SettingFragment.this.view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return this.view;
    }

    private void ChangeChineseYaTai() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        if (string == null) {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "GetCurrentLanguageForLayout KEY_LANGUAGE is null");
            string = BuildConfig.FLAVOR;
        }
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "GetCurrentLanguageForLayout: " + string);
        if (string.contains("cmn-CHN")) {
            if (Common.isApkExist(Common.kikaPackageName) || Common.isApkExist(Common.sougouPackageName)) {
                this.voice_setting_card_wakeup_free.setVisibility(0);
                this.voice_setting_card_user_define.setVisibility(0);
                this.voice_setting_card_register_lock.setVisibility(0);
            } else {
                this.voice_setting_card_wakeup_free.setVisibility(0);
                this.voice_setting_card_user_define.setVisibility(8);
                this.voice_setting_card_register_lock.setVisibility(0);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            Settings.System.putString(this.contentResolver, Constant.KEY_SETTING_TAG, "setting");
            return;
        }
        Settings.System.putString(this.contentResolver, Constant.KEY_SETTING_TAG, BuildConfig.FLAVOR);
        CustomDialog customDialog = this.userDefineDialog;
        if (customDialog != null && customDialog.isShowing()) {
            this.mViewModel.userDefineText = BuildConfig.FLAVOR;
            this.userDefineDialog.dismiss();
        }
        CustomDialog customDialog2 = this.registerLockDialog;
        if (customDialog2 != null && customDialog2.isShowing()) {
            this.registerLockDialog.dismiss();
        }
        CustomDialog customDialog3 = this.allRegisterLockDialog;
        if (customDialog3 != null && customDialog3.isShowing()) {
            this.allRegisterLockDialog.dismiss();
        }
        CustomDialog customDialog4 = this.versionDialog;
        if (customDialog4 != null && customDialog4.isShowing()) {
            this.versionDialog.dismiss();
        }
        CustomDialog customDialog5 = this.disclaimerPrivacyDialog;
        if (customDialog5 != null && customDialog5.isShowing()) {
            this.disclaimerPrivacyDialog.dismiss();
        }
        CustomDialog customDialog6 = this.audioWakeupDialog;
        if (customDialog6 != null && customDialog6.isShowing()) {
            this.audioWakeupDialog.dismiss();
        }
        CustomDialog customDialog7 = this.privacyPolicyDialog;
        if (customDialog7 != null && customDialog7.isShowing()) {
            this.privacyPolicyDialog.dismiss();
        }
        CustomDialog customDialog8 = this.cerencePrivacyPolicyDialog;
        if (customDialog8 != null && customDialog8.isShowing()) {
            this.mViewModel.curPos = 0;
            destroyWebView();
            this.cerencePrivacyPolicyDialog.dismiss();
            this.viewCerencePrivacy = null;
        }
        this.mViewModel.showDialogValue = 0;
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "1 mViewModel.showDialogValue==" + this.mViewModel.showDialogValue);
    }

    private void initView() {
        Log.d("NEU_SettingFragment AppVersion=S.8.12.2", "initView");
        this.contentResolver = ((FragmentActivity) Objects.requireNonNull(getActivity())).getContentResolver();
        this.fragmentManager = getFragmentManager();
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_setting);
        this.sv_setting = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.2
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onScrollY: Y" + i);
            }
        });
        this.cl_item_voice_setting = (ConstraintLayout) this.view.findViewById(R.id.cl_item_voice_setting);
        this.voice_setting_card_wakeup_free = this.view.findViewById(R.id.voice_setting_card_wakeup_free);
        this.voice_setting_card_user_define = this.view.findViewById(R.id.voice_setting_card_user_define);
        this.voice_setting_card_register_lock = this.view.findViewById(R.id.voice_setting_card_register_lock);
        this.tv_wakeup_free_title = (TextView) this.view.findViewById(R.id.tv_wakeup_free_title);
        this.tv_user_define_title = (TextView) this.view.findViewById(R.id.tv_user_define_title);
        this.tv_register_lock_title = (TextView) this.view.findViewById(R.id.tv_register_lock_title);
        this.tv_wakeup_free_tip = (TextView) this.view.findViewById(R.id.tv_wakeup_free_tip);
        this.tv_user_define_tip = (TextView) this.view.findViewById(R.id.tv_user_define_tip);
        this.tv_register_lock_tip = (TextView) this.view.findViewById(R.id.tv_register_lock_tip);
        this.iv_tab_more_wakeup_free = (ImageView) this.view.findViewById(R.id.iv_tab_more_wakeup_free);
        this.iv_tab_more_user_define = (ImageView) this.view.findViewById(R.id.iv_tab_more_user_define);
        this.iv_tab_more_register_lock = (ImageView) this.view.findViewById(R.id.iv_tab_more_register_lock);
        this.iv_switch_audio_wakeup = (ImageView) this.view.findViewById(R.id.iv_switch_audio_wakeup);
        this.iv_switch_privacy = (ImageView) this.view.findViewById(R.id.iv_switch_privacy);
        this.v_contiunue_dialog = (CustomSettingCell) this.view.findViewById(R.id.v_contiunue_dialog);
        this.iv_switch_contiunue_dialog = (ImageView) this.view.findViewById(R.id.iv_switch_contiunue_dialog);
        this.tv_contiunue_dialog_title = (TextView) this.view.findViewById(R.id.tv_contiunue_dialog_title);
        this.tv_contiunue_dialog_title_tip = (TextView) this.view.findViewById(R.id.tv_contiunue_dialog_title_tip);
        this.sb_volume = (SeekBar) this.view.findViewById(R.id.sb_volume);
        this.tv_volume = (TextView) this.view.findViewById(R.id.tv_volume);
        this.tv_volume_move = (TextView) this.view.findViewById(R.id.tv_volume_move);
        this.iv_volume_move = (ImageView) this.view.findViewById(R.id.iv_volume_move);
        this.language_icon = (ImageView) this.view.findViewById(R.id.language_icon);
        this.version_icon = (ImageView) this.view.findViewById(R.id.version_icon);
        this.v_user_Imporvement_icon = (ImageView) this.view.findViewById(R.id.v_user_Imporvement_icon);
        this.v_card = this.view.findViewById(R.id.v_card);
        this.v_wakeup = (CustomSettingCell) this.view.findViewById(R.id.v_wakeup);
        this.v_register_lock_hided = this.view.findViewById(R.id.v_register_lock_hided);
        this.v_volume = (CustomSettingCell) this.view.findViewById(R.id.v_volume);
        this.v_language = (CustomSettingCell) this.view.findViewById(R.id.v_language);
        this.v_version = (CustomSettingCell) this.view.findViewById(R.id.v_version);
        this.v_user_Imporvement = (CustomSettingCell) this.view.findViewById(R.id.v_user_Imporvement);
        this.v_privacy = (CustomSettingCell) this.view.findViewById(R.id.v_privacy);
        this.tv_wakeup = (TextView) this.view.findViewById(R.id.tv_wakeup);
        this.tv_audio_wakeup_title = (TextView) this.view.findViewById(R.id.tv_audio_wakeup_title);
        this.tv_audio_registe_title = (TextView) this.view.findViewById(R.id.tv_audio_registe_title);
        this.tv_audio_wakeup_title_tip = (TextView) this.view.findViewById(R.id.tv_audio_wakeup_title_tip);
        this.tv_audio_registe_title_tip = (TextView) this.view.findViewById(R.id.tv_audio_registe_title_tip);
        this.tv_voice_broadcast_volume = (TextView) this.view.findViewById(R.id.tv_voice_broadcast_volume);
        this.tv_language = (TextView) this.view.findViewById(R.id.tv_language);
        this.tv_cur_language = (TextView) this.view.findViewById(R.id.tv_cur_language);
        this.view_update_point = this.view.findViewById(R.id.view_update_point);
        this.tv_version_disclaimer = (TextView) this.view.findViewById(R.id.tv_version_disclaimer);
        this.tv_user_Imporvement_disclaimer = (TextView) this.view.findViewById(R.id.tv_user_Imporvement_disclaimer);
        this.tv_privacy_disclaimer = (TextView) this.view.findViewById(R.id.tv_privacy_disclaimer);
        this.voice_setting_card_wakeup_free.setOnClickListener(this);
        this.voice_setting_card_user_define.setOnClickListener(this);
        this.voice_setting_card_register_lock.setOnClickListener(this);
        this.tv_wakeup.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.wakeupClick();
            }
        });
        this.iv_switch_audio_wakeup.setOnClickListener(this);
        this.iv_switch_contiunue_dialog.setOnClickListener(this);
        this.iv_switch_privacy.setOnClickListener(this);
        this.v_language.setOnClickListener(this);
        this.v_version.setOnClickListener(this);
        this.v_user_Imporvement.setOnClickListener(this);
        if (this.isAutograph) {
            String string = Settings.System.getString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP);
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "initView: audio_wakeup = " + string);
            if (string == null || string.length() == 0) {
                setSwitchDisStatus(this.iv_switch_audio_wakeup, false);
            } else {
                setSwitchStatus(this.iv_switch_audio_wakeup, string.equals("true"));
            }
            String string2 = Settings.System.getString(this.contentResolver, Constant.KEY_PRIVACY_POLICY);
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Settings: provacy_policy ===========" + string2);
            if (string2 == null || string2.length() == 0) {
                setSwitchDisStatus(this.iv_switch_privacy, false);
            } else {
                setSwitchStatus(this.iv_switch_privacy, string2.equals("true"));
            }
            this.tv_cur_language.setText(OtaDataManager.getDPSCurrentLanguage());
            if (PropertiesUtil.IfCurrentLanguageIsEnableToContinue()) {
                this.v_contiunue_dialog.setVisibility(0);
                this.iv_switch_contiunue_dialog.setVisibility(0);
                this.tv_contiunue_dialog_title.setVisibility(0);
                this.tv_contiunue_dialog_title_tip.setVisibility(0);
                String string3 = Settings.System.getString(this.contentResolver, Constant.KEY_CONTINUE_DIALOG);
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "changeLanguage: continue_dialog ===========" + string3);
                if (string3 == null || string3.length() == 0) {
                    setSwitchStatus(this.iv_switch_contiunue_dialog, true);
                } else {
                    setSwitchStatus(this.iv_switch_contiunue_dialog, string3.equals("true"));
                }
            } else {
                this.v_contiunue_dialog.setVisibility(8);
                this.iv_switch_contiunue_dialog.setVisibility(8);
                this.tv_contiunue_dialog_title.setVisibility(8);
                this.tv_contiunue_dialog_title_tip.setVisibility(8);
            }
        }
        this.mAudioManager = (AudioManager) getActivity().getSystemService("audio");
        int tTsMaxVolume = Common.get().getTTsMaxVolume();
        RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "initView: maxVolume ===========" + tTsMaxVolume);
        this.sb_volume.setMax(tTsMaxVolume - volumeMin);
        this.currentVolume = Common.get().getTTsVolume();
        this.mixVolume = Common.get().getTTsMixVolume();
        RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "initView: currentVolume =============" + this.currentVolume);
        this.sb_volume.setProgress(this.currentVolume - volumeMin);
        this.tv_volume.setText(this.currentVolume + BuildConfig.FLAVOR);
        this.tv_volume_move.setText(this.currentVolume + BuildConfig.FLAVOR);
        this.set = new ConstraintSet();
        this.sb_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.iv_volume_move_width = settingFragment.iv_volume_move.getWidth();
                SettingFragment settingFragment2 = SettingFragment.this;
                settingFragment2.tv_volume_move_width = settingFragment2.tv_volume_move.getWidth();
                SettingFragment settingFragment3 = SettingFragment.this;
                settingFragment3.sb_volume_width = settingFragment3.sb_volume.getProgressDrawable().getBounds().width();
                if (SettingFragment.this.sb_volume.getLayoutDirection() == 1) {
                    SettingFragment settingFragment4 = SettingFragment.this;
                    settingFragment4.sb_volumeX = settingFragment4.sb_volume.getX() + SettingFragment.this.sb_volume_width;
                } else {
                    SettingFragment settingFragment5 = SettingFragment.this;
                    settingFragment5.sb_volumeX = settingFragment5.sb_volume.getX();
                }
                float width = ((SettingFragment.this.sb_volume.getWidth() - SettingFragment.this.sb_volume_width) / 2) - (SettingFragment.this.iv_volume_move_width / 2);
                if (i >= SettingFragment.this.mixVolume - SettingFragment.volumeMin) {
                    Common.get().TtsVolumeTo(i + SettingFragment.volumeMin);
                    int tTsVolume = Common.get().getTTsVolume();
                    seekBar.setProgress(tTsVolume - SettingFragment.volumeMin);
                    SettingFragment.this.currentVolume = tTsVolume;
                    SettingFragment.this.tv_volume.setText(SettingFragment.this.currentVolume + BuildConfig.FLAVOR);
                    float progress = SettingFragment.this.sb_volume.getProgress() / SettingFragment.this.sb_volume.getMax();
                    if (SettingFragment.this.sb_volume.getLayoutDirection() == 1) {
                        SettingFragment.this.iv_volume_move.setX((SettingFragment.this.sb_volumeX + width) - (SettingFragment.this.sb_volume_width * progress));
                        SettingFragment.this.tv_volume_move.setX(((SettingFragment.this.sb_volumeX + ((SettingFragment.this.iv_volume_move_width - SettingFragment.this.tv_volume_move_width) / 2)) + width) - (SettingFragment.this.sb_volume_width * progress));
                    } else {
                        SettingFragment.this.iv_volume_move.setX(SettingFragment.this.sb_volumeX + width + (SettingFragment.this.sb_volume_width * progress));
                        SettingFragment.this.tv_volume_move.setX(SettingFragment.this.sb_volumeX + ((SettingFragment.this.iv_volume_move_width - SettingFragment.this.tv_volume_move_width) / 2) + width + (SettingFragment.this.sb_volume_width * progress));
                    }
                    SettingFragment.this.tv_volume.setText(tTsVolume + BuildConfig.FLAVOR);
                    SettingFragment.this.tv_volume_move.setText(SettingFragment.this.currentVolume + BuildConfig.FLAVOR);
                    return;
                }
                seekBar.setProgress(SettingFragment.this.mixVolume);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (SettingFragment.this.mVolumeMiss != null) {
                    SettingFragment.this.mVolumeMiss.removeCallbacksAndMessages(null);
                }
                SettingFragment.this.tv_volume_move.setVisibility(0);
                SettingFragment.this.iv_volume_move.setVisibility(0);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (SettingFragment.this.mVolumeMiss != null) {
                    SettingFragment.this.mVolumeMiss.removeCallbacksAndMessages(null);
                }
                SettingFragment.this.mVolumeMiss.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SettingFragment.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SettingFragment.this.tv_volume_move.setVisibility(4);
                        SettingFragment.this.iv_volume_move.setVisibility(4);
                    }
                }, 1000L);
            }
        });
        this.carVolumeCallback = new CarAudioManager.CarVolumeCallback() { // from class: com.byd.vrsettings.fragment.SettingFragment.5
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int i, int i2, int i3) {
                SettingFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.fragment.SettingFragment.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int tTsVolume = Common.get().getTTsVolume();
                        RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "registerCarVolumeCallback: currentVolume" + tTsVolume);
                        SettingFragment.this.sb_volume.setProgress(tTsVolume - SettingFragment.volumeMin);
                        SettingFragment.this.tv_volume.setText(tTsVolume + BuildConfig.FLAVOR);
                        SettingFragment.this.tv_volume_move.setText(tTsVolume + BuildConfig.FLAVOR);
                    }
                });
            }
        };
        Common.get().registerCarVolumeCallback(this.carVolumeCallback);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE")) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Settings: changeLanguage start");
            SharedPreferencesUtils.setParam(getContext(), "KEY_WAKEUP_WORD", BuildConfig.FLAVOR);
            Settings.System.putString(this.contentResolver, "KEY_WAKEUP_WORD", BuildConfig.FLAVOR);
            if (PropertiesUtil.IfCurrentLanguageIsEnableToContinue()) {
                this.v_contiunue_dialog.setVisibility(0);
                this.iv_switch_contiunue_dialog.setVisibility(0);
                this.tv_contiunue_dialog_title.setVisibility(0);
                this.tv_contiunue_dialog_title_tip.setVisibility(0);
                String string = Settings.System.getString(this.contentResolver, Constant.KEY_CONTINUE_DIALOG);
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Settings: continue_dialog ===========" + string);
                if (string == null || string.length() == 0) {
                    setSwitchStatus(this.iv_switch_contiunue_dialog, true);
                } else {
                    setSwitchStatus(this.iv_switch_contiunue_dialog, string.equals("true"));
                }
            } else {
                this.v_contiunue_dialog.setVisibility(8);
                this.iv_switch_contiunue_dialog.setVisibility(8);
                this.tv_contiunue_dialog_title.setVisibility(8);
                this.tv_contiunue_dialog_title_tip.setVisibility(8);
            }
            Log.d("NEU_SettingFragment AppVersion=S.8.12.2", "changeLanguage BuildConfig.VersionType ==sop");
            if (!PropertiesUtil.IfSopLanguageIsEnableShowWakeUp()) {
                hidePortionView();
            } else {
                showPortionView();
            }
            ChangeChineseYaTai();
            return;
        }
        if (privacyPolicyEvent.getType() == com.byd.vrassistant.hint.Constant.VR_START_SUCCESS) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SettingFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    SettingFragment.this.dismissLoading();
                }
            }, 1500L);
        }
    }

    private void hidePortionView() {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "begin: hidePortionView=====");
        this.isHidePortionView = true;
        this.v_card.setVisibility(0);
        this.voice_setting_card_wakeup_free.setVisibility(8);
        this.voice_setting_card_user_define.setVisibility(8);
        this.voice_setting_card_register_lock.setVisibility(0);
    }

    private void showPortionView() {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "begin: showPortionView=====");
        this.isHidePortionView = false;
        this.v_card.setVisibility(0);
        this.voice_setting_card_wakeup_free.setVisibility(0);
        this.voice_setting_card_user_define.setVisibility(0);
        this.voice_setting_card_register_lock.setVisibility(0);
    }

    private void setLandScape() {
        this.set.clone(this.cl_item_voice_setting);
        if (Common.isRightDriver() || Common.isRtl(getContext())) {
            this.set.connect(R.id.v_card, 6, 0, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.v_card, 7, 0, 7, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.v_card, 3, 0, 3);
        } else {
            this.set.connect(R.id.v_card, 6, 0, 6, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.v_card, 7, 0, 7, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.v_card, 3, 0, 3);
        }
        this.set.applyTo(this.cl_item_voice_setting);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.sb_volume.getLayoutParams();
        layoutParams.width = (int) TypedValue.applyDimension(1, 503.0f, getResources().getDisplayMetrics());
        this.sb_volume.setLayoutParams(layoutParams);
        if (this.cerence_privacy != null) {
            this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 660.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 560.0f, getResources().getDisplayMetrics())));
        }
        this.tv_user_define_title.setText(R.string.text_wakeup_phrases);
        this.tv_register_lock_title.setText(R.string.text_audio_zone_settings);
        this.tv_wakeup_free_tip.setText(R.string.text_wakeup_free_tip);
        this.tv_user_define_tip.setText(R.string.text_user_define_tip);
        this.tv_register_lock_tip.setText(R.string.text_audio_zone_settings_short_tip);
    }

    private void setPortrait() {
        this.set.clone(this.cl_item_voice_setting);
        this.set.connect(R.id.v_card, 6, 0, 6, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.v_card, 7, 0, 7, (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.v_card, 3, 0, 3, (int) TypedValue.applyDimension(1, 46.0f, getResources().getDisplayMetrics()));
        this.set.applyTo(this.cl_item_voice_setting);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.sb_volume.getLayoutParams();
        layoutParams.width = (int) TypedValue.applyDimension(1, 290.0f, getResources().getDisplayMetrics());
        this.sb_volume.setLayoutParams(layoutParams);
        if (this.cerence_privacy != null) {
            this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 660.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 560.0f, getResources().getDisplayMetrics())));
        }
        this.tv_user_define_title.setText(R.string.text_wakeup_phrases);
        this.tv_register_lock_title.setText(R.string.text_audio_zone_settings);
        this.tv_wakeup_free_tip.setText(R.string.text_wakeup_free_tip_vertical);
        this.tv_user_define_tip.setText(R.string.text_user_define_tip_vertical);
        this.tv_register_lock_tip.setText(R.string.text_audio_zone_settings_short_tip);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisable() {
        if (Boolean.parseBoolean(Settings.System.getString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP))) {
            if (ModeUtils.isNightMode(getContext())) {
                this.voice_setting_card_wakeup_free.setBackground(getResources().getDrawable(R.mipmap.card_wakeup_free_night, null));
                this.voice_setting_card_user_define.setBackground(getResources().getDrawable(R.mipmap.card_user_define_night, null));
                this.voice_setting_card_register_lock.setBackground(getResources().getDrawable(R.mipmap.card_register_lock_night, null));
                this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
                this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
                this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
                this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
                this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
                this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
                this.iv_tab_more_wakeup_free.setImageResource(R.mipmap.iv_jiantou_night);
                this.iv_tab_more_user_define.setImageResource(R.mipmap.iv_jiantou_night);
                this.iv_tab_more_register_lock.setImageResource(R.mipmap.iv_jiantou_night);
            } else {
                this.voice_setting_card_wakeup_free.setBackground(getResources().getDrawable(R.mipmap.card_wakeup_free, null));
                this.voice_setting_card_user_define.setBackground(getResources().getDrawable(R.mipmap.card_user_define, null));
                this.voice_setting_card_register_lock.setBackground(getResources().getDrawable(R.mipmap.card_register_lock, null));
                this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
                this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
                this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
                this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
                this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
                this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
                this.iv_tab_more_wakeup_free.setImageResource(R.mipmap.iv_jiantou);
                this.iv_tab_more_user_define.setImageResource(R.mipmap.iv_jiantou);
                this.iv_tab_more_register_lock.setImageResource(R.mipmap.iv_jiantou);
            }
            this.voice_setting_card_wakeup_free.setClickable(true);
            this.voice_setting_card_user_define.setClickable(true);
            this.voice_setting_card_register_lock.setClickable(true);
            return;
        }
        if (ModeUtils.isNightMode(getContext())) {
            this.voice_setting_card_wakeup_free.setBackground(getResources().getDrawable(R.mipmap.card_wakeup_free_disable_night, null));
            this.voice_setting_card_user_define.setBackground(getResources().getDrawable(R.mipmap.card_user_define_disable_night, null));
            this.voice_setting_card_register_lock.setBackground(getResources().getDrawable(R.mipmap.card_register_lock_disable_night, null));
            this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_dis_night_8155, null));
            this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_dis_night_8155, null));
            this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_text_dis_night_8155, null));
            this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_dis_night_8155, null));
            this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_text_dis_night_8155, null));
            this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_dis_night_8155, null));
            this.iv_tab_more_wakeup_free.setImageResource(R.mipmap.iv_jiantou_dis_night);
            this.iv_tab_more_user_define.setImageResource(R.mipmap.iv_jiantou_dis_night);
            this.iv_tab_more_register_lock.setImageResource(R.mipmap.iv_jiantou_dis_night);
        } else {
            this.voice_setting_card_wakeup_free.setBackground(getResources().getDrawable(R.mipmap.card_wakeup_free_disable, null));
            this.voice_setting_card_user_define.setBackground(getResources().getDrawable(R.mipmap.card_user_define_disable, null));
            this.voice_setting_card_register_lock.setBackground(getResources().getDrawable(R.mipmap.card_register_lock_disable, null));
            this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_disText_8155, null));
            this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_disText_8155, null));
            this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_disText_8155, null));
            this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_disText_8155, null));
            this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_disText_8155, null));
            this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_disText_8155, null));
            this.iv_tab_more_wakeup_free.setImageResource(R.mipmap.iv_jiantou_dis);
            this.iv_tab_more_user_define.setImageResource(R.mipmap.iv_jiantou_dis);
            this.iv_tab_more_register_lock.setImageResource(R.mipmap.iv_jiantou_dis);
        }
        this.voice_setting_card_wakeup_free.setClickable(false);
        this.voice_setting_card_user_define.setClickable(false);
        this.voice_setting_card_register_lock.setClickable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (FastClickUtils.isFastClick() || this.mViewModel.currentFragment == 2) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_switch_audio_wakeup /* 2131296524 */:
                if (Boolean.parseBoolean(Settings.System.getString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP))) {
                    createAudioWakeupDialog();
                } else {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    long j = lastClickTime;
                    long j2 = jElapsedRealtime - j;
                    if (j > 0 && j2 < DIFF && j2 > 0) {
                        RLog.i("isFastDoubleClick", "短时间内按钮多次触发");
                    } else {
                        Settings.System.putString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP, "true");
                        setSwitchStatus(this.iv_switch_audio_wakeup, true);
                        setDisable();
                        RLog.e("NEU_Audio_Wakeup", Settings.System.getString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP));
                        showLoading();
                    }
                }
                break;
            case R.id.iv_switch_contiunue_dialog /* 2131296525 */:
                if (Boolean.parseBoolean(Settings.System.getString(this.contentResolver, Constant.KEY_CONTINUE_DIALOG))) {
                    createContinneDialog();
                } else {
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    long j3 = lastClickTime;
                    long j4 = jElapsedRealtime2 - j3;
                    if (j3 > 0 && j4 < DIFF && j4 > 0) {
                        RLog.i("isFastDoubleClick", "短时间内按钮多次触发");
                    } else {
                        Settings.System.putString(this.contentResolver, Constant.KEY_CONTINUE_DIALOG, "true");
                        setSwitchStatus(this.iv_switch_contiunue_dialog, true);
                        RLog.e("NEU_KEY_CONTINUE_DIALOG", Settings.System.getString(this.contentResolver, Constant.KEY_CONTINUE_DIALOG));
                    }
                }
                break;
            case R.id.iv_switch_privacy /* 2131296526 */:
                if (Settings.System.getString(this.contentResolver, Constant.KEY_PRIVACY_POLICY).equals("true")) {
                    createPrivacyPolicyDialog();
                } else {
                    setSwitchStatus(this.iv_switch_privacy, true);
                    Settings.System.putString(this.contentResolver, Constant.KEY_PRIVACY_POLICY, "true");
                }
                break;
            case R.id.v_language /* 2131296888 */:
                startActivity(new Intent(getActivity(), (Class<?>) OtaUpgradeActivity.class));
                break;
            case R.id.v_user_Imporvement /* 2131296904 */:
                EventBus.getDefault().post(new FragmentChangetoImproveBean());
                break;
            case R.id.v_version /* 2131296908 */:
                createVersionDialog();
                break;
            case R.id.voice_setting_card_register_lock /* 2131296929 */:
                createAllRegisterLockDialog();
                break;
            case R.id.voice_setting_card_user_define /* 2131296930 */:
                createUserDefineDialog();
                break;
            case R.id.voice_setting_card_wakeup_free /* 2131296931 */:
                String currentLanguageName = OtaDataManager.getCurrentLanguageName();
                String currentLanguageVersion = OtaDataManager.getCurrentLanguageVersion(currentLanguageName);
                RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "vrLanguage:" + currentLanguageName + ",dpVersion:" + currentLanguageVersion);
                if (TextUtils.isEmpty(currentLanguageName) || TextUtils.isEmpty(currentLanguageVersion)) {
                    RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "wakeup free setting--ota language is null vrLanguage:" + currentLanguageName + ",version:" + currentLanguageVersion);
                } else if (currentLanguageName.contains("cmn") && !ToolUtils.compareNewVersionIsHigher(com.byd.vrassistant.hint.Constant.CMN_FREE_WORDS_SUPPORT_VERSION, currentLanguageVersion.replace("V.", BuildConfig.FLAVOR))) {
                    RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "cmn,version < D.4.24.0618A");
                    showFreeWakeupWordsDialog();
                } else if ((currentLanguageName.contains("eng") || currentLanguageName.contains(LanguageName.POR)) && !ToolUtils.compareNewVersionIsHigher(com.byd.vrassistant.hint.Constant.ENG_POR_FREE_WORDS_SUPPORT_VERSION, currentLanguageVersion.replace("V.", BuildConfig.FLAVOR))) {
                    RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "eng or por version < D.4.24.0730S");
                    showFreeWakeupWordsDialog();
                } else {
                    EventBus.getDefault().post(new FragmentChangeBean());
                }
                break;
        }
    }

    public void setSwitchPrivacy(boolean z) {
        setSwitchStatus(this.iv_switch_privacy, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchStatus(ImageView imageView, boolean z) {
        imageView.setClickable(true);
        if (z) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_open, null));
        } else if (ModeUtils.isNightMode(getContext())) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_light_night, null));
        } else {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_light, null));
        }
    }

    private void setSwitchDisStatus(ImageView imageView, boolean z) {
        imageView.setClickable(false);
        if (z) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_open_dis, null));
            return;
        }
        if (imageView.getId() == R.id.iv_switch_audio_wakeup) {
            this.voice_setting_card_wakeup_free.setClickable(false);
            this.voice_setting_card_user_define.setClickable(false);
            this.voice_setting_card_register_lock.setClickable(false);
        }
        if (ModeUtils.isNightMode(getContext())) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_light_dis_night, null));
        } else {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_dis_light, null));
        }
    }

    private void setSwitchAutoStatus() {
        String string = Settings.System.getString(this.contentResolver, Constant.KEY_AUDIO_WAKEUP);
        Settings.System.getString(this.contentResolver, Constant.KEY_REGISTER_LOCK);
        if (string == null || string.length() == 0) {
            setSwitchDisStatus(this.iv_switch_audio_wakeup, false);
        } else {
            setSwitchStatus(this.iv_switch_audio_wakeup, Boolean.parseBoolean(string));
        }
    }

    private void createUserDefineDialog() {
        String subWakeUpWord;
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_wakeup_phrases, (ViewGroup) null);
        this.viewDialogWakePhrases = viewInflate;
        this.cl_wakeup_phrases = (ConstraintLayout) viewInflate.findViewById(R.id.cl_wakeup_phrases);
        this.userDefineDialog = new CustomDialog(getContext(), this.viewDialogWakePhrases);
        this.v_wakeup_phrases = this.viewDialogWakePhrases.findViewById(R.id.v_wakeup_phrases);
        this.tv_title_wakeup = (TextView) this.viewDialogWakePhrases.findViewById(R.id.tv_title_wakeup);
        this.tv_wakeup_phrases = (TextView) this.viewDialogWakePhrases.findViewById(R.id.tv_wakeup_phrases);
        this.tv_btn_yes_wakeup = (TextView) this.viewDialogWakePhrases.findViewById(R.id.tv_btn_yes_wakeup);
        this.tv_btn_no_wakeup = (TextView) this.viewDialogWakePhrases.findViewById(R.id.tv_btn_no_wakeup);
        this.wakeup_line = this.viewDialogWakePhrases.findViewById(R.id.wakeup_line);
        this.editText = (EditText) this.viewDialogWakePhrases.findViewById(R.id.et_wake_up);
        this.ivEmpty = (ImageView) this.viewDialogWakePhrases.findViewById(R.id.iv_empty);
        this.v_wakeupBackground = this.viewDialogWakePhrases.findViewById(R.id.v_wakeupBackground);
        RLog.e("NEU_Wakeup_Words", "language =" + Common.getSystemLanguage(getContext()));
        setClickPlaySound(this.v_wakeupBackground, this.userDefineDialog);
        this.editText.setHint(String.format(getResources().getString(R.string.wakeup_favorite_wake_word_hint), OtaDataManager.getDPSCurrentLanguage()));
        this.set.clone(this.cl_wakeup_phrases);
        if (Common.isRightDriver() || Common.isRtl(getContext())) {
            this.set.clear(R.id.tv_btn_yes_wakeup, 6);
            this.set.clear(R.id.tv_btn_yes_wakeup, 7);
            this.set.connect(R.id.tv_btn_yes_wakeup, 7, R.id.v_wakeup_phrases, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_wakeup, 6, R.id.tv_btn_no_wakeup, 7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_wakeup, 6);
            this.set.clear(R.id.tv_btn_no_wakeup, 7);
            this.set.connect(R.id.tv_btn_no_wakeup, 6, R.id.v_wakeup_phrases, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_wakeup, 7, R.id.tv_btn_yes_wakeup, 6);
        } else {
            this.set.clear(R.id.tv_btn_no_wakeup, 6);
            this.set.clear(R.id.tv_btn_no_wakeup, 7);
            this.set.connect(R.id.tv_btn_no_wakeup, 7, R.id.v_wakeup_phrases, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_wakeup, 6, R.id.tv_btn_yes_wakeup, 7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_yes_wakeup, 6);
            this.set.clear(R.id.tv_btn_yes_wakeup, 7);
            this.set.connect(R.id.tv_btn_yes_wakeup, 6, R.id.v_wakeup_phrases, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_wakeup, 7, R.id.tv_btn_no_wakeup, 6);
        }
        this.set.applyTo(this.cl_wakeup_phrases);
        if (ModeUtils.isNightMode(getContext())) {
            this.cl_wakeup_phrases.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_wakeup_phrases.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_wakeup.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_wakeup_phrases.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_wakeup.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.editText.setBackground(getResources().getDrawable(R.drawable.rounded_editview_night, null));
            this.editText.setTextColor(getResources().getColor(R.color.dialog_editTextColor_night, null));
            this.editText.setHintTextColor(getResources().getColor(R.color.dialog_editTextHintColor_night, null));
            this.wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.ivEmpty.setImageResource(R.mipmap.empty_night);
        } else {
            this.cl_wakeup_phrases.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_wakeup_phrases.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_wakeup.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_wakeup_phrases.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_wakeup.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.editText.setTextColor(getResources().getColor(R.color.dialog_editTextColor, null));
            this.editText.setHintTextColor(getResources().getColor(R.color.dialog_editTextHintColor, null));
            this.editText.setBackground(getResources().getDrawable(R.drawable.rounded_editview, null));
            this.wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.ivEmpty.setImageResource(R.mipmap.empty);
        }
        if (this.mViewModel.userDefineText.length() > 0) {
            subWakeUpWord = this.mViewModel.userDefineText;
        } else {
            subWakeUpWord = Common.getSubWakeUpWord();
        }
        if (!TextUtils.isEmpty(subWakeUpWord)) {
            this.editText.setFocusable(true);
            this.editText.setFocusableInTouchMode(true);
            this.editText.requestFocus();
            ((InputMethodManager) this.editText.getContext().getSystemService("input_method")).showSoftInput(this.editText, 2);
            this.editText.setText(subWakeUpWord);
            EditText editText = this.editText;
            editText.setSelection(editText.getText().toString().length());
            this.ivEmpty.setVisibility(0);
        }
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.7
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                SettingFragment.this.mViewModel.userDefineText = BuildConfig.FLAVOR;
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "3 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_userDefineDialog.clearOnDetach(SettingFragment.this.userDefineDialog);
            }
        });
        this.detachableDialogDismissListener_userDefineDialog = detachableDialogDismissListenerWrap;
        this.userDefineDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.userDefineDialog.show();
        this.mViewModel.showDialogValue = 1;
        this.editText.setInputType(524288);
        this.editText.setFilters(new InputFilter[]{new EmojiExcludeFilter(), new InputFilter.LengthFilter(30)});
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.byd.vrsettings.fragment.SettingFragment.8
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                RLog.e("NEU_Wakeup_Words", "beforeTextChanged =" + i);
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String string = SettingFragment.this.editText.getText().toString();
                RLog.e("NEU_Wakeup_Words", "editText =" + string);
                if (string.isEmpty()) {
                    SettingFragment.this.ivEmpty.setVisibility(4);
                    SettingFragment.this.mViewModel.userDefineText = BuildConfig.FLAVOR;
                } else {
                    SettingFragment.this.ivEmpty.setVisibility(0);
                    SettingFragment.this.mViewModel.userDefineText = string;
                }
            }
        });
        this.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.9
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
                if (string.contains("cmn-CHN") || string.contains("yue-HKG")) {
                    if (i == 6 || i == 2 || i == 3 || i == 4) {
                        RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener onclick");
                        String string2 = SettingFragment.this.editText.getText().toString();
                        RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener");
                        if (Common.filterNonChineseCharacters(string2)) {
                            RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener filterNonChineseCharacters");
                            Common.DeleteLetterToast();
                        }
                        return false;
                    }
                } else if (string.contains("heb-ISR") && (i == 6 || i == 2 || i == 3 || i == 4)) {
                    RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener heb-ISR onclick");
                    String string3 = SettingFragment.this.editText.getText().toString();
                    RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener heb-ISR");
                    if (Common.filterNonhebISRCharacters(string3)) {
                        RLog.e("NEU_Wakeup_Words", "setOnEditorActionListener filterNonChineseCharacters heb-ISR");
                        Common.DeleteLetterToast();
                    }
                    return false;
                }
                return false;
            }
        });
        this.viewDialogWakePhrases.findViewById(R.id.iv_empty).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.editText.setText(R.string.text_empty);
            }
        });
        this.viewDialogWakePhrases.findViewById(R.id.tv_btn_no_wakeup).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "4 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.mViewModel.userDefineText = BuildConfig.FLAVOR;
                SettingFragment.this.userDefineDialog.dismiss();
            }
        });
        this.viewDialogWakePhrases.findViewById(R.id.tv_btn_yes_wakeup).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean zFilterDirtyWord;
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
                if (string.contains("cmn-CHN") || string.contains("yue-HKG")) {
                    if (Common.filterNonChineseCharacters(SettingFragment.this.editText.getText().toString())) {
                        Common.DeleteLetterToast();
                        return;
                    }
                } else if (string.contains("heb-ISR") && Common.filterNonhebISRCharacters(SettingFragment.this.editText.getText().toString())) {
                    Common.DeleteLetterToast();
                    return;
                }
                String strReplaceAll = Pattern.compile("\\s{2,}|\t").matcher(SettingFragment.this.editText.getText().toString().trim()).replaceAll(" ");
                if (strReplaceAll.length() == 0) {
                    try {
                        Common.setSubWakeUpWord(strReplaceAll);
                        SettingFragment.this.showLoading();
                    } catch (Exception unused) {
                        RLog.e("NEU_Wakeup_Words", "Error");
                    }
                    SettingFragment.this.mViewModel.showDialogValue = 0;
                    RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "5 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                    SettingFragment.this.mViewModel.userDefineText = BuildConfig.FLAVOR;
                    SettingFragment.this.userDefineDialog.dismiss();
                    return;
                }
                if (Common.GetCurrentLanguageForUserDefineWakeupWord() == 1) {
                    zFilterDirtyWord = Common.FilterDirtyWord(strReplaceAll, Common.dirtyWordsEng);
                } else if (Common.GetCurrentLanguageForUserDefineWakeupWord() == 2) {
                    zFilterDirtyWord = Common.FilterDirtyWordForChinese(strReplaceAll, Common.dirtyWordsChinese);
                } else {
                    zFilterDirtyWord = Common.GetCurrentLanguageForUserDefineWakeupWord() == 3 ? Common.FilterDirtyWord(strReplaceAll, Common.dirtyWordsPtbr) : false;
                }
                if (zFilterDirtyWord) {
                    CustomToast.showToast(BydVoiceSettingApplication.getContext(), SettingFragment.this.getResources().getString(R.string.text_dirty_wakeup_toast), PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
                int length = strReplaceAll.replaceAll(" ", BuildConfig.FLAVOR).length();
                RLog.e("NEU_Wakeup_Words", "text====" + strReplaceAll);
                RLog.e("NEU_Wakeup_Words", "text.length====" + strReplaceAll.length());
                RLog.e("NEU_Wakeup_Words", "textFilter.reallength====" + length);
                if ((Common.GetCurrentLanguageForUserDefineWakeupWord() == 1 || Common.GetCurrentLanguageForUserDefineWakeupWord() == 3) && (length < 8 || length > 16)) {
                    CustomToast.showToast(BydVoiceSettingApplication.getContext(), SettingFragment.this.getResources().getString(R.string.text_eng_wakeup_toast), PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
                if (Common.GetCurrentLanguageForUserDefineWakeupWord() == 2 && (length < 4 || length > 6)) {
                    CustomToast.showToast(BydVoiceSettingApplication.getContext(), SettingFragment.this.getResources().getString(R.string.text_chinese_wakeup_toast), PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
                if (length < 3 || length > 30) {
                    CustomToast.showToast(BydVoiceSettingApplication.getContext(), SettingFragment.this.getResources().getString(R.string.text_word_limit), PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
                try {
                    Common.setSubWakeUpWord(strReplaceAll);
                    ToastUtils.show(BydVoiceSettingApplication.getContext(), strReplaceAll + " " + ((Object) SettingFragment.this.getResources().getText(R.string.text_set_success_last)));
                    SettingFragment.this.showLoading();
                } catch (Exception unused2) {
                    RLog.e("NEU_Wakeup_Words", "Error");
                }
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "6 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.mViewModel.userDefineText = BuildConfig.FLAVOR;
                SettingFragment.this.userDefineDialog.dismiss();
            }
        });
    }

    private void createRegisterLockDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_driver_register_lock, (ViewGroup) null);
        this.viewDialogRegister = viewInflate;
        this.register_lock_layout = (ConstraintLayout) viewInflate.findViewById(R.id.v_driver_register_lock);
        this.v_driver_register_lock = this.viewDialogRegister.findViewById(R.id.v_driver_register_lock);
        this.registerLockDialog = new CustomDialog(getContext(), this.viewDialogRegister);
        this.tv_title_register = (TextView) this.viewDialogRegister.findViewById(R.id.tv_title_register);
        this.tv_content_register = (TextView) this.viewDialogRegister.findViewById(R.id.tv_content_register);
        this.tv_driver_wakeup_locked = (TextView) this.viewDialogRegister.findViewById(R.id.tv_driver_wakeup_locked);
        this.tv_driver_wakeup_locked_tip = (TextView) this.viewDialogRegister.findViewById(R.id.tv_driver_wakeup_locked_tip);
        this.tv_front_passenger_wakeup_locked = (TextView) this.viewDialogRegister.findViewById(R.id.tv_front_passenger_wakeup_locked);
        this.tv_front_passenger_wakeup_locked_tip = (TextView) this.viewDialogRegister.findViewById(R.id.tv_front_passenger_wakeup_locked_tip);
        this.tv_btn_yes_register = (TextView) this.viewDialogRegister.findViewById(R.id.tv_btn_yes_register);
        this.tv_btn_no_register = (TextView) this.viewDialogRegister.findViewById(R.id.tv_btn_no_register);
        this.iv_driver_wakeup_locked = (ImageView) this.viewDialogRegister.findViewById(R.id.iv_driver_wakeup_locked);
        this.iv_front_passenger_wakeup_locked = (ImageView) this.viewDialogRegister.findViewById(R.id.iv_front_passenger_wakeup_locked);
        this.set.clone(this.register_lock_layout);
        if (Common.isRightDriver()) {
            this.iv_driver_wakeup_locked.setImageResource(R.mipmap.ic_front_passenger_wakeup_locked);
            this.iv_front_passenger_wakeup_locked.setImageResource(R.mipmap.ic_driver_wakeup_locked);
            this.set.clear(R.id.tv_btn_yes_register, 6);
            this.set.clear(R.id.tv_btn_yes_register, 7);
            this.set.connect(R.id.tv_btn_yes_register, 7, R.id.v_driver_register_lock, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_register, 6, R.id.tv_btn_no_register, 7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_register, 6);
            this.set.clear(R.id.tv_btn_no_register, 7);
            this.set.connect(R.id.tv_btn_no_register, 6, R.id.v_driver_register_lock, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_register, 7, R.id.tv_btn_yes_register, 6);
        } else {
            this.iv_driver_wakeup_locked.setImageResource(R.mipmap.ic_driver_wakeup_locked);
            this.iv_front_passenger_wakeup_locked.setImageResource(R.mipmap.ic_front_passenger_wakeup_locked);
            this.set.clear(R.id.tv_btn_yes_register, 6);
            this.set.clear(R.id.tv_btn_yes_register, 7);
            this.set.connect(R.id.tv_btn_yes_register, 7, R.id.tv_btn_no_register, 6, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_register, 6, R.id.v_driver_register_lock, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_register, 6);
            this.set.clear(R.id.tv_btn_no_register, 7);
            this.set.connect(R.id.tv_btn_no_register, 7, R.id.v_driver_register_lock, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_register, 6, R.id.tv_btn_yes_register, 7);
        }
        this.set.applyTo(this.register_lock_layout);
        if (ModeUtils.isNightMode(getContext())) {
            this.tv_btn_yes_register.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_register.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.register_lock_layout.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog_night, null));
            this.tv_title_register.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.tv_content_register.setTextColor(getResources().getColor(R.color.voice_setting_text_night, null));
            this.tv_driver_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.tv_driver_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night, null));
            this.tv_front_passenger_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.tv_front_passenger_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night, null));
            this.tv_btn_no_register.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
        } else {
            this.tv_btn_yes_register.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_register.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.register_lock_layout.setBackground(getResources().getDrawable(R.mipmap.register_lock_background, null));
            this.tv_title_register.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_content_register.setTextColor(getResources().getColor(R.color.voice_setting_text, null));
            this.tv_driver_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_driver_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
            this.tv_front_passenger_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_front_passenger_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
            this.tv_btn_no_register.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        }
        this.registerLockDialog.show();
        this.viewDialogRegister.findViewById(R.id.tv_btn_no_register).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.registerLockDialog.dismiss();
            }
        });
        this.viewDialogRegister.findViewById(R.id.tv_btn_yes_register).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.registerLockDialog.dismiss();
            }
        });
    }

    private void createAllRegisterLockDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_register_lock, (ViewGroup) null);
        this.viewDialogAllRegister = viewInflate;
        this.all_register_lock_layout = (ConstraintLayout) viewInflate.findViewById(R.id.cl_register_lock);
        this.register_background = this.viewDialogAllRegister.findViewById(R.id.register_lock_background);
        this.allRegisterLockDialog = new CustomDialog(getContext(), this.viewDialogAllRegister);
        View viewFindViewById = this.viewDialogAllRegister.findViewById(R.id.v_background);
        this.v_background = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.allRegisterLockDialog);
        this.tv_title = (TextView) this.viewDialogAllRegister.findViewById(R.id.tv_title);
        this.tv_four_register_locked = (TextView) this.viewDialogAllRegister.findViewById(R.id.tv_four_register_locked);
        this.iv_register_lock = (ImageView) this.viewDialogAllRegister.findViewById(R.id.iv_register_lock);
        this.iv_front_left = (Button) this.viewDialogAllRegister.findViewById(R.id.iv_front_left);
        this.iv_front_right = (Button) this.viewDialogAllRegister.findViewById(R.id.iv_front_right);
        this.iv_rear_left = (Button) this.viewDialogAllRegister.findViewById(R.id.iv_rear_left);
        this.iv_rear_right = (Button) this.viewDialogAllRegister.findViewById(R.id.iv_rear_right);
        this.tv_btn_yes = (TextView) this.viewDialogAllRegister.findViewById(R.id.tv_btn_yes);
        this.register_lock_line = this.viewDialogAllRegister.findViewById(R.id.register_lock_line);
        this.set.clone(this.all_register_lock_layout);
        if (Common.isRightDriver()) {
            this.driver = this.iv_front_right;
            this.passenger = this.iv_front_left;
        } else {
            this.driver = this.iv_front_left;
            this.passenger = this.iv_front_right;
        }
        this.set.applyTo(this.all_register_lock_layout);
        if (ModeUtils.isNightMode(getContext())) {
            this.register_background.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.all_register_lock_layout.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_four_register_locked.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.register_lock_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            if (Common.isRightDriver()) {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_night_right);
            } else {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_night);
            }
        } else {
            this.register_background.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.all_register_lock_layout.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_four_register_locked.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.register_lock_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            if (Common.isRightDriver()) {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_right);
            } else {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock);
            }
        }
        final String strInitAllRegister = initAllRegister();
        this.tv_btn_yes.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String strSendRegisterSet = SettingFragment.this.sendRegisterSet();
                if ((!TextUtils.isEmpty(strInitAllRegister) || !strSendRegisterSet.equals("8")) && !strSendRegisterSet.equals(strInitAllRegister)) {
                    SettingFragment.this.cancelAllRegisterLockDialog();
                    Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY_REGISTER_SET_REQUEST, strSendRegisterSet);
                } else {
                    SettingFragment.this.cancelAllRegisterLockDialog();
                }
            }
        });
        this.driver.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ToastUtils.show(BydVoiceSettingApplication.getContext(), R.string.text_driver_disable);
            }
        });
        this.passenger.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.clickLockingIcon(view);
            }
        });
        this.iv_rear_left.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.clickLockingIcon(view);
            }
        });
        this.iv_rear_right.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.clickLockingIcon(view);
            }
        });
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.20
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "allRegisterLockDialog onDismiss");
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "9 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_allRegisterLockDialog.clearOnDetach(SettingFragment.this.allRegisterLockDialog);
            }
        });
        this.detachableDialogDismissListener_allRegisterLockDialog = detachableDialogDismissListenerWrap;
        this.allRegisterLockDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.allRegisterLockDialog.show();
        this.mViewModel.showDialogValue = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAllRegisterLockDialog() {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "sendRegisterSet == " + Settings.System.getString(this.contentResolver, Constant.KEY_REGISTER_SET_RESULT));
        this.mViewModel.showDialogValue = 0;
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "7 mViewModel.showDialogValue==" + this.mViewModel.showDialogValue);
        this.allRegisterLockDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickLockingIcon(View view) {
        if (view.isSelected()) {
            view.setSelected(false);
        } else {
            view.setSelected(true);
        }
    }

    private void createVersionDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_setting_voice_version, (ViewGroup) null);
        this.viewDialogVersion = viewInflate;
        this.cl_version = viewInflate.findViewById(R.id.cl_version);
        this.v_versionBackground = this.viewDialogVersion.findViewById(R.id.v_versionBackground);
        CustomDialog customDialog = new CustomDialog(getContext(), this.viewDialogVersion);
        this.versionDialog = customDialog;
        setClickPlaySound(this.v_versionBackground, customDialog);
        this.version_backView = this.viewDialogVersion.findViewById(R.id.version_backView);
        this.tv_version_title = (TextView) this.viewDialogVersion.findViewById(R.id.tv_version_title);
        this.iv_version_back = (ImageView) this.viewDialogVersion.findViewById(R.id.iv_version_back);
        this.tv_smart_voice = (TextView) this.viewDialogVersion.findViewById(R.id.tv_smart_voice);
        this.tv_smart_voice_version = (TextView) this.viewDialogVersion.findViewById(R.id.tv_smart_voice_version);
        this.tv_download_version = (TextView) this.viewDialogVersion.findViewById(R.id.tv_download_version);
        this.tv_setting_version = (TextView) this.viewDialogVersion.findViewById(R.id.tv_setting_version);
        this.tv_settingSDK_version = (TextView) this.viewDialogVersion.findViewById(R.id.tv_settingSDK_version3);
        this.tv_copyright = (TextView) this.viewDialogVersion.findViewById(R.id.tv_copyright);
        this.tv_btn_disclaimer = (TextView) this.viewDialogVersion.findViewById(R.id.tv_btn_disclaimer);
        this.setting_voice_line = this.viewDialogVersion.findViewById(R.id.setting_voice_line);
        this.iv_smart_voice = (ImageView) this.viewDialogVersion.findViewById(R.id.iv_smart_voice);
        this.tv = (TextView) this.viewDialogVersion.findViewById(R.id.tv);
        this.tv3 = (TextView) this.viewDialogVersion.findViewById(R.id.tv3);
        TextView textView = (TextView) this.viewDialogVersion.findViewById(R.id.tv4);
        this.tv4 = textView;
        textView.setVisibility(8);
        this.tv_settingSDK_version.setVisibility(8);
        if (ModeUtils.isNightMode(getContext())) {
            this.cl_version.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_version_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_smart_voice.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_download_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_setting_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv3.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv4.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.setting_voice_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.iv_version_back.setImageDrawable(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144_night, null));
        } else {
            this.cl_version.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_version_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_smart_voice.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_download_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_setting_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv3.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv4.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.setting_voice_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.iv_version_back.setImageDrawable(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
            this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144, null));
        }
        this.iv_version_back.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.tv4.setVisibility(8);
                SettingFragment.this.tv_settingSDK_version.setVisibility(8);
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "10 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.versionDialog.dismiss();
            }
        });
        this.tv_btn_disclaimer.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 7;
                SettingFragment.this.createDisclaimerPrivacyDialog();
            }
        });
        this.tv_download_version.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.continuoustwoClick();
            }
        });
        try {
            String str = getContext().getPackageManager().getPackageInfo("com.byd.vrassistant", 0).versionName;
            String str2 = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            String string = Settings.System.getString(getContext().getContentResolver(), "KEY_VOICE_VERSION");
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "sdkVersionJson = " + string);
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "assistantVerName = " + str);
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "settingsVerName = " + str2);
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "DownloadAppVersion = " + AidlServiceManager.getInstance().getDownloadAppVersion());
            this.tv_smart_voice_version.setText("V." + str);
            this.tv_setting_version.setText("V." + str2);
            this.tv_settingSDK_version.setText("V." + string);
            this.tv_download_version.setText(AidlServiceManager.getInstance().getDownloadAppVersion());
        } catch (PackageManager.NameNotFoundException | RemoteException e) {
            e.printStackTrace();
        }
        this.mViewModel.showDialogValue = 6;
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.24
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "versionDialog onDismiss");
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "12 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_version.clearOnDetach(SettingFragment.this.versionDialog);
            }
        });
        this.detachableDialogDismissListener_version = detachableDialogDismissListenerWrap;
        this.versionDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.versionDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continuoustwoClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.time < 2000) {
            this.count++;
        } else {
            this.count = 0;
        }
        this.time = jElapsedRealtime;
        if (this.count >= 5) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "continuousClick: 连续点击，开启暗门 ,查看版本");
            this.tv4.setVisibility(0);
            this.tv_settingSDK_version.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDisclaimerPrivacyDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_disclaimer_privacy, (ViewGroup) null);
        this.viewDialogDisclaimerPrivacy = viewInflate;
        this.cl_guide = (ConstraintLayout) viewInflate.findViewById(R.id.cl_guide);
        this.disclaimerPrivacyDialog = new CustomDialog(getContext(), this.viewDialogDisclaimerPrivacy);
        View viewFindViewById = this.viewDialogDisclaimerPrivacy.findViewById(R.id.v_guideBackground);
        this.v_guideBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.disclaimerPrivacyDialog);
        this.v_guide = this.viewDialogDisclaimerPrivacy.findViewById(R.id.v_guide);
        this.sv_guide = (ScrollView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.sv_guide);
        this.tv_title_guide = (TextView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.tv_title_guide);
        this.tv_text_guide = (TextView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.tv_text_guide);
        this.disclaimer_BtnX = (ImageView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.disclaimer_BtnX);
        this.disclaimer_line = this.viewDialogDisclaimerPrivacy.findViewById(R.id.disclaimer_line);
        if (ModeUtils.isNightMode(getContext())) {
            this.v_guide.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            if (Build.VERSION.SDK_INT >= 29) {
                this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
            }
            this.disclaimer_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            this.disclaimer_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.v_guide.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            if (Build.VERSION.SDK_INT >= 29) {
                this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
            }
            this.disclaimer_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
            this.disclaimer_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        this.disclaimer_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 6;
                SettingFragment.this.disclaimerPrivacyDialog.dismiss();
            }
        });
        String string = getResources().getString(R.string.auth_text);
        String string2 = getResources().getString(R.string.auth_click);
        int iIndexOf = string.indexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        try {
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.byd.vrsettings.fragment.SettingFragment.26
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    SettingFragment.this.mViewModel.showDialogValue = 8;
                    view.playSoundEffect(0);
                    SettingFragment.this.createCerencePrivacyPolicyDialog();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#00ACEB"));
                    textPaint.setUnderlineText(true);
                }
            }, iIndexOf, string2.length() + iIndexOf, 33);
            this.tv_text_guide.setText(spannableStringBuilder);
            TextView textView = this.tv_text_guide;
            textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
            this.tv_text_guide.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception unused) {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "span 崩溃");
        }
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.27
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                SettingFragment.this.mViewModel.showDialogValue = 6;
                SettingFragment.this.detachableDialogDismissListener_disclaimerPrivacyDialog.clearOnDetach(SettingFragment.this.disclaimerPrivacyDialog);
            }
        });
        this.detachableDialogDismissListener_disclaimerPrivacyDialog = detachableDialogDismissListenerWrap;
        this.disclaimerPrivacyDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.disclaimerPrivacyDialog.show();
        this.v_guide.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 608.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 592.0f, getResources().getDisplayMetrics())));
        this.set.clone(this.cl_guide);
        this.set.connect(R.id.v_guide, 6, R.id.cl_guide, 6);
        this.set.connect(R.id.v_guide, 3, R.id.cl_guide, 3);
        this.set.connect(R.id.v_guide, 7, R.id.cl_guide, 7);
        this.set.connect(R.id.v_guide, 4, R.id.cl_guide, 4);
        this.set.applyTo(this.cl_guide);
    }

    private void createAudioWakeupDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_audio_wakeup, (ViewGroup) null);
        this.viewDialogAudio = viewInflate;
        this.audio_wakeUp_layout = (ConstraintLayout) viewInflate.findViewById(R.id.cl_audio_wakeup);
        this.audioWakeupDialog = new CustomDialog(getContext(), this.viewDialogAudio);
        View viewFindViewById = this.viewDialogAudio.findViewById(R.id.v_audioBackground);
        this.v_audioBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.audioWakeupDialog);
        this.v_audio = this.viewDialogAudio.findViewById(R.id.v_audio);
        this.tv_title_audio = (TextView) this.viewDialogAudio.findViewById(R.id.tv_title_audio);
        this.tv_content_audio = (TextView) this.viewDialogAudio.findViewById(R.id.tv_audio_wakeup);
        this.audio_wakeup_line = this.viewDialogAudio.findViewById(R.id.audio_wakeup_line);
        this.tv_btn_yes_audio = (TextView) this.viewDialogAudio.findViewById(R.id.tv_btn_yes_audio);
        this.tv_btn_no_audio = (TextView) this.viewDialogAudio.findViewById(R.id.tv_btn_no_audio);
        this.set.clone(this.audio_wakeUp_layout);
        if (Common.isRightDriver() || Common.isRtl(getContext())) {
            this.set.clear(R.id.tv_btn_yes_audio, 6);
            this.set.clear(R.id.tv_btn_yes_audio, 7);
            this.set.connect(R.id.tv_btn_yes_audio, 7, R.id.v_audio, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_audio, 6, R.id.tv_btn_no_audio, 7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_audio, 6);
            this.set.clear(R.id.tv_btn_no_audio, 7);
            this.set.connect(R.id.tv_btn_no_audio, 6, R.id.v_audio, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_audio, 7, R.id.tv_btn_yes_audio, 6);
        } else {
            this.set.clear(R.id.tv_btn_yes_audio, 6);
            this.set.clear(R.id.tv_btn_yes_audio, 7);
            this.set.connect(R.id.tv_btn_yes_audio, 6, R.id.v_audio, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_audio, 7, R.id.tv_btn_no_audio, 6, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_audio, 6);
            this.set.clear(R.id.tv_btn_no_audio, 7);
            this.set.connect(R.id.tv_btn_no_audio, 7, R.id.v_audio, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_audio, 6, R.id.tv_btn_yes_audio, 7);
        }
        this.set.applyTo(this.audio_wakeUp_layout);
        if (ModeUtils.isNightMode(getContext())) {
            this.audio_wakeUp_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_audio.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_audio.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_content_audio.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_audio.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.audio_wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.audio_wakeUp_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_audio.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_audio.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_content_audio.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_audio.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.audio_wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.28
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "audioWakeupDialog onCancel");
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "14 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_audioWakeupDialog.clearOnDetach(SettingFragment.this.audioWakeupDialog);
            }
        });
        this.detachableDialogDismissListener_audioWakeupDialog = detachableDialogDismissListenerWrap;
        this.audioWakeupDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.audioWakeupDialog.show();
        this.mViewModel.showDialogValue = 3;
        this.viewDialogAudio.findViewById(R.id.tv_btn_no_audio).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "15 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.audioWakeupDialog.dismiss();
            }
        });
        this.viewDialogAudio.findViewById(R.id.tv_btn_yes_audio).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long unused = SettingFragment.lastClickTime = SystemClock.elapsedRealtime();
                Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY_AUDIO_WAKEUP, "false");
                RLog.e("NEU_Audio_Wakeup", Settings.System.getString(SettingFragment.this.contentResolver, Constant.KEY_AUDIO_WAKEUP));
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.setSwitchStatus(settingFragment.iv_switch_audio_wakeup, false);
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "16 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.audioWakeupDialog.dismiss();
                SettingFragment.this.setDisable();
                SettingFragment.this.showLoading();
            }
        });
    }

    private void createContinneDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_audio_wakeup, (ViewGroup) null);
        this.viewContinueDialog = viewInflate;
        this.continue_dialog_layout = (ConstraintLayout) viewInflate.findViewById(R.id.cl_audio_wakeup);
        this.continue_dialog_Dialog = new CustomDialog(getContext(), this.viewContinueDialog);
        View viewFindViewById = this.viewContinueDialog.findViewById(R.id.v_audioBackground);
        this.v_continue_Background = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.continue_dialog_Dialog);
        this.v_continueDialog = this.viewContinueDialog.findViewById(R.id.v_audio);
        this.tv_title_continue_dialog = (TextView) this.viewContinueDialog.findViewById(R.id.tv_title_audio);
        this.tv_content_continue_dialog = (TextView) this.viewContinueDialog.findViewById(R.id.tv_audio_wakeup);
        this.continue_dialog_line = this.viewContinueDialog.findViewById(R.id.audio_wakeup_line);
        this.tv_btn_yes_audio_continue = (TextView) this.viewContinueDialog.findViewById(R.id.tv_btn_yes_audio);
        this.tv_btn_no_audio_continue = (TextView) this.viewContinueDialog.findViewById(R.id.tv_btn_no_audio);
        this.tv_title_continue_dialog.setText(getContext().getString(R.string.continue_dialog_title));
        this.tv_content_continue_dialog.setText(getContext().getString(R.string.continue_dialog_disable));
        this.set.clone(this.continue_dialog_layout);
        if (Common.isRightDriver() || Common.isRtl(getContext())) {
            this.set.clear(R.id.tv_btn_yes_audio, 6);
            this.set.clear(R.id.tv_btn_yes_audio, 7);
            this.set.connect(R.id.tv_btn_yes_audio, 7, R.id.v_audio, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_audio, 6, R.id.tv_btn_no_audio, 7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_audio, 6);
            this.set.clear(R.id.tv_btn_no_audio, 7);
            this.set.connect(R.id.tv_btn_no_audio, 6, R.id.v_audio, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_audio, 7, R.id.tv_btn_yes_audio, 6);
        } else {
            this.set.clear(R.id.tv_btn_yes_audio, 6);
            this.set.clear(R.id.tv_btn_yes_audio, 7);
            this.set.connect(R.id.tv_btn_yes_audio, 6, R.id.v_audio, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_yes_audio, 7, R.id.tv_btn_no_audio, 6, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_no_audio, 6);
            this.set.clear(R.id.tv_btn_no_audio, 7);
            this.set.connect(R.id.tv_btn_no_audio, 7, R.id.v_audio, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_no_audio, 6, R.id.tv_btn_yes_audio, 7);
        }
        this.set.applyTo(this.continue_dialog_layout);
        if (ModeUtils.isNightMode(getContext())) {
            this.continue_dialog_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_continueDialog.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_content_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_audio_continue.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.continue_dialog_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.continue_dialog_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_continueDialog.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_content_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_audio_continue.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.continue_dialog_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.31
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "continue_dialog_Dialog onCancel");
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "17 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_continueDialog.clearOnDetach(SettingFragment.this.continue_dialog_Dialog);
            }
        });
        this.detachableDialogDismissListener_continueDialog = detachableDialogDismissListenerWrap;
        this.continue_dialog_Dialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.continue_dialog_Dialog.show();
        this.mViewModel.showDialogValue = 9;
        this.viewContinueDialog.findViewById(R.id.tv_btn_no_audio).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "15 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.continue_dialog_Dialog.dismiss();
            }
        });
        this.viewContinueDialog.findViewById(R.id.tv_btn_yes_audio).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long unused = SettingFragment.lastClickTime = SystemClock.elapsedRealtime();
                Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY_CONTINUE_DIALOG, "false");
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.setSwitchStatus(settingFragment.iv_switch_contiunue_dialog, false);
                RLog.e("NEU_KEY_CONTINUE_DIALOG", Settings.System.getString(SettingFragment.this.contentResolver, Constant.KEY_CONTINUE_DIALOG));
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "16 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.continue_dialog_Dialog.dismiss();
                SettingFragment.this.setDisable();
            }
        });
    }

    private void createPrivacyPolicyDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_privacy_policy, (ViewGroup) null);
        this.viewPrivacyPolicy = viewInflate;
        this.privacy_policy_layout = (ConstraintLayout) viewInflate.findViewById(R.id.privacy_policy_layout);
        this.privacyPolicyDialog = new CustomDialog(getContext(), this.viewPrivacyPolicy);
        View viewFindViewById = this.viewPrivacyPolicy.findViewById(R.id.v_privacyBackground);
        this.v_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.privacyPolicyDialog);
        this.privacy = this.viewPrivacyPolicy.findViewById(R.id.privacy);
        this.tv_title_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_title_privacy);
        this.tv_privacy_policy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_privacy_policy);
        this.privacy_line = this.viewPrivacyPolicy.findViewById(R.id.privacy_line);
        this.tv_btn_cancel_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_cancel_privacy);
        this.tv_btn_disable_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_disable_privacy);
        String string = getResources().getString(R.string.text_privacy_content);
        String string2 = getResources().getString(R.string.text_privacy_content_click);
        int iIndexOf = string.indexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.byd.vrsettings.fragment.SettingFragment.34
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 5;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "a mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                view.playSoundEffect(0);
                SettingFragment.this.createCerencePrivacyPolicyDialog();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#00ACEB"));
                textPaint.setUnderlineText(true);
            }
        };
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "spannable.length() = " + spannableStringBuilder.length());
        try {
            if (iIndexOf > 0) {
                spannableStringBuilder.setSpan(clickableSpan, iIndexOf, string2.length() + iIndexOf, 33);
                this.tv_privacy_policy.setText(spannableStringBuilder);
            } else {
                this.tv_privacy_policy.setText(string);
            }
            TextView textView = this.tv_privacy_policy;
            textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
            this.tv_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception unused) {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "span崩溃");
        }
        if (ModeUtils.isNightMode(getContext())) {
            this.privacy_policy_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_disable_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.tv_btn_cancel_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_disable_privacy.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_privacy_policy.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.privacy_policy_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_disable_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.tv_btn_cancel_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_disable_privacy.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_privacy_policy.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        this.set.clone(this.privacy_policy_layout);
        if (Common.isRightDriver() || Common.isRtl(getContext())) {
            this.set.clear(R.id.tv_btn_disable_privacy, 6);
            this.set.clear(R.id.tv_btn_disable_privacy, 7);
            this.set.connect(R.id.tv_btn_disable_privacy, 7, R.id.tv_btn_cancel_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_disable_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.tv_btn_disable_privacy, 7);
        } else {
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.tv_btn_disable_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_disable_privacy, 6);
            this.set.clear(R.id.tv_btn_disable_privacy, 7);
            this.set.connect(R.id.tv_btn_disable_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_disable_privacy, 6, R.id.tv_btn_cancel_privacy, 7);
        }
        this.set.applyTo(this.privacy_policy_layout);
        this.mViewModel.showDialogValue = 4;
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.35
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "19 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.detachableDialogDismissListener_privacyPolicyDialog.clearOnDetach(SettingFragment.this.privacyPolicyDialog);
            }
        });
        this.detachableDialogDismissListener_privacyPolicyDialog = detachableDialogDismissListenerWrap;
        this.privacyPolicyDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.privacyPolicyDialog.show();
        this.tv_btn_cancel_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY_PRIVACY_POLICY, "false");
                Settings.System.putString(SettingFragment.this.getContext().getContentResolver(), Constant.KEY_USER_IMPROVEMENT, "false");
                Settings.System.putString(SettingFragment.this.getContext().getContentResolver(), Constant.KEY_USER_IMPROVEMENT_EXIT, "false");
                SettingFragment settingFragment = SettingFragment.this;
                settingFragment.setSwitchStatus(settingFragment.iv_switch_privacy, false);
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "20 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.privacyPolicyDialog.dismiss();
                Process.killProcess(Process.myPid());
            }
        });
        this.tv_btn_disable_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "21 mViewModel.showDialogValue==" + SettingFragment.this.mViewModel.showDialogValue);
                SettingFragment.this.privacyPolicyDialog.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createCerencePrivacyPolicyDialog() {
        if (Common.isFastDoubleClick(11, 1000L)) {
            return;
        }
        this.viewCerencePrivacy = LayoutInflater.from(getContext()).inflate(R.layout.layout_cerence_privacy_pllicy, (ViewGroup) null);
        OtaDataManager.switchAppLanguage(getActivity());
        this.cl_cerence_privacy = (ConstraintLayout) this.viewCerencePrivacy.findViewById(R.id.cl_cerence_privacy);
        this.cerencePrivacyPolicyDialog = new CustomDialog(getContext(), this.viewCerencePrivacy);
        View viewFindViewById = this.viewCerencePrivacy.findViewById(R.id.v_cerence_privacyBackground);
        this.v_cerence_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.cerencePrivacyPolicyDialog);
        this.cerence_privacy = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy);
        MyWebView myWebView = (MyWebView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_webView);
        this.cerence_privacy_webView = myWebView;
        myWebView.setLayerType(1, null);
        if (this.mViewModel.curPos != 0) {
            RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "111 mViewModel.curPos ==" + this.mViewModel.curPos);
            MyWebView myWebView2 = this.cerence_privacy_webView;
            if (myWebView2 != null) {
                myWebView2.scrollTo(0, this.mViewModel.curPos);
            }
        }
        this.cerence_privacy_webView.setWebChromeClient(new WebChromeClient() { // from class: com.byd.vrsettings.fragment.SettingFragment.38
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (webView.getProgress() == 100) {
                    RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "onProgressChanged mViewModel.curPos ==" + SettingFragment.this.mViewModel.curPos);
                    Common.jumpToY(SettingFragment.this.cerence_privacy_webView, SettingFragment.this.mViewModel.curPos);
                }
            }
        });
        this.cerence_privacy_webView.setOnOveredScroll(new MyWebView.onOveredScroll() { // from class: com.byd.vrsettings.fragment.SettingFragment.39
            @Override // com.byd.vrsettings.utils.MyWebView.onOveredScroll
            public void onOvered(int i) {
                if (SettingFragment.this.onconfigchange) {
                    SettingFragment.this.onconfigchange = false;
                } else {
                    RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "setOnOveredScroll  scrollY==" + i);
                    SettingFragment.this.mViewModel.curPos = i;
                }
            }
        });
        this.cerence_privacy_BtnX = (ImageView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_BtnX);
        this.tv_title_cerence_privacy = (TextView) this.viewCerencePrivacy.findViewById(R.id.tv_title_cerence_privacy);
        this.cerence_privacy_line = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_line);
        if (ModeUtils.isNightMode(getContext())) {
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.cerence_privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_cerence_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.cerence_privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(true));
            this.cerence_privacy_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
        } else {
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.cerence_privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_cerence_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(false));
            this.cerence_privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar, null));
            this.cerence_privacy_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
        }
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.40
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                SettingFragment.this.destroyWebView();
                SettingFragment.this.mViewModel.curPos = 0;
                SettingFragment.this.viewCerencePrivacy = null;
                if (SettingFragment.this.mViewModel.showDialogValue == 5) {
                    SettingFragment.this.mViewModel.showDialogValue = 4;
                } else {
                    SettingFragment.this.mViewModel.showDialogValue = 7;
                }
                SettingFragment.this.detachableDialogDismissListener_cerencePrivacyPolicyDialog.clearOnDetach(SettingFragment.this.cerencePrivacyPolicyDialog);
            }
        });
        this.detachableDialogDismissListener_cerencePrivacyPolicyDialog = detachableDialogDismissListenerWrap;
        this.cerencePrivacyPolicyDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.cerencePrivacyPolicyDialog.show();
        this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 608.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 592.0f, getResources().getDisplayMetrics())));
        this.set.clone(this.cl_cerence_privacy);
        this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
        this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
        this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
        this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
        this.set.applyTo(this.cl_cerence_privacy);
        this.cerence_privacy_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.e("YZS", "customDialog===cerence_privacy_BtnX");
                if (SettingFragment.this.mViewModel.showDialogValue == 5) {
                    SettingFragment.this.mViewModel.showDialogValue = 4;
                } else {
                    SettingFragment.this.mViewModel.showDialogValue = 7;
                }
                SettingFragment.this.mViewModel.curPos = 0;
                SettingFragment.this.destroyWebView();
                SettingFragment.this.cerencePrivacyPolicyDialog.dismiss();
                SettingFragment.this.viewCerencePrivacy = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyWebView() {
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "destroyWebView");
        MyWebView myWebView = this.cerence_privacy_webView;
        if (myWebView != null) {
            ((ViewGroup) myWebView.getParent()).removeAllViews();
            this.cerence_privacy_webView.clearHistory();
            this.cerence_privacy_webView.clearCache(true);
            this.cerence_privacy_webView.loadUrl("about:blank");
            this.cerence_privacy_webView.onPause();
            this.cerence_privacy_webView.removeAllViews();
            this.cerence_privacy_webView.destroyDrawingCache();
            this.cerence_privacy_webView.pauseTimers();
            this.cerence_privacy_webView.destroy();
            this.cerence_privacy_webView = null;
        }
    }

    private void jumpToY(final int i) {
        MyWebView myWebView = this.cerence_privacy_webView;
        if (myWebView != null) {
            myWebView.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.SettingFragment.42
                @Override // java.lang.Runnable
                public void run() {
                    if (SettingFragment.this.cerence_privacy_webView != null) {
                        SettingFragment.this.cerence_privacy_webView.scrollTo(0, i);
                    }
                }
            }, 300L);
        }
    }

    public String getLanguageDataPathSuffix() {
        String string = Settings.System.getString(this.contentResolver, "SP_KEY_CUR_LANGUAGE");
        return TextUtils.isEmpty(string) ? "data_eng-USA" : string;
    }

    public static String getCountrycode(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public String subStringWithfileName(String str) {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "subStringWithfileName:str =" + str);
        return str.indexOf("data_") > 0 ? str.substring(str.indexOf("data_")).substring(5) : BuildConfig.FLAVOR;
    }

    public List<String> getFilesAllName(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles == null) {
            RLog.e("error", "空目录");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            arrayList.add(file.getName().substring(5));
        }
        return arrayList;
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Configuration");
        if (configuration.orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Configuration.UI_MODE_NIGHT_YES");
            setNight();
        } else {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "Configuration.UI_MODE_NIGHT_NO");
            setLight();
        }
    }

    private void setNight() {
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "setNight: ");
        setDisable();
        this.v_card.setBackground(getResources().getDrawable(R.mipmap.bg_setting_popup_night, null));
        this.v_wakeup.setNight();
        this.v_contiunue_dialog.setNight();
        this.v_volume.setNight();
        this.v_language.setNight();
        this.v_version.setNight();
        this.v_privacy.setNight();
        this.v_user_Imporvement.setNight();
        this.tv_audio_wakeup_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_contiunue_dialog_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_audio_registe_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_audio_wakeup_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night_8155, null));
        this.tv_contiunue_dialog_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night_8155, null));
        this.tv_audio_registe_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night_8155, null));
        this.iv_volume_move.setImageResource(R.mipmap.img_volume_move_night);
        this.tv_volume_move.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_voice_broadcast_volume.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_language.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_cur_language.setTextColor(getResources().getColor(R.color.voice_setting_title_night_8155, null));
        this.tv_version_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_user_Imporvement_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_privacy_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_volume.setTextColor(getResources().getColor(R.color.voice_setting_tip_night_8155, null));
        this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
        this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
        this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
        this.language_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou_night, null));
        this.version_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou_night, null));
        this.v_user_Imporvement_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou_night, null));
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_setting.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
        this.tv_wakeup.setTextColor(getResources().getColor(R.color.voice_setting_tv_wakeup_night_8155, null));
        Rect bounds = this.sb_volume.getProgressDrawable().getBounds();
        this.sb_volume.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress_drawable_night_8155, null));
        this.sb_volume.getProgressDrawable().setBounds(bounds);
        if (this.isAutograph) {
            setSwitchAutoStatus();
            String string = Settings.System.getString(this.contentResolver, Constant.KEY_PRIVACY_POLICY);
            if (string == null || string.length() == 0) {
                setSwitchDisStatus(this.iv_switch_privacy, false);
            } else if (string.equals("false")) {
                setSwitchStatus(this.iv_switch_privacy, false);
            }
        }
        if (this.viewDialogWakePhrases != null) {
            this.cl_wakeup_phrases.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_wakeup_phrases.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_wakeup.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_wakeup_phrases.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_wakeup.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.editText.setBackground(getResources().getDrawable(R.drawable.rounded_editview_night, null));
            this.editText.setTextColor(getResources().getColor(R.color.dialog_editTextColor_night, null));
            this.editText.setHintTextColor(getResources().getColor(R.color.dialog_editTextHintColor_night, null));
            this.wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.ivEmpty.setImageResource(R.mipmap.empty_night);
        }
        if (this.viewDialogVersion != null) {
            this.cl_version.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_version_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_smart_voice.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_download_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_setting_version.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv3.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv4.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_night_8155, null));
            this.setting_voice_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.iv_version_back.setImageDrawable(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144_night, null));
        }
        if (this.viewDialogAllRegister != null) {
            this.register_background.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.all_register_lock_layout.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_four_register_locked.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.register_lock_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            if (Common.isRightDriver() || Common.isRtl(getContext())) {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_night_right);
            } else {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_night);
            }
        }
        if (this.viewDialogAudio != null) {
            this.audio_wakeUp_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_audio.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_audio.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_content_audio.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_audio.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.audio_wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        }
        if (this.viewContinueDialog != null) {
            this.continue_dialog_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_yes_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_no_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.v_continueDialog.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_content_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.tv_btn_no_audio_continue.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.continue_dialog_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        }
        if (this.viewPrivacyPolicy != null) {
            this.privacy_policy_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_disable_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.tv_btn_cancel_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_disable_privacy.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_privacy_policy.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        }
        if (this.viewCerencePrivacy != null) {
            RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "viewCerencePrivacy !!!=== null");
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.cerence_privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_cerence_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.cerence_privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(true));
            this.cerence_privacy_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            this.set.clone(this.cl_cerence_privacy);
            this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
            this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
            this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
            this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
            this.set.applyTo(this.cl_cerence_privacy);
            this.onconfigchange = true;
        } else {
            RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "viewCerencePrivacy ==== null");
        }
        if (this.viewDialogDisclaimerPrivacy != null) {
            this.v_guide.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.dialog_contentTextColor_night, null));
            if (Build.VERSION.SDK_INT >= 29) {
                this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
            }
            this.disclaimer_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            this.disclaimer_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        }
        LoadingDialog.Builder builder = this.mLoadingBuilder;
        if (builder != null) {
            builder.setLight(false);
        }
    }

    private void setLight() {
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "setLight: ");
        setDisable();
        this.v_card.setBackground(getResources().getDrawable(R.mipmap.bg_setting_popup, null));
        this.v_wakeup.setLight();
        this.v_contiunue_dialog.setLight();
        this.v_volume.setLight();
        this.v_language.setLight();
        this.v_version.setLight();
        this.v_user_Imporvement.setLight();
        this.v_privacy.setLight();
        this.tv_audio_wakeup_title.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_contiunue_dialog_title.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_audio_registe_title.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_audio_wakeup_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.tv_contiunue_dialog_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.tv_audio_registe_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.iv_volume_move.setImageResource(R.mipmap.img_volume_move);
        this.tv_volume_move.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_voice_broadcast_volume.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_language.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_cur_language.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_version_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_user_Imporvement_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_privacy_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_volume.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.tv_wakeup_free_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_wakeup_free_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
        this.tv_user_define_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_user_define_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
        this.tv_register_lock_title.setTextColor(getResources().getColor(R.color.voice_setting_text_8155, null));
        this.tv_register_lock_tip.setTextColor(getResources().getColor(R.color.voice_setting_sub_text_8155, null));
        this.language_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou, null));
        this.version_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou, null));
        this.v_user_Imporvement_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou, null));
        this.tv_wakeup.setTextColor(getResources().getColor(R.color.voice_setting_tv_wakeup_8155, null));
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_setting.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
        Rect bounds = this.sb_volume.getProgressDrawable().getBounds();
        this.sb_volume.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress_drawable_8155, null));
        this.sb_volume.getProgressDrawable().setBounds(bounds);
        if (this.isAutograph) {
            setSwitchAutoStatus();
            String string = Settings.System.getString(this.contentResolver, Constant.KEY_PRIVACY_POLICY);
            if (string == null || string.length() == 0) {
                setSwitchDisStatus(this.iv_switch_privacy, false);
            } else if (string.equals("false")) {
                setSwitchStatus(this.iv_switch_privacy, false);
            }
        }
        if (this.viewDialogWakePhrases != null) {
            this.cl_wakeup_phrases.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_wakeup.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_wakeup_phrases.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_wakeup.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_wakeup_phrases.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_wakeup.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.editText.setTextColor(getResources().getColor(R.color.dialog_editTextColor, null));
            this.editText.setHintTextColor(getResources().getColor(R.color.dialog_editTextHintColor, null));
            this.editText.setBackground(getResources().getDrawable(R.drawable.rounded_editview, null));
            this.wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.ivEmpty.setImageResource(R.mipmap.empty);
        }
        if (this.viewDialogRegister != null) {
            this.v_driver_register_lock.setBackground(getResources().getDrawable(R.mipmap.register_lock_background, null));
            this.tv_title_register.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_content_register.setTextColor(getResources().getColor(R.color.voice_setting_text, null));
            this.tv_driver_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_driver_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
            this.tv_front_passenger_wakeup_locked.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
            this.tv_front_passenger_wakeup_locked_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
            this.tv_btn_no_register.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        }
        if (this.viewDialogAllRegister != null) {
            this.register_background.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.all_register_lock_layout.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_four_register_locked.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.register_lock_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            if (Common.isRightDriver() || Common.isRtl(getContext())) {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock_right);
            } else {
                this.iv_register_lock.setImageResource(R.mipmap.img_register_lock);
            }
        }
        if (this.viewDialogVersion != null) {
            this.cl_version.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_version_title.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_smart_voice.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_download_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_setting_version.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv3.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv4.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
            this.setting_voice_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.iv_version_back.setImageDrawable(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
            this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144, null));
        }
        if (this.viewDialogAudio != null) {
            this.audio_wakeUp_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_audio.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_audio.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_audio.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_content_audio.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_audio.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.audio_wakeup_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        if (this.viewContinueDialog != null) {
            this.continue_dialog_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_yes_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_no_audio_continue.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.v_continueDialog.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_content_continue_dialog.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.tv_btn_no_audio_continue.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.continue_dialog_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        if (this.viewPrivacyPolicy != null) {
            this.privacy_policy_layout.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_disable_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.tv_btn_cancel_privacy.setBackground(getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_disable_privacy.setTextColor(getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_privacy_policy.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            this.privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        if (this.viewCerencePrivacy != null) {
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.cerence_privacy.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_cerence_privacy.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(false));
            this.cerence_privacy_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar, null));
            this.cerence_privacy_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
            this.set.clone(this.cl_cerence_privacy);
            this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
            this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
            this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
            this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
            this.set.applyTo(this.cl_cerence_privacy);
            this.onconfigchange = true;
        }
        if (this.viewDialogDisclaimerPrivacy != null) {
            this.v_guide.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.dialog_contentTextColor, null));
            if (Build.VERSION.SDK_INT >= 29) {
                this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
            }
            this.disclaimer_BtnX.setBackground(getResources().getDrawable(R.mipmap.dialog_icon_back, null));
            this.disclaimer_line.setBackgroundColor(getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        LoadingDialog.Builder builder = this.mLoadingBuilder;
        if (builder != null) {
            builder.setLight(true);
        }
    }

    private void addView(Fragment fragment) {
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            WakeupFreeFragment wakeupFreeFragment = (WakeupFreeFragment) this.fragmentManager.findFragmentByTag(Wakeup_FRAGMENT);
            if (wakeupFreeFragment == null) {
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "addView: wuFragment == null");
                WakeupFreeFragment wakeupFreeFragment2 = new WakeupFreeFragment();
                this.wakeupFreeFragment = wakeupFreeFragment2;
                fragmentTransactionBeginTransaction.add(R.id.fl_setting, wakeupFreeFragment2, Wakeup_FRAGMENT);
            } else {
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "addView: wuFragment != null");
                this.wakeupFreeFragment = wakeupFreeFragment;
            }
            fragmentTransactionBeginTransaction.show(this.wakeupFreeFragment);
            fragmentTransactionBeginTransaction.commit();
        }
    }

    private void removeView(Fragment fragment) {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "removeView: begin");
        if (this.fragmentManager != null) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "removeView: fragmentManager != null");
            this.fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishVersionFragment(FinishVersionFragmentBean finishVersionFragmentBean) {
        removeView(this.versionFragment);
        this.versionFragment = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishOtaFragment(FinishOtaFragmentEvent finishOtaFragmentEvent) {
        removeView(this.otaFragment);
        this.otaFragment = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSwitchPrivacy(PrivacyPolicyEvent privacyPolicyEvent) {
        String type = privacyPolicyEvent.getType();
        type.hashCode();
        switch (type) {
            case "KEY_AUDIO_WAKEUP":
                if (privacyPolicyEvent.getValue().equals("false")) {
                    setSwitchStatus(this.iv_switch_audio_wakeup, false);
                } else {
                    setSwitchStatus(this.iv_switch_audio_wakeup, true);
                }
                setDisable();
                break;
            case "KEY_LANGUAG_EVENT":
                this.tv_cur_language.setText(OtaDataManager.getDPSCurrentLanguage());
                break;
            case "KEY_OTA_UPGRADE":
                RLog.i("wens", "event====KEY_OTA_UPGRADE");
                if (privacyPolicyEvent.getValue().equals("1")) {
                    this.view_update_point.setVisibility(0);
                    break;
                } else {
                    this.view_update_point.setVisibility(8);
                    break;
                }
                break;
            case "KEY_PRIVACY_POLICY":
                if (Settings.System.getString(this.contentResolver, Constant.KEY_PRIVACY_POLICY).equals("false")) {
                    setSwitchStatus(this.iv_switch_privacy, false);
                    break;
                } else {
                    setSwitchStatus(this.iv_switch_privacy, true);
                    break;
                }
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "SettingFragment onDestroyView");
        super.onDestroyView();
        destroyWebView();
        Handler handler = this.mVolumeMiss;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mVolumeMiss = null;
        }
        this.wakeupFreeFragment = null;
        this.userImproveFragment = null;
        EventBus.getDefault().unregister(this);
        if (this.carVolumeCallback != null) {
            Common.get().unregisterCarVolumeCallback(this.carVolumeCallback);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.mLoadingRunnable);
        RLog.d("NEU_SettingFragment AppVersion=S.8.12.2", "SettingFragment onDestroy");
    }

    private void continuousClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "continuousClick: 连续点击");
        if (jElapsedRealtime - this.time < 2000) {
            this.count++;
        } else {
            this.count = 0;
        }
        this.time = jElapsedRealtime;
        if (this.count >= 5) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "continuousClick: 连续点击，开启暗门");
            createBlankdoorDialog();
        }
    }

    private void createBlankdoorDialog() {
        List arrayList;
        if (this.isOpen.booleanValue()) {
            this.blankdoorDialog.show();
            return;
        }
        this.blankdoorView = LayoutInflater.from(getContext()).inflate(R.layout.layout_privacy_blankdoor, (ViewGroup) null);
        this.blankdoorDialog = new CustomDialog(getContext(), this.blankdoorView);
        this.blankdoorBackView = this.blankdoorView.findViewById(R.id.blankdoorBackView);
        this.blankdoor_BtnX = (ImageView) this.blankdoorView.findViewById(R.id.blankdoor_BtnX);
        this.mRecyclerView = (RecyclerView) this.blankdoorView.findViewById(R.id.mRecyclerView);
        this.privacyLogBtn = (Button) this.blankdoorView.findViewById(R.id.privacyLogBtn);
        this.soundRecordingBtn = (Button) this.blankdoorView.findViewById(R.id.soundRecordingBtn);
        this.sound_begin_btn = (Button) this.blankdoorView.findViewById(R.id.sound_begin_btn);
        this.weatherBtn = (Button) this.blankdoorView.findViewById(R.id.weatherBtn);
        this.weatherSwitch = (Button) this.blankdoorView.findViewById(R.id.weatherSwitch);
        this.privacyLogBtn.setSelected(true);
        this.sound_begin_btn.setSelected(false);
        this.weatherBtn.setSelected(false);
        this.weatherSwitch.setSelected(false);
        this.privacyLogBtn.setTextColor(getContext().getResources().getColor(R.color.black, null));
        this.soundRecordingBtn.setTextColor(getContext().getResources().getColor(R.color.bg_dialog_grey, null));
        this.weatherBtn.setTextColor(getContext().getResources().getColor(R.color.bg_dialog_grey, null));
        this.privacyLogBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.mRecyclerView.setVisibility(0);
                SettingFragment.this.sound_begin_btn.setVisibility(8);
                SettingFragment.this.weatherSwitch.setVisibility(8);
                view.setSelected(true);
                SettingFragment.this.privacyLogBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.black, null));
                SettingFragment.this.weatherBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.soundRecordingBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.soundRecordingBtn.setSelected(false);
                if (SettingFragment.this.isBeginSound.booleanValue()) {
                    SettingFragment.this.beginSoundRecording(false);
                }
                SettingFragment.this.weatherBtn.setSelected(false);
            }
        });
        this.soundRecordingBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.44
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(true);
                SettingFragment.this.privacyLogBtn.setSelected(false);
                SettingFragment.this.weatherBtn.setSelected(false);
                SettingFragment.this.privacyLogBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.weatherBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.soundRecordingBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.black, null));
                SettingFragment.this.mRecyclerView.setVisibility(8);
                SettingFragment.this.weatherSwitch.setVisibility(8);
                SettingFragment.this.sound_begin_btn.setVisibility(0);
            }
        });
        this.weatherBtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.45
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(true);
                SettingFragment.this.privacyLogBtn.setSelected(false);
                SettingFragment.this.privacyLogBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.soundRecordingBtn.setSelected(false);
                SettingFragment.this.soundRecordingBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.bg_dialog_grey, null));
                SettingFragment.this.weatherBtn.setTextColor(SettingFragment.this.getContext().getResources().getColor(R.color.black, null));
                SettingFragment.this.mRecyclerView.setVisibility(8);
                SettingFragment.this.sound_begin_btn.setVisibility(8);
                SettingFragment.this.weatherSwitch.setVisibility(0);
                if (SettingFragment.this.isBeginSound.booleanValue()) {
                    SettingFragment.this.beginSoundRecording(false);
                }
            }
        });
        this.weatherSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.46
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if (view.isSelected()) {
                    Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY__WEOTHER_LOCK, "true");
                } else {
                    Settings.System.putString(SettingFragment.this.contentResolver, Constant.KEY__WEOTHER_LOCK, "false");
                }
            }
        });
        this.sound_begin_btn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.47
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.beginSoundRecording(!view.isSelected());
            }
        });
        this.blankdoor_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.48
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.isOpen = false;
                SettingFragment.this.blankdoorDialog.dismiss();
            }
        });
        try {
            arrayList = Common.getPrivacyPolicyModel();
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "createBlankdoorDialog: models =" + arrayList.size());
        } catch (Exception unused) {
            arrayList = new ArrayList();
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "createBlankdoorDialog: models = 0 errer");
        }
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRecyclerView.setAdapter(new blankdoorAdapter(arrayList));
        this.blankdoorDialog.show();
        this.isOpen = true;
    }

    private String initAllRegister() {
        String string = Settings.System.getString(this.contentResolver, Constant.KEY_REGISTER_SET_RESULT);
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "initAllRegister: register = " + string);
        if (string == null || string.length() == 0) {
            this.driver.setSelected(true);
            this.passenger.setSelected(true);
            this.iv_rear_left.setSelected(true);
            this.iv_rear_right.setSelected(true);
        } else {
            string.hashCode();
            switch (string) {
                case "1":
                    this.driver.setSelected(true);
                    break;
                case "2":
                    this.driver.setSelected(true);
                    this.passenger.setSelected(true);
                    break;
                case "3":
                    this.driver.setSelected(true);
                    this.iv_rear_left.setSelected(true);
                    break;
                case "4":
                    this.driver.setSelected(true);
                    this.iv_rear_right.setSelected(true);
                    break;
                case "5":
                    this.driver.setSelected(true);
                    this.iv_rear_left.setSelected(true);
                    this.iv_rear_right.setSelected(true);
                    break;
                case "6":
                    this.driver.setSelected(true);
                    this.passenger.setSelected(true);
                    this.iv_rear_left.setSelected(true);
                    break;
                case "7":
                    this.driver.setSelected(true);
                    this.passenger.setSelected(true);
                    this.iv_rear_right.setSelected(true);
                    break;
                default:
                    this.driver.setSelected(true);
                    this.passenger.setSelected(true);
                    this.iv_rear_left.setSelected(true);
                    this.iv_rear_right.setSelected(true);
                    break;
            }
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String sendRegisterSet() {
        String str;
        if (this.driver.isSelected() && this.passenger.isSelected() && this.iv_rear_left.isSelected() && this.iv_rear_right.isSelected()) {
            str = "8";
        } else if (this.driver.isSelected() && this.passenger.isSelected() && this.iv_rear_right.isSelected()) {
            str = "7";
        } else if (this.driver.isSelected() && this.passenger.isSelected() && this.iv_rear_left.isSelected()) {
            str = "6";
        } else if (this.driver.isSelected() && this.iv_rear_left.isSelected() && this.iv_rear_right.isSelected()) {
            str = "5";
        } else if (this.driver.isSelected() && this.iv_rear_right.isSelected()) {
            str = "4";
        } else if (this.driver.isSelected() && this.iv_rear_left.isSelected()) {
            str = "3";
        } else if (this.driver.isSelected() && this.passenger.isSelected()) {
            str = "2";
        } else {
            str = this.driver.isSelected() ? "1" : null;
        }
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "sendRegisterSet == " + str);
        return str;
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
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "sendBroadcast: " + z);
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.receiver.AutoRunReceiver"));
        intent.addFlags(32);
        getContext().sendBroadcast(intent, "com.byd.vrassistant.autorun.permission");
    }

    private void setClickPlaySound(View view, final CustomDialog customDialog) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "onClick: 触发按键音");
                if (view2 == SettingFragment.this.v_cerence_privacyBackground) {
                    SettingFragment.this.mViewModel.curPos = 0;
                }
                customDialog.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeupClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "wakeupClick: 连续点击");
        if (jElapsedRealtime - this.time < 2000) {
            this.count++;
        } else {
            this.count = 0;
        }
        this.time = jElapsedRealtime;
        if (this.count >= 10) {
            RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "wakeupClick: 连续点击，开启暗门 查看carMode");
            createBlankdoorTwoDialog();
            this.count = 0;
        }
    }

    private void createBlankdoorTwoDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.blankdoor_twodialog, (ViewGroup) null);
        this.viewDialogCarModel = viewInflate;
        this.tv_car_model = (TextView) viewInflate.findViewById(R.id.tv_car_model);
        this.tv_x = (TextView) this.viewDialogCarModel.findViewById(R.id.tv_x);
        this.carModelDialog = new CustomDialog(getContext(), this.viewDialogCarModel);
        String string = Settings.System.getString(this.contentResolver, "KEY_VOICE_CARMODE");
        String string2 = Settings.System.getString(this.contentResolver, "KEY_REALLY_CARMODE");
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "KEY_VOICE_CARMODE=" + string + "| KEY_REALLY_CARMODE=" + string2);
        this.tv_car_model.setText("KEY_VOICE_CARMODE=" + string + "| KEY_REALLY_CARMODE=" + string2);
        this.carModelDialog.show();
        this.tv_x.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.50
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.carModelDialog.dismiss();
            }
        });
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
        this.tv_cur_language.setText(OtaDataManager.getDPSCurrentLanguage());
        OtaDataManager.updateViewLanguage(this.viewDialogWakePhrases);
        OtaDataManager.updateViewLanguage(this.viewDialogAllRegister);
        OtaDataManager.updateViewLanguage(this.viewDialogRegister);
        OtaDataManager.updateViewLanguage(this.viewDialogVersion);
        OtaDataManager.updateViewLanguage(this.viewCerencePrivacy);
        OtaDataManager.updateViewLanguage(this.viewPrivacyPolicy);
        OtaDataManager.updateViewLanguage(this.viewContinueDialog);
        OtaDataManager.updateViewLanguage(this.viewDialogAudio);
        OtaDataManager.updateViewLanguage(this.viewDialogDisclaimerPrivacy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "showLoading");
        if (this.mLoadingBuilder == null) {
            LoadingDialog.Builder light = new LoadingDialog.Builder(getActivity()).setLight(!ModeUtils.isNightMode(getActivity()));
            this.mLoadingBuilder = light;
            light.create(new OnBackPressInterface() { // from class: com.byd.vrsettings.fragment.SettingFragment.51
                @Override // com.byd.vrsettings.interf.OnBackPressInterface
                public void onBackPress() {
                    EventBus.getDefault().post("onBackPress");
                }
            });
        }
        if (!this.mLoadingBuilder.create().isShowing() && getActivity() != null && !getActivity().isFinishing() && !getActivity().isDestroyed()) {
            this.mLoadingBuilder.create().show();
        }
        this.mHandler.removeCallbacks(this.mLoadingRunnable);
        this.mHandler.postDelayed(this.mLoadingRunnable, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissLoading() {
        RLog.i("NEU_SettingFragment AppVersion=S.8.12.2", "dismissLoading");
        if (this.mLoadingBuilder != null && getActivity() != null && !getActivity().isFinishing() && !getActivity().isDestroyed()) {
            this.mLoadingBuilder.create().dismiss();
        }
        this.mHandler.removeCallbacks(this.mLoadingRunnable);
    }

    private void showFreeWakeupWordsDialog() {
        if (this.mWakeupWordsFreeDialogBuilder == null) {
            this.mWakeupWordsFreeDialogBuilder = new WakeupWordsFreeDialog.Builder(getContext());
        }
        if (this.mWakeupWordsFreeDialogBuilder.create().isShowing()) {
            return;
        }
        this.mWakeupWordsFreeDialogBuilder.setLight(true ^ ModeUtils.isNightMode(getContext())).setContent(String.format(getResources().getString(R.string.wakeup_free_word_update_tip), LanguageUtil.topLanguageConvert(getContext(), OtaDataManager.getCurrentLanguageName()))).setPositiveClickLitener(getResources().getString(R.string.text_update), new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.54
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.startActivity(new Intent(SettingFragment.this.getActivity(), (Class<?>) OtaUpgradeActivity.class));
            }
        }).setNegativeClickLitener(getResources().getString(R.string.text_btn_off), new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.SettingFragment.53
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.mWakeupWordsFreeDialogBuilder.create().show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void voiceZoneUpdate(VoiceZoneUIEvent voiceZoneUIEvent) {
        if (voiceZoneUIEvent.isSucc()) {
            RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "voice zone ui update success");
            return;
        }
        initAllRegister();
        RLog.e("NEU_SettingFragment AppVersion=S.8.12.2", "voice zone ui update failed");
        ToastUtils.show(BydVoiceSettingApplication.getContext(), R.string.operation_fail);
    }
}
