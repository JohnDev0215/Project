package com.amazon.mp3.download;

import android.content.Context;
import com.amazon.digitalmusicplayback.DownloadCoordinator;
import com.amazon.digitalmusicplayback.DownloadCoordinatorConfig;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.harley.DownloadCoordinatorConfigConverter;
import com.amazon.mp3.playback.harley.HarleyDownloadCoordinatorListenerDispatcher;
import com.amazon.mp3.playback.harley.HarleyDownloadProperties;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadCoordinatorProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b!\u0010\"J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0002H\u0002J\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u0010\u001a\u00020\u000fR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010 \u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001c¨\u0006#"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorProvider;", "", "Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "getDownloadCoordinator", "initDownloadCoordinator", "", "logStorageMountedState", "logStoragePermissionsState", "Lcom/amazon/mp3/playback/harley/HarleyDownloadProperties;", "downloadProperties", "Lcom/amazon/digitalmusicplayback/DownloadCoordinator;", "getHarleyDownloadCoordinator", "downloadCoordinatorWrapperInterface", "initHarleyDownloadCoordinatorSettings", "resetDownloadCoordinator", "", "isInitialized", "", "TAG", "Ljava/lang/String;", "isInitializing", "Z", "()Z", "setInitializing", "(Z)V", "initializedDownloadCoordinatorWrapper", "Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "getInitializedDownloadCoordinatorWrapper", "()Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "setInitializedDownloadCoordinatorWrapper", "(Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;)V", "getDownloadCoordinatorWrapper", "downloadCoordinatorWrapper", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadCoordinatorProvider {
    public static final DownloadCoordinatorProvider INSTANCE = new DownloadCoordinatorProvider();
    private static final String TAG;
    private static volatile DownloadCoordinatorWrapperInterface initializedDownloadCoordinatorWrapper;
    private static boolean isInitializing;

    private DownloadCoordinatorProvider() {
    }

    static {
        String simpleName = DownloadCoordinatorProvider.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "DownloadCoordinatorProvider::class.java.simpleName");
        TAG = simpleName;
        initializedDownloadCoordinatorWrapper = NoOpDownloadCoordinatorWrapper.INSTANCE;
    }

    public final boolean isInitializing() {
        return isInitializing;
    }

    public final DownloadCoordinatorWrapperInterface getDownloadCoordinatorWrapper() {
        return getDownloadCoordinator();
    }

    public final DownloadCoordinatorWrapperInterface getInitializedDownloadCoordinatorWrapper() {
        return initializedDownloadCoordinatorWrapper;
    }

    public final void setInitializedDownloadCoordinatorWrapper(DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
        Intrinsics.checkNotNullParameter(downloadCoordinatorWrapperInterface, "<set-?>");
        initializedDownloadCoordinatorWrapper = downloadCoordinatorWrapperInterface;
    }

    private final DownloadCoordinatorWrapperInterface getDownloadCoordinator() {
        if (initializedDownloadCoordinatorWrapper instanceof NoOpDownloadCoordinatorWrapper) {
            synchronized (this) {
                DownloadCoordinatorProvider downloadCoordinatorProvider = INSTANCE;
                if (downloadCoordinatorProvider.getInitializedDownloadCoordinatorWrapper() instanceof NoOpDownloadCoordinatorWrapper) {
                    downloadCoordinatorProvider.setInitializedDownloadCoordinatorWrapper(downloadCoordinatorProvider.initDownloadCoordinator());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return initializedDownloadCoordinatorWrapper;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final DownloadCoordinatorWrapperInterface initDownloadCoordinator() {
        isInitializing = true;
        logStoragePermissionsState();
        logStorageMountedState();
        HarleyDownloadProperties harleyDownloadProperties = new HarleyDownloadProperties(0, 0, null, 7, null);
        DownloadCoordinator harleyDownloadCoordinator = getHarleyDownloadCoordinator(harleyDownloadProperties);
        if (harleyDownloadCoordinator == null) {
            String str = TAG;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            Log.error(str, Intrinsics.stringPlus("Failed to get Download Coordinator from Harley engine. ", stackTrace != null ? ArraysKt___ArraysKt.joinToString$default(stackTrace, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) : null));
        } else {
            String str2 = TAG;
            StackTraceElement[] stackTrace2 = Thread.currentThread().getStackTrace();
            Log.warning(str2, Intrinsics.stringPlus("Successfully initialized DownloadCoordinator from Harley. ", stackTrace2 != null ? ArraysKt___ArraysKt.joinToString$default(stackTrace2, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) : 0));
            initializedDownloadCoordinatorWrapper = new DownloadCoordinatorWrapper(harleyDownloadCoordinator, harleyDownloadProperties);
        }
        if (!(initializedDownloadCoordinatorWrapper instanceof NoOpDownloadCoordinatorWrapper)) {
            initHarleyDownloadCoordinatorSettings(initializedDownloadCoordinatorWrapper);
        }
        isInitializing = false;
        return initializedDownloadCoordinatorWrapper;
    }

    private final void logStorageMountedState() {
        boolean zSecondaryStoragePresent = AmazonApplication.getCapabilities().secondaryStoragePresent(AmazonApplication.getContext());
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Secondary Storage Present State = [");
        sb.append(zSecondaryStoragePresent ? "MOUNTED" : "UNMOUNTED");
        sb.append(']');
        Log.warning(str, sb.toString());
    }

    private final void logStoragePermissionsState() {
        Context context = AmazonApplication.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        boolean zIsPermissionGranted = ContextKt.isPermissionGranted(context, "android.permission.READ_EXTERNAL_STORAGE");
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Read External Storage Permission state = [");
        sb.append(zIsPermissionGranted ? "GRANTED" : "DENIED");
        sb.append(']');
        Log.warning(str, sb.toString());
        Context context2 = AmazonApplication.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext()");
        boolean zIsPermissionGranted2 = ContextKt.isPermissionGranted(context2, "android.permission.WRITE_EXTERNAL_STORAGE");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Write External Storage Permission State = [");
        sb2.append(zIsPermissionGranted2 ? "GRANTED" : "DENIED");
        sb2.append(']');
        Log.warning(str, sb2.toString());
    }

    private final DownloadCoordinator getHarleyDownloadCoordinator(HarleyDownloadProperties downloadProperties) {
        DownloadCoordinatorConfig configFromDownloadProperties = DownloadCoordinatorConfigConverter.INSTANCE.getConfigFromDownloadProperties(downloadProperties);
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        Player localPlayer = playbackConfig.getLocalPlayer(childUserUtil.getMusicRequestIdentityContextToken(applicationContext));
        if (!(localPlayer instanceof HarleyPlayer)) {
            return null;
        }
        String str = TAG;
        Log.warning(str, Intrinsics.stringPlus("Trying to initialize DownloadCoordinator with properties ", downloadProperties));
        HarleyPlayer harleyPlayer = (HarleyPlayer) localPlayer;
        DownloadCoordinator downloadCoordinator = harleyPlayer.getDownloadCoordinator(HarleyDownloadCoordinatorListenerDispatcher.INSTANCE, configFromDownloadProperties);
        if (downloadCoordinator != null) {
            return downloadCoordinator;
        }
        Log.info(str, Intrinsics.stringPlus("Failed to get DownloadCoordinator from Harley Player . Retrying. ", Arrays.toString(Thread.currentThread().getStackTrace())));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException unused) {
        }
        return harleyPlayer.getDownloadCoordinator(HarleyDownloadCoordinatorListenerDispatcher.INSTANCE, configFromDownloadProperties);
    }

    private final void initHarleyDownloadCoordinatorSettings(DownloadCoordinatorWrapperInterface downloadCoordinatorWrapperInterface) {
        downloadCoordinatorWrapperInterface.setDownloadOnWifiOnly(SettingsUtil.getBooleanPref(AmazonApplication.getContext(), R.string.setting_key_downloads_wifi_only_pref));
    }

    public final void resetDownloadCoordinator() {
        synchronized (this) {
            Log.warning(TAG, "Resetting download coordinator to NoOp so a new instance can be initialized.");
            INSTANCE.setInitializedDownloadCoordinatorWrapper(NoOpDownloadCoordinatorWrapper.INSTANCE);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean isInitialized() {
        return !(initializedDownloadCoordinatorWrapper instanceof NoOpDownloadCoordinatorWrapper);
    }
}
