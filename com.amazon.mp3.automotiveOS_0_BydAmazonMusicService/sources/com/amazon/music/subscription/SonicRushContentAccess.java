package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.subscription.ContentAccessResult;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class SonicRushContentAccess extends ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(SonicRushContentAccess.class.getSimpleName());
    private final UserSubscription userSubscription;

    SonicRushContentAccess(AccountManager accountManager, FeatureFlagProviderWrapper featureFlagProviderWrapper, UserSubscription userSubscription) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]), (FeatureFlagProviderWrapper) Validate.notNull(featureFlagProviderWrapper, "AccountManager cannot be null", new Object[0]));
        this.userSubscription = (UserSubscription) Validate.notNull(userSubscription, "AccountManager cannot be null", new Object[0]);
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canDownloadContent() {
        ContentAccessResult contentAccessResultCanStreamContent = canStreamContent();
        return !contentAccessResultCanStreamContent.isAccessible() ? contentAccessResultCanStreamContent : deviceIsPrimeAuthorized();
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
        if (this.userSubscription.isSonicRush() || this.userSubscription.isHawkfireAllDevices()) {
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
        if (this.userSubscription.isSonicRush() || this.userSubscription.isHawkfireHome() || this.userSubscription.isHawkfireAllDevices()) {
            return this.featureFlagProviderWrapper.canAccessSonicRush() ? ContentAccessResult.successfulContentAccessResult() : ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
        }
        return getInteractWithContentReason();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult isInMarketplace() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.user.isInSonicRushMusicTerritory() && this.featureFlagProviderWrapper.canAccessSonicRush()) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        LOG.debug("Marketplace does not support Sonic Rush.");
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }
}
