package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public interface ReferralAndCampaignMetrics {
    void recordCampaignOpen(String campaignName, boolean newInstall);

    void recordCampaignPurchase();

    void recordReferralAppOpen(String referrerId);

    void recordReferralPurchaseComplete();
}
