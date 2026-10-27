package com.amazon.mp3.playback.view.lyrics;

import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class LyricsListViewContainer_MembersInjector implements MembersInjector<LyricsListViewContainer> {
    public static void injectMLyricsSharingDataModel(LyricsListViewContainer instance, LyricsSharingDataModel mLyricsSharingDataModel) {
        instance.mLyricsSharingDataModel = mLyricsSharingDataModel;
    }
}
