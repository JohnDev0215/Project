package com.android.provision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemProperties;
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
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.provision.base.BaseDialog;
import com.android.provision.base.BaseFragment;
import com.android.provision.utils.CountryCodeUtils;
import com.android.provision.utils.FastClickUtil;
import com.android.provision.utils.StyleUtil;
import com.android.provision.widget.ToneGeneratorButton;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyPolicyFragment extends BaseFragment implements View.OnClickListener {
    public static String TAG = "PrivacyPolicyFragment";
    private boolean countDownFinished;
    private CountDownTimer countDownTimer;
    private boolean isAvmConnected;
    private AvmManager mAvmManager;
    private FrameLayout mFrameLayoutView;
    private PolicyTipsDialog mPolicyTipsDialog;
    private boolean mShown;
    private int mTimeValue;
    private WebView mWebView;
    private Button tv_privacy_policy_skip;
    private Button tv_privacy_policy_sure;
    private String url;
    private boolean countDownFinishedOnSaved = false;
    private int mTimeValueOnSaved = -1;
    private final IAvmServiceConnectionListener mConnectionListener = new IAvmServiceConnectionListener() { // from class: com.android.provision.PrivacyPolicyFragment.1
        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onError(int i, String s) {
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceConnected() {
            BydLogHelper.logW(PrivacyPolicyFragment.TAG, "onServiceConnected");
            PrivacyPolicyFragment.this.isAvmConnected = true;
            try {
                int i = SystemProperties.getInt(BootGuideManager.PRIVACY_POLICY_AGREE, 0);
                if (PrivacyPolicyFragment.this.mAvmManager != null) {
                    PrivacyPolicyFragment.this.mAvmManager.setProvisionFinished(i);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
        public void onServiceDisConnected() {
            BydLogHelper.logW(PrivacyPolicyFragment.TAG, "onServiceDisConnected");
        }
    };

    static /* synthetic */ int access$310(PrivacyPolicyFragment privacyPolicyFragment) {
        int i = privacyPolicyFragment.mTimeValue;
        privacyPolicyFragment.mTimeValue = i - 1;
        return i;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BydLogHelper.logW(TAG, "onCreate");
        initAvmSDK();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int i;
        if (BootGuideManager.getInstance().isRightRudder()) {
            i = StyleUtil.isDenza() ? R.layout.denza_activity_privacy_policy_right_rudder : R.layout.activity_privacy_policy_right_rudder;
        } else {
            i = StyleUtil.isDenza() ? R.layout.denza_activity_privacy_policy : R.layout.activity_privacy_policy;
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

    private void initAvmSDK() {
        BydLogHelper.logW(TAG, "initAvmSDK");
        try {
            AvmManager avmManager = AvmManager.getInstance();
            this.mAvmManager = avmManager;
            avmManager.init(getContext(), this.mConnectionListener);
        } catch (Throwable th) {
            BydLogHelper.logE("initAvmSDK error");
            th.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.provision.PrivacyPolicyFragment$2] */
    @Override // android.app.Fragment
    public void onResume() {
        int i;
        FrameLayout frameLayout;
        super.onResume();
        BydLogHelper.logW(TAG, "onResume");
        if (isNightMode() && (frameLayout = this.mFrameLayoutView) != null) {
            frameLayout.setBackgroundColor(getContext().getColor(R.color.white));
        }
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
        this.countDownTimer = new CountDownTimer(this.mTimeValue * 1000, 1000L) { // from class: com.android.provision.PrivacyPolicyFragment.2
            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                BydLogHelper.logW(PrivacyPolicyFragment.TAG, "onTick");
                PrivacyPolicyFragment.this.setTextCountDown();
                PrivacyPolicyFragment.access$310(PrivacyPolicyFragment.this);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                BydLogHelper.logW(PrivacyPolicyFragment.TAG, "countDownTimer onFinish");
                PrivacyPolicyFragment.this.setTextTimeFinish();
                PrivacyPolicyFragment.this.countDownFinished = true;
                PrivacyPolicyFragment.this.countDownTimer = null;
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextCountDown() {
        BydLogHelper.logW(TAG, "mTimeValue:" + this.mTimeValue);
        Button button = this.tv_privacy_policy_sure;
        if (button != null) {
            button.setText(getString(R.string.confirm) + "(" + this.mTimeValue + "s)");
            this.tv_privacy_policy_sure.setAlpha(0.6f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextTimeFinish() {
        Button button = this.tv_privacy_policy_sure;
        if (button != null) {
            button.setText(getString(R.string.confirm));
            this.tv_privacy_policy_sure.setAlpha(1.0f);
        }
    }

    private void initPrivacyPolicyView(View view) {
        this.mFrameLayoutView = (FrameLayout) view.findViewById(R.id.fl_content);
        this.tv_privacy_policy_sure = (Button) view.findViewById(R.id.tv_privacy_policy_sure);
        TextView textView = (TextView) view.findViewById(R.id.tv_safety_warning_title);
        if (StyleUtil.isDenza()) {
            textView.setText(R.string.privacy_policy_denza);
        }
        this.tv_privacy_policy_sure.setOnClickListener(this);
        initWebView();
    }

    private void initWebData() {
        this.url = CountryCodeUtils.getPrivacyH5();
        Log.w(TAG, "initWebData url = " + this.url);
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
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.android.provision.PrivacyPolicyFragment.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                BydLogHelper.logE(PrivacyPolicyFragment.TAG, "errorDes:" + ((Object) error.getDescription()) + ", errorCode:" + error.getErrorCode());
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.provision.PrivacyPolicyFragment.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        BydLogHelper.logW(TAG, "url:" + this.url);
        this.mWebView.loadUrl(this.url);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_privacy_policy_skip /* 2131231090 */:
                handlerSureClick(false);
                break;
            case R.id.tv_privacy_policy_sure /* 2131231091 */:
                handlerSureClick(true);
                break;
        }
    }

    private void handlerSureClick(boolean agree) {
        BydLogHelper.logD();
        if (this.isAvmConnected && this.mAvmManager != null) {
            try {
                BydLogHelper.logD("setProvisionFinished");
                this.mAvmManager.setProvisionFinished(agree ? 1 : 3);
            } catch (Throwable th) {
                BydLogHelper.logD("setProvisionFinished error");
                th.printStackTrace();
            }
        }
        if (FastClickUtil.isFastClick()) {
            return;
        }
        DefaultActivity defaultActivity = (DefaultActivity) getActivity();
        if (this.countDownFinished) {
            defaultActivity.cancelTips();
            if (agree) {
                nextStep(true);
                return;
            }
            if (this.mPolicyTipsDialog == null) {
                this.mPolicyTipsDialog = new PolicyTipsDialog(getActivity());
            }
            if (this.mPolicyTipsDialog.isShowing()) {
                return;
            }
            this.mPolicyTipsDialog.show();
            return;
        }
        defaultActivity.showTips(getString(R.string.make_read_after_operation), R.dimen.dp_120);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextStep(boolean agree) {
        BootGuideManager.getInstance().setPrivacyPolicyAgree(getContext(), agree, System.currentTimeMillis());
        BootGuideManager.getInstance().setPrivacyPolicyFinished();
        DefaultActivity defaultActivity = (DefaultActivity) getActivity();
        defaultActivity.resetPrivacyView();
        BootGuideManager.getInstance();
        if (!BootGuideManager.isSafetyWarnFinished()) {
            defaultActivity.showWarnView();
        } else {
            BootGuideManager.getInstance().finishSetUpWizard(getActivity());
            getActivity().finish();
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        AvmManager avmManager = this.mAvmManager;
        if (avmManager != null) {
            avmManager.deinit(this.mConnectionListener);
        }
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

    private class PolicyTipsDialog extends BaseDialog {
        private ToneGeneratorButton tv_cancel_policy;
        private ToneGeneratorButton tv_sure_policy;

        public PolicyTipsDialog(Context context) {
            super(context);
        }

        @Override // com.android.provision.base.BaseDialog
        protected int getLayoutId() {
            return BootGuideManager.getInstance().isRightRudder() ? StyleUtil.isDenza() ? R.layout.denza_dialog_policy_tips_right_rudder : R.layout.dialog_policy_tips_right_rudder : StyleUtil.isDenza() ? R.layout.denza_dialog_policy_tips : R.layout.dialog_policy_tips;
        }

        @Override // com.android.provision.base.BaseDialog
        protected void initData() {
            this.mView.findViewById(R.id.view_root).setOnClickListener(new View.OnClickListener() { // from class: com.android.provision.PrivacyPolicyFragment.PolicyTipsDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    PolicyTipsDialog.this.dismiss();
                }
            });
            this.tv_sure_policy = (ToneGeneratorButton) this.mView.findViewById(R.id.tv_sure_policy);
            this.tv_cancel_policy = (ToneGeneratorButton) this.mView.findViewById(R.id.tv_cancel_policy);
            this.tv_sure_policy.setOnClickListener(new View.OnClickListener() { // from class: com.android.provision.PrivacyPolicyFragment.PolicyTipsDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    PolicyTipsDialog.this.dismiss();
                    PrivacyPolicyFragment.this.nextStep(false);
                }
            });
            this.tv_cancel_policy.setOnClickListener(new View.OnClickListener() { // from class: com.android.provision.PrivacyPolicyFragment.PolicyTipsDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    PolicyTipsDialog.this.dismiss();
                }
            });
        }
    }
}
