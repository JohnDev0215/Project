package com.amazon.music.platform.aom.hints;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: AlexaHintsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.amazon.music.platform.aom.hints.AlexaHintsRepository", f = "AlexaHintsRepository.kt", i = {0, 0}, l = {48}, m = "getTemplatedHints$MusicPlatformAOM_release", n = {"this", "dae"}, s = {"L$0", "L$1"})
final class AlexaHintsRepository$getTemplatedHints$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AlexaHintsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AlexaHintsRepository$getTemplatedHints$1(AlexaHintsRepository alexaHintsRepository, Continuation<? super AlexaHintsRepository$getTemplatedHints$1> continuation) {
        super(continuation);
        this.this$0 = alexaHintsRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getTemplatedHints$MusicPlatformAOM_release(null, this);
    }
}
