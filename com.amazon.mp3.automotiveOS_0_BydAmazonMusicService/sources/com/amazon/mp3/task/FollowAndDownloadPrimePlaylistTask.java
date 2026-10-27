package com.amazon.mp3.task;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;

/* JADX INFO: loaded from: classes4.dex */
public class FollowAndDownloadPrimePlaylistTask extends AsyncTask<Void, Integer, Void> {
    private static final String TAG = FollowAndDownloadPrimePlaylistTask.class.getSimpleName();
    private Activity mActivity;
    private boolean mDownload;
    private PrimePlaylist mPlaylist;

    public FollowAndDownloadPrimePlaylistTask(Activity activity, PrimePlaylist playlist, boolean download) {
        this.mActivity = activity;
        this.mPlaylist = playlist;
        this.mDownload = download;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... params) {
        try {
            if (this.mPlaylist.getAsin() == null) {
                if (this.mPlaylist.getPlaylistId() == null) {
                    Log.error(TAG, "Could not follow playlist, playlistId and asin were null");
                    return null;
                }
                PrimePlaylistUtil.followPrimePlaylist(this.mActivity, new PrimePlaylistDatabaseManager(this.mActivity), 0, this.mPlaylist.getPlaylistId());
            } else {
                PrimePlaylistUtil.followPrimePlaylist(this.mActivity, new PrimePlaylistDatabaseManager(this.mActivity), 0, this.mPlaylist.getAsin());
            }
            if (this.mDownload) {
                publishProgress(1);
                String playlistId = this.mPlaylist.getAsin() == null ? this.mPlaylist.getPlaylistId() : this.mPlaylist.getAsin();
                Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlistId);
                MusicDownloader.getInstance(this.mActivity).download(contentUri.toString(), contentUri, new NotificationInfo(this.mPlaylist.getTitle(), this.mPlaylist.getDescription(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), playlistId).toString()), true, true);
            }
            return null;
        } catch (AbstractHttpClient.HttpClientException | ServiceException e) {
            Log.error(TAG, "Exception received %s", e.getMessage());
            return null;
        }
    }
}
