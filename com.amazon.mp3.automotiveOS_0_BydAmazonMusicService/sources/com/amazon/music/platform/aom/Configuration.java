package com.amazon.music.platform.aom;

import com.amazon.music.platform.aom.launcher.Catalog;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Configuration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/aom/Configuration;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/aom/launcher/Catalog;", "catalogs", "Ljava/util/List;", "getCatalogs", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Configuration {
    private final List<Catalog> catalogs;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Configuration) && Intrinsics.areEqual(this.catalogs, ((Configuration) other).catalogs);
    }

    public int hashCode() {
        return this.catalogs.hashCode();
    }

    public String toString() {
        return "Configuration(catalogs=" + this.catalogs + ')';
    }

    public Configuration(List<Catalog> catalogs) {
        Intrinsics.checkNotNullParameter(catalogs, "catalogs");
        this.catalogs = catalogs;
        PlatformProviderUtilKt.freeze(this);
    }

    public final List<Catalog> getCatalogs() {
        return this.catalogs;
    }
}
