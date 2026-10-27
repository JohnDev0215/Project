package com.amazon.mp3.voice;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkResolver;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.voice.AlexaFavouritesController;
import com.amazon.music.voice.AlexaPlaylistController;
import com.amazon.music.voice.AlexaSeekController;
import com.amazon.music.voice.EMPPlaybackController;
import com.amazon.music.voice.GUIPlaybackController;
import com.amazon.music.voice.emp.EMPPlaybackState;
import com.amazon.music.voice.emp.events.EMPError;
import com.amazon.music.voice.emp.events.EMPEvent;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaGUIPlaybackController implements GUIPlaybackController, EMPPlaybackController, AlexaSeekController, AlexaFavouritesController, AlexaPlaylistController {
    private static final String TAG = "AlexaGUIPlaybackController";
    private final AlexaEMPPlaybackEventObserver alexaPlaybackEventObserver;
    private final Context context;
    private final PublishSubject<EMPError> empErrorPublishSubject;
    private final PublishSubject<EMPEvent> empEventPublishSubject;
    private final PublishSubject<EMPPlaybackState> empPlaybackStatePublishSubject;
    private AtomicBoolean ignoreNextPlayRequest;
    private final VoiceDeeplinkResolver voiceDeeplinkResolver;

    @Override // com.amazon.music.voice.EMPPlaybackController
    public final Observable<EMPEvent> getEmpEventsObservable() {
        return this.empEventPublishSubject;
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public final Observable<EMPError> getEmpErrorObservable() {
        return this.empErrorPublishSubject;
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public Observable<EMPPlaybackState> getEmpPlaybackStateObservable() {
        return this.empPlaybackStatePublishSubject;
    }

    public AlexaGUIPlaybackController(Context context, VoiceDeeplinkResolver voiceDeeplinkResolver) {
        PublishSubject<EMPEvent> publishSubjectCreate = PublishSubject.create();
        this.empEventPublishSubject = publishSubjectCreate;
        PublishSubject<EMPError> publishSubjectCreate2 = PublishSubject.create();
        this.empErrorPublishSubject = publishSubjectCreate2;
        PublishSubject<EMPPlaybackState> publishSubjectCreate3 = PublishSubject.create();
        this.empPlaybackStatePublishSubject = publishSubjectCreate3;
        this.ignoreNextPlayRequest = new AtomicBoolean(false);
        this.context = context.getApplicationContext();
        this.voiceDeeplinkResolver = voiceDeeplinkResolver;
        this.alexaPlaybackEventObserver = new AlexaEMPPlaybackEventObserver(publishSubjectCreate, publishSubjectCreate2, publishSubjectCreate3);
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void play() {
        if (this.ignoreNextPlayRequest.compareAndSet(true, false)) {
            return;
        }
        Log.debug(TAG, "GUI initiated playback received PLAY command via Alexa.");
        getPlaybackController().play();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void pause() {
        Log.debug(TAG, "GUI initiated playback received PAUSE command via Alexa.");
        getPlaybackController().pause();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void stop() {
        Log.debug(TAG, "GUI initiated playback received STOP command via Alexa.");
        getPlaybackController().stop(ChangeReason.SYSTEM_STOP);
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void next() {
        Log.debug(TAG, "GUI initiated playback received NEXT command via Alexa.");
        getActionValidatedPlaybackController().skipNextForResult();
    }

    @Override // com.amazon.music.voice.GUIPlaybackController
    public void previous() {
        Log.debug(TAG, "GUI initiated playback received PREVIOUS command via Alexa.");
        getPlaybackController().skipPrevious();
        getPlaybackController().play();
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public void playWithUri(final Uri uri, final String sessionId) {
        this.ignoreNextPlayRequest.set(true);
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.voice.AlexaGUIPlaybackController.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AlexaGUIPlaybackController.this.voiceDeeplinkResolver.canResolve(uri)) {
                        AlexaGUIPlaybackController.this.voiceDeeplinkResolver.resolve(AlexaGUIPlaybackController.this.context, uri, sessionId, true);
                    }
                } catch (Exception e) {
                    Log.error(AlexaGUIPlaybackController.TAG, "Error resolving uri by deep link resolver " + e.getLocalizedMessage());
                }
            }
        });
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public void rewind() {
        Log.debug(TAG, "GUI initiated playback received REWIND command via Alexa");
        if (getPlaybackController().canRewind()) {
            getPlaybackController().rewind();
        }
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public void fastForward() {
        Log.debug(TAG, "GUI initiated playback received FAST FORWARD command via Alexa");
        if (getPlaybackController().canFastForward()) {
            getPlaybackController().fastForward();
        }
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public void startOver() {
        Log.debug(TAG, "GUI initiated playback received START OVER command via Alexa");
        if (getPlaybackController().canRestartItem()) {
            getPlaybackController().restartMediaItem();
        }
    }

    @Override // com.amazon.music.voice.AlexaSeekController
    public void adjustSeekPosition(long position) {
        Log.debug(TAG, "GUI initiated playback received ADJUST SEEK POSITION command via Alexa");
        if (getPlaybackController().canSeek()) {
            getPlaybackController().seek(getPlaybackController().getPositionMillis() + position, 0);
        }
    }

    @Override // com.amazon.music.voice.AlexaSeekController
    public void setSeekPosition(long position) {
        Log.debug(TAG, "GUI initiated playback received SET SEEK POSITION command via Alexa");
        if (getPlaybackController().canSeek()) {
            getPlaybackController().seek(position, 0);
        }
    }

    @Override // com.amazon.music.voice.AlexaFavouritesController
    public void toggleFavourite() {
        Log.debug(TAG, "GUI initiated playback received FAVORITE command via Alexa");
        if (getPlaybackController().canRateCurrentItem()) {
            getPlaybackController().toggleThumbsUp();
        }
    }

    @Override // com.amazon.music.voice.AlexaFavouritesController
    public void toggleUnFavourite() {
        Log.debug(TAG, "GUI initiated playback received UNFAVORITE command via Alexa");
        if (getPlaybackController().canRateCurrentItem()) {
            getPlaybackController().toggleThumbsDown();
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaylistController
    public void disableRepeat() {
        Log.debug(TAG, "GUI initiated playback received DISABLE REPEAT command via Alexa");
        EnumSet<RepeatMode> availableRepeatModes = getPlaybackController().getAvailableRepeatModes();
        RepeatMode repeatMode = RepeatMode.REPEAT_NONE;
        if (availableRepeatModes.contains(repeatMode)) {
            getPlaybackController().setRepeatMode(repeatMode);
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaylistController
    public void disableShuffle() {
        Log.debug(TAG, "GUI initiated playback received DISABLE SHUFFLE command via Alexa");
        changeShuffleState(false);
    }

    @Override // com.amazon.music.voice.AlexaPlaylistController
    public void enableRepeat() {
        Log.debug(TAG, "GUI initiated playback received ENABLE REPEAT command via Alexa");
        EnumSet<RepeatMode> availableRepeatModes = getPlaybackController().getAvailableRepeatModes();
        RepeatMode repeatMode = RepeatMode.REPEAT_ALL;
        if (availableRepeatModes.contains(repeatMode)) {
            getPlaybackController().setRepeatMode(repeatMode);
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaylistController
    public void enableRepeatOne() {
        Log.debug(TAG, "GUI initiated playback received ENABLE REPEAT ONE command via Alexa");
        EnumSet<RepeatMode> availableRepeatModes = getPlaybackController().getAvailableRepeatModes();
        RepeatMode repeatMode = RepeatMode.REPEAT_ONE;
        if (availableRepeatModes.contains(repeatMode)) {
            getPlaybackController().setRepeatMode(repeatMode);
        }
    }

    @Override // com.amazon.music.voice.AlexaPlaylistController
    public void enableShuffle() {
        Log.debug(TAG, "GUI initiated playback received ENABLE SHUFFLE command via Alexa");
        changeShuffleState(true);
    }

    private void changeShuffleState(final boolean shouldShuffle) {
        if (getPlaybackController().canShuffle() && getPlaybackController().isShuffled() != shouldShuffle) {
            getPlaybackController().setShuffled(shouldShuffle);
            if (CloudQueueSequencer.isCloudQueuePlaying()) {
                CloudQueueSequencer.shuffleAll(shouldShuffle);
            }
        }
    }

    private PlaybackController getPlaybackController() {
        return PlaybackControllerProvider.getController(ControlSource.ALEXA);
    }

    private ActionValidatedPlaybackController getActionValidatedPlaybackController() {
        return PlaybackControllerProvider.getController(ControlSource.ALEXA);
    }

    @Override // com.amazon.music.voice.EMPPlaybackController
    public void attachPlaybackEventObservers() {
        getPlaybackController().addOnPlaybackEventListener(this.alexaPlaybackEventObserver.getMOnPlaybackEventListener());
        getPlaybackController().addOnPlaybackErrorListener(this.alexaPlaybackEventObserver.getMOnPlaybackErrorListener());
        getPlaybackController().addOnPlayStateChangedListener(this.alexaPlaybackEventObserver.getOnPlayStateChangedListener());
    }
}
