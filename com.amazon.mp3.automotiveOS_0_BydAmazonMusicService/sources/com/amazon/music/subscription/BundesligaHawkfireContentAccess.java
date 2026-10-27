package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.account.User;
import com.amazon.music.subscription.ContentAccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class BundesligaHawkfireContentAccess extends BundesligaContentAccess {
    private static final Logger LOG = LoggerFactory.getLogger(BundesligaHawkfireContentAccess.class.getSimpleName());

    BundesligaHawkfireContentAccess(AccountManager accountManager) {
        super(accountManager);
    }

    @Override // com.amazon.music.subscription.BundesligaContentAccess
    ContentAccessResult hasPlaybackAccess() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.user.getBenefits().contains(User.Benefit.BUNDESLIGA_UNLIMITED_PLAYBACK)) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        LOG.debug("Marketplace unsupported for Bundesliga Hawkfire.");
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION);
    }
}
