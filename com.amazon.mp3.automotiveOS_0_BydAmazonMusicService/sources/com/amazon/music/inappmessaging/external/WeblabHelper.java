package com.amazon.music.inappmessaging.external;

/* JADX INFO: loaded from: classes4.dex */
public class WeblabHelper {
    private static WeblabHelper sInstance;
    private boolean isCustomerAmazonWallet;
    private boolean isFireOS;
    private boolean isGPBLaunchedInTerritory;
    private boolean shouldUseSplashV2;

    public static synchronized WeblabHelper getInstance() {
        if (sInstance == null) {
            sInstance = new WeblabHelper();
        }
        return sInstance;
    }

    public void enableSplashV2(final boolean shouldUseSplashV2) {
        this.shouldUseSplashV2 = shouldUseSplashV2;
    }

    public void enableGPBLaunchedInTerritory(final boolean isGPBLaunchedInTerritory) {
        this.isGPBLaunchedInTerritory = isGPBLaunchedInTerritory;
    }

    public void setIsCustomerAmazonWallet(final boolean isCustomerAmazonWallet) {
        this.isCustomerAmazonWallet = isCustomerAmazonWallet;
    }

    public void setIsFireOS(final boolean isFireOS) {
        this.isFireOS = isFireOS;
    }

    public boolean isSplashV2Enabled() {
        return this.shouldUseSplashV2;
    }

    public boolean isGPBLaunchedInTerritory() {
        return this.isGPBLaunchedInTerritory;
    }

    public boolean isCustomerAmazonWallet() {
        return this.isCustomerAmazonWallet;
    }

    public boolean isFireOS() {
        return this.isFireOS;
    }
}
