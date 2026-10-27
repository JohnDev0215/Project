package android.graphics;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Size;
import android.util.TypedValue;
import com.google.android.mms.ContentType;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ImageDecoder implements AutoCloseable {
    public static final int ALLOCATOR_DEFAULT = 0;
    public static final int ALLOCATOR_HARDWARE = 3;
    public static final int ALLOCATOR_SHARED_MEMORY = 2;
    public static final int ALLOCATOR_SOFTWARE = 1;

    @Deprecated
    public static final int ERROR_SOURCE_ERROR = 3;

    @Deprecated
    public static final int ERROR_SOURCE_EXCEPTION = 1;

    @Deprecated
    public static final int ERROR_SOURCE_INCOMPLETE = 2;
    public static final int MEMORY_POLICY_DEFAULT = 1;
    public static final int MEMORY_POLICY_LOW_RAM = 0;
    public static int sApiLevel;
    private final boolean mAnimated;
    private AssetFileDescriptor mAssetFd;
    private final CloseGuard mCloseGuard;
    private Rect mCropRect;
    private int mDesiredHeight;
    private int mDesiredWidth;
    private final int mHeight;
    private InputStream mInputStream;
    private final boolean mIsNinePatch;
    private long mNativePtr;
    private OnPartialImageListener mOnPartialImageListener;
    private Rect mOutPaddingRect;
    private boolean mOwnsInputStream;
    private PostProcessor mPostProcessor;
    private Source mSource;
    private byte[] mTempStorage;
    private final int mWidth;
    private int mAllocator = 0;
    private boolean mUnpremultipliedRequired = false;
    private boolean mMutable = false;
    private boolean mConserveMemory = false;
    private boolean mDecodeAsAlphaMask = false;
    private ColorSpace mDesiredColorSpace = null;
    private final AtomicBoolean mClosed = new AtomicBoolean();

    @Retention(RetentionPolicy.SOURCE)
    public @interface Allocator {
    }

    @Deprecated
    public static class IncompleteException extends IOException {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MemoryPolicy {
    }

    public interface OnHeaderDecodedListener {
        void onHeaderDecoded(ImageDecoder imageDecoder, ImageInfo imageInfo, Source source);
    }

    public interface OnPartialImageListener {
        boolean onPartialImage(DecodeException decodeException);
    }

    private static native void nClose(long j);

    private static native ImageDecoder nCreate(long j, boolean z, Source source) throws IOException;

    private static native ImageDecoder nCreate(FileDescriptor fileDescriptor, boolean z, Source source) throws IOException;

    private static native ImageDecoder nCreate(InputStream inputStream, byte[] bArr, boolean z, Source source) throws IOException;

    /* JADX INFO: Access modifiers changed from: private */
    public static native ImageDecoder nCreate(ByteBuffer byteBuffer, int i, int i2, boolean z, Source source) throws IOException;

    /* JADX INFO: Access modifiers changed from: private */
    public static native ImageDecoder nCreate(byte[] bArr, int i, int i2, boolean z, Source source) throws IOException;

    private static native Bitmap nDecodeBitmap(long j, ImageDecoder imageDecoder, boolean z, int i, int i2, Rect rect, boolean z2, int i3, boolean z3, boolean z4, boolean z5, long j2, boolean z6) throws IOException;

    private static native ColorSpace nGetColorSpace(long j);

    private static native String nGetMimeType(long j);

    private static native void nGetPadding(long j, Rect rect);

    private static native Size nGetSampledSize(long j, int i);

    public static abstract class Source {
        abstract ImageDecoder createImageDecoder(boolean z) throws IOException;

        int getDensity() {
            return 0;
        }

        Resources getResources() {
            return null;
        }

        private Source() {
        }

        final int computeDstDensity() {
            Resources resources = getResources();
            if (resources == null) {
                return Bitmap.getDefaultDensity();
            }
            return resources.getDisplayMetrics().densityDpi;
        }
    }

    private static class ByteArraySource extends Source {
        private final byte[] mData;
        private final int mLength;
        private final int mOffset;

        ByteArraySource(byte[] bArr, int i, int i2) {
            super();
            this.mData = bArr;
            this.mOffset = i;
            this.mLength = i2;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            return ImageDecoder.nCreate(this.mData, this.mOffset, this.mLength, z, this);
        }
    }

    private static class ByteBufferSource extends Source {
        private final ByteBuffer mBuffer;

        ByteBufferSource(ByteBuffer byteBuffer) {
            super();
            this.mBuffer = byteBuffer;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            if (!this.mBuffer.isDirect() && this.mBuffer.hasArray()) {
                return ImageDecoder.nCreate(this.mBuffer.array(), this.mBuffer.arrayOffset() + this.mBuffer.position(), this.mBuffer.limit() - this.mBuffer.position(), z, this);
            }
            ByteBuffer byteBufferSlice = this.mBuffer.slice();
            return ImageDecoder.nCreate(byteBufferSlice, byteBufferSlice.position(), byteBufferSlice.limit(), z, this);
        }
    }

    private static class ContentResolverSource extends Source {
        private final ContentResolver mResolver;
        private final Resources mResources;
        private final Uri mUri;

        ContentResolverSource(ContentResolver contentResolver, Uri uri, Resources resources) {
            super();
            this.mResolver = contentResolver;
            this.mUri = uri;
            this.mResources = resources;
        }

        @Override // android.graphics.ImageDecoder.Source
        Resources getResources() {
            return this.mResources;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws Throwable {
            AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = null;
            try {
                if ("content".equals(this.mUri.getScheme())) {
                    assetFileDescriptorOpenAssetFileDescriptor = this.mResolver.openTypedAssetFileDescriptor(this.mUri, ContentType.IMAGE_UNSPECIFIED, null);
                } else {
                    assetFileDescriptorOpenAssetFileDescriptor = this.mResolver.openAssetFileDescriptor(this.mUri, "r");
                }
            } catch (FileNotFoundException unused) {
            }
            if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                return ImageDecoder.createFromAssetFileDescriptor(assetFileDescriptorOpenAssetFileDescriptor, z, this);
            }
            InputStream inputStreamOpenInputStream = this.mResolver.openInputStream(this.mUri);
            if (inputStreamOpenInputStream != null) {
                return ImageDecoder.createFromStream(inputStreamOpenInputStream, true, z, this);
            }
            throw new FileNotFoundException(this.mUri.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImageDecoder createFromFile(File file, boolean z, Source source) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileDescriptor fd = fileInputStream.getFD();
        try {
            Os.lseek(fd, 0L, OsConstants.SEEK_CUR);
            try {
                ImageDecoder imageDecoderNCreate = nCreate(fd, z, source);
                if (imageDecoderNCreate != null) {
                    imageDecoderNCreate.mInputStream = fileInputStream;
                    imageDecoderNCreate.mOwnsInputStream = true;
                }
                return imageDecoderNCreate;
            } finally {
                IoUtils.closeQuietly(fileInputStream);
            }
        } catch (ErrnoException unused) {
            return createFromStream(fileInputStream, true, z, source);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImageDecoder createFromStream(InputStream inputStream, boolean z, boolean z2, Source source) throws IOException {
        byte[] bArr = new byte[16384];
        try {
            ImageDecoder imageDecoderNCreate = nCreate(inputStream, bArr, z2, source);
            if (imageDecoderNCreate != null) {
                imageDecoderNCreate.mInputStream = inputStream;
                imageDecoderNCreate.mOwnsInputStream = z;
                imageDecoderNCreate.mTempStorage = bArr;
            }
            return imageDecoderNCreate;
        } finally {
            if (z) {
                IoUtils.closeQuietly(inputStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImageDecoder createFromAssetFileDescriptor(AssetFileDescriptor assetFileDescriptor, boolean z, Source source) throws IOException {
        ImageDecoder imageDecoderCreateFromStream;
        if (assetFileDescriptor == null) {
            throw new FileNotFoundException();
        }
        FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
        try {
            try {
                Os.lseek(fileDescriptor, assetFileDescriptor.getStartOffset(), OsConstants.SEEK_SET);
                imageDecoderCreateFromStream = nCreate(fileDescriptor, z, source);
            } catch (ErrnoException unused) {
                imageDecoderCreateFromStream = createFromStream(new FileInputStream(fileDescriptor), true, z, source);
            }
            if (imageDecoderCreateFromStream != null) {
                imageDecoderCreateFromStream.mAssetFd = assetFileDescriptor;
            }
            return imageDecoderCreateFromStream;
        } finally {
            IoUtils.closeQuietly(assetFileDescriptor);
        }
    }

    private static class InputStreamSource extends Source {
        final int mInputDensity;
        InputStream mInputStream;
        final Resources mResources;

        InputStreamSource(Resources resources, InputStream inputStream, int i) {
            super();
            if (inputStream == null) {
                throw new IllegalArgumentException("The InputStream cannot be null");
            }
            this.mResources = resources;
            this.mInputStream = inputStream;
            this.mInputDensity = i;
        }

        @Override // android.graphics.ImageDecoder.Source
        public Resources getResources() {
            return this.mResources;
        }

        @Override // android.graphics.ImageDecoder.Source
        public int getDensity() {
            return this.mInputDensity;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            ImageDecoder imageDecoderCreateFromStream;
            synchronized (this) {
                if (this.mInputStream == null) {
                    throw new IOException("Cannot reuse InputStreamSource");
                }
                InputStream inputStream = this.mInputStream;
                this.mInputStream = null;
                imageDecoderCreateFromStream = ImageDecoder.createFromStream(inputStream, false, z, this);
            }
            return imageDecoderCreateFromStream;
        }
    }

    public static class AssetInputStreamSource extends Source {
        private AssetManager.AssetInputStream mAssetInputStream;
        private final int mDensity;
        private final Resources mResources;

        public AssetInputStreamSource(AssetManager.AssetInputStream assetInputStream, Resources resources, TypedValue typedValue) {
            super();
            this.mAssetInputStream = assetInputStream;
            this.mResources = resources;
            if (typedValue.density == 0) {
                this.mDensity = 160;
            } else if (typedValue.density != 65535) {
                this.mDensity = typedValue.density;
            } else {
                this.mDensity = 0;
            }
        }

        @Override // android.graphics.ImageDecoder.Source
        public Resources getResources() {
            return this.mResources;
        }

        @Override // android.graphics.ImageDecoder.Source
        public int getDensity() {
            return this.mDensity;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            ImageDecoder imageDecoderCreateFromAsset;
            synchronized (this) {
                if (this.mAssetInputStream == null) {
                    throw new IOException("Cannot reuse AssetInputStreamSource");
                }
                AssetManager.AssetInputStream assetInputStream = this.mAssetInputStream;
                this.mAssetInputStream = null;
                imageDecoderCreateFromAsset = ImageDecoder.createFromAsset(assetInputStream, z, this);
            }
            return imageDecoderCreateFromAsset;
        }
    }

    private static class ResourceSource extends Source {
        private Object mLock;
        int mResDensity;
        final int mResId;
        final Resources mResources;

        ResourceSource(Resources resources, int i) {
            super();
            this.mLock = new Object();
            this.mResources = resources;
            this.mResId = i;
            this.mResDensity = 0;
        }

        @Override // android.graphics.ImageDecoder.Source
        public Resources getResources() {
            return this.mResources;
        }

        @Override // android.graphics.ImageDecoder.Source
        public int getDensity() {
            int i;
            synchronized (this.mLock) {
                i = this.mResDensity;
            }
            return i;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            TypedValue typedValue = new TypedValue();
            InputStream inputStreamOpenRawResource = this.mResources.openRawResource(this.mResId, typedValue);
            synchronized (this.mLock) {
                if (typedValue.density == 0) {
                    this.mResDensity = 160;
                } else if (typedValue.density != 65535) {
                    this.mResDensity = typedValue.density;
                }
            }
            return ImageDecoder.createFromAsset((AssetManager.AssetInputStream) inputStreamOpenRawResource, z, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImageDecoder createFromAsset(AssetManager.AssetInputStream assetInputStream, boolean z, Source source) throws IOException {
        try {
            ImageDecoder imageDecoderNCreate = nCreate(assetInputStream.getNativeAsset(), z, source);
            if (imageDecoderNCreate != null) {
                imageDecoderNCreate.mInputStream = assetInputStream;
                imageDecoderNCreate.mOwnsInputStream = true;
            }
            return imageDecoderNCreate;
        } finally {
            IoUtils.closeQuietly(assetInputStream);
        }
    }

    private static class AssetSource extends Source {
        private final AssetManager mAssets;
        private final String mFileName;

        AssetSource(AssetManager assetManager, String str) {
            super();
            this.mAssets = assetManager;
            this.mFileName = str;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            return ImageDecoder.createFromAsset((AssetManager.AssetInputStream) this.mAssets.open(this.mFileName), z, this);
        }
    }

    private static class FileSource extends Source {
        private final File mFile;

        FileSource(File file) {
            super();
            this.mFile = file;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            return ImageDecoder.createFromFile(this.mFile, z, this);
        }
    }

    private static class CallableSource extends Source {
        private final Callable<AssetFileDescriptor> mCallable;

        CallableSource(Callable<AssetFileDescriptor> callable) {
            super();
            this.mCallable = callable;
        }

        @Override // android.graphics.ImageDecoder.Source
        public ImageDecoder createImageDecoder(boolean z) throws IOException {
            try {
                return ImageDecoder.createFromAssetFileDescriptor(this.mCallable.call(), z, this);
            } catch (Exception e) {
                if (e instanceof IOException) {
                    throw ((IOException) e);
                }
                throw new IOException(e);
            }
        }
    }

    public static class ImageInfo {
        private ImageDecoder mDecoder;
        private final Size mSize;

        private ImageInfo(ImageDecoder imageDecoder) {
            this.mSize = new Size(imageDecoder.mWidth, imageDecoder.mHeight);
            this.mDecoder = imageDecoder;
        }

        public Size getSize() {
            return this.mSize;
        }

        public String getMimeType() {
            return this.mDecoder.getMimeType();
        }

        public boolean isAnimated() {
            return this.mDecoder.mAnimated;
        }

        public ColorSpace getColorSpace() {
            return this.mDecoder.getColorSpace();
        }
    }

    public static final class DecodeException extends IOException {
        public static final int SOURCE_EXCEPTION = 1;
        public static final int SOURCE_INCOMPLETE = 2;
        public static final int SOURCE_MALFORMED_DATA = 3;
        final int mError;
        final Source mSource;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Error {
        }

        DecodeException(int i, Throwable th, Source source) {
            super(errorMessage(i, th), th);
            this.mError = i;
            this.mSource = source;
        }

        DecodeException(int i, String str, Throwable th, Source source) {
            super(str + errorMessage(i, th), th);
            this.mError = i;
            this.mSource = source;
        }

        public int getError() {
            return this.mError;
        }

        public Source getSource() {
            return this.mSource;
        }

        private static String errorMessage(int i, Throwable th) {
            if (i != 1) {
                return i != 2 ? i != 3 ? "" : "Input contained an error." : "Input was incomplete.";
            }
            return "Exception in input: " + th;
        }
    }

    private ImageDecoder(long j, int i, int i2, boolean z, boolean z2) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        this.mNativePtr = j;
        this.mWidth = i;
        this.mHeight = i2;
        this.mDesiredWidth = i;
        this.mDesiredHeight = i2;
        this.mAnimated = z;
        this.mIsNinePatch = z2;
        closeGuard.open("close");
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mCloseGuard != null) {
                this.mCloseGuard.warnIfOpen();
            }
            this.mInputStream = null;
            this.mAssetFd = null;
            close();
        } finally {
            super.finalize();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isMimeTypeSupported(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.ImageDecoder.isMimeTypeSupported(java.lang.String):boolean");
    }

    public static Source createSource(Resources resources, int i) {
        return new ResourceSource(resources, i);
    }

    public static Source createSource(ContentResolver contentResolver, Uri uri) {
        return new ContentResolverSource(contentResolver, uri, null);
    }

    public static Source createSource(ContentResolver contentResolver, Uri uri, Resources resources) {
        return new ContentResolverSource(contentResolver, uri, resources);
    }

    public static Source createSource(AssetManager assetManager, String str) {
        return new AssetSource(assetManager, str);
    }

    public static Source createSource(byte[] bArr, int i, int i2) throws ArrayIndexOutOfBoundsException {
        if (bArr == null) {
            throw new NullPointerException("null byte[] in createSource!");
        }
        if (i < 0 || i2 < 0 || i >= bArr.length || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("invalid offset/length!");
        }
        return new ByteArraySource(bArr, i, i2);
    }

    public static Source createSource(byte[] bArr) {
        return createSource(bArr, 0, bArr.length);
    }

    public static Source createSource(ByteBuffer byteBuffer) {
        return new ByteBufferSource(byteBuffer);
    }

    public static Source createSource(Resources resources, InputStream inputStream) {
        return new InputStreamSource(resources, inputStream, Bitmap.getDefaultDensity());
    }

    public static Source createSource(Resources resources, InputStream inputStream, int i) {
        return new InputStreamSource(resources, inputStream, i);
    }

    public static Source createSource(File file) {
        return new FileSource(file);
    }

    public static Source createSource(Callable<AssetFileDescriptor> callable) {
        return new CallableSource(callable);
    }

    public Size getSampledSize(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("sampleSize must be positive! provided " + i);
        }
        long j = this.mNativePtr;
        if (j == 0) {
            throw new IllegalStateException("ImageDecoder is closed!");
        }
        return nGetSampledSize(j, i);
    }

    @Deprecated
    public ImageDecoder setResize(int i, int i2) {
        setTargetSize(i, i2);
        return this;
    }

    public void setTargetSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive! provided (" + i + ", " + i2 + ")");
        }
        this.mDesiredWidth = i;
        this.mDesiredHeight = i2;
    }

    @Deprecated
    public ImageDecoder setResize(int i) {
        setTargetSampleSize(i);
        return this;
    }

    private int getTargetDimension(int i, int i2, int i3) {
        if (i2 >= i) {
            return 1;
        }
        int i4 = i / i2;
        return (i3 != i4 && Math.abs((i3 * i2) - i) >= i2) ? i4 : i3;
    }

    public void setTargetSampleSize(int i) {
        Size sampledSize = getSampledSize(i);
        setTargetSize(getTargetDimension(this.mWidth, i, sampledSize.getWidth()), getTargetDimension(this.mHeight, i, sampledSize.getHeight()));
    }

    private boolean requestedResize() {
        return (this.mWidth == this.mDesiredWidth && this.mHeight == this.mDesiredHeight) ? false : true;
    }

    public void setAllocator(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("invalid allocator " + i);
        }
        this.mAllocator = i;
    }

    public int getAllocator() {
        return this.mAllocator;
    }

    public void setUnpremultipliedRequired(boolean z) {
        this.mUnpremultipliedRequired = z;
    }

    @Deprecated
    public ImageDecoder setRequireUnpremultiplied(boolean z) {
        setUnpremultipliedRequired(z);
        return this;
    }

    public boolean isUnpremultipliedRequired() {
        return this.mUnpremultipliedRequired;
    }

    @Deprecated
    public boolean getRequireUnpremultiplied() {
        return isUnpremultipliedRequired();
    }

    public void setPostProcessor(PostProcessor postProcessor) {
        this.mPostProcessor = postProcessor;
    }

    public PostProcessor getPostProcessor() {
        return this.mPostProcessor;
    }

    public void setOnPartialImageListener(OnPartialImageListener onPartialImageListener) {
        this.mOnPartialImageListener = onPartialImageListener;
    }

    public OnPartialImageListener getOnPartialImageListener() {
        return this.mOnPartialImageListener;
    }

    public void setCrop(Rect rect) {
        this.mCropRect = rect;
    }

    public Rect getCrop() {
        return this.mCropRect;
    }

    public void setOutPaddingRect(Rect rect) {
        this.mOutPaddingRect = rect;
    }

    public void setMutableRequired(boolean z) {
        this.mMutable = z;
    }

    @Deprecated
    public ImageDecoder setMutable(boolean z) {
        setMutableRequired(z);
        return this;
    }

    public boolean isMutableRequired() {
        return this.mMutable;
    }

    @Deprecated
    public boolean getMutable() {
        return isMutableRequired();
    }

    public void setMemorySizePolicy(int i) {
        this.mConserveMemory = i == 0;
    }

    public int getMemorySizePolicy() {
        return !this.mConserveMemory ? 1 : 0;
    }

    @Deprecated
    public void setConserveMemory(boolean z) {
        this.mConserveMemory = z;
    }

    @Deprecated
    public boolean getConserveMemory() {
        return this.mConserveMemory;
    }

    public void setDecodeAsAlphaMaskEnabled(boolean z) {
        this.mDecodeAsAlphaMask = z;
    }

    @Deprecated
    public ImageDecoder setDecodeAsAlphaMask(boolean z) {
        setDecodeAsAlphaMaskEnabled(z);
        return this;
    }

    @Deprecated
    public ImageDecoder setAsAlphaMask(boolean z) {
        setDecodeAsAlphaMask(z);
        return this;
    }

    public boolean isDecodeAsAlphaMaskEnabled() {
        return this.mDecodeAsAlphaMask;
    }

    @Deprecated
    public boolean getDecodeAsAlphaMask() {
        return this.mDecodeAsAlphaMask;
    }

    @Deprecated
    public boolean getAsAlphaMask() {
        return getDecodeAsAlphaMask();
    }

    public void setTargetColorSpace(ColorSpace colorSpace) {
        this.mDesiredColorSpace = colorSpace;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mCloseGuard.close();
        if (this.mClosed.compareAndSet(false, true)) {
            nClose(this.mNativePtr);
            this.mNativePtr = 0L;
            if (this.mOwnsInputStream) {
                IoUtils.closeQuietly(this.mInputStream);
            }
            IoUtils.closeQuietly(this.mAssetFd);
            this.mInputStream = null;
            this.mAssetFd = null;
            this.mTempStorage = null;
        }
    }

    private void checkState(boolean z) {
        if (this.mNativePtr == 0) {
            throw new IllegalStateException("Cannot use closed ImageDecoder!");
        }
        checkSubset(this.mDesiredWidth, this.mDesiredHeight, this.mCropRect);
        if (!z && this.mAllocator == 3) {
            if (this.mMutable) {
                throw new IllegalStateException("Cannot make mutable HARDWARE Bitmap!");
            }
            if (this.mDecodeAsAlphaMask) {
                throw new IllegalStateException("Cannot make HARDWARE Alpha mask Bitmap!");
            }
        }
        if (this.mPostProcessor != null && this.mUnpremultipliedRequired) {
            throw new IllegalStateException("Cannot draw to unpremultiplied pixels!");
        }
    }

    private static void checkSubset(int i, int i2, Rect rect) {
        if (rect == null) {
            return;
        }
        if (rect.width() <= 0 || rect.height() <= 0) {
            throw new IllegalStateException("Subset " + rect + " is empty/unsorted");
        }
        if (rect.left < 0 || rect.top < 0 || rect.right > i || rect.bottom > i2) {
            throw new IllegalStateException("Subset " + rect + " not contained by scaled image bounds: (" + i + " x " + i2 + ")");
        }
    }

    private boolean checkForExtended() {
        ColorSpace colorSpace = this.mDesiredColorSpace;
        if (colorSpace == null) {
            return false;
        }
        return colorSpace == ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB) || this.mDesiredColorSpace == ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
    }

    private long getColorSpacePtr() {
        ColorSpace colorSpace = this.mDesiredColorSpace;
        if (colorSpace == null) {
            return 0L;
        }
        return colorSpace.getNativeInstance();
    }

    private Bitmap decodeBitmapInternal() throws IOException {
        checkState(false);
        return nDecodeBitmap(this.mNativePtr, this, this.mPostProcessor != null, this.mDesiredWidth, this.mDesiredHeight, this.mCropRect, this.mMutable, this.mAllocator, this.mUnpremultipliedRequired, this.mConserveMemory, this.mDecodeAsAlphaMask, getColorSpacePtr(), checkForExtended());
    }

    private void callHeaderDecoded(OnHeaderDecodedListener onHeaderDecodedListener, Source source) {
        if (onHeaderDecodedListener != null) {
            ImageDecoder imageDecoder = null;
            ImageInfo imageInfo = new ImageInfo();
            try {
                onHeaderDecodedListener.onHeaderDecoded(this, imageInfo, source);
            } finally {
                imageInfo.mDecoder = null;
            }
        }
    }

    public static Drawable decodeDrawable(Source source, OnHeaderDecodedListener onHeaderDecodedListener) throws IOException {
        if (onHeaderDecodedListener == null) {
            throw new IllegalArgumentException("listener cannot be null! Use decodeDrawable(Source) to not have a listener");
        }
        return decodeDrawableImpl(source, onHeaderDecodedListener);
    }

    private static Drawable decodeDrawableImpl(Source source, OnHeaderDecodedListener onHeaderDecodedListener) throws IOException {
        ImageDecoder imageDecoderCreateImageDecoder = source.createImageDecoder(true);
        try {
            imageDecoderCreateImageDecoder.mSource = source;
            imageDecoderCreateImageDecoder.callHeaderDecoded(onHeaderDecodedListener, source);
            if (imageDecoderCreateImageDecoder.mUnpremultipliedRequired) {
                throw new IllegalStateException("Cannot decode a Drawable with unpremultiplied pixels!");
            }
            if (imageDecoderCreateImageDecoder.mMutable) {
                throw new IllegalStateException("Cannot decode a mutable Drawable!");
            }
            int iComputeDensity = imageDecoderCreateImageDecoder.computeDensity(source);
            if (imageDecoderCreateImageDecoder.mAnimated) {
                ImageDecoder imageDecoder = imageDecoderCreateImageDecoder.mPostProcessor == null ? null : imageDecoderCreateImageDecoder;
                imageDecoderCreateImageDecoder.checkState(true);
                AnimatedImageDrawable animatedImageDrawable = new AnimatedImageDrawable(imageDecoderCreateImageDecoder.mNativePtr, imageDecoder, imageDecoderCreateImageDecoder.mDesiredWidth, imageDecoderCreateImageDecoder.mDesiredHeight, imageDecoderCreateImageDecoder.getColorSpacePtr(), imageDecoderCreateImageDecoder.checkForExtended(), iComputeDensity, source.computeDstDensity(), imageDecoderCreateImageDecoder.mCropRect, imageDecoderCreateImageDecoder.mInputStream, imageDecoderCreateImageDecoder.mAssetFd);
                imageDecoderCreateImageDecoder.mInputStream = null;
                imageDecoderCreateImageDecoder.mAssetFd = null;
                if (imageDecoderCreateImageDecoder != null) {
                    imageDecoderCreateImageDecoder.close();
                }
                return animatedImageDrawable;
            }
            Bitmap bitmapDecodeBitmapInternal = imageDecoderCreateImageDecoder.decodeBitmapInternal();
            bitmapDecodeBitmapInternal.setDensity(iComputeDensity);
            Resources resources = source.getResources();
            byte[] ninePatchChunk = bitmapDecodeBitmapInternal.getNinePatchChunk();
            if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                Rect rect = new Rect();
                bitmapDecodeBitmapInternal.getOpticalInsets(rect);
                Rect rect2 = imageDecoderCreateImageDecoder.mOutPaddingRect;
                if (rect2 == null) {
                    rect2 = new Rect();
                }
                nGetPadding(imageDecoderCreateImageDecoder.mNativePtr, rect2);
                NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(resources, bitmapDecodeBitmapInternal, ninePatchChunk, rect2, rect, null);
                if (imageDecoderCreateImageDecoder != null) {
                    imageDecoderCreateImageDecoder.close();
                }
                return ninePatchDrawable;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmapDecodeBitmapInternal);
            if (imageDecoderCreateImageDecoder != null) {
                imageDecoderCreateImageDecoder.close();
            }
            return bitmapDrawable;
        } catch (Throwable th) {
            if (imageDecoderCreateImageDecoder == null) {
                throw th;
            }
            try {
                imageDecoderCreateImageDecoder.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static Drawable decodeDrawable(Source source) throws IOException {
        return decodeDrawableImpl(source, null);
    }

    public static Bitmap decodeBitmap(Source source, OnHeaderDecodedListener onHeaderDecodedListener) throws IOException {
        if (onHeaderDecodedListener == null) {
            throw new IllegalArgumentException("listener cannot be null! Use decodeBitmap(Source) to not have a listener");
        }
        return decodeBitmapImpl(source, onHeaderDecodedListener);
    }

    private static Bitmap decodeBitmapImpl(Source source, OnHeaderDecodedListener onHeaderDecodedListener) throws IOException {
        byte[] ninePatchChunk;
        ImageDecoder imageDecoderCreateImageDecoder = source.createImageDecoder(false);
        try {
            imageDecoderCreateImageDecoder.mSource = source;
            imageDecoderCreateImageDecoder.callHeaderDecoded(onHeaderDecodedListener, source);
            int iComputeDensity = imageDecoderCreateImageDecoder.computeDensity(source);
            Bitmap bitmapDecodeBitmapInternal = imageDecoderCreateImageDecoder.decodeBitmapInternal();
            bitmapDecodeBitmapInternal.setDensity(iComputeDensity);
            Rect rect = imageDecoderCreateImageDecoder.mOutPaddingRect;
            if (rect != null && (ninePatchChunk = bitmapDecodeBitmapInternal.getNinePatchChunk()) != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                nGetPadding(imageDecoderCreateImageDecoder.mNativePtr, rect);
            }
            if (imageDecoderCreateImageDecoder != null) {
                imageDecoderCreateImageDecoder.close();
            }
            return bitmapDecodeBitmapInternal;
        } catch (Throwable th) {
            if (imageDecoderCreateImageDecoder != null) {
                try {
                    imageDecoderCreateImageDecoder.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private int computeDensity(Source source) {
        int iComputeDstDensity;
        if (requestedResize()) {
            return 0;
        }
        int density = source.getDensity();
        if (density == 0) {
            return density;
        }
        if (this.mIsNinePatch && this.mPostProcessor == null) {
            return density;
        }
        Resources resources = source.getResources();
        if ((resources != null && resources.getDisplayMetrics().noncompatDensityDpi == density) || density == (iComputeDstDensity = source.computeDstDensity())) {
            return density;
        }
        if (density < iComputeDstDensity && sApiLevel >= 28) {
            return density;
        }
        float f = iComputeDstDensity / density;
        setTargetSize(Math.max((int) ((this.mWidth * f) + 0.5f), 1), Math.max((int) ((this.mHeight * f) + 0.5f), 1));
        return iComputeDstDensity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMimeType() {
        return nGetMimeType(this.mNativePtr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ColorSpace getColorSpace() {
        return nGetColorSpace(this.mNativePtr);
    }

    public static Bitmap decodeBitmap(Source source) throws IOException {
        return decodeBitmapImpl(source, null);
    }

    private int postProcessAndRelease(Canvas canvas) {
        try {
            return this.mPostProcessor.onPostProcess(canvas);
        } finally {
            canvas.release();
        }
    }

    private void onPartialImage(int i, Throwable th) throws DecodeException {
        DecodeException decodeException = new DecodeException(i, th, this.mSource);
        OnPartialImageListener onPartialImageListener = this.mOnPartialImageListener;
        if (onPartialImageListener == null || !onPartialImageListener.onPartialImage(decodeException)) {
            throw decodeException;
        }
    }
}
