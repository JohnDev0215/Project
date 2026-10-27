package com.android.car.audio;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class CarAudioContext {
    static final int ALARM = 6;
    static final int ANNOUNCEMENT = 12;
    static final int CALL = 5;
    static final int CALL_RING = 4;
    static final int[] CONTEXTS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private static final SparseArray<String> CONTEXT_NAMES;
    private static final SparseArray<int[]> CONTEXT_TO_USAGES;
    static final int EMERGENCY = 9;
    static final int INVALID = 0;
    static final int MUSIC = 1;
    static final int NAVIGATION = 2;
    static final int NOTIFICATION = 7;
    static final int SAFETY = 10;
    static final int SYSTEM_SOUND = 8;
    private static final SparseIntArray USAGE_TO_CONTEXT;
    static final int VEHICLE_STATUS = 11;
    static final int VOICE_COMMAND = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioContext {
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>(CONTEXTS.length + 1);
        CONTEXT_NAMES = sparseArray;
        sparseArray.append(0, "INVALID");
        CONTEXT_NAMES.append(1, "MUSIC");
        CONTEXT_NAMES.append(2, "NAVIGATION");
        CONTEXT_NAMES.append(3, "VOICE_COMMAND");
        CONTEXT_NAMES.append(4, "CALL_RING");
        CONTEXT_NAMES.append(5, "CALL");
        CONTEXT_NAMES.append(6, "ALARM");
        CONTEXT_NAMES.append(7, "NOTIFICATION");
        CONTEXT_NAMES.append(8, "SYSTEM_SOUND");
        CONTEXT_NAMES.append(9, "EMERGENCY");
        CONTEXT_NAMES.append(10, "SAFETY");
        CONTEXT_NAMES.append(11, "VEHICLE_STATUS");
        CONTEXT_NAMES.append(12, "ANNOUNCEMENT");
        SparseArray<int[]> sparseArray2 = new SparseArray<>();
        CONTEXT_TO_USAGES = sparseArray2;
        sparseArray2.put(1, new int[]{0, 14, 1});
        CONTEXT_TO_USAGES.put(2, new int[]{12});
        CONTEXT_TO_USAGES.put(3, new int[]{11, 16});
        CONTEXT_TO_USAGES.put(4, new int[]{6});
        CONTEXT_TO_USAGES.put(5, new int[]{2, 3});
        CONTEXT_TO_USAGES.put(6, new int[]{4});
        CONTEXT_TO_USAGES.put(7, new int[]{5, 7, 8, 9, 10});
        CONTEXT_TO_USAGES.put(8, new int[]{13});
        CONTEXT_TO_USAGES.put(9, new int[]{1000});
        CONTEXT_TO_USAGES.put(10, new int[]{1001});
        CONTEXT_TO_USAGES.put(11, new int[]{1002});
        CONTEXT_TO_USAGES.put(12, new int[]{1003});
        CONTEXT_TO_USAGES.put(0, new int[]{15});
        USAGE_TO_CONTEXT = new SparseIntArray();
        for (int i = 0; i < CONTEXT_TO_USAGES.size(); i++) {
            int audioContext = CONTEXT_TO_USAGES.keyAt(i);
            int[] usages = CONTEXT_TO_USAGES.valueAt(i);
            for (int usage : usages) {
                USAGE_TO_CONTEXT.put(usage, audioContext);
            }
        }
    }

    static void preconditionCheckAudioContext(int audioContext) {
        Preconditions.checkArgument(Arrays.binarySearch(CONTEXTS, audioContext) >= 0, "audioContext %d is invalid", new Object[]{Integer.valueOf(audioContext)});
    }

    static int[] getUsagesForContext(int carAudioContext) {
        preconditionCheckAudioContext(carAudioContext);
        return CONTEXT_TO_USAGES.get(carAudioContext);
    }

    static int getContextForUsage(int audioUsage) {
        return USAGE_TO_CONTEXT.get(audioUsage, 0);
    }

    static String toString(int audioContext) {
        String name = CONTEXT_NAMES.get(audioContext);
        if (name != null) {
            return name;
        }
        return "Unsupported Context 0x" + Integer.toHexString(audioContext);
    }
}
