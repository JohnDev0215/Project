package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J4\u0010\b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/stingray/kca/model/AppsflyerParameter;", "", "", "advertisingId", "appsflyerId", "vendorId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/AppsflyerParameter;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class AppsflyerParameter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13048a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13049b;
    public final String c;

    public AppsflyerParameter() {
        this(null, null, null, 7, null);
    }

    public final AppsflyerParameter copy(@o(name = "advertisingId") String advertisingId, @o(name = "appsflyerId") String appsflyerId, @o(name = "vendorId") String vendorId) {
        return new AppsflyerParameter(advertisingId, appsflyerId, vendorId);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppsflyerParameter)) {
            return false;
        }
        AppsflyerParameter appsflyerParameter = (AppsflyerParameter) obj;
        return m.a(this.f13048a, appsflyerParameter.f13048a) && m.a(this.f13049b, appsflyerParameter.f13049b) && m.a(this.c, appsflyerParameter.c);
    }

    public final int hashCode() {
        String str = this.f13048a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13049b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AppsflyerParameter(advertisingId=");
        sb.append(this.f13048a);
        sb.append(", appsflyerId=");
        sb.append(this.f13049b);
        sb.append(", vendorId=");
        return a.l(sb, this.c, ")");
    }

    public AppsflyerParameter(@o(name = "advertisingId") String str, @o(name = "appsflyerId") String str2, @o(name = "vendorId") String str3) {
        this.f13048a = str;
        this.f13049b = str2;
        this.c = str3;
    }

    public /* synthetic */ AppsflyerParameter(String str, String str2, String str3, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3);
    }
}
