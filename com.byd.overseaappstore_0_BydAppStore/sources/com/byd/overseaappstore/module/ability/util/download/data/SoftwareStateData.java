package com.byd.overseaappstore.module.ability.util.download.data;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "", "percentCount", "", "state", "(II)V", "getPercentCount", "()I", "getState", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareStateData {
    private final int percentCount;
    private final int state;

    public SoftwareStateData(int i, int i2) {
        this.percentCount = i;
        this.state = i2;
    }

    public final int getPercentCount() {
        return this.percentCount;
    }

    public final int getState() {
        return this.state;
    }
}
