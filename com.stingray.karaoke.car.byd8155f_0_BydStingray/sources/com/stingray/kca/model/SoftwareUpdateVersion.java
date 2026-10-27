package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ@\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/kca/model/SoftwareUpdateVersion;", "", "", "version", "name", "", "forced", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/stingray/kca/model/SoftwareUpdateVersion;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SoftwareUpdateVersion {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13210a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13211b;
    public final Boolean c;
    public final String d;

    public SoftwareUpdateVersion() {
        this(null, null, null, null, 15, null);
    }

    public final SoftwareUpdateVersion copy(@o(name = "version") String version, @o(name = "name") String name, @o(name = "forced") Boolean forced, @o(name = "url") String url) {
        return new SoftwareUpdateVersion(version, name, forced, url);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SoftwareUpdateVersion)) {
            return false;
        }
        SoftwareUpdateVersion softwareUpdateVersion = (SoftwareUpdateVersion) obj;
        return m.a(this.f13210a, softwareUpdateVersion.f13210a) && m.a(this.f13211b, softwareUpdateVersion.f13211b) && m.a(this.c, softwareUpdateVersion.c) && m.a(this.d, softwareUpdateVersion.d);
    }

    public final int hashCode() {
        String str = this.f13210a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13211b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.c;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.d;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SoftwareUpdateVersion(version=");
        sb.append(this.f13210a);
        sb.append(", name=");
        sb.append(this.f13211b);
        sb.append(", forced=");
        sb.append(this.c);
        sb.append(", url=");
        return a.l(sb, this.d, ")");
    }

    public SoftwareUpdateVersion(@o(name = "version") String str, @o(name = "name") String str2, @o(name = "forced") Boolean bool, @o(name = "url") String str3) {
        this.f13210a = str;
        this.f13211b = str2;
        this.c = bool;
        this.d = str3;
    }

    public /* synthetic */ SoftwareUpdateVersion(String str, String str2, Boolean bool, String str3, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : bool, (i7 & 8) != 0 ? null : str3);
    }
}
