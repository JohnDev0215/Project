package com.android.bluetooth.avrcpcontroller;

import android.net.INetd;
import android.util.Log;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BipTransformation {
    public static final int CROP = 2;
    public static final int FILL = 1;
    public static final int STRETCH = 0;
    private static final String TAG = "avrcpcontroller.BipTransformation";
    public static final int UNKNOWN = -1;
    public final HashSet<Integer> mSupportedTransformations = new HashSet<>(3);

    public BipTransformation() {
    }

    public BipTransformation(String transformations) {
        if (transformations == null) {
            return;
        }
        String[] tokens = transformations.trim().toLowerCase().split(" ");
        for (String token : tokens) {
            byte b = -1;
            int iHashCode = token.hashCode();
            if (iHashCode != -1881872635) {
                if (iHashCode != 3062416) {
                    if (iHashCode == 3143043 && token.equals("fill")) {
                        b = 1;
                    }
                } else if (token.equals("crop")) {
                    b = 2;
                }
            } else if (token.equals("stretch")) {
                b = 0;
            }
            if (b == 0) {
                addTransformation(0);
            } else if (b == 1) {
                addTransformation(1);
            } else if (b == 2) {
                addTransformation(2);
            } else {
                Log.e(TAG, "Found unknown transformation '" + token + "'");
            }
        }
    }

    public BipTransformation(int transformation) {
        addTransformation(transformation);
    }

    public BipTransformation(int[] transformations) {
        for (int transformation : transformations) {
            addTransformation(transformation);
        }
    }

    public void addTransformation(int transformation) {
        if (!isValid(transformation)) {
            throw new IllegalArgumentException("Invalid transformation ID '" + transformation + "'");
        }
        this.mSupportedTransformations.add(Integer.valueOf(transformation));
    }

    public void removeTransformation(int transformation) {
        if (!isValid(transformation)) {
            throw new IllegalArgumentException("Invalid transformation ID '" + transformation + "'");
        }
        this.mSupportedTransformations.remove(Integer.valueOf(transformation));
    }

    private boolean isValid(int transformation) {
        return transformation >= 0 && transformation <= 2;
    }

    public boolean isSupported(int transformation) {
        return this.mSupportedTransformations.contains(Integer.valueOf(transformation));
    }

    public boolean supportsAny() {
        return !this.mSupportedTransformations.isEmpty();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null && !supportsAny()) {
            return true;
        }
        if (!(o instanceof BipTransformation)) {
            return false;
        }
        BipTransformation t = (BipTransformation) o;
        return this.mSupportedTransformations.equals(t.mSupportedTransformations);
    }

    public String toString() {
        if (!supportsAny()) {
            return null;
        }
        String transformations = INetd.NEXTHOP_NONE;
        if (isSupported(0)) {
            transformations = INetd.NEXTHOP_NONE + "stretch ";
        }
        if (isSupported(1)) {
            transformations = transformations + "fill ";
        }
        if (isSupported(2)) {
            transformations = transformations + "crop ";
        }
        return transformations.trim();
    }
}
