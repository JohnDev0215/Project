package com.byd.overseaappstore.module.ability.data.req;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/SearchSoftwareReq;", "", "keyword", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "pageSize", "(Ljava/lang/String;II)V", "getKeyword", "()Ljava/lang/String;", "getOffset", "()I", "getPageSize", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchSoftwareReq {

    @NotNull
    private final String keyword;
    private final int offset;
    private final int pageSize;

    public SearchSoftwareReq(@NotNull String keyword, int i, int i2) {
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        this.keyword = keyword;
        this.offset = i;
        this.pageSize = i2;
    }

    public /* synthetic */ SearchSoftwareReq(String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 20 : i2);
    }

    @NotNull
    public final String getKeyword() {
        return this.keyword;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getPageSize() {
        return this.pageSize;
    }
}
