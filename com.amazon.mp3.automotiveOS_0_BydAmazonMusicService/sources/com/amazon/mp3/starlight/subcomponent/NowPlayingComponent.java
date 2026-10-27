package com.amazon.mp3.starlight.subcomponent;

import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.fragment.StageVideoPlayerFragment;
import com.amazon.mp3.playback.fragment.VideoPlayerFragment;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor;
import com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.StageOverlayMetricsReporter;
import com.amazon.mp3.view.stage.StageViewPresenter;
import kotlin.Metadata;

/* JADX INFO: compiled from: NowPlayingComponent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0001\u0019J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017H&¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/starlight/subcomponent/NowPlayingComponent;", "", "Lcom/amazon/mp3/playback/fragment/NowPlayingFragment;", "nowPlayingFragment", "", "inject", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleInteractor;", "audioVideoToggleInteractor", "Lcom/amazon/mp3/playback/harley/PlayQueueSequencer;", "playQueueSequencer", "Lcom/amazon/mp3/playback/fragment/VideoPlayerFragment;", "videoPlayerFragment", "Lcom/amazon/mp3/playback/fragment/StageVideoPlayerFragment;", "Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "cloudQueueSequencer", "Lcom/amazon/mp3/view/stage/StageViewPresenter;", "stageViewPresenter", "Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter;", "stageOverlayMetricsReporter", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "stageAutomatedSequencingHandler", "Lcom/amazon/mp3/view/lyrics/sharing/view/LyricsSelectionFragment;", "lyricsSelectionFragment", "Lcom/amazon/mp3/playback/view/lyrics/LyricsListViewContainer;", "lyricsListViewContainer", "Factory", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface NowPlayingComponent {

    /* JADX INFO: compiled from: NowPlayingComponent.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/starlight/subcomponent/NowPlayingComponent$Factory;", "", "create", "Lcom/amazon/mp3/starlight/subcomponent/NowPlayingComponent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Factory {
        NowPlayingComponent create();
    }

    void inject(CloudQueueSequencer cloudQueueSequencer);

    void inject(NowPlayingFragment nowPlayingFragment);

    void inject(StageVideoPlayerFragment videoPlayerFragment);

    void inject(VideoPlayerFragment videoPlayerFragment);

    void inject(PlayQueueSequencer playQueueSequencer);

    void inject(AudioVideoToggleInteractor audioVideoToggleInteractor);

    void inject(LyricsListViewContainer lyricsListViewContainer);

    void inject(LyricsSelectionFragment lyricsSelectionFragment);

    void inject(StageAutomatedSequencingHandler stageAutomatedSequencingHandler);

    void inject(StageOverlayMetricsReporter stageOverlayMetricsReporter);

    void inject(StageViewPresenter stageViewPresenter);
}
