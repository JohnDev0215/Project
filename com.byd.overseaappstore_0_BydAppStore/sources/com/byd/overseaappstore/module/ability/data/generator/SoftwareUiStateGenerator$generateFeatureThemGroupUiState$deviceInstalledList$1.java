package com.byd.overseaappstore.module.ability.data.generator;

import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import java.util.List;
import kotlin.Metadata;
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
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1", f = "SoftwareUiStateGenerator.kt", i = {}, l = {271}, m = "invokeSuspend", n = {}, s = {})
public final class SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SoftwareDeviceInstalledRsp>>, Object> {
    public int label;
    public final /* synthetic */ SoftwareUiStateGenerator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1(SoftwareUiStateGenerator softwareUiStateGenerator, Continuation<? super SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1> continuation) {
        super(2, continuation);
        this.this$0 = softwareUiStateGenerator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SoftwareDeviceInstalledRsp>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<SoftwareDeviceInstalledRsp>>) continuation);
    }

    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<SoftwareDeviceInstalledRsp>> continuation) {
        return ((SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<SourceDataBody<List<SoftwareDeviceInstalledRsp>>> flowF = this.this$0.softwareRepository.f();
            this.label = 1;
            obj = FlowKt.first(flowF, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return ((SourceDataBody) obj).getApiData();
    }
}
