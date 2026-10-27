package com.visioncritical.touchpointkit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.destination.parser.ParserUtil;
import com.visioncritical.touchpointkit.R$id;
import com.visioncritical.touchpointkit.R$layout;
import com.visioncritical.touchpointkit.commons.TouchPointCommon;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointWebViewActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/visioncritical/touchpointkit/ui/TouchPointWebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "alwaysShow", "", "distUrl", "", "isVisitorEventSent", ParserUtil.TAG_QUERY_PARAM_NAME, "webView", "Landroid/webkit/WebView;", "handleCloseButtonEvent", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setVisitor", "MyWebViewClient", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointWebViewActivity extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache;
    private boolean alwaysShow;
    private String distUrl;
    private boolean isVisitorEventSent;
    private final String tag;
    private WebView webView;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleCloseButtonEvent$lambda-2, reason: not valid java name */
    public static final void m2792handleCloseButtonEvent$lambda2(String str) {
    }

    public TouchPointWebViewActivity() {
        super(R$layout.touchpoint_webview);
        this._$_findViewCache = new LinkedHashMap();
        this.tag = "TouchPointWebViewActivity";
        this.distUrl = "";
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String stringExtra = getIntent().getStringExtra("distUrl");
        this.alwaysShow = getIntent().getBooleanExtra("alwaysShow", false);
        if (stringExtra != null) {
            this.distUrl = stringExtra;
        }
        View viewFindViewById = findViewById(R$id.ll_webView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.ll_webView)");
        FrameLayout frameLayout = (FrameLayout) viewFindViewById;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        String parameterisedActivityUrlString = new TouchPointCommon().getParameterisedActivityUrlString(stringExtra, this.alwaysShow);
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        Pair<WebView, Boolean> pairWebView = companion.getShared().webView(parameterisedActivityUrlString);
        WebView webViewComponent1 = pairWebView.component1();
        boolean zBooleanValue = pairWebView.component2().booleanValue();
        if (webViewComponent1.getParent() != null) {
            ViewParent parent = webViewComponent1.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ((ViewGroup) parent).removeView(webViewComponent1);
        }
        this.webView = webViewComponent1;
        frameLayout.removeView(webViewComponent1);
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.setLayoutParams(layoutParams);
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView3 = null;
        }
        webView3.setWebViewClient(new MyWebViewClient(this));
        WebView webView4 = this.webView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView4 = null;
        }
        frameLayout.addView(webView4);
        if (companion.getShared().getDisableCaching() || !zBooleanValue) {
            WebView webView5 = this.webView;
            if (webView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView2 = webView5;
            }
            webView2.loadUrl(parameterisedActivityUrlString);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.visioncritical.touchpointkit.ui.TouchPointWebViewActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TouchPointWebViewActivity.m2793onCreate$lambda0(this.f$0);
            }
        }, 4000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m2793onCreate$lambda0(TouchPointWebViewActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setVisitor();
    }

    private final void setVisitor() {
        if (TouchPointActivity.INSTANCE.getShared().getIsNetworkAvailable()) {
            String visitorTrackerScript = new TouchPointCommon().getVisitorTrackerScript(this.distUrl);
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript(visitorTrackerScript, new ValueCallback() { // from class: com.visioncritical.touchpointkit.ui.TouchPointWebViewActivity$$ExternalSyntheticLambda2
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    TouchPointWebViewActivity.m2794setVisitor$lambda1(this.f$0, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setVisitor$lambda-1, reason: not valid java name */
    public static final void m2794setVisitor$lambda1(TouchPointWebViewActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isVisitorEventSent = true;
    }

    /* JADX INFO: compiled from: TouchPointWebViewActivity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/visioncritical/touchpointkit/ui/TouchPointWebViewActivity$MyWebViewClient;", "Landroid/webkit/WebViewClient;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "onReceivedError", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MyWebViewClient extends WebViewClient {
        private final Activity activity;

        public MyWebViewClient(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activity = activity;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(request, "request");
            Intrinsics.checkNotNullParameter(error, "error");
            Toast.makeText(this.activity, Intrinsics.stringPlus("Got Error! ", error), 0).show();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        handleCloseButtonEvent();
        super.onBackPressed();
    }

    private final void handleCloseButtonEvent() {
        if (!this.isVisitorEventSent) {
            setVisitor();
        }
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        if (companion.getShared().getIsNetworkAvailable()) {
            String activityCollapsedScript = new TouchPointCommon().getActivityCollapsedScript(this.distUrl);
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.evaluateJavascript(activityCollapsedScript, new ValueCallback() { // from class: com.visioncritical.touchpointkit.ui.TouchPointWebViewActivity$$ExternalSyntheticLambda0
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    TouchPointWebViewActivity.m2792handleCloseButtonEvent$lambda2((String) obj);
                }
            });
            companion.getShared().setActivitySeen(this.distUrl);
        }
    }
}
