package com.byd.cpaa.androidauto;

import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import b5.h;
import com.byd.bt.model.BTDevice;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.screencast.AndroidAutoNoticeDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoStartDialog;
import com.byd.uereport.dialog.screencast.CastConnectingDialog;
import com.byd.uereport.dialog.screencast.CastSwitchDeviceDialog;
import com.ts.car.core.ServiceProvider;
import g5.a;
import g5.d;
import g5.m;
import h3.c;
import j7.c;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListListener;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.IBatteryStatusListener;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;

/* JADX INFO: loaded from: classes.dex */
public class AndroidAutoManager {
    private static volatile AndroidAutoManager mInstance;
    private static Toast tryAgainTipToast;
    private CastConnectingDialog androidAutoConnectingDialog;
    private boolean isAndroidAutoCasting;
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private IAndroidAutoDeviceListManager mAndroidAutoDeviceListManager;
    private final Context mContext;
    private AndroidAutoDevice mCurrentConnectedDevice;
    private volatile boolean mDevicesConnected;
    private AndroidAutoDevice mStatusChangedDevice;
    private final String TAG = "UE_AndroidAutoManager";
    private final int MSG_TOAST_TRY_AGAIN_TIP = 1002;
    private final int MSG_TOAST_CONNECT_UNSOLICITED_MESSAGE = 1003;
    private final int MSG_TOAST_ROTATION_SCREEN_TIP = 1004;
    private final int MSG_TOAST_SSL_AUTH_FAIL_TIP = 1005;
    private final int MSG_DIALOG_BATTERY_LOW = 2003;
    private final int MSG_DIALOG_UNAUTH = 2004;
    private final int MSG_DIALOG_START = 2005;
    private final int MSG_DIALOG_SWITCH_DEVICE_TIP = 2006;
    private final int MSG_DIALOG_CONNECTING_SHOW = 2007;
    private final int MSG_DIALOG_CONNECTING_DISMISS = 2008;
    private final int MSG_TOAST_USB_CONNECT_FAIL = 2009;
    private final int MSG_TOAST_WIFI_SIGNAL_WEAK = 2010;
    private final int MSG_CLEAR_ALL_DIALOG = 2011;
    private final Handler mUiHandler = new Handler(new Handler.Callback() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i8 = message.what;
            switch (i8) {
                case 1002:
                    a.b("UE_AndroidAutoManager", "show AndroidAuto try again  toast .");
                    Toast unused = AndroidAutoManager.tryAgainTipToast = m.a(AndroidAutoManager.this.mContext, AndroidAutoManager.this.mContext.getString(R.string.aa_toast_try_again_tip), 1);
                    AndroidAutoManager.tryAgainTipToast.show();
                    return false;
                case 1003:
                    a.b("UE_AndroidAutoManager", "show AndroidAuto connect unsolicited message toast .");
                    String string = message.getData().getString("message");
                    if (TextUtils.isEmpty(string)) {
                        a.b("UE_AndroidAutoManager", "AndroidAuto connect unsolicited message is empty .");
                    } else {
                        m.e(AndroidAutoManager.this.mContext, string, 1);
                    }
                    return false;
                case 1004:
                    a.b("UE_AndroidAutoManager", "show rotation screen toast .");
                    m.e(AndroidAutoManager.this.mContext, AndroidAutoManager.this.mContext.getString(R.string.aa_toast_screen_rotation_tip), 0);
                    return false;
                case 1005:
                    a.b("UE_AndroidAutoManager", "show SSL auth fail toast .");
                    m.e(AndroidAutoManager.this.mContext, AndroidAutoManager.this.mContext.getString(R.string.aa_toast_ssl_auth_fail_tip), 0);
                    return false;
                default:
                    switch (i8) {
                        case 2003:
                            a.b("UE_AndroidAutoManager", "show AndroidAuto battery dialog .");
                            AndroidAutoNoticeDialog androidAutoNoticeDialog = new AndroidAutoNoticeDialog(AndroidAutoManager.this.mContext);
                            androidAutoNoticeDialog.d(1);
                            androidAutoNoticeDialog.show();
                            break;
                        case 2004:
                            a.b("UE_AndroidAutoManager", "show AndroidAuto unauth dialog .");
                            AndroidAutoNoticeDialog androidAutoNoticeDialog2 = new AndroidAutoNoticeDialog(AndroidAutoManager.this.mContext);
                            androidAutoNoticeDialog2.d(0);
                            androidAutoNoticeDialog2.show();
                            break;
                        case 2005:
                            a.b("UE_AndroidAutoManager", "show AndroidAuto start dialog .");
                            String string2 = message.getData().getString("deviceName");
                            int i9 = message.getData().getInt("connectType");
                            AndroidAutoStartDialog androidAutoStartDialog = new AndroidAutoStartDialog(AndroidAutoManager.this.mContext);
                            androidAutoStartDialog.setDeviceName(string2);
                            androidAutoStartDialog.f(i9);
                            androidAutoStartDialog.show();
                            break;
                        case 2006:
                            a.b("UE_AndroidAutoManager", "show switch device dialog .");
                            String string3 = message.getData().getString("deviceId");
                            CastSwitchDeviceDialog castSwitchDeviceDialog = new CastSwitchDeviceDialog(AndroidAutoManager.this.mContext);
                            castSwitchDeviceDialog.l(1);
                            castSwitchDeviceDialog.k(string3);
                            castSwitchDeviceDialog.show();
                            break;
                        case 2007:
                            a.b("UE_AndroidAutoManager", "show AndroidAuto connecting dialog .");
                            if (AndroidAutoManager.this.getAndroidAutoConnectionStatus() != 3) {
                                a.b("UE_AndroidAutoManager", "current AndroidAuto status is not ACTIVATING .");
                            } else if (AndroidAutoManager.this.androidAutoConnectingDialog == null) {
                                a.b("UE_AndroidAutoManager", "show connecting dialog .");
                                AndroidAutoManager androidAutoManager = AndroidAutoManager.this;
                                androidAutoManager.androidAutoConnectingDialog = new CastConnectingDialog(androidAutoManager.mContext);
                                if (AndroidAutoManager.this.mStatusChangedDevice != null) {
                                    AndroidAutoManager.this.androidAutoConnectingDialog.setDeviceName(AndroidAutoManager.this.mStatusChangedDevice.getDeviceName());
                                }
                                AndroidAutoManager.this.androidAutoConnectingDialog.d(1);
                                AndroidAutoManager.this.androidAutoConnectingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.1.1
                                    @Override // android.content.DialogInterface.OnDismissListener
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        AndroidAutoManager.this.androidAutoConnectingDialog = null;
                                    }
                                });
                                AndroidAutoManager.this.androidAutoConnectingDialog.show();
                            }
                            break;
                        case 2008:
                            if (AndroidAutoManager.this.androidAutoConnectingDialog == null) {
                                a.b("UE_AndroidAutoManager", "connecting dialog is null .");
                            } else {
                                a.b("UE_AndroidAutoManager", "dismiss connecting dialog .");
                                AndroidAutoManager.this.androidAutoConnectingDialog.dismiss();
                                AndroidAutoManager.this.androidAutoConnectingDialog = null;
                            }
                            break;
                        case 2009:
                            a.b("UE_AndroidAutoManager", "show usb connect fail toast .");
                            m.e(AndroidAutoManager.this.mContext, AndroidAutoManager.this.mContext.getString(R.string.aa_toast_usb_connect_fail), 0);
                            break;
                        case 2010:
                            a.b("UE_AndroidAutoManager", "show wifi signal weak toast .");
                            m.e(AndroidAutoManager.this.mContext, AndroidAutoManager.this.mContext.getString(R.string.aa_toast_wifi_signal_weak), 0);
                            break;
                        case 2011:
                            h.e(AndroidAutoManager.this.mContext).c();
                            break;
                    }
                    break;
            }
        }
    });
    private final IAndroidAutoDeviceListListener mAndroidAutoDeviceListListener = new IAndroidAutoDeviceListListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.2
        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(AndroidAutoDevice androidAutoDevice) {
            super.onAndroidAutoDeviceStatusChanged(androidAutoDevice);
            a.b("UE_AndroidAutoManager", "onAndroidAutoDeviceStatusChanged name:" + androidAutoDevice.getDeviceName() + ",status:" + androidAutoDevice.getDeviceStatus());
            AndroidAutoManager.this.mStatusChangedDevice = androidAutoDevice;
            if (androidAutoDevice.getDeviceStatus() == 4) {
                AndroidAutoManager.this.mCurrentConnectedDevice = androidAutoDevice;
            }
        }

        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onProjectionRequested(AndroidAutoDevice androidAutoDevice, int i8) {
            super.onProjectionRequested(androidAutoDevice, i8);
            a.b("UE_AndroidAutoManager", "onProjectionRequested : " + androidAutoDevice.getDeviceName());
            c.c().k(new m2.a(230011));
            if (BYDAutoBodyworkDevice.getInstance(AndroidAutoManager.this.mContext).getPowerLevel() == 0) {
                a.c("UE_AndroidAutoManager,In OFF gear, skip this request.");
                return;
            }
            if (AndroidAutoManager.this.isBootGuideComplete()) {
                int androidAutoConnectionStatus = AndroidAutoManager.this.getAndroidAutoConnectionStatus();
                a.b("UE_AndroidAutoManager", "autoConnectionStatus : " + androidAutoConnectionStatus);
                int carPlayStatus = CarPlayManager.getInstance(AndroidAutoManager.this.mContext).getCarPlayStatus();
                a.b("UE_AndroidAutoManager", "carPlayStatus : " + carPlayStatus);
                String usbSerialNumber = null;
                if (carPlayStatus == 2 || carPlayStatus == 1) {
                    if (i8 == 2) {
                        usbSerialNumber = androidAutoDevice.getBtAddr();
                    } else if (i8 == 1) {
                        usbSerialNumber = androidAutoDevice.getUsbSerialNumber();
                    }
                    AndroidAutoManager.this.sendMsgShowSwitchDeviceDialog(usbSerialNumber);
                    return;
                }
                if (androidAutoConnectionStatus != 4 && androidAutoConnectionStatus != 3) {
                    if (i8 == 2) {
                        a.b("UE_AndroidAutoManager", "wireless first connection.");
                        AndroidAutoManager.this.sendMsgShowAndroidAutoStartDialog(androidAutoDevice.getBtAddr(), 2);
                        return;
                    } else {
                        if (i8 == 1) {
                            if (androidAutoDevice.isFirstConntect()) {
                                a.b("UE_AndroidAutoManager", "usb first connection.");
                                AndroidAutoManager.this.sendMsgShowAndroidAutoStartDialog(androidAutoDevice.getUsbSerialNumber(), 1);
                                return;
                            } else {
                                a.b("UE_AndroidAutoManager", ",usb not first connection.");
                                AndroidAutoManager.this.connectAndroidAuto(androidAutoDevice.getUsbSerialNumber(), 1);
                                return;
                            }
                        }
                        return;
                    }
                }
                if (androidAutoConnectionStatus == 4) {
                    if (AndroidAutoManager.this.mCurrentConnectedDevice != null) {
                        String usbSerialNumber2 = AndroidAutoManager.this.mCurrentConnectedDevice.getUsbSerialNumber();
                        String btAddr = AndroidAutoManager.this.mCurrentConnectedDevice.getBtAddr();
                        if (usbSerialNumber2.equals(androidAutoDevice.getUsbSerialNumber()) || btAddr.equals(androidAutoDevice.getBtAddr())) {
                            a.i("UE_AndroidAutoManager,the AA device connected is same with current casting AA device.");
                            return;
                        }
                    }
                    a.b("UE_AndroidAutoManager", "there is other AA device connected, show AA switch dialog.");
                    if (i8 == 2) {
                        usbSerialNumber = androidAutoDevice.getBtAddr();
                    } else if (i8 == 1) {
                        usbSerialNumber = androidAutoDevice.getUsbSerialNumber();
                    }
                    AndroidAutoManager.this.sendMsgShowSwitchDeviceDialog(usbSerialNumber);
                }
            }
        }
    };
    private final IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.3
        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onProjectionAppStatesChanged(int i8, boolean z7) {
            super.onProjectionAppStatesChanged(i8, z7);
        }

        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionConnectStatusChanged(int i8, int i9, String str, int i10) {
            super.onSessionConnectStatusChanged(i8, i9, str, i10);
            a.b("UE_AndroidAutoManager", " onSessionStatusChanged: status=" + i8 + " errorCode=" + i9 + " message=" + str + " projectionConnectType:" + i10);
            if (i8 == 5) {
                if (i9 != 13 && i9 != 11 && i9 != 8) {
                    if (i10 == 1) {
                        AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2009);
                    } else {
                        AndroidAutoManager.this.mUiHandler.sendEmptyMessage(1002);
                    }
                }
                AndroidAutoManager.this.mUiHandler.removeMessages(2008);
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2008);
            } else if (i8 == 3) {
                AndroidAutoManager.this.sendMsgShowConnectingDialog(500);
                AndroidAutoManager.this.mUiHandler.sendEmptyMessageDelayed(2008, 30000L);
            } else if (i8 == 4) {
                AndroidAutoManager.this.mUiHandler.removeMessages(2008);
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2008);
                if (AndroidAutoManager.tryAgainTipToast != null) {
                    AndroidAutoManager.tryAgainTipToast.cancel();
                    Toast unused = AndroidAutoManager.tryAgainTipToast = null;
                }
                AndroidAutoManager.this.mUiHandler.removeMessages(2009);
            } else if (i8 == 6) {
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2004);
            } else if (i8 == 1) {
                AndroidAutoManager.this.mCurrentConnectedDevice = null;
            }
            if (i9 == 12) {
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2004);
                return;
            }
            if (i9 == 11) {
                Message messageObtainMessage = AndroidAutoManager.this.mUiHandler.obtainMessage();
                messageObtainMessage.what = 1003;
                Bundle bundle = new Bundle();
                bundle.putString("message", str);
                messageObtainMessage.setData(bundle);
                AndroidAutoManager.this.mUiHandler.sendMessage(messageObtainMessage);
                return;
            }
            if (i9 == 4) {
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(1005);
            } else if (i9 == 8) {
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2010);
            }
        }

        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionStatusChanged(int i8, int i9, String str) {
        }
    };
    private final IAndroidAutoAppListener mAndroidAutoAppListener = new IAndroidAutoAppListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.4
        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onVideoFocusStatusChanged(int i8) {
            a.b("UE_AndroidAutoManager", "android auto video focus status change :" + i8);
            super.onVideoFocusStatusChanged(i8);
            if (i8 != 1) {
                AndroidAutoManager.this.isAndroidAutoCasting = false;
                return;
            }
            AndroidAutoManager.this.isAndroidAutoCasting = true;
            d.a(AndroidAutoManager.this.mContext);
            AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2011);
            if (AndroidAutoManager.this.mContext.getResources().getConfiguration().orientation == 1) {
                AndroidAutoManager.this.mUiHandler.sendEmptyMessage(1004);
            }
        }
    };
    private final IBatteryStatusListener iBatteryStatusListener = new IBatteryStatusListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.5
        @Override // ts.car.service.androidauto.IBatteryStatusListener
        public void onBatteryStatusChanged(int i8, int i9, boolean z7) {
            super.onBatteryStatusChanged(i8, i9, z7);
            AndroidAutoManager.this.mUiHandler.sendEmptyMessage(2003);
        }
    };
    private final IServiceConnectionListener mAppManagerServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.6
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            a.i("UE_AndroidAutoManager,Android App Manager onServiceConnected ");
            try {
                AndroidAutoManager.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(AndroidAutoManager.this.mAndroidAutoAppListener);
                AndroidAutoManager.this.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(AndroidAutoManager.this.mAndroidAutoSessionStatusListener);
                AndroidAutoManager.this.mAndroidAutoAppManager.registerBatteryStatusListener(AndroidAutoManager.this.iBatteryStatusListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.c("UE_AndroidAutoManager,Android App Manager onServiceDisconnected ");
            AndroidAutoManager.this.unRegisterAppManagerListener();
        }
    };
    private final IServiceConnectionListener mDeviceListServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.7
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            a.i("UE_AndroidAutoManager,Android Device List onServiceConnected ");
            try {
                AndroidAutoManager.this.mAndroidAutoDeviceListManager.registerAndroidAutoDeviceListListener(AndroidAutoManager.this.mAndroidAutoDeviceListListener);
                AndroidAutoManager.this.mDevicesConnected = true;
                if (AndroidAutoManager.this.isBootGuideComplete()) {
                    AndroidAutoManager.this.handleUsbAndroidAutoDevice();
                } else {
                    AndroidAutoManager.this.registerBootGuideCompete();
                }
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.c("UE_AndroidAutoManager,Android Device List onServiceDisconnected ");
            AndroidAutoManager.this.mDevicesConnected = false;
            AndroidAutoManager.this.unRegisterDeviceListListener();
        }
    };
    private final String KEY_BOOT_GUIDE_COMPLETE = "sys.byd.safety.warn";
    private final ContentObserver mBootGuideObserver = new ContentObserver(new Handler()) { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.10
        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            super.onChange(z7);
            a.c("UE_AndroidAutoManager,-----------onChange------");
            if (AndroidAutoManager.this.isBootGuideComplete()) {
                AndroidAutoManager.this.handleUsbAndroidAutoDevice();
            }
        }
    };

    private AndroidAutoManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static AndroidAutoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CarPlayManager.class) {
                if (mInstance == null) {
                    mInstance = new AndroidAutoManager(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUsbAndroidAutoDevice() {
        try {
            List<AndroidAutoDevice> usbAndroidAutoDeviceList = this.mAndroidAutoDeviceListManager.getUsbAndroidAutoDeviceList();
            int androidAutoConnectionStatus = getAndroidAutoConnectionStatus();
            boolean zIsCarPlayConnected = CarPlayManager.getInstance(this.mContext).isCarPlayConnected();
            if (usbAndroidAutoDeviceList != null && usbAndroidAutoDeviceList.size() == 1) {
                AndroidAutoDevice androidAutoDevice = usbAndroidAutoDeviceList.get(0);
                if (androidAutoDevice.getDeviceStatus() != 4) {
                    if (androidAutoConnectionStatus == 4 || zIsCarPlayConnected) {
                        a.c("UE_AndroidAutoManager,ACC ON,show switch device dialog.");
                        sendMsgShowSwitchDeviceDialog(androidAutoDevice.getUsbSerialNumber());
                    } else if (androidAutoDevice.isFirstConntect()) {
                        a.c("UE_AndroidAutoManager,ACC ON,usb first connection,show Android Auto Start Dialog.");
                        sendMsgShowAndroidAutoStartDialog(androidAutoDevice.getUsbSerialNumber(), 1);
                    } else {
                        a.c("UE_AndroidAutoManager,ACC ON,usb NOT first,connecting direct.");
                        connectAndroidAuto(androidAutoDevice.getUsbSerialNumber(), 1);
                    }
                }
            }
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerBootGuideCompete() {
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("sys.byd.safety.warn"), false, this.mBootGuideObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgShowSwitchDeviceDialog(String str) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 2006;
        Bundle bundle = new Bundle();
        bundle.putString("deviceId", str);
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterAppManagerListener() {
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.unregisterAndroidAutoAppListener(this.mAndroidAutoAppListener);
                this.mAndroidAutoAppManager.unregisterAndroidAutoSessionStatusListener(this.mAndroidAutoSessionStatusListener);
                this.mAndroidAutoAppManager.unregisterBatteryStatusListener(this.iBatteryStatusListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterDeviceListListener() {
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceListManager;
        if (iAndroidAutoDeviceListManager != null) {
            try {
                iAndroidAutoDeviceListManager.unregisterAndroidAutoDeviceListListener(this.mAndroidAutoDeviceListListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
    }

    public void connectAndroidAuto(final String str, final int i8) {
        if (this.mAndroidAutoDeviceListManager == null) {
            a.c("mAndroidAutoDeviceListManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.c("mDevicesConnected not connect");
            return;
        }
        AndroidAutoDevice androidAutoDevice = this.mStatusChangedDevice;
        if (androidAutoDevice != null && androidAutoDevice.getDeviceStatus() == 3 && this.mStatusChangedDevice.getBtAddr() != null && (this.mStatusChangedDevice.getBtAddr().equals(str) || this.mStatusChangedDevice.getUsbSerialNumber().equals(str))) {
            a.a("UE_AndroidAutoManager,same with current connecting device , skip this connect .");
            sendMsgShowConnectingDialog(0);
            return;
        }
        if (c.a.a().a(this.mContext)) {
            a.a("UE_AndroidAutoManager,delay 2000ms to connect.");
            j3.a.b(new Runnable() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.8
                @Override // java.lang.Runnable
                public void run() {
                    ConnectRequest connectRequest = new ConnectRequest();
                    connectRequest.setConnectType(i8);
                    connectRequest.setDeviceId(str);
                    connectRequest.setUserRequest(true);
                    connectRequest.setForceProjection(true);
                    try {
                        AndroidAutoManager.this.mAndroidAutoDeviceListManager.connectAndroidAuto(connectRequest);
                        a.a("UE_AndroidAutoManager,connectAndroidAuto request:" + connectRequest);
                    } catch (RemoteException e8) {
                        e8.printStackTrace();
                        a.c("connectAndroidAuto ,RemoteException:" + e8);
                    }
                }
            }, BTDevice.CONNECTED_TIME_STANDARD);
            return;
        }
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setConnectType(i8);
        connectRequest.setDeviceId(str);
        connectRequest.setUserRequest(true);
        connectRequest.setForceProjection(true);
        try {
            this.mAndroidAutoDeviceListManager.connectAndroidAuto(connectRequest);
            a.a("UE_AndroidAutoManager,connectAndroidAuto request:" + connectRequest);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("connectAndroidAuto ,RemoteException:" + e8);
        }
    }

    public void deleteAndroidAutoDevice(String str) {
        if (this.mAndroidAutoDeviceListManager == null) {
            a.c("mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.c("mDevicesConnected not connect");
            return;
        }
        try {
            this.mAndroidAutoDeviceListManager.deleteAndroidAutoDevice(str);
            a.b("UE_AndroidAutoManager", "deleteAndroidAutoDevice devId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("deleteAndroidAutoDevice ,RemoteException:" + e8);
        }
    }

    public void disconnectAndroidAuto(String str) {
        if (this.mAndroidAutoDeviceListManager == null) {
            a.c("mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.c("mDevicesConnected not connect");
            return;
        }
        try {
            this.mAndroidAutoDeviceListManager.disconnectAndroidAuto(str);
            a.a("UE_AndroidAutoManager,disconnectAndroidAuto deviceId:" + a.h(str));
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,getAndroidAutoConnectionStatus,RemoteException:" + e8);
        }
    }

    public int getAndroidAutoConnectionStatus() {
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        int androidAutoSessionStatus = 0;
        if (iAndroidAutoAppManager == null) {
            a.c("UE_AndroidAutoManager,mAndroidAutoAppManager == null");
            return 0;
        }
        try {
            androidAutoSessionStatus = iAndroidAutoAppManager.getAndroidAutoSessionStatus();
        } catch (Exception e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,getAndroidAutoConnectionStatus,RemoteException:" + e8);
        }
        a.i("UE_AndroidAutoManager,getAndroidAutoConnectionStatus:" + androidAutoSessionStatus);
        return androidAutoSessionStatus;
    }

    public List<AndroidAutoDevice> getAndroidAutoDevices() {
        List<AndroidAutoDevice> arrayList = new ArrayList<>();
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceListManager;
        if (iAndroidAutoDeviceListManager == null) {
            a.c("UE_AndroidAutoManager,mAndroidAutoDeviceListManager == null");
            return arrayList;
        }
        try {
            arrayList = iAndroidAutoDeviceListManager.getAndroidAutoDeviceList();
            return arrayList == null ? new ArrayList() : arrayList;
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,getAndroidAutoDevices,RemoteException:" + e8);
            return arrayList;
        }
    }

    public AndroidAutoDevice getConnectedAndroidAutoDevice() {
        for (AndroidAutoDevice androidAutoDevice : getAndroidAutoDevices()) {
            if (androidAutoDevice.getDeviceStatus() == 4) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    public List<AndroidAutoDevice> getUsbAndroidAutoDevices() {
        List<AndroidAutoDevice> arrayList = new ArrayList<>();
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceListManager;
        if (iAndroidAutoDeviceListManager == null) {
            a.c("UE_AndroidAutoManager,getUsbAndroidAutoDevices , mAndroidAutoDeviceListManager == null");
            return arrayList;
        }
        try {
            arrayList = iAndroidAutoDeviceListManager.getUsbAndroidAutoDeviceList();
            return arrayList == null ? new ArrayList() : arrayList;
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,getAndroidAutoDevices,RemoteException:" + e8);
            return arrayList;
        }
    }

    public void handleAccOnEvent() {
        a.i("UE_AndroidAutoManager,handleAccOnEvent");
        if (isBootGuideComplete()) {
            handleUsbAndroidAutoDevice();
        } else {
            a.c("UE_AndroidAutoManager,boot guide is not complete.");
        }
    }

    public void init() {
        a.i("UE_AndroidAutoManager,init androidAuto ");
        if (this.mAndroidAutoAppManager == null) {
            IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
            this.mAndroidAutoAppManager = iAndroidAutoAppManager;
            if (iAndroidAutoAppManager != null) {
                iAndroidAutoAppManager.registerConnectionListener(this.mAppManagerServiceConnectionListener);
            }
        }
        if (this.mAndroidAutoDeviceListManager == null) {
            IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, this.mContext);
            this.mAndroidAutoDeviceListManager = iAndroidAutoDeviceListManager;
            if (iAndroidAutoDeviceListManager != null) {
                iAndroidAutoDeviceListManager.registerConnectionListener(this.mDeviceListServiceConnectionListener);
            }
        }
    }

    public boolean isAndroidAutoCasting() {
        return this.isAndroidAutoCasting;
    }

    public boolean isAndroidAutoConnected() {
        return getAndroidAutoConnectionStatus() == 4;
    }

    public boolean isAndroidAutoConnecting() {
        return getAndroidAutoConnectionStatus() == 3;
    }

    public boolean isBootGuideComplete() {
        int i8 = Settings.System.getInt(this.mContext.getContentResolver(), "sys.byd.safety.warn", 0);
        a.i("UE_AndroidAutoManager,isGuideComplete:" + i8);
        return i8 == 1;
    }

    public void sendMsgShowAndroidAutoStartDialog(String str, int i8) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 2005;
        Bundle bundle = new Bundle();
        bundle.putString("deviceName", str);
        bundle.putInt("connectType", i8);
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    public void sendMsgShowConnectingDialog(int i8) {
        this.mUiHandler.sendEmptyMessageDelayed(2007, i8);
    }

    public void setAndroidAutoConnectingDialog(final CastConnectingDialog castConnectingDialog) {
        a.b("UE_AndroidAutoManager", "setAndroidAutoConnectingDialog");
        j3.a.b(new Runnable() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.9
            @Override // java.lang.Runnable
            public void run() {
                if (AndroidAutoManager.this.getAndroidAutoConnectionStatus() != 3) {
                    return;
                }
                AndroidAutoManager.this.androidAutoConnectingDialog = castConnectingDialog;
                AndroidAutoManager.this.androidAutoConnectingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.cpaa.androidauto.AndroidAutoManager.9.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        AndroidAutoManager.this.androidAutoConnectingDialog = null;
                    }
                });
                AndroidAutoManager.this.androidAutoConnectingDialog.show();
            }
        }, 500L);
    }

    public void startAndroidAuto() {
        a.c("UE_AndroidAutoManager,-------startAndroidAuto------");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null) {
            a.c("UE_AndroidAutoManager,mAndroidAutoAppManager == null");
            return;
        }
        try {
            iAndroidAutoAppManager.requestUi(0);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,startAndroidAuto,RemoteException:" + e8);
        }
    }

    public void startAndroidAutoMusic() {
        a.c("UE_AndroidAutoManager,-------startAndroidAutoMusic------");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null) {
            a.c("UE_AndroidAutoManager,mAndroidAutoAppManager == null");
            return;
        }
        try {
            iAndroidAutoAppManager.requestUi(3);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_AndroidAutoManager,startAndroidAutoMusic,RemoteException:" + e8);
        }
    }

    public void unRegister() {
        unRegisterAppManagerListener();
        unRegisterDeviceListListener();
        this.mContext.getContentResolver().unregisterContentObserver(this.mBootGuideObserver);
    }

    public void connectAndroidAuto(ConnectRequest connectRequest) {
        if (this.mAndroidAutoDeviceListManager == null) {
            a.c("mAndroidAutoDeviceListManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.c("mDevicesConnected not connect");
            return;
        }
        try {
            this.mAndroidAutoDeviceListManager.connectAndroidAuto(connectRequest);
            a.a("UE_AndroidAutoManager,connectAndroidAuto request:" + connectRequest);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("connectAndroidAuto ,RemoteException:" + e8);
        }
    }
}
