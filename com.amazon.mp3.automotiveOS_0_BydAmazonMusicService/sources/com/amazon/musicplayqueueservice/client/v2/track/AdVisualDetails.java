package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AdVisualDetails implements Comparable<AdVisualDetails> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AdVisualDetails");
    private String adChoiceURL;
    private String clickThroughURL;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AdVisualDetails adVisualDetails) {
        if (adVisualDetails == null) {
            return -1;
        }
        if (adVisualDetails == this) {
            return 0;
        }
        String adChoiceURL = getAdChoiceURL();
        String adChoiceURL2 = adVisualDetails.getAdChoiceURL();
        if (adChoiceURL != adChoiceURL2) {
            if (adChoiceURL == null) {
                return -1;
            }
            if (adChoiceURL2 == null) {
                return 1;
            }
            int iCompareTo = adChoiceURL.compareTo(adChoiceURL2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String clickThroughURL = getClickThroughURL();
        String clickThroughURL2 = adVisualDetails.getClickThroughURL();
        if (clickThroughURL != clickThroughURL2) {
            if (clickThroughURL == null) {
                return -1;
            }
            if (clickThroughURL2 == null) {
                return 1;
            }
            int iCompareTo2 = clickThroughURL.compareTo(clickThroughURL2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String imageUrl = getImageUrl();
        String imageUrl2 = adVisualDetails.getImageUrl();
        if (imageUrl != imageUrl2) {
            if (imageUrl == null) {
                return -1;
            }
            if (imageUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = imageUrl.compareTo(imageUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String fileFormat = getFileFormat();
        String fileFormat2 = adVisualDetails.getFileFormat();
        if (fileFormat != fileFormat2) {
            if (fileFormat == null) {
                return -1;
            }
            if (fileFormat2 == null) {
                return 1;
            }
            int iCompareTo4 = fileFormat.compareTo(fileFormat2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        if (getWidthInPixels() < adVisualDetails.getWidthInPixels()) {
            return -1;
        }
        if (getWidthInPixels() > adVisualDetails.getWidthInPixels()) {
            return 1;
        }
        if (getHeightInPixels() < adVisualDetails.getHeightInPixels()) {
            return -1;
        }
        return getHeightInPixels() > adVisualDetails.getHeightInPixels() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdVisualDetails)) {
            return false;
        }
        AdVisualDetails adVisualDetails = (AdVisualDetails) obj;
        return internalEqualityCheck(getAdChoiceURL(), adVisualDetails.getAdChoiceURL()) && internalEqualityCheck(getClickThroughURL(), adVisualDetails.getClickThroughURL()) && internalEqualityCheck(getImageUrl(), adVisualDetails.getImageUrl()) && internalEqualityCheck(getFileFormat(), adVisualDetails.getFileFormat()) && internalEqualityCheck(Double.valueOf(getWidthInPixels()), Double.valueOf(adVisualDetails.getWidthInPixels())) && internalEqualityCheck(Double.valueOf(getHeightInPixels()), Double.valueOf(adVisualDetails.getHeightInPixels()));
    }

    public String getAdChoiceURL() {
        return this.adChoiceURL;
    }

    public String getClickThroughURL() {
        return this.clickThroughURL;
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

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAdChoiceURL(), getClickThroughURL(), getImageUrl(), getFileFormat(), Double.valueOf(getWidthInPixels()), Double.valueOf(getHeightInPixels()));
    }

    public void setAdChoiceURL(String str) {
        this.adChoiceURL = str;
    }

    public void setClickThroughURL(String str) {
        this.clickThroughURL = str;
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
