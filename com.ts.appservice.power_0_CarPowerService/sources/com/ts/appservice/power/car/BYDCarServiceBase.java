package com.ts.appservice.power.car;

import android.car.Car;
import android.content.Context;
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
    private static final int RANDOM_RETRY_INTERVAL = 1000;
    private static final String TAG = BYDCarServiceBase.class.getSimpleName();
    private Car mCar;
    protected Context mContext;
    protected HelperHandler mHandler;
    protected boolean mIsBinding;
    protected boolean mManualDisconnected;
    protected HandlerThread mThread;
    private Car.CarServiceLifecycleListener mCarServiceLifecycle = new Car.CarServiceLifecycleListener() { // from class: com.ts.appservice.power.car.BYDCarServiceBase.1
        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean ready) {
            Log.e(BYDCarServiceBase.TAG, "onLifecycleChanged ready car " + car + " ready=" + ready);
            if (ready) {
                synchronized (BYDCarServiceBase.this) {
                    BYDCarServiceBase.this.mCar = car;
                    BYDCarServiceBase.this.mIsBinding = false;
                }
                BYDCarServiceBase.this.mHandler.removeMessages(1);
                BYDCarServiceBase.this.mHandler.sendEmptyMessage(1);
                return;
            }
            synchronized (BYDCarServiceBase.this) {
                BYDCarServiceBase.this.mCar = null;
                BYDCarServiceBase.this.mIsBinding = false;
            }
            BYDCarServiceBase.this.mHandler.removeMessages(2);
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
        Log.w(TAG, "bindService");
        synchronized (this) {
            if (this.mCar != null && this.mCar.isConnected()) {
                Log.w(TAG, "mCar.isConnected()");
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
            if (this.mCar != null && this.mCar.isConnected()) {
                if (this.mIsBinding) {
                    return false;
                }
                this.mManualDisconnected = true;
                return true;
            }
            return true;
        }
    }

    @Override // com.ts.appservice.power.car.IBydService
    public boolean isServiceConnected() {
        Car car = this.mCar;
        return car != null && car.isConnected();
    }

    @Override // com.ts.appservice.power.car.IBydService
    public boolean registerConnectionListener(IServiceConnectionListener listener) {
        boolean res = this.mListenerHelper.addListener(listener);
        if (listener != null && isServiceConnected()) {
            Message message = this.mHandler.obtainMessage(3);
            message.obj = listener;
            message.sendToTarget();
        }
        return res;
    }

    @Override // com.ts.appservice.power.car.IBydService
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
                BYDCarServiceBase.this.onServiceConnected(car);
                BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.appservice.power.car.-$$Lambda$BYDCarServiceBase$HelperHandler$M_ubY_qD-mLyRFHEKo349SXg2Ko
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IServiceConnectionListener) obj).onServiceConnected(true);
                    }
                });
                return;
            }
            if (i == 2) {
                Log.w(BYDCarServiceBase.TAG, "MESSAGE_SERVICE_DISCONNECTED " + BYDCarServiceBase.this.mManualDisconnected);
                BYDCarServiceBase.this.onServiceDied();
                BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.appservice.power.car.-$$Lambda$BYDCarServiceBase$HelperHandler$RAJfsk3JporaW0NMfJ-o2TxMS9A
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IServiceConnectionListener) obj).onServiceDisconnected(false);
                    }
                });
                if (!BYDCarServiceBase.this.mManualDisconnected) {
                    Log.w(BYDCarServiceBase.TAG, "MESSAGE_SERVICE_DISCONNECTED RANDOM_RETRY_INTERVAL=1000");
                    BYDCarServiceBase.this.mHandler.removeMessages(5);
                    BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, 1000L);
                    return;
                }
                return;
            }
            if (i == 3) {
                IServiceConnectionListener listener = (IServiceConnectionListener) msg.obj;
                synchronized (BYDCarServiceBase.this) {
                    car2 = BYDCarServiceBase.this.mCar;
                }
                if (car2 != null && BYDCarServiceBase.this.mListenerHelper.containsListener(listener)) {
                    listener.onServiceConnected(true);
                    return;
                }
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    Log.w(BYDCarServiceBase.TAG, "unrecognized handler what:" + msg.what);
                    return;
                }
                if (BYDCarServiceBase.this.mIsBinding || BYDCarServiceBase.this.mManualDisconnected) {
                    return;
                }
                BYDCarServiceBase.this.bindService();
                return;
            }
            synchronized (BYDCarServiceBase.this) {
                if (!BYDCarServiceBase.this.mIsBinding) {
                    Log.w(BYDCarServiceBase.TAG, "MESSAGE_SERVICE_CREATE_CAR mIsBinding :" + BYDCarServiceBase.this.mIsBinding);
                    return;
                }
                Car car3 = Car.createCar(BYDCarServiceBase.this.mContext, BYDCarServiceBase.this.mHandler, 200000L, BYDCarServiceBase.this.mCarServiceLifecycle);
                Log.w(BYDCarServiceBase.TAG, "MESSAGE_SERVICE_CREATE_CAR :" + car3 + "isConnected= " + car3.isConnected());
                if (car3 != null) {
                    synchronized (BYDCarServiceBase.this) {
                        BYDCarServiceBase.this.mCar = car3;
                        BYDCarServiceBase.this.mIsBinding = false;
                    }
                    return;
                }
                Log.w(BYDCarServiceBase.TAG, "restart RANDOM_RETRY_INTERVAL:1000");
                BYDCarServiceBase.this.mHandler.removeMessages(5);
                BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, 1000L);
            }
        }
    }
}
