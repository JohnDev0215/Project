package com.amazon.mp3.eux;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.widget.eux.EUXConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public class EUXProvider {
    public static EUXConfiguration buildEUXConfiguration(final Context context) {
        EUXConfiguration.EUXConfigurationBuilder eUXConfigurationBuilderBuilder = EUXConfiguration.builder(new EUXDestinationLauncher(context));
        if (hasMusicInLibrary()) {
            eUXConfigurationBuilderBuilder = eUXConfigurationBuilderBuilder.withMusicInLibrary();
        }
        if (hasSubscription()) {
            eUXConfigurationBuilderBuilder = eUXConfigurationBuilderBuilder.withSubscription();
        }
        if (AmazonApplication.getCapabilities().isStoreSupported()) {
            eUXConfigurationBuilderBuilder = eUXConfigurationBuilderBuilder.withStoreSupport();
        }
        return eUXConfigurationBuilderBuilder.build();
    }

    private static boolean hasMusicInLibrary() {
        return new CirrusSourceLibraryItemFactory().hasTracksInCirrusLibrary();
    }

    private static boolean hasSubscription() {
        return UserSubscriptionUtil.getUserSubscription().hasSubscription();
    }
}
