package com.amazon.mp3.playback.harley;

import android.content.Context;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.Device;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.access.BasicMediaAccessPolicy;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.platform.data.SonicRushSubscriptionTracking;
import com.amazon.music.platform.data.subscription.FreeSubscriptionTracking;
import com.amazon.music.platform.data.subscription.MusicHDSubscriptionTracking;
import com.amazon.music.platform.data.subscription.MusicUnlimitedSubscriptionTracking;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;
import com.amazon.music.platform.data.subscription.PrimeSubscriptionTracking;
import com.amazon.music.platform.providers.SubscriptionTracking;
import com.amazon.music.subscription.ContentAccess;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes3.dex */
public class MediaAccessPolicyHelper {
    public static void updateMediaAccessPolicy(final Context context) {
        ContentAccess contentAccess = new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
        EnumSet enumSetNoneOf = EnumSet.noneOf(ContentAccessType.class);
        for (ContentAccessType contentAccessType : ContentAccessUtil.getAllContentAccessTypeValues()) {
            if (contentAccess.canStreamContent(contentAccessType).isAccessible()) {
                enumSetNoneOf.add(contentAccessType);
            }
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            enumSetNoneOf.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
            enumSetNoneOf.add(ContentAccessType.NIGHTWING);
        }
        if (!isDeviceAuthorized()) {
            enumSetNoneOf.add(ContentAccessType.OWNED);
        }
        boolean zIsAccessible = contentAccess.canStreamContent(ContentAccessType.PRIME).isAccessible();
        boolean zIsAccessible2 = contentAccess.canStreamContent(ContentAccessType.HAWKFIRE).isAccessible();
        boolean z = contentAccess.canStreamContent(ContentAccessType.NIGHTWING).isAccessible() || contentAccess.canStreamContent(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE).isAccessible();
        boolean zIsAccessible3 = contentAccess.canStreamContent(ContentAccessType.SONIC_RUSH).isAccessible();
        boolean zIsConnected = ChromecastController.getInstance().isConnected();
        SubscriptionTracking subscription = getSubscription(zIsAccessible, zIsAccessible2, z, zIsAccessible3);
        String strContentSubscriptionMode = subscription.contentSubscriptionMode();
        String strSubContentSubscriptionMode = subscription.subContentSubscriptionMode();
        new ExplicitLanguageManager(context.getApplicationContext());
        BasicMediaAccessPolicy basicMediaAccessPolicy = new BasicMediaAccessPolicy(strContentSubscriptionMode, strSubContentSubscriptionMode, true ^ ExplicitLanguageManager.isExplicitFilterEnabled(), enumSetNoneOf, zIsAccessible2 ? enumSetNoneOf : EnumSet.of(ContentAccessType.OWNED), zIsConnected);
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        if (ObjectUtils.equal(playbackConfig.getMediaAccessPolicy(), basicMediaAccessPolicy)) {
            return;
        }
        playbackConfig.setMediaAccessPolicy(basicMediaAccessPolicy);
    }

    private static SubscriptionTracking getSubscription(final boolean canStreamPrimeContent, final boolean canStreamUnlimitedContent, final boolean canStreamNightwingContent, final boolean canStreamSonicRushContent) {
        if (canStreamUnlimitedContent) {
            if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                return new MusicHDSubscriptionTracking();
            }
            return new MusicUnlimitedSubscriptionTracking();
        }
        if (canStreamSonicRushContent) {
            return new SonicRushSubscriptionTracking();
        }
        if (canStreamPrimeContent) {
            return new PrimeSubscriptionTracking();
        }
        if (canStreamNightwingContent) {
            return new FreeSubscriptionTracking();
        }
        return new NoSubscriptionTracking();
    }

    private static boolean isDeviceAuthorized() {
        try {
            return Device.AuthorizationState.AUTHORIZED.equals(AccountManagerSingleton.get().getDevice().getAuthorizationState());
        } catch (DataNotReadyException unused) {
            return false;
        }
    }
}
