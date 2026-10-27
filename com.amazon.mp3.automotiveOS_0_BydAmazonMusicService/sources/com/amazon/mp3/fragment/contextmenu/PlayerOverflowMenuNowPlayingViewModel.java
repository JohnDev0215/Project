package com.amazon.mp3.fragment.contextmenu;

import androidx.lifecycle.ViewModel;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlaybackController;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerOverflowMenuNowPlayingViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuNowPlayingViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "currentTrackSourceNameObservable", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "getCurrentTrackSourceNameObservable", "()Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "playbackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "getCurrentTrackSourceName", "()Ljava/lang/String;", "currentTrackSourceName", "<init>", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayerOverflowMenuNowPlayingViewModel extends ViewModel {
    private final BehaviorSubject<String> currentTrackSourceNameObservable;
    private final PlaybackController playbackController;
    private final OnPlaybackEventListener playbackEventListener;

    public PlayerOverflowMenuNowPlayingViewModel() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ PlayerOverflowMenuNowPlayingViewModel(PlaybackController playbackController, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI) : playbackController);
    }

    public PlayerOverflowMenuNowPlayingViewModel(PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
        BehaviorSubject<String> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(getCurrentTrackSourceName());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(currentTrackSourceName)");
        this.currentTrackSourceNameObservable = behaviorSubjectCreateDefault;
        OnPlaybackEventListener onPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuNowPlayingViewModel$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                PlayerOverflowMenuNowPlayingViewModel.m1138playbackEventListener$lambda0(this.f$0, i, controlSource);
            }
        };
        this.playbackEventListener = onPlaybackEventListener;
        playbackController.addOnPlaybackEventListener(onPlaybackEventListener);
    }

    public final BehaviorSubject<String> getCurrentTrackSourceNameObservable() {
        return this.currentTrackSourceNameObservable;
    }

    private final String getCurrentTrackSourceName() {
        if (this.playbackController.getCurrentMediaItem() == null) {
            return "";
        }
        MediaCollectionInfo sourceMediaCollectionInfo = this.playbackController.getCurrentMediaItem().getSourceMediaCollectionInfo();
        String name = sourceMediaCollectionInfo == null ? null : sourceMediaCollectionInfo.getName();
        if (name != null) {
            return name;
        }
        String albumName = this.playbackController.getCurrentMediaItem().getAlbumName();
        Intrinsics.checkNotNullExpressionValue(albumName, "playbackController.currentMediaItem.albumName");
        return albumName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playbackEventListener$lambda-0, reason: not valid java name */
    public static final void m1138playbackEventListener$lambda0(PlayerOverflowMenuNowPlayingViewModel this$0, int i, ControlSource noName_1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 11) {
            this$0.currentTrackSourceNameObservable.onNext(this$0.getCurrentTrackSourceName());
        }
    }
}
