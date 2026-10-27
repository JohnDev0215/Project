package com.byd.vrsettings.fragment;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.eventbus.FinishUserImproveFragmentBean;
import com.byd.vrsettings.eventbus.FinishVersionFragmentBean;
import com.byd.vrsettings.eventbus.HideFragmentBean;
import com.byd.vrsettings.eventbus.ShowFragmentBean;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.view.CustomDialog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class VersionFragment extends BaseFragment {
    private static final String TAG = "NEU_VersionFragment";
    private OnBackPressedCallback callback;
    private View cl_disclaimer;
    private View cl_guide;
    private ConstraintLayout cl_version;
    private ImageView disclaimer_BtnX;
    private MyWebView disclaimer_webView;
    private ImageView iv_smart_voice;
    private ImageView iv_version_back;
    private MyViewModel mViewModel;
    private ConstraintSet set;
    private ScrollView sv_guide;
    private TextView tv;
    private TextView tv3;
    private TextView tv_btn_disclaimer;
    private TextView tv_copyright;
    private TextView tv_settingSDK_version;
    private TextView tv_setting_version;
    private TextView tv_smart_voice;
    private TextView tv_smart_voice_version;
    private TextView tv_text_guide;
    private TextView tv_title_disclaimer;
    private TextView tv_title_guide;
    private TextView tv_version_title;
    private View v_disclaimer;
    private View v_guide;
    private View version_backView;
    private View view;
    private View viewDialogDisclaimer;
    private View viewDialogDisclaimerPrivacy;
    private long time = 0;
    private int count = 0;
    private boolean onconfigchange = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.layout_setting_voice_version, (ViewGroup) null);
        initView();
        this.mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
        this.callback = new OnBackPressedCallback(true) { // from class: com.byd.vrsettings.fragment.VersionFragment.1
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                RLog.d(VersionFragment.TAG, "requireActivity onBackPressed");
                VersionFragment.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d(VersionFragment.TAG, "29 mViewModel.showUserImproveDialogValue==" + VersionFragment.this.mViewModel.showUserImproveDialogValue);
                EventBus.getDefault().post(new FinishUserImproveFragmentBean());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.callback);
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
        this.set = new ConstraintSet();
        this.cl_version = (ConstraintLayout) this.view.findViewById(R.id.cl_version);
        this.tv_version_title = (TextView) this.view.findViewById(R.id.tv_version_title);
        this.tv_smart_voice = (TextView) this.view.findViewById(R.id.tv_smart_voice);
        this.tv_copyright = (TextView) this.view.findViewById(R.id.tv_copyright);
        this.iv_version_back = (ImageView) this.view.findViewById(R.id.iv_version_back);
        this.version_backView = this.view.findViewById(R.id.version_backView);
        this.iv_smart_voice = (ImageView) this.view.findViewById(R.id.iv_smart_voice);
        this.iv_version_back.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.VersionFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VersionFragment.this.tv3.setVisibility(4);
                VersionFragment.this.tv_settingSDK_version.setVisibility(4);
                EventBus.getDefault().post(new FinishVersionFragmentBean());
            }
        });
        TextView textView = (TextView) this.view.findViewById(R.id.tv_btn_disclaimer);
        this.tv_btn_disclaimer = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.VersionFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VersionFragment.this.createDisclaimerPrivacyDialog();
            }
        });
        this.tv_smart_voice_version = (TextView) this.view.findViewById(R.id.tv_smart_voice_version);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tv_setting_version);
        this.tv_setting_version = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.VersionFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VersionFragment.this.continuousClick();
            }
        });
        this.tv = (TextView) this.view.findViewById(R.id.tv);
        this.tv3 = (TextView) this.view.findViewById(R.id.tv3);
        this.tv_settingSDK_version = (TextView) this.view.findViewById(R.id.tv_settingSDK_version3);
        this.tv3.setVisibility(4);
        this.tv_settingSDK_version.setVisibility(4);
        try {
            String str = getContext().getPackageManager().getPackageInfo("com.byd.vrassistant", 0).versionName;
            String str2 = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            String string = Settings.System.getString(getContext().getContentResolver(), "KEY_VOICE_VERSION");
            RLog.i(TAG, "sdkVersionJson = " + string);
            RLog.i(TAG, "assistantVerName = " + str);
            RLog.i(TAG, "settingsVerName = " + str2);
            this.tv_smart_voice_version.setText("V." + str);
            this.tv_setting_version.setText("V." + str2);
            this.tv_settingSDK_version.setText("V." + string);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDisclaimerPrivacyDialog() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_disclaimer_privacy, (ViewGroup) null);
        this.viewDialogDisclaimerPrivacy = viewInflate;
        this.cl_guide = viewInflate.findViewById(R.id.cl_guide);
        final CustomDialog customDialog = new CustomDialog(getContext(), this.viewDialogDisclaimerPrivacy);
        this.v_guide = this.viewDialogDisclaimerPrivacy.findViewById(R.id.v_guide);
        this.sv_guide = (ScrollView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.sv_guide);
        this.tv_title_guide = (TextView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.tv_title_guide);
        this.tv_text_guide = (TextView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.tv_text_guide);
        this.disclaimer_BtnX = (ImageView) this.viewDialogDisclaimerPrivacy.findViewById(R.id.disclaimer_BtnX);
        if (ModeUtils.isNightMode(getContext())) {
            this.v_guide.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog_night, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.voice_setting_text_night, null));
            this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        } else {
            this.v_guide.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.voice_setting_text, null));
            this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
        this.disclaimer_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.VersionFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        String string = getResources().getString(R.string.auth_text);
        String string2 = getResources().getString(R.string.auth_click);
        int iIndexOf = string.indexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        try {
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.byd.vrsettings.fragment.VersionFragment.6
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    VersionFragment.this.createDisclaimerDialog();
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
            RLog.d(TAG, "span 崩溃");
        }
        customDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDisclaimerDialog() {
        MyWebView myWebView;
        if (Common.isFastDoubleClick(10, 1000L)) {
            return;
        }
        this.viewDialogDisclaimer = LayoutInflater.from(getContext()).inflate(R.layout.layout_disclaimer, (ViewGroup) null);
        OtaDataManager.switchAppLanguage(getContext());
        this.cl_disclaimer = this.viewDialogDisclaimer.findViewById(R.id.cl_disclaimer);
        final CustomDialog customDialog = new CustomDialog(getContext(), this.viewDialogDisclaimer);
        this.v_disclaimer = this.viewDialogDisclaimer.findViewById(R.id.v_disclaimer);
        this.tv_title_disclaimer = (TextView) this.viewDialogDisclaimer.findViewById(R.id.tv_title_disclaimer);
        MyWebView myWebView2 = (MyWebView) this.viewDialogDisclaimer.findViewById(R.id.disclaimer_webView);
        this.disclaimer_webView = myWebView2;
        myWebView2.getSettings().setJavaScriptEnabled(true);
        this.disclaimer_webView.setLayerType(1, null);
        if (this.mViewModel.curPos != 0 && (myWebView = this.disclaimer_webView) != null) {
            myWebView.scrollTo(0, this.mViewModel.curPos);
        }
        this.disclaimer_webView.setWebChromeClient(new WebChromeClient() { // from class: com.byd.vrsettings.fragment.VersionFragment.7
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (webView.getProgress() == 100) {
                    Common.jumpToY(VersionFragment.this.disclaimer_webView, VersionFragment.this.mViewModel.curPos);
                }
            }
        });
        this.disclaimer_webView.setOnOveredScroll(new MyWebView.onOveredScroll() { // from class: com.byd.vrsettings.fragment.VersionFragment.8
            @Override // com.byd.vrsettings.utils.MyWebView.onOveredScroll
            public void onOvered(int i) {
                if (VersionFragment.this.onconfigchange) {
                    VersionFragment.this.onconfigchange = false;
                } else {
                    RLog.d(VersionFragment.TAG, "setOnOveredScroll  scrollY==" + i);
                    VersionFragment.this.mViewModel.curPos = i;
                }
            }
        });
        if (ModeUtils.isNightMode(getContext())) {
            this.v_disclaimer.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog_night, null));
            this.tv_title_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.disclaimer_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.disclaimer_webView.setBackgroundColor(getResources().getColor(R.color.bg_dialog_night, null));
            this.disclaimer_webView.loadUrl(Common.getPrivacyPath(true));
        } else {
            this.v_disclaimer.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog, null));
            this.tv_title_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
            this.disclaimer_webView.loadUrl(Common.getPrivacyPath(false));
            this.disclaimer_webView.setBackgroundColor(getResources().getColor(R.color.bg_dialog, null));
            this.disclaimer_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar, null));
        }
        if (getResources().getConfiguration().orientation == 2) {
            this.v_disclaimer.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 1173.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 513.0f, getResources().getDisplayMetrics())));
        } else {
            this.v_disclaimer.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 633.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 1100.0f, getResources().getDisplayMetrics())));
        }
        customDialog.show();
        this.viewDialogDisclaimer.findViewById(R.id.iv_close_disclaimer).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.VersionFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
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
    }

    private void setLandScape() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cl_version.setBackgroundResource(R.mipmap.img_bg_setting_night);
        } else {
            this.cl_version.setBackgroundResource(R.mipmap.img_bg_setting);
        }
        if (this.v_disclaimer != null) {
            this.v_disclaimer.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 1173.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 513.0f, getResources().getDisplayMetrics())));
        }
        this.set.clone(this.cl_version);
        this.set.setMargin(R.id.iv_version_back, 6, 54);
        this.set.applyTo(this.cl_version);
    }

    private void setPortrait() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cl_version.setBackgroundResource(R.mipmap.img_bg_setting_vertical_night);
        } else {
            this.cl_version.setBackgroundResource(R.mipmap.img_bg_setting_vertical);
        }
        if (this.v_disclaimer != null) {
            this.v_disclaimer.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 633.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 1100.0f, getResources().getDisplayMetrics())));
        }
        this.set.clone(this.cl_version);
        this.set.setMargin(R.id.iv_version_back, 6, 30);
        this.set.applyTo(this.cl_version);
    }

    private void setNight() {
        this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
        this.tv_version_title.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
        this.tv_smart_voice.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv_setting_version.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv3.setTextColor(getResources().getColor(R.color.smart_voice_night, null));
        this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip_night, null));
        this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144_night, null));
        if (this.viewDialogDisclaimerPrivacy != null) {
            this.v_guide.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog_night, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.voice_setting_text_night, null));
            this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
        if (this.viewDialogDisclaimer != null) {
            this.v_disclaimer.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog_night, null));
            this.disclaimer_webView.setBackgroundColor(getResources().getColor(R.color.bg_dialog_night, null));
            this.tv_title_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
            this.disclaimer_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.disclaimer_webView.loadUrl(Common.getPrivacyPath(true));
            this.onconfigchange = true;
        }
    }

    private void setLight() {
        this.version_backView.setBackground(getResources().getDrawable(R.mipmap.view_dialog_background, null));
        this.tv_version_title.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
        this.tv_smart_voice.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.tv_smart_voice_version.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.tv_settingSDK_version.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.tv_setting_version.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.tv.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.tv3.setTextColor(getResources().getColor(R.color.smart_voice, null));
        this.iv_smart_voice.setImageDrawable(getResources().getDrawable(R.mipmap.img_light_voice_144, null));
        this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
        if (this.viewDialogDisclaimerPrivacy != null) {
            this.v_guide.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog, null));
            this.tv_title_guide.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
            this.tv_text_guide.setTextColor(getResources().getColor(R.color.voice_setting_text, null));
            this.sv_guide.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
        if (this.viewDialogDisclaimer != null) {
            this.v_disclaimer.setBackground(getResources().getDrawable(R.drawable.bg_setting_dialog, null));
            this.tv_title_disclaimer.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
            this.disclaimer_webView.loadUrl(Common.getPrivacyPath(false));
            this.disclaimer_webView.setBackgroundColor(getResources().getColor(R.color.bg_dialog, null));
            this.disclaimer_webView.setVerticalScrollbarThumbDrawable(getResources().getDrawable(R.drawable.bg_scrollbar, null));
            this.onconfigchange = true;
        }
    }

    private void setVersion(Boolean bool) {
        if (bool.booleanValue()) {
            RLog.i(TAG, "settingEqualsVoice = true");
            this.tv_settingSDK_version.setVisibility(4);
            this.tv3.setVisibility(4);
            this.set.clone(this.cl_version);
            this.set.clear(R.id.tv_setting_version, 6);
            this.set.clear(R.id.tv_setting_version, 7);
            this.set.connect(R.id.tv_setting_version, 6, R.id.tv, 7, 10);
            this.set.connect(R.id.tv, 6, R.id.iv_smart_voice, 6);
            this.set.connect(R.id.tv, 7, R.id.iv_smart_voice, 7);
            this.set.applyTo(this.cl_version);
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void continuousClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.time < 2000) {
            this.count++;
        } else {
            this.count = 0;
        }
        this.time = jElapsedRealtime;
        if (this.count >= 5) {
            RLog.i(TAG, "continuousClick: 连续点击，开启暗门");
            this.tv3.setVisibility(0);
            this.tv_settingSDK_version.setVisibility(0);
        }
    }
}
