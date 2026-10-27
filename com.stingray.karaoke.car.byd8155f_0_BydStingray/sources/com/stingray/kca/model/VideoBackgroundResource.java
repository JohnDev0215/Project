package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJL\u0010\u000b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/VideoBackgroundResource;", "", "", "uri", "", "expirationEpoch", "format", "videoCodec", "resolution", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/VideoBackgroundResource;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class VideoBackgroundResource {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13237b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f13238e;

    public VideoBackgroundResource() {
        this(null, null, null, null, null, 31, null);
    }

    public final VideoBackgroundResource copy(@o(name = "uri") String uri, @o(name = "expirationEpoch") Integer expirationEpoch, @o(name = "format") String format, @o(name = "videoCodec") String videoCodec, @o(name = "resolution") String resolution) {
        return new VideoBackgroundResource(uri, expirationEpoch, format, videoCodec, resolution);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoBackgroundResource)) {
            return false;
        }
        VideoBackgroundResource videoBackgroundResource = (VideoBackgroundResource) obj;
        return m.a(this.f13236a, videoBackgroundResource.f13236a) && m.a(this.f13237b, videoBackgroundResource.f13237b) && m.a(this.c, videoBackgroundResource.c) && m.a(this.d, videoBackgroundResource.d) && m.a(this.f13238e, videoBackgroundResource.f13238e);
    }

    public final int hashCode() {
        String str = this.f13236a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.f13237b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.c;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f13238e;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("VideoBackgroundResource(uri=");
        sb.append(this.f13236a);
        sb.append(", expirationEpoch=");
        sb.append(this.f13237b);
        sb.append(", format=");
        sb.append(this.c);
        sb.append(", videoCodec=");
        sb.append(this.d);
        sb.append(", resolution=");
        return a.l(sb, this.f13238e, ")");
    }

    public VideoBackgroundResource(@o(name = "uri") String str, @o(name = "expirationEpoch") Integer num, @o(name = "format") String str2, @o(name = "videoCodec") String str3, @o(name = "resolution") String str4) {
        this.f13236a = str;
        this.f13237b = num;
        this.c = str2;
        this.d = str3;
        this.f13238e = str4;
    }

    public /* synthetic */ VideoBackgroundResource(String str, Integer num, String str2, String str3, String str4, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : str2, (i7 & 8) != 0 ? null : str3, (i7 & 16) != 0 ? null : str4);
    }
}
