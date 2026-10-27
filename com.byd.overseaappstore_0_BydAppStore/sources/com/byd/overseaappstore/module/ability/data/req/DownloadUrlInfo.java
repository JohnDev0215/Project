package com.byd.overseaappstore.module.ability.data.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlInfo;", "", "packageName", "", "downloadUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getDownloadUrl", "()Ljava/lang/String;", "getPackageName", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DownloadUrlInfo {

    @NotNull
    private final String downloadUrl;

    @NotNull
    private final String packageName;

    public DownloadUrlInfo(@NotNull String packageName, @NotNull String downloadUrl) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        this.packageName = packageName;
        this.downloadUrl = downloadUrl;
    }

    @NotNull
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    @NotNull
    public final String getPackageName() {
        return this.packageName;
    }
}
