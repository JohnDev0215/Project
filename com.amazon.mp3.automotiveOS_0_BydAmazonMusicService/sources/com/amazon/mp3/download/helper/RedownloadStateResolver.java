package com.amazon.mp3.download.helper;

import android.content.Context;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.HarleyDownloadedItem;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RedownloadStateResolver {
    private static final String TAG = "RedownloadStateResolver";
    private final Context mContext;
    private final DownloadQualityPreferences mCurrentDownloadQualityPref;

    public RedownloadStateResolver(final Context context) {
        this.mContext = context;
        this.mCurrentDownloadQualityPref = new UserPreferenceProvider(context).getCurrentDownloadQualityPreference();
    }

    public boolean shouldRedownloadTrack(final String trackIdentifier, final boolean isDrm, final List<ContentEncoding> contentEncodings) {
        if (StringUtil.isNullOrWhiteSpaces(trackIdentifier) || !isDrm || !UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return false;
        }
        HarleyDownloadedItem downloadedItem = MusicDownloader.getInstance(this.mContext).getDownloadedItem(trackIdentifier, isDrm);
        if (downloadedItem == null) {
            Log.warning(TAG, "DownloadedItem is null. This is expected when read/write permissions are denied by the user");
            return false;
        }
        RedownloadStateEvaluator redownloadStateEvaluator = new RedownloadStateEvaluator(downloadedItem.getPlaybackAudioQuality().getPlayerAudioQuality(), this.mCurrentDownloadQualityPref, contentEncodings, SettingsUtil.isSpatialModeSettingEnabled(this.mContext));
        if (downloadedItem.isAvailableOffline() && !redownloadStateEvaluator.shouldRedownload()) {
            return false;
        }
        Log.debug(TAG, "Requires redownload of track with identifier %s and download item %s", trackIdentifier, downloadedItem);
        return true;
    }
}
