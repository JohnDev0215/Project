package com.amazon.music.voice.launcher;

import android.content.Context;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.voice.core.Consumable;
import com.amazon.alexa.voice.core.VoiceResponder;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.platform.aom.launcher.LaunchTarget;
import com.amazon.music.platform.aom.launcher.events.EventTarget;
import com.amazon.music.platform.aom.launcher.events.LaunchOutcome;
import com.amazon.music.platform.aom.launcher.events.LaunchReason;
import com.amazon.music.voice.ContextAwareDeeplinkDelegate;
import com.amazon.music.voice.launcher.events.LaunchTargetAttemptedEvent;
import com.amazon.music.voice.launcher.events.LaunchTargetFailedEvent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AlexaLauncherPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b \u0010!J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\bH\u0014J!\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/music/voice/launcher/AlexaLauncherPlugin;", "Lcom/amazon/alexa/voice/superbowl/VoicePlugin;", "Lcom/amazon/alexa/voice/core/VoiceResponder;", "createLaunchResponder", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "error", "Lcom/amazon/music/platform/aom/launcher/events/LaunchReason;", "getLaunchFailedReasonFromLaunchError", "", "onInitialize", "Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective;", "directive", "", "isDebug", "handleLaunchTargetDirective$DMMVoice_release", "(Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective;Z)V", "handleLaunchTargetDirective", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlEvent;", NotificationCompat.CATEGORY_EVENT, "reportEvent", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;", "contextAwareDeeplinkDelegate", "Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;", "getContextAwareDeeplinkDelegate", "()Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;", "setContextAwareDeeplinkDelegate", "(Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;)V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public class AlexaLauncherPlugin extends VoicePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaLauncherPlugin.class.getSimpleName());
    private final Context context;
    private ContextAwareDeeplinkDelegate contextAwareDeeplinkDelegate;

    public AlexaLauncherPlugin(Context context, ContextAwareDeeplinkDelegate contextAwareDeeplinkDelegate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contextAwareDeeplinkDelegate, "contextAwareDeeplinkDelegate");
        this.context = context;
        this.contextAwareDeeplinkDelegate = contextAwareDeeplinkDelegate;
    }

    @Override // com.amazon.alexa.voice.superbowl.VoicePlugin
    protected void onInitialize() {
        super.onInitialize();
        addResponderInSequence(createLaunchResponder(), AlexaLaunchTargetDirective.class);
    }

    private final VoiceResponder createLaunchResponder() {
        return new VoiceResponder() { // from class: com.amazon.music.voice.launcher.AlexaLauncherPlugin$$ExternalSyntheticLambda0
            @Override // com.amazon.alexa.voice.core.VoiceResponder
            public final void respond(Consumable consumable) {
                AlexaLauncherPlugin.m2460createLaunchResponder$lambda0(this.f$0, consumable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createLaunchResponder$lambda-0, reason: not valid java name */
    public static final void m2460createLaunchResponder$lambda0(AlexaLauncherPlugin this$0, Consumable consumable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object obj = consumable.get();
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.voice.launcher.AlexaLaunchTargetDirective");
        }
        handleLaunchTargetDirective$DMMVoice_release$default(this$0, (AlexaLaunchTargetDirective) obj, false, 2, null);
        consumable.accept();
    }

    public static /* synthetic */ void handleLaunchTargetDirective$DMMVoice_release$default(AlexaLauncherPlugin alexaLauncherPlugin, AlexaLaunchTargetDirective alexaLaunchTargetDirective, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleLaunchTargetDirective");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        alexaLauncherPlugin.handleLaunchTargetDirective$DMMVoice_release(alexaLaunchTargetDirective, z);
    }

    public final void handleLaunchTargetDirective$DMMVoice_release(final AlexaLaunchTargetDirective directive, final boolean isDebug) {
        Intrinsics.checkNotNullParameter(directive, "directive");
        final LaunchTarget target = directive.getTarget();
        final String messageId = directive.getMessageId();
        Uri uri = Uri.parse(target.getIdentifier());
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        this.contextAwareDeeplinkDelegate.handle(uri, new LauncherTokens(uri, directive.getToken(), target.getToken()), new Function2<LaunchTargetError, LauncherTokens, Unit>() { // from class: com.amazon.music.voice.launcher.AlexaLauncherPlugin$handleLaunchTargetDirective$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(LaunchTargetError launchTargetError, LauncherTokens launcherTokens) {
                invoke2(launchTargetError, launcherTokens);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LaunchTargetError launchTargetError, LauncherTokens tokens) {
                Intrinsics.checkNotNullParameter(tokens, "tokens");
                if (launchTargetError == null) {
                    AlexaLauncherPlugin alexaLauncherPlugin = this.this$0;
                    boolean z = isDebug;
                    AlexaLaunchTargetDirective alexaLaunchTargetDirective = directive;
                    LaunchTarget launchTarget = target;
                    String str = messageId;
                    if (z) {
                        return;
                    }
                    Logger logger = AlexaLauncherPlugin.LOG;
                    LaunchOutcome launchOutcome = LaunchOutcome.TARGET_LAUNCHED;
                    logger.info(Intrinsics.stringPlus("Sending LaunchTargetAttemptedEvent with Outcome : ", launchOutcome));
                    alexaLauncherPlugin.reportEvent(new LaunchTargetAttemptedEvent.Builder(alexaLaunchTargetDirective.getToken(), new EventTarget(launchTarget.getToken()), launchOutcome, null, str, 8, null).build());
                    return;
                }
                if (isDebug) {
                    return;
                }
                AlexaLauncherPlugin.LOG.error(Intrinsics.stringPlus("Cannot launch target due to error: ", launchTargetError));
                LaunchReason launchFailedReasonFromLaunchError = this.this$0.getLaunchFailedReasonFromLaunchError(launchTargetError);
                Logger logger2 = AlexaLauncherPlugin.LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Sending LaunchTargetFailedEvent with Outcome : ");
                LaunchOutcome launchOutcome2 = LaunchOutcome.TARGET_NOT_LAUNCHED;
                sb.append(launchOutcome2);
                sb.append(" and reason: ");
                sb.append(launchFailedReasonFromLaunchError.getLaunchReason());
                logger2.info(sb.toString());
                this.this$0.reportEvent(new LaunchTargetFailedEvent.Builder(directive.getToken(), new EventTarget(target.getToken()), launchOutcome2, CollectionsKt__CollectionsKt.mutableListOf(launchFailedReasonFromLaunchError), messageId).build());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LaunchReason getLaunchFailedReasonFromLaunchError(LaunchTargetError error) {
        if (error instanceof UnknownContext ? true : error instanceof CannotHandle ? true : error instanceof UnknownTargetError) {
            return LaunchReason.UNKNOWN_ERROR;
        }
        throw new NoWhenBranchMatchedException();
    }

    public void reportEvent(SuperbowlEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        postEvent(event);
    }
}
