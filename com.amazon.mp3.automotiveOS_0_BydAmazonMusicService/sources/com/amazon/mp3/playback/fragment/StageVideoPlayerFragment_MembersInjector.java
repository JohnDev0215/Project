package com.amazon.mp3.playback.fragment;

import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class StageVideoPlayerFragment_MembersInjector implements MembersInjector<StageVideoPlayerFragment> {
    public static void injectAudioVideoToggleInteractor(StageVideoPlayerFragment instance, AudioVideoToggleUseCase audioVideoToggleInteractor) {
        instance.audioVideoToggleInteractor = audioVideoToggleInteractor;
    }

    public static void injectVideoPlayerStateProvider(StageVideoPlayerFragment instance, VideoPlayerStateProvider videoPlayerStateProvider) {
        instance.videoPlayerStateProvider = videoPlayerStateProvider;
    }
}
