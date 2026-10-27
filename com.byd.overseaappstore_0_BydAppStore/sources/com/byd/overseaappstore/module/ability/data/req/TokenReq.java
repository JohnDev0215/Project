package com.byd.overseaappstore.module.ability.data.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/TokenReq;", "", "vehicle_code", "", "screenOrientation", "platform", "", "countryCode", "(IILjava/lang/String;Ljava/lang/String;)V", "getCountryCode", "()Ljava/lang/String;", "getPlatform", "getScreenOrientation", "()I", "getVehicle_code", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class TokenReq {

    @NotNull
    private final String countryCode;

    @NotNull
    private final String platform;
    private final int screenOrientation;
    private final int vehicle_code;

    public TokenReq(int i, int i2, @NotNull String platform, @NotNull String countryCode) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.vehicle_code = i;
        this.screenOrientation = i2;
        this.platform = platform;
        this.countryCode = countryCode;
    }

    public static /* synthetic */ TokenReq copy$default(TokenReq tokenReq, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = tokenReq.vehicle_code;
        }
        if ((i3 & 2) != 0) {
            i2 = tokenReq.screenOrientation;
        }
        if ((i3 & 4) != 0) {
            str = tokenReq.platform;
        }
        if ((i3 & 8) != 0) {
            str2 = tokenReq.countryCode;
        }
        return tokenReq.copy(i, i2, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getVehicle_code() {
        return this.vehicle_code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getScreenOrientation() {
        return this.screenOrientation;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final TokenReq copy(int vehicle_code, int screenOrientation, @NotNull String platform, @NotNull String countryCode) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return new TokenReq(vehicle_code, screenOrientation, platform, countryCode);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenReq)) {
            return false;
        }
        TokenReq tokenReq = (TokenReq) other;
        return this.vehicle_code == tokenReq.vehicle_code && this.screenOrientation == tokenReq.screenOrientation && Intrinsics.areEqual(this.platform, tokenReq.platform) && Intrinsics.areEqual(this.countryCode, tokenReq.countryCode);
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @NotNull
    public final String getPlatform() {
        return this.platform;
    }

    public final int getScreenOrientation() {
        return this.screenOrientation;
    }

    public final int getVehicle_code() {
        return this.vehicle_code;
    }

    public int hashCode() {
        return this.countryCode.hashCode() + xi.b(this.platform, (Integer.hashCode(this.screenOrientation) + (Integer.hashCode(this.vehicle_code) * 31)) * 31, 31);
    }

    @NotNull
    public String toString() {
        int i = this.vehicle_code;
        int i2 = this.screenOrientation;
        String str = this.platform;
        String str2 = this.countryCode;
        StringBuilder sbL = e.l("TokenReq(vehicle_code=", i, ", screenOrientation=", i2, ", platform=");
        sbL.append(str);
        sbL.append(", countryCode=");
        sbL.append(str2);
        sbL.append(")");
        return sbL.toString();
    }
}
