package com.spotify.core.corelimitedimpl;

import com.spotify.playbacknative.AudioDriver;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LimitedAuthenticatedScopeConfiguration {
    private String cachePath;
    private String clientId;
    private String deduplicationId;
    private String description;
    private String descriptionShort;
    private String deviceId;
    private String deviceModelName;
    private String deviceName;
    private int deviceType;
    private String nativeBundleId;
    private String settingsPath;
    private String[] supportedContentTypes;
    private String versionName;
    private int versionNumber;
    private String volatileCachePath;
    private int volumeSteps;

    public LimitedAuthenticatedScopeConfiguration() {
        this(null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, null, AudioDriver.SPOTIFY_MAX_VOLUME, null);
    }

    public final String component1() {
        return this.clientId;
    }

    public final String component10() {
        return this.versionName;
    }

    public final String component11() {
        return this.deviceId;
    }

    public final int component12() {
        return this.deviceType;
    }

    public final String[] component13() {
        return this.supportedContentTypes;
    }

    public final String component14() {
        return this.deduplicationId;
    }

    public final int component15() {
        return this.volumeSteps;
    }

    public final String component16() {
        return this.deviceModelName;
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
        return this.cachePath;
    }

    public final String component7() {
        return this.volatileCachePath;
    }

    public final String component8() {
        return this.settingsPath;
    }

    public final int component9() {
        return this.versionNumber;
    }

    public final LimitedAuthenticatedScopeConfiguration copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, int i2, String[] strArr, String str11, int i3, String str12) {
        return new LimitedAuthenticatedScopeConfiguration(str, str2, str3, str4, str5, str6, str7, str8, i, str9, str10, i2, strArr, str11, i3, str12);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimitedAuthenticatedScopeConfiguration)) {
            return false;
        }
        LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration = (LimitedAuthenticatedScopeConfiguration) obj;
        return v03.b(this.clientId, limitedAuthenticatedScopeConfiguration.clientId) && v03.b(this.nativeBundleId, limitedAuthenticatedScopeConfiguration.nativeBundleId) && v03.b(this.deviceName, limitedAuthenticatedScopeConfiguration.deviceName) && v03.b(this.description, limitedAuthenticatedScopeConfiguration.description) && v03.b(this.descriptionShort, limitedAuthenticatedScopeConfiguration.descriptionShort) && v03.b(this.cachePath, limitedAuthenticatedScopeConfiguration.cachePath) && v03.b(this.volatileCachePath, limitedAuthenticatedScopeConfiguration.volatileCachePath) && v03.b(this.settingsPath, limitedAuthenticatedScopeConfiguration.settingsPath) && this.versionNumber == limitedAuthenticatedScopeConfiguration.versionNumber && v03.b(this.versionName, limitedAuthenticatedScopeConfiguration.versionName) && v03.b(this.deviceId, limitedAuthenticatedScopeConfiguration.deviceId) && this.deviceType == limitedAuthenticatedScopeConfiguration.deviceType && v03.b(this.supportedContentTypes, limitedAuthenticatedScopeConfiguration.supportedContentTypes) && v03.b(this.deduplicationId, limitedAuthenticatedScopeConfiguration.deduplicationId) && this.volumeSteps == limitedAuthenticatedScopeConfiguration.volumeSteps && v03.b(this.deviceModelName, limitedAuthenticatedScopeConfiguration.deviceModelName);
    }

    public final String getCachePath() {
        return this.cachePath;
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
        String str6 = this.cachePath;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.volatileCachePath;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.settingsPath;
        int iA = hf2.a(this.versionNumber, (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31, 31);
        String str9 = this.versionName;
        int iHashCode8 = (iA + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.deviceId;
        int iA2 = hf2.a(this.deviceType, (iHashCode8 + (str10 == null ? 0 : str10.hashCode())) * 31, 31);
        String[] strArr = this.supportedContentTypes;
        int iHashCode9 = (iA2 + (strArr == null ? 0 : Arrays.hashCode(strArr))) * 31;
        String str11 = this.deduplicationId;
        int iA3 = hf2.a(this.volumeSteps, (iHashCode9 + (str11 == null ? 0 : str11.hashCode())) * 31, 31);
        String str12 = this.deviceModelName;
        return iA3 + (str12 != null ? str12.hashCode() : 0);
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
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
        StringBuilder sb = new StringBuilder("LimitedAuthenticatedScopeConfiguration(clientId=");
        sb.append(this.clientId);
        sb.append(", nativeBundleId=");
        sb.append(this.nativeBundleId);
        sb.append(", deviceName=");
        sb.append(this.deviceName);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", descriptionShort=");
        sb.append(this.descriptionShort);
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
        return hf2.d(sb, this.deviceModelName, ')');
    }

    public LimitedAuthenticatedScopeConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, int i2, String[] strArr, String str11, int i3, String str12) {
        this.clientId = str;
        this.nativeBundleId = str2;
        this.deviceName = str3;
        this.description = str4;
        this.descriptionShort = str5;
        this.cachePath = str6;
        this.volatileCachePath = str7;
        this.settingsPath = str8;
        this.versionNumber = i;
        this.versionName = str9;
        this.deviceId = str10;
        this.deviceType = i2;
        this.supportedContentTypes = strArr;
        this.deduplicationId = str11;
        this.volumeSteps = i3;
        this.deviceModelName = str12;
    }

    public /* synthetic */ LimitedAuthenticatedScopeConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, int i2, String[] strArr, String str11, int i3, String str12, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? null : str4, (i4 & 16) != 0 ? null : str5, (i4 & 32) != 0 ? null : str6, (i4 & 64) != 0 ? null : str7, (i4 & 128) != 0 ? null : str8, (i4 & 256) != 0 ? 0 : i, (i4 & 512) != 0 ? null : str9, (i4 & 1024) != 0 ? null : str10, (i4 & 2048) == 0 ? i2 : 0, (i4 & 4096) != 0 ? null : strArr, (i4 & 8192) != 0 ? null : str11, (i4 & 16384) != 0 ? 32 : i3, (i4 & 32768) != 0 ? null : str12);
    }
}
