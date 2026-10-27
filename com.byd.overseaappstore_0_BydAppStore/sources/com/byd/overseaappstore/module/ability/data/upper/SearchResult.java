package com.byd.overseaappstore.module.ability.data.upper;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.hi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J/\u0010\u0015\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/SearchResult;", "", "list", "", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "size", "", "sizeTip", "", "(Ljava/util/List;ILjava/lang/String;)V", "getList", "()Ljava/util/List;", "getSize", "()I", "getSizeTip", "()Ljava/lang/String;", "setSizeTip", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchResult {

    @Nullable
    private final List<Software> list;
    private final int size;

    @NotNull
    private String sizeTip;

    /* JADX WARN: Multi-variable type inference failed */
    public SearchResult(@Nullable List<? extends Software> list, int i, @NotNull String sizeTip) {
        Intrinsics.checkNotNullParameter(sizeTip, "sizeTip");
        this.list = list;
        this.size = i;
        this.sizeTip = sizeTip;
    }

    public /* synthetic */ SearchResult(List list, int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : list, i, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchResult copy$default(SearchResult searchResult, List list, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = searchResult.list;
        }
        if ((i2 & 2) != 0) {
            i = searchResult.size;
        }
        if ((i2 & 4) != 0) {
            str = searchResult.sizeTip;
        }
        return searchResult.copy(list, i, str);
    }

    @Nullable
    public final List<Software> component1() {
        return this.list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSizeTip() {
        return this.sizeTip;
    }

    @NotNull
    public final SearchResult copy(@Nullable List<? extends Software> list, int size, @NotNull String sizeTip) {
        Intrinsics.checkNotNullParameter(sizeTip, "sizeTip");
        return new SearchResult(list, size, sizeTip);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchResult)) {
            return false;
        }
        SearchResult searchResult = (SearchResult) other;
        return Intrinsics.areEqual(this.list, searchResult.list) && this.size == searchResult.size && Intrinsics.areEqual(this.sizeTip, searchResult.sizeTip);
    }

    @Nullable
    public final List<Software> getList() {
        return this.list;
    }

    public final int getSize() {
        return this.size;
    }

    @NotNull
    public final String getSizeTip() {
        return this.sizeTip;
    }

    public int hashCode() {
        List<Software> list = this.list;
        return this.sizeTip.hashCode() + ((Integer.hashCode(this.size) + ((list == null ? 0 : list.hashCode()) * 31)) * 31);
    }

    public final void setSizeTip(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sizeTip = str;
    }

    @NotNull
    public String toString() {
        List<Software> list = this.list;
        int i = this.size;
        String str = this.sizeTip;
        StringBuilder sb = new StringBuilder();
        sb.append("SearchResult(list=");
        sb.append(list);
        sb.append(", size=");
        sb.append(i);
        sb.append(", sizeTip=");
        return hi.f(sb, str, ")");
    }
}
