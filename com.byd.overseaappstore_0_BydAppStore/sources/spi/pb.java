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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$fetchFeatureData$1", f = "FeatureViewModel.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6}, l = {204, 205, 207, 209, 214, 215, 216}, m = "invokeSuspend", n = {"asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "bannerUiState", "asyncBannerSourceBodyResult", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "deviceInstalledList", "bannerUiState", "featureThemListUiState", "asyncFeatureThemListSourceBodyResult", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "asyncFeatureGroupListSourceBodyResult", "bannerUiState", "featureThemListUiState", "featureGroupListUiState", "bannerUiState", "featureThemListUiState", "featureGroupListUiState"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
public final class pb extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public SoftwareUiStateGenerator f;
    public int g;
    public /* synthetic */ Object h;
    public final /* synthetic */ FeatureViewModel i;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$fetchFeatureData$1$asyncBannerSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {196, 196}, m = "invokeSuspend", n = {}, s = {})
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$fetchFeatureData$1$asyncFeatureGroupListSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {202, 202}, m = "invokeSuspend", n = {}, s = {})
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.FeatureViewModel$fetchFeatureData$1$asyncFeatureThemListSourceBodyResult$1", f = "FeatureViewModel.kt", i = {}, l = {199, 199}, m = "invokeSuspend", n = {}, s = {})
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
    public pb(FeatureViewModel featureViewModel, Continuation<? super pb> continuation) {
        super(2, continuation);
        this.i = featureViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        pb pbVar = new pb(this.i, continuation);
        pbVar.h = obj;
        return pbVar;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((pb) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0181 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0209 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x022d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x024a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x024b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.pb.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
