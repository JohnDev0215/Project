package com.amazon.music.voice.emp.events;

import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.voice.EMPPlaybackController;
import com.amazon.music.voice.GUIPlaybackController;
import com.amazon.music.voice.emp.events.EMPPlayerError;
import com.amazon.music.voice.emp.events.EMPPlayerEvent;
import com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContextResolver;
import com.amazon.music.voice.playbackstate.PlaybackStateContextResolver;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayDeque;
import java.util.Deque;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EMPEventReporterPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 22\u00020\u0001:\u00012BC\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010-\u001a\u00020\u0015\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0002\u0012\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b0\u00101J\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0015J\u0016\u0010\u001a\u001a\u00020\u00112\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0018H\u0016R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\r0\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,¨\u00063"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPEventReporterPlugin;", "Lcom/amazon/alexa/voice/superbowl/VoicePlugin;", "Lio/reactivex/Observable;", "Lcom/amazon/music/voice/emp/events/EMPError;", "empErrorObservable", "Lio/reactivex/disposables/Disposable;", "subscribeToEmpErrorObservable", "Lcom/amazon/music/voice/emp/events/EMPEvent;", "empEventObservable", "subscribeToEmpEventObservable", "empEvent", "", Splash.PARAMS_SESSION_ID, "Lcom/amazon/music/voice/emp/events/EMPPlayerEvent;", "getPlayerEvent", "", "isAomInitiatedEmpPlayback", "", "handleEMPEventReporting", "empError", "handleEMPErrorReporting", "Lcom/amazon/music/voice/GUIPlaybackController;", "playbackController", "subscribeToEventsOfPodCastPlaybackController", "Ljava/util/Deque;", "playerEventsDeque", "reportPlayerEvents", "Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;", "playerStateProvider", "Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;", "Lcom/amazon/music/voice/playbackstate/PlaybackStateContextResolver;", "playbackStateContextResolver", "Lcom/amazon/music/voice/playbackstate/PlaybackStateContextResolver;", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContextResolver;", "empPlaybackStateContextResolver", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContextResolver;", "currentEmpPlaybackSessionId", "Ljava/lang/String;", "aomEmpPlaybackSessionId", "skillToken", "eventsDeque", "Ljava/util/Deque;", "Lio/reactivex/disposables/CompositeDisposable;", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "musicPlaybackController", "empSkillTokenObservable", "aomEmpPlaybackSessionIdObservable", "<init>", "(Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;Lcom/amazon/music/voice/GUIPlaybackController;Lio/reactivex/Observable;Lio/reactivex/Observable;Lcom/amazon/music/voice/playbackstate/PlaybackStateContextResolver;Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContextResolver;)V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public class EMPEventReporterPlugin extends VoicePlugin {
    private static final Logger LOG;
    private static final String TAG;
    private String aomEmpPlaybackSessionId;
    private volatile String currentEmpPlaybackSessionId;
    private final CompositeDisposable disposables;
    private final EMPPlaybackStateContextResolver empPlaybackStateContextResolver;
    private final Deque<EMPPlayerEvent> eventsDeque;
    private final PlaybackStateContextResolver playbackStateContextResolver;
    private final PlayerStateProvider playerStateProvider;
    private volatile String skillToken;

    public EMPEventReporterPlugin(PlayerStateProvider playerStateProvider, GUIPlaybackController musicPlaybackController, Observable<String> empSkillTokenObservable, Observable<String> aomEmpPlaybackSessionIdObservable, PlaybackStateContextResolver playbackStateContextResolver, EMPPlaybackStateContextResolver empPlaybackStateContextResolver) {
        Intrinsics.checkNotNullParameter(playerStateProvider, "playerStateProvider");
        Intrinsics.checkNotNullParameter(musicPlaybackController, "musicPlaybackController");
        Intrinsics.checkNotNullParameter(empSkillTokenObservable, "empSkillTokenObservable");
        Intrinsics.checkNotNullParameter(aomEmpPlaybackSessionIdObservable, "aomEmpPlaybackSessionIdObservable");
        Intrinsics.checkNotNullParameter(playbackStateContextResolver, "playbackStateContextResolver");
        Intrinsics.checkNotNullParameter(empPlaybackStateContextResolver, "empPlaybackStateContextResolver");
        this.playerStateProvider = playerStateProvider;
        this.playbackStateContextResolver = playbackStateContextResolver;
        this.empPlaybackStateContextResolver = empPlaybackStateContextResolver;
        this.eventsDeque = new ArrayDeque();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.disposables = compositeDisposable;
        EMPPlaybackController eMPPlaybackController = musicPlaybackController instanceof EMPPlaybackController ? (EMPPlaybackController) musicPlaybackController : null;
        if (eMPPlaybackController != null) {
            compositeDisposable.add(subscribeToEmpEventObservable(eMPPlaybackController.getEmpEventsObservable()));
        }
        if (eMPPlaybackController != null) {
            compositeDisposable.add(subscribeToEmpErrorObservable(eMPPlaybackController.getEmpErrorObservable()));
        }
        compositeDisposable.add(empSkillTokenObservable.subscribe(new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2447_init_$lambda2(this.f$0, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2448_init_$lambda3((Throwable) obj);
            }
        }));
        compositeDisposable.add(aomEmpPlaybackSessionIdObservable.subscribe(new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2449_init_$lambda4(this.f$0, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2450_init_$lambda5((Throwable) obj);
            }
        }));
    }

    static {
        String simpleName = EMPEventReporterPlugin.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(EMPEventReporterPlugin.TAG)");
        LOG = logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m2447_init_$lambda2(EMPEventReporterPlugin this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.skillToken = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m2448_init_$lambda3(Throwable th) {
        LOG.debug(th.getLocalizedMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m2449_init_$lambda4(EMPEventReporterPlugin this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.aomEmpPlaybackSessionId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m2450_init_$lambda5(Throwable th) {
        LOG.debug(th.getLocalizedMessage());
    }

    public final synchronized void handleEMPEventReporting(EMPEvent empEvent) {
        EMPEvent eMPEvent;
        EMPPlayerEvent playerEvent;
        Intrinsics.checkNotNullParameter(empEvent, "empEvent");
        String sessionId = this.playerStateProvider.getPlaybackSessionId();
        if (empEvent == EMPEvent.PLAYBACK_STARTED && !Intrinsics.areEqual(sessionId, this.currentEmpPlaybackSessionId)) {
            String str = this.currentEmpPlaybackSessionId;
            if (str != null && (playerEvent = getPlayerEvent((eMPEvent = EMPEvent.PLAYBACK_SESSION_ENDED), str)) != null) {
                this.eventsDeque.addLast(playerEvent);
                LOG.debug("Enqueued " + eMPEvent.getEventName() + " event for playbackSessionId - " + str);
            }
            if (isAomInitiatedEmpPlayback(sessionId)) {
                this.currentEmpPlaybackSessionId = sessionId;
                EMPEvent eMPEvent2 = EMPEvent.PLAYBACK_SESSION_STARTED;
                Intrinsics.checkNotNullExpressionValue(sessionId, "sessionId");
                EMPPlayerEvent playerEvent2 = getPlayerEvent(eMPEvent2, sessionId);
                if (playerEvent2 != null) {
                    this.eventsDeque.addLast(playerEvent2);
                    LOG.debug("Enqueued " + eMPEvent2.getEventName() + " event for playbackSessionId - " + ((Object) sessionId));
                }
            } else {
                this.currentEmpPlaybackSessionId = null;
            }
        }
        if (isAomInitiatedEmpPlayback(this.currentEmpPlaybackSessionId)) {
            Intrinsics.checkNotNullExpressionValue(sessionId, "sessionId");
            EMPPlayerEvent playerEvent3 = getPlayerEvent(empEvent, sessionId);
            if (playerEvent3 != null) {
                this.eventsDeque.addLast(playerEvent3);
                LOG.debug("Enqueued " + empEvent.getEventName() + " event for playbackSessionId - " + ((Object) sessionId));
            }
        }
        reportPlayerEvents(this.eventsDeque);
    }

    public final void handleEMPErrorReporting(EMPError empError) {
        Intrinsics.checkNotNullParameter(empError, "empError");
        if (isAomInitiatedEmpPlayback(this.playerStateProvider.getPlaybackSessionId())) {
            String playbackSessionId = this.playerStateProvider.getPlaybackSessionId();
            Intrinsics.checkNotNullExpressionValue(playbackSessionId, "playerStateProvider.playbackSessionId");
            EMPPlayerError eMPPlayerError = (EMPPlayerError) new EMPPlayerError.Builder(empError, playbackSessionId).withSkillToken(this.skillToken).addContext(this.playbackStateContextResolver.resolve()).addContext(this.empPlaybackStateContextResolver.resolve()).build();
            LOG.error(Intrinsics.stringPlus("Sending EMPPlayerErrorEvent - ", empError.getDescription()));
            postEvent(eMPPlayerError);
        }
    }

    public final void subscribeToEventsOfPodCastPlaybackController(GUIPlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        EMPPlaybackController eMPPlaybackController = playbackController instanceof EMPPlaybackController ? (EMPPlaybackController) playbackController : null;
        if (eMPPlaybackController != null) {
            this.disposables.add(subscribeToEmpEventObservable(eMPPlaybackController.getEmpEventsObservable()));
        }
        if (eMPPlaybackController == null) {
            return;
        }
        this.disposables.add(subscribeToEmpErrorObservable(eMPPlaybackController.getEmpErrorObservable()));
    }

    private final Disposable subscribeToEmpErrorObservable(Observable<EMPError> empErrorObservable) {
        Disposable disposableSubscribe = empErrorObservable.subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2451subscribeToEmpErrorObservable$lambda12(this.f$0, (EMPError) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2452subscribeToEmpErrorObservable$lambda13((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "empErrorObservable\n     …r(it.localizedMessage) })");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToEmpErrorObservable$lambda-12, reason: not valid java name */
    public static final void m2451subscribeToEmpErrorObservable$lambda12(EMPEventReporterPlugin this$0, EMPError empError) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(empError, "empError");
        this$0.handleEMPErrorReporting(empError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToEmpErrorObservable$lambda-13, reason: not valid java name */
    public static final void m2452subscribeToEmpErrorObservable$lambda13(Throwable th) {
        LOG.error(th.getLocalizedMessage());
    }

    private final Disposable subscribeToEmpEventObservable(Observable<EMPEvent> empEventObservable) {
        Disposable disposableSubscribe = empEventObservable.subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2453subscribeToEmpEventObservable$lambda14(this.f$0, (EMPEvent) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.emp.events.EMPEventReporterPlugin$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPEventReporterPlugin.m2454subscribeToEmpEventObservable$lambda15((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "empEventObservable\n     …r(it.localizedMessage) })");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToEmpEventObservable$lambda-14, reason: not valid java name */
    public static final void m2453subscribeToEmpEventObservable$lambda14(EMPEventReporterPlugin this$0, EMPEvent empEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(empEvent, "empEvent");
        this$0.handleEMPEventReporting(empEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToEmpEventObservable$lambda-15, reason: not valid java name */
    public static final void m2454subscribeToEmpEventObservable$lambda15(Throwable th) {
        LOG.error(th.getLocalizedMessage());
    }

    private final EMPPlayerEvent getPlayerEvent(EMPEvent empEvent, String sessionId) {
        String str = this.skillToken;
        if (str == null) {
            return null;
        }
        return (EMPPlayerEvent) new EMPPlayerEvent.Builder(empEvent, sessionId).withSkillToken(str).addContext(this.playbackStateContextResolver.resolve()).addContext(this.empPlaybackStateContextResolver.resolve()).build();
    }

    private final boolean isAomInitiatedEmpPlayback(String sessionId) {
        String str;
        if (sessionId == null || (str = this.aomEmpPlaybackSessionId) == null) {
            return false;
        }
        return Intrinsics.areEqual(sessionId, str);
    }

    public synchronized void reportPlayerEvents(Deque<EMPPlayerEvent> playerEventsDeque) {
        Intrinsics.checkNotNullParameter(playerEventsDeque, "playerEventsDeque");
        while (!playerEventsDeque.isEmpty()) {
            postEvent(playerEventsDeque.removeFirst());
        }
    }
}
