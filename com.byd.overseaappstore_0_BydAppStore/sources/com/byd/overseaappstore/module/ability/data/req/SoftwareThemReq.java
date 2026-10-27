package com.byd.overseaappstore.module.ability.data.req;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/SoftwareThemReq;", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "pageSize", "(II)V", "getOffset", "()I", "getPageSize", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareThemReq {
    private final int offset;
    private final int pageSize;

    /* JADX WARN: Illegal instructions before constructor call */
    public SoftwareThemReq() {
        int i = 0;
        this(i, i, 3, null);
    }

    public SoftwareThemReq(int i, int i2) {
        this.offset = i;
        this.pageSize = i2;
    }

    public /* synthetic */ SoftwareThemReq(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 20 : i2);
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getPageSize() {
        return this.pageSize;
    }
}
