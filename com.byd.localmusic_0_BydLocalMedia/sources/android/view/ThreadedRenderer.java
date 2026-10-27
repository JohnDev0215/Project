package android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.HardwareRenderer;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.os.Trace;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Surface;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.android.internal.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class ThreadedRenderer extends HardwareRenderer {
    public static final String DEBUG_DIRTY_REGIONS_PROPERTY = "debug.hwui.show_dirty_regions";
    public static final String DEBUG_FORCE_DARK = "debug.hwui.force_dark";
    public static final String DEBUG_FPS_DIVISOR = "debug.hwui.fps_divisor";
    public static final String DEBUG_OVERDRAW_PROPERTY = "debug.hwui.overdraw";
    public static final String DEBUG_SHOW_LAYERS_UPDATES_PROPERTY = "debug.hwui.show_layers_updates";
    public static final String DEBUG_SHOW_NON_RECTANGULAR_CLIP_PROPERTY = "debug.hwui.show_non_rect_clip";
    public static int EGL_CONTEXT_PRIORITY_HIGH_IMG = 12545;
    public static int EGL_CONTEXT_PRIORITY_LOW_IMG = 12547;
    public static int EGL_CONTEXT_PRIORITY_MEDIUM_IMG = 12546;
    public static final String OVERDRAW_PROPERTY_SHOW = "show";
    static final String PRINT_CONFIG_PROPERTY = "debug.hwui.print_config";
    static final String PROFILE_MAXFRAMES_PROPERTY = "debug.hwui.profile.maxframes";
    public static final String PROFILE_PROPERTY = "debug.hwui.profile";
    public static final String PROFILE_PROPERTY_VISUALIZE_BARS = "visual_bars";
    private static final String[] VISUALIZERS;
    public static boolean sRendererDisabled;
    public static boolean sSystemRendererDisabled;
    public static boolean sTrimForeground;
    private boolean mEnabled;
    private int mHeight;
    private int mInsetLeft;
    private int mInsetTop;
    private final float mLightRadius;
    private final float mLightY;
    private final float mLightZ;
    private ArrayList<HardwareRenderer.FrameDrawingCallback> mNextRtFrameCallbacks;
    private boolean mRootNodeNeedsUpdate;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private int mWidth;
    private boolean mInitialized = false;
    private boolean mRequested = true;

    interface DrawCallbacks {
        void onPostDraw(RecordingCanvas recordingCanvas);

        void onPreDraw(RecordingCanvas recordingCanvas);
    }

    public static boolean isAvailable() {
        return true;
    }

    Picture captureRenderingCommands() {
        return null;
    }

    static {
        isAvailable();
        sRendererDisabled = false;
        sSystemRendererDisabled = false;
        sTrimForeground = false;
        VISUALIZERS = new String[]{PROFILE_PROPERTY_VISUALIZE_BARS};
    }

    public static void disable(boolean z) {
        sRendererDisabled = true;
        if (z) {
            sSystemRendererDisabled = true;
        }
    }

    public static void enableForegroundTrimming() {
        sTrimForeground = true;
    }

    public static ThreadedRenderer create(Context context, boolean z, String str) {
        if (isAvailable()) {
            return new ThreadedRenderer(context, z, str);
        }
        return null;
    }

    ThreadedRenderer(Context context, boolean z, String str) {
        setName(str);
        setOpaque(!z);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.Lighting, 0, 0);
        this.mLightY = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
        this.mLightZ = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.mLightRadius = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        float f = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
        float f2 = typedArrayObtainStyledAttributes.getFloat(1, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        setLightSourceAlpha(f, f2);
    }

    @Override // android.graphics.HardwareRenderer
    public void destroy() {
        this.mInitialized = false;
        updateEnabledState(null);
        super.destroy();
    }

    boolean isEnabled() {
        return this.mEnabled;
    }

    void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    boolean isRequested() {
        return this.mRequested;
    }

    void setRequested(boolean z) {
        this.mRequested = z;
    }

    private void updateEnabledState(Surface surface) {
        if (surface == null || !surface.isValid()) {
            setEnabled(false);
        } else {
            setEnabled(this.mInitialized);
        }
    }

    boolean initialize(Surface surface) throws Surface.OutOfResourcesException {
        boolean z = !this.mInitialized;
        this.mInitialized = true;
        updateEnabledState(surface);
        setSurface(surface);
        return z;
    }

    boolean initializeIfNeeded(int i, int i2, View.AttachInfo attachInfo, Surface surface, Rect rect) throws Surface.OutOfResourcesException {
        if (!isRequested() || isEnabled() || !initialize(surface)) {
            return false;
        }
        setup(i, i2, attachInfo, rect);
        return true;
    }

    void updateSurface(Surface surface) throws Surface.OutOfResourcesException {
        updateEnabledState(surface);
        setSurface(surface);
    }

    @Override // android.graphics.HardwareRenderer
    public void setSurface(Surface surface) {
        if (surface != null && surface.isValid()) {
            super.setSurface(surface);
        } else {
            super.setSurface(null);
        }
    }

    void registerRtFrameCallback(HardwareRenderer.FrameDrawingCallback frameDrawingCallback) {
        if (this.mNextRtFrameCallbacks == null) {
            this.mNextRtFrameCallbacks = new ArrayList<>();
        }
        this.mNextRtFrameCallbacks.add(frameDrawingCallback);
    }

    void destroyHardwareResources(View view) {
        destroyResources(view);
        clearContent();
    }

    private static void destroyResources(View view) {
        view.destroyHardwareResources();
    }

    void setup(int i, int i2, View.AttachInfo attachInfo, Rect rect) {
        this.mWidth = i;
        this.mHeight = i2;
        if (rect != null && (rect.left != 0 || rect.right != 0 || rect.top != 0 || rect.bottom != 0)) {
            this.mInsetLeft = rect.left;
            this.mInsetTop = rect.top;
            this.mSurfaceWidth = i + this.mInsetLeft + rect.right;
            this.mSurfaceHeight = i2 + this.mInsetTop + rect.bottom;
            setOpaque(false);
        } else {
            this.mInsetLeft = 0;
            this.mInsetTop = 0;
            this.mSurfaceWidth = i;
            this.mSurfaceHeight = i2;
        }
        this.mRootNode.setLeftTopRightBottom(-this.mInsetLeft, -this.mInsetTop, this.mSurfaceWidth, this.mSurfaceHeight);
        setLightCenter(attachInfo);
    }

    void setLightCenter(View.AttachInfo attachInfo) {
        attachInfo.mDisplay.getRealSize(attachInfo.mPoint);
        setLightSourceGeometry((r0.x / 2.0f) - attachInfo.mWindowLeft, this.mLightY - attachInfo.mWindowTop, this.mLightZ, this.mLightRadius);
    }

    int getWidth() {
        return this.mWidth;
    }

    int getHeight() {
        return this.mHeight;
    }

    void dumpGfxInfo(PrintWriter printWriter, FileDescriptor fileDescriptor, String[] strArr) {
        printWriter.flush();
        int i = (strArr == null || strArr.length == 0) ? 1 : 0;
        for (String str : strArr) {
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != -252053678) {
                if (iHashCode != 1492) {
                    if (iHashCode == 108404047 && str.equals("reset")) {
                        b = 1;
                    }
                } else if (str.equals("-a")) {
                    b = 2;
                }
            } else if (str.equals("framestats")) {
                b = 0;
            }
            if (b == 0) {
                i |= 1;
            } else if (b == 1) {
                i |= 2;
            } else if (b == 2) {
                i = 1;
            }
        }
        dumpProfileInfo(fileDescriptor, i);
    }

    @Override // android.graphics.HardwareRenderer
    public boolean loadSystemProperties() {
        boolean zLoadSystemProperties = super.loadSystemProperties();
        if (zLoadSystemProperties) {
            invalidateRoot();
        }
        return zLoadSystemProperties;
    }

    private void updateViewTreeDisplayList(View view) {
        view.mPrivateFlags |= 32;
        view.mRecreateDisplayList = (view.mPrivateFlags & Integer.MIN_VALUE) == Integer.MIN_VALUE;
        view.mPrivateFlags &= Integer.MAX_VALUE;
        view.updateDisplayListIfDirty();
        view.mRecreateDisplayList = false;
    }

    private void updateRootDisplayList(View view, DrawCallbacks drawCallbacks) {
        Trace.traceBegin(8L, "Record View#draw()");
        updateViewTreeDisplayList(view);
        final ArrayList<HardwareRenderer.FrameDrawingCallback> arrayList = this.mNextRtFrameCallbacks;
        if (arrayList != null) {
            this.mNextRtFrameCallbacks = null;
            setFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.-$$Lambda$ThreadedRenderer$ydBD-R1iP5u-97XYakm-jKvC1b4
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    ThreadedRenderer.lambda$updateRootDisplayList$0(arrayList, j);
                }
            });
        }
        if (this.mRootNodeNeedsUpdate || !this.mRootNode.hasDisplayList()) {
            RecordingCanvas recordingCanvasBeginRecording = this.mRootNode.beginRecording(this.mSurfaceWidth, this.mSurfaceHeight);
            try {
                int iSave = recordingCanvasBeginRecording.save();
                recordingCanvasBeginRecording.translate(this.mInsetLeft, this.mInsetTop);
                drawCallbacks.onPreDraw(recordingCanvasBeginRecording);
                recordingCanvasBeginRecording.enableZ();
                recordingCanvasBeginRecording.drawRenderNode(view.updateDisplayListIfDirty());
                recordingCanvasBeginRecording.disableZ();
                drawCallbacks.onPostDraw(recordingCanvasBeginRecording);
                recordingCanvasBeginRecording.restoreToCount(iSave);
                this.mRootNodeNeedsUpdate = false;
            } finally {
                this.mRootNode.endRecording();
            }
        }
        Trace.traceEnd(8L);
    }

    static /* synthetic */ void lambda$updateRootDisplayList$0(ArrayList arrayList, long j) {
        for (int i = 0; i < arrayList.size(); i++) {
            ((HardwareRenderer.FrameDrawingCallback) arrayList.get(i)).onFrameDraw(j);
        }
    }

    void invalidateRoot() {
        this.mRootNodeNeedsUpdate = true;
    }

    void draw(View view, View.AttachInfo attachInfo, DrawCallbacks drawCallbacks) {
        Choreographer choreographer = attachInfo.mViewRootImpl.mChoreographer;
        choreographer.mFrameInfo.markDrawStart();
        updateRootDisplayList(view, drawCallbacks);
        if (attachInfo.mPendingAnimatingRenderNodes != null) {
            int size = attachInfo.mPendingAnimatingRenderNodes.size();
            for (int i = 0; i < size; i++) {
                registerAnimatingRenderNode(attachInfo.mPendingAnimatingRenderNodes.get(i));
            }
            attachInfo.mPendingAnimatingRenderNodes.clear();
            attachInfo.mPendingAnimatingRenderNodes = null;
        }
        int iSyncAndDrawFrame = syncAndDrawFrame(choreographer.mFrameInfo);
        if ((iSyncAndDrawFrame & 2) != 0) {
            Log.w("OpenGLRenderer", "Surface lost, forcing relayout");
            attachInfo.mViewRootImpl.mForceNextWindowRelayout = true;
            attachInfo.mViewRootImpl.requestLayout();
        }
        if ((iSyncAndDrawFrame & 1) != 0) {
            attachInfo.mViewRootImpl.invalidate();
        }
    }

    public RenderNode getRootNode() {
        return this.mRootNode;
    }

    public static class SimpleRenderer extends HardwareRenderer {
        private final float mLightRadius;
        private final float mLightY;
        private final float mLightZ;

        public SimpleRenderer(Context context, String str, Surface surface) {
            setName(str);
            setOpaque(false);
            setSurface(surface);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.Lighting, 0, 0);
            this.mLightY = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
            this.mLightZ = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
            this.mLightRadius = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
            float f = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
            float f2 = typedArrayObtainStyledAttributes.getFloat(1, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
            setLightSourceAlpha(f, f2);
        }

        public void setLightCenter(Display display, int i, int i2) {
            display.getRealSize(new Point());
            setLightSourceGeometry((r0.x / 2.0f) - i, this.mLightY - i2, this.mLightZ, this.mLightRadius);
        }

        public RenderNode getRootNode() {
            return this.mRootNode;
        }

        public void draw(HardwareRenderer.FrameDrawingCallback frameDrawingCallback) {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() * TimeUtils.NANOS_PER_MS;
            if (frameDrawingCallback != null) {
                setFrameCallback(frameDrawingCallback);
            }
            createRenderRequest().setVsyncTime(jCurrentAnimationTimeMillis).syncAndDraw();
        }
    }
}
