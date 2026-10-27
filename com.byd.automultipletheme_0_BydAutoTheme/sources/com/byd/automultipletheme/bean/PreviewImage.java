package com.byd.automultipletheme.bean;

import com.byd.automultipletheme.common.Const;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreviewImage.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/byd/automultipletheme/bean/PreviewImage;", "Ljava/io/Serializable;", Const.DIRECTION_LAND, "", "", Const.DIRECTION_PORT, "wallpaper", "Lcom/byd/automultipletheme/bean/Wallpaper;", "(Ljava/util/List;Ljava/util/List;Lcom/byd/automultipletheme/bean/Wallpaper;)V", "getLand", "()Ljava/util/List;", "getPort", "getWallpaper", "()Lcom/byd/automultipletheme/bean/Wallpaper;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PreviewImage implements Serializable {
    private final List<String> land;
    private final List<String> port;
    private final Wallpaper wallpaper;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PreviewImage copy$default(PreviewImage previewImage, List list, List list2, Wallpaper wallpaper, int i, Object obj) {
        if ((i & 1) != 0) {
            list = previewImage.land;
        }
        if ((i & 2) != 0) {
            list2 = previewImage.port;
        }
        if ((i & 4) != 0) {
            wallpaper = previewImage.wallpaper;
        }
        return previewImage.copy(list, list2, wallpaper);
    }

    public final List<String> component1() {
        return this.land;
    }

    public final List<String> component2() {
        return this.port;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Wallpaper getWallpaper() {
        return this.wallpaper;
    }

    public final PreviewImage copy(List<String> land, List<String> port, Wallpaper wallpaper) {
        Intrinsics.checkNotNullParameter(land, "land");
        Intrinsics.checkNotNullParameter(port, "port");
        Intrinsics.checkNotNullParameter(wallpaper, "wallpaper");
        return new PreviewImage(land, port, wallpaper);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreviewImage)) {
            return false;
        }
        PreviewImage previewImage = (PreviewImage) other;
        return Intrinsics.areEqual(this.land, previewImage.land) && Intrinsics.areEqual(this.port, previewImage.port) && Intrinsics.areEqual(this.wallpaper, previewImage.wallpaper);
    }

    public int hashCode() {
        return (((this.land.hashCode() * 31) + this.port.hashCode()) * 31) + this.wallpaper.hashCode();
    }

    public String toString() {
        return "PreviewImage(land=" + this.land + ", port=" + this.port + ", wallpaper=" + this.wallpaper + ')';
    }

    public PreviewImage(List<String> land, List<String> port, Wallpaper wallpaper) {
        Intrinsics.checkNotNullParameter(land, "land");
        Intrinsics.checkNotNullParameter(port, "port");
        Intrinsics.checkNotNullParameter(wallpaper, "wallpaper");
        this.land = land;
        this.port = port;
        this.wallpaper = wallpaper;
    }

    public final List<String> getLand() {
        return this.land;
    }

    public final List<String> getPort() {
        return this.port;
    }

    public final Wallpaper getWallpaper() {
        return this.wallpaper;
    }
}
