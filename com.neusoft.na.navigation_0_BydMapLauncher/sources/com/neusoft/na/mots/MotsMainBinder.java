package com.neusoft.na.mots;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import com.neusoft.na.navigation.IMotsServiceCallBack;
import com.neusoft.na.navigation.IMotsServiceInterface;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.MainCallBack;
import com.neusoft.na.ocservice.OneCoreLifeCycleController;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.nts.common.IGuardInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.xwalk.core.XWalkInitializer;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: MotsMainBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b'*\u0001(\u0018\u0000 X2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001XB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010.\u001a\u00020/H\u0002J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002J\b\u00103\u001a\u00020/H\u0002J\b\u00104\u001a\u00020\u0012H\u0016J\b\u00105\u001a\u000202H\u0002J\b\u00106\u001a\u00020\u0012H\u0016J\b\u00107\u001a\u00020\u0012H\u0016J\b\u00108\u001a\u00020/H\u0016J\b\u00109\u001a\u00020/H\u0016J\b\u0010:\u001a\u00020/H\u0016J\u0010\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020\u0012H\u0016J\u0006\u0010=\u001a\u00020/J\u0010\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u0012H\u0016J\b\u0010@\u001a\u00020/H\u0016J\b\u0010A\u001a\u00020/H\u0016J\b\u0010B\u001a\u00020/H\u0016J\b\u0010C\u001a\u00020/H\u0016J\b\u0010D\u001a\u00020/H\u0016J\b\u0010E\u001a\u00020/H\u0003J\u0012\u0010F\u001a\u00020/2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010G\u001a\u00020/2\b\u0010H\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020\u001eH\u0016J\u0012\u0010K\u001a\u00020/2\b\u0010H\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010L\u001a\u00020/H\u0002J\u0012\u0010M\u001a\u00020/2\b\u0010H\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010N\u001a\u00020/H\u0002J\b\u0010O\u001a\u00020/H\u0002J\u0006\u0010P\u001a\u00020/J\b\u0010Q\u001a\u00020/H\u0002J\b\u0010R\u001a\u00020/H\u0016J\b\u0010S\u001a\u00020/H\u0002J\b\u0010T\u001a\u00020/H\u0016J\u0010\u0010U\u001a\u00020/2\u0006\u0010V\u001a\u000202H\u0016J\b\u0010W\u001a\u00020/H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)R\u000e\u0010*\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lcom/neusoft/na/mots/MotsMainBinder;", "Lcom/neusoft/na/navigation/IMotsServiceInterface$Stub;", "Lcom/neusoft/na/ocservice/MainCallBack;", "Lcom/neusoft/na/mots/UsbStatusCallBack;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activeStatus", "Lcom/neusoft/na/mots/MotsMainBinder$Companion$ActivityStatus;", "binder", "Lcom/neusoft/nts/common/IGuardInterface;", "callback", "Lcom/neusoft/na/navigation/IMotsServiceCallBack;", "clusterSurface", "Landroid/view/Surface;", "getContext", "()Landroid/content/Context;", "dayNightMode", "", "intentFilter", "Landroid/content/IntentFilter;", "mMotsBroadcastReceiver", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver;", "mMotsLifeCycleController", "Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;", "getMMotsLifeCycleController", "()Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;", "setMMotsLifeCycleController", "(Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;)V", "mMotsServiceStarted", "", "mWebViewMode", "Lcom/neusoft/na/mots/WebViewMode;", "getMWebViewMode", "()Lcom/neusoft/na/mots/WebViewMode;", "mWebViewMode$delegate", "Lkotlin/Lazy;", "mXWalkInitializer", "Lorg/xwalk/core/XWalkInitializer;", "motsJavascriptInstance", "com/neusoft/na/mots/MotsMainBinder$motsJavascriptInstance$1", "Lcom/neusoft/na/mots/MotsMainBinder$motsJavascriptInstance$1;", "motsStatus", "serviceConnection", "Landroid/content/ServiceConnection;", "widgetSurface", "blRunning", "", "checkPermissions", "", "", "checkStartMots", "getDayNightMode", "getHmiUrl", "getMotsStatus", "getWebViewMode", "haspermissions", "maskGone", "notifySurfaceSizeChanged", "onDayNightModeChanged", ServiceBase.PAYLOAD_MODE, "onDestroy", "onKeyCode", "code", "onMainCreate", "onMainDestroy", "onMainPause", "onMainResume", "onPlaySoundEffect", "registerMotsBroadcastReceiver", "setCallBack", "setClusterSurface", OneCoreService.PAYLOAD_SURFACE, "setInMultiWindowMode", OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, "setSurface", "setUsbCallBack", "setWidgetSurface", "setupWebView", "setupXWalkView", "startGuardService", "startMotsService", "timeZoneChanged", "unRegisterMotsBroadcastReceiver", "unSetCallBack", "usbMount", OneCoreService.PAYLOAD_KEY_PATH, "usbUnMount", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsMainBinder extends IMotsServiceInterface.Stub implements MainCallBack, UsbStatusCallBack {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "XJMotsMainBinder";
    private static WebView mWebView;
    private static XWalkView mXWalkView;
    private Companion.ActivityStatus activeStatus;
    private IGuardInterface binder;
    private IMotsServiceCallBack callback;
    private Surface clusterSurface;
    private final Context context;
    private int dayNightMode;
    private IntentFilter intentFilter;
    private MotsBroadcastReceiver mMotsBroadcastReceiver;
    private OneCoreLifeCycleController mMotsLifeCycleController;
    private boolean mMotsServiceStarted;

    /* JADX INFO: renamed from: mWebViewMode$delegate, reason: from kotlin metadata */
    private final Lazy mWebViewMode;
    private XWalkInitializer mXWalkInitializer;
    private final MotsMainBinder$motsJavascriptInstance$1 motsJavascriptInstance;
    private int motsStatus;
    private final ServiceConnection serviceConnection;
    private Surface widgetSurface;

    /* JADX INFO: compiled from: MotsMainBinder.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WebViewMode.values().length];
            try {
                iArr[WebViewMode.PREFER_XWALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WebViewMode.REQUIRE_XWALK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WebViewMode.USE_WEBVIEW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a4  */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.neusoft.na.mots.MotsMainBinder$motsJavascriptInstance$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MotsMainBinder(android.content.Context r8) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.mots.MotsMainBinder.<init>(android.content.Context):void");
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: MotsMainBinder.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u0004\u0018\u00010\u0006J\b\u0010\n\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/neusoft/na/mots/MotsMainBinder$Companion;", "", "()V", "TAG", "", "mWebView", "Landroid/webkit/WebView;", "mXWalkView", "Lorg/xwalk/core/XWalkView;", "getWebView", "getXWalkView", "ActivityStatus", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: MotsMainBinder.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/mots/MotsMainBinder$Companion$ActivityStatus;", "", "(Ljava/lang/String;I)V", "Create", "Resume", "Pause", "Destroy", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public enum ActivityStatus {
            Create,
            Resume,
            Pause,
            Destroy
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebView getWebView() {
            Log.i(MotsMainBinder.TAG, "getWebView");
            return MotsMainBinder.mWebView;
        }

        public final XWalkView getXWalkView() {
            Log.i(MotsMainBinder.TAG, "getXWalkView");
            return MotsMainBinder.mXWalkView;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WebViewMode getMWebViewMode() {
        return (WebViewMode) this.mWebViewMode.getValue();
    }

    public final OneCoreLifeCycleController getMMotsLifeCycleController() {
        return this.mMotsLifeCycleController;
    }

    public final void setMMotsLifeCycleController(OneCoreLifeCycleController oneCoreLifeCycleController) {
        this.mMotsLifeCycleController = oneCoreLifeCycleController;
    }

    private final void registerMotsBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        this.intentFilter = intentFilter;
        intentFilter.addAction(MotsBroadcastReceiver.TIMEZONE_CHANGED);
        IntentFilter intentFilter2 = this.intentFilter;
        if (intentFilter2 != null) {
            intentFilter2.addAction(MotsBroadcastReceiver.ACTION_TIME_CHANGED);
        }
        IntentFilter intentFilter3 = this.intentFilter;
        if (intentFilter3 != null) {
            intentFilter3.addAction(MotsBroadcastReceiver.ACTION_DATE_CHANGED);
        }
        IntentFilter intentFilter4 = this.intentFilter;
        if (intentFilter4 != null) {
            intentFilter4.addAction("com.neusoft.na.mots.closeMainActivity");
        }
        IntentFilter intentFilter5 = this.intentFilter;
        if (intentFilter5 != null) {
            intentFilter5.addAction("com.neusoft.na.mots.closeAndKillMainActivity");
        }
        IntentFilter intentFilter6 = this.intentFilter;
        if (intentFilter6 != null) {
            intentFilter6.addAction("com.neusoft.na.mots.appSwitchToBackground");
        }
        IntentFilter intentFilter7 = this.intentFilter;
        if (intentFilter7 != null) {
            intentFilter7.addAction("com.neusoft.na.mots.showBackDoor");
        }
        IntentFilter intentFilter8 = this.intentFilter;
        if (intentFilter8 != null) {
            intentFilter8.addAction("com.neusoft.na.mots.exitWithoutRestarting");
        }
        IntentFilter intentFilter9 = this.intentFilter;
        if (intentFilter9 != null) {
            intentFilter9.addAction("com.neusoft.na.mots.mask.gone");
        }
        IntentFilter intentFilter10 = this.intentFilter;
        if (intentFilter10 != null) {
            intentFilter10.addAction("com.neusoft.na.mots.widget.mask.gone");
        }
        IntentFilter intentFilter11 = this.intentFilter;
        if (intentFilter11 != null) {
            intentFilter11.addAction(MotsBroadcastReceiver.AR_STATUS_BROADCAST);
        }
        IntentFilter intentFilter12 = this.intentFilter;
        if (intentFilter12 != null) {
            intentFilter12.addAction(MotsBroadcastReceiver.ACTION_CLICK_WEB_VIEW_PRIVACY_DETAILS);
        }
        IntentFilter intentFilter13 = this.intentFilter;
        if (intentFilter13 != null) {
            intentFilter13.addAction(MotsBroadcastReceiver.ACTION_PROTON_X11_MOBILE_POSITIONING);
        }
        IntentFilter intentFilter14 = this.intentFilter;
        if (intentFilter14 != null) {
            intentFilter14.addAction(MotsBroadcastReceiver.INTENT_ACTION_PRESENTATION_TEST);
        }
        IntentFilter intentFilter15 = this.intentFilter;
        if (intentFilter15 != null) {
            intentFilter15.addAction(IMapPresentationComponent.INTENT_ACTION_ACTIVATION_PRIVACY_POLICY_STATUS_CHANGED);
        }
        IntentFilter intentFilter16 = this.intentFilter;
        if (intentFilter16 != null) {
            intentFilter16.addAction(MotsBroadcastReceiver.ACTION_HMI_PAGE_FULL_CHANGED);
        }
        IntentFilter intentFilter17 = this.intentFilter;
        if (intentFilter17 != null) {
            intentFilter17.addAction(MotsBroadcastReceiver.ACTION_HMI_REQUEST_FOCUS);
        }
        MotsBroadcastReceiver companion = MotsBroadcastReceiver.INSTANCE.getInstance();
        this.mMotsBroadcastReceiver = companion;
        this.context.registerReceiver(companion, this.intentFilter);
    }

    private final void setUsbCallBack() {
        UsbBroadcastReceiver.setUsbStatusCallBack(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupWebView() {
        Log.i(TAG, "setupWebView");
        MainActivity.INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder.setupWebView.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "setupWebView";
            }
        });
        if (mXWalkView == null && mWebView == null) {
            WebView.setWebContentsDebuggingEnabled(false);
            Log.i(TAG, "setupWebView1");
            MotsWebView motsWebView = (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) ? new MotsWebView(this.context) : new WebView(this.context);
            motsWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            motsWebView.setBackgroundColor(0);
            motsWebView.setWebViewClient(new WebViewClient() { // from class: com.neusoft.na.mots.MotsMainBinder.setupWebView.2
                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }
            });
            WebSettings settings = motsWebView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setMediaPlaybackRequiresUserGesture(false);
            settings.setDisplayZoomControls(false);
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            if (CheckBoardKt.isC4Pro()) {
                settings.setLoadWithOverviewMode(true);
            }
            settings.setAllowFileAccess(true);
            Log.i(TAG, "setupWebView2");
            motsWebView.setWebChromeClient(new WebChromeClient() { // from class: com.neusoft.na.mots.MotsMainBinder.setupWebView.4
                @Override // android.webkit.WebChromeClient
                public void onProgressChanged(WebView view, final int newProgress) throws RemoteException {
                    Log.i(MotsMainBinder.TAG, "[NeuMapLCM]WebViewnewProgress" + newProgress);
                    super.onProgressChanged(view, newProgress);
                    MainActivity.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder$setupWebView$4$onProgressChanged$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "onProgressChanged: " + newProgress;
                        }
                    });
                    if (newProgress == 100) {
                        MainActivity.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder$setupWebView$4$onProgressChanged$2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "[NeuMapLCM]MOTS HMI rendered first frame";
                            }
                        });
                        MotsMainBinder.this.motsStatus = 2;
                        IMotsServiceCallBack iMotsServiceCallBack = MotsMainBinder.this.callback;
                        if (iMotsServiceCallBack != null) {
                            iMotsServiceCallBack.onWebViewShowHmi();
                        }
                    }
                }
            });
            Log.i(TAG, "[NeuMapLCM]setupWebView3");
            motsWebView.addJavascriptInterface(this.motsJavascriptInstance, "android");
            motsWebView.loadUrl(getHmiUrl());
            mWebView = motsWebView;
            MainActivity.INSTANCE.getLogger().info("using WebView: " + motsWebView.getSettings().getUserAgentString());
            checkStartMots();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupXWalkView() {
        Log.i(TAG, "setupXWalkView");
        MainActivity.INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder.setupXWalkView.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "setupXWalkView";
            }
        });
        if (mXWalkView == null && mWebView == null) {
            XWalkPreferences.setValue(XWalkPreferences.ENABLE_JAVASCRIPT, true);
            XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, false);
            Boolean XWALK_USE_TEXTUREVIEW = BuildConfig.XWALK_USE_TEXTUREVIEW;
            Intrinsics.checkNotNullExpressionValue(XWALK_USE_TEXTUREVIEW, "XWALK_USE_TEXTUREVIEW");
            if (XWALK_USE_TEXTUREVIEW.booleanValue()) {
                XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true);
            }
            final XWalkView xWalkView = new XWalkView(this.context);
            xWalkView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            xWalkView.setBackgroundColor(0);
            xWalkView.setZOrderOnTop(true);
            xWalkView.setUIClient(new XWalkUIClient(xWalkView));
            xWalkView.setUIClient(new XWalkUIClient(this) { // from class: com.neusoft.na.mots.MotsMainBinder$setupXWalkView$xWalkView$1$1
                final /* synthetic */ MotsMainBinder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this.$this_apply);
                    this.this$0 = this;
                }

                @Override // org.xwalk.core.XWalkUIClient
                public void onPageLoadStarted(XWalkView view, String url) {
                    this.$this_apply.addJavascriptInterface(this.this$0.motsJavascriptInstance, "android");
                    super.onPageLoadStarted(view, url);
                }
            });
            xWalkView.setResourceClient(new XWalkResourceClient(xWalkView) { // from class: com.neusoft.na.mots.MotsMainBinder$setupXWalkView$xWalkView$1$2
                @Override // org.xwalk.core.XWalkResourceClient
                public void onProgressChanged(XWalkView view, final int progressInPercent) throws RemoteException {
                    super.onProgressChanged(view, progressInPercent);
                    MainActivity.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder$setupXWalkView$xWalkView$1$2$onProgressChanged$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "onProgressChanged: " + progressInPercent;
                        }
                    });
                    if (progressInPercent == 100) {
                        MainActivity.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.mots.MotsMainBinder$setupXWalkView$xWalkView$1$2$onProgressChanged$2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "MOTS HMI rendered first frame";
                            }
                        });
                        this.motsStatus = 3;
                        IMotsServiceCallBack iMotsServiceCallBack = this.callback;
                        if (iMotsServiceCallBack != null) {
                            iMotsServiceCallBack.onXWalkViewShowHmi();
                        }
                    }
                }
            });
            xWalkView.getSettings().setSupportZoom(false);
            if (CheckBoardKt.isC4Pro()) {
                xWalkView.getSettings().setInitialPageScale(100.0f);
                xWalkView.getSettings().setUseWideViewPort(true);
            }
            xWalkView.addJavascriptInterface(this.motsJavascriptInstance, "android");
            System.loadLibrary("liquidjs");
            xWalkView.loadUrl(getHmiUrl());
            mXWalkView = xWalkView;
            MainActivity.INSTANCE.getLogger().info("using XWalkView: " + xWalkView.getUserAgentString());
            checkStartMots();
        }
    }

    private final String getHmiUrl() {
        return CheckBoardKt.isC4Pro() ? MainActivity.WWW_URL_C4PRO : MainActivity.WWW_URL;
    }

    private final void checkStartMots() {
        Log.i(TAG, "checkStartMots");
        if (checkPermissions().isEmpty()) {
            startMotsService();
        }
    }

    private final List<String> checkPermissions() {
        ArrayList arrayList = new ArrayList();
        if (this.context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (Build.VERSION.SDK_INT < 30) {
            if (this.context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (this.context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (this.context.checkSelfPermission("android.permission.CALL_PHONE") != 0) {
            arrayList.add("android.permission.CALL_PHONE");
        }
        if (CheckBoardKt.isBydSeries()) {
            if (this.context.checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON) != 0) {
                arrayList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
            }
            if (this.context.checkSelfPermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
                arrayList.add(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            if (this.context.checkSelfPermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON) != 0) {
                arrayList.add(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
            }
            if (this.context.checkSelfPermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON) != 0) {
                arrayList.add(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
            }
            if (this.context.checkSelfPermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) != 0) {
                arrayList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
            }
            if (this.context.checkSelfPermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON) != 0) {
                arrayList.add(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Log.i(TAG, "missing Permissions: " + ((String) it.next()));
        }
        return arrayList;
    }

    private final void startMotsService() {
        Observable<ApplicationStatus> applicationStatusObservable;
        Log.i(TAG, "startMotsService");
        if (this.mMotsServiceStarted) {
            return;
        }
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null && (applicationStatusObservable = oneCoreLifeCycleController.getApplicationStatusObservable()) != null) {
            final Function1<ApplicationStatus, Unit> function1 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.mots.MotsMainBinder.startMotsService.1

                /* JADX INFO: renamed from: com.neusoft.na.mots.MotsMainBinder$startMotsService$1$WhenMappings */
                /* JADX INFO: compiled from: MotsMainBinder.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[ApplicationStatus.Status.values().length];
                        try {
                            iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[ApplicationStatus.Status.RUNNING.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                    invoke2(applicationStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApplicationStatus applicationStatus) {
                    Log.i(MotsMainBinder.TAG, "startMotsService" + applicationStatus.getMStatus());
                    int i = WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()];
                    if (i != 1) {
                        if (i == 2) {
                            MotsMainBinder.this.blRunning();
                            return;
                        } else {
                            Log.i(MotsMainBinder.TAG, "Drop status:" + applicationStatus.getMStatus());
                            return;
                        }
                    }
                    OneCoreLifeCycleController mMotsLifeCycleController = MotsMainBinder.this.getMMotsLifeCycleController();
                    if (mMotsLifeCycleController != null) {
                        mMotsLifeCycleController.startApplication();
                    }
                }
            };
            applicationStatusObservable.subscribe(new Action1() { // from class: com.neusoft.na.mots.MotsMainBinder$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MotsMainBinder.startMotsService$lambda$3(function1, obj);
                }
            });
        }
        Log.i(TAG, "mMotsLifeCycleController?.init()");
        OneCoreLifeCycleController oneCoreLifeCycleController2 = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController2 != null) {
            oneCoreLifeCycleController2.init();
        }
        this.mMotsServiceStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startMotsService$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void blRunning() {
        Log.i(TAG, "blRunning");
        MotsBroadcastReceiver motsBroadcastReceiver = this.mMotsBroadcastReceiver;
        if (motsBroadcastReceiver != null) {
            motsBroadcastReceiver.setBlRunningStatus(true);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void haspermissions() {
        startMotsService();
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void onMainResume() {
        Log.i(TAG, "onMainResume");
        this.activeStatus = Companion.ActivityStatus.Resume;
        try {
            IGuardInterface iGuardInterface = this.binder;
            if (iGuardInterface != null) {
                iGuardInterface.active(true);
            }
        } catch (Exception e) {
            Log.i(TAG, "IGuardInterface Err");
            e.printStackTrace();
        }
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setMapStatus(10);
        }
        XWalkInitializer xWalkInitializer = this.mXWalkInitializer;
        if (xWalkInitializer == null || mXWalkView != null) {
            return;
        }
        xWalkInitializer.initAsync();
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public int getDayNightMode() {
        return this.dayNightMode;
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public int getMotsStatus() {
        Log.i(TAG, "motsStatus:" + this.motsStatus);
        return this.motsStatus;
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void onMainPause() {
        Log.i(TAG, "onMainPause");
        this.activeStatus = Companion.ActivityStatus.Pause;
        try {
            IGuardInterface iGuardInterface = this.binder;
            if (iGuardInterface != null) {
                iGuardInterface.active(false);
            }
        } catch (Exception e) {
            Log.i(TAG, "IGuardInterface Err");
            e.printStackTrace();
        }
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setMapStatus(11);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void unSetCallBack() {
        this.callback = null;
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void setWidgetSurface(Surface surface) {
        Log.i(TAG, "setWidgetSurface");
        this.widgetSurface = surface;
        Log.i(TAG, "setWidgetSurface: " + (surface != null ? Boolean.valueOf(surface.isValid()) : null));
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setWidgetSurface(surface);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void setClusterSurface(Surface surface) {
        Log.i(TAG, "setClusterSurface");
        this.clusterSurface = surface;
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setClusterSurface(surface);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void onMainCreate() {
        this.activeStatus = Companion.ActivityStatus.Create;
        try {
            IGuardInterface iGuardInterface = this.binder;
            if (iGuardInterface != null) {
                iGuardInterface.active(true);
            }
        } catch (Exception e) {
            Log.i(TAG, "IGuardInterface Err");
            e.printStackTrace();
        }
        Log.i(TAG, "onMainCreate");
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void setInMultiWindowMode(boolean isInMultiWindowMode) {
        Log.i(TAG, "setInMultiWindowMode == " + isInMultiWindowMode);
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setInMultiWindowMode(isInMultiWindowMode);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void maskGone() {
        Log.i(TAG, "maskGone");
        if (CheckBoardKt.isByd8155f()) {
            startGuardService();
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void setSurface(Surface surface) {
        Log.i(TAG, "setSurface");
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setSurface(surface);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void notifySurfaceSizeChanged() {
        Log.i(TAG, "[notifySurfaceSizeChanged]");
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.notifySurfaceSizeChanged();
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void onKeyCode(int code) {
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setKeyCode(code);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void setCallBack(IMotsServiceCallBack callback) {
        this.callback = callback;
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void onMainDestroy() {
        this.activeStatus = Companion.ActivityStatus.Destroy;
        Log.i(TAG, "onMainDestroy");
        try {
            IGuardInterface iGuardInterface = this.binder;
            if (iGuardInterface != null) {
                iGuardInterface.active(false);
            }
        } catch (Exception e) {
            Log.i(TAG, "IGuardInterface Err");
            e.printStackTrace();
        }
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.setMapStatus(9);
        }
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public int getWebViewMode() {
        return getWebViewMode();
    }

    @Override // com.neusoft.na.navigation.IMotsServiceInterface
    public void timeZoneChanged() {
        Log.i(TAG, "timeZoneChanged");
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.timeZoneChanged();
        }
    }

    @Override // com.neusoft.na.ocservice.MainCallBack
    public void onPlaySoundEffect() throws RemoteException {
        Log.i(TAG, "onPlaySoundEffect");
        IMotsServiceCallBack iMotsServiceCallBack = this.callback;
        if (iMotsServiceCallBack != null) {
            iMotsServiceCallBack.onPlaySoundEffect();
        }
    }

    @Override // com.neusoft.na.ocservice.MainCallBack
    public void onDayNightModeChanged(int mode) throws RemoteException {
        this.dayNightMode = mode;
        IMotsServiceCallBack iMotsServiceCallBack = this.callback;
        if (iMotsServiceCallBack != null) {
            iMotsServiceCallBack.onDayNightModeChanged(mode);
        }
    }

    @Override // com.neusoft.na.mots.UsbStatusCallBack
    public void usbMount(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Log.i(TAG, "usbMount");
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.usbMount(path);
        }
    }

    @Override // com.neusoft.na.mots.UsbStatusCallBack
    public void usbUnMount() {
        Log.i(TAG, "usbUnMount");
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mMotsLifeCycleController;
        if (oneCoreLifeCycleController != null) {
            oneCoreLifeCycleController.usbUnMount();
        }
    }

    private final void unRegisterMotsBroadcastReceiver() {
        this.context.unregisterReceiver(this.mMotsBroadcastReceiver);
    }

    public final void onDestroy() {
        unRegisterMotsBroadcastReceiver();
    }

    public final void startGuardService() {
        Log.i(MainActivity.TAG, "startMotsMainService");
        if (CheckBoardKt.isByd8155f()) {
            List<String> listCheckPermissions = checkPermissions();
            if (!listCheckPermissions.isEmpty() && (listCheckPermissions.size() != 1 || !Intrinsics.areEqual(listCheckPermissions.get(0), "android.permission.CALL_PHONE"))) {
                return;
            } else {
                Log.i(TAG, "can startGuardService");
            }
        }
        if (CheckBoardKt.isGreatwallV61()) {
            this.context.startService(new Intent(this.context, (Class<?>) GuardService.class));
        } else {
            this.context.startForegroundService(new Intent(this.context, (Class<?>) GuardService.class));
        }
        this.context.bindService(new Intent(this.context, (Class<?>) GuardService.class), this.serviceConnection, 1);
    }
}
