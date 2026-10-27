package com.amazon.mp3.library.mylibrary.likesandfollows;

import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1", f = "LikesAndFollowsFireflyCalls.kt", i = {}, l = {142, voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID}, m = "invokeSuspend", n = {}, s = {})
final class LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $asin;
    final /* synthetic */ Function1<Boolean, Unit> $followStateCallback;
    final /* synthetic */ boolean $isFollowing;
    final /* synthetic */ ArtistMetadata $metadata;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1(boolean z, String str, ArtistMetadata artistMetadata, Function1<? super Boolean, Unit> function1, Continuation<? super LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1> continuation) {
        super(2, continuation);
        this.$isFollowing = z;
        this.$asin = str;
        this.$metadata = artistMetadata;
        this.$followStateCallback = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1(this.$isFollowing, this.$asin, this.$metadata, this.$followStateCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer num;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$isFollowing) {
                String str = this.$asin;
                this.label = 1;
                obj = LikesAndFollowsFireflyCallsKt.unfollowArtistFireFlyMutation(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                num = (Integer) obj;
            } else {
                String str2 = this.$asin;
                this.label = 2;
                obj = LikesAndFollowsFireflyCallsKt.followArtistFireFlyMutation(str2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                num = (Integer) obj;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            num = (Integer) obj;
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            num = (Integer) obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (num != null) {
            Function1<Boolean, Unit> function1 = this.$followStateCallback;
            boolean z = this.$isFollowing;
            num.intValue();
            if (function1 != null) {
                function1.invoke(Boxing.boxBoolean(!z));
            }
        }
        LikesAndFollowsFireflyCallsKt.emitUiClickMetric(this.$isFollowing ? ActionType.UNFOLLOW_ARTIST : ActionType.FOLLOW_ARTIST, this.$asin, this.$metadata.getViewId(), EntityType.ARTIST);
        return Unit.INSTANCE;
    }
}
