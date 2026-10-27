package ts.car.usb.utils;

import android.os.Handler;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class AutoServiceConnector4Hidl<T extends IHwInterface> implements IHwBinder.DeathRecipient {
    private static final int CONNECT_INTERVAL = 2000;
    private Handler mHandler;
    private T mService;

    protected abstract T createService();

    protected abstract void onServiceConnected();

    protected abstract void onServiceDisconnected();

    protected AutoServiceConnector4Hidl() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.mHandler = new Handler();
        onInit();
        autoConnect();
    }

    protected void autoConnect() {
        if (this.mService != null) {
            return;
        }
        T t = (T) createService();
        if (t == null) {
            this.mHandler.postDelayed(new Runnable() { // from class: ts.car.usb.utils.-$$Lambda$HIzZY9zWAD28w8OXh9YafKpKZ4I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.autoConnect();
                }
            }, 2000L);
            return;
        }
        t.asBinder().linkToDeath(this, 0L);
        this.mService = t;
        onServiceConnected();
    }

    protected final T getService() {
        if (this.mService == null) {
            autoConnect();
        }
        return this.mService;
    }

    protected void onInit() {
    }

    public void serviceDied(long cookie) {
        this.mService.asBinder().unlinkToDeath(this);
        this.mService = null;
        onServiceDisconnected();
        autoConnect();
    }
}
