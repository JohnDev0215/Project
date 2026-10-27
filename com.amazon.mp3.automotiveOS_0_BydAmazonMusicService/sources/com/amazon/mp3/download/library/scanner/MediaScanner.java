package com.amazon.mp3.download.library.scanner;

import android.content.Context;
import android.media.MediaScannerConnection;
import com.amazon.mp3.util.Log;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class MediaScanner {
    public static void scan(Context context, String filepath) {
        scan(context, filepath, voMimeTypes.VOAUDIO_MPEG, null);
    }

    public static void scan(Context context, String filepath, TrackScanCompletedListener callback) {
        scan(context, filepath, voMimeTypes.VOAUDIO_MPEG, callback);
    }

    public static void scan(Context context, String filepath, String mimeType, TrackScanCompletedListener callback) {
        scanFile(context, new SyncMediaScannerClient(context, filepath, mimeType, callback));
    }

    public static void scanSync(Context context, String filepath) {
        scanSync(context, filepath, voMimeTypes.VOAUDIO_MPEG, null);
    }

    public static void scanSync(Context context, String filepath, String mimeType, TrackScanCompletedListener callback) {
        scanFileSync(context, new SyncMediaScannerClient(context, filepath, mimeType, callback));
    }

    private static void scanFile(Context context, IMediaScannerClient client) {
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, client);
        client.setConnection(mediaScannerConnection);
        mediaScannerConnection.connect();
    }

    private static void scanFileSync(Context context, IMediaScannerClient client) {
        synchronized (client.getLockObject()) {
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, client);
            client.setConnection(mediaScannerConnection);
            mediaScannerConnection.connect();
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            long j = 30000 + timeInMillis;
            while (j > timeInMillis && !client.isCompleted()) {
                try {
                    client.getLockObject().wait(j - timeInMillis);
                } catch (InterruptedException e) {
                    Log.debug("MediaScanner", "InterruptedException", e);
                }
                timeInMillis = Calendar.getInstance().getTimeInMillis();
            }
        }
    }
}
