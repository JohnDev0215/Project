package android.hardware.bydauto.audio.tsimpl;

import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
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
    private static final String TAG = "BYDCarServiceBase";
    private Car mCar;
    public Context mContext;
    public HelperHandler mHandler;
    public boolean mIsBinding;
    public boolean mManualDisconnected;
    public HandlerThread mThread;
    private Car.CarServiceLifecycleListener mCarServiceLifecycle = new Car.CarServiceLifecycleListener() { // from class: android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase.1
        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean z) {
            BYDCarServiceBase bYDCarServiceBase;
            BYDCarServiceBase bYDCarServiceBase2;
            if (z) {
                synchronized (BYDCarServiceBase.this) {
                    BYDCarServiceBase.this.mCar = car;
                    bYDCarServiceBase2 = BYDCarServiceBase.this;
                    bYDCarServiceBase2.mIsBinding = false;
                }
                bYDCarServiceBase2.mHandler.sendEmptyMessage(1);
                return;
            }
            synchronized (BYDCarServiceBase.this) {
                BYDCarServiceBase.this.mCar = null;
                bYDCarServiceBase = BYDCarServiceBase.this;
                bYDCarServiceBase.mIsBinding = false;
            }
            bYDCarServiceBase.mHandler.sendEmptyMessage(2);
        }
    };
    private LocalListenerHelper<IServiceConnectionListener> mListenerHelper = new LocalListenerHelper<>();

    public class HelperHandler extends Handler {
        public HelperHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Car car;
            Car car2;
            int i = message.what;
            if (i == 1) {
                synchronized (BYDCarServiceBase.this) {
                    car = BYDCarServiceBase.this.mCar;
                }
                if (car == null) {
                    return;
                }
                BYDCarServiceBase.this.onServiceConnected(car);
                BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: a.b.e.a.a.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IServiceConnectionListener) obj).onServiceConnected(true);
                    }
                });
                return;
            }
            if (i == 2) {
                BYDCarServiceBase.this.onServiceDied();
                BYDCarServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: a.b.e.a.a.b
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((IServiceConnectionListener) obj).onServiceDisconnected(false);
                    }
                });
                if (BYDCarServiceBase.this.mManualDisconnected) {
                    return;
                }
                BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, (int) (Math.random() * 1000.0d));
                return;
            }
            if (i == 3) {
                IServiceConnectionListener iServiceConnectionListener = (IServiceConnectionListener) message.obj;
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
                    Log.w(BYDCarServiceBase.TAG, "unrecognized handler what:" + message.what);
                    return;
                }
                BYDCarServiceBase bYDCarServiceBase = BYDCarServiceBase.this;
                if (bYDCarServiceBase.mIsBinding || bYDCarServiceBase.mManualDisconnected) {
                    return;
                }
                bYDCarServiceBase.bindService();
                return;
            }
            synchronized (BYDCarServiceBase.this) {
                BYDCarServiceBase bYDCarServiceBase2 = BYDCarServiceBase.this;
                if (bYDCarServiceBase2.mIsBinding) {
                    Car carCreateCar = Car.createCar(bYDCarServiceBase2.mContext, bYDCarServiceBase2.mHandler, 200000L, bYDCarServiceBase2.mCarServiceLifecycle);
                    if (carCreateCar == null) {
                        BYDCarServiceBase.this.mHandler.sendEmptyMessageDelayed(5, (int) (Math.random() * 1000.0d));
                    } else {
                        synchronized (BYDCarServiceBase.this) {
                            BYDCarServiceBase.this.mCar = carCreateCar;
                            BYDCarServiceBase.this.mIsBinding = false;
                        }
                    }
                }
            }
        }
    }

    public BYDCarServiceBase(Context context) {
        this.mContext = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        HelperHandler helperHandler = new HelperHandler(this.mThread.getLooper());
        this.mHandler = helperHandler;
        helperHandler.sendEmptyMessage(5);
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

    public Car getCar() {
        return this.mCar;
    }

    @Override // android.hardware.bydauto.audio.tsimpl.IBydService
    public boolean isServiceConnected() {
        Car car = this.mCar;
        return car != null && car.isConnected();
    }

    public abstract void onServiceConnected(Car car);

    public abstract void onServiceDied();

    @Override // android.hardware.bydauto.audio.tsimpl.IBydService
    public boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener) {
        boolean zAddListener = this.mListenerHelper.addListener(iServiceConnectionListener);
        if (iServiceConnectionListener != null && isServiceConnected()) {
            Message messageObtainMessage = this.mHandler.obtainMessage(3);
            messageObtainMessage.obj = iServiceConnectionListener;
            messageObtainMessage.sendToTarget();
        }
        return zAddListener;
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
    public boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener) {
        return this.mListenerHelper.removeListener(iServiceConnectionListener);
    }
}
