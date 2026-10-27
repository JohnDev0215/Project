package com.amazon.music.share.menu;

import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.share.ShareHelper;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class SharingFragment_MembersInjector implements MembersInjector<SharingFragment> {
    public static void injectStyleSheetProvider(SharingFragment sharingFragment, StyleSheetProvider styleSheetProvider) {
        sharingFragment.styleSheetProvider = styleSheetProvider;
    }

    public static void injectSocialMetadataProvider(SharingFragment sharingFragment, SocialMetadataProvider socialMetadataProvider) {
        sharingFragment.socialMetadataProvider = socialMetadataProvider;
    }

    public static void injectEnvironmentProvider(SharingFragment sharingFragment, EnvironmentProvider environmentProvider) {
        sharingFragment.environmentProvider = environmentProvider;
    }

    public static void injectImageUrlRepository(SharingFragment sharingFragment, ImageUrlRepository imageUrlRepository) {
        sharingFragment.imageUrlRepository = imageUrlRepository;
    }

    public static void injectShareHelper(SharingFragment sharingFragment, ShareHelper shareHelper) {
        sharingFragment.shareHelper = shareHelper;
    }
}
