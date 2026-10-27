package com.amazon.music.externalstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class StorageStateProvider {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) StorageStateProvider.class);
    private final Context context;

    public StorageStateProvider(Context context) {
        this.context = context;
    }

    public StorageState getStorageLocationState(StorageLocation storageLocation, String str) throws ExternalStorageNotSupportedException {
        if (storageLocation == (Environment.isExternalStorageEmulated() ? StorageLocation.DEVICE : StorageLocation.SDCARD)) {
            return StorageState.STORAGE_MOUNTED;
        }
        updateSecondaryMediaState(str);
        return getLastKnownSecondaryMediaState();
    }

    private StorageState getLastKnownSecondaryMediaState() {
        return StorageState.lookupByCode(this.context.getSharedPreferences("ExternalStorageUtilPrefs", 0).getInt("ExternaStorageLastKnownStateKey", -1));
    }

    protected void setLastKnownSecondaryMediaState(StorageState storageState) {
        SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("ExternalStorageUtilPrefs", 0).edit();
        editorEdit.putInt("ExternaStorageLastKnownStateKey", storageState.getCode());
        editorEdit.apply();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateSecondaryMediaState(java.lang.String r3) {
        /*
            r2 = this;
            boolean r0 = r2.isExternalStorageMounted()
            r1 = 1
            if (r0 == 0) goto L15
            android.content.Context r0 = r2.context
            java.io.File[] r3 = r0.getExternalFilesDirs(r3)
            int r0 = r3.length
            if (r0 <= r1) goto L15
            r3 = r3[r1]
            if (r3 == 0) goto L15
            goto L16
        L15:
            r1 = 0
        L16:
            if (r1 == 0) goto L1b
            com.amazon.music.externalstorage.StorageState r3 = com.amazon.music.externalstorage.StorageState.STORAGE_MOUNTED
            goto L1d
        L1b:
            com.amazon.music.externalstorage.StorageState r3 = com.amazon.music.externalstorage.StorageState.STORAGE_REMOVED
        L1d:
            r2.setLastKnownSecondaryMediaState(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.externalstorage.StorageStateProvider.updateSecondaryMediaState(java.lang.String):void");
    }

    private boolean isExternalStorageMounted() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            LOG.error("Exception occurred when trying to read externalStorageState", (Throwable) e);
            return false;
        }
    }
}
