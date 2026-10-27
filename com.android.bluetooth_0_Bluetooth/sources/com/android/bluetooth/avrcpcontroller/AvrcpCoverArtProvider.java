package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.INetd;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpCoverArtProvider extends ContentProvider {
    static final String AUTHORITY = "com.android.bluetooth.avrcpcontroller.AvrcpCoverArtProvider";
    private BluetoothAdapter mAdapter;
    private static final String TAG = "AvrcpCoverArtProvider";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    static final Uri CONTENT_URI = Uri.parse("content://com.android.bluetooth.avrcpcontroller.AvrcpCoverArtProvider");

    public static Uri getImageUri(BluetoothDevice device, String imageUuid) {
        if (device == null || imageUuid == null || INetd.NEXTHOP_NONE.equals(imageUuid)) {
            return null;
        }
        Uri uri = CONTENT_URI.buildUpon().appendQueryParameter("device", device.getAddress()).appendQueryParameter("uuid", imageUuid).build();
        debug("getImageUri -> " + uri.toString());
        return uri;
    }

    private Bitmap getImage(BluetoothDevice device, String imageUuid) {
        AvrcpControllerService service = AvrcpControllerService.getAvrcpControllerService();
        if (service == null) {
            debug("Failed to get service, cover art not available");
            return null;
        }
        AvrcpCoverArtManager manager = service.getCoverArtManager();
        if (manager == null) {
            debug("Failed to get cover art manager. Cover art may not be enabled.");
            return null;
        }
        return manager.getImage(device, imageUuid);
    }

    private ParcelFileDescriptor getImageDescriptor(BluetoothDevice device, String imageUuid) throws IOException {
        debug("getImageDescriptor(" + device.getAddressOnlyforLog() + ", " + imageUuid + ")");
        final Bitmap image = getImage(device, imageUuid);
        if (image == null) {
            debug("Could not get requested image");
            throw new FileNotFoundException();
        }
        final ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
        Thread transferThread = new Thread() { // from class: com.android.bluetooth.avrcpcontroller.AvrcpCoverArtProvider.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    FileOutputStream fout = new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]);
                    image.compress(Bitmap.CompressFormat.PNG, 100, fout);
                    fout.flush();
                    fout.close();
                } catch (IOException e) {
                }
            }
        };
        transferThread.start();
        return pipe[0];
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        debug("openFile(" + uri + ", '" + mode + "')");
        try {
            String address = uri.getQueryParameter("device");
            String imageUuid = uri.getQueryParameter("uuid");
            try {
                BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
                try {
                    ParcelFileDescriptor pfd = getImageDescriptor(device, imageUuid);
                    return pfd;
                } catch (IOException e) {
                    debug("Failed to create inputstream from Bitmap");
                    throw new FileNotFoundException();
                }
            } catch (IllegalArgumentException e2) {
                throw new FileNotFoundException();
            }
        } catch (NullPointerException e3) {
            throw new FileNotFoundException();
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    private static void debug(String msg) {
        if (DBG) {
            Log.d(TAG, msg);
        }
    }
}
