package com.amazon.mp3.library.job;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class UpdatePlaylistJob extends Job {
    private static final String TAG = "UpdatePlaylistJob";
    private final boolean mIsUdoPlaylist;
    private final long mPlaylistId;
    private final String mSourceId;

    @Override // com.amazon.mp3.service.job.Job
    public void cancel() {
    }

    @Override // com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return false;
    }

    @Override // com.amazon.mp3.service.job.Job
    public String toString() {
        return "UpdatePlaylistJob";
    }

    public UpdatePlaylistJob(Uri playlistUri) {
        this.mSourceId = MediaProvider.getSource(playlistUri);
        this.mPlaylistId = PlaylistUtil.getPlaylistId(playlistUri);
        this.mIsUdoPlaylist = MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri);
        Log.debug(TAG, "Stack for UpdatePlaylistJob: ", new Throwable());
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        boolean zPost = CirrusPlaylistScratch.post(getContext(), this.mSourceId, this.mPlaylistId);
        if (this.mIsUdoPlaylist && zPost && "cirrus-local".equals(this.mSourceId)) {
            PlaylistSyncHelper.syncUnifiedPlaylistsToCloud(getContext());
        }
        return zPost ? 1 : 3;
    }
}
