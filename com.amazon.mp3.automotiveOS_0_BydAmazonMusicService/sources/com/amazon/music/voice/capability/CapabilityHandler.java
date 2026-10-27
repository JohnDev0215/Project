package com.amazon.music.voice.capability;

import com.amazon.alexa.voice.core.processor.superbowl.AccessTokenProvider;
import com.amazon.music.platform.aom.Capabilities$Builder;
import com.amazon.music.platform.aom.Capability;
import com.amazon.music.platform.aom.CapabilityRegistry$Interface;
import com.amazon.music.platform.aom.CapabilityRegistry$Version;
import com.amazon.music.platform.aom.launcher.LauncherConfiguration;
import com.amazon.music.voice.AlexaAccessProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CapabilityHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/voice/capability/CapabilityHandler;", "", "", "registerCapabilities", "Lcom/amazon/music/voice/AlexaAccessProvider;", "alexaAccessProvider", "Lcom/amazon/music/voice/AlexaAccessProvider;", "getAlexaAccessProvider", "()Lcom/amazon/music/voice/AlexaAccessProvider;", "", "Lcom/amazon/music/platform/aom/Capability;", "supportedCapabilities", "Ljava/util/List;", "Lcom/amazon/music/voice/capability/CapabilityAgent;", "capabilityAgent", "Lcom/amazon/music/voice/capability/CapabilityAgent;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/Job;", "coroutineJob", "Lkotlinx/coroutines/Job;", "Lcom/amazon/alexa/voice/core/processor/superbowl/AccessTokenProvider;", "accessTokenProvider", "<init>", "(Lcom/amazon/alexa/voice/core/processor/superbowl/AccessTokenProvider;Lcom/amazon/music/voice/AlexaAccessProvider;)V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class CapabilityHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CapabilityHandler.class.getSimpleName());
    private final AlexaAccessProvider alexaAccessProvider;
    private final CapabilityAgent capabilityAgent;
    private final Job coroutineJob;
    private final CoroutineScope coroutineScope;
    private final List<Capability> supportedCapabilities;

    /* JADX WARN: Type inference failed for: r6v1, types: [com.amazon.music.platform.aom.Capabilities$Builder] */
    public CapabilityHandler(AccessTokenProvider accessTokenProvider, AlexaAccessProvider alexaAccessProvider) {
        Intrinsics.checkNotNullParameter(accessTokenProvider, "accessTokenProvider");
        Intrinsics.checkNotNullParameter(alexaAccessProvider, "alexaAccessProvider");
        this.alexaAccessProvider = alexaAccessProvider;
        this.capabilityAgent = new CapabilityAgent(accessTokenProvider);
        ?? r6 = new Object() { // from class: com.amazon.music.platform.aom.Capabilities$Builder
            private final List<Capability> capabilitiesCollection = new ArrayList();

            public final Capabilities$Builder with(Capability capability) {
                Intrinsics.checkNotNullParameter(capability, "capability");
                this.capabilitiesCollection.add(capability);
                return this;
            }

            public final Collection<Capability> build() {
                return this.capabilitiesCollection;
            }

            public final Capabilities$Builder with(Capability... capabilities) {
                Intrinsics.checkNotNullParameter(capabilities, "capabilities");
                CollectionsKt__MutableCollectionsKt.addAll(this.capabilitiesCollection, capabilities);
                return this;
            }
        };
        CapabilityRegistry$Interface capabilityRegistry$Interface = CapabilityRegistry$Interface.SYSTEM;
        CapabilityRegistry$Version capabilityRegistry$Version = CapabilityRegistry$Version.VERSION_1_0;
        Capabilities$Builder capabilities$BuilderWith = r6.with(capabilityRegistry$Interface.with(capabilityRegistry$Version), CapabilityRegistry$Interface.AUDIOPLAYER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.SPEECHRECOGNIZER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.PLAYBACKCONTROLLER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.SETTINGS.with(capabilityRegistry$Version), CapabilityRegistry$Interface.SPEECHSYNTHESIZER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.EXTERNALMEDIAPLAYER.with(CapabilityRegistry$Version.VERSION_1_2), CapabilityRegistry$Interface.ALEXA_PLAYBACK_CONTROLLER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.ALEXA_PLAYLIST_CONTROLLER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.ALEXA_FAVORITES_CONTROLLER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.ALEXA_SEEK_CONTROLLER.with(capabilityRegistry$Version), CapabilityRegistry$Interface.ALEXA_PLAYBACK_STATE_REPORTER.with(capabilityRegistry$Version));
        if (alexaAccessProvider.isHyperloopFeatureSupported()) {
            capabilities$BuilderWith.with(CapabilityRegistry$Interface.LAUNCHER.with(CapabilityRegistry$Version.VERSION_0_2, LauncherConfiguration.INSTANCE.getCONFIGURATION()));
        }
        if (alexaAccessProvider.isTextCapabilityFeatureSupported()) {
            capabilities$BuilderWith.with(CapabilityRegistry$Interface.ALEXA_INPUT_TEXT.with(capabilityRegistry$Version));
        }
        this.supportedCapabilities = (List) capabilities$BuilderWith.build();
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.coroutineJob = completableJobSupervisorJob$default;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(completableJobSupervisorJob$default));
    }

    public final void registerCapabilities() {
        if (!this.supportedCapabilities.isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
        } else {
            LOG.warn("Unable to register capabilities. Supported capabilities is empty");
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.capability.CapabilityHandler$registerCapabilities$1, reason: invalid class name */
    /* JADX INFO: compiled from: CapabilityHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.voice.capability.CapabilityHandler$registerCapabilities$1", f = "CapabilityHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CapabilityHandler.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                CapabilityHandler.this.capabilityAgent.registerCapabilities(CapabilityHandler.this.supportedCapabilities);
            } catch (Exception e) {
                CapabilityHandler.LOG.error(Intrinsics.stringPlus("Unable to register capabilities. Failed with exception - ", e.getMessage()));
            }
            return Unit.INSTANCE;
        }
    }
}
