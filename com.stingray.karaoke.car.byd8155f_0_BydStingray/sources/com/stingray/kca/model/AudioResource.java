package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJd\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/stingray/kca/model/AudioResource;", "", "", "uri", "", "expirationEpoch", "durationSec", "format", "", "hasLeadVocals", "audioCodec", "sample", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;)V", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/stingray/kca/model/AudioResource;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class AudioResource {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13069b;
    public final Integer c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Boolean f13070e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13071f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Boolean f13072g;

    public AudioResource() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public final AudioResource copy(@o(name = "uri") String uri, @o(name = "expirationEpoch") Integer expirationEpoch, @o(name = "durationSec") Integer durationSec, @o(name = "format") String format, @o(name = "hasLeadVocals") Boolean hasLeadVocals, @o(name = "audioCodec") String audioCodec, @o(name = "sample") Boolean sample) {
        return new AudioResource(uri, expirationEpoch, durationSec, format, hasLeadVocals, audioCodec, sample);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioResource)) {
            return false;
        }
        AudioResource audioResource = (AudioResource) obj;
        return m.a(this.f13068a, audioResource.f13068a) && m.a(this.f13069b, audioResource.f13069b) && m.a(this.c, audioResource.c) && m.a(this.d, audioResource.d) && m.a(this.f13070e, audioResource.f13070e) && m.a(this.f13071f, audioResource.f13071f) && m.a(this.f13072g, audioResource.f13072g);
    }

    public final int hashCode() {
        String str = this.f13068a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.f13069b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.f13070e;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.f13071f;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool2 = this.f13072g;
        return iHashCode6 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final String toString() {
        return "AudioResource(uri=" + this.f13068a + ", expirationEpoch=" + this.f13069b + ", durationSec=" + this.c + ", format=" + this.d + ", hasLeadVocals=" + this.f13070e + ", audioCodec=" + this.f13071f + ", sample=" + this.f13072g + ")";
    }

    public AudioResource(@o(name = "uri") String str, @o(name = "expirationEpoch") Integer num, @o(name = "durationSec") Integer num2, @o(name = "format") String str2, @o(name = "hasLeadVocals") Boolean bool, @o(name = "audioCodec") String str3, @o(name = "sample") Boolean bool2) {
        this.f13068a = str;
        this.f13069b = num;
        this.c = num2;
        this.d = str2;
        this.f13070e = bool;
        this.f13071f = str3;
        this.f13072g = bool2;
    }

    public /* synthetic */ AudioResource(String str, Integer num, Integer num2, String str2, Boolean bool, String str3, Boolean bool2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : num2, (i7 & 8) != 0 ? null : str2, (i7 & 16) != 0 ? null : bool, (i7 & 32) != 0 ? null : str3, (i7 & 64) != 0 ? null : bool2);
    }
}
