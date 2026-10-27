package com.android.server.backup;

import android.app.backup.BlobBackupHelper;
import android.os.UserHandle;
import android.permission.PermissionManagerInternal;
import android.util.Slog;
import com.android.server.LocalServices;

/* JADX INFO: loaded from: classes4.dex */
public class PermissionBackupHelper extends BlobBackupHelper {
    private static final boolean DEBUG = false;
    private static final String KEY_PERMISSIONS = "permissions";
    private static final int STATE_VERSION = 1;
    private static final String TAG = "PermissionBackup";
    private final PermissionManagerInternal mPermissionManager;
    private final UserHandle mUser;

    public PermissionBackupHelper(int i) {
        super(1, "permissions");
        this.mUser = UserHandle.of(i);
        this.mPermissionManager = (PermissionManagerInternal) LocalServices.getService(PermissionManagerInternal.class);
    }

    @Override // android.app.backup.BlobBackupHelper
    protected byte[] getBackupPayload(String str) {
        byte b = -1;
        try {
            if (str.hashCode() == 1133704324 && str.equals("permissions")) {
                b = 0;
            }
            if (b == 0) {
                return this.mPermissionManager.backupRuntimePermissions(this.mUser);
            }
            Slog.w(TAG, "Unexpected backup key " + str);
            return null;
        } catch (Exception unused) {
            Slog.e(TAG, "Unable to store payload " + str);
            return null;
        }
    }

    @Override // android.app.backup.BlobBackupHelper
    protected void applyRestoredPayload(String str, byte[] bArr) {
        byte b = -1;
        try {
            if (str.hashCode() == 1133704324 && str.equals("permissions")) {
                b = 0;
            }
            if (b == 0) {
                this.mPermissionManager.restoreRuntimePermissions(bArr, this.mUser);
            } else {
                Slog.w(TAG, "Unexpected restore key " + str);
            }
        } catch (Exception unused) {
            Slog.w(TAG, "Unable to restore key " + str);
        }
    }
}
