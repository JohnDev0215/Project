package com.amazon.music.account;

import android.content.SharedPreferences;
import java.util.concurrent.locks.ReadWriteLock;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class SharedPreferenceAccountCache implements AccountCache {
    private DeviceAuthorizations deviceAuthorizationsInMemory;
    private DeviceCapabilities deviceCapabilitiesInMemory;
    private Device deviceInMemory;
    private Boolean invokeWebview;
    private final ReadWriteLock lock;
    private User userInMemory;
    private final SharedPreferenceAccountCacheUtilsFactory utilsFactory;

    public SharedPreferenceAccountCache(SharedPreferenceAccountCacheUtilsFactory sharedPreferenceAccountCacheUtilsFactory) {
        this.utilsFactory = (SharedPreferenceAccountCacheUtilsFactory) Validate.notNull(sharedPreferenceAccountCacheUtilsFactory);
        this.lock = sharedPreferenceAccountCacheUtilsFactory.buildReentrantReadWriteLock();
    }

    @Override // com.amazon.music.account.AccountCache
    public User getCachedUser() {
        this.lock.readLock().lock();
        try {
            User userDeserialize = this.userInMemory;
            if (userDeserialize == null) {
                String string = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").getString("User", null);
                if (string != null) {
                    userDeserialize = this.utilsFactory.buildUserSerializer().deserialize(string);
                    this.userInMemory = userDeserialize;
                } else {
                    userDeserialize = null;
                }
            }
            return userDeserialize;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public Device getCachedDevice() {
        this.lock.readLock().lock();
        try {
            Device deviceDeserialize = this.deviceInMemory;
            if (deviceDeserialize == null) {
                String string = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").getString("Device", null);
                if (string != null) {
                    deviceDeserialize = this.utilsFactory.buildDeviceSerializer().deserialize(string);
                    this.deviceInMemory = deviceDeserialize;
                } else {
                    deviceDeserialize = null;
                }
            }
            return deviceDeserialize;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public DeviceAuthorizations getCachedDeviceAuthorizations() {
        this.lock.readLock().lock();
        try {
            DeviceAuthorizations deviceAuthorizationsDeserialize = this.deviceAuthorizationsInMemory;
            if (deviceAuthorizationsDeserialize == null) {
                String string = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").getString("DeviceAuthorizations", null);
                if (string != null) {
                    deviceAuthorizationsDeserialize = this.utilsFactory.buildDeviceAuthorizationsSerializer().deserialize(string);
                    this.deviceAuthorizationsInMemory = deviceAuthorizationsDeserialize;
                } else {
                    deviceAuthorizationsDeserialize = null;
                }
            }
            return deviceAuthorizationsDeserialize;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public DeviceCapabilities getCachedDeviceCapabilities() {
        this.lock.readLock().lock();
        try {
            DeviceCapabilities deviceCapabilitiesDeserialize = this.deviceCapabilitiesInMemory;
            if (deviceCapabilitiesDeserialize == null) {
                String string = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").getString("DeviceCapabilities", null);
                if (string != null) {
                    deviceCapabilitiesDeserialize = this.utilsFactory.buildDeviceCapabilitiesSerializer().deserialize(string);
                    this.deviceCapabilitiesInMemory = deviceCapabilitiesDeserialize;
                } else {
                    deviceCapabilitiesDeserialize = null;
                }
            }
            return deviceCapabilitiesDeserialize;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void setCachedUser(User user) {
        this.lock.writeLock().lock();
        try {
            this.userInMemory = user;
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.putString("User", this.utilsFactory.buildUserSerializer().serialize(user));
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void setCachedDevice(Device device) {
        this.lock.writeLock().lock();
        try {
            this.deviceInMemory = device;
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.putString("Device", this.utilsFactory.buildDeviceSerializer().serialize(device));
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void setCachedDeviceAuthorizations(DeviceAuthorizations deviceAuthorizations) {
        this.lock.writeLock().lock();
        try {
            this.deviceAuthorizationsInMemory = deviceAuthorizations;
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.putString("DeviceAuthorizations", this.utilsFactory.buildDeviceAuthorizationsSerializer().serialize(deviceAuthorizations));
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void setCachedDeviceCapabilities(DeviceCapabilities deviceCapabilities) {
        this.lock.writeLock().lock();
        try {
            this.deviceCapabilitiesInMemory = deviceCapabilities;
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.putString("DeviceCapabilities", this.utilsFactory.buildDeviceCapabilitiesSerializer().serialize(deviceCapabilities));
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void clearCache() {
        this.lock.writeLock().lock();
        try {
            this.userInMemory = null;
            this.deviceInMemory = null;
            this.deviceAuthorizationsInMemory = null;
            this.deviceCapabilitiesInMemory = null;
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.remove("User");
            editorEdit.remove("Device");
            editorEdit.remove("DeviceAuthorizations");
            editorEdit.remove("DeviceCapabilities");
            editorEdit.remove("InvokeAccountWorkflow");
            editorEdit.clear();
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public void setCachedInvokeAccountWorkflow(boolean z) {
        this.lock.writeLock().lock();
        try {
            this.invokeWebview = Boolean.valueOf(z);
            SharedPreferences.Editor editorEdit = this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").edit();
            editorEdit.putBoolean("InvokeAccountWorkflow", z);
            editorEdit.apply();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.amazon.music.account.AccountCache
    public boolean getCachedInvokeAccountWorkflow() {
        this.lock.readLock().lock();
        try {
            if (this.invokeWebview == null) {
                this.invokeWebview = Boolean.valueOf(this.utilsFactory.getSharedPrefs("DMMUserAccount_AccountCache").getBoolean("InvokeAccountWorkflow", false));
            }
            return this.invokeWebview.booleanValue();
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
