package com.android.providers.media;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Trace;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.app.JobIntentService;
import com.android.providers.media.util.FileUtils;
import com.android.providers.media.util.Logging;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MediaService extends JobIntentService {
    private static final int JOB_ID = -300;

    public static void enqueueWork(Context context, Intent intent) {
        JobIntentService.enqueueWork(context, (Class<?>) MediaService.class, JOB_ID, intent);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        StringBuilder sb;
        Trace.beginSection(intent.getAction());
        if (Log.isLoggable(Logging.TAG, 4)) {
            Log.i(Logging.TAG, "Begin " + intent);
        }
        try {
            try {
                String action = intent.getAction();
                byte b = -1;
                switch (action.hashCode()) {
                    case -1514214344:
                        if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                            b = 4;
                        }
                        break;
                    case -19011148:
                        if (action.equals("android.intent.action.LOCALE_CHANGED")) {
                            b = 0;
                        }
                        break;
                    case 267468725:
                        if (action.equals("android.intent.action.PACKAGE_DATA_CLEARED")) {
                            b = 2;
                        }
                        break;
                    case 852070077:
                        if (action.equals("android.intent.action.MEDIA_SCANNER_SCAN_FILE")) {
                            b = 3;
                        }
                        break;
                    case 1580442797:
                        if (action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")) {
                            b = 1;
                        }
                        break;
                }
                if (b == 0) {
                    onLocaleChanged();
                } else if (b == 1 || b == 2) {
                    onPackageOrphaned(intent.getData().getSchemeSpecificPart());
                } else if (b == 3) {
                    onScanFile(this, intent.getData());
                } else if (b == 4) {
                    onScanVolume(this, intent.getData(), 1);
                } else {
                    Log.w(Logging.TAG, "Unknown intent " + intent);
                }
            } catch (Exception e) {
                Log.w(Logging.TAG, "Failed operation " + intent, e);
                if (Log.isLoggable(Logging.TAG, 4)) {
                    sb = new StringBuilder();
                }
            }
            if (Log.isLoggable(Logging.TAG, 4)) {
                sb = new StringBuilder();
                sb.append("End ");
                sb.append(intent);
                Log.i(Logging.TAG, sb.toString());
            }
            Trace.endSection();
        } catch (Throwable th) {
            if (Log.isLoggable(Logging.TAG, 4)) {
                Log.i(Logging.TAG, "End " + intent);
            }
            Trace.endSection();
            throw th;
        }
    }

    private void onLocaleChanged() {
        ContentProviderClient contentProviderClientAcquireContentProviderClient = getContentResolver().acquireContentProviderClient("media");
        try {
            ((MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider()).onLocaleChanged();
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void onPackageOrphaned(String str) {
        ContentProviderClient contentProviderClientAcquireContentProviderClient = getContentResolver().acquireContentProviderClient("media");
        try {
            ((MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider()).onPackageOrphaned(str);
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static void onScanVolume(Context context, Uri uri, int i) throws IOException {
        onScanVolume(context, FileUtils.getVolumeName(context, new File(uri.getPath()).getCanonicalFile()), i);
    }

    public static void onScanVolume(Context context, String str, int i) throws IOException {
        File volumePath;
        if (!"internal".equals(str)) {
            onScanVolume(context, "internal", i);
            RingtoneManager.ensureDefaultRingtones(context);
        }
        Uri uriFromFile = ("internal".equals(str) || (volumePath = FileUtils.getVolumePath(context, str)) == null) ? null : Uri.fromFile(volumePath);
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient("media");
            try {
                MediaProvider mediaProvider = (MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider();
                mediaProvider.attachVolume(str, true);
                ContentResolver contentResolverWrap = ContentResolver.wrap(contentProviderClientAcquireContentProviderClient.getLocalContentProvider());
                ContentValues contentValues = new ContentValues();
                contentValues.put("volume", str);
                Uri uriInsert = contentResolverWrap.insert(MediaStore.getMediaScannerUri(), contentValues);
                if (uriFromFile != null) {
                    context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_STARTED", uriFromFile));
                }
                Iterator<File> it = FileUtils.getVolumeScanPaths(context, str).iterator();
                while (it.hasNext()) {
                    mediaProvider.scanDirectory(it.next(), i);
                }
                contentResolverWrap.delete(uriInsert, null, null);
                if (contentProviderClientAcquireContentProviderClient != null) {
                    contentProviderClientAcquireContentProviderClient.close();
                }
            } finally {
            }
        } finally {
            if (uriFromFile != null) {
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_FINISHED", uriFromFile));
            }
        }
    }

    private static Uri onScanFile(Context context, Uri uri) throws IOException {
        File canonicalFile = new File(uri.getPath()).getCanonicalFile();
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient("media");
        try {
            Uri uriScanFile = ((MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider()).scanFile(canonicalFile, 2);
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
            return uriScanFile;
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
