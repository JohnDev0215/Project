package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicGroupRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$refreshAll$1", f = "FeatureViewModel.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9}, l = {148, 150, 153, 158, 163, 164, 165, 168, 169, 170}, m = "invokeSuspend", n = {"asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "bannerUiState", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "bannerUiState", "featureThemListUiState", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "bannerUiState", "featureThemListUiState", "featureGroupListUiState"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
public final class rb extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public SoftwareUiStateGenerator g;
    public SourceDataBody h;
    public SourceDataBody i;
    public int j;
    public /* synthetic */ Object k;
    public final /* synthetic */ FeatureViewModel l;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$refreshAll$1$asyncBannerSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {138, 138}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>>, Object> {
        public int a;
        public final /* synthetic */ FeatureViewModel b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(FeatureViewModel featureViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = featureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                e1 e1Var = this.b.d;
                this.a = 1;
                obj = e1Var.b();
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.a = 2;
            obj = FlowKt.first((Flow) obj, this);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$refreshAll$1$asyncFeatureGroupListSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {146, 146}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends FeatureTopicGroupRsp>>>>, Object> {
        public int a;
        public final /* synthetic */ FeatureViewModel b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(FeatureViewModel featureViewModel, Continuation<? super b> continuation) {
            super(2, continuation);
            this.b = featureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends FeatureTopicGroupRsp>>>> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SoftwareRepository softwareRepository = this.b.a;
                this.a = 1;
                obj = softwareRepository.e();
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.a = 2;
            obj = FlowKt.first((Flow) obj, this);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$refreshAll$1$asyncFeatureThemListSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {142, 142}, m = "invokeSuspend", n = {}, s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareThemRsp>>>>, Object> {
        public int a;
        public final /* synthetic */ FeatureViewModel b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(FeatureViewModel featureViewModel, Continuation<? super c> continuation) {
            super(2, continuation);
            this.b = featureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareThemRsp>>>> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SoftwareRepository softwareRepository = this.b.a;
                this.a = 1;
                obj = SoftwareRepository.d(softwareRepository);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.a = 2;
            obj = FlowKt.first((Flow) obj, this);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rb(FeatureViewModel featureViewModel, Continuation<? super rb> continuation) {
        super(2, continuation);
        this.l = featureViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        rb rbVar = new rb(this.l, continuation);
        rbVar.k = obj;
        return rbVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((rb) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x01f8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x021e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0248 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0276 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x02a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02cb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0301 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x031e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0343 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0387  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 940
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.rb.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
