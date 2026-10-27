package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.INetd;
import android.net.Uri;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpCoverArtStorage {
    private final Context mContext;
    private final Map<BluetoothDevice, Map<String, Bitmap>> mDeviceImages = new ConcurrentHashMap(1);
    private static final String TAG = "AvrcpCoverArtStorage";
    private static final boolean DBG = Log.isLoggable(TAG, 3);

    public AvrcpCoverArtStorage(Context context) {
        this.mContext = context;
    }

    public boolean doesImageExist(BluetoothDevice device, String imageUuid) {
        Map<String, Bitmap> images;
        if (device == null || imageUuid == null || INetd.NEXTHOP_NONE.equals(imageUuid) || (images = this.mDeviceImages.get(device)) == null) {
            return false;
        }
        return images.containsKey(imageUuid);
    }

    public Bitmap getImage(BluetoothDevice device, String imageUuid) {
        Map<String, Bitmap> images;
        if (device == null || imageUuid == null || INetd.NEXTHOP_NONE.equals(imageUuid) || (images = this.mDeviceImages.get(device)) == null) {
            return null;
        }
        return images.get(imageUuid);
    }

    public Uri addImage(BluetoothDevice device, String imageUuid, Bitmap image) {
        debug("Storing image '" + imageUuid + "' from device " + device);
        if (device == null || imageUuid == null || INetd.NEXTHOP_NONE.equals(imageUuid) || image == null) {
            debug("Cannot store image. Improper aruguments");
            return null;
        }
        Map<String, Bitmap> newImageSet = new ConcurrentHashMap<>(1);
        Map<String, Bitmap> images = this.mDeviceImages.putIfAbsent(device, newImageSet);
        if (images == null) {
            newImageSet.put(imageUuid, image);
        } else {
            images.put(imageUuid, image);
        }
        Uri uri = AvrcpCoverArtProvider.getImageUri(device, imageUuid);
        this.mContext.getContentResolver().notifyChange(uri, null);
        debug("Image '" + imageUuid + "' stored for device '" + device.getAddressOnlyforLog() + "'");
        return uri;
    }

    public void removeImage(BluetoothDevice device, String imageUuid) {
        Map<String, Bitmap> images;
        debug("Removing image '" + imageUuid + "' from device " + device);
        if (device == null || imageUuid == null || INetd.NEXTHOP_NONE.equals(imageUuid) || (images = this.mDeviceImages.get(device)) == null) {
            return;
        }
        images.remove(imageUuid);
        if (images.size() == 0) {
            this.mDeviceImages.remove(device);
        }
        debug("Image '" + imageUuid + "' removed for device '" + device.getAddressOnlyforLog() + "'");
    }

    public void removeImagesForDevice(BluetoothDevice device) {
        if (device == null) {
            return;
        }
        debug("Remove cover art for device " + device.getAddressOnlyforLog());
        this.mDeviceImages.remove(device);
    }

    public void clear() {
        debug("Clearing all images");
        this.mDeviceImages.clear();
    }

    public String toString() {
        String s = "CoverArtStorage:\n";
        for (BluetoothDevice device : this.mDeviceImages.keySet()) {
            Map<String, Bitmap> images = this.mDeviceImages.get(device);
            String s2 = s + "  " + device.getAddressOnlyforLog() + " (" + images.size() + "):";
            for (String uuid : images.keySet()) {
                s2 = s2 + "\n    " + uuid;
            }
            s = s2 + "\n";
        }
        return s;
    }

    private void debug(String msg) {
        if (DBG) {
            Log.d(TAG, msg);
        }
    }

    private void error(String msg) {
        Log.e(TAG, msg);
    }
}
