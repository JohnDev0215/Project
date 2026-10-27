package com.amazon.mp3.customerprofile;

import com.amazon.music.platform.experiences.socialprofile.SocialProfileService;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CustomerProfileDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.customerprofile.CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1", f = "CustomerProfileDataProvider.kt", i = {0, 1}, l = {64, 70}, m = "invokeSuspend", n = {"isFollowing", "isFollowing"}, s = {"I$0", "I$0"})
final class CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isSelfView;
    final /* synthetic */ Ref.ObjectRef<List<BaseViewModel>> $models;
    final /* synthetic */ int $pageSize;
    final /* synthetic */ SocialProfileService $socialProfileService;
    int I$0;
    int label;
    final /* synthetic */ CustomerProfileDataProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1(boolean z, SocialProfileService socialProfileService, int i, CustomerProfileDataProvider customerProfileDataProvider, Ref.ObjectRef<List<BaseViewModel>> objectRef, Continuation<? super CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1> continuation) {
        super(2, continuation);
        this.$isSelfView = z;
        this.$socialProfileService = socialProfileService;
        this.$pageSize = i;
        this.this$0 = customerProfileDataProvider;
        this.$models = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1(this.$isSelfView, this.$socialProfileService, this.$pageSize, this.this$0, this.$models, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006e A[PHI: r0
  0x006e: PHI (r0v11 ??) = (r0v3 ??), (r0v3 ??), (r0v12 ??), (r0v12 ??), (r0v14 ??) binds: [B:17:0x0045, B:22:0x0057, B:34:0x0092, B:36:0x009a, B:40:0x00ac] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r15v17, types: [T, java.util.List] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.customerprofile.CustomerProfileDataProvider$fetchViewModelItems$single$1$1$request$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
