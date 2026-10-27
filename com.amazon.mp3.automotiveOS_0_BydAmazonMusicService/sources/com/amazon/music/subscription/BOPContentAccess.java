package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.subscription.ContentAccessResult;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class BOPContentAccess extends ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(BOPContentAccess.class.getSimpleName());
    private final UserSubscription userSubscription;

    BOPContentAccess(AccountManager accountManager, FeatureFlagProviderWrapper featureFlagProviderWrapper, UserSubscription userSubscription) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]), (FeatureFlagProviderWrapper) Validate.notNull(featureFlagProviderWrapper, "FeatureFlagProviderWrapper cannot be null", new Object[0]));
        this.userSubscription = (UserSubscription) Validate.notNull(userSubscription, "UserSubscription cannot be null", new Object[0]);
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canDownloadContent() {
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.BOP_TIER_RESTRICTIONS);
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
        return ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canPlayDownloadedContent() {
        return canDownloadContent();
    }
}
