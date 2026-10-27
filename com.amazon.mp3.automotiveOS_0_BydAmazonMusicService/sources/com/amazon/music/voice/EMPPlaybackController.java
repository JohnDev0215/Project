package com.amazon.music.voice;

import android.net.Uri;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.voice.emp.EMPPlaybackState;
import com.amazon.music.voice.emp.events.EMPError;
import com.amazon.music.voice.emp.events.EMPEvent;
import io.reactivex.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: EMPPlaybackController.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\b\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\u0006H&J\b\u0010\n\u001a\u00020\u0006H&J\b\u0010\u000b\u001a\u00020\u0006H&R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/voice/EMPPlaybackController;", "Lcom/amazon/music/voice/GUIPlaybackController;", "Landroid/net/Uri;", "uri", "", Splash.PARAMS_SESSION_ID, "", "playWithUri", "rewind", "fastForward", "startOver", "attachPlaybackEventObservers", "Lio/reactivex/Observable;", "Lcom/amazon/music/voice/emp/events/EMPEvent;", "getEmpEventsObservable", "()Lio/reactivex/Observable;", "empEventsObservable", "Lcom/amazon/music/voice/emp/events/EMPError;", "getEmpErrorObservable", "empErrorObservable", "Lcom/amazon/music/voice/emp/EMPPlaybackState;", "getEmpPlaybackStateObservable", "empPlaybackStateObservable", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public interface EMPPlaybackController extends GUIPlaybackController {
    void attachPlaybackEventObservers();

    void fastForward();

    Observable<EMPError> getEmpErrorObservable();

    Observable<EMPEvent> getEmpEventsObservable();

    Observable<EMPPlaybackState> getEmpPlaybackStateObservable();

    void playWithUri(Uri uri, String sessionId);

    void rewind();

    void startOver();
}
