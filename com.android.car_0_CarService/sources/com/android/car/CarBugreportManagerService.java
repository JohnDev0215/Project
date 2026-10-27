package com.android.car;

import android.car.ICarBugreportCallback;
import android.car.ICarBugreportService;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Slog;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class CarBugreportManagerService extends ICarBugreportService.Stub implements CarServiceBase {
    private static final String BEGIN_PREFIX = "BEGIN:";
    private static final String BUGREPORTD_SERVICE = "car-bugreportd";
    private static final String BUGREPORT_EXTRA_OUTPUT_SOCKET = "car_br_extra_output_socket";
    private static final String BUGREPORT_OUTPUT_SOCKET = "car_br_output_socket";
    private static final String BUGREPORT_PROGRESS_SOCKET = "car_br_progress_socket";
    private static final String DUMPSTATEZ_SERVICE = "car-dumpstatez";
    private static final String FAIL_PREFIX = "FAIL:";
    private static final String OK_PREFIX = "OK:";
    private static final String PROGRESS_PREFIX = "PROGRESS:";
    private static final int SOCKET_CONNECTION_MAX_RETRY = 10;
    private static final int SOCKET_CONNECTION_RETRY_DELAY_IN_MS = 5000;
    private static final String TAG = "CarBugreportMgrService";
    private final Context mContext;
    private final Object mLock = new Object();
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mHandler = new Handler(this.mHandlerThread.getLooper());
    private final AtomicBoolean mIsServiceRunning = new AtomicBoolean(false);

    public CarBugreportManagerService(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    public void requestBugreport(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.DUMP", "requestBugreport");
        ensureTheCallerIsSignedWithPlatformKeys();
        ensureTheCallerIsDesignatedBugReportApp();
        synchronized (this.mLock) {
            if (this.mIsServiceRunning.getAndSet(true)) {
                Slog.w(TAG, "Bugreport Service already running");
                reportError(callback, 2);
            } else {
                requestBugReportLocked(output, extraOutput, callback);
            }
        }
    }

    public void cancelBugreport() {
        this.mContext.enforceCallingOrSelfPermission("android.permission.DUMP", "cancelBugreport");
        ensureTheCallerIsSignedWithPlatformKeys();
        ensureTheCallerIsDesignatedBugReportApp();
        synchronized (this.mLock) {
            if (!this.mIsServiceRunning.getAndSet(false)) {
                Slog.i(TAG, "Failed to cancel. Service is not running.");
                return;
            }
            Slog.i(TAG, "Cancelling the running bugreport");
            this.mHandler.removeCallbacksAndMessages(null);
            try {
                SystemProperties.set("ctl.stop", BUGREPORTD_SERVICE);
            } catch (RuntimeException e) {
                Slog.e(TAG, "Failed to stop car-bugreportd", e);
            }
            try {
                SystemProperties.set("ctl.stop", DUMPSTATEZ_SERVICE);
            } catch (RuntimeException e2) {
                Slog.e(TAG, "Failed to stop car-dumpstatez", e2);
            }
        }
    }

    private void ensureTheCallerIsSignedWithPlatformKeys() {
        PackageManager pm = this.mContext.getPackageManager();
        int callingUid = Binder.getCallingUid();
        if (pm.checkSignatures(Process.myUid(), callingUid) != 0) {
            throw new SecurityException("Caller " + pm.getNameForUid(callingUid) + " does not have the right signature");
        }
    }

    private void ensureTheCallerIsDesignatedBugReportApp() {
        if (Build.IS_DEBUGGABLE) {
            return;
        }
        String defaultAppPkgName = this.mContext.getString(R.string.config_car_bugreport_application);
        int callingUid = Binder.getCallingUid();
        PackageManager pm = this.mContext.getPackageManager();
        String[] packageNamesForCallerUid = pm.getPackagesForUid(callingUid);
        if (packageNamesForCallerUid != null) {
            for (String packageName : packageNamesForCallerUid) {
                if (defaultAppPkgName.equals(packageName)) {
                    return;
                }
            }
        }
        throw new SecurityException("Caller " + pm.getNameForUid(callingUid) + " is not a designated bugreport app");
    }

    private void requestBugReportLocked(final ParcelFileDescriptor output, final ParcelFileDescriptor extraOutput, final ICarBugreportCallback callback) {
        Slog.i(TAG, "Starting car-bugreportd");
        try {
            SystemProperties.set("ctl.start", BUGREPORTD_SERVICE);
            this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$CarBugreportManagerService$AG6L1uBySyDSdAjmnFy5_-H9Ci0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestBugReportLocked$0$CarBugreportManagerService(output, extraOutput, callback);
                }
            });
        } catch (RuntimeException e) {
            this.mIsServiceRunning.set(false);
            Slog.e(TAG, "Failed to start car-bugreportd", e);
            reportError(callback, 1);
        }
    }

    public /* synthetic */ void lambda$requestBugReportLocked$0$CarBugreportManagerService(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) {
        try {
            processBugreportSockets(output, extraOutput, callback);
        } finally {
            this.mIsServiceRunning.set(false);
        }
    }

    private void handleProgress(String line, ICarBugreportCallback callback) {
        String progressOverTotal = line.substring(PROGRESS_PREFIX.length());
        String[] parts = progressOverTotal.split("/");
        if (parts.length != 2) {
            Slog.w(TAG, "Invalid progress line from bugreportz: " + line);
            return;
        }
        try {
            float progress = Float.parseFloat(parts[0]);
            float total = Float.parseFloat(parts[1]);
            if (total == 0.0f) {
                Slog.w(TAG, "Invalid progress total value: " + line);
                return;
            }
            try {
                callback.onProgress((100.0f * progress) / total);
            } catch (RemoteException e) {
                Slog.e(TAG, "Failed to call onProgress callback", e);
            }
        } catch (NumberFormatException e2) {
            Slog.w(TAG, "Invalid progress value: " + line, e2);
        }
    }

    private void handleFinished(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) {
        Slog.i(TAG, "Finished reading bugreport");
        if (!copySocketToPfd(output, BUGREPORT_OUTPUT_SOCKET, callback) || !copySocketToPfd(extraOutput, BUGREPORT_EXTRA_OUTPUT_SOCKET, callback)) {
            return;
        }
        try {
            callback.onFinished();
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to call onFinished callback", e);
        }
    }

    private void processBugreportSockets(ParcelFileDescriptor output, ParcelFileDescriptor extraOutput, ICarBugreportCallback callback) {
        String line;
        LocalSocket localSocket = connectSocket(BUGREPORT_PROGRESS_SOCKET);
        if (localSocket == null) {
            reportError(callback, 3);
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
            while (this.mIsServiceRunning.get() && (line = reader.readLine()) != null) {
                try {
                    if (line.startsWith(PROGRESS_PREFIX)) {
                        handleProgress(line, callback);
                    } else {
                        if (line.startsWith(FAIL_PREFIX)) {
                            String errorMessage = line.substring(FAIL_PREFIX.length());
                            Slog.e(TAG, "Failed to dumpstate: " + errorMessage);
                            reportError(callback, 1);
                            reader.close();
                            return;
                        }
                        if (line.startsWith(OK_PREFIX)) {
                            handleFinished(output, extraOutput, callback);
                            reader.close();
                            return;
                        } else if (!line.startsWith(BEGIN_PREFIX)) {
                            Slog.w(TAG, "Received unknown progress line from dumpstate: " + line);
                        }
                    }
                } catch (Throwable th) {
                    try {
                        reader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            Slog.e(TAG, "dumpstate progress unexpectedly ended");
            reportError(callback, 1);
            reader.close();
        } catch (IOException | RuntimeException e) {
            Slog.i(TAG, "Failed to read from progress socket", e);
            reportError(callback, 3);
        }
    }

    private boolean copySocketToPfd(ParcelFileDescriptor pfd, String remoteSocket, ICarBugreportCallback callback) {
        LocalSocket localSocket = connectSocket(remoteSocket);
        if (localSocket == null) {
            reportError(callback, 3);
            return false;
        }
        try {
            DataInputStream in = new DataInputStream(localSocket.getInputStream());
            try {
                DataOutputStream out = new DataOutputStream(new ParcelFileDescriptor.AutoCloseOutputStream(pfd));
                try {
                    rawCopyStream(out, in);
                    out.close();
                    in.close();
                    return true;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    in.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | RuntimeException e) {
            Slog.e(TAG, "Failed to grab dump state from car_br_output_socket", e);
            reportError(callback, 1);
            return false;
        }
    }

    private void reportError(ICarBugreportCallback callback, int errorCode) {
        try {
            callback.onError(errorCode);
        } catch (RemoteException e) {
            Slog.e(TAG, "onError() failed", e);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
    }

    private LocalSocket connectSocket(String socketName) {
        LocalSocket socket = new LocalSocket();
        int retryCount = 0;
        while (true) {
            for (int i = 0; i < 100; i++) {
                if (!this.mIsServiceRunning.get()) {
                    Slog.i(TAG, "Failed to connect to socket " + socketName + ". The service is prematurely cancelled.");
                    return null;
                }
                SystemClock.sleep(50L);
            }
            try {
                socket.connect(new LocalSocketAddress(socketName, LocalSocketAddress.Namespace.RESERVED));
                return socket;
            } catch (IOException e) {
                retryCount++;
                if (retryCount >= 10) {
                    Slog.i(TAG, "Failed to connect to dumpstate socket " + socketName + " after " + retryCount + " retries", e);
                    return null;
                }
                Log.i(TAG, "Failed to connect to " + socketName + ". Will try again. " + e.getMessage());
            }
        }
    }

    private static void rawCopyStream(OutputStream writer, InputStream reader) throws IOException {
        byte[] buf = new byte[8192];
        while (true) {
            int read = reader.read(buf, 0, buf.length);
            if (read > 0) {
                writer.write(buf, 0, read);
            } else {
                return;
            }
        }
    }
}
