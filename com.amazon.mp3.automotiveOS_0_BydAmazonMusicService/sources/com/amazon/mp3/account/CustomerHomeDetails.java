package com.amazon.mp3.account;

/* JADX INFO: loaded from: classes3.dex */
public class CustomerHomeDetails {
    private boolean mCloudPlayerAccountExists;
    private String mCloudPlayerMarketplaceId;

    public CustomerHomeDetails(boolean cloudPlayerAccountExists, String cloudPlayerMarketplaceId) {
        this.mCloudPlayerAccountExists = cloudPlayerAccountExists;
        this.mCloudPlayerMarketplaceId = cloudPlayerMarketplaceId;
    }

    public String getCloudPlayerMarketplaceId() {
        return this.mCloudPlayerMarketplaceId;
    }

    public boolean getCloudPlayerAccountExists() {
        return this.mCloudPlayerAccountExists;
    }

    public String toString() {
        return super.toString() + "\ncloudPlayerAccountExists: " + this.mCloudPlayerAccountExists + "cloudPlayerMarketplaceId: " + this.mCloudPlayerMarketplaceId;
    }
}
