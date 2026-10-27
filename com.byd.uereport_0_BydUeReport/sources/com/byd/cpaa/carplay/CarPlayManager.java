package com.byd.cpaa.carplay;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import b5.h;
import com.byd.bt.model.BTDevice;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.screencast.CarPlayStartDialog;
import com.byd.uereport.dialog.screencast.CarPlayWirelessDialog;
import com.byd.uereport.dialog.screencast.CastConnectingDialog;
import com.byd.uereport.dialog.screencast.CastProtectionNoticeDialog;
import com.byd.uereport.dialog.screencast.CastSwitchDeviceDialog;
import com.ts.car.core.ServiceProvider;
import g5.a;
import g5.b;
import g5.d;
import g5.k;
import g5.m;
import h3.c;
import j7.c;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListListener;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CarPlayConstants;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayManager {
    public static final String TAG = "UE_CarPlayManager";
    private static volatile CarPlayManager mInstance;
    private CastConnectingDialog carPlayConnectingDialog;
    private boolean isDisconnectingState;
    private boolean isUIShow;
    private int lastStatus;
    private String mConnectingDeviceBtMac;
    private final Context mContext;
    private CarPlayDevice mCurrentChangeCarPlayDevice;
    private String mDeviceName;
    private String mRealUsbDeviceSerialNum;
    private volatile int mScreenOrientation;
    private String mUsbBtMac;
    private String mUsbDeviceSerialNum;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private boolean isNeedConnectCarPlay = false;
    private boolean isUSBNeedConnectCarPlay = false;
    private boolean isUserConnectCp = true;
    private final int MSG_DIALOG_WIRELESS = 1000;
    private final int MSG_DIALOG_CONNECTING_SHOW = 1001;
    private final int MSG_DIALOG_CONNECTING_DISMISS = 1002;
    private final int MSG_TOAST_CONNECT = 2003;
    private final int MSG_TOAST_CONNECT_FAILED = 2004;
    private final int MSG_TOAST_ROTATION_SCREEN_TIP = 2005;
    private final int MSG_DIALOG_SWITCH_DEVICE_TIP = 2006;
    private final int MSG_TOAST_CARPLAY_DISABLE_TIP = 2007;
    private final int MSG_DIALOG_CAST_PROTECTION = 2008;
    private final int MSG_DIALOG_CARPLAY_START = 2009;
    private final int MSG_TOAST_UPGRADE_IOS_VERSION = 2010;
    private final Handler mUiHandler = new Handler(new Handler.Callback() { // from class: com.byd.cpaa.carplay.CarPlayManager.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i8 = message.what;
            switch (i8) {
                case 1000:
                    a.b(CarPlayManager.TAG, "show wireless dialog .");
                    String string = message.getData().getString("deviceName");
                    String string2 = message.getData().getString("deviceId");
                    String string3 = message.getData().getString("deviceSerialNum");
                    CarPlayWirelessDialog carPlayWirelessDialog = new CarPlayWirelessDialog(CarPlayManager.this.mContext);
                    carPlayWirelessDialog.f(string);
                    carPlayWirelessDialog.g(string3);
                    if (string2 != null) {
                        carPlayWirelessDialog.e(string2);
                    }
                    carPlayWirelessDialog.show();
                    return false;
                case 1001:
                    String string4 = message.getData().getString("deviceName");
                    if (CarPlayManager.this.carPlayConnectingDialog == null) {
                        a.b(CarPlayManager.TAG, "show connecting dialog .");
                        CarPlayManager carPlayManager = CarPlayManager.this;
                        carPlayManager.carPlayConnectingDialog = new CastConnectingDialog(carPlayManager.mContext);
                        CarPlayManager.this.carPlayConnectingDialog.setDeviceName(string4);
                        CarPlayManager.this.carPlayConnectingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.1.1
                            @Override // android.content.DialogInterface.OnDismissListener
                            public void onDismiss(DialogInterface dialogInterface) {
                                CarPlayManager.this.carPlayConnectingDialog = null;
                            }
                        });
                        CarPlayManager.this.carPlayConnectingDialog.show();
                    }
                    return false;
                case 1002:
                    if (CarPlayManager.this.carPlayConnectingDialog != null) {
                        a.b(CarPlayManager.TAG, "dismiss connecting dialog .");
                        CarPlayManager.this.carPlayConnectingDialog.dismiss();
                        CarPlayManager.this.carPlayConnectingDialog = null;
                    }
                    return false;
                default:
                    switch (i8) {
                        case 2003:
                            if (!CarPlayManager.this.isUIShow) {
                                a.b(CarPlayManager.TAG, "show carplay connect toast .");
                                m.e(CarPlayManager.this.mContext, CarPlayManager.this.mContext.getString(R.string.cp_toast_connected_tip), 0);
                            }
                            break;
                        case 2004:
                            a.b(CarPlayManager.TAG, "show carplay connect failed toast . ");
                            m.e(CarPlayManager.this.mContext, CarPlayManager.this.mContext.getString(R.string.cp_toast_unavailable_tip), 0);
                            break;
                        case 2005:
                            a.b(CarPlayManager.TAG, "show rotation screen toast .");
                            m.e(CarPlayManager.this.mContext, CarPlayManager.this.mContext.getString(R.string.aa_toast_screen_rotation_tip), 0);
                            break;
                        case 2006:
                            a.b(CarPlayManager.TAG, "show switch device dialog .");
                            String string5 = message.getData().getString("deviceId");
                            CastSwitchDeviceDialog castSwitchDeviceDialog = new CastSwitchDeviceDialog(CarPlayManager.this.mContext);
                            castSwitchDeviceDialog.l(0);
                            castSwitchDeviceDialog.k(string5);
                            castSwitchDeviceDialog.show();
                            break;
                        case 2007:
                            a.b(CarPlayManager.TAG, "show device disable carplay toast .");
                            h3.a.e(CarPlayManager.this.mContext).g();
                            break;
                        case 2008:
                            a.b(CarPlayManager.TAG, "show cast dialog .");
                            String string6 = message.getData().getString("btMac");
                            CastProtectionNoticeDialog castProtectionNoticeDialog = new CastProtectionNoticeDialog(CarPlayManager.this.mContext);
                            castProtectionNoticeDialog.f(string6);
                            castProtectionNoticeDialog.show();
                            break;
                        case 2009:
                            CarPlayStartDialog carPlayStartDialog = new CarPlayStartDialog(CarPlayManager.this.mContext);
                            String string7 = message.getData().getString("serialNum");
                            if (!TextUtils.isEmpty(string7)) {
                                carPlayStartDialog.j(string7);
                                carPlayStartDialog.i(true);
                            }
                            String string8 = message.getData().getString("mac");
                            String string9 = message.getData().getString("name");
                            carPlayStartDialog.h(string8);
                            carPlayStartDialog.setDeviceName(string9);
                            carPlayStartDialog.show();
                            break;
                        case 2010:
                            a.b(CarPlayManager.TAG, "show upgrade ios version toast .");
                            m.e(CarPlayManager.this.mContext, CarPlayManager.this.mContext.getString(R.string.cp_toast_upgrade_ios_version), 0);
                            break;
                    }
                    break;
            }
        }
    });
    private final ICarPlayStateListener mCarPlayStateListener = new ICarPlayStateListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.2
        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onCarPlaySessionConnectStateChanged(int i8, boolean z7, String str, boolean z8) {
            super.onCarPlaySessionConnectStateChanged(i8, z7, str, z8);
            a.b(CarPlayManager.TAG, "onCarPlaySessionConnectStateChanged,status :" + i8 + " isUserConnect : " + z8);
            if (i8 == 1) {
                CarPlayManager.this.isUserConnectCp = z8;
            }
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionConnectStateChanged(int i8, boolean z7, String str) {
            super.onSessionConnectStateChanged(i8, z7, str);
            a.b(CarPlayManager.TAG, "onSessionConnectStateChanged,status :" + i8 + " isOldConnection : " + z7);
            if (i8 == 2 && z7) {
                CarPlayManager.this.mUiHandler.sendEmptyMessage(2010);
            }
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int i8) {
            a.b(CarPlayManager.TAG, "onSessionStateChanged:" + i8);
            if (i8 == 5 && !CarPlayManager.this.isDisconnectingState && CarPlayManager.this.isUserConnectCp) {
                CarPlayManager.this.mUiHandler.sendEmptyMessage(2004);
            }
        }
    };
    private final ICarPlayAppListener mCarPlayAppListener = new ICarPlayAppListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.3
        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            a.b(CarPlayManager.TAG, "onCarPlayDisplayStatusChanged:::::::" + z7);
            CarPlayManager.this.isUIShow = z7;
            if (z7) {
                h.e(CarPlayManager.this.mContext).c();
                d.a(CarPlayManager.this.mContext);
                if (CarPlayManager.this.mScreenOrientation == 1) {
                    CarPlayManager.this.mUiHandler.sendEmptyMessage(2005);
                }
            }
        }
    };
    private final ICarPlayDeviceListListener mCarPlayDeviceListListener = new ICarPlayDeviceListListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.4
        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) {
            a.b(CarPlayManager.TAG, "onCarPlayDeviceStatusChanged: [device name :" + carPlayDevice.getDeviceName() + ", status:" + carPlayDevice.getDeviceStatus() + ", usbSerialNum:" + a.h(carPlayDevice.getUsbSerialNumber()) + ", curWirelessCap:" + carPlayDevice.getCurWirelessCap() + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("lastStatus :");
            sb.append(CarPlayManager.this.lastStatus);
            a.b(CarPlayManager.TAG, sb.toString());
            CarPlayManager.this.mUsbDeviceSerialNum = carPlayDevice.getUsbSerialNumber();
            CarPlayManager.this.mDeviceName = carPlayDevice.getDeviceName();
            CarPlayManager.this.mCurrentChangeCarPlayDevice = carPlayDevice;
            int deviceStatus = carPlayDevice.getDeviceStatus();
            if (deviceStatus == 3) {
                CarPlayManager.this.isDisconnectingState = false;
                CarPlayManager carPlayManager = CarPlayManager.this;
                carPlayManager.sendConnectingLocalBroadcast(carPlayManager.mUsbDeviceSerialNum);
                if (CarPlayManager.this.carPlayConnectingDialog == null && CarPlayManager.this.lastStatus != 3 && CarPlayManager.this.isUserConnectCp) {
                    CarPlayManager.this.mConnectingDeviceBtMac = carPlayDevice.getBtAddr();
                    CarPlayManager.this.sendMsgShowConnectingDialog(20);
                }
            } else if (deviceStatus == 4) {
                if (CarPlayManager.this.carPlayConnectingDialog != null) {
                    CarPlayManager.this.mUiHandler.sendEmptyMessage(1002);
                } else {
                    CarPlayManager.this.mUiHandler.removeMessages(1001);
                }
            } else if (deviceStatus == 2) {
                if (CarPlayManager.this.carPlayConnectingDialog != null) {
                    CarPlayManager.this.mUiHandler.sendEmptyMessage(1002);
                } else {
                    CarPlayManager.this.mUiHandler.removeMessages(1001);
                }
                if (CarPlayManager.this.lastStatus == 3 && CarPlayManager.this.isUserConnectCp) {
                    CarPlayManager.this.mUiHandler.sendEmptyMessageDelayed(2004, 500L);
                }
            } else if (deviceStatus == 1) {
                if (carPlayDevice.getBtAddr().equals(CarPlayManager.this.mConnectingDeviceBtMac)) {
                    if (CarPlayManager.this.lastStatus == 3) {
                        CarPlayManager.this.mUiHandler.removeMessages(1001);
                        if (CarPlayManager.this.carPlayConnectingDialog != null) {
                            CarPlayManager.this.mUiHandler.sendEmptyMessage(1002);
                        }
                    }
                    if (CarPlayManager.this.lastStatus == 3 && !carPlayDevice.getCurWirelessCap() && !CarPlayManager.this.isDisconnectingState && CarPlayManager.this.isUserConnectCp) {
                        CarPlayManager.this.mUiHandler.sendEmptyMessageDelayed(2007, 500L);
                    }
                } else {
                    a.a("UE_CarPlayManager,not the same device.");
                }
            }
            CarPlayManager.this.lastStatus = carPlayDevice.getDeviceStatus();
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onCarPlayOobStartEvent(byte[] bArr) {
            super.onCarPlayOobStartEvent(bArr);
            if (!b.a(CarPlayManager.this.mContext)) {
                a.i("UE_CarPlayManager,boot is not complete, skip this onCarPlayOobStartEvent.");
                return;
            }
            CarPlayManager.this.setUSBNeedConnectCarPlay(false);
            if (bArr == null || bArr.length == 0) {
                a.b(CarPlayManager.TAG, "onCarPlayOobStartEvent already pair");
                return;
            }
            a.b(CarPlayManager.TAG, "onCarPlayOobStartEvent new oob device");
            StringBuffer stringBuffer = new StringBuffer();
            for (int i8 = 0; i8 < bArr.length; i8++) {
                String hexString = Integer.toHexString(bArr[i8] & 255);
                if (hexString.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(hexString);
                if (i8 != bArr.length - 1) {
                    stringBuffer.append(":");
                }
            }
            a.b(CarPlayManager.TAG, "onCarPlayOobStartEvent new oob device mac: " + ((Object) stringBuffer));
            CarPlayManager carPlayManager = CarPlayManager.this;
            carPlayManager.mRealUsbDeviceSerialNum = carPlayManager.mUsbDeviceSerialNum;
            a.b(CarPlayManager.TAG, ",mRealUsbDeviceSerialNum :" + a.h(CarPlayManager.this.mRealUsbDeviceSerialNum));
            CarPlayManager.this.mUsbBtMac = stringBuffer.toString();
            CarPlayManager carPlayManager2 = CarPlayManager.this;
            carPlayManager2.sendMsgShowStartDialog(carPlayManager2.mUsbBtMac, true);
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onProjectionRequested(CarPlayDevice carPlayDevice, int i8) {
            super.onProjectionRequested(carPlayDevice, i8);
            a.b(CarPlayManager.TAG, "onProjectionRequested device:" + carPlayDevice.getDeviceName());
            CarPlayManager.this.mDeviceName = carPlayDevice.getDeviceName();
            String btAddr = carPlayDevice.getBtAddr();
            c.c().k(new m2.a(230011));
            if (btAddr.equalsIgnoreCase(CarPlayManager.this.mUsbBtMac)) {
                if (!CarPlayManager.this.isUSBNeedConnectCarPlay) {
                    a.b(CarPlayManager.TAG, " set USB Need Connect CarPlay true. ");
                    CarPlayManager.this.setUSBNeedConnectCarPlay(true);
                    return;
                }
                if (CarPlayManager.this.isCarPlayConnected() || AndroidAutoManager.getInstance(CarPlayManager.this.mContext).isAndroidAutoConnected()) {
                    a.b(CarPlayManager.TAG, "USB show switch dialog ! ");
                    CarPlayManager.this.sendMsgShowSwitchDialog(carPlayDevice.getBtAddr());
                } else {
                    a.b(CarPlayManager.TAG, "USB Need Connect CarPlay ! ");
                    CarPlayManager.this.connectCarPlay(CarPlayManager.this.createCarPlayRequest(carPlayDevice.getBtAddr()));
                }
                CarPlayManager.this.setUSBNeedConnectCarPlay(false);
                return;
            }
            int androidAutoConnectionStatus = AndroidAutoManager.getInstance(CarPlayManager.this.mContext).getAndroidAutoConnectionStatus();
            boolean zIsCarPlayConnected = CarPlayManager.this.isCarPlayConnected();
            a.b(CarPlayManager.TAG, "androidAutoStatus : " + androidAutoConnectionStatus + ",isCarPlayConnected :" + zIsCarPlayConnected);
            if (androidAutoConnectionStatus == 4 || zIsCarPlayConnected) {
                CarPlayManager.this.sendMsgShowSwitchDialog(carPlayDevice.getBtAddr());
            } else {
                CarPlayManager.this.sendMsgShowStartDialog(carPlayDevice.getBtAddr(), false);
            }
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void oniPhoneAttachedOverUsb(CarPlayDevice carPlayDevice, boolean z7) {
            super.oniPhoneAttachedOverUsb(carPlayDevice, z7);
            a.b(CarPlayManager.TAG, "oniPhoneAttachedOverUsb device:" + carPlayDevice.getDeviceName());
        }
    };
    private final IServiceConnectionListener mAppManagerServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.5
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            a.i("UE_CarPlayManager,App Manager onServiceConnected ");
            try {
                CarPlayManager carPlayManager = CarPlayManager.this;
                carPlayManager.lastStatus = carPlayManager.convertStatus(carPlayManager.getCarPlayStatus());
                CarPlayManager.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayManager.this.mCarPlayStateListener);
                CarPlayManager.this.mCarPlayAppManager.registerCarPlayAppListener(CarPlayManager.this.mCarPlayAppListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.c("UE_CarPlayManager,App Manager onServiceDisconnected ");
            CarPlayManager.this.unRegisterAppManagerListener();
        }
    };
    private final IServiceConnectionListener mDeviceManagerServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.6
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            a.i("UE_CarPlayManager,Device manager onServiceConnected ");
            try {
                CarPlayManager.this.mCarPlayDeviceManager.registerCarPlayDeviceListListener(CarPlayManager.this.mCarPlayDeviceListListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            a.c("UE_CarPlayManager,Device manager onServiceDisconnected ");
            CarPlayManager.this.unRegisterDeviceManagerListener();
        }
    };

    private CarPlayManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private synchronized void clearNotShowProtectRecord(CarPlayDevice carPlayDevice) {
        String btAddr = carPlayDevice.getBtAddr();
        String strF = k.f(this.mContext, "cp_not_protection_device", "");
        a.b(TAG, ",notShowProtectDeviceValue " + strF);
        if (strF != null && btAddr != null && strF.contains(btAddr)) {
            k.k(this.mContext, "cp_not_protection_device", strF.replace(btAddr, ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectCP(ConnectRequest connectRequest) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.c("UE_CarPlayManager,mCarPlayDeviceManager == null");
            return;
        }
        try {
            iCarPlayDeviceListManager.connectCarPlay(connectRequest);
            a.b(TAG, ",connectCarPlay request:" + connectRequest);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_CarPlayManager,connectCarPlay ,RemoteException:" + e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int convertStatus(int i8) {
        if (i8 == 1) {
            return 3;
        }
        if (i8 != 2) {
            return i8 != 3 ? 1 : 2;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConnectRequest createCarPlayRequest(String str) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDeviceId(str);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        return connectRequest;
    }

    public static CarPlayManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CarPlayManager.class) {
                if (mInstance == null) {
                    mInstance = new CarPlayManager(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectingLocalBroadcast(String str) {
        Intent intent = new Intent();
        intent.setAction("action_cp_connecting");
        intent.putExtra("device", str);
        a1.a.b(this.mContext).d(intent);
    }

    private void sendMsgShowCPProtectionDialog(String str) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 2008;
        Bundle bundle = new Bundle();
        bundle.putString("btMac", str);
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgShowStartDialog(String str, boolean z7) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 2009;
        Bundle bundle = new Bundle();
        bundle.putString("name", this.mDeviceName);
        bundle.putString("mac", str);
        if (z7) {
            bundle.putString("serialNum", this.mUsbDeviceSerialNum);
        }
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgShowSwitchDialog(String str) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 2006;
        Bundle bundle = new Bundle();
        bundle.putString("deviceId", str);
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    private void sendMsgShowWirelessDialog(String str) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 1000;
        Bundle bundle = new Bundle();
        bundle.putString("deviceName", this.mDeviceName);
        bundle.putString("deviceSerialNum", this.mUsbDeviceSerialNum);
        if (str != null) {
            bundle.putString("deviceId", str);
        }
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterAppManagerListener() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            try {
                iCarPlayAppManager.unregisterCarPlayStateListener(this.mCarPlayStateListener);
                this.mCarPlayAppManager.unregisterCarPlayAppListener(this.mCarPlayAppListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterDeviceManagerListener() {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            try {
                iCarPlayDeviceListManager.unregisterCarPlayDeviceListListener(this.mCarPlayDeviceListListener);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
    }

    public void clearUsbSerialNumber(String str) {
        a.b(TAG, "clearUsbSerialNumber");
        if (this.mUsbBtMac == null || str == null) {
            return;
        }
        if (str.equals(this.mRealUsbDeviceSerialNum) || str.contains(this.mRealUsbDeviceSerialNum)) {
            a.b(TAG, "clearUsbSerialNumber ...");
            this.mUsbBtMac = null;
            this.mRealUsbDeviceSerialNum = null;
        }
    }

    public void connectCarPlay(String str) {
        connectCarPlay(createCarPlayRequest(str));
    }

    public void deleteCarPlayDevice(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.c("UE_CarPlayManager,mCarPlayDeviceManager == null");
            return;
        }
        try {
            iCarPlayDeviceListManager.deleteCarPlayDevice(str);
            a.b(TAG, ",deleteCarPlayDevice devId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_CarPlayManager,deleteCarPlayDevice ,RemoteException:" + e8);
        }
    }

    public void directConnectCarPlay(final ConnectRequest connectRequest) {
        if (!c.a.a().a(this.mContext)) {
            connectCP(connectRequest);
        } else {
            a.b(TAG, ",delay 2000ms to connect.");
            j3.a.b(new Runnable() { // from class: com.byd.cpaa.carplay.CarPlayManager.8
                @Override // java.lang.Runnable
                public void run() {
                    CarPlayManager.this.connectCP(connectRequest);
                }
            }, BTDevice.CONNECTED_TIME_STANDARD);
        }
    }

    public void disconnectCarPlay(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.c("UE_CarPlayManager,mCarPlayDeviceManager == null");
            return;
        }
        try {
            this.isDisconnectingState = true;
            iCarPlayDeviceListManager.disconnectCarPlay(str);
            a.b(TAG, ",disconnectCarPlay deviceId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public ICarPlayDeviceListManager getCarPlayDeviceManager() {
        return this.mCarPlayDeviceManager;
    }

    public List<CarPlayDevice> getCarPlayDevices() {
        List<CarPlayDevice> arrayList = new ArrayList<>();
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.c("UE_CarPlayManager,mCarPlayDeviceManager == null");
            return arrayList;
        }
        try {
            arrayList = iCarPlayDeviceListManager.getCarPlayDeviceList();
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_CarPlayManager,getCarPlayDevices,RemoteException:" + e8);
        }
        a.b(TAG, "getCarPlayDevices size :" + arrayList.size());
        return arrayList;
    }

    public int getCarPlayStatus() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        int carPlaySessionStatus = 0;
        if (iCarPlayAppManager == null) {
            a.c("UE_CarPlayManager,mCarPlayAppManager == null");
            return 0;
        }
        try {
            carPlaySessionStatus = iCarPlayAppManager.getCarPlaySessionStatus();
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.c("UE_CarPlayManager,getCarPlayStatus,RemoteException:" + e8);
        }
        a.b(TAG, "getCarPlayStatus:" + carPlaySessionStatus);
        return carPlaySessionStatus;
    }

    public void init() {
        a.i("UE_CarPlayManager,init carplay ");
        this.mScreenOrientation = this.mContext.getResources().getConfiguration().orientation;
        if (this.mCarPlayAppManager == null) {
            ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.mContext);
            this.mCarPlayAppManager = iCarPlayAppManager;
            if (iCarPlayAppManager != null) {
                iCarPlayAppManager.registerConnectionListener(this.mAppManagerServiceConnectionListener);
            } else {
                a.i("UE_CarPlayManager,mCarPlayAppManager is null ");
            }
        }
        if (this.mCarPlayDeviceManager == null) {
            ICarPlayDeviceListManager iCarPlayDeviceListManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this.mContext);
            this.mCarPlayDeviceManager = iCarPlayDeviceListManager;
            if (iCarPlayDeviceListManager != null) {
                iCarPlayDeviceListManager.registerConnectionListener(this.mDeviceManagerServiceConnectionListener);
            } else {
                a.i("UE_CarPlayManager,mCarPlayDeviceManager is null  ");
            }
        }
    }

    public boolean isCarPlayConnected() {
        return getCarPlayStatus() == 2;
    }

    public boolean isCarPlayConnecting() {
        return getCarPlayStatus() == 1;
    }

    public boolean isNeedConnectCarPlay() {
        return this.isNeedConnectCarPlay;
    }

    public boolean isUSBNeedConnectCarPlay() {
        return this.isUSBNeedConnectCarPlay;
    }

    public void sendMsgShowConnectingDialog(int i8) {
        Message messageObtainMessage = this.mUiHandler.obtainMessage();
        messageObtainMessage.what = 1001;
        Bundle bundle = new Bundle();
        bundle.putString("deviceName", this.mDeviceName);
        messageObtainMessage.setData(bundle);
        this.mUiHandler.sendMessageDelayed(messageObtainMessage, i8);
    }

    public void setCarPlayConnectingDialog(final CastConnectingDialog castConnectingDialog) {
        a.b(TAG, "setCarPlayConnectingDialog");
        j3.a.b(new Runnable() { // from class: com.byd.cpaa.carplay.CarPlayManager.9
            @Override // java.lang.Runnable
            public void run() {
                CarPlayManager carPlayManager = CarPlayManager.this;
                if (carPlayManager.convertStatus(carPlayManager.getCarPlayStatus()) != 3) {
                    return;
                }
                CarPlayManager.this.carPlayConnectingDialog = castConnectingDialog;
                CarPlayManager.this.carPlayConnectingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.cpaa.carplay.CarPlayManager.9.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        CarPlayManager.this.carPlayConnectingDialog = null;
                    }
                });
                CarPlayManager.this.carPlayConnectingDialog.show();
            }
        }, 500L);
    }

    public void setNeedConnectCarPlay(boolean z7) {
        this.isNeedConnectCarPlay = z7;
    }

    public void setScreenOrientation(int i8) {
        this.mScreenOrientation = i8;
    }

    public void setUSBNeedConnectCarPlay(boolean z7) {
        this.isUSBNeedConnectCarPlay = z7;
    }

    public void showNowPlaying() {
        if (this.mCarPlayAppManager == null) {
            a.c("UE_CarPlayManager,mCarPlayAppManager == null");
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setComponent(new ComponentName(y4.b.f9302a, y4.b.f9303b));
        intent.setAction(CarPlayConstants.DEVICE_ASK_CARPLAYUI);
        this.mContext.startActivity(intent);
        try {
            this.mCarPlayAppManager.requestUi(4);
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public void startCarPlayDisPlay() {
        Intent intent = new Intent();
        intent.setPackage(y4.b.f9302a);
        intent.setClassName(y4.b.f9302a, y4.b.f9303b);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }

    public void unRegister() {
        unRegisterAppManagerListener();
        unRegisterDeviceManagerListener();
    }

    public void connectCarPlay(final ConnectRequest connectRequest) {
        CarPlayDevice carPlayDevice = this.mCurrentChangeCarPlayDevice;
        if (carPlayDevice != null && carPlayDevice.getDeviceStatus() == 3 && this.mCurrentChangeCarPlayDevice.getBtAddr() != null && this.mCurrentChangeCarPlayDevice.getBtAddr().equals(connectRequest.getDeviceId())) {
            a.a("UE_CarPlayManager,same with current connecting device , skip this connect .");
            sendMsgShowConnectingDialog(0);
        } else if (c.a.a().a(this.mContext)) {
            a.b(TAG, "delay 2000ms to connect.");
            j3.a.b(new Runnable() { // from class: com.byd.cpaa.carplay.CarPlayManager.7
                @Override // java.lang.Runnable
                public void run() {
                    CarPlayManager.this.connectCP(connectRequest);
                }
            }, BTDevice.CONNECTED_TIME_STANDARD);
        } else {
            connectCP(connectRequest);
        }
    }
}
