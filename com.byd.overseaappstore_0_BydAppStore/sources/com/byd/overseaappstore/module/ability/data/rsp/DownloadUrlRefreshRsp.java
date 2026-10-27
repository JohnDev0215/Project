package com.byd.overseaappstore.module.ability.data.rsp;

import com.byd.overseaappstore.module.ability.data.req.DownloadUrlInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/DownloadUrlRefreshRsp;", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlInfo;", "packageName", "", "downloadUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadUrlRefreshRsp extends DownloadUrlInfo {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadUrlRefreshRsp(@NotNull String packageName, @NotNull String downloadUrl) {
        super(packageName, downloadUrl);
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
    }
}
