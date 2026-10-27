package com.amazon.mp3.auto.waze;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.media.auto.AllowedMediaBrowserCaller;
import com.waze.sdk.WazeAudioSdk;
import com.waze.sdk.WazeAudioSdkSettings;
import com.waze.sdk.WazeSdkCallback;
import com.waze.sdk.WazeSdkConstants$WazeInstructions;
import com.waze.sdk.WazeSdkUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WazeSdkConnection.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\fJ\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u0017H\u0016J\u0012\u0010\u001b\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0012\u0010\u001f\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u000e\u0010!\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nR\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/mp3/auto/waze/WazeSdkConnection;", "Lcom/waze/sdk/WazeSdkCallback;", "Lcom/waze/sdk/WazeAudioSdk$NavigationListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "wazeAudioSdk", "Lcom/waze/sdk/WazeAudioSdk;", "wazeNavigationStatusListener", "Lcom/amazon/mp3/auto/waze/WazeNavigationStatusListener;", "connectToWazeIfNeeded", "", "context", "Landroid/content/Context;", "redirectToWaze", "", "disconnectFromWaze", "initializeWazeSdk", "isWazeSdkConnected", "onConnected", "onDisconnected", "reason", "", "onInstructionDistanceUpdated", "p0", "p1", "onInstructionUpdated", "Lcom/waze/sdk/WazeSdkConstants$WazeInstructions;", "onNavigationStatusChanged", "onRoundaboutExitUpdated", "onStreetNameChanged", "onTrafficSideUpdated", "setWazeNavigationStatusListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WazeSdkConnection implements WazeSdkCallback, WazeAudioSdk.NavigationListener {
    public static final WazeSdkConnection INSTANCE = new WazeSdkConnection();
    private static final String TAG = WazePlaybackService.class.getSimpleName();
    private static WazeAudioSdk wazeAudioSdk;
    private static WazeNavigationStatusListener wazeNavigationStatusListener;

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onInstructionDistanceUpdated(String p0, int p1) {
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onInstructionUpdated(WazeSdkConstants$WazeInstructions p0) {
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onRoundaboutExitUpdated(int p0) {
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onStreetNameChanged(String p0) {
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onTrafficSideUpdated(boolean p0) {
    }

    private WazeSdkConnection() {
    }

    @Override // com.waze.sdk.WazeSdkCallback
    public void onConnected() {
        Log.debug(TAG, "Waze SDK connected.");
    }

    @Override // com.waze.sdk.WazeSdkCallback
    public void onDisconnected(int reason) {
        wazeAudioSdk = null;
        Log.debug(TAG, Intrinsics.stringPlus("Waze SDK disconnected: ", Integer.valueOf(reason)));
    }

    public final void connectToWazeIfNeeded(Context context, boolean redirectToWaze) {
        boolean zIsWazeIntegrationEnabled;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            zIsWazeIntegrationEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isWazeIntegrationEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, Intrinsics.stringPlus("Did not access feature flag WAZE_INTEGRATION successfully with error message: ", e.getMessage()));
            zIsWazeIntegrationEnabled = false;
        }
        WazeAudioSdk wazeAudioSdk2 = wazeAudioSdk;
        if (wazeAudioSdk2 == null && zIsWazeIntegrationEnabled) {
            initializeWazeSdk(context, redirectToWaze);
        } else {
            if (!zIsWazeIntegrationEnabled || wazeAudioSdk2 == null || wazeAudioSdk2.isConnected()) {
                return;
            }
            INSTANCE.initializeWazeSdk(context, redirectToWaze);
        }
    }

    private final void initializeWazeSdk(Context context, boolean redirectToWaze) {
        Resources resources;
        int i;
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            resources = context.getResources();
            i = R.color.cyan_accent;
        } else {
            resources = context.getResources();
            i = R.color.bauhaus_accent;
        }
        int color = resources.getColor(i);
        if (WazeAudioSdk.isWazeVersionSupported(context)) {
            WazeAudioSdk wazeAudioSdkInit = WazeAudioSdk.init(context, ((WazeAudioSdkSettings.Builder) ((WazeAudioSdkSettings.Builder) new WazeAudioSdkSettings.Builder().setOpenMeIntent(PendingIntentUtil.getActivity$default(PendingIntentUtil.INSTANCE, context, 0, new Intent(context, (Class<?>) MusicHomeActivity.class), 134217728, false, 16, null))).setThemeColor(color)).build(), this);
            wazeAudioSdk = wazeAudioSdkInit;
            if (wazeAudioSdkInit != null) {
                wazeAudioSdkInit.setNavigationListener(this);
            }
        }
        if (!redirectToWaze || !WazeAudioSdk.isWazeVersionSupported(context)) {
            if (redirectToWaze) {
                WazeSdkUtils.openWazeInPlayStore(context);
            }
        } else {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(AllowedMediaBrowserCaller.WAZE.getPackageName());
            if (launchIntentForPackage != null) {
                context.startActivity(launchIntentForPackage);
            }
        }
    }

    public final void disconnectFromWaze() {
        WazeAudioSdk wazeAudioSdk2 = wazeAudioSdk;
        if (wazeAudioSdk2 == null) {
            return;
        }
        wazeAudioSdk2.disconnect();
    }

    public final boolean isWazeSdkConnected() {
        WazeAudioSdk wazeAudioSdk2 = wazeAudioSdk;
        if (wazeAudioSdk2 == null) {
            return false;
        }
        return wazeAudioSdk2.isConnected();
    }

    public final void setWazeNavigationStatusListener(WazeNavigationStatusListener wazeNavigationStatusListener2) {
        Intrinsics.checkNotNullParameter(wazeNavigationStatusListener2, "wazeNavigationStatusListener");
        wazeNavigationStatusListener = wazeNavigationStatusListener2;
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onNavigationStatusChanged(boolean p0) {
        WazeNavigationStatusListener wazeNavigationStatusListener2 = wazeNavigationStatusListener;
        if (wazeNavigationStatusListener2 != null) {
            if (wazeNavigationStatusListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wazeNavigationStatusListener");
                wazeNavigationStatusListener2 = null;
            }
            wazeNavigationStatusListener2.onNavigationStatusChanged(p0);
        }
    }
}
