package com.amazon.avod.playback.renderer.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Build;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.util.DLog;
import com.visualon.OSMPUtils.voMimeTypes;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TunneledPlaybackEvaluator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001d\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0010R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator;", "", "mediaCodecDeviceCapabilityDetector", "Lcom/amazon/avod/media/playback/support/MediaCodecDeviceCapabilityDetector;", "deviceResources", "Lcom/amazon/avod/playback/capability/DeviceResources;", "(Lcom/amazon/avod/media/playback/support/MediaCodecDeviceCapabilityDetector;Lcom/amazon/avod/playback/capability/DeviceResources;)V", "deviceCapabilityDetector", "tunneledPlaybackConfig", "Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator$TunneledPlaybackConfigInterface;", "(Lcom/amazon/avod/media/playback/support/MediaCodecDeviceCapabilityDetector;Lcom/amazon/avod/playback/capability/DeviceResources;Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator$TunneledPlaybackConfigInterface;)V", "evaluateTunneledPlaybackCapability", "", "videoConfig", "Lcom/amazon/avod/media/playback/util/VideoConfig;", "videoFormat", "Landroid/media/MediaFormat;", "shouldSkipSendingAudioTrackHeaderAsStandaloneSample", "isTunneledPlayback", "audioFormat", "TunneledPlaybackConfig", "TunneledPlaybackConfigInterface", "ATVPlaybackAndroidMediaCodecRenderer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@TargetApi(21)
public final class TunneledPlaybackEvaluator {
    private final MediaCodecDeviceCapabilityDetector deviceCapabilityDetector;
    private final DeviceResources deviceResources;
    private final TunneledPlaybackConfigInterface tunneledPlaybackConfig;

    /* JADX INFO: compiled from: TunneledPlaybackEvaluator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator$TunneledPlaybackConfigInterface;", "", "getSkipSendingDolbyAudioTrackHeaderAsStandaloneSample", "", "isTunnelModeEnabled", "isTunnelModeForLiveStreamingEnabled", "isTunnelModeForNonUHDPlaybackEnabled", "isTunnelModeOverBTSinkEnabled", "ATVPlaybackAndroidMediaCodecRenderer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface TunneledPlaybackConfigInterface {
        boolean getSkipSendingDolbyAudioTrackHeaderAsStandaloneSample();

        boolean isTunnelModeEnabled();

        boolean isTunnelModeForLiveStreamingEnabled();

        boolean isTunnelModeForNonUHDPlaybackEnabled();

        boolean isTunnelModeOverBTSinkEnabled();
    }

    public TunneledPlaybackEvaluator(MediaCodecDeviceCapabilityDetector deviceCapabilityDetector, DeviceResources deviceResources, TunneledPlaybackConfigInterface tunneledPlaybackConfig) {
        Intrinsics.checkNotNullParameter(deviceCapabilityDetector, "deviceCapabilityDetector");
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        Intrinsics.checkNotNullParameter(tunneledPlaybackConfig, "tunneledPlaybackConfig");
        this.deviceCapabilityDetector = deviceCapabilityDetector;
        this.deviceResources = deviceResources;
        this.tunneledPlaybackConfig = tunneledPlaybackConfig;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TunneledPlaybackEvaluator(MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, DeviceResources deviceResources) {
        this(mediaCodecDeviceCapabilityDetector, deviceResources, TunneledPlaybackConfig.INSTANCE);
        Intrinsics.checkNotNullParameter(mediaCodecDeviceCapabilityDetector, "mediaCodecDeviceCapabilityDetector");
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
    }

    public final boolean evaluateTunneledPlaybackCapability(VideoConfig videoConfig, MediaFormat videoFormat) {
        Intrinsics.checkNotNullParameter(videoConfig, "videoConfig");
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector = this.deviceCapabilityDetector;
        int i = Build.VERSION.SDK_INT;
        String string = videoFormat.getString("mime");
        Intrinsics.checkNotNull(string);
        boolean deviceTunnelModeCapability = mediaCodecDeviceCapabilityDetector.getDeviceTunnelModeCapability(i, string);
        boolean zIsBluetoothAudioOutputDeviceConnected = this.deviceCapabilityDetector.isBluetoothAudioOutputDeviceConnected(i);
        boolean z = this.tunneledPlaybackConfig.isTunnelModeEnabled() && deviceTunnelModeCapability && (this.tunneledPlaybackConfig.isTunnelModeForNonUHDPlaybackEnabled() || videoConfig.getMaxVideoResolution().isUltraHDOrHigher()) && ((this.tunneledPlaybackConfig.isTunnelModeForLiveStreamingEnabled() || !videoConfig.isDynamic()) && (this.tunneledPlaybackConfig.isTunnelModeOverBTSinkEnabled() || !zIsBluetoothAudioOutputDeviceConnected));
        String str = "Tunneled " + z + "(Cfg:" + this.tunneledPlaybackConfig.isTunnelModeEnabled() + "/Cap:" + deviceTunnelModeCapability + ", is4k:" + videoConfig.getMaxVideoResolution().isUltraHDOrHigher() + "/Non4kCfg:" + this.tunneledPlaybackConfig.isTunnelModeForNonUHDPlaybackEnabled() + ", isLive:" + videoConfig.isDynamic() + "/LiveCfg:" + this.tunneledPlaybackConfig.isTunnelModeForLiveStreamingEnabled() + ", BTSink:" + zIsBluetoothAudioOutputDeviceConnected + "/BTCfg:" + this.tunneledPlaybackConfig.isTunnelModeOverBTSinkEnabled() + ')';
        DLog.logf(str);
        this.deviceResources.setTunneledPlaybackInfo(str);
        return z;
    }

    public final boolean shouldSkipSendingAudioTrackHeaderAsStandaloneSample(boolean isTunneledPlayback, MediaFormat audioFormat) {
        Intrinsics.checkNotNullParameter(audioFormat, "audioFormat");
        boolean z = isTunneledPlayback && this.tunneledPlaybackConfig.getSkipSendingDolbyAudioTrackHeaderAsStandaloneSample() && ArraysKt___ArraysKt.contains(new String[]{voMimeTypes.VOAUDIO_AC3, voMimeTypes.VOAUDIO_E_AC3}, audioFormat.getString("mime"));
        DLog.logf("isTunneledPlayback " + isTunneledPlayback + ",skipSendingAudioTrackHeaderAsStandaloneSample " + z + ",AudioTrack mimeType " + ((Object) audioFormat.getString("mime")));
        return z;
    }

    /* JADX INFO: compiled from: TunneledPlaybackEvaluator.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0018\u0010\b\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0018\u0010\t\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0018\u0010\n\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0018\u0010\u000b\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator$TunneledPlaybackConfig;", "Lcom/amazon/avod/playback/renderer/mediacodec/TunneledPlaybackEvaluator$TunneledPlaybackConfigInterface;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "isTunnelModeEnabled", "", "kotlin.jvm.PlatformType", "Ljava/lang/Boolean;", "isTunnelModeForLiveStreamingEnabled", "isTunnelModeForNonUHDPlaybackEnabled", "isTunnelModeOverBTSinkEnabled", "skipSendingDolbyAudioTrackHeaderAsStandaloneSample", "getSkipSendingDolbyAudioTrackHeaderAsStandaloneSample", "ATVPlaybackAndroidMediaCodecRenderer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TunneledPlaybackConfig extends MediaConfigBase implements TunneledPlaybackConfigInterface {
        public static final TunneledPlaybackConfig INSTANCE;
        private static final Boolean isTunnelModeEnabled;
        private static final Boolean isTunnelModeForLiveStreamingEnabled;
        private static final Boolean isTunnelModeForNonUHDPlaybackEnabled;
        private static final Boolean isTunnelModeOverBTSinkEnabled;
        private static final Boolean skipSendingDolbyAudioTrackHeaderAsStandaloneSample;

        private TunneledPlaybackConfig() {
        }

        static {
            TunneledPlaybackConfig tunneledPlaybackConfig = new TunneledPlaybackConfig();
            INSTANCE = tunneledPlaybackConfig;
            isTunnelModeEnabled = tunneledPlaybackConfig.newBooleanConfigValue("playback_isTunnelModeEnabled", false).getValue();
            isTunnelModeOverBTSinkEnabled = tunneledPlaybackConfig.newBooleanConfigValue("playback_isTunnelModeOverBTSinkEnabled", false).getValue();
            isTunnelModeForLiveStreamingEnabled = tunneledPlaybackConfig.newBooleanConfigValue("playback_isTunnelModeForLiveStreamingEnabled", true).getValue();
            isTunnelModeForNonUHDPlaybackEnabled = tunneledPlaybackConfig.newBooleanConfigValue("playback_isTunnelModeForNonUHDPlaybackEnabled", true).getValue();
            skipSendingDolbyAudioTrackHeaderAsStandaloneSample = tunneledPlaybackConfig.newBooleanConfigValue("playback_skipSendingDolbyAudioTrackHeaderAsStandaloneSample", true).getValue();
        }

        @Override // com.amazon.avod.playback.renderer.mediacodec.TunneledPlaybackEvaluator.TunneledPlaybackConfigInterface
        public boolean isTunnelModeEnabled() {
            Boolean isTunnelModeEnabled2 = isTunnelModeEnabled;
            Intrinsics.checkNotNullExpressionValue(isTunnelModeEnabled2, "isTunnelModeEnabled");
            return isTunnelModeEnabled2.booleanValue();
        }

        @Override // com.amazon.avod.playback.renderer.mediacodec.TunneledPlaybackEvaluator.TunneledPlaybackConfigInterface
        public boolean isTunnelModeOverBTSinkEnabled() {
            Boolean isTunnelModeOverBTSinkEnabled2 = isTunnelModeOverBTSinkEnabled;
            Intrinsics.checkNotNullExpressionValue(isTunnelModeOverBTSinkEnabled2, "isTunnelModeOverBTSinkEnabled");
            return isTunnelModeOverBTSinkEnabled2.booleanValue();
        }

        @Override // com.amazon.avod.playback.renderer.mediacodec.TunneledPlaybackEvaluator.TunneledPlaybackConfigInterface
        public boolean isTunnelModeForLiveStreamingEnabled() {
            Boolean isTunnelModeForLiveStreamingEnabled2 = isTunnelModeForLiveStreamingEnabled;
            Intrinsics.checkNotNullExpressionValue(isTunnelModeForLiveStreamingEnabled2, "isTunnelModeForLiveStreamingEnabled");
            return isTunnelModeForLiveStreamingEnabled2.booleanValue();
        }

        @Override // com.amazon.avod.playback.renderer.mediacodec.TunneledPlaybackEvaluator.TunneledPlaybackConfigInterface
        public boolean isTunnelModeForNonUHDPlaybackEnabled() {
            Boolean isTunnelModeForNonUHDPlaybackEnabled2 = isTunnelModeForNonUHDPlaybackEnabled;
            Intrinsics.checkNotNullExpressionValue(isTunnelModeForNonUHDPlaybackEnabled2, "isTunnelModeForNonUHDPlaybackEnabled");
            return isTunnelModeForNonUHDPlaybackEnabled2.booleanValue();
        }

        @Override // com.amazon.avod.playback.renderer.mediacodec.TunneledPlaybackEvaluator.TunneledPlaybackConfigInterface
        public boolean getSkipSendingDolbyAudioTrackHeaderAsStandaloneSample() {
            Boolean skipSendingDolbyAudioTrackHeaderAsStandaloneSample2 = skipSendingDolbyAudioTrackHeaderAsStandaloneSample;
            Intrinsics.checkNotNullExpressionValue(skipSendingDolbyAudioTrackHeaderAsStandaloneSample2, "skipSendingDolbyAudioTrackHeaderAsStandaloneSample");
            return skipSendingDolbyAudioTrackHeaderAsStandaloneSample2.booleanValue();
        }
    }
}
