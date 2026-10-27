package com.ts.avm.utils.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.manager.AvmManager;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.wrappers.CmdPool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class UsbHelper {
    private static final String AVM_EXPORT_FOLDER_NAME = "/export_avm";
    private static final int CODE_READ_END = -1;
    private static final String DEFAULT_LOCAL_STORAGE_PATH = "/storage/emulated/0";
    private static final Object SYNCLOCK = new Object();
    private static final String TAG = "UsbHelper:";
    private static volatile UsbHelper sInstance;
    private AvmManager mAvmManager;
    private Context mContext;
    private Boolean mUsbIsMounted = false;
    private String mUsbMountPath = "";

    public static UsbHelper getInstance(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (SYNCLOCK) {
            if (sInstance == null) {
                sInstance = new UsbHelper(context);
            }
        }
        return sInstance;
    }

    private UsbHelper(Context context) {
        this.mContext = null;
        if (context != null) {
            this.mContext = context;
        } else {
            LogManager.loge(TAG, "context is null");
        }
        init();
    }

    private void init() {
        initBroadcast();
        this.mAvmManager = AvmManager.getInstance();
    }

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme("file");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.ts.avm.utils.usb.UsbHelper.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r8, android.content.Intent r9) {
                /*
                    r7 = this;
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "onReceive: action = "
                    r0.append(r1)
                    java.lang.String r1 = r9.getAction()
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    java.lang.String r1 = "UsbHelper:"
                    com.ts.avm.utils.LogManager.logi(r1, r0)
                    java.lang.String r0 = r9.getAction()
                    int r2 = r0.hashCode()
                    r3 = 0
                    r4 = 3
                    r5 = 2
                    r6 = 1
                    switch(r2) {
                        case -1665311200: goto L48;
                        case -1514214344: goto L3e;
                        case -963871873: goto L34;
                        case -625887599: goto L2a;
                        default: goto L29;
                    }
                L29:
                    goto L52
                L2a:
                    java.lang.String r2 = "android.intent.action.MEDIA_EJECT"
                    boolean r0 = r0.equals(r2)
                    if (r0 == 0) goto L29
                    r0 = r4
                    goto L53
                L34:
                    java.lang.String r2 = "android.intent.action.MEDIA_UNMOUNTED"
                    boolean r0 = r0.equals(r2)
                    if (r0 == 0) goto L29
                    r0 = r5
                    goto L53
                L3e:
                    java.lang.String r2 = "android.intent.action.MEDIA_MOUNTED"
                    boolean r0 = r0.equals(r2)
                    if (r0 == 0) goto L29
                    r0 = r3
                    goto L53
                L48:
                    java.lang.String r2 = "android.intent.action.MEDIA_REMOVED"
                    boolean r0 = r0.equals(r2)
                    if (r0 == 0) goto L29
                    r0 = r6
                    goto L53
                L52:
                    r0 = -1
                L53:
                    if (r0 == 0) goto L64
                    if (r0 == r6) goto L5c
                    if (r0 == r5) goto L5c
                    if (r0 == r4) goto L5c
                    goto L8d
                L5c:
                    com.ts.avm.utils.usb.UsbHelper r0 = com.ts.avm.utils.usb.UsbHelper.this
                    java.lang.String r1 = ""
                    com.ts.avm.utils.usb.UsbHelper.access$000(r0, r3, r1)
                    goto L8d
                L64:
                    android.net.Uri r0 = r9.getData()
                    java.lang.String r0 = r0.getPath()
                    boolean r2 = android.text.TextUtils.isEmpty(r0)
                    if (r2 != 0) goto L78
                    com.ts.avm.utils.usb.UsbHelper r1 = com.ts.avm.utils.usb.UsbHelper.this
                    com.ts.avm.utils.usb.UsbHelper.access$000(r1, r6, r0)
                    goto L8d
                L78:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "mount path is empty : "
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r2 = r2.toString()
                    com.ts.avm.utils.LogManager.logi(r1, r2)
                L8d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ts.avm.utils.usb.UsbHelper.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUsbMountStatus(boolean isUsbMounted, String path) {
        LogManager.logi(TAG, "setUsbMountStatus: isUsbMounted:" + isUsbMounted + " path:" + path);
        if (path.contains(DEFAULT_LOCAL_STORAGE_PATH)) {
            LogManager.logi(TAG, "setUsbMountStatus: ignore default storage:" + path);
            return;
        }
        this.mUsbMountPath = path;
        this.mUsbIsMounted = Boolean.valueOf(isUsbMounted);
        setUsbMountStatusToQnx(isUsbMounted);
    }

    private void setUsbMountStatusToQnx(boolean usbMountStatus) {
        LogManager.logd(TAG, "setUsbMountStatusToQnx usbMountStatus:" + usbMountStatus);
        CmdBean cmdBean = new CmdBean();
        if (usbMountStatus) {
            cmdBean.setId(CmdPool.SID_USB_MOUNT_STATUS_SEND);
            cmdBean.setSrcId(9);
            cmdBean.setTargetId(7);
            cmdBean.setValue(new int[]{0, 0, 0, 1});
            AvmManager avmManager = this.mAvmManager;
            if (avmManager != null) {
                avmManager.sendCommand(cmdBean);
                return;
            } else {
                LogManager.loge(TAG, "setUsbMountStatusToQnx: mManager is null");
                return;
            }
        }
        cmdBean.setId(CmdPool.SID_USB_MOUNT_STATUS_SEND);
        cmdBean.setSrcId(9);
        cmdBean.setTargetId(7);
        cmdBean.setValue(new int[]{0, 0, 0, 0});
        AvmManager avmManager2 = this.mAvmManager;
        if (avmManager2 != null) {
            avmManager2.sendCommand(cmdBean);
        } else {
            LogManager.loge(TAG, "setUsbMountStatusToQnx: mManager is null");
        }
    }

    public boolean pullDataFromDevice2Usb(String path) {
        if (!this.mUsbIsMounted.booleanValue()) {
            LogManager.loge(TAG, "usb is not mounted");
            clearOldFile(path);
            return false;
        }
        if (checkPathIsValid(path).booleanValue() && checkPathIsValid(this.mUsbMountPath).booleanValue()) {
            File targetFileFolder = new File(this.mUsbMountPath + AVM_EXPORT_FOLDER_NAME + "_" + getTime());
            if (!targetFileFolder.exists()) {
                LogManager.loge(TAG, "targetFileFolder is not exist mkdir:" + targetFileFolder);
                targetFileFolder.mkdirs();
            }
            if (!checkPathIsValid(targetFileFolder.getPath()).booleanValue()) {
                LogManager.loge(TAG, "targetFileFolder is invalid");
                clearOldFile(path);
                return false;
            }
            try {
                File devFileFolder = new File(path);
                for (File file : devFileFolder.listFiles()) {
                    FileInputStream inputStream = new FileInputStream(file.getPath());
                    FileOutputStream outputStream = new FileOutputStream(targetFileFolder.getPath() + "/" + file.getName());
                    byte[] buffer = new byte[2048];
                    while (true) {
                        int byteRead = inputStream.read(buffer);
                        if (byteRead != -1) {
                            outputStream.write(buffer, 0, byteRead);
                        }
                    }
                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                    LogManager.logd(TAG, "pullDataFromDevice2Usb: Export " + file.getName() + " OK");
                }
                LogManager.logd(TAG, "pullDataFromDevice2Usb: copy success");
                clearOldFile(path);
                return true;
            } catch (Exception ex) {
                clearOldFile(path);
                ex.printStackTrace();
                return false;
            }
        }
        clearOldFile(path);
        return false;
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss", Locale.getDefault());
        long timeMillis = System.currentTimeMillis();
        String time = "" + sdf.format(Long.valueOf(timeMillis));
        return time;
    }

    private void clearOldFile(String targetPath) {
        if (TextUtils.isEmpty(targetPath)) {
            LogManager.loge(TAG, "clearOldFile: targetFile is null");
            return;
        }
        LogManager.logd(TAG, "clearOldFile target path:" + targetPath);
        File targetFile = new File(targetPath);
        for (File file : targetFile.listFiles()) {
            try {
                file.delete();
                LogManager.logd(TAG, "clearOldFile:" + file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private Boolean checkPathIsValid(String path) {
        if (TextUtils.isEmpty(path)) {
            LogManager.loge(TAG, "checkPathIsValid: path is Empty:" + path);
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            LogManager.loge(TAG, "checkPathIsValid: file is not exists:" + path);
            return false;
        }
        if (!file.isDirectory()) {
            LogManager.loge(TAG, "checkPathIsValid: file is not a Directory:" + path);
            return false;
        }
        if (!file.canRead()) {
            LogManager.loge(TAG, "checkPathIsValid: file is don't canRead:" + path);
            return false;
        }
        LogManager.logi(TAG, "checkPathIsValid the " + path + " is OK");
        return true;
    }
}
