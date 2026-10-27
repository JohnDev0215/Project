package com.neusoft.na.byd.presentation;

import android.app.Presentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import cerence.ark.assistant.controller.domain.hmi.HMIRequestsEnum;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.neusoft.na.byd.R;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.MotsService;
import com.neusoft.na.ocservice.OneCoreService;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MapPresentationView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013*\u0004\n\u000f\u0014\"\u0018\u0000 L2\u00020\u0001:\u0001LB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020)H\u0002J\u0006\u0010+\u001a\u00020)J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u0004\u0018\u00010'J\b\u00101\u001a\u00020\bH\u0002J\u0006\u00102\u001a\u00020)J\b\u00103\u001a\u00020)H\u0007J\b\u00104\u001a\u00020)H\u0002J\b\u00105\u001a\u00020)H\u0002J\u0006\u00106\u001a\u00020)J\b\u00107\u001a\u00020\bH\u0002J\u0012\u00108\u001a\u00020)2\b\u00109\u001a\u0004\u0018\u00010:H\u0015J\b\u0010;\u001a\u00020)H\u0016J\b\u0010<\u001a\u00020)H\u0016J\u000e\u0010=\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017J\b\u0010>\u001a\u00020)H\u0014J\b\u0010?\u001a\u00020)H\u0014J\u000e\u0010@\u001a\u00020)2\u0006\u0010 \u001a\u00020\u0017J\u0010\u0010A\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002J\u0012\u0010B\u001a\u00020)2\b\u0010C\u001a\u0004\u0018\u00010'H\u0003J\u0016\u0010D\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010E\u001a\u00020)2\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010F\u001a\u00020)J\u0010\u0010G\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002J\b\u0010H\u001a\u00020)H\u0002J\u0010\u0010I\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002J\u0010\u0010J\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002J\u0010\u0010K\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0017H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#R\u000e\u0010$\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/neusoft/na/byd/presentation/MapPresentationView;", "Landroid/app/Presentation;", "context", "Landroid/content/Context;", HMIRequestsEnum.DISPLAY, "Landroid/view/Display;", "(Landroid/content/Context;Landroid/view/Display;)V", "activationStatus", "", "broadcastReceiver", "com/neusoft/na/byd/presentation/MapPresentationView$broadcastReceiver$1", "Lcom/neusoft/na/byd/presentation/MapPresentationView$broadcastReceiver$1;", "bydAutoBodyworkDevice", "Landroid/hardware/bydauto/bodywork/BYDAutoBodyworkDevice;", "bydAutoBodyworkListener", "com/neusoft/na/byd/presentation/MapPresentationView$bydAutoBodyworkListener$1", "Lcom/neusoft/na/byd/presentation/MapPresentationView$bydAutoBodyworkListener$1;", "bydAutoNaviDevice", "Lcom/byd/bydnaviapi/BydAutoNaviDevice;", "bydAutoNaviListener", "com/neusoft/na/byd/presentation/MapPresentationView$bydAutoNaviListener$1", "Lcom/neusoft/na/byd/presentation/MapPresentationView$bydAutoNaviListener$1;", "cacheUiState", "", "dayNightMode", "firstShow", "handler", "Landroid/os/Handler;", "hmiWebView", "Landroid/webkit/WebView;", "localDisplayMode", "mapFocusChanged2Navigation", "mapType", "motsJavascriptInstance", "com/neusoft/na/byd/presentation/MapPresentationView$motsJavascriptInstance$1", "Lcom/neusoft/na/byd/presentation/MapPresentationView$motsJavascriptInstance$1;", "motsStatus", "privacyPolicyStatus", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "clearWebView", "", "closeDisplayPresentation", "closePresentation", "fullDisplayPresentation", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "getHmiUrl", "", "getSurfaceTexture", "hasMapFocus", "hidePresentation", "init", "initHmi", "initMap", "initSystemInterface", "isC4Pro", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetachedFromWindow", "onDisplayRemoved", "onINSNavDisplayMode", "onStart", "onStop", "onTestMapFocusChanged", "sendNaviPresentationState", "sendSurface", OneCoreService.PAYLOAD_SURFACE, "setActivationPrivacyPolicyStatus", "setDayNightModeChange", "showPresentation", "smallDisplayPresentation", "startDisplayConfig", "startPresentationClose", "startPresentationOpen", "switchDisplayMode", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MapPresentationView extends Presentation {
    private static final String ACTION_PRESENTATION_BYDAUTONAVIDEVICE_CAN_USE = "com.neusoft.na.navigation.presentation.interface.can.use";
    public static final String ACTION_PRESENTATION_MAP_FOCUS_CHANGED = "com.neusoft.na.navigation.presentation.mapFocusChanged";
    public static final String ACTION_PRESENTATION_MODE_SWITH = "com.neusoft.na.navigation.presentation.mode.switch";
    private static final int MOTS_STATUS_NO = -1;
    private static final int MOTS_STATUS_WEBVIEW = 2;
    private static final String TAG = "MapPresentationView";
    private static final String WWW_URL = "file:///android_asset/shmi/index.html";
    private boolean activationStatus;
    private final MapPresentationView$broadcastReceiver$1 broadcastReceiver;
    private BYDAutoBodyworkDevice bydAutoBodyworkDevice;
    private final MapPresentationView$bydAutoBodyworkListener$1 bydAutoBodyworkListener;
    private BydAutoNaviDevice bydAutoNaviDevice;
    private final MapPresentationView$bydAutoNaviListener$1 bydAutoNaviListener;
    private int cacheUiState;
    private int dayNightMode;
    private boolean firstShow;
    private final Handler handler;
    private WebView hmiWebView;
    private int localDisplayMode;
    private boolean mapFocusChanged2Navigation;
    private int mapType;
    private final MapPresentationView$motsJavascriptInstance$1 motsJavascriptInstance;
    private int motsStatus;
    private boolean privacyPolicyStatus;
    private SurfaceTexture surfaceTexture;

    private final String getHmiUrl() {
        return WWW_URL;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.neusoft.na.byd.presentation.MapPresentationView$motsJavascriptInstance$1] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.neusoft.na.byd.presentation.MapPresentationView$broadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.neusoft.na.byd.presentation.MapPresentationView$bydAutoBodyworkListener$1] */
    public MapPresentationView(final Context context, Display display) {
        super(context, display);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(display, "display");
        this.motsStatus = -1;
        this.localDisplayMode = 1;
        this.mapType = 1;
        this.dayNightMode = -1;
        this.cacheUiState = -1;
        this.firstShow = true;
        this.handler = new Handler();
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent != null) {
                    MapPresentationView mapPresentationView = this.this$0;
                    if (Intrinsics.areEqual(intent.getAction(), "com.neusoft.na.navigation.presentation.daynight.mode.changed")) {
                        int intExtra = intent.getIntExtra(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, -1);
                        Log.i("MapPresentationView", "presentationDayNightMode == " + intExtra);
                        mapPresentationView.setDayNightModeChange(intExtra);
                        return;
                    }
                    if (Intrinsics.areEqual(intent.getAction(), "com.neusoft.na.navigation.presentation.dlt.open")) {
                        Log.i("MapPresentationView", "open dlt log...");
                        if (ContextCompat.checkSelfPermission(mapPresentationView.getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) == 0) {
                            BydAutoNaviDevice bydAutoNaviDevice = mapPresentationView.bydAutoNaviDevice;
                            boolean z = false;
                            if (!(bydAutoNaviDevice != null && bydAutoNaviDevice.getFocusMapType() == 0)) {
                                BydAutoNaviDevice bydAutoNaviDevice2 = mapPresentationView.bydAutoNaviDevice;
                                if (bydAutoNaviDevice2 != null && bydAutoNaviDevice2.getFocusMapType() == 1) {
                                    z = true;
                                }
                                if (!z) {
                                    return;
                                }
                            }
                            if (mapPresentationView.activationStatus && mapPresentationView.privacyPolicyStatus) {
                                BydAutoNaviDevice bydAutoNaviDevice3 = mapPresentationView.bydAutoNaviDevice;
                                Integer numValueOf = bydAutoNaviDevice3 != null ? Integer.valueOf(bydAutoNaviDevice3.getInsNavDisplayMode()) : null;
                                Log.i("MapPresentationView", "current insNavDisplayMode == " + numValueOf);
                                if (numValueOf != null && numValueOf.intValue() == 1) {
                                    return;
                                }
                                mapPresentationView.sendNaviPresentationState(1);
                                mapPresentationView.startPresentationClose(1);
                                return;
                            }
                            return;
                        }
                        Log.e("MapPresentationView", "has no BYDAUTO_INSTRUMENT_COMMON permission!");
                    }
                }
            }
        };
        this.bydAutoBodyworkListener = new AbsBYDAutoBodyworkListener() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$bydAutoBodyworkListener$1
            /* JADX WARN: Removed duplicated region for block: B:19:0x0075  */
            @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPowerLevelChanged(int r6) {
                /*
                    Method dump skipped, instruction units count: 228
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.presentation.MapPresentationView$bydAutoBodyworkListener$1.onPowerLevelChanged(int):void");
            }

            @Override // android.hardware.IBYDAutoListener
            public void isConnected(boolean isBinding) {
                super.isConnected(isBinding);
                Log.i("MapPresentationView", "bydAutoBodyworkListener --> isConnected == " + isBinding);
            }
        };
        this.bydAutoNaviListener = new MapPresentationView$bydAutoNaviListener$1(this);
        this.motsJavascriptInstance = new Object() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$motsJavascriptInstance$1
            @JavascriptInterface
            public final String getNaConfigJson() {
                File file = new File(Environment.getExternalStorageDirectory(), "naviexternconfig/mots_runtime_config.json");
                return (file.exists() && file.isFile()) ? FilesKt.readText$default(file, null, 1, null) : "{}";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void switchDisplayMode(final int r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "[switchDisplayMode] value --> "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MapPresentationView"
            android.util.Log.i(r1, r0)
            int r0 = r6.localDisplayMode
            r2 = 2
            r3 = 4
            r4 = 3
            r5 = 1
            if (r0 == r5) goto L1e
            if (r0 != r2) goto L23
        L1e:
            if (r7 == r4) goto L4a
            if (r7 != r3) goto L23
            goto L4a
        L23:
            if (r0 == r4) goto L27
            if (r0 != r3) goto L2c
        L27:
            if (r7 == r5) goto L44
            if (r7 != r2) goto L2c
            goto L44
        L2c:
            if (r7 == r4) goto L3b
            if (r7 != r3) goto L31
            goto L3b
        L31:
            if (r7 == r5) goto L35
            if (r7 != r2) goto L52
        L35:
            java.lang.String r0 = "display from close change close."
            android.util.Log.i(r1, r0)
            goto L52
        L3b:
            java.lang.String r0 = "display from open change open."
            android.util.Log.i(r1, r0)
            r6.startPresentationOpen(r7)
            goto L52
        L44:
            java.lang.String r0 = "display from open 2 close."
            android.util.Log.i(r1, r0)
            goto L52
        L4a:
            java.lang.String r0 = "display from close 2 open."
            android.util.Log.i(r1, r0)
            r6.startPresentationOpen(r7)
        L52:
            boolean r0 = r6.firstShow
            r2 = 0
            if (r0 == 0) goto L68
            java.lang.String r0 = "start from the first"
            android.util.Log.i(r1, r0)
            int r0 = r6.localDisplayMode
            if (r0 == r4) goto L63
            if (r0 != r3) goto L7d
        L63:
            r6.firstShow = r2
            r2 = 2000(0x7d0, double:9.88E-321)
            goto L7f
        L68:
            boolean r0 = r6.mapFocusChanged2Navigation
            if (r0 == 0) goto L7d
            java.lang.String r0 = "start from the mapFocusChanged2Navigation"
            android.util.Log.i(r1, r0)
            int r0 = r6.localDisplayMode
            if (r0 == r4) goto L78
            if (r0 != r3) goto L7d
        L78:
            r6.mapFocusChanged2Navigation = r2
            r2 = 100
            goto L7f
        L7d:
            r2 = 0
        L7f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = "delayMillis == "
            r0.<init>(r4)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r1, r0)
            android.os.Handler r0 = r6.handler
            com.neusoft.na.byd.presentation.MapPresentationView$$ExternalSyntheticLambda0 r1 = new com.neusoft.na.byd.presentation.MapPresentationView$$ExternalSyntheticLambda0
            r1.<init>()
            r0.postDelayed(r1, r2)
            r6.localDisplayMode = r7
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.neusoft.na.navigation.presentation.mode.switch"
            r0.<init>(r1)
            java.lang.String r1 = "mode"
            r0.putExtra(r1, r7)
            android.content.Context r7 = r6.getContext()
            r7.sendBroadcast(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.presentation.MapPresentationView.switchDisplayMode(int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void switchDisplayMode$lambda$0(MapPresentationView this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendNaviPresentationState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNaviPresentationState(int value) {
        Log.i(TAG, "[sendNaviPresentationState] --> mapType == " + this.mapType + ", value == " + value);
        int i = value != 2 ? value != 3 ? value != 4 ? 1 : 6 : 5 : 7;
        if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
        } else {
            BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
            Log.i(TAG, "[sendNaviPresentationState] naviPresentationState == " + i + ", code == " + (bydAutoNaviDevice != null ? Integer.valueOf(bydAutoNaviDevice.sendNaviPresentationState(this.mapType, i)) : null));
        }
    }

    private final void startPresentationOpen(int value) {
        Log.i(TAG, "[startPresentationFromClose2Open] --> value == " + value);
        if (value == 3) {
            smallDisplayPresentation(value);
        } else {
            if (value != 4) {
                return;
            }
            fullDisplayPresentation(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPresentationClose(int value) {
        Log.i(TAG, "[startPresentationFromOpen2Close] --> value == " + value);
        this.localDisplayMode = value;
        closeDisplayPresentation();
    }

    private final void smallDisplayPresentation(int value) {
        this.localDisplayMode = value;
        Log.i(TAG, "[smallDisplayPresentation] localDisplayMode == " + this.localDisplayMode);
        if (isShowing()) {
            return;
        }
        showPresentation();
    }

    private final void fullDisplayPresentation(int value) {
        this.localDisplayMode = value;
        Log.i(TAG, "[fullDisplayPresentation] localDisplayMode == " + this.localDisplayMode);
        if (isShowing()) {
            return;
        }
        showPresentation();
    }

    private final void closeDisplayPresentation() {
        Log.i(TAG, "[closeDisplayPresentation]");
        if (isShowing()) {
            Log.i(TAG, "goto close.");
            hidePresentation();
        } else {
            Log.i(TAG, "not need close, because is closed now.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasMapFocus() {
        Log.i(TAG, "[hasMapFocus]");
        boolean z = this.mapType == 1;
        Log.i(TAG, "[hasMapFocus] --> " + (this.mapType == 1));
        return z;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "[onCreate]");
        setContentView(R.layout.layout_map_presentation_view);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.neusoft.na.navigation.presentation.daynight.mode.changed");
        intentFilter.addAction("com.neusoft.na.navigation.presentation.dlt.open");
        getContext().registerReceiver(this.broadcastReceiver, intentFilter);
    }

    @Override // android.app.Presentation
    public void onDisplayRemoved() {
        super.onDisplayRemoved();
        Log.i(TAG, "[onDisplayRemoved]");
    }

    @Override // android.app.Presentation, android.app.Dialog
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "[onStart]");
        initMap();
        initHmi();
        initSystemInterface();
    }

    @Override // android.app.Presentation, android.app.Dialog
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "[onStop]");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        Log.i(TAG, "[onDetachedFromWindow]");
        super.onDetachedFromWindow();
    }

    public final SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSurface(SurfaceTexture surface) {
        Log.i(TAG, "[sendSurface] --> surface == " + surface);
        Intent intent = new Intent(MotsService.ACTION_SET_CLUSTER_SURFACE);
        intent.putExtra("clusterSurface", surface == null ? null : new Surface(surface));
        getContext().sendStickyBroadcast(intent);
    }

    private final void initMap() {
        Log.i(TAG, "[initMap]");
        TextureView textureView = (TextureView) findViewById(R.id.presentationMapTextureView);
        Intrinsics.checkNotNull(textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.neusoft.na.byd.presentation.MapPresentationView.initMap.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MapPresentationView.TAG, "onSurfaceTextureAvailable width == " + width + " , height == " + height);
                MapPresentationView.this.surfaceTexture = surface;
                MapPresentationView.this.sendSurface(surface);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MapPresentationView.TAG, "onSurfaceTextureSizeChanged width == " + width + " , height == " + height);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MapPresentationView.TAG, "onSurfaceTextureDestroyed");
                MapPresentationView.this.surfaceTexture = null;
                MapPresentationView.this.sendSurface(null);
                return true;
            }
        });
    }

    private final void initHmi() {
        Log.i(TAG, "[initHmi]");
        if (this.hmiWebView == null) {
            Log.i(TAG, "[initHmi] --> webView == null");
            WebView.setWebContentsDebuggingEnabled(false);
            final WebView webView = new WebView(getContext());
            webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            webView.setBackgroundColor(0);
            webView.setWebViewClient(new WebViewClient() { // from class: com.neusoft.na.byd.presentation.MapPresentationView.initHmi.1
                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    webView.addJavascriptInterface(this.motsJavascriptInstance, "android");
                    super.onPageStarted(view, url, favicon);
                }
            });
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setMediaPlaybackRequiresUserGesture(false);
            settings.setDisplayZoomControls(false);
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            if (isC4Pro()) {
                settings.setLoadWithOverviewMode(true);
            }
            webView.setWebChromeClient(new WebChromeClient() { // from class: com.neusoft.na.byd.presentation.MapPresentationView.initHmi.3
                @Override // android.webkit.WebChromeClient
                public void onProgressChanged(WebView view, int newProgress) {
                    Log.d(MapPresentationView.TAG, "newProgress == " + newProgress);
                    super.onProgressChanged(view, newProgress);
                    if (newProgress == 100) {
                        MapPresentationView.this.motsStatus = 2;
                    }
                }
            });
            webView.addJavascriptInterface(this.motsJavascriptInstance, "android");
            webView.loadUrl(getHmiUrl());
            this.hmiWebView = webView;
            Log.i(TAG, "hmiWebView instance.");
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.hmiWebFrameLayout);
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.hmiWebFrameLayout);
            if (frameLayout2 != null) {
                frameLayout2.addView(this.hmiWebView);
            }
            FrameLayout frameLayout3 = (FrameLayout) findViewById(R.id.hmiWebFrameLayout);
            if (frameLayout3 == null) {
                return;
            }
            frameLayout3.setVisibility(0);
            return;
        }
        Log.i(TAG, "hmiWebView instance already, do not instance again.");
    }

    private final boolean isC4Pro() {
        return Intrinsics.areEqual(Build.BOARD, "c4pro");
    }

    public final void initSystemInterface() {
        Log.i(TAG, "[initSystemInterface]");
        if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
            return;
        }
        if (this.bydAutoNaviDevice == null) {
            this.bydAutoNaviDevice = BydAutoNaviDevice.getInstance(getContext());
            Log.i(TAG, "bydAutoNaviDevice instance.");
        } else {
            Log.i(TAG, "BydAutoNaviDevice is instance, do not instance again.");
        }
        if (this.bydAutoBodyworkDevice == null) {
            this.bydAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(getContext());
            Log.i(TAG, "BYDAutoBodyworkDevice instance.");
        } else {
            Log.i(TAG, "BYDAutoBodyworkDevice is instance, do not instance again.");
        }
    }

    public final void init() {
        Log.i(TAG, "[init]");
        if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
            return;
        }
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        if (bydAutoNaviDevice != null) {
            this.mapType = bydAutoNaviDevice.getFocusMapType();
            Log.i(TAG, "mapType == " + this.mapType);
            if (this.mapType == 0) {
                this.mapType = 1;
                Log.i(TAG, "mapType from 0 to " + this.mapType);
            }
            Log.i(TAG, "requestMapFocus");
            getContext().sendStickyBroadcast(new Intent(ACTION_PRESENTATION_BYDAUTONAVIDEVICE_CAN_USE));
            bydAutoNaviDevice.registerListener((AbsBYDAutoNaviListener) this.bydAutoNaviListener);
            Log.i(TAG, "register AbsBYDAutoNaviListener.");
            if (hasMapFocus()) {
                startDisplayConfig();
            }
        }
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.bydAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.registerListener((AbsBYDAutoBodyworkListener) this.bydAutoBodyworkListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDisplayConfig() {
        Log.i(TAG, "[startDisplayConfig]");
        if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
            return;
        }
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        Intrinsics.checkNotNull(bydAutoNaviDevice);
        int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
        Log.i(TAG, "startInsNavDisplayMode == " + insNavDisplayMode);
        switchDisplayMode(insNavDisplayMode);
    }

    public final void showPresentation() {
        this.handler.post(new Runnable() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                MapPresentationView.showPresentation$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPresentation$lambda$3(MapPresentationView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringBuilder sb = new StringBuilder("[showPresentation] --> displayId == ");
        Display display = this$0.getDisplay();
        Intrinsics.checkNotNull(display);
        Log.i(TAG, sb.append(display.getDisplayId()).toString());
        this$0.show();
    }

    public final void hidePresentation() {
        this.handler.post(new Runnable() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MapPresentationView.hidePresentation$lambda$4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hidePresentation$lambda$4(MapPresentationView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "[hidePresentation]");
        this$0.hide();
    }

    public final void setDayNightModeChange(int dayNightMode) {
        Log.i(TAG, "[setDayNightModeChange] --> dayNightMode == " + dayNightMode + ", this@MapPresentationView.dayNightMode == " + this.dayNightMode);
        Log.i(TAG, "[setDayNightModeChange] --> activationStatus == " + this.activationStatus + ", privacyPolicyStatus == " + this.privacyPolicyStatus);
        int i = this.dayNightMode;
        if (i == -1) {
            Log.i(TAG, "[setDayNightModeChange] init set dayNightMode");
            this.dayNightMode = dayNightMode;
            return;
        }
        if (i != dayNightMode) {
            Log.i(TAG, "[setDayNightModeChange] dayNightMode changed");
            this.dayNightMode = dayNightMode;
            if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
                Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
                return;
            }
            BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
            Integer numValueOf = bydAutoNaviDevice != null ? Integer.valueOf(bydAutoNaviDevice.getFocusMapType()) : null;
            BydAutoNaviDevice bydAutoNaviDevice2 = this.bydAutoNaviDevice;
            Integer numValueOf2 = bydAutoNaviDevice2 != null ? Integer.valueOf(bydAutoNaviDevice2.getInsNavDisplayMode()) : null;
            Log.i(TAG, "[setDayNightModeChange] focusMapType == " + numValueOf + ", insNavDisplayMode == " + numValueOf2);
            if (((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 1)) && (((numValueOf2 != null && numValueOf2.intValue() == 3) || (numValueOf2 != null && numValueOf2.intValue() == 4)) && this.activationStatus && this.privacyPolicyStatus)) {
                Log.i(TAG, "[setDayNightModeChange] dayNightMode change start");
                if (this.cacheUiState != 7) {
                    sendNaviPresentationState(1);
                    this.handler.postDelayed(new Runnable() { // from class: com.neusoft.na.byd.presentation.MapPresentationView$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            MapPresentationView.setDayNightModeChange$lambda$5(this.f$0);
                        }
                    }, 1000L);
                    return;
                } else {
                    Log.i(TAG, "cacheUiState is UISTATE_MAP_CLOSE, so don't close and open meter hole.");
                    return;
                }
            }
            Log.i(TAG, "[setDayNightModeChange] current focus is not NeuMap or current insNavDisplayMode is not small/full mode, pass this time setDayNightModeChange");
            return;
        }
        Log.i(TAG, "[setDayNightModeChange] pass this time setDayNightModeChange");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDayNightModeChange$lambda$5(MapPresentationView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BydAutoNaviDevice bydAutoNaviDevice = this$0.bydAutoNaviDevice;
        Intrinsics.checkNotNull(bydAutoNaviDevice);
        int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
        Log.i(TAG, "[setDayNightModeChange] currentInsNavDisplayMode == " + insNavDisplayMode);
        this$0.sendNaviPresentationState(insNavDisplayMode);
    }

    public final void setActivationPrivacyPolicyStatus(boolean activationStatus, boolean privacyPolicyStatus) {
        Log.i(TAG, "[setActivationPrivacyPolicyStatus] --> activationStatus == " + activationStatus + ", privacyPolicyStatus == " + privacyPolicyStatus);
        this.activationStatus = activationStatus;
        this.privacyPolicyStatus = privacyPolicyStatus;
        if (activationStatus && privacyPolicyStatus) {
            sendSurface(this.surfaceTexture);
        }
    }

    public final void closePresentation() {
        Log.i(TAG, "[closePresentation]");
        if (ContextCompat.checkSelfPermission(getContext(), BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
            Log.e(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission!");
            return;
        }
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        Integer numValueOf = bydAutoNaviDevice != null ? Integer.valueOf(bydAutoNaviDevice.getFocusMapType()) : null;
        BydAutoNaviDevice bydAutoNaviDevice2 = this.bydAutoNaviDevice;
        Integer numValueOf2 = bydAutoNaviDevice2 != null ? Integer.valueOf(bydAutoNaviDevice2.getInsNavDisplayMode()) : null;
        Log.i(TAG, "focusMapType == " + numValueOf + ", insNavDisplayMode == " + numValueOf2);
        if ((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 1)) {
            if (numValueOf2 == null || numValueOf2.intValue() != 1) {
                Log.i(TAG, "close");
                sendNaviPresentationState(1);
                startPresentationClose(1);
            }
            BydAutoNaviDevice bydAutoNaviDevice3 = this.bydAutoNaviDevice;
            Log.i(TAG, "[sendPrivacyPolicy] --> sendNaviPresentationState MAP_STATE_NOT_AGREE_AGREEMENT, value == 9, code == " + (bydAutoNaviDevice3 != null ? Integer.valueOf(bydAutoNaviDevice3.sendNaviPresentationState(1, 9)) : null));
        }
    }

    private final void clearWebView() {
        Log.i(TAG, "[clearWebView]");
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.hmiWebFrameLayout);
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        WebView webView = this.hmiWebView;
        if (webView != null) {
            webView.onPause();
            webView.destroy();
        }
        this.hmiWebView = null;
    }

    public final void onINSNavDisplayMode(int value) {
        Log.i(TAG, "{test}[onINSNavDisplayMode] --> value == " + value);
        this.bydAutoNaviListener.onINSNavDisplayMode(value);
        if (value == 1 || value == 2) {
            this.bydAutoNaviListener.onInsUiChanged(value);
        }
    }

    public final void onTestMapFocusChanged(int mapType) {
        Log.i(TAG, "{test}[onTestMapFocusChanged] --> mapType == " + mapType);
        this.bydAutoNaviListener.onMapFocusChanged(mapType);
    }
}
