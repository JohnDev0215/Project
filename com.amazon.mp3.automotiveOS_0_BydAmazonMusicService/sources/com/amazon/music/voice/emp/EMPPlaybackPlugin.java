package com.amazon.music.voice.emp;

import android.net.Uri;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.voice.EMPPlaybackController;
import com.amazon.music.voice.UnsupportedActionHandler;
import com.amazon.music.voice.emp.directives.EMPPlaybackPlayDirective;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: EMPPlaybackPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015J\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0015J\b\u0010\u0017\u001a\u00020\rH\u0014R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/voice/emp/EMPPlaybackPlugin;", "Lcom/amazon/alexa/voice/superbowl/VoicePlugin;", "empPlaybackController", "Lcom/amazon/music/voice/EMPPlaybackController;", "(Lcom/amazon/music/voice/EMPPlaybackController;)V", "aomEmpPlaybackSessionIdSubject", "Lio/reactivex/subjects/PublishSubject;", "", "kotlin.jvm.PlatformType", "empSkillTokenSubject", "unsupportedActionHandler", "Lcom/amazon/music/voice/UnsupportedActionHandler;", "addSupportedDirectivesResponders", "", "addUnsupportedActionHandler", "createEMPPlayResponder", "Lcom/amazon/alexa/voice/core/VoiceResponder;", "forceFullScreenPlayback", "Landroid/net/Uri;", "uri", "getAomEmpPlaybackSessionIdObservable", "Lio/reactivex/Observable;", "getEMPSkillTokenObservable", "onInitialize", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EMPPlaybackPlugin extends VoicePlugin {
    private final PublishSubject<String> aomEmpPlaybackSessionIdSubject;
    private final EMPPlaybackController empPlaybackController;
    private final PublishSubject<String> empSkillTokenSubject;
    private UnsupportedActionHandler unsupportedActionHandler;

    public EMPPlaybackPlugin(EMPPlaybackController empPlaybackController) {
        Intrinsics.checkNotNullParameter(empPlaybackController, "empPlaybackController");
        this.empPlaybackController = empPlaybackController;
        PublishSubject<String> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create<String>()");
        this.empSkillTokenSubject = publishSubjectCreate;
        PublishSubject<String> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create<String>()");
        this.aomEmpPlaybackSessionIdSubject = publishSubjectCreate2;
        empPlaybackController.attachPlaybackEventObservers();
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        addSupportedDirectivesResponders();
    }

    private final VoiceResponder createEMPPlayResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.emp.EMPPlaybackPlugin$$ExternalSyntheticLambda0
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public final void respond(Consumable consumable) {
                EMPPlaybackPlugin.m2442createEMPPlayResponder$lambda2(this.f$0, consumable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createEMPPlayResponder$lambda-2, reason: not valid java name */
    public static final void m2442createEMPPlayResponder$lambda2(EMPPlaybackPlugin this$0, Consumable consumable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object obj = consumable.get();
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.voice.emp.directives.EMPPlaybackPlayDirective");
        }
        EMPPlaybackPlayDirective eMPPlaybackPlayDirective = (EMPPlaybackPlayDirective) obj;
        Uri uri = Uri.parse(eMPPlaybackPlayDirective.getBuilder().getPlaybackContextToken());
        Uri uriForceFullScreenPlayback = uri == null ? null : this$0.forceFullScreenPlayback(uri);
        String playbackSessionId = eMPPlaybackPlayDirective.getBuilder().getPlaybackSessionId();
        if (playbackSessionId != null && uriForceFullScreenPlayback != null) {
            this$0.aomEmpPlaybackSessionIdSubject.onNext(playbackSessionId);
            this$0.empPlaybackController.playWithUri(uriForceFullScreenPlayback, playbackSessionId);
            String skillToken = eMPPlaybackPlayDirective.getBuilder().getSkillToken();
            if (skillToken != null) {
                this$0.empSkillTokenSubject.onNext(skillToken);
            }
            consumable.accept();
            return;
        }
        UnsupportedActionHandler unsupportedActionHandler = this$0.unsupportedActionHandler;
        if (unsupportedActionHandler != null) {
            unsupportedActionHandler.onUnsupportedActionReceived();
        }
        consumable.reject(new RejectedExecutionException("Unsupported action - EMP Play"));
    }

    public final void addUnsupportedActionHandler(UnsupportedActionHandler unsupportedActionHandler) {
        Intrinsics.checkNotNullParameter(unsupportedActionHandler, "unsupportedActionHandler");
        this.unsupportedActionHandler = unsupportedActionHandler;
    }

    private final void addSupportedDirectivesResponders() {
        addResponderInSequence(createEMPPlayResponder(), EMPPlaybackPlayDirective.class);
    }

    public final Observable<String> getEMPSkillTokenObservable() {
        return this.empSkillTokenSubject;
    }

    public final Observable<String> getAomEmpPlaybackSessionIdObservable() {
        return this.aomEmpPlaybackSessionIdSubject;
    }

    private final Uri forceFullScreenPlayback(Uri uri) {
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder builderClearQuery = uri.buildUpon().clearQuery();
        String queryParameter = uri.getQueryParameter("do");
        if (queryParameter == null) {
            queryParameter = "";
        }
        String strReplace = StringsKt__StringsJVMKt.replace(queryParameter, PageUriUtils.DO_PLAY_VALUE, PageUriUtils.DO_PLAYFULL_VALUE, true);
        for (String str : queryParameterNames) {
            builderClearQuery.appendQueryParameter(str, (Intrinsics.areEqual(str, "do") && StringsKt__StringsKt.contains((CharSequence) queryParameter, (CharSequence) PageUriUtils.DO_PLAY_VALUE, true) && !StringsKt__StringsKt.contains$default((CharSequence) queryParameter, (CharSequence) PageUriUtils.DO_PLAYFULL_VALUE, false, 2, (Object) null)) ? strReplace : queryParameter);
        }
        Uri uriBuild = builderClearQuery.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "fullScreenUriBuilder.build()");
        return uriBuild;
    }
}
