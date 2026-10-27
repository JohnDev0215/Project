package com.byd.vrsettings.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class JoinUserImprovePrivacyView {
    private CustomDialog cerencePrivacyPolicyDialog;
    private View cerence_privacy;
    private ImageView cerence_privacy_BtnX;
    private View cerence_privacy_line;
    private MyWebView cerence_privacy_webView;
    private ConstraintLayout cl_cerence_privacy;
    DetachableDialogDismissListener detachableDialogDismissListener;
    private DetachableDialogDismissListener detachableDialogDismissListener_cerencePrivacyPolicyDialog;
    private clickCancel mClickCancel;
    private clickConfirm mClickConfirm;
    private Context mContext;
    private CustomDialog mCustomDialog;
    private View privacy;
    private View privacy_line;
    private ConstraintLayout privacy_policy_layout;
    private TextView tv_btn_cancel_privacy;
    private TextView tv_btn_confirm_privacy;
    private TextView tv_privacy_policy;
    private TextView tv_title_cerence_privacy;
    private TextView tv_title_privacy;
    private View v_cerence_privacyBackground;
    private View v_privacyBackground;
    private View viewCerencePrivacy;
    private View viewPrivacyPolicy;
    private int fromwinow = 0;
    private boolean onconfigchange = false;
    private final String TAG = "NEU_JoinUserImprovePrivacyView";
    private ConstraintSet set = new ConstraintSet();
    private MyViewModel mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);

    public interface clickCancel {
        void clickCancel();
    }

    public interface clickConfirm {
        void clickConfirm();
    }

    public JoinUserImprovePrivacyView(Context context, clickConfirm clickconfirm, clickCancel clickcancel) {
        this.mContext = context;
        this.mClickConfirm = clickconfirm;
        this.mClickCancel = clickcancel;
        initView();
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_join_user_policy, (ViewGroup) null);
        this.viewPrivacyPolicy = viewInflate;
        this.privacy_policy_layout = (ConstraintLayout) viewInflate.findViewById(R.id.privacy_policy_layout);
        this.mCustomDialog = new CustomDialog(this.mContext, this.viewPrivacyPolicy);
        View viewFindViewById = this.viewPrivacyPolicy.findViewById(R.id.v_privacyBackground);
        this.v_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.mCustomDialog);
        this.privacy = this.viewPrivacyPolicy.findViewById(R.id.privacy);
        this.tv_title_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_title_privacy);
        this.tv_privacy_policy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_privacy_policy);
        this.privacy_line = this.viewPrivacyPolicy.findViewById(R.id.privacy_line);
        this.tv_btn_confirm_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_confirm_privacy);
        this.tv_btn_cancel_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_cancel_privacy);
        String string = this.mContext.getResources().getString(R.string.user_privacy_improvement_text);
        String string2 = this.mContext.getResources().getString(R.string.join_improvement_click);
        int iLastIndexOf = string.lastIndexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                view.playSoundEffect(0);
                JoinUserImprovePrivacyView.this.createCerencePrivacyPolicyDialog();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#00ACEB"));
                textPaint.setUnderlineText(true);
            }
        };
        RLog.i("NEU_JoinUserImprovePrivacyView", "spannable.length() = " + spannableStringBuilder.length());
        try {
            if (iLastIndexOf > 0) {
                spannableStringBuilder.setSpan(clickableSpan, iLastIndexOf, string2.length() + iLastIndexOf, 33);
                this.tv_privacy_policy.setText(spannableStringBuilder);
            } else {
                this.tv_privacy_policy.setText(string);
            }
            TextView textView = this.tv_privacy_policy;
            textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
            this.tv_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
            this.tv_title_privacy.setText(R.string.text_join_userimprove_title);
        } catch (Exception unused) {
            RLog.d("NEU_JoinUserImprovePrivacyView", "span 崩溃");
        }
        this.tv_btn_confirm_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Settings.System.putString(JoinUserImprovePrivacyView.this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT, "true");
                RLog.i("NEU_JoinUserImprovePrivacyView", "tv_btn_confirm_privacy onclick  set KEY_USER_IMPROVEMENT is true");
                JoinUserImprovePrivacyView.this.mClickConfirm.clickConfirm();
                JoinUserImprovePrivacyView.this.mCustomDialog.dismiss();
            }
        });
        this.tv_btn_cancel_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_JoinUserImprovePrivacyView", "tv_btn_cancel_privacy onclick ");
                JoinUserImprovePrivacyView.this.mClickCancel.clickCancel();
                JoinUserImprovePrivacyView.this.mCustomDialog.dismiss();
            }
        });
        initViewPrivacyPolicy();
        this.mCustomDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() != 4) {
                    return false;
                }
                RLog.i("NEU_JoinUserImprovePrivacyView", "mCustomDialog KEYCODE_BACK");
                JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_JoinUserImprovePrivacyView", "45 mViewModel.showUserImproveDialogValue==" + JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                JoinUserImprovePrivacyView.this.mCustomDialog.dismiss();
                return true;
            }
        });
    }

    private void initViewPrivacyPolicy() {
        if (ModeUtils.isNightMode(this.mContext)) {
            this.privacy_policy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.tv_btn_confirm_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_cancel_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.privacy_policy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.tv_btn_confirm_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_cancel_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        this.set.clone(this.privacy_policy_layout);
        if (Common.isRightDriver() || Common.isRtl(BydVoiceSettingApplication.getContext())) {
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.tv_btn_confirm_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_confirm_privacy, 6);
            this.set.clear(R.id.tv_btn_confirm_privacy, 7);
            this.set.connect(R.id.tv_btn_confirm_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_confirm_privacy, 6, R.id.tv_btn_cancel_privacy, 7);
        } else {
            this.set.clear(R.id.tv_btn_confirm_privacy, 6);
            this.set.clear(R.id.tv_btn_confirm_privacy, 7);
            this.set.connect(R.id.tv_btn_confirm_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_confirm_privacy, 7, R.id.tv_btn_cancel_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.tv_btn_confirm_privacy, 7);
        }
        this.set.applyTo(this.privacy_policy_layout);
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.5
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_JoinUserImprovePrivacyView", "joinUserView onDismiss 47 mViewModel.showUserImproveDialogValue==" + JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                JoinUserImprovePrivacyView.this.detachableDialogDismissListener.clearOnDetach(JoinUserImprovePrivacyView.this.mCustomDialog);
            }
        });
        this.detachableDialogDismissListener = detachableDialogDismissListenerWrap;
        this.mCustomDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
    }

    public void clearOnDetachJoinView() {
        if (this.detachableDialogDismissListener == null || this.mCustomDialog == null) {
            return;
        }
        RLog.d("NEU_JoinUserImprovePrivacyView", "JoinViewclearOnDetach detachableDialogDismissListener");
        this.detachableDialogDismissListener.clearOnDetach(this.mCustomDialog);
    }

    public void AddJoindialogListen() {
        RLog.d("NEU_JoinUserImprovePrivacyView", "AddJoindialogListen  mCustomDialog");
        CustomDialog customDialog = this.mCustomDialog;
        if (customDialog != null) {
            customDialog.setOnDismissListener(this.detachableDialogDismissListener);
        }
    }

    public void DismissJoinWebViewOnChange() {
        DetachableDialogDismissListener detachableDialogDismissListener = this.detachableDialogDismissListener_cerencePrivacyPolicyDialog;
        if (detachableDialogDismissListener != null) {
            detachableDialogDismissListener.clearOnDetach(this.cerencePrivacyPolicyDialog);
        }
    }

    public void AddJoinwebviewListen() {
        RLog.d("NEU_JoinUserImprovePrivacyView", "AddwebviewListen  cerencePrivacyPolicyDialog");
        CustomDialog customDialog = this.cerencePrivacyPolicyDialog;
        if (customDialog != null) {
            customDialog.setOnDismissListener(this.detachableDialogDismissListener_cerencePrivacyPolicyDialog);
        }
    }

    public void createCerencePrivacyPolicyDialog() {
        MyWebView myWebView;
        if (Common.isFastDoubleClick(11, 1000L)) {
            return;
        }
        if (this.fromwinow == 1) {
            this.mViewModel.showUserImproveDialogValue = 21;
        } else {
            this.mViewModel.showUserImproveDialogValue = 11;
        }
        this.viewCerencePrivacy = LayoutInflater.from(this.mContext).inflate(R.layout.layout_cerence_privacy_pllicy, (ViewGroup) null);
        OtaDataManager.switchAppLanguage(this.mContext);
        this.cl_cerence_privacy = (ConstraintLayout) this.viewCerencePrivacy.findViewById(R.id.cl_cerence_privacy);
        this.cerencePrivacyPolicyDialog = new CustomDialog(this.mContext, this.viewCerencePrivacy);
        View viewFindViewById = this.viewCerencePrivacy.findViewById(R.id.v_cerence_privacyBackground);
        this.v_cerence_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.cerencePrivacyPolicyDialog);
        this.cerence_privacy = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy);
        MyWebView myWebView2 = (MyWebView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_webView);
        this.cerence_privacy_webView = myWebView2;
        myWebView2.setLayerType(1, null);
        if (this.mViewModel.curPos != 0 && (myWebView = this.cerence_privacy_webView) != null) {
            myWebView.scrollTo(0, this.mViewModel.curPos);
        }
        this.cerence_privacy_webView.setWebChromeClient(new WebChromeClient() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.6
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (webView.getProgress() == 100) {
                    Common.jumpToY(JoinUserImprovePrivacyView.this.cerence_privacy_webView, JoinUserImprovePrivacyView.this.mViewModel.curPos);
                }
            }
        });
        this.cerence_privacy_webView.setOnOveredScroll(new MyWebView.onOveredScroll() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.7
            @Override // com.byd.vrsettings.utils.MyWebView.onOveredScroll
            public void onOvered(int i) {
                if (JoinUserImprovePrivacyView.this.onconfigchange) {
                    JoinUserImprovePrivacyView.this.onconfigchange = false;
                } else {
                    RLog.d("NEU_JoinUserImprovePrivacyView", "setOnOveredScroll  scrollY==" + i);
                    JoinUserImprovePrivacyView.this.mViewModel.curPos = i;
                }
            }
        });
        this.cerence_privacy_BtnX = (ImageView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_BtnX);
        this.tv_title_cerence_privacy = (TextView) this.viewCerencePrivacy.findViewById(R.id.tv_title_cerence_privacy);
        this.cerence_privacy_line = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_line);
        this.tv_title_cerence_privacy.setText(this.mContext.getResources().getString(R.string.text_improve_privacy_policy));
        initcreateCerencePrivacyPolicyDialog();
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.8
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                JoinUserImprovePrivacyView.this.destroyWebView();
                JoinUserImprovePrivacyView.this.viewCerencePrivacy = null;
                if (JoinUserImprovePrivacyView.this.fromwinow == 1) {
                    JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 20;
                } else {
                    JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 10;
                }
                JoinUserImprovePrivacyView.this.mViewModel.curPos = 0;
                JoinUserImprovePrivacyView.this.detachableDialogDismissListener_cerencePrivacyPolicyDialog.clearOnDetach(JoinUserImprovePrivacyView.this.cerencePrivacyPolicyDialog);
                JoinUserImprovePrivacyView.this.cerencePrivacyPolicyDialog = null;
            }
        });
        this.detachableDialogDismissListener_cerencePrivacyPolicyDialog = detachableDialogDismissListenerWrap;
        this.cerencePrivacyPolicyDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.cerencePrivacyPolicyDialog.show();
        this.cerence_privacy_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.e("NEU_JoinUserImprovePrivacyView", "customDialog===cerence_privacy_BtnX");
                if (JoinUserImprovePrivacyView.this.fromwinow == 1) {
                    JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 20;
                } else {
                    JoinUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 10;
                }
                JoinUserImprovePrivacyView.this.mViewModel.curPos = 0;
                JoinUserImprovePrivacyView.this.destroyWebView();
                JoinUserImprovePrivacyView.this.cerencePrivacyPolicyDialog.dismiss();
                JoinUserImprovePrivacyView.this.viewCerencePrivacy = null;
                JoinUserImprovePrivacyView.this.cerencePrivacyPolicyDialog = null;
            }
        });
    }

    private void initcreateCerencePrivacyPolicyDialog() {
        if (ModeUtils.isNightMode(this.mContext)) {
            this.cl_cerence_privacy.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(true));
            this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
        } else {
            this.cl_cerence_privacy.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(false));
            this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar, null));
            this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back, null));
        }
        this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 608.0f, this.mContext.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 592.0f, this.mContext.getResources().getDisplayMetrics())));
        this.set.clone(this.cl_cerence_privacy);
        this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
        this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
        this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
        this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
        this.set.applyTo(this.cl_cerence_privacy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyWebView() {
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
            myWebView.postDelayed(new Runnable() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.10
                @Override // java.lang.Runnable
                public void run() {
                    if (JoinUserImprovePrivacyView.this.cerence_privacy_webView != null) {
                        JoinUserImprovePrivacyView.this.cerence_privacy_webView.scrollTo(0, i);
                    }
                }
            }, 300L);
        }
    }

    public void show(int i) {
        this.fromwinow = i;
        if (i == 1) {
            this.mViewModel.showUserImproveDialogValue = 20;
        } else {
            this.mViewModel.showUserImproveDialogValue = 10;
        }
        this.mCustomDialog.show();
        RLog.d("NEU_JoinUserImprovePrivacyView", "show join dialog mViewModel.showUserImproveDialogValue==" + this.mViewModel.showUserImproveDialogValue);
    }

    public boolean isShowing() {
        return this.mCustomDialog.isShowing();
    }

    public void setNight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
        if (this.cerencePrivacyPolicyDialog != null) {
            this.onconfigchange = true;
            RLog.d("NEU_JoinUserImprovePrivacyView", "initDisclaimer start");
            initcreateCerencePrivacyPolicyDialog();
            RLog.d("NEU_JoinUserImprovePrivacyView", "initDisclaimer end");
        }
    }

    public void setLight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
        if (this.cerencePrivacyPolicyDialog != null) {
            this.onconfigchange = true;
            RLog.d("NEU_JoinUserImprovePrivacyView", "initDisclaimer start");
            initcreateCerencePrivacyPolicyDialog();
            RLog.d("NEU_JoinUserImprovePrivacyView", "initDisclaimer end");
        }
    }

    public void dismiss() {
        this.mCustomDialog.dismiss();
    }

    public void setPortrait() {
        if (this.cerence_privacy != null) {
            this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 660.0f, this.mContext.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 560.0f, this.mContext.getResources().getDisplayMetrics())));
        }
    }

    public void setLandScape() {
        if (this.cerence_privacy != null) {
            this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 660.0f, this.mContext.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 560.0f, this.mContext.getResources().getDisplayMetrics())));
        }
    }

    private void setClickPlaySound(View view, final CustomDialog customDialog) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.JoinUserImprovePrivacyView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RLog.i("NEU_JoinUserImprovePrivacyView", "onClick: 触发按键音");
                if (view2 == JoinUserImprovePrivacyView.this.v_cerence_privacyBackground) {
                    JoinUserImprovePrivacyView.this.mViewModel.curPos = 0;
                }
                customDialog.dismiss();
            }
        });
    }
}
