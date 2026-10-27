package android.hardware.bydauto.sensor.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.hardware.bydauto_test.TestSensorManagerImplCallback;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.sensor.CarSensorAdapterManager;
import com.ts.lib.caradapter.sensor.OnCarSensorChangedListener;
import com.ts.lib.caradapter.sensor.SensorPacket;
import java.util.Arrays;
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    SensorManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission("android.permission.BYDAUTO_SENSOR_COMMON");
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "SensorManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new TestSensorManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.sensor.tsimpl.SensorManagerImpl.1
            public void onCarSensorBinderChanged(boolean z) {
            }

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
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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
                } else {
                    Log.v(TAG, "The value is not illegal" + slope);
                }
                break;
            case SensorHalId.ID_TEMPERATURE_SENSOR_VALUE /* 559958785 */:
                float temperatureSensorValue = sensorPacket.getTemperatureSensorValue();
                if (temperatureSensorValue >= -40.0f && temperatureSensorValue <= 125.0f) {
                    Iterator<AbsBYDAutoSensorListener> it3 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTemperatureSensorValueChanged(temperatureSensorValue);
                    }
                } else {
                    Log.v(TAG, "The value is not illegal" + temperatureSensorValue);
                }
                break;
            case SensorHalId.ID_HUMIDITY_SENSOR_VALUE /* 559958786 */:
                float humiditySensorValue = sensorPacket.getHumiditySensorValue();
                if (humiditySensorValue <= 100.0f && humiditySensorValue >= 0.0f) {
                    Iterator<AbsBYDAutoSensorListener> it4 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onHumiditySensorValueChanged(humiditySensorValue);
                    }
                } else {
                    Log.v(TAG, "The value is not illegal" + humiditySensorValue);
                }
                break;
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

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public float getTemperatureSensorValue() {
        Log.v(TAG, "getTemperatureSensorValue is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTemperatureSensorValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float temperatureSensorValue = this.mTsManagerImpl.getCarSensorAdapterManager().getTemperatureSensorValue();
        if (temperatureSensorValue >= -40.0f && temperatureSensorValue <= 125.0f) {
            return temperatureSensorValue;
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1" + temperatureSensorValue);
        return -1.0f;
    }

    public float getHumiditySensorValue() {
        Log.v(TAG, "getHumiditySensorValue is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getHumiditySensorValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float humiditySensorValue = this.mTsManagerImpl.getCarSensorAdapterManager().getHumiditySensorValue();
        if (humiditySensorValue <= 100.0f && humiditySensorValue >= 0.0f) {
            return humiditySensorValue;
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1" + humiditySensorValue);
        return -1.0f;
    }

    public int getLightIntensity() {
        Log.v(TAG, "getLightIntensity is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getLightIntensity mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getLightIntensity();
    }

    public int setOrientationAngle(int i) {
        Log.v(TAG, "setOrientationAngle is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_SET");
        if (1 == this.mForTest) {
            Log.v(TAG, "setOrientationAngle mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestSensorManagerImplCallback.SET_ORIENTATION_ANGLE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i <= 359 && i >= 0) {
            return this.mTsManagerImpl.getCarSensorAdapterManager().setOrientationAngle(i);
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1");
        return -1;
    }

    public int getSlope() {
        Log.v(TAG, "getSlope is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSlope mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int slope = this.mTsManagerImpl.getCarSensorAdapterManager().getSlope();
        if (slope <= 60 && slope >= -60) {
            return slope;
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1" + slope);
        return -1;
    }

    public byte[] getSensorAxValue() {
        Log.v(TAG, "getSensorAxValue is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSensorAxValue mForTestValue is" + this.mForTest + " execute return");
            return new byte[0];
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().getSensorAxValue();
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        Log.v(TAG, "postEvent is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_SET");
        Log.v(TAG, "postEvent byte value is empty implementation. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "postEvent byte value mForTestValue is" + this.mForTest + " execute return");
        }
        return false;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.v(TAG, "postEvent int value is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_SET");
        if (1 == this.mForTest) {
            Log.v(TAG, "postEvent int value mForTestValue is" + this.mForTest + " execute return");
        }
        return false;
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        Log.v(TAG, "postEvent float value is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_SET");
        if (1 == this.mForTest) {
            Log.v(TAG, "postEvent float value mForTestValue is" + this.mForTest + " execute return");
        }
        return false;
    }

    public int getType() {
        Log.v(TAG, "getType is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is empty implementation. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_GET");
        if (1 != this.mForTest) {
            return new int[0];
        }
        Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        registerListener(absBYDAutoSensorListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener, int[] iArr) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe sensor listener " + absBYDAutoSensorListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoSensorListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener one para mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission("android.permission.BYDAUTO_SENSOR_COMMON");
        if (!this.mAbsBydAutoSensorListeners.contains(absBYDAutoSensorListener)) {
            this.mAbsBydAutoSensorListeners.add(absBYDAutoSensorListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoSensorListener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission("android.permission.BYDAUTO_SENSOR_COMMON");
        this.mAbsBydAutoSensorListener = null;
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
        } else {
            this.mAbsBydAutoSensorListeners.remove(absBYDAutoSensorListener);
            unregisterConnectListener(absBYDAutoSensorListener);
        }
    }
}
