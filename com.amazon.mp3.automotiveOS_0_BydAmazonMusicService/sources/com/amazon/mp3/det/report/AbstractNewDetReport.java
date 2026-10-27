package com.amazon.mp3.det.report;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.LogFiles;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractNewDetReport implements DetReport {
    private static final String TAG = "AbstractNewDetReport";
    protected final Context mContext;
    private final String mReportTag;
    private final ReportType mReportType;

    protected abstract StringBuilder createReportBody();

    protected AbstractNewDetReport(Context context, ReportType logType) {
        this.mContext = context;
        this.mReportType = logType;
        this.mReportTag = createUploadTag(context, logType);
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public String getReportTag() {
        return this.mReportTag;
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public ReportType getReportType() {
        return this.mReportType;
    }

    @Override // com.amazon.mp3.det.report.DetReport
    public final String buildReport() {
        StringBuilder sbCreateReportBody = createReportBody();
        if (sbCreateReportBody.length() == 0) {
            return "";
        }
        return sbCreateReportBody.insert(0, "MFBS/1.0 1\nContent-Encoding: text\nContent-Type: Text/Plain\nContent-Length: " + sbCreateReportBody.length() + "\n[filecontent]\n").toString();
    }

    protected StringBuilder readLogs(boolean includeLogcat) {
        StringBuilder sb = new StringBuilder();
        File logcatFile = LogFiles.getLogcatFile(this.mContext);
        synchronized (AbstractNewDetReport.class) {
            for (File file : LogFiles.createLogs(this.mContext, includeLogcat)) {
                Log.debug(TAG, "adding: " + file.getName());
                boolean zEquals = file.equals(logcatFile);
                if (zEquals) {
                    sb.append("\n======= Begin logcat =======\n");
                }
                String strLoadFromFile = FileUtil.loadFromFile(file);
                if (strLoadFromFile != null) {
                    sb.append(strLoadFromFile);
                }
                if (zEquals) {
                    sb.append("======== End logcat ========\n");
                }
            }
        }
        return sb;
    }

    private static String createUploadTag(Context context, ReportType logType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM'_'dd'_'yyyy'_'HHmm'.'ss'_'z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return AccountCredentialStorage.get(context).getDeviceType() + "-" + logType + "-" + simpleDateFormat.format(new Date());
    }
}
