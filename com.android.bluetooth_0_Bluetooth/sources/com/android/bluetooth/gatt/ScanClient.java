package com.android.bluetooth.gatt;

import android.bluetooth.le.ResultStorageDescriptor;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.os.Binder;
import android.os.UserHandle;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class ScanClient {
    private static final ScanSettings DEFAULT_SCAN_SETTINGS = new ScanSettings.Builder().setScanMode(2).build();
    public boolean appDied;
    public int appUid;
    public List<String> associatedDevices;
    public boolean eligibleForSanitizedExposureNotification;
    public List<ScanFilter> filters;
    public boolean hasLocationPermission;
    public boolean hasNetworkSettingsPermission;
    public boolean hasNetworkSetupWizardPermission;
    public boolean hasScanWithoutLocationPermission;
    public boolean isQApp;
    public ScanSettings passiveSettings;
    public int scannerId;
    public ScanSettings settings;
    public AppScanStats stats;
    public List<List<ResultStorageDescriptor>> storages;
    public UserHandle userHandle;
    public UUID[] uuids;

    ScanClient(int scannerId) {
        this(scannerId, new UUID[0], DEFAULT_SCAN_SETTINGS, null, null);
    }

    ScanClient(int scannerId, UUID[] uuids) {
        this(scannerId, uuids, DEFAULT_SCAN_SETTINGS, null, null);
    }

    ScanClient(int scannerId, ScanSettings settings, List<ScanFilter> filters) {
        this(scannerId, new UUID[0], settings, filters, null);
    }

    ScanClient(int scannerId, ScanSettings settings, List<ScanFilter> filters, List<List<ResultStorageDescriptor>> storages) {
        this(scannerId, new UUID[0], settings, filters, storages);
    }

    private ScanClient(int scannerId, UUID[] uuids, ScanSettings settings, List<ScanFilter> filters, List<List<ResultStorageDescriptor>> storages) {
        this.stats = null;
        this.scannerId = scannerId;
        this.uuids = uuids;
        this.settings = settings;
        this.passiveSettings = null;
        this.filters = filters;
        this.storages = storages;
        this.appUid = Binder.getCallingUid();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScanClient other = (ScanClient) obj;
        if (this.scannerId == other.scannerId) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.scannerId));
    }
}
