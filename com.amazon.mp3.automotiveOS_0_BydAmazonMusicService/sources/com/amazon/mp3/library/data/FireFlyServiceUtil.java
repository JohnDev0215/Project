package com.amazon.mp3.library.data;

import com.amazon.music.platform.graphql.generated.ArtistsImagesQuery;
import com.amazon.music.platform.graphql.generated.TracksIdsQuery;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.future.FutureKt;

/* JADX INFO: compiled from: FireFlyServiceUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007JF\u0010\f\u001a4\u00120\u0012.\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00020\tj\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0002`\u000b0\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J$\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J+\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0007R\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00030\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/library/data/FireFlyServiceUtil;", "", "", "", "artistAsins", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Data;", "invokeFireFlyArtistsImagesQuery", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/reactivex/rxjava3/core/Single;", "Ljava/util/HashMap;", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Image;", "Lkotlin/collections/HashMap;", "fetchArtistsImages", "ids", "Ljava/util/concurrent/CompletableFuture;", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Track;", "getTrackAndAlbumAsinsAsync", "getTrackAndAlbumAsins", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FireFlyServiceUtil {
    public static final FireFlyServiceUtil INSTANCE = new FireFlyServiceUtil();
    private static final String TAG = FireFlyServiceUtil.class.getSimpleName();

    /* JADX INFO: renamed from: com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsins$1, reason: invalid class name */
    /* JADX INFO: compiled from: FireFlyServiceUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.data.FireFlyServiceUtil", f = "FireFlyServiceUtil.kt", i = {0}, l = {109}, m = "getTrackAndAlbumAsins", n = {"ids"}, s = {"L$0"})
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
            return FireFlyServiceUtil.this.getTrackAndAlbumAsins(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.data.FireFlyServiceUtil$invokeFireFlyArtistsImagesQuery$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FireFlyServiceUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.data.FireFlyServiceUtil", f = "FireFlyServiceUtil.kt", i = {}, l = {74}, m = "invokeFireFlyArtistsImagesQuery", n = {}, s = {})
    static final class C03641 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C03641(Continuation<? super C03641> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FireFlyServiceUtil.this.invokeFireFlyArtistsImagesQuery(null, this);
        }
    }

    private FireFlyServiceUtil() {
    }

    public final Single<HashMap<String, List<ArtistsImagesQuery.Image>>> fetchArtistsImages(final List<String> artistAsins) {
        Intrinsics.checkNotNullParameter(artistAsins, "artistAsins");
        Single<HashMap<String, List<ArtistsImagesQuery.Image>>> singleObserveOn = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.library.data.FireFlyServiceUtil$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws InterruptedException {
                FireFlyServiceUtil.m1229fetchArtistsImages$lambda0(artistAsins, singleEmitter);
            }
        }).observeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleObserveOn, "create<HashMap<String, L…bserveOn(Schedulers.io())");
        return singleObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchArtistsImages$lambda-0, reason: not valid java name */
    public static final void m1229fetchArtistsImages$lambda0(List artistAsins, SingleEmitter singleEmitter) throws InterruptedException {
        Intrinsics.checkNotNullParameter(artistAsins, "$artistAsins");
        BuildersKt__BuildersKt.runBlocking$default(null, new FireFlyServiceUtil$fetchArtistsImages$1$1(artistAsins, singleEmitter, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeFireFlyArtistsImagesQuery(java.util.List<java.lang.String> r6, kotlin.coroutines.Continuation<? super com.amazon.music.platform.graphql.generated.ArtistsImagesQuery.Data> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.library.data.FireFlyServiceUtil.C03641
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.library.data.FireFlyServiceUtil$invokeFireFlyArtistsImagesQuery$1 r0 = (com.amazon.mp3.library.data.FireFlyServiceUtil.C03641) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.data.FireFlyServiceUtil$invokeFireFlyArtistsImagesQuery$1 r0 = new com.amazon.mp3.library.data.FireFlyServiceUtil$invokeFireFlyArtistsImagesQuery$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            if (r2 != r4) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L68
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.music.platform.locator.Providers r7 = com.amazon.music.platform.locator.Providers.INSTANCE
            com.amazon.music.platform.AtomicMap r7 = r7.getProviderInstances()
            java.lang.Class<com.amazon.music.platform.providers.FireFlyServiceProvider> r2 = com.amazon.music.platform.providers.FireFlyServiceProvider.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            java.lang.String r2 = com.amazon.music.platform.locator.ProvidersKt.getOriginalClassName(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.Object r7 = r7.get(r2)
            boolean r2 = r7 instanceof com.amazon.music.platform.providers.FireFlyServiceProvider
            if (r2 != 0) goto L51
            r7 = r3
        L51:
            com.amazon.music.platform.providers.FireFlyServiceProvider r7 = (com.amazon.music.platform.providers.FireFlyServiceProvider) r7
            if (r7 == 0) goto L8c
            com.apollographql.apollo3.api.Optional$Present r2 = new com.apollographql.apollo3.api.Optional$Present
            r2.<init>(r6)
            com.amazon.music.platform.graphql.generated.ArtistsImagesQuery r6 = new com.amazon.music.platform.graphql.generated.ArtistsImagesQuery
            r6.<init>(r2)
            r0.label = r4
            java.lang.Object r7 = r7.query(r6, r0)
            if (r7 != r1) goto L68
            return r1
        L68:
            com.amazon.music.platform.Outcome r7 = (com.amazon.music.platform.Outcome) r7
            boolean r6 = r7 instanceof com.amazon.music.platform.Outcome.Success
            if (r6 == 0) goto L85
            com.amazon.music.platform.Outcome$Success r7 = (com.amazon.music.platform.Outcome.Success) r7
            java.lang.Object r6 = r7.getValue()
            com.apollographql.apollo3.api.ApolloResponse r6 = (com.apollographql.apollo3.api.ApolloResponse) r6
            D extends com.apollographql.apollo3.api.Operation$Data r6 = r6.data
            if (r6 == 0) goto L7d
            com.amazon.music.platform.graphql.generated.ArtistsImagesQuery$Data r6 = (com.amazon.music.platform.graphql.generated.ArtistsImagesQuery.Data) r6
            return r6
        L7d:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "null cannot be cast to non-null type com.amazon.music.platform.graphql.generated.ArtistsImagesQuery.Data"
            r6.<init>(r7)
            throw r6
        L85:
            java.lang.String r6 = com.amazon.mp3.library.data.FireFlyServiceUtil.TAG
            java.lang.String r7 = "Failed to retrieve Artists Images from FireFly"
            com.amazon.mp3.util.Log.error(r6, r7)
        L8c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.data.FireFlyServiceUtil.invokeFireFlyArtistsImagesQuery(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsinsAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FireFlyServiceUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Track;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsinsAsync$1", f = "FireFlyServiceUtil.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
    static final class C03631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TracksIdsQuery.Track>>, Object> {
        final /* synthetic */ List<String> $ids;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03631(List<String> list, Continuation<? super C03631> continuation) {
            super(2, continuation);
            this.$ids = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03631(this.$ids, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TracksIdsQuery.Track>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<TracksIdsQuery.Track>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<TracksIdsQuery.Track>> continuation) {
            return ((C03631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FireFlyServiceUtil fireFlyServiceUtil = FireFlyServiceUtil.INSTANCE;
                List<String> list = this.$ids;
                this.label = 1;
                obj = fireFlyServiceUtil.getTrackAndAlbumAsins(list, this);
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

    public final CompletableFuture<List<TracksIdsQuery.Track>> getTrackAndAlbumAsinsAsync(List<String> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        return FutureKt.future$default(GlobalScope.INSTANCE, null, null, new C03631(ids, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getTrackAndAlbumAsins(java.util.List<java.lang.String> r7, kotlin.coroutines.Continuation<? super java.util.List<com.amazon.music.platform.graphql.generated.TracksIdsQuery.Track>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.library.data.FireFlyServiceUtil.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsins$1 r0 = (com.amazon.mp3.library.data.FireFlyServiceUtil.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsins$1 r0 = new com.amazon.mp3.library.data.FireFlyServiceUtil$getTrackAndAlbumAsins$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r7 = r0.L$0
            java.util.List r7 = (java.util.List) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L71
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            com.amazon.music.platform.locator.Providers r8 = com.amazon.music.platform.locator.Providers.INSTANCE
            com.amazon.music.platform.AtomicMap r8 = r8.getProviderInstances()
            java.lang.Class<com.amazon.music.platform.providers.FireFlyServiceProvider> r2 = com.amazon.music.platform.providers.FireFlyServiceProvider.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            java.lang.String r2 = com.amazon.music.platform.locator.ProvidersKt.getOriginalClassName(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.Object r8 = r8.get(r2)
            boolean r2 = r8 instanceof com.amazon.music.platform.providers.FireFlyServiceProvider
            if (r2 != 0) goto L55
            r8 = r4
        L55:
            com.amazon.music.platform.providers.FireFlyServiceProvider r8 = (com.amazon.music.platform.providers.FireFlyServiceProvider) r8
            com.amazon.music.platform.graphql.generated.TracksIdsQuery r2 = new com.amazon.music.platform.graphql.generated.TracksIdsQuery
            com.apollographql.apollo3.api.Optional$Companion r5 = com.apollographql.apollo3.api.Optional.INSTANCE
            com.apollographql.apollo3.api.Optional r5 = r5.presentIfNotNull(r7)
            r2.<init>(r5)
            if (r8 != 0) goto L66
            r8 = r4
            goto L73
        L66:
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r8.query(r2, r0)
            if (r8 != r1) goto L71
            return r1
        L71:
            com.amazon.music.platform.Outcome r8 = (com.amazon.music.platform.Outcome) r8
        L73:
            boolean r0 = r8 instanceof com.amazon.music.platform.Outcome.Success
            if (r0 == 0) goto L8b
            com.amazon.music.platform.Outcome$Success r8 = (com.amazon.music.platform.Outcome.Success) r8
            java.lang.Object r7 = r8.getValue()
            com.apollographql.apollo3.api.ApolloResponse r7 = (com.apollographql.apollo3.api.ApolloResponse) r7
            D extends com.apollographql.apollo3.api.Operation$Data r7 = r7.data
            com.amazon.music.platform.graphql.generated.TracksIdsQuery$Data r7 = (com.amazon.music.platform.graphql.generated.TracksIdsQuery.Data) r7
            if (r7 != 0) goto L86
            goto L8a
        L86:
            java.util.List r4 = r7.getTracks()
        L8a:
            return r4
        L8b:
            java.lang.String r8 = com.amazon.mp3.library.data.FireFlyServiceUtil.TAG
            java.lang.String r0 = "Failed to retrieve track asins from FireFly: "
            java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r7)
            com.amazon.mp3.util.Log.error(r8, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.data.FireFlyServiceUtil.getTrackAndAlbumAsins(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
