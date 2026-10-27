package com.amazon.mp3.playback.fragment;

import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class NowPlayingFragment_MembersInjector implements MembersInjector<NowPlayingFragment> {
    public static void injectMAudioVideoToggleInteractor(NowPlayingFragment instance, AudioVideoToggleUseCase mAudioVideoToggleInteractor) {
        instance.mAudioVideoToggleInteractor = mAudioVideoToggleInteractor;
    }
}
