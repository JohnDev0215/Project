package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.oa;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J3\u0010\n\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0016R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/PoolDataSoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "softwareDataWrapper", "f", "", "treeLogicRet", "handle", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;Ljava/util/List;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateDefaultRetData", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lspi/oa;", "executorSoftwareStateMgr", "<init>", "(Landroid/content/Context;Lspi/oa;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class PoolDataSoftwareStateInterceptor extends SoftwareStateInterceptor<SoftwareDataWrapper, SoftwareInterceptorRetData, List<? extends SoftwareDeviceInstalledRsp>> {

    @NotNull
    private final Context context;

    @NotNull
    private final oa executorSoftwareStateMgr;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.PoolDataSoftwareStateInterceptor$handle$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.PoolDataSoftwareStateInterceptor", f = "PoolDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, l = {46, 53, 67, 73, 81, 90}, m = "handle", n = {"this", "remotePackageName", "treeLogicRet", "this", "remotePackageName", "this", "remotePackageName", "this", "remotePackageName", "this", "remotePackageName", "this", "remotePackageName"}, s = {"L$0", "L$1", "B$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        public byte B$0;
        public Object L$0;
        public Object L$1;
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
            return PoolDataSoftwareStateInterceptor.this.handle2((SoftwareDataWrapper) null, (List<SoftwareDeviceInstalledRsp>) null, (byte) 0, (Continuation<? super SoftwareInterceptorRetData>) this);
        }
    }

    public PoolDataSoftwareStateInterceptor(@NotNull Context context, @NotNull oa executorSoftwareStateMgr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        this.context = context;
        this.executorSoftwareStateMgr = executorSoftwareStateMgr;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    @NotNull
    public SoftwareInterceptorRetData generateDefaultRetData(byte treeLogicRet) {
        SoftwareInterceptorRetData softwareInterceptorRetData = new SoftwareInterceptorRetData(new SoftwareStateData(0, 0), new SoftwareDtFixInfo(null, 0L, 0, 7, null));
        ct.b("handleRemote generateDefaultRetData", new Object[0]);
        return softwareInterceptorRetData;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0253  */
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: handle, reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object handle2(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r19, @org.jetbrains.annotations.Nullable java.util.List<com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp> r20, byte r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r22) {
        /*
            Method dump skipped, instruction units count: 820
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.PoolDataSoftwareStateInterceptor.handle2(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, java.util.List, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    public /* bridge */ /* synthetic */ Object handle(SoftwareDataWrapper softwareDataWrapper, List<? extends SoftwareDeviceInstalledRsp> list, byte b, Continuation<? super SoftwareInterceptorRetData> continuation) {
        return handle2(softwareDataWrapper, (List<SoftwareDeviceInstalledRsp>) list, b, continuation);
    }
}
