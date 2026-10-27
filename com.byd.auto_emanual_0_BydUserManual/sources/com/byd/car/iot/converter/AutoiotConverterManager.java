package com.byd.car.iot.converter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.byd.autoiot.service.AutoiotProfile;
import com.byd.autoiot.service.IAutoiotInterface;
import com.byd.car.iot.ICarIotManagerCallback;
import com.byd.car.iot.ReadStatusEnum;
import com.byd.car.iot.WriteStatusEnum;
import com.byd.car.iot.converter.AutoiotSettingConstants;
import com.byd.foundation.utils.StringUtils;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final class AutoiotConverterManager extends DefaultTaskWrapper implements ServiceConnection {
    private static final String CONNECTED = "CONNECTED";
    private static final String DISCONNECTED = "DISCONNECTED";
    private static final String SERVICE_ACTION = "autoiot.service";
    private static final String SERVICE_PACKAGE = "com.byd.autoiot.service";
    private static final long SINGLE_TASK_DELAY = 200;
    private static final String TAG = "AutoiotConverterManager";
    private static final long TASK_DELAY = 800;
    public volatile boolean bounded;
    private ICarIotManagerCallback iCarIotManagerCallback;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Handler mListener;
    private IAutoiotInterface remoteServer;

    public static class AutoIotControlType {
        public static final int DI_CAR_CONTROL = 0;
        public static final int DI_CAR_CONTROL_CB = 2;
        public static final int DI_CAR_GET = 1;
        public static final int DI_CAR_GET_CB = 3;
        public static final int DI_CAR_GET_DEVICE_LIST = 4;
    }

    public void init(Context context) {
        this.mContext = context;
        bindServer();
    }

    private static final class SingletonHolder {
        private static final AutoiotConverterManager INSTANCE = new AutoiotConverterManager();

        private SingletonHolder() {
        }
    }

    public static AutoiotConverterManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public IAutoiotInterface getRemoteServer() {
        return this.remoteServer;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    private void bindServer() {
        Intent intent = new Intent();
        intent.setAction(SERVICE_ACTION);
        intent.setPackage(SERVICE_PACKAGE);
        if (this.mContext.bindService(intent, this, 1)) {
            Log.d(TAG, "bindServer success");
        } else {
            Log.d(TAG, "bindServer failed");
        }
    }

    private AutoiotConverterManager() {
        this.mHandlerThread = new HandlerThread("AutoBLEDeviceManager");
        this.remoteServer = null;
        this.bounded = false;
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.byd.car.iot.converter.AutoiotConverterManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                AutoiotConverterManager.this.onDiCarMessage(message);
            }
        };
        this.mListener = new Handler(this.mHandlerThread.getLooper());
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            IAutoiotInterface iAutoiotInterfaceAsInterface = IAutoiotInterface.Stub.asInterface(iBinder);
            this.remoteServer = iAutoiotInterfaceAsInterface;
            iAutoiotInterfaceAsInterface.registerListener(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onServiceConnected");
        this.bounded = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.bounded = false;
        try {
            this.remoteServer.unregisterListener(this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerCallBack(ICarIotManagerCallback iCarIotManagerCallback) {
        this.iCarIotManagerCallback = iCarIotManagerCallback;
    }

    public void unregisterCallBack() {
        this.iCarIotManagerCallback = null;
    }

    private boolean remoteRead(String str, String str2) throws RemoteException {
        if (this.remoteServer == null) {
            Log.w(TAG, "[remoteRead]read from device:" + str + " , attr:" + str2 + "failed! cause unbind server.");
            return false;
        }
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            Log.w(TAG, "[remoteRead]read from device:" + str + " , attr:" + str2 + "failed");
            return false;
        }
        Pair<String, String> pair = AutoiotSettingConstants.parserAttr(str2);
        if (pair == null) {
            Log.w(TAG, "[remoteRead]read from device:" + str + " , attr:" + str2 + " failed! parser attr error!");
            return false;
        }
        Log.d(TAG, "[remoteRead]read from device:" + str + " , attr:" + str2);
        return this.remoteServer.autoiotReadChr(str, (String) pair.first, (String) pair.second);
    }

    private boolean remoteWrite(String str, String str2, AutoiotProfile autoiotProfile) throws RemoteException {
        if (this.remoteServer == null) {
            Log.w(TAG, "[remoteWrite]read from device:" + str + " , attr:" + str2 + "failed! cause unbind server.");
            return false;
        }
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str2) || autoiotProfile == null) {
            Log.w(TAG, "[remoteWrite]write to device:" + str + " , attr:" + str2 + ",autoiotProfile:" + autoiotProfile + "failed");
            return false;
        }
        Pair<String, String> pair = AutoiotSettingConstants.parserAttr(str2);
        if (pair == null) {
            Log.w(TAG, "[remoteWrite]write to device:" + str + " , attr:" + str2 + " failed! parser attr error!");
            return false;
        }
        Log.d(TAG, "[remoteWrite]write to device:" + str + " , attr:" + str2);
        return this.remoteServer.autoiotWriteChr(autoiotProfile, str, (String) pair.first, (String) pair.second);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDiCarMessage(final Message message) {
        if (message.obj instanceof AutoiotSetting) {
            final AutoiotSetting autoiotSetting = (AutoiotSetting) message.obj;
            if (this.remoteServer == null) {
                Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$tYYZ7FUOSlcnp6GIel6Ys07HpHI
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$onDiCarMessage$1$AutoiotConverterManager(message, (ICarIotManagerCallback) obj);
                    }
                });
                Log.w(TAG, "unbind server !");
                return;
            }
            int i = message.what;
            if (i == 0) {
                AutoiotProfile autoiotProfile = new AutoiotProfile();
                autoiotProfile.setAutoiotProfile(autoiotSetting.getAutoiotValues());
                IotTask<Boolean> iotTaskCreate = IotTask.create(TASK_DELAY);
                iotTaskCreate.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$ICEHBUCXO5SGnbYJbYbU_C6IgUk
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.f$0.lambda$onDiCarMessage$6$AutoiotConverterManager(autoiotSetting, (Boolean) obj, (Throwable) obj2);
                    }
                });
                addWriteTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr(), iotTaskCreate);
                try {
                    if (remoteWrite(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr(), autoiotProfile)) {
                        return;
                    }
                    iotTaskCreate.closeByException(new RemoteException());
                    return;
                } catch (RemoteException e) {
                    Log.e(TAG, "auto iot write failed! deviceId:" + autoiotSetting.getAttr() + " ,attr:" + autoiotSetting.getAttr(), e);
                    iotTaskCreate.closeByException(e);
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if ("state_Device Work State".equals(autoiotSetting.getAttr())) {
                requestForMultipleStatus(autoiotSetting);
                return;
            }
            IotTask<AutoiotProfile> iotTaskCreate2 = IotTask.create(TASK_DELAY);
            iotTaskCreate2.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$UtWZJm6TFPPvNl0kEyovA9tiIEU
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$onDiCarMessage$12$AutoiotConverterManager(autoiotSetting, (AutoiotProfile) obj, (Throwable) obj2);
                }
            });
            try {
                addReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr(), iotTaskCreate2);
                if (remoteRead(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr())) {
                    return;
                }
                iotTaskCreate2.closeByException(new RemoteException("read failed!"));
            } catch (RemoteException e2) {
                Log.e(TAG, "auto iot read failed! deviceId:" + autoiotSetting.getAttr() + " ,attr:" + autoiotSetting.getAttr(), e2);
                iotTaskCreate2.closeByException(e2);
            }
        }
    }

    public /* synthetic */ void lambda$onDiCarMessage$1$AutoiotConverterManager(final Message message, final ICarIotManagerCallback iCarIotManagerCallback) {
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$xvRUMgTsizSkVPwZdBsy5dEB420
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.getIotDeviceInfoCallback(null, message.what == 0 ? WriteStatusEnum.CONTROL_FAILED.code : ReadStatusEnum.READ_FAILED.code);
            }
        });
    }

    public /* synthetic */ void lambda$onDiCarMessage$6$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final Boolean bool, final Throwable th) {
        Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$86GljYZn0dWXqm58nUbnaF44jgs
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onDiCarMessage$5$AutoiotConverterManager(autoiotSetting, th, bool, (ICarIotManagerCallback) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onDiCarMessage$5$AutoiotConverterManager(final AutoiotSetting autoiotSetting, Throwable th, Boolean bool, final ICarIotManagerCallback iCarIotManagerCallback) {
        final AutoiotSetting autoiotSetting2 = new AutoiotSetting();
        autoiotSetting2.setAutoiotDeviceId(autoiotSetting.getAutoiotDeviceId());
        autoiotSetting2.setAttr(autoiotSetting.getAttr());
        if (th != null || !bool.booleanValue()) {
            try {
                Log.w(TAG, "write failed,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr());
                final boolean zAutoiotConnected = this.remoteServer.autoiotConnected(autoiotSetting.getAutoiotDeviceId());
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$B6HEXvd4dSJ3xM0y9VNpWQdpyzw
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.controlIotDeviceCallback(autoiotSetting2, (zAutoiotConnected ? WriteStatusEnum.CONTROL_FAILED : WriteStatusEnum.DEVICE_DISCONNECTED).code);
                    }
                });
                return;
            } catch (RemoteException e) {
                Log.w(TAG, "write failed,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr(), e);
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$K117VDTdX1UXTDFczQ8UM8hbblw
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.controlIotDeviceCallback(autoiotSetting2, WriteStatusEnum.CONTROL_FAILED.code);
                    }
                });
                return;
            }
        }
        Log.d(TAG, "write success,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr());
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$Qepec-5VmG9qjL12ZRsFkRh1zLw
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.controlIotDeviceCallback(autoiotSetting, WriteStatusEnum.CONTROL_SUCCESS.code);
            }
        });
        removeWriteTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr());
    }

    public /* synthetic */ void lambda$onDiCarMessage$12$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final AutoiotProfile autoiotProfile, final Throwable th) {
        Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$McWc1edbGQwRB27AvlA7s98SrbU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onDiCarMessage$11$AutoiotConverterManager(autoiotSetting, autoiotProfile, th, (ICarIotManagerCallback) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onDiCarMessage$11$AutoiotConverterManager(AutoiotSetting autoiotSetting, AutoiotProfile autoiotProfile, Throwable th, final ICarIotManagerCallback iCarIotManagerCallback) {
        final AutoiotSetting autoiotSetting2 = new AutoiotSetting();
        autoiotSetting2.setAutoiotDeviceId(autoiotSetting.getAutoiotDeviceId());
        autoiotSetting2.setAttr(autoiotSetting.getAttr());
        Optional.ofNullable(autoiotProfile).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$G2aNiv-XXMacdmNEBfKg1JaMZeo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                autoiotSetting2.setAutoiotValues(((AutoiotProfile) obj).getAutoiotProfile());
            }
        });
        if (th != null) {
            try {
                Log.w(TAG, "read failed,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr());
                final boolean zAutoiotConnected = this.remoteServer.autoiotConnected(autoiotSetting.getAutoiotDeviceId());
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$_SUXs5Ejl-w2AEbi9RoQDDSMmuY
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, (zAutoiotConnected ? ReadStatusEnum.READ_FAILED : ReadStatusEnum.DEVICE_DISCONNECTED).code);
                    }
                });
                return;
            } catch (RemoteException unused) {
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$t3a0DHZzy0s2nv1AHe2TAWNkIew
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_FAILED.code);
                    }
                });
                return;
            }
        }
        Log.d(TAG, "read success,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr());
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$yoSfXMYYfLIzRxtkKFgcdz5tMIs
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_SUCCESS.code);
            }
        });
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr());
    }

    /* JADX INFO: renamed from: com.byd.car.iot.converter.AutoiotConverterManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$car$iot$converter$DeviceTypeEnum;

        static {
            int[] iArr = new int[DeviceTypeEnum.values().length];
            $SwitchMap$com$byd$car$iot$converter$DeviceTypeEnum = iArr;
            try {
                iArr[DeviceTypeEnum.AUTOIOT_SEAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$car$iot$converter$DeviceTypeEnum[DeviceTypeEnum.AUTOIOT_LOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$car$iot$converter$DeviceTypeEnum[DeviceTypeEnum.AUTOIOT_EYEMASSAGER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void requestForMultipleStatus(AutoiotSetting autoiotSetting) {
        int i = AnonymousClass2.$SwitchMap$com$byd$car$iot$converter$DeviceTypeEnum[autoiotSetting.deviceType().ordinal()];
        if (i == 1) {
            requestForSeatWorkState(autoiotSetting);
        } else if (i == 2) {
            requestForLockWorkState(autoiotSetting);
        } else {
            if (i != 3) {
                return;
            }
            requestForEyeMassagerWorkState(autoiotSetting);
        }
    }

    private void requestForSeatWorkState(final AutoiotSetting autoiotSetting) {
        final byte[] bArr = new byte[3];
        IotTask<AutoiotProfile> iotTaskCreate = IotTask.create(TASK_DELAY);
        CompletableFuture<AutoiotProfile> completableFutureWhenComplete = iotTaskCreate.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$1Po4-35unByVZCQg1a-o702tjic
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForSeatWorkState$13$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        });
        IotTask<AutoiotProfile> iotTaskCreate2 = IotTask.create(TASK_DELAY);
        CompletableFuture<AutoiotProfile> completableFutureWhenComplete2 = iotTaskCreate2.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$-amvTDDHoK6HwU7NLuewdpS2Jeg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForSeatWorkState$14$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        });
        IotTask<AutoiotProfile> iotTaskCreate3 = IotTask.create(TASK_DELAY);
        IotTask<AutoiotProfile> iotTaskCreate4 = IotTask.create(TASK_DELAY, CompletableFuture.allOf(completableFutureWhenComplete, completableFutureWhenComplete2, iotTaskCreate3.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$rrK0-m-PIOAf09ELxa2jz6kHA7A
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForSeatWorkState$15$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        })).whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$6AaGAsjFE1dNC02vu3k8wCrkrS4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForSeatWorkState$21$AutoiotConverterManager(autoiotSetting, bArr, (Void) obj, (Throwable) obj2);
            }
        }));
        try {
            addReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_BUCKLE_LOCK, iotTaskCreate);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_CURRENT_ORIENTATION, iotTaskCreate2);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Fan Mode", iotTaskCreate3);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr(), iotTaskCreate4);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_BUCKLE_LOCK);
            TimeUnit.MILLISECONDS.sleep(SINGLE_TASK_DELAY);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_CURRENT_ORIENTATION);
            TimeUnit.MILLISECONDS.sleep(SINGLE_TASK_DELAY);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), "mode_Fan Mode");
        } catch (RemoteException | InterruptedException e) {
            iotTaskCreate4.closeByException(e);
        }
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$13$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_BUCKLE_LOCK);
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + AutoiotSettingConstants.Attr.STATE_BUCKLE_LOCK + "] failed!");
            throw new RuntimeException(th);
        }
        bArr[0] = autoiotProfile.getAutoiotProfile()[0];
        Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + AutoiotSettingConstants.Attr.STATE_BUCKLE_LOCK + "], value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$14$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_CURRENT_ORIENTATION);
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + AutoiotSettingConstants.Attr.STATE_CURRENT_ORIENTATION + "] failed!");
            throw new RuntimeException(th);
        }
        bArr[1] = autoiotProfile.getAutoiotProfile()[0];
        Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + AutoiotSettingConstants.Attr.STATE_CURRENT_ORIENTATION + "] , value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$15$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Fan Mode");
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Fan Mode] failed!");
            throw new RuntimeException(th);
        }
        bArr[2] = autoiotProfile.getAutoiotProfile()[0];
        Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Fan Mode], value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$21$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final byte[] bArr, Void r4, final Throwable th) {
        findReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr()).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$5QJhyVrK0qdFENcdZs38aZ4OX6g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$requestForSeatWorkState$20$AutoiotConverterManager(autoiotSetting, bArr, th, (IotTask) obj);
            }
        });
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$20$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final byte[] bArr, final Throwable th, IotTask iotTask) {
        Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$--UwDS2qa7Ck3u4pOtvEsRON8dk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$requestForSeatWorkState$19$AutoiotConverterManager(autoiotSetting, bArr, th, (ICarIotManagerCallback) obj);
            }
        });
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr());
    }

    public /* synthetic */ void lambda$requestForSeatWorkState$19$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, Throwable th, final ICarIotManagerCallback iCarIotManagerCallback) {
        final AutoiotSetting autoiotSetting2 = new AutoiotSetting();
        autoiotSetting2.setAutoiotDeviceId(autoiotSetting.getAutoiotDeviceId());
        autoiotSetting2.setAttr(autoiotSetting.getAttr());
        autoiotSetting2.setAutoiotValues(bArr);
        if (th != null) {
            try {
                Log.w(TAG, "[requestForBuckleLock] read all status from  deviceId [" + autoiotSetting2.getAutoiotDeviceId() + "] failed! cause: " + th.getMessage());
                final boolean zAutoiotConnected = this.remoteServer.autoiotConnected(autoiotSetting.getAutoiotDeviceId());
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$-3WXYBuuZGEUN6s6qKqaYbN5giQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, (zAutoiotConnected ? ReadStatusEnum.READ_FAILED : ReadStatusEnum.DEVICE_DISCONNECTED).code);
                    }
                });
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "[requestForBuckleLock] read all status from  deviceId [" + autoiotSetting2.getAutoiotDeviceId() + "] failed! cause: " + th.getMessage(), e);
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$hX3K3u8NylWmsT0hX92nf54mp0I
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_FAILED.code);
                    }
                });
                return;
            }
        }
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$I8uez7zX1HPgEyszd6DL4RU4bo8
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_SUCCESS.code);
            }
        });
        Log.d(TAG, "[requestForBuckleLock] read all status from  deviceId [" + autoiotSetting2.getAutoiotDeviceId() + "] SUCCESS!,value :" + ((int) bArr[0]) + "," + ((int) bArr[1]) + "," + ((int) bArr[2]));
    }

    private void requestForLockWorkState(final AutoiotSetting autoiotSetting) {
        IotTask<AutoiotProfile> iotTaskCreate = IotTask.create(TASK_DELAY);
        iotTaskCreate.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$UIEvqxSnf5gshl0vN77mCpSnOVo
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForLockWorkState$27$AutoiotConverterManager(autoiotSetting, (AutoiotProfile) obj, (Throwable) obj2);
            }
        });
        try {
            addReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_LOCK_UP_DOWN_STATE, iotTaskCreate);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_LOCK_UP_DOWN_STATE);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public /* synthetic */ void lambda$requestForLockWorkState$27$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final AutoiotProfile autoiotProfile, final Throwable th) {
        Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$qEBef6x11OpPEJ9MjkLHuzv8b_k
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$requestForLockWorkState$26$AutoiotConverterManager(autoiotSetting, autoiotProfile, th, (ICarIotManagerCallback) obj);
            }
        });
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), AutoiotSettingConstants.Attr.STATE_LOCK_UP_DOWN_STATE);
    }

    public /* synthetic */ void lambda$requestForLockWorkState$26$AutoiotConverterManager(final AutoiotSetting autoiotSetting, AutoiotProfile autoiotProfile, Throwable th, final ICarIotManagerCallback iCarIotManagerCallback) {
        final AutoiotSetting autoiotSetting2 = new AutoiotSetting();
        autoiotSetting2.setAttr(autoiotSetting.getAttr());
        autoiotSetting2.setAutoiotDeviceId(autoiotSetting.getAutoiotDeviceId());
        Optional.ofNullable(autoiotProfile).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$P7fTIG409IOHc0QtBGTou_fTZio
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                autoiotSetting2.setAutoiotValues(((AutoiotProfile) obj).getAutoiotProfile());
            }
        });
        if (th != null) {
            try {
                Log.w(TAG, "[requestForAutoIotLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + autoiotSetting.getAttr() + "] failed! cause: " + th.getMessage());
                final boolean zAutoiotConnected = this.remoteServer.autoiotConnected(autoiotSetting.getAutoiotDeviceId());
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$fJoBt1YgZBFCW08kV2lJCufR4hM
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting, (zAutoiotConnected ? ReadStatusEnum.READ_FAILED : ReadStatusEnum.DEVICE_DISCONNECTED).code);
                    }
                });
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "[requestForAutoIotLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + autoiotSetting.getAttr() + "] failed! cause: " + th.getMessage(), e);
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$d88mXzPTl7t06we6Al6tBdsdR38
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting, ReadStatusEnum.READ_FAILED.code);
                    }
                });
                return;
            }
        }
        Log.d(TAG, "[requestForBuckleLock]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [" + autoiotSetting.getAttr() + "], success!");
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$X8AzG86eQ8Lrlq5jsWXDZerIp7U
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting, ReadStatusEnum.READ_SUCCESS.code);
            }
        });
    }

    private void requestForEyeMassagerWorkState(final AutoiotSetting autoiotSetting) {
        final byte[] bArr = new byte[3];
        IotTask<AutoiotProfile> iotTaskCreate = IotTask.create(TASK_DELAY);
        CompletableFuture<AutoiotProfile> completableFutureWhenComplete = iotTaskCreate.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$fMk1Sz2TK8HcJdUx8cfOnyaXSh4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForEyeMassagerWorkState$28$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        });
        IotTask<AutoiotProfile> iotTaskCreate2 = IotTask.create(TASK_DELAY);
        CompletableFuture<AutoiotProfile> completableFutureWhenComplete2 = iotTaskCreate2.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$ZBEJIf2X_9p2KavpMjhDDOv7HXc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForEyeMassagerWorkState$29$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        });
        IotTask<AutoiotProfile> iotTaskCreate3 = IotTask.create(TASK_DELAY);
        IotTask<AutoiotProfile> iotTaskCreate4 = IotTask.create(TASK_DELAY, CompletableFuture.allOf(completableFutureWhenComplete, completableFutureWhenComplete2, iotTaskCreate3.get().whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$-Rm2HkxhZKgyDkNGJkCXi9Pi1Ag
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForEyeMassagerWorkState$30$AutoiotConverterManager(autoiotSetting, bArr, (AutoiotProfile) obj, (Throwable) obj2);
            }
        })).whenComplete(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$NPP8_J8vnV__R2KxQzLtyUrZBEI
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$requestForEyeMassagerWorkState$36$AutoiotConverterManager(autoiotSetting, bArr, (Void) obj, (Throwable) obj2);
            }
        }));
        try {
            addReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Change Mode", iotTaskCreate);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Temp Mode", iotTaskCreate2);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Vibrate Mode", iotTaskCreate3);
            addReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr(), iotTaskCreate4);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), "mode_Change Mode");
            TimeUnit.MILLISECONDS.sleep(SINGLE_TASK_DELAY);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), "mode_Temp Mode");
            TimeUnit.MILLISECONDS.sleep(SINGLE_TASK_DELAY);
            remoteRead(autoiotSetting.getAutoiotDeviceId(), "mode_Vibrate Mode");
        } catch (RemoteException | InterruptedException e) {
            iotTaskCreate4.closeByException(e);
        }
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$28$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Change Mode");
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Change Mode] failed!");
            throw new RuntimeException(th);
        }
        Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Change Mode] , value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
        bArr[0] = autoiotProfile.getAutoiotProfile()[0];
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$29$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Temp Mode");
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Temp Mode] failed!");
            throw new RuntimeException(th);
        }
        Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Temp Mode] , value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
        bArr[1] = autoiotProfile.getAutoiotProfile()[0];
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$30$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, AutoiotProfile autoiotProfile, Throwable th) {
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), "mode_Vibrate Mode");
        if (th != null || !Optional.ofNullable(autoiotProfile).isPresent()) {
            Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Vibrate Mode] failed!");
            throw new RuntimeException(th);
        }
        Log.d(TAG, "[requestForEyeMassager]read from device [" + autoiotSetting.getAutoiotDeviceId() + "] , attr [mode_Vibrate Mode] , value:" + ((int) autoiotProfile.getAutoiotProfile()[0]));
        bArr[2] = autoiotProfile.getAutoiotProfile()[0];
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$36$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final byte[] bArr, Void r4, final Throwable th) {
        findReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr()).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$HmmX-h_CPtEXPQ3nYc3bOCVaezM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$requestForEyeMassagerWorkState$35$AutoiotConverterManager(autoiotSetting, bArr, th, (IotTask) obj);
            }
        });
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$35$AutoiotConverterManager(final AutoiotSetting autoiotSetting, final byte[] bArr, final Throwable th, IotTask iotTask) {
        Optional.ofNullable(this.iCarIotManagerCallback).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$sRT15QPEfVxPOkbQ63X2plpjmMY
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$requestForEyeMassagerWorkState$34$AutoiotConverterManager(autoiotSetting, bArr, th, (ICarIotManagerCallback) obj);
            }
        });
        removeReadTask(autoiotSetting.getAutoiotDeviceId(), autoiotSetting.getAttr());
    }

    public /* synthetic */ void lambda$requestForEyeMassagerWorkState$34$AutoiotConverterManager(AutoiotSetting autoiotSetting, byte[] bArr, Throwable th, final ICarIotManagerCallback iCarIotManagerCallback) {
        final AutoiotSetting autoiotSetting2 = new AutoiotSetting();
        autoiotSetting2.setAutoiotDeviceId(autoiotSetting.getAutoiotDeviceId());
        autoiotSetting2.setAttr(autoiotSetting.getAttr());
        autoiotSetting2.setAutoiotValues(bArr);
        if (th != null) {
            try {
                Log.w(TAG, "[requestForBuckleLock] read all status from  deviceId [" + autoiotSetting2.getAutoiotDeviceId() + "] failed! cause: " + th.getMessage());
                final boolean zAutoiotConnected = this.remoteServer.autoiotConnected(autoiotSetting.getAutoiotDeviceId());
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$uNanXXA6C0QZ6TaA6Jk-RDiSG38
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, (zAutoiotConnected ? ReadStatusEnum.READ_FAILED : ReadStatusEnum.DEVICE_DISCONNECTED).code);
                    }
                });
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "[requestForBuckleLock]write failed,deviceId " + autoiotSetting.getAutoiotDeviceId() + ", attr " + autoiotSetting.getAttr(), e);
                this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$jPHQz6qC_5TIRq49y74EEiMfpU8
                    @Override // java.lang.Runnable
                    public final void run() {
                        iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_FAILED.code);
                    }
                });
                return;
            }
        }
        Log.d(TAG, "[requestForBuckleLock] read all status from  deviceId [" + autoiotSetting2.getAutoiotDeviceId() + "] SUCCESS!,value :" + ((int) bArr[0]) + "," + ((int) bArr[1]) + "," + ((int) bArr[2]));
        this.mListener.post(new Runnable() { // from class: com.byd.car.iot.converter.-$$Lambda$AutoiotConverterManager$5Sa2eymme__sbPzOLfQ9sgH95eY
            @Override // java.lang.Runnable
            public final void run() {
                iCarIotManagerCallback.getIotDeviceInfoCallback(autoiotSetting2, ReadStatusEnum.READ_SUCCESS.code);
            }
        });
    }
}
