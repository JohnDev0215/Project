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
import java.util.Arrays;
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

    SensorManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
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
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnSensorChangedListener(new OnCarSensorChangedListener() { // from class: android.hardware.bydauto.sensor.tsimpl.SensorManagerImpl.1
            public void onCarSensorStateChanged(int id, int areaId, SensorPacket packet) {
                if (!SensorManagerImpl.this.mAbsBydAutoSensorListeners.isEmpty()) {
                    try {
                        SensorManagerImpl.this.sensorCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(SensorManagerImpl.TAG, "sensorCallback is error");
                    }
                }
            }

            public void onCarSensorBinderChanged(boolean binderState) {
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sensorCallback(int id, int areaId, SensorPacket sensorPacket) {
        if (sensorPacket == null) {
            Log.e(TAG, "sensorCallback is null");
        }
        Log.i(TAG, "sensorCallback propertyId:" + id);
        switch (id) {
            case 557861632:
                for (AbsBYDAutoSensorListener listener : this.mAbsBydAutoSensorListeners) {
                    listener.onLightIntensityChanged(sensorPacket.getLightIntensity());
                }
                break;
            case 557861635:
                int slope = sensorPacket.getSlope();
                if (slope > 60 || slope < -60) {
                    Log.v(TAG, "The value is not illegal" + slope);
                } else {
                    for (AbsBYDAutoSensorListener listener2 : this.mAbsBydAutoSensorListeners) {
                        listener2.onSlopeValueChanged(slope);
                    }
                }
                break;
            case 559958785:
                float temperature = sensorPacket.getTemperatureSensorValue();
                if (temperature < -40.0f || temperature > 125.0f) {
                    Log.v(TAG, "The value is not illegal" + temperature);
                } else {
                    for (AbsBYDAutoSensorListener listener3 : this.mAbsBydAutoSensorListeners) {
                        listener3.onTemperatureSensorValueChanged(temperature);
                    }
                }
                break;
            case 559958786:
                float humidity = sensorPacket.getHumiditySensorValue();
                if (humidity > 100.0f || humidity < 0.0f) {
                    Log.v(TAG, "The value is not illegal" + humidity);
                } else {
                    for (AbsBYDAutoSensorListener listener4 : this.mAbsBydAutoSensorListeners) {
                        listener4.onHumiditySensorValueChanged(humidity);
                    }
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
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public float getTemperatureSensorValue() {
        Log.v(TAG, "getTemperatureSensorValue is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTemperatureSensorValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float temperature = this.mTsManagerImpl.getCarSensorAdapterManager().getTemperatureSensorValue();
        if (temperature >= -40.0f && temperature <= 125.0f) {
            return temperature;
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1" + temperature);
        return -1.0f;
    }

    public float getHumiditySensorValue() {
        Log.v(TAG, "getHumiditySensorValue is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getHumiditySensorValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        float humidity = this.mTsManagerImpl.getCarSensorAdapterManager().getHumiditySensorValue();
        if (humidity <= 100.0f && humidity >= 0.0f) {
            return humidity;
        }
        Log.v(TAG, "The value is not illegal,errorCode:-1" + humidity);
        return -1.0f;
    }

    public int getLightIntensity() {
        Log.v(TAG, "getLightIntensity is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
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

    public int setOrientationAngle(int value) {
        Log.v(TAG, "setOrientationAngle is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setOrientationAngle mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestSensorManagerImplCallback.SET_ORIENTATION_ANGLE, value);
            return 0;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (value > 359 || value < 0) {
            Log.v(TAG, "The value is not illegal,errorCode:-1");
            return -1;
        }
        return this.mTsManagerImpl.getCarSensorAdapterManager().setOrientationAngle(value);
    }

    public int getSlope() {
        Log.v(TAG, "getSlope is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
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
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
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

    public boolean postEvent(int deviceType, int eventType, byte[] value, Object data) {
        Log.v(TAG, "postEvent is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        Log.v(TAG, "postEvent byte value is empty implementation. ");
        if (1 != this.mForTest) {
            return false;
        }
        Log.v(TAG, "postEvent byte value mForTestValue is" + this.mForTest + " execute return");
        return false;
    }

    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        Log.v(TAG, "postEvent int value is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 != this.mForTest) {
            return false;
        }
        Log.v(TAG, "postEvent int value mForTestValue is" + this.mForTest + " execute return");
        return false;
    }

    public boolean postEvent(int deviceType, int eventType, float value, Object data) {
        Log.v(TAG, "postEvent float value is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 != this.mForTest) {
            return false;
        }
        Log.v(TAG, "postEvent float value mForTestValue is" + this.mForTest + " execute return");
        return false;
    }

    public int getType() {
        Log.v(TAG, "getType is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        return new int[0];
    }

    public void registerListener(AbsBYDAutoSensorListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSensorListener listener, int[] featureIDs) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe sensor listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener one para mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        if (!this.mAbsBydAutoSensorListeners.contains(listener)) {
            this.mAbsBydAutoSensorListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerSensorCallback();
    }

    public void unregisterListener(AbsBYDAutoSensorListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        this.mAbsBydAutoSensorListener = null;
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
        } else {
            this.mAbsBydAutoSensorListeners.remove(listener);
            unregisterConnectListener(listener);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        return 0;
    }
}
