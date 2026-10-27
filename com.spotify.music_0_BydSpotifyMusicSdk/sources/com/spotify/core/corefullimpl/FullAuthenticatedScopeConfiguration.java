package com.spotify.core.corefullimpl;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FullAuthenticatedScopeConfiguration {
    private String brand;
    private String cachePath;
    private boolean canPlay;
    private String clientId;
    private String deduplicationId;
    private String description;
    private String descriptionShort;
    private String deviceId;
    private String deviceModelName;
    private String deviceName;
    private int deviceType;
    private String model;
    private String nativeBundleId;
    private String settingsPath;
    private String[] supportedContentTypes;
    private String versionName;
    private int versionNumber;
    private String volatileCachePath;
    private int volumeSteps;

    public FullAuthenticatedScopeConfiguration() {
        this(null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, null, false, 524287, null);
    }

    public final String component1() {
        return this.clientId;
    }

    public final String component10() {
        return this.settingsPath;
    }

    public final int component11() {
        return this.versionNumber;
    }

    public final String component12() {
        return this.versionName;
    }

    public final String component13() {
        return this.deviceId;
    }

    public final int component14() {
        return this.deviceType;
    }

    public final String[] component15() {
        return this.supportedContentTypes;
    }

    public final String component16() {
        return this.deduplicationId;
    }

    public final int component17() {
        return this.volumeSteps;
    }

    public final String component18() {
        return this.deviceModelName;
    }

    public final boolean component19() {
        return this.canPlay;
    }

    public final String component2() {
        return this.nativeBundleId;
    }

    public final String component3() {
        return this.deviceName;
    }

    public final String component4() {
        return this.description;
    }

    public final String component5() {
        return this.descriptionShort;
    }

    public final String component6() {
        return this.model;
    }

    public final String component7() {
        return this.brand;
    }

    public final String component8() {
        return this.cachePath;
    }

    public final String component9() {
        return this.volatileCachePath;
    }

    public final FullAuthenticatedScopeConfiguration copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, String str11, String str12, int i2, String[] strArr, String str13, int i3, String str14, boolean z) {
        return new FullAuthenticatedScopeConfiguration(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i, str11, str12, i2, strArr, str13, i3, str14, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FullAuthenticatedScopeConfiguration)) {
            return false;
        }
        FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration = (FullAuthenticatedScopeConfiguration) obj;
        return v03.b(this.clientId, fullAuthenticatedScopeConfiguration.clientId) && v03.b(this.nativeBundleId, fullAuthenticatedScopeConfiguration.nativeBundleId) && v03.b(this.deviceName, fullAuthenticatedScopeConfiguration.deviceName) && v03.b(this.description, fullAuthenticatedScopeConfiguration.description) && v03.b(this.descriptionShort, fullAuthenticatedScopeConfiguration.descriptionShort) && v03.b(this.model, fullAuthenticatedScopeConfiguration.model) && v03.b(this.brand, fullAuthenticatedScopeConfiguration.brand) && v03.b(this.cachePath, fullAuthenticatedScopeConfiguration.cachePath) && v03.b(this.volatileCachePath, fullAuthenticatedScopeConfiguration.volatileCachePath) && v03.b(this.settingsPath, fullAuthenticatedScopeConfiguration.settingsPath) && this.versionNumber == fullAuthenticatedScopeConfiguration.versionNumber && v03.b(this.versionName, fullAuthenticatedScopeConfiguration.versionName) && v03.b(this.deviceId, fullAuthenticatedScopeConfiguration.deviceId) && this.deviceType == fullAuthenticatedScopeConfiguration.deviceType && v03.b(this.supportedContentTypes, fullAuthenticatedScopeConfiguration.supportedContentTypes) && v03.b(this.deduplicationId, fullAuthenticatedScopeConfiguration.deduplicationId) && this.volumeSteps == fullAuthenticatedScopeConfiguration.volumeSteps && v03.b(this.deviceModelName, fullAuthenticatedScopeConfiguration.deviceModelName) && this.canPlay == fullAuthenticatedScopeConfiguration.canPlay;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final String getCachePath() {
        return this.cachePath;
    }

    public final boolean getCanPlay() {
        return this.canPlay;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getDeduplicationId() {
        return this.deduplicationId;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getDescriptionShort() {
        return this.descriptionShort;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceModelName() {
        return this.deviceModelName;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getNativeBundleId() {
        return this.nativeBundleId;
    }

    public final String getSettingsPath() {
        return this.settingsPath;
    }

    public final String[] getSupportedContentTypes() {
        return this.supportedContentTypes;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final int getVersionNumber() {
        return this.versionNumber;
    }

    public final String getVolatileCachePath() {
        return this.volatileCachePath;
    }

    public final int getVolumeSteps() {
        return this.volumeSteps;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        String str = this.clientId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.nativeBundleId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.deviceName;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.description;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.descriptionShort;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.model;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.brand;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.cachePath;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.volatileCachePath;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.settingsPath;
        int iA = hf2.a(this.versionNumber, (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31, 31);
        String str11 = this.versionName;
        int iHashCode10 = (iA + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.deviceId;
        int iA2 = hf2.a(this.deviceType, (iHashCode10 + (str12 == null ? 0 : str12.hashCode())) * 31, 31);
        String[] strArr = this.supportedContentTypes;
        int iHashCode11 = (iA2 + (strArr == null ? 0 : Arrays.hashCode(strArr))) * 31;
        String str13 = this.deduplicationId;
        int iA3 = hf2.a(this.volumeSteps, (iHashCode11 + (str13 == null ? 0 : str13.hashCode())) * 31, 31);
        String str14 = this.deviceModelName;
        int iHashCode12 = (iA3 + (str14 != null ? str14.hashCode() : 0)) * 31;
        boolean z = this.canPlay;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode12 + r1;
    }

    public final void setBrand(String str) {
        this.brand = str;
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
    }

    public final void setCanPlay(boolean z) {
        this.canPlay = z;
    }

    public final void setClientId(String str) {
        this.clientId = str;
    }

    public final void setDeduplicationId(String str) {
        this.deduplicationId = str;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final void setDescriptionShort(String str) {
        this.descriptionShort = str;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final void setDeviceModelName(String str) {
        this.deviceModelName = str;
    }

    public final void setDeviceName(String str) {
        this.deviceName = str;
    }

    public final void setDeviceType(int i) {
        this.deviceType = i;
    }

    public final void setModel(String str) {
        this.model = str;
    }

    public final void setNativeBundleId(String str) {
        this.nativeBundleId = str;
    }

    public final void setSettingsPath(String str) {
        this.settingsPath = str;
    }

    public final void setSupportedContentTypes(String[] strArr) {
        this.supportedContentTypes = strArr;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }

    public final void setVersionNumber(int i) {
        this.versionNumber = i;
    }

    public final void setVolatileCachePath(String str) {
        this.volatileCachePath = str;
    }

    public final void setVolumeSteps(int i) {
        this.volumeSteps = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FullAuthenticatedScopeConfiguration(clientId=");
        sb.append(this.clientId);
        sb.append(", nativeBundleId=");
        sb.append(this.nativeBundleId);
        sb.append(", deviceName=");
        sb.append(this.deviceName);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", descriptionShort=");
        sb.append(this.descriptionShort);
        sb.append(", model=");
        sb.append(this.model);
        sb.append(", brand=");
        sb.append(this.brand);
        sb.append(", cachePath=");
        sb.append(this.cachePath);
        sb.append(", volatileCachePath=");
        sb.append(this.volatileCachePath);
        sb.append(", settingsPath=");
        sb.append(this.settingsPath);
        sb.append(", versionNumber=");
        sb.append(this.versionNumber);
        sb.append(", versionName=");
        sb.append(this.versionName);
        sb.append(", deviceId=");
        sb.append(this.deviceId);
        sb.append(", deviceType=");
        sb.append(this.deviceType);
        sb.append(", supportedContentTypes=");
        sb.append(Arrays.toString(this.supportedContentTypes));
        sb.append(", deduplicationId=");
        sb.append(this.deduplicationId);
        sb.append(", volumeSteps=");
        sb.append(this.volumeSteps);
        sb.append(", deviceModelName=");
        sb.append(this.deviceModelName);
        sb.append(", canPlay=");
        return u4.n(sb, this.canPlay, ')');
    }

    public FullAuthenticatedScopeConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, String str11, String str12, int i2, String[] strArr, String str13, int i3, String str14, boolean z) {
        this.clientId = str;
        this.nativeBundleId = str2;
        this.deviceName = str3;
        this.description = str4;
        this.descriptionShort = str5;
        this.model = str6;
        this.brand = str7;
        this.cachePath = str8;
        this.volatileCachePath = str9;
        this.settingsPath = str10;
        this.versionNumber = i;
        this.versionName = str11;
        this.deviceId = str12;
        this.deviceType = i2;
        this.supportedContentTypes = strArr;
        this.deduplicationId = str13;
        this.volumeSteps = i3;
        this.deviceModelName = str14;
        this.canPlay = z;
    }

    public /* synthetic */ FullAuthenticatedScopeConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, String str11, String str12, int i2, String[] strArr, String str13, int i3, String str14, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? null : str4, (i4 & 16) != 0 ? null : str5, (i4 & 32) != 0 ? null : str6, (i4 & 64) != 0 ? null : str7, (i4 & 128) != 0 ? null : str8, (i4 & 256) != 0 ? null : str9, (i4 & 512) != 0 ? null : str10, (i4 & 1024) != 0 ? 0 : i, (i4 & 2048) != 0 ? null : str11, (i4 & 4096) != 0 ? null : str12, (i4 & 8192) == 0 ? i2 : 0, (i4 & 16384) != 0 ? null : strArr, (i4 & 32768) != 0 ? null : str13, (i4 & 65536) != 0 ? 32 : i3, (i4 & 131072) != 0 ? null : str14, (i4 & 262144) != 0 ? true : z);
    }
}
