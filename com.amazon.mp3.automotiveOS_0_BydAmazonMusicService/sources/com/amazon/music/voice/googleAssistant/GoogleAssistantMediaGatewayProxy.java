package com.amazon.music.voice.googleAssistant;

import com.amazon.music.platform.providers.MediaGatewayServiceProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: GoogleAssistantMediaGatewayProxy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/googleAssistant/GoogleAssistantMediaGatewayProxy;", "", "mediaGatewayServiceProvider", "Lcom/amazon/music/platform/providers/MediaGatewayServiceProvider;", "googleAssistantListener", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantListener;", "filterExplicitLanguage", "", "(Lcom/amazon/music/platform/providers/MediaGatewayServiceProvider;Lcom/amazon/music/voice/googleAssistant/GoogleAssistantListener;Z)V", "onPlayFromSearch", "", "voiceSearchQuery", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;", "(Lcom/amazon/music/voice/googleAssistant/GoogleAssistantVoiceSearchQuery;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GoogleAssistantMediaGatewayProxy {
    private final boolean filterExplicitLanguage;
    private final GoogleAssistantListener googleAssistantListener;
    private final MediaGatewayServiceProvider mediaGatewayServiceProvider;
    private static final String TAG = GoogleAssistantMediaGatewayProxy.class.getSimpleName();
    private static final Logger LOG = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(GoogleAssistantMediaGatewayProxy.class).getSimpleName());

    /* JADX INFO: renamed from: com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy$onPlayFromSearch$1, reason: invalid class name */
    /* JADX INFO: compiled from: GoogleAssistantMediaGatewayProxy.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy", f = "GoogleAssistantMediaGatewayProxy.kt", i = {0}, l = {44}, m = "onPlayFromSearch", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GoogleAssistantMediaGatewayProxy.this.onPlayFromSearch(null, this);
        }
    }

    public GoogleAssistantMediaGatewayProxy(MediaGatewayServiceProvider mediaGatewayServiceProvider, GoogleAssistantListener googleAssistantListener, boolean z) {
        Intrinsics.checkNotNullParameter(googleAssistantListener, "googleAssistantListener");
        this.mediaGatewayServiceProvider = mediaGatewayServiceProvider;
        this.googleAssistantListener = googleAssistantListener;
        this.filterExplicitLanguage = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object onPlayFromSearch(com.amazon.music.voice.googleAssistant.GoogleAssistantVoiceSearchQuery r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy$onPlayFromSearch$1 r0 = (com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy$onPlayFromSearch$1 r0 = new com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy$onPlayFromSearch$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r9 = r0.L$0
            com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy r9 = (com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L7d
        L2e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L36:
            kotlin.ResultKt.throwOnFailure(r10)
            com.amazon.music.platform.graphql.mediagateway.generated.type.GoogleAssistantInput r10 = new com.amazon.music.platform.graphql.mediagateway.generated.type.GoogleAssistantInput
            com.amazon.music.platform.graphql.mediagateway.generated.type.SettingsInput r2 = new com.amazon.music.platform.graphql.mediagateway.generated.type.SettingsInput
            com.amazon.music.platform.graphql.mediagateway.generated.type.MusicSettingsInput r5 = new com.amazon.music.platform.graphql.mediagateway.generated.type.MusicSettingsInput
            com.apollographql.apollo3.api.Optional$Companion r6 = com.apollographql.apollo3.api.Optional.INSTANCE
            boolean r7 = r8.filterExplicitLanguage
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            com.apollographql.apollo3.api.Optional r6 = r6.presentIfNotNull(r7)
            r5.<init>(r6)
            r2.<init>(r5)
            com.amazon.music.platform.graphql.mediagateway.generated.type.GoogleNLUInput r9 = r9.convertToGoogleNLUInput()
            r10.<init>(r2, r9)
            org.slf4j.Logger r9 = com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.LOG
            java.lang.String r2 = com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.TAG
            java.lang.String r5 = "Request to Music3PMediaGateway "
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r5, r10)
            r9.debug(r2, r5)
            com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery r9 = new com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery
            r9.<init>(r10)
            com.amazon.music.platform.providers.MediaGatewayServiceProvider r10 = r8.mediaGatewayServiceProvider
            if (r10 != 0) goto L71
            r9 = r8
            r10 = r4
            goto L7f
        L71:
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r10 = r10.recommend(r9, r0)
            if (r10 != r1) goto L7c
            return r1
        L7c:
            r9 = r8
        L7d:
            com.amazon.music.platform.Outcome r10 = (com.amazon.music.platform.Outcome) r10
        L7f:
            boolean r0 = r10 instanceof com.amazon.music.platform.Outcome.Success
            if (r0 == 0) goto La2
            org.slf4j.Logger r0 = com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.LOG
            java.lang.String r1 = com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.TAG
            com.amazon.music.platform.Outcome$Success r10 = (com.amazon.music.platform.Outcome.Success) r10
            java.lang.Object r2 = r10.getValue()
            java.lang.String r3 = "Data from Music3PMediaGateway "
            java.lang.String r2 = kotlin.jvm.internal.Intrinsics.stringPlus(r3, r2)
            r0.debug(r1, r2)
            com.amazon.music.voice.googleAssistant.GoogleAssistantListener r9 = r9.googleAssistantListener
            java.lang.Object r10 = r10.getValue()
            java.util.List r10 = (java.util.List) r10
            r9.onMediaGatewayResponse(r10)
            goto Laa
        La2:
            com.amazon.music.voice.googleAssistant.GoogleAssistantListener r9 = r9.googleAssistantListener
            com.amazon.music.voice.googleAssistant.GoogleAssistantError r10 = com.amazon.music.voice.googleAssistant.GoogleAssistantError.GATEWAY_SERVER_ERROR
            r0 = 2
            com.amazon.music.voice.googleAssistant.GoogleAssistantListener.DefaultImpls.onError$default(r9, r10, r4, r0, r4)
        Laa:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy.onPlayFromSearch(com.amazon.music.voice.googleAssistant.GoogleAssistantVoiceSearchQuery, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
