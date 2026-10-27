package com.amazon.mp3.detailpages.playlist;

import com.amazon.music.explore.managers.ExploreManagerFactory;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaylistDetailFragment_MembersInjector implements MembersInjector<PlaylistDetailFragment> {
    public static void injectExploreManagerFactory(PlaylistDetailFragment instance, ExploreManagerFactory exploreManagerFactory) {
        instance.exploreManagerFactory = exploreManagerFactory;
    }
}
