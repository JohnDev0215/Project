package com.spotify.playbacknative;

import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.os.Handler;
import com.spotify.playbacknative.AudioTrackAdapter;
import java.util.HashMap;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AudioTrackAdapterImpl implements AudioTrackAdapter {
    private final HashMap<AudioTrackAdapter.OnRoutingChangedListener, AudioRouting.OnRoutingChangedListener> audioRoutingListeners;
    private final AudioTrack audioTrack;
    private final HashMap<AudioTrackAdapter.OnRoutingChangedListener, AudioTrack.OnRoutingChangedListener> audioTrackListeners;

    public AudioTrackAdapterImpl(AudioTrack audioTrack) {
        v03.j(audioTrack, "audioTrack");
        this.audioTrack = audioTrack;
        this.audioTrackListeners = new HashMap<>();
        this.audioRoutingListeners = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AudioDeviceInfoFacade toAudioDeviceInfoFacade(AudioDeviceInfo audioDeviceInfo) {
        if (audioDeviceInfo != null) {
            return new AudioDeviceInfoFacade(audioDeviceInfo);
        }
        return null;
    }

    @Override // com.spotify.playbacknative.AudioTrackAdapter
    public void addOnRoutingChangedListener(final AudioTrackAdapter.OnRoutingChangedListener onRoutingChangedListener, Handler handler) {
        v03.j(onRoutingChangedListener, "listener");
        v03.j(handler, "handler");
        AudioRouting.OnRoutingChangedListener onRoutingChangedListener2 = new AudioRouting.OnRoutingChangedListener() { // from class: com.spotify.playbacknative.AudioTrackAdapterImpl$addOnRoutingChangedListener$innerListener$2
            @Override // android.media.AudioRouting.OnRoutingChangedListener
            public final void onRoutingChanged(AudioRouting audioRouting) {
                onRoutingChangedListener.onRoutingChanged(this.toAudioDeviceInfoFacade(audioRouting != null ? audioRouting.getRoutedDevice() : null));
            }
        };
        this.audioRoutingListeners.put(onRoutingChangedListener, onRoutingChangedListener2);
        this.audioTrack.addOnRoutingChangedListener(onRoutingChangedListener2, handler);
    }

    public final AudioTrack getAudioTrack() {
        return this.audioTrack;
    }

    @Override // com.spotify.playbacknative.AudioTrackAdapter
    public AudioDeviceInfoFacade getRoutedDevice() {
        return toAudioDeviceInfoFacade(this.audioTrack.getRoutedDevice());
    }

    @Override // com.spotify.playbacknative.AudioTrackAdapter
    public void removeOnRoutingChangedListener(AudioTrackAdapter.OnRoutingChangedListener onRoutingChangedListener) {
        v03.j(onRoutingChangedListener, "listener");
        AudioRouting.OnRoutingChangedListener onRoutingChangedListenerRemove = this.audioRoutingListeners.remove(onRoutingChangedListener);
        if (onRoutingChangedListenerRemove != null) {
            this.audioTrack.removeOnRoutingChangedListener(onRoutingChangedListenerRemove);
        }
    }
}
