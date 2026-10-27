package com.android.car.storagemonitoring;

import android.hardware.health.V2_0.IHealth;
import android.hardware.health.V2_0.StorageInfo;
import android.os.RemoteException;
import android.util.Log;
import android.util.MutableInt;
import com.android.car.CarLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public class HealthServiceWearInfoProvider implements WearInformationProvider {
    private IHealthSupplier mHealthSupplier = new IHealthSupplier() { // from class: com.android.car.storagemonitoring.HealthServiceWearInfoProvider.1
    };
    private static final String INSTANCE_VENDOR = "default";
    private static final String INSTANCE_HEALTHD = "backup";
    private static final List<String> sAllInstances = Arrays.asList(INSTANCE_VENDOR, INSTANCE_HEALTHD);

    @Override // com.android.car.storagemonitoring.WearInformationProvider
    public WearInformation load() throws RemoteException {
        IHealth healthService = getHealthService();
        final MutableInt success = new MutableInt(1);
        final MutableInt foundInternalStorageDeviceInfo = new MutableInt(0);
        final MutableInt lifetimeA = new MutableInt(0);
        final MutableInt lifetimeB = new MutableInt(0);
        final MutableInt preEol = new MutableInt(0);
        IHealth.getStorageInfoCallback getStorageInfoCallback = new IHealth.getStorageInfoCallback() { // from class: com.android.car.storagemonitoring.HealthServiceWearInfoProvider.2
            @Override // android.hardware.health.V2_0.IHealth.getStorageInfoCallback
            public void onValues(int result, ArrayList<StorageInfo> value) {
                success.value = result;
                if (result == 0) {
                    int len = value.size();
                    for (int i = 0; i < len; i++) {
                        StorageInfo value2 = value.get(i);
                        if (value2.attr.isInternal) {
                            lifetimeA.value = value2.lifetimeA;
                            lifetimeB.value = value2.lifetimeB;
                            preEol.value = value2.eol;
                            foundInternalStorageDeviceInfo.value = 1;
                        }
                    }
                }
            }
        };
        if (healthService == null) {
            Log.w(CarLog.TAG_STORAGE, "No health service is available to fetch wear information.");
            return null;
        }
        try {
            healthService.getStorageInfo(getStorageInfoCallback);
            if (success.value != 0) {
                Log.w(CarLog.TAG_STORAGE, "Health service returned result :" + success.value);
                return null;
            }
            if (foundInternalStorageDeviceInfo.value == 0) {
                Log.w(CarLog.TAG_STORAGE, "Failed to find storage information forinternal storage device");
                return null;
            }
            return new WearInformation(lifetimeA.value, lifetimeB.value, preEol.value);
        } catch (Exception e) {
            Log.w(CarLog.TAG_STORAGE, "Failed to get storage information fromhealth service, exception :" + e);
            return null;
        }
    }

    private IHealth getHealthService() throws RemoteException {
        for (String name : sAllInstances) {
            IHealth newService = null;
            try {
                newService = this.mHealthSupplier.get(name);
            } catch (Exception e) {
            }
            if (newService != null) {
                return newService;
            }
        }
        return null;
    }

    public void setHealthSupplier(IHealthSupplier healthSupplier) {
        this.mHealthSupplier = healthSupplier;
    }

    interface IHealthSupplier {
        default IHealth get(String name) throws RemoteException, NoSuchElementException {
            return IHealth.getService(name, false);
        }
    }
}
