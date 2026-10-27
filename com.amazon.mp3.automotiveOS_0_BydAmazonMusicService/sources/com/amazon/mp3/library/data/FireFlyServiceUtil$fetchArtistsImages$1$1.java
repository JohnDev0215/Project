package com.amazon.mp3.library.data;

import com.amazon.music.platform.graphql.generated.ArtistsImagesQuery;
import io.reactivex.rxjava3.core.SingleEmitter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: FireFlyServiceUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.library.data.FireFlyServiceUtil$fetchArtistsImages$1$1", f = "FireFlyServiceUtil.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"artistsImagesMap"}, s = {"L$0"})
final class FireFlyServiceUtil$fetchArtistsImages$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<String> $artistAsins;
    final /* synthetic */ SingleEmitter<HashMap<String, List<ArtistsImagesQuery.Image>>> $emitter;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FireFlyServiceUtil$fetchArtistsImages$1$1(List<String> list, SingleEmitter<HashMap<String, List<ArtistsImagesQuery.Image>>> singleEmitter, Continuation<? super FireFlyServiceUtil$fetchArtistsImages$1$1> continuation) {
        super(2, continuation);
        this.$artistAsins = list;
        this.$emitter = singleEmitter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FireFlyServiceUtil$fetchArtistsImages$1$1 fireFlyServiceUtil$fetchArtistsImages$1$1 = new FireFlyServiceUtil$fetchArtistsImages$1$1(this.$artistAsins, this.$emitter, continuation);
        fireFlyServiceUtil$fetchArtistsImages$1$1.L$0 = obj;
        return fireFlyServiceUtil$fetchArtistsImages$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FireFlyServiceUtil$fetchArtistsImages$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HashMap<String, List<ArtistsImagesQuery.Image>> map;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            HashMap<String, List<ArtistsImagesQuery.Image>> map2 = new HashMap<>();
            List listChunked = CollectionsKt___CollectionsKt.chunked(this.$artistAsins, 100);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listChunked, 10));
            Iterator it = listChunked.iterator();
            while (it.hasNext()) {
                arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1((List) it.next(), map2, null), 3, null));
            }
            this.L$0 = map2;
            this.label = 1;
            if (AwaitKt.awaitAll(arrayList, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            map = map2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            map = (HashMap) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.$emitter.onSuccess(map);
        return Unit.INSTANCE;
    }
}
