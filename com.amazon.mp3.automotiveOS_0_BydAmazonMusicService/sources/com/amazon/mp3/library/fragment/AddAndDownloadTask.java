package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AddAndDownloadTask extends AsyncTask<Void, Integer, Integer> {
    private static final String TAG = AddAndDownloadTask.class.getSimpleName();
    private final FragmentActivity mActivity;
    private final Context mContext;
    private final boolean mDownload;
    private final boolean mUserInitiated;

    protected abstract Uri getContentUriToDownload();

    protected abstract NotificationInfo getNotificationInfoForDownload();

    protected abstract String getRequestIdForDownload();

    protected abstract List<String> getTracksAsinsToAdd();

    protected abstract void onItemAdded();

    protected boolean shouldAddToLibrary() {
        return true;
    }

    public AddAndDownloadTask(final Context context, final boolean download, final boolean userInitiated) {
        this.mContext = context.getApplicationContext();
        if (context instanceof FragmentActivity) {
            this.mActivity = (FragmentActivity) context;
        } else {
            this.mActivity = null;
        }
        this.mDownload = download;
        this.mUserInitiated = userInitiated;
    }

    public AddAndDownloadTask(final Context context, final boolean download) {
        this(context, download, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final Integer doInBackground(Void... params) {
        if (this.mDownload) {
            publishProgress(2);
        } else {
            publishProgress(1);
        }
        if (shouldAddToLibrary()) {
            List<String> tracksAsinsToAdd = getTracksAsinsToAdd();
            if (tracksAsinsToAdd.size() > 0) {
                addContentToLibrary(tracksAsinsToAdd);
            }
        }
        onItemAdded();
        if (this.mDownload) {
            downloadContent(getContentUriToDownload());
        }
        return 1;
    }

    private void addContentToLibrary(List<String> trackAsins) {
        if (PrimeContentUtil.addPrimeTracksInDb(CirrusDatabase.getWritableDatabase(this.mContext), trackAsins) > 0) {
            this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        }
        addNewTracksToTheLibrary(trackAsins);
    }

    private boolean downloadContent(final Uri contentUri) {
        if (contentUri != null) {
            FragmentActivity fragmentActivity = this.mActivity;
            if (fragmentActivity != null && DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(fragmentActivity)) {
                this.mActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddAndDownloadTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$downloadContent$0(contentUri);
                    }
                });
                return true;
            }
            if ((this instanceof AddAndDownloadTrackTask) && ((AddAndDownloadTrackTask) this).shouldTrackBeRedownloaded().booleanValue()) {
                MusicDownloader.getInstance(this.mContext).redownload(getRequestIdForDownload(), contentUri, getNotificationInfoForDownload(), true, this.mUserInitiated);
                return false;
            }
            MusicDownloader.getInstance(this.mContext).download(getRequestIdForDownload(), contentUri, getNotificationInfoForDownload(), true, this.mUserInitiated);
            return false;
        }
        Log.error(TAG, "Could not start download because content URI is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadContent$0(Uri uri) {
        DownloadSettingDialogFragment.showSettingsDialog(this.mActivity, getRequestIdForDownload(), uri, getNotificationInfoForDownload(), true);
    }

    private void addNewTracksToTheLibrary(List<String> trackAsins) {
        AddRemovePrimeManager.getInstance(this.mContext).queueTracksToAdd(trackAsins);
        SyncService.startSync(this.mContext, 1026);
        if (this.mDownload) {
            SyncService.waitForSync(this.mContext, 1026);
            Log.debug(TAG, "the sync is done");
        }
    }

    protected final Context getContext() {
        return this.mContext;
    }

    public final boolean isDownload() {
        return this.mDownload;
    }

    @Nullable
    protected String getTrackLuid(String trackAsin) {
        MusicTrack trackForAsin = new CirrusSourceLibraryItemFactory(this.mContext).getTrackForAsin(trackAsin);
        if (trackForAsin != null) {
            Log.debug(TAG, "Track: " + trackForAsin);
            return trackForAsin.getLuid();
        }
        Log.debug(TAG, "Unable to find a track for the asin: " + trackAsin);
        return null;
    }
}
