package com.amazon.avod.playback.renderer.visualon;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnDeviceCapabilityConfig extends MediaConfigBase {
    public static final VisualOnDeviceCapabilityConfig INSTANCE = new VisualOnDeviceCapabilityConfig();
    private final ConfigurationValue<Set<String>> mBlackList = newSemicolonDelimitedStringSetConfigurationValue("renderer_blackListedDevices", new String[0]);
    private final ConfigurationValue<Set<String>> mSupportedArchitectures = newSemicolonDelimitedStringSetConfigurationValue("renderer_supportedArchitectures", new String[]{"arm"});
    private final ConfigurationValue<Integer> mLimitedCapabilityCoreFrequency = newIntConfigValue("renderer_limitedCapabilityCoreFrequencyMHz", 0);
    private final ConfigurationValue<List<String>> mSupportedAudioCodecs = newSemicolonDelimitedStringListConfigurationValue("playback_visualOnSupportedAudioCodecs", new String[]{AudioStreamType.AACH.getFourCC(), AudioStreamType.AACL.getFourCC()});

    private VisualOnDeviceCapabilityConfig() {
    }

    public boolean isDeviceBlackListed(@Nonnull String str) {
        return isDeviceBlackListed(str, this.mBlackList.getValue());
    }

    boolean isDeviceBlackListed(@Nonnull String str, @Nonnull Set<String> set) {
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(!str.isEmpty());
        Preconditions.checkNotNull(set);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next().trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isSupportedArchitecture(@Nonnull VisualOnCpuArchitecture visualOnCpuArchitecture) {
        return isSupportedArchitecture(visualOnCpuArchitecture, this.mSupportedArchitectures.getValue());
    }

    boolean isSupportedArchitecture(@Nonnull VisualOnCpuArchitecture visualOnCpuArchitecture, @Nonnull Set<String> set) {
        Preconditions.checkNotNull(visualOnCpuArchitecture);
        Preconditions.checkNotNull(set);
        String strName = visualOnCpuArchitecture.name();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (strName.equalsIgnoreCase(it.next().trim())) {
                return true;
            }
        }
        return false;
    }

    public int getLimitedCapabilityCoreFrequency() {
        return this.mLimitedCapabilityCoreFrequency.getValue().intValue();
    }

    public List<String> getDefaultSupportedAudioCodecs() {
        return this.mSupportedAudioCodecs.getValue();
    }
}
