package android.graphics;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RegionIterator {
    private long mNativeIter;

    private static native long nativeConstructor(long j);

    private static native void nativeDestructor(long j);

    private static native boolean nativeNext(long j, Rect rect);

    public RegionIterator(Region region) {
        this.mNativeIter = nativeConstructor(region.ni());
    }

    public final boolean next(Rect rect) {
        Objects.requireNonNull(rect, "The Rect must be provided");
        return nativeNext(this.mNativeIter, rect);
    }

    protected void finalize() throws Throwable {
        nativeDestructor(this.mNativeIter);
        this.mNativeIter = 0L;
    }
}
