package com.byd.music.ThirdSDK.fce;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.byd.music.OnLineMusic;
import com.byd.music.beans.MultiMusicBean;
import com.byd.music.beans.SampleMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class CommonFceManager {
    private static final String TAG = "FceManager";
    private Context mContext;
    private int mEngineType;
    private MediaBrowserCompat mMediaBrowser;
    private MediaControllerCompat mMediaController;
    private String mPackageName;
    private String mServiceName;
    private int sdkInitializationDelayBusiness;
    private MediaControllerCompat.TransportControls transportControls;
    volatile long lastConnectTiming = 0;
    private final MediaBrowserCompat.ConnectionCallback mMediaBrowserConnectionCallback = new MediaBrowserCompat.ConnectionCallback() { // from class: com.byd.music.ThirdSDK.fce.CommonFceManager.1
        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            LogUtil.d(CommonFceManager.TAG, "onConnected: enter");
            try {
                CommonFceManager.this.mMediaController = new MediaControllerCompat(CommonFceManager.this.mContext, CommonFceManager.this.mMediaBrowser.getSessionToken());
                CommonFceManager.this.mMediaController.registerCallback(CommonFceManager.this.mMediaControllerCallback);
                CommonFceManager.this.transportControls = CommonFceManager.this.mMediaController.getTransportControls();
            } catch (Throwable th) {
                LogUtil.e(CommonFceManager.TAG, "onConnected: Throwable e = " + th.getMessage());
                th.printStackTrace();
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            LogUtil.e(CommonFceManager.TAG, "onConnectionFailed: ");
            CommonFceManager.this.deInit();
        }
    };
    private final MediaControllerCompat.Callback mMediaControllerCallback = new AnonymousClass2();

    public void reset(Context context, String str, String str2, int i) {
        if (TextUtils.equals(str, this.mPackageName) && TextUtils.equals(str2, this.mServiceName) && this.mEngineType == i) {
            LogUtil.d(TAG, "reset: enter but double trip, just return");
            return;
        }
        LogUtil.d(TAG, "reset: enter");
        deInit();
        init(context, str, str2, i);
    }

    public void deInit() {
        LogUtil.d(TAG, "deInit: enter");
        if (Looper.getMainLooper().isCurrentThread()) {
            this.transportControls = null;
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat != null) {
                try {
                    if (mediaBrowserCompat.isConnected()) {
                        this.mMediaBrowser.disconnect();
                    }
                } catch (Throwable unused) {
                }
                this.mMediaBrowser = null;
            }
            MediaControllerCompat mediaControllerCompat = this.mMediaController;
            if (mediaControllerCompat != null) {
                try {
                    mediaControllerCompat.unregisterCallback(this.mMediaControllerCallback);
                } catch (Throwable unused2) {
                }
                this.mMediaController = null;
            }
            this.lastConnectTiming = 0L;
            return;
        }
        new Throwable("MediaBrowserCompat deInit only can by main thread");
    }

    public void init(Context context, String str, String str2, int i) {
        LogUtil.d(TAG, "init: enter, isInited = " + this.lastConnectTiming);
        if (System.currentTimeMillis() - this.lastConnectTiming < JobInfo.MIN_BACKOFF_MILLIS) {
            return;
        }
        this.mContext = context;
        this.mEngineType = i;
        this.mPackageName = str;
        this.mServiceName = str2;
        if (Looper.getMainLooper().isCurrentThread()) {
            try {
                MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(context, new ComponentName(this.mPackageName, this.mServiceName), this.mMediaBrowserConnectionCallback, null);
                this.mMediaBrowser = mediaBrowserCompat;
                if (!mediaBrowserCompat.isConnected()) {
                    LogUtil.d(TAG, "init: go to connect");
                    this.mMediaBrowser.connect();
                }
                this.lastConnectTiming = System.currentTimeMillis();
                return;
            } catch (Throwable th) {
                LogUtil.d(TAG, "init: error.." + th.getMessage());
                th.printStackTrace();
                return;
            }
        }
        new Throwable("MediaBrowserCompat init only can by main thread");
    }

    private void initRetryConnect() {
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.ThirdSDK.fce.-$$Lambda$CommonFceManager$c7E9omyUuH8flsVpII9P1nLgNc8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initRetryConnect$0$CommonFceManager();
            }
        });
    }

    public /* synthetic */ void lambda$initRetryConnect$0$CommonFceManager() {
        init(this.mContext, this.mPackageName, this.mServiceName, this.mEngineType);
    }

    /* JADX INFO: renamed from: com.byd.music.ThirdSDK.fce.CommonFceManager$2, reason: invalid class name */
    class AnonymousClass2 extends MediaControllerCompat.Callback {
        AnonymousClass2() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            if (CommonFceManager.this.mContext != null) {
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.ThirdSDK.fce.-$$Lambda$CommonFceManager$2$-waGSPjmDWrQ-WA8S6L5ALLV3mw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSessionDestroyed$0$CommonFceManager$2();
                    }
                });
            }
            LogUtil.d(CommonFceManager.TAG, "onSessionDestroyed: ");
        }

        public /* synthetic */ void lambda$onSessionDestroyed$0$CommonFceManager$2() {
            CommonFceManager.this.deInit();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionEvent(String str, Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            sb.append("onSessionEvent: ");
            sb.append(str);
            sb.append(", result: ");
            Object obj = bundle;
            if (bundle == null) {
                obj = "null";
            }
            sb.append(obj);
            LogUtil.d(CommonFceManager.TAG, sb.toString());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                if (OnLineMusic.musicEngine != CommonFceManager.this.mEngineType) {
                    return;
                }
                String string = mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID");
                String string2 = mediaMetadataCompat.getString("android.media.metadata.ALBUM");
                String string3 = mediaMetadataCompat.getString("android.media.metadata.TITLE");
                String string4 = mediaMetadataCompat.getString("android.media.metadata.ARTIST");
                String string5 = mediaMetadataCompat.getString("android.media.metadata.DISPLAY_ICON_URI");
                long j = mediaMetadataCompat.getLong("android.media.metadata.DURATION");
                Uri iconUri = mediaMetadataCompat.getDescription().getIconUri();
                MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
                if (multiMusicBean != null && (multiMusicBean.getEntity() instanceof SampleMusicBean)) {
                    SampleMusicBean sampleMusicBean = (SampleMusicBean) multiMusicBean.getEntity();
                    if (Objects.equals(string3, sampleMusicBean.getTitle()) && Objects.equals(string4, sampleMusicBean.getArtist()) && Objects.equals(string5, sampleMusicBean.getAlbum()) && j == sampleMusicBean.getDuration()) {
                        return;
                    }
                }
                LogUtil.d(CommonFceManager.TAG, "onMetadataChanged: mediaId：" + string + "\nalbum：" + string2 + "\nartist：" + string4 + "\ntitle：" + string3 + "\niconPath：" + string5 + "\niconPath2：" + iconUri);
                SampleMusicBean sampleMusicBean2 = new SampleMusicBean(string3, string4, iconUri == null ? "" : iconUri.toString(), j);
                CurrentMediaInfoUtils.getInstance().maxProgress.set(j);
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(sampleMusicBean2));
                return;
            }
            LogUtil.e(CommonFceManager.TAG, "onMetadataChanged: metadata null");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat == null) {
                LogUtil.e(CommonFceManager.TAG, "onPlaybackStateChanged: state error");
                return;
            }
            LogUtil.d(CommonFceManager.TAG, "onPlaybackStateChanged: state = " + playbackStateCompat.getState() + ", errorCode = " + playbackStateCompat.getErrorCode());
            if (OnLineMusic.musicEngine != CommonFceManager.this.mEngineType) {
                return;
            }
            long j = CurrentMediaInfoUtils.getInstance().curProgress.get();
            long position = playbackStateCompat.getPosition();
            if (position != j) {
                long j2 = CurrentMediaInfoUtils.getInstance().maxProgress.get();
                CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(position, j2, j2);
            }
            if (playbackStateCompat.getState() == 3) {
                CurrentMediaInfoUtils.getInstance().setPlayingState(true);
            } else {
                CurrentMediaInfoUtils.getInstance().setPlayingState(false);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionReady() {
            super.onSessionReady();
            LogUtil.d(CommonFceManager.TAG, "onSessionReady");
            if (OnLineMusic.musicEngine != CommonFceManager.this.mEngineType) {
                return;
            }
            MediaMetadataCompat metadata = CommonFceManager.this.mMediaController.getMetadata();
            if (metadata == null) {
                CurrentMediaInfoUtils.getInstance().maxProgress.set(0L);
                CurrentMediaInfoUtils.getInstance().curProgress.set(0L);
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(null));
                return;
            }
            String string = metadata.getString("android.media.metadata.MEDIA_ID");
            String string2 = metadata.getString("android.media.metadata.ALBUM");
            String string3 = metadata.getString("android.media.metadata.TITLE");
            String string4 = metadata.getString("android.media.metadata.ARTIST");
            String string5 = metadata.getString("android.media.metadata.DISPLAY_ICON_URI");
            long j = metadata.getLong("android.media.metadata.DURATION");
            Uri iconUri = metadata.getDescription().getIconUri();
            MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
            if (multiMusicBean != null && (multiMusicBean.getEntity() instanceof SampleMusicBean)) {
                SampleMusicBean sampleMusicBean = (SampleMusicBean) multiMusicBean.getEntity();
                if (Objects.equals(string3, sampleMusicBean.getTitle()) && Objects.equals(string4, sampleMusicBean.getArtist()) && Objects.equals(string5, sampleMusicBean.getAlbum()) && j == sampleMusicBean.getDuration()) {
                    return;
                }
            }
            LogUtil.d(CommonFceManager.TAG, "onSessionReady: mediaId：" + string + "\nalbum：" + string2 + "\nartist：" + string4 + "\ntitle：" + string3 + "\niconPath：" + string5 + "\niconPath2：" + iconUri);
            SampleMusicBean sampleMusicBean2 = new SampleMusicBean(string3, string4, iconUri == null ? "" : iconUri.toString(), j);
            CurrentMediaInfoUtils.getInstance().maxProgress.set(j);
            CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(sampleMusicBean2));
            int i = CommonFceManager.this.sdkInitializationDelayBusiness;
            if (i == 1) {
                CommonFceManager.this.play();
            } else if (i == 2) {
                CommonFceManager.this.playNext();
            } else if (i == 3) {
                CommonFceManager.this.playPrevious();
            }
            CommonFceManager.this.sdkInitializationDelayBusiness = 0;
        }
    }

    public void playOrStop(boolean z) {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "playOrStop enter but transportControls null");
            if (z) {
                this.sdkInitializationDelayBusiness = 1;
            }
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "playOrStop enter");
        if (z) {
            this.transportControls.play();
        } else {
            this.transportControls.pause();
        }
    }

    public void play() {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "play enter but transportControls null");
            this.sdkInitializationDelayBusiness = 1;
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "play enter");
        this.transportControls.play();
    }

    public void pause() {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "pause enter but transportControls null");
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "pause enter");
        this.transportControls.pause();
    }

    public void playNext() {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "playNext enter but transportControls null");
            this.sdkInitializationDelayBusiness = 2;
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "playNext enter");
        this.transportControls.skipToNext();
    }

    public void playPrevious() {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "playPrevious enter but transportControls null");
            this.sdkInitializationDelayBusiness = 3;
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "playPrevious enter");
        this.transportControls.skipToPrevious();
    }

    public void requestAppInfo() {
        if (this.transportControls == null) {
            LogUtil.d(TAG, "requestAppInfo enter but transportControls null");
            MediaBrowserCompat mediaBrowserCompat = this.mMediaBrowser;
            if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
                initRetryConnect();
                return;
            }
            return;
        }
        LogUtil.d(TAG, "requestAppInfo enter");
        MediaMetadataCompat metadata = this.mMediaController.getMetadata();
        if (metadata == null) {
            CurrentMediaInfoUtils.getInstance().maxProgress.set(0L);
            CurrentMediaInfoUtils.getInstance().curProgress.set(0L);
            CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(null));
            return;
        }
        String string = metadata.getString("android.media.metadata.MEDIA_ID");
        String string2 = metadata.getString("android.media.metadata.ALBUM");
        String string3 = metadata.getString("android.media.metadata.TITLE");
        String string4 = metadata.getString("android.media.metadata.ARTIST");
        String string5 = metadata.getString("android.media.metadata.DISPLAY_ICON_URI");
        long j = metadata.getLong("android.media.metadata.DURATION");
        Uri iconUri = metadata.getDescription().getIconUri();
        MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
        if (multiMusicBean != null && (multiMusicBean.getEntity() instanceof SampleMusicBean)) {
            SampleMusicBean sampleMusicBean = (SampleMusicBean) multiMusicBean.getEntity();
            if (Objects.equals(string3, sampleMusicBean.getTitle()) && Objects.equals(string4, sampleMusicBean.getArtist()) && Objects.equals(string5, sampleMusicBean.getAlbum()) && j == sampleMusicBean.getDuration()) {
                return;
            }
        }
        LogUtil.d(TAG, "onSessionReady: mediaId：" + string + "\nalbum：" + string2 + "\nartist：" + string4 + "\ntitle：" + string3 + "\niconPath：" + string5 + "\niconPath2：" + iconUri);
        SampleMusicBean sampleMusicBean2 = new SampleMusicBean(string3, string4, iconUri == null ? "" : iconUri.toString(), j);
        CurrentMediaInfoUtils.getInstance().maxProgress.set(j);
        CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(sampleMusicBean2));
    }
}
