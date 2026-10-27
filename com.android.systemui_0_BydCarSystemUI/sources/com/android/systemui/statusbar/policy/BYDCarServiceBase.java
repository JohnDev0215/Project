package com.android.systemui.statusbar.policy;

import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.audio.tsimpl.IBydService;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDCarServiceBase implements IBydService {
    private static final int MAX_CREATE_CAR_MS = 200000;
    private static final int MESSAGE_LISTENER_ADDED = 3;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CONNECTED = 1;
    private static final int MESSAGE_SERVICE_CREATE_CAR = 4;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final String TAG = "BYDCarServiceBase";
    private Car mCar;
    protected Context mContext;
    protected HelperHandler mHandler;
    protected boolean mIsBinding;
    protected boolean mManualDisconnected;
    protected HandlerThread mThread;
    private Car.CarServiceLifecycleListener mCarServiceLifecycle = new Car.CarServiceLifecycleListener() { // from class: com.android.systemui.statusbar.policy.BYDCarServiceBase.1
        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean ready) {
            if (ready) {
                synchronized (BYDCarServiceBase.this) {
                    BYDCarServiceBase.this.mCar = car;
                    BYDCarServiceBase.this.mIsBinding = false;
                }
                BYDCarServiceBase.this.mHandler.sendEmptyMessage(1);
                return;
            }
            synchronized (BYDCarServiceBase.this) {
                BYDCarServiceBase.this.mCar = null;
                BYDCarServiceBase.this.mIsBinding = false;
            }
            BYDCarServiceBase.this.mHandler.sendEmptyMessage(2);
        }
    };
    private LocalListenerHelper<IServiceConnectionListener> mListenerHelper = new LocalListenerHelper<>();

    protected abstract void onServiceConnected(Car car);

    protected abstract void onServiceDied();

    public BYDCarServiceBase(Context context) {
        this.mContext = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        HelperHandler helperHandler = new HelperHandler(this.mThread.getLooper());
        this.mHandler = helperHandler;
        helperHandler.sendEmptyMessage(5);
    }

    public Car getCar() {
        return this.mCar;
    }

    public boolean bindService() {
        synchronized (this) {
            Car car = this.mCar;
            if (car != null && car.isConnected()) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mIsBinding = true;
            this.mManualDisconnected = false;
            this.mHandler.sendEmptyMessage(4);
            return true;
        }
    }

    public boolean unbindService() {
        synchronized (this) {
            Car car = this.mCar;
            if (car != null && car.isConnected()) {
                if (this.mIsBinding) {
                    return false;
                }
                this.mManualDisconnected = true;
                return true;
            }
            return true;
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.IBydService
    public boolean isServiceConnected() {
        Car car = this.mCar;
        return car != null && car.isConnected();
    }

    @Override // android.hardware.bydauto.audio.tsimpl.IBydService
    public boolean registerConnectionListener(IServiceConnectionListener listener) {
        boolean zAddListener = this.mListenerHelper.addListener(listener);
        if (listener != null && isServiceConnected()) {
            Message messageObtainMessage = this.mHandler.obtainMessage(3);
            messageObtainMessage.obj = listener;
            messageObtainMessage.sendToTarget();
        }
        return zAddListener;
    }

    @Override // android.hardware.bydauto.audio.tsimpl.IBydService
    public boolean unregisterConnectionListener(IServiceConnectionListener listener) {
        return this.mListenerHelper.removeListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class HelperHandler extends Handler {
        public HelperHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Car car;
            Car car2;
            int i = msg.what;
            if (i == 1) {
                synchronized (BYDCarServiceBase.this) {
                    car = BYDCarServiceBase.this.mCar;
                }
                if (car == null) {
                    return;
                }
                try {
                    BYDCarServiceBase.this.onServiceConnected(car);
                    BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$BYDCarServiceBase$HelperHandler$ztlXHhQ3BLcTv2r4o8QWRR0MSyk
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((IServiceConnectionListener) obj).onServiceConnected(true);
                        }
                    });
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            if (i == 2) {
                BYDCarServiceBase.this.onServiceDied();
                BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$BYDCarServiceBase$HelperHandler$ILnP-dZcvuymQ0XCW3kbwlxBHwU
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IServiceConnectionListener) obj).onServiceDisconnected(false);
                    }
                });
                if (BYDCarServiceBase.this.mManualDisconnected) {
                    return;
                }
                BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, (int) (Math.random() * 500.0d));
                return;
            }
            if (i == 3) {
                IServiceConnectionListener iServiceConnectionListener = (IServiceConnectionListener) msg.obj;
                synchronized (BYDCarServiceBase.this) {
                    car2 = BYDCarServiceBase.this.mCar;
                }
                if (car2 == null || !BYDCarServiceBase.this.mListenerHelper.containsListener(iServiceConnectionListener)) {
                    return;
                }
                iServiceConnectionListener.onServiceConnected(true);
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    Log.w(BYDCarServiceBase.TAG, "unrecognized handler what:" + msg.what);
                    return;
                } else {
                    if (BYDCarServiceBase.this.mIsBinding || BYDCarServiceBase.this.mManualDisconnected) {
                        return;
                    }
                    BYDCarServiceBase.this.bindService();
                    return;
                }
            }
            synchronized (BYDCarServiceBase.this) {
                if (BYDCarServiceBase.this.mIsBinding) {
                    Car carCreateCar = Car.createCar(BYDCarServiceBase.this.mContext, BYDCarServiceBase.this.mHandler, 200000L, BYDCarServiceBase.this.mCarServiceLifecycle);
                    if (carCreateCar != null) {
                        synchronized (BYDCarServiceBase.this) {
                            BYDCarServiceBase.this.mCar = carCreateCar;
                            BYDCarServiceBase.this.mIsBinding = false;
                        }
                        return;
                    }
                    BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, (int) (Math.random() * 500.0d));
                }
            }
        }
    }
}
