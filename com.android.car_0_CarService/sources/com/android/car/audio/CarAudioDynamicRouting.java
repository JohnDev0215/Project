package com.android.car.audio;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.audiopolicy.AudioMix;
import android.media.audiopolicy.AudioMixingRule;
import android.media.audiopolicy.AudioPolicy;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.CarLog;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class CarAudioDynamicRouting {
    static final int[] STREAM_TYPES = {3, 4, 2};
    static final int[] STREAM_TYPE_USAGES = {1, 4, 6};

    CarAudioDynamicRouting() {
    }

    static void setupAudioDynamicRouting(AudioPolicy.Builder builder, SparseArray<CarAudioZone> carAudioZones) {
        for (int i = 0; i < carAudioZones.size(); i++) {
            CarAudioZone zone = carAudioZones.valueAt(i);
            for (CarVolumeGroup group : zone.getVolumeGroups()) {
                setupAudioDynamicRoutingForGroup(group, builder);
            }
        }
    }

    private static void setupAudioDynamicRoutingForGroup(CarVolumeGroup group, AudioPolicy.Builder builder) {
        for (String address : group.getAddresses()) {
            boolean hasContext = false;
            CarAudioDeviceInfo info = group.getCarAudioDeviceInfoForAddress(address);
            AudioFormat mixFormat = new AudioFormat.Builder().setSampleRate(info.getSampleRate()).setEncoding(info.getEncodingFormat()).setChannelMask(info.getChannelCount()).build();
            AudioMixingRule.Builder mixingRuleBuilder = new AudioMixingRule.Builder();
            for (int carAudioContext : group.getContextsForAddress(address)) {
                hasContext = true;
                int[] usages = CarAudioContext.getUsagesForContext(carAudioContext);
                for (int usage : usages) {
                    AudioAttributes attributes = buildAttributesWithUsage(usage);
                    mixingRuleBuilder.addRule(attributes, 1);
                }
                if (Log.isLoggable(CarLog.TAG_AUDIO, 3)) {
                    Log.d(CarLog.TAG_AUDIO, String.format("Address: %s AudioContext: %s sampleRate: %d channels: %d usages: %s", address, Integer.valueOf(carAudioContext), Integer.valueOf(info.getSampleRate()), Integer.valueOf(info.getChannelCount()), Arrays.toString(usages)));
                }
            }
            if (hasContext) {
                AudioMix audioMix = new AudioMix.Builder(mixingRuleBuilder.build()).setFormat(mixFormat).setDevice(info.getAudioDeviceInfo()).setRouteFlags(1).build();
                builder.addMix(audioMix);
            }
        }
    }

    private static AudioAttributes buildAttributesWithUsage(int usage) {
        AudioAttributes.Builder attributesBuilder = new AudioAttributes.Builder();
        if (AudioAttributes.isSystemUsage(usage)) {
            attributesBuilder.setSystemUsage(usage);
        } else {
            attributesBuilder.setUsage(usage);
        }
        return attributesBuilder.build();
    }
}
