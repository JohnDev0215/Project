package com.amazon.music.voice.emp.playbackstatecontext;

import android.util.Log;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContext;
import com.amazon.music.voice.emp.playercookie.PlayerCookie;
import com.amazon.music.voice.playbackstate.PlayerStateProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EMPPlaybackStateContextResolver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\u0010\tJ\b\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContextResolver;", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContextResolver;", "Lcom/amazon/music/voice/emp/playbackstatecontext/EMPPlaybackStateContext;", "version", "", "playerStateProvider", "Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;", "empSkillTokenObservable", "Lio/reactivex/Observable;", "(Ljava/lang/String;Lcom/amazon/music/voice/playbackstate/PlayerStateProvider;Lio/reactivex/Observable;)V", "disposable", "Lio/reactivex/disposables/Disposable;", "skillToken", "resolve", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EMPPlaybackStateContextResolver implements ContextResolver<EMPPlaybackStateContext> {
    private static final String TAG = EMPPlaybackStateContext.class.getSimpleName();
    private final Disposable disposable;
    private final PlayerStateProvider playerStateProvider;
    private String skillToken;
    private final String version;

    public EMPPlaybackStateContextResolver(String str, PlayerStateProvider playerStateProvider, Observable<String> empSkillTokenObservable) {
        Intrinsics.checkNotNullParameter(playerStateProvider, "playerStateProvider");
        Intrinsics.checkNotNullParameter(empSkillTokenObservable, "empSkillTokenObservable");
        this.version = str;
        this.playerStateProvider = playerStateProvider;
        Disposable disposableSubscribe = empSkillTokenObservable.subscribe(new Consumer() { // from class: com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContextResolver$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPPlaybackStateContextResolver.m2457_init_$lambda0(this.f$0, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.voice.emp.playbackstatecontext.EMPPlaybackStateContextResolver$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EMPPlaybackStateContextResolver.m2458_init_$lambda1((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "empSkillTokenObservable.…alizedMessage)\n        })");
        this.disposable = disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m2457_init_$lambda0(EMPPlaybackStateContextResolver this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.skillToken = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m2458_init_$lambda1(Throwable th) {
        Log.d(TAG, th.getLocalizedMessage());
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
    public EMPPlaybackStateContext resolve() {
        return new EMPPlaybackStateContext.Builder().withPlayerCookie(PlayerCookie.VERSION3.getCookie()).withSessionId(this.playerStateProvider.getPlaybackSessionId()).withSkillToken(this.skillToken).withPlayerVersion(this.version).setLoggedInState(true).build();
    }
}
