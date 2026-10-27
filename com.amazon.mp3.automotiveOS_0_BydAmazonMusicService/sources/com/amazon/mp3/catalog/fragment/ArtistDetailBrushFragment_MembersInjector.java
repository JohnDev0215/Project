package com.amazon.mp3.catalog.fragment;

import com.amazon.music.explore.containers.FeaturedContainer;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class ArtistDetailBrushFragment_MembersInjector implements MembersInjector<ArtistDetailBrushFragment> {
    public static void injectExploreManagerFactory(ArtistDetailBrushFragment instance, ExploreManagerFactory exploreManagerFactory) {
        instance.exploreManagerFactory = exploreManagerFactory;
    }

    public static void injectFeaturedContainer(ArtistDetailBrushFragment instance, FeaturedContainer featuredContainer) {
        instance.featuredContainer = featuredContainer;
    }
}
