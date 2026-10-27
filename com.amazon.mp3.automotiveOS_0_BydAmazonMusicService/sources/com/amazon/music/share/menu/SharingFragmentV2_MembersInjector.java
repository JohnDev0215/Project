package com.amazon.music.share.menu;

import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.share.ShareHelper;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class SharingFragmentV2_MembersInjector implements MembersInjector<SharingFragmentV2> {
    public static void injectStyleSheetProvider(SharingFragmentV2 sharingFragmentV2, StyleSheetProvider styleSheetProvider) {
        sharingFragmentV2.styleSheetProvider = styleSheetProvider;
    }

    public static void injectSocialMetadataProvider(SharingFragmentV2 sharingFragmentV2, SocialMetadataProvider socialMetadataProvider) {
        sharingFragmentV2.socialMetadataProvider = socialMetadataProvider;
    }

    public static void injectEnvironmentProvider(SharingFragmentV2 sharingFragmentV2, EnvironmentProvider environmentProvider) {
        sharingFragmentV2.environmentProvider = environmentProvider;
    }

    public static void injectImageUrlRepository(SharingFragmentV2 sharingFragmentV2, ImageUrlRepository imageUrlRepository) {
        sharingFragmentV2.imageUrlRepository = imageUrlRepository;
    }

    public static void injectShareHelper(SharingFragmentV2 sharingFragmentV2, ShareHelper shareHelper) {
        sharingFragmentV2.shareHelper = shareHelper;
    }
}
