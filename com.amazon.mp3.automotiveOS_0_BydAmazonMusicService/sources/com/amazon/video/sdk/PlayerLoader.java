package com.amazon.video.sdk;

import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerLoader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/PlayerLoader;", "", "()V", "executor", "Lcom/google/common/util/concurrent/ListeningScheduledExecutorService;", "kotlin.jvm.PlatformType", "state", "Lcom/amazon/video/sdk/PlayerLoaderState;", "getSdk", "Lcom/amazon/video/sdk/ObservableFuture;", "Lcom/amazon/video/sdk/PlayerSdk;", "config", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerLoader {
    public static final PlayerLoader INSTANCE;
    private static final ListeningScheduledExecutorService executor;
    private static final PlayerLoaderState state;

    private PlayerLoader() {
    }

    static {
        PlayerLoader playerLoader = new PlayerLoader();
        INSTANCE = playerLoader;
        executor = MoreExecutors.listeningDecorator((ScheduledExecutorService) ScheduledExecutorBuilder.newBuilderFor(playerLoader, new String[0]).withFixedThreadPoolSize(1).build());
        state = new PlayerLoaderState();
    }

    public final ObservableFuture<PlayerSdk> getSdk(PlayerSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        DLog.logf("PlayerLoader.getSDK(%s)", config);
        Future futureSubmit = executor.submit((Callable) state.invoke(config));
        Intrinsics.checkNotNullExpressionValue(futureSubmit, "executor.submit(state.invoke(config))");
        return new ListenableFutureWrappingObservableFuture((ListenableFuture) futureSubmit);
    }
}
