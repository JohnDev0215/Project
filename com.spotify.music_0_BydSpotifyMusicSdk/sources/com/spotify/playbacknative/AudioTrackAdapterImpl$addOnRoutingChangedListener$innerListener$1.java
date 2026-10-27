package com.spotify.playbacknative;

import android.media.AudioTrack;
import com.spotify.playbacknative.AudioTrackAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class AudioTrackAdapterImpl$addOnRoutingChangedListener$innerListener$1 implements AudioTrack.OnRoutingChangedListener {
    final /* synthetic */ AudioTrackAdapter.OnRoutingChangedListener $listener;
    final /* synthetic */ AudioTrackAdapterImpl this$0;

    public AudioTrackAdapterImpl$addOnRoutingChangedListener$innerListener$1(AudioTrackAdapter.OnRoutingChangedListener onRoutingChangedListener, AudioTrackAdapterImpl audioTrackAdapterImpl) {
        this.$listener = onRoutingChangedListener;
        this.this$0 = audioTrackAdapterImpl;
    }

    @Override // android.media.AudioTrack.OnRoutingChangedListener
    public final void onRoutingChanged(AudioTrack audioTrack) {
        this.$listener.onRoutingChanged(this.this$0.toAudioDeviceInfoFacade(audioTrack != null ? audioTrack.getRoutedDevice() : null));
    }
}
