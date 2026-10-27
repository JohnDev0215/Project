package com.amazon.mp3.det;

import android.content.Context;
import com.amazon.mp3.det.report.DetReport;
import com.amazon.mp3.util.Log;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class DetUploader {
    public static final Executor SERIAL_EXECUTOR = new ThreadPoolExecutor(0, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
    private static final String TAG = DetUploader.class.getSimpleName();

    public static void submitReport(final Context context, final DetReport report) {
        SERIAL_EXECUTOR.execute(new UploadTask(context, report));
    }

    public static void submitReport(final Context context, final DetReport report, final String dtsSessionId) {
        SERIAL_EXECUTOR.execute(new UploadTask(context, report, dtsSessionId));
    }

    private static class UploadTask implements Runnable {
        private static final String UPLOAD_REPORT_METHOD_NAME = UploadTask.class.getName() + ".uploadReport";
        private final Context mContext;
        private final DetReport mDetReport;
        private final String mDtsSessionId;

        UploadTask(final Context context, final DetReport report) {
            this(context, report, null);
        }

        UploadTask(final Context context, final DetReport report, final String dtsSessionId) {
            this.mContext = context.getApplicationContext();
            this.mDetReport = report;
            this.mDtsSessionId = dtsSessionId;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0045  */
        /* JADX WARN: Removed duplicated region for block: B:12:0x004d  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 207
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.det.DetUploader.UploadTask.run():void");
        }

        private void uploadReport(Request request) {
            try {
                Response responseExecute = FirebasePerfOkHttpClient.execute(UnifiedOkHttpClient.newClient().newCall(request));
                Log.debug(DetUploader.TAG, "response code for log upload: " + responseExecute.code());
                if (responseExecute.isSuccessful()) {
                    PendingReports.deleteSavedReport(this.mContext, this.mDetReport);
                }
            } catch (IOException e) {
                Log.warning(DetUploader.TAG, "Failed to upload log", e);
            }
        }
    }
}
