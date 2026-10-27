package android.support.v4.media;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.mediacompat.Rating2;
import android.support.v4.media.IMediaSession2;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaLibraryService2;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class MediaSession2Stub extends IMediaSession2.Stub {
    private static final boolean DEBUG = true;
    private static final String TAG = "MediaSession2Stub";
    private static final SparseArray<SessionCommand2> sCommandsForOnCommandRequest = new SparseArray<>();
    final Context mContext;
    final MediaSession2.SupportLibraryImpl mSession;
    private final Object mLock = new Object();
    private final ArrayMap<IBinder, MediaSession2.ControllerInfo> mControllers = new ArrayMap<>();
    private final Set<IBinder> mConnectingControllers = new HashSet();
    private final ArrayMap<MediaSession2.ControllerInfo, SessionCommandGroup2> mAllowedCommandGroupMap = new ArrayMap<>();

    @FunctionalInterface
    private interface SessionRunnable {
        void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException;
    }

    static {
        SessionCommandGroup2 group = new SessionCommandGroup2();
        group.addAllPlaybackCommands();
        group.addAllPlaylistCommands();
        group.addAllVolumeCommands();
        Set<SessionCommand2> commands = group.getCommands();
        for (SessionCommand2 command : commands) {
            sCommandsForOnCommandRequest.append(command.getCommandCode(), command);
        }
    }

    MediaSession2Stub(MediaSession2.SupportLibraryImpl session) {
        this.mSession = session;
        this.mContext = session.getContext();
    }

    List<MediaSession2.ControllerInfo> getConnectedControllers() {
        ArrayList<MediaSession2.ControllerInfo> controllers = new ArrayList<>();
        synchronized (this.mLock) {
            for (int i = 0; i < this.mControllers.size(); i++) {
                controllers.add(this.mControllers.valueAt(i));
            }
        }
        return controllers;
    }

    void setAllowedCommands(MediaSession2.ControllerInfo controller, SessionCommandGroup2 commands) {
        synchronized (this.mLock) {
            this.mAllowedCommandGroupMap.put(controller, commands);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAllowedCommand(MediaSession2.ControllerInfo controller, SessionCommand2 command) throws Throwable {
        synchronized (this.mLock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                SessionCommandGroup2 allowedCommands = this.mAllowedCommandGroupMap.get(controller);
                if (allowedCommands == null || !allowedCommands.hasCommand(command)) {
                    return false;
                }
                return DEBUG;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAllowedCommand(MediaSession2.ControllerInfo controller, int commandCode) throws Throwable {
        synchronized (this.mLock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                SessionCommandGroup2 allowedCommands = this.mAllowedCommandGroupMap.get(controller);
                if (allowedCommands == null || !allowedCommands.hasCommand(commandCode)) {
                    return false;
                }
                return DEBUG;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private void onSessionCommand(IMediaController2 caller, int commandCode, SessionRunnable runnable) throws Throwable {
        onSessionCommandInternal(caller, null, commandCode, runnable);
    }

    private void onSessionCommand(IMediaController2 caller, SessionCommand2 sessionCommand, SessionRunnable runnable) throws Throwable {
        onSessionCommandInternal(caller, sessionCommand, 0, runnable);
    }

    private void onSessionCommandInternal(IMediaController2 caller, final SessionCommand2 sessionCommand, final int commandCode, final SessionRunnable runnable) throws Throwable {
        synchronized (this.mLock) {
            MediaSession2.ControllerInfo controller = null;
            try {
                if (caller != null) {
                    try {
                        controller = this.mControllers.get(caller.asBinder());
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                if (this.mSession.isClosed() || controller == null) {
                    return;
                }
                final MediaSession2.ControllerInfo controllerInfo = controller;
                this.mSession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaSession2Stub.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SessionCommand2 command;
                        synchronized (MediaSession2Stub.this.mLock) {
                            if (MediaSession2Stub.this.mControllers.containsValue(controllerInfo)) {
                                SessionCommand2 sessionCommand2 = sessionCommand;
                                if (sessionCommand2 != null) {
                                    if (MediaSession2Stub.this.isAllowedCommand(controllerInfo, sessionCommand2)) {
                                        command = (SessionCommand2) MediaSession2Stub.sCommandsForOnCommandRequest.get(sessionCommand.getCommandCode());
                                    } else {
                                        return;
                                    }
                                } else if (MediaSession2Stub.this.isAllowedCommand(controllerInfo, commandCode)) {
                                    command = (SessionCommand2) MediaSession2Stub.sCommandsForOnCommandRequest.get(commandCode);
                                } else {
                                    return;
                                }
                                if (command != null) {
                                    boolean accepted = MediaSession2Stub.this.mSession.getCallback().onCommandRequest(MediaSession2Stub.this.mSession.getInstance(), controllerInfo, command);
                                    if (!accepted) {
                                        Log.d(MediaSession2Stub.TAG, "Command (" + command + ") from " + controllerInfo + " was rejected by " + MediaSession2Stub.this.mSession);
                                        return;
                                    }
                                }
                                try {
                                    runnable.run(controllerInfo);
                                } catch (RemoteException e) {
                                    Log.w(MediaSession2Stub.TAG, "Exception in " + controllerInfo.toString(), e);
                                }
                            }
                        }
                    }
                });
            } catch (Throwable th2) {
                while (true) {
                    th = th2;
                }
            }
        }
    }

    private void onBrowserCommand(IMediaController2 caller, int commandCode, SessionRunnable runnable) throws Throwable {
        if (!(this.mSession instanceof MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl)) {
            throw new RuntimeException("MediaSession2 cannot handle MediaLibrarySession command");
        }
        onSessionCommandInternal(caller, null, commandCode, runnable);
    }

    void removeControllerInfo(MediaSession2.ControllerInfo controller) {
        synchronized (this.mLock) {
            Log.d(TAG, "releasing " + this.mControllers.remove(controller.getId()));
        }
    }

    private void releaseController(IMediaController2 iController) throws Throwable {
        synchronized (this.mLock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                final MediaSession2.ControllerInfo controller = this.mControllers.remove(iController.asBinder());
                Log.d(TAG, "releasing " + controller);
                if (this.mSession.isClosed() || controller == null) {
                    return;
                }
                this.mSession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaSession2Stub.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaSession2Stub.this.mSession.getCallback().onDisconnected(MediaSession2Stub.this.mSession.getInstance(), controller);
                    }
                });
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // android.support.v4.media.IMediaSession2
    public void connect(final IMediaController2 caller, String callingPackage) throws RuntimeException {
        final MediaSession2.ControllerInfo controllerInfo = new MediaSession2.ControllerInfo(callingPackage, Binder.getCallingPid(), Binder.getCallingUid(), new Controller2Cb(caller));
        this.mSession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaSession2Stub.3
            @Override // java.lang.Runnable
            public void run() {
                SessionCommandGroup2 allowedCommands;
                if (!MediaSession2Stub.this.mSession.isClosed()) {
                    synchronized (MediaSession2Stub.this.mLock) {
                        MediaSession2Stub.this.mConnectingControllers.add(controllerInfo.getId());
                    }
                    SessionCommandGroup2 allowedCommands2 = MediaSession2Stub.this.mSession.getCallback().onConnect(MediaSession2Stub.this.mSession.getInstance(), controllerInfo);
                    boolean accept = (allowedCommands2 != null || controllerInfo.isTrusted()) ? MediaSession2Stub.DEBUG : false;
                    if (!accept) {
                        synchronized (MediaSession2Stub.this.mLock) {
                            MediaSession2Stub.this.mConnectingControllers.remove(controllerInfo.getId());
                        }
                        Log.d(MediaSession2Stub.TAG, "Rejecting connection, controllerInfo=" + controllerInfo);
                        try {
                            caller.onDisconnected();
                            return;
                        } catch (RemoteException e) {
                            return;
                        }
                    }
                    Log.d(MediaSession2Stub.TAG, "Accepting connection, controllerInfo=" + controllerInfo + " allowedCommands=" + allowedCommands2);
                    if (allowedCommands2 != null) {
                        allowedCommands = allowedCommands2;
                    } else {
                        allowedCommands = new SessionCommandGroup2();
                    }
                    synchronized (MediaSession2Stub.this.mLock) {
                        MediaSession2Stub.this.mConnectingControllers.remove(controllerInfo.getId());
                        MediaSession2Stub.this.mControllers.put(controllerInfo.getId(), controllerInfo);
                        MediaSession2Stub.this.mAllowedCommandGroupMap.put(controllerInfo, allowedCommands);
                    }
                    int playerState = MediaSession2Stub.this.mSession.getPlayerState();
                    Bundle currentItem = MediaSession2Stub.this.mSession.getCurrentMediaItem() == null ? null : MediaSession2Stub.this.mSession.getCurrentMediaItem().toBundle();
                    long positionEventTimeMs = SystemClock.elapsedRealtime();
                    long positionMs = MediaSession2Stub.this.mSession.getCurrentPosition();
                    float playbackSpeed = MediaSession2Stub.this.mSession.getPlaybackSpeed();
                    long bufferedPositionMs = MediaSession2Stub.this.mSession.getBufferedPosition();
                    Bundle playbackInfoBundle = MediaSession2Stub.this.mSession.getPlaybackInfo().toBundle();
                    int repeatMode = MediaSession2Stub.this.mSession.getRepeatMode();
                    int shuffleMode = MediaSession2Stub.this.mSession.getShuffleMode();
                    PendingIntent sessionActivity = MediaSession2Stub.this.mSession.getSessionActivity();
                    List<MediaItem2> playlist = allowedCommands.hasCommand(18) ? MediaSession2Stub.this.mSession.getPlaylist() : null;
                    List<Bundle> playlistBundle = MediaUtils2.convertMediaItem2ListToBundleList(playlist);
                    if (!MediaSession2Stub.this.mSession.isClosed()) {
                        try {
                            caller.onConnected(MediaSession2Stub.this, allowedCommands.toBundle(), playerState, currentItem, positionEventTimeMs, positionMs, playbackSpeed, bufferedPositionMs, playbackInfoBundle, repeatMode, shuffleMode, playlistBundle, sessionActivity);
                        } catch (RemoteException e2) {
                        }
                    }
                }
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void release(IMediaController2 caller) throws Throwable {
        releaseController(caller);
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setVolumeTo(IMediaController2 caller, final int value, final int flags) throws Throwable {
        onSessionCommand(caller, 10, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.4
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                VolumeProviderCompat volumeProvider = MediaSession2Stub.this.mSession.getVolumeProvider();
                if (volumeProvider == null) {
                    MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSession.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().setVolumeTo(value, flags);
                        return;
                    }
                    return;
                }
                volumeProvider.onSetVolumeTo(value);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void adjustVolume(IMediaController2 caller, final int direction, final int flags) throws Throwable {
        onSessionCommand(caller, 11, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.5
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                VolumeProviderCompat volumeProvider = MediaSession2Stub.this.mSession.getVolumeProvider();
                if (volumeProvider == null) {
                    MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSession.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().adjustVolume(direction, flags);
                        return;
                    }
                    return;
                }
                volumeProvider.onAdjustVolume(direction);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void play(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 1, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.6
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.play();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void pause(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 2, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.7
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.pause();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void reset(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 3, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.8
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.reset();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepare(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 6, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.9
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.prepare();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void fastForward(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 7, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.10
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onFastForward(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void rewind(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 8, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.11
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onRewind(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void seekTo(IMediaController2 caller, final long pos) throws Throwable {
        onSessionCommand(caller, 9, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.12
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.seekTo(pos);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void sendCustomCommand(IMediaController2 caller, Bundle commandBundle, final Bundle args, final ResultReceiver receiver) throws Throwable {
        final SessionCommand2 command = SessionCommand2.fromBundle(commandBundle);
        onSessionCommand(caller, SessionCommand2.fromBundle(commandBundle), new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.13
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onCustomCommand(MediaSession2Stub.this.mSession.getInstance(), controller, command, args, receiver);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromUri(IMediaController2 caller, final Uri uri, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 26, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.14
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromUri(): Ignoring null uri from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromUri(MediaSession2Stub.this.mSession.getInstance(), controller, uri, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromSearch(IMediaController2 caller, final String query, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 27, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.15
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromSearch(): Ignoring empty query from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromSearch(MediaSession2Stub.this.mSession.getInstance(), controller, query, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void prepareFromMediaId(IMediaController2 caller, final String mediaId, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 25, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.16
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromMediaId(): Ignoring null mediaId from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPrepareFromMediaId(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromUri(IMediaController2 caller, final Uri uri, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 23, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.17
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromUri(): Ignoring null uri from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromUri(MediaSession2Stub.this.mSession.getInstance(), controller, uri, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromSearch(IMediaController2 caller, final String query, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 24, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.18
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "playFromSearch(): Ignoring empty query from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromSearch(MediaSession2Stub.this.mSession.getInstance(), controller, query, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void playFromMediaId(IMediaController2 caller, final String mediaId, final Bundle extras) throws Throwable {
        onSessionCommand(caller, 22, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.19
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromMediaId(): Ignoring null mediaId from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onPlayFromMediaId(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, extras);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setRating(IMediaController2 caller, final String mediaId, final Bundle ratingBundle) throws Throwable {
        onSessionCommand(caller, 28, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.20
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null mediaId from " + controller);
                    return;
                }
                Bundle bundle = ratingBundle;
                if (bundle == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null ratingBundle from " + controller);
                    return;
                }
                Rating2 rating = Rating2.fromBundle(bundle);
                if (rating == null) {
                    if (ratingBundle == null) {
                        Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null rating from " + controller);
                        return;
                    }
                    return;
                }
                MediaSession2Stub.this.mSession.getCallback().onSetRating(MediaSession2Stub.this.mSession.getInstance(), controller, mediaId, rating);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setPlaybackSpeed(IMediaController2 caller, final float speed) throws Throwable {
        onSessionCommand(caller, 39, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.21
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setPlaybackSpeed(speed);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setPlaylist(IMediaController2 caller, final List<Bundle> playlist, final Bundle metadata) throws Throwable {
        onSessionCommand(caller, 19, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.22
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (playlist == null) {
                    Log.w(MediaSession2Stub.TAG, "setPlaylist(): Ignoring null playlist from " + controller);
                    return;
                }
                MediaSession2Stub.this.mSession.getInstance().setPlaylist(MediaUtils2.convertBundleListToMediaItem2List(playlist), MediaMetadata2.fromBundle(metadata));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void updatePlaylistMetadata(IMediaController2 caller, final Bundle metadata) throws Throwable {
        onSessionCommand(caller, 21, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.23
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().updatePlaylistMetadata(MediaMetadata2.fromBundle(metadata));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void addPlaylistItem(IMediaController2 caller, final int index, final Bundle mediaItem) throws Throwable {
        onSessionCommand(caller, 15, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.24
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().addPlaylistItem(index, MediaItem2.fromBundle(mediaItem, null));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void removePlaylistItem(IMediaController2 caller, final Bundle mediaItem) throws Throwable {
        onSessionCommand(caller, 16, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.25
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaItem2 item = MediaItem2.fromBundle(mediaItem);
                MediaSession2Stub.this.mSession.getInstance().removePlaylistItem(item);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void replacePlaylistItem(IMediaController2 caller, final int index, final Bundle mediaItem) throws Throwable {
        onSessionCommand(caller, 17, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.26
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().replacePlaylistItem(index, MediaItem2.fromBundle(mediaItem, null));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToPlaylistItem(IMediaController2 caller, final Bundle mediaItem) throws Throwable {
        onSessionCommand(caller, 12, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.27
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaItem == null) {
                    Log.w(MediaSession2Stub.TAG, "skipToPlaylistItem(): Ignoring null mediaItem from " + controller);
                }
                MediaSession2Stub.this.mSession.getInstance().skipToPlaylistItem(MediaItem2.fromBundle(mediaItem));
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToPreviousItem(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 5, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.28
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().skipToPreviousItem();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void skipToNextItem(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 4, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.29
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().skipToNextItem();
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setRepeatMode(IMediaController2 caller, final int repeatMode) throws Throwable {
        onSessionCommand(caller, 14, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.30
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setRepeatMode(repeatMode);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void setShuffleMode(IMediaController2 caller, final int shuffleMode) throws Throwable {
        onSessionCommand(caller, 13, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.31
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getInstance().setShuffleMode(shuffleMode);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void subscribeRoutesInfo(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 36, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.32
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onSubscribeRoutesInfo(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void unsubscribeRoutesInfo(IMediaController2 caller) throws Throwable {
        onSessionCommand(caller, 37, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.33
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onUnsubscribeRoutesInfo(MediaSession2Stub.this.mSession.getInstance(), controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void selectRoute(IMediaController2 caller, final Bundle route) throws Throwable {
        onSessionCommand(caller, 37, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.34
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.mSession.getCallback().onSelectRoute(MediaSession2Stub.this.mSession.getInstance(), controller, route);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl getLibrarySession() {
        MediaSession2.SupportLibraryImpl supportLibraryImpl = this.mSession;
        if (!(supportLibraryImpl instanceof MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl)) {
            throw new RuntimeException("Session cannot be casted to library session");
        }
        return (MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl) supportLibraryImpl;
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getLibraryRoot(IMediaController2 caller, final Bundle rootHints) throws Throwable {
        onBrowserCommand(caller, 31, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.35
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                MediaSession2Stub.this.getLibrarySession().onGetLibraryRootOnExecutor(controller, rootHints);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getItem(IMediaController2 caller, final String mediaId) throws Throwable {
        onBrowserCommand(caller, 30, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.36
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (mediaId != null) {
                    MediaSession2Stub.this.getLibrarySession().onGetItemOnExecutor(controller, mediaId);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "getItem(): Ignoring null mediaId from " + controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getChildren(IMediaController2 caller, final String parentId, final int page, final int pageSize, final Bundle extras) throws Throwable {
        onBrowserCommand(caller, 29, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.37
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId == null) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring null parentId from " + controller);
                    return;
                }
                if (page >= 1 && pageSize >= 1) {
                    MediaSession2Stub.this.getLibrarySession().onGetChildrenOnExecutor(controller, parentId, page, pageSize, extras);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring page nor pageSize less than 1 from " + controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void search(IMediaController2 caller, final String query, final Bundle extras) throws Throwable {
        onBrowserCommand(caller, 33, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.38
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (!TextUtils.isEmpty(query)) {
                    MediaSession2Stub.this.getLibrarySession().onSearchOnExecutor(controller, query, extras);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "search(): Ignoring empty query from " + controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void getSearchResult(IMediaController2 caller, final String query, final int page, final int pageSize, final Bundle extras) throws Throwable {
        onBrowserCommand(caller, 32, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.39
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (TextUtils.isEmpty(query)) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring empty query from " + controller);
                    return;
                }
                if (page >= 1 && pageSize >= 1) {
                    MediaSession2Stub.this.getLibrarySession().onGetSearchResultOnExecutor(controller, query, page, pageSize, extras);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring page nor pageSize less than 1  from " + controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void subscribe(IMediaController2 caller, final String parentId, final Bundle option) throws Throwable {
        onBrowserCommand(caller, 34, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.40
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId != null) {
                    MediaSession2Stub.this.getLibrarySession().onSubscribeOnExecutor(controller, parentId, option);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "subscribe(): Ignoring null parentId from " + controller);
            }
        });
    }

    @Override // android.support.v4.media.IMediaSession2
    public void unsubscribe(IMediaController2 caller, final String parentId) throws Throwable {
        onBrowserCommand(caller, 35, new SessionRunnable() { // from class: android.support.v4.media.MediaSession2Stub.41
            @Override // android.support.v4.media.MediaSession2Stub.SessionRunnable
            public void run(MediaSession2.ControllerInfo controller) throws RemoteException {
                if (parentId != null) {
                    MediaSession2Stub.this.getLibrarySession().onUnsubscribeOnExecutor(controller, parentId);
                    return;
                }
                Log.w(MediaSession2Stub.TAG, "unsubscribe(): Ignoring null parentId from " + controller);
            }
        });
    }

    static final class Controller2Cb extends MediaSession2.ControllerCb {
        private final IMediaController2 mIControllerCallback;

        Controller2Cb(IMediaController2 callback) {
            this.mIControllerCallback = callback;
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        IBinder getId() {
            return this.mIControllerCallback.asBinder();
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onCustomLayoutChanged(List<MediaSession2.CommandButton> layout) throws RemoteException {
            this.mIControllerCallback.onCustomLayoutChanged(MediaUtils2.convertCommandButtonListToBundleList(layout));
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onPlaybackInfoChanged(MediaController2.PlaybackInfo info) throws RemoteException {
            this.mIControllerCallback.onPlaybackInfoChanged(info.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onAllowedCommandsChanged(SessionCommandGroup2 commands) throws RemoteException {
            this.mIControllerCallback.onAllowedCommandsChanged(commands.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onCustomCommand(SessionCommand2 command, Bundle args, ResultReceiver receiver) throws RemoteException {
            this.mIControllerCallback.onCustomCommand(command.toBundle(), args, receiver);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onPlayerStateChanged(long eventTimeMs, long positionMs, int playerState) throws RemoteException {
            this.mIControllerCallback.onPlayerStateChanged(eventTimeMs, positionMs, playerState);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onPlaybackSpeedChanged(long eventTimeMs, long positionMs, float speed) throws RemoteException {
            this.mIControllerCallback.onPlaybackSpeedChanged(eventTimeMs, positionMs, speed);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onBufferingStateChanged(MediaItem2 item, int state, long bufferedPositionMs) throws RemoteException {
            this.mIControllerCallback.onBufferingStateChanged(item == null ? null : item.toBundle(), state, bufferedPositionMs);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onSeekCompleted(long eventTimeMs, long positionMs, long seekPositionMs) throws RemoteException {
            this.mIControllerCallback.onSeekCompleted(eventTimeMs, positionMs, seekPositionMs);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onError(int errorCode, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onError(errorCode, extras);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onCurrentMediaItemChanged(MediaItem2 item) throws RemoteException {
            this.mIControllerCallback.onCurrentMediaItemChanged(item == null ? null : item.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onPlaylistChanged(List<MediaItem2> playlist, MediaMetadata2 metadata) throws RemoteException {
            this.mIControllerCallback.onPlaylistChanged(MediaUtils2.convertMediaItem2ListToBundleList(playlist), metadata == null ? null : metadata.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onPlaylistMetadataChanged(MediaMetadata2 metadata) throws RemoteException {
            this.mIControllerCallback.onPlaylistMetadataChanged(metadata.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onShuffleModeChanged(int shuffleMode) throws RemoteException {
            this.mIControllerCallback.onShuffleModeChanged(shuffleMode);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onRepeatModeChanged(int repeatMode) throws RemoteException {
            this.mIControllerCallback.onRepeatModeChanged(repeatMode);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onRoutesInfoChanged(List<Bundle> routes) throws RemoteException {
            this.mIControllerCallback.onRoutesInfoChanged(routes);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onGetLibraryRootDone(Bundle rootHints, String rootMediaId, Bundle rootExtra) throws RemoteException {
            this.mIControllerCallback.onGetLibraryRootDone(rootHints, rootMediaId, rootExtra);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onChildrenChanged(String parentId, int itemCount, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onChildrenChanged(parentId, itemCount, extras);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onGetChildrenDone(String parentId, int page, int pageSize, List<MediaItem2> result, Bundle extras) throws RemoteException {
            List<Bundle> bundleList = MediaUtils2.convertMediaItem2ListToBundleList(result);
            this.mIControllerCallback.onGetChildrenDone(parentId, page, pageSize, bundleList, extras);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onGetItemDone(String mediaId, MediaItem2 result) throws RemoteException {
            this.mIControllerCallback.onGetItemDone(mediaId, result == null ? null : result.toBundle());
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onSearchResultChanged(String query, int itemCount, Bundle extras) throws RemoteException {
            this.mIControllerCallback.onSearchResultChanged(query, itemCount, extras);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onGetSearchResultDone(String query, int page, int pageSize, List<MediaItem2> result, Bundle extras) throws RemoteException {
            List<Bundle> bundleList = MediaUtils2.convertMediaItem2ListToBundleList(result);
            this.mIControllerCallback.onGetSearchResultDone(query, page, pageSize, bundleList, extras);
        }

        @Override // android.support.v4.media.MediaSession2.ControllerCb
        void onDisconnected() throws RemoteException {
            this.mIControllerCallback.onDisconnected();
        }
    }
}
