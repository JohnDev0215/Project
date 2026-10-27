package com.vivid.sdk.spotify;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.vivid.sdk.music.MediaManager;
import com.vivid.sdk.music.RequestCallBack;
import com.vivid.sdk.music.ResponseCode;
import com.vivid.spotify.IMediaInfoControllerCallback;
import com.vivid.spotify.IRequestCallBack;
import com.vivid.spotify.ISpotifyMedia;
import com.vivid.spotify.SearchParam;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class SpotifyMediaManager extends MediaManager {
    private IMediaInfoControllerCallback.Stub mMediaInfoControllerCallBack;
    private ISpotifyMedia mMediaService;
    private Metadata mMetadata;
    private Bundle mPlayState;
    private long mPosition;
    private CopyOnWriteArrayList<MediaControllerCallBack> mediaControllerCallbacks;

    @Override // com.vivid.sdk.music.MediaManager
    protected String getServiceAction() {
        return "com.vivid.spotify.service.action.MediaManagerService";
    }

    @Override // com.vivid.sdk.music.MediaManager
    protected String getServiceClassName() {
        return "com.vivid.spotify.service.MediaManagerService";
    }

    private SpotifyMediaManager() {
        this.mediaControllerCallbacks = new CopyOnWriteArrayList<>();
        this.mMediaInfoControllerCallBack = new IMediaInfoControllerCallback.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.1
            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onPlaybackStateChanged(Bundle bundle) throws RemoteException {
                SpotifyMediaManager.this.mPlayState = bundle;
                SpotifyMediaManager spotifyMediaManager = SpotifyMediaManager.this;
                spotifyMediaManager.notifyPlaybackStateChanged(spotifyMediaManager.mPlayState);
            }

            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onMetadataChanged(String str, String str2, String str3, long j) throws RemoteException {
                SpotifyMediaManager.this.mMetadata = new Metadata(str, str2, str3, j);
                SpotifyMediaManager spotifyMediaManager = SpotifyMediaManager.this;
                spotifyMediaManager.notifyMetadataChanged(spotifyMediaManager.mMetadata);
            }

            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onPositionChanged(long j) throws RemoteException {
                SpotifyMediaManager.this.mPosition = j;
                SpotifyMediaManager spotifyMediaManager = SpotifyMediaManager.this;
                spotifyMediaManager.notifyPositionChange(spotifyMediaManager.mPosition);
            }
        };
    }

    public static SpotifyMediaManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final SpotifyMediaManager INSTANCE = new SpotifyMediaManager();

        private Holder() {
        }
    }

    @Override // com.vivid.sdk.music.MediaManager, android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mMediaService = ISpotifyMedia.Stub.asInterface(iBinder);
        try {
            Log.i(this.TAG, "addMediaControllerCallback");
            this.mMediaService.addMediaControllerCallback(this.mMediaInfoControllerCallBack);
            super.onServiceConnected(componentName, iBinder);
        } catch (RemoteException e) {
            Log.e(this.TAG, "addMediaControllerCallback error " + e);
            destroy();
        }
    }

    @Override // com.vivid.sdk.music.MediaManager, android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        super.onServiceDisconnected(componentName);
        clearData();
    }

    public Drawable getAppIcon() {
        Log.i(this.TAG, "getAppIcon");
        try {
            PackageManager packageManager = this.context.getPackageManager();
            return packageManager.getApplicationInfo(this.mTargetPackageName, 0).loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.TAG, "getAppIcon " + e);
            return null;
        }
    }

    public String getAppName() {
        Log.i(this.TAG, "getAppName");
        try {
            return this.mMediaService.getAppName();
        } catch (RemoteException e) {
            Log.e(this.TAG, "getAppName error  " + e);
            return "";
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void play(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.play(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.2
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "1 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "1 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "1 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void pause(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.pause(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.3
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "2 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "2 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "2 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void stop(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.stop(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.4
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "3 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "3 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "3 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void resume(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.resume(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.5
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "4 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "4 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "4 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void skipToPrevious(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.skipToPrevious(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.6
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "5 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "5 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "5 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void skipToNext(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.skipToNext(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.7
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "6 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "6 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "6 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void addFavorite(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.addFavorite(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.8
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "7 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "7 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "7 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    @Override // com.vivid.sdk.music.MediaManager
    public void removeFavorite(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.removeFavorite(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.9
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "8 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "8 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "8 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void setRepeatMode(RepeatMode repeatMode, final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.setRepeatMode(repeatMode.getCode(), new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.10
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "9 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "9 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "9 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void setShuffleMode(ShuffleMode shuffleMode, final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.setShuffleMode(shuffleMode.getCode(), new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.11
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "10 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "10 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "10 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void replay(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.replay(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.12
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "11 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "11 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "11 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void openPlayList(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.openPlayList(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.13
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "12 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "12 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "12 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void closePlayList(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.closePlayList(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.14
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "13 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "13 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "13 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void moveAppToFront(final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            try {
                this.mMediaService.moveAppToFront(new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.15
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "14 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "14 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "14 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public void playFromSearch(String str, SearchParam searchParam, final RequestCallBack requestCallBack) {
        if (checkServiceReady(requestCallBack)) {
            if (str == null) {
                str = "";
            }
            if (searchParam == null) {
                searchParam = SearchParam.Builder.buildEmptyParam().build();
            }
            try {
                this.mMediaService.playFromSearch(str, searchParam, new IRequestCallBack.Stub() { // from class: com.vivid.sdk.spotify.SpotifyMediaManager.16
                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onSuccess() throws RemoteException {
                        Log.i(SpotifyMediaManager.this.TAG, "16 success");
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onSuccess();
                        }
                    }

                    @Override // com.vivid.spotify.IRequestCallBack
                    public void onError(int i) throws RemoteException {
                        Log.e(SpotifyMediaManager.this.TAG, "16 error  errorCode = " + i);
                        RequestCallBack requestCallBack2 = requestCallBack;
                        if (requestCallBack2 != null) {
                            requestCallBack2.onError(SpotifyMediaManager.this.convertErrorCode(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(this.TAG, "16 error  " + e);
                if (requestCallBack != null) {
                    requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public PlayState getPlayState() {
        if (!checkServiceReady(null)) {
            return PlayState.STATE_ERROR;
        }
        try {
            int playState = this.mMediaService.getPlayState();
            Log.i(this.TAG, "get play state is " + playState);
            return PlayState.getPlayState(playState);
        } catch (RemoteException e) {
            Log.e(this.TAG, "getPlayState error  " + e);
            return PlayState.STATE_ERROR;
        }
    }

    public boolean addMediaControllerCallback(MediaControllerCallBack mediaControllerCallBack) {
        Log.i(this.TAG, "addMediaControllerCallback mediaControllerCallBack is null " + (mediaControllerCallBack == null));
        if (mediaControllerCallBack == null) {
            return false;
        }
        boolean zAddIfAbsent = this.mediaControllerCallbacks.addIfAbsent(mediaControllerCallBack);
        if (zAddIfAbsent) {
            notifyPlaybackStateChanged(mediaControllerCallBack);
            notifyMetadataChanged(mediaControllerCallBack);
            notifyPositionChange(mediaControllerCallBack);
        }
        return zAddIfAbsent;
    }

    public boolean removeMediaControllerCallback(MediaControllerCallBack mediaControllerCallBack) {
        Log.i(this.TAG, "removeMediaControllerCallback mediaControllerCallBack is null " + (mediaControllerCallBack == null));
        if (mediaControllerCallBack == null) {
            return false;
        }
        return this.mediaControllerCallbacks.remove(mediaControllerCallBack);
    }

    private void notifyPositionChange(MediaControllerCallBack mediaControllerCallBack) {
        mediaControllerCallBack.onPositionChanged(this.mPosition);
    }

    private void notifyMetadataChanged(MediaControllerCallBack mediaControllerCallBack) {
        mediaControllerCallBack.onMetadataChanged(this.mMetadata);
    }

    private void notifyPlaybackStateChanged(MediaControllerCallBack mediaControllerCallBack) {
        mediaControllerCallBack.onPlaybackStateChanged(this.mPlayState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMetadataChanged(Metadata metadata) {
        Iterator<MediaControllerCallBack> it = this.mediaControllerCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onMetadataChanged(metadata);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPositionChange(long j) {
        Iterator<MediaControllerCallBack> it = this.mediaControllerCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPositionChanged(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPlaybackStateChanged(Bundle bundle) {
        Iterator<MediaControllerCallBack> it = this.mediaControllerCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackStateChanged(bundle);
        }
    }

    private boolean checkServiceReady(RequestCallBack requestCallBack) {
        Log.i(this.TAG, "checkServiceReady");
        boolean zIsServiceConnected = isServiceConnected();
        if (!zIsServiceConnected) {
            Log.e(this.TAG, "service disconnected");
            if (requestCallBack != null) {
                requestCallBack.onError(ResponseCode.SERVICE_DISCONNECTED);
            }
        }
        return zIsServiceConnected;
    }

    @Override // com.vivid.sdk.music.MediaManager
    public boolean isServiceConnected() {
        return this.mMediaService != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResponseCode convertErrorCode(int i) {
        switch (i) {
            case 1:
                return ResponseCode.SPOTIFY_NOT_AVAILABLE;
            case 2:
                return ResponseCode.USER_NOT_LOGIN;
            case 3:
                return ResponseCode.MODE_UNSUPPORT;
            case 4:
                return ResponseCode.CURRENT_MEDIA_IS_NULL;
            case 5:
            default:
                return ResponseCode.UNKNOW_ERROR;
            case 6:
                return ResponseCode.MEDIA_UNSUPPORT_FAVORIT;
            case 7:
                return ResponseCode.REQUEST_TIMEOUT;
            case 8:
                return ResponseCode.NO_NEXT_SONG;
            case 9:
                return ResponseCode.MEDIA_IS_COLLECTED;
            case 10:
                return ResponseCode.MEDIA_IS_NOT_COLLECTED;
            case 11:
                return ResponseCode.REPEAT_SET_MODE;
            case 12:
                return ResponseCode.UNSUPPORT_OPERATION;
            case 13:
                return ResponseCode.NO_PREV_SONG;
        }
    }

    private void clearData() {
        this.mPosition = 0L;
        this.mMetadata = null;
        this.mPlayState = null;
    }
}
