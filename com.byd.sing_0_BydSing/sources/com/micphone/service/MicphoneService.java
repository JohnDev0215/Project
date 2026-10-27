package com.micphone.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.micphone.IMicphoneNotificationCallback;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.e;
import com.android.volley.BuildConfig;
import com.audio.iflytek.iFlyTekRecord;
import com.audio.usbmic.UsbAudioNativeLoopConfig;
import com.audio.usbmic.UsbAudioRecordLoopDriver;
import com.audio.usbmic.UsbMicDevice;
import com.huawei.hmsauto.intelligence.appmanager.IntelligenceApiManager;
import com.micphone.TsBydListener;
import com.micphone.TsBydMicManager;
import com.micphone.TsBydUDiskConfig;
import com.micphone.utils.Config;
import com.micphone.utils.HttpUtils;
import com.micphone.utils.ReflectUtil;
import com.micphone.utils.SysUtils;
import com.micphone.vehicle.MicphoneEffectLoader;
import com.thunder.wakeup_host.TsWakeupManager;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MicphoneService extends Service implements ICallbackListener, IUsbDeviceEventListener {
    private static final String ACTION_USB_PERMISSION = "com.android.thunder.micphone.USB_PERMISSION";
    private static final String CHANNEL_ID = "TsMicphoneServiceID";
    private static final String CHANNEL_NAME = "TsMicphoneService";
    public static final int DONGLE_STATE_PLUG_IN = 1;
    public static final int DONGLE_STATE_PLUG_OUT = 0;
    private static final int DONGLE_STATUS_TYPE = 0;
    private static final int EFFECT_STATUS_TYPE = 2;
    private static final int EVENT_MIC_CHECK_PERMISSION = 2;
    private static final int EVENT_MIC_CHECK_PLUGIN = 0;
    private static final int EVENT_MIC_CHECK_PLUGOUT = 1;
    private static final int EVENT_MIC_CHECK_TIMEROUT = 4;
    private static final int EVENT_MIC_INIT_EVENT = 3;
    private static final int EVENT_MIC_OTA_UPGRADE = 255;
    private static final int EVENT_MIC_UPDATE_EFFECT = 5;
    private static final int LOOPBACK_STATUS_TYPE = 239;
    private static final int MICPHONE_SERVICE_PERM_DELAY_MS = 1000;
    private static final int MICPHONE_STATUS_TYPE = 1;
    private static final String PROPERTY_KEY = "sys.micphone.loadeffect.config";
    private static final int SERVICE_START_TYPE = 10;
    private static final int SERVICE_USB_PERMISSION_TYPE = 11;
    private static final int SERVICE_VERSION_CODE = 524287;
    private static final String TAG = "MicphoneService";
    private static UsbDevice mDevice;
    private static TsBydListener mTsBydListener;
    private UsbDeviceConnection mConnection;
    private Context mContext;
    private iFlyTekRecord mIFlyTekRecord;
    private UsbManager mUsbManager;
    private Lock mCallbackLock = new ReentrantLock();
    private boolean mStart = false;
    private RemoteCallbackList<IMicphoneNotificationCallback> callbackList = new RemoteCallbackList<>();
    private EventHandler mHandler = null;
    private volatile boolean mDeviceOpend = false;
    private String mDeviceOpenNme = null;
    private volatile int mDongleIn = 0;
    private int[] mMicConnectState = new int[4];
    private int mConnectNum = 0;
    private volatile int mStartOta = 0;
    private IEffectLoader mLoader = new IEffectLoader() { // from class: com.micphone.service.MicphoneService.1
        @Override // com.micphone.service.IEffectLoader
        public int loadEffect(int i) {
            if (ReflectUtil.getProperty(MicphoneService.PROPERTY_KEY, "enable").equals("disable")) {
                return 0;
            }
            InputStream inputStreamOpenRawResource = MicphoneService.this.getResources().openRawResource(i);
            int iUsbMicLoadAudioEffect = -1;
            try {
                byte[] bArr = new byte[inputStreamOpenRawResource.available()];
                inputStreamOpenRawResource.read(bArr);
                iUsbMicLoadAudioEffect = UsbMicDevice.usbMicLoadAudioEffect(bArr);
                Log.e(MicphoneService.TAG, "load audio myeffect " + iUsbMicLoadAudioEffect);
                inputStreamOpenRawResource.close();
                return iUsbMicLoadAudioEffect;
            } catch (Exception e) {
                e.printStackTrace();
                return iUsbMicLoadAudioEffect;
            }
        }
    };
    private BroadcastReceiver mUsbReceiver = new BroadcastReceiver() { // from class: com.micphone.service.MicphoneService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UsbDevice usbDevice;
            Log.e(MicphoneService.TAG, "onReceive: " + intent.getAction());
            usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            String action = intent.getAction();
            action.hashCode();
            switch (action) {
                case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                    Log.e(MicphoneService.TAG, "onReceive: ACTION_USB_DEVICE_ATTACHED pid " + usbDevice.getProductId() + " vid " + usbDevice.getVendorId());
                    if (Config.isSupportDeviceId(usbDevice.getVendorId(), usbDevice.getProductId())) {
                        String property = ReflectUtil.getProperty(TsBydMicManager.BYD_PROP_INSIDE_MIC, "false");
                        Log.d(MicphoneService.TAG, "insideMic=" + property);
                        if ("true".equals(property)) {
                            if (MicphoneService.mTsBydListener != null) {
                                MicphoneService.mTsBydListener.onInsideMic();
                                return;
                            }
                            return;
                        }
                        Log.e(MicphoneService.TAG, "mDeviceOpen =" + MicphoneService.this.mDeviceOpend);
                        if (MicphoneService.this.mDeviceOpend || MicphoneService.this.mHandler == null) {
                            return;
                        }
                        MicphoneService.this.Byd_IS_BROAD_FLAG = true;
                        Log.e(MicphoneService.TAG, "onMicphonePlugIn");
                        MicphoneService.this.mHandler.obtainMessage(0, usbDevice).sendToTarget();
                        return;
                    }
                    return;
                case "com.android.thunder.micphone.USB_PERMISSION":
                    Log.e(MicphoneService.TAG, "ACTION_USB_PERMISSION");
                    synchronized (this) {
                        if (intent.getBooleanExtra("permission", false)) {
                            Log.e(MicphoneService.TAG, "get usb perimission");
                            if (MicphoneService.this.mHandler != null) {
                                MicphoneService.this.mHandler.sendEmptyMessage(3);
                            }
                        } else {
                            Log.e(MicphoneService.TAG, "can not get usb perimission, retry");
                            if (MicphoneService.this.mHandler != null) {
                                MicphoneService.this.mHandler.sendEmptyMessageDelayed(2, 1000L);
                            }
                        }
                        break;
                    }
                    return;
                case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                    Log.e(MicphoneService.TAG, "onReceive: ACTION_USB_DEVICE_DETACHED pid " + usbDevice.getProductId() + " vid " + usbDevice.getVendorId());
                    if (Config.isSupportDeviceId(usbDevice.getVendorId(), usbDevice.getProductId())) {
                        Log.e(MicphoneService.TAG, "mDeviceOpen =" + MicphoneService.this.mDeviceOpenNme + ", device=" + usbDevice.getDeviceName());
                        if ((TextUtils.isEmpty(MicphoneService.this.mDeviceOpenNme) || TextUtils.isEmpty(usbDevice.getDeviceName()) || MicphoneService.this.mDeviceOpenNme.equals(usbDevice.getDeviceName())) && MicphoneService.this.mHandler != null) {
                            MicphoneService.this.Byd_IS_BROAD_FLAG = true;
                            Log.e(MicphoneService.TAG, "onMicphonePlugOut");
                            MicphoneService.this.mHandler.obtainMessage(1, usbDevice).sendToTarget();
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private BroadcastReceiver mUDiskReceiver = new BroadcastReceiver() { // from class: com.micphone.service.MicphoneService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.e(MicphoneService.TAG, "onReceive: " + intent.getAction());
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                TsBydUDiskConfig.getInstance(MicphoneService.this.mContext).parseUDisk(intent.getData().getPath());
            }
        }
    };
    private boolean Byd_IS_BROAD_FLAG = false;
    private boolean Byd_IS_CHECK_FLAG = false;
    private boolean hasDteVersion = true;

    private class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void oldHandleMicInit() {
            if (MicphoneService.this.mDeviceOpend) {
                return;
            }
            int iOpenUsbDevice = MicphoneService.this.openUsbDevice();
            if (iOpenUsbDevice != 0) {
                if (iOpenUsbDevice == 1) {
                    MicphoneService.this.mDeviceOpend = true;
                    if (MicphoneService.mTsBydListener != null) {
                        MicphoneService.mTsBydListener.onHotPlugInPidVid(MicphoneService.mDevice.getVendorId(), MicphoneService.mDevice.getProductId());
                        return;
                    }
                    return;
                }
                return;
            }
            if (MicphoneService.mTsBydListener != null) {
                MicphoneService.mTsBydListener.onHotPlugInPidVid(MicphoneService.mDevice.getVendorId(), MicphoneService.mDevice.getProductId());
            }
            MicphoneService.this.launchKaraokeApplication();
            MicphoneService.this.mDeviceOpend = true;
            MicphoneService.this.mDeviceOpenNme = MicphoneService.mDevice.getDeviceName();
            MicphoneEffectLoader.getInstance(MicphoneService.this.mContext, MicphoneService.this.mLoader).loadEffect();
            if (Config.isAudioRecordDrive()) {
                UsbAudioRecordLoopDriver.getInstance().micServiceStart();
            } else if (Config.isNativeLoopbackDrive()) {
                UsbMicDevice.activeSource(0, 1);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                Log.e(MicphoneService.TAG, "usb plugin");
                UsbDevice usbDevice = MicphoneService.this.getUsbDevice();
                StringBuilder sb = new StringBuilder();
                sb.append("device is null? ");
                sb.append(usbDevice == null);
                Log.e(MicphoneService.TAG, sb.toString());
                if (usbDevice == null) {
                    MicphoneService.this.mDongleIn = 0;
                } else {
                    MicphoneService.this.mDongleIn = 1;
                }
                UsbDevice unused = MicphoneService.mDevice = (UsbDevice) message.obj;
                MicphoneService.this.hasDteVersion = true;
                MicphoneService.this.requestUsbPermission();
                return;
            }
            String str = null;
            if (i == 1) {
                Log.e(MicphoneService.TAG, "usb plugout " + MicphoneService.this.mDeviceOpend);
                MicphoneService.this.mDongleIn = 0;
                if (!MicphoneService.this.mDeviceOpend) {
                    MicphoneService.this.Byd_IS_CHECK_FLAG = false;
                    MicphoneService.this.sendDongleState(0);
                    return;
                }
                if (Config.isAudioRecordDrive()) {
                    UsbAudioRecordLoopDriver.getInstance().micServiceStop();
                } else if (Config.isNativeLoopbackDrive()) {
                    UsbMicDevice.activeSource(0, 0);
                }
                removeMessages(4);
                removeMessages(3);
                UsbMicDevice.usbMicClose();
                if (MicphoneService.this.mConnection != null) {
                    MicphoneService.this.mConnection.close();
                }
                MicphoneService.this.mConnection = null;
                UsbDevice unused2 = MicphoneService.mDevice = null;
                MicphoneService.this.mDeviceOpend = false;
                MicphoneService.this.mDeviceOpenNme = null;
                MicphoneService.this.callBack(1, 0);
                MicphoneService.this.callBack(0, 0);
                MicphoneService.this.Byd_IS_CHECK_FLAG = true;
                MicphoneService.this.sendDongleState(0);
                return;
            }
            if (i == 2) {
                Log.e(MicphoneService.TAG, "usb req permission");
                MicphoneService.this.hasDteVersion = true;
                MicphoneService.this.requestUsbPermission();
                return;
            }
            if (i != 3) {
                return;
            }
            Log.e(MicphoneService.TAG, "loopbacck usb init " + MicphoneService.this.mDeviceOpend);
            MicphoneService.this.callBack(11, 1);
            String productType = Config.getProductType();
            Log.e(MicphoneService.TAG, "product=" + productType);
            String property = ReflectUtil.getProperty(ReflectUtil.BYD_SERVER_CHECK, "1");
            Log.e(MicphoneService.TAG, "server_check=" + property);
            boolean zIsServerCheck = TsBydUDiskConfig.getInstance(MicphoneService.this.mContext).isServerCheck();
            Log.e(MicphoneService.TAG, "u_disk_check=" + zIsServerCheck);
            boolean zIsKtCdc = Config.isKtCdc(MicphoneService.mDevice.getProductName());
            Log.e(MicphoneService.TAG, "isKtCdc=" + zIsKtCdc);
            if (TextUtils.isEmpty(productType) || !productType.contains("byd") || !property.equals("1") || !zIsServerCheck || zIsKtCdc) {
                oldHandleMicInit();
                return;
            }
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    str = "https://mic.ktvsky.com/auth/check?sn=" + MicphoneService.mDevice.getSerialNumber() + "&product_name=" + MicphoneService.mDevice.getProductName() + "&vin=" + SysUtils.getInstance().getMacAddress() + "&channel_no=" + TsBydMicManager.TS_CHANNEL;
                }
                HttpUtils.httpGetAsyncRequest(str, new HttpUtils.HttpListener() { // from class: com.micphone.service.MicphoneService.EventHandler.1
                    @Override // com.micphone.utils.HttpUtils.HttpListener
                    public void onResult(String str2) throws JSONException {
                        int i2 = -1;
                        try {
                            JSONObject jSONObject = new JSONObject(str2);
                            if (jSONObject.has("available")) {
                                i2 = jSONObject.getInt("available");
                            }
                        } catch (Exception e) {
                            Log.e(MicphoneService.TAG, "server_check=" + e.getMessage());
                        }
                        if (i2 != 0) {
                            EventHandler.this.oldHandleMicInit();
                        } else {
                            MicphoneService.this.Byd_IS_CHECK_FLAG = false;
                            MicphoneService.this.sendDongleState(1);
                        }
                    }
                });
            } catch (Exception e) {
                Log.e(MicphoneService.TAG, "server_check=" + e.getMessage());
            }
        }
    }

    private boolean checkDongleName(UsbDevice usbDevice, String str) {
        if (usbDevice == null || TextUtils.isEmpty(str)) {
            return false;
        }
        String productName = Build.VERSION.SDK_INT >= 21 ? usbDevice.getProductName() : null;
        Log.e(TAG, " dongleName " + productName);
        if (str.contains("lingKe")) {
            return Config.DONGLE_LING_KE.contains(productName);
        }
        if (str.contains("common")) {
            return Config.DONGLE_DEFAULT.contains(productName);
        }
        return true;
    }

    private String getProductName() {
        try {
            return getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("channel_value");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UsbDevice getUsbDevice() {
        UsbManager usbManager = this.mUsbManager;
        if (usbManager == null) {
            return null;
        }
        if (mDevice == null) {
            for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
                if (Config.isSupportDeviceId(usbDevice.getVendorId(), usbDevice.getProductId())) {
                    mDevice = usbDevice;
                }
            }
        }
        return mDevice;
    }

    public static String getUsbDeviceManufacturer() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return null;
        }
        return usbDevice.getManufacturerName();
    }

    public static String getUsbDeviceName() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return null;
        }
        return usbDevice.getProductName();
    }

    public static int getUsbDevicePid() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return 0;
        }
        return usbDevice.getProductId();
    }

    public static String getUsbDeviceSn() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return null;
        }
        return usbDevice.getSerialNumber().substring(r0.length() - 10);
    }

    public static String getUsbDeviceSnLong() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return null;
        }
        return usbDevice.getSerialNumber();
    }

    public static int getUsbDeviceVid() {
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            return 0;
        }
        return usbDevice.getVendorId();
    }

    private int getUserId() {
        int iMyUid = Process.myUid();
        int i = iMyUid / 100000;
        Log.e(TAG, "getUserId: UID = " + i + " myUid " + iMyUid);
        return i;
    }

    private void huaweiRequest() {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vendorId", mDevice.getVendorId());
            jSONObject.put("productId", mDevice.getProductId());
            jSONObject.put("broadcastAction", ACTION_USB_PERMISSION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "send command " + jSONObject.toString());
        new Thread(new Runnable() { // from class: com.micphone.service.MicphoneService.4
            @Override // java.lang.Runnable
            public void run() {
                String strSendDomainCommand = IntelligenceApiManager.getDomainManager().sendDomainCommand("domaincommand.karaoke.general.usbPermissionRequest", jSONObject.toString());
                Log.e(MicphoneService.TAG, "result_code " + strSendDomainCommand);
                if (TextUtils.isEmpty(strSendDomainCommand)) {
                    Log.e(MicphoneService.TAG, "system no has dte !!!");
                    MicphoneService.this.hasDteVersion = false;
                    MicphoneService.this.requestUsbPermission();
                    return;
                }
                try {
                    int i = new JSONObject(strSendDomainCommand).getInt("resultCode");
                    if (i == 1) {
                        Log.e(MicphoneService.TAG, "system no has dte !!!");
                        MicphoneService.this.hasDteVersion = false;
                        MicphoneService.this.requestUsbPermission();
                    }
                    Log.e(MicphoneService.TAG, "sendCode " + i);
                } catch (JSONException e2) {
                    Log.e(MicphoneService.TAG, "code " + e2.getMessage());
                }
            }
        }).start();
    }

    private boolean isNeedCheckMicManufacturer() {
        String productName = getProductName();
        if (Build.VERSION.SDK_INT >= 21) {
            Log.e(TAG, "this micphone package  name " + productName + "  devicename " + mDevice.getProductName());
            if (TextUtils.isEmpty(productName)) {
                return false;
            }
            if ((productName.equals("changanc281") || productName.equals("changanc301") || productName.equals("changanc589") || productName.equals("changans202") || productName.equals("changancs203")) && mDevice.getProductName().equals("S10")) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchKaraokeApplication() {
        UsbDevice usbDevice = getUsbDevice();
        String serialNumber = (usbDevice == null || Build.VERSION.SDK_INT < 21) ? BuildConfig.FLAVOR : usbDevice.getSerialNumber();
        if (Config.isSupportLaunchKTVApp()) {
            Log.e(TAG, "will wakeup carplay App.");
            TsWakeupManager.getInstance().wakeupApp(this, serialNumber);
        }
    }

    private boolean multiUserprocess() {
        int userId = getUserId();
        if (userId == 0 && Config.getProductType().toLowerCase().contains("changans202")) {
            Log.e(TAG, "onCreate: changans202  uid 0 will return");
            return true;
        }
        if (userId == 0 && Config.getProductType().toLowerCase().contains("lantuh53")) {
            Log.e(TAG, "onCreate: lantuh53  uid 0 will return");
            stopSelf();
            System.exit(0);
            return true;
        }
        if (userId != 10 || !Config.getProductType().toLowerCase().contains("ep40")) {
            return false;
        }
        Log.e(TAG, "onCreate:  ep40 uid 10 will return");
        stopSelf();
        System.exit(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestUsbPermission() {
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
        Log.e(TAG, "requestUsbPermission");
        if (this.mDongleIn == 0) {
            Log.e(TAG, "dongle has been removed");
            return;
        }
        if (mDevice == null) {
            Log.e(TAG, "getUsbDevice");
            mDevice = getUsbDevice();
        }
        if (mDevice == null) {
            Log.e(TAG, "mDevice is null");
            return;
        }
        Log.e(TAG, "this mic " + mDevice.getProductName());
        if (Build.VERSION.SDK_INT >= 21 && !UsbAudioNativeLoopConfig.isSupportDevice(this.mContext, mDevice.getProductName()) && !Config.isKtCdc(mDevice.getProductName())) {
            Log.e(TAG, "permission unsupport this micphone ");
            return;
        }
        boolean zHasPermission = this.mUsbManager.hasPermission(mDevice);
        Log.e(TAG, "permission=" + zHasPermission);
        if (zHasPermission) {
            EventHandler eventHandler = this.mHandler;
            if (eventHandler != null) {
                eventHandler.sendEmptyMessage(3);
                return;
            }
            return;
        }
        String productType = Config.getProductType();
        Log.e(TAG, "product=" + productType + " hasDteVersion=" + this.hasDteVersion);
        if (checkDongleName(mDevice, productType)) {
            if (!TextUtils.isEmpty(productType) && productType.contains("huawei") && this.hasDteVersion) {
                huaweiRequest();
            } else {
                this.mUsbManager.requestPermission(mDevice, broadcast);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDongleState(int i) {
        TsBydListener tsBydListener;
        String productType = Config.getProductType();
        Log.e(TAG, "DongleState product=" + productType);
        if (TextUtils.isEmpty(productType) || !productType.contains("byd") || (tsBydListener = mTsBydListener) == null) {
            return;
        }
        tsBydListener.onHotPlugState(i, this.Byd_IS_BROAD_FLAG, this.Byd_IS_CHECK_FLAG);
    }

    public static void setBydListener(TsBydListener tsBydListener) {
        mTsBydListener = tsBydListener;
    }

    private void setSoundEffectsEnabled(boolean z) {
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (z) {
            audioManager.loadSoundEffects();
        } else {
            audioManager.unloadSoundEffects();
        }
        Settings.System.putInt(this.mContext.getContentResolver(), "sound_effects_enabled", z ? 1 : 0);
    }

    public int callBack(int i, int i2) {
        this.mCallbackLock.lock();
        int i3 = 0;
        try {
            try {
                int iBeginBroadcast = this.callbackList.beginBroadcast();
                while (i3 < iBeginBroadcast) {
                    try {
                        try {
                            ((IMicphoneNotificationCallback) this.callbackList.getBroadcastItem(i3)).onMicphoneNotification(i, i2);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        i3++;
                    } catch (Exception e2) {
                        e = e2;
                        i3 = iBeginBroadcast;
                        e.printStackTrace();
                        this.mCallbackLock.unlock();
                        return i3;
                    }
                }
                this.callbackList.finishBroadcast();
                return iBeginBroadcast;
            } finally {
                this.mCallbackLock.unlock();
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public int callBack2(int i, int i2, int i3) {
        this.mCallbackLock.lock();
        int i4 = 0;
        try {
            try {
                int iBeginBroadcast = this.callbackList.beginBroadcast();
                while (i4 < iBeginBroadcast) {
                    try {
                        try {
                            ((IMicphoneNotificationCallback) this.callbackList.getBroadcastItem(i4)).onMicphoneDongleEvent(i, i2, i3);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        i4++;
                    } catch (Exception e2) {
                        e = e2;
                        i4 = iBeginBroadcast;
                        e.printStackTrace();
                        this.mCallbackLock.unlock();
                        return i4;
                    }
                }
                this.callbackList.finishBroadcast();
                return iBeginBroadcast;
            } finally {
                this.mCallbackLock.unlock();
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new MicphoneDeviceImpl(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        int i;
        super.onCreate();
        Log.e(TAG, "onCreate");
        if (multiUserprocess()) {
            stopSelf();
            System.exit(0);
        }
        this.mUsbManager = (UsbManager) getSystemService("usb");
        this.mContext = getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction(ACTION_USB_PERMISSION);
        registerReceiver(this.mUsbReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter2.addDataScheme("file");
        registerReceiver(this.mUDiskReceiver, intentFilter2);
        this.mIFlyTekRecord = new iFlyTekRecord(this.mContext);
        UsbAudioNativeLoopConfig.parserXmlConfig(this.mContext);
        if (Config.isSupportLaunchKTVApp()) {
            TsWakeupManager.getInstance().init(this);
        }
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mHandler = new EventHandler(looperMyLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mHandler = new EventHandler(mainLooper);
            } else {
                this.mHandler = null;
                Log.e(TAG, " handle is null!");
            }
        }
        if (Config.isFrontService() && (i = Build.VERSION.SDK_INT) >= 26 && i >= 26) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 4));
            startForeground(11111, new e.c(this, CHANNEL_ID).e(System.currentTimeMillis()).d(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MicphoneService.class), 0)).a());
        }
        UsbMicDevice.loopbackInit();
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            this.Byd_IS_BROAD_FLAG = false;
            eventHandler.sendEmptyMessage(0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service onDestroy");
        unregisterReceiver(this.mUsbReceiver);
        unregisterReceiver(this.mUDiskReceiver);
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.removeCallbacksAndMessages(null);
        }
        iFlyTekRecord iflytekrecord = this.mIFlyTekRecord;
        if (iflytekrecord != null) {
            iflytekrecord.release();
            this.mIFlyTekRecord = null;
        }
        if (this.mDeviceOpend) {
            UsbMicDevice.usbMicClose();
            UsbDeviceConnection usbDeviceConnection = this.mConnection;
            if (usbDeviceConnection != null) {
                usbDeviceConnection.close();
            }
            this.mDeviceOpend = false;
            this.mDeviceOpenNme = null;
        }
        this.mHandler = null;
        Log.e(TAG, "service stop&release loopback");
        UsbMicDevice.loopbackDeinit();
        Log.e(TAG, "service ok");
    }

    @Override // com.micphone.service.IUsbDeviceEventListener
    public void onDongleEvent(int i, int i2, int i3) {
        if (i == 1) {
            int[] iArr = this.mMicConnectState;
            iArr[i2] = i3;
            int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3];
            int i5 = this.mConnectNum;
            if (i5 == 0 && i4 > 0) {
                this.mConnectNum = i4;
                launchKaraokeApplication();
            } else if (i4 == 0 && i5 > 0) {
                this.mConnectNum = i4;
            }
        }
        callBack2(i, i2, i3);
    }

    @Override // com.micphone.service.IUsbDeviceEventListener
    public void onEndpointEvent(int i, int i2, int i3) {
        TsBydListener tsBydListener = mTsBydListener;
        if (tsBydListener != null) {
            tsBydListener.onEndpointEvent(i, i2, i3);
        }
    }

    @Override // com.micphone.service.IUsbDeviceEventListener
    public void onKeyEvent(int i, int i2) {
        callBack(i, i2);
    }

    @Override // com.micphone.service.IUsbDeviceEventListener
    public void onLoopbackEvent(int i, int i2, int i3) {
        if (i != 239 || this.mIFlyTekRecord == null) {
            return;
        }
        if (i2 == 1) {
            Log.e(TAG, "loopback start");
        } else {
            Log.e(TAG, "loopback stop");
        }
        this.mIFlyTekRecord.activeSource(i2);
    }

    @Override // com.micphone.service.IUsbDeviceEventListener
    public void onOtaEvent(int i, int i2, int i3) {
        TsBydListener tsBydListener = mTsBydListener;
        if (tsBydListener != null) {
            tsBydListener.onOtaEvent(i, i2, i3);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.e(TAG, "onStartCommand!");
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    int openUsbDevice() {
        EventHandler eventHandler;
        Log.e(TAG, "Thunderstone Micphone Service: " + Config.getVersion(this.mContext));
        Log.e(TAG, "openUsbDevice");
        if (mDevice == null) {
            Log.e(TAG, "getUsbDevice");
            mDevice = getUsbDevice();
        }
        UsbDevice usbDevice = mDevice;
        if (usbDevice == null) {
            Log.e(TAG, "can not get Micphone device");
            return -1;
        }
        this.mConnection = this.mUsbManager.openDevice(usbDevice);
        this.mStartOta = 0;
        UsbDeviceConnection usbDeviceConnection = this.mConnection;
        if (usbDeviceConnection == null) {
            Log.e(TAG, "UsbManager openDevice failed");
            if (!this.mUsbManager.hasPermission(mDevice) && (eventHandler = this.mHandler) != null) {
                eventHandler.sendEmptyMessage(2);
            }
            return -1;
        }
        int fileDescriptor = usbDeviceConnection.getFileDescriptor();
        if ((mDevice.getVendorId() != Config.MIC_VID || mDevice.getProductId() != Config.MIC_PID) && (mDevice.getVendorId() != 0 || mDevice.getProductId() != 0)) {
            Log.e(TAG, "before ota usbMicOpen");
            if (UsbMicDevice.usbMicOpen(mDevice.getDeviceName(), fileDescriptor, this) < 0) {
                Log.e(TAG, "usbMicOpen failed " + fileDescriptor);
                this.mConnection.close();
                return -1;
            }
            if (mDevice.getVendorId() == Config.KT_LOADER_VID && mDevice.getProductId() == Config.KT_LOADER_PID) {
                Log.e(TAG, "loader mode");
                UsbMicDevice.change2OtaMode();
            } else if (mDevice.getVendorId() == Config.KT_CDC_VID && mDevice.getProductId() == Config.KT_CDC_PID) {
                if (callBack(255, 0) == 0) {
                    Log.e(TAG, "no callback find");
                    this.mStartOta = 1;
                }
                TsBydListener tsBydListener = mTsBydListener;
                if (tsBydListener != null) {
                    tsBydListener.onOtaKTEventMode();
                }
            }
            return 1;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (isNeedCheckMicManufacturer()) {
                String[] strArrSplit = mDevice.getManufacturerName().split("_");
                if (!strArrSplit[0].equals("S10") || !strArrSplit[2].equals("CA")) {
                    Log.e(TAG, "micphone name = " + mDevice.getDeviceName() + "version name " + mDevice.getManufacturerName());
                    Log.e(TAG, "unsupport this micphone ");
                    this.mConnection.close();
                    return -1;
                }
                Log.e(TAG, "this changan micphone ok");
            }
            if (!UsbAudioNativeLoopConfig.isSupportDevice(this.mContext, mDevice.getProductName())) {
                Log.e(TAG, "unsupport this micphone ");
                this.mConnection.close();
                return -1;
            }
        }
        String agreement = TsBydUDiskConfig.getInstance(this.mContext).getAgreement();
        Log.e(TAG, "agreement " + agreement);
        if (agreement == null) {
            Log.e(TAG, "before normal usbMicOpen");
            if (UsbMicDevice.usbMicOpen(mDevice.getDeviceName(), fileDescriptor, this) < 0) {
                Log.e(TAG, "usbMicOpen failed " + fileDescriptor);
                this.mConnection.close();
                this.Byd_IS_CHECK_FLAG = false;
                sendDongleState(1);
                return -1;
            }
        } else {
            Log.e(TAG, "before u disk usbMicOpen");
            if (UsbMicDevice.usbMicOpenByDisk(agreement, fileDescriptor, this) < 0) {
                Log.e(TAG, "usbMicOpen failed " + fileDescriptor);
                this.mConnection.close();
                this.Byd_IS_CHECK_FLAG = false;
                sendDongleState(1);
                return -1;
            }
        }
        if (UsbMicDevice.usbMicAuthcheck(BuildConfig.FLAVOR, mDevice.getVendorId(), mDevice.getProductId()) == 0) {
            Log.e(TAG, "usbMicAuthcheck ok");
            callBack(0, 1);
            this.Byd_IS_CHECK_FLAG = true;
            sendDongleState(1);
            Log.e(TAG, "openUsbDevice ok");
            return 0;
        }
        Log.e(TAG, "usbMicAuthcheck failed");
        callBack(0, -1);
        UsbMicDevice.usbMicClose();
        this.mConnection.close();
        this.Byd_IS_CHECK_FLAG = false;
        sendDongleState(1);
        return -1;
    }

    @Override // com.micphone.service.ICallbackListener
    public void registerCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) {
        Log.e(TAG, " registerCallback .......");
        if (iMicphoneNotificationCallback == null) {
            return;
        }
        this.callbackList.register(iMicphoneNotificationCallback);
        if (!this.mStart) {
            this.mStart = true;
            Log.e(TAG, " Notification  service start.......");
            callBack(10, 0);
        }
        if (this.mContext != null) {
            Log.e(TAG, "get version code " + Config.getVersionCode(this.mContext));
            callBack(SERVICE_VERSION_CODE, Config.getVersionCode(this.mContext));
        }
        UsbDevice usbDevice = mDevice;
        if (usbDevice != null && this.mUsbManager.hasPermission(usbDevice)) {
            Log.e(TAG, "usb get permission");
            callBack(11, 1);
        }
        if (this.mDongleIn == 1) {
            callBack(0, 1);
        } else {
            callBack(0, 0);
        }
        if (this.mStartOta == 1) {
            this.mStartOta = 0;
            callBack2(255, 0, 0);
        }
    }

    @Override // com.micphone.service.ICallbackListener
    public void unregisterCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) {
        if (iMicphoneNotificationCallback != null) {
            this.callbackList.unregister(iMicphoneNotificationCallback);
        }
    }
}
