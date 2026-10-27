package com.amazon.mp3.playback.fragment;

import androidx.fragment.app.FragmentActivity;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.SurfaceHandlingMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: VideoPlayerFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
final class VideoPlayerFragment$onPlaybackStarted$1 extends Lambda implements Function1<Unit, Unit> {
    final /* synthetic */ VideoPlayerFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoPlayerFragment$onPlaybackStarted$1(VideoPlayerFragment videoPlayerFragment) {
        super(1);
        this.this$0 = videoPlayerFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Unit unit) {
        invoke2(unit);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return;
        }
        final VideoPlayerFragment videoPlayerFragment = this.this$0;
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$onPlaybackStarted$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoPlayerFragment$onPlaybackStarted$1.m1465invoke$lambda0(videoPlayerFragment);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
    public static final void m1465invoke$lambda0(VideoPlayerFragment this$0) {
        RenderingConfig startRenderingConfig;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PrimeVideoPlayer primeVideoPlayer = this$0.videoPlayer;
        if (((primeVideoPlayer == null || (startRenderingConfig = primeVideoPlayer.getStartRenderingConfig()) == null) ? null : startRenderingConfig.getSurfaceHandlingMode()) == SurfaceHandlingMode.AUDIO_ONLY && this$0.isFragmentVisible) {
            this$0.initPlayer();
            this$0.videoPlayer.setStartRenderingConfig(null);
        }
        this$0.setupCaptions();
    }
}
