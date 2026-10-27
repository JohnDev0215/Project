package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(UpsellStrategy.class.getSimpleName());
    private final AccountManager accountManager;
    private final FeatureFlagProvider featureFlagProvider;

    public enum Upsell {
        TO_NONE,
        TO_HAWKFIRE,
        TO_PRIME
    }

    public UpsellStrategy(AccountManager accountManager, FeatureFlagProvider featureFlagProvider) {
        this.accountManager = (AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]);
        this.featureFlagProvider = (FeatureFlagProvider) Validate.notNull(featureFlagProvider, "FeatureFlagProvider cannot be null", new Object[0]);
    }

    public Upsell getUpsellType() {
        boolean zCanAccessUnlimited;
        UserSubscription userSubscription = new UserSubscription(this.accountManager, this.featureFlagProvider);
        try {
            User user = this.accountManager.getUser();
            if (userSubscription.hasSubscription()) {
                return Upsell.TO_NONE;
            }
            boolean zCanAccessPrime = true;
            try {
                zCanAccessUnlimited = this.featureFlagProvider.canAccessUnlimited();
            } catch (FeatureFlagUnavailableException unused) {
                zCanAccessUnlimited = true;
            }
            if (zCanAccessUnlimited && user.isInHawkfireMarketplace()) {
                return Upsell.TO_HAWKFIRE;
            }
            try {
                zCanAccessPrime = this.featureFlagProvider.canAccessPrime();
            } catch (FeatureFlagUnavailableException unused2) {
            }
            if (zCanAccessPrime && user.isInPrimeMarketplace()) {
                return Upsell.TO_PRIME;
            }
            return Upsell.TO_NONE;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return Upsell.TO_NONE;
        }
    }
}
