package com.amazon.mp3.view.lyrics.sharing.view;

import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class LyricsSelectionFragment_MembersInjector implements MembersInjector<LyricsSelectionFragment> {
    public static void injectLyricsSharingDataModel(LyricsSelectionFragment instance, LyricsSharingDataModel lyricsSharingDataModel) {
        instance.lyricsSharingDataModel = lyricsSharingDataModel;
    }
}
