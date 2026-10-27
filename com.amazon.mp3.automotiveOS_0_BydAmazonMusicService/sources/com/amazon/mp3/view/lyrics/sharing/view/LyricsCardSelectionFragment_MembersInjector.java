package com.amazon.mp3.view.lyrics.sharing.view;

import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class LyricsCardSelectionFragment_MembersInjector implements MembersInjector<LyricsCardSelectionFragment> {
    public static void injectStyleSheetProvider(LyricsCardSelectionFragment instance, StyleSheetProvider styleSheetProvider) {
        instance.styleSheetProvider = styleSheetProvider;
    }

    public static void injectSocialMetadataProvider(LyricsCardSelectionFragment instance, SocialMetadataProvider socialMetadataProvider) {
        instance.socialMetadataProvider = socialMetadataProvider;
    }

    public static void injectEnvironmentProvider(LyricsCardSelectionFragment instance, EnvironmentProvider environmentProvider) {
        instance.environmentProvider = environmentProvider;
    }
}
