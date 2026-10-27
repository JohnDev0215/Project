package android.os;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Build;
import android.os.ITwilightCallback;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CarTwilightManager {
    private static final String TAG = "CarTwilightManager";
    private ICarTwilightManager mCarTwilightManagerService;
    private Context mContext;
    private List<TwilightChangeListener> mTwilightChangeListener = new ArrayList();
    private final ITwilightCallback mTwilightCallback = new ITwilightCallback.Stub() { // from class: android.os.CarTwilightManager.1
        @Override // android.os.ITwilightCallback
        public void onTwilightState(int i) {
            CarTwilightManager.this.callbackTwilightChanged(i);
        }
    };

    public interface TwilightChangeListener {
        void onTwilightChanged(int i);
    }

    public CarTwilightManager(Context context, ICarTwilightManager iCarTwilightManager) {
        this.mContext = context;
        this.mCarTwilightManagerService = iCarTwilightManager;
        subscribeTwilightCallback();
    }

    @SystemApi
    public void subscribeTwilightCallback() {
        ICarTwilightManager iCarTwilightManager = this.mCarTwilightManagerService;
        if (iCarTwilightManager == null) {
            Log.e(TAG, "mCarTwilightManagerService is null");
            return;
        }
        try {
            iCarTwilightManager.subscribeTwilightCallback(this.mTwilightCallback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void unsubscribeTwilightCallback() {
        ICarTwilightManager iCarTwilightManager = this.mCarTwilightManagerService;
        if (iCarTwilightManager == null) {
            Log.e(TAG, "mCarTwilightManagerService is null");
            return;
        }
        try {
            iCarTwilightManager.unsubscribeTwilightCallback(this.mTwilightCallback);
        } catch (RemoteException e) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void subscribeTwilightChangeListener(TwilightChangeListener twilightChangeListener) {
        synchronized (this.mTwilightChangeListener) {
            if (!this.mTwilightChangeListener.contains(twilightChangeListener) && twilightChangeListener != null) {
                this.mTwilightChangeListener.add(twilightChangeListener);
                Log.i(TAG, "subscribeTwilightChangeListener ok --> listener size: " + this.mTwilightChangeListener.size());
            }
        }
    }

    public void unSubscribeTwilightChangeListener(TwilightChangeListener twilightChangeListener) {
        synchronized (this.mTwilightChangeListener) {
            if (twilightChangeListener != null) {
                if (this.mTwilightChangeListener.contains(twilightChangeListener)) {
                    this.mTwilightChangeListener.remove(twilightChangeListener);
                    Log.i(TAG, "unSubscribeTwilightChangeListener ok --> listener size: " + this.mTwilightChangeListener.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SystemApi
    public void callbackTwilightChanged(int i) {
        Iterator<TwilightChangeListener> it = this.mTwilightChangeListener.iterator();
        while (it.hasNext()) {
            it.next().onTwilightChanged(i);
        }
    }

    @SystemApi
    public boolean isNight() {
        ICarTwilightManager iCarTwilightManager = this.mCarTwilightManagerService;
        if (iCarTwilightManager == null) {
            Log.e(TAG, "mCarTwilightManagerService is null");
            return false;
        }
        try {
            return iCarTwilightManager.isNight();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public long getSunriseTimeMillis() {
        ICarTwilightManager iCarTwilightManager = this.mCarTwilightManagerService;
        if (iCarTwilightManager == null) {
            Log.e(TAG, "mCarTwilightManagerService is null");
            return -1L;
        }
        try {
            return iCarTwilightManager.getSunriseTimeMillis();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public long getSunsetTimeMillis() {
        ICarTwilightManager iCarTwilightManager = this.mCarTwilightManagerService;
        if (iCarTwilightManager == null) {
            Log.e(TAG, "mCarTwilightManagerService is null");
            return -1L;
        }
        try {
            return iCarTwilightManager.getSunsetTimeMillis();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
