package com.byd.profile.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BydStorageManager extends BroadcastReceiver {
    private static final String TAG = "BydStorageManager";
    private static volatile BydStorageManager mInstance;
    private BydStorageListener listener;
    private Context mContext;
    private StorageManager mStorageManager;
    private boolean usbConnected = false;
    private boolean sdCardMounted = false;
    private StorageEventListener mListener = new StorageEventListener() { // from class: com.byd.profile.storage.BydStorageManager.1
        public void onVolumeStateChanged(VolumeInfo vol, int oldState, int newState) {
            BydStorageManager.this.checkVolumeInfo(vol, newState);
        }

        public void onStorageStateChanged(String path, String oldState, String newState) {
            BydLogHelper.logD("onStorageStateChanged", "path:" + path + " , oldState:" + oldState + " , newState:" + newState);
        }
    };

    public interface BydStorageListener {
        void sdcardStateChanged(boolean state);

        void usbStateChanged(boolean state);
    }

    public boolean isUsbConnected() {
        return this.usbConnected;
    }

    public boolean isSdCardMounted() {
        return this.sdCardMounted;
    }

    public void setListener(BydStorageListener listener) {
        this.listener = listener;
    }

    private BydStorageManager(Context context) {
        this.mContext = context;
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        this.mStorageManager = storageManager;
        storageManager.registerListener(this.mListener);
        BydLogHelper.logD(TAG, "StorageManager registerListener");
    }

    public static BydStorageManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (BydStorageManager.class) {
                if (mInstance == null) {
                    mInstance = new BydStorageManager(context);
                }
            }
        }
        return mInstance;
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        this.mContext.registerReceiver(this, intentFilter);
    }

    private boolean isUsbDeviceType(String deviceType) {
        return TextUtils.equals("usbotg", deviceType);
    }

    private boolean isSdCardDeviceType(String deviceType) {
        return TextUtils.equals("sdcard", deviceType);
    }

    public void getVolumesStatus() {
        List<VolumeInfo> volumes = this.mStorageManager.getVolumes();
        BydLogHelper.logD(TAG, "volumes-size:" + volumes.size());
        if (volumes != null && volumes.size() > 0) {
            for (VolumeInfo volumeInfo : volumes) {
                String deviceType = volumeInfo.getDeviceType();
                int state = volumeInfo.getState();
                BydLogHelper.logD(TAG, "getVolumesStatus deviceType:" + deviceType + ",state:" + state);
                if (state == 2) {
                    if (isUsbDeviceType(deviceType)) {
                        this.usbConnected = true;
                    } else if (isSdCardDeviceType(deviceType)) {
                        this.sdCardMounted = true;
                    }
                }
            }
        }
        notifyUsbStatusChanged();
        notifySdCardStatusChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkVolumeInfo(VolumeInfo vol, int newState) {
        if (vol != null) {
            int i = vol.type;
            BydLogHelper.logD(TAG, "checkVolumeInfo type:" + i + ",state:" + newState);
            if (i == 0) {
                String deviceType = vol.getDeviceType();
                BydLogHelper.logD(TAG, "checkVolumeInfo deviceType:" + deviceType);
                if (newState == 5) {
                    if (isUsbDeviceType(deviceType)) {
                        BydLogHelper.logD("usb EJECTING");
                        this.usbConnected = false;
                        notifyUsbStatusChanged();
                        return;
                    } else {
                        if (isSdCardDeviceType(deviceType)) {
                            BydLogHelper.logD("sdCard EJECTING");
                            this.sdCardMounted = false;
                            notifySdCardStatusChanged();
                            return;
                        }
                        return;
                    }
                }
                if (newState == 2) {
                    if (isUsbDeviceType(deviceType)) {
                        BydLogHelper.logD("usb MOUNTED");
                        this.usbConnected = true;
                        notifyUsbStatusChanged();
                    } else if (isSdCardDeviceType(deviceType)) {
                        BydLogHelper.logD("sdCard MOUNTED");
                        this.sdCardMounted = true;
                        notifySdCardStatusChanged();
                    }
                }
            }
        }
    }

    private void notifyUsbStatusChanged() {
        BydStorageListener bydStorageListener = this.listener;
        if (bydStorageListener != null) {
            bydStorageListener.usbStateChanged(this.usbConnected);
        }
    }

    private void notifySdCardStatusChanged() {
        BydStorageListener bydStorageListener = this.listener;
        if (bydStorageListener != null) {
            bydStorageListener.sdcardStateChanged(this.sdCardMounted);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logE("action:" + action);
        action.hashCode();
        switch (action) {
            case "android.intent.action.MEDIA_MOUNTED":
                handlerSdCardStatus(intent, true);
                break;
            case "android.intent.action.MEDIA_UNMOUNTED":
            case "android.intent.action.MEDIA_EJECT":
                handlerSdCardStatus(intent, false);
                break;
        }
    }

    private void handlerSdCardStatus(Intent intent, boolean status) {
        String stringExtra = intent.getStringExtra("device_type");
        BydLogHelper.logD("device_type:" + stringExtra);
        if (isUsbDeviceType(stringExtra)) {
            this.usbConnected = status;
            BydLogHelper.logD("usbConnected:" + this.usbConnected);
            notifyUsbStatusChanged();
        } else if (isSdCardDeviceType(stringExtra)) {
            this.sdCardMounted = status;
            BydLogHelper.logD("sdCardMounted:" + this.sdCardMounted);
            notifySdCardStatusChanged();
        }
    }
}
