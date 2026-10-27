package com.amazon.music.platform.providers;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.PlaybackStateListener;
import com.amazon.music.platform.playback.commands.PlaybackControls;
import com.amazon.music.platform.playback.data.PlaybackRequest;
import com.amazon.music.platform.playback.state.DevicePlaybackState;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: PlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&J!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/providers/PlaybackProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/playback/state/DevicePlaybackState;", "state", "Lcom/amazon/music/platform/playback/commands/PlaybackControls;", "controls", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "playbackRequest", "Lcom/amazon/music/platform/Outcome;", "initiatePlayback", "(Lcom/amazon/music/platform/playback/data/PlaybackRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/playback/PlaybackStateListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "addPlaybackStateListener", "removePlaybackStateListener", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface PlaybackProvider extends InterfaceProvider {
    void addPlaybackStateListener(PlaybackStateListener listener);

    PlaybackControls controls();

    Object initiatePlayback(PlaybackRequest playbackRequest, Continuation<? super Outcome<DevicePlaybackState>> continuation);

    void removePlaybackStateListener(PlaybackStateListener listener);

    DevicePlaybackState state();
}
