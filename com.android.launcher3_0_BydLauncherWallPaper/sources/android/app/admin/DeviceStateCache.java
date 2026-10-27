package android.app.admin;

import com.android.server.LocalServices;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeviceStateCache {
    public abstract boolean isDeviceProvisioned();

    protected DeviceStateCache() {
    }

    public static DeviceStateCache getInstance() {
        DevicePolicyManagerInternal devicePolicyManagerInternal = (DevicePolicyManagerInternal) LocalServices.getService(DevicePolicyManagerInternal.class);
        if (devicePolicyManagerInternal != null) {
            return devicePolicyManagerInternal.getDeviceStateCache();
        }
        return EmptyDeviceStateCache.INSTANCE;
    }

    private static class EmptyDeviceStateCache extends DeviceStateCache {
        private static final EmptyDeviceStateCache INSTANCE = new EmptyDeviceStateCache();

        @Override // android.app.admin.DeviceStateCache
        public boolean isDeviceProvisioned() {
            return false;
        }

        private EmptyDeviceStateCache() {
        }
    }
}
