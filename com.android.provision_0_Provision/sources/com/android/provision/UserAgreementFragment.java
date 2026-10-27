package com.android.provision;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.provision.base.BaseFragment;
import com.android.provision.utils.CountryCodeUtils;
import com.android.provision.utils.FastClickUtil;
import com.android.provision.utils.StyleUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class UserAgreementFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static String TAG = "UserAgreementFragment";
    private CheckBox checkBox;
    private boolean countDownFinished;
    private CountDownTimer countDownTimer;
    private FrameLayout mFrameLayoutView;
    private boolean mShown;
    private int mTimeValue;
    private WebView mWebView;
    private TextView tv_agree_tips;
    private Button tv_privacy_policy_sure;
    private String url;
    private boolean countDownFinishedOnSaved = false;
    private int mTimeValueOnSaved = -1;
    private boolean checked = false;

    static /* synthetic */ int access$110(UserAgreementFragment userAgreementFragment) {
        int i = userAgreementFragment.mTimeValue;
        userAgreementFragment.mTimeValue = i - 1;
        return i;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int i;
        if (BootGuideManager.getInstance().isRightRudder()) {
            i = StyleUtil.isDenza() ? R.layout.denza_activity_user_agreement_right_rudder : R.layout.activity_user_agreement_right_rudder;
        } else {
            i = StyleUtil.isDenza() ? R.layout.denza_activity_user_agreement : R.layout.activity_user_agreement;
        }
        return inflater.inflate(i, container, false);
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("countDownFinishedOnSaved", this.countDownFinished);
        outState.putInt("mTimeValueOnSaved", this.mTimeValue);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BydLogHelper.logW(TAG, "onViewCreated");
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("countDownFinishedOnSaved")) {
                this.countDownFinishedOnSaved = savedInstanceState.getBoolean("countDownFinishedOnSaved", false);
                BydLogHelper.logW(TAG, "onViewCreated countDownFinishedOnSaved:" + this.countDownFinishedOnSaved);
            }
            if (savedInstanceState.containsKey("mTimeValueOnSaved")) {
                this.mTimeValueOnSaved = savedInstanceState.getInt("mTimeValueOnSaved");
                BydLogHelper.logW(TAG, "onViewCreated mTimeValueOnSaved:" + this.mTimeValueOnSaved);
            }
        }
        initWebData();
        destroyWebView();
        initPrivacyPolicyView(view);
    }

    @Override // android.app.Fragment
    public void onResume() {
        FrameLayout frameLayout;
        super.onResume();
        BydLogHelper.logW(TAG, "onResume");
        if (isNightMode() && (frameLayout = this.mFrameLayoutView) != null) {
            frameLayout.setBackgroundColor(getContext().getColor(R.color.white));
        }
        DefaultActivity defaultActivity = (DefaultActivity) getActivity();
        if (defaultActivity != null) {
            boolean zIsReCreate = defaultActivity.isReCreate();
            BydLogHelper.logW(TAG, "isRecreate:" + zIsReCreate);
            if (zIsReCreate) {
                startTimer();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.provision.UserAgreementFragment$1] */
    public void startTimer() {
        int i;
        BydLogHelper.logW(TAG, "startTimer");
        if (this.countDownFinishedOnSaved || (i = this.mTimeValueOnSaved) == 0) {
            BydLogHelper.logW(TAG, "onResume countDownFinishedOnSaved：" + this.countDownFinishedOnSaved + " -- mTimeValueOnSaved:" + this.mTimeValueOnSaved);
            setTextTimeFinish();
            this.countDownFinished = true;
            this.countDownTimer = null;
            return;
        }
        if (this.mShown) {
            return;
        }
        this.mShown = true;
        if (i > 0 && i < 9) {
            this.mTimeValue = i;
        } else {
            this.mTimeValue = 8;
        }
        setTextCountDown();
        this.countDownTimer = new CountDownTimer(this.mTimeValue * 1000, 1000L) { // from class: com.android.provision.UserAgreementFragment.1
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                BydLogHelper.logW(UserAgreementFragment.TAG, "onTick");
                UserAgreementFragment.this.setTextCountDown();
                UserAgreementFragment.access$110(UserAgreementFragment.this);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                BydLogHelper.logW(UserAgreementFragment.TAG, "countDownTimer onFinish");
                BootGuideManager.getInstance().notifyDiLink();
                UserAgreementFragment.this.setTextTimeFinish();
                UserAgreementFragment.this.countDownFinished = true;
                UserAgreementFragment.this.countDownTimer = null;
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextCountDown() {
        BydLogHelper.logW(TAG, "mTimeValue:" + this.mTimeValue);
        Button button = this.tv_privacy_policy_sure;
        if (button != null) {
            button.setText(getString(R.string.agree) + "(" + this.mTimeValue + "s)");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextTimeFinish() {
        Button button = this.tv_privacy_policy_sure;
        if (button != null) {
            button.setText(getString(R.string.agree));
            boolean z = this.checked;
            if (z) {
                this.tv_privacy_policy_sure.setSelected(z);
            }
        }
    }

    private void initPrivacyPolicyView(View view) {
        this.mFrameLayoutView = (FrameLayout) view.findViewById(R.id.fl_content);
        this.tv_privacy_policy_sure = (Button) view.findViewById(R.id.tv_privacy_policy_sure);
        this.tv_agree_tips = (TextView) view.findViewById(R.id.tv_agree_tips);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        this.checkBox = checkBox;
        checkBox.setEnabled(true);
        this.checkBox.setOnCheckedChangeListener(this);
        this.tv_privacy_policy_sure.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.tv_safety_warning_title);
        if (StyleUtil.isDenza()) {
            textView.setText(R.string.user_agreement_denza);
        }
        initWebView();
    }

    private void initWebData() {
        getUserAgreementUrl();
    }

    private void getUserAgreementUrl() {
        boolean zIsDenza = StyleUtil.isDenza();
        BydLogHelper.logW(TAG, "isDenza = " + zIsDenza);
        Locale curLanguage = CountryCodeUtils.getCurLanguage();
        if (curLanguage == null) {
            if (zIsDenza) {
                this.url = "file:///android_asset/user_agreement/user_agreement_en_denza.html";
                return;
            } else {
                this.url = "file:///android_asset/user_agreement/user_agreement_en.html";
                return;
            }
        }
        BydLogHelper.logW(TAG, "locale = " + curLanguage.getLanguage());
        if (zIsDenza) {
            if (Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                this.url = "file:///android_asset/user_agreement/user_agreement_zh_denza.html";
            } else if (Locale.TRADITIONAL_CHINESE.equals(curLanguage) || Locale.forLanguageTag("zh-HK").equals(curLanguage)) {
                this.url = "file:///android_asset/user_agreement/user_agreement_tw_denza.html";
            } else if (Locale.forLanguageTag("ms").equals(curLanguage)) {
                this.url = "file:///android_asset/user_agreement/user_agreement_ms_denza.html";
            } else if (Locale.forLanguageTag("th").equals(curLanguage) || Locale.forLanguageTag("in").equals(curLanguage)) {
                this.url = "file:///android_asset/user_agreement/user_agreement_th_denza.html";
            } else {
                this.url = "file:///android_asset/user_agreement/user_agreement_en_denza.html";
            }
        } else if (Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_zh.html";
        } else if (Locale.TRADITIONAL_CHINESE.equals(curLanguage) || Locale.forLanguageTag("zh-HK").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_zh_tw.html";
        } else if (Locale.forLanguageTag("es").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_es.html";
        } else if (Locale.forLanguageTag("es-LA").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_es_la.html";
        } else if (Locale.forLanguageTag("pt-PT").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_pt.html";
        } else if (Locale.forLanguageTag("pt-BR").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_pt_br.html";
        } else if (Locale.FRENCH.equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_fr.html";
        } else if (Locale.GERMAN.equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_de.html";
        } else if (Locale.ITALIAN.equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_it.html";
        } else if (Locale.forLanguageTag("sv").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_sv.html";
        } else if (Locale.forLanguageTag("nb").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_nb.html";
        } else if (Locale.forLanguageTag("iw").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_iw.html";
        } else if (Locale.forLanguageTag("ru").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_ru.html";
        } else if (Locale.forLanguageTag("uz-UZ").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_uz.html";
        } else if (Locale.forLanguageTag("tr").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_tr.html";
        } else if (Locale.forLanguageTag("ms").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_ms.html";
        } else if (Locale.forLanguageTag("ja").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_ja.html";
        } else if (Locale.forLanguageTag("th").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_th.html";
        } else if (Locale.forLanguageTag("in").equals(curLanguage)) {
            this.url = "file:///android_asset/user_agreement/user_agreement_in.html";
        } else {
            this.url = "file:///android_asset/user_agreement/user_agreement_en.html";
        }
        BydLogHelper.logW(TAG, "url = " + this.url);
    }

    private void initWebView() {
        if (isNightMode()) {
            this.mFrameLayoutView.setBackgroundColor(getContext().getColor(R.color.white));
        }
        WebView webView = new WebView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mWebView = webView;
        this.mFrameLayoutView.addView(webView, -1, layoutParams);
        this.mWebView.setBackgroundColor(0);
        this.mWebView.setBackground(null);
        this.mWebView.setHapticFeedbackEnabled(false);
        this.mWebView.setOverScrollMode(2);
        WebSettings settings = this.mWebView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setJavaScriptEnabled(true);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.setVerticalScrollbarTrackDrawable(getContext().getDrawable(R.drawable.shape_webview_scrollbar_track));
        this.mWebView.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.shape_webview_scrollbar_thumb));
        this.mWebView.setVerticalScrollBarEnabled(true);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setLayerType(2, null);
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.android.provision.UserAgreementFragment.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BydLogHelper.logW(UserAgreementFragment.TAG, "onPageFinished");
                BootGuideManager.getInstance().notifyDiLink();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e(UserAgreementFragment.TAG, "errorDes:" + ((Object) error.getDescription()) + ", errorCode:" + error.getErrorCode());
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.provision.UserAgreementFragment.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.mWebView.loadUrl(this.url);
        BydLogHelper.logW(TAG, "url:" + this.url);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_privacy_policy_sure) {
            return;
        }
        handlerSureClick();
    }

    private void handlerSureClick() {
        BydLogHelper.logW(TAG, "handlerSureClick");
        if (FastClickUtil.isFastClick()) {
            return;
        }
        if (this.checked && this.countDownFinished) {
            DefaultActivity defaultActivity = (DefaultActivity) getActivity();
            defaultActivity.cancelTips();
            BootGuideManager.getInstance().setUserAgreementAgree(true);
            defaultActivity.showPrivacyView();
            return;
        }
        ((DefaultActivity) getActivity()).showTips(getString(R.string.make_check_after_operation), R.dimen.dp_120);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Button button;
        BydLogHelper.logW(TAG, "onCheckedChanged = " + isChecked);
        if (compoundButton.getId() == R.id.checkbox) {
            this.checked = isChecked;
            TextView textView = this.tv_agree_tips;
            if (textView != null) {
                textView.setSelected(isChecked);
            }
            if (!this.countDownFinished || (button = this.tv_privacy_policy_sure) == null) {
                return;
            }
            button.setSelected(this.checked);
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        destroyWebView();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        super.onDestroyView();
    }

    private void destroyWebView() {
        WebView webView = this.mWebView;
        if (webView != null) {
            WebStorage.getInstance().deleteAllData();
            ((ViewGroup) webView.getParent()).removeView(this.mWebView);
            this.mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            this.mWebView.clearCache(true);
            this.mWebView.clearHistory();
            this.mWebView.destroy();
            this.mWebView = null;
        }
        FrameLayout frameLayout = this.mFrameLayoutView;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            this.mFrameLayoutView = null;
        }
    }
}
