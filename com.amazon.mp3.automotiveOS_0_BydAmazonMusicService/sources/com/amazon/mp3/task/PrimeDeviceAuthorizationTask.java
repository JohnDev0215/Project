package com.amazon.mp3.task;

import android.os.AsyncTask;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeDeviceAuthorizationTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = PrimeDeviceAuthorizationTask.class.getSimpleName();
    private final IContinuation mIContinuation;

    public interface IContinuation {
        void doNotWork();

        void doWork();

        void onNetworkError();
    }

    public PrimeDeviceAuthorizationTask(final IContinuation iContinuation) {
        this.mIContinuation = iContinuation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(final Void... voids) {
        if (!ConnectivityUtil.hasAnyInternetConnection() || !ConnectivityUtil.isAvailable()) {
            IContinuation iContinuation = this.mIContinuation;
            if (iContinuation == null) {
                return null;
            }
            iContinuation.onNetworkError();
            return null;
        }
        if (!DeviceAuthorizationManager.getInstance().primeAuthorizeDevice()) {
            IContinuation iContinuation2 = this.mIContinuation;
            if (iContinuation2 != null) {
                iContinuation2.doNotWork();
            }
            Log.error(TAG, "Failed to prime authorize device");
            return null;
        }
        IContinuation iContinuation3 = this.mIContinuation;
        if (iContinuation3 == null) {
            return null;
        }
        iContinuation3.doWork();
        return null;
    }
}
