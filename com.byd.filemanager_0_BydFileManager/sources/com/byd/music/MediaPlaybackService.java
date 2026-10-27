package com.byd.music;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlaybackService extends MediaBrowserService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MediaSession f3905b;

    @Override // android.service.media.MediaBrowserService, android.app.Service
    public void onCreate() {
        super.onCreate();
        MediaSession mediaSession = new MediaSession(this, "MediaPlaybackService");
        this.f3905b = mediaSession;
        mediaSession.setFlags(3);
        this.f3905b.setPlaybackState(new PlaybackState.Builder().setActions(516L).build());
        setSessionToken(this.f3905b.getSessionToken());
        Context applicationContext = getApplicationContext();
        this.f3905b.setSessionActivity(PendingIntent.getActivity(applicationContext, 99, new Intent(applicationContext, (Class<?>) MusicBrowserActivity.class), 134217728));
    }

    @Override // android.app.Service
    public void onDestroy() {
    }

    @Override // android.service.media.MediaBrowserService
    public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        return null;
    }

    @Override // android.service.media.MediaBrowserService
    public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
        result.sendResult(null);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
