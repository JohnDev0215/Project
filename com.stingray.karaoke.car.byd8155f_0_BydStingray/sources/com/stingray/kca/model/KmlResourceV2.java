package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/KmlResourceV2;", "", "", "uri", "", "expirationEpoch", "format", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/stingray/kca/model/KmlResourceV2;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class KmlResourceV2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13156b;
    public final String c;

    public KmlResourceV2() {
        this(null, null, null, 7, null);
    }

    public final KmlResourceV2 copy(@o(name = "uri") String uri, @o(name = "expirationEpoch") Integer expirationEpoch, @o(name = "format") String format) {
        return new KmlResourceV2(uri, expirationEpoch, format);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KmlResourceV2)) {
            return false;
        }
        KmlResourceV2 kmlResourceV2 = (KmlResourceV2) obj;
        return m.a(this.f13155a, kmlResourceV2.f13155a) && m.a(this.f13156b, kmlResourceV2.f13156b) && m.a(this.c, kmlResourceV2.c);
    }

    public final int hashCode() {
        String str = this.f13155a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.f13156b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.c;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KmlResourceV2(uri=");
        sb.append(this.f13155a);
        sb.append(", expirationEpoch=");
        sb.append(this.f13156b);
        sb.append(", format=");
        return a.l(sb, this.c, ")");
    }

    public KmlResourceV2(@o(name = "uri") String str, @o(name = "expirationEpoch") Integer num, @o(name = "format") String str2) {
        this.f13155a = str;
        this.f13156b = num;
        this.c = str2;
    }

    public /* synthetic */ KmlResourceV2(String str, Integer num, String str2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : str2);
    }
}
