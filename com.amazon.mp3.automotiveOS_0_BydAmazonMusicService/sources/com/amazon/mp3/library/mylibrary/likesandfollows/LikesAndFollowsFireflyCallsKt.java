package com.amazon.mp3.library.mylibrary.likesandfollows;

import android.content.Context;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.graphql.generated.FollowedArtistsQuery;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.apollographql.apollo3.api.Optional;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017\u001a-\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u001b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0010\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a\u0018\u0010\"\u001a\u00020\r2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$H\u0002\u001a\u001b\u0010&\u001a\u0004\u0018\u00010 2\u0006\u0010\u0010\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a.\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0016\b\u0002\u0010,\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\r\u0018\u00010-\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"CACHE_CONTROL_HEADER_KEY", "", "CACHE_CONTROL_HEADER_NO_CACHE_VALUE", "TAG", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "fireFlyServiceProvider", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "getFireFlyServiceProvider", "()Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "fireFlyServiceProvider$delegate", "Lkotlin/Lazy;", "emitUiClickMetric", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "asin", "viewId", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "fetchAllFollowedArtists", "Lkotlinx/coroutines/Job;", "context", "Landroid/content/Context;", "fetchFollowedArtistsPaginated", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;", "pageSize", "", "cursorOptional", "Lcom/apollographql/apollo3/api/Optional;", "(DLcom/apollographql/apollo3/api/Optional;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "followArtistFireFlyMutation", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseApolloResponseAndStoreInLocalDb", "unparsedArtistsEdges", "", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Edge;", "unfollowArtistFireFlyMutation", "updateFireflyArtistFollowStatus", "metadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "isFollowing", "", "followStateCallback", "Lkotlin/Function1;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LikesAndFollowsFireflyCallsKt {
    private static final String CACHE_CONTROL_HEADER_KEY = "Cache-Control";
    private static final String CACHE_CONTROL_HEADER_NO_CACHE_VALUE = "no-cache";
    private static final String TAG = "LikesAndFollowsFireflyCalls";
    private static final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static final Lazy fireFlyServiceProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<FireFlyServiceProvider>() { // from class: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fireFlyServiceProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FireFlyServiceProvider invoke() {
            return (FireFlyServiceProvider) Providers.INSTANCE.get(FireFlyServiceProvider.class);
        }
    });

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchFollowedArtistsPaginated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt", f = "LikesAndFollowsFireflyCalls.kt", i = {}, l = {83}, m = "fetchFollowedArtistsPaginated", n = {}, s = {})
    static final class C03651 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03651(Continuation<? super C03651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LikesAndFollowsFireflyCallsKt.fetchFollowedArtistsPaginated(0.0d, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$followArtistFireFlyMutation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt", f = "LikesAndFollowsFireflyCalls.kt", i = {}, l = {174}, m = "followArtistFireFlyMutation", n = {}, s = {})
    static final class C03661 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03661(Continuation<? super C03661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LikesAndFollowsFireflyCallsKt.followArtistFireFlyMutation(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$unfollowArtistFireFlyMutation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt", f = "LikesAndFollowsFireflyCalls.kt", i = {}, l = {202}, m = "unfollowArtistFireFlyMutation", n = {}, s = {})
    static final class C03671 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03671(Continuation<? super C03671> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LikesAndFollowsFireflyCallsKt.unfollowArtistFireFlyMutation(null, this);
        }
    }

    private static final FireFlyServiceProvider getFireFlyServiceProvider() {
        return (FireFlyServiceProvider) fireFlyServiceProvider$delegate.getValue();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchAllFollowedArtists$1, reason: invalid class name */
    /* JADX INFO: compiled from: LikesAndFollowsFireflyCalls.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchAllFollowedArtists$1", f = "LikesAndFollowsFireflyCalls.kt", i = {0, 0}, l = {55}, m = "invokeSuspend", n = {"cursorOptional", "hasNextPage"}, s = {"L$0", "I$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int I$0;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Path cross not found for [B:20:0x005c, B:21:0x005e], limit reached: 36 */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0083  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x004a -> B:14:0x004d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                r2 = 0
                r3 = 0
                r4 = 1
                if (r1 == 0) goto L20
                if (r1 != r4) goto L18
                int r1 = r13.I$0
                java.lang.Object r5 = r13.L$0
                com.apollographql.apollo3.api.Optional$Present r5 = (com.apollographql.apollo3.api.Optional.Present) r5
                kotlin.ResultKt.throwOnFailure(r14)
                r11 = r13
                goto L4d
            L18:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L20:
                kotlin.ResultKt.throwOnFailure(r14)
                com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations r5 = com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations.INSTANCE
                android.content.Context r6 = r13.$context
                r7 = 0
                r8 = 0
                r9 = 6
                r10 = 0
                com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations.removeArtistsFromLocalDB$default(r5, r6, r7, r8, r9, r10)
                com.apollographql.apollo3.api.Optional$Present r14 = new com.apollographql.apollo3.api.Optional$Present
                r14.<init>(r2)
                r11 = r13
                r1 = r4
            L35:
                if (r1 == 0) goto L83
                r5 = 0
                r9 = 1
                r10 = 0
                r11.L$0 = r14
                r11.I$0 = r1
                r11.label = r4
                r7 = r14
                r8 = r11
                java.lang.Object r5 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.fetchFollowedArtistsPaginated$default(r5, r7, r8, r9, r10)
                if (r5 != r0) goto L4a
                return r0
            L4a:
                r12 = r5
                r5 = r14
                r14 = r12
            L4d:
                com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$FollowedArtists r14 = (com.amazon.music.platform.graphql.generated.FollowedArtistsQuery.FollowedArtists) r14
                if (r14 != 0) goto L53
                r6 = r2
                goto L57
            L53:
                java.util.List r6 = r14.edgesFilterNotNull()
            L57:
                com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.access$parseApolloResponseAndStoreInLocalDb(r6)
                if (r14 != 0) goto L5e
            L5c:
                r6 = r3
                goto L6c
            L5e:
                com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$PageInfo r6 = r14.getPageInfo()
                if (r6 != 0) goto L65
                goto L5c
            L65:
                boolean r6 = r6.getHasNextPage()
                if (r6 != r4) goto L5c
                r6 = r4
            L6c:
                if (r6 == 0) goto L80
                com.apollographql.apollo3.api.Optional$Present r5 = new com.apollographql.apollo3.api.Optional$Present
                com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$PageInfo r14 = r14.getPageInfo()
                if (r14 != 0) goto L78
                r14 = r2
                goto L7c
            L78:
                java.lang.String r14 = r14.getToken()
            L7c:
                r5.<init>(r14)
                goto L81
            L80:
                r1 = r3
            L81:
                r14 = r5
                goto L35
            L83:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Job fetchAllFollowedArtists(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new AnonymousClass1(context, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object fetchFollowedArtistsPaginated(double r12, com.apollographql.apollo3.api.Optional<java.lang.String> r14, kotlin.coroutines.Continuation<? super com.amazon.music.platform.graphql.generated.FollowedArtistsQuery.FollowedArtists> r15) {
        /*
            boolean r0 = r15 instanceof com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03651
            if (r0 == 0) goto L13
            r0 = r15
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchFollowedArtistsPaginated$1 r0 = (com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03651) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchFollowedArtistsPaginated$1 r0 = new com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$fetchFollowedArtistsPaginated$1
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r12 = r0.L$0
            com.amazon.music.platform.providers.FireFlyServiceProvider r12 = (com.amazon.music.platform.providers.FireFlyServiceProvider) r12
            kotlin.ResultKt.throwOnFailure(r15)
            goto L63
        L2e:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L36:
            kotlin.ResultKt.throwOnFailure(r15)
            com.amazon.music.platform.providers.FireFlyServiceProvider r15 = getFireFlyServiceProvider()
            if (r15 != 0) goto L40
            goto L97
        L40:
            com.amazon.music.platform.graphql.generated.FollowedArtistsQuery r2 = new com.amazon.music.platform.graphql.generated.FollowedArtistsQuery
            r9 = 0
            r10 = 4
            r11 = 0
            r5 = r2
            r6 = r12
            r8 = r14
            r5.<init>(r6, r8, r9, r10, r11)
            kotlin.Pair r12 = new kotlin.Pair
            java.lang.String r13 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.CACHE_CONTROL_HEADER_KEY
            java.lang.String r14 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.CACHE_CONTROL_HEADER_NO_CACHE_VALUE
            r12.<init>(r13, r14)
            java.util.Map r12 = kotlin.collections.MapsKt.mapOf(r12)
            r0.L$0 = r15
            r0.label = r3
            java.lang.Object r15 = r15.query(r2, r12, r0)
            if (r15 != r1) goto L63
            return r1
        L63:
            com.amazon.music.platform.Outcome r15 = (com.amazon.music.platform.Outcome) r15
            boolean r12 = r15 instanceof com.amazon.music.platform.Outcome.Success
            if (r12 == 0) goto L84
            com.amazon.music.platform.Outcome$Success r15 = (com.amazon.music.platform.Outcome.Success) r15
            java.lang.Object r12 = r15.getValue()
            com.apollographql.apollo3.api.ApolloResponse r12 = (com.apollographql.apollo3.api.ApolloResponse) r12
            D extends com.apollographql.apollo3.api.Operation$Data r12 = r12.data
            com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$Data r12 = (com.amazon.music.platform.graphql.generated.FollowedArtistsQuery.Data) r12
            if (r12 != 0) goto L78
            goto L83
        L78:
            com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$User r12 = r12.getUser()
            if (r12 != 0) goto L7f
            goto L83
        L7f:
            com.amazon.music.platform.graphql.generated.FollowedArtistsQuery$FollowedArtists r4 = r12.getFollowedArtists()
        L83:
            return r4
        L84:
            boolean r12 = r15 instanceof com.amazon.music.platform.Outcome.Failure
            if (r12 == 0) goto L97
            java.lang.String r12 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.TAG
            com.amazon.music.platform.Outcome$Failure r15 = (com.amazon.music.platform.Outcome.Failure) r15
            java.lang.Throwable r13 = r15.getCause()
            java.lang.String r13 = r13.getMessage()
            com.amazon.mp3.util.Log.error(r12, r13)
        L97:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.fetchFollowedArtistsPaginated(double, com.apollographql.apollo3.api.Optional, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object fetchFollowedArtistsPaginated$default(double d, Optional optional, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            d = 100.0d;
        }
        return fetchFollowedArtistsPaginated(d, optional, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void parseApolloResponseAndStoreInLocalDb(List<FollowedArtistsQuery.Edge> list) {
        ArtistEmbedded artistEmbedded;
        if (list == null) {
            return;
        }
        Iterator<FollowedArtistsQuery.Edge> it = list.iterator();
        while (it.hasNext()) {
            FollowedArtistsQuery.Node node = it.next().getNode();
            if (node != null && (artistEmbedded = node.getArtistEmbedded()) != null) {
                String id = artistEmbedded.getId();
                String name = artistEmbedded.getName();
                ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
                String id2 = artistEmbedded.getId();
                if (id2 == null) {
                    id2 = "";
                }
                LikesAndFollowsDBOperations.INSTANCE.updateArtistFollowStatusInLocalDB(true, new ArtistMetadata(null, contentPlaybackUtils.getArtistUri(id2), null, null, id, null, name, null, null, null, null, true, null, null, false, null, null, false, false, null, 1046445, null));
            }
        }
    }

    public static /* synthetic */ void updateFireflyArtistFollowStatus$default(ArtistMetadata artistMetadata, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        updateFireflyArtistFollowStatus(artistMetadata, z, function1);
    }

    public static final void updateFireflyArtistFollowStatus(ArtistMetadata metadata, boolean z, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        String asin = metadata.getAsin();
        if (asin == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new LikesAndFollowsFireflyCallsKt$updateFireflyArtistFollowStatus$1$1(z, asin, metadata, function1, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object followArtistFireFlyMutation(java.lang.String r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            boolean r0 = r6 instanceof com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03661
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$followArtistFireFlyMutation$1 r0 = (com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03661) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$followArtistFireFlyMutation$1 r0 = new com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$followArtistFireFlyMutation$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.providers.FireFlyServiceProvider r5 = (com.amazon.music.platform.providers.FireFlyServiceProvider) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L54
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.providers.FireFlyServiceProvider r6 = getFireFlyServiceProvider()
            if (r6 != 0) goto L3f
            goto L7f
        L3f:
            com.amazon.music.platform.graphql.generated.FollowArtistMutation r2 = new com.amazon.music.platform.graphql.generated.FollowArtistMutation
            com.apollographql.apollo3.api.Optional$Present r4 = new com.apollographql.apollo3.api.Optional$Present
            r4.<init>(r5)
            r2.<init>(r4)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r6 = r6.mutation(r2, r0)
            if (r6 != r1) goto L54
            return r1
        L54:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Success
            if (r5 == 0) goto L66
            java.lang.String r5 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.TAG
            java.lang.String r6 = "Firefly follow successful"
            com.amazon.mp3.util.Log.debug(r5, r6)
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            return r5
        L66:
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            if (r5 == 0) goto L7f
            java.lang.String r5 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.TAG
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r6 = r6.getCause()
            java.lang.String r6 = r6.getMessage()
            java.lang.String r0 = "Cannot update artist follow status "
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r6)
            com.amazon.mp3.util.Log.error(r5, r6)
        L7f:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.followArtistFireFlyMutation(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object unfollowArtistFireFlyMutation(java.lang.String r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            boolean r0 = r6 instanceof com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03671
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$unfollowArtistFireFlyMutation$1 r0 = (com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.C03671) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$unfollowArtistFireFlyMutation$1 r0 = new com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt$unfollowArtistFireFlyMutation$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.providers.FireFlyServiceProvider r5 = (com.amazon.music.platform.providers.FireFlyServiceProvider) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L54
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.providers.FireFlyServiceProvider r6 = getFireFlyServiceProvider()
            if (r6 != 0) goto L3f
            goto L80
        L3f:
            com.amazon.music.platform.graphql.generated.UnfollowArtistMutation r2 = new com.amazon.music.platform.graphql.generated.UnfollowArtistMutation
            com.apollographql.apollo3.api.Optional$Present r4 = new com.apollographql.apollo3.api.Optional$Present
            r4.<init>(r5)
            r2.<init>(r4)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r6 = r6.mutation(r2, r0)
            if (r6 != r1) goto L54
            return r1
        L54:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Success
            if (r5 == 0) goto L67
            java.lang.String r5 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.TAG
            java.lang.String r6 = "Firefly unfollow successful"
            com.amazon.mp3.util.Log.debug(r5, r6)
            r5 = 0
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        L67:
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            if (r5 == 0) goto L80
            java.lang.String r5 = com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.TAG
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r6 = r6.getCause()
            java.lang.String r6 = r6.getMessage()
            java.lang.String r0 = "Cannot update artist follow status "
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r6)
            com.amazon.mp3.util.Log.error(r5, r6)
        L80:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt.unfollowArtistFireFlyMutation(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void emitUiClickMetric(ActionType actionType, String str, String str2, EntityType entityType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEntityType(entityType).withEntityIdType(EntityIdType.ASIN).withEntityId(str).withEventTime(System.currentTimeMillis()).withBlockRef(str2).build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "builder(actionType)\n    …(viewId)\n        .build()");
        MetricsHolder.getManager().handleEvent(uiClickEventBuild);
    }
}
