package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpReferralMetrics implements ReferralAndCampaignMetrics {
    @Override // com.amazon.mp3.service.metrics.ReferralAndCampaignMetrics
    public void recordCampaignOpen(String campaignName, boolean newInstall) {
    }

    @Override // com.amazon.mp3.service.metrics.ReferralAndCampaignMetrics
    public void recordCampaignPurchase() {
    }

    @Override // com.amazon.mp3.service.metrics.ReferralAndCampaignMetrics
    public void recordReferralAppOpen(String referrerId) {
    }

    @Override // com.amazon.mp3.service.metrics.ReferralAndCampaignMetrics
    public void recordReferralPurchaseComplete() {
    }
}
