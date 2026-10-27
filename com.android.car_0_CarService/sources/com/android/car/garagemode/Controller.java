package com.android.car.garagemode;

import android.app.job.JobScheduler;
import android.car.hardware.power.CarPowerManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import com.android.car.CarLocalServices;
import com.android.car.systeminterface.SystemInterface;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes3.dex */
public class Controller implements CarPowerManager.CarPowerStateListenerWithCompletion {
    private static final Logger LOG = new Logger("Controller");
    private CarPowerManager mCarPowerManager;
    private final Context mContext;
    private final GarageMode mGarageMode;
    private final Handler mHandler;
    final WakeupPolicy mWakeupPolicy;

    public Controller(Context context, Looper looper) {
        this(context, looper, null, null, null);
    }

    public Controller(Context context, Looper looper, WakeupPolicy wakeupPolicy, Handler handler, GarageMode garageMode) {
        this.mContext = context;
        this.mHandler = handler == null ? new Handler(looper) : handler;
        this.mWakeupPolicy = wakeupPolicy == null ? WakeupPolicy.initFromResources(context) : wakeupPolicy;
        this.mGarageMode = garageMode == null ? new GarageMode(this) : garageMode;
    }

    public void init() {
        CarPowerManager carPowerManagerCreateCarPowerManager = CarLocalServices.createCarPowerManager(this.mContext);
        this.mCarPowerManager = carPowerManagerCreateCarPowerManager;
        carPowerManagerCreateCarPowerManager.setListenerWithCompletion(this);
    }

    public void release() {
        this.mCarPowerManager.clearListener();
    }

    public void onStateChanged(int state, CompletableFuture<Void> future) {
        if (state == 2) {
            LOG.d("CPM state changed to SUSPEND_ENTER");
            handleSuspendEnter();
            return;
        }
        if (state == 3) {
            LOG.d("CPM state changed to SUSPEND_EXIT");
            handleSuspendExit();
            return;
        }
        if (state == 5) {
            LOG.d("CPM state changed to SHUTDOWN_ENTER");
            handleShutdownEnter();
        } else if (state == 7) {
            LOG.d("CPM state changed to SHUTDOWN_PREPARE");
            handleShutdownPrepare(future);
        } else if (state == 8) {
            LOG.d("CPM state changed to SHUTDOWN_CANCELLED");
            handleShutdownCancelled();
        }
    }

    boolean isGarageModeActive() {
        return this.mGarageMode.isGarageModeActive();
    }

    List<String> dump() {
        return this.mGarageMode.dump();
    }

    void sendBroadcast(Intent i) {
        SystemInterface systemInterface = (SystemInterface) CarLocalServices.getService(SystemInterface.class);
        LOG.d("Sending broadcast with action: " + i.getAction());
        systemInterface.sendBroadcastAsUser(i, UserHandle.ALL);
    }

    JobScheduler getJobSchedulerService() {
        return (JobScheduler) this.mContext.getSystemService("jobscheduler");
    }

    Handler getHandler() {
        return this.mHandler;
    }

    void initiateGarageMode(CompletableFuture<Void> future) {
        this.mWakeupPolicy.incrementCounter();
        this.mGarageMode.enterGarageMode(future);
    }

    void resetGarageMode() {
        this.mGarageMode.cancel();
        this.mWakeupPolicy.resetCounter();
    }

    void finishGarageMode() {
        this.mGarageMode.finish();
    }

    void setCarPowerManager(CarPowerManager cpm) {
        this.mCarPowerManager = cpm;
    }

    void scheduleNextWakeup() {
        if (this.mWakeupPolicy.getNextWakeUpInterval() <= 0) {
            return;
        }
        int seconds = this.mWakeupPolicy.getNextWakeUpInterval();
        this.mCarPowerManager.scheduleNextWakeupTime(seconds);
    }

    private void handleSuspendExit() {
        resetGarageMode();
    }

    private void handleSuspendEnter() {
        resetGarageMode();
    }

    private void handleShutdownEnter() {
        resetGarageMode();
    }

    private void handleShutdownPrepare(CompletableFuture<Void> future) {
        initiateGarageMode(future);
    }

    private void handleShutdownCancelled() {
        resetGarageMode();
    }
}
