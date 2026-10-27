package com.amazon.music.platform.experiences.playlists;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.LoggingProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistFireFlyService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J#\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\b\u001a\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/platform/experiences/playlists/PlaylistFireFlyService;", "", "", "title", "", "logSuccess", "errorMsg", "logFailure", "id", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/experiences/playlists/PlaylistFollowerCount;", "getFollowerCount", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "fireflyProvider", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "getFireflyProvider", "()Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "Lcom/amazon/music/platform/providers/LoggingProvider;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "<init>", "(Lcom/amazon/music/platform/providers/FireFlyServiceProvider;Lcom/amazon/music/platform/providers/LoggingProvider;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class PlaylistFireFlyService {
    private static final Map<String, String> FEATURE_FLAGS_HEADERS;
    private static final Map<String, String> NO_CACHE_HEADERS;
    private static final String TAG;
    private final FireFlyServiceProvider fireflyProvider;
    private final LoggingProvider loggingProvider;

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService$getFollowerCount$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistFireFlyService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService", f = "PlaylistFireFlyService.kt", i = {0}, l = {47}, m = "getFollowerCount", n = {"this"}, s = {"L$0"})
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
            return PlaylistFireFlyService.this.getFollowerCount(null, this);
        }
    }

    public PlaylistFireFlyService() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public PlaylistFireFlyService(FireFlyServiceProvider fireFlyServiceProvider, LoggingProvider loggingProvider) {
        this.fireflyProvider = fireFlyServiceProvider;
        this.loggingProvider = loggingProvider;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ PlaylistFireFlyService(FireFlyServiceProvider fireFlyServiceProvider, LoggingProvider loggingProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (FireFlyServiceProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class)) : fireFlyServiceProvider, (i & 2) != 0 ? (LoggingProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(LoggingProvider.class)) : loggingProvider);
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        String simpleName = Reflection.getOrCreateKotlinClass(companion.getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = "PlaylistsService";
        }
        TAG = simpleName;
        FEATURE_FLAGS_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("x-amzn-feature-flags", "use-mups"));
        NO_CACHE_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("cache-control", "no-cache"));
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getFollowerCount(java.lang.String r6, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService$getFollowerCount$1 r0 = (com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService$getFollowerCount$1 r0 = new com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService$getFollowerCount$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r6 = r0.L$0
            com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService r6 = (com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5b
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery r7 = new com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery
            com.apollographql.apollo3.api.Optional$Present r2 = new com.apollographql.apollo3.api.Optional$Present
            r2.<init>(r6)
            r7.<init>(r2)
            java.util.Map<java.lang.String, java.lang.String> r6 = com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService.FEATURE_FLAGS_HEADERS
            java.util.Map<java.lang.String, java.lang.String> r2 = com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService.NO_CACHE_HEADERS
            java.util.Map r6 = kotlin.collections.MapsKt.plus(r6, r2)
            com.amazon.music.platform.providers.FireFlyServiceProvider r2 = r5.fireflyProvider
            if (r2 == 0) goto L5e
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = r2.query(r7, r6, r0)
            if (r7 != r1) goto L5a
            return r1
        L5a:
            r6 = r5
        L5b:
            com.amazon.music.platform.Outcome r7 = (com.amazon.music.platform.Outcome) r7
            goto L60
        L5e:
            r6 = r5
            r7 = r4
        L60:
            boolean r0 = r7 instanceof com.amazon.music.platform.Outcome.Success
            java.lang.String r1 = "PlaylistFollowerCountQuery"
            if (r0 == 0) goto L98
            r6.logSuccess(r1)
            com.amazon.music.platform.Outcome$Success r7 = (com.amazon.music.platform.Outcome.Success) r7
            java.lang.Object r6 = r7.getValue()
            com.apollographql.apollo3.api.ApolloResponse r6 = (com.apollographql.apollo3.api.ApolloResponse) r6
            if (r6 == 0) goto L7e
            D extends com.apollographql.apollo3.api.Operation$Data r6 = r6.data
            com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery$Data r6 = (com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery.Data) r6
            if (r6 == 0) goto L7e
            com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery$Playlist r6 = r6.getPlaylist()
            goto L7f
        L7e:
            r6 = r4
        L7f:
            com.amazon.music.platform.Outcome$Success r7 = new com.amazon.music.platform.Outcome$Success
            com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount r0 = new com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount
            if (r6 == 0) goto L8a
            java.lang.String r1 = r6.getId()
            goto L8b
        L8a:
            r1 = r4
        L8b:
            if (r6 == 0) goto L91
            java.lang.Double r4 = r6.getFollowerCount()
        L91:
            r0.<init>(r1, r4)
            r7.<init>(r0)
            return r7
        L98:
            boolean r0 = r7 instanceof com.amazon.music.platform.Outcome.Failure
            java.lang.String r2 = "Uknown Firefly Apollo Client Errror"
            if (r0 == 0) goto Lbc
            com.amazon.music.platform.Outcome$Failure r7 = (com.amazon.music.platform.Outcome.Failure) r7
            java.lang.Throwable r7 = r7.getCause()
            if (r7 == 0) goto Lae
            java.lang.String r7 = r7.getMessage()
            if (r7 != 0) goto Lad
            goto Lae
        Lad:
            r2 = r7
        Lae:
            r6.logFailure(r1, r2)
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r7 = new java.lang.Exception
            r7.<init>(r2)
            r6.<init>(r7)
            return r6
        Lbc:
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r7 = new java.lang.Exception
            r7.<init>(r2)
            r6.<init>(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService.getFollowerCount(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void logSuccess(String title) {
        LoggingProvider loggingProvider = this.loggingProvider;
        if (loggingProvider != null) {
            loggingProvider.logInfo(TAG, title + " : Success");
        }
    }

    private final void logFailure(String title, String errorMsg) {
        LoggingProvider loggingProvider = this.loggingProvider;
        if (loggingProvider != null) {
            loggingProvider.logInfo(TAG, title + " : " + errorMsg);
        }
    }
}
