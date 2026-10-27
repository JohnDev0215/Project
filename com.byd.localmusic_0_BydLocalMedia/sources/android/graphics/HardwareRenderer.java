package android.graphics;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.TimeUtils;
import android.view.IGraphicsStats;
import android.view.IGraphicsStatsCallback;
import android.view.NativeVectorDrawableAnimator;
import android.view.Surface;
import android.view.TextureLayer;
import android.view.animation.AnimationUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import sun.misc.Cleaner;

/* JADX INFO: loaded from: classes.dex */
public class HardwareRenderer {
    private static final String CACHE_PATH_SHADERS = "com.android.opengl.shaders_cache";
    private static final String CACHE_PATH_SKIASHADERS = "com.android.skia.shaders_cache";
    public static final int FLAG_DUMP_ALL = 1;
    public static final int FLAG_DUMP_FRAMESTATS = 1;
    public static final int FLAG_DUMP_RESET = 2;
    private static final String LOG_TAG = "HardwareRenderer";
    public static final int SYNC_CONTEXT_IS_STOPPED = 4;
    public static final int SYNC_FRAME_DROPPED = 8;
    public static final int SYNC_LOST_SURFACE_REWARD_IF_FOUND = 2;
    public static final int SYNC_OK = 0;
    public static final int SYNC_REDRAW_REQUESTED = 1;
    private final long mNativeProxy;
    protected RenderNode mRootNode;
    private boolean mOpaque = true;
    private boolean mForceDark = false;
    private boolean mIsWideGamut = false;
    private FrameRenderRequest mRenderRequest = new FrameRenderRequest();

    @Retention(RetentionPolicy.SOURCE)
    public @interface DumpFlags {
    }

    public interface FrameCompleteCallback {
        void onFrameComplete(long j);
    }

    public interface FrameDrawingCallback {
        void onFrameDraw(long j);
    }

    public interface PictureCapturedCallback {
        void onPictureCaptured(Picture picture);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncAndDrawResult {
    }

    public static native void disableVsync();

    private static native void nAddObserver(long j, long j2);

    private static native void nAddRenderNode(long j, long j2, boolean z);

    private static native void nAllocateBuffers(long j);

    private static native void nBuildLayer(long j, long j2);

    private static native void nCancelLayerUpdate(long j, long j2);

    private static native boolean nCopyLayerInto(long j, long j2, long j3);

    private static native int nCopySurfaceInto(Surface surface, int i, int i2, int i3, int i4, long j);

    private static native Bitmap nCreateHardwareBitmap(long j, int i, int i2);

    private static native long nCreateProxy(boolean z, boolean z2, long j);

    private static native long nCreateRootRenderNode();

    private static native long nCreateTextureLayer(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nDeleteProxy(long j);

    private static native void nDestroy(long j, long j2);

    private static native void nDestroyHardwareResources(long j);

    private static native void nDetachSurfaceTexture(long j, long j2);

    private static native void nDrawRenderNode(long j, long j2);

    private static native void nDumpProfileInfo(long j, FileDescriptor fileDescriptor, int i);

    private static native void nFence(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native int nGetRenderThreadTid(long j);

    private static native void nHackySetRTAnimationsEnabled(boolean z);

    private static native void nInvokeFunctor(long j, boolean z);

    private static native boolean nLoadSystemProperties(long j);

    private static native void nNotifyFramePending(long j);

    private static native void nOverrideProperty(String str, String str2);

    private static native boolean nPause(long j);

    private static native void nPushLayerUpdate(long j, long j2);

    private static native void nRegisterAnimatingRenderNode(long j, long j2);

    private static native void nRegisterVectorDrawableAnimator(long j, long j2);

    private static native void nRemoveObserver(long j, long j2);

    private static native void nRemoveRenderNode(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nRotateProcessStatsBuffer();

    private static native void nSetContentDrawBounds(long j, int i, int i2, int i3, int i4);

    private static native void nSetContextPriority(int i);

    private static native void nSetDebuggingEnabled(boolean z);

    private static native void nSetForceDark(long j, boolean z);

    private static native void nSetFrameCallback(long j, FrameDrawingCallback frameDrawingCallback);

    private static native void nSetFrameCompleteCallback(long j, FrameCompleteCallback frameCompleteCallback);

    private static native void nSetHighContrastText(boolean z);

    private static native void nSetIsolatedProcess(boolean z);

    private static native void nSetLightAlpha(long j, float f, float f2);

    private static native void nSetLightGeometry(long j, float f, float f2, float f3, float f4);

    private static native void nSetName(long j, String str);

    private static native void nSetOpaque(long j, boolean z);

    private static native void nSetPictureCaptureCallback(long j, PictureCapturedCallback pictureCapturedCallback);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nSetProcessStatsBuffer(int i);

    private static native void nSetStopped(long j, boolean z);

    private static native void nSetSurface(long j, Surface surface, boolean z);

    private static native void nSetWideGamut(long j, boolean z);

    private static native void nStopDrawing(long j);

    private static native int nSyncAndDrawFrame(long j, long[] jArr, int i);

    private static native void nTrimMemory(int i);

    public static native void preload();

    protected static native void setupShadersDiskCache(String str, String str2);

    public HardwareRenderer() {
        RenderNode renderNodeAdopt = RenderNode.adopt(nCreateRootRenderNode());
        this.mRootNode = renderNodeAdopt;
        renderNodeAdopt.setClipToBounds(false);
        long jNCreateProxy = nCreateProxy(true ^ this.mOpaque, this.mIsWideGamut, this.mRootNode.mNativeRenderNode);
        this.mNativeProxy = jNCreateProxy;
        if (jNCreateProxy == 0) {
            throw new OutOfMemoryError("Unable to create hardware renderer");
        }
        Cleaner.create(this, new DestroyContextRunnable(this.mNativeProxy));
        ProcessInitializer.sInstance.init(this.mNativeProxy);
    }

    public void destroy() {
        nDestroy(this.mNativeProxy, this.mRootNode.mNativeRenderNode);
    }

    public void setName(String str) {
        nSetName(this.mNativeProxy, str);
    }

    public void setLightSourceGeometry(float f, float f2, float f3, float f4) {
        validateFinite(f, "lightX");
        validateFinite(f2, "lightY");
        validatePositive(f3, "lightZ");
        validatePositive(f4, "lightRadius");
        nSetLightGeometry(this.mNativeProxy, f, f2, f3, f4);
    }

    public void setLightSourceAlpha(float f, float f2) {
        validateAlpha(f, "ambientShadowAlpha");
        validateAlpha(f2, "spotShadowAlpha");
        nSetLightAlpha(this.mNativeProxy, f, f2);
    }

    public void setContentRoot(RenderNode renderNode) {
        RecordingCanvas recordingCanvasBeginRecording = this.mRootNode.beginRecording();
        if (renderNode != null) {
            recordingCanvasBeginRecording.drawRenderNode(renderNode);
        }
        this.mRootNode.endRecording();
    }

    public void setSurface(Surface surface) {
        setSurface(surface, false);
    }

    public void setSurface(Surface surface, boolean z) {
        if (surface != null && !surface.isValid()) {
            throw new IllegalArgumentException("Surface is invalid. surface.isValid() == false.");
        }
        nSetSurface(this.mNativeProxy, surface, z);
    }

    public final class FrameRenderRequest {
        private FrameInfo mFrameInfo;
        private boolean mWaitForPresent;

        private FrameRenderRequest() {
            this.mFrameInfo = new FrameInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mWaitForPresent = false;
            HardwareRenderer.this.mRenderRequest.setVsyncTime(AnimationUtils.currentAnimationTimeMillis() * TimeUtils.NANOS_PER_MS);
        }

        public void setFrameInfo(FrameInfo frameInfo) {
            System.arraycopy(frameInfo.frameInfo, 0, this.mFrameInfo.frameInfo, 0, frameInfo.frameInfo.length);
        }

        public FrameRenderRequest setVsyncTime(long j) {
            this.mFrameInfo.setVsync(j, j);
            this.mFrameInfo.addFlags(4L);
            return this;
        }

        public FrameRenderRequest setFrameCommitCallback(final Executor executor, final Runnable runnable) {
            HardwareRenderer.this.setFrameCompleteCallback(new FrameCompleteCallback() { // from class: android.graphics.-$$Lambda$HardwareRenderer$FrameRenderRequest$dejdYejpuxp3nc7eP6FZ2zBu778
                @Override // android.graphics.HardwareRenderer.FrameCompleteCallback
                public final void onFrameComplete(long j) {
                    executor.execute(runnable);
                }
            });
            return this;
        }

        public FrameRenderRequest setWaitForPresent(boolean z) {
            this.mWaitForPresent = z;
            return this;
        }

        public int syncAndDraw() {
            int iSyncAndDrawFrame = HardwareRenderer.this.syncAndDrawFrame(this.mFrameInfo);
            if (this.mWaitForPresent && (iSyncAndDrawFrame & 8) == 0) {
                HardwareRenderer.this.fence();
            }
            return iSyncAndDrawFrame;
        }
    }

    public FrameRenderRequest createRenderRequest() {
        this.mRenderRequest.reset();
        return this.mRenderRequest;
    }

    public int syncAndDrawFrame(FrameInfo frameInfo) {
        return nSyncAndDrawFrame(this.mNativeProxy, frameInfo.frameInfo, frameInfo.frameInfo.length);
    }

    public boolean pause() {
        return nPause(this.mNativeProxy);
    }

    public void setStopped(boolean z) {
        nSetStopped(this.mNativeProxy, z);
    }

    public void stop() {
        nSetStopped(this.mNativeProxy, true);
    }

    public void start() {
        nSetStopped(this.mNativeProxy, false);
    }

    public void clearContent() {
        nDestroyHardwareResources(this.mNativeProxy);
    }

    public boolean setForceDark(boolean z) {
        if (this.mForceDark == z) {
            return false;
        }
        this.mForceDark = z;
        nSetForceDark(this.mNativeProxy, z);
        return true;
    }

    public void allocateBuffers() {
        nAllocateBuffers(this.mNativeProxy);
    }

    public void notifyFramePending() {
        nNotifyFramePending(this.mNativeProxy);
    }

    public void setOpaque(boolean z) {
        if (this.mOpaque != z) {
            this.mOpaque = z;
            nSetOpaque(this.mNativeProxy, z);
        }
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }

    public void setFrameCompleteCallback(FrameCompleteCallback frameCompleteCallback) {
        nSetFrameCompleteCallback(this.mNativeProxy, frameCompleteCallback);
    }

    public void addObserver(HardwareRendererObserver hardwareRendererObserver) {
        nAddObserver(this.mNativeProxy, hardwareRendererObserver.getNativeInstance());
    }

    public void removeObserver(HardwareRendererObserver hardwareRendererObserver) {
        nRemoveObserver(this.mNativeProxy, hardwareRendererObserver.getNativeInstance());
    }

    public void setWideGamut(boolean z) {
        this.mIsWideGamut = z;
        nSetWideGamut(this.mNativeProxy, z);
    }

    public void fence() {
        nFence(this.mNativeProxy);
    }

    public void registerAnimatingRenderNode(RenderNode renderNode) {
        nRegisterAnimatingRenderNode(this.mRootNode.mNativeRenderNode, renderNode.mNativeRenderNode);
    }

    public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator nativeVectorDrawableAnimator) {
        nRegisterVectorDrawableAnimator(this.mRootNode.mNativeRenderNode, nativeVectorDrawableAnimator.getAnimatorNativePtr());
    }

    public void stopDrawing() {
        nStopDrawing(this.mNativeProxy);
    }

    public TextureLayer createTextureLayer() {
        return TextureLayer.adoptTextureLayer(this, nCreateTextureLayer(this.mNativeProxy));
    }

    public void detachSurfaceTexture(long j) {
        nDetachSurfaceTexture(this.mNativeProxy, j);
    }

    public void buildLayer(RenderNode renderNode) {
        if (renderNode.hasDisplayList()) {
            nBuildLayer(this.mNativeProxy, renderNode.mNativeRenderNode);
        }
    }

    public boolean copyLayerInto(TextureLayer textureLayer, Bitmap bitmap) {
        return nCopyLayerInto(this.mNativeProxy, textureLayer.getDeferredLayerUpdater(), bitmap.getNativeInstance());
    }

    public void pushLayerUpdate(TextureLayer textureLayer) {
        nPushLayerUpdate(this.mNativeProxy, textureLayer.getDeferredLayerUpdater());
    }

    public void onLayerDestroyed(TextureLayer textureLayer) {
        nCancelLayerUpdate(this.mNativeProxy, textureLayer.getDeferredLayerUpdater());
    }

    public void setFrameCallback(FrameDrawingCallback frameDrawingCallback) {
        nSetFrameCallback(this.mNativeProxy, frameDrawingCallback);
    }

    public void addRenderNode(RenderNode renderNode, boolean z) {
        nAddRenderNode(this.mNativeProxy, renderNode.mNativeRenderNode, z);
    }

    public void removeRenderNode(RenderNode renderNode) {
        nRemoveRenderNode(this.mNativeProxy, renderNode.mNativeRenderNode);
    }

    public void drawRenderNode(RenderNode renderNode) {
        nDrawRenderNode(this.mNativeProxy, renderNode.mNativeRenderNode);
    }

    public boolean loadSystemProperties() {
        return nLoadSystemProperties(this.mNativeProxy);
    }

    public void dumpProfileInfo(FileDescriptor fileDescriptor, int i) {
        nDumpProfileInfo(this.mNativeProxy, fileDescriptor, i);
    }

    public void setContentDrawBounds(int i, int i2, int i3, int i4) {
        nSetContentDrawBounds(this.mNativeProxy, i, i2, i3, i4);
    }

    public void setPictureCaptureCallback(PictureCapturedCallback pictureCapturedCallback) {
        nSetPictureCaptureCallback(this.mNativeProxy, pictureCapturedCallback);
    }

    public boolean isWideGamut() {
        return this.mIsWideGamut;
    }

    static void invokePictureCapturedCallback(long j, PictureCapturedCallback pictureCapturedCallback) {
        pictureCapturedCallback.onPictureCaptured(new Picture(j));
    }

    private static void validateAlpha(float f, String str) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException(str + " must be a valid alpha, " + f + " is not in the range of 0.0f to 1.0f");
        }
    }

    private static void validatePositive(float f, String str) {
        if (!Float.isFinite(f) || f < 0.0f) {
            throw new IllegalArgumentException(str + " must be a finite positive, given=" + f);
        }
    }

    private static void validateFinite(float f, String str) {
        if (Float.isFinite(f)) {
            return;
        }
        throw new IllegalArgumentException(str + " must be finite, given=" + f);
    }

    public static void invokeFunctor(long j, boolean z) {
        nInvokeFunctor(j, z);
    }

    public static void setFPSDivisor(int i) {
        nHackySetRTAnimationsEnabled(i <= 1);
    }

    public static void setContextPriority(int i) {
        nSetContextPriority(i);
    }

    public static void setHighContrastText(boolean z) {
        nSetHighContrastText(z);
    }

    public static void setIsolatedProcess(boolean z) {
        nSetIsolatedProcess(z);
    }

    public static void setDebuggingEnabled(boolean z) {
        nSetDebuggingEnabled(z);
    }

    public static int copySurfaceInto(Surface surface, Rect rect, Bitmap bitmap) {
        if (rect == null) {
            return nCopySurfaceInto(surface, 0, 0, 0, 0, bitmap.getNativeInstance());
        }
        return nCopySurfaceInto(surface, rect.left, rect.top, rect.right, rect.bottom, bitmap.getNativeInstance());
    }

    public static Bitmap createHardwareBitmap(RenderNode renderNode, int i, int i2) {
        return nCreateHardwareBitmap(renderNode.mNativeRenderNode, i, i2);
    }

    public static void trimMemory(int i) {
        nTrimMemory(i);
    }

    public static void overrideProperty(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("name and value must be non-null");
        }
        nOverrideProperty(str, str2);
    }

    public static void setupDiskCache(File file) {
        setupShadersDiskCache(new File(file, CACHE_PATH_SHADERS).getAbsolutePath(), new File(file, CACHE_PATH_SKIASHADERS).getAbsolutePath());
    }

    public static void setPackageName(String str) {
        ProcessInitializer.sInstance.setPackageName(str);
    }

    private static final class DestroyContextRunnable implements Runnable {
        private final long mNativeInstance;

        DestroyContextRunnable(long j) {
            this.mNativeInstance = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            HardwareRenderer.nDeleteProxy(this.mNativeInstance);
        }
    }

    private static class ProcessInitializer {
        static ProcessInitializer sInstance = new ProcessInitializer();
        private IGraphicsStats mGraphicsStatsService;
        private String mPackageName;
        private boolean mInitialized = false;
        private IGraphicsStatsCallback mGraphicsStatsCallback = new IGraphicsStatsCallback.Stub() { // from class: android.graphics.HardwareRenderer.ProcessInitializer.1
            @Override // android.view.IGraphicsStatsCallback
            public void onRotateGraphicsStatsBuffer() throws RemoteException {
                ProcessInitializer.this.rotateBuffer();
            }
        };

        private ProcessInitializer() {
        }

        synchronized void setPackageName(String str) {
            if (this.mInitialized) {
                return;
            }
            this.mPackageName = str;
        }

        synchronized void init(long j) {
            if (this.mInitialized) {
                return;
            }
            this.mInitialized = true;
            initSched(j);
            initGraphicsStats();
        }

        private void initSched(long j) {
            try {
                ActivityManager.getService().setRenderThread(HardwareRenderer.nGetRenderThreadTid(j));
            } catch (Throwable th) {
                Log.w(HardwareRenderer.LOG_TAG, "Failed to set scheduler for RenderThread", th);
            }
        }

        private void initGraphicsStats() {
            if (this.mPackageName == null) {
                return;
            }
            try {
                IBinder service = ServiceManager.getService(GraphicsStatsService.GRAPHICS_STATS_SERVICE);
                if (service == null) {
                    return;
                }
                this.mGraphicsStatsService = IGraphicsStats.Stub.asInterface(service);
                requestBuffer();
            } catch (Throwable th) {
                Log.w(HardwareRenderer.LOG_TAG, "Could not acquire gfx stats buffer", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void rotateBuffer() {
            HardwareRenderer.nRotateProcessStatsBuffer();
            requestBuffer();
        }

        private void requestBuffer() {
            try {
                ParcelFileDescriptor parcelFileDescriptorRequestBufferForProcess = this.mGraphicsStatsService.requestBufferForProcess(this.mPackageName, this.mGraphicsStatsCallback);
                HardwareRenderer.nSetProcessStatsBuffer(parcelFileDescriptorRequestBufferForProcess.getFd());
                parcelFileDescriptorRequestBufferForProcess.close();
            } catch (Throwable th) {
                Log.w(HardwareRenderer.LOG_TAG, "Could not acquire gfx stats buffer", th);
            }
        }
    }
}
