package android.os;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.ISystemConfig;
import android.util.ArraySet;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class SystemConfigManager {
    private static final String TAG = SystemConfigManager.class.getSimpleName();
    private final ISystemConfig mInterface = ISystemConfig.Stub.asInterface(ServiceManager.getService(Context.SYSTEM_CONFIG_SERVICE));

    public Set<String> getDisabledUntilUsedPreinstalledCarrierApps() {
        try {
            return new ArraySet(this.mInterface.getDisabledUntilUsedPreinstalledCarrierApps());
        } catch (RemoteException unused) {
            Log.e(TAG, "Caught remote exception");
            return Collections.emptySet();
        }
    }

    public Map<String, List<String>> getDisabledUntilUsedPreinstalledCarrierAssociatedApps() {
        try {
            return this.mInterface.getDisabledUntilUsedPreinstalledCarrierAssociatedApps();
        } catch (RemoteException unused) {
            Log.e(TAG, "Caught remote exception");
            return Collections.emptyMap();
        }
    }

    public Map<String, List<CarrierAssociatedAppEntry>> getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries() {
        try {
            return this.mInterface.getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries();
        } catch (RemoteException e) {
            Log.e(TAG, "Caught remote exception", e);
            return Collections.emptyMap();
        }
    }
}
