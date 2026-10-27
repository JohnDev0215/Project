package com.amazon.mp3.customerprofile;

import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.platform.experiences.socialprofile.SocialProfileService;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.SingleEmitter;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: CustomerProfileDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.customerprofile.CustomerProfileDataProvider$fetchViewModelItems$single$1$1", f = "CustomerProfileDataProvider.kt", i = {0}, l = {92}, m = "invokeSuspend", n = {"models"}, s = {"L$0"})
final class CustomerProfileDataProvider$fetchViewModelItems$single$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SingleEmitter<PageGridViewModel> $emitter;
    final /* synthetic */ int $pageSize;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CustomerProfileDataProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CustomerProfileDataProvider$fetchViewModelItems$single$1$1(CustomerProfileDataProvider customerProfileDataProvider, SingleEmitter<PageGridViewModel> singleEmitter, int i, Continuation<? super CustomerProfileDataProvider$fetchViewModelItems$single$1$1> continuation) {
        super(2, continuation);
        this.this$0 = customerProfileDataProvider;
        this.$emitter = singleEmitter;
        this.$pageSize = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CustomerProfileDataProvider$fetchViewModelItems$single$1$1 customerProfileDataProvider$fetchViewModelItems$single$1$1 = new CustomerProfileDataProvider$fetchViewModelItems$single$1$1(this.this$0, this.$emitter, this.$pageSize, continuation);
        customerProfileDataProvider$fetchViewModelItems$single$1$1.L$0 = obj;
        return customerProfileDataProvider$fetchViewModelItems$single$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CustomerProfileDataProvider$fetchViewModelItems$single$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.util.List] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            PageLoadLatencyCode pageLoadLatencyCode = this.this$0.pageLoadLatencyCode;
            if (pageLoadLatencyCode != null) {
                LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
            }
            SocialProfileService socialProfileService = new SocialProfileService(null, null, 3, null);
            String str = this.this$0.profileId;
            boolean z = str == null || str.length() == 0;
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = CollectionsKt__CollectionsKt.emptyList();
            Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1(z, socialProfileService, this.$pageSize, this.this$0, objectRef2, null), 3, null);
            this.L$0 = objectRef2;
            this.label = 1;
            if (deferredAsync$default.await(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((List) objectRef.element).isEmpty()) {
            this.$emitter.onError(new Throwable("FailedToRetrieveProfile"));
        } else {
            this.$emitter.onSuccess(new PageGridViewModel(null, null, null, null, (List) objectRef.element, 2, 0, "", null, null, null, 1807, null));
        }
        return Unit.INSTANCE;
    }
}
