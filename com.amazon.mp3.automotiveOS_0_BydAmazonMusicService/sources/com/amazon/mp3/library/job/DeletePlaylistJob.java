package com.amazon.mp3.library.job;

import android.app.Application;
import android.net.Uri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class DeletePlaylistJob extends Job {
    private final Uri mPlaylistUri;

    @Override // com.amazon.mp3.service.job.Job
    public void cancel() {
    }

    @Override // com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return false;
    }

    @Override // com.amazon.mp3.service.job.Job
    public String toString() {
        return "DeletePlaylistJob";
    }

    public DeletePlaylistJob(String sourceId, long playlistId) {
        this.mPlaylistUri = MediaProvider.UdoPlaylists.getContentUri(sourceId, playlistId);
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        Application context = getContext();
        Uri uriBuild = this.mPlaylistUri.buildUpon().appendEncodedPath("tracks").build();
        NowPlayingUtil.clearCollection(context, uriBuild, PlayStateMutationReason.DELETING_TRACKS);
        MusicDownloader.getInstance(context).cancel(uriBuild.toString());
        return getContext().getContentResolver().delete(this.mPlaylistUri, null, null) == 0 ? 3 : 1;
    }
}
