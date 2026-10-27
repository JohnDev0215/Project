package com.amazon.mp3.environment.url;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.configuration.managers.EndPointManager;
import com.amazon.mp3.configuration.managers.MarketPlaceManager;

/* JADX INFO: loaded from: classes3.dex */
public class BaseEndPointURLFactory extends EndPointURLFactory {
    private String mCurrentPath;
    private int mEnvironmentKey;

    public BaseEndPointURLFactory() {
        this.mEnvironmentKey = -1;
    }

    public BaseEndPointURLFactory(int environmentKey, String currentPath) {
        this(environmentKey);
        this.mCurrentPath = currentPath;
    }

    public BaseEndPointURLFactory(int environmentKey) {
        this.mEnvironmentKey = environmentKey;
    }

    @Override // com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            EndPointURL endPointURLCreate = EndPointURL.create(this, IntlConfiguration.ENDPOINT.id(getCurrentEndPointId()), IntlConfiguration.MARKET.obfuscatedId(AccountDetailStorage.get().getLibraryHomeMarketplace(null)).countryCode());
            if (endPointURLCreate.id() != null) {
                return endPointURLCreate;
            }
        } catch (EndPointManager.UnknownEndpointException | MarketPlaceManager.UnknownMarketplaceException unused) {
        }
        return new EndPointURL();
    }

    @Override // com.amazon.mp3.environment.url.EndPointURLFactory
    public String getCurrentEndPointId() {
        return this.mEnvironmentKey != -1 ? SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), this.mEnvironmentKey, "prod") : "prod";
    }

    public void setEnvironmentKey(int environmentKey) {
        this.mEnvironmentKey = environmentKey;
    }

    @Override // com.amazon.mp3.environment.url.EndPointURLFactory
    public String getCurrentPath() {
        return this.mCurrentPath;
    }
}
