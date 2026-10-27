package com.android.car.developeroptions.notification;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.volume.MediaSessions;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RemoteVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_REMOTE_VOLUME = "remote_volume";
    static final int REMOTE_VOLUME = 100;
    MediaSession.Token mActiveToken;
    MediaSessions.Callbacks mCallbacks;
    MediaController mMediaController;
    private MediaSessionManager mMediaSessionManager;
    private MediaSessions mMediaSessions;

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return REMOTE_VOLUME;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return R.drawable.ic_volume_remote_mute;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_REMOTE_VOLUME;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public RemoteVolumePreferenceController(Context context) {
        super(context, KEY_REMOTE_VOLUME);
        this.mCallbacks = new MediaSessions.Callbacks() { // from class: com.android.car.developeroptions.notification.RemoteVolumePreferenceController.1
            @Override // com.android.settingslib.volume.MediaSessions.Callbacks
            public void onRemoteUpdate(MediaSession.Token token, String str, MediaController.PlaybackInfo playbackInfo) {
                RemoteVolumePreferenceController remoteVolumePreferenceController = RemoteVolumePreferenceController.this;
                if (remoteVolumePreferenceController.mActiveToken == null) {
                    remoteVolumePreferenceController.updateToken(token);
                }
                if (Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token)) {
                    RemoteVolumePreferenceController remoteVolumePreferenceController2 = RemoteVolumePreferenceController.this;
                    remoteVolumePreferenceController2.updatePreference(remoteVolumePreferenceController2.mPreference, remoteVolumePreferenceController2.mActiveToken, playbackInfo);
                }
            }

            @Override // com.android.settingslib.volume.MediaSessions.Callbacks
            public void onRemoteRemoved(MediaSession.Token token) {
                if (Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token)) {
                    RemoteVolumePreferenceController.this.updateToken(null);
                    VolumeSeekBarPreference volumeSeekBarPreference = RemoteVolumePreferenceController.this.mPreference;
                    if (volumeSeekBarPreference != null) {
                        volumeSeekBarPreference.setVisible(false);
                    }
                }
            }

            @Override // com.android.settingslib.volume.MediaSessions.Callbacks
            public void onRemoteVolumeChanged(MediaSession.Token token, int i) {
                MediaController.PlaybackInfo playbackInfo;
                if (!Objects.equals(RemoteVolumePreferenceController.this.mActiveToken, token) || (playbackInfo = RemoteVolumePreferenceController.this.mMediaController.getPlaybackInfo()) == null) {
                    return;
                }
                RemoteVolumePreferenceController.this.setSliderPosition(playbackInfo.getCurrentVolume());
            }
        };
        this.mMediaSessionManager = (MediaSessionManager) context.getSystemService(MediaSessionManager.class);
        this.mMediaSessions = new MediaSessions(context, Looper.getMainLooper(), this.mCallbacks);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        for (MediaController mediaController : this.mMediaSessionManager.getActiveSessions(null)) {
            if (isRemote(mediaController.getPlaybackInfo())) {
                updateToken(mediaController.getSessionToken());
                return 0;
            }
        }
        return 2;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            updatePreference(this.mPreference, this.mActiveToken, mediaController.getPlaybackInfo());
        }
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        this.mMediaSessions.init();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        super.onPause();
        this.mMediaSessions.destroy();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController
    public int getSliderPosition() {
        MediaController.PlaybackInfo playbackInfo;
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            return volumeSeekBarPreference.getProgress();
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null || (playbackInfo = mediaController.getPlaybackInfo()) == null) {
            return 0;
        }
        return playbackInfo.getCurrentVolume();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController
    public boolean setSliderPosition(int i) {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            volumeSeekBarPreference.setProgress(i);
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null) {
            return false;
        }
        mediaController.setVolumeTo(i, 0);
        return true;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController
    public int getMaxSteps() {
        MediaController.PlaybackInfo playbackInfo;
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            return volumeSeekBarPreference.getMax();
        }
        MediaController mediaController = this.mMediaController;
        if (mediaController == null || (playbackInfo = mediaController.getPlaybackInfo()) == null) {
            return 0;
        }
        return playbackInfo.getMaxVolume();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_REMOTE_VOLUME);
    }

    public static boolean isRemote(MediaController.PlaybackInfo playbackInfo) {
        return playbackInfo != null && playbackInfo.getPlaybackType() == 2;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return RemoteVolumeSliceWorker.class;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreference(VolumeSeekBarPreference volumeSeekBarPreference, MediaSession.Token token, MediaController.PlaybackInfo playbackInfo) {
        if (volumeSeekBarPreference == null || token == null || playbackInfo == null) {
            return;
        }
        volumeSeekBarPreference.setMax(playbackInfo.getMaxVolume());
        volumeSeekBarPreference.setVisible(true);
        setSliderPosition(playbackInfo.getCurrentVolume());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateToken(MediaSession.Token token) {
        this.mActiveToken = token;
        if (token != null) {
            this.mMediaController = new MediaController(this.mContext, this.mActiveToken);
        } else {
            this.mMediaController = null;
        }
    }

    public static class RemoteVolumeSliceWorker extends SliceBackgroundWorker<Void> implements MediaSessions.Callbacks {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public RemoteVolumeSliceWorker(Context context, Uri uri) {
            super(context, uri);
            new MediaSessions(context, Looper.getMainLooper(), this);
        }

        @Override // com.android.settingslib.volume.MediaSessions.Callbacks
        public void onRemoteUpdate(MediaSession.Token token, String str, MediaController.PlaybackInfo playbackInfo) {
            notifySliceChange();
        }

        @Override // com.android.settingslib.volume.MediaSessions.Callbacks
        public void onRemoteRemoved(MediaSession.Token token) {
            notifySliceChange();
        }

        @Override // com.android.settingslib.volume.MediaSessions.Callbacks
        public void onRemoteVolumeChanged(MediaSession.Token token, int i) {
            notifySliceChange();
        }
    }
}
