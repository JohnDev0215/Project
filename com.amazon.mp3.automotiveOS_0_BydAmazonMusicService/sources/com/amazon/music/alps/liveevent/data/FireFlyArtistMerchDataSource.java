package com.amazon.music.alps.liveevent.data;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import io.reactivex.rxjava3.core.Observable;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.rx3.RxObservableKt;

/* JADX INFO: compiled from: FireFlyArtistMerchDataSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/FireFlyArtistMerchDataSource;", "Lcom/amazon/music/alps/liveevent/data/ArtistMerchDataSource;", "fireFlyService", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "(Lcom/amazon/music/platform/providers/FireFlyServiceProvider;)V", ParserUtil.TAG_QUERY_PARAM_NAME, "", "kotlin.jvm.PlatformType", "getArtist", "Lio/reactivex/rxjava3/core/Observable;", "Ljava/util/Optional;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artistId", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FireFlyArtistMerchDataSource implements ArtistMerchDataSource {
    private final FireFlyServiceProvider fireFlyService;
    private final String tag;

    public FireFlyArtistMerchDataSource() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public FireFlyArtistMerchDataSource(FireFlyServiceProvider fireFlyServiceProvider) {
        this.fireFlyService = fireFlyServiceProvider;
        this.tag = FireFlyArtistMerchDataSource.class.getSimpleName();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FireFlyArtistMerchDataSource(FireFlyServiceProvider fireFlyServiceProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            fireFlyServiceProvider = (FireFlyServiceProvider) (interfaceProvider instanceof FireFlyServiceProvider ? interfaceProvider : null);
        }
        this(fireFlyServiceProvider);
    }

    /* JADX INFO: renamed from: com.amazon.music.alps.liveevent.data.FireFlyArtistMerchDataSource$getArtist$1, reason: invalid class name */
    /* JADX INFO: compiled from: FireFlyArtistMerchDataSource.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "Ljava/util/Optional;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.alps.liveevent.data.FireFlyArtistMerchDataSource$getArtist$1", f = "FireFlyArtistMerchDataSource.kt", i = {1}, l = {20, 23, 27, 30, 34, 37}, m = "invokeSuspend", n = {"$this$rxObservable"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super Optional<ArtistMerchQuery.Artist>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $artistId;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ FireFlyArtistMerchDataSource this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, FireFlyArtistMerchDataSource fireFlyArtistMerchDataSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$artistId = str;
            this.this$0 = fireFlyArtistMerchDataSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$artistId, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super Optional<ArtistMerchQuery.Artist>> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ba  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 314
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.alps.liveevent.data.FireFlyArtistMerchDataSource.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.music.alps.liveevent.data.ArtistMerchDataSource
    public Observable<Optional<ArtistMerchQuery.Artist>> getArtist(String artistId) {
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        return RxObservableKt.rxObservable$default(null, new AnonymousClass1(artistId, this, null), 1, null);
    }
}
