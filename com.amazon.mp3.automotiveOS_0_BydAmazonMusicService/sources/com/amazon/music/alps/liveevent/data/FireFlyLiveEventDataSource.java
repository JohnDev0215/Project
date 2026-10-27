package com.amazon.music.alps.liveevent.data;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import io.reactivex.rxjava3.core.Observable;
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

/* JADX INFO: compiled from: FireFlyLiveEventDataSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/FireFlyLiveEventDataSource;", "Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;", "fireFlyService", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "(Lcom/amazon/music/platform/providers/FireFlyServiceProvider;)V", "getEvent", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", ContentUtils.KEY_EVENT_ID, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FireFlyLiveEventDataSource implements LiveEventDataSource {
    private final FireFlyServiceProvider fireFlyService;

    public FireFlyLiveEventDataSource() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public FireFlyLiveEventDataSource(FireFlyServiceProvider fireFlyServiceProvider) {
        this.fireFlyService = fireFlyServiceProvider;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FireFlyLiveEventDataSource(FireFlyServiceProvider fireFlyServiceProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            fireFlyServiceProvider = (FireFlyServiceProvider) (interfaceProvider instanceof FireFlyServiceProvider ? interfaceProvider : null);
        }
        this(fireFlyServiceProvider);
    }

    /* JADX INFO: renamed from: com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource$getEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: FireFlyLiveEventDataSource.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource$getEvent$1", f = "FireFlyLiveEventDataSource.kt", i = {0}, l = {20, 26}, m = "invokeSuspend", n = {"$this$rxObservable"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super LiveEvent>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $eventId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$eventId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = FireFlyLiveEventDataSource.this.new AnonymousClass1(this.$eventId, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super LiveEvent> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L23
                if (r1 == r3) goto L1b
                if (r1 != r2) goto L13
                kotlin.ResultKt.throwOnFailure(r8)
                goto L75
            L13:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1b:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L47
            L23:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                r1 = r8
                kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
                com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource r8 = com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource.this
                com.amazon.music.platform.providers.FireFlyServiceProvider r8 = com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource.access$getFireFlyService$p(r8)
                if (r8 != 0) goto L35
                r8 = r4
                goto L49
            L35:
                com.amazon.music.platform.graphql.generated.LiveEventQuery r5 = new com.amazon.music.platform.graphql.generated.LiveEventQuery
                java.lang.String r6 = r7.$eventId
                r5.<init>(r6)
                r7.L$0 = r1
                r7.label = r3
                java.lang.Object r8 = r8.query(r5, r7)
                if (r8 != r0) goto L47
                return r0
            L47:
                com.amazon.music.platform.Outcome r8 = (com.amazon.music.platform.Outcome) r8
            L49:
                boolean r3 = r8 instanceof com.amazon.music.platform.Outcome.Success
                if (r3 == 0) goto L80
                com.amazon.music.platform.Outcome$Success r8 = (com.amazon.music.platform.Outcome.Success) r8
                java.lang.Object r8 = r8.getValue()
                com.apollographql.apollo3.api.ApolloResponse r8 = (com.apollographql.apollo3.api.ApolloResponse) r8
                D extends com.apollographql.apollo3.api.Operation$Data r8 = r8.data
                com.amazon.music.platform.graphql.generated.LiveEventQuery$Data r8 = (com.amazon.music.platform.graphql.generated.LiveEventQuery.Data) r8
                if (r8 != 0) goto L5d
            L5b:
                r8 = r4
                goto L68
            L5d:
                com.amazon.music.platform.graphql.generated.LiveEventQuery$LiveEvent r8 = r8.getLiveEvent()
                if (r8 != 0) goto L64
                goto L5b
            L64:
                com.amazon.music.platform.graphql.generated.fragment.LiveEvent r8 = r8.getLiveEvent()
            L68:
                if (r8 == 0) goto L78
                r7.L$0 = r4
                r7.label = r2
                java.lang.Object r8 = r1.send(r8, r7)
                if (r8 != r0) goto L75
                return r0
            L75:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L78:
                java.lang.Throwable r8 = new java.lang.Throwable
                java.lang.String r0 = "getEvent is empty"
                r8.<init>(r0)
                throw r8
            L80:
                boolean r0 = r8 instanceof com.amazon.music.platform.Outcome.Failure
                if (r0 == 0) goto L8b
                com.amazon.music.platform.Outcome$Failure r8 = (com.amazon.music.platform.Outcome.Failure) r8
                java.lang.Throwable r8 = r8.getCause()
                throw r8
            L8b:
                java.lang.Throwable r8 = new java.lang.Throwable
                java.lang.String r0 = "getEvent failed"
                r8.<init>(r0)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.music.alps.liveevent.data.LiveEventDataSource
    public Observable<LiveEvent> getEvent(String eventId) {
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        return RxObservableKt.rxObservable$default(null, new AnonymousClass1(eventId, null), 1, null);
    }
}
