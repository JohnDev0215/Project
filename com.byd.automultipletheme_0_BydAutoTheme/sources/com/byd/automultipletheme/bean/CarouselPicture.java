package com.byd.automultipletheme.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselPicture.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JO\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011¨\u0006'"}, d2 = {"Lcom/byd/automultipletheme/bean/CarouselPicture;", "Ljava/io/Serializable;", "portLight", "", "portNight", "landLight", "landNight", "isSelected", "", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "()Z", "setSelected", "(Z)V", "getLandLight", "()Ljava/lang/String;", "setLandLight", "(Ljava/lang/String;)V", "getLandNight", "setLandNight", "getName", "setName", "getPortLight", "setPortLight", "getPortNight", "setPortNight", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class CarouselPicture implements Serializable {
    private boolean isSelected;
    private String landLight;
    private String landNight;
    private String name;
    private String portLight;
    private String portNight;

    public CarouselPicture() {
        this(null, null, null, null, false, null, 63, null);
    }

    public static /* synthetic */ CarouselPicture copy$default(CarouselPicture carouselPicture, String str, String str2, String str3, String str4, boolean z, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = carouselPicture.portLight;
        }
        if ((i & 2) != 0) {
            str2 = carouselPicture.portNight;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = carouselPicture.landLight;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = carouselPicture.landNight;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            z = carouselPicture.isSelected;
        }
        boolean z2 = z;
        if ((i & 32) != 0) {
            str5 = carouselPicture.name;
        }
        return carouselPicture.copy(str, str6, str7, str8, z2, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPortLight() {
        return this.portLight;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPortNight() {
        return this.portNight;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLandLight() {
        return this.landLight;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLandNight() {
        return this.landNight;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final CarouselPicture copy(String portLight, String portNight, String landLight, String landNight, boolean isSelected, String name) {
        return new CarouselPicture(portLight, portNight, landLight, landNight, isSelected, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CarouselPicture)) {
            return false;
        }
        CarouselPicture carouselPicture = (CarouselPicture) other;
        return Intrinsics.areEqual(this.portLight, carouselPicture.portLight) && Intrinsics.areEqual(this.portNight, carouselPicture.portNight) && Intrinsics.areEqual(this.landLight, carouselPicture.landLight) && Intrinsics.areEqual(this.landNight, carouselPicture.landNight) && this.isSelected == carouselPicture.isSelected && Intrinsics.areEqual(this.name, carouselPicture.name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v16 */
    public int hashCode() {
        String str = this.portLight;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.portNight;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.landLight;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.landNight;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        boolean z = this.isSelected;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode4 + r2) * 31;
        String str5 = this.name;
        return i + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "CarouselPicture(portLight=" + this.portLight + ", portNight=" + this.portNight + ", landLight=" + this.landLight + ", landNight=" + this.landNight + ", isSelected=" + this.isSelected + ", name=" + this.name + ')';
    }

    public CarouselPicture(String str, String str2, String str3, String str4, boolean z, String str5) {
        this.portLight = str;
        this.portNight = str2;
        this.landLight = str3;
        this.landNight = str4;
        this.isSelected = z;
        this.name = str5;
    }

    public /* synthetic */ CarouselPicture(String str, String str2, String str3, String str4, boolean z, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : str5);
    }

    public final String getPortLight() {
        return this.portLight;
    }

    public final void setPortLight(String str) {
        this.portLight = str;
    }

    public final String getPortNight() {
        return this.portNight;
    }

    public final void setPortNight(String str) {
        this.portNight = str;
    }

    public final String getLandLight() {
        return this.landLight;
    }

    public final void setLandLight(String str) {
        this.landLight = str;
    }

    public final String getLandNight() {
        return this.landNight;
    }

    public final void setLandNight(String str) {
        this.landNight = str;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }
}
