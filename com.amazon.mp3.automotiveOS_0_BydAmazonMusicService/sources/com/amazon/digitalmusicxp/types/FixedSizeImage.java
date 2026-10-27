package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FixedSizeImage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "", "", "toString", "", "hashCode", "other", "", "equals", "fileFormat", "Ljava/lang/String;", "getFileFormat", "()Ljava/lang/String;", "heightInPixels", "Ljava/lang/Integer;", "getHeightInPixels", "()Ljava/lang/Integer;", "widthInPixels", "getWidthInPixels", "imageUrl", "getImageUrl", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FixedSizeImage {
    private final String fileFormat;
    private final Integer heightInPixels;
    private final String imageUrl;
    private final Integer widthInPixels;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FixedSizeImage)) {
            return false;
        }
        FixedSizeImage fixedSizeImage = (FixedSizeImage) other;
        return Intrinsics.areEqual(this.fileFormat, fixedSizeImage.fileFormat) && Intrinsics.areEqual(this.heightInPixels, fixedSizeImage.heightInPixels) && Intrinsics.areEqual(this.widthInPixels, fixedSizeImage.widthInPixels) && Intrinsics.areEqual(this.imageUrl, fixedSizeImage.imageUrl);
    }

    public int hashCode() {
        String str = this.fileFormat;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.heightInPixels;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.widthInPixels;
        return ((iHashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31) + this.imageUrl.hashCode();
    }

    public String toString() {
        return "FixedSizeImage(fileFormat=" + this.fileFormat + ", heightInPixels=" + this.heightInPixels + ", widthInPixels=" + this.widthInPixels + ", imageUrl=" + this.imageUrl + ')';
    }

    public FixedSizeImage(String str, Integer num, Integer num2, String imageUrl) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        this.fileFormat = str;
        this.heightInPixels = num;
        this.widthInPixels = num2;
        this.imageUrl = imageUrl;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ FixedSizeImage(String str, Integer num, Integer num2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, str2);
    }

    public final String getFileFormat() {
        return this.fileFormat;
    }

    public final Integer getHeightInPixels() {
        return this.heightInPixels;
    }

    public final Integer getWidthInPixels() {
        return this.widthInPixels;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }
}
