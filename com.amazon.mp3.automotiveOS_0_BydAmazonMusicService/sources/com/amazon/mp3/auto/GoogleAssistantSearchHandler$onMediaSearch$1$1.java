package com.amazon.mp3.auto;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.voice.googleAssistant.GoogleAssistantMediaGatewayProxy;
import com.amazon.music.voice.googleAssistant.GoogleAssistantVoiceSearchQuery;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GoogleAssistantSearchHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.auto.GoogleAssistantSearchHandler$onMediaSearch$1$1", f = "GoogleAssistantSearchHandler.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {})
final class GoogleAssistantSearchHandler$onMediaSearch$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ GoogleAssistantVoiceSearchQuery $voiceSearchQuery;
    int label;
    final /* synthetic */ GoogleAssistantSearchHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GoogleAssistantSearchHandler$onMediaSearch$1$1(GoogleAssistantSearchHandler googleAssistantSearchHandler, GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery, Continuation<? super GoogleAssistantSearchHandler$onMediaSearch$1$1> continuation) {
        super(2, continuation);
        this.this$0 = googleAssistantSearchHandler;
        this.$voiceSearchQuery = googleAssistantVoiceSearchQuery;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GoogleAssistantSearchHandler$onMediaSearch$1$1(this.this$0, this.$voiceSearchQuery, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GoogleAssistantSearchHandler$onMediaSearch$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            GoogleAssistantMediaGatewayProxy googleAssistantMediaGatewayProxy = new GoogleAssistantMediaGatewayProxy(this.this$0.getMediaGatewayServiceProvider(), this.this$0, AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
            GoogleAssistantVoiceSearchQuery googleAssistantVoiceSearchQuery = this.$voiceSearchQuery;
            this.label = 1;
            if (googleAssistantMediaGatewayProxy.onPlayFromSearch(googleAssistantVoiceSearchQuery, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
