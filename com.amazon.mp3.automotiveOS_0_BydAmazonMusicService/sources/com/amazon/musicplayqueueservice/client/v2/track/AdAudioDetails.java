package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AdAudioDetails implements Comparable<AdAudioDetails> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AdAudioDetails");
    private double bitRate;
    private String fileFormat;
    private String streamingUrl;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AdAudioDetails adAudioDetails) {
        if (adAudioDetails == null) {
            return -1;
        }
        if (adAudioDetails == this) {
            return 0;
        }
        String streamingUrl = getStreamingUrl();
        String streamingUrl2 = adAudioDetails.getStreamingUrl();
        if (streamingUrl != streamingUrl2) {
            if (streamingUrl == null) {
                return -1;
            }
            if (streamingUrl2 == null) {
                return 1;
            }
            int iCompareTo = streamingUrl.compareTo(streamingUrl2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getBitRate() < adAudioDetails.getBitRate()) {
            return -1;
        }
        if (getBitRate() > adAudioDetails.getBitRate()) {
            return 1;
        }
        String fileFormat = getFileFormat();
        String fileFormat2 = adAudioDetails.getFileFormat();
        if (fileFormat != fileFormat2) {
            if (fileFormat == null) {
                return -1;
            }
            if (fileFormat2 == null) {
                return 1;
            }
            int iCompareTo2 = fileFormat.compareTo(fileFormat2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdAudioDetails)) {
            return false;
        }
        AdAudioDetails adAudioDetails = (AdAudioDetails) obj;
        return internalEqualityCheck(getStreamingUrl(), adAudioDetails.getStreamingUrl()) && internalEqualityCheck(Double.valueOf(getBitRate()), Double.valueOf(adAudioDetails.getBitRate())) && internalEqualityCheck(getFileFormat(), adAudioDetails.getFileFormat());
    }

    public double getBitRate() {
        return this.bitRate;
    }

    public String getFileFormat() {
        return this.fileFormat;
    }

    public String getStreamingUrl() {
        return this.streamingUrl;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getStreamingUrl(), Double.valueOf(getBitRate()), getFileFormat());
    }

    public void setBitRate(double d) {
        this.bitRate = d;
    }

    public void setFileFormat(String str) {
        this.fileFormat = str;
    }

    public void setStreamingUrl(String str) {
        this.streamingUrl = str;
    }
}
