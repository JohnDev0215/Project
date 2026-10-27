package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemProperties;
import android.util.Log;
import com.android.bluetooth.avrcpcontroller.AvrcpBipClient;
import com.android.bluetooth.avrcpcontroller.BipImageDescriptor;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpCoverArtManager {
    public static final String AVRCP_CONTROLLER_COVER_ART_IMGHEIGHT = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_HEIGHT";
    public static final String AVRCP_CONTROLLER_COVER_ART_IMGMAXSIZE = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_MAXSIZE";
    public static final String AVRCP_CONTROLLER_COVER_ART_IMGTYPE = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_TYPE";
    public static final String AVRCP_CONTROLLER_COVER_ART_IMGWIDTH = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_WIDTH";
    public static final String AVRCP_CONTROLLER_COVER_ART_MIMETYPE = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_MIMETYPE";
    public static final String AVRCP_CONTROLLER_COVER_ART_SCHEME = "persist.bluetooth.avrcpcontroller.BIP_DOWNLOAD_SCHEME";
    public static final int IMAGE_HEIGHT_DEFAULT = 500;
    public static final int IMAGE_MAXSIZE_DEFAULT = 200000;
    public static final String IMAGE_TYPE_DEFAULT = "Image";
    public static final int IMAGE_WIDTH_DEFAULT = 500;
    public static final String MIMETYPE_DEFAULT = "JPEG";
    public static final String SCHEME_DEFUALT = "thumbnail";
    public static final String SCHEME_NATIVE = "native";
    public static final String SCHEME_THUMBNAIL = "thumbnail";
    public static final String TYPE_IMAGE = "Image";
    public static final String TYPE_THUMBNAIL = "Thumbnail";
    public static final String TYPE_THUMBNAILLINKED = "ThumbnailLinked";
    private final Callback mCallback;
    private final AvrcpCoverArtStorage mCoverArtStorage;
    private String mDownloadScheme;
    private int mHeight;
    private int mMaxSize;
    private int mMimeType;
    private final AvrcpControllerService mService;
    private String mType;
    private int mWidth;
    private static final String TAG = "AvrcpCoverArtManager";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    protected final Map<BluetoothDevice, AvrcpBipClient> mClients = new ConcurrentHashMap(1);
    private Map<BluetoothDevice, AvrcpBipSession> mBipSessions = new ConcurrentHashMap(1);

    interface Callback {
        void onImageDownloadComplete(BluetoothDevice bluetoothDevice, DownloadEvent downloadEvent);
    }

    public class DownloadEvent {
        final String mImageUuid;
        final Uri mUri;

        public DownloadEvent(String uuid, Uri uri) {
            this.mImageUuid = uuid;
            this.mUri = uri;
        }

        public String getUuid() {
            return this.mImageUuid;
        }

        public Uri getUri() {
            return this.mUri;
        }
    }

    private class AvrcpBipSession {
        private final BluetoothDevice mDevice;
        private Map<String, String> mUuids = new ConcurrentHashMap(1);
        private Map<String, String> mHandles = new ConcurrentHashMap(1);

        AvrcpBipSession(BluetoothDevice device) {
            this.mDevice = device;
        }

        public String getHandleUuid(String handle) {
            if (handle == null) {
                return null;
            }
            String newUuid = UUID.randomUUID().toString();
            String existingUuid = this.mUuids.putIfAbsent(handle, newUuid);
            if (existingUuid != null) {
                return existingUuid;
            }
            this.mHandles.put(newUuid, handle);
            return newUuid;
        }

        public String getUuidHandle(String uuid) {
            return this.mHandles.get(uuid);
        }

        public void clearHandleUuids() {
            this.mUuids.clear();
            this.mHandles.clear();
        }

        public Set<String> getSessionHandles() {
            return this.mUuids.keySet();
        }
    }

    public AvrcpCoverArtManager(AvrcpControllerService service, Callback callback) {
        this.mService = service;
        this.mCoverArtStorage = new AvrcpCoverArtStorage(this.mService);
        this.mCallback = callback;
        updateImageProperties();
        this.mCoverArtStorage.clear();
    }

    public synchronized boolean connect(BluetoothDevice device, int psm) {
        debug("Connect " + device.getAddressOnlyforLog() + ", psm: " + psm);
        if (this.mClients.containsKey(device)) {
            return false;
        }
        AvrcpBipClient client = new AvrcpBipClient(device, psm, new BipClientCallback(device));
        this.mClients.put(device, client);
        this.mBipSessions.put(device, new AvrcpBipSession(device));
        return true;
    }

    public synchronized boolean refreshSession(BluetoothDevice device) {
        debug("Refresh OBEX session for " + device.getAddressOnlyforLog());
        AvrcpBipClient client = getClient(device);
        if (client == null) {
            warn("No client for " + device.getAddressOnlyforLog());
            return false;
        }
        client.refreshSession();
        return true;
    }

    public synchronized boolean disconnect(BluetoothDevice device) {
        debug("Disconnect " + device.getAddressOnlyforLog());
        AvrcpBipClient client = getClient(device);
        if (client == null) {
            warn("No client for " + device.getAddressOnlyforLog());
            return false;
        }
        client.shutdown();
        this.mClients.remove(device);
        this.mBipSessions.remove(device);
        this.mCoverArtStorage.removeImagesForDevice(device);
        return true;
    }

    public synchronized void cleanup() {
        debug("Clean up and shutdown");
        for (BluetoothDevice device : this.mClients.keySet()) {
            disconnect(device);
        }
    }

    public int getState(BluetoothDevice device) {
        AvrcpBipClient client = getClient(device);
        if (client == null) {
            return 0;
        }
        return client.getState();
    }

    public String getUuidForHandle(BluetoothDevice device, String handle) {
        AvrcpBipSession session = getSession(device);
        if (session == null || handle == null) {
            return null;
        }
        return session.getHandleUuid(handle);
    }

    public String getHandleForUuid(BluetoothDevice device, String uuid) {
        AvrcpBipSession session = getSession(device);
        if (session == null || uuid == null) {
            return null;
        }
        return session.getUuidHandle(uuid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHandleUuids(BluetoothDevice device) {
        AvrcpBipSession session = getSession(device);
        if (session == null) {
            return;
        }
        session.clearHandleUuids();
    }

    public Uri getImageUri(BluetoothDevice device, String imageUuid) {
        if (this.mCoverArtStorage.doesImageExist(device, imageUuid)) {
            return AvrcpCoverArtProvider.getImageUri(device, imageUuid);
        }
        return null;
    }

    public Uri downloadImage(BluetoothDevice device, String imageUuid) {
        return downloadImage(device, imageUuid, false);
    }

    public Uri downloadImage(BluetoothDevice device, String imageUuid, boolean forced) {
        boolean status;
        debug("Download Image - device: " + device.getAddressOnlyforLog() + ", Handle: " + imageUuid);
        AvrcpBipClient client = getClient(device);
        if (client == null) {
            error("Cannot download an image. No client is available.");
            return null;
        }
        if (this.mCoverArtStorage.doesImageExist(device, imageUuid)) {
            if (forced) {
                debug("Image is already downloaded, remove it in forced mode!");
                this.mCoverArtStorage.removeImage(device, imageUuid);
            } else {
                debug("Image is already downloaded, return directly in normal mode!");
                return AvrcpCoverArtProvider.getImageUri(device, imageUuid);
            }
        }
        String imageHandle = getHandleForUuid(device, imageUuid);
        if (imageHandle == null) {
            warn("No handle for UUID");
            return null;
        }
        if (TYPE_THUMBNAILLINKED.equalsIgnoreCase(this.mType)) {
            status = client.getLinkedThumbnail(imageHandle);
        } else {
            status = client.getImageProperties(imageHandle);
        }
        if (!status) {
            return null;
        }
        return AvrcpCoverArtProvider.getImageUri(device, imageUuid);
    }

    public Bitmap getImage(BluetoothDevice device, String imageUuid) {
        return this.mCoverArtStorage.getImage(device, imageUuid);
    }

    public void removeImage(BluetoothDevice device, String imageUuid) {
        this.mCoverArtStorage.removeImage(device, imageUuid);
    }

    public synchronized void updateImageProperties() {
        this.mType = SystemProperties.get(AVRCP_CONTROLLER_COVER_ART_IMGTYPE, "Image");
        this.mDownloadScheme = SystemProperties.get(AVRCP_CONTROLLER_COVER_ART_SCHEME, "thumbnail");
        this.mMimeType = BipEncoding.getEncodingTypeFromString(SystemProperties.get(AVRCP_CONTROLLER_COVER_ART_MIMETYPE, MIMETYPE_DEFAULT));
        this.mHeight = SystemProperties.getInt(AVRCP_CONTROLLER_COVER_ART_IMGHEIGHT, 500);
        this.mWidth = SystemProperties.getInt(AVRCP_CONTROLLER_COVER_ART_IMGWIDTH, 500);
        this.mMaxSize = SystemProperties.getInt(AVRCP_CONTROLLER_COVER_ART_IMGMAXSIZE, IMAGE_MAXSIZE_DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AvrcpBipClient getClient(BluetoothDevice device) {
        return this.mClients.get(device);
    }

    private AvrcpBipSession getSession(BluetoothDevice device) {
        return this.mBipSessions.get(device);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BipImageDescriptor determineImageDescriptor(BipImageProperties properties) {
        if (properties == null || !properties.isValid()) {
            warn("Provided properties don't meet the spec. Requesting thumbnail format anyway.");
        }
        BipImageDescriptor.Builder builder = new BipImageDescriptor.Builder();
        String str = this.mDownloadScheme;
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -1052618729) {
            if (iHashCode == 1330532588 && str.equals("thumbnail")) {
                b = 1;
            }
        } else if (str.equals(SCHEME_NATIVE)) {
            b = 0;
        }
        if (b == 0) {
            return null;
        }
        builder.setEncoding(this.mMimeType);
        builder.setFixedDimensions(this.mWidth, this.mHeight);
        BipImageDescriptor bipImageDescriptor = builder.build();
        debug("determineImageDescriptor->bipImageDescriptor:" + bipImageDescriptor);
        return bipImageDescriptor;
    }

    class BipClientCallback implements AvrcpBipClient.Callback {
        final BluetoothDevice mDevice;

        BipClientCallback(BluetoothDevice device) {
            this.mDevice = device;
        }

        @Override // com.android.bluetooth.avrcpcontroller.AvrcpBipClient.Callback
        public void onConnectionStateChanged(int oldState, int newState) {
            AvrcpCoverArtManager.this.debug(this.mDevice.getAddressOnlyforLog() + ": " + oldState + " -> " + newState);
            if (newState == 2) {
                AvrcpCoverArtManager.this.clearHandleUuids(this.mDevice);
                AvrcpCoverArtManager.this.mService.getCurrentMetadataIfNoCoverArt(this.mDevice);
            } else if (newState == 0) {
                AvrcpBipClient client = AvrcpCoverArtManager.this.getClient(this.mDevice);
                boolean shouldReconnect = client != null;
                AvrcpCoverArtManager.this.disconnect(this.mDevice);
                if (shouldReconnect) {
                    AvrcpCoverArtManager.this.debug("Disconnect was not expected by us. Attempt to reconnect.");
                }
            }
        }

        @Override // com.android.bluetooth.avrcpcontroller.AvrcpBipClient.Callback
        public void onGetImagePropertiesComplete(int status, String imageHandle, BipImageProperties properties) {
            if (status == 160 && properties != null) {
                BipImageDescriptor descriptor = AvrcpCoverArtManager.this.determineImageDescriptor(properties);
                AvrcpCoverArtManager.this.debug(this.mDevice.getAddressOnlyforLog() + ": Download image - handle='" + imageHandle + "'");
                AvrcpBipClient client = AvrcpCoverArtManager.this.getClient(this.mDevice);
                if (client == null) {
                    AvrcpCoverArtManager.this.warn(this.mDevice.getAddressOnlyforLog() + ": Could not getImage() for " + imageHandle + " because client has disconnected.");
                    return;
                }
                client.getImage(imageHandle, descriptor);
                return;
            }
            AvrcpCoverArtManager.this.warn(this.mDevice.getAddressOnlyforLog() + ": GetImageProperties() failed - Handle: " + imageHandle + ", Code: " + status);
        }

        @Override // com.android.bluetooth.avrcpcontroller.AvrcpBipClient.Callback
        public void onGetImageComplete(int status, String imageHandle, BipImage image) {
            if (status != 160) {
                AvrcpCoverArtManager.this.warn(this.mDevice.getAddressOnlyforLog() + ": GetImage() failed - Handle: " + imageHandle + ", Code: " + status);
                return;
            }
            String imageUuid = AvrcpCoverArtManager.this.getUuidForHandle(this.mDevice, imageHandle);
            AvrcpCoverArtManager.this.debug(this.mDevice.getAddressOnlyforLog() + ": Received image data for handle: " + imageHandle + ", uuid: " + imageUuid + ", image: " + image);
            Uri uri = AvrcpCoverArtManager.this.mCoverArtStorage.addImage(this.mDevice, imageUuid, image.getImage());
            if (uri == null) {
                AvrcpCoverArtManager.this.error("Could not store downloaded image");
                return;
            }
            DownloadEvent event = AvrcpCoverArtManager.this.new DownloadEvent(imageUuid, uri);
            if (AvrcpCoverArtManager.this.mCallback != null) {
                AvrcpCoverArtManager.this.mCallback.onImageDownloadComplete(this.mDevice, event);
            }
        }
    }

    public String toString() {
        String s = "CoverArtManager:\n    Download Scheme: " + this.mDownloadScheme + "\n";
        for (BluetoothDevice device : this.mClients.keySet()) {
            AvrcpBipClient client = getClient(device);
            AvrcpBipSession session = getSession(device);
            s = ((s + "    " + device.getAddressOnlyforLog() + ":\n") + "      Client: " + client.toString() + "\n") + "      Handles: \n";
            for (String handle : session.getSessionHandles()) {
                s = s + "        " + handle + " -> " + session.getHandleUuid(handle) + "\n";
            }
        }
        return s + "  " + this.mCoverArtStorage.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debug(String msg) {
        if (DBG) {
            Log.d(TAG, msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void warn(String msg) {
        Log.w(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void error(String msg) {
        Log.e(TAG, msg);
    }
}
