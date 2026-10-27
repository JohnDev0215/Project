package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;", "", "remoteSoftware", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "softwareDeviceInstalledRspList", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "poolSoftwareStateData", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "findSoftwareDeviceInstalledRsp", "(Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;Ljava/util/List;Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;)V", "getFindSoftwareDeviceInstalledRsp", "()Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "setFindSoftwareDeviceInstalledRsp", "(Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;)V", "getPoolSoftwareStateData", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "setPoolSoftwareStateData", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;)V", "getRemoteSoftware", "()Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "setRemoteSoftware", "(Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;)V", "getSoftwareDeviceInstalledRspList", "()Ljava/util/List;", "setSoftwareDeviceInstalledRspList", "(Ljava/util/List;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareDataWrapper {

    @Nullable
    private SoftwareDeviceInstalledRsp findSoftwareDeviceInstalledRsp;

    @Nullable
    private SoftwareStateData poolSoftwareStateData;

    @NotNull
    private SoftwareIntermediaryRsp remoteSoftware;

    @Nullable
    private List<SoftwareDeviceInstalledRsp> softwareDeviceInstalledRspList;

    public SoftwareDataWrapper(@NotNull SoftwareIntermediaryRsp remoteSoftware, @Nullable List<SoftwareDeviceInstalledRsp> list, @Nullable SoftwareStateData softwareStateData, @Nullable SoftwareDeviceInstalledRsp softwareDeviceInstalledRsp) {
        Intrinsics.checkNotNullParameter(remoteSoftware, "remoteSoftware");
        this.remoteSoftware = remoteSoftware;
        this.softwareDeviceInstalledRspList = list;
        this.poolSoftwareStateData = softwareStateData;
        this.findSoftwareDeviceInstalledRsp = softwareDeviceInstalledRsp;
    }

    public /* synthetic */ SoftwareDataWrapper(SoftwareIntermediaryRsp softwareIntermediaryRsp, List list, SoftwareStateData softwareStateData, SoftwareDeviceInstalledRsp softwareDeviceInstalledRsp, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(softwareIntermediaryRsp, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : softwareStateData, (i & 8) != 0 ? null : softwareDeviceInstalledRsp);
    }

    @Nullable
    public final SoftwareDeviceInstalledRsp getFindSoftwareDeviceInstalledRsp() {
        return this.findSoftwareDeviceInstalledRsp;
    }

    @Nullable
    public final SoftwareStateData getPoolSoftwareStateData() {
        return this.poolSoftwareStateData;
    }

    @NotNull
    public final SoftwareIntermediaryRsp getRemoteSoftware() {
        return this.remoteSoftware;
    }

    @Nullable
    public final List<SoftwareDeviceInstalledRsp> getSoftwareDeviceInstalledRspList() {
        return this.softwareDeviceInstalledRspList;
    }

    public final void setFindSoftwareDeviceInstalledRsp(@Nullable SoftwareDeviceInstalledRsp softwareDeviceInstalledRsp) {
        this.findSoftwareDeviceInstalledRsp = softwareDeviceInstalledRsp;
    }

    public final void setPoolSoftwareStateData(@Nullable SoftwareStateData softwareStateData) {
        this.poolSoftwareStateData = softwareStateData;
    }

    public final void setRemoteSoftware(@NotNull SoftwareIntermediaryRsp softwareIntermediaryRsp) {
        Intrinsics.checkNotNullParameter(softwareIntermediaryRsp, "<set-?>");
        this.remoteSoftware = softwareIntermediaryRsp;
    }

    public final void setSoftwareDeviceInstalledRspList(@Nullable List<SoftwareDeviceInstalledRsp> list) {
        this.softwareDeviceInstalledRspList = list;
    }
}
