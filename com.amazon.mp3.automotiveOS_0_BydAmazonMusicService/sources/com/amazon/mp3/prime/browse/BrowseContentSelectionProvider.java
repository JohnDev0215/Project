package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.recommendation.RecommendationsContentSelector;
import com.amazon.music.station.StationContentSelector;
import com.amazon.music.subscription.BrowseMode;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseContentSelectionProvider {
    public BrowseContentSelector getBrowseContentSelection(final Context context) {
        BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            browseMode = BrowseMode.Mode.NIGHTWING;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[browseMode.ordinal()];
        if (i == 1) {
            return BrowseContentSelector.SONIC_RUSH;
        }
        if (i == 2) {
            return BrowseContentSelector.PRIME;
        }
        if (i == 3) {
            return BrowseContentSelector.MUSIC_SUBSCRIPTION;
        }
        if (i == 4) {
            return BrowseContentSelector.KATANA;
        }
        if (i == 5) {
            return BrowseContentSelector.FREE;
        }
        return BrowseContentSelector.ALL_STREAMABLE;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.BrowseContentSelectionProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode;

        static {
            int[] iArr = new int[BrowseMode.Mode.values().length];
            $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode = iArr;
            try {
                iArr[BrowseMode.Mode.SONIC_RUSH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.PRIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.HAWKFIRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.KATANA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.NIGHTWING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public BrowseContentSelector getBrowseContentSelectionWithOwnedContent(final Context context) {
        return getBrowseContentSelectionWithOwnedContent(context, false);
    }

    public BrowseContentSelector getBrowseContentSelectionWithOwnedContent(final Context context, final boolean useTierOverride) {
        if (useTierOverride && !UserSubscriptionUtil.isHawkfire() && UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
            return getBrowseContentSelection(context);
        }
        return BrowseContentSelector.FULL_CATALOG;
    }

    public RecommendationsContentSelector getRecommendationsContentSelection(final Context context) {
        BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            browseMode = BrowseMode.Mode.NIGHTWING;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[browseMode.ordinal()];
        if (i == 2) {
            return RecommendationsContentSelector.PRIME;
        }
        if (i == 3) {
            return RecommendationsContentSelector.MUSIC_SUBSCRIPTION;
        }
        if (i == 4) {
            return RecommendationsContentSelector.KATANA;
        }
        if (i == 5) {
            return RecommendationsContentSelector.FREE;
        }
        return RecommendationsContentSelector.MUSIC_SUBSCRIPTION;
    }

    public StationContentSelector getStationContentSelection(final Context context) {
        BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            browseMode = BrowseMode.Mode.NIGHTWING;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[browseMode.ordinal()];
        if (i == 1) {
            return StationContentSelector.SONIC_RUSH;
        }
        if (i == 2) {
            return StationContentSelector.PRIME;
        }
        if (i == 3) {
            return StationContentSelector.MUSIC_SUBSCRIPTION;
        }
        if (i == 4) {
            return StationContentSelector.KATANA;
        }
        if (i == 5) {
            return StationContentSelector.FREE;
        }
        return StationContentSelector.MUSIC_SUBSCRIPTION;
    }
}
