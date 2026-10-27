package android.os;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.IDumpstateListener;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class BugreportManager {
    private static final String INTENT_UI_INTENSIVE_BUGREPORT_DUMPS_FINISHED = "com.android.internal.intent.action.UI_INTENSIVE_BUGREPORT_DUMPS_FINISHED";
    private static final String TAG = "BugreportManager";
    private final IDumpstate mBinder;
    private final Context mContext;

    public static abstract class BugreportCallback {
        public static final int BUGREPORT_ERROR_ANOTHER_REPORT_IN_PROGRESS = 5;
        public static final int BUGREPORT_ERROR_INVALID_INPUT = 1;
        public static final int BUGREPORT_ERROR_RUNTIME = 2;
        public static final int BUGREPORT_ERROR_USER_CONSENT_TIMED_OUT = 4;
        public static final int BUGREPORT_ERROR_USER_DENIED_CONSENT = 3;

        @Retention(RetentionPolicy.SOURCE)
        public @interface BugreportErrorCode {
        }

        public void onError(int i) {
        }

        public void onFinished() {
        }

        public void onProgress(float f) {
        }
    }

    public BugreportManager(Context context, IDumpstate iDumpstate) {
        this.mContext = context;
        this.mBinder = iDumpstate;
    }

    public void startBugreport(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, BugreportParams bugreportParams, Executor executor, BugreportCallback bugreportCallback) {
        try {
            try {
                try {
                    Preconditions.checkNotNull(parcelFileDescriptor);
                    Preconditions.checkNotNull(bugreportParams);
                    Preconditions.checkNotNull(executor);
                    Preconditions.checkNotNull(bugreportCallback);
                    boolean z = parcelFileDescriptor2 != null;
                    if (parcelFileDescriptor2 == null) {
                        parcelFileDescriptor2 = ParcelFileDescriptor.open(new File("/dev/null"), 268435456);
                    }
                    this.mBinder.startBugreport(-1, this.mContext.getOpPackageName(), parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor2.getFileDescriptor(), bugreportParams.getMode(), new DumpstateListener(executor, bugreportCallback, z), z);
                    IoUtils.closeQuietly(parcelFileDescriptor);
                    if (parcelFileDescriptor2 == null) {
                        return;
                    }
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (FileNotFoundException e2) {
                Log.wtf(TAG, "Not able to find /dev/null file: ", e2);
                IoUtils.closeQuietly(parcelFileDescriptor);
                if (parcelFileDescriptor2 == null) {
                    return;
                }
            }
            IoUtils.closeQuietly(parcelFileDescriptor2);
        } catch (Throwable th) {
            IoUtils.closeQuietly(parcelFileDescriptor);
            if (parcelFileDescriptor2 != null) {
                IoUtils.closeQuietly(parcelFileDescriptor2);
            }
            throw th;
        }
    }

    public void cancelBugreport() {
        try {
            this.mBinder.cancelBugreport();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void requestBugreport(BugreportParams bugreportParams, CharSequence charSequence, CharSequence charSequence2) {
        String string;
        String string2 = null;
        if (charSequence == null) {
            string = null;
        } else {
            try {
                string = charSequence.toString();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        if (charSequence2 != null) {
            string2 = charSequence2.toString();
        }
        ActivityManager.getService().requestBugReportWithDescription(string, string2, bugreportParams.getMode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class DumpstateListener extends IDumpstateListener.Stub {
        private final BugreportCallback mCallback;
        private final Executor mExecutor;
        private final boolean mIsScreenshotRequested;

        DumpstateListener(Executor executor, BugreportCallback bugreportCallback, boolean z) {
            this.mExecutor = executor;
            this.mCallback = bugreportCallback;
            this.mIsScreenshotRequested = z;
        }

        @Override // android.os.IDumpstateListener
        public void onProgress(final int i) throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.os.-$$Lambda$BugreportManager$DumpstateListener$Vi18nEKTiYzuC_I5Io1XCZxd88w
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onProgress$0$BugreportManager$DumpstateListener(i);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onProgress$0$BugreportManager$DumpstateListener(int i) {
            this.mCallback.onProgress(i);
        }

        @Override // android.os.IDumpstateListener
        public void onError(final int i) throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.os.-$$Lambda$BugreportManager$DumpstateListener$srBmWyEMI-89xDivmKB4DtiSQ2A
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onError$1$BugreportManager$DumpstateListener(i);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onError$1$BugreportManager$DumpstateListener(int i) {
            this.mCallback.onError(i);
        }

        @Override // android.os.IDumpstateListener
        public void onFinished() throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.os.-$$Lambda$BugreportManager$DumpstateListener$XpZbAM9CYGe3tPOak0Nw-HdFQ8I
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onFinished$2$BugreportManager$DumpstateListener();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onFinished$2$BugreportManager$DumpstateListener() {
            this.mCallback.onFinished();
        }

        @Override // android.os.IDumpstateListener
        public void onScreenshotTaken(final boolean z) throws RemoteException {
            if (this.mIsScreenshotRequested) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.os.-$$Lambda$BugreportManager$DumpstateListener$25-fbbhpwKLVX23K6WDSFGsWmHM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onScreenshotTaken$3$BugreportManager$DumpstateListener(z);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onScreenshotTaken$3$BugreportManager$DumpstateListener(boolean z) {
            Toast.makeText(BugreportManager.this.mContext, z ? R.string.bugreport_screenshot_success_toast : R.string.bugreport_screenshot_failure_toast, 1).show();
        }

        @Override // android.os.IDumpstateListener
        public void onUiIntensiveBugreportDumpsFinished(final String str) throws RemoteException {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.os.-$$Lambda$BugreportManager$DumpstateListener$8jYsAwxtBYQkBW2vxfMUYgDXLK8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onUiIntensiveBugreportDumpsFinished$4$BugreportManager$DumpstateListener(str);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onUiIntensiveBugreportDumpsFinished$4$BugreportManager$DumpstateListener(String str) {
            Intent intent = new Intent(BugreportManager.INTENT_UI_INTENSIVE_BUGREPORT_DUMPS_FINISHED);
            intent.setPackage(str);
            intent.addFlags(268435456);
            intent.addFlags(16777216);
            BugreportManager.this.mContext.sendBroadcast(intent, Manifest.permission.DUMP);
        }
    }
}
