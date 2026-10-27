package com.amazon.music.platform.graphql.generated.fragment;

import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Image.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "width", "Ljava/lang/Double;", "getWidth", "()Ljava/lang/Double;", "height", "getHeight", "aspectRatio", "getAspectRatio", ImagesContract.URL, "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "externalUrl", "getExternalUrl", "imageType", "getImageType", MediaTrack.ROLE_DESCRIPTION, "getDescription", "<init>", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Image {
    private final Double aspectRatio;
    private final String description;
    private final String externalUrl;
    private final Double height;
    private final String imageType;
    private final String url;
    private final Double width;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Image)) {
            return false;
        }
        Image image = (Image) other;
        return Intrinsics.areEqual((Object) this.width, (Object) image.width) && Intrinsics.areEqual((Object) this.height, (Object) image.height) && Intrinsics.areEqual((Object) this.aspectRatio, (Object) image.aspectRatio) && Intrinsics.areEqual(this.url, image.url) && Intrinsics.areEqual(this.externalUrl, image.externalUrl) && Intrinsics.areEqual(this.imageType, image.imageType) && Intrinsics.areEqual(this.description, image.description);
    }

    public int hashCode() {
        Double d = this.width;
        int iHashCode = (d == null ? 0 : d.hashCode()) * 31;
        Double d2 = this.height;
        int iHashCode2 = (iHashCode + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.aspectRatio;
        int iHashCode3 = (iHashCode2 + (d3 == null ? 0 : d3.hashCode())) * 31;
        String str = this.url;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.externalUrl;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.imageType;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.description;
        return iHashCode6 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "Image(width=" + this.width + ", height=" + this.height + ", aspectRatio=" + this.aspectRatio + ", url=" + this.url + ", externalUrl=" + this.externalUrl + ", imageType=" + this.imageType + ", description=" + this.description + ')';
    }

    public Image(Double d, Double d2, Double d3, String str, String str2, String str3, String str4) {
        this.width = d;
        this.height = d2;
        this.aspectRatio = d3;
        this.url = str;
        this.externalUrl = str2;
        this.imageType = str3;
        this.description = str4;
    }

    public final Double getWidth() {
        return this.width;
    }

    public final Double getHeight() {
        return this.height;
    }

    public final Double getAspectRatio() {
        return this.aspectRatio;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getExternalUrl() {
        return this.externalUrl;
    }

    public final String getImageType() {
        return this.imageType;
    }

    public final String getDescription() {
        return this.description;
    }
}
