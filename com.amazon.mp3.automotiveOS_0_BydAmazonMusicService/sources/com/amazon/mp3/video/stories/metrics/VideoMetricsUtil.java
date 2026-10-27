package com.amazon.mp3.video.stories.metrics;

import android.content.Context;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.media.playback.config.NetworkProvider;
import com.amazon.music.metrics.mts.event.types.ContentSubscriptionMode;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.subscription.ContentAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoMetricsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/VideoMetricsUtil;", "", "()V", "getContentSubscriptionMode", "Lcom/amazon/music/metrics/mts/event/types/ContentSubscriptionMode;", "getNetworkProvider", "Lcom/amazon/music/media/playback/config/NetworkProvider;", "context", "Landroid/content/Context;", "getResourceType", "Lcom/amazon/music/metrics/mts/event/types/ResourceType;", "getSubscriptionMode", "canStreamPrimeContent", "", "canStreamUnlimitedContent", "canStreamNightwingContent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoMetricsUtil {
    public static final VideoMetricsUtil INSTANCE = new VideoMetricsUtil();

    private VideoMetricsUtil() {
    }

    public final NetworkProvider getNetworkProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NetworkProvider networkProvider = NetworkProvider.UNKNOWN;
        if (ConnectivityUtil.isWifiConnected(context)) {
            networkProvider = NetworkProvider.WIFI;
        }
        Intrinsics.checkNotNullExpressionValue(networkProvider, "networkProvider");
        return networkProvider;
    }

    public final ContentSubscriptionMode getContentSubscriptionMode() {
        ContentAccess contentAccess = new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
        boolean z = true;
        boolean zIsAccessible = contentAccess.canStreamContent(ContentAccessType.PRIME).isAccessible();
        boolean zIsAccessible2 = contentAccess.canStreamContent(ContentAccessType.HAWKFIRE).isAccessible();
        if (!contentAccess.canStreamContent(ContentAccessType.NIGHTWING).isAccessible() && !contentAccess.canStreamContent(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE).isAccessible()) {
            z = false;
        }
        return getSubscriptionMode(zIsAccessible, zIsAccessible2, z);
    }

    public final ContentSubscriptionMode getSubscriptionMode(boolean canStreamPrimeContent, boolean canStreamUnlimitedContent, boolean canStreamNightwingContent) {
        if (canStreamUnlimitedContent) {
            if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                return ContentSubscriptionMode.KATANA;
            }
            return ContentSubscriptionMode.UNLIMITED;
        }
        if (canStreamPrimeContent) {
            return ContentSubscriptionMode.PRIME;
        }
        if (canStreamNightwingContent) {
            return ContentSubscriptionMode.AMF;
        }
        return ContentSubscriptionMode.NONE;
    }

    public final ResourceType getResourceType() {
        if (UserSubscriptionUtil.isHawkfire()) {
            return ResourceType.UNLIMITED_MUSIC;
        }
        if (UserSubscriptionUtil.isPrime()) {
            return ResourceType.PRIME_MUSIC;
        }
        return ResourceType.AMF_MUSIC;
    }
}
