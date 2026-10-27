package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class FixedSizeImage extends ImageMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.FixedSizeImage");
    private String fileFormat;
    private double heightInPixels;
    private String imageUrl;
    private double widthInPixels;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ImageMetadataBase imageMetadataBase) {
        if (imageMetadataBase == null) {
            return -1;
        }
        if (imageMetadataBase == this) {
            return 0;
        }
        if (!(imageMetadataBase instanceof FixedSizeImage)) {
            return 1;
        }
        FixedSizeImage fixedSizeImage = (FixedSizeImage) imageMetadataBase;
        String fileFormat = getFileFormat();
        String fileFormat2 = fixedSizeImage.getFileFormat();
        if (fileFormat != fileFormat2) {
            if (fileFormat == null) {
                return -1;
            }
            if (fileFormat2 == null) {
                return 1;
            }
            int iCompareTo = fileFormat.compareTo(fileFormat2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getHeightInPixels() < fixedSizeImage.getHeightInPixels()) {
            return -1;
        }
        if (getHeightInPixels() > fixedSizeImage.getHeightInPixels()) {
            return 1;
        }
        if (getWidthInPixels() < fixedSizeImage.getWidthInPixels()) {
            return -1;
        }
        if (getWidthInPixels() > fixedSizeImage.getWidthInPixels()) {
            return 1;
        }
        String imageUrl = getImageUrl();
        String imageUrl2 = fixedSizeImage.getImageUrl();
        if (imageUrl != imageUrl2) {
            if (imageUrl == null) {
                return -1;
            }
            if (imageUrl2 == null) {
                return 1;
            }
            int iCompareTo2 = imageUrl.compareTo(imageUrl2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(imageMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase
    public boolean equals(Object obj) {
        if (!(obj instanceof FixedSizeImage)) {
            return false;
        }
        FixedSizeImage fixedSizeImage = (FixedSizeImage) obj;
        return super.equals(obj) && internalEqualityCheck(getFileFormat(), fixedSizeImage.getFileFormat()) && internalEqualityCheck(Double.valueOf(getHeightInPixels()), Double.valueOf(fixedSizeImage.getHeightInPixels())) && internalEqualityCheck(Double.valueOf(getWidthInPixels()), Double.valueOf(fixedSizeImage.getWidthInPixels())) && internalEqualityCheck(getImageUrl(), fixedSizeImage.getImageUrl());
    }

    public String getFileFormat() {
        return this.fileFormat;
    }

    public double getHeightInPixels() {
        return this.heightInPixels;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public double getWidthInPixels() {
        return this.widthInPixels;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.ImageMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getFileFormat(), Double.valueOf(getHeightInPixels()), Double.valueOf(getWidthInPixels()), getImageUrl());
    }

    public void setFileFormat(String str) {
        this.fileFormat = str;
    }

    public void setHeightInPixels(double d) {
        this.heightInPixels = d;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setWidthInPixels(double d) {
        this.widthInPixels = d;
    }
}
