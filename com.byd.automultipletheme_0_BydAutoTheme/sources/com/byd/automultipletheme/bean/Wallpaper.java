package com.byd.automultipletheme.bean;

import com.byd.automultipletheme.common.Const;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Wallpaper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/byd/automultipletheme/bean/Wallpaper;", "Ljava/io/Serializable;", Const.DIRECTION_LAND, "", "", Const.DIRECTION_PORT, "(Ljava/util/List;Ljava/util/List;)V", "getLand", "()Ljava/util/List;", "getPort", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class Wallpaper implements Serializable {
    private final List<String> land;
    private final List<String> port;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Wallpaper copy$default(Wallpaper wallpaper, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = wallpaper.land;
        }
        if ((i & 2) != 0) {
            list2 = wallpaper.port;
        }
        return wallpaper.copy(list, list2);
    }

    public final List<String> component1() {
        return this.land;
    }

    public final List<String> component2() {
        return this.port;
    }

    public final Wallpaper copy(List<String> land, List<String> port) {
        Intrinsics.checkNotNullParameter(land, "land");
        Intrinsics.checkNotNullParameter(port, "port");
        return new Wallpaper(land, port);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Wallpaper)) {
            return false;
        }
        Wallpaper wallpaper = (Wallpaper) other;
        return Intrinsics.areEqual(this.land, wallpaper.land) && Intrinsics.areEqual(this.port, wallpaper.port);
    }

    public int hashCode() {
        return (this.land.hashCode() * 31) + this.port.hashCode();
    }

    public String toString() {
        return "Wallpaper(land=" + this.land + ", port=" + this.port + ')';
    }

    public Wallpaper(List<String> land, List<String> port) {
        Intrinsics.checkNotNullParameter(land, "land");
        Intrinsics.checkNotNullParameter(port, "port");
        this.land = land;
        this.port = port;
    }

    public final List<String> getLand() {
        return this.land;
    }

    public final List<String> getPort() {
        return this.port;
    }
}
