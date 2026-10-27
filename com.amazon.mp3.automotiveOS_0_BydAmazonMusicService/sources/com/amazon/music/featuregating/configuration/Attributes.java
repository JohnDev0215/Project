package com.amazon.music.featuregating.configuration;

import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Attributes.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001a¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/featuregating/configuration/Attributes;", "", "", "toString", "", "hashCode", "other", "", "equals", "carrier", "Ljava/lang/String;", "getCarrier", "()Ljava/lang/String;", Splash.PARAMS_DEVICE_ID, "getDeviceId", DeviceInformation.PARAMS_DEVICE_MODEL, "getDeviceModel", "deviceManufacturer", "getDeviceManufacturer", IntlConfiguration.MARKETPLACE, "getMarketplace", "territory", "getTerritory", Splash.PARAMS_APP_VERSION, "Ljava/lang/Integer;", "getAppVersion", "()Ljava/lang/Integer;", "osVersion", "getOsVersion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Attributes {
    private final Integer appVersion;
    private final String carrier;
    private final String deviceId;
    private final String deviceManufacturer;
    private final String deviceModel;
    private final String marketplace;
    private final Integer osVersion;
    private final String territory;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Attributes)) {
            return false;
        }
        Attributes attributes = (Attributes) other;
        return Intrinsics.areEqual(this.carrier, attributes.carrier) && Intrinsics.areEqual(this.deviceId, attributes.deviceId) && Intrinsics.areEqual(this.deviceModel, attributes.deviceModel) && Intrinsics.areEqual(this.deviceManufacturer, attributes.deviceManufacturer) && Intrinsics.areEqual(this.marketplace, attributes.marketplace) && Intrinsics.areEqual(this.territory, attributes.territory) && Intrinsics.areEqual(this.appVersion, attributes.appVersion) && Intrinsics.areEqual(this.osVersion, attributes.osVersion);
    }

    public int hashCode() {
        String str = this.carrier;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.deviceId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.deviceModel;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.deviceManufacturer;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.marketplace;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.territory;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num = this.appVersion;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.osVersion;
        return iHashCode7 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "Attributes(carrier=" + this.carrier + ", deviceId=" + this.deviceId + ", deviceModel=" + this.deviceModel + ", deviceManufacturer=" + this.deviceManufacturer + ", marketplace=" + this.marketplace + ", territory=" + this.territory + ", appVersion=" + this.appVersion + ", osVersion=" + this.osVersion + ')';
    }

    public Attributes(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2) {
        this.carrier = str;
        this.deviceId = str2;
        this.deviceModel = str3;
        this.deviceManufacturer = str4;
        this.marketplace = str5;
        this.territory = str6;
        this.appVersion = num;
        this.osVersion = num2;
    }

    public final String getCarrier() {
        return this.carrier;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public final String getMarketplace() {
        return this.marketplace;
    }

    public final String getTerritory() {
        return this.territory;
    }

    public final Integer getAppVersion() {
        return this.appVersion;
    }

    public final Integer getOsVersion() {
        return this.osVersion;
    }
}
