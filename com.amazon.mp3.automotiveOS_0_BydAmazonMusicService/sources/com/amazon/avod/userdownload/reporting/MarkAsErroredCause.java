package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum MarkAsErroredCause implements Cause {
    MEDIA_EJECTED("User:MediaEjected", "UserMarkAsError"),
    AUTOMATIC_PURCHASE_CHARGE_FAILURE("Automatic:PurchaseChargeFailure", "AppMarkAsError"),
    APP_MARK_ERRORED_SYNC_WITH_DRM_STORE("Automatic:SyncDrmStatusWithStore", "AppMarkAsError"),
    APP_MARK_ERRORED_LICENSE_LOST("Automatic:LicenseLost", "AppMarkAsError"),
    PRS_RENDER_UNPLAYABLE("Automatic:PRS:RenderUnplayable", "AppMarkAsError"),
    APP_MARK_ERRORED_AV_MARKETPLACE_CHANGED("Automatic:AvMarketplaceChange", "AppMarkAsError"),
    QA_MARK_ERRORED("Other:QA", "QAMarkAsError"),
    MIGRATION_ERROR("Automatic:DownloadMigration", "AppMarkAsError"),
    PLAYER_SDK_ERROR("Automatic:PlayerSdkError", "PlayerSdkMarkAsError"),
    SYNC_FALLBACK_DISABLE("Automatic:CharonSync:Fallback", "AppMarkAsError"),
    CUSTOMER_LEFT_PRIME_SUBSCRIPTION("Automatic:CharonSync:CustomerLeftPrimeSubscription", "SyncMarkAsError"),
    CUSTOMER_LOST_RENTAL_ENTITLEMENT("Automatic:CharonSync:CustomerLostRentalEntitlement", "SyncMarkAsError"),
    TITLE_LEFT_PRIME("Automatic:CharonSync:TitleLeftPrime", "SyncMarkAsError"),
    CUSTOMER_LEFT_A4K("Automatic:CharonSync:CustomerLeftA4K", "SyncMarkAsError"),
    TITLE_LEFT_A4K("Automatic:CharonSync:TitleLeftA4K", "SyncMarkAsError"),
    CUSTOMER_LEFT_3P("Automatic:CharonSync:CustomerLeft3p", "SyncMarkAsError"),
    TITLE_LEFT_3P("Automatic:CharonSync:TitleLeft3p", "SyncMarkAsError"),
    TITLE_LEFT_FVOD("Automatic:CharonSync:TitleLeftFvod", "SyncMarkAsError"),
    PURCHASED_TITLE_LEFT_DOWNLOADABILITY_OR_CUSTOMER_WAS_REFUNDED("Automatic:CharonSync:PurchasedTitleLeftDownloadabilityOrCustomerWasRefunded", "SyncMarkAsError"),
    CUSTOMER_LOST_UNKNOWN_ENTITLEMENTS("Automatic:CharonSync:CustomerLostUnknownEntitlements", "SyncMarkAsError"),
    CUSTOMER_OR_TITLE_LEFT_A4K("Automatic:CharonSync:CustomerOrTitleLeftA4K", "SyncMarkAsError"),
    TITLE_DISABLED_ON_DEVICE_BUT_RECORDED_IN_DYNAMO("Automatic:CharonSync:TitleDisabledOnDeviceButRecordedInDynamo", "SyncMarkAsError");

    private static final String OPERATION_NAME = "DownloadMarkAsErrored";
    private final String mCauseMessage;
    private final String mEventSubType;

    MarkAsErroredCause(@Nonnull String str, @Nonnull String str2) {
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
