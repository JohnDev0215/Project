package com.ts.car.timesync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import com.ts.car.timesync.utils.DateUtils;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.assist.CarVendorAssistManager;

/* JADX INFO: loaded from: classes.dex */
public class McuClockSource extends ClockSourceBase {
    private static final int CONNECT_INTERVAL = 1000;
    private static final String IN_OUT_DAY_LIGHT_TIME_INTENT = "android.intent.action.dayLight.time.status";
    private static final int SYNC_TIME_WITH_MCU_PERIOD = 600000;
    private static final String TAG = "McuClockSource";
    private static final int TYPE = 6;
    private CarConnectionCallback carConnectionCallback;
    private Car mCarApiClient;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private CarVendorAssistManager mManager;
    private boolean reconnectionCarservice;
    private Runnable syncTimeRunnable;

    public McuClockSource(Context context) {
        super(context);
        this.syncTimeRunnable = new Runnable() { // from class: com.ts.car.timesync.McuClockSource.2
            @Override // java.lang.Runnable
            public void run() {
                McuClockSource.this.startSyncTimeToMcu();
                LogUtils.info(McuClockSource.TAG, "periodically sync time to mcu start.");
                McuClockSource.this.setTime(System.currentTimeMillis());
            }
        };
        this.carConnectionCallback = new AnonymousClass3();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("McuClockSource_handlerthread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        start();
        registerDayLightTimeBroadcast();
    }

    private void registerDayLightTimeBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IN_OUT_DAY_LIGHT_TIME_INTENT);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.ts.car.timesync.McuClockSource.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                McuClockSource.this.setTime(System.currentTimeMillis());
                LogUtils.warning(McuClockSource.TAG, "receive dayLight Time Broadcast to set current time : " + System.currentTimeMillis());
            }
        };
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start() {
        LogUtils.debug(TAG, "start()");
        Car carCreateCar = Car.createCar(this.mContext, this.carConnectionCallback);
        this.mCarApiClient = carCreateCar;
        carCreateCar.connect();
    }

    public void setTime(long time) {
        try {
            if (this.mManager != null) {
                this.mManager.setRtcTime(DateUtils.millisToFormatDate(time));
                LogUtils.info(TAG, "setTime to mcu success");
            } else {
                this.reconnectionCarservice = true;
                LogUtils.warning(TAG, "CarVendorAssistManager is null!");
                this.mHandler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$MwSW6Gms7oVUYaiwaIi6yN_5uVw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.start();
                    }
                }, 1000L);
            }
        } catch (CarNotConnectedException ex) {
            this.reconnectionCarservice = true;
            resetCarManager();
            LogUtils.warning(TAG, "CarNotConnectedException!", ex);
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$MwSW6Gms7oVUYaiwaIi6yN_5uVw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.start();
                }
            }, 1000L);
        } catch (Throwable ex2) {
            this.reconnectionCarservice = true;
            resetCarManager();
            LogUtils.warning(TAG, "Maybe binder died, reconnect and retry!", ex2);
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$MwSW6Gms7oVUYaiwaIi6yN_5uVw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.start();
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startSyncTimeToMcu() {
        this.mHandler.removeCallbacks(this.syncTimeRunnable);
        this.mHandler.postDelayed(this.syncTimeRunnable, 600000L);
    }

    /* JADX INFO: renamed from: com.ts.car.timesync.McuClockSource$3, reason: invalid class name */
    class AnonymousClass3 extends CarConnectionCallback {
        AnonymousClass3() {
        }

        @Override // com.ts.lib.car.CarConnectionCallback
        public void onConnected(Car car) {
            LogUtils.debug(McuClockSource.TAG, "onServiceConnected(): car service has conneted!");
            try {
                McuClockSource.this.mManager = (CarVendorAssistManager) car.getCarManager(Car.VENDOR_ASSIST_SERVICE);
                LogUtils.debug(McuClockSource.TAG, "CarVendorAssistManager=" + McuClockSource.this.mManager);
                try {
                    if (McuClockSource.this.mManager != null) {
                        if (McuClockSource.this.reconnectionCarservice) {
                            McuClockSource.this.setTime(System.currentTimeMillis());
                            McuClockSource.this.reconnectionCarservice = false;
                            LogUtils.debug(McuClockSource.TAG, "setTime to currentTimeMillis , reconnectionCarservice is" + McuClockSource.this.reconnectionCarservice);
                        }
                        McuClockSource.this.setTime(System.currentTimeMillis());
                        McuClockSource.this.startSyncTimeToMcu();
                        return;
                    }
                    LogUtils.warning(McuClockSource.TAG, "CarVendorAssistManager is null!");
                    Handler handler = McuClockSource.this.mHandler;
                    final McuClockSource mcuClockSource = McuClockSource.this;
                    handler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$3$S-eTgngMoq9Y0eNFdODP4D0jWQw
                        @Override // java.lang.Runnable
                        public final void run() {
                            mcuClockSource.start();
                        }
                    }, 1000L);
                } catch (Throwable ex) {
                    Handler handler2 = McuClockSource.this.mHandler;
                    final McuClockSource mcuClockSource2 = McuClockSource.this;
                    handler2.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$3$B2TK96hOxv65CJ_vExg0AnKfjRw
                        @Override // java.lang.Runnable
                        public final void run() {
                            mcuClockSource2.start();
                        }
                    }, 1000L);
                    LogUtils.warning(McuClockSource.TAG, "maybe binder died!", ex);
                }
            } catch (CarNotConnectedException ex2) {
                Handler handler3 = McuClockSource.this.mHandler;
                final McuClockSource mcuClockSource3 = McuClockSource.this;
                handler3.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$3$EKQotSnaWoZz4xcD6Ot4bdlbies
                    @Override // java.lang.Runnable
                    public final void run() {
                        mcuClockSource3.start();
                    }
                }, 1000L);
                LogUtils.warning(McuClockSource.TAG, "CarNotConnectedException!", ex2);
            }
        }

        @Override // com.ts.lib.car.CarConnectionCallback
        public void onDisconnected(Car car) {
            LogUtils.warning(McuClockSource.TAG, "onServiceDisconnected(): car service has disconneted!");
            McuClockSource.this.resetCarManager();
            Handler handler = McuClockSource.this.mHandler;
            final McuClockSource mcuClockSource = McuClockSource.this;
            handler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$McuClockSource$3$ts6s1DQCyjJiHREUGOQUjYhlfxU
                @Override // java.lang.Runnable
                public final void run() {
                    mcuClockSource.start();
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCarManager() {
        this.mManager = null;
    }
}
