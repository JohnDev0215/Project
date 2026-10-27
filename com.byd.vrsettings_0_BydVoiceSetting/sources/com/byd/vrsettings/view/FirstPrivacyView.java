package com.byd.vrsettings.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Process;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.dialog.DetachableDialogDismissListener;
import com.byd.vrsettings.service.DialogManager;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class FirstPrivacyView {
    private ImageView UserImprovementButton;
    private TextView UserImprovementContext;
    private View bg_privacy;
    private View cerence_privacy;
    private ImageView cerence_privacy_BtnX;
    private View cerence_privacy_line;
    private MyWebView cerence_privacy_webView;
    private ConstraintLayout cl_cerence_privacy;
    DetachableDialogDismissListener detachableDialogDismissListener_customDialog;
    private ConstraintLayout first_privacy_layout;
    private clickDismiss mClickDismiss;
    private Context mContext;
    private CustomDialog mCustomDialog;
    private View privacy_line;
    private TextView tv_btn_cancel_privacy;
    private TextView tv_btn_disable_privacy;
    private TextView tv_privacy_policy;
    private TextView tv_title_cerence_privacy;
    private TextView tv_title_privacy;
    private View v_cerence_privacyBackground;
    private View viewCerencePrivacy;
    private View viewPrivacyPolicy;
    private boolean onconfigchange = false;
    private final String TAG = "NEU_FirstPrivacyView";
    private MyViewModel mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
    private ConstraintSet set = new ConstraintSet();

    public interface clickDismiss {
        void clickDismiss();

        void notAllow();
    }

    public void setLandScape() {
    }

    public void setPortrait() {
    }

    public FirstPrivacyView(Context context, clickDismiss clickdismiss) {
        this.mContext = context;
        this.mClickDismiss = clickdismiss;
        initView();
    }

    private void initView() {
        RLog.i("NEU_FirstPrivacyView", "mViewModel.showDialogValue =" + this.mViewModel.showDialogValue);
        this.viewPrivacyPolicy = LayoutInflater.from(this.mContext).inflate(R.layout.layout_first_privacy, (ViewGroup) null);
        CustomDialog customDialog = new CustomDialog(this.mContext, this.viewPrivacyPolicy);
        this.mCustomDialog = customDialog;
        customDialog.setCanceledOnTouchOutside(false);
        this.first_privacy_layout = (ConstraintLayout) this.viewPrivacyPolicy.findViewById(R.id.cl_guide);
        this.bg_privacy = this.viewPrivacyPolicy.findViewById(R.id.v_guide);
        this.tv_title_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_title_guide);
        this.tv_privacy_policy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_text_guide);
        this.tv_btn_cancel_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_yes);
        this.privacy_line = this.viewPrivacyPolicy.findViewById(R.id.privacy_line);
        this.tv_btn_disable_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_no);
        this.UserImprovementContext = (TextView) this.viewPrivacyPolicy.findViewById(R.id.UserImprovementContext);
        ImageView imageView = (ImageView) this.viewPrivacyPolicy.findViewById(R.id.UserImprovementButton);
        this.UserImprovementButton = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_FristPrivacyView", "UserImprovementButton onclick mViewModel.UserImprovementButtonState is =" + FirstPrivacyView.this.mViewModel.UserImprovementButtonState);
                if (FirstPrivacyView.this.mViewModel.UserImprovementButtonState) {
                    FirstPrivacyView firstPrivacyView = FirstPrivacyView.this;
                    firstPrivacyView.setSwitchStatus(firstPrivacyView.UserImprovementButton, false);
                } else {
                    FirstPrivacyView firstPrivacyView2 = FirstPrivacyView.this;
                    firstPrivacyView2.setSwitchStatus(firstPrivacyView2.UserImprovementButton, true);
                }
            }
        });
        hightText();
        this.tv_btn_cancel_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_FristPrivacyView", "tv_btn_cancel_privacy onclick mViewModel.UserImprovementButtonState is =" + FirstPrivacyView.this.mViewModel.UserImprovementButtonState);
                if (FirstPrivacyView.this.mViewModel.UserImprovementButtonState) {
                    RLog.i("NEU_FristPrivacyView", "UserImprovementButton is checked");
                    Settings.System.putString(FirstPrivacyView.this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT, "true");
                }
                Settings.System.putString(FirstPrivacyView.this.mContext.getContentResolver(), Constant.KEY_PRIVACY_POLICY, "true");
                if (!NetUtils.isNetAvailable(BydVoiceSettingApplication.getContext())) {
                    DialogManager.get().showNetTipDialog(true);
                }
                FirstPrivacyView.this.mClickDismiss.clickDismiss();
                FirstPrivacyView.this.mCustomDialog.dismiss();
            }
        });
        this.tv_btn_disable_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FirstPrivacyView.this.mClickDismiss.notAllow();
                FirstPrivacyView.this.mCustomDialog.dismiss();
            }
        });
        initViewPrivacyPolicy();
        this.mCustomDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                RLog.i("NEU_FirstPrivacyView", "onkey :" + i + "keyEvent :" + keyEvent);
                if (keyEvent.getKeyCode() != 4) {
                    return false;
                }
                RLog.i("NEU_FirstPrivacyView", "kill setting");
                Process.killProcess(Process.myPid());
                return true;
            }
        });
    }

    private void initViewPrivacyPolicy() {
        if (ModeUtils.isNightMode(this.mContext)) {
            this.first_privacy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_disable_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.tv_btn_disable_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.bg_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.UserImprovementContext.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            if (this.mViewModel.UserImprovementButtonState) {
                this.UserImprovementButton.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_on_night, null));
            } else {
                this.UserImprovementButton.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_off_night, null));
            }
        } else {
            this.first_privacy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_disable_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.tv_btn_disable_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.bg_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor, null));
            this.UserImprovementContext.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
            if (this.mViewModel.UserImprovementButtonState) {
                this.UserImprovementButton.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_on_light, null));
            } else {
                this.UserImprovementButton.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_off_light, null));
            }
        }
        this.set.clone(this.first_privacy_layout);
        if (Common.isRightDriver() || Common.isRtl(BydVoiceSettingApplication.getContext())) {
            this.set.clear(R.id.tv_no, 6);
            this.set.clear(R.id.tv_no, 7);
            this.set.connect(R.id.tv_no, 7, R.id.tv_yes, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_no, 6, R.id.v_guide, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_yes, 6);
            this.set.clear(R.id.tv_yes, 7);
            this.set.connect(R.id.tv_yes, 7, R.id.v_guide, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_yes, 6, R.id.tv_no, 7);
        } else {
            this.set.clear(R.id.tv_yes, 6);
            this.set.clear(R.id.tv_yes, 7);
            this.set.connect(R.id.tv_yes, 6, R.id.v_guide, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_yes, 7, R.id.tv_no, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_no, 6);
            this.set.clear(R.id.tv_no, 7);
            this.set.connect(R.id.tv_no, 7, R.id.v_guide, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_no, 6, R.id.tv_yes, 7);
        }
        this.set.applyTo(this.first_privacy_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createCerencePrivacyPolicyDialog(boolean z) {
        if (Common.isFastDoubleClick(11, 1000L)) {
            return;
        }
        this.viewCerencePrivacy = LayoutInflater.from(this.mContext).inflate(R.layout.layout_cerence_privacy_pllicy, (ViewGroup) null);
        OtaDataManager.switchAppLanguage(this.mContext);
        this.cl_cerence_privacy = (ConstraintLayout) this.viewCerencePrivacy.findViewById(R.id.cl_cerence_privacy);
        final CustomDialog customDialog = new CustomDialog(this.mContext, this.viewCerencePrivacy);
        View viewFindViewById = this.viewCerencePrivacy.findViewById(R.id.v_cerence_privacyBackground);
        this.v_cerence_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, customDialog);
        this.cerence_privacy = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy);
        MyWebView myWebView = (MyWebView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_webView);
        this.cerence_privacy_webView = myWebView;
        myWebView.setLayerType(1, null);
        if (this.mViewModel.curPos != 0 && this.cerence_privacy_webView != null) {
            RLog.d("NEU_FirstPrivacyView", "has the scroll recode  mViewModel.curPos==" + this.mViewModel.curPos);
            this.cerence_privacy_webView.scrollTo(0, this.mViewModel.curPos);
        }
        this.cerence_privacy_webView.setWebChromeClient(new WebChromeClient() { // from class: com.byd.vrsettings.view.FirstPrivacyView.5
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (webView.getProgress() == 100) {
                    RLog.d("NEU_FirstPrivacyView", "onProgressChanged  mViewModel.curPos==" + FirstPrivacyView.this.mViewModel.curPos);
                    Common.jumpToY(FirstPrivacyView.this.cerence_privacy_webView, FirstPrivacyView.this.mViewModel.curPos);
                }
            }
        });
        this.cerence_privacy_webView.setOnOveredScroll(new MyWebView.onOveredScroll() { // from class: com.byd.vrsettings.view.FirstPrivacyView.6
            @Override // com.byd.vrsettings.utils.MyWebView.onOveredScroll
            public void onOvered(int i) {
                if (FirstPrivacyView.this.onconfigchange) {
                    FirstPrivacyView.this.onconfigchange = false;
                } else {
                    RLog.d("NEU_FirstPrivacyView", "setOnOveredScroll  scrollY==" + i);
                    FirstPrivacyView.this.mViewModel.curPos = i;
                }
            }
        });
        this.cerence_privacy_BtnX = (ImageView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_BtnX);
        this.tv_title_cerence_privacy = (TextView) this.viewCerencePrivacy.findViewById(R.id.tv_title_cerence_privacy);
        this.cerence_privacy_line = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_line);
        if (z) {
            this.tv_title_cerence_privacy.setText(this.mContext.getResources().getString(R.string.text_disclaimer));
        } else {
            this.tv_title_cerence_privacy.setText(this.mContext.getResources().getString(R.string.text_improve_privacy_policy));
        }
        initDisclaimer(z);
        this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 608.0f, this.mContext.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 592.0f, this.mContext.getResources().getDisplayMetrics())));
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.7
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                FirstPrivacyView.this.destroyWebView();
                FirstPrivacyView.this.viewCerencePrivacy = null;
                FirstPrivacyView.this.mViewModel.showDialogValue = 0;
                FirstPrivacyView.this.mViewModel.curPos = 0;
                FirstPrivacyView.this.detachableDialogDismissListener_customDialog.clearOnDetach(customDialog);
            }
        });
        this.detachableDialogDismissListener_customDialog = detachableDialogDismissListenerWrap;
        customDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        customDialog.show();
        this.set.clone(this.cl_cerence_privacy);
        this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
        this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
        this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
        this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
        this.set.applyTo(this.cl_cerence_privacy);
        this.cerence_privacy_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.e("NEU_FirstPrivacyView", "customDialog===cerence_privacy_BtnX");
                FirstPrivacyView.this.mViewModel.showDialogValue = 0;
                RLog.d("NEU_FirstPrivacyView", "43 mViewModel.showDialogValue==" + FirstPrivacyView.this.mViewModel.showDialogValue);
                FirstPrivacyView.this.mViewModel.curPos = 0;
                FirstPrivacyView.this.destroyWebView();
                customDialog.dismiss();
                FirstPrivacyView.this.viewCerencePrivacy = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyWebView() {
        RLog.d("NEU_FirstPrivacyView", "destroyWebView");
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
            myWebView.postDelayed(new Runnable() { // from class: com.byd.vrsettings.view.FirstPrivacyView.9
                @Override // java.lang.Runnable
                public void run() {
                    if (FirstPrivacyView.this.cerence_privacy_webView != null) {
                        FirstPrivacyView.this.cerence_privacy_webView.scrollTo(0, i);
                    }
                }
            }, 300L);
        }
    }

    private void setClickPlaySound(View view, final CustomDialog customDialog) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.FirstPrivacyView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (view2 == FirstPrivacyView.this.v_cerence_privacyBackground) {
                    FirstPrivacyView.this.mViewModel.curPos = 0;
                    FirstPrivacyView.this.mViewModel.showDialogValue = 0;
                    RLog.d("NEU_FirstPrivacyView", "44 mViewModel.showDialogValue==" + FirstPrivacyView.this.mViewModel.showDialogValue);
                }
                customDialog.dismiss();
            }
        });
    }

    private void initDisclaimer(boolean z) {
        if (ModeUtils.isNightMode(this.mContext)) {
            this.cl_cerence_privacy.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            if (z) {
                this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(true));
            } else {
                this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(true));
            }
            this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
            return;
        }
        this.cl_cerence_privacy.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_day, null));
        this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
        this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
        if (z) {
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(false));
        } else {
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(false));
        }
        this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
        this.cerence_privacy_webView.setBackgroundColor(0);
        this.cerence_privacy_webView.getBackground().setAlpha(0);
        this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar, null));
        this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back, null));
    }

    public void show() {
        this.mCustomDialog.show();
        if (this.mViewModel.showDialogValue == 100) {
            createCerencePrivacyPolicyDialog(this.mViewModel.showCerencePri);
        }
    }

    public void setNight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
        if (this.cerence_privacy_webView != null) {
            this.onconfigchange = true;
            RLog.d("NEU_FirstPrivacyView", "initDisclaimer start");
            initDisclaimer(this.mViewModel.showCerencePri);
            RLog.d("NEU_FirstPrivacyView", "initDisclaimer end");
        }
    }

    public void setLight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
        if (this.cerence_privacy_webView != null) {
            this.onconfigchange = true;
            RLog.d("NEU_FirstPrivacyView", "initDisclaimer start");
            initDisclaimer(this.mViewModel.showCerencePri);
            RLog.d("NEU_FirstPrivacyView", "initDisclaimer end");
        }
    }

    public void dismiss() {
        this.mCustomDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchStatus(ImageView imageView, boolean z) {
        this.mViewModel.UserImprovementButtonState = z;
        imageView.setClickable(true);
        if (z) {
            if (ModeUtils.isNightMode(this.mContext)) {
                imageView.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_on_night, null));
                return;
            } else {
                imageView.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_on_light, null));
                return;
            }
        }
        if (ModeUtils.isNightMode(this.mContext)) {
            imageView.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_off_night, null));
        } else {
            imageView.setBackground(this.mContext.getResources().getDrawable(R.mipmap.user_improve_off_light, null));
        }
    }

    public void updateView(Context context) {
        RLog.i("FirstPrivacyView", "updateView:" + this.mContext.getResources().getConfiguration().getLocales());
        OtaDataManager.updateViewLanguage(this.viewPrivacyPolicy);
        hightText();
        if (this.mViewModel.showCerencePri) {
            this.cerence_privacy_webView.loadUrl(Common.getPrivacyPath(ModeUtils.isNightMode(BydVoiceSettingApplication.getContext())));
        } else {
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(ModeUtils.isNightMode(BydVoiceSettingApplication.getContext())));
        }
    }

    private void hightText() {
        spannablePrivacy(this.mContext.getResources().getString(R.string.auth_text), this.mContext.getResources().getString(R.string.auth_click), this.tv_privacy_policy, true);
        spannablePrivacy(this.mContext.getResources().getString(R.string.user_privacy_improvement_text), this.mContext.getResources().getString(R.string.join_improvement_click), this.UserImprovementContext, false);
    }

    private void spannablePrivacy(String str, String str2, TextView textView, final boolean z) {
        int iLastIndexOf;
        if (z) {
            iLastIndexOf = str.indexOf(str2);
        } else {
            iLastIndexOf = str.lastIndexOf(str2);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        try {
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.byd.vrsettings.view.FirstPrivacyView.11
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    FirstPrivacyView.this.mViewModel.showDialogValue = 100;
                    FirstPrivacyView.this.mViewModel.showCerencePri = z;
                    FirstPrivacyView.this.createCerencePrivacyPolicyDialog(z);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#00ACEB"));
                    textPaint.setUnderlineText(true);
                }
            }, iLastIndexOf, str2.length() + iLastIndexOf, 33);
            textView.setText(spannableStringBuilder);
            textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception unused) {
            RLog.d("NEU_FirstPrivacyView", "span 崩溃");
        }
    }
}
