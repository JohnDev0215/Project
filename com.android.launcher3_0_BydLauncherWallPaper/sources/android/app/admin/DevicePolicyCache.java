package android.app.admin;

import com.android.server.LocalServices;

/* JADX INFO: loaded from: classes.dex */
public abstract class DevicePolicyCache {
    public abstract int getPasswordQuality(int i);

    public abstract boolean isScreenCaptureAllowed(int i, boolean z);

    protected DevicePolicyCache() {
    }

    public static DevicePolicyCache getInstance() {
        DevicePolicyManagerInternal devicePolicyManagerInternal = (DevicePolicyManagerInternal) LocalServices.getService(DevicePolicyManagerInternal.class);
        if (devicePolicyManagerInternal != null) {
            return devicePolicyManagerInternal.getDevicePolicyCache();
        }
        return EmptyDevicePolicyCache.INSTANCE;
    }

    private static class EmptyDevicePolicyCache extends DevicePolicyCache {
        private static final EmptyDevicePolicyCache INSTANCE = new EmptyDevicePolicyCache();

        @Override // android.app.admin.DevicePolicyCache
        public int getPasswordQuality(int i) {
            return 0;
        }

        @Override // android.app.admin.DevicePolicyCache
        public boolean isScreenCaptureAllowed(int i, boolean z) {
            return true;
        }

        private EmptyDevicePolicyCache() {
        }
    }
}
