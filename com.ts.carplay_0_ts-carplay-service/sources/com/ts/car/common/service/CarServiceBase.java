package com.ts.car.common.service;

import android.car.Car;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.car.common.listener.LocalListenerHelper;
import java.util.function.Consumer;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarServiceBase implements IPlatformService {
    private static final int MAX_CREATE_CAR_MS = 200;
    private static final int MESSAGE_LISTENER_ADDED = 3;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CONNECTED = 1;
    private static final int MESSAGE_SERVICE_CREATE_CAR = 4;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final String TAG = CarServiceBase.class.getSimpleName();
    private Car mCar;
    protected Context mContext;
    protected HelperHandler mHandler;
    protected boolean mIsBinding;
    protected boolean mManualDisconnected;
    protected HandlerThread mThread;
    private Car.CarServiceLifecycleListener mCarServiceLifecycle = new Car.CarServiceLifecycleListener() { // from class: com.ts.car.common.service.CarServiceBase.1
        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean ready) {
            if (ready) {
                synchronized (CarServiceBase.this) {
                    CarServiceBase.this.mCar = car;
                    CarServiceBase.this.mIsBinding = false;
                }
                CarServiceBase.this.mHandler.sendEmptyMessage(1);
                return;
            }
            synchronized (CarServiceBase.this) {
                CarServiceBase.this.mCar = null;
                CarServiceBase.this.mIsBinding = false;
            }
            CarServiceBase.this.mHandler.sendEmptyMessage(2);
        }
    };
    private LocalListenerHelper<IServiceConnectionListener> mListenerHelper = new LocalListenerHelper<>();

    protected abstract void onServiceConnected(Car car);

    protected abstract void onServiceDied();

    public CarServiceBase(Context context) {
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
            if (this.mCar != null && this.mCar.isConnected()) {
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

    @Override // ts.car.service.IPlatformService
    public boolean isServiceConnected() {
        Car car = this.mCar;
        return car != null && car.isConnected();
    }

    @Override // ts.car.service.IPlatformService
    public boolean registerConnectionListener(IServiceConnectionListener listener) {
        boolean res = this.mListenerHelper.addListener(listener);
        if (listener != null && isServiceConnected()) {
            Message message = this.mHandler.obtainMessage(3);
            message.obj = listener;
            message.sendToTarget();
        }
        return res;
    }

    @Override // ts.car.service.IPlatformService
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
                synchronized (CarServiceBase.this) {
                    car = CarServiceBase.this.mCar;
                }
                if (car == null) {
                    return;
                }
                CarServiceBase.this.onServiceConnected(car);
                CarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$CarServiceBase$HelperHandler$NFyH2BNrsVuzl2wrGIjufbk1LY4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$handleMessage$0$CarServiceBase$HelperHandler((IServiceConnectionListener) obj);
                    }
                });
                return;
            }
            if (i == 2) {
                CarServiceBase.this.onServiceDied();
                CarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$CarServiceBase$HelperHandler$7Fjc3u4m6op3mlgKSfTfxHk78tM
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$handleMessage$1$CarServiceBase$HelperHandler((IServiceConnectionListener) obj);
                    }
                });
                if (!CarServiceBase.this.mManualDisconnected) {
                    int randTime = (int) (Math.random() * 500.0d);
                    CarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, randTime);
                    return;
                }
                return;
            }
            if (i == 3) {
                IServiceConnectionListener listener = (IServiceConnectionListener) msg.obj;
                synchronized (CarServiceBase.this) {
                    car2 = CarServiceBase.this.mCar;
                }
                if (car2 != null && CarServiceBase.this.mListenerHelper.containsListener(listener)) {
                    listener.onServiceConnected(CarServiceBase.this);
                    return;
                }
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    Log.w(CarServiceBase.TAG, "unrecognized handler what:" + msg.what);
                    return;
                }
                if (CarServiceBase.this.mIsBinding || CarServiceBase.this.mManualDisconnected) {
                    return;
                }
                CarServiceBase.this.bindService();
                return;
            }
            synchronized (CarServiceBase.this) {
                if (CarServiceBase.this.mIsBinding) {
                    Car car3 = Car.createCar(CarServiceBase.this.mContext, CarServiceBase.this.mHandler, 200L, CarServiceBase.this.mCarServiceLifecycle);
                    if (car3 != null) {
                        synchronized (CarServiceBase.this) {
                            CarServiceBase.this.mCar = car3;
                            CarServiceBase.this.mIsBinding = false;
                        }
                        return;
                    }
                    int randTime2 = (int) (Math.random() * 500.0d);
                    CarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, randTime2);
                }
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$CarServiceBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceConnected(CarServiceBase.this);
        }

        public /* synthetic */ void lambda$handleMessage$1$CarServiceBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceDisconnected(CarServiceBase.this);
        }
    }
}
