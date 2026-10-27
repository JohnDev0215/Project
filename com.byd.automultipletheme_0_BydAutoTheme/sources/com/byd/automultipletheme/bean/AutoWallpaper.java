package com.byd.automultipletheme.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoWallpaper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/byd/automultipletheme/bean/AutoWallpaper;", "Ljava/io/Serializable;", "imgUrl", "Lcom/byd/automultipletheme/bean/PreviewImage;", "rsId", "", "rsName", "", "rsType", "(Lcom/byd/automultipletheme/bean/PreviewImage;ILjava/lang/String;I)V", "getImgUrl", "()Lcom/byd/automultipletheme/bean/PreviewImage;", "getRsId", "()I", "getRsName", "()Ljava/lang/String;", "getRsType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class AutoWallpaper implements Serializable {
    private final PreviewImage imgUrl;
    private final int rsId;
    private final String rsName;
    private final int rsType;

    public static /* synthetic */ AutoWallpaper copy$default(AutoWallpaper autoWallpaper, PreviewImage previewImage, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            previewImage = autoWallpaper.imgUrl;
        }
        if ((i3 & 2) != 0) {
            i = autoWallpaper.rsId;
        }
        if ((i3 & 4) != 0) {
            str = autoWallpaper.rsName;
        }
        if ((i3 & 8) != 0) {
            i2 = autoWallpaper.rsType;
        }
        return autoWallpaper.copy(previewImage, i, str, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PreviewImage getImgUrl() {
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

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getRsType() {
        return this.rsType;
    }

    public final AutoWallpaper copy(PreviewImage imgUrl, int rsId, String rsName, int rsType) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        Intrinsics.checkNotNullParameter(rsName, "rsName");
        return new AutoWallpaper(imgUrl, rsId, rsName, rsType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoWallpaper)) {
            return false;
        }
        AutoWallpaper autoWallpaper = (AutoWallpaper) other;
        return Intrinsics.areEqual(this.imgUrl, autoWallpaper.imgUrl) && this.rsId == autoWallpaper.rsId && Intrinsics.areEqual(this.rsName, autoWallpaper.rsName) && this.rsType == autoWallpaper.rsType;
    }

    public int hashCode() {
        return (((((this.imgUrl.hashCode() * 31) + this.rsId) * 31) + this.rsName.hashCode()) * 31) + this.rsType;
    }

    public String toString() {
        return "AutoWallpaper(imgUrl=" + this.imgUrl + ", rsId=" + this.rsId + ", rsName=" + this.rsName + ", rsType=" + this.rsType + ')';
    }

    public AutoWallpaper(PreviewImage imgUrl, int i, String rsName, int i2) {
        Intrinsics.checkNotNullParameter(imgUrl, "imgUrl");
        Intrinsics.checkNotNullParameter(rsName, "rsName");
        this.imgUrl = imgUrl;
        this.rsId = i;
        this.rsName = rsName;
        this.rsType = i2;
    }

    public final PreviewImage getImgUrl() {
        return this.imgUrl;
    }

    public final int getRsId() {
        return this.rsId;
    }

    public final String getRsName() {
        return this.rsName;
    }

    public final int getRsType() {
        return this.rsType;
    }
}
