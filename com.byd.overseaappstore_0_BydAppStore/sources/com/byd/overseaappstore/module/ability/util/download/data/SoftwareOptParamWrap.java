package com.byd.overseaappstore.module.ability.util.download.data;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;", "", "softwareOptParam", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "pair", "Lkotlin/Pair;", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;Lkotlin/Pair;)V", "getPair", "()Lkotlin/Pair;", "getSoftwareOptParam", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareOptParamWrap {

    @Nullable
    private final Pair<SoftwareStateData, SoftwareStateData> pair;

    @NotNull
    private final SoftwareOptParam softwareOptParam;

    public SoftwareOptParamWrap(@NotNull SoftwareOptParam softwareOptParam, @Nullable Pair<SoftwareStateData, SoftwareStateData> pair) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        this.softwareOptParam = softwareOptParam;
        this.pair = pair;
    }

    @Nullable
    public final Pair<SoftwareStateData, SoftwareStateData> getPair() {
        return this.pair;
    }

    @NotNull
    public final SoftwareOptParam getSoftwareOptParam() {
        return this.softwareOptParam;
    }
}
