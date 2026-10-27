package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.subscription.ContentAccessResult;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class BundesligaContentAccess extends ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(BundesligaContentAccess.class.getSimpleName());

    BundesligaContentAccess(AccountManager accountManager) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]), null);
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canDownloadContent() {
        throw new IllegalArgumentException("Unable to download streaming content");
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canStreamContent() {
        ContentAccessResult contentAccessResultCanAccessCloud = canAccessCloud();
        return !contentAccessResultCanAccessCloud.isAccessible() ? contentAccessResultCanAccessCloud : hasPlaybackAccess();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canInteractWithContent() {
        throw new IllegalArgumentException("Called canInteractWithContent for unsupported BundesligaFreeContentAccess");
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult isInMarketplace() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.user.isInBundesligaMarketplace()) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        LOG.debug("Marketplace does not support Bundesliga.");
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }

    ContentAccessResult hasPlaybackAccess() {
        throw new IllegalArgumentException("Called hasPlaybackAccess for unsupported BundesligaContentAccess");
    }
}
