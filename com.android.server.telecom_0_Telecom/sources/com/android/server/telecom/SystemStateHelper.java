package com.android.server.telecom;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.telecom.Log;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class SystemStateHelper {
    private final Context mContext;
    private boolean mIsCarMode;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.SystemStateHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("SSP.oR");
            try {
                String action = intent.getAction();
                if ("android.app.action.ENTER_CAR_MODE_PRIORITIZED".equals(action)) {
                    int intExtra = intent.getIntExtra("android.app.extra.PRIORITY", 0);
                    String stringExtra = intent.getStringExtra("android.app.extra.CALLING_PACKAGE");
                    Log.i(SystemStateHelper.this, "ENTER_CAR_MODE_PRIVILEGED; priority=%d, pkg=%s", new Object[]{Integer.valueOf(intExtra), stringExtra});
                    SystemStateHelper.this.onEnterCarMode(intExtra, stringExtra);
                } else if ("android.app.action.EXIT_CAR_MODE_PRIORITIZED".equals(action)) {
                    int intExtra2 = intent.getIntExtra("android.app.extra.PRIORITY", 0);
                    String stringExtra2 = intent.getStringExtra("android.app.extra.CALLING_PACKAGE");
                    Log.i(SystemStateHelper.this, "EXIT_CAR_MODE_PRIVILEGED; priority=%d, pkg=%s", new Object[]{Integer.valueOf(intExtra2), stringExtra2});
                    SystemStateHelper.this.onExitCarMode(intExtra2, stringExtra2);
                } else {
                    Log.w(this, "Unexpected intent received: %s", new Object[]{intent.getAction()});
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private Set<SystemStateListener> mListeners = new CopyOnWriteArraySet();

    public interface SystemStateListener {
        void onCarModeChanged(int i, String str, boolean z);
    }

    public SystemStateHelper(Context context) {
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter("android.app.action.ENTER_CAR_MODE_PRIORITIZED");
        intentFilter.addAction("android.app.action.EXIT_CAR_MODE_PRIORITIZED");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
        Log.i(this, "Registering car mode receiver: %s", new Object[]{intentFilter});
        this.mIsCarMode = getSystemCarMode();
    }

    public void addListener(SystemStateListener systemStateListener) {
        if (systemStateListener != null) {
            this.mListeners.add(systemStateListener);
        }
    }

    public boolean isCarMode() {
        return this.mIsCarMode;
    }

    public boolean isDeviceAtEar() {
        return isDeviceAtEar(this.mContext);
    }

    public static boolean isDeviceAtEar(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(SensorManager.class);
        if (sensorManager == null) {
            return false;
        }
        Sensor defaultSensor = sensorManager.getDefaultSensor(9);
        final Sensor defaultSensor2 = sensorManager.getDefaultSensor(8);
        if (defaultSensor != null && defaultSensor2 != null) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final CountDownLatch countDownLatch2 = new CountDownLatch(1);
            final double d = context.getResources().getFloat(R.dimen.device_on_ear_xy_gravity_threshold);
            final double d2 = context.getResources().getFloat(R.dimen.device_on_ear_y_gravity_negative_threshold);
            SensorEventListener sensorEventListener = new SensorEventListener() { // from class: com.android.server.telecom.SystemStateHelper.2
                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent.sensor.getType() != 9) {
                        if (sensorEvent.sensor.getType() != 8 || countDownLatch2.getCount() == 0) {
                            return;
                        }
                        if (sensorEvent.values[0] >= defaultSensor2.getMaximumRange()) {
                            atomicBoolean.set(false);
                        }
                        countDownLatch2.countDown();
                        return;
                    }
                    if (countDownLatch.getCount() == 0) {
                        return;
                    }
                    float[] fArr = sensorEvent.values;
                    if (Math.sqrt((fArr[0] * fArr[0]) + (fArr[1] * fArr[1])) < d || sensorEvent.values[1] < d2) {
                        atomicBoolean.set(false);
                    }
                    countDownLatch.countDown();
                }
            };
            try {
                sensorManager.registerListener(sensorEventListener, defaultSensor, 0);
                sensorManager.registerListener(sensorEventListener, defaultSensor2, 0);
                boolean zAwait = countDownLatch.await(100L, TimeUnit.MILLISECONDS);
                boolean zAwait2 = countDownLatch2.await(100L, TimeUnit.MILLISECONDS);
                if (zAwait && zAwait2) {
                    return atomicBoolean.get();
                }
                Log.w(SystemStateHelper.class.getSimpleName(), "Timed out waiting for sensors: %b %b", new Object[]{Boolean.valueOf(zAwait), Boolean.valueOf(zAwait2)});
                return false;
            } catch (InterruptedException unused) {
            } finally {
                sensorManager.unregisterListener(sensorEventListener);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnterCarMode(int i, String str) {
        Log.i(this, "Entering carmode", new Object[0]);
        this.mIsCarMode = getSystemCarMode();
        Iterator<SystemStateListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCarModeChanged(i, str, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExitCarMode(int i, String str) {
        Log.i(this, "Exiting carmode", new Object[0]);
        this.mIsCarMode = getSystemCarMode();
        Iterator<SystemStateListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCarModeChanged(i, str, false);
        }
    }

    private boolean getSystemCarMode() {
        UiModeManager uiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 3;
    }
}
