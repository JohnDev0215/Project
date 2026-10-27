package com.amazon.music.subscription;

import com.amazon.music.account.SonicRushFeature;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class FeatureFlagProviderWrapper {
    private static final Logger LOG = LoggerFactory.getLogger(FeatureFlagProviderWrapper.class.getSimpleName());
    private final FeatureFlagProvider featureFlagProvider;

    FeatureFlagProviderWrapper(FeatureFlagProvider featureFlagProvider) {
        this.featureFlagProvider = featureFlagProvider;
    }

    boolean canAccessNightwing() {
        try {
            return this.featureFlagProvider.canAccessNightwing();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return true;
        }
    }

    boolean canAccessKatana() {
        try {
            return this.featureFlagProvider.canAccessKatana();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return true;
        }
    }

    boolean canAccessIvy() {
        try {
            return this.featureFlagProvider.isIvySupported();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return true;
        }
    }

    boolean canAccessSonicRush() {
        return new SonicRushFeature().isEnabled();
    }

    boolean canAccessPrime() {
        try {
            return this.featureFlagProvider.canAccessPrime();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return true;
        }
    }

    boolean canAccessUnlimited() {
        try {
            return this.featureFlagProvider.canAccessUnlimited();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return true;
        }
    }

    boolean canSDPAccessFreeTier() {
        try {
            return this.featureFlagProvider.isFreeTierEnabledForSDP();
        } catch (FeatureFlagUnavailableException e) {
            LOG.error("Cannot access FeatureFlag", (Throwable) e);
            return false;
        }
    }
}
