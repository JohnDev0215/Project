package org.chromium.content.browser;

import android.car.Car;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.CollectionUtil;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class DeviceSensors implements SensorEventListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String EXPERIMENTAL_WEB_PLAFTORM_FEATURES = "enable-experimental-web-platform-features";
    private static final String TAG = "cr.DeviceSensors";
    private static DeviceSensors sSingleton;
    private final Context mAppContext;
    Set<Integer> mDeviceOrientationSensors;
    private float[] mDeviceRotationMatrix;
    private Handler mHandler;
    private float[] mMagneticFieldVector;
    private long mNativePtr;
    private double[] mRotationAngles;
    private SensorManagerProxy mSensorManagerProxy;
    private Thread mThread;
    private float[] mTruncatedRotationVector;
    private static Object sSingletonLock = new Object();
    static final Set<Integer> DEVICE_ORIENTATION_SENSORS_A = CollectionUtil.newHashSet(15);
    static final Set<Integer> DEVICE_ORIENTATION_SENSORS_B = CollectionUtil.newHashSet(11);
    static final Set<Integer> DEVICE_ORIENTATION_SENSORS_C = CollectionUtil.newHashSet(1, 2);
    static final Set<Integer> DEVICE_ORIENTATION_ABSOLUTE_SENSORS = CollectionUtil.newHashSet(11);
    static final Set<Integer> DEVICE_MOTION_SENSORS = CollectionUtil.newHashSet(1, 10, 4);
    static final Set<Integer> DEVICE_LIGHT_SENSORS = CollectionUtil.newHashSet(5);
    private final Object mHandlerLock = new Object();
    private final Object mNativePtrLock = new Object();
    final Set<Integer> mActiveSensors = new HashSet();
    boolean mDeviceLightIsActive = false;
    boolean mDeviceMotionIsActive = false;
    boolean mDeviceOrientationIsActive = false;
    boolean mDeviceOrientationIsActiveWithBackupSensors = false;
    boolean mDeviceOrientationAbsoluteIsActive = false;
    boolean mOrientationNotAvailable = false;
    final List<Set<Integer>> mOrientationSensorSets = CollectionUtil.newArrayList(DEVICE_ORIENTATION_SENSORS_A, DEVICE_ORIENTATION_SENSORS_B, DEVICE_ORIENTATION_SENSORS_C);

    interface SensorManagerProxy {
        boolean registerListener(SensorEventListener sensorEventListener, int i, int i2, Handler handler);

        void unregisterListener(SensorEventListener sensorEventListener, int i);
    }

    private native void nativeGotAcceleration(long j, double d, double d2, double d3);

    private native void nativeGotAccelerationIncludingGravity(long j, double d, double d2, double d3);

    private native void nativeGotLight(long j, double d);

    private native void nativeGotOrientation(long j, double d, double d2, double d3);

    private native void nativeGotOrientationAbsolute(long j, double d, double d2, double d3);

    private native void nativeGotRotationRate(long j, double d, double d2, double d3);

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    protected DeviceSensors(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    protected boolean registerOrientationSensorsWithFallback(int i) {
        if (this.mOrientationNotAvailable) {
            return false;
        }
        Set<Integer> set = this.mDeviceOrientationSensors;
        if (set != null) {
            return registerSensors(set, i, true);
        }
        ensureRotationStructuresAllocated();
        for (Set<Integer> set2 : this.mOrientationSensorSets) {
            this.mDeviceOrientationSensors = set2;
            if (registerSensors(set2, i, true)) {
                return true;
            }
        }
        this.mOrientationNotAvailable = true;
        this.mDeviceOrientationSensors = null;
        this.mDeviceRotationMatrix = null;
        this.mRotationAngles = null;
        return false;
    }

    public boolean start(long j, int i, int i2) {
        boolean zRegisterSensors;
        synchronized (this.mNativePtrLock) {
            if (i == 1) {
                zRegisterSensors = registerSensors(DEVICE_MOTION_SENSORS, i2, false);
            } else if (i == 2) {
                zRegisterSensors = registerOrientationSensorsWithFallback(i2);
            } else if (i == 4) {
                ensureRotationStructuresAllocated();
                zRegisterSensors = registerSensors(DEVICE_ORIENTATION_ABSOLUTE_SENSORS, i2, true);
            } else {
                if (i != 8) {
                    Log.e(TAG, "Unknown event type: %d", Integer.valueOf(i));
                    return false;
                }
                zRegisterSensors = registerSensors(DEVICE_LIGHT_SENSORS, i2, true);
            }
            if (zRegisterSensors) {
                this.mNativePtr = j;
                setEventTypeActive(i, true);
            }
            return zRegisterSensors;
        }
    }

    public int getNumberActiveDeviceMotionSensors() {
        Set<Integer> set = DEVICE_MOTION_SENSORS;
        HashSet hashSet = new HashSet(set);
        hashSet.removeAll(this.mActiveSensors);
        return set.size() - hashSet.size();
    }

    public int getOrientationSensorTypeUsed() {
        if (this.mOrientationNotAvailable) {
            return 0;
        }
        Set<Integer> set = this.mDeviceOrientationSensors;
        if (set == DEVICE_ORIENTATION_SENSORS_A) {
            return 3;
        }
        if (set == DEVICE_ORIENTATION_SENSORS_B) {
            return 1;
        }
        return set == DEVICE_ORIENTATION_SENSORS_C ? 2 : 0;
    }

    public void stop(int i) {
        HashSet hashSet = new HashSet();
        synchronized (this.mNativePtrLock) {
            if (this.mDeviceOrientationIsActive && i != 2) {
                hashSet.addAll(this.mDeviceOrientationSensors);
            }
            if (this.mDeviceOrientationAbsoluteIsActive && i != 4) {
                hashSet.addAll(DEVICE_ORIENTATION_ABSOLUTE_SENSORS);
            }
            if (this.mDeviceMotionIsActive && i != 1) {
                hashSet.addAll(DEVICE_MOTION_SENSORS);
            }
            if (this.mDeviceLightIsActive && i != 8) {
                hashSet.addAll(DEVICE_LIGHT_SENSORS);
            }
            HashSet hashSet2 = new HashSet(this.mActiveSensors);
            hashSet2.removeAll(hashSet);
            unregisterSensors(hashSet2);
            setEventTypeActive(i, false);
            if (this.mActiveSensors.isEmpty()) {
                this.mNativePtr = 0L;
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensorChanged(sensorEvent.sensor.getType(), sensorEvent.values);
    }

    void sensorChanged(int i, float[] fArr) {
        if (i == 1) {
            if (this.mDeviceMotionIsActive) {
                gotAccelerationIncludingGravity(fArr[0], fArr[1], fArr[2]);
            }
            if (this.mDeviceOrientationIsActiveWithBackupSensors) {
                getOrientationFromGeomagneticVectors(fArr, this.mMagneticFieldVector);
                return;
            }
            return;
        }
        if (i == 2) {
            if (this.mDeviceOrientationIsActiveWithBackupSensors) {
                if (this.mMagneticFieldVector == null) {
                    this.mMagneticFieldVector = new float[3];
                }
                float[] fArr2 = this.mMagneticFieldVector;
                System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
                return;
            }
            return;
        }
        if (i == 4) {
            if (this.mDeviceMotionIsActive) {
                gotRotationRate(fArr[0], fArr[1], fArr[2]);
                return;
            }
            return;
        }
        if (i == 5) {
            if (this.mDeviceLightIsActive) {
                gotLight(fArr[0]);
                return;
            }
            return;
        }
        if (i == 10) {
            if (this.mDeviceMotionIsActive) {
                gotAcceleration(fArr[0], fArr[1], fArr[2]);
                return;
            }
            return;
        }
        if (i == 11) {
            if (this.mDeviceOrientationAbsoluteIsActive) {
                convertRotationVectorToAngles(fArr, this.mRotationAngles);
                double[] dArr = this.mRotationAngles;
                gotOrientationAbsolute(dArr[0], dArr[1], dArr[2]);
            }
            if (this.mDeviceOrientationIsActive && this.mDeviceOrientationSensors == DEVICE_ORIENTATION_SENSORS_B) {
                if (!this.mDeviceOrientationAbsoluteIsActive) {
                    convertRotationVectorToAngles(fArr, this.mRotationAngles);
                }
                double[] dArr2 = this.mRotationAngles;
                gotOrientation(dArr2[0], dArr2[1], dArr2[2]);
                return;
            }
            return;
        }
        if (i == 15 && this.mDeviceOrientationIsActive) {
            convertRotationVectorToAngles(fArr, this.mRotationAngles);
            double[] dArr3 = this.mRotationAngles;
            gotOrientation(dArr3[0], dArr3[1], dArr3[2]);
        }
    }

    public static double[] computeDeviceOrientationFromRotationMatrix(float[] fArr, double[] dArr) {
        if (fArr.length != 9) {
            return dArr;
        }
        float f = fArr[8];
        if (f > 0.0f) {
            dArr[0] = Math.atan2(-fArr[1], fArr[4]);
            dArr[1] = Math.asin(fArr[7]);
            dArr[2] = Math.atan2(-fArr[6], fArr[8]);
        } else if (f < 0.0f) {
            dArr[0] = Math.atan2(fArr[1], -fArr[4]);
            double d = -Math.asin(fArr[7]);
            dArr[1] = d;
            dArr[1] = d + (d >= 0.0d ? -3.141592653589793d : 3.141592653589793d);
            dArr[2] = Math.atan2(fArr[6], -fArr[8]);
        } else {
            float f2 = fArr[6];
            if (f2 > 0.0f) {
                dArr[0] = Math.atan2(-fArr[1], fArr[4]);
                dArr[1] = Math.asin(fArr[7]);
                dArr[2] = -1.5707963267948966d;
            } else if (f2 < 0.0f) {
                dArr[0] = Math.atan2(fArr[1], -fArr[4]);
                double d2 = -Math.asin(fArr[7]);
                dArr[1] = d2;
                dArr[1] = d2 + (d2 >= 0.0d ? -3.141592653589793d : 3.141592653589793d);
                dArr[2] = -1.5707963267948966d;
            } else {
                dArr[0] = Math.atan2(fArr[3], fArr[0]);
                dArr[1] = fArr[7] > 0.0f ? 1.5707963267948966d : -1.5707963267948966d;
                dArr[2] = 0.0d;
            }
        }
        double d3 = dArr[0];
        if (d3 < 0.0d) {
            dArr[0] = d3 + 6.283185307179586d;
        }
        return dArr;
    }

    public void convertRotationVectorToAngles(float[] fArr, double[] dArr) {
        if (fArr.length > 4) {
            System.arraycopy(fArr, 0, this.mTruncatedRotationVector, 0, 4);
            SensorManager.getRotationMatrixFromVector(this.mDeviceRotationMatrix, this.mTruncatedRotationVector);
        } else {
            SensorManager.getRotationMatrixFromVector(this.mDeviceRotationMatrix, fArr);
        }
        computeDeviceOrientationFromRotationMatrix(this.mDeviceRotationMatrix, dArr);
        for (int i = 0; i < 3; i++) {
            dArr[i] = Math.toDegrees(dArr[i]);
        }
    }

    private void getOrientationFromGeomagneticVectors(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null || !SensorManager.getRotationMatrix(this.mDeviceRotationMatrix, null, fArr, fArr2)) {
            return;
        }
        computeDeviceOrientationFromRotationMatrix(this.mDeviceRotationMatrix, this.mRotationAngles);
        gotOrientation(Math.toDegrees(this.mRotationAngles[0]), Math.toDegrees(this.mRotationAngles[1]), Math.toDegrees(this.mRotationAngles[2]));
    }

    private SensorManagerProxy getSensorManagerProxy() {
        SensorManagerProxy sensorManagerProxy = this.mSensorManagerProxy;
        if (sensorManagerProxy != null) {
            return sensorManagerProxy;
        }
        ThreadUtils.assertOnUiThread();
        SensorManager sensorManager = (SensorManager) this.mAppContext.getSystemService(Car.SENSOR_SERVICE);
        if (sensorManager != null) {
            this.mSensorManagerProxy = new SensorManagerProxyImpl(sensorManager);
        }
        return this.mSensorManagerProxy;
    }

    void setSensorManagerProxy(SensorManagerProxy sensorManagerProxy) {
        this.mSensorManagerProxy = sensorManagerProxy;
    }

    private void setEventTypeActive(int i, boolean z) {
        if (i == 1) {
            this.mDeviceMotionIsActive = z;
            return;
        }
        if (i == 2) {
            this.mDeviceOrientationIsActive = z;
            this.mDeviceOrientationIsActiveWithBackupSensors = z && this.mDeviceOrientationSensors == DEVICE_ORIENTATION_SENSORS_C;
        } else if (i == 4) {
            this.mDeviceOrientationAbsoluteIsActive = z;
        } else {
            if (i != 8) {
                return;
            }
            this.mDeviceLightIsActive = z;
        }
    }

    private void ensureRotationStructuresAllocated() {
        if (this.mDeviceRotationMatrix == null) {
            this.mDeviceRotationMatrix = new float[9];
        }
        if (this.mRotationAngles == null) {
            this.mRotationAngles = new double[3];
        }
        if (this.mTruncatedRotationVector == null) {
            this.mTruncatedRotationVector = new float[4];
        }
    }

    private boolean registerSensors(Set<Integer> set, int i, boolean z) {
        HashSet<Integer> hashSet = new HashSet(set);
        hashSet.removeAll(this.mActiveSensors);
        if (hashSet.isEmpty()) {
            return true;
        }
        boolean z2 = false;
        for (Integer num : hashSet) {
            boolean zRegisterForSensorType = registerForSensorType(num.intValue(), i);
            if (!zRegisterForSensorType && z) {
                unregisterSensors(hashSet);
                return false;
            }
            if (zRegisterForSensorType) {
                this.mActiveSensors.add(num);
                z2 = true;
            }
        }
        return z2;
    }

    private void unregisterSensors(Iterable<Integer> iterable) {
        for (Integer num : iterable) {
            if (this.mActiveSensors.contains(num)) {
                getSensorManagerProxy().unregisterListener(this, num.intValue());
                this.mActiveSensors.remove(num);
            }
        }
    }

    private boolean registerForSensorType(int i, int i2) {
        SensorManagerProxy sensorManagerProxy = getSensorManagerProxy();
        if (sensorManagerProxy == null) {
            return false;
        }
        return sensorManagerProxy.registerListener(this, i, i2, getHandler());
    }

    protected void gotOrientation(double d, double d2, double d3) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotOrientation(j, d, d2, d3);
            }
        }
    }

    protected void gotOrientationAbsolute(double d, double d2, double d3) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotOrientationAbsolute(j, d, d2, d3);
            }
        }
    }

    protected void gotAcceleration(double d, double d2, double d3) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotAcceleration(j, d, d2, d3);
            }
        }
    }

    protected void gotAccelerationIncludingGravity(double d, double d2, double d3) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotAccelerationIncludingGravity(j, d, d2, d3);
            }
        }
    }

    protected void gotRotationRate(double d, double d2, double d3) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotRotationRate(j, d, d2, d3);
            }
        }
    }

    protected void gotLight(double d) {
        synchronized (this.mNativePtrLock) {
            long j = this.mNativePtr;
            if (j != 0) {
                nativeGotLight(j, d);
            }
        }
    }

    private Handler getHandler() {
        Handler handler;
        synchronized (this.mHandlerLock) {
            if (this.mHandler == null) {
                HandlerThread handlerThread = new HandlerThread("DeviceMotionAndOrientation");
                handlerThread.start();
                this.mHandler = new Handler(handlerThread.getLooper());
            }
            handler = this.mHandler;
        }
        return handler;
    }

    static DeviceSensors getInstance(Context context) {
        DeviceSensors deviceSensors;
        synchronized (sSingletonLock) {
            if (sSingleton == null) {
                sSingleton = new DeviceSensors(context);
            }
            deviceSensors = sSingleton;
        }
        return deviceSensors;
    }

    static class SensorManagerProxyImpl implements SensorManagerProxy {
        private final SensorManager mSensorManager;

        SensorManagerProxyImpl(SensorManager sensorManager) {
            this.mSensorManager = sensorManager;
        }

        @Override // org.chromium.content.browser.DeviceSensors.SensorManagerProxy
        public boolean registerListener(SensorEventListener sensorEventListener, int i, int i2, Handler handler) {
            List<Sensor> sensorList = this.mSensorManager.getSensorList(i);
            if (sensorList.isEmpty()) {
                return false;
            }
            return this.mSensorManager.registerListener(sensorEventListener, sensorList.get(0), i2, handler);
        }

        @Override // org.chromium.content.browser.DeviceSensors.SensorManagerProxy
        public void unregisterListener(SensorEventListener sensorEventListener, int i) {
            List<Sensor> sensorList = this.mSensorManager.getSensorList(i);
            if (sensorList.isEmpty()) {
                return;
            }
            try {
                this.mSensorManager.unregisterListener(sensorEventListener, sensorList.get(0));
            } catch (IllegalArgumentException unused) {
                Log.w(DeviceSensors.TAG, "Failed to unregister device sensor " + sensorList.get(0).getName(), new Object[0]);
            }
        }
    }
}
