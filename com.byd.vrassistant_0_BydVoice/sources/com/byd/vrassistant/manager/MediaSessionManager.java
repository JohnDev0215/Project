package com.byd.vrassistant.manager;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import cerence.ark.assistant.controller.enumeration.MediaStateEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.RadioAudioDemo.CommonConst;
import com.byd.vrassistant.conversation.onlinemedia.FavoriteCollectListener;
import com.byd.vrassistant.conversation.onlinemedia.GetPlaylistStateListener;
import com.byd.vrassistant.conversation.onlinemedia.MediaInfoListener;
import com.byd.vrassistant.conversation.onlinemedia.MediaMetadataCompatListener;
import com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper;
import com.byd.vrassistant.conversation.onlinemedia.PlaybackStateListener;
import com.byd.vrassistant.utils.RLog;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class MediaSessionManager {
    private static final String ACTION_CHECK_PLAY_LIST_SHOW_STATUS = "checkPlayListShowStatus";
    public static final String METADATA_KEY_FAVORITE_STATE = "android.media.metadata.FAVORITE_STATE";
    public static final String METADATA_KEY_SUPPORT_FAVORITE = "android.media.metadata.SUPPORT_FAVORITE";
    public static final String METADATA_KEY_SUPPORT_MEDIA_SEARCH = "android.media.metadata.SUPPORT_MEDIA_SEARCH";
    public static final String METADATA_KEY_SUPPORT_PLAY_FAVORITE = "android.media.metadata.SUPPORT_PLAY_FAVORITE";
    public static final String METADATA_KEY_SUPPORT_SHOW_PLAY_LIST = "android.media.metadata.SUPPORT_SHOW_PLAY_LIST";
    private static final String TAG = "NEU_MediaSessionManager";
    private static MediaSessionManager instance;
    private MediaInfoListener currentMediaInfoListener;
    private MediaMetadataCompatListener currentMediaMetadataCompatListener;
    private GetPlaylistStateListener currentPlaylistStateListener;
    private FavoriteCollectListener currentfavoriteCollectListener;
    private PlaybackStateListener currentplaybackStateListener;
    MediaBrowserCompat mediaBrowser;
    private Map<String, MediaBrowserCompat> mediaBrowsers = new HashMap();
    private Map<String, MediaControllerCompat> mediaControllers = new HashMap();
    public boolean requestfavioriteflag = false;
    public boolean requestAppInfoflag = false;
    public boolean requestMediaMetadataInfoflag = false;
    public boolean requestPreNextInfoflag = false;
    public boolean requestplaylistflag = false;
    public String recordmediaId = "";
    public boolean issupportFavorit = false;
    public boolean iscurrentCollect = false;
    public boolean currentsupportSkipPrev = false;
    public boolean currentsupportSkipNext = false;
    public boolean currentsupportSetRepeatMode = false;
    public boolean currentsupportSetShuffleMode = false;
    private Context mContext = BydVoiceApplication.getContext();

    private MediaSessionManager() {
    }

    public static synchronized MediaSessionManager get() {
        if (instance == null) {
            instance = new MediaSessionManager();
        }
        return instance;
    }

    public void connectToMediaService(final String str, final CountDownLatch countDownLatch) {
        RLog.d(TAG, "connectToMediaService packageName = " + str);
        if (this.mediaBrowsers.containsKey(str)) {
            return;
        }
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(this.mContext, new ComponentName(str, OnlineMediaHelper.ONLINE_MEDIASESSION_CLASS), new MediaBrowserCompat.ConnectionCallback() { // from class: com.byd.vrassistant.manager.MediaSessionManager.1
            @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
            public void onConnected() {
                RLog.d(MediaSessionManager.TAG, "connectToMediaService onConnected");
                try {
                    MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(MediaSessionManager.this.mContext, MediaSessionManager.this.mediaBrowser.getSessionToken());
                    mediaControllerCompat.registerCallback(MediaSessionManager.this.new CustomControllerCallback(str));
                    MediaSessionManager.this.mediaControllers.put(str, mediaControllerCompat);
                    MediaSessionManager.this.mediaBrowsers.put(str, MediaSessionManager.this.mediaBrowser);
                } catch (Exception e) {
                    Log.e(MediaSessionManager.TAG, "Failed to create media controller", e);
                }
                countDownLatch.countDown();
            }

            @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
            public void onConnectionSuspended() {
                RLog.d(MediaSessionManager.TAG, "Connection suspended for package: " + str);
                countDownLatch.countDown();
            }

            @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
            public void onConnectionFailed() {
                Log.e(MediaSessionManager.TAG, "Connection failed for package: " + str);
                countDownLatch.countDown();
            }
        }, null);
        this.mediaBrowser = mediaBrowserCompat;
        mediaBrowserCompat.connect();
    }

    public boolean isServiceConnected(String str) {
        RLog.d(TAG, "isServiceConnected packageName = " + str);
        MediaBrowserCompat mediaBrowserCompat = this.mediaBrowsers.get(str);
        return mediaBrowserCompat != null && mediaBrowserCompat.isConnected();
    }

    public void play(String str, PlaybackStateListener playbackStateListener) {
        RLog.d(TAG, "play packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().play();
            this.currentplaybackStateListener = playbackStateListener;
        }
    }

    public void pause(String str, PlaybackStateListener playbackStateListener) {
        RLog.d(TAG, "pause packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().pause();
            this.currentplaybackStateListener = playbackStateListener;
        }
    }

    public void stop(String str) {
        RLog.d(TAG, "stop packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().stop();
        }
    }

    public void playPrevious(String str, MediaMetadataCompatListener mediaMetadataCompatListener) {
        RLog.d(TAG, "playPrevious packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().skipToPrevious();
            this.requestPreNextInfoflag = true;
            this.currentMediaMetadataCompatListener = mediaMetadataCompatListener;
        }
    }

    public void playNext(String str, MediaMetadataCompatListener mediaMetadataCompatListener) {
        RLog.d(TAG, "playNext packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().skipToNext();
            this.requestPreNextInfoflag = true;
            this.currentMediaMetadataCompatListener = mediaMetadataCompatListener;
        }
    }

    public void addFavorite(String str, FavoriteCollectListener favoriteCollectListener) {
        RLog.e(TAG, "addFavorite packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            this.requestfavioriteflag = true;
            this.currentfavoriteCollectListener = favoriteCollectListener;
            mediaControllerCompat.getTransportControls().sendCustomAction(CommonConst.addFavorite, (Bundle) null);
        }
    }

    public void removeFavorite(String str, FavoriteCollectListener favoriteCollectListener) {
        RLog.e(TAG, "removeFavorite packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            this.requestfavioriteflag = true;
            this.currentfavoriteCollectListener = favoriteCollectListener;
            mediaControllerCompat.getTransportControls().sendCustomAction(CommonConst.removeFavorite, (Bundle) null);
        }
    }

    public int getCurrentShuffleMode(String str) {
        RLog.e(TAG, "getCurrentShuffleMode packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat == null) {
            return 0;
        }
        return mediaControllerCompat.getShuffleMode();
    }

    public int getCurrentRepeatMode(String str) {
        RLog.e(TAG, "getCurrentRepeatMode packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat == null) {
            return 0;
        }
        return mediaControllerCompat.getRepeatMode();
    }

    public void setRepeatMode(int i, String str) {
        RLog.d(TAG, "setRepeatMode packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().setRepeatMode(i);
        }
    }

    public void setShuffleMode(String str, boolean z) {
        RLog.d(TAG, "setShuffleMode packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().setShuffleMode(z ? 1 : 0);
        }
    }

    public void openOnlineMedia(String str) {
        RLog.d(TAG, "openOnlineMedia packageName = " + str);
        Bundle bundle = new Bundle();
        bundle.putString("source", "current");
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("openOnlineMedia", bundle);
        }
    }

    public void exitMedia(String str) {
        RLog.d(TAG, "exitMedia packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("exit_media", new Bundle());
        }
    }

    public void replay(String str) {
        RLog.d(TAG, "replay packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("replayContent", (Bundle) null);
        }
    }

    public void playFromSearch(String str, String str2, String str3) {
        RLog.d(TAG, "playFromSearch packageName = " + str3);
        Bundle bundle = new Bundle();
        bundle.putString("searchType", str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str3);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().playFromSearch(str2, bundle);
        }
    }

    public void playFavoriteList(String str) {
        RLog.d(TAG, "playFavoriteList packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("playFavorite", (Bundle) null);
        }
    }

    public void rewind(String str) {
        RLog.d(TAG, "rewind packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().rewind();
        }
    }

    public void forward(String str) {
        RLog.d(TAG, "forward packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().fastForward();
        }
    }

    public void requestAppInfo(String str, MediaInfoListener mediaInfoListener) {
        RLog.d(TAG, "requestAppInfo packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            this.requestAppInfoflag = true;
            this.currentMediaInfoListener = mediaInfoListener;
            mediaControllerCompat.getTransportControls().sendCustomAction("requestUpdateAppStatus", (Bundle) null);
        }
    }

    public void requestMediaMetadataInfo(String str, MediaMetadataCompatListener mediaMetadataCompatListener) {
        RLog.d(TAG, "requestMediaMetadataInfo packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            this.requestMediaMetadataInfoflag = true;
            this.currentMediaMetadataCompatListener = mediaMetadataCompatListener;
            mediaControllerCompat.getTransportControls().sendCustomAction("requestUpdateAppStatus", (Bundle) null);
        }
    }

    public MediaMetadataCompat getCurrentMediaMeta(String str) {
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            RLog.d(TAG, "getCurrentMediaMeta ok = " + str);
            return mediaControllerCompat.getMetadata();
        }
        RLog.d(TAG, "getCurrentMediaMeta error = " + str);
        return null;
    }

    public PlaybackStateCompat getCurrentPlaybackState(String str) {
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            RLog.d(TAG, "getCurrentPlaybackState ok = " + str);
            return mediaControllerCompat.getPlaybackState();
        }
        RLog.d(TAG, "getCurrentPlaybackState error = " + str);
        return null;
    }

    public void requestPlayListShowStatus(String str, GetPlaylistStateListener getPlaylistStateListener) {
        RLog.d(TAG, "requestPlayListShowStatus");
        RLog.d(TAG, "requestPlayListShowStatus packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            this.requestplaylistflag = true;
            this.currentPlaylistStateListener = getPlaylistStateListener;
            mediaControllerCompat.getTransportControls().sendCustomAction(ACTION_CHECK_PLAY_LIST_SHOW_STATUS, (Bundle) null);
        }
    }

    public void playListShowHide(boolean z, String str) {
        RLog.d(TAG, "playListShowHide packageName = " + str + ",isShow =" + z);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShow", z);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            mediaControllerCompat.getTransportControls().sendCustomAction("playListShowHide", bundle);
        }
    }

    public String getPlayStateInfo(String str) {
        RLog.d(TAG, "cccccccccccccccc getPlaybackInfo packageName = " + str);
        MediaControllerCompat mediaControllerCompat = this.mediaControllers.get(str);
        if (mediaControllerCompat != null) {
            PlaybackStateCompat playbackState = mediaControllerCompat.getPlaybackState();
            int state = playbackState.getState();
            RLog.d(TAG, "cccccccccccccccc getPlayStateInfo state = " + playbackState.getState());
            return state != 1 ? state != 2 ? (state == 3 || state == 6) ? MediaStateEnum.PLAYING : "UNKNOWN" : MediaStateEnum.PAUSED : MediaStateEnum.STOPPED;
        }
        RLog.d(TAG, "getPlayStateInfo mediaController is null = ");
        return "UNKNOWN";
    }

    private class CustomControllerCallback extends MediaControllerCompat.Callback {
        private String packageName;

        CustomControllerCallback(String str) {
            this.packageName = str;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            MediaSessionManager.this.connectToMediaService(this.packageName, countDownLatch);
            RLog.d(MediaSessionManager.TAG, "onSessionDestroyed: ");
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionEvent(String str, Bundle bundle) {
            RLog.d(MediaSessionManager.TAG, "onSessionEvent: " + str + ", result: " + (bundle == null ? "null" : bundle));
            if (str.equals("updateAppStatus")) {
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: packageName==" + bundle.getString("packageName"));
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: appName==" + bundle.getString("appName"));
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: connectDescription==" + bundle.getString("connectDescription"));
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: playState==" + bundle.getInt("playState"));
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: isLogin==" + bundle.getInt("isLogin"));
                if (MediaSessionManager.this.currentMediaInfoListener == null || !MediaSessionManager.this.requestAppInfoflag) {
                    return;
                }
                MediaSessionManager.this.currentMediaInfoListener.onMediaInfoGet(bundle);
                MediaSessionManager.this.currentMediaInfoListener = null;
                MediaSessionManager.this.requestAppInfoflag = false;
                return;
            }
            if (MediaSessionManager.ACTION_CHECK_PLAY_LIST_SHOW_STATUS.equals(str) && bundle != null && bundle.containsKey(MediaSessionManager.ACTION_CHECK_PLAY_LIST_SHOW_STATUS)) {
                boolean z = bundle.getBoolean(MediaSessionManager.ACTION_CHECK_PLAY_LIST_SHOW_STATUS, false);
                RLog.d(MediaSessionManager.TAG, "onSessionEvent: ACTION_CHECK_PLAY_LIST_SHOW_STATUS " + z);
                if (MediaSessionManager.this.currentPlaylistStateListener == null || !MediaSessionManager.this.requestplaylistflag) {
                    return;
                }
                MediaSessionManager.this.currentPlaylistStateListener.onPlaylistState(z);
                MediaSessionManager.this.currentPlaylistStateListener = null;
                MediaSessionManager.this.requestplaylistflag = false;
                return;
            }
            str.equals("playListShowHide");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat == null) {
                RLog.e(MediaSessionManager.TAG, "onMetadataChanged: metadata null");
                return;
            }
            String string = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            String string2 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
            String string3 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
            RLog.d(MediaSessionManager.TAG, "onMetadataChanged: mediaId：" + string + "\nalbum：" + string2 + "\nartist：" + mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) + "\ntitle：" + string3 + "\niconPath：" + mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI) + "\niconPath2：" + mediaMetadataCompat.getDescription().getIconUri());
            boolean z = mediaMetadataCompat.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_FAVORITE) == 1;
            MediaSessionManager.this.issupportFavorit = z;
            boolean z2 = mediaMetadataCompat.getLong(MediaSessionManager.METADATA_KEY_FAVORITE_STATE) == 1;
            MediaSessionManager.this.iscurrentCollect = z2;
            Log.e(MediaSessionManager.TAG, "onMetadataChanged: supportFavorite " + z + ", isCollect " + z2 + ", supportPlayFavorite " + (mediaMetadataCompat.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_PLAY_FAVORITE) == 1) + ", isSupportMediaSearch " + (mediaMetadataCompat.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_MEDIA_SEARCH) == 1) + ", isSupportShowPlayList " + (mediaMetadataCompat.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_SHOW_PLAY_LIST) == 1));
            if (MediaSessionManager.this.recordmediaId != string && MediaSessionManager.this.currentMediaMetadataCompatListener != null && MediaSessionManager.this.requestPreNextInfoflag) {
                MediaSessionManager.this.recordmediaId = string;
                Log.e(MediaSessionManager.TAG, "currentMediaMetadataCompatListener: for next previous");
                MediaSessionManager.this.currentMediaMetadataCompatListener.onMetadataChanged(mediaMetadataCompat);
                MediaSessionManager.this.currentMediaMetadataCompatListener = null;
                MediaSessionManager.this.requestPreNextInfoflag = false;
                return;
            }
            if (!MediaSessionManager.this.requestMediaMetadataInfoflag || MediaSessionManager.this.currentMediaMetadataCompatListener == null) {
                if (MediaSessionManager.this.currentfavoriteCollectListener == null || !MediaSessionManager.this.requestfavioriteflag) {
                    return;
                }
                Log.e(MediaSessionManager.TAG, "currentfavoriteCollectListener: for favorite");
                MediaSessionManager.this.currentfavoriteCollectListener.onFavoriteChanged(MediaSessionManager.this.iscurrentCollect);
                MediaSessionManager.this.currentfavoriteCollectListener = null;
                MediaSessionManager.this.requestfavioriteflag = false;
                return;
            }
            Log.e(MediaSessionManager.TAG, "currentMediaMetadataCompatListener: for requestMediaMetadataInfo");
            MediaSessionManager.this.currentMediaMetadataCompatListener.onMetadataChanged(mediaMetadataCompat);
            MediaSessionManager.this.currentMediaMetadataCompatListener = null;
            MediaSessionManager.this.requestMediaMetadataInfoflag = false;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat != null) {
                if (MediaSessionManager.this.currentplaybackStateListener != null) {
                    Log.e(MediaSessionManager.TAG, "currentplaybackStateListener: is not null success");
                    MediaSessionManager.this.currentplaybackStateListener.onStateChanged(playbackStateCompat);
                    MediaSessionManager.this.currentplaybackStateListener = null;
                } else {
                    Log.e(MediaSessionManager.TAG, "currentplaybackStateListener: is null error");
                }
                RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: state = " + playbackStateCompat.getState() + ", errorCode = " + playbackStateCompat.getErrorCode());
                if (playbackStateCompat.getState() == 3) {
                    RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: 已播放1 = ");
                } else if (playbackStateCompat.getState() == 2) {
                    RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: 已暂停1 = ");
                } else if (playbackStateCompat.getState() == 7) {
                    RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: 播放出错1 = ");
                } else if (playbackStateCompat.getState() == 1) {
                    RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: 播放停止 = ");
                } else if (playbackStateCompat.getState() == 6) {
                    RLog.d(MediaSessionManager.TAG, "onPlaybackStateChanged: 缓存中 = ");
                }
                boolean z = (playbackStateCompat.getActions() & 32) != 0;
                MediaSessionManager.this.currentsupportSkipNext = z;
                boolean z2 = (playbackStateCompat.getActions() & 16) != 0;
                MediaSessionManager.this.currentsupportSkipPrev = z2;
                Log.e(MediaSessionManager.TAG, "onPlaybackStateChanged: support SkipNext " + z + ", supportSkipPrev " + z2 + ", supportPlay " + ((playbackStateCompat.getActions() & 4) != 0) + ", supportPause " + ((playbackStateCompat.getActions() & 2) != 0) + ", supportStop " + ((playbackStateCompat.getActions() & 1) != 0) + ", supportRewind " + ((playbackStateCompat.getActions() & 8) != 0) + ", supportFastForward " + ((playbackStateCompat.getActions() & 64) != 0) + ", supportSeekTo " + ((playbackStateCompat.getActions() & 256) != 0) + ", supportSetRepeatMode " + ((playbackStateCompat.getActions() & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) + ", supportSetShuffleMode " + ((playbackStateCompat.getActions() & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) != 0));
                return;
            }
            Log.e(MediaSessionManager.TAG, "onPlaybackStateChanged: state error");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionReady() {
            super.onSessionReady();
            RLog.d(MediaSessionManager.TAG, "onSessionReady");
        }
    }

    public void disconnect(String str) {
        RLog.d(TAG, "disconnect");
        MediaBrowserCompat mediaBrowserCompat = this.mediaBrowsers.get(str);
        if (mediaBrowserCompat == null || !mediaBrowserCompat.isConnected()) {
            return;
        }
        mediaBrowserCompat.disconnect();
        this.mediaBrowsers.remove(str);
        this.mediaControllers.remove(str);
        this.currentplaybackStateListener = null;
        this.currentMediaMetadataCompatListener = null;
        this.currentfavoriteCollectListener = null;
        this.currentMediaInfoListener = null;
        this.recordmediaId = "";
        this.issupportFavorit = false;
        this.iscurrentCollect = false;
        this.currentsupportSkipPrev = false;
        this.currentsupportSkipNext = false;
        this.currentsupportSetRepeatMode = false;
        this.currentsupportSetShuffleMode = false;
    }
}
