package com.amazon.music.account;

import com.amazon.music.account.User;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.stratus.AttemptAccountCreationResponse;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class AccountManager {
    private final AccountCache accountCache;
    private AccountService accountService;
    private final AccountServiceFactory accountServiceFactory;
    private final AccountStateChangeListenerDispatcher accountStateChangeListenerDispatcher;
    private boolean disableInitialUpdate;
    private final MarketplaceConfiguration marketplaceConfiguration;
    private boolean pollingEnabled;
    private long pollingIntervalMs;
    private final StratusMarketplaceProvider stratusMarketplaceProvider;
    private UpdateService updateService;
    private final UpdateServiceFactory updateServiceFactory;
    private final Lock updateServiceReadLock;
    private final Lock updateServiceWriteLock;

    AccountManager(AccountCache accountCache, ReadWriteLock readWriteLock, UpdateServiceFactory updateServiceFactory, AccountServiceFactory accountServiceFactory, long j, boolean z, boolean z2, AccountStateChangeListenerDispatcher accountStateChangeListenerDispatcher, MarketplaceConfiguration marketplaceConfiguration) {
        this.accountCache = (AccountCache) Validate.notNull(accountCache, "AccountCache cannot be null", new Object[0]);
        this.updateServiceFactory = (UpdateServiceFactory) Validate.notNull(updateServiceFactory);
        this.accountServiceFactory = (AccountServiceFactory) Validate.notNull(accountServiceFactory);
        Validate.notNull(readWriteLock);
        this.updateServiceReadLock = readWriteLock.readLock();
        this.updateServiceWriteLock = readWriteLock.writeLock();
        this.pollingIntervalMs = j;
        this.pollingEnabled = z;
        this.disableInitialUpdate = z2;
        this.accountStateChangeListenerDispatcher = (AccountStateChangeListenerDispatcher) Validate.notNull(accountStateChangeListenerDispatcher);
        this.stratusMarketplaceProvider = accountServiceFactory.buildStratusMarketplaceProvider(accountCache);
        this.marketplaceConfiguration = marketplaceConfiguration;
    }

    AccountManager(AccountCache accountCache, ReadWriteLock readWriteLock, UpdateServiceFactory updateServiceFactory, AccountServiceFactory accountServiceFactory, long j, boolean z, boolean z2, AccountStateChangeListenerDispatcher accountStateChangeListenerDispatcher, AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, MusicIdentityServiceConfiguration musicIdentityServiceConfiguration, MarketplaceConfiguration marketplaceConfiguration) {
        this(accountCache, readWriteLock, updateServiceFactory, accountServiceFactory, j, z, z2, accountStateChangeListenerDispatcher, marketplaceConfiguration);
        Validate.notNull(accountServiceConfiguration);
        Validate.notNull(stratusServiceConfiguration);
        onUserSignedIn(accountServiceConfiguration, stratusServiceConfiguration, musicIdentityServiceConfiguration);
    }

    public User getUser() throws MusicAccountNotCreatedException, DataNotReadyException {
        User cachedUser = this.accountCache.getCachedUser();
        if (cachedUser == null) {
            throw new DataNotReadyException();
        }
        if (User.MusicAccountStatus.NOT_CREATED.equals(cachedUser.getMusicAccountStatus())) {
            throw new MusicAccountNotCreatedException("Music account does not exist");
        }
        return cachedUser;
    }

    public User getCachedUser() {
        return this.accountCache.getCachedUser();
    }

    public boolean shouldInvokeAccountWorkflow() {
        return this.accountCache.getCachedInvokeAccountWorkflow();
    }

    public Device getDevice() throws DataNotReadyException {
        Device cachedDevice = this.accountCache.getCachedDevice();
        if (cachedDevice != null) {
            return cachedDevice;
        }
        throw new DataNotReadyException();
    }

    public DeviceAuthorizations getDeviceAuthorizations() throws DataNotReadyException {
        DeviceAuthorizations cachedDeviceAuthorizations = this.accountCache.getCachedDeviceAuthorizations();
        if (cachedDeviceAuthorizations != null) {
            return cachedDeviceAuthorizations;
        }
        throw new DataNotReadyException();
    }

    public DeviceCapabilities getDeviceCapabilities() throws DataNotReadyException {
        DeviceCapabilities cachedDeviceCapabilities = this.accountCache.getCachedDeviceCapabilities();
        if (cachedDeviceCapabilities != null) {
            return cachedDeviceCapabilities;
        }
        throw new DataNotReadyException();
    }

    public void updateAsync() {
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                updateService.updateAsync();
            }
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public void updateUser() {
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                updateService.updateUser();
            }
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public void updateDevices() {
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                updateService.updateDevices();
            }
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public void onUserSignedIn(AccountServiceConfiguration accountServiceConfiguration, StratusServiceConfiguration stratusServiceConfiguration, MusicIdentityServiceConfiguration musicIdentityServiceConfiguration) {
        this.updateServiceWriteLock.lock();
        try {
            stopPollingInternal();
            this.updateService = this.updateServiceFactory.buildUpdateService(this.accountServiceFactory, this.accountCache, accountServiceConfiguration, stratusServiceConfiguration, this.accountStateChangeListenerDispatcher);
            if (musicIdentityServiceConfiguration != null) {
                this.accountService = this.accountServiceFactory.buildAccountService(stratusServiceConfiguration, musicIdentityServiceConfiguration, accountServiceConfiguration, this.accountCache);
            } else {
                this.accountService = this.accountServiceFactory.buildAccountService(stratusServiceConfiguration, accountServiceConfiguration, this.accountCache);
            }
            if (this.pollingEnabled) {
                startPollingInternal(this.pollingIntervalMs);
            } else if (!this.disableInitialUpdate) {
                updateAsync();
            }
        } finally {
            this.updateServiceWriteLock.unlock();
        }
    }

    public void updateUserMarketplace() throws MarketplaceProvisionFailedException {
        this.updateServiceWriteLock.lock();
        try {
            User cachedUser = this.accountCache.getCachedUser();
            if (cachedUser == null || cachedUser.getLibraryHomeMarketplace() == null) {
                new AccountCacheSaver(this.accountCache, new AccountStateComparator()).saveUser(this.accountService.getUserWithMarketplaceInfo());
            }
        } finally {
            this.updateServiceWriteLock.unlock();
        }
    }

    public AttemptAccountCreationResponse attemptAccountCreation() throws MusicAccountNotCreatedException {
        return this.accountService.attemptAccountCreation(this.accountCache.getCachedUser().getMusicTerritoryOfResidence());
    }

    public void onUserSignedOut() {
        this.updateServiceWriteLock.lock();
        try {
            stopPollingInternal();
            this.updateService = null;
            this.accountCache.clearCache();
        } finally {
            this.updateServiceWriteLock.unlock();
        }
    }

    private void startPollingInternal(long j) {
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                updateService.startPolling(j);
            }
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    private void stopPollingInternal() {
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                updateService.stopPolling();
            }
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public void registerListener(AccountStateChangeListener accountStateChangeListener) {
        this.accountStateChangeListenerDispatcher.registerListener(accountStateChangeListener);
    }

    public void deregisterListener(AccountStateChangeListener accountStateChangeListener) {
        this.accountStateChangeListenerDispatcher.deregisterListener(accountStateChangeListener);
    }

    public boolean acceptTermsOfUse() {
        boolean zAcceptTermsOfUse;
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                zAcceptTermsOfUse = updateService.acceptTermsOfUse();
                this.updateService.update();
            } else {
                zAcceptTermsOfUse = false;
            }
            return zAcceptTermsOfUse;
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public boolean catalogAuthorizeDevice() {
        boolean zCatalogAuthorizeDevice;
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                zCatalogAuthorizeDevice = updateService.catalogAuthorizeDevice();
                this.updateService.updateDevices();
            } else {
                zCatalogAuthorizeDevice = false;
            }
            return zCatalogAuthorizeDevice;
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public boolean updateExplicitLanguagePreference(boolean z) {
        boolean zUpdateExplicitLanguagePreference;
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                zUpdateExplicitLanguagePreference = updateService.updateExplicitLanguagePreference(z);
                updateUser();
            } else {
                zUpdateExplicitLanguagePreference = false;
            }
            return zUpdateExplicitLanguagePreference;
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public Marketplace getMarketplace() {
        try {
            return this.stratusMarketplaceProvider.getMarketplace();
        } catch (MarketplaceProvisionFailedException unused) {
            return this.marketplaceConfiguration.getDefaultMarketplace();
        }
    }

    public String getMusicTerritory() {
        try {
            return this.stratusMarketplaceProvider.getTerritory();
        } catch (MarketplaceProvisionFailedException unused) {
            return this.marketplaceConfiguration.getDefaultTerritory();
        }
    }

    public boolean updateTargetedAdsPreference(boolean z) {
        boolean zUpdateTargetedAdsPreference;
        this.updateServiceReadLock.lock();
        try {
            UpdateService updateService = this.updateService;
            if (updateService != null) {
                zUpdateTargetedAdsPreference = updateService.updateTargetedAdsPreference(z);
                updateUser();
            } else {
                zUpdateTargetedAdsPreference = false;
            }
            return zUpdateTargetedAdsPreference;
        } finally {
            this.updateServiceReadLock.unlock();
        }
    }

    public String fetchMusicRequestIdentityContextToken() {
        AccountService accountService = this.accountService;
        if (accountService != null) {
            return accountService.getMusicRequestIdentityContextToken();
        }
        return null;
    }
}
