package com.android.providers.media.fuse;

import android.content.ContentProviderClient;
import android.os.OperationCanceledException;
import android.os.ParcelFileDescriptor;
import android.service.storage.ExternalStorageService;
import android.util.Log;
import com.android.providers.media.MediaProvider;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ExternalStorageServiceImpl extends ExternalStorageService {
    private static final String TAG = "ExternalStorageServiceImpl";
    private static final Object sLock = new Object();
    private static final Map<String, FuseDaemon> sFuseDaemons = new HashMap();

    public void onStartSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, File file, File file2) {
        MediaProvider mediaProvider = getMediaProvider();
        synchronized (sLock) {
            if (sFuseDaemons.containsKey(str)) {
                Log.w(TAG, "Session already started with id: " + str);
            } else {
                Log.i(TAG, "Starting session for id: " + str);
                FuseDaemon fuseDaemon = new FuseDaemon(mediaProvider, this, parcelFileDescriptor, str, file.getPath());
                fuseDaemon.start();
                sFuseDaemons.put(str, fuseDaemon);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onVolumeStateChanged(android.os.storage.StorageVolume r9) throws java.io.IOException {
        /*
            r8 = this;
            com.android.providers.media.MediaProvider r8 = r8.getMediaProvider()
            java.lang.String r0 = r9.getMediaStoreVolumeName()
            java.lang.String r1 = r9.getState()
            int r2 = r1.hashCode()
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r2) {
                case -1792139919: goto L41;
                case -1340233281: goto L37;
                case 1091836000: goto L2d;
                case 1203725746: goto L23;
                case 1242932856: goto L19;
                default: goto L18;
            }
        L18:
            goto L4b
        L19:
            java.lang.String r2 = "mounted"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4b
            r1 = r3
            goto L4c
        L23:
            java.lang.String r2 = "bad_removal"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4b
            r1 = r4
            goto L4c
        L2d:
            java.lang.String r2 = "removed"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4b
            r1 = r5
            goto L4c
        L37:
            java.lang.String r2 = "unmounted"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4b
            r1 = r7
            goto L4c
        L41:
            java.lang.String r2 = "ejecting"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4b
            r1 = r6
            goto L4c
        L4b:
            r1 = -1
        L4c:
            if (r1 == 0) goto L7d
            if (r1 == r7) goto L79
            if (r1 == r6) goto L79
            if (r1 == r5) goto L79
            if (r1 == r4) goto L79
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Ignoring volume state for vol:"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = ". State: "
            r1.append(r0)
            java.lang.String r9 = r9.getState()
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            java.lang.String r0 = "ExternalStorageServiceImpl"
            android.util.Log.i(r0, r9)
            goto L80
        L79:
            r8.detachVolume(r0)
            goto L80
        L7d:
            r8.attachVolume(r0, r3)
        L80:
            r8.updateVolumes()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.fuse.ExternalStorageServiceImpl.onVolumeStateChanged(android.os.storage.StorageVolume):void");
    }

    public void onEndSession(String str) {
        FuseDaemon fuseDaemonOnExitSession = onExitSession(str);
        if (fuseDaemonOnExitSession == null) {
            Log.w(TAG, "Session already ended with id: " + str);
            return;
        }
        Log.i(TAG, "Ending session for id: " + str);
        fuseDaemonOnExitSession.waitForExit();
    }

    public FuseDaemon onExitSession(String str) {
        FuseDaemon fuseDaemonRemove;
        Log.i(TAG, "Exiting session for id: " + str);
        synchronized (sLock) {
            fuseDaemonRemove = sFuseDaemons.remove(str);
        }
        return fuseDaemonRemove;
    }

    public static FuseDaemon getFuseDaemon(String str) {
        FuseDaemon fuseDaemon;
        synchronized (sLock) {
            fuseDaemon = sFuseDaemons.get(str);
        }
        return fuseDaemon;
    }

    private MediaProvider getMediaProvider() {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = getContentResolver().acquireContentProviderClient("media");
            try {
                MediaProvider mediaProvider = (MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider();
                if (contentProviderClientAcquireContentProviderClient != null) {
                    contentProviderClientAcquireContentProviderClient.close();
                }
                return mediaProvider;
            } finally {
            }
        } catch (OperationCanceledException e) {
            throw new IllegalStateException("Failed to acquire MediaProvider", e);
        }
    }
}
