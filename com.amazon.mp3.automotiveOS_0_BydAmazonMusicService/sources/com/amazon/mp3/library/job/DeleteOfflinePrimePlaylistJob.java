package com.amazon.mp3.library.job;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteOfflinePrimePlaylistJob extends Job {
    private String mPrimePlaylistAsin;
    private String mPrimePlaylistLuid;

    public DeleteOfflinePrimePlaylistJob(String primePlaylistAsin, String primePlaylistLuid) {
        this.mPrimePlaylistAsin = primePlaylistAsin;
        this.mPrimePlaylistLuid = primePlaylistLuid;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws IllegalStateException {
        NowPlayingUtil.clearCollection(AmazonApplication.getContext(), MediaProvider.PrimePlaylists.getContentUri("cirrus-local", this.mPrimePlaylistAsin), PlayStateMutationReason.DELETING_TRACKS);
        PrimePlaylistUtil.deleteOfflinePrimePlaylist(getContext(), this.mPrimePlaylistLuid);
        return 1;
    }
}
