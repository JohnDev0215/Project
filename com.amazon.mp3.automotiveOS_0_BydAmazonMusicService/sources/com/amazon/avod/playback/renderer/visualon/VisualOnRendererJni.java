package com.amazon.avod.playback.renderer.visualon;

import android.content.Context;
import android.os.Build;
import android.view.SurfaceView;
import com.amazon.avod.drm.playready.PlayReadyException;
import com.amazon.avod.drm.playready.SoftwarePlayReadyInitialization;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.playback.support.MutableRendererPerformanceData;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playback.renderer.shared.NativeRendererJniBase;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnRendererJni extends NativeRendererJniBase {
    private static final String SURFACE_DESTROYED_PARAM = "VOOSMP_PID_APPLICATION_SUSPEND";
    private static final String SURFACE_RECREATED_PARAM = "VOOSMP_PID_APPLICATION_RESUME";
    private static final String VISUAL_ON_EXPECTED_LIBRARY_VERSION = "13";
    public static final String VOLUME_PARAM = "VOOSMP_PID_AUDIO_VOLUME";
    private long mAudioBufferRef;
    private final Context mContext;
    private final DeviceConfiguration mDeviceConfiguration;
    private final FileSystem mFileSystem;
    private String mLibraryPath;
    private long mNativeContext;
    private final PlaybackNativeLibrariesLoader mNativeLibrariesLoader;
    private final VisualOnRendererConfig mRendererConfiguration;
    private long mStatisticsBufferRef;
    private long mVideoBufferRef;

    private native void configureRenderer(int i, int i2, int i3, int i4, int i5, int i6, int i7, Context context, SurfaceView surfaceView, int i8, int i9, boolean z, int i10, String str, boolean z2) throws PlaybackException;

    private native void createRenderer(long j, long j2, int i);

    public static native DeviceCapability getDeviceCapability();

    private static native void initNativeLibrary();

    private native void initializeDrm(String str, String str2);

    private native void setIsSurfaceDestroyed(boolean z);

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer allocateCyclicAudioBuffer(int i);

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer allocateCyclicVideoBuffer(int i);

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void flushRenderer() throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected String getExpectedLibraryVersion() {
        return VISUAL_ON_EXPECTED_LIBRARY_VERSION;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected native String getNativeCodeCompileTime();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected native String getNativeLibraryVersion();

    public native void getPerformanceData(MutableRendererPerformanceData mutableRendererPerformanceData, boolean z) throws VisualOnRendererException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer getStatisticsBuffer();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native int getWritableAudioRegion(int i) throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native int getWritableVideoRegion(int i) throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void pauseRenderer() throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void queueToSampleMetaDataList(SampleMetadata sampleMetadata, boolean z, int i, int i2, int i3, long j, long j2, int i4) throws PlaybackException {
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void resumeRenderer() throws PlaybackException;

    public native void setAnalyticsFilter(int i, int i2, int i3, int i4, int i5, boolean z) throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void setDrmHeader(byte[] bArr) throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void setEndOfStream() {
    }

    public native void setParam(@Nonnull String str, Object obj);

    public native void setPlaybackSpeed(float f);

    public native void setSoftkeyVisibilityChanged(int i);

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void setVideoRegion(int i, int i2, int i3, int i4) throws PlaybackException;

    public native void setView(SurfaceView surfaceView);

    public native void submitSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws PlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void terminateRenderer();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void waitForSetDrmHeader() throws PlaybackException;

    private static class RendererMode {
        private static final int RENDERING_MODE_DEFAULT = 0;
        private static final int RENDERING_MODE_YUV = 2;
        private static final int RENDERING_MODE_YUV_AMR64_ANDROID_L = 1;

        private RendererMode() {
        }
    }

    public VisualOnRendererJni(Context context, DeviceConfiguration deviceConfiguration, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this(context, deviceConfiguration, fileSystem, VisualOnRendererConfig.INSTANCE, playbackNativeLibrariesLoader);
    }

    VisualOnRendererJni(Context context, DeviceConfiguration deviceConfiguration, FileSystem fileSystem, VisualOnRendererConfig visualOnRendererConfig, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        playbackNativeLibrariesLoader.addLibraryLoadedListener(VisualOnNativeLibrary.class, new PlaybackNativeLibrariesLoader.LibraryLoadedListener() { // from class: com.amazon.avod.playback.renderer.visualon.VisualOnRendererJni.1
            @Override // com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader.LibraryLoadedListener
            public void onLibraryLoaded(@Nullable String str) {
                VisualOnRendererJni.this.mLibraryPath = str;
            }
        });
        this.mContext = context;
        this.mDeviceConfiguration = deviceConfiguration;
        this.mFileSystem = fileSystem;
        this.mRendererConfiguration = visualOnRendererConfig;
        this.mNativeLibrariesLoader = playbackNativeLibrariesLoader;
    }

    public void initialize() throws PlaybackException {
        if (!this.mNativeLibrariesLoader.waitForInitialization()) {
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "Failed to load the playback native libraries!");
        }
        initNativeLibrary();
        checkNativeLibraryVersion(VisualOnNativeLibrary.NATIVE_RENDERER_LIBRARY);
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public int createRenderer(long j, long j2, int i, int i2, boolean z) {
        createRenderer(j, j2, i2);
        return 1;
    }

    public void configureRenderer(int i, int i2, int i3, int i4, int i5, @Nullable SurfaceView surfaceView, @Nonnull VisualOnRendererType visualOnRendererType) throws PlaybackException {
        int i6;
        int i7;
        String str;
        if (this.mRendererConfiguration.isDeviceOnYuvRenderingBlacklist(Build.MODEL)) {
            i7 = 0;
        } else {
            if (!this.mRendererConfiguration.getRenderingModeYuv()) {
                i6 = this.mRendererConfiguration.getRenderingModeYuvArm64AndroidL() ? 1 : 2;
                i7 = 0;
            }
            i7 = i6;
        }
        boolean zEquals = "".equals(this.mLibraryPath);
        String str2 = this.mLibraryPath;
        if (str2 == PlaybackNativeLibrariesLoader.NATIVE_LIBRARY_PATH_NOT_LOADED) {
            str = "";
        } else {
            if (!str2.equals("") && !str2.endsWith(File.pathSeparator)) {
                str2 = str2 + File.separator;
            }
            str = str2;
        }
        configureRenderer(i, i2, i3, i4, i5, this.mDeviceConfiguration.getScreenWidth(), this.mDeviceConfiguration.getScreenHeight(), this.mContext, surfaceView, visualOnRendererType.getTypeAsInt(), this.mRendererConfiguration.getDecodedSampleBufferSize(), this.mRendererConfiguration.getEnableRunningAtHigherPriority(), i7, str, zEquals);
        setAnalyticsFilter(this.mRendererConfiguration.getReportTime(), this.mRendererConfiguration.getSourceTimeThreshold(), this.mRendererConfiguration.getDecodeTimeThreshold(), this.mRendererConfiguration.getRenderTimeThreshold(), this.mRendererConfiguration.getJitterTimeThreshold(), this.mRendererConfiguration.getEnableCpuLoad());
    }

    public void configureDrm() throws PlaybackException {
        try {
            SoftwarePlayReadyInitialization softwarePlayReadyInitializationInitialize = SoftwarePlayReadyInitialization.initialize(this.mContext, this.mFileSystem);
            initializeDrm(softwarePlayReadyInitializationInitialize.getSystemDirectoryPath(), softwarePlayReadyInitializationInitialize.getLicenseStorePath());
        } catch (PlayReadyException e) {
            throw new PlaybackException(e);
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void queueSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws PlaybackException {
        submitSample(z, i, i2, i3, i4, j, j2, i5);
    }

    public void getPerformanceData(MutableRendererPerformanceData mutableRendererPerformanceData) {
        try {
            getPerformanceData(mutableRendererPerformanceData, this.mRendererConfiguration.getEnableMetricsLogging());
        } catch (VisualOnRendererException e) {
            DLog.warnf("Native exception %s is thrown. Ignoring it in the context of getPerformanceData call", e);
        }
    }

    public void notifySurfaceDestroyed() {
        setParam(SURFACE_DESTROYED_PARAM, null);
        setIsSurfaceDestroyed(true);
    }

    public void notifySurfaceRecreated(@Nonnull SurfaceView surfaceView) {
        Preconditions.checkNotNull(surfaceView, "surfaceView");
        setParam(SURFACE_RECREATED_PARAM, surfaceView);
        setIsSurfaceDestroyed(false);
    }
}
