package com.amazon.mp3.library.dialog.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity;
import com.amazon.mp3.library.dialog.DownloadRetryDialog;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.task.PrimeDeviceAuthorizationTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadRetryDialogListener implements DownloadRetryDialog.RetryListener {
    private static final String TAG = "DownloadRetryDialogListener";
    private final Context mContext;
    private final NotificationInfo mNotificationInfo;
    private final String mRequestId;
    private final Uri mUri;
    private final DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener.1
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Group group) {
            Log.info(DownloadRetryDialogListener.TAG, "group download listener status update, " + DownloadRetryDialogListener.this.mRequestId + " " + downloadState.toString());
            if (downloadState == DownloadState.CANCELED && DownloadRetryDialogListener.this.mRequestId.equals(s)) {
                MusicDownloader.getInstance(DownloadRetryDialogListener.this.mContext).download(DownloadRetryDialogListener.this.mRequestId, DownloadRetryDialogListener.this.mUri, DownloadRetryDialogListener.this.mNotificationInfo, false, true);
                MusicDownloader.getInstance(DownloadRetryDialogListener.this.mContext).unregisterDownloadListener(this);
            }
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Item item) {
            Log.info(DownloadRetryDialogListener.TAG, "item download listener status update, " + DownloadRetryDialogListener.this.mRequestId + " " + downloadState.toString());
            if (downloadState == DownloadState.CANCELED && DownloadRetryDialogListener.this.mRequestId.equals(s)) {
                MusicDownloader.getInstance(DownloadRetryDialogListener.this.mContext).download(DownloadRetryDialogListener.this.mRequestId, DownloadRetryDialogListener.this.mUri, DownloadRetryDialogListener.this.mNotificationInfo, false, true);
                MusicDownloader.getInstance(DownloadRetryDialogListener.this.mContext).unregisterDownloadListener(this);
            }
        }
    };
    private final PrimeDeviceAuthorizationTask.IContinuation mContinuation = new PrimeDeviceAuthorizationTask.IContinuation() { // from class: com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener.2
        @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
        public void doWork() {
            DownloadRetryDialogListener.this.doRetry();
        }

        @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
        public void doNotWork() {
            Log.info(DownloadRetryDialogListener.TAG, "Could not retry download. prime auth was not initiated by user");
        }

        @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
        public void onNetworkError() {
            Log.warning(DownloadRetryDialogListener.TAG, "No network. Failing silently");
        }
    };

    private enum Failure {
        DEVICE_AUTH,
        PRIME_AUTH,
        NONE
    }

    public DownloadRetryDialogListener(@NonNull Context context, @NonNull String requestId, @NonNull Uri uri, @NonNull NotificationInfo notification) {
        this.mContext = context.getApplicationContext();
        this.mRequestId = requestId;
        this.mUri = uri;
        this.mNotificationInfo = notification;
    }

    @Override // com.amazon.mp3.library.dialog.DownloadRetryDialog.RetryListener
    public void retry() {
        ErrorReason errorReason;
        MusicDownloader musicDownloader = MusicDownloader.getInstance(this.mContext);
        Item item = musicDownloader.getItem(this.mRequestId);
        if (item == null) {
            Group group = musicDownloader.getGroup(this.mRequestId);
            if (group != null) {
                errorReason = group.getErrorReason();
            } else {
                errorReason = ErrorReason.UNKNOWN;
            }
        } else {
            errorReason = item.getErrorReason();
        }
        int i = AnonymousClass3.$SwitchMap$com$amazon$music$downloads$ErrorReason[errorReason.ordinal()];
        if (i == 1) {
            new DeviceAuthorizationTask(this.mContext, false).execute(new Void[0]);
        } else if (i == 2) {
            new DeviceAuthorizationTask(this.mContext, true).execute(new Void[0]);
        } else {
            doRetry();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRetry() {
        MusicDownloader musicDownloader = MusicDownloader.getInstance(this.mContext);
        musicDownloader.registerDownloadListener(this.mDownloadListener);
        this.mDownloadListener.addRequestId(this.mRequestId);
        musicDownloader.cancel(this.mRequestId);
    }

    private class DeviceAuthorizationTask extends AsyncTask<Void, Void, Failure> {
        private final Context mContext;
        private final boolean mOnlyPrimeAuthorize;

        public DeviceAuthorizationTask(Context context, boolean onlyPrimeAuthorize) {
            this.mContext = context.getApplicationContext();
            this.mOnlyPrimeAuthorize = onlyPrimeAuthorize;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Failure doInBackground(final Void... params) {
            DeviceAuthorizationManager deviceAuthorizationManager = DeviceAuthorizationManager.getInstance();
            if (!this.mOnlyPrimeAuthorize) {
                deviceAuthorizationManager.refresh();
                if (!deviceAuthorizationManager.autoAuthorize(this.mContext)) {
                    return Failure.DEVICE_AUTH;
                }
            }
            if (UserSubscriptionUtil.getUserSubscription().hasSubscription() && !deviceAuthorizationManager.primeAuthorizeDevice()) {
                return Failure.PRIME_AUTH;
            }
            return Failure.NONE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Failure failure) {
            int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$library$dialog$listener$DownloadRetryDialogListener$Failure[failure.ordinal()];
            if (i == 1) {
                Intent startIntent = LibraryDeviceAuthorizationWebViewActivity.getStartIntent(this.mContext);
                startIntent.addFlags(268435456);
                this.mContext.startActivity(startIntent);
            } else if (i == 2) {
                new PrimeDeviceAuthorizationTask(DownloadRetryDialogListener.this.mContinuation).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                if (i != 3) {
                    return;
                }
                DownloadRetryDialogListener.this.doRetry();
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.dialog.listener.DownloadRetryDialogListener$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$dialog$listener$DownloadRetryDialogListener$Failure;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$ErrorReason;

        static {
            int[] iArr = new int[Failure.values().length];
            $SwitchMap$com$amazon$mp3$library$dialog$listener$DownloadRetryDialogListener$Failure = iArr;
            try {
                iArr[Failure.DEVICE_AUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$dialog$listener$DownloadRetryDialogListener$Failure[Failure.PRIME_AUTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$dialog$listener$DownloadRetryDialogListener$Failure[Failure.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ErrorReason.values().length];
            $SwitchMap$com$amazon$music$downloads$ErrorReason = iArr2;
            try {
                iArr2[ErrorReason.DEVICE_NOT_AUTHORIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$ErrorReason[ErrorReason.DEVICE_NOT_ELIGIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.amazon.mp3.library.dialog.DownloadRetryDialog.RetryListener
    public void cancel() {
        MusicDownloader.getInstance(this.mContext).cancel(this.mRequestId);
    }
}
