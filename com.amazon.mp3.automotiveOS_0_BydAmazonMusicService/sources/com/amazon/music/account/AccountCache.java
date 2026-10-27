package com.amazon.music.account;

/* JADX INFO: loaded from: classes4.dex */
interface AccountCache {
    void clearCache();

    Device getCachedDevice();

    DeviceAuthorizations getCachedDeviceAuthorizations();

    DeviceCapabilities getCachedDeviceCapabilities();

    boolean getCachedInvokeAccountWorkflow();

    User getCachedUser();

    void setCachedDevice(Device device);

    void setCachedDeviceAuthorizations(DeviceAuthorizations deviceAuthorizations);

    void setCachedDeviceCapabilities(DeviceCapabilities deviceCapabilities);

    void setCachedInvokeAccountWorkflow(boolean z);

    void setCachedUser(User user);
}
