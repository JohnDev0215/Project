package android.hardware.bydauto.sensor.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.hardware.bydauto.sensor.BYDAutoSensorDevice;
import android.hardware.bydauto_test.TestSensorManagerImplCallback;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.sensor.CarSensorAdapterManager;
import com.ts.lib.caradapter.sensor.OnCarSensorChangedListener;
import com.ts.lib.caradapter.sensor.SensorPacket;
import defpackage.C0175b;
import defpackage.gq2;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SensorManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_SensorManagerImpl";
    protected static final String TAG = "SensorManagerImpl";
    private static SensorManagerImpl sInstance;
    private AbsBYDAutoSensorListener mAbsBydAutoSensorListener;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private OnCarSensorChangedListener mOnSensorChangedListener;
    private TestSensorManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private CarSensorAdapterManager mTsSensorManager;
    private final CopyOnWriteArrayList<AbsBYDAutoSensorListener> mAbsBydAutoSensorListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public SensorManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestSensorManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.sensor.tsimpl.SensorManagerImpl.1
            public void onCarSensorStateChanged(int i2, int i3, SensorPacket sensorPacket) {
                if (SensorManagerImpl.this.mAbsBydAutoSensorListeners.isEmpty()) {
                    return;
                }
                try {
                    SensorManagerImpl.this.sensorCallback(i2, i3, sensorPacket);
                } catch (Exception unused) {
                    Log.e(SensorManagerImpl.TAG, "sensorCallback is error");
                }
            }

            public void onCarSensorBinderChanged(boolean z) {
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(C0175b.j("requires permission ", str));
        }
    }

    public static SensorManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (SensorManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SensorManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sensorCallback(int i, int i2, SensorPacket sensorPacket) {
        if (sensorPacket == null) {
            Log.e(TAG, "sensorCallback is null");
        }
        Log.i(TAG, "sensorCallback propertyId:" + i);
        switch (i) {
            case SensorHalId.ID_LIGHT_INTENSITY /* 557861632 */:
                Iterator<AbsBYDAutoSensorListener> it = this.mAbsBydAutoSensorListeners.iterator();
                while (it.hasNext()) {
                    it.next().onLightIntensityChanged(sensorPacket.getLightIntensity());
                }
                break;
            case SensorHalId.ID_SLOPE /* 557861635 */:
                int slope = sensorPacket.getSlope();
                if (slope <= 60 && slope >= -60) {
                    Iterator<AbsBYDAutoSensorListener> it2 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onSlopeValueChanged(slope);
                    }
                    break;
                }
                break;
            case SensorHalId.ID_TEMPERATURE_SENSOR_VALUE /* 559958785 */:
                float temperatureSensorValue = sensorPacket.getTemperatureSensorValue();
                if (temperatureSensorValue >= -40.0f && temperatureSensorValue <= 125.0f) {
                    Iterator<AbsBYDAutoSensorListener> it3 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTemperatureSensorValueChanged(temperatureSensorValue);
                    }
                    break;
                }
                break;
            case SensorHalId.ID_HUMIDITY_SENSOR_VALUE /* 559958786 */:
                float humiditySensorValue = sensorPacket.getHumiditySensorValue();
                if (humiditySensorValue <= 100.0f && humiditySensorValue >= 0.0f) {
                    Iterator<AbsBYDAutoSensorListener> it4 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onHumiditySensorValueChanged(humiditySensorValue);
                    }
                    break;
                }
                break;
        }
    }

    public int getDevicetype() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        return 0;
    }

    public int[] getFeatureList() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        return 1 == this.mForTest ? new int[0] : new int[0];
    }

    public String getGetPermission() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        return "";
    }

    public float getHumiditySensorValue() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float humiditySensorValue = this.mTsManagerImpl.getCarSensorAdapterManager().getHumiditySensorValue();
        if (humiditySensorValue > 100.0f || humiditySensorValue < 0.0f) {
            return -1.0f;
        }
        return humiditySensorValue;
    }

    public int getLightIntensity() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
            return this.mTsManagerImpl.getCarSensorAdapterManager().getLightIntensity();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public byte[] getSensorAxValue() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            return new byte[0];
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
            return this.mTsManagerImpl.getCarSensorAdapterManager().getSensorAxValue();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new byte[0];
    }

    public String getSetPermission() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        return "";
    }

    public int getSlope() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int slope = this.mTsManagerImpl.getCarSensorAdapterManager().getSlope();
        if (slope > 60 || slope < -60) {
            return -1;
        }
        return slope;
    }

    public float getTemperatureSensorValue() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float temperatureSensorValue = this.mTsManagerImpl.getCarSensorAdapterManager().getTemperatureSensorValue();
        if (temperatureSensorValue < -40.0f || temperatureSensorValue > 125.0f) {
            return -1.0f;
        }
        return temperatureSensorValue;
    }

    public int getType() {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        return 0;
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        return false;
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        registerListener(absBYDAutoSensorListener, new int[0]);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    public int setOrientationAngle(int i) {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestSensorManagerImplCallback.SET_ORIENTATION_ANGLE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i > 359 || i < 0) {
            return -1;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().setOrientationAngle(i);
    }

    public void unregisterListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        this.mAbsBydAutoSensorListener = null;
        if (1 == this.mForTest || this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            return;
        }
        this.mAbsBydAutoSensorListeners.remove(absBYDAutoSensorListener);
        unregisterConnectListener(absBYDAutoSensorListener);
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        return false;
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe sensor listener ");
        sb.append(absBYDAutoSensorListener);
        sb.append(" featureIDs = ");
        gq2.k(iArr, sb, TAG);
        if (absBYDAutoSensorListener == null || 1 == this.mForTest) {
            return;
        }
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        if (!this.mAbsBydAutoSensorListeners.contains(absBYDAutoSensorListener)) {
            this.mAbsBydAutoSensorListeners.add(absBYDAutoSensorListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSensorListener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        return false;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }
}
