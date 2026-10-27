package com.amazon.mp3.playback.fragment;

import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoPlayerFragment_MembersInjector implements MembersInjector<VideoPlayerFragment> {
    public static void injectAudioVideoToggleInteractor(VideoPlayerFragment instance, AudioVideoToggleUseCase audioVideoToggleInteractor) {
        instance.audioVideoToggleInteractor = audioVideoToggleInteractor;
    }

    public static void injectVideoPlayerStateProvider(VideoPlayerFragment instance, VideoPlayerStateProvider videoPlayerStateProvider) {
        instance.videoPlayerStateProvider = videoPlayerStateProvider;
    }
}
