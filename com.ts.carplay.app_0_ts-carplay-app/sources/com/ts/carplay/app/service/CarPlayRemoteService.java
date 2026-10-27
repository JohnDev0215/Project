package com.ts.carplay.app.service;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.app.service.CarPlayRemoteService;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.utils.LogUtil;
import com.ts.lib.car.VehicleVendorProperty;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListListener;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.IWirelessCarPlayAvailabilityCallback;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayRemoteService extends Service {
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    private static final String CPCONNECT_CLASS_NAME = "com.ts.carplay.app.ui.entry.ShowConnectToast";
    private static final String CPCONNECT_PACKAGE_NAME = "com.ts.carplay.app";
    private static final String CPDISCONNECT_CLASS_NAME = "com.ts.carplay.app.ui.entry.ShowDisconnectToast";
    private static final String CPDISCONNECT_PACKAGE_NAME = "com.ts.carplay.app";
    public static final int START_CHOICE_CARPLAY = 1;
    private static final String TAG = "CarPlayRemoteService";
    private Context mContext;
    private Handler mHandler;
    private String mMacAddress;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private BtBroadcastReceiver mReceiver = null;
    public IWirelessCarPlayAvailabilityCallback mIWirelessCarPlayAvailabilityCallback = new IWirelessCarPlayAvailabilityCallback() { // from class: com.ts.carplay.app.service.CarPlayRemoteService.2
        public void onWirelessCarPlayAvailabilityChanged(String mac, boolean isSupported) {
            Message message = new Message();
            if (isSupported) {
                LogUtil.info(CarPlayRemoteService.TAG, "wirelesscarplay start");
                message.what = 1;
            } else {
                Intent intent = new Intent();
                intent.setFlags(VehicleVendorProperty.VehiclePropertyGroup.SYSTEM);
                intent.setComponent(new ComponentName("com.ts.carplay.app", CarPlayRemoteService.CPDISCONNECT_CLASS_NAME));
            }
            CarPlayRemoteService.this.mHandler.sendMessage(message);
        }
    };
    private final IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.carplay.app.service.CarPlayRemoteService.3
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(CarPlayRemoteService.TAG, "mCarPlayDeviceManager:" + CarPlayRemoteService.this.mCarPlayDeviceManager);
            if (CarPlayRemoteService.this.mCarPlayDeviceManager != null) {
                try {
                    LogUtil.debug(CarPlayRemoteService.TAG, "registerCarPlayDeviceListListener");
                    CarPlayRemoteService.this.mCarPlayDeviceManager.registerCarPlayDeviceListListener(CarPlayRemoteService.this.mCarPlayDeviceListListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void onServiceDisconnected(IPlatformService service) {
            if (CarPlayRemoteService.this.mCarPlayDeviceManager != null) {
                try {
                    CarPlayRemoteService.this.mCarPlayDeviceManager.unregisterCarPlayDeviceListListener(CarPlayRemoteService.this.mCarPlayDeviceListListener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };
    private final ICarPlayDeviceListListener mCarPlayDeviceListListener = new AnonymousClass4();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        this.mCarPlayDeviceManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this);
        this.mCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this);
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        initHandler();
        init(this.mContext);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mReceiver = new BtBroadcastReceiver();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: com.ts.carplay.app.service.CarPlayRemoteService.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    LogUtil.debug(CarPlayRemoteService.TAG, "START_CHOICE_CARPLAY=" + CarPlayRemoteService.this.mMacAddress);
                    Intent intent = new Intent();
                    intent.setFlags(VehicleVendorProperty.VehiclePropertyGroup.SYSTEM);
                    intent.putExtra("serialNumber", CarPlayRemoteService.this.mMacAddress);
                    intent.setComponent(new ComponentName("com.ts.carplay.app", CarPlayRemoteService.CPCONNECT_CLASS_NAME));
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_BOND_STATE_CHANGED);
        this.mContext.registerReceiver(this.mReceiver, filter, null, this.mHandler);
    }

    private class BtBroadcastReceiver extends BroadcastReceiver {
        private BtBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctx, Intent intent) {
            try {
                String action = intent.getAction();
                LogUtil.debug(CarPlayRemoteService.TAG, "onReceive(): action=" + action);
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    for (String key : bundle.keySet()) {
                        LogUtil.debug(CarPlayRemoteService.TAG, "key:" + key + ",value:" + bundle.get(key));
                    }
                }
                if (action.equals(CarPlayRemoteService.ACTION_BOND_STATE_CHANGED)) {
                    int btState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                    LogUtil.debug(CarPlayRemoteService.TAG, "Receive btState:" + btState);
                    if (btState == 12) {
                        BluetoothDevice btDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        CarPlayRemoteService.this.mMacAddress = btDevice.getAddress();
                        LogUtil.debug(CarPlayRemoteService.TAG, "Receive btMacAddress:" + CarPlayRemoteService.this.mMacAddress);
                        try {
                            CarPlayRemoteService.this.mCarPlayDeviceManager.checkWirelessCarPlayAvailability(CarPlayRemoteService.this.mMacAddress, CarPlayRemoteService.this.mIWirelessCarPlayAvailabilityCallback);
                        } catch (RemoteException e) {
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.info(TAG, "onStartCommand start" + intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        uninit(this.mContext);
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mCarPlayDeviceManager = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info(TAG, "onBind start.");
        return null;
    }

    private void init(Context context) {
        LogUtil.info(TAG, "init start context:" + context);
        CarPlayRemoteManager.getInstance().init(context);
        LogUtil.info(TAG, "init end.");
    }

    private void uninit(Context context) {
        CarPlayRemoteManager.getInstance().uninit(context);
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.service.CarPlayRemoteService$4, reason: invalid class name */
    class AnonymousClass4 implements ICarPlayDeviceListListener {
        AnonymousClass4() {
        }

        public void oniPhoneAttachedOverUsb(CarPlayDevice device, final boolean supported) {
            LogUtil.debug(CarPlayRemoteService.TAG, "oniPhoneAttachedOverUsb supported: " + supported + ", device: " + device);
            final String serialNumber = device.getUsbSerialNumber();
            CarPlayRemoteService.this.mHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.-$$Lambda$CarPlayRemoteService$4$_9QqO0U9vMJn0fg6LOr8N7d9s_0
                @Override // java.lang.Runnable
                public final void run() {
                    CarPlayRemoteService.AnonymousClass4.lambda$oniPhoneAttachedOverUsb$0(supported, serialNumber);
                }
            });
        }

        static /* synthetic */ void lambda$oniPhoneAttachedOverUsb$0(boolean supported, String serialNumber) {
            if (!supported) {
                Intent intent = new Intent();
                intent.setFlags(VehicleVendorProperty.VehiclePropertyGroup.SYSTEM);
                intent.setComponent(new ComponentName("com.ts.carplay.app", CarPlayRemoteService.CPDISCONNECT_CLASS_NAME));
            } else {
                Intent intent2 = new Intent();
                intent2.setFlags(VehicleVendorProperty.VehiclePropertyGroup.SYSTEM);
                intent2.putExtra("serialNumber", serialNumber);
                intent2.setComponent(new ComponentName("com.ts.carplay.app", CarPlayRemoteService.CPCONNECT_CLASS_NAME));
            }
        }

        public void onCarPlayDeviceStatusChanged(CarPlayDevice device) {
            LogUtil.debug(CarPlayRemoteService.TAG, "onCarPlayDeviceStatusChanged: " + device);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.debug(TAG, "onConfigurationChanged newConfig" + newConfig);
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.app.service.-$$Lambda$CarPlayRemoteService$jh1SHE1CSzO1CJo3zSqNxciY7RA
            @Override // java.lang.Runnable
            public final void run() {
                CarPlayRemoteManager.getInstance().notifyUiConfigurationChanged(newConfig);
            }
        });
    }
}
