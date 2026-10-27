package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J1\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DeviceDataSoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "()V", "generateDefaultRetData", "treeLogicRet", "", "handle", "softwareDataWrapper", "deviceInstalledList", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;Ljava/util/List;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceDataSoftwareStateInterceptor extends SoftwareStateInterceptor<SoftwareDataWrapper, SoftwareInterceptorRetData, List<? extends SoftwareDeviceInstalledRsp>> {

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor$handle$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor", f = "DeviceDataSoftwareStateInterceptor.kt", i = {0}, l = {26}, m = "handle", n = {"this"}, s = {"L$0"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DeviceDataSoftwareStateInterceptor.this.handle2((SoftwareDataWrapper) null, (List<SoftwareDeviceInstalledRsp>) null, (byte) 0, (Continuation<? super SoftwareInterceptorRetData>) this);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    @NotNull
    public SoftwareInterceptorRetData generateDefaultRetData(byte treeLogicRet) {
        SoftwareInterceptorRetData softwareInterceptorRetData = new SoftwareInterceptorRetData(new SoftwareStateData(0, 0), new SoftwareDtFixInfo(null, 0L, 0, 7, null));
        ct.b("handleRemote generateDefaultRetData", new Object[0]);
        return softwareInterceptorRetData;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: handle, reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object handle2(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r11, @org.jetbrains.annotations.Nullable java.util.List<com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp> r12, byte r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r14) {
        /*
            r10 = this;
            boolean r13 = r14 instanceof com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor.AnonymousClass1
            if (r13 == 0) goto L13
            r13 = r14
            com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor$handle$1 r13 = (com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor.AnonymousClass1) r13
            int r0 = r13.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r13.label = r0
            goto L18
        L13:
            com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor$handle$1 r13 = new com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor$handle$1
            r13.<init>(r14)
        L18:
            r4 = r13
            java.lang.Object r13 = r4.result
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.label
            r7 = 0
            r8 = 0
            r9 = 1
            if (r0 == 0) goto L38
            if (r0 != r9) goto L30
            java.lang.Object r11 = r4.L$0
            com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor r11 = (com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L8b
        L30:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L38:
            kotlin.ResultKt.throwOnFailure(r13)
            r11.setSoftwareDeviceInstalledRspList(r12)
            com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp r13 = r11.getRemoteSoftware()
            java.lang.String r13 = r13.getPackageName()
            java.util.List r0 = r11.getSoftwareDeviceInstalledRspList()
            if (r0 == 0) goto L55
            int r0 = r0.size()
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            goto L56
        L55:
            r0 = r8
        L56:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "handleRemote in DeviceDataSoftwareStateInterceptor pkn:"
            r1.append(r2)
            r1.append(r13)
            java.lang.String r13 = " deviceInstalledList.size:"
            r1.append(r13)
            r1.append(r0)
            java.lang.String r13 = r1.toString()
            java.lang.Object[] r0 = new java.lang.Object[r7]
            spi.ct.a(r13, r0)
            com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor r0 = r10.getNext()
            if (r0 == 0) goto L90
            r3 = 0
            r5 = 4
            r6 = 0
            r4.L$0 = r10
            r4.label = r9
            r1 = r11
            r2 = r12
            java.lang.Object r13 = com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor.handle$default(r0, r1, r2, r3, r4, r5, r6)
            if (r13 != r14) goto L8a
            return r14
        L8a:
            r11 = r10
        L8b:
            com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData r13 = (com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData) r13
            if (r13 != 0) goto L98
            goto L91
        L90:
            r11 = r10
        L91:
            java.lang.Object r11 = com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor.generateDefaultRetData$default(r11, r7, r9, r8)
            r13 = r11
            com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData r13 = (com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData) r13
        L98:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor.handle2(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, java.util.List, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    public /* bridge */ /* synthetic */ Object handle(SoftwareDataWrapper softwareDataWrapper, List<? extends SoftwareDeviceInstalledRsp> list, byte b, Continuation<? super SoftwareInterceptorRetData> continuation) {
        return handle2(softwareDataWrapper, (List<SoftwareDeviceInstalledRsp>) list, b, continuation);
    }
}
