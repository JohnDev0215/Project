package com.spotify.localfiles.localfiles;

import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalCovers {

    /* JADX INFO: renamed from: default, reason: not valid java name */
    private final String f0default;
    private final String extraLarge;
    private final String large;
    private final String small;

    public LocalCovers(@z61(name = "default") String str, @z61(name = "small") String str2, @z61(name = "large") String str3, @z61(name = "xlarge") String str4) {
        this.f0default = str;
        this.small = str2;
        this.large = str3;
        this.extraLarge = str4;
    }

    public static /* synthetic */ LocalCovers copy$default(LocalCovers localCovers, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = localCovers.f0default;
        }
        if ((i & 2) != 0) {
            str2 = localCovers.small;
        }
        if ((i & 4) != 0) {
            str3 = localCovers.large;
        }
        if ((i & 8) != 0) {
            str4 = localCovers.extraLarge;
        }
        return localCovers.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.f0default;
    }

    public final String component2() {
        return this.small;
    }

    public final String component3() {
        return this.large;
    }

    public final String component4() {
        return this.extraLarge;
    }

    public final LocalCovers copy(@z61(name = "default") String str, @z61(name = "small") String str2, @z61(name = "large") String str3, @z61(name = "xlarge") String str4) {
        return new LocalCovers(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalCovers)) {
            return false;
        }
        LocalCovers localCovers = (LocalCovers) obj;
        return v03.b(this.f0default, localCovers.f0default) && v03.b(this.small, localCovers.small) && v03.b(this.large, localCovers.large) && v03.b(this.extraLarge, localCovers.extraLarge);
    }

    public final String getDefault() {
        return this.f0default;
    }

    public final String getExtraLarge() {
        return this.extraLarge;
    }

    public final String getLarge() {
        return this.large;
    }

    public final String getSmall() {
        return this.small;
    }

    public int hashCode() {
        String str = this.f0default;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.small;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.large;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.extraLarge;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocalCovers(default=");
        sb.append(this.f0default);
        sb.append(", small=");
        sb.append(this.small);
        sb.append(", large=");
        sb.append(this.large);
        sb.append(", extraLarge=");
        return hf2.d(sb, this.extraLarge, ')');
    }
}
