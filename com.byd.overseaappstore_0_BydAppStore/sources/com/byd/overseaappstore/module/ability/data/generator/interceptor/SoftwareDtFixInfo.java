package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDtFixInfo;", "", "downloadUrl", "", "totalSize", "", "versionCode", "", "(Ljava/lang/String;JI)V", "getDownloadUrl", "()Ljava/lang/String;", "getTotalSize", "()J", "getVersionCode", "()I", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SoftwareDtFixInfo {

    @NotNull
    private final String downloadUrl;
    private final long totalSize;
    private final int versionCode;

    public SoftwareDtFixInfo() {
        this(null, 0L, 0, 7, null);
    }

    public SoftwareDtFixInfo(@NotNull String downloadUrl, long j, int i) {
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        this.downloadUrl = downloadUrl;
        this.totalSize = j;
        this.versionCode = i;
    }

    public /* synthetic */ SoftwareDtFixInfo(String str, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 0 : i);
    }

    @NotNull
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    public final long getTotalSize() {
        return this.totalSize;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }
}
