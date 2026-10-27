package android.car;

import android.car.drivingstate.XCarUxRestrictionsManager;
import android.car.media.XCarMediaManager;
import android.car.utils.Logs;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.TransactionTooLargeException;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class XCar {
    public static final String CAR_INTENT_ACTION_MEDIA_TEMPLATE = "android.car.intent.action.MEDIA_TEMPLATE";
    public static final String CAR_MEDIA_SERVICE = "car_media";
    public static final String CAR_UX_RESTRICTION_SERVICE = "uxrestriction";
    public static final long CAR_WAIT_TIMEOUT_DO_NOT_WAIT = 0;
    public static final String PACKAGE_SERVICE = "package";
    public static final String TAG_CAR = "XCar";
    private final Context mContext;
    private final Handler mEventHandler;
    private final Handler mMainThreadEventHandler;
    private final Object mLock = new Object();
    private final HashMap<String, XCarManagerBase> mServiceMap = new HashMap<>();

    private XCar(Context context, Handler handler) {
        this.mContext = context;
        Handler handlerDetermineEventHandler = determineEventHandler(handler);
        this.mEventHandler = handlerDetermineEventHandler;
        this.mMainThreadEventHandler = determineMainThreadEventHandler(handlerDetermineEventHandler);
    }

    public static XCar createCar(Context context) {
        return new XCar(context, null);
    }

    private XCarManagerBase createCarManagerLocked(String str) {
        XCarManagerBase xCarMediaManager;
        Objects.requireNonNull(str);
        if (str.equals(CAR_MEDIA_SERVICE)) {
            xCarMediaManager = new XCarMediaManager(this, null);
        } else {
            if (!str.equals(CAR_UX_RESTRICTION_SERVICE)) {
                return null;
            }
            xCarMediaManager = new XCarUxRestrictionsManager(this, null);
        }
        return xCarMediaManager;
    }

    private static Handler determineEventHandler(Handler handler) {
        return handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }

    private static Handler determineMainThreadEventHandler(Handler handler) {
        Looper mainLooper = Looper.getMainLooper();
        return handler.getLooper() == mainLooper ? handler : new Handler(mainLooper);
    }

    private void tearDownCarManagersLocked() {
        Iterator<XCarManagerBase> it = this.mServiceMap.values().iterator();
        while (it.hasNext()) {
            it.next().onCarDisconnected();
        }
        this.mServiceMap.clear();
    }

    public void disconnect() {
        tearDownCarManagersLocked();
    }

    public Object getCarManager(String str) {
        XCarManagerBase xCarManagerBaseCreateCarManagerLocked;
        Logs.d(TAG_CAR, "getCarManager serviceName: " + str);
        synchronized (this.mLock) {
            xCarManagerBaseCreateCarManagerLocked = this.mServiceMap.get(str);
            if (xCarManagerBaseCreateCarManagerLocked == null) {
                try {
                    xCarManagerBaseCreateCarManagerLocked = createCarManagerLocked(str);
                    if (xCarManagerBaseCreateCarManagerLocked != null) {
                        this.mServiceMap.put(str, xCarManagerBaseCreateCarManagerLocked);
                    } else {
                        Log.e(TAG_CAR, "getCarManager could not create manager for service: " + str);
                    }
                } catch (Exception unused) {
                    Log.e(TAG_CAR, "getCarManager catch error");
                }
            }
        }
        if (xCarManagerBaseCreateCarManagerLocked == null) {
            Log.d(TAG_CAR, "getCarManager serviceName: " + str + " manager is null ");
        }
        return xCarManagerBaseCreateCarManagerLocked;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Handler getEventHandler() {
        return this.mEventHandler;
    }

    public <T> T handleRemoteExceptionFromCarService(Exception exc, T t) {
        handleRemoteExceptionFromCarService(exc);
        return t;
    }

    public void handleRemoteExceptionFromCarService(Exception exc) {
        if (exc instanceof TransactionTooLargeException) {
            Log.w(TAG_CAR, "Car service threw TransactionTooLargeException", exc);
            throw new XCarTransactionException(exc, "Car service threw TransactionTooLargException", new Object[0]);
        }
        Log.w(TAG_CAR, "Car service has crashed", exc);
    }
}
