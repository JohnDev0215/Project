package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.Device;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.subscription.ContentAccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
abstract class ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(ContentAccessTypeBase.class.getSimpleName());
    AccountManager accountManager;
    Device device;
    FeatureFlagProviderWrapper featureFlagProviderWrapper;
    User user;

    protected abstract ContentAccessResult canDownloadContent();

    protected abstract ContentAccessResult canInteractWithContent();

    protected abstract ContentAccessResult canStreamContent();

    protected abstract ContentAccessResult isInMarketplace();

    ContentAccessTypeBase(AccountManager accountManager, FeatureFlagProviderWrapper featureFlagProviderWrapper) {
        this.accountManager = accountManager;
        this.featureFlagProviderWrapper = featureFlagProviderWrapper;
    }

    protected ContentAccessResult canPlayDownloadedContent() {
        return canDownloadContent();
    }

    protected ContentAccessResult canBrowseContent() {
        return isAccountValid();
    }

    ContentAccessResult isUserDataReady() {
        AccountManager accountManager = this.accountManager;
        if (accountManager == null) {
            throw new IllegalStateException("isUserDataReady called with null accountManager");
        }
        try {
            this.user = accountManager.getUser();
            this.device = this.accountManager.getDevice();
            return ContentAccessResult.successfulContentAccessResult();
        } catch (DataNotReadyException unused) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_DATA_NOT_READY);
        } catch (MusicAccountNotCreatedException unused2) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.ACCOUNT_NOT_CREATED);
        }
    }

    ContentAccessResult getPlaybackAccessReason() {
        User user = this.user;
        if (user == null) {
            throw new IllegalStateException("getPlaybackAccessReason called with null user, did you call isUserDataReady?");
        }
        if (user.getBenefits().contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD)) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessUnlimited() && this.user.isInHawkfireMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessPrime() && this.user.isInPrimeMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_PRIME_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessNightwing() && this.user.isInNightwingMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_NIGHTWING_SUBSCRIPTION);
        }
        LOG.debug("Marketplace support forNightwing/Prime/HF: isInHawkfireMarketplace: " + this.user.isInHawkfireMarketplace() + ", isInPrimeMarketplace: " + this.user.isInPrimeMarketplace() + ", isInNightMarketplace: " + this.user.isInNightwingMarketplace());
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }

    ContentAccessResult getInteractWithContentReason() {
        if (this.user == null) {
            throw new IllegalStateException("getPlaybackAccessReason called with null user, did you call isUserDataReady?");
        }
        if (this.featureFlagProviderWrapper.canAccessUnlimited() && this.user.isInHawkfireMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessSonicRush() && this.user.isInSonicRushMusicTerritory()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_SONIC_RUSH_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessPrime() && this.user.isInPrimeMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_PRIME_SUBSCRIPTION);
        }
        if (this.featureFlagProviderWrapper.canAccessNightwing() && this.user.isInNightwingMarketplace()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_NIGHTWING_SUBSCRIPTION);
        }
        LOG.debug("Marketplace support forNightwing/Prime/HF: isInHawkfireMarketplace: " + this.user.isInHawkfireMarketplace() + ", isInPrimeMarketplace: " + this.user.isInPrimeMarketplace() + ", isInNightMarketplace: " + this.user.isInNightwingMarketplace());
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }

    ContentAccessResult canAccessCloud() {
        ContentAccessResult contentAccessResultIsAccountValid = isAccountValid();
        if (!contentAccessResultIsAccountValid.isAccessible()) {
            return contentAccessResultIsAccountValid;
        }
        if (!Device.AuthorizationState.AUTHORIZED.equals(this.device.getAuthorizationState())) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.DEVICE_NOT_AUTHORIZED);
        }
        return ContentAccessResult.successfulContentAccessResult();
    }

    ContentAccessResult isAccountValid() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (!this.user.isAccountValid()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.ACCOUNT_INVALID);
        }
        if (this.user.isAccountLocked()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.ACCOUNT_LOCKED);
        }
        if (!this.user.isAccountVerified()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.ACCOUNT_NOT_VERIFIED);
        }
        if (!this.user.hasAcceptedTermsOfUse()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.TERMS_OF_USE_NOT_ACCEPTED);
        }
        return ContentAccessResult.successfulContentAccessResult();
    }

    ContentAccessResult deviceIsPrimeAuthorized() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (!this.device.getCapabilities().contains(Device.Capability.RETRIEVE_ROBIN_CONTENT)) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.DEVICE_NOT_PRIME_AUTHORIZED);
        }
        return ContentAccessResult.successfulContentAccessResult();
    }
}
