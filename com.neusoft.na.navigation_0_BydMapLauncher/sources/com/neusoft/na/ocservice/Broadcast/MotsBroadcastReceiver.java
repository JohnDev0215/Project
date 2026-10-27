package com.neusoft.na.ocservice.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MotsBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "blRunningStatus", "", "callback", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastCallBack;", "goneMaskStatus", "goneWidgetMaskStatus", "maskCallback", "Lcom/neusoft/na/ocservice/Broadcast/MotsMaskBroadcastCallBack;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "setBlRunningStatus", "boolean", "setMotsBroadcastCallBack", "motsBroadcastCallBack", "setMotsMaskBroadcastCallBack", "motsMaskBroadcastCallBack", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_CLICK_WEB_VIEW_PRIVACY_DETAILS = "com.neusoft.na.navigation.action.ClickWebViewPrivacyDetails";
    public static final String ACTION_DATE_CHANGED = " android.intent.action.DATE_CHANGED";
    public static final String ACTION_HMI_PAGE_FULL_CHANGED = "com.neusoft.na.navigation.hmi.page.full.changed";
    public static final String ACTION_HMI_REQUEST_FOCUS = "com.neusoft.na.navigation.action.hmi.request.focus";
    public static final String ACTION_PRESENTATION_KEY = "type";
    public static final String ACTION_PRESENTATION_VALUE = "value";
    public static final String ACTION_PROTON_X11_MOBILE_POSITIONING = "com.neusoft.na.navigation.ProtonX11MobilePositioning";
    public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
    public static final String APP_SWITCH_TO_BACKGROUND = "com.neusoft.na.mots.appSwitchToBackground";
    public static final String AR_STATUS_BROADCAST = "com.neusoft.ar.status";
    public static final String CLOSE_MAINACTIVITY = "com.neusoft.na.mots.closeMainActivity";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXIT_WITHOUT_RESTARTING = "com.neusoft.na.mots.exitWithoutRestarting";
    public static final String GONEMASK = "com.neusoft.na.mots.mask.gone";
    public static final String INTENT_ACTION_PRESENTATION_TEST = "com.neusoft.na.navigation.presentation.test";
    public static final String KILL_MAINACTIVITY = "com.neusoft.na.mots.closeAndKillMainActivity";
    public static final String SHOW_BACK_DOOR = "com.neusoft.na.mots.showBackDoor";
    public static final String TAG = "XJMotsBroadcastReceiver";
    public static final String TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
    public static final String WIDGET_GONE_MASK = "com.neusoft.na.mots.widget.mask.gone";
    private static MotsBroadcastReceiver instance;
    private boolean blRunningStatus;
    private MotsBroadcastCallBack callback;
    private boolean goneMaskStatus;
    private boolean goneWidgetMaskStatus;
    private MotsMaskBroadcastCallBack maskCallback;

    /* JADX INFO: compiled from: MotsBroadcastReceiver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver$Companion;", "", "()V", "ACTION_CLICK_WEB_VIEW_PRIVACY_DETAILS", "", "ACTION_DATE_CHANGED", "ACTION_HMI_PAGE_FULL_CHANGED", "ACTION_HMI_REQUEST_FOCUS", "ACTION_PRESENTATION_KEY", "ACTION_PRESENTATION_VALUE", "ACTION_PROTON_X11_MOBILE_POSITIONING", "ACTION_TIME_CHANGED", "APP_SWITCH_TO_BACKGROUND", "AR_STATUS_BROADCAST", "CLOSE_MAINACTIVITY", "EXIT_WITHOUT_RESTARTING", "GONEMASK", "INTENT_ACTION_PRESENTATION_TEST", "KILL_MAINACTIVITY", "SHOW_BACK_DOOR", "TAG", "TIMEZONE_CHANGED", "WIDGET_GONE_MASK", "instance", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver;", "getInstance", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MotsBroadcastReceiver getInstance() {
            if (MotsBroadcastReceiver.instance == null) {
                MotsBroadcastReceiver.instance = new MotsBroadcastReceiver();
            }
            return MotsBroadcastReceiver.instance;
        }
    }

    public final void setMotsBroadcastCallBack(MotsBroadcastCallBack motsBroadcastCallBack) {
        Log.i(TAG, "[setMotsBroadcastCallBack] --> " + motsBroadcastCallBack);
        this.callback = motsBroadcastCallBack;
        if (!this.goneMaskStatus || motsBroadcastCallBack == null) {
            return;
        }
        motsBroadcastCallBack.goneMask();
    }

    public final void setMotsMaskBroadcastCallBack(MotsMaskBroadcastCallBack motsMaskBroadcastCallBack) {
        Intrinsics.checkNotNullParameter(motsMaskBroadcastCallBack, "motsMaskBroadcastCallBack");
        this.maskCallback = motsMaskBroadcastCallBack;
        if (this.goneWidgetMaskStatus && motsMaskBroadcastCallBack != null) {
            motsMaskBroadcastCallBack.goneMask();
        }
        MotsMaskBroadcastCallBack motsMaskBroadcastCallBack2 = this.maskCallback;
        if (motsMaskBroadcastCallBack2 != null) {
            motsMaskBroadcastCallBack2.blRunningStatus(this.blRunningStatus);
        }
    }

    public final void setBlRunningStatus(boolean z) {
        this.blRunningStatus = z;
        MotsMaskBroadcastCallBack motsMaskBroadcastCallBack = this.maskCallback;
        if (motsMaskBroadcastCallBack != null) {
            motsMaskBroadcastCallBack.blRunningStatus(z);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        MotsBroadcastCallBack motsBroadcastCallBack;
        MotsBroadcastCallBack motsBroadcastCallBack2;
        MotsBroadcastCallBack motsBroadcastCallBack3;
        MotsBroadcastCallBack motsBroadcastCallBack4;
        MotsBroadcastCallBack motsBroadcastCallBack5;
        MotsMaskBroadcastCallBack motsMaskBroadcastCallBack;
        MotsBroadcastCallBack motsBroadcastCallBack6;
        MotsBroadcastCallBack motsBroadcastCallBack7;
        MotsBroadcastCallBack motsBroadcastCallBack8;
        MotsBroadcastCallBack motsBroadcastCallBack9;
        MotsBroadcastCallBack motsBroadcastCallBack10;
        MotsBroadcastCallBack motsBroadcastCallBack11;
        MotsBroadcastCallBack motsBroadcastCallBack12;
        Log.i(TAG, "action:" + (intent != null ? intent.getAction() : null));
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.closeMainActivity", false, 2, null) && (motsBroadcastCallBack12 = this.callback) != null) {
            motsBroadcastCallBack12.closeActivity();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.exitWithoutRestarting", false, 2, null)) {
            if (intent != null && intent.hasExtra("closePresentation")) {
                boolean booleanExtra = intent.getBooleanExtra("closePresentation", false);
                Log.i(TAG, "closePresentation == " + booleanExtra);
                if (booleanExtra && (motsBroadcastCallBack11 = this.callback) != null) {
                    motsBroadcastCallBack11.closePresentation();
                }
            }
            MotsBroadcastCallBack motsBroadcastCallBack13 = this.callback;
            if (motsBroadcastCallBack13 != null) {
                motsBroadcastCallBack13.exitWithoutRestarting();
            }
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.closeAndKillMainActivity", false, 2, null) && (motsBroadcastCallBack10 = this.callback) != null) {
            motsBroadcastCallBack10.killActivity();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.showBackDoor", false, 2, null) && (motsBroadcastCallBack9 = this.callback) != null) {
            motsBroadcastCallBack9.showBackDoor();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, TIMEZONE_CHANGED, false, 2, null) && (motsBroadcastCallBack8 = this.callback) != null) {
            motsBroadcastCallBack8.timeZoneChanged();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, ACTION_TIME_CHANGED, false, 2, null) && (motsBroadcastCallBack7 = this.callback) != null) {
            motsBroadcastCallBack7.timeChanged();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, ACTION_DATE_CHANGED, false, 2, null) && (motsBroadcastCallBack6 = this.callback) != null) {
            motsBroadcastCallBack6.timeChanged();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.mask.gone", false, 2, null)) {
            this.goneMaskStatus = true;
            MotsBroadcastCallBack motsBroadcastCallBack14 = this.callback;
            if (motsBroadcastCallBack14 != null) {
                motsBroadcastCallBack14.goneMask();
            }
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.widget.mask.gone", false, 2, null) && (motsMaskBroadcastCallBack = this.maskCallback) != null) {
            motsMaskBroadcastCallBack.goneMask();
        }
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, AR_STATUS_BROADCAST, false, 2, null)) {
            Boolean boolValueOf = intent != null ? Boolean.valueOf(intent.getBooleanExtra("status", false)) : null;
            if (boolValueOf != null && (motsBroadcastCallBack5 = this.callback) != null) {
                motsBroadcastCallBack5.ARStatus(boolValueOf.booleanValue());
            }
        }
        if (Intrinsics.areEqual(ACTION_PROTON_X11_MOBILE_POSITIONING, intent != null ? intent.getAction() : null)) {
            String stringExtra = intent.getStringExtra("onLocationChanged");
            String stringExtra2 = intent.getStringExtra("lastKnownLocation");
            MotsBroadcastCallBack motsBroadcastCallBack15 = this.callback;
            if (motsBroadcastCallBack15 != null) {
                motsBroadcastCallBack15.showMessage("onLocationChanged: " + stringExtra + " \n lastKnownLocation: " + stringExtra2);
            }
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.mots.appSwitchToBackground") && (motsBroadcastCallBack4 = this.callback) != null) {
            motsBroadcastCallBack4.appSwitchToBackground();
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_CLICK_WEB_VIEW_PRIVACY_DETAILS) && (motsBroadcastCallBack3 = this.callback) != null) {
            motsBroadcastCallBack3.webViewPrivacyDetails();
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, IMapPresentationComponent.INTENT_ACTION_ACTIVATION_PRIVACY_POLICY_STATUS_CHANGED)) {
            boolean booleanExtra2 = intent.getBooleanExtra("activationStatus", false);
            boolean booleanExtra3 = intent.getBooleanExtra("privacyPolicyStatus", false);
            Log.i(TAG, "activationStatus == " + booleanExtra2 + ", privacyPolicyStatus == " + booleanExtra3);
            MotsBroadcastCallBack motsBroadcastCallBack16 = this.callback;
            if (motsBroadcastCallBack16 != null) {
                motsBroadcastCallBack16.activationPrivacyPolicyStatusChanged(booleanExtra2, booleanExtra3);
            }
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_HMI_PAGE_FULL_CHANGED)) {
            boolean booleanExtra4 = intent.getBooleanExtra(ACTION_PRESENTATION_VALUE, true);
            Log.i(TAG, "isNaviHMIFullScreenPage == " + booleanExtra4);
            MotsBroadcastCallBack motsBroadcastCallBack17 = this.callback;
            if (motsBroadcastCallBack17 != null) {
                motsBroadcastCallBack17.naviHMIFullScreenPageChanged(booleanExtra4);
            }
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_HMI_REQUEST_FOCUS) && (motsBroadcastCallBack2 = this.callback) != null) {
            motsBroadcastCallBack2.hmiRequestFocus();
        }
        if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, INTENT_ACTION_PRESENTATION_TEST) || (motsBroadcastCallBack = this.callback) == null) {
            return;
        }
        motsBroadcastCallBack.presentationTest(intent.getIntExtra("type", -1), intent.getIntExtra(ACTION_PRESENTATION_VALUE, -1));
    }
}
