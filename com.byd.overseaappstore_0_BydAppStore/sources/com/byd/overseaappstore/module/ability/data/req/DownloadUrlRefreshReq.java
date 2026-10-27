package com.byd.overseaappstore.module.ability.data.req;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlRefreshReq;", "", "list", "", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlInfo;", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class DownloadUrlRefreshReq {

    @Nullable
    private final List<DownloadUrlInfo> list;

    /* JADX WARN: Multi-variable type inference failed */
    public DownloadUrlRefreshReq(@Nullable List<? extends DownloadUrlInfo> list) {
        this.list = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DownloadUrlRefreshReq copy$default(DownloadUrlRefreshReq downloadUrlRefreshReq, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = downloadUrlRefreshReq.list;
        }
        return downloadUrlRefreshReq.copy(list);
    }

    @Nullable
    public final List<DownloadUrlInfo> component1() {
        return this.list;
    }

    @NotNull
    public final DownloadUrlRefreshReq copy(@Nullable List<? extends DownloadUrlInfo> list) {
        return new DownloadUrlRefreshReq(list);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DownloadUrlRefreshReq) && Intrinsics.areEqual(this.list, ((DownloadUrlRefreshReq) other).list);
    }

    @Nullable
    public final List<DownloadUrlInfo> getList() {
        return this.list;
    }

    public int hashCode() {
        List<DownloadUrlInfo> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    @NotNull
    public String toString() {
        return "DownloadUrlRefreshReq(list=" + this.list + ")";
    }
}
