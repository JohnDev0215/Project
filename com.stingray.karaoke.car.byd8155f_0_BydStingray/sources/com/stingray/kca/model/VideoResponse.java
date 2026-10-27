package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\r\u0010\u000eJp\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/stingray/kca/model/VideoResponse;", "", "", "uri", "", "expirationMilliSec", "durationSec", "format", "", "hasLeadVocals", "background", "videoCodec", "isSample", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/stingray/kca/model/VideoResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class VideoResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13242b;
    public final Integer c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Boolean f13243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13244f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13245g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Boolean f13246h;

    public VideoResponse() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public final VideoResponse copy(@o(name = "uri") String uri, @o(name = "expirationMilliSec") Integer expirationMilliSec, @o(name = "durationSec") Integer durationSec, @o(name = "format") String format, @o(name = "hasLeadVocals") Boolean hasLeadVocals, @o(name = "background") String background, @o(name = "videoCodec") String videoCodec, @o(name = "isSample") Boolean isSample) {
        return new VideoResponse(uri, expirationMilliSec, durationSec, format, hasLeadVocals, background, videoCodec, isSample);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoResponse)) {
            return false;
        }
        VideoResponse videoResponse = (VideoResponse) obj;
        return m.a(this.f13241a, videoResponse.f13241a) && m.a(this.f13242b, videoResponse.f13242b) && m.a(this.c, videoResponse.c) && m.a(this.d, videoResponse.d) && m.a(this.f13243e, videoResponse.f13243e) && m.a(this.f13244f, videoResponse.f13244f) && m.a(this.f13245g, videoResponse.f13245g) && m.a(this.f13246h, videoResponse.f13246h);
    }

    public final int hashCode() {
        String str = this.f13241a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.f13242b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.f13243e;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.f13244f;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f13245g;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool2 = this.f13246h;
        return iHashCode7 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final String toString() {
        return "VideoResponse(uri=" + this.f13241a + ", expirationMilliSec=" + this.f13242b + ", durationSec=" + this.c + ", format=" + this.d + ", hasLeadVocals=" + this.f13243e + ", background=" + this.f13244f + ", videoCodec=" + this.f13245g + ", isSample=" + this.f13246h + ")";
    }

    public VideoResponse(@o(name = "uri") String str, @o(name = "expirationMilliSec") Integer num, @o(name = "durationSec") Integer num2, @o(name = "format") String str2, @o(name = "hasLeadVocals") Boolean bool, @o(name = "background") String str3, @o(name = "videoCodec") String str4, @o(name = "isSample") Boolean bool2) {
        this.f13241a = str;
        this.f13242b = num;
        this.c = num2;
        this.d = str2;
        this.f13243e = bool;
        this.f13244f = str3;
        this.f13245g = str4;
        this.f13246h = bool2;
    }

    public /* synthetic */ VideoResponse(String str, Integer num, Integer num2, String str2, Boolean bool, String str3, String str4, Boolean bool2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : num2, (i7 & 8) != 0 ? null : str2, (i7 & 16) != 0 ? null : bool, (i7 & 32) != 0 ? null : str3, (i7 & 64) != 0 ? null : str4, (i7 & 128) != 0 ? null : bool2);
    }
}
