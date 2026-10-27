package com.amazon.mp3.skiplimit;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.CloudQueueFactory;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.skiplimit.impl.SkiplimitShadowMessenger;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipLimitController.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0015\u001a\u00020\u000fH&J\u0012\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0006H&J\u0018\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u001e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\"\u001a\u00020\u000fJ\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004¨\u0006%"}, d2 = {"Lcom/amazon/mp3/skiplimit/SkipLimitController;", "", "skipLimitProvider", "Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;", "(Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;)V", "LAST_LOCAL_SKIP_LIMIT_UPDATE_KEY", "", "TAG", "kotlin.jvm.PlatformType", "getSkipLimitProvider", "()Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;", "setSkipLimitProvider", "getLastLocalSkipLimitUpdate", "", "initIotSkipLimitSource", "", "context", "Landroid/content/Context;", "thingShadowName", "notifySkipLimitUpdate", "onQueueStarted", "onQueueTerminate", "onReceivedIotConfig", "config", "Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "onReceivedThingShadowName", "name", "onRefreshError", "errorType", "Lcom/amazon/mp3/skiplimit/ErrorType;", "onUpdateCount", "newCount", "", "updateReason", "onUserNext", "setLastLocalSkipLimitUpdate", "time", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SkipLimitController {
    private final String LAST_LOCAL_SKIP_LIMIT_UPDATE_KEY;
    private final String TAG;
    private SkipLimitProvider skipLimitProvider;

    public abstract void onQueueStarted(Context context);

    public abstract void onQueueTerminate();

    public abstract void onReceivedIotConfig(ConnectionInfoMetadata config);

    public abstract void onReceivedThingShadowName(String name);

    public abstract void onRefreshError(ErrorType errorType, Context context);

    public SkipLimitController(SkipLimitProvider skipLimitProvider) {
        Intrinsics.checkNotNullParameter(skipLimitProvider, "skipLimitProvider");
        this.skipLimitProvider = skipLimitProvider;
        this.TAG = SkipLimitController.class.getSimpleName();
        this.LAST_LOCAL_SKIP_LIMIT_UPDATE_KEY = "last_local_skip_limit_update";
    }

    public final SkipLimitProvider getSkipLimitProvider() {
        return this.skipLimitProvider;
    }

    public final void setSkipLimitProvider(SkipLimitProvider skipLimitProvider) {
        Intrinsics.checkNotNullParameter(skipLimitProvider, "<set-?>");
        this.skipLimitProvider = skipLimitProvider;
    }

    public final void onUserNext() {
        Log.info(this.TAG, "Decrementing skip count because user skipped.");
        setLastLocalSkipLimitUpdate(Calendar.getInstance().getTimeInMillis());
        this.skipLimitProvider.decrement();
    }

    public final void onUpdateCount(int newCount, String updateReason, Context context) {
        Intrinsics.checkNotNullParameter(updateReason, "updateReason");
        Intrinsics.checkNotNullParameter(context, "context");
        Log.info(this.TAG, "Updating skip count to " + newCount + " because " + updateReason + '.');
        setLastLocalSkipLimitUpdate(Calendar.getInstance().getTimeInMillis());
        this.skipLimitProvider.setCount(newCount >= 0 ? newCount : 0);
        if (newCount > 0) {
            Log.info(SkiplimitShadowMessenger.INSTANCE.getTAG(), "Remaining skip count is " + newCount + ". Notifying the player to update button config");
            notifySkipLimitUpdate(context);
        }
    }

    public final void initIotSkipLimitSource(Context context, String thingShadowName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(thingShadowName, "thingShadowName");
        onReceivedThingShadowName(thingShadowName);
        CloudQueueFactory.getInstance(context).getCloudQueueService().getConnectionInfoMetadata().subscribe(new Consumer() { // from class: com.amazon.mp3.skiplimit.SkipLimitController$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SkipLimitController.m1628initIotSkipLimitSource$lambda0(this.f$0, (ConnectionInfoMetadata) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.skiplimit.SkipLimitController$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SkipLimitController.m1629initIotSkipLimitSource$lambda1(this.f$0, (Throwable) obj);
            }
        });
        onQueueStarted(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initIotSkipLimitSource$lambda-0, reason: not valid java name */
    public static final void m1628initIotSkipLimitSource$lambda0(SkipLimitController this$0, ConnectionInfoMetadata connectionInfoMetadata) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onReceivedIotConfig(connectionInfoMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initIotSkipLimitSource$lambda-1, reason: not valid java name */
    public static final void m1629initIotSkipLimitSource$lambda1(SkipLimitController this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Error fetching connection info for skip limits: ", th);
    }

    public final long getLastLocalSkipLimitUpdate() {
        return AmazonApplication.getContext().getSharedPreferences(this.TAG, 0).getLong(this.LAST_LOCAL_SKIP_LIMIT_UPDATE_KEY, 0L);
    }

    private final void setLastLocalSkipLimitUpdate(long time) {
        AmazonApplication.getContext().getSharedPreferences(this.TAG, 0).edit().putLong(this.LAST_LOCAL_SKIP_LIMIT_UPDATE_KEY, time).apply();
    }

    private final void notifySkipLimitUpdate(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("IOT_SKIP_LIMIT_BROADCAST"));
    }
}
