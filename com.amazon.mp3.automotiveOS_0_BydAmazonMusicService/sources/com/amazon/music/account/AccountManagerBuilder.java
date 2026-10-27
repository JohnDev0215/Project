package com.amazon.music.account;

import android.content.Context;
import com.amazon.music.marketplace.Marketplace;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes4.dex */
public class AccountManagerBuilder {
    public static final long DEFAULT_POLLING_INTERVAL_MS = TimeUnit.MINUTES.toMillis(15);

    public interface AccountCacheBuilder {
        SignInStateBuilder withPersistentStorage(Context context);
    }

    public interface FinalBuilder {
        AccountManager build();

        FinalBuilder withAllAutomaticCallsDisabled();

        FinalBuilder withDefaultMarketplace(Marketplace marketplace);

        FinalBuilder withDefaultTerritory(String str);

        FinalBuilder withPollingDisabled();
    }

    public interface SignInStateBuilder {
        FinalBuilder withUserAlreadySignedIn(AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, MusicIdentityServiceConfiguration musicIdentityServiceConfiguration);

        FinalBuilder withUserNotSignedIn();
    }

    private AccountManagerBuilder() {
    }

    public static AccountCacheBuilder newBuilder() {
        return new BuilderImpl();
    }

    private static final class BuilderImpl implements SignInStateBuilder, AccountCacheBuilder, FinalBuilder {
        private AccountCache accountCache;
        private AccountServiceConfiguration accountServiceConfiguration;
        private Marketplace defaultMarketplace;
        private String defaultTerritory;
        private boolean disableInitialUpdate;
        private MusicIdentityServiceConfiguration musicIdentityServiceConfiguration;
        private boolean pollingEnabled;
        private long pollingIntervalMs;
        private StratusServiceConfiguration stratusServiceConfiguration;

        @Override // com.amazon.music.account.AccountManagerBuilder.SignInStateBuilder
        public FinalBuilder withUserNotSignedIn() {
            return this;
        }

        private BuilderImpl() {
            this.pollingIntervalMs = AccountManagerBuilder.DEFAULT_POLLING_INTERVAL_MS;
            this.pollingEnabled = true;
            this.disableInitialUpdate = false;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.AccountCacheBuilder
        public SignInStateBuilder withPersistentStorage(Context context) {
            this.accountCache = new SharedPreferenceAccountCache(new SharedPreferenceAccountCacheUtilsFactory(context));
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.SignInStateBuilder
        public FinalBuilder withUserAlreadySignedIn(AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, MusicIdentityServiceConfiguration musicIdentityServiceConfiguration) {
            this.accountServiceConfiguration = accountServiceConfiguration;
            this.stratusServiceConfiguration = stratusServiceConfiguration;
            this.musicIdentityServiceConfiguration = musicIdentityServiceConfiguration;
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.FinalBuilder
        public FinalBuilder withPollingDisabled() {
            this.pollingEnabled = false;
            this.disableInitialUpdate = false;
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.FinalBuilder
        public FinalBuilder withAllAutomaticCallsDisabled() {
            this.pollingEnabled = false;
            this.disableInitialUpdate = true;
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.FinalBuilder
        public FinalBuilder withDefaultMarketplace(Marketplace marketplace) {
            this.defaultMarketplace = marketplace;
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.FinalBuilder
        public FinalBuilder withDefaultTerritory(String str) {
            this.defaultTerritory = str;
            return this;
        }

        @Override // com.amazon.music.account.AccountManagerBuilder.FinalBuilder
        public AccountManager build() {
            if (this.accountServiceConfiguration == null || this.stratusServiceConfiguration == null) {
                return new AccountManager(this.accountCache, new ReentrantReadWriteLock(), new UpdateServiceFactory(), new AccountServiceFactory(), this.pollingIntervalMs, this.pollingEnabled, this.disableInitialUpdate, new AccountStateChangeListenerDispatcher(), new MarketplaceConfiguration(this.defaultMarketplace, this.defaultTerritory));
            }
            return new AccountManager(this.accountCache, new ReentrantReadWriteLock(), new UpdateServiceFactory(), new AccountServiceFactory(), this.pollingIntervalMs, this.pollingEnabled, this.disableInitialUpdate, new AccountStateChangeListenerDispatcher(), this.accountServiceConfiguration, this.stratusServiceConfiguration, this.musicIdentityServiceConfiguration, new MarketplaceConfiguration(this.defaultMarketplace, this.defaultTerritory));
        }
    }
}
