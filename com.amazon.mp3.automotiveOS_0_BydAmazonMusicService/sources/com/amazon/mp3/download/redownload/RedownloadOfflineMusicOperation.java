package com.amazon.mp3.download.redownload;

import android.content.Context;
import android.os.Environment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.music.externalstorage.DeviceStorageConfiguration;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/download/redownload/RedownloadOfflineMusicOperation;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "cancel", "", "context", "Landroid/content/Context;", "getStorageLocationFileManager", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "redownload", "Lcom/amazon/mp3/download/redownload/Result;", "shouldSendServiceInitiatedEvent", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedownloadOfflineMusicOperation {
    private final String TAG = RedownloadOfflineMusicOperation.class.getSimpleName();

    /* JADX WARN: Removed duplicated region for block: B:29:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x017a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0180 A[Catch: all -> 0x01c1, TryCatch #0 {, blocks: (B:4:0x0005, B:14:0x00ba, B:32:0x0174, B:37:0x0180, B:40:0x0187, B:41:0x019d, B:43:0x01a1, B:44:0x01ab, B:52:0x01b4, B:53:0x01b7, B:59:0x01bd, B:60:0x01c0, B:49:0x01b1, B:5:0x0019, B:7:0x004c, B:9:0x007e, B:10:0x0086, B:12:0x00b7, B:56:0x01ba, B:15:0x00c1, B:17:0x00e2, B:19:0x0109, B:20:0x0129, B:30:0x0171, B:28:0x015f), top: B:64:0x0005, inners: #1, #2, #3, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0185 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01a1 A[Catch: all -> 0x01c1, TryCatch #0 {, blocks: (B:4:0x0005, B:14:0x00ba, B:32:0x0174, B:37:0x0180, B:40:0x0187, B:41:0x019d, B:43:0x01a1, B:44:0x01ab, B:52:0x01b4, B:53:0x01b7, B:59:0x01bd, B:60:0x01c0, B:49:0x01b1, B:5:0x0019, B:7:0x004c, B:9:0x007e, B:10:0x0086, B:12:0x00b7, B:56:0x01ba, B:15:0x00c1, B:17:0x00e2, B:19:0x0109, B:20:0x0129, B:30:0x0171, B:28:0x015f), top: B:64:0x0005, inners: #1, #2, #3, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01ab A[Catch: all -> 0x01c1, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0005, B:14:0x00ba, B:32:0x0174, B:37:0x0180, B:40:0x0187, B:41:0x019d, B:43:0x01a1, B:44:0x01ab, B:52:0x01b4, B:53:0x01b7, B:59:0x01bd, B:60:0x01c0, B:49:0x01b1, B:5:0x0019, B:7:0x004c, B:9:0x007e, B:10:0x0086, B:12:0x00b7, B:56:0x01ba, B:15:0x00c1, B:17:0x00e2, B:19:0x0109, B:20:0x0129, B:30:0x0171, B:28:0x015f), top: B:64:0x0005, inners: #1, #2, #3, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized com.amazon.mp3.download.redownload.Result redownload(android.content.Context r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.redownload.RedownloadOfflineMusicOperation.redownload(android.content.Context, boolean):com.amazon.mp3.download.redownload.Result");
    }

    public final synchronized void cancel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getResources().getString(R.string.dmusic_redownload_group_name_tracks);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…wnload_group_name_tracks)");
        String string2 = context.getResources().getString(R.string.dmusic_redownload_group_name_prime_playlists);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…oup_name_prime_playlists)");
        MusicDownloader.getInstance(context).cancel(string);
        MusicDownloader.getInstance(context).cancel(string2);
    }

    private final StorageLocationFileManager getStorageLocationFileManager(Context context) {
        StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(context, new DeviceStorageConfiguration(context, Environment.DIRECTORY_MUSIC), Environment.DIRECTORY_MUSIC);
        Intrinsics.checkNotNullExpressionValue(storageLocationFileManagerCreate, "create(context,\n        …ironment.DIRECTORY_MUSIC)");
        return storageLocationFileManagerCreate;
    }
}
