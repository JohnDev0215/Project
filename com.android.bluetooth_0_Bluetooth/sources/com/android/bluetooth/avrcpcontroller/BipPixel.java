package com.android.bluetooth.avrcpcontroller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class BipPixel {
    public static final int PIXEL_MAX = 65535;
    private static final String TAG = "avrcpcontroller.BipPixel";
    public static final int TYPE_FIXED = 1;
    public static final int TYPE_RESIZE_FIXED_ASPECT_RATIO = 3;
    public static final int TYPE_RESIZE_MODIFIED_ASPECT_RATIO = 2;
    public static final int TYPE_UNKNOWN = 0;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final int mMinHeight;
    private final int mMinWidth;
    private final int mType;

    public static BipPixel createFixed(int width, int height) {
        return new BipPixel(1, width, height, width, height);
    }

    public static BipPixel createResizableModified(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        return new BipPixel(2, minWidth, minHeight, maxWidth, maxHeight);
    }

    public static BipPixel createResizableFixed(int minWidth, int maxWidth, int maxHeight) {
        int minHeight = (minWidth * maxHeight) / maxWidth;
        return new BipPixel(3, minWidth, minHeight, maxWidth, maxHeight);
    }

    private BipPixel(int type, int minWidth, int minHeight, int maxWidth, int maxHeight) {
        if (isDimensionInvalid(minWidth) || isDimensionInvalid(maxWidth) || isDimensionInvalid(minHeight) || isDimensionInvalid(maxHeight)) {
            throw new IllegalArgumentException("Dimension's must be in [0, 65535]");
        }
        this.mType = type;
        this.mMinWidth = minWidth;
        this.mMinHeight = minHeight;
        this.mMaxWidth = maxWidth;
        this.mMaxHeight = maxHeight;
    }

    public BipPixel(String pixel) {
        int type = 0;
        int minWidth = -1;
        int minHeight = -1;
        int maxWidth = -1;
        int maxHeight = -1;
        int typeHint = determinePixelType(pixel);
        if (typeHint != 1) {
            if (typeHint == 2) {
                Pattern modifiedRatio = Pattern.compile("^(\\d{1,5})\\*(\\d{1,5})-(\\d{1,5})\\*(\\d{1,5})$");
                Matcher m2 = modifiedRatio.matcher(pixel);
                if (m2.matches()) {
                    type = 2;
                    minWidth = Integer.parseInt(m2.group(1));
                    minHeight = Integer.parseInt(m2.group(2));
                    maxWidth = Integer.parseInt(m2.group(3));
                    maxHeight = Integer.parseInt(m2.group(4));
                }
            } else if (typeHint == 3) {
                Pattern fixedRatio = Pattern.compile("^(\\d{1,5})\\*\\*-(\\d{1,5})\\*(\\d{1,5})$");
                Matcher m3 = fixedRatio.matcher(pixel);
                if (m3.matches()) {
                    type = 3;
                    minWidth = Integer.parseInt(m3.group(1));
                    maxWidth = Integer.parseInt(m3.group(2));
                    maxHeight = Integer.parseInt(m3.group(3));
                    minHeight = (minWidth * maxHeight) / maxWidth;
                }
            }
        } else {
            Pattern fixed = Pattern.compile("^(\\d{1,5})\\*(\\d{1,5})$");
            Matcher m1 = fixed.matcher(pixel);
            if (m1.matches()) {
                type = 1;
                minWidth = Integer.parseInt(m1.group(1));
                maxWidth = Integer.parseInt(m1.group(1));
                minHeight = Integer.parseInt(m1.group(2));
                maxHeight = Integer.parseInt(m1.group(2));
            }
        }
        if (type == 0) {
            throw new ParseException("Failed to determine type of '" + pixel + "'");
        }
        if (isDimensionInvalid(minWidth) || isDimensionInvalid(maxWidth) || isDimensionInvalid(minHeight) || isDimensionInvalid(maxHeight)) {
            throw new ParseException("Parsed dimensions must be in [0, 65535]");
        }
        this.mType = type;
        this.mMinWidth = minWidth;
        this.mMinHeight = minHeight;
        this.mMaxWidth = maxWidth;
        this.mMaxHeight = maxHeight;
    }

    public int getType() {
        return this.mType;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private static int determinePixelType(String pixel) {
        if (pixel == null || pixel.length() > 23) {
            return 0;
        }
        int delimCount = 0;
        for (char c : pixel.toCharArray()) {
            if (c == '*') {
                delimCount++;
            }
        }
        if (delimCount <= 0 || delimCount > 3) {
            return 0;
        }
        return delimCount;
    }

    protected static boolean isDimensionInvalid(int dimension) {
        return dimension < 0 || dimension > 65535;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipPixel)) {
            return false;
        }
        BipPixel p = (BipPixel) o;
        return p.getType() == getType() && p.getMinWidth() == getMinWidth() && p.getMaxWidth() == getMaxWidth() && p.getMinHeight() == getMinHeight() && p.getMaxHeight() == getMaxHeight();
    }

    public String toString() {
        int i = this.mType;
        if (i == 1) {
            String s = this.mMaxWidth + "*" + this.mMaxHeight;
            return s;
        }
        if (i != 2) {
            if (i != 3) {
                return null;
            }
            String s2 = this.mMinWidth + "**-" + this.mMaxWidth + "*" + this.mMaxHeight;
            return s2;
        }
        String s3 = this.mMinWidth + "*" + this.mMinHeight + "-" + this.mMaxWidth + "*" + this.mMaxHeight;
        return s3;
    }
}
