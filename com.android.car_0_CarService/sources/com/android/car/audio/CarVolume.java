package com.android.car.audio;

import android.media.AudioAttributes;
import android.media.AudioPlaybackConfiguration;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class CarVolume {
    private static final int CONTEXT_NOT_PRIORITIZED = -1;
    private static final String TAG = CarVolume.class.getSimpleName();
    private static final int[] AUDIO_CONTEXT_VOLUME_PRIORITY = {2, 5, 1, 12, 3, 4, 8, 10, 6, 7, 11, 9};
    private static final SparseIntArray VOLUME_PRIORITY_BY_AUDIO_CONTEXT = new SparseIntArray();

    CarVolume() {
    }

    static {
        int priority = 0;
        while (true) {
            int[] iArr = AUDIO_CONTEXT_VOLUME_PRIORITY;
            if (priority < iArr.length) {
                VOLUME_PRIORITY_BY_AUDIO_CONTEXT.append(iArr[priority], priority);
                priority++;
            } else {
                return;
            }
        }
    }

    static int getSuggestedAudioContext(List<AudioPlaybackConfiguration> configurations, int callState) {
        int currentContext = CarAudioService.DEFAULT_AUDIO_CONTEXT;
        int currentPriority = AUDIO_CONTEXT_VOLUME_PRIORITY.length;
        if (callState == 1) {
            currentContext = 4;
            currentPriority = VOLUME_PRIORITY_BY_AUDIO_CONTEXT.get(4);
        } else if (callState == 2) {
            currentContext = 5;
            currentPriority = VOLUME_PRIORITY_BY_AUDIO_CONTEXT.get(5);
        }
        for (AudioPlaybackConfiguration configuration : configurations) {
            if (configuration.isActive()) {
                int usage = configuration.getAudioAttributes().getSystemUsage();
                int context = CarAudioContext.getContextForUsage(usage);
                int priority = VOLUME_PRIORITY_BY_AUDIO_CONTEXT.get(context, -1);
                if (priority == -1) {
                    Log.w(TAG, "Usage " + AudioAttributes.usageToString(usage) + " mapped to context " + CarAudioContext.toString(context) + " which is not prioritized");
                } else if (priority < currentPriority) {
                    currentContext = context;
                    currentPriority = priority;
                }
            }
        }
        return currentContext;
    }
}
