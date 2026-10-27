package com.amazon.avod.media.playback.reporting.aloysius;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.purchase.AssociateTagProviderProxy;
import com.amazon.avod.purchase.PlayerAssociateTagRequestSource;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusDeviceReporter implements MediaEventProducer {
    private static final String DEFAULT_VERSION_NAME = "1.01.000";
    private final String mApplicationName;
    private final String mApplicationVersion;
    private final AssociateTagProviderProxy mAssociateTagProviderProxy;
    private final DeviceIdentity mDeviceIdentity;
    private DrmScheme mLastDrmScheme;
    private RendererSchemeType mLastRendererSchemeType;
    private final MediaEventQueue mMediaEventQueue;
    private final String mPlayerName;
    private final String mPlayerVersion;
    private final RendererSchemeController mRendererSchemeController;
    private static final String MANUFACTURER = Build.MANUFACTURER;
    private static final String MODEL = Build.MODEL;
    private static final String CHIPSET = Build.BOARD;
    private static final String FIRMWARE_VERSION = Build.FINGERPRINT;
    private static final String OS_VERSION = Build.VERSION.RELEASE;

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    @Nullable
    private static PackageInfo getPackageInfo(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            DLog.warnf("Couldn't read package info");
            return null;
        }
    }

    @Nonnull
    private static String getApplicationVersion(@Nullable PackageInfo packageInfo) {
        return packageInfo == null ? DEFAULT_VERSION_NAME : String.valueOf(packageInfo.versionCode);
    }

    @Nonnull
    private static String getPlayerVersion(@Nonnull String str, @Nullable String str2, @Nullable PackageInfo packageInfo) {
        String str3;
        Preconditions.checkNotNull(str, "playerName");
        return (!"Android Player SDK".equals(str) || str2 == null) ? (packageInfo == null || (str3 = packageInfo.versionName) == null) ? DEFAULT_VERSION_NAME : str3 : str2;
    }

    public AloysiusDeviceReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull DeviceIdentity deviceIdentity, @Nonnull String str, @Nullable String str2, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull Context context, @Nonnull AssociateTagProviderProxy associateTagProviderProxy) {
        this(mediaEventQueue, deviceIdentity, ((Context) Preconditions.checkNotNull(context, "context")).getPackageName(), getApplicationVersion(getPackageInfo(context)), str, getPlayerVersion(str, str2, getPackageInfo(context)), rendererSchemeController, associateTagProviderProxy);
    }

    @VisibleForTesting
    AloysiusDeviceReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull DeviceIdentity deviceIdentity, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AssociateTagProviderProxy associateTagProviderProxy) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mApplicationName = (String) Preconditions.checkNotNull(str, CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME);
        this.mApplicationVersion = (String) Preconditions.checkNotNull(str2, "applicationVersion");
        this.mPlayerName = (String) Preconditions.checkNotNull(str3, "playerName");
        this.mPlayerVersion = (String) Preconditions.checkNotNull(str4, "playerVersion");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mAssociateTagProviderProxy = (AssociateTagProviderProxy) Preconditions.checkNotNull(associateTagProviderProxy, "associateTagProviderProxy");
    }

    public void reportDeviceEventIfRendererChanged(@Nonnull RendererSchemeType rendererSchemeType, @Nonnull DrmScheme drmScheme) {
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        if (rendererSchemeType == this.mLastRendererSchemeType && drmScheme == this.mLastDrmScheme) {
            return;
        }
        this.mLastRendererSchemeType = rendererSchemeType;
        this.mLastDrmScheme = drmScheme;
        produce();
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public void produce() {
        this.mMediaEventQueue.add(createMediaEvent());
    }

    @Nonnull
    private MediaEvent createMediaEvent() {
        DeviceMediaEvent.Builder builder = new DeviceMediaEvent.Builder();
        builder.hardware(MANUFACTURER, MODEL, CHIPSET, this.mDeviceIdentity.getDeviceTypeId());
        builder.firmware(FIRMWARE_VERSION);
        builder.operatingSystem("Android", OS_VERSION);
        builder.application(this.mApplicationName, this.mApplicationVersion);
        builder.player(this.mPlayerName, this.mPlayerVersion);
        if (this.mLastRendererSchemeType == null || this.mLastDrmScheme == null) {
            RendererScheme rendererScheme = this.mRendererSchemeController.getRendererScheme(null, null);
            this.mLastRendererSchemeType = rendererScheme.getSchemeType();
            this.mLastDrmScheme = rendererScheme.getDrmFramework().getCurrentDrmScheme();
        }
        builder.renderer(this.mLastRendererSchemeType.getShorthand(), this.mLastDrmScheme.name());
        builder.attributeTag(this.mAssociateTagProviderProxy.getAssociateTag(PlayerAssociateTagRequestSource.Aloysius).orNull());
        return builder.build();
    }

    @VisibleForTesting
    static class DeviceMediaEvent implements MediaEvent {
        private final Hardware mHardware;
        private final Software mSoftware;

        private DeviceMediaEvent(@Nonnull Builder builder) {
            this.mHardware = ((Builder) Preconditions.checkNotNull(builder, "builder")).mHardware;
            this.mSoftware = new Software(builder.mFirmware, builder.mOperatingSystem, builder.mApplication, builder.mPlayer, builder.mRenderer, builder.mAttributeTag);
        }

        @Nonnull
        public Hardware getHardware() {
            return this.mHardware;
        }

        @Nonnull
        public Software getSoftware() {
            return this.mSoftware;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Device;
        }

        public static class Hardware {
            private final String mChipset;
            private final String mClassTypeId;
            private final String mManufacturer;
            private final String mModelName;

            private Hardware(@Nonnull String str, @Nullable String str2, @Nullable String str3, @Nonnull String str4) {
                this.mManufacturer = (String) Preconditions.checkNotNull(str, "manufacturer");
                this.mModelName = str2;
                this.mChipset = str3;
                this.mClassTypeId = (String) Preconditions.checkNotNull(str4, "classTypeId");
            }

            @Nonnull
            public String getManufacturer() {
                return this.mManufacturer;
            }

            @Nullable
            public String getModelName() {
                return this.mModelName;
            }

            @Nullable
            public String getChipset() {
                return this.mChipset;
            }

            @Nonnull
            public String getClassTypeId() {
                return this.mClassTypeId;
            }
        }

        @VisibleForTesting
        static class Software {
            private final SoftwareInfo mApplication;
            private final String mAttributionTag;
            private final Firmware mFirmware;
            private final SoftwareInfo mOperatingSystem;
            private final SoftwareInfo mPlayer;
            private final SoftwareInfo mRenderer;

            private Software(@Nonnull Firmware firmware, @Nonnull SoftwareInfo softwareInfo, @Nonnull SoftwareInfo softwareInfo2, @Nonnull SoftwareInfo softwareInfo3, @Nonnull SoftwareInfo softwareInfo4, @Nullable String str) {
                this.mFirmware = firmware;
                this.mOperatingSystem = softwareInfo;
                this.mApplication = (SoftwareInfo) Preconditions.checkNotNull(softwareInfo2, "application");
                this.mPlayer = (SoftwareInfo) Preconditions.checkNotNull(softwareInfo3, "player");
                this.mRenderer = (SoftwareInfo) Preconditions.checkNotNull(softwareInfo4, "renderer");
                this.mAttributionTag = str;
            }

            @Nullable
            public Firmware getFirmware() {
                return this.mFirmware;
            }

            @Nullable
            public SoftwareInfo getOperatingSystem() {
                return this.mOperatingSystem;
            }

            @Nonnull
            public SoftwareInfo getApplication() {
                return this.mApplication;
            }

            @Nonnull
            public SoftwareInfo getPlayer() {
                return this.mPlayer;
            }

            @Nonnull
            public SoftwareInfo getRenderer() {
                return this.mRenderer;
            }

            @Nullable
            public String getAttributionTag() {
                return this.mAttributionTag;
            }

            @VisibleForTesting
            static class Firmware {
                private final String mVersion;

                private Firmware(@Nonnull String str) {
                    this.mVersion = (String) Preconditions.checkNotNull(str, "version");
                }

                @Nonnull
                public String getVersion() {
                    return this.mVersion;
                }
            }

            @VisibleForTesting
            static class SoftwareInfo {
                private final String mName;
                private final String mVersion;

                private SoftwareInfo(@Nonnull String str, @Nonnull String str2) {
                    this.mName = (String) Preconditions.checkNotNull(str, "name");
                    this.mVersion = (String) Preconditions.checkNotNull(str2, "version");
                }

                @Nonnull
                public String getName() {
                    return this.mName;
                }

                @Nonnull
                public String getVersion() {
                    return this.mVersion;
                }
            }
        }

        private static class Builder {
            private Software.SoftwareInfo mApplication;
            private String mAttributeTag;
            private Software.Firmware mFirmware;
            private Hardware mHardware;
            private Software.SoftwareInfo mOperatingSystem;
            private Software.SoftwareInfo mPlayer;
            private Software.SoftwareInfo mRenderer;

            private Builder() {
            }

            public Builder hardware(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4) {
                this.mHardware = new Hardware(str, str2, str3, str4);
                return this;
            }

            public Builder firmware(@Nonnull String str) {
                this.mFirmware = new Software.Firmware(str);
                return this;
            }

            public Builder operatingSystem(@Nonnull String str, @Nonnull String str2) {
                this.mOperatingSystem = new Software.SoftwareInfo(str, str2);
                return this;
            }

            public Builder application(@Nonnull String str, @Nonnull String str2) {
                this.mApplication = new Software.SoftwareInfo(str, str2);
                return this;
            }

            public Builder player(@Nonnull String str, @Nonnull String str2) {
                this.mPlayer = new Software.SoftwareInfo(str, str2);
                return this;
            }

            public Builder renderer(@Nonnull String str, @Nonnull String str2) {
                this.mRenderer = new Software.SoftwareInfo(str, str2);
                return this;
            }

            public Builder attributeTag(@Nullable String str) {
                this.mAttributeTag = str;
                return this;
            }

            public MediaEvent build() {
                return new DeviceMediaEvent(this);
            }
        }
    }
}
