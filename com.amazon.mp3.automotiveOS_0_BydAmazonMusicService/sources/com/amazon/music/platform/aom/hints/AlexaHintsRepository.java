package com.amazon.music.platform.aom.hints;

import com.amazon.music.platform.aom.PlatformProviderUtilKt;
import com.amazon.music.platform.aom.hints.dae.DAE;
import com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager;
import com.amazon.music.platform.aom.hints.provider.RemoteAlexaHintsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: AlexaHintsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001d\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AlexaHintsRepository;", "", "alexaHintsCacheManager", "Lcom/amazon/music/platform/aom/hints/provider/AlexaHintsCacheManager;", "alexaHintsProvider", "Lcom/amazon/music/platform/aom/hints/provider/RemoteAlexaHintsProvider;", "(Lcom/amazon/music/platform/aom/hints/provider/AlexaHintsCacheManager;Lcom/amazon/music/platform/aom/hints/provider/RemoteAlexaHintsProvider;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "cacheAlexaHints", "", "dae", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "alexaHints", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "getTemplatedHints", "getTemplatedHints$MusicPlatformAOM_release", "(Lcom/amazon/music/platform/aom/hints/dae/DAE;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetAlexaHintsCache", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AlexaHintsRepository {
    private static final String TAG;
    private final AlexaHintsCacheManager alexaHintsCacheManager;
    private final RemoteAlexaHintsProvider alexaHintsProvider;
    private final CoroutineScope coroutineScope;

    public AlexaHintsRepository(AlexaHintsCacheManager alexaHintsCacheManager, RemoteAlexaHintsProvider alexaHintsProvider) {
        Intrinsics.checkNotNullParameter(alexaHintsCacheManager, "alexaHintsCacheManager");
        Intrinsics.checkNotNullParameter(alexaHintsProvider, "alexaHintsProvider");
        this.alexaHintsCacheManager = alexaHintsCacheManager;
        this.alexaHintsProvider = alexaHintsProvider;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(PlatformProviderUtilKt.getIoDispatcher());
    }

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(AlexaHintsRepository.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    private final void cacheAlexaHints(DAE dae, AlexaHints alexaHints) {
        this.alexaHintsCacheManager.updateHints(dae, alexaHints);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getTemplatedHints$MusicPlatformAOM_release(com.amazon.music.platform.aom.hints.dae.DAE r11, kotlin.coroutines.Continuation<? super com.amazon.music.platform.aom.hints.AlexaHints> r12) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$1
            if (r0 == 0) goto L13
            r0 = r12
            com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$1 r0 = (com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$1 r0 = new com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$1
            r0.<init>(r10, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r11 = r0.L$1
            com.amazon.music.platform.aom.hints.dae.DAE r11 = (com.amazon.music.platform.aom.hints.dae.DAE) r11
            java.lang.Object r0 = r0.L$0
            com.amazon.music.platform.aom.hints.AlexaHintsRepository r0 = (com.amazon.music.platform.aom.hints.AlexaHintsRepository) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L5c
        L32:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager r12 = r10.alexaHintsCacheManager
            com.amazon.music.platform.aom.hints.AlexaHints r12 = r12.getHints(r11)
            if (r12 != 0) goto L91
            kotlin.coroutines.CoroutineContext r12 = com.amazon.music.platform.aom.PlatformProviderUtilKt.getIoDispatcher()
            com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$futureHints$1 r2 = new com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$futureHints$1
            r2.<init>(r10, r11, r3)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r4
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r2, r0)
            if (r12 != r1) goto L5b
            return r1
        L5b:
            r0 = r10
        L5c:
            com.amazon.music.platform.aom.Outcome r12 = (com.amazon.music.platform.aom.Outcome) r12
            boolean r1 = r12 instanceof com.amazon.music.platform.aom.Outcome.Success
            if (r1 == 0) goto L75
            com.amazon.music.platform.aom.Outcome$Success r12 = (com.amazon.music.platform.aom.Outcome.Success) r12
            java.lang.Object r1 = r12.getValue()
            com.amazon.music.platform.aom.hints.AlexaHints r1 = (com.amazon.music.platform.aom.hints.AlexaHints) r1
            r0.cacheAlexaHints(r11, r1)
            java.lang.Object r11 = r12.getValue()
            r3 = r11
            com.amazon.music.platform.aom.hints.AlexaHints r3 = (com.amazon.music.platform.aom.hints.AlexaHints) r3
            goto L92
        L75:
            com.amazon.music.platform.aom.Logger r4 = com.amazon.music.platform.aom.Logger.INSTANCE
            java.lang.String r5 = com.amazon.music.platform.aom.hints.AlexaHintsRepository.TAG
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Received null hints from AlexaHintService for dae: "
            r12.append(r0)
            r12.append(r11)
            java.lang.String r6 = r12.toString()
            r7 = 0
            r8 = 4
            r9 = 0
            com.amazon.music.platform.aom.Logger.error$default(r4, r5, r6, r7, r8, r9)
            goto L92
        L91:
            r3 = r12
        L92:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.aom.hints.AlexaHintsRepository.getTemplatedHints$MusicPlatformAOM_release(com.amazon.music.platform.aom.hints.dae.DAE, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void resetAlexaHintsCache() {
        this.alexaHintsCacheManager.removeAllHints();
    }
}
