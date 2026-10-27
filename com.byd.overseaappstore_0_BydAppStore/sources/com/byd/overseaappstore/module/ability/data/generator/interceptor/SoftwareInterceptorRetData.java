package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "", "softwareStateData", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "dtFixInfo", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;)V", "getDtFixInfo", "()Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "getSoftwareStateData", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareInterceptorRetData {

    @NotNull
    private final SoftwareDtFixInfo dtFixInfo;

    @NotNull
    private final SoftwareStateData softwareStateData;

    public SoftwareInterceptorRetData(@NotNull SoftwareStateData softwareStateData, @NotNull SoftwareDtFixInfo dtFixInfo) {
        Intrinsics.checkNotNullParameter(softwareStateData, "softwareStateData");
        Intrinsics.checkNotNullParameter(dtFixInfo, "dtFixInfo");
        this.softwareStateData = softwareStateData;
        this.dtFixInfo = dtFixInfo;
    }

    @NotNull
    public final SoftwareDtFixInfo getDtFixInfo() {
        return this.dtFixInfo;
    }

    @NotNull
    public final SoftwareStateData getSoftwareStateData() {
        return this.softwareStateData;
    }
}
