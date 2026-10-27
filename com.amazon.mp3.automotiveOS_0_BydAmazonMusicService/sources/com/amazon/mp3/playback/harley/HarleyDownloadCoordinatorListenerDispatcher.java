package com.amazon.mp3.playback.harley;

import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.DownloadCoordinatorProvider;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.util.Log;
import com.concurrent.RateLimiter;
import com.concurrent.TokenBucketRateLimiter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyDownloadCoordinatorListenerDispatcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001eH\u0016J\u000e\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u0001J\u000e\u0010#\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyDownloadCoordinatorListenerDispatcher;", "Lcom/amazon/digitalmusicplayback/DownloadCoordinatorListener;", "()V", "listeners", "", "mTAG", "", "progressUpdateLimiter", "Lcom/concurrent/RateLimiter;", "backgroundDownloadSessionIdentifierSet", "", "id", "backgroundDownloadsDidFinish", "configUpdated", "engineError", "Lcom/amazon/digitalmusicplayback/EngineError;", "downloadComplete", "ID", "location", "downloadPaused", "downloadRemoved", "downloadResumed", "downloadsStalled", "stalled", "", "invalidDownloadDetected", "mergeCompleted", "from", "mergeUpdate", NotificationCompat.CATEGORY_PROGRESS, "", "progressUpdate", "percent", "registerListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "unregisterListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HarleyDownloadCoordinatorListenerDispatcher extends DownloadCoordinatorListener {
    public static final HarleyDownloadCoordinatorListenerDispatcher INSTANCE = new HarleyDownloadCoordinatorListenerDispatcher();
    private static Set<DownloadCoordinatorListener> listeners;
    private static final String mTAG;
    private static final RateLimiter progressUpdateLimiter;

    /* JADX INFO: compiled from: HarleyDownloadCoordinatorListenerDispatcher.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorCode.values().length];
            iArr[ErrorCode.NOERROR.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void backgroundDownloadSessionIdentifierSet(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void backgroundDownloadsDidFinish() {
    }

    private HarleyDownloadCoordinatorListenerDispatcher() {
    }

    static {
        String simpleName = HarleyDownloadCoordinatorListenerDispatcher.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "HarleyDownloadCoordinato…er::class.java.simpleName");
        mTAG = simpleName;
        progressUpdateLimiter = new TokenBucketRateLimiter(0L, 0L, 1L, TimeUnit.SECONDS, null, 19, null);
        Set<DownloadCoordinatorListener> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkNotNullExpressionValue(setNewSetFromMap, "newSetFromMap(Concurrent…atorListener, Boolean>())");
        listeners = setNewSetFromMap;
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void configUpdated(EngineError engineError) {
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        ErrorCode code = engineError.getCode();
        if ((code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()]) == 1) {
            Log.warning(mTAG, "updateConfig for Download Coordinator Config was successful.");
        } else {
            Log.error(mTAG, "updateConfig for Download Coordinator Config failed " + engineError + '.');
        }
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().configUpdated(engineError);
        }
    }

    public final void unregisterListener(DownloadCoordinatorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.remove(listener);
    }

    public final void registerListener(DownloadCoordinatorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.add(listener);
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadComplete(String ID, EngineError engineError, String location) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Intrinsics.checkNotNullParameter(location, "location");
        if (engineError.getCode() == ErrorCode.NOERROR) {
            Log.info(mTAG, "Download complete for item ID " + ID + ", error " + engineError + ", location " + location);
        } else {
            DownloadProperties currentDownloadProperties = DownloadCoordinatorProvider.INSTANCE.getDownloadCoordinatorWrapper().getCurrentDownloadProperties();
            Boolean boolIsDownloadOnWifiOnly = MusicDownloader.getInstance(AmazonApplication.getContext()).isDownloadOnWifiOnly();
            Intrinsics.checkNotNullExpressionValue(boolIsDownloadOnWifiOnly, "getInstance(AmazonApplic…t()).isDownloadOnWifiOnly");
            boolean zBooleanValue = boolIsDownloadOnWifiOnly.booleanValue();
            Log.error(mTAG, "Download error occurred: item ID " + ID + ", error " + engineError + ", location " + location + "\nCurrent DownloadCoordinator Properties: " + currentDownloadProperties + "\nisDownloadOnWifiOnly " + zBooleanValue);
        }
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().downloadComplete(ID, engineError, location);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void progressUpdate(String ID, float percent) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().progressUpdate(ID, percent);
        }
        if (RateLimiter.DefaultImpls.tryConsume$default(progressUpdateLimiter, 0, 1, null)) {
            Log.debug(mTAG, "Download progress update for ID " + ID + " percent " + percent + '.');
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadRemoved(String ID, EngineError engineError) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Log.info(mTAG, "Download removed for item ID " + ID + ", error " + engineError);
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().downloadRemoved(ID, engineError);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadPaused(String ID, EngineError engineError) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Log.info(mTAG, "Download paused for item ID " + ID + ", error " + engineError);
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().downloadPaused(ID, engineError);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadsStalled(boolean stalled, EngineError engineError) {
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        if (stalled) {
            Log.info(mTAG, Intrinsics.stringPlus("Stalling downloads due to download-wifi-preference policy, error ", engineError));
        } else {
            Log.info(mTAG, Intrinsics.stringPlus("Resuming stalled downloads due to download-wifi-preference policy, error ", engineError));
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void downloadResumed(String ID, EngineError engineError) {
        Intrinsics.checkNotNullParameter(ID, "ID");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Log.info(mTAG, "Download resumed for item ID " + ID + ", error " + engineError);
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().downloadResumed(ID, engineError);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void mergeCompleted(String from, EngineError engineError) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Log.info(mTAG, "Merge from " + from + " completed with engine error " + engineError);
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().mergeCompleted(from, engineError);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void mergeUpdate(String from, float progress) {
        Intrinsics.checkNotNullParameter(from, "from");
        Log.info(mTAG, "Merge from " + from + ", progress: %" + (100 * progress) + '.');
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().mergeUpdate(from, progress);
        }
    }

    @Override // com.amazon.digitalmusicplayback.DownloadCoordinatorListener
    public void invalidDownloadDetected(String id, EngineError engineError) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(engineError, "engineError");
        Log.info(mTAG, "invalidDownloadDetected for item ID " + id + ", error " + engineError);
        Iterator<DownloadCoordinatorListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().invalidDownloadDetected(id, engineError);
        }
    }
}
