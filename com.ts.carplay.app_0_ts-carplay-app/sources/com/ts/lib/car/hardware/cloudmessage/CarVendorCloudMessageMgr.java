package com.ts.lib.car.hardware.cloudmessage;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorCloudMessageMgr extends CarVendorManagerBase implements CarVendorCloudMessageManager {
    private static final String CLOUD_THREAD = "VEHICLE-CLOUDMGR";
    private static final String S_43C;
    private static final byte[] S_BYTE_ARRAY_43C;
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private static CloudHandler mCloudHandler;
    private CarPropertyManager mCarPropertyManager;
    private HandlerThread mCloudHandlerThread;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static final byte[] S_BYTE_ARRAY_43D = {4, 61};
    private static final byte[] S_BYTE_ARRAY_3D9 = {3, -39};
    private static final byte[] S_BYTE_ARRAY_4A5 = {4, -91};
    private static final String S_43D = Arrays.toString(S_BYTE_ARRAY_43D);
    private static final String S_3D9 = Arrays.toString(S_BYTE_ARRAY_3D9);
    private static final String S_4A5 = Arrays.toString(S_BYTE_ARRAY_4A5);
    private final LinkedList<OnCarCloudMessageChangeEventListenerProxy> mListeners = new LinkedList<>();
    private final Set<String> mCanIdSet = new HashSet();
    private Integer[] mExtAssistIds = {Integer.valueOf(CarVendorCloudMessageManager.ID_BUS_CLOUD_MESSAGE)};

    static {
        byte[] bArr = {4, 60};
        S_BYTE_ARRAY_43C = bArr;
        S_43C = Arrays.toString(bArr);
    }

    public CarVendorCloudMessageMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), "persist.car.vendor.log.assist", 0) == 1;
        Log.info(CarVendorCloudMessageManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorCloudMessageManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorCloudMessageManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
            HandlerThread handlerThread = new HandlerThread(CLOUD_THREAD);
            this.mCloudHandlerThread = handlerThread;
            if (!handlerThread.isAlive()) {
                this.mCloudHandlerThread.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class CloudHandler extends Handler {
        private static final int S_DETECT_CALLBACK_MS = 15000;
        private static final int S_DIFF_MILL_SECOND = 950;
        private static final int S_MESSAGE_CALLBACK = 100;
        private static final int S_MESSAGE_DETECT_CALLBACK = 101;
        private CarPropertyValue mCarPropertyValue;
        private CloudMessageEventChangeValue mEventChangeValue;
        private boolean mFirstCallback;
        private CarPropertyValue mLastCarPropertyValue;
        private long mLastTime;
        private CarVendorCloudMessageManager.OnCarCloudMessageEventListener mListener;

        public void setCarPropertyValue(CarPropertyValue carPropertyValue) {
            this.mCarPropertyValue = carPropertyValue;
        }

        public void setCarEventValue(CloudMessageEventChangeValue carPropertyValue) {
            this.mEventChangeValue = carPropertyValue;
        }

        public CloudHandler(Looper looper, CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) {
            super(looper);
            this.mFirstCallback = false;
            this.mListener = listener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 100) {
                if (i != 101) {
                    return;
                }
                if (SystemClock.elapsedRealtime() - this.mLastTime > 15000) {
                    CarVendorCloudMessageMgr.mCloudHandler.removeMessages(100);
                    this.mFirstCallback = false;
                    Log.debug(CarVendorCloudMessageManager.TAG, "remove callback message.");
                    return;
                }
                CarVendorCloudMessageMgr.mCloudHandler.sendEmptyMessageDelayed(101, 15000L);
                return;
            }
            if (this.mCarPropertyValue != this.mLastCarPropertyValue) {
                Log.debug(CarVendorCloudMessageManager.TAG, "execute callback cloud data." + this.mCarPropertyValue);
                try {
                    this.mLastCarPropertyValue = this.mCarPropertyValue;
                    this.mListener.onCloudMessageEventChange(this.mEventChangeValue);
                } catch (Exception exception) {
                    android.util.Log.e(CarVendorCloudMessageManager.TAG, "convert car vendor property error!!!! " + exception);
                }
            }
            CarVendorCloudMessageMgr.mCloudHandler.sendEmptyMessageDelayed(100, 950L);
            this.mFirstCallback = true;
        }
    }

    private void initCloudHandler(CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) {
        Log.debug(CarVendorCloudMessageManager.TAG, "init cloud handler.");
        if (mCloudHandler == null) {
            mCloudHandler = new CloudHandler(this.mCloudHandlerThread.getLooper(), listener);
        }
    }

    @Override // com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager
    public byte[] getCloudMessage() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<byte[]> value = this.mCarPropertyManager.getProperty(byte[].class, CarVendorCloudMessageManager.ID_BUS_CLOUD_MESSAGE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCloudMessage property: 0x");
            sb.append(tenToHex(CarVendorCloudMessageManager.ID_BUS_CLOUD_MESSAGE));
            sb.append(" class: byte[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCloudMessageManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager
    public void setCloudMessage(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorCloudMessageManager.TAG, "setCloudMessage property: 0x" + tenToHex(CarVendorCloudMessageManager.ID_BUS_CLOUD_MESSAGE) + " class: byte[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorCloudMessageManager.ID_BUS_CLOUD_MESSAGE, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager
    public void setCanId(Set<String> canIdSet) {
        this.mCanIdSet.addAll(canIdSet);
    }

    @Override // com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager
    public void addCarCloudMessageEventListener(CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) throws CarNotConnectedException {
        OnCarCloudMessageChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarCloudMessageChangeEventListenerProxy(listener);
                initCloudHandler(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorCloudMessageManager.TAG, "addCarAssistEventListener propId size: " + this.mExtAssistIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtAssistIds) {
                int propId = num.intValue();
                Log.debug(CarVendorCloudMessageManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager
    public void removeCarCloudMessageEventListener(CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarCloudMessageChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorCloudMessageManager.TAG, "removeCarAssistEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CloudMessageEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        CloudMessageEventChangeValue cloudMessageEventChangeValue = CloudMessageEventChangeValue.obtain();
        cloudMessageEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorCloudMessageManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId == 561049648) {
            cloudMessageEventChangeValue.setCloudMessage(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
        } else {
            Log.debug(CarVendorCloudMessageManager.TAG, "break other properties ,except assist");
        }
        Log.debug(CarVendorCloudMessageManager.TAG, "convertCarVendorPropertyValue " + cloudMessageEventChangeValue.toString(), DEBUG_LOGGER);
        return cloudMessageEventChangeValue;
    }

    private OnCarCloudMessageChangeEventListenerProxy findListenerLocked(CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) {
        for (OnCarCloudMessageChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarCloudMessageChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener;

        public OnCarCloudMessageChangeEventListenerProxy(CarVendorCloudMessageManager.OnCarCloudMessageEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            Log.debug(CarVendorCloudMessageManager.TAG, "onChangeEvent  propertyId: " + value.getPropertyId() + " value: " + value.toString());
            if (value.getStatus() != 2) {
                CloudMessageEventChangeValue newValue = CarVendorCloudMessageMgr.convertCarVendorProperty(value);
                byte[] cloudMessage = newValue.getCloudMessage();
                Log.debug(CarVendorCloudMessageManager.TAG, "cloudMessage: " + Arrays.toString(cloudMessage));
                if (cloudMessage != null && cloudMessage.length > 2) {
                    Log.debug(CarVendorCloudMessageManager.TAG, "value = " + Arrays.toString(cloudMessage));
                    byte[] canId = {cloudMessage[2], cloudMessage[1]};
                    String key = Arrays.toString(canId);
                    Log.debug(CarVendorCloudMessageManager.TAG, "value can id = " + key + " mCanId map size = " + CarVendorCloudMessageMgr.this.mCanIdSet.size());
                    if (CarVendorCloudMessageMgr.this.mCanIdSet.size() <= 0 || !CarVendorCloudMessageMgr.this.mCanIdSet.contains(key)) {
                        if (!CarVendorCloudMessageMgr.S_3D9.equals(key) && !CarVendorCloudMessageMgr.S_43C.equals(key) && !CarVendorCloudMessageMgr.S_43D.equals(key) && !CarVendorCloudMessageMgr.S_4A5.equals(key)) {
                            CarVendorCloudMessageMgr.mCloudHandler.setCarPropertyValue(value);
                            CarVendorCloudMessageMgr.mCloudHandler.setCarEventValue(newValue);
                            if (!CarVendorCloudMessageMgr.mCloudHandler.mFirstCallback) {
                                CarVendorCloudMessageMgr.mCloudHandler.mFirstCallback = true;
                                Log.debug(CarVendorCloudMessageManager.TAG, "first callback.");
                                CarVendorCloudMessageMgr.mCloudHandler.sendEmptyMessage(100);
                                CarVendorCloudMessageMgr.mCloudHandler.sendEmptyMessageDelayed(101, 15000L);
                            }
                            CarVendorCloudMessageMgr.mCloudHandler.mLastTime = SystemClock.elapsedRealtime();
                            return;
                        }
                        Log.debug(CarVendorCloudMessageManager.TAG, "not delay callback " + key);
                        this.listener.onCloudMessageEventChange(newValue);
                        return;
                    }
                    this.listener.onCloudMessageEventChange(newValue);
                    CarVendorCloudMessageMgr.this.mCanIdSet.remove(key);
                }
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorCloudMessageManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onCloudMessageErrorEvent(propertyId, zone);
        }
    }
}
