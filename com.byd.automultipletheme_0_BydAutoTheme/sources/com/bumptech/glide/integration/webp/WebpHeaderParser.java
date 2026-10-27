package com.bumptech.glide.integration.webp;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.MotionEventCompat;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class WebpHeaderParser {
    public static final int MAX_WEBP_HEADER_SIZE = 21;
    private static final int RIFF_HEADER = 1380533830;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final int WEBP_EXTENDED_ALPHA_FLAG = 16;
    private static final int WEBP_EXTENDED_ANIM_FLAG = 2;
    private static final int WEBP_HEADER = 1464156752;
    private static final int WEBP_LOSSLESS_ALPHA_FLAG = 8;
    private static final int WEBP_VP8L_HEADER = 1448097868;
    private static final int WEBP_VP8X_HEADER = 1448097880;
    private static final int WEBP_VP8_HEADER = 1448097824;
    public static final boolean sIsExtendedWebpSupported = isExtendedWebpSupported();

    private interface Reader {
        int getByte() throws IOException;

        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    public static boolean isExtendedWebpSupported() {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (Build.VERSION.SDK_INT == 17) {
            byte[] bArrDecode = Base64.decode(VP8X_WEBP_BASE64, 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
            if (options.outHeight != 1 || options.outWidth != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStaticWebpType(WebpImageType webpImageType) {
        return webpImageType == WebpImageType.WEBP_SIMPLE || webpImageType == WebpImageType.WEBP_LOSSLESS || webpImageType == WebpImageType.WEBP_LOSSLESS_WITH_ALPHA || webpImageType == WebpImageType.WEBP_EXTENDED || webpImageType == WebpImageType.WEBP_EXTENDED_WITH_ALPHA;
    }

    public static boolean isNonSimpleWebpType(WebpImageType webpImageType) {
        return (webpImageType == WebpImageType.NONE_WEBP || webpImageType == WebpImageType.WEBP_SIMPLE) ? false : true;
    }

    public static boolean isAnimatedWebpType(WebpImageType webpImageType) {
        return webpImageType == WebpImageType.WEBP_EXTENDED_ANIMATED;
    }

    public static WebpImageType getType(InputStream inputStream, ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return WebpImageType.NONE_WEBP;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(21);
        try {
            return getType(new StreamReader((InputStream) Preconditions.checkNotNull(inputStream)));
        } finally {
            inputStream.reset();
        }
    }

    public static WebpImageType getType(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            return WebpImageType.NONE_WEBP;
        }
        return getType(new ByteBufferReader((ByteBuffer) Preconditions.checkNotNull(byteBuffer)));
    }

    public static WebpImageType getType(byte[] bArr) throws IOException {
        return getType(bArr, 0, bArr.length);
    }

    public static WebpImageType getType(byte[] bArr, int i, int i2) throws IOException {
        return getType(new ByteArrayReader(bArr, i, i2));
    }

    private static WebpImageType getType(Reader reader) throws IOException {
        if ((((reader.getUInt16() << 16) & SupportMenu.CATEGORY_MASK) | (reader.getUInt16() & 65535)) != RIFF_HEADER) {
            return WebpImageType.NONE_WEBP;
        }
        reader.skip(4L);
        if ((((reader.getUInt16() << 16) & SupportMenu.CATEGORY_MASK) | (reader.getUInt16() & 65535)) != WEBP_HEADER) {
            return WebpImageType.NONE_WEBP;
        }
        int uInt16 = ((reader.getUInt16() << 16) & SupportMenu.CATEGORY_MASK) | (reader.getUInt16() & 65535);
        if (uInt16 == WEBP_VP8_HEADER) {
            return WebpImageType.WEBP_SIMPLE;
        }
        if (uInt16 == WEBP_VP8L_HEADER) {
            reader.skip(4L);
            return (reader.getByte() & 8) != 0 ? WebpImageType.WEBP_LOSSLESS_WITH_ALPHA : WebpImageType.WEBP_LOSSLESS;
        }
        if (uInt16 == WEBP_VP8X_HEADER) {
            reader.skip(4L);
            int i = reader.getByte();
            if ((i & 2) != 0) {
                return WebpImageType.WEBP_EXTENDED_ANIMATED;
            }
            if ((i & 16) != 0) {
                return WebpImageType.WEBP_EXTENDED_WITH_ALPHA;
            }
            return WebpImageType.WEBP_EXTENDED;
        }
        return WebpImageType.NONE_WEBP;
    }

    public enum WebpImageType {
        WEBP_SIMPLE(false, false),
        WEBP_LOSSLESS(false, false),
        WEBP_LOSSLESS_WITH_ALPHA(true, false),
        WEBP_EXTENDED(false, false),
        WEBP_EXTENDED_WITH_ALPHA(true, false),
        WEBP_EXTENDED_ANIMATED(false, true),
        NONE_WEBP(false, false);

        private final boolean hasAlpha;
        private final boolean hasAnimation;

        WebpImageType(boolean z, boolean z2) {
            this.hasAlpha = z;
            this.hasAnimation = z2;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }

        public boolean hasAnimation() {
            return this.hasAnimation;
        }
    }

    private static final class ByteArrayReader implements Reader {
        private final byte[] data;
        private final int offset;
        private int pos;
        private final int size;

        ByteArrayReader(byte[] bArr, int i, int i2) {
            this.data = bArr;
            this.offset = i;
            this.size = i2;
            this.pos = i;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getUInt16() throws IOException {
            return ((getByte() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (getByte() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public short getUInt8() throws IOException {
            return (short) (getByte() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public long skip(long j) throws IOException {
            int iMin = (int) Math.min((this.offset + this.size) - this.pos, j);
            this.pos += iMin;
            return iMin;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int read(byte[] bArr, int i) throws IOException {
            int iMin = Math.min((this.offset + this.size) - this.pos, i);
            if (iMin == 0) {
                return -1;
            }
            System.arraycopy(this.data, this.pos, bArr, 0, iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getByte() throws IOException {
            int i = this.pos;
            if (i >= this.offset + this.size) {
                return -1;
            }
            byte[] bArr = this.data;
            this.pos = i + 1;
            return bArr[i];
        }
    }

    private static final class ByteBufferReader implements Reader {
        private final ByteBuffer byteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getUInt16() throws IOException {
            return ((getByte() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (getByte() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public short getUInt8() throws IOException {
            return (short) (getByte() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public long skip(long j) throws IOException {
            int iMin = (int) Math.min(this.byteBuffer.remaining(), j);
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int read(byte[] bArr, int i) throws IOException {
            int iMin = Math.min(i, this.byteBuffer.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.byteBuffer.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getByte() throws IOException {
            if (this.byteBuffer.remaining() < 1) {
                return -1;
            }
            return this.byteBuffer.get();
        }
    }

    private static final class StreamReader implements Reader {
        private final InputStream is;

        StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getUInt16() throws IOException {
            return ((this.is.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.is.read() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public short getUInt8() throws IOException {
            return (short) (this.is.read() & 255);
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.is.skip(j2);
                if (jSkip <= 0) {
                    if (this.is.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 -= jSkip;
            }
            return j - j2;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int read(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int i3 = this.is.read(bArr, i - i2, i2);
                if (i3 == -1) {
                    break;
                }
                i2 -= i3;
            }
            return i - i2;
        }

        @Override // com.bumptech.glide.integration.webp.WebpHeaderParser.Reader
        public int getByte() throws IOException {
            return this.is.read();
        }
    }
}
