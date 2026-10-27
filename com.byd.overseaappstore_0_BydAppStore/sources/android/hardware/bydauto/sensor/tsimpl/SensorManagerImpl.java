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
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class SensorManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_SensorManagerImpl";
    public static final String TAG = "SensorManagerImpl";
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
                StringBuilder sbK = e.k("SensorManagerImpl mForTestValue is");
                sbK.append(this.mForTest);
                sbK.append(" execute return");
                Log.v(TAG, sbK.toString());
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
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
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
        String strC;
        float temperatureSensorValue;
        StringBuilder sb;
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
                    strC = e.c("The value is not illegal", slope);
                    Log.v(TAG, strC);
                }
                break;
            case SensorHalId.ID_TEMPERATURE_SENSOR_VALUE /* 559958785 */:
                temperatureSensorValue = sensorPacket.getTemperatureSensorValue();
                if (temperatureSensorValue >= -40.0f && temperatureSensorValue <= 125.0f) {
                    Iterator<AbsBYDAutoSensorListener> it3 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTemperatureSensorValueChanged(temperatureSensorValue);
                    }
                } else {
                    sb = new StringBuilder();
                    sb.append("The value is not illegal");
                    sb.append(temperatureSensorValue);
                    strC = sb.toString();
                    Log.v(TAG, strC);
                }
                break;
            case SensorHalId.ID_HUMIDITY_SENSOR_VALUE /* 559958786 */:
                temperatureSensorValue = sensorPacket.getHumiditySensorValue();
                if (temperatureSensorValue <= 100.0f && temperatureSensorValue >= 0.0f) {
                    Iterator<AbsBYDAutoSensorListener> it4 = this.mAbsBydAutoSensorListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onHumiditySensorValueChanged(temperatureSensorValue);
                    }
                } else {
                    sb = new StringBuilder();
                    sb.append("The value is not illegal");
                    sb.append(temperatureSensorValue);
                    strC = sb.toString();
                    Log.v(TAG, strC);
                }
                break;
        }
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getDevicetype mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            return new int[0];
        }
        StringBuilder sbK = e.k("getFeatureList mForTestValue is");
        sbK.append(this.mForTest);
        sbK.append(" execute return");
        Log.v(TAG, sbK.toString());
        return new int[0];
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getGetPermission mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return "";
    }

    public float getHumiditySensorValue() {
        Log.v(TAG, "getHumiditySensorValue is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getHumiditySensorValue mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
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
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getLightIntensity();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        StringBuilder sbK = e.k("getLightIntensity mForTestValue is");
        sbK.append(this.mForTest);
        sbK.append(" execute return");
        Log.v(TAG, sbK.toString());
        return 0;
    }

    public byte[] getSensorAxValue() {
        Log.v(TAG, "getSensorAxValue is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarSensorAdapterManager() != null) {
                return this.mTsManagerImpl.getCarSensorAdapterManager().getSensorAxValue();
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        StringBuilder sbK = e.k("getSensorAxValue mForTestValue is");
        sbK.append(this.mForTest);
        sbK.append(" execute return");
        Log.v(TAG, sbK.toString());
        return new byte[0];
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getSetPermission mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return "";
    }

    public int getSlope() {
        Log.v(TAG, "getSlope is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getSlope mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
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
        e.r("The value is not illegal,errorCode:-1", slope, TAG);
        return -1;
    }

    public float getTemperatureSensorValue() {
        Log.v(TAG, "getTemperatureSensorValue is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getTemperatureSensorValue mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
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

    public int getType() {
        Log.v(TAG, "getType is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_GET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("getType mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        Log.v(TAG, "postEvent float value is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("postEvent float value mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return false;
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        registerListener(absBYDAutoSensorListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoSensorListener absBYDAutoSensorListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe sensor listener ");
        sb.append(absBYDAutoSensorListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoSensorListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            e.v(e.k("registerListener one para mForTestValue is"), this.mForTest, " execute return", TAG);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    public int setOrientationAngle(int i) {
        Log.v(TAG, "setOrientationAngle is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("setOrientationAngle mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
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

    public void unregisterListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_COMMON);
        this.mAbsBydAutoSensorListener = null;
        if (1 == this.mForTest) {
            e.v(e.k("unregisterListener mForTestValue is "), this.mForTest, " execute return", TAG);
        } else if (this.mTsManagerImpl.getCarSensorAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
        } else {
            this.mAbsBydAutoSensorListeners.remove(absBYDAutoSensorListener);
            unregisterConnectListener(absBYDAutoSensorListener);
        }
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.v(TAG, "postEvent int value is empty implementation. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("postEvent int value mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return false;
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        Log.v(TAG, "postEvent is run. ");
        enforcePermission(BYDAutoSensorDevice.PERMISSION_BYDAUTO_SENSOR_SET);
        Log.v(TAG, "postEvent byte value is empty implementation. ");
        if (1 == this.mForTest) {
            StringBuilder sbK = e.k("postEvent byte value mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
        }
        return false;
    }
}
