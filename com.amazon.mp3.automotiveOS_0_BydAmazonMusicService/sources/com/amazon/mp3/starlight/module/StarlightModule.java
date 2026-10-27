package com.amazon.mp3.starlight.module;

import android.content.Context;
import com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequester;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequesterImpl;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StarlightModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\bH\u0007J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/starlight/module/StarlightModule;", "", "()V", "getAudioVideoUseCase", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "context", "Landroid/content/Context;", "hybridQueueStateProvider", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "hybridQueueTrackRequester", "Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;", "getHybridQueueStateProvider", "getHybridQueueTrackRequester", "getVideoPlayerStateProvider", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StarlightModule {
    public final HybridQueueStateProvider getHybridQueueStateProvider() {
        return new HybridQueueStateProvider(null, null, null, null, 15, null);
    }

    public final VideoPlayerStateProvider getVideoPlayerStateProvider() {
        return new VideoPlayerStateProvider(false, 1, null);
    }

    public final HybridQueueTrackRequester getHybridQueueTrackRequester(Context context, HybridQueueStateProvider hybridQueueStateProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hybridQueueStateProvider, "hybridQueueStateProvider");
        return new HybridQueueTrackRequesterImpl(context, hybridQueueStateProvider);
    }

    public final AudioVideoToggleUseCase getAudioVideoUseCase(Context context, HybridQueueStateProvider hybridQueueStateProvider, HybridQueueTrackRequester hybridQueueTrackRequester) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hybridQueueStateProvider, "hybridQueueStateProvider");
        Intrinsics.checkNotNullParameter(hybridQueueTrackRequester, "hybridQueueTrackRequester");
        return new AudioVideoToggleInteractor(context, hybridQueueStateProvider, hybridQueueTrackRequester);
    }
}
