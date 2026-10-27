package com.amazon.music.casting.util;

import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class VolumeUtil {
    public static float mapVolumeLevel(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.round(((((f - f2) * (f5 - f4)) / (f3 - f2)) + f4) / f6) * f6;
    }

    public static String generateDirectionRequestId() {
        return UUID.randomUUID().toString();
    }
}
