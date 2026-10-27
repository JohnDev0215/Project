package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum RightsOperationCause implements Cause {
    RIGHTS_RELEASED_DOWNLOAD_DELETED("AppRightsReleased:DownloadDeletion", "AppRightsReleased"),
    RIGHTS_RELEASED_PRS_DIRECTIVE("AppRightsReleased:PrsDirective", "AppRightsReleased"),
    RIGHTS_RELEASED_NO_SERVER_ENTITLEMENTS("AppRightsReleased:PrsDirective", "AppRightsReleased"),
    RIGHTS_RELEASED_AV_MARKETPLACE_CHANGE("AppRightsReleased:AvMarketplaceChange", "AppLicenseRemoved");

    private static final String OPERATION_NAME = "DownloadRightsOperation";
    private final String mCauseMessage;
    private final String mEventSubType;

    RightsOperationCause(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "causeMessage");
        this.mCauseMessage = String.format(Locale.US, "%s:%s", OPERATION_NAME, str);
        this.mEventSubType = (String) Preconditions.checkNotNull(str2, "downloadSubtype");
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getCauseMessage() {
        return this.mCauseMessage;
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getEventSubType() {
        return this.mEventSubType;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return getCauseMessage();
    }
}
