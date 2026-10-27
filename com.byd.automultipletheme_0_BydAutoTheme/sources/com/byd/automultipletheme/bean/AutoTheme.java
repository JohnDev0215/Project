package com.byd.automultipletheme.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoTheme.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/byd/automultipletheme/bean/AutoTheme;", "Ljava/io/Serializable;", "imgUrl", "Lcom/byd/automultipletheme/bean/ImgUrl;", "rsId", "", "rsName", "", "(Lcom/byd/automultipletheme/bean/ImgUrl;ILjava/lang/String;)V", "getImgUrl", "()Lcom/byd/automultipletheme/bean/ImgUrl;", "getRsId", "()I", "getRsName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class AutoTheme implements Serializable {
    private final ImgUrl imgUrl;
    private final int rsId;
    private final String rsName;

    public static /* synthetic */ AutoTheme copy$default(AutoTheme autoTheme, ImgUrl imgUrl, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            imgUrl = autoTheme.imgUrl;
        }
        if ((i2 & 2) != 0) {
            i = autoTheme.rsId;
        }
        if ((i2 & 4) != 0) {
            str = autoTheme.rsName;
        }
        return autoTheme.copy(imgUrl, i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ImgUrl getImgUrl() {
        return this.imgUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getRsId() {
        return this.rsId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRsName() {
        return this.rsName;
    }

    public final AutoTheme copy(ImgUrl imgUrl, int rsId, String rsName) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        Intrinsics.checkNotNullParameter(rsName, "rsName");
        return new AutoTheme(imgUrl, rsId, rsName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoTheme)) {
            return false;
        }
        AutoTheme autoTheme = (AutoTheme) other;
        return Intrinsics.areEqual(this.imgUrl, autoTheme.imgUrl) && this.rsId == autoTheme.rsId && Intrinsics.areEqual(this.rsName, autoTheme.rsName);
    }

    public int hashCode() {
        return (((this.imgUrl.hashCode() * 31) + this.rsId) * 31) + this.rsName.hashCode();
    }

    public String toString() {
        return "AutoTheme(imgUrl=" + this.imgUrl + ", rsId=" + this.rsId + ", rsName=" + this.rsName + ')';
    }

    public AutoTheme(ImgUrl imgUrl, int i, String rsName) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        Intrinsics.checkNotNullParameter(rsName, "rsName");
        this.imgUrl = imgUrl;
        this.rsId = i;
        this.rsName = rsName;
    }

    public final ImgUrl getImgUrl() {
        return this.imgUrl;
    }

    public final int getRsId() {
        return this.rsId;
    }

    public final String getRsName() {
        return this.rsName;
    }
}
