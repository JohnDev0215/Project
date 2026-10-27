package android.content.res.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ApkAssets;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ResourcesProvider implements AutoCloseable, Closeable {
    private static final String TAG = "ResourcesProvider";
    private final ApkAssets mApkAssets;
    private final Object mLock = new Object();
    private boolean mOpen = true;
    private int mOpenCount = 0;

    public static ResourcesProvider empty(AssetsProvider assetsProvider) {
        return new ResourcesProvider(ApkAssets.loadEmptyForLoader(4, assetsProvider));
    }

    public static ResourcesProvider loadFromApk(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        return loadFromApk(parcelFileDescriptor, null);
    }

    public static ResourcesProvider loadFromApk(ParcelFileDescriptor parcelFileDescriptor, AssetsProvider assetsProvider) throws IOException {
        return new ResourcesProvider(ApkAssets.loadFromFd(parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor.toString(), 4, assetsProvider));
    }

    public static ResourcesProvider loadFromApk(ParcelFileDescriptor parcelFileDescriptor, long j, long j2, AssetsProvider assetsProvider) throws IOException {
        return new ResourcesProvider(ApkAssets.loadFromFd(parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor.toString(), j, j2, 4, assetsProvider));
    }

    public static ResourcesProvider loadFromTable(ParcelFileDescriptor parcelFileDescriptor, AssetsProvider assetsProvider) throws IOException {
        return new ResourcesProvider(ApkAssets.loadTableFromFd(parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor.toString(), 4, assetsProvider));
    }

    public static ResourcesProvider loadFromTable(ParcelFileDescriptor parcelFileDescriptor, long j, long j2, AssetsProvider assetsProvider) throws IOException {
        return new ResourcesProvider(ApkAssets.loadTableFromFd(parcelFileDescriptor.getFileDescriptor(), parcelFileDescriptor.toString(), j, j2, 4, assetsProvider));
    }

    public static ResourcesProvider loadFromSplit(Context context, String str) throws IOException {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int iIndexOf = ArrayUtils.indexOf(applicationInfo.splitNames, str);
        if (iIndexOf < 0) {
            throw new IllegalArgumentException("Split " + str + " not found");
        }
        return new ResourcesProvider(ApkAssets.loadFromPath(applicationInfo.getSplitCodePaths()[iIndexOf], 4, null));
    }

    public static ResourcesProvider loadFromDirectory(String str, AssetsProvider assetsProvider) throws IOException {
        return new ResourcesProvider(ApkAssets.loadFromDir(str, 4, assetsProvider));
    }

    private ResourcesProvider(ApkAssets apkAssets) {
        this.mApkAssets = apkAssets;
    }

    public ApkAssets getApkAssets() {
        return this.mApkAssets;
    }

    final void incrementRefCount() {
        synchronized (this.mLock) {
            if (!this.mOpen) {
                throw new IllegalStateException("Operation failed: resources provider is closed");
            }
            this.mOpenCount++;
        }
    }

    final void decrementRefCount() {
        synchronized (this.mLock) {
            this.mOpenCount--;
        }
    }

    @Override // java.lang.AutoCloseable, java.io.Closeable
    public void close() {
        synchronized (this.mLock) {
            if (this.mOpen) {
                if (this.mOpenCount != 0) {
                    throw new IllegalStateException("Failed to close provider used by " + this.mOpenCount + " ResourcesLoader instances");
                }
                this.mOpen = false;
                try {
                    this.mApkAssets.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    protected void finalize() throws Throwable {
        synchronized (this.mLock) {
            if (this.mOpenCount != 0) {
                Log.w(TAG, "ResourcesProvider " + this + " finalized with non-zero refs: " + this.mOpenCount);
            }
            if (this.mOpen) {
                this.mOpen = false;
                this.mApkAssets.close();
            }
        }
    }
}
