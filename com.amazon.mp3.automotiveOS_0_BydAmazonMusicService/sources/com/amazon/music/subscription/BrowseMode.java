package com.amazon.music.subscription;

import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManager;
import com.amazon.music.featureflag.FeatureFlagProvider;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseMode {
    private final AccountManager accountManager;
    private final FeatureFlagProvider featureFlagProvider;

    public enum Mode {
        NONE,
        NIGHTWING,
        PRIME,
        HAWKFIRE,
        KATANA,
        SONIC_RUSH
    }

    public BrowseMode(AccountManager accountManager, FeatureFlagProvider featureFlagProvider) {
        this.accountManager = (AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]);
        this.featureFlagProvider = (FeatureFlagProvider) Validate.notNull(featureFlagProvider, "FeatureFlag cannot be null", new Object[0]);
    }

    public Mode getBrowseMode() {
        ContentAccess contentAccess = new ContentAccess(this.accountManager, this.featureFlagProvider);
        UserSubscription userSubscription = new UserSubscription(this.accountManager, this.featureFlagProvider);
        if (userSubscription.isKatana() && contentAccess.canBrowseContent(ContentAccessType.HAWKFIRE).isAccessible()) {
            return Mode.KATANA;
        }
        if (userSubscription.isHawkfireAllDevices() && contentAccess.canBrowseContent(ContentAccessType.HAWKFIRE).isAccessible()) {
            return Mode.HAWKFIRE;
        }
        if (userSubscription.isSonicRush() && contentAccess.canBrowseContent(ContentAccessType.SONIC_RUSH).isAccessible()) {
            return Mode.SONIC_RUSH;
        }
        if (userSubscription.isPrime() && contentAccess.canBrowseContent(ContentAccessType.PRIME).isAccessible()) {
            return Mode.PRIME;
        }
        if (userSubscription.isNightwing()) {
            return Mode.NIGHTWING;
        }
        if (!userSubscription.hasSubscription() && contentAccess.canBrowseContent(ContentAccessType.HAWKFIRE).isAccessible()) {
            return Mode.HAWKFIRE;
        }
        if (!userSubscription.hasSubscription() && contentAccess.canBrowseContent(ContentAccessType.PRIME).isAccessible()) {
            return Mode.PRIME;
        }
        return Mode.NONE;
    }
}
