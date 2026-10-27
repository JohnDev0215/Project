package com.amazon.mp3.library.job;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class RenamePlaylistJob extends Job {
    private final Application applicationContext;
    private final String mName;
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
        return "RenamePlaylistJob";
    }

    public RenamePlaylistJob(String sourceId, String name, long playlistId, Application applicationContext) {
        this.mSourceId = sourceId;
        this.mName = name;
        this.mPlaylistId = playlistId;
        this.applicationContext = applicationContext;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        ContentResolver contentResolver = this.applicationContext.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.mName);
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(this.mSourceId, this.mPlaylistId);
        int iUpdate = contentResolver.update(contentUri, contentValues, null, null);
        if (iUpdate > 0) {
            PlaylistUtil.refreshPlaylistArt(contentUri);
        }
        return iUpdate == 0 ? 3 : 1;
    }
}
