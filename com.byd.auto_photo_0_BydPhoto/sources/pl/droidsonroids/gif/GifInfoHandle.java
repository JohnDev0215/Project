package pl.droidsonroids.gif;

import android.car.Car;
import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
final class GifInfoHandle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile long f3175a;

    static {
        i.b();
    }

    GifInfoHandle() {
    }

    GifInfoHandle(AssetFileDescriptor assetFileDescriptor) {
        try {
            this.f3175a = n(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), false);
        } finally {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    GifInfoHandle(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            throw new IllegalArgumentException("InputStream does not support marking");
        }
        this.f3175a = openStream(inputStream);
    }

    GifInfoHandle(String str) {
        this.f3175a = openFile(str);
    }

    static native int createTempNativeFileDescriptor();

    static native int extractNativeFileDescriptor(FileDescriptor fileDescriptor, boolean z);

    private static native void free(long j);

    private static native int getCurrentFrameIndex(long j);

    private static native int getCurrentLoop(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getHeight(long j);

    private static native int getLoopCount(long j);

    private static native int getNativeErrorCode(long j);

    private static native int getNumberOfFrames(long j);

    private static native long[] getSavedState(long j);

    private static native int getWidth(long j);

    @RequiresApi(21)
    private static int h(FileDescriptor fileDescriptor, boolean z) throws ErrnoException {
        try {
            int iCreateTempNativeFileDescriptor = createTempNativeFileDescriptor();
            Os.dup2(fileDescriptor, iCreateTempNativeFileDescriptor);
            return iCreateTempNativeFileDescriptor;
        } finally {
            if (z) {
                Os.close(fileDescriptor);
            }
        }
    }

    private static native boolean isOpaque(long j);

    private static long n(FileDescriptor fileDescriptor, long j, boolean z) throws GifIOException {
        int iH;
        if (Build.VERSION.SDK_INT > 27) {
            try {
                iH = h(fileDescriptor, z);
            } catch (Exception e) {
                throw new GifIOException(d.OPEN_FAILED.x, e.getMessage());
            }
        } else {
            iH = extractNativeFileDescriptor(fileDescriptor, z);
        }
        return openNativeFileDescriptor(iH, j);
    }

    static GifInfoHandle o(ContentResolver contentResolver, Uri uri) throws IOException {
        if ("file".equals(uri.getScheme())) {
            return new GifInfoHandle(uri.getPath());
        }
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            return new GifInfoHandle(assetFileDescriptorOpenAssetFileDescriptor);
        }
        throw new IOException("Could not open AssetFileDescriptor for " + uri);
    }

    static native long openFile(String str);

    static native long openNativeFileDescriptor(int i, long j);

    static native long openStream(InputStream inputStream);

    private static native void postUnbindSurface(long j);

    private static native long renderFrame(long j, Bitmap bitmap);

    private static native boolean reset(long j);

    private static native long restoreRemainder(long j);

    private static native int restoreSavedState(long j, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j);

    private static native void seekToTime(long j, int i, Bitmap bitmap);

    private static native void setLoopCount(long j, char c2);

    private static native void setSpeedFactor(long j, float f);

    synchronized int a() {
        return getCurrentFrameIndex(this.f3175a);
    }

    synchronized int b() {
        return getCurrentLoop(this.f3175a);
    }

    synchronized int c() {
        return getCurrentPosition(this.f3175a);
    }

    synchronized int d() {
        return getDuration(this.f3175a);
    }

    synchronized int e() {
        return getHeight(this.f3175a);
    }

    synchronized int f() {
        return getLoopCount(this.f3175a);
    }

    protected void finalize() throws Throwable {
        try {
            q();
        } finally {
            super.finalize();
        }
    }

    synchronized int g() {
        return getNativeErrorCode(this.f3175a);
    }

    synchronized int i() {
        return getNumberOfFrames(this.f3175a);
    }

    synchronized long[] j() {
        return getSavedState(this.f3175a);
    }

    synchronized int k() {
        return getWidth(this.f3175a);
    }

    synchronized boolean l() {
        return isOpaque(this.f3175a);
    }

    synchronized boolean m() {
        return this.f3175a == 0;
    }

    synchronized void p() {
        postUnbindSurface(this.f3175a);
    }

    synchronized void q() {
        free(this.f3175a);
        this.f3175a = 0L;
    }

    synchronized long r(Bitmap bitmap) {
        return renderFrame(this.f3175a, bitmap);
    }

    synchronized boolean s() {
        return reset(this.f3175a);
    }

    synchronized long t() {
        return restoreRemainder(this.f3175a);
    }

    synchronized int u(long[] jArr, Bitmap bitmap) {
        return restoreSavedState(this.f3175a, jArr, bitmap);
    }

    synchronized void v() {
        saveRemainder(this.f3175a);
    }

    synchronized void w(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 2147483647L) int i, Bitmap bitmap) {
        seekToTime(this.f3175a, i, bitmap);
    }

    void x(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 65535) int i) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("Loop count of range <0, 65535>");
        }
        synchronized (this) {
            setLoopCount(this.f3175a, (char) i);
        }
    }

    void y(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Speed factor is not positive");
        }
        if (f < 4.656613E-10f) {
            f = 4.656613E-10f;
        }
        synchronized (this) {
            setSpeedFactor(this.f3175a, f);
        }
    }
}
