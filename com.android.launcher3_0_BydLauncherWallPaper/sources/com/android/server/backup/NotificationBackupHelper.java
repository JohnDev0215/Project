package com.android.server.backup;

import android.app.INotificationManager;
import android.app.backup.BlobBackupHelper;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationBackupHelper extends BlobBackupHelper {
    static final int BLOB_VERSION = 1;
    static final String KEY_NOTIFICATIONS = "notifications";
    private final int mUserId;
    static final String TAG = "NotifBackupHelper";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public NotificationBackupHelper(int i) {
        super(1, KEY_NOTIFICATIONS);
        this.mUserId = i;
    }

    @Override // android.app.backup.BlobBackupHelper
    protected byte[] getBackupPayload(String str) {
        if (!KEY_NOTIFICATIONS.equals(str)) {
            return null;
        }
        try {
            return INotificationManager.Stub.asInterface(ServiceManager.getService("notification")).getBackupPayload(this.mUserId);
        } catch (Exception unused) {
            Slog.e(TAG, "Couldn't communicate with notification manager");
            return null;
        }
    }

    @Override // android.app.backup.BlobBackupHelper
    protected void applyRestoredPayload(String str, byte[] bArr) {
        if (DEBUG) {
            Slog.v(TAG, "Got restore of " + str);
        }
        if (KEY_NOTIFICATIONS.equals(str)) {
            try {
                INotificationManager.Stub.asInterface(ServiceManager.getService("notification")).applyRestore(bArr, this.mUserId);
            } catch (Exception unused) {
                Slog.e(TAG, "Couldn't communicate with notification manager");
            }
        }
    }
}
