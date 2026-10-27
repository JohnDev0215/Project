package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ:\u0010\u000b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/AudioResponse;", "", "Lcom/stingray/kca/model/KmlResourceV2;", "kml", "", "Lcom/stingray/kca/model/AudioResource;", "audioFiles", "Lcom/stingray/kca/model/VideoBackground;", "videoBackground", "<init>", "(Lcom/stingray/kca/model/KmlResourceV2;Ljava/util/List;Lcom/stingray/kca/model/VideoBackground;)V", "copy", "(Lcom/stingray/kca/model/KmlResourceV2;Ljava/util/List;Lcom/stingray/kca/model/VideoBackground;)Lcom/stingray/kca/model/AudioResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class AudioResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KmlResourceV2 f13076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13077b;
    public final VideoBackground c;

    public AudioResponse() {
        this(null, null, null, 7, null);
    }

    public final AudioResponse copy(@o(name = "kml") KmlResourceV2 kml, @o(name = "audioFiles") List<AudioResource> audioFiles, @o(name = "videoBackground") VideoBackground videoBackground) {
        return new AudioResponse(kml, audioFiles, videoBackground);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioResponse)) {
            return false;
        }
        AudioResponse audioResponse = (AudioResponse) obj;
        return m.a(this.f13076a, audioResponse.f13076a) && m.a(this.f13077b, audioResponse.f13077b) && m.a(this.c, audioResponse.c);
    }

    public final int hashCode() {
        KmlResourceV2 kmlResourceV2 = this.f13076a;
        int iHashCode = (kmlResourceV2 == null ? 0 : kmlResourceV2.hashCode()) * 31;
        List list = this.f13077b;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        VideoBackground videoBackground = this.c;
        return iHashCode2 + (videoBackground != null ? videoBackground.hashCode() : 0);
    }

    public final String toString() {
        return "AudioResponse(kml=" + this.f13076a + ", audioFiles=" + this.f13077b + ", videoBackground=" + this.c + ")";
    }

    public AudioResponse(@o(name = "kml") KmlResourceV2 kmlResourceV2, @o(name = "audioFiles") List<AudioResource> list, @o(name = "videoBackground") VideoBackground videoBackground) {
        this.f13076a = kmlResourceV2;
        this.f13077b = list;
        this.c = videoBackground;
    }

    public /* synthetic */ AudioResponse(KmlResourceV2 kmlResourceV2, List list, VideoBackground videoBackground, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : kmlResourceV2, (i7 & 2) != 0 ? null : list, (i7 & 4) != 0 ? null : videoBackground);
    }
}
