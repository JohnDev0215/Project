package com.ecarx.xui.adaptapi.navigation.dr;

import android.car.Car;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap;
import com.neusoft.alfus.RpcManager;
import com.neusoft.extraservice.IRequestSensorAidlInterface;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class DrAutoByMap implements IDrAutoByMap {
    private static final float G = 9.8f;
    public static final String TAG = "DrAutoByMap";
    private Handler handler;
    private Acc3dInfo mAcc3dInfo;
    private Context mContext;
    private DRBean mDRBean;
    private IDrAutoByMap.OnDrChangedListener mDrChangeListener;
    private GyroInfo mGyroInfo;
    private MountAngleInfo mMountAngleInfo;
    private PulseInfo mPulseInfo;
    private IRequestSensorAidlInterface mSensorAidl;
    private Sensor sensorAccelerometer;
    private Sensor sensorGyroscope;
    private SensorManager sensorManager;
    private long gyroscope_last_time = 0;
    private long accelerometer_last_time = 0;
    HandlerThread mHandlerThread = new HandlerThread("handler_thread");
    private boolean reverse_flag = false;
    private RpcManager.OnRpcListener TCU_ModeGearDisplayStsCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.navigation.dr.DrAutoByMap.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1834 && bArr[0] == 2) {
                if (bArr[1] == 2) {
                    DrAutoByMap.this.reverse_flag = true;
                    DrAutoByMap.this.mPulseInfo.setReverse_flag(true);
                } else {
                    DrAutoByMap.this.reverse_flag = false;
                    DrAutoByMap.this.mPulseInfo.setReverse_flag(false);
                }
            }
        }
    };
    private RpcManager.OnRpcListener mSpeedCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.navigation.dr.DrAutoByMap.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr[0] == 4) {
                int i2 = ((char) bArr[1]) & 255;
                double d = ((double) (((i2 << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (((char) bArr[2]) & 255))) * 0.05625d;
                if (DrAutoByMap.this.reverse_flag) {
                    d = -d;
                }
                DrAutoByMap.this.mPulseInfo.setmSpeed(Float.valueOf(String.valueOf(d)).floatValue());
            }
        }
    };
    private SensorEventListener mGyroListener = new SensorEventListener() { // from class: com.ecarx.xui.adaptapi.navigation.dr.DrAutoByMap.3
        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                DrAutoByMap.this.sendAcceData(sensorEvent);
            } else if (type == 4) {
                DrAutoByMap.this.sendGyroData(sensorEvent);
            } else {
                Log.d(DrAutoByMap.TAG, "onSensorChanged default");
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            Log.d(DrAutoByMap.TAG, "onAccuracyChanged");
        }
    };

    public DrAutoByMap(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        LogTool.d(TAG, "DrAutoByMap init~");
        this.mDRBean = new DRBean();
        this.mGyroInfo = new GyroInfo();
        this.mPulseInfo = new PulseInfo();
        this.mAcc3dInfo = new Acc3dInfo();
        this.mMountAngleInfo = new MountAngleInfo();
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService(Car.SENSOR_SERVICE);
        this.sensorManager = sensorManager;
        this.sensorGyroscope = sensorManager.getDefaultSensor(4);
        this.sensorAccelerometer = this.sensorManager.getDefaultSensor(1);
        this.mHandlerThread.start();
        this.handler = new Handler(this.mHandlerThread.getLooper());
        RpcManager.getInstance().registerListener(1834, (byte) 2, this.TCU_ModeGearDisplayStsCallBack);
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public FunctionStatus isKeyTypeSupported(int i) {
        LogTool.d(TAG, "getSupportedKeyTypes keyType = " + i);
        if (i == 5) {
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.active;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public int[] getSupportedKeyTypes() {
        LogTool.d(TAG, "getSupportedKeyTypes");
        return new int[]{1, 2, 4, 3};
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public IDrAPInfo getDrLatestInfo(int i) {
        if (this.mDRBean != null) {
            LogTool.d(TAG, "getDrLatestInfo");
            return this.mDRBean;
        }
        LogTool.d(TAG, "getDrLatestInfo null");
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public boolean registerListener(IDrAutoByMap.OnDrChangedListener onDrChangedListener, int i) {
        LogTool.d(TAG, "registerListener1");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public boolean registerListener(IDrAutoByMap.OnDrChangedListener onDrChangedListener, int i, int i2) {
        LogTool.d(TAG, "registerListener2");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public boolean registerListener(IDrAutoByMap.OnDrChangedListener onDrChangedListener, int[] iArr, int i) {
        LogTool.d(TAG, "registerListener6~");
        if (onDrChangedListener == null) {
            return false;
        }
        this.mDrChangeListener = onDrChangedListener;
        this.sensorManager.registerListener(this.mGyroListener, this.sensorGyroscope, 1);
        this.sensorManager.registerListener(this.mGyroListener, this.sensorAccelerometer, 1);
        RpcManager.getInstance().registerListener(1821, (byte) 4, this.mSpeedCallback);
        loopSend loopsend = new loopSend();
        loopsend.startTime = SystemClock.elapsedRealtime();
        this.handler.post(loopsend);
        return true;
    }

    public class loopSend implements Runnable {
        public long startTime = 0;

        public loopSend() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DrAutoByMap.this.mDrChangeListener != null) {
                DrAutoByMap.this.mDrChangeListener.onSensorChanged(1, DrAutoByMap.this.mDRBean);
                DrAutoByMap.this.mDrChangeListener.onSensorChanged(3, DrAutoByMap.this.mDRBean);
                DrAutoByMap.this.mDrChangeListener.onSensorChanged(4, DrAutoByMap.this.mDRBean);
                DrAutoByMap.this.mDrChangeListener.onSensorChanged(2, DrAutoByMap.this.mDRBean);
                long jElapsedRealtime = SystemClock.elapsedRealtime() - this.startTime;
                if (jElapsedRealtime < 100 && jElapsedRealtime >= 0) {
                    long j = 100 - jElapsedRealtime;
                    this.startTime = SystemClock.elapsedRealtime() + j;
                    DrAutoByMap.this.handler.postDelayed(this, j);
                } else {
                    LogTool.d(DrAutoByMap.TAG, "[ljTest] sensor sendTag error useTime > 100ms");
                    this.startTime = SystemClock.elapsedRealtime();
                    DrAutoByMap.this.handler.post(this);
                }
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAutoByMap
    public void unregisterListener(IDrAutoByMap.OnDrChangedListener onDrChangedListener) {
        LogTool.d(TAG, "unregisterListener");
        this.sensorManager.unregisterListener(this.mGyroListener, this.sensorGyroscope);
        this.sensorManager.unregisterListener(this.mGyroListener, this.sensorAccelerometer);
        RpcManager.getInstance().unregisterListener(1821, (byte) 4, this.mSpeedCallback);
        this.mDrChangeListener = null;
    }

    private boolean isBytesIndexOut(float[] fArr, int i) {
        return fArr.length <= i;
    }

    public void sendGyroData(SensorEvent sensorEvent) {
        this.mGyroInfo.setAxis(7);
        if (!isBytesIndexOut(sensorEvent.values, 2)) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            this.mGyroInfo.setPitch(-f);
            this.mGyroInfo.setRoll(f2);
            this.mGyroInfo.setYaw(-f3);
            if (sensorEvent.values.length == 4) {
                this.mGyroInfo.setTem(sensorEvent.values[3]);
            }
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.gyroscope_last_time;
        this.mGyroInfo.setInterval((int) (j != 0 ? jElapsedRealtime - j : 0L));
        this.gyroscope_last_time = jElapsedRealtime;
        this.mGyroInfo.setTime(SystemClock.elapsedRealtime());
    }

    public void sendAcceData(SensorEvent sensorEvent) {
        this.mAcc3dInfo.setAxis(7);
        if (!isBytesIndexOut(sensorEvent.values, 2)) {
            this.mAcc3dInfo.setPitch(sensorEvent.values[0] / G);
            this.mAcc3dInfo.setRoll(sensorEvent.values[1] / G);
            this.mAcc3dInfo.setYaw(sensorEvent.values[2] / G);
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.accelerometer_last_time;
        this.mAcc3dInfo.setInterval((int) (j != 0 ? jElapsedRealtime - j : 0L));
        this.accelerometer_last_time = jElapsedRealtime;
        this.mAcc3dInfo.setTime(SystemClock.elapsedRealtime());
    }

    public String getVehicleType() {
        String str = SystemProperties.get("ro.product.device");
        Log.d(TAG, "getVehicleType = " + str);
        return (str == null || str.isEmpty()) ? "车型null" : str;
    }

    public class DRBean implements IDrAPInfo {
        @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo
        public IDrAPInfo.IW4m getW4m() {
            return null;
        }

        public DRBean() {
        }

        @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo
        public IDrAPInfo.IGyro getGyro() {
            return DrAutoByMap.this.mGyroInfo;
        }

        @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo
        public IDrAPInfo.IPulse getPulse() {
            return DrAutoByMap.this.mPulseInfo;
        }

        @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo
        public IDrAPInfo.IAcc3d getAcc3d() {
            return DrAutoByMap.this.mAcc3dInfo;
        }

        @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo
        public IDrAPInfo.IMountAngle getMountAngle() {
            return DrAutoByMap.this.mMountAngleInfo;
        }
    }
}
