package com.amazon.mp3.det;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.det.report.CustomerServiceReport;
import com.amazon.mp3.util.ConnectivityUtil;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CustomerLogUploader {
    protected LogUploader logUploader;

    @VisibleForTesting
    public interface LogUploader {
        void upload(@NonNull final Context context);
    }

    void upload(@NonNull final Context context) {
        getLogUploader().upload(context);
    }

    @VisibleForTesting
    boolean canUpload(@NonNull final Context context) {
        return ConnectivityUtil.isWifiConnected(context);
    }

    private LogUploader getLogUploader() {
        if (this.logUploader == null) {
            this.logUploader = new DetLogUploader();
        }
        return this.logUploader;
    }

    private static class DetLogUploader implements LogUploader {
        private DetLogUploader() {
        }

        @Override // com.amazon.mp3.det.CustomerLogUploader.LogUploader
        public void upload(@NonNull final Context context) {
            DetUploader.submitReport(context, new CustomerServiceReport(context));
        }
    }
}
