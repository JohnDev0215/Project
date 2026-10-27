package com.visualon.OSMPSensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.view.Display;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes7.dex */
public class voSphericalVideoViewImpl implements SensorEventListener {
    private Clock mClock;
    private final Display mDisplay;
    private float mLastHeadingDegree;
    private long mLatestGyroEventClockTimeNs;
    private final float[] mNeckModelTranslation;
    private SensorEventProvider mSensorEventProvider;
    private volatile boolean mTracking;
    private boolean headingResetToZero = false;
    private final float[] mEkfToHeadTracker = new float[16];
    private final float[] mSensorToDisplay = new float[16];
    private float mDisplayRotation = Float.NaN;
    private final float[] mTmpHeadView = new float[16];
    private final float[] mTmpHeadView2 = new float[16];
    private boolean mNeckModelEnabled = false;
    private final Vector3d mGyroBias = new Vector3d();
    private final Vector3d mLatestGyro = new Vector3d();
    private final Vector3d mLatestAcc = new Vector3d();
    private OrientationEKF mTracker = new OrientationEKF();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public static voSphericalVideoViewImpl createFromContext(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return new voSphericalVideoViewImpl(new DeviceSensorLooper(sensorManager), new SystemClock(), ((WindowManager) context.getSystemService("window")).getDefaultDisplay());
    }

    public voSphericalVideoViewImpl(SensorEventProvider sensorEventProvider, Clock clock, Display display) {
        float[] fArr = new float[16];
        this.mNeckModelTranslation = fArr;
        this.mClock = clock;
        this.mSensorEventProvider = sensorEventProvider;
        this.mDisplay = display;
        Matrix.setIdentityM(fArr, 0);
        Matrix.translateM(fArr, 0, 0.0f, -0.075f, 0.08f);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            Vector3d vector3d = this.mLatestAcc;
            float[] fArr = sensorEvent.values;
            vector3d.set(fArr[0], fArr[1], fArr[2]);
            this.mTracker.processAcc(this.mLatestAcc, sensorEvent.timestamp);
            return;
        }
        if (sensorEvent.sensor.getType() == 4) {
            this.mLatestGyroEventClockTimeNs = this.mClock.nanoTime();
            Vector3d vector3d2 = this.mLatestGyro;
            float[] fArr2 = sensorEvent.values;
            vector3d2.set(fArr2[0], fArr2[1], fArr2[2]);
            Vector3d vector3d3 = this.mLatestGyro;
            Vector3d.sub(vector3d3, this.mGyroBias, vector3d3);
            this.mTracker.processGyro(this.mLatestGyro, sensorEvent.timestamp);
            if (!this.headingResetToZero) {
                this.mTracker.setHeadingDegrees(0.0d);
                this.headingResetToZero = true;
            }
            this.mLastHeadingDegree = (float) this.mTracker.getHeadingDegrees();
        }
    }

    public float getHeadingDegrees() {
        float f = this.mLastHeadingDegree;
        return f == -1.0f ? (float) this.mTracker.getHeadingDegrees() : f;
    }

    public void startTracking() {
        if (this.mTracking) {
            return;
        }
        this.mTracker.reset();
        this.mSensorEventProvider.registerListener(this);
        this.mSensorEventProvider.start();
        this.mTracking = true;
        this.headingResetToZero = false;
    }

    public void stopTracking() {
        if (this.mTracking) {
            this.mSensorEventProvider.unregisterListener(this);
            this.mSensorEventProvider.stop();
            this.mTracking = false;
        }
    }

    public void pauseTracking() {
        if (this.mTracking) {
            this.mSensorEventProvider.stop();
            this.mTracking = false;
        }
    }

    public void resumeTracking() {
        if (this.mTracking) {
            return;
        }
        this.mSensorEventProvider.start();
        this.mTracking = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float[] getLastHeadView() {
        /*
            r9 = this;
            r0 = 16
            float[] r8 = new float[r0]
            android.view.Display r1 = r9.mDisplay
            int r1 = r1.getRotation()
            r2 = 1127481344(0x43340000, float:180.0)
            r3 = 0
            if (r1 == 0) goto L18
            r4 = 1
            if (r1 == r4) goto L1f
            r4 = 2
            if (r1 == r4) goto L1d
            r4 = 3
            if (r1 == r4) goto L1a
        L18:
            r1 = r3
            goto L21
        L1a:
            r1 = 1132920832(0x43870000, float:270.0)
            goto L21
        L1d:
            r1 = r2
            goto L21
        L1f:
            r1 = 1119092736(0x42b40000, float:90.0)
        L21:
            float r4 = r9.mDisplayRotation
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            r5 = 0
            if (r4 == 0) goto L35
            r9.mDisplayRotation = r1
            float[] r4 = r9.mSensorToDisplay
            float r1 = -r1
            android.opengl.Matrix.setRotateEulerM(r4, r5, r3, r3, r1)
            float[] r1 = r9.mEkfToHeadTracker
            android.opengl.Matrix.setRotateEulerM(r1, r5, r2, r3, r3)
        L35:
            com.visualon.OSMPSensor.OrientationEKF r1 = r9.mTracker
            monitor-enter(r1)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.Throwable -> L9a
            com.visualon.OSMPSensor.Clock r3 = r9.mClock     // Catch: java.lang.Throwable -> L9a
            long r3 = r3.nanoTime()     // Catch: java.lang.Throwable -> L9a
            long r6 = r9.mLatestGyroEventClockTimeNs     // Catch: java.lang.Throwable -> L9a
            long r3 = r3 - r6
            long r2 = r2.toSeconds(r3)     // Catch: java.lang.Throwable -> L9a
            double r2 = (double) r2     // Catch: java.lang.Throwable -> L9a
            r6 = 4584964660638322961(0x3fa1111111111111, double:0.03333333333333333)
            double r2 = r2 + r6
            com.visualon.OSMPSensor.OrientationEKF r4 = r9.mTracker     // Catch: java.lang.Throwable -> L9a
            boolean r6 = r9.mTracking     // Catch: java.lang.Throwable -> L9a
            if (r6 == 0) goto L55
            goto L57
        L55:
            r2 = 0
        L57:
            double[] r2 = r4.getPredictedGLMatrix(r2)     // Catch: java.lang.Throwable -> L9a
        L5b:
            if (r5 >= r0) goto L67
            float[] r3 = r9.mTmpHeadView     // Catch: java.lang.Throwable -> L9a
            r6 = r2[r5]     // Catch: java.lang.Throwable -> L9a
            float r4 = (float) r6     // Catch: java.lang.Throwable -> L9a
            r3[r5] = r4     // Catch: java.lang.Throwable -> L9a
            int r5 = r5 + 1
            goto L5b
        L67:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L9a
            float[] r2 = r9.mTmpHeadView2
            r3 = 0
            float[] r4 = r9.mSensorToDisplay
            r5 = 0
            float[] r6 = r9.mTmpHeadView
            r7 = 0
            android.opengl.Matrix.multiplyMM(r2, r3, r4, r5, r6, r7)
            r2 = 0
            float[] r3 = r9.mTmpHeadView2
            r4 = 0
            float[] r5 = r9.mEkfToHeadTracker
            r6 = 0
            r1 = r8
            android.opengl.Matrix.multiplyMM(r1, r2, r3, r4, r5, r6)
            boolean r0 = r9.mNeckModelEnabled
            if (r0 == 0) goto L99
            float[] r1 = r9.mTmpHeadView
            r2 = 0
            float[] r3 = r9.mNeckModelTranslation
            r4 = 0
            r6 = 0
            r5 = r8
            android.opengl.Matrix.multiplyMM(r1, r2, r3, r4, r5, r6)
            float[] r3 = r9.mTmpHeadView
            r5 = 0
            r6 = 1033476506(0x3d99999a, float:0.075)
            r7 = 0
            r1 = r8
            android.opengl.Matrix.translateM(r1, r2, r3, r4, r5, r6, r7)
        L99:
            return r8
        L9a:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L9a
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPSensor.voSphericalVideoViewImpl.getLastHeadView():float[]");
    }
}
