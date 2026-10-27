package com.amazon.mp3.library.data;

import com.amazon.music.platform.graphql.generated.ArtistsImagesQuery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: FireFlyServiceUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.library.data.FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1", f = "FireFlyServiceUtil.kt", i = {}, l = {37}, m = "invokeSuspend", n = {}, s = {})
final class FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HashMap<String, List<ArtistsImagesQuery.Image>> $artistsImagesMap;
    final /* synthetic */ List<String> $asins;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1(List<String> list, HashMap<String, List<ArtistsImagesQuery.Image>> map, Continuation<? super FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1> continuation) {
        super(2, continuation);
        this.$asins = list;
        this.$artistsImagesMap = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1(this.$asins, this.$artistsImagesMap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FireFlyServiceUtil$fetchArtistsImages$1$1$requests$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List<ArtistsImagesQuery.Artist> artists;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.$asins.isEmpty()) {
                FireFlyServiceUtil fireFlyServiceUtil = FireFlyServiceUtil.INSTANCE;
                List<String> list = this.$asins;
                this.label = 1;
                obj = fireFlyServiceUtil.invokeFireFlyArtistsImagesQuery(list, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ArtistsImagesQuery.Data data = (ArtistsImagesQuery.Data) obj;
        if (data != null && (artists = data.getArtists()) != null) {
            HashMap<String, List<ArtistsImagesQuery.Image>> map = this.$artistsImagesMap;
            Iterator<ArtistsImagesQuery.Artist> it = artists.iterator();
            while (it.hasNext()) {
                ArtistsImagesQuery.Artist next = it.next();
                String id = next == null ? null : next.getId();
                List<ArtistsImagesQuery.Image> images = next != null ? next.getImages() : null;
                if (id != null && images != null && !map.containsKey(id)) {
                    map.put(id, images);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
