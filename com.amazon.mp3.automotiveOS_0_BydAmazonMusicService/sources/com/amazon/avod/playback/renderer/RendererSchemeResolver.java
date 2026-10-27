package com.amazon.avod.playback.renderer;

import android.content.Context;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.AbstractMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class RendererSchemeResolver {
    private final RendererSchemeResolverConfig mConfig;
    private String mCurrentDeviceTypeId;
    private final RendererSchemeFactory mFactory;
    private final boolean mIsChromeOsDetectionEnabled;
    private final boolean mIsEmulator;
    private final boolean mIsMultiRendererSchemeResolutionEnabled;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final StringBuilder mResolutionChain;
    private final boolean mShouldDisableVisualOnRendererOnAndroidTv;
    private static final Set<String> ANDROID_3P_DEVICES_DTIDS = ImmutableSet.of("A1MPSLFC7L5AFK", "A1PY8QQU9P0WJV", "A43PXU4ZN2AL1");
    private static final Set<String> OMXIL_RENDERER_SCHEME_DTIDS = ImmutableSet.of("A2W5AJPLW5Q6YM", "A2MJ2WHF2K4V21", "AC5DXSR5G8JPX", "A2TR7IN2V8NATY", "ANDK8J66NR3L", "AX5Q0TWIKDVK1", "AXWOV4XZMCHP1", "A3KMUO14KJF686", "A831W44HZ8ZX9", "A1CJBQKTERGM4W", "A17PYKRA4ES6YB", "AFRP7VQQ7US69", "A2E50Q8IVZ79QG", "ANSTXZRNSPRXT", "A1KF4O3GA2MPZU", "A2XEBUI9EJ55OK", "A2VZ790DVVI91K", "A7YWW3KUHA54O", "A3DOA4P2WFIK9D", "ABJRG3JXAY5JL", "A2IK56KYGDHUVQ", "AI00TLC8V1PCT", "A2YWQEZX3UC3J1", "A1EXS4KWCX7GYC", "AM08S97P8ESGT", "A3MEKX9EL7SW8T", "A1GXILNJBV9CU7", "A2CBAN119017AE", "A3FO6QR7E7PFQX", "A2EOVT31LL6KPV", "A1FNA83TYYU3QK", "A27OOP63XLO9TI", "A3JB7490MR9K86", "ANHPQG9GCMO4A", "A234HDVPYTUVNS", "AJ3B6LDE2HP5J", "A2GZIBBOG0DCV4", "A2E0SNTXJVT7WK", "ADVBD696BHNV5", "A12GXV8XMS007S", "A2LWARUGJLBYEW", "A3SNN7STP3MAW9", "A2L2XAR79NB8LF", "A30CIFXHZJQA05", "A2RDJ5BI7KDD92", "A1AAU7AIFRCQMO", "AIEV7U38725PT", "AIQEAO9TPRZWG", "A2NX0Y9UV2GWHF", "A390CW53E1P0G4", "A2D0X18EHNKEOJ", "A3JN21B5ZOWUAN", "A1TG8VNKP4DSQR", "A2XZMRZUFPEDN4", "A2LJ4A527WOX9J", "A2TIAYXTNQWU3T", "ADP5BND5THPTX", "A1H8RTR0E3Y362", "AUNIXHOL9EVMI", "AZKAGPPWORIRY", "A324X3KDTS7NYA", "A25521KS9QCAMD", "AGQHFIWNI20PO", "A27XSKZJJPVQA4", "A1LCAPNEM1C36Z", "A31POKKHZJR1J4", "AOLDXB6WYN0UM", "A2RJLFEH0UEKI9", "AE5DW8GVLP9NX", "AIE8AMJ60B7OK", "A3ZKCWKG4097P", "A2H1I0AR67NWAC", "A3QXXOBP9MU5LY", "A1BSQJM6E77NJE", "AAJB0R7QJO84W", "AO4A5QLO9663Q", "A15MU3EQ4XZ3Y5", "A3IWJ2DYJQRA3T", "A1Q878J3NE8P81", "A2M1CHUCI6RHN8", "A6IUL9CVJZXRR", "A1FYY15VCM5WG1", "A1G2XVSR1VA5DI", "A3SSWQ04XYPXBH", "A2TX61L00VISA5", "A1S15DUFSI8AUG", "ALYWZPYF4JAIT", "A2M4YX06LWP8WI", "A3L0T0VL9A921N");
    private static final AvailabilityStatus OFF_BY_CONFIG = new AvailabilityStatus(AvailabilityCode.OFF_BY_CONFIG);
    private static final AvailabilityStatus NOT_FOUND_UPON_REFLECTION = new AvailabilityStatus(AvailabilityCode.NOT_FOUND_UPON_REFLECTION);

    public RendererSchemeResolver() {
        this(new RendererSchemeFactory(), RendererSchemeResolverConfig.getInstance(), PlaybackPmetMetricReporter.getInstance());
    }

    RendererSchemeResolver(@Nonnull RendererSchemeFactory rendererSchemeFactory, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mFactory = (RendererSchemeFactory) Preconditions.checkNotNull(rendererSchemeFactory, "factory");
        this.mResolutionChain = new StringBuilder();
        RendererSchemeResolverConfig rendererSchemeResolverConfig2 = (RendererSchemeResolverConfig) Preconditions.checkNotNull(rendererSchemeResolverConfig, "config");
        this.mConfig = rendererSchemeResolverConfig2;
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mIsMultiRendererSchemeResolutionEnabled = rendererSchemeResolverConfig2.isMultiRendererSchemeResolutionEnabled();
        this.mIsChromeOsDetectionEnabled = rendererSchemeResolverConfig2.isChromeOsDetectionEnabled();
        this.mShouldDisableVisualOnRendererOnAndroidTv = rendererSchemeResolverConfig2.shouldDisableVisualOnRendererOnAndroidTv();
        this.mIsEmulator = rendererSchemeResolverConfig2.isEmulator();
    }

    @Nonnull
    private List<Map.Entry<String, WhitelistBlacklistAvailabilityConfig>> getAvailableSchemes(@Nonnull Context context, @Nonnull DeviceIdentity deviceIdentity, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
        deviceIdentity.waitOnInitialized();
        this.mCurrentDeviceTypeId = deviceIdentity.getDeviceTypeId();
        boolean zIsAutoSchemeResolutionEnabled = this.mConfig.isAutoSchemeResolutionEnabled();
        boolean z = rendererSchemeOverrides.getForceMediaCodecMediaDrm() || !(OMXIL_RENDERER_SCHEME_DTIDS.contains(this.mCurrentDeviceTypeId) || ANDROID_3P_DEVICES_DTIDS.contains(this.mCurrentDeviceTypeId)) || this.mIsEmulator || zIsAutoSchemeResolutionEnabled;
        boolean zContains = OMXIL_RENDERER_SCHEME_DTIDS.contains(this.mCurrentDeviceTypeId);
        boolean z2 = (this.mIsChromeOsDetectionEnabled && this.mConfig.isChromeOs(context)) || rendererSchemeOverrides.getForceMediaCodecSwPlayReady() || zIsAutoSchemeResolutionEnabled || this.mConfig.isMediaCodecSoftwarePlayreadyOverrideEnabled(deviceIdentity);
        boolean z3 = this.mShouldDisableVisualOnRendererOnAndroidTv && this.mConfig.isAndroidTv(context);
        ImmutableList.Builder builder = ImmutableList.builder();
        ThirdPartyConfigurationProfile thirdPartyConfigurationProfile = ThirdPartyConfigurationProfile.getInstance();
        Boolean bool = Boolean.FALSE;
        return builder.add(new AbstractMap.SimpleImmutableEntry("com.amazon.avod.playback.renderer.mediacodec.MediaCodecMediaDrmRendererScheme", new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.MEDIA_CODEC_MEDIA_DRM, thirdPartyConfigurationProfile, "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), z))).add(new AbstractMap.SimpleImmutableEntry("com.amazon.avod.playback.renderer.mediacodec.MediaCodecSoftwarePlayReadyRendererScheme", new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.MEDIA_CODEC_SOFTWARE_PLAYREADY, ThirdPartyConfigurationProfile.getInstance(), String.format(Locale.US, "%s:%s", ThirdPartyProfileName.SD_MEDIACODEC_SOFTWARE_PLAYREADY, "true"), bool, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), z2))).add(new AbstractMap.SimpleImmutableEntry("com.amazon.avod.playback.renderer.tate.OMXILRendererScheme", new WhitelistBlacklistAvailabilityConfig("OMXIL", zContains))).add(new AbstractMap.SimpleImmutableEntry("com.amazon.avod.playback.renderer.visualon.VisualOnRendererScheme", new WhitelistBlacklistAvailabilityConfig("VisualOn", !z3))).build();
    }

    @Nonnull
    public ImmutableList<RendererScheme> resolveSchemes(@Nonnull Context context, @Nonnull DeviceIdentity deviceIdentity, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) throws MediaException {
        return resolveSchemes(context, getAvailableSchemes(context, deviceIdentity, rendererSchemeOverrides));
    }

    @Nonnull
    ImmutableList<RendererScheme> resolveSchemes(@Nonnull Context context, @Nonnull List<Map.Entry<String, WhitelistBlacklistAvailabilityConfig>> list) throws MediaException {
        Preconditions.checkNotNull(context, "appContext");
        Preconditions.checkNotNull(list, "schemes");
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            Map.Entry<String, WhitelistBlacklistAvailabilityConfig> entry = list.get(i);
            String key = entry.getKey();
            if (!entry.getValue().isAvailableForDevice()) {
                DLog.logf("Skipping %s because it is disabled by server config or weblab", key);
                appendToResolutionChainStringAndReport(key, OFF_BY_CONFIG);
            } else {
                RendererScheme rendererSchemeCreateScheme = this.mFactory.createScheme(key);
                if (rendererSchemeCreateScheme != null) {
                    AvailabilityStatus availabilityStatusIsAvailable = rendererSchemeCreateScheme.isAvailable(context);
                    if (availabilityStatusIsAvailable.isAvailable()) {
                        String simpleName = rendererSchemeCreateScheme.getClass().getSimpleName();
                        appendToResolutionChainStringAndReport(key, availabilityStatusIsAvailable);
                        DLog.logf("Adding %s to renderer scheme list", simpleName);
                        builder.add(rendererSchemeCreateScheme);
                        if (!this.mIsMultiRendererSchemeResolutionEnabled) {
                            DLog.warnf("Multi renderer scheme resolution is disabled.");
                            break;
                        }
                    } else {
                        DLog.logf("Skipping %s because it is unavailable: %s", key, availabilityStatusIsAvailable);
                        appendToResolutionChainStringAndReport(key, availabilityStatusIsAvailable);
                    }
                } else {
                    DLog.logf("Skipping %s because it was not found via reflection", key);
                    appendToResolutionChainStringAndReport(key, NOT_FOUND_UPON_REFLECTION);
                }
            }
            i++;
        }
        ImmutableList<RendererScheme> immutableListBuild = builder.build();
        if (immutableListBuild.isEmpty()) {
            throw new PlaybackException(PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR, String.format(Locale.US, "No supported rendering scheme found, Resolution chain: %s", this.mResolutionChain));
        }
        DLog.logf("Resolved %s renderer schemes: %s", Integer.valueOf(immutableListBuild.size()), getResolutionChain());
        return immutableListBuild;
    }

    @Nonnull
    public String getResolutionChain() {
        return this.mResolutionChain.toString();
    }

    private void appendToResolutionChainStringAndReport(@Nonnull String str, @Nonnull AvailabilityStatus availabilityStatus) {
        String strSubstring = str.substring(str.lastIndexOf(46) + 1);
        StringBuilder sb = this.mResolutionChain;
        sb.append(strSubstring);
        sb.append(":");
        sb.append(availabilityStatus);
        sb.append(">");
        if (ANDROID_3P_DEVICES_DTIDS.contains(this.mCurrentDeviceTypeId)) {
            this.mPlaybackPmetMetricReporter.reportRendererSchemeAvailabilityStatus(strSubstring, availabilityStatus);
        }
    }

    public static class RendererSchemeFactory {
        @Nullable
        public RendererScheme createScheme(@Nonnull String str) {
            Preconditions.checkNotNull(str, "fullyQualifiedSchemeName");
            try {
                return (RendererScheme) CastUtils.castTo(Class.forName(str).getConstructors()[0].newInstance(new Object[0]), RendererScheme.class);
            } catch (ClassNotFoundException | Exception unused) {
                return null;
            }
        }
    }
}
