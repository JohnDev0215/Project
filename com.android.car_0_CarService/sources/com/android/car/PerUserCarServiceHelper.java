package com.android.car;

import android.car.IPerUserCarService;
import android.car.user.CarUserManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;
import com.android.car.user.CarUserService;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PerUserCarServiceHelper implements CarServiceBase {
    private static boolean DBG = false;
    private static final String EXTRA_USER_HANDLE = "android.intent.extra.user_handle";
    private static final String TAG = "PerUserCarSvcHelper";
    private final Context mContext;
    private IPerUserCarService mPerUserCarService;
    private final CarUserService mUserService;
    private final Object mServiceBindLock = new Object();
    private boolean mBound = false;
    private final CarUserManager.UserLifecycleListener mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.-$$Lambda$PerUserCarServiceHelper$ItIbUR-YJft6O_3UMzXgGyjUm7w
        public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
            this.f$0.lambda$new$0$PerUserCarServiceHelper(userLifecycleEvent);
        }
    };
    private final ServiceConnection mUserServiceConnection = new ServiceConnection() { // from class: com.android.car.PerUserCarServiceHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            List<ServiceCallback> callbacks;
            if (PerUserCarServiceHelper.DBG) {
                Log.d(PerUserCarServiceHelper.TAG, "Connected to User Service");
            }
            PerUserCarServiceHelper.this.mPerUserCarService = IPerUserCarService.Stub.asInterface(service);
            if (PerUserCarServiceHelper.this.mPerUserCarService != null) {
                synchronized (PerUserCarServiceHelper.this.mServiceBindLock) {
                    callbacks = new ArrayList<>(PerUserCarServiceHelper.this.mServiceCallbacks);
                }
                for (ServiceCallback callback : callbacks) {
                    callback.onServiceConnected(PerUserCarServiceHelper.this.mPerUserCarService);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            List<ServiceCallback> callbacks;
            if (PerUserCarServiceHelper.DBG) {
                Log.d(PerUserCarServiceHelper.TAG, "Disconnected from User Service");
            }
            synchronized (PerUserCarServiceHelper.this.mServiceBindLock) {
                callbacks = new ArrayList<>(PerUserCarServiceHelper.this.mServiceCallbacks);
            }
            for (ServiceCallback callback : callbacks) {
                callback.onServiceDisconnected();
            }
        }
    };
    private List<ServiceCallback> mServiceCallbacks = new ArrayList();

    public interface ServiceCallback {
        void onPreUnbind();

        void onServiceConnected(IPerUserCarService iPerUserCarService);

        void onServiceDisconnected();
    }

    public PerUserCarServiceHelper(Context context, CarUserService userService) {
        this.mContext = context;
        this.mUserService = userService;
        userService.addUserLifecycleListener(this.mUserLifecycleListener);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mServiceBindLock) {
            bindToPerUserCarService();
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mServiceBindLock) {
            unbindFromPerUserCarService();
            this.mUserService.removeUserLifecycleListener(this.mUserLifecycleListener);
        }
    }

    public /* synthetic */ void lambda$new$0$PerUserCarServiceHelper(CarUserManager.UserLifecycleEvent event) {
        List<ServiceCallback> callbacks;
        if (DBG) {
            Log.d(TAG, "onEvent(" + event + ")");
        }
        if (2 == event.getEventType()) {
            int userId = event.getUserId();
            if (DBG) {
                Log.d(TAG, "User Switch Happened. New User" + userId);
            }
            synchronized (this.mServiceBindLock) {
                callbacks = new ArrayList<>(this.mServiceCallbacks);
            }
            for (ServiceCallback callback : callbacks) {
                callback.onPreUnbind();
            }
            unbindFromPerUserCarService();
            bindToPerUserCarService();
        }
    }

    private void bindToPerUserCarService() {
        if (DBG) {
            Log.d(TAG, "Binding to User service");
        }
        Intent startIntent = new Intent(this.mContext, (Class<?>) PerUserCarService.class);
        synchronized (this.mServiceBindLock) {
            this.mBound = true;
            boolean bindSuccess = this.mContext.bindServiceAsUser(startIntent, this.mUserServiceConnection, 1, UserHandle.CURRENT);
            if (!bindSuccess) {
                Log.e(TAG, "bindToPerUserCarService() failed to get valid connection");
                unbindFromPerUserCarService();
            }
        }
    }

    private void unbindFromPerUserCarService() {
        synchronized (this.mServiceBindLock) {
            if (this.mBound) {
                if (DBG) {
                    Log.d(TAG, "Unbinding from User Service");
                }
                this.mContext.unbindService(this.mUserServiceConnection);
                this.mBound = false;
            }
        }
    }

    public void registerServiceCallback(ServiceCallback listener) {
        if (listener != null) {
            if (DBG) {
                Log.d(TAG, "Registering PerUserCarService Listener");
            }
            synchronized (this.mServiceBindLock) {
                this.mServiceCallbacks.add(listener);
            }
        }
    }

    public void unregisterServiceCallback(ServiceCallback listener) {
        if (DBG) {
            Log.d(TAG, "Unregistering PerUserCarService Listener");
        }
        if (listener != null) {
            synchronized (this.mServiceBindLock) {
                this.mServiceCallbacks.remove(listener);
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public synchronized void dump(PrintWriter writer) {
    }
}
