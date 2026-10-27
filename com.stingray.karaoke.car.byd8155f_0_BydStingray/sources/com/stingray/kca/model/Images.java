package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0016\b\u0003\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0016\b\u0003\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/Images;", "", "Lcom/stingray/kca/model/ImageType;", "defaultType", "", "", "imageByType", "<init>", "(Lcom/stingray/kca/model/ImageType;Ljava/util/Map;)V", "copy", "(Lcom/stingray/kca/model/ImageType;Ljava/util/Map;)Lcom/stingray/kca/model/Images;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class Images {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageType f13099a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f13100b;

    /* JADX WARN: Multi-variable type inference failed */
    public Images() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final Images copy(@o(name = "defaultType") ImageType defaultType, @o(name = "imageByType") Map<String, String> imageByType) {
        return new Images(defaultType, imageByType);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Images)) {
            return false;
        }
        Images images = (Images) obj;
        return this.f13099a == images.f13099a && m.a(this.f13100b, images.f13100b);
    }

    public final int hashCode() {
        ImageType imageType = this.f13099a;
        int iHashCode = (imageType == null ? 0 : imageType.hashCode()) * 31;
        Map map = this.f13100b;
        return iHashCode + (map != null ? map.hashCode() : 0);
    }

    public final String toString() {
        return "Images(defaultType=" + this.f13099a + ", imageByType=" + this.f13100b + ")";
    }

    public Images(@o(name = "defaultType") ImageType imageType, @o(name = "imageByType") Map<String, String> map) {
        this.f13099a = imageType;
        this.f13100b = map;
    }

    public /* synthetic */ Images(ImageType imageType, Map map, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : imageType, (i7 & 2) != 0 ? null : map);
    }
}
