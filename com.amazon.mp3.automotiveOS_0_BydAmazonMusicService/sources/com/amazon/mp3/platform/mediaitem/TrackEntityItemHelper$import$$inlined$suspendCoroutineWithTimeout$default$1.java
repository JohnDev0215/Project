package com.amazon.mp3.platform.mediaitem;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1", f = "TrackEntityItemHelper.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
public final class TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MusicTrack>, Object> {
    final /* synthetic */ EntityItemIdentifier $identifier$inlined;
    int label;
    final /* synthetic */ TrackEntityItemHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1(Continuation continuation, EntityItemIdentifier entityItemIdentifier, TrackEntityItemHelper trackEntityItemHelper) {
        super(2, continuation);
        this.$identifier$inlined = entityItemIdentifier;
        this.this$0 = trackEntityItemHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1(continuation, this.$identifier$inlined, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super MusicTrack> continuation) {
        return ((TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            String identifierType = this.$identifier$inlined.getId().getIdentifierType();
            if (!Intrinsics.areEqual(this.$identifier$inlined.getType(), SingleType.Track.INSTANCE) || (!Intrinsics.areEqual(identifierType, "ASIN") && !Intrinsics.areEqual(identifierType, "OBJECT_ID"))) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new Throwable("EntityItemIdentifier not suitable to fetch track"))));
            }
            String identifier = this.$identifier$inlined.getId().getIdentifier();
            TrackRequester trackRequester = new TrackRequester(this.this$0.getContext());
            if (Intrinsics.areEqual(identifierType, "ASIN")) {
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(trackRequester.getTrackFromAsin(identifier)));
            } else if (Intrinsics.areEqual(identifierType, "OBJECT_ID")) {
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(new MusicTrack(AmazonApplication.getLibraryItemFactory(), MediaProvider.Tracks.getContentUri("cirrus", identifier))));
            } else {
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new Throwable(Intrinsics.stringPlus(identifierType, " not suitable to fetch track")))));
            }
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
