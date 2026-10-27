package com.neusoft.na.byd.view;

import android.app.UiStyleConfig;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.bydux.api.common.tools.util.ListUtils;
import com.neusoft.na.byd.R;
import com.neusoft.na.byd.util.ChangeHistoryUtil;
import com.neusoft.na.common.BasicPrivacyView;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.util.SystemUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyPolicyView extends BasicPrivacyView {
    private static final String TAG = "PrivacyView";
    private boolean isRunning;
    private ChangeHistoryUtil mChangeHistoryUtil;
    private String mPrivacyUrl;
    private float offset;
    private int offsetX;
    private int oldHeight;
    private ScrollView sl_content;
    private WebView web_content;

    public PrivacyPolicyView(Context context) {
        this(context, null);
    }

    public PrivacyPolicyView(Context context, AttributeSet attributeSet) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        super(context, attributeSet);
        this.mPrivacyUrl = "";
        LayoutInflater.from(context).inflate(R.layout.privacy_policy_view, this);
        this.mChangeHistoryUtil = new ChangeHistoryUtil(context);
        initView();
        updateView();
        updateChangeButton();
        getContext().registerReceiver(new MyBroadcast(), new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    @Override // android.view.View
    protected void onConfigurationChanged(final Configuration configuration) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "onConfigurationChanged");
        this.isRunning = false;
        this.oldHeight = this.web_content.getHeight();
        this.web_content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int height = PrivacyPolicyView.this.web_content.getHeight();
                if (height != PrivacyPolicyView.this.oldHeight) {
                    PrivacyPolicyView.this.web_content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    float f = PrivacyPolicyView.this.offset;
                    if (configuration.orientation != 1 ? f > 0.932d : f > 0.973d) {
                        f = 1.0f;
                    }
                    int i = (int) (height * f);
                    Log.i(PrivacyPolicyView.TAG, "scrollTo: " + i);
                    PrivacyPolicyView.this.sl_content.scrollTo(PrivacyPolicyView.this.offsetX, i);
                }
            }
        });
        loadUrl();
        updateView();
    }

    private void printWindowInfo() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ll_root_privacy);
        Rect rect = new Rect();
        Point point = new Point();
        if (relativeLayout == null) {
            return;
        }
        Log.i(TAG, "printWindowInfo root_privacy.getGlobalVisibleRect return:" + relativeLayout.getGlobalVisibleRect(rect, point) + ", Rect:" + rect + ", globalOffset:" + point);
        Rect rect2 = new Rect();
        Log.i(TAG, "printWindowInfo root_privacy.getLocalVisibleRect return:" + relativeLayout.getLocalVisibleRect(rect2) + ", Rect:" + rect2);
    }

    private void initView() {
        setViewVisibility(4);
        TextView textView = (TextView) findViewById(R.id.tv_hint);
        boolean zIsNewPrivacy = FileUtils.getInstance(getContext()).isNewPrivacy();
        if (FileUtils.getInstance(getContext()).isAgree() && zIsNewPrivacy) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) findViewById(R.id.tv_exit);
        TextView textView3 = (TextView) findViewById(R.id.tv_setting);
        ImageView imageView = (ImageView) findViewById(R.id.iv_change);
        this.web_content = (WebView) findViewById(R.id.web_content);
        this.web_content.setWebChromeClient(new WebChromeClient() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                Log.i(PrivacyPolicyView.TAG, "onProgressChanged: " + i);
                if (i < 100 || !PrivacyPolicyView.this.isLoadStatus || PrivacyPolicyView.this.mLoadListener == null) {
                    return;
                }
                PrivacyPolicyView.this.isLoadStatus = false;
                PrivacyPolicyView.this.mLoadListener.onLoadDone();
            }
        });
        this.web_content.getSettings().setJavaScriptEnabled(true);
        this.web_content.getSettings().setAllowFileAccess(true);
        ScrollView scrollView = (ScrollView) findViewById(R.id.sl_content);
        this.sl_content = scrollView;
        scrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m155lambda$initView$0$comneusoftnabydviewPrivacyPolicyView(view, motionEvent);
            }
        });
        this.sl_content.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                this.f$0.m156lambda$initView$1$comneusoftnabydviewPrivacyPolicyView(view, i, i2, i3, i4);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m158lambda$initView$3$comneusoftnabydviewPrivacyPolicyView(view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m159lambda$initView$4$comneusoftnabydviewPrivacyPolicyView(view);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m160lambda$initView$5$comneusoftnabydviewPrivacyPolicyView(view);
            }
        });
        loadUrl();
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ boolean m155lambda$initView$0$comneusoftnabydviewPrivacyPolicyView(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Log.i(TAG, "onTouch: ACTION_UP");
            this.isRunning = true;
            setViewVisibility(0);
        }
        return false;
    }

    /* JADX INFO: renamed from: lambda$initView$1$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ void m156lambda$initView$1$comneusoftnabydviewPrivacyPolicyView(View view, int i, int i2, int i3, int i4) {
        if (this.isRunning) {
            this.offset = (i2 * 1.0f) / this.web_content.getHeight();
            this.offsetX = i;
        }
    }

    /* JADX INFO: renamed from: lambda$initView$3$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ void m158lambda$initView$3$comneusoftnabydviewPrivacyPolicyView(View view) {
        this.mChangeHistoryUtil.saveChangeHistory(true, new ChangeHistoryUtil.IChangeHistoryListener() { // from class: com.neusoft.na.byd.view.PrivacyPolicyView$$ExternalSyntheticLambda5
            @Override // com.neusoft.na.byd.util.ChangeHistoryUtil.IChangeHistoryListener
            public final void onDone(boolean z) {
                this.f$0.m157lambda$initView$2$comneusoftnabydviewPrivacyPolicyView(z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$2$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ void m157lambda$initView$2$comneusoftnabydviewPrivacyPolicyView(boolean z) {
        if (this.mListener != null) {
            this.mListener.onClickAgree();
        }
    }

    /* JADX INFO: renamed from: lambda$initView$4$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ void m159lambda$initView$4$comneusoftnabydviewPrivacyPolicyView(View view) {
        if (this.mListener != null) {
            this.mListener.onClickCancel();
        }
    }

    /* JADX INFO: renamed from: lambda$initView$5$com-neusoft-na-byd-view-PrivacyPolicyView, reason: not valid java name */
    /* synthetic */ void m160lambda$initView$5$comneusoftnabydviewPrivacyPolicyView(View view) {
        if (this.mListener != null) {
            this.mListener.onClickChange();
        }
    }

    private void setViewVisibility(int i) {
        findViewById(R.id.ll_bottom).setVisibility(i);
    }

    private void loadUrl() {
        WebView webView = (WebView) findViewById(R.id.web_content);
        String privacyUrl = this.mChangeHistoryUtil.getPrivacyUrl();
        boolean zEquals = this.mPrivacyUrl.equals(privacyUrl);
        Log.i(TAG, "loadUrl: " + zEquals + ListUtils.DEFAULT_JOIN_SEPARATOR + privacyUrl);
        if (zEquals) {
            return;
        }
        this.mPrivacyUrl = privacyUrl;
        webView.loadUrl(privacyUrl);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(i, i2, i3, i4);
        }
    }

    @Override // com.neusoft.na.common.BasicPrivacyView
    public void setInMultiWindowMode(boolean z) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        super.setInMultiWindowMode(z);
        Log.i(TAG, "setInMultiWindowMode");
        loadUrl();
        updateView();
    }

    private void updateView() throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        float dimension;
        float dimension2;
        printWindowInfo();
        Log.i(TAG, "updateView: style ui " + UiStyleConfig.getInstance(getContext()).getUiStyle());
        int systemPropertiesIntValue = SystemUtils.INSTANCE.getSystemPropertiesIntValue(getContext(), "persist.sys.AutoType");
        boolean z = 123 == systemPropertiesIntValue || 60 == systemPropertiesIntValue;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ll_root_privacy);
        if (z) {
            relativeLayout.setBackgroundColor(getColor(R.color.privacy_policy_bg_start_denza));
        } else {
            relativeLayout.setBackgroundColor(getColor(R.color.privacy_policy_bg_start));
        }
        TextView textView = (TextView) findViewById(R.id.tv_exit);
        textView.setTextColor(getColor(R.color.privacy_policy_text));
        textView.setText(getResources().getString(R.string.privacy_policy_deny));
        textView.setBackgroundColor(getColor(R.color.privacy_policy_deny));
        textView.setBackgroundResource(z ? R.drawable.privacy_policy_deny_denza_bg : R.drawable.privacy_policy_deny_bg);
        TextView textView2 = (TextView) findViewById(R.id.tv_setting);
        textView2.setText(getResources().getString(R.string.privacy_policy_agree));
        textView2.setTextColor(getColor(R.color.privacy_policy_allow));
        textView2.setBackgroundColor(getColor(R.color.privacy_policy_allow_bg_start));
        textView2.setBackgroundColor(getColor(R.color.privacy_policy_allow_bg_end));
        textView2.setBackgroundResource(z ? R.drawable.privacy_policy_allow_denza_bg : R.drawable.privacy_policy_allow_bg);
        TextView textView3 = (TextView) findViewById(R.id.tv_title);
        textView3.setText(getResources().getString(R.string.privacy_policy));
        textView3.setTextColor(getColor(R.color.privacy_policy_text));
        TextView textView4 = (TextView) findViewById(R.id.tv_hint);
        textView4.setText(getResources().getString(R.string.privacy_policy_hint));
        if (z) {
            textView4.setGravity(GravityCompat.START);
            textView4.setTextColor(getColor(R.color.privacy_policy_hint_denza));
        } else {
            textView4.setGravity(17);
            textView4.setTextColor(getColor(R.color.privacy_policy_hint));
        }
        ((WebView) findViewById(R.id.web_content)).setBackgroundColor(getColor(android.R.color.transparent));
        Log.i(TAG, "updateView: " + this.mIsInMultiWindowMode);
        ScrollView scrollView = (ScrollView) findViewById(R.id.sl_content);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_content);
        if (this.mIsInMultiWindowMode) {
            dimension = getResources().getDimension(R.dimen.size16);
            dimension2 = dimension;
        } else {
            dimension = getResources().getDimension(R.dimen.privacy_padding);
            dimension2 = getResources().getDimension(R.dimen.privacy_padding_end);
        }
        int i = (int) dimension;
        int i2 = (int) dimension2;
        scrollView.setPaddingRelative(i, 0, i2, 0);
        linearLayout.setPaddingRelative(i, 0, i2, 0);
        scrollView.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.color.privacy_policy_text));
        ImageView imageView = (ImageView) findViewById(R.id.iv_change);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.bottomMargin = this.mIsInMultiWindowMode ? 24 : (int) getResources().getDimension(R.dimen.privacy_change);
        if (isRightRudderMode()) {
            layoutParams.addRule(11);
            layoutParams.removeRule(9);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 29;
        } else {
            layoutParams.addRule(9);
            layoutParams.removeRule(11);
            layoutParams.leftMargin = 29;
            layoutParams.rightMargin = 0;
        }
        imageView.setLayoutParams(layoutParams);
        if (z) {
            imageView.setImageResource(R.drawable.icon_theme_change_denza);
        } else {
            imageView.setImageResource(R.drawable.icon_theme_change);
        }
    }

    private boolean isRightRudderMode() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int iIntValue = (objInvoke == null || !(objInvoke instanceof Integer)) ? 0 : ((Integer) objInvoke).intValue();
        boolean z = iIntValue == 1;
        Log.i(TAG, "isRightRudderMode == " + z);
        return z;
    }

    private int getColor(int i) {
        return ContextCompat.getColor(getContext(), i);
    }

    private class MyBroadcast extends BroadcastReceiver {
        private MyBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getStringExtra("reason"), "homekey")) {
                Log.i(PrivacyPolicyView.TAG, "home key down");
                PrivacyPolicyView.this.updateChangeButton();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChangeButton() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity != null) {
            Log.i(TAG, "resolveInfo.activityInfo.packageName == ${resolveInfo.activityInfo.packageName}");
            if (getContext().getPackageName().equals(resolveInfoResolveActivity.activityInfo.packageName)) {
                findViewById(R.id.iv_change).setVisibility(0);
                Log.i(TAG, "byd8155F is launcher and top, send HMI to home page.");
            } else {
                findViewById(R.id.iv_change).setVisibility(8);
                Log.i(TAG, "byd8155F is not launcher, do not send HMI to home page.");
            }
        }
    }
}
