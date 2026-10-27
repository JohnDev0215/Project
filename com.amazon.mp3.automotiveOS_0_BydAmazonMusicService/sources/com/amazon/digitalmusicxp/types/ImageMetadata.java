package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageMetadata.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "clickUrl", "Ljava/lang/String;", "getClickUrl", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "fixedSizeImage", "Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "getFixedSizeImage", "()Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "Lcom/amazon/digitalmusicxp/types/ImageManifest;", "imageManifest", "Lcom/amazon/digitalmusicxp/types/ImageManifest;", "getImageManifest", "()Lcom/amazon/digitalmusicxp/types/ImageManifest;", "adChoiceClickUrl", "getAdChoiceClickUrl", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/FixedSizeImage;Lcom/amazon/digitalmusicxp/types/ImageManifest;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ImageMetadata {
    private final String adChoiceClickUrl;
    private final String clickUrl;
    private final FixedSizeImage fixedSizeImage;
    private final ImageManifest imageManifest;

    public ImageMetadata() {
        this(null, null, null, null, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageMetadata)) {
            return false;
        }
        ImageMetadata imageMetadata = (ImageMetadata) other;
        return Intrinsics.areEqual(this.clickUrl, imageMetadata.clickUrl) && Intrinsics.areEqual(this.fixedSizeImage, imageMetadata.fixedSizeImage) && Intrinsics.areEqual(this.imageManifest, imageMetadata.imageManifest) && Intrinsics.areEqual(this.adChoiceClickUrl, imageMetadata.adChoiceClickUrl);
    }

    public int hashCode() {
        String str = this.clickUrl;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        FixedSizeImage fixedSizeImage = this.fixedSizeImage;
        int iHashCode2 = (iHashCode + (fixedSizeImage == null ? 0 : fixedSizeImage.hashCode())) * 31;
        ImageManifest imageManifest = this.imageManifest;
        int iHashCode3 = (iHashCode2 + (imageManifest == null ? 0 : imageManifest.hashCode())) * 31;
        String str2 = this.adChoiceClickUrl;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ImageMetadata(clickUrl=" + this.clickUrl + ", fixedSizeImage=" + this.fixedSizeImage + ", imageManifest=" + this.imageManifest + ", adChoiceClickUrl=" + this.adChoiceClickUrl + ')';
    }

    public ImageMetadata(String str, FixedSizeImage fixedSizeImage, ImageManifest imageManifest, String str2) {
        this.clickUrl = str;
        this.fixedSizeImage = fixedSizeImage;
        this.imageManifest = imageManifest;
        this.adChoiceClickUrl = str2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ ImageMetadata(String str, FixedSizeImage fixedSizeImage, ImageManifest imageManifest, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : fixedSizeImage, (i & 4) != 0 ? null : imageManifest, (i & 8) != 0 ? null : str2);
    }

    public final String getClickUrl() {
        return this.clickUrl;
    }

    public final FixedSizeImage getFixedSizeImage() {
        return this.fixedSizeImage;
    }

    public final ImageManifest getImageManifest() {
        return this.imageManifest;
    }

    public final String getAdChoiceClickUrl() {
        return this.adChoiceClickUrl;
    }
}
