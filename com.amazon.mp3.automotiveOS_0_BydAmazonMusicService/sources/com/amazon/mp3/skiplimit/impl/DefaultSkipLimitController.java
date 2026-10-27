package com.amazon.mp3.skiplimit.impl;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.skiplimit.ErrorType;
import com.amazon.mp3.skiplimit.SkipLimitController;
import com.amazon.mp3.skiplimit.SkipLimitRefreshSource;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultSkipLimitController.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\u0012\u0010 \u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u0006H\u0016J\u0018\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u0014H\u0002J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R(\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006+"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/DefaultSkipLimitController;", "Lcom/amazon/mp3/skiplimit/SkipLimitController;", "skipLimitProvider", "Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;", "(Lcom/amazon/music/media/playback/skiplimit/SkipLimitProvider;)V", "TAG", "", "kotlin.jvm.PlatformType", "connectionConfig", "Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "getConnectionConfig", "()Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "setConnectionConfig", "(Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;)V", "currentThingShadowName", "getCurrentThingShadowName", "()Ljava/lang/String;", "setCurrentThingShadowName", "(Ljava/lang/String;)V", "<set-?>", "Lcom/amazon/mp3/skiplimit/SkipLimitRefreshSource;", "currentlyActiveSource", "getCurrentlyActiveSource", "()Lcom/amazon/mp3/skiplimit/SkipLimitRefreshSource;", "setCurrentlyActiveSource", "(Lcom/amazon/mp3/skiplimit/SkipLimitRefreshSource;)V", "clearSession", "", "onQueueStarted", "context", "Landroid/content/Context;", "onQueueTerminate", "onReceivedIotConfig", "config", "onReceivedThingShadowName", "name", "onRefreshError", "errorType", "Lcom/amazon/mp3/skiplimit/ErrorType;", "switch", "newSource", "switchToHardcoded", "switchToIoT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultSkipLimitController extends SkipLimitController {
    private final String TAG;
    private ConnectionInfoMetadata connectionConfig;
    private String currentThingShadowName;
    private SkipLimitRefreshSource currentlyActiveSource;

    /* JADX INFO: compiled from: DefaultSkipLimitController.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorType.values().length];
            iArr[ErrorType.IoT.ordinal()] = 1;
            iArr[ErrorType.HARD_CODED.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSkipLimitController(SkipLimitProvider skipLimitProvider) {
        super(skipLimitProvider);
        Intrinsics.checkNotNullParameter(skipLimitProvider, "skipLimitProvider");
        this.TAG = DefaultSkipLimitController.class.getSimpleName();
    }

    public final SkipLimitRefreshSource getCurrentlyActiveSource() {
        return this.currentlyActiveSource;
    }

    @VisibleForTesting
    public final void setCurrentlyActiveSource(SkipLimitRefreshSource skipLimitRefreshSource) {
        this.currentlyActiveSource = skipLimitRefreshSource;
    }

    public final String getCurrentThingShadowName() {
        return this.currentThingShadowName;
    }

    public final void setCurrentThingShadowName(String str) {
        this.currentThingShadowName = str;
    }

    public final ConnectionInfoMetadata getConnectionConfig() {
        return this.connectionConfig;
    }

    public final void setConnectionConfig(ConnectionInfoMetadata connectionInfoMetadata) {
        this.connectionConfig = connectionInfoMetadata;
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitController
    public void onQueueStarted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (AmazonApplication.getCapabilities().isIoTSkipLimitSupported()) {
            switchToIoT(context);
        } else {
            m1630switch(new HardCodedRefreshStrategy(this, context));
        }
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitController
    public void onQueueTerminate() {
        synchronized (this) {
            SkipLimitRefreshSource currentlyActiveSource = getCurrentlyActiveSource();
            if (currentlyActiveSource != null) {
                currentlyActiveSource.terminate();
            }
            setCurrentlyActiveSource(null);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitController
    public void onReceivedThingShadowName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.currentThingShadowName = name;
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitController
    public void onReceivedIotConfig(ConnectionInfoMetadata config) {
        this.connectionConfig = config;
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitController
    public void onRefreshError(ErrorType errorType, Context context) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(context, "context");
        int i = WhenMappings.$EnumSwitchMapping$0[errorType.ordinal()];
        if (i == 1) {
            switchToHardcoded(context);
        } else {
            if (i != 2) {
                return;
            }
            Log.error(this.TAG, "Hard coded strategy failed.");
        }
    }

    private final void switchToIoT(Context context) {
        Unit unit;
        Log.info(this.TAG, "Starting IoT strategy to refresh skip limit.");
        ConnectionInfoMetadata connectionInfoMetadata = this.connectionConfig;
        if (connectionInfoMetadata == null) {
            unit = null;
        } else {
            m1630switch(new IoTRefreshStrategy(this, context, connectionInfoMetadata, getCurrentThingShadowName()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            switchToHardcoded(context);
        }
    }

    private final void switchToHardcoded(Context context) {
        Log.info(this.TAG, "Starting hard coded strategy to refresh skip limit.");
        m1630switch(new HardCodedRefreshStrategy(this, context));
    }

    /* JADX INFO: renamed from: switch, reason: not valid java name */
    private final void m1630switch(SkipLimitRefreshSource newSource) {
        synchronized (this) {
            SkipLimitRefreshSource currentlyActiveSource = getCurrentlyActiveSource();
            if (currentlyActiveSource != null) {
                currentlyActiveSource.terminate();
            }
            setCurrentlyActiveSource(newSource);
            newSource.start();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearSession() {
        SkipLimitRefreshSource skipLimitRefreshSource = this.currentlyActiveSource;
        if (skipLimitRefreshSource == null) {
            return;
        }
        skipLimitRefreshSource.clearSession();
    }
}
