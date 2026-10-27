package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SourceDevice {
    private final String brand;
    private final String deviceId;
    private final String deviceType;
    private final String model;
    private final boolean voiceEnabled;

    public SourceDevice(@z61(name = "brand") String str, @z61(name = "model") String str2, @z61(name = "device_type") String str3, @z61(name = "device_id") String str4, @z61(name = "voice_enabled") boolean z) {
        v03.j(str, "brand");
        v03.j(str2, "model");
        v03.j(str3, "deviceType");
        v03.j(str4, "deviceId");
        this.brand = str;
        this.model = str2;
        this.deviceType = str3;
        this.deviceId = str4;
        this.voiceEnabled = z;
    }

    public static /* synthetic */ SourceDevice copy$default(SourceDevice sourceDevice, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sourceDevice.brand;
        }
        if ((i & 2) != 0) {
            str2 = sourceDevice.model;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = sourceDevice.deviceType;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = sourceDevice.deviceId;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            z = sourceDevice.voiceEnabled;
        }
        return sourceDevice.copy(str, str5, str6, str7, z);
    }

    public final String component1() {
        return this.brand;
    }

    public final String component2() {
        return this.model;
    }

    public final String component3() {
        return this.deviceType;
    }

    public final String component4() {
        return this.deviceId;
    }

    public final boolean component5() {
        return this.voiceEnabled;
    }

    public final SourceDevice copy(@z61(name = "brand") String str, @z61(name = "model") String str2, @z61(name = "device_type") String str3, @z61(name = "device_id") String str4, @z61(name = "voice_enabled") boolean z) {
        v03.j(str, "brand");
        v03.j(str2, "model");
        v03.j(str3, "deviceType");
        v03.j(str4, "deviceId");
        return new SourceDevice(str, str2, str3, str4, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SourceDevice)) {
            return false;
        }
        SourceDevice sourceDevice = (SourceDevice) obj;
        return v03.b(this.brand, sourceDevice.brand) && v03.b(this.model, sourceDevice.model) && v03.b(this.deviceType, sourceDevice.deviceType) && v03.b(this.deviceId, sourceDevice.deviceId) && this.voiceEnabled == sourceDevice.voiceEnabled;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getModel() {
        return this.model;
    }

    public final boolean getVoiceEnabled() {
        return this.voiceEnabled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    public int hashCode() {
        int iB = hf2.b(this.deviceId, hf2.b(this.deviceType, hf2.b(this.model, this.brand.hashCode() * 31, 31), 31), 31);
        boolean z = this.voiceEnabled;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iB + r1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SourceDevice(brand=");
        sb.append(this.brand);
        sb.append(", model=");
        sb.append(this.model);
        sb.append(", deviceType=");
        sb.append(this.deviceType);
        sb.append(", deviceId=");
        sb.append(this.deviceId);
        sb.append(", voiceEnabled=");
        return u4.n(sb, this.voiceEnabled, ')');
    }

    public /* synthetic */ SourceDevice(String str, String str2, String str3, String str4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? true : z);
    }
}
