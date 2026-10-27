package com.amazon.mp3.det;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.music.arcus.ArcusUtils;
import com.amazon.music.logger.AmazonMusicMarketLogger;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class AutoLogUploader extends CustomerLogUploader {
    public void uploadLogsIfRequired(final Context context, final Date requestedDate) {
        if (shouldUploadLogs(context, requestedDate) && canUpload(context)) {
            boolean nonCriticalLoggingEnabled = AmazonMusicMarketLogger.getNonCriticalLoggingEnabled();
            if (!nonCriticalLoggingEnabled) {
                AmazonMusicMarketLogger.setNonCriticalLoggingEnabled(true);
            }
            upload(context);
            if (!nonCriticalLoggingEnabled) {
                AmazonMusicMarketLogger.setNonCriticalLoggingEnabled(false);
            }
            setLogsLastSubmittedDate(context, requestedDate);
        }
    }

    @VisibleForTesting
    boolean shouldUploadLogs(final Context context, final Date requestedDate) {
        if (requestedDate == null) {
            return false;
        }
        String string = SettingsUtil.getPrefs(context).getString("com.amazon.mp3_LogSubmissionDate", null);
        if (string == null) {
            return true;
        }
        Date date = ArcusUtils.toDate(string);
        Date moreRecentDate = ArcusUtils.getMoreRecentDate(date, requestedDate);
        return (moreRecentDate == null || moreRecentDate.equals(date)) ? false : true;
    }

    private void setLogsLastSubmittedDate(final Context context, final Date requestedDate) {
        String string = ArcusUtils.toString(requestedDate);
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString("com.amazon.mp3_LogSubmissionDate", string);
        editorEdit.apply();
    }
}
