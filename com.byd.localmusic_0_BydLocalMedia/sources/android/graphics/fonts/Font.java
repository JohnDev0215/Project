package android.graphics.fonts;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.LocaleList;
import android.os.ParcelFileDescriptor;
import android.util.TypedValue;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Objects;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public final class Font {
    private static final int NOT_SPECIFIED = -1;
    private static final int STYLE_ITALIC = 1;
    private static final int STYLE_NORMAL = 0;
    private static final String TAG = "Font";
    private final FontVariationAxis[] mAxes;
    private final ByteBuffer mBuffer;
    private final File mFile;
    private final FontStyle mFontStyle;
    private final String mLocaleList;
    private final long mNativePtr;
    private final int mTtcIndex;

    public static final class Builder {
        private static final NativeAllocationRegistry sFontRegistry = NativeAllocationRegistry.createMalloced(Font.class.getClassLoader(), nGetReleaseNativeFont());
        private FontVariationAxis[] mAxes;
        private ByteBuffer mBuffer;
        private IOException mException;
        private File mFile;
        private int mItalic;
        private String mLocaleList;
        private int mTtcIndex;
        private int mWeight;

        @CriticalNative
        private static native void nAddAxis(long j, int i, float f);

        private static native long nBuild(long j, ByteBuffer byteBuffer, String str, int i, boolean z, int i2);

        @CriticalNative
        private static native long nGetReleaseNativeFont();

        private static native long nInitBuilder();

        public Builder(ByteBuffer byteBuffer) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            Preconditions.checkNotNull(byteBuffer, "buffer can not be null");
            if (!byteBuffer.isDirect()) {
                throw new IllegalArgumentException("Only direct buffer can be used as the source of font data.");
            }
            this.mBuffer = byteBuffer;
        }

        public Builder(ByteBuffer byteBuffer, File file, String str) {
            this(byteBuffer);
            this.mFile = file;
            this.mLocaleList = str;
        }

        public Builder(File file) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            Preconditions.checkNotNull(file, "path can not be null");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    this.mBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                } finally {
                }
            } catch (IOException e) {
                this.mException = e;
            }
            this.mFile = file;
        }

        public Builder(ParcelFileDescriptor parcelFileDescriptor) {
            this(parcelFileDescriptor, 0L, -1L);
        }

        public Builder(ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    this.mBuffer = channel.map(FileChannel.MapMode.READ_ONLY, j, j2 == -1 ? channel.size() - j : j2);
                    fileInputStream.close();
                } finally {
                }
            } catch (IOException e) {
                this.mException = e;
            }
        }

        public Builder(AssetManager assetManager, String str) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            try {
                this.mBuffer = createBuffer(assetManager, str, true, 0);
            } catch (IOException e) {
                this.mException = e;
            }
        }

        public Builder(AssetManager assetManager, String str, boolean z, int i) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            try {
                this.mBuffer = createBuffer(assetManager, str, z, i);
            } catch (IOException e) {
                this.mException = e;
            }
        }

        public Builder(Resources resources, int i) {
            this.mLocaleList = "";
            this.mWeight = -1;
            this.mItalic = -1;
            this.mTtcIndex = 0;
            this.mAxes = null;
            TypedValue typedValue = new TypedValue();
            resources.getValue(i, typedValue, true);
            if (typedValue.string == null) {
                this.mException = new FileNotFoundException(i + " not found");
                return;
            }
            String string = typedValue.string.toString();
            if (string.toLowerCase().endsWith(".xml")) {
                this.mException = new FileNotFoundException(i + " must be font file.");
                return;
            }
            try {
                this.mBuffer = createBuffer(resources.getAssets(), string, false, typedValue.assetCookie);
            } catch (IOException e) {
                this.mException = e;
            }
        }

        public static ByteBuffer createBuffer(AssetManager assetManager, String str, boolean z, int i) throws IOException {
            InputStream inputStreamOpenNonAsset;
            AssetFileDescriptor assetFileDescriptorOpenNonAssetFd;
            Preconditions.checkNotNull(assetManager, "assetManager can not be null");
            Preconditions.checkNotNull(str, "path can not be null");
            try {
                if (z) {
                    assetFileDescriptorOpenNonAssetFd = assetManager.openFd(str);
                } else if (i > 0) {
                    assetFileDescriptorOpenNonAssetFd = assetManager.openNonAssetFd(i, str);
                } else {
                    assetFileDescriptorOpenNonAssetFd = assetManager.openNonAssetFd(str);
                }
                FileInputStream fileInputStreamCreateInputStream = assetFileDescriptorOpenNonAssetFd.createInputStream();
                try {
                    MappedByteBuffer map = fileInputStreamCreateInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenNonAssetFd.getStartOffset(), assetFileDescriptorOpenNonAssetFd.getDeclaredLength());
                    if (fileInputStreamCreateInputStream != null) {
                        fileInputStreamCreateInputStream.close();
                    }
                    return map;
                } finally {
                }
            } catch (IOException unused) {
                if (z) {
                    inputStreamOpenNonAsset = assetManager.open(str, 3);
                } else {
                    inputStreamOpenNonAsset = assetManager.openNonAsset(i, str, 3);
                }
                try {
                    ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(inputStreamOpenNonAsset.available());
                    byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
                    inputStreamOpenNonAsset.read(byteBufferAllocateDirect.array(), byteBufferAllocateDirect.arrayOffset(), inputStreamOpenNonAsset.available());
                    if (inputStreamOpenNonAsset.read() == -1) {
                        if (inputStreamOpenNonAsset != null) {
                            inputStreamOpenNonAsset.close();
                        }
                        return byteBufferAllocateDirect;
                    }
                    throw new IOException("Unable to access full contents of " + str);
                } catch (Throwable th) {
                    if (inputStreamOpenNonAsset != null) {
                        try {
                            inputStreamOpenNonAsset.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
        }

        public Builder setWeight(int i) {
            Preconditions.checkArgument(1 <= i && i <= 1000);
            this.mWeight = i;
            return this;
        }

        public Builder setSlant(int i) {
            this.mItalic = i == 0 ? 0 : 1;
            return this;
        }

        public Builder setTtcIndex(int i) {
            this.mTtcIndex = i;
            return this;
        }

        public Builder setFontVariationSettings(String str) {
            this.mAxes = FontVariationAxis.fromFontVariationSettings(str);
            return this;
        }

        public Builder setFontVariationSettings(FontVariationAxis[] fontVariationAxisArr) {
            this.mAxes = fontVariationAxisArr == null ? null : (FontVariationAxis[]) fontVariationAxisArr.clone();
            return this;
        }

        public Font build() throws IOException {
            if (this.mException != null) {
                throw new IOException("Failed to read font contents", this.mException);
            }
            if (this.mWeight == -1 || this.mItalic == -1) {
                int iAnalyzeStyle = FontFileUtil.analyzeStyle(this.mBuffer, this.mTtcIndex, this.mAxes);
                if (FontFileUtil.isSuccess(iAnalyzeStyle)) {
                    if (this.mWeight == -1) {
                        this.mWeight = FontFileUtil.unpackWeight(iAnalyzeStyle);
                    }
                    if (this.mItalic == -1) {
                        this.mItalic = FontFileUtil.unpackItalic(iAnalyzeStyle) ? 1 : 0;
                    }
                } else {
                    this.mWeight = 400;
                    this.mItalic = 0;
                }
            }
            this.mWeight = Math.max(1, Math.min(1000, this.mWeight));
            boolean z = this.mItalic == 1;
            int i = this.mItalic != 1 ? 0 : 1;
            long jNInitBuilder = nInitBuilder();
            FontVariationAxis[] fontVariationAxisArr = this.mAxes;
            if (fontVariationAxisArr != null) {
                for (FontVariationAxis fontVariationAxis : fontVariationAxisArr) {
                    nAddAxis(jNInitBuilder, fontVariationAxis.getOpenTypeTagValue(), fontVariationAxis.getStyleValue());
                }
            }
            ByteBuffer byteBufferAsReadOnlyBuffer = this.mBuffer.asReadOnlyBuffer();
            File file = this.mFile;
            long jNBuild = nBuild(jNInitBuilder, byteBufferAsReadOnlyBuffer, file == null ? "" : file.getAbsolutePath(), this.mWeight, z, this.mTtcIndex);
            Font font = new Font(jNBuild, byteBufferAsReadOnlyBuffer, this.mFile, new FontStyle(this.mWeight, i), this.mTtcIndex, this.mAxes, this.mLocaleList);
            sFontRegistry.registerNativeAllocation(font, jNBuild);
            return font;
        }
    }

    private Font(long j, ByteBuffer byteBuffer, File file, FontStyle fontStyle, int i, FontVariationAxis[] fontVariationAxisArr, String str) {
        this.mBuffer = byteBuffer;
        this.mFile = file;
        this.mFontStyle = fontStyle;
        this.mNativePtr = j;
        this.mTtcIndex = i;
        this.mAxes = fontVariationAxisArr;
        this.mLocaleList = str;
    }

    public ByteBuffer getBuffer() {
        return this.mBuffer;
    }

    public File getFile() {
        return this.mFile;
    }

    public FontStyle getStyle() {
        return this.mFontStyle;
    }

    public int getTtcIndex() {
        return this.mTtcIndex;
    }

    public FontVariationAxis[] getAxes() {
        FontVariationAxis[] fontVariationAxisArr = this.mAxes;
        if (fontVariationAxisArr == null) {
            return null;
        }
        return (FontVariationAxis[]) fontVariationAxisArr.clone();
    }

    public LocaleList getLocaleList() {
        return LocaleList.forLanguageTags(this.mLocaleList);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Font)) {
            return false;
        }
        Font font = (Font) obj;
        return this.mFontStyle.equals(font.mFontStyle) && font.mTtcIndex == this.mTtcIndex && Arrays.equals(font.mAxes, this.mAxes) && font.mBuffer.equals(this.mBuffer) && Objects.equals(font.mLocaleList, this.mLocaleList);
    }

    public int hashCode() {
        return Objects.hash(this.mFontStyle, Integer.valueOf(this.mTtcIndex), Integer.valueOf(Arrays.hashCode(this.mAxes)), this.mBuffer, this.mLocaleList);
    }

    public String toString() {
        return "Font {path=" + this.mFile + ", style=" + this.mFontStyle + ", ttcIndex=" + this.mTtcIndex + ", axes=" + FontVariationAxis.toFontVariationSettings(this.mAxes) + ", localeList=" + this.mLocaleList + ", buffer=" + this.mBuffer + "}";
    }
}
