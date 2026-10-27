package com.google.android.projection.protocol;

import android.support.v4.media.MediaPlayer2;
import android.util.Log;
import com.google.android.projection.proto.Protos;
import com.google.protobuf.ExtensionRegistryLite;
import com.ts.androidauto.aap.AapPhoneManager;
import com.ts.androidauto.aap.common.AapConfig;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.carlife.data.CarLifeConfig;

/* JADX INFO: loaded from: classes.dex */
public class VideoSink implements CarServiceProvider, VideoSinkCallbacks {
    private static final boolean DBG_TRACE = false;
    private static final int MAX_UNACKED_FRAMES = 16;
    private static final String TAG = "AHU.VIDEO";
    private final boolean mAutoStartProjection;
    private Protos.UiConfig mCurrentUiConfig;
    private long mNativeVideoSink;
    private final ProjectionListener mProjectionListener;
    private final List<Protos.VideoConfiguration> mVideoConfigurations = new ArrayList();
    private final int mViewingDistance;
    public static final int VIDEO_FOCUS_NATIVE = Protos.VideoFocusMode.VIDEO_FOCUS_NATIVE.getNumber();
    public static final int VIDEO_FOCUS_PROJECTED = Protos.VideoFocusMode.VIDEO_FOCUS_PROJECTED.getNumber();
    public static final int VIDEO_FOCUS_NATIVE_TRANSIENT = Protos.VideoFocusMode.VIDEO_FOCUS_NATIVE_TRANSIENT.getNumber();
    public static final int VIDEO_FOCUS_PROJECTED_NO_INPUT_FOCUS = Protos.VideoFocusMode.VIDEO_FOCUS_PROJECTED_NO_INPUT_FOCUS.getNumber();

    private native void nativeAckFrames(int i, int i2);

    private native void nativeAckFramesNew(int i, long[] jArr);

    private native void nativeAddSupportedConfiguration(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, byte[] bArr);

    private native int nativeInit(int i, long j, boolean z, int i2) throws IllegalStateException;

    private native void nativeMaxUnackedFrames(int i);

    private native void nativeSetCodecType(int i);

    private native void nativeSetDisplayIdAndType(int i, int i2);

    private native void nativeSetInitialContentKeyCode(int i);

    private native void nativeSetMaxUnackedFrames(int i);

    private native void nativeSetVideoFocus(int i, boolean z);

    private native void nativeShutdown();

    private native void nativeUpdateUiConfig(byte[] bArr);

    public static abstract class ProjectionListener {
        public void onProjectionUpdate(VideoFrame frame) {
        }

        public void onCodecConfig(byte[] data) {
        }

        public void onSourceVideoConfigChanged(int width, int height, Protos.VideoConfiguration config) {
        }

        public void onCodecSetup(int mediaCodecType) {
        }

        public void onVideoFocusRequest(int focus, int reason) {
        }

        public void onVideoFocusModeChange(int focusMode, int reason) {
        }

        public void onUpdateUiConfigReply(Protos.UiConfig currentUiConfig) {
        }
    }

    public VideoSink(ProjectionListener listener, boolean autoStartProjection, int viewingDistance) {
        this.mProjectionListener = listener;
        this.mAutoStartProjection = autoStartProjection;
        this.mViewingDistance = viewingDistance;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver, this.mAutoStartProjection, this.mViewingDistance) == 0) {
            nativeSetMaxUnackedFrames(16);
            return true;
        }
        return DBG_TRACE;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeVideoSink;
    }

    public Protos.VideoConfiguration nearestVideoConfiguration(int width, int height, int dpi, int realDpi, int fps, int decoderAdditionalDepth, Integer viewingDistance, Float pixelAspectRatio, Protos.UiConfig.Builder configBuilder) {
        Protos.VideoCodecResolutionType codecResolution;
        int widthMargin;
        int heightMargin;
        if (width > height) {
            if (width <= 800) {
                codecResolution = Protos.VideoCodecResolutionType.VIDEO_800x480;
                widthMargin = 800 - width;
                heightMargin = Math.max(0, 480 - height);
            } else if (width <= 1280) {
                codecResolution = Protos.VideoCodecResolutionType.VIDEO_1280x720;
                widthMargin = 1280 - width;
                heightMargin = Math.max(0, 720 - height);
            } else if (width <= 1920) {
                codecResolution = Protos.VideoCodecResolutionType.VIDEO_1920x1080;
                widthMargin = 1920 - width;
                heightMargin = Math.max(0, 1080 - height);
            } else if (width <= 2560) {
                codecResolution = Protos.VideoCodecResolutionType.VIDEO_2560x1440;
                widthMargin = 2560 - width;
                heightMargin = Math.max(0, 1440 - height);
            } else {
                if (width > 3840) {
                    return null;
                }
                codecResolution = Protos.VideoCodecResolutionType.VIDEO_3840x2160;
                widthMargin = 3840 - width;
                heightMargin = Math.max(0, 2160 - height);
            }
        } else if (height <= 1280) {
            codecResolution = Protos.VideoCodecResolutionType.VIDEO_720x1280;
            widthMargin = Math.max(0, 720 - width);
            heightMargin = 1280 - height;
        } else if (height <= 1920) {
            codecResolution = Protos.VideoCodecResolutionType.VIDEO_1080x1920;
            widthMargin = Math.max(0, 1080 - width);
            heightMargin = 1920 - height;
        } else if (height <= 2560) {
            codecResolution = Protos.VideoCodecResolutionType.VIDEO_1440x2560;
            widthMargin = Math.max(0, 1440 - width);
            heightMargin = 2560 - height;
        } else {
            if (height > 3840) {
                return null;
            }
            codecResolution = Protos.VideoCodecResolutionType.VIDEO_2160x3840;
            widthMargin = Math.max(0, 2160 - width);
            heightMargin = 3840 - height;
        }
        Protos.VideoFrameRateType frameRate = Protos.VideoFrameRateType.VIDEO_FPS_30;
        if (fps >= 60) {
            frameRate = Protos.VideoFrameRateType.VIDEO_FPS_60;
        }
        Protos.VideoConfiguration.Builder builder = Protos.VideoConfiguration.newBuilder().setCodecResolution(codecResolution).setFrameRate(frameRate).setWidthMargin(widthMargin).setHeightMargin(heightMargin).setDensity(dpi).setRealDensity(realDpi).setDecoderAdditionalDepth(decoderAdditionalDepth);
        if (viewingDistance != null) {
            builder.setViewingDistance(viewingDistance.intValue());
        }
        if (pixelAspectRatio != null) {
            builder.setPixelAspectRatioE4(Math.round(pixelAspectRatio.floatValue() * 10000.0f));
        }
        if (configBuilder != null) {
            builder.setUiConfig((Protos.UiConfig) configBuilder.build());
        }
        return (Protos.VideoConfiguration) builder.build();
    }

    public Protos.VideoConfiguration nearestVideoConfiguration(int width, int height, int dpi, int realDpi, int fps, int decoderAdditionalDepth) {
        return nearestVideoConfiguration(width, height, dpi, realDpi, fps, decoderAdditionalDepth, null, null, null);
    }

    public void addSupportedConfiguration(Protos.VideoConfiguration config) {
        int number;
        this.mVideoConfigurations.add(config);
        int number2 = config.getCodecResolution().getNumber();
        int i = config.getFrameRate() == Protos.VideoFrameRateType.VIDEO_FPS_60 ? 60 : 30;
        int widthMargin = config.getWidthMargin();
        int heightMargin = config.getHeightMargin();
        int density = config.getDensity();
        int realDensity = config.getRealDensity();
        int decoderAdditionalDepth = config.getDecoderAdditionalDepth();
        int pixelAspectRatioE4 = config.hasPixelAspectRatioE4() ? config.getPixelAspectRatioE4() : 10000;
        if (config.hasVideoCodecType()) {
            number = config.getVideoCodecType().getNumber();
        } else {
            number = Protos.MediaCodecType.MEDIA_CODEC_VIDEO_H264_BP.getNumber();
        }
        nativeAddSupportedConfiguration(number2, i, widthMargin, heightMargin, density, realDensity, decoderAdditionalDepth, pixelAspectRatioE4, number, config.getUiConfig().toByteArray());
    }

    public void setCodecType(Protos.MediaCodecType codecType) {
        nativeSetCodecType(codecType.getNumber());
    }

    public void ackFrames(int sessionId, int numFrames) {
        nativeAckFrames(sessionId, numFrames);
    }

    public void ackFrames(int sessionId, long[] receiveTimestampNs) {
        nativeAckFramesNew(sessionId, receiveTimestampNs);
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void dataAvailableCallback(int sessionId, long timestamp, ByteBuffer data) {
        VideoFrame frame = new VideoFrame(sessionId, timestamp, data);
        this.mProjectionListener.onProjectionUpdate(frame);
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public int codecConfigCallback(byte[] data) {
        this.mProjectionListener.onCodecConfig(data);
        return 0;
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public int setupCallback(int mediaCodecType) {
        this.mProjectionListener.onCodecSetup(mediaCodecType);
        return 0;
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void playbackStartCallback(int sessionId) {
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void playbackStopCallback(int sessionId) {
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void sourceVideoConfigCallback(int index) {
        Protos.VideoConfiguration config = this.mVideoConfigurations.get(index);
        int codecWidth = MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING;
        int codecHeight = AapConfig.VIDEO_SIZE_480P;
        switch (AnonymousClass1.$SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[config.getCodecResolution().ordinal()]) {
            case 1:
                codecWidth = AapPhoneManager.MSCREENHEIGHT1280;
                codecHeight = AapConfig.VIDEO_SIZE_720P;
                break;
            case 2:
                codecWidth = 1920;
                codecHeight = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                break;
            case 3:
                codecWidth = AapConfig.VIDEO_SIZE_720P;
                codecHeight = AapPhoneManager.MSCREENHEIGHT1280;
                break;
            case 4:
                codecWidth = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                codecHeight = 1920;
                break;
            case 5:
                codecWidth = 2560;
                codecHeight = 1440;
                break;
            case 6:
                codecWidth = 3840;
                codecHeight = 2160;
                break;
            case 7:
                codecWidth = 1440;
                codecHeight = 2560;
                break;
            case 8:
                codecWidth = 2160;
                codecHeight = 3840;
                break;
            case 9:
                codecWidth = MediaPlayer2.MEDIA_INFO_BAD_INTERLEAVING;
                codecHeight = AapConfig.VIDEO_SIZE_480P;
                break;
        }
        this.mProjectionListener.onSourceVideoConfigChanged(codecWidth, codecHeight, config);
    }

    /* JADX INFO: renamed from: com.google.android.projection.protocol.VideoSink$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType;

        static {
            int[] iArr = new int[Protos.VideoCodecResolutionType.values().length];
            $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType = iArr;
            try {
                iArr[Protos.VideoCodecResolutionType.VIDEO_1280x720.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_1920x1080.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_720x1280.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_1080x1920.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_2560x1440.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_3840x2160.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_1440x2560.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_2160x3840.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$projection$proto$Protos$VideoCodecResolutionType[Protos.VideoCodecResolutionType.VIDEO_800x480.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public void setVideoFocus(int focusMode, int reason, boolean unsolicited) {
        this.mProjectionListener.onVideoFocusModeChange(focusMode, reason);
        nativeSetVideoFocus(focusMode, unsolicited);
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void videoFocusCallback(int focus, int reason) {
        this.mProjectionListener.onVideoFocusRequest(focus, reason);
    }

    @Override // com.google.android.projection.protocol.VideoSinkCallbacks
    public void updateUiConfigReplyCallback(byte[] uiConfig) {
        try {
            if (this.mCurrentUiConfig != null) {
                this.mCurrentUiConfig = (Protos.UiConfig) ((Protos.UiConfig.Builder) ((Protos.UiConfig.Builder) this.mCurrentUiConfig.toBuilder()).mergeFrom(uiConfig, ExtensionRegistryLite.getEmptyRegistry())).build();
            } else {
                this.mCurrentUiConfig = Protos.UiConfig.parseFrom(uiConfig, ExtensionRegistryLite.getEmptyRegistry());
            }
            this.mProjectionListener.onUpdateUiConfigReply(this.mCurrentUiConfig);
        } catch (Exception e) {
            this.mCurrentUiConfig = null;
            Log.e("AHU.VIDEO", "Failed to decode uiConfig!");
            throw new RuntimeException("Failed to decode uiConfig");
        }
    }

    public Protos.UiConfig getCurrentUiConfig() {
        return this.mCurrentUiConfig;
    }

    public void updateUiConfig(Protos.UiConfig uiConfig) {
        nativeUpdateUiConfig(uiConfig.toByteArray());
    }

    public void setDisplayIdAndType(int displayId, Protos.DisplayType displayType) {
        nativeSetDisplayIdAndType(displayId, displayType.getNumber());
    }

    public void setInitialContentKeyCode(Protos.KeyCode keyCode) {
        nativeSetInitialContentKeyCode(keyCode.getNumber());
    }
}
