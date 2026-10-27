package com.visualon.OSMPUtils;

import android.annotation.TargetApi;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes7.dex */
public final class VOSupportEncoding {
    private static final String AC3 = "AC3";
    private static final String EAC3 = "EAC3";
    private static final String RAW = "OMX.google.raw.decoder";
    private final int isPlugged;
    private final int[] mCapableEncodings;

    public VOSupportEncoding(int[] iArr, int i) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.mCapableEncodings = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
        } else {
            this.mCapableEncodings = new int[0];
        }
        this.isPlugged = i;
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.mCapableEncodings, i) >= 0;
    }

    @TargetApi(21)
    public HashMap<Integer, String> getCapableEncodings() {
        HashMap<Integer, String> map = new HashMap<>();
        if (supportsEncoding(6)) {
            map.put(6, RAW);
        }
        if (supportsEncoding(5)) {
            map.put(5, RAW);
        }
        return map;
    }

    public int getIsPlugged() {
        return this.isPlugged;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VOSupportEncoding)) {
            return false;
        }
        VOSupportEncoding vOSupportEncoding = (VOSupportEncoding) obj;
        return Arrays.equals(this.mCapableEncodings, vOSupportEncoding.mCapableEncodings) && this.isPlugged == vOSupportEncoding.isPlugged;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mCapableEncodings) * 33;
    }

    public String toString() {
        return "SupportEncoding : capable_encodings =" + Arrays.toString(this.mCapableEncodings) + ", isPlugged=" + this.isPlugged + "]";
    }
}
