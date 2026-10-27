package com.amazon.mp3.playback.playbackcontrol;

import com.amazon.music.media.playback.PlaybackController;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActionValidatedPlaybackController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH&R\u0014\u0010\u000f\u001a\u00020\u00018&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/music/media/playback/PlaybackController;", "", "action", "", "canDisplayAction", "Lcom/amazon/mp3/playback/playbackcontrol/ActionResult;", "skipNextForResult", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "addOnLyricsLineUpdateListener", "removeOnLyricsLineUpdateListener", "getController", "()Lcom/amazon/music/media/playback/PlaybackController;", "controller", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface ActionValidatedPlaybackController extends PlaybackController {
    void addOnLyricsLineUpdateListener(OnLyricsLineUpdateListener listener);

    boolean canDisplayAction(String action);

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ int getAudioFocusStatus();

    PlaybackController getController();

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ int getCurrentIndex();

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ int getCurrentItemRating();

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ float getPlaybackSpeed();

    void removeOnLyricsLineUpdateListener(OnLyricsLineUpdateListener listener);

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ void setCurrentItemRating(int i);

    @Override // com.amazon.music.media.playback.PlaybackController
    /* synthetic */ void setPlaybackSpeed(double d);

    ActionResult skipNextForResult();
}
