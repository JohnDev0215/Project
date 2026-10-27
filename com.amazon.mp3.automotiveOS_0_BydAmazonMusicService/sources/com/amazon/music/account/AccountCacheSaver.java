package com.amazon.music.account;

import java.util.Collections;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class AccountCacheSaver {
    private final AccountCache accountCache;
    private final AccountStateComparator accountStateComparator;

    public AccountCacheSaver(AccountCache accountCache, AccountStateComparator accountStateComparator) {
        this.accountCache = (AccountCache) Validate.notNull(accountCache);
        this.accountStateComparator = (AccountStateComparator) Validate.notNull(accountStateComparator);
    }

    public Set<AccountStateField> saveUser(User user) {
        if (user == null) {
            return Collections.emptySet();
        }
        User cachedUser = this.accountCache.getCachedUser();
        this.accountCache.setCachedUser(user);
        return this.accountStateComparator.getDifferences(user, cachedUser);
    }

    public Set<AccountStateField> saveDevice(Device device) {
        if (device == null) {
            return Collections.emptySet();
        }
        Device cachedDevice = this.accountCache.getCachedDevice();
        this.accountCache.setCachedDevice(device);
        return this.accountStateComparator.getDifferences(device, cachedDevice);
    }

    public Set<AccountStateField> saveDeviceAuthorizations(DeviceAuthorizations deviceAuthorizations) {
        if (deviceAuthorizations == null) {
            return Collections.emptySet();
        }
        DeviceAuthorizations cachedDeviceAuthorizations = this.accountCache.getCachedDeviceAuthorizations();
        this.accountCache.setCachedDeviceAuthorizations(deviceAuthorizations);
        return this.accountStateComparator.getDifferences(deviceAuthorizations, cachedDeviceAuthorizations);
    }

    public Set<AccountStateField> saveDeviceCapabilities(DeviceCapabilities deviceCapabilities) {
        if (deviceCapabilities == null) {
            return Collections.emptySet();
        }
        DeviceCapabilities cachedDeviceCapabilities = this.accountCache.getCachedDeviceCapabilities();
        this.accountCache.setCachedDeviceCapabilities(deviceCapabilities);
        return this.accountStateComparator.getDifferences(deviceCapabilities, cachedDeviceCapabilities);
    }

    public void saveInvokeAccountWebview(boolean z) {
        this.accountCache.setCachedInvokeAccountWorkflow(z);
    }
}
