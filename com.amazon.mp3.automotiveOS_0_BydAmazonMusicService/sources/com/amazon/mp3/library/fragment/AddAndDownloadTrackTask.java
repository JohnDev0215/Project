package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.MediaNotificationFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AddAndDownloadTrackTask extends AddAndDownloadTask {
    private static final String TAG = AddAndDownloadTrackTask.class.getSimpleName();
    private final OnItemFinishedAddingListener mAddedListener;
    private boolean mRedownload;
    private final boolean mShowToast;
    private final String mTrackAsin;
    private final String mTrackluid;

    public AddAndDownloadTrackTask(final Context context, final String trackAsin, final String trackluid, final boolean download, final boolean showToast, final boolean userInitiated, @Nullable final OnItemFinishedAddingListener listener) {
        super(context, download, userInitiated);
        this.mRedownload = false;
        this.mTrackAsin = trackAsin;
        this.mTrackluid = trackluid;
        this.mShowToast = showToast;
        this.mAddedListener = listener;
    }

    public AddAndDownloadTrackTask(final Context context, final String trackAsin, final boolean download, final boolean showToast, @Nullable final OnItemFinishedAddingListener listener) {
        this(context, trackAsin, null, download, showToast, true, listener);
    }

    public AddAndDownloadTrackTask(final Context context, final String trackAsin, final String trackLuid, final boolean download, final boolean showToast, @Nullable final OnItemFinishedAddingListener listener) {
        this(context, trackAsin, trackLuid, download, showToast, true, listener);
    }

    public AddAndDownloadTrackTask(final Context context, final String trackAsin, final boolean download, final boolean showToast, final boolean redownload, @Nullable final OnItemFinishedAddingListener listener) {
        this(context, trackAsin, null, download, showToast, true, listener);
        this.mRedownload = redownload;
    }

    public AddAndDownloadTrackTask(final Context context, PrimeTrack track, final boolean download, final boolean showToast, @Nullable final OnItemFinishedAddingListener listener) {
        this(context, track.getAsin(), track.getLuid(), download, showToast, true, listener);
    }

    public AddAndDownloadTrackTask(final Context context, PrimeTrack track, final boolean download, final boolean showToast, final boolean redownload, @Nullable final OnItemFinishedAddingListener listener) {
        this(context, track.getAsin(), track.getLuid(), download, showToast, true, listener);
        this.mRedownload = redownload;
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected List<String> getTracksAsinsToAdd() {
        MusicTrack trackFromAsin = new TrackRequester(getContext()).getTrackFromAsin(this.mTrackAsin);
        if (trackFromAsin == null || !trackFromAsin.isInLibrary()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mTrackAsin);
            return arrayList;
        }
        return new ArrayList();
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected boolean shouldAddToLibrary() {
        String str = this.mTrackAsin;
        return (str == null || str.isEmpty()) ? false : true;
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected Uri getContentUriToDownload() {
        String str = this.mTrackAsin;
        String trackLuid = (str == null || str.isEmpty()) ? this.mTrackluid : getTrackLuid(this.mTrackAsin);
        if (trackLuid != null) {
            return MediaProvider.Tracks.getContentUri("cirrus", trackLuid);
        }
        Log.debug(TAG, "The track has no LUID. The asin: " + this.mTrackAsin);
        return MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", this.mTrackAsin);
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected String getRequestIdForDownload() {
        return this.mTrackAsin;
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected NotificationInfo getNotificationInfoForDownload() {
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(getContext()).query("Track", new String[]{"title", "artist", "album", "album_artist"}, "asin=?", new String[]{this.mTrackAsin}, null, null, null);
        try {
            if (cursorQuery == null) {
                return null;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    return new NotificationInfo(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("title")), cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist")), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), IdGenerator.generateAlbumId(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album_asin")), cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album")))).toString());
                }
            } catch (Exception unused) {
                Log.error(TAG, "ClientBuddy CTA failed for Album ASIN: " + this.mTrackAsin);
            }
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... values) {
        if (values[0].intValue() == 1) {
            if (this.mShowToast) {
                BauhausToastUtils.showDefaultToast(getContext(), R.string.dmusic_prime_song_added_to_library, 1);
                return;
            }
            return;
        }
        super.onProgressUpdate((Object[]) values);
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    public void onItemAdded() {
        OnItemFinishedAddingListener onItemFinishedAddingListener = this.mAddedListener;
        if (onItemFinishedAddingListener != null) {
            onItemFinishedAddingListener.onItemAdded(this.mTrackAsin, isDownload());
        }
        updateNotificationState();
    }

    public void updateNotificationState() {
        MediaNotificationFactory.ForceUpdateHandler forceUpdateHandler;
        MediaNotificationFactory mediaNotificationFactory = Playback.getInstance().getPlaybackConfig().getMediaNotificationFactory();
        if (mediaNotificationFactory == null || (forceUpdateHandler = mediaNotificationFactory.getForceUpdateHandler()) == null) {
            return;
        }
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        MediaItemId mediaItemId = currentMediaItem == null ? null : currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        if (mediaItemId == null || !this.mTrackAsin.equals(mediaItemId.getId())) {
            return;
        }
        forceUpdateHandler.forceUpdate();
    }

    public Boolean shouldTrackBeRedownloaded() {
        return Boolean.valueOf(this.mRedownload);
    }
}
