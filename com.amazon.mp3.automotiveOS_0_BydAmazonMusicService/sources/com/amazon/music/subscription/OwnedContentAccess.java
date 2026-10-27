package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class OwnedContentAccess extends ContentAccessTypeBase {
    private static final Logger LOG = LoggerFactory.getLogger(OwnedContentAccess.class.getSimpleName());

    OwnedContentAccess(AccountManager accountManager) {
        super((AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]), null);
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canDownloadContent() {
        ContentAccessResult contentAccessResultCanAccessCloud = canAccessCloud();
        return !contentAccessResultCanAccessCloud.isAccessible() ? contentAccessResultCanAccessCloud : ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canPlayDownloadedContent() {
        return ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canStreamContent() {
        ContentAccessResult contentAccessResultCanAccessCloud = canAccessCloud();
        return !contentAccessResultCanAccessCloud.isAccessible() ? contentAccessResultCanAccessCloud : ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canBrowseContent() {
        return ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult canInteractWithContent() {
        return ContentAccessResult.successfulContentAccessResult();
    }

    @Override // com.amazon.music.subscription.ContentAccessTypeBase
    protected ContentAccessResult isInMarketplace() {
        return ContentAccessResult.successfulContentAccessResult();
    }
}
