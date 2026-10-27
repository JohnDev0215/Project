package com.android.car.setupwizardlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.car.setupwizardlib.IInitialLockSetupService;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class InitialLockSetupClient implements ServiceConnection {
    private static final long CONNECTION_TIMEOUT_MS = 5000;
    private static final String TAG = "InitialLockSetupClient";
    private Context mContext;
    private SaveLockAsyncTask mCurrentSaveLockTask;
    private ValidateLockAsyncTask mCurrentValidateLockTask;
    private boolean mCurrentlyBinding;
    private InitialLockListener mInitialLockListener;
    private IInitialLockSetupService mInitialLockSetupService;
    private boolean mTimedOut;
    private static final String INITIAL_LOCK_SERVICE_PACKAGE = "com.android.car.settings";
    private static final String INITIAL_LOCK_SERVICE_CLASS_NAME = "com.android.car.settings.setupservice.InitialLockSetupService";
    private static final Intent INITIAL_LOCK_SERVICE_INTENT = new Intent().setComponent(new ComponentName(INITIAL_LOCK_SERVICE_PACKAGE, INITIAL_LOCK_SERVICE_CLASS_NAME));
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: com.android.car.setupwizardlib.-$$Lambda$InitialLockSetupClient$BSWdPcP-ADAU0k-P07u1b0kLqgc
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.connectionTimeout();
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public InitialLockSetupClient(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void startConnection(InitialLockListener listener) {
        logVerbose("startConnection");
        this.mInitialLockListener = listener;
        if (this.mInitialLockSetupService != null || this.mCurrentlyBinding) {
            logVerbose("Unable to bind to initial setup service, already connected or connecting.");
            return;
        }
        this.mTimedOut = false;
        try {
            this.mHandler.postDelayed(this.mTimeoutRunnable, CONNECTION_TIMEOUT_MS);
            this.mCurrentlyBinding = this.mContext.bindService(INITIAL_LOCK_SERVICE_INTENT, this, 1);
        } catch (Exception e) {
            e.printStackTrace();
            this.mInitialLockListener.onConnectionAttemptFinished(false);
        }
        if (!this.mCurrentlyBinding) {
            logVerbose("Unable to bind to initial setup service");
        }
    }

    public void stopConnection() {
        logVerbose("stopConnection");
        if (this.mInitialLockSetupService == null) {
            logVerbose("Attempting to disconnect from service when not connected");
            return;
        }
        this.mContext.unbindService(this);
        this.mInitialLockSetupService = null;
        this.mInitialLockListener = null;
        this.mCurrentlyBinding = false;
    }

    public boolean isServiceConnected() {
        return false;
    }

    public void getLockConfigs() {
        LockConfigsAsyncTask lockConfigsAsyncTask = new LockConfigsAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService);
        lockConfigsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    public void checkValidPassword(byte[] password) {
        logVerbose("checkValidPassword");
        ValidateLockAsyncTask validateLockAsyncTask = this.mCurrentValidateLockTask;
        if (validateLockAsyncTask != null && validateLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mCurrentValidateLockTask.cancel(true);
        }
        ValidateLockAsyncTask validateLockAsyncTask2 = new ValidateLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 0);
        this.mCurrentValidateLockTask = validateLockAsyncTask2;
        validateLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, password);
    }

    public void checkValidPin(byte[] pin) {
        logVerbose("checkValidPin");
        ValidateLockAsyncTask validateLockAsyncTask = this.mCurrentValidateLockTask;
        if (validateLockAsyncTask != null && validateLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mCurrentValidateLockTask.cancel(true);
        }
        ValidateLockAsyncTask validateLockAsyncTask2 = new ValidateLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 1);
        this.mCurrentValidateLockTask = validateLockAsyncTask2;
        validateLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pin);
    }

    public void checkValidPattern(byte[] pattern) {
        logVerbose("checkValidPattern");
        ValidateLockAsyncTask validateLockAsyncTask = this.mCurrentValidateLockTask;
        if (validateLockAsyncTask != null && validateLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mCurrentValidateLockTask.cancel(true);
        }
        ValidateLockAsyncTask validateLockAsyncTask2 = new ValidateLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 2);
        this.mCurrentValidateLockTask = validateLockAsyncTask2;
        validateLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pattern);
    }

    public void saveLockPassword(byte[] password) {
        SaveLockAsyncTask saveLockAsyncTask = this.mCurrentSaveLockTask;
        if (saveLockAsyncTask != null && saveLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            Log.e(TAG, "Can't save multiple passwords at once");
            return;
        }
        SaveLockAsyncTask saveLockAsyncTask2 = new SaveLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 0);
        this.mCurrentSaveLockTask = saveLockAsyncTask2;
        saveLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, password);
    }

    public void saveLockPin(byte[] pin) {
        SaveLockAsyncTask saveLockAsyncTask = this.mCurrentSaveLockTask;
        if (saveLockAsyncTask != null && saveLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            Log.e(TAG, "Can't save multiple passwords at once");
            return;
        }
        SaveLockAsyncTask saveLockAsyncTask2 = new SaveLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 1);
        this.mCurrentSaveLockTask = saveLockAsyncTask2;
        saveLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pin);
    }

    public void saveLockPattern(byte[] pattern) {
        SaveLockAsyncTask saveLockAsyncTask = this.mCurrentSaveLockTask;
        if (saveLockAsyncTask != null && saveLockAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            Log.e(TAG, "Can't save multiple passwords at once");
            return;
        }
        SaveLockAsyncTask saveLockAsyncTask2 = new SaveLockAsyncTask(this.mInitialLockListener, this.mInitialLockSetupService, 2);
        this.mCurrentSaveLockTask = saveLockAsyncTask2;
        saveLockAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pattern);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        logVerbose("onServiceConnected");
        if (this.mTimedOut) {
            return;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mCurrentlyBinding = false;
        this.mInitialLockSetupService = IInitialLockSetupService.Stub.asInterface(service);
        InitialLockListener initialLockListener = this.mInitialLockListener;
        if (initialLockListener != null) {
            initialLockListener.onConnectionAttemptFinished(true);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        logVerbose("onServiceDisconnected");
        this.mCurrentlyBinding = false;
        this.mInitialLockSetupService = null;
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName name) {
        logVerbose("onNullBinding");
        if (this.mTimedOut) {
            return;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mCurrentlyBinding = false;
        InitialLockListener initialLockListener = this.mInitialLockListener;
        if (initialLockListener != null) {
            initialLockListener.onConnectionAttemptFinished(false);
        }
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName name) {
        logVerbose("onBindingDied");
        if (this.mTimedOut) {
            return;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mCurrentlyBinding = false;
        InitialLockListener initialLockListener = this.mInitialLockListener;
        if (initialLockListener != null) {
            initialLockListener.onConnectionAttemptFinished(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionTimeout() {
        logVerbose("connectionTimeout");
        InitialLockListener initialLockListener = this.mInitialLockListener;
        if (initialLockListener == null) {
            return;
        }
        initialLockListener.onConnectionAttemptFinished(false);
        this.mTimedOut = true;
    }

    private static class LockConfigsAsyncTask extends AsyncTask<Void, Void, Map<Integer, LockConfig>> {
        private WeakReference<InitialLockListener> mInitialLockListener;
        private WeakReference<IInitialLockSetupService> mInitialLockSetupService;

        LockConfigsAsyncTask(InitialLockListener initialLockListener, IInitialLockSetupService initialLockSetupService) {
            this.mInitialLockListener = new WeakReference<>(initialLockListener);
            this.mInitialLockSetupService = new WeakReference<>(initialLockSetupService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Map<Integer, LockConfig> doInBackground(Void... voids) {
            IInitialLockSetupService initialLockSetupService = this.mInitialLockSetupService.get();
            if (initialLockSetupService == null) {
                InitialLockSetupClient.logVerbose("Lost reference to service in LockConfigsAsyncTask");
                return null;
            }
            try {
                LockConfig passwordConfig = initialLockSetupService.getLockConfig(0);
                LockConfig pinConfig = initialLockSetupService.getLockConfig(1);
                LockConfig patternConfig = initialLockSetupService.getLockConfig(2);
                Map<Integer, LockConfig> map = new HashMap<>();
                map.put(0, passwordConfig);
                map.put(1, pinConfig);
                map.put(2, patternConfig);
                return map;
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Map<Integer, LockConfig> map) {
            InitialLockListener listener = this.mInitialLockListener.get();
            if (listener == null) {
                return;
            }
            listener.onGetLockConfigs(map);
        }
    }

    private static class ValidateLockAsyncTask extends AsyncTask<byte[], Void, Integer> {
        private WeakReference<InitialLockListener> mInitialLockListener;
        private WeakReference<IInitialLockSetupService> mInitialLockSetupService;
        private int mLockType;

        ValidateLockAsyncTask(InitialLockListener initialLockListener, IInitialLockSetupService initialLockSetupService, int lockType) {
            this.mInitialLockListener = new WeakReference<>(initialLockListener);
            this.mInitialLockSetupService = new WeakReference<>(initialLockSetupService);
            this.mLockType = lockType;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(byte[]... passwords) {
            InitialLockSetupClient.logVerbose("ValidateLockAsyncTask doInBackground");
            IInitialLockSetupService initialLockSetupService = this.mInitialLockSetupService.get();
            int i = 8;
            if (initialLockSetupService == null) {
                InitialLockSetupClient.logVerbose("Lost reference to service in ValidateLockAsyncTask");
                return i;
            }
            try {
                int output = initialLockSetupService.checkValidLock(this.mLockType, passwords[0]);
                return Integer.valueOf(output);
            } catch (RemoteException e) {
                e.printStackTrace();
                return i;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer resultCode) {
            InitialLockListener listener;
            if (isCancelled() || (listener = this.mInitialLockListener.get()) == null) {
                return;
            }
            listener.onLockValidated(resultCode.intValue());
        }
    }

    private static class SaveLockAsyncTask extends AsyncTask<byte[], Void, Integer> {
        private WeakReference<InitialLockListener> mInitialLockListener;
        private WeakReference<IInitialLockSetupService> mInitialLockSetupService;
        private int mLockType;

        SaveLockAsyncTask(InitialLockListener initialLockListener, IInitialLockSetupService initialLockSetupService, int lockType) {
            this.mInitialLockListener = new WeakReference<>(initialLockListener);
            this.mInitialLockSetupService = new WeakReference<>(initialLockSetupService);
            this.mLockType = lockType;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(byte[]... passwords) {
            InitialLockSetupClient.logVerbose("SaveLockAsyncTask doInBackground");
            IInitialLockSetupService initialLockSetupService = this.mInitialLockSetupService.get();
            int i = -3;
            if (initialLockSetupService == null) {
                InitialLockSetupClient.logVerbose("Lost reference to service in SaveLockAsyncTask");
                return i;
            }
            try {
                int output = initialLockSetupService.setLock(this.mLockType, passwords[0]);
                return Integer.valueOf(output);
            } catch (RemoteException e) {
                e.printStackTrace();
                return i;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer resultCode) {
            InitialLockListener listener = this.mInitialLockListener.get();
            if (listener == null) {
                return;
            }
            listener.onSetLockFinished(resultCode.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logVerbose(String logStatement) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, logStatement);
        }
    }
}
