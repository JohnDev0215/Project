package com.amazon.mp3.playback.harley;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.library.job.DeletePrimeTrackJob;
import com.amazon.mp3.library.job.DeleteTrackJob;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.ContentAccessType;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.impl.AbstractLibraryAccessProvider;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryAccessProviderImpl extends AbstractLibraryAccessProvider implements OnItemFinishedAddingListener {
    private static final String TAG = "LibraryAccessProviderImpl";
    private static LibraryAccessProviderImpl sLibraryAccessProvider;
    private final Context mContext;
    private final JobQueue mJobQueue;
    private final Set<MediaItem> mLibrary = new HashSet();

    @Override // com.amazon.mp3.library.fragment.OnItemFinishedAddingListener
    public void onItemAdded(final String item, final boolean downloadAfterAdd) {
    }

    private LibraryAccessProviderImpl(final Context context) {
        this.mContext = context;
        this.mJobQueue = new JobQueue(context);
    }

    public static LibraryAccessProviderImpl getInstance(final Context context) {
        if (sLibraryAccessProvider == null) {
            sLibraryAccessProvider = new LibraryAccessProviderImpl(context);
        }
        return sLibraryAccessProvider;
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public boolean isInLibrary(final MediaItem item) {
        return this.mLibrary.contains(item);
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public boolean canAddToLibrary(final MediaItem item) {
        return (MediaItemHelper.getAsin(item) == null || isInLibrary(item)) ? false : true;
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public void addToLibrary(final MediaItem item) {
        if (!canAddToLibrary(item)) {
            Log.debug(TAG, "Could not add item to library, MediaItem or ASIN is null or track is already in the library. %s", MediaItemHelper.getAsin(item));
            return;
        }
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            Context context = this.mContext;
            context.startActivity(NetworkErrorDialogActivity.getStartIntent(context));
        } else {
            this.mJobQueue.addJob(new AddPrimeTrackJob(this.mContext, MediaItemHelper.getAsin(item)));
            BauhausToastUtils.showDefaultToast(this.mContext, this.mContext.getResources().getString(R.string.dmusic_prime_song_added_to_library), 0);
            addToMap(item);
            notifyLibraryChanged();
        }
    }

    private void addToMap(final MediaItem item) {
        this.mLibrary.add(item);
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public void removeFromLibrary(final MediaItem item) {
        Job job;
        if (item == null) {
            Log.debug(TAG, "tried to remove MediaItem that was null");
            return;
        }
        final String string = this.mContext.getString(R.string.song_removed_content_description);
        final String string2 = this.mContext.getString(R.string.removal_fail_content_description);
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        String asin = MediaItemHelper.getAsin(item);
        if (item.getMediaAccessInfo().getContentAccessTypes().contains(ContentAccessType.PRIME)) {
            job = new DeletePrimeTrackJob(writableDatabase, asin) { // from class: com.amazon.mp3.playback.harley.LibraryAccessProviderImpl.1
                @Override // com.amazon.mp3.service.job.Job
                protected void onFinished(final int resultCode, final Bundle resultBundle) {
                    super.onFinished(resultCode, resultBundle);
                    LibraryAccessProviderImpl.this.announceDeleteJobFinished(resultCode, string, string2);
                }
            };
        } else {
            job = new DeleteTrackJob(MediaItemHelper.getContentUri(item)) { // from class: com.amazon.mp3.playback.harley.LibraryAccessProviderImpl.2
                @Override // com.amazon.mp3.service.job.Job
                protected void onFinished(final int resultCode, final Bundle resultBundle) {
                    super.onFinished(resultCode, resultBundle);
                    LibraryAccessProviderImpl.this.announceDeleteJobFinished(resultCode, string, string2);
                }
            };
        }
        BauhausToastUtils.showDefaultToast(this.mContext, R.string.dmusic_track_toast_deleting_track_cloud, 1);
        this.mJobQueue.addJob(job);
        removeFromMap(item);
        notifyLibraryChanged();
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public boolean isAvailableOffline(final MediaItem item) {
        if (item == null) {
            return false;
        }
        MediaItemId.Type type = MediaItemId.Type.LOCAL_URI;
        boolean zIsTrackAvailableOffline = item.getMediaItemId(type) != null ? MusicDownloader.getInstance(this.mContext).isTrackAvailableOffline(item.getMediaItemId(type).getId(), false) : false;
        MediaItemId.Type type2 = MediaItemId.Type.ASIN;
        return zIsTrackAvailableOffline || (item.getMediaItemId(type2) != null ? MusicDownloader.getInstance(this.mContext).isTrackAvailableOffline(item.getMediaItemId(type2).getId(), true) : false);
    }

    @Override // com.amazon.music.media.playback.config.LibraryAccessProvider
    public void updateLibraryWithItem(MediaItem item) {
        updateLibrary(item);
    }

    public boolean isAvailableOffline(final AbstractItem track, final boolean checkEligibilities) {
        ContentUnavailableReason offlineAssetUnavailableReason = ContentAccessUtil.getOfflineAssetUnavailableReason(track);
        if (offlineAssetUnavailableReason == null && checkEligibilities && (track instanceof EntityEligibilitiesProvider)) {
            offlineAssetUnavailableReason = new EligibilityUtil().getContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE);
        }
        return offlineAssetUnavailableReason == null;
    }

    private void removeFromMap(final MediaItem item) {
        this.mLibrary.remove(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void announceDeleteJobFinished(final int resultCode, final String successMsg, final String failMsg) {
        if (1 == resultCode) {
            AccessibilityUtil.announceForAccessibility(this.mContext, successMsg);
        } else {
            AccessibilityUtil.announceForAccessibility(this.mContext, failMsg);
        }
    }

    public DownloadState updateLibrary(final MediaItem item) {
        Uri contentUri;
        String[] strArr = {"download_state", "ownership_status", "playlist_track_status"};
        int i = -1;
        if (this.mContext != null && (contentUri = MediaItemHelper.getContentUri(item)) != null) {
            Cursor cursorQuery = this.mContext.getContentResolver().query(contentUri, strArr, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
                int i3 = cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status"));
                if (cursorQuery.getInt(cursorQuery.getColumnIndex("playlist_track_status")) == MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue() || i3 == ContentOwnershipStatus.OWNED.getValue() || i3 == ContentOwnershipStatus.ADDED.getValue()) {
                    addToMap(item);
                } else {
                    removeFromMap(item);
                }
                i = i2;
            }
            DbUtil.closeCursor(cursorQuery);
        }
        return convertValueToDownloadState(i);
    }

    private DownloadState convertValueToDownloadState(final int downloadValue) {
        if (downloadValue == 0) {
            return DownloadState.DOWNLOADED;
        }
        if (downloadValue == 1) {
            return DownloadState.QUEUED;
        }
        if (downloadValue == 2) {
            return DownloadState.ERROR;
        }
        if (downloadValue == 3) {
            return DownloadState.PAUSED_USER;
        }
        if (downloadValue == 4) {
            return DownloadState.DOWNLOADING;
        }
        return DownloadState.UNKNOWN;
    }
}
