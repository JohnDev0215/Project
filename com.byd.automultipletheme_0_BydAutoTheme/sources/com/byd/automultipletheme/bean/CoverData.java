package com.byd.automultipletheme.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoverData.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/byd/automultipletheme/bean/CoverData;", "", "landDark", "", "landLight", "portDark", "portLight", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLandDark", "()Ljava/lang/String;", "getLandLight", "getPortDark", "getPortLight", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class CoverData {
    private final String landDark;
    private final String landLight;
    private final String portDark;
    private final String portLight;

    public static /* synthetic */ CoverData copy$default(CoverData coverData, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coverData.landDark;
        }
        if ((i & 2) != 0) {
            str2 = coverData.landLight;
        }
        if ((i & 4) != 0) {
            str3 = coverData.portDark;
        }
        if ((i & 8) != 0) {
            str4 = coverData.portLight;
        }
        return coverData.copy(str, str2, str3, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLandDark() {
        return this.landDark;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLandLight() {
        return this.landLight;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPortDark() {
        return this.portDark;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPortLight() {
        return this.portLight;
    }

    public final CoverData copy(String landDark, String landLight, String portDark, String portLight) {
        Intrinsics.checkNotNullParameter(landDark, "landDark");
        Intrinsics.checkNotNullParameter(landLight, "landLight");
        Intrinsics.checkNotNullParameter(portDark, "portDark");
        Intrinsics.checkNotNullParameter(portLight, "portLight");
        return new CoverData(landDark, landLight, portDark, portLight);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CoverData)) {
            return false;
        }
        CoverData coverData = (CoverData) other;
        return Intrinsics.areEqual(this.landDark, coverData.landDark) && Intrinsics.areEqual(this.landLight, coverData.landLight) && Intrinsics.areEqual(this.portDark, coverData.portDark) && Intrinsics.areEqual(this.portLight, coverData.portLight);
    }

    public int hashCode() {
        return (((((this.landDark.hashCode() * 31) + this.landLight.hashCode()) * 31) + this.portDark.hashCode()) * 31) + this.portLight.hashCode();
    }

    public String toString() {
        return "CoverData(landDark=" + this.landDark + ", landLight=" + this.landLight + ", portDark=" + this.portDark + ", portLight=" + this.portLight + ')';
    }

    public CoverData(String landDark, String landLight, String portDark, String portLight) {
        Intrinsics.checkNotNullParameter(landDark, "landDark");
        Intrinsics.checkNotNullParameter(landLight, "landLight");
        Intrinsics.checkNotNullParameter(portDark, "portDark");
        Intrinsics.checkNotNullParameter(portLight, "portLight");
        this.landDark = landDark;
        this.landLight = landLight;
        this.portDark = portDark;
        this.portLight = portLight;
    }

    public final String getLandDark() {
        return this.landDark;
    }

    public final String getLandLight() {
        return this.landLight;
    }

    public final String getPortDark() {
        return this.portDark;
    }

    public final String getPortLight() {
        return this.portLight;
    }
}
