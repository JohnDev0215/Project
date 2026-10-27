package android.media;

import android.graphics.ImageFormat;
import android.media.Image;
import android.util.Size;
import java.nio.ByteBuffer;
import libcore.io.Memory;

/* JADX INFO: loaded from: classes2.dex */
class ImageUtils {
    ImageUtils() {
    }

    public static int getNumPlanesForFormat(int i) {
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            if (i == 16) {
                return 2;
            }
            if (i != 17) {
                if (i != 256 && i != 257) {
                    switch (i) {
                        case 20:
                        case 32:
                        case 4098:
                        case ImageFormat.Y8 /* 538982489 */:
                        case ImageFormat.Y16 /* 540422489 */:
                        case ImageFormat.DEPTH16 /* 1144402265 */:
                        case ImageFormat.HEIC /* 1212500294 */:
                        case ImageFormat.DEPTH_JPEG /* 1768253795 */:
                            break;
                        case ImageFormat.YV12 /* 842094169 */:
                            break;
                        default:
                            switch (i) {
                                case 34:
                                    return 0;
                                case 35:
                                    break;
                                case 36:
                                case 37:
                                case 38:
                                    break;
                                default:
                                    throw new UnsupportedOperationException(String.format("Invalid format specified %d", Integer.valueOf(i)));
                            }
                            break;
                    }
                }
            }
            return 3;
        }
        return 1;
    }

    public static void imageCopy(Image image, Image image2) {
        int iRemaining;
        if (image == null || image2 == null) {
            throw new IllegalArgumentException("Images should be non-null");
        }
        if (image.getFormat() != image2.getFormat()) {
            throw new IllegalArgumentException("Src and dst images should have the same format");
        }
        if (image.getFormat() == 34 || image2.getFormat() == 34) {
            throw new IllegalArgumentException("PRIVATE format images are not copyable");
        }
        if (image.getFormat() == 36) {
            throw new IllegalArgumentException("Copy of RAW_OPAQUE format has not been implemented");
        }
        if (image.getFormat() == 4098) {
            throw new IllegalArgumentException("Copy of RAW_DEPTH format has not been implemented");
        }
        if (!(image2.getOwner() instanceof ImageWriter)) {
            throw new IllegalArgumentException("Destination image is not from ImageWriter. Only the images from ImageWriter are writable");
        }
        Size size = new Size(image.getWidth(), image.getHeight());
        Size size2 = new Size(image2.getWidth(), image2.getHeight());
        if (!size.equals(size2)) {
            throw new IllegalArgumentException("source image size " + size + " is different with destination image size " + size2);
        }
        Image.Plane[] planes = image.getPlanes();
        Image.Plane[] planes2 = image2.getPlanes();
        for (int i = 0; i < planes.length; i++) {
            int rowStride = planes[i].getRowStride();
            int rowStride2 = planes2[i].getRowStride();
            ByteBuffer buffer = planes[i].getBuffer();
            ByteBuffer buffer2 = planes2[i].getBuffer();
            if (!buffer.isDirect() || !buffer2.isDirect()) {
                throw new IllegalArgumentException("Source and destination ByteBuffers must be direct byteBuffer!");
            }
            if (planes[i].getPixelStride() != planes2[i].getPixelStride()) {
                throw new IllegalArgumentException("Source plane image pixel stride " + planes[i].getPixelStride() + " must be same as destination image pixel stride " + planes2[i].getPixelStride());
            }
            int iPosition = buffer.position();
            buffer.rewind();
            buffer2.rewind();
            if (rowStride == rowStride2) {
                buffer2.put(buffer);
            } else {
                int iPosition2 = buffer.position();
                int iPosition3 = buffer2.position();
                Size effectivePlaneSizeForImage = getEffectivePlaneSizeForImage(image, i);
                int width = effectivePlaneSizeForImage.getWidth() * planes[i].getPixelStride();
                for (int i2 = 0; i2 < effectivePlaneSizeForImage.getHeight(); i2++) {
                    if (i2 == effectivePlaneSizeForImage.getHeight() - 1 && width > (iRemaining = buffer.remaining() - iPosition2)) {
                        width = iRemaining;
                    }
                    directByteBufferCopy(buffer, iPosition2, buffer2, iPosition3, width);
                    iPosition2 += rowStride;
                    iPosition3 += rowStride2;
                }
            }
            buffer.position(iPosition);
            buffer2.rewind();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getEstimatedNativeAllocBytes(int r2, int r3, int r4, int r5) {
        /*
            r0 = 1
            if (r4 == r0) goto L4c
            r1 = 2
            if (r4 == r1) goto L4c
            r1 = 3
            if (r4 == r1) goto L49
            r1 = 4
            if (r4 == r1) goto L46
            r1 = 16
            if (r4 == r1) goto L46
            r1 = 17
            if (r4 == r1) goto L43
            r1 = 256(0x100, float:3.59E-43)
            if (r4 == r1) goto L3d
            r1 = 257(0x101, float:3.6E-43)
            if (r4 == r1) goto L3d
            switch(r4) {
                case 20: goto L46;
                case 32: goto L46;
                case 4098: goto L46;
                case 538982489: goto L3a;
                case 540422489: goto L46;
                case 842094169: goto L43;
                case 1144402265: goto L46;
                case 1212500294: goto L3d;
                case 1768253795: goto L3d;
                default: goto L1f;
            }
        L1f:
            switch(r4) {
                case 34: goto L43;
                case 35: goto L43;
                case 36: goto L46;
                case 37: goto L37;
                case 38: goto L43;
                default: goto L22;
            }
        L22:
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
            java.lang.Object[] r3 = new java.lang.Object[r0]
            r5 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r5] = r4
            java.lang.String r4 = "Invalid format specified %d"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.<init>(r3)
            throw r2
        L37:
            r0 = 4608308318706860032(0x3ff4000000000000, double:1.25)
            goto L4e
        L3a:
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L4e
        L3d:
            r0 = 4599075939470750515(0x3fd3333333333333, double:0.3)
            goto L4e
        L43:
            r0 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            goto L4e
        L46:
            r0 = 4611686018427387904(0x4000000000000000, double:2.0)
            goto L4e
        L49:
            r0 = 4613937818241073152(0x4008000000000000, double:3.0)
            goto L4e
        L4c:
            r0 = 4616189618054758400(0x4010000000000000, double:4.0)
        L4e:
            int r2 = r2 * r3
            double r2 = (double) r2
            double r2 = r2 * r0
            double r4 = (double) r5
            double r2 = r2 * r4
            int r2 = (int) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.ImageUtils.getEstimatedNativeAllocBytes(int, int, int, int):int");
    }

    private static Size getEffectivePlaneSizeForImage(Image image, int i) {
        int format = image.getFormat();
        if (format != 1 && format != 2 && format != 3 && format != 4) {
            if (format == 16) {
                if (i == 0) {
                    return new Size(image.getWidth(), image.getHeight());
                }
                return new Size(image.getWidth(), image.getHeight() / 2);
            }
            if (format != 17) {
                if (format == 34) {
                    return new Size(0, 0);
                }
                if (format != 35) {
                    if (format != 37 && format != 38) {
                        switch (format) {
                            case 20:
                            case 32:
                            case 256:
                            case 4098:
                            case ImageFormat.Y8 /* 538982489 */:
                            case ImageFormat.Y16 /* 540422489 */:
                            case ImageFormat.HEIC /* 1212500294 */:
                                break;
                            case ImageFormat.YV12 /* 842094169 */:
                                break;
                            default:
                                throw new UnsupportedOperationException(String.format("Invalid image format %d", Integer.valueOf(image.getFormat())));
                        }
                    }
                }
            }
            if (i == 0) {
                return new Size(image.getWidth(), image.getHeight());
            }
            return new Size(image.getWidth() / 2, image.getHeight() / 2);
        }
        return new Size(image.getWidth(), image.getHeight());
    }

    private static void directByteBufferCopy(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        Memory.memmove(byteBuffer2, i2, byteBuffer, i, i3);
    }
}
