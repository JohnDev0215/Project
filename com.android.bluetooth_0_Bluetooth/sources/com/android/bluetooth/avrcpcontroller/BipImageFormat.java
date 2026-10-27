package com.android.bluetooth.avrcpcontroller;

import android.net.INetd;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BipImageFormat {
    public static final int FORMAT_NATIVE = 0;
    public static final int FORMAT_VARIANT = 1;
    private static final String TAG = "avrcpcontroller.BipImageFormat";
    private final BipEncoding mEncoding;
    private final int mFormatType;
    private final int mMaxSize;
    private final BipPixel mPixel;
    private final int mSize;
    private final BipTransformation mTransformation;

    public static BipImageFormat parseNative(String encoding, String pixel, String size) {
        return new BipImageFormat(0, encoding, pixel, size, (String) null, (String) null);
    }

    public static BipImageFormat parseVariant(String encoding, String pixel, String maxSize, String transformation) {
        return new BipImageFormat(1, encoding, pixel, (String) null, maxSize, transformation);
    }

    public static BipImageFormat createNative(BipEncoding encoding, BipPixel pixel, int size) {
        return new BipImageFormat(0, encoding, pixel, size, -1, (BipTransformation) null);
    }

    public static BipImageFormat createVariant(BipEncoding encoding, BipPixel pixel, int maxSize, BipTransformation transformation) {
        return new BipImageFormat(1, encoding, pixel, -1, maxSize, transformation);
    }

    private BipImageFormat(int type, BipEncoding encoding, BipPixel pixel, int size, int maxSize, BipTransformation transformation) {
        this.mFormatType = type;
        Objects.requireNonNull(encoding, "Encoding cannot be null");
        this.mEncoding = encoding;
        Objects.requireNonNull(pixel, "Pixel cannot be null");
        this.mPixel = pixel;
        this.mTransformation = transformation;
        this.mSize = size;
        this.mMaxSize = maxSize;
    }

    private BipImageFormat(int type, String encoding, String pixel, String size, String maxSize, String transformation) {
        this.mFormatType = type;
        this.mEncoding = new BipEncoding(encoding);
        this.mPixel = new BipPixel(pixel);
        this.mTransformation = new BipTransformation(transformation);
        this.mSize = parseInt(size);
        this.mMaxSize = parseInt(maxSize);
    }

    private static int parseInt(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            error("Failed to parse '" + s + "'");
            return -1;
        }
    }

    public int getType() {
        return this.mFormatType;
    }

    public BipEncoding getEncoding() {
        return this.mEncoding;
    }

    public BipPixel getPixel() {
        return this.mPixel;
    }

    public BipTransformation getTransformation() {
        return this.mTransformation;
    }

    public int getSize() {
        return this.mSize;
    }

    public int getMaxSize() {
        return this.mMaxSize;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipImageFormat)) {
            return false;
        }
        BipImageFormat f = (BipImageFormat) o;
        return f.getType() == getType() && f.getEncoding() == getEncoding() && f.getPixel() == getPixel() && f.getTransformation() == getTransformation() && f.getSize() == getSize() && f.getMaxSize() == getMaxSize();
    }

    public String toString() {
        BipPixel bipPixel;
        BipEncoding bipEncoding = this.mEncoding;
        if (bipEncoding == null || bipEncoding.getType() == 7 || (bipPixel = this.mPixel) == null || bipPixel.getType() == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Missing required fields [ ");
            BipEncoding bipEncoding2 = this.mEncoding;
            String str = INetd.NEXTHOP_NONE;
            sb.append(bipEncoding2 == null ? "encoding " : INetd.NEXTHOP_NONE);
            if (this.mPixel == null) {
                str = "pixel ";
            }
            sb.append(str);
            error(sb.toString());
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        int i = this.mFormatType;
        if (i == 0) {
            sb2.append("<native");
            sb2.append(" encoding=\"" + this.mEncoding.toString() + "\"");
            sb2.append(" pixel=\"" + this.mPixel.toString() + "\"");
            if (this.mSize > -1) {
                sb2.append(" size=\"" + this.mSize + "\"");
            }
            sb2.append(" />");
            return sb2.toString();
        }
        if (i == 1) {
            sb2.append("<variant");
            sb2.append(" encoding=\"" + this.mEncoding.toString() + "\"");
            sb2.append(" pixel=\"" + this.mPixel.toString() + "\"");
            BipTransformation bipTransformation = this.mTransformation;
            if (bipTransformation != null && bipTransformation.supportsAny()) {
                sb2.append(" transformation=\"" + this.mTransformation.toString() + "\"");
            }
            if (this.mSize > -1) {
                sb2.append(" size=\"" + this.mSize + "\"");
            }
            if (this.mMaxSize > -1) {
                sb2.append(" maxsize=\"" + this.mMaxSize + "\"");
            }
            sb2.append(" />");
            return sb2.toString();
        }
        error("Unsupported format type '" + this.mFormatType + "'");
        return null;
    }

    private static void error(String msg) {
        Log.e(TAG, msg);
    }
}
