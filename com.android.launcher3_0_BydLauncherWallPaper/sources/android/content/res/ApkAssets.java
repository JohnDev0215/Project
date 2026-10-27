package android.content.res;

import android.content.om.OverlayableInfo;
import android.content.res.loader.AssetsProvider;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ApkAssets {
    private static final int FORMAT_APK = 0;
    private static final int FORMAT_ARSC = 2;
    private static final int FORMAT_DIR = 3;
    private static final int FORMAT_IDMAP = 1;
    public static final int PROPERTY_DYNAMIC = 2;
    public static final int PROPERTY_LOADER = 4;
    private static final int PROPERTY_OVERLAY = 8;
    public static final int PROPERTY_SYSTEM = 1;
    private final AssetsProvider mAssets;
    private final int mFlags;
    private final long mNativePtr;
    private boolean mOpen;
    private final StringBlock mStringBlock;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PropertyFlags {
    }

    private static native boolean nativeDefinesOverlayable(long j) throws IOException;

    private static native void nativeDestroy(long j);

    private static native String nativeGetAssetPath(long j);

    private static native OverlayableInfo nativeGetOverlayableInfo(long j, String str) throws IOException;

    private static native long nativeGetStringBlock(long j);

    private static native boolean nativeIsUpToDate(long j);

    private static native long nativeLoad(int i, String str, int i2, AssetsProvider assetsProvider) throws IOException;

    private static native long nativeLoadEmpty(int i, AssetsProvider assetsProvider);

    private static native long nativeLoadFd(int i, FileDescriptor fileDescriptor, String str, int i2, AssetsProvider assetsProvider) throws IOException;

    private static native long nativeLoadFdOffsets(int i, FileDescriptor fileDescriptor, String str, long j, long j2, int i2, AssetsProvider assetsProvider) throws IOException;

    private static native long nativeOpenXml(long j, String str) throws IOException;

    public static ApkAssets loadFromPath(String str) throws IOException {
        return loadFromPath(str, 0);
    }

    public static ApkAssets loadFromPath(String str, int i) throws IOException {
        return new ApkAssets(0, str, i, null);
    }

    public static ApkAssets loadFromPath(String str, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(0, str, i, assetsProvider);
    }

    public static ApkAssets loadFromFd(FileDescriptor fileDescriptor, String str, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(0, fileDescriptor, str, i, assetsProvider);
    }

    public static ApkAssets loadFromFd(FileDescriptor fileDescriptor, String str, long j, long j2, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(0, fileDescriptor, str, j, j2, i, assetsProvider);
    }

    public static ApkAssets loadOverlayFromPath(String str, int i) throws IOException {
        return new ApkAssets(1, str, i, null);
    }

    public static ApkAssets loadTableFromFd(FileDescriptor fileDescriptor, String str, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(2, fileDescriptor, str, i, assetsProvider);
    }

    public static ApkAssets loadTableFromFd(FileDescriptor fileDescriptor, String str, long j, long j2, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(2, fileDescriptor, str, j, j2, i, assetsProvider);
    }

    public static ApkAssets loadFromDir(String str, int i, AssetsProvider assetsProvider) throws IOException {
        return new ApkAssets(3, str, i, assetsProvider);
    }

    public static ApkAssets loadEmptyForLoader(int i, AssetsProvider assetsProvider) {
        return new ApkAssets(i, assetsProvider);
    }

    private ApkAssets(int i, String str, int i2, AssetsProvider assetsProvider) throws IOException {
        this.mOpen = true;
        Objects.requireNonNull(str, "path");
        this.mFlags = i2;
        long jNativeLoad = nativeLoad(i, str, i2, assetsProvider);
        this.mNativePtr = jNativeLoad;
        this.mStringBlock = new StringBlock(nativeGetStringBlock(jNativeLoad), true);
        this.mAssets = assetsProvider;
    }

    private ApkAssets(int i, FileDescriptor fileDescriptor, String str, int i2, AssetsProvider assetsProvider) throws IOException {
        this.mOpen = true;
        Objects.requireNonNull(fileDescriptor, "fd");
        Objects.requireNonNull(str, "friendlyName");
        this.mFlags = i2;
        long jNativeLoadFd = nativeLoadFd(i, fileDescriptor, str, i2, assetsProvider);
        this.mNativePtr = jNativeLoadFd;
        this.mStringBlock = new StringBlock(nativeGetStringBlock(jNativeLoadFd), true);
        this.mAssets = assetsProvider;
    }

    private ApkAssets(int i, FileDescriptor fileDescriptor, String str, long j, long j2, int i2, AssetsProvider assetsProvider) throws IOException {
        this.mOpen = true;
        Objects.requireNonNull(fileDescriptor, "fd");
        Objects.requireNonNull(str, "friendlyName");
        this.mFlags = i2;
        long jNativeLoadFdOffsets = nativeLoadFdOffsets(i, fileDescriptor, str, j, j2, i2, assetsProvider);
        this.mNativePtr = jNativeLoadFdOffsets;
        this.mStringBlock = new StringBlock(nativeGetStringBlock(jNativeLoadFdOffsets), true);
        this.mAssets = assetsProvider;
    }

    private ApkAssets(int i, AssetsProvider assetsProvider) {
        this.mOpen = true;
        this.mFlags = i;
        this.mNativePtr = nativeLoadEmpty(i, assetsProvider);
        this.mStringBlock = null;
        this.mAssets = assetsProvider;
    }

    public String getAssetPath() {
        String strNativeGetAssetPath;
        synchronized (this) {
            strNativeGetAssetPath = nativeGetAssetPath(this.mNativePtr);
        }
        return strNativeGetAssetPath;
    }

    CharSequence getStringFromPool(int i) {
        CharSequence charSequence;
        if (this.mStringBlock == null) {
            return null;
        }
        synchronized (this) {
            charSequence = this.mStringBlock.get(i);
        }
        return charSequence;
    }

    public boolean isForLoader() {
        return (this.mFlags & 4) != 0;
    }

    public AssetsProvider getAssetsProvider() {
        return this.mAssets;
    }

    public XmlResourceParser openXml(String str) throws IOException {
        XmlResourceParser xmlResourceParserNewParser;
        Objects.requireNonNull(str, "fileName");
        synchronized (this) {
            XmlBlock xmlBlock = new XmlBlock(null, nativeOpenXml(this.mNativePtr, str));
            try {
                xmlResourceParserNewParser = xmlBlock.newParser();
                if (xmlResourceParserNewParser == null) {
                    throw new AssertionError("block.newParser() returned a null parser");
                }
                xmlBlock.close();
            } finally {
            }
        }
        return xmlResourceParserNewParser;
    }

    public OverlayableInfo getOverlayableInfo(String str) throws IOException {
        return nativeGetOverlayableInfo(this.mNativePtr, str);
    }

    public boolean definesOverlayable() throws IOException {
        return nativeDefinesOverlayable(this.mNativePtr);
    }

    public boolean isUpToDate() {
        boolean zNativeIsUpToDate;
        synchronized (this) {
            zNativeIsUpToDate = nativeIsUpToDate(this.mNativePtr);
        }
        return zNativeIsUpToDate;
    }

    public String toString() {
        return "ApkAssets{path=" + getAssetPath() + "}";
    }

    protected void finalize() throws Throwable {
        close();
    }

    public void close() {
        synchronized (this) {
            if (this.mOpen) {
                this.mOpen = false;
                StringBlock stringBlock = this.mStringBlock;
                if (stringBlock != null) {
                    stringBlock.close();
                }
                nativeDestroy(this.mNativePtr);
            }
        }
    }
}
