package com.byd.overseaappstore.module.ability.data.generator;

import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataInterceptorFactory;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;", "", "softwareDataInterceptorFactory", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataInterceptorFactory;", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataInterceptorFactory;)V", "softwareStateDataComprehensiveParse", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "remoteSoftware", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "deviceInstalledList", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareStateDataParserGenerator {

    @NotNull
    private final SoftwareDataInterceptorFactory softwareDataInterceptorFactory;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.SoftwareStateDataParserGenerator$softwareStateDataComprehensiveParse$1, reason: invalid class name */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.SoftwareStateDataParserGenerator$softwareStateDataComprehensiveParse$1", f = "SoftwareStateDataParserGenerator.kt", i = {}, l = {26}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SoftwareInterceptorRetData>, Object> {
        public final /* synthetic */ List<SoftwareDeviceInstalledRsp> $deviceInstalledList;
        public final /* synthetic */ SoftwareIntermediaryRsp $remoteSoftware;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SoftwareIntermediaryRsp softwareIntermediaryRsp, List<SoftwareDeviceInstalledRsp> list, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$remoteSoftware = softwareIntermediaryRsp;
            this.$deviceInstalledList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareStateDataParserGenerator.this.new AnonymousClass1(this.$remoteSoftware, this.$deviceInstalledList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo6invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super SoftwareInterceptorRetData> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SoftwareDataInterceptorFactory softwareDataInterceptorFactory = SoftwareStateDataParserGenerator.this.softwareDataInterceptorFactory;
                SoftwareIntermediaryRsp softwareIntermediaryRsp = this.$remoteSoftware;
                List<SoftwareDeviceInstalledRsp> list = this.$deviceInstalledList;
                this.label = 1;
                obj = SoftwareStateInterceptor.handle$default(softwareDataInterceptorFactory, softwareIntermediaryRsp, list, (byte) 0, this, 4, null);
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

    public SoftwareStateDataParserGenerator(@NotNull SoftwareDataInterceptorFactory softwareDataInterceptorFactory) {
        Intrinsics.checkNotNullParameter(softwareDataInterceptorFactory, "softwareDataInterceptorFactory");
        this.softwareDataInterceptorFactory = softwareDataInterceptorFactory;
    }

    @NotNull
    public final SoftwareInterceptorRetData softwareStateDataComprehensiveParse(@NotNull SoftwareIntermediaryRsp remoteSoftware, @Nullable List<SoftwareDeviceInstalledRsp> deviceInstalledList) {
        Intrinsics.checkNotNullParameter(remoteSoftware, "remoteSoftware");
        return (SoftwareInterceptorRetData) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(remoteSoftware, deviceInstalledList, null), 1, null);
    }
}
