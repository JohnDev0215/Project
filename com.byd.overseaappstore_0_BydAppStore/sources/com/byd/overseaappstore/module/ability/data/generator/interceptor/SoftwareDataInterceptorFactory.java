package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\b\u0007\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J1\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00022\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataInterceptorFactory;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "deviceDataSoftwareStateInterceptor", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DeviceDataSoftwareStateInterceptor;", "poolDataSoftwareStateInterceptor", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/PoolDataSoftwareStateInterceptor;", "dbDataSoftwareStateInterceptor", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DbDataSoftwareStateInterceptor;", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DeviceDataSoftwareStateInterceptor;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/PoolDataSoftwareStateInterceptor;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DbDataSoftwareStateInterceptor;)V", "generateDefaultRetData", "treeLogicRet", "", "handle", "remoteSoftware", "deviceInstalledList", "(Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;Ljava/util/List;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareDataInterceptorFactory extends SoftwareStateInterceptor<SoftwareIntermediaryRsp, SoftwareInterceptorRetData, List<? extends SoftwareDeviceInstalledRsp>> {

    @NotNull
    private final DeviceDataSoftwareStateInterceptor deviceDataSoftwareStateInterceptor;

    public SoftwareDataInterceptorFactory(@NotNull DeviceDataSoftwareStateInterceptor deviceDataSoftwareStateInterceptor, @NotNull PoolDataSoftwareStateInterceptor poolDataSoftwareStateInterceptor, @NotNull DbDataSoftwareStateInterceptor dbDataSoftwareStateInterceptor) {
        Intrinsics.checkNotNullParameter(deviceDataSoftwareStateInterceptor, "deviceDataSoftwareStateInterceptor");
        Intrinsics.checkNotNullParameter(poolDataSoftwareStateInterceptor, "poolDataSoftwareStateInterceptor");
        Intrinsics.checkNotNullParameter(dbDataSoftwareStateInterceptor, "dbDataSoftwareStateInterceptor");
        this.deviceDataSoftwareStateInterceptor = deviceDataSoftwareStateInterceptor;
        poolDataSoftwareStateInterceptor.setNext(dbDataSoftwareStateInterceptor);
        deviceDataSoftwareStateInterceptor.setNext(poolDataSoftwareStateInterceptor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    @NotNull
    public SoftwareInterceptorRetData generateDefaultRetData(byte treeLogicRet) {
        SoftwareInterceptorRetData softwareInterceptorRetData = new SoftwareInterceptorRetData(new SoftwareStateData(0, 0), new SoftwareDtFixInfo(null, 0L, 0, 7, null));
        ct.b("handleRemote generateDefaultRetData factory", new Object[0]);
        return softwareInterceptorRetData;
    }

    @Nullable
    /* JADX INFO: renamed from: handle, reason: avoid collision after fix types in other method */
    public Object handle2(@NotNull SoftwareIntermediaryRsp softwareIntermediaryRsp, @Nullable List<SoftwareDeviceInstalledRsp> list, byte b, @NotNull Continuation<? super SoftwareInterceptorRetData> continuation) {
        String packageName = softwareIntermediaryRsp.getPackageName();
        String signature = softwareIntermediaryRsp.getSignature();
        if (packageName.length() == 0) {
            ct.a("handleRemote remotePackageName is isNullOrEmpty deviceInstalledList.size:" + (list != null ? Boxing.boxInt(list.size()) : null), new Object[0]);
            return SoftwareStateInterceptor.generateDefaultRetData$default(this, (byte) 0, 1, null);
        }
        if (!(signature.length() == 0)) {
            return SoftwareStateInterceptor.handle$default(this.deviceDataSoftwareStateInterceptor, new SoftwareDataWrapper(softwareIntermediaryRsp, null, null, null, 14, null), list, (byte) 0, continuation, 4, null);
        }
        ct.a("handleRemote remoteSignature is isNullOrEmpty deviceInstalledList.size:" + (list != null ? Boxing.boxInt(list.size()) : null) + " pkn:" + packageName, new Object[0]);
        return SoftwareStateInterceptor.generateDefaultRetData$default(this, (byte) 0, 1, null);
    }

    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    public /* bridge */ /* synthetic */ Object handle(SoftwareIntermediaryRsp softwareIntermediaryRsp, List<? extends SoftwareDeviceInstalledRsp> list, byte b, Continuation<? super SoftwareInterceptorRetData> continuation) {
        return handle2(softwareIntermediaryRsp, (List<SoftwareDeviceInstalledRsp>) list, b, continuation);
    }
}
