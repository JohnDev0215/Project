package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.account.User;
import com.amazon.music.subscription.ContentAccessResult;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class BundesligaFreeContentAccess extends BundesligaContentAccess {
    private static final Logger LOG = LoggerFactory.getLogger(BundesligaFreeContentAccess.class.getSimpleName());

    BundesligaFreeContentAccess(AccountManager accountManager) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]));
    }

    @Override // com.amazon.music.subscription.BundesligaContentAccess
    ContentAccessResult hasPlaybackAccess() {
        ContentAccessResult contentAccessResultIsUserDataReady = isUserDataReady();
        if (!contentAccessResultIsUserDataReady.isAccessible()) {
            return contentAccessResultIsUserDataReady;
        }
        if (this.user.getBenefits().contains(User.Benefit.BUNDESLIGA_FREE_PLAYBACK)) {
            return ContentAccessResult.successfulContentAccessResult();
        }
        LOG.debug("Marketplace unsupported for Bundesliga free.");
        return ContentAccessResult.failedContentAccessResult(ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED);
    }
}
