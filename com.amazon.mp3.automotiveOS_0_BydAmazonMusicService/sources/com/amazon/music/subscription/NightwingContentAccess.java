package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.subscription.ContentAccessResult;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class NightwingContentAccess extends ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(NightwingContentAccess.class.getSimpleName());
    private final UserSubscription userSubscription;

    NightwingContentAccess(AccountManager accountManager, FeatureFlagProviderWrapper featureFlagProviderWrapper, UserSubscription userSubscription) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]), (FeatureFlagProviderWrapper) Validate.notNull(featureFlagProviderWrapper, "AccountManager cannot be null", new Object[0]));
        this.userSubscription = (UserSubscription) Validate.notNull(userSubscription, "AccountManager cannot be null", new Object[0]);
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canDownloadContent() {
        ContentAccessResult contentAccessResultCanStreamContent = canStreamContent();
        if (!contentAccessResultCanStreamContent.isAccessible()) {
            return contentAccessResultCanStreamContent;
        }
        if (this.userSubscription.isNightwingOnly()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.NIGHTWING_TIER_RESTRICTIONS);
        }
        if (this.userSubscription.isHomeOnly()) {
            return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION);
        }
        return deviceIsPrimeAuthorized();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canStreamContent() {
        ContentAccessResult contentAccessResultCanAccessCloud = canAccessCloud();
        if (!contentAccessResultCanAccessCloud.isAccessible()) {
            return contentAccessResultCanAccessCloud;
        }
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.userSubscription.isNightwing() || this.userSubscription.isPrime() || this.userSubscription.isHawkfireHome() || this.userSubscription.isHawkfireAllDevices()) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        return getPlaybackAccessReason();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canInteractWithContent() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.userSubscription.isNightwing() || this.userSubscription.isPrime() || this.userSubscription.isHawkfireHome() || this.userSubscription.isHawkfireAllDevices()) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        return getInteractWithContentReason();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult isInMarketplace() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.user.isInNightwingMarketplace() && this.featureFlagProviderWrapper.canAccessNightwing()) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        LOG.debug("Marketplace does not support Nightwing.");
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }
}
