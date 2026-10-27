package android.graphics.text;

import android.graphics.Paint;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public class MeasuredText {
    private char[] mChars;
    private boolean mComputeHyphenation;
    private boolean mComputeLayout;
    private long mNativePtr;

    private static native void nGetBounds(long j, char[] cArr, int i, int i2, Rect rect);

    @CriticalNative
    private static native float nGetCharWidthAt(long j, int i);

    @CriticalNative
    private static native int nGetMemoryUsage(long j);

    /* JADX INFO: Access modifiers changed from: private */
    @CriticalNative
    public static native long nGetReleaseFunc();

    @CriticalNative
    private static native float nGetWidth(long j, int i, int i2);

    private MeasuredText(long j, char[] cArr, boolean z, boolean z2) {
        this.mNativePtr = j;
        this.mChars = cArr;
        this.mComputeHyphenation = z;
        this.mComputeLayout = z2;
    }

    public char[] getChars() {
        return this.mChars;
    }

    public float getWidth(int i, int i2) {
        Preconditions.checkArgument(i >= 0 && i <= this.mChars.length, "start(" + i + ") must be 0 <= start <= " + this.mChars.length);
        Preconditions.checkArgument(i2 >= 0 && i2 <= this.mChars.length, "end(" + i2 + ") must be 0 <= end <= " + this.mChars.length);
        Preconditions.checkArgument(i <= i2, "start(" + i + ") is larger than end(" + i2 + ")");
        return nGetWidth(this.mNativePtr, i, i2);
    }

    public int getMemoryUsage() {
        return nGetMemoryUsage(this.mNativePtr);
    }

    public void getBounds(int i, int i2, Rect rect) {
        Preconditions.checkArgument(i >= 0 && i <= this.mChars.length, "start(" + i + ") must be 0 <= start <= " + this.mChars.length);
        Preconditions.checkArgument(i2 >= 0 && i2 <= this.mChars.length, "end(" + i2 + ") must be 0 <= end <= " + this.mChars.length);
        Preconditions.checkArgument(i <= i2, "start(" + i + ") is larger than end(" + i2 + ")");
        Preconditions.checkNotNull(rect);
        nGetBounds(this.mNativePtr, this.mChars, i, i2, rect);
    }

    public float getCharWidthAt(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.mChars.length, "offset(" + i + ") is larger than text length: " + this.mChars.length);
        return nGetCharWidthAt(this.mNativePtr, i);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    public static final class Builder {
        private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(MeasuredText.class.getClassLoader(), MeasuredText.nGetReleaseFunc());
        private boolean mComputeHyphenation;
        private boolean mComputeLayout;
        private int mCurrentOffset;
        private MeasuredText mHintMt;
        private long mNativePtr;
        private final char[] mText;

        private static native void nAddReplacementRun(long j, long j2, int i, int i2, float f);

        private static native void nAddStyleRun(long j, long j2, int i, int i2, boolean z);

        private static native long nBuildMeasuredText(long j, long j2, char[] cArr, boolean z, boolean z2);

        private static native void nFreeBuilder(long j);

        private static native long nInitBuilder();

        public Builder(char[] cArr) {
            this.mComputeHyphenation = false;
            this.mComputeLayout = true;
            this.mCurrentOffset = 0;
            this.mHintMt = null;
            Preconditions.checkNotNull(cArr);
            this.mText = cArr;
            this.mNativePtr = nInitBuilder();
        }

        public Builder(MeasuredText measuredText) {
            this.mComputeHyphenation = false;
            this.mComputeLayout = true;
            this.mCurrentOffset = 0;
            this.mHintMt = null;
            Preconditions.checkNotNull(measuredText);
            this.mText = measuredText.mChars;
            this.mNativePtr = nInitBuilder();
            if (measuredText.mComputeLayout) {
                this.mComputeHyphenation = measuredText.mComputeHyphenation;
                this.mComputeLayout = measuredText.mComputeLayout;
                this.mHintMt = measuredText;
                return;
            }
            throw new IllegalArgumentException("The input MeasuredText must not be created with setComputeLayout(false).");
        }

        public Builder appendStyleRun(Paint paint, int i, boolean z) {
            Preconditions.checkNotNull(paint);
            Preconditions.checkArgument(i > 0, "length can not be negative");
            int i2 = this.mCurrentOffset + i;
            Preconditions.checkArgument(i2 <= this.mText.length, "Style exceeds the text length");
            nAddStyleRun(this.mNativePtr, paint.getNativeInstance(), this.mCurrentOffset, i2, z);
            this.mCurrentOffset = i2;
            return this;
        }

        public Builder appendReplacementRun(Paint paint, int i, float f) {
            Preconditions.checkArgument(i > 0, "length can not be negative");
            int i2 = this.mCurrentOffset + i;
            Preconditions.checkArgument(i2 <= this.mText.length, "Replacement exceeds the text length");
            nAddReplacementRun(this.mNativePtr, paint.getNativeInstance(), this.mCurrentOffset, i2, f);
            this.mCurrentOffset = i2;
            return this;
        }

        public Builder setComputeHyphenation(boolean z) {
            this.mComputeHyphenation = z;
            return this;
        }

        public Builder setComputeLayout(boolean z) {
            this.mComputeLayout = z;
            return this;
        }

        public MeasuredText build() {
            ensureNativePtrNoReuse();
            if (this.mCurrentOffset != this.mText.length) {
                throw new IllegalStateException("Style info has not been provided for all text.");
            }
            MeasuredText measuredText = this.mHintMt;
            if (measuredText != null && measuredText.mComputeHyphenation != this.mComputeHyphenation) {
                throw new IllegalArgumentException("The hyphenation configuration is different from given hint MeasuredText");
            }
            try {
                MeasuredText measuredText2 = this.mHintMt;
                long jNBuildMeasuredText = nBuildMeasuredText(this.mNativePtr, measuredText2 == null ? 0L : measuredText2.getNativePtr(), this.mText, this.mComputeHyphenation, this.mComputeLayout);
                MeasuredText measuredText3 = new MeasuredText(jNBuildMeasuredText, this.mText, this.mComputeHyphenation, this.mComputeLayout);
                sRegistry.registerNativeAllocation(measuredText3, jNBuildMeasuredText);
                return measuredText3;
            } finally {
                nFreeBuilder(this.mNativePtr);
                this.mNativePtr = 0L;
            }
        }

        private void ensureNativePtrNoReuse() {
            if (this.mNativePtr == 0) {
                throw new IllegalStateException("Builder can not be reused.");
            }
        }
    }
}
