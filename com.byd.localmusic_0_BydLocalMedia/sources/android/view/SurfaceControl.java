package android.view;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.DeviceProductInfo;
import android.hardware.display.DisplayedContentSample;
import android.hardware.display.DisplayedContentSamplingAttributes;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.proto.ProtoOutputStream;
import android.view.Display;
import android.view.Surface;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Objects;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes3.dex */
public final class SurfaceControl implements Parcelable {
    public static final Parcelable.Creator<SurfaceControl> CREATOR = new Parcelable.Creator<SurfaceControl>() { // from class: android.view.SurfaceControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SurfaceControl createFromParcel(Parcel parcel) {
            return new SurfaceControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SurfaceControl[] newArray(int i) {
            return new SurfaceControl[i];
        }
    };
    public static final int CURSOR_WINDOW = 8192;
    public static final int FX_SURFACE_BLAST = 262144;
    public static final int FX_SURFACE_CONTAINER = 524288;
    public static final int FX_SURFACE_EFFECT = 131072;
    public static final int FX_SURFACE_MASK = 983040;
    public static final int FX_SURFACE_NORMAL = 0;
    public static final int HIDDEN = 4;
    private static final int INTERNAL_DATASPACE_DISPLAY_P3 = 143261696;
    private static final int INTERNAL_DATASPACE_SCRGB = 411107328;
    private static final int INTERNAL_DATASPACE_SRGB = 142671872;
    public static final int METADATA_ACCESSIBILITY_ID = 5;
    public static final int METADATA_MOUSE_CURSOR = 4;
    public static final int METADATA_OWNER_UID = 1;
    public static final int METADATA_TASK_ID = 3;
    public static final int METADATA_WINDOW_TYPE = 2;
    public static final int NON_PREMULTIPLIED = 256;
    public static final int NO_COLOR_FILL = 16384;
    public static final int OPAQUE = 1024;
    public static final int POWER_MODE_DOZE = 1;
    public static final int POWER_MODE_DOZE_SUSPEND = 3;
    public static final int POWER_MODE_NORMAL = 2;
    public static final int POWER_MODE_OFF = 0;
    public static final int POWER_MODE_ON_SUSPEND = 4;
    public static final int PROTECTED_APP = 2048;
    public static final int SECURE = 128;
    private static final int SURFACE_HIDDEN = 1;
    private static final int SURFACE_OPAQUE = 2;
    private static final String TAG = "SurfaceControl";
    public static final int WINDOW_TYPE_DONT_SCREENSHOT = 441731;
    static Transaction sGlobalTransaction;
    static long sTransactionNestCount;
    private final CloseGuard mCloseGuard;
    private int mHeight;
    private WeakReference<View> mLocalOwnerView;
    private final Object mLock;
    private String mName;
    private long mNativeHandle;
    public long mNativeObject;
    private ArrayList<OnReparentListener> mReparentListeners;
    private int mWidth;

    public static final class CieXyz {
        public float X;
        public float Y;
        public float Z;
    }

    public static final class DisplayPrimaries {
        public CieXyz blue;
        public CieXyz green;
        public CieXyz red;
        public CieXyz white;
    }

    public interface OnReparentListener {
        void onReparent(Transaction transaction, SurfaceControl surfaceControl);
    }

    private static native long nativeAcquireFrameRateFlexibilityToken();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeApplyTransaction(long j, boolean z);

    private static native ScreenshotGraphicBuffer nativeCaptureLayers(IBinder iBinder, long j, Rect rect, float f, long[] jArr, int i);

    private static native boolean nativeClearAnimationFrameStats();

    private static native boolean nativeClearContentFrameStats(long j);

    private static native long nativeCopyFromSurfaceControl(long j);

    private static native long nativeCreate(SurfaceSession surfaceSession, String str, int i, int i2, int i3, int i4, long j, Parcel parcel) throws Surface.OutOfResourcesException;

    private static native IBinder nativeCreateDisplay(String str, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateTransaction();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeDeferTransactionUntil(long j, long j2, long j3, long j4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeDeferTransactionUntilSurface(long j, long j2, long j3, long j4);

    private static native void nativeDestroyDisplay(IBinder iBinder);

    private static native void nativeDisconnect(long j);

    private static native int nativeGetActiveColorMode(IBinder iBinder);

    private static native int nativeGetActiveConfig(IBinder iBinder);

    private static native boolean nativeGetAnimationFrameStats(WindowAnimationFrameStats windowAnimationFrameStats);

    private static native boolean nativeGetAutoLowLatencyModeSupport(IBinder iBinder);

    private static native int[] nativeGetCompositionDataspaces();

    private static native boolean nativeGetContentFrameStats(long j, WindowContentFrameStats windowContentFrameStats);

    private static native DesiredDisplayConfigSpecs nativeGetDesiredDisplayConfigSpecs(IBinder iBinder);

    private static native boolean nativeGetDisplayBrightnessSupport(IBinder iBinder);

    private static native int[] nativeGetDisplayColorModes(IBinder iBinder);

    private static native DisplayConfig[] nativeGetDisplayConfigs(IBinder iBinder);

    private static native DisplayInfo nativeGetDisplayInfo(IBinder iBinder);

    private static native DisplayPrimaries nativeGetDisplayNativePrimaries(IBinder iBinder);

    private static native DisplayedContentSample nativeGetDisplayedContentSample(IBinder iBinder, long j, long j2);

    private static native DisplayedContentSamplingAttributes nativeGetDisplayedContentSamplingAttributes(IBinder iBinder);

    private static native boolean nativeGetGameContentTypeSupport(IBinder iBinder);

    private static native long nativeGetHandle(long j);

    private static native Display.HdrCapabilities nativeGetHdrCapabilities(IBinder iBinder);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetNativeTransactionFinalizer();

    private static native long[] nativeGetPhysicalDisplayIds();

    private static native IBinder nativeGetPhysicalDisplayToken(long j);

    private static native boolean nativeGetProtectedContentSupport();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeMergeTransaction(long j, long j2);

    private static native long nativeMirrorSurface(long j);

    private static native long nativeReadFromParcel(Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeReadTransactionFromParcel(Parcel parcel);

    private static native void nativeRelease(long j);

    private static native void nativeReleaseFrameRateFlexibilityToken(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeReparent(long j, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeReparentChildren(long j, long j2, long j3);

    private static native ScreenshotGraphicBuffer nativeScreenshot(IBinder iBinder, Rect rect, int i, int i2, boolean z, int i3, boolean z2);

    private static native boolean nativeSetActiveColorMode(IBinder iBinder, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetAlpha(long j, long j2, float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetAnimationTransaction(long j);

    private static native void nativeSetAutoLowLatencyMode(IBinder iBinder, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetBackgroundBlurRadius(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColor(long j, long j2, float[] fArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColorSpaceAgnostic(long j, long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColorTransform(long j, long j2, float[] fArr, float[] fArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetCornerRadius(long j, long j2, float f);

    private static native boolean nativeSetDesiredDisplayConfigSpecs(IBinder iBinder, DesiredDisplayConfigSpecs desiredDisplayConfigSpecs);

    private static native boolean nativeSetDisplayBrightness(IBinder iBinder, float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplayLayerStack(long j, IBinder iBinder, int i);

    private static native void nativeSetDisplayPowerMode(IBinder iBinder, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplayProjection(long j, IBinder iBinder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplaySize(long j, IBinder iBinder, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplaySurface(long j, IBinder iBinder, long j2);

    private static native boolean nativeSetDisplayedContentSamplingEnabled(IBinder iBinder, boolean z, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEarlyWakeup(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEarlyWakeupEnd(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEarlyWakeupStart(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFixedTransformHint(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFlags(long j, long j2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFrameRate(long j, long j2, float f, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFrameRateSelectionPriority(long j, long j2, int i);

    private static native void nativeSetGameContentType(IBinder iBinder, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetGeometry(long j, long j2, Rect rect, Rect rect2, long j3);

    private static native void nativeSetGlobalShadowSettings(float[] fArr, float[] fArr2, float f, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetInputWindowInfo(long j, long j2, InputWindowHandle inputWindowHandle);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetLayer(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetLayerStack(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetMatrix(long j, long j2, float f, float f2, float f3, float f4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetMetadata(long j, long j2, int i, Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetOverrideScalingMode(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetPosition(long j, long j2, float f, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetRelativeLayer(long j, long j2, long j3, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetShadowRadius(long j, long j2, float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetSize(long j, long j2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetTransparentRegionHint(long j, long j2, Region region);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetWindowCrop(long j, long j2, int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSeverChildren(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSyncInputWindows(long j);

    private static native void nativeWriteToParcel(long j, Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeWriteTransactionToParcel(long j, Parcel parcel);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean addOnReparentListener(OnReparentListener onReparentListener) {
        boolean zAdd;
        synchronized (this.mLock) {
            if (this.mReparentListeners == null) {
                this.mReparentListeners = new ArrayList<>(1);
            }
            zAdd = this.mReparentListeners.add(onReparentListener);
        }
        return zAdd;
    }

    public boolean removeOnReparentListener(OnReparentListener onReparentListener) {
        boolean zRemove;
        synchronized (this.mLock) {
            zRemove = this.mReparentListeners.remove(onReparentListener);
            if (this.mReparentListeners.isEmpty()) {
                this.mReparentListeners = null;
            }
        }
        return zRemove;
    }

    private void assignNativeObject(long j, String str) {
        if (this.mNativeObject != 0) {
            release();
        }
        if (j != 0) {
            this.mCloseGuard.openWithCallSite("release", str);
        }
        this.mNativeObject = j;
        this.mNativeHandle = j != 0 ? nativeGetHandle(j) : 0L;
    }

    public void copyFrom(SurfaceControl surfaceControl, String str) {
        this.mName = surfaceControl.mName;
        this.mWidth = surfaceControl.mWidth;
        this.mHeight = surfaceControl.mHeight;
        this.mLocalOwnerView = surfaceControl.mLocalOwnerView;
        assignNativeObject(nativeCopyFromSurfaceControl(surfaceControl.mNativeObject), str);
    }

    public static class ScreenshotGraphicBuffer {
        private final ColorSpace mColorSpace;
        private final boolean mContainsSecureLayers;
        private final GraphicBuffer mGraphicBuffer;

        public ScreenshotGraphicBuffer(GraphicBuffer graphicBuffer, ColorSpace colorSpace, boolean z) {
            this.mGraphicBuffer = graphicBuffer;
            this.mColorSpace = colorSpace;
            this.mContainsSecureLayers = z;
        }

        private static ScreenshotGraphicBuffer createFromNative(int i, int i2, int i3, int i4, long j, int i5, boolean z) {
            return new ScreenshotGraphicBuffer(GraphicBuffer.createFromExisting(i, i2, i3, i4, j), ColorSpace.get(ColorSpace.Named.values()[i5]), z);
        }

        public ColorSpace getColorSpace() {
            return this.mColorSpace;
        }

        public GraphicBuffer getGraphicBuffer() {
            return this.mGraphicBuffer;
        }

        public boolean containsSecureLayers() {
            return this.mContainsSecureLayers;
        }
    }

    public static class Builder {
        private int mHeight;
        private WeakReference<View> mLocalOwnerView;
        private SparseIntArray mMetadata;
        private String mName;
        private SurfaceControl mParent;
        private SurfaceSession mSession;
        private int mWidth;
        private int mFlags = 4;
        private int mFormat = -1;
        private String mCallsite = "SurfaceControl.Builder";

        public Builder(SurfaceSession surfaceSession) {
            this.mSession = surfaceSession;
        }

        public Builder() {
        }

        public SurfaceControl build() {
            int i;
            int i2 = this.mWidth;
            if (i2 < 0 || (i = this.mHeight) < 0) {
                throw new IllegalStateException("width and height must be positive or unset");
            }
            if ((i2 > 0 || i > 0) && (isEffectLayer() || isContainerLayer())) {
                throw new IllegalStateException("Only buffer layers can set a valid buffer size.");
            }
            return new SurfaceControl(this.mSession, this.mName, this.mWidth, this.mHeight, this.mFormat, this.mFlags, this.mParent, this.mMetadata, this.mLocalOwnerView, this.mCallsite);
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setLocalOwnerView(View view) {
            this.mLocalOwnerView = new WeakReference<>(view);
            return this;
        }

        public Builder setBufferSize(int i, int i2) {
            if (i < 0 || i2 < 0) {
                throw new IllegalArgumentException("width and height must be positive");
            }
            this.mWidth = i;
            this.mHeight = i2;
            return setFlags(0, SurfaceControl.FX_SURFACE_MASK);
        }

        private void unsetBufferSize() {
            this.mWidth = 0;
            this.mHeight = 0;
        }

        public Builder setFormat(int i) {
            this.mFormat = i;
            return this;
        }

        public Builder setProtected(boolean z) {
            if (z) {
                this.mFlags |= 2048;
            } else {
                this.mFlags &= -2049;
            }
            return this;
        }

        public Builder setSecure(boolean z) {
            if (z) {
                this.mFlags |= 128;
            } else {
                this.mFlags &= -129;
            }
            return this;
        }

        public Builder setOpaque(boolean z) {
            if (z) {
                this.mFlags |= 1024;
            } else {
                this.mFlags &= -1025;
            }
            return this;
        }

        public Builder setHidden(boolean z) {
            if (z) {
                this.mFlags |= 4;
            } else {
                this.mFlags &= -5;
            }
            return this;
        }

        public Builder setParent(SurfaceControl surfaceControl) {
            this.mParent = surfaceControl;
            return this;
        }

        public Builder setMetadata(int i, int i2) {
            if (this.mMetadata == null) {
                this.mMetadata = new SparseIntArray();
            }
            this.mMetadata.put(i, i2);
            return this;
        }

        public Builder setEffectLayer() {
            this.mFlags |= 16384;
            unsetBufferSize();
            return setFlags(131072, SurfaceControl.FX_SURFACE_MASK);
        }

        public Builder setColorLayer() {
            unsetBufferSize();
            return setFlags(131072, SurfaceControl.FX_SURFACE_MASK);
        }

        private boolean isEffectLayer() {
            return (this.mFlags & 131072) == 131072;
        }

        public Builder setBLASTLayer() {
            unsetBufferSize();
            return setFlags(262144, SurfaceControl.FX_SURFACE_MASK);
        }

        public Builder setContainerLayer() {
            unsetBufferSize();
            return setFlags(524288, SurfaceControl.FX_SURFACE_MASK);
        }

        private boolean isContainerLayer() {
            return (this.mFlags & 524288) == 524288;
        }

        public Builder setFlags(int i) {
            this.mFlags = i;
            return this;
        }

        public Builder setCallsite(String str) {
            this.mCallsite = str;
            return this;
        }

        private Builder setFlags(int i, int i2) {
            this.mFlags = i | ((~i2) & this.mFlags);
            return this;
        }
    }

    private SurfaceControl(SurfaceSession surfaceSession, String str, int i, int i2, int i3, int i4, SurfaceControl surfaceControl, SparseIntArray sparseIntArray, WeakReference<View> weakReference, String str2) throws Surface.OutOfResourcesException, IllegalArgumentException {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        if (str == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.mName = str;
        this.mWidth = i;
        this.mHeight = i2;
        this.mLocalOwnerView = weakReference;
        Parcel parcelObtain = Parcel.obtain();
        if (sparseIntArray != null) {
            try {
                if (sparseIntArray.size() > 0) {
                    parcelObtain.writeInt(sparseIntArray.size());
                    for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
                        parcelObtain.writeInt(sparseIntArray.keyAt(i5));
                        parcelObtain.writeByteArray(ByteBuffer.allocate(4).order(ByteOrder.nativeOrder()).putInt(sparseIntArray.valueAt(i5)).array());
                    }
                    parcelObtain.setDataPosition(0);
                }
            } catch (Throwable th) {
                parcelObtain.recycle();
                throw th;
            }
        }
        this.mNativeObject = nativeCreate(surfaceSession, str, i, i2, i3, i4, surfaceControl != null ? surfaceControl.mNativeObject : 0L, parcelObtain);
        parcelObtain.recycle();
        long j = this.mNativeObject;
        if (j == 0) {
            throw new Surface.OutOfResourcesException("Couldn't allocate SurfaceControl native object");
        }
        this.mNativeHandle = nativeGetHandle(j);
        this.mCloseGuard.openWithCallSite("release", str2);
    }

    public SurfaceControl(SurfaceControl surfaceControl, String str) {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        copyFrom(surfaceControl, str);
    }

    private SurfaceControl(Parcel parcel) {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        readFromParcel(parcel);
    }

    public SurfaceControl() {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("source must not be null");
        }
        this.mName = parcel.readString8();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        assignNativeObject(parcel.readInt() != 0 ? nativeReadFromParcel(parcel) : 0L, "readFromParcel");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString8(this.mName);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
        if (this.mNativeObject == 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
        }
        nativeWriteToParcel(this.mNativeObject, parcel);
        if ((i & 1) != 0) {
            release();
        }
    }

    public boolean isSameSurface(SurfaceControl surfaceControl) {
        return surfaceControl.mNativeHandle == this.mNativeHandle;
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1120986464257L, System.identityHashCode(this));
        protoOutputStream.write(1138166333442L, this.mName);
        protoOutputStream.end(jStart);
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mCloseGuard != null) {
                this.mCloseGuard.warnIfOpen();
            }
            if (this.mNativeObject != 0) {
                nativeRelease(this.mNativeObject);
            }
        } finally {
            super.finalize();
        }
    }

    public void release() {
        long j = this.mNativeObject;
        if (j != 0) {
            nativeRelease(j);
            this.mNativeObject = 0L;
            this.mNativeHandle = 0L;
            this.mCloseGuard.close();
        }
    }

    public void disconnect() {
        long j = this.mNativeObject;
        if (j != 0) {
            nativeDisconnect(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNotReleased() {
        if (this.mNativeObject != 0) {
            return;
        }
        throw new NullPointerException("Invalid " + this + ", mNativeObject is null. Have you called release() already?");
    }

    public boolean isValid() {
        return this.mNativeObject != 0;
    }

    public static void openTransaction() {
        synchronized (SurfaceControl.class) {
            if (sGlobalTransaction == null) {
                sGlobalTransaction = new Transaction();
            }
            synchronized (SurfaceControl.class) {
                sTransactionNestCount++;
            }
        }
    }

    @Deprecated
    public static void mergeToGlobalTransaction(Transaction transaction) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.merge(transaction);
        }
    }

    public static void closeTransaction() {
        synchronized (SurfaceControl.class) {
            if (sTransactionNestCount == 0) {
                Log.e(TAG, "Call to SurfaceControl.closeTransaction without matching openTransaction");
            } else {
                long j = sTransactionNestCount - 1;
                sTransactionNestCount = j;
                if (j > 0) {
                    return;
                }
            }
            sGlobalTransaction.apply();
        }
    }

    public void deferTransactionUntil(SurfaceControl surfaceControl, long j) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.deferTransactionUntil(this, surfaceControl, j);
        }
    }

    public void reparentChildren(SurfaceControl surfaceControl) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.reparentChildren(this, surfaceControl);
        }
    }

    public void detachChildren() {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.detachChildren(this);
        }
    }

    public void setOverrideScalingMode(int i) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setOverrideScalingMode(this, i);
        }
    }

    public void setLayer(int i) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setLayer(this, i);
        }
    }

    public void setPosition(float f, float f2) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setPosition(this, f, f2);
        }
    }

    public void setBufferSize(int i, int i2) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setBufferSize(this, i, i2);
        }
    }

    public void hide() {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.hide(this);
        }
    }

    public void show() {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.show(this);
        }
    }

    public void setTransparentRegionHint(Region region) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setTransparentRegionHint(this, region);
        }
    }

    public boolean clearContentFrameStats() {
        checkNotReleased();
        return nativeClearContentFrameStats(this.mNativeObject);
    }

    public boolean getContentFrameStats(WindowContentFrameStats windowContentFrameStats) {
        checkNotReleased();
        return nativeGetContentFrameStats(this.mNativeObject, windowContentFrameStats);
    }

    public static boolean clearAnimationFrameStats() {
        return nativeClearAnimationFrameStats();
    }

    public static boolean getAnimationFrameStats(WindowAnimationFrameStats windowAnimationFrameStats) {
        return nativeGetAnimationFrameStats(windowAnimationFrameStats);
    }

    public void setAlpha(float f) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setAlpha(this, f);
        }
    }

    public void setMatrix(float f, float f2, float f3, float f4) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setMatrix(this, f, f2, f3, f4);
        }
    }

    public void setColorSpaceAgnostic(boolean z) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setColorSpaceAgnostic(this, z);
        }
    }

    public void setWindowCrop(Rect rect) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setWindowCrop(this, rect);
        }
    }

    public void setOpaque(boolean z) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setOpaque(this, z);
        }
    }

    public void setSecure(boolean z) {
        checkNotReleased();
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setSecure(this, z);
        }
    }

    public int getWidth() {
        int i;
        synchronized (this.mLock) {
            i = this.mWidth;
        }
        return i;
    }

    public int getHeight() {
        int i;
        synchronized (this.mLock) {
            i = this.mHeight;
        }
        return i;
    }

    public View getLocalOwnerView() {
        WeakReference<View> weakReference = this.mLocalOwnerView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public String toString() {
        return "Surface(name=" + this.mName + ")/@0x" + Integer.toHexString(System.identityHashCode(this));
    }

    public static final class DisplayInfo {
        public float density;
        public DeviceProductInfo deviceProductInfo;
        public boolean isInternal;
        public boolean secure;

        public String toString() {
            return "DisplayInfo{isInternal=" + this.isInternal + ", density=" + this.density + ", secure=" + this.secure + ", deviceProductInfo=" + this.deviceProductInfo + "}";
        }
    }

    public static final class DisplayConfig {
        public static final int INVALID_DISPLAY_CONFIG_ID = -1;
        public long appVsyncOffsetNanos;
        public int configGroup;
        public int height;
        public long presentationDeadlineNanos;
        public float refreshRate;
        public int width;
        public float xDpi;
        public float yDpi;

        public String toString() {
            return "DisplayConfig{width=" + this.width + ", height=" + this.height + ", xDpi=" + this.xDpi + ", yDpi=" + this.yDpi + ", refreshRate=" + this.refreshRate + ", appVsyncOffsetNanos=" + this.appVsyncOffsetNanos + ", presentationDeadlineNanos=" + this.presentationDeadlineNanos + ", configGroup=" + this.configGroup + "}";
        }
    }

    public static void setDisplayPowerMode(IBinder iBinder, int i) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        nativeSetDisplayPowerMode(iBinder, i);
    }

    public static DisplayInfo getDisplayInfo(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayInfo(iBinder);
    }

    public static DisplayConfig[] getDisplayConfigs(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayConfigs(iBinder);
    }

    public static int getActiveConfig(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetActiveConfig(iBinder);
    }

    public static DisplayedContentSamplingAttributes getDisplayedContentSamplingAttributes(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayedContentSamplingAttributes(iBinder);
    }

    public static boolean setDisplayedContentSamplingEnabled(IBinder iBinder, boolean z, int i, int i2) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        if ((i >> 4) != 0) {
            throw new IllegalArgumentException("invalid componentMask when enabling sampling");
        }
        return nativeSetDisplayedContentSamplingEnabled(iBinder, z, i, i2);
    }

    public static DisplayedContentSample getDisplayedContentSample(IBinder iBinder, long j, long j2) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayedContentSample(iBinder, j, j2);
    }

    public static final class DesiredDisplayConfigSpecs {
        public float appRequestRefreshRateMax;
        public float appRequestRefreshRateMin;
        public int defaultConfig;
        public float primaryRefreshRateMax;
        public float primaryRefreshRateMin;

        public int hashCode() {
            return 0;
        }

        public DesiredDisplayConfigSpecs() {
        }

        public DesiredDisplayConfigSpecs(DesiredDisplayConfigSpecs desiredDisplayConfigSpecs) {
            copyFrom(desiredDisplayConfigSpecs);
        }

        public DesiredDisplayConfigSpecs(int i, float f, float f2, float f3, float f4) {
            this.defaultConfig = i;
            this.primaryRefreshRateMin = f;
            this.primaryRefreshRateMax = f2;
            this.appRequestRefreshRateMin = f3;
            this.appRequestRefreshRateMax = f4;
        }

        public boolean equals(Object obj) {
            return (obj instanceof DesiredDisplayConfigSpecs) && equals((DesiredDisplayConfigSpecs) obj);
        }

        public boolean equals(DesiredDisplayConfigSpecs desiredDisplayConfigSpecs) {
            return desiredDisplayConfigSpecs != null && this.defaultConfig == desiredDisplayConfigSpecs.defaultConfig && this.primaryRefreshRateMin == desiredDisplayConfigSpecs.primaryRefreshRateMin && this.primaryRefreshRateMax == desiredDisplayConfigSpecs.primaryRefreshRateMax && this.appRequestRefreshRateMin == desiredDisplayConfigSpecs.appRequestRefreshRateMin && this.appRequestRefreshRateMax == desiredDisplayConfigSpecs.appRequestRefreshRateMax;
        }

        public void copyFrom(DesiredDisplayConfigSpecs desiredDisplayConfigSpecs) {
            this.defaultConfig = desiredDisplayConfigSpecs.defaultConfig;
            this.primaryRefreshRateMin = desiredDisplayConfigSpecs.primaryRefreshRateMin;
            this.primaryRefreshRateMax = desiredDisplayConfigSpecs.primaryRefreshRateMax;
            this.appRequestRefreshRateMin = desiredDisplayConfigSpecs.appRequestRefreshRateMin;
            this.appRequestRefreshRateMax = desiredDisplayConfigSpecs.appRequestRefreshRateMax;
        }

        public String toString() {
            return String.format("defaultConfig=%d primaryRefreshRateRange=[%.0f %.0f] appRequestRefreshRateRange=[%.0f %.0f]", Integer.valueOf(this.defaultConfig), Float.valueOf(this.primaryRefreshRateMin), Float.valueOf(this.primaryRefreshRateMax), Float.valueOf(this.appRequestRefreshRateMin), Float.valueOf(this.appRequestRefreshRateMax));
        }
    }

    public static boolean setDesiredDisplayConfigSpecs(IBinder iBinder, DesiredDisplayConfigSpecs desiredDisplayConfigSpecs) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeSetDesiredDisplayConfigSpecs(iBinder, desiredDisplayConfigSpecs);
    }

    public static DesiredDisplayConfigSpecs getDesiredDisplayConfigSpecs(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDesiredDisplayConfigSpecs(iBinder);
    }

    public static int[] getDisplayColorModes(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayColorModes(iBinder);
    }

    public static DisplayPrimaries getDisplayNativePrimaries(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetDisplayNativePrimaries(iBinder);
    }

    public static int getActiveColorMode(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetActiveColorMode(iBinder);
    }

    public static boolean setActiveColorMode(IBinder iBinder, int i) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeSetActiveColorMode(iBinder, i);
    }

    public static ColorSpace[] getCompositionColorSpaces() {
        int[] iArrNativeGetCompositionDataspaces = nativeGetCompositionDataspaces();
        ColorSpace colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        ColorSpace[] colorSpaceArr = new ColorSpace[2];
        colorSpaceArr[0] = colorSpace;
        colorSpaceArr[1] = colorSpace;
        if (iArrNativeGetCompositionDataspaces.length == 2) {
            for (int i = 0; i < 2; i++) {
                int i2 = iArrNativeGetCompositionDataspaces[i];
                if (i2 == INTERNAL_DATASPACE_DISPLAY_P3) {
                    colorSpaceArr[i] = ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
                } else if (i2 == INTERNAL_DATASPACE_SCRGB) {
                    colorSpaceArr[i] = ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
                }
            }
        }
        return colorSpaceArr;
    }

    public static void setAutoLowLatencyMode(IBinder iBinder, boolean z) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        nativeSetAutoLowLatencyMode(iBinder, z);
    }

    public static void setGameContentType(IBinder iBinder, boolean z) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        nativeSetGameContentType(iBinder, z);
    }

    public static void setDisplayProjection(IBinder iBinder, int i, Rect rect, Rect rect2) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplayProjection(iBinder, i, rect, rect2);
        }
    }

    public static void setDisplayLayerStack(IBinder iBinder, int i) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplayLayerStack(iBinder, i);
        }
    }

    public static void setDisplaySurface(IBinder iBinder, Surface surface) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplaySurface(iBinder, surface);
        }
    }

    public static void setDisplaySize(IBinder iBinder, int i, int i2) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplaySize(iBinder, i, i2);
        }
    }

    public static Display.HdrCapabilities getHdrCapabilities(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetHdrCapabilities(iBinder);
    }

    public static boolean getAutoLowLatencyModeSupport(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetAutoLowLatencyModeSupport(iBinder);
    }

    public static boolean getGameContentTypeSupport(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeGetGameContentTypeSupport(iBinder);
    }

    public static IBinder createDisplay(String str, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        return nativeCreateDisplay(str, z);
    }

    public static void destroyDisplay(IBinder iBinder) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        nativeDestroyDisplay(iBinder);
    }

    public static long[] getPhysicalDisplayIds() {
        return nativeGetPhysicalDisplayIds();
    }

    public static IBinder getPhysicalDisplayToken(long j) {
        return nativeGetPhysicalDisplayToken(j);
    }

    public static IBinder getInternalDisplayToken() {
        long[] physicalDisplayIds = getPhysicalDisplayIds();
        if (physicalDisplayIds.length == 0) {
            return null;
        }
        return getPhysicalDisplayToken(physicalDisplayIds[0]);
    }

    public static void screenshot(IBinder iBinder, Surface surface) {
        screenshot(iBinder, surface, new Rect(), 0, 0, false, 0);
    }

    public static void screenshot(IBinder iBinder, Surface surface, Rect rect, int i, int i2, boolean z, int i3) {
        if (surface == null) {
            throw new IllegalArgumentException("consumer must not be null");
        }
        ScreenshotGraphicBuffer screenshotGraphicBufferScreenshotToBuffer = screenshotToBuffer(iBinder, rect, i, i2, z, i3);
        try {
            surface.attachAndQueueBufferWithColorSpace(screenshotGraphicBufferScreenshotToBuffer.getGraphicBuffer(), screenshotGraphicBufferScreenshotToBuffer.getColorSpace());
        } catch (RuntimeException e) {
            Log.w(TAG, "Failed to take screenshot - " + e.getMessage());
        }
    }

    public static Bitmap screenshot(Rect rect, int i, int i2, int i3) {
        return screenshot(rect, i, i2, false, i3);
    }

    public static Bitmap screenshot(Rect rect, int i, int i2, boolean z, int i3) {
        IBinder internalDisplayToken = getInternalDisplayToken();
        if (internalDisplayToken == null) {
            Log.w(TAG, "Failed to take screenshot because internal display is disconnected");
            return null;
        }
        if (i3 == 1 || i3 == 3) {
            i3 = i3 == 1 ? 3 : 1;
        }
        int i4 = i3;
        rotateCropForSF(rect, i4);
        ScreenshotGraphicBuffer screenshotGraphicBufferScreenshotToBuffer = screenshotToBuffer(internalDisplayToken, rect, i, i2, z, i4);
        if (screenshotGraphicBufferScreenshotToBuffer == null) {
            Log.w(TAG, "Failed to take screenshot");
            return null;
        }
        return Bitmap.wrapHardwareBuffer(screenshotGraphicBufferScreenshotToBuffer.getGraphicBuffer(), screenshotGraphicBufferScreenshotToBuffer.getColorSpace());
    }

    public static ScreenshotGraphicBuffer screenshotToBuffer(IBinder iBinder, Rect rect, int i, int i2, boolean z, int i3) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeScreenshot(iBinder, rect, i, i2, z, i3, false);
    }

    public static ScreenshotGraphicBuffer screenshotToBufferWithSecureLayersUnsafe(IBinder iBinder, Rect rect, int i, int i2, boolean z, int i3) {
        if (iBinder == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        }
        return nativeScreenshot(iBinder, rect, i, i2, z, i3, true);
    }

    private static void rotateCropForSF(Rect rect, int i) {
        if (i == 1 || i == 3) {
            int i2 = rect.top;
            rect.top = rect.left;
            rect.left = i2;
            int i3 = rect.right;
            rect.right = rect.bottom;
            rect.bottom = i3;
        }
    }

    public static ScreenshotGraphicBuffer captureLayers(SurfaceControl surfaceControl, Rect rect, float f) {
        return captureLayers(surfaceControl, rect, f, 1);
    }

    public static ScreenshotGraphicBuffer captureLayers(SurfaceControl surfaceControl, Rect rect, float f, int i) {
        return nativeCaptureLayers(getInternalDisplayToken(), surfaceControl.mNativeObject, rect, f, null, i);
    }

    public static ScreenshotGraphicBuffer captureLayersExcluding(SurfaceControl surfaceControl, Rect rect, float f, int i, SurfaceControl[] surfaceControlArr) {
        IBinder internalDisplayToken = getInternalDisplayToken();
        long[] jArr = new long[surfaceControlArr.length];
        for (int i2 = 0; i2 < surfaceControlArr.length; i2++) {
            jArr[i2] = surfaceControlArr[i2].mNativeObject;
        }
        return nativeCaptureLayers(internalDisplayToken, surfaceControl.mNativeObject, rect, f, jArr, 1);
    }

    public static boolean getProtectedContentSupport() {
        return nativeGetProtectedContentSupport();
    }

    public static boolean getDisplayBrightnessSupport(IBinder iBinder) {
        return nativeGetDisplayBrightnessSupport(iBinder);
    }

    public static boolean setDisplayBrightness(IBinder iBinder, float f) {
        Objects.requireNonNull(iBinder);
        if (Float.isNaN(f) || f > 1.0f || (f < 0.0f && f != -1.0f)) {
            throw new IllegalArgumentException("brightness must be a number between 0.0f and 1.0f, or -1 to turn the backlight off.");
        }
        return nativeSetDisplayBrightness(iBinder, f);
    }

    public static SurfaceControl mirrorSurface(SurfaceControl surfaceControl) {
        long jNativeMirrorSurface = nativeMirrorSurface(surfaceControl.mNativeObject);
        SurfaceControl surfaceControl2 = new SurfaceControl();
        surfaceControl2.assignNativeObject(jNativeMirrorSurface, "mirrorSurface");
        return surfaceControl2;
    }

    private static void validateColorArg(float[] fArr) {
        if (fArr.length != 4) {
            throw new IllegalArgumentException("Color must be specified as a float array with four values to represent r, g, b, a in range [0..1]");
        }
        for (float f : fArr) {
            if (f < 0.0f || f > 1.0f) {
                throw new IllegalArgumentException("Color must be specified as a float array with four values to represent r, g, b, a in range [0..1]");
            }
        }
    }

    public static void setGlobalShadowSettings(float[] fArr, float[] fArr2, float f, float f2, float f3) {
        validateColorArg(fArr);
        validateColorArg(fArr2);
        nativeSetGlobalShadowSettings(fArr, fArr2, f, f2, f3);
    }

    public static class Transaction implements Closeable, Parcelable {
        Runnable mFreeNativeResources;
        public long mNativeObject;
        private final ArrayMap<SurfaceControl, SurfaceControl> mReparentedSurfaces;
        private final ArrayMap<SurfaceControl, Point> mResizedSurfaces;
        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(Transaction.class.getClassLoader(), SurfaceControl.nativeGetNativeTransactionFinalizer(), 512);
        private static final float[] INVALID_COLOR = {-1.0f, -1.0f, -1.0f};
        public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() { // from class: android.view.SurfaceControl.Transaction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transaction createFromParcel(Parcel parcel) {
                return new Transaction(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transaction[] newArray(int i) {
                return new Transaction[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected void checkPreconditions(SurfaceControl surfaceControl) {
            surfaceControl.checkNotReleased();
        }

        public Transaction() {
            this.mResizedSurfaces = new ArrayMap<>();
            this.mReparentedSurfaces = new ArrayMap<>();
            long jNativeCreateTransaction = SurfaceControl.nativeCreateTransaction();
            this.mNativeObject = jNativeCreateTransaction;
            this.mFreeNativeResources = sRegistry.registerNativeAllocation(this, jNativeCreateTransaction);
        }

        private Transaction(Parcel parcel) {
            this.mResizedSurfaces = new ArrayMap<>();
            this.mReparentedSurfaces = new ArrayMap<>();
            readFromParcel(parcel);
        }

        public void apply() {
            apply(false);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mResizedSurfaces.clear();
            this.mReparentedSurfaces.clear();
            this.mFreeNativeResources.run();
            this.mNativeObject = 0L;
        }

        public void apply(boolean z) {
            applyResizedSurfaces();
            notifyReparentedSurfaces();
            SurfaceControl.nativeApplyTransaction(this.mNativeObject, z);
        }

        private void applyResizedSurfaces() {
            for (int size = this.mResizedSurfaces.size() - 1; size >= 0; size--) {
                Point pointValueAt = this.mResizedSurfaces.valueAt(size);
                SurfaceControl surfaceControlKeyAt = this.mResizedSurfaces.keyAt(size);
                synchronized (surfaceControlKeyAt.mLock) {
                    surfaceControlKeyAt.mWidth = pointValueAt.x;
                    surfaceControlKeyAt.mHeight = pointValueAt.y;
                }
            }
            this.mResizedSurfaces.clear();
        }

        private void notifyReparentedSurfaces() {
            for (int size = this.mReparentedSurfaces.size() - 1; size >= 0; size--) {
                SurfaceControl surfaceControlKeyAt = this.mReparentedSurfaces.keyAt(size);
                synchronized (surfaceControlKeyAt.mLock) {
                    int size2 = surfaceControlKeyAt.mReparentListeners != null ? surfaceControlKeyAt.mReparentListeners.size() : 0;
                    for (int i = 0; i < size2; i++) {
                        ((OnReparentListener) surfaceControlKeyAt.mReparentListeners.get(i)).onReparent(this, this.mReparentedSurfaces.valueAt(size));
                    }
                    this.mReparentedSurfaces.removeAt(size);
                }
            }
        }

        public Transaction setVisibility(SurfaceControl surfaceControl, boolean z) {
            checkPreconditions(surfaceControl);
            if (z) {
                return show(surfaceControl);
            }
            return hide(surfaceControl);
        }

        public Transaction setFrameRateSelectionPriority(SurfaceControl surfaceControl, int i) {
            surfaceControl.checkNotReleased();
            SurfaceControl.nativeSetFrameRateSelectionPriority(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction show(SurfaceControl surfaceControl) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 0, 1);
            return this;
        }

        public Transaction hide(SurfaceControl surfaceControl) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 1, 1);
            return this;
        }

        public Transaction setPosition(SurfaceControl surfaceControl, float f, float f2) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetPosition(this.mNativeObject, surfaceControl.mNativeObject, f, f2);
            return this;
        }

        public Transaction setBufferSize(SurfaceControl surfaceControl, int i, int i2) {
            checkPreconditions(surfaceControl);
            this.mResizedSurfaces.put(surfaceControl, new Point(i, i2));
            SurfaceControl.nativeSetSize(this.mNativeObject, surfaceControl.mNativeObject, i, i2);
            return this;
        }

        public Transaction setFixedTransformHint(SurfaceControl surfaceControl, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetFixedTransformHint(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction unsetFixedTransformHint(SurfaceControl surfaceControl) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetFixedTransformHint(this.mNativeObject, surfaceControl.mNativeObject, -1);
            return this;
        }

        public Transaction setLayer(SurfaceControl surfaceControl, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetLayer(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction setRelativeLayer(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetRelativeLayer(this.mNativeObject, surfaceControl.mNativeObject, surfaceControl2.mNativeObject, i);
            return this;
        }

        public Transaction setTransparentRegionHint(SurfaceControl surfaceControl, Region region) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetTransparentRegionHint(this.mNativeObject, surfaceControl.mNativeObject, region);
            return this;
        }

        public Transaction setAlpha(SurfaceControl surfaceControl, float f) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetAlpha(this.mNativeObject, surfaceControl.mNativeObject, f);
            return this;
        }

        public Transaction setInputWindowInfo(SurfaceControl surfaceControl, InputWindowHandle inputWindowHandle) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetInputWindowInfo(this.mNativeObject, surfaceControl.mNativeObject, inputWindowHandle);
            return this;
        }

        public Transaction syncInputWindows() {
            SurfaceControl.nativeSyncInputWindows(this.mNativeObject);
            return this;
        }

        public Transaction setGeometry(SurfaceControl surfaceControl, Rect rect, Rect rect2, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetGeometry(this.mNativeObject, surfaceControl.mNativeObject, rect, rect2, i);
            return this;
        }

        public Transaction setMatrix(SurfaceControl surfaceControl, float f, float f2, float f3, float f4) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetMatrix(this.mNativeObject, surfaceControl.mNativeObject, f, f2, f3, f4);
            return this;
        }

        public Transaction setMatrix(SurfaceControl surfaceControl, Matrix matrix, float[] fArr) {
            matrix.getValues(fArr);
            setMatrix(surfaceControl, fArr[0], fArr[3], fArr[1], fArr[4]);
            setPosition(surfaceControl, fArr[2], fArr[5]);
            return this;
        }

        public Transaction setColorTransform(SurfaceControl surfaceControl, float[] fArr, float[] fArr2) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetColorTransform(this.mNativeObject, surfaceControl.mNativeObject, fArr, fArr2);
            return this;
        }

        public Transaction setColorSpaceAgnostic(SurfaceControl surfaceControl, boolean z) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetColorSpaceAgnostic(this.mNativeObject, surfaceControl.mNativeObject, z);
            return this;
        }

        public Transaction setWindowCrop(SurfaceControl surfaceControl, Rect rect) {
            checkPreconditions(surfaceControl);
            if (rect != null) {
                SurfaceControl.nativeSetWindowCrop(this.mNativeObject, surfaceControl.mNativeObject, rect.left, rect.top, rect.right, rect.bottom);
            } else {
                SurfaceControl.nativeSetWindowCrop(this.mNativeObject, surfaceControl.mNativeObject, 0, 0, 0, 0);
            }
            return this;
        }

        public Transaction setWindowCrop(SurfaceControl surfaceControl, int i, int i2) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetWindowCrop(this.mNativeObject, surfaceControl.mNativeObject, 0, 0, i, i2);
            return this;
        }

        public Transaction setCornerRadius(SurfaceControl surfaceControl, float f) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetCornerRadius(this.mNativeObject, surfaceControl.mNativeObject, f);
            return this;
        }

        public Transaction setBackgroundBlurRadius(SurfaceControl surfaceControl, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetBackgroundBlurRadius(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction setLayerStack(SurfaceControl surfaceControl, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetLayerStack(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction deferTransactionUntil(SurfaceControl surfaceControl, SurfaceControl surfaceControl2, long j) {
            if (j < 0) {
                return this;
            }
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeDeferTransactionUntil(this.mNativeObject, surfaceControl.mNativeObject, surfaceControl2.mNativeObject, j);
            return this;
        }

        @Deprecated
        public Transaction deferTransactionUntilSurface(SurfaceControl surfaceControl, Surface surface, long j) {
            if (j < 0) {
                return this;
            }
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeDeferTransactionUntilSurface(this.mNativeObject, surfaceControl.mNativeObject, surface.mNativeObject, j);
            return this;
        }

        public Transaction reparentChildren(SurfaceControl surfaceControl, SurfaceControl surfaceControl2) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeReparentChildren(this.mNativeObject, surfaceControl.mNativeObject, surfaceControl2.mNativeObject);
            return this;
        }

        public Transaction reparent(SurfaceControl surfaceControl, SurfaceControl surfaceControl2) {
            long j;
            checkPreconditions(surfaceControl);
            if (surfaceControl2 != null) {
                surfaceControl2.checkNotReleased();
                j = surfaceControl2.mNativeObject;
            } else {
                j = 0;
            }
            SurfaceControl.nativeReparent(this.mNativeObject, surfaceControl.mNativeObject, j);
            this.mReparentedSurfaces.put(surfaceControl, surfaceControl2);
            return this;
        }

        public Transaction detachChildren(SurfaceControl surfaceControl) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSeverChildren(this.mNativeObject, surfaceControl.mNativeObject);
            return this;
        }

        public Transaction setOverrideScalingMode(SurfaceControl surfaceControl, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetOverrideScalingMode(this.mNativeObject, surfaceControl.mNativeObject, i);
            return this;
        }

        public Transaction setColor(SurfaceControl surfaceControl, float[] fArr) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetColor(this.mNativeObject, surfaceControl.mNativeObject, fArr);
            return this;
        }

        public Transaction unsetColor(SurfaceControl surfaceControl) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetColor(this.mNativeObject, surfaceControl.mNativeObject, INVALID_COLOR);
            return this;
        }

        public Transaction setSecure(SurfaceControl surfaceControl, boolean z) {
            checkPreconditions(surfaceControl);
            if (z) {
                SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 128, 128);
            } else {
                SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 0, 128);
            }
            return this;
        }

        public Transaction setOpaque(SurfaceControl surfaceControl, boolean z) {
            checkPreconditions(surfaceControl);
            if (z) {
                SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 2, 2);
            } else {
                SurfaceControl.nativeSetFlags(this.mNativeObject, surfaceControl.mNativeObject, 0, 2);
            }
            return this;
        }

        public Transaction setDisplaySurface(IBinder iBinder, Surface surface) {
            if (iBinder == null) {
                throw new IllegalArgumentException("displayToken must not be null");
            }
            if (surface == null) {
                SurfaceControl.nativeSetDisplaySurface(this.mNativeObject, iBinder, 0L);
            } else {
                synchronized (surface.mLock) {
                    SurfaceControl.nativeSetDisplaySurface(this.mNativeObject, iBinder, surface.mNativeObject);
                }
            }
            return this;
        }

        public Transaction setDisplayLayerStack(IBinder iBinder, int i) {
            if (iBinder != null) {
                SurfaceControl.nativeSetDisplayLayerStack(this.mNativeObject, iBinder, i);
                return this;
            }
            throw new IllegalArgumentException("displayToken must not be null");
        }

        public Transaction setDisplayProjection(IBinder iBinder, int i, Rect rect, Rect rect2) {
            if (iBinder == null) {
                throw new IllegalArgumentException("displayToken must not be null");
            }
            if (rect == null) {
                throw new IllegalArgumentException("layerStackRect must not be null");
            }
            if (rect2 != null) {
                SurfaceControl.nativeSetDisplayProjection(this.mNativeObject, iBinder, i, rect.left, rect.top, rect.right, rect.bottom, rect2.left, rect2.top, rect2.right, rect2.bottom);
                return this;
            }
            throw new IllegalArgumentException("displayRect must not be null");
        }

        public Transaction setDisplaySize(IBinder iBinder, int i, int i2) {
            if (iBinder == null) {
                throw new IllegalArgumentException("displayToken must not be null");
            }
            if (i > 0 && i2 > 0) {
                SurfaceControl.nativeSetDisplaySize(this.mNativeObject, iBinder, i, i2);
                return this;
            }
            throw new IllegalArgumentException("width and height must be positive");
        }

        public Transaction setAnimationTransaction() {
            SurfaceControl.nativeSetAnimationTransaction(this.mNativeObject);
            return this;
        }

        @Deprecated
        public Transaction setEarlyWakeup() {
            SurfaceControl.nativeSetEarlyWakeup(this.mNativeObject);
            return this;
        }

        public Transaction setEarlyWakeupStart() {
            SurfaceControl.nativeSetEarlyWakeupStart(this.mNativeObject);
            return this;
        }

        public Transaction setEarlyWakeupEnd() {
            SurfaceControl.nativeSetEarlyWakeupEnd(this.mNativeObject);
            return this;
        }

        public Transaction setMetadata(SurfaceControl surfaceControl, int i, int i2) {
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInt(i2);
            try {
                setMetadata(surfaceControl, i, parcelObtain);
                return this;
            } finally {
                parcelObtain.recycle();
            }
        }

        public Transaction setMetadata(SurfaceControl surfaceControl, int i, Parcel parcel) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetMetadata(this.mNativeObject, surfaceControl.mNativeObject, i, parcel);
            return this;
        }

        public Transaction setShadowRadius(SurfaceControl surfaceControl, float f) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetShadowRadius(this.mNativeObject, surfaceControl.mNativeObject, f);
            return this;
        }

        public Transaction setFrameRate(SurfaceControl surfaceControl, float f, int i) {
            checkPreconditions(surfaceControl);
            SurfaceControl.nativeSetFrameRate(this.mNativeObject, surfaceControl.mNativeObject, f, i);
            return this;
        }

        public Transaction merge(Transaction transaction) {
            if (this == transaction) {
                return this;
            }
            this.mResizedSurfaces.putAll((ArrayMap<? extends SurfaceControl, ? extends Point>) transaction.mResizedSurfaces);
            transaction.mResizedSurfaces.clear();
            this.mReparentedSurfaces.putAll((ArrayMap<? extends SurfaceControl, ? extends SurfaceControl>) transaction.mReparentedSurfaces);
            transaction.mReparentedSurfaces.clear();
            SurfaceControl.nativeMergeTransaction(this.mNativeObject, transaction.mNativeObject);
            return this;
        }

        public Transaction remove(SurfaceControl surfaceControl) {
            reparent(surfaceControl, null);
            surfaceControl.release();
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.mNativeObject == 0) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
            }
            SurfaceControl.nativeWriteTransactionToParcel(this.mNativeObject, parcel);
        }

        private void readFromParcel(Parcel parcel) {
            this.mNativeObject = 0L;
            if (parcel.readInt() != 0) {
                long jNativeReadTransactionFromParcel = SurfaceControl.nativeReadTransactionFromParcel(parcel);
                this.mNativeObject = jNativeReadTransactionFromParcel;
                this.mFreeNativeResources = sRegistry.registerNativeAllocation(this, jNativeReadTransactionFromParcel);
            }
        }
    }

    public static class LockDebuggingTransaction extends Transaction {
        Object mMonitor;

        public LockDebuggingTransaction(Object obj) {
            this.mMonitor = obj;
        }

        @Override // android.view.SurfaceControl.Transaction
        protected void checkPreconditions(SurfaceControl surfaceControl) {
            super.checkPreconditions(surfaceControl);
            if (!Thread.holdsLock(this.mMonitor)) {
                throw new RuntimeException("Unlocked access to synchronized SurfaceControl.Transaction");
            }
        }
    }

    public static long acquireFrameRateFlexibilityToken() {
        return nativeAcquireFrameRateFlexibilityToken();
    }

    public static void releaseFrameRateFlexibilityToken(long j) {
        nativeReleaseFrameRateFlexibilityToken(j);
    }
}
