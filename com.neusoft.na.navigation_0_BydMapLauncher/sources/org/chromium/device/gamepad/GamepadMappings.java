package org.chromium.device.gamepad;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class GamepadMappings {
    static final String AMAZON_FIRE_DEVICE_NAME = "Amazon Fire Game Controller";
    static final String MICROSOFT_XBOX_PAD_DEVICE_NAME = "Microsoft X-Box 360 pad";
    static final String NVIDIA_SHIELD_DEVICE_NAME_PREFIX = "NVIDIA Corporation NVIDIA Controller";
    static final String PS3_SIXAXIS_DEVICE_NAME = "Sony PLAYSTATION(R)3 Controller";
    static final String SAMSUNG_EI_GP20_DEVICE_NAME = "Samsung Game Pad EI-GP20";

    static float negativeAxisValueAsButton(float f) {
        return f < -0.5f ? 1.0f : 0.0f;
    }

    static float positiveAxisValueAsButton(float f) {
        return f > 0.5f ? 1.0f : 0.0f;
    }

    GamepadMappings() {
    }

    public static boolean mapToStandardGamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, String str) {
        if (str.startsWith(NVIDIA_SHIELD_DEVICE_NAME_PREFIX)) {
            mapShieldGamepad(fArr2, fArr4, fArr, fArr3);
            return true;
        }
        if (str.equals(MICROSOFT_XBOX_PAD_DEVICE_NAME)) {
            mapXBox360Gamepad(fArr2, fArr4, fArr, fArr3);
            return true;
        }
        if (str.equals(PS3_SIXAXIS_DEVICE_NAME)) {
            mapPS3SixAxisGamepad(fArr2, fArr4, fArr, fArr3);
            return true;
        }
        if (str.equals(SAMSUNG_EI_GP20_DEVICE_NAME)) {
            mapSamsungEIGP20Gamepad(fArr2, fArr4, fArr, fArr3);
            return true;
        }
        if (str.equals(AMAZON_FIRE_DEVICE_NAME)) {
            mapAmazonFireGamepad(fArr2, fArr4, fArr, fArr3);
            return true;
        }
        mapUnknownGamepad(fArr2, fArr4, fArr, fArr3);
        return false;
    }

    private static void mapCommonXYABButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[96];
        float f2 = fArr2[97];
        float f3 = fArr2[99];
        float f4 = fArr2[100];
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    private static void mapCommonStartSelectMetaButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[108];
        float f2 = fArr2[109];
        float f3 = fArr2[110];
        fArr[9] = f;
        fArr[8] = f2;
        fArr[16] = f3;
    }

    private static void mapCommonThumbstickButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[106];
        float f2 = fArr2[107];
        fArr[10] = f;
        fArr[11] = f2;
    }

    private static void mapCommonTriggerButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[102];
        float f2 = fArr2[103];
        fArr[6] = f;
        fArr[7] = f2;
    }

    private static void mapTriggerButtonsToTopShoulder(float[] fArr, float[] fArr2) {
        float f = fArr2[102];
        float f2 = fArr2[103];
        fArr[4] = f;
        fArr[5] = f2;
    }

    private static void mapCommonDpadButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[20];
        float f2 = fArr2[19];
        float f3 = fArr2[21];
        float f4 = fArr2[22];
        fArr[13] = f;
        fArr[12] = f2;
        fArr[14] = f3;
        fArr[15] = f4;
    }

    private static void mapXYAxes(float[] fArr, float[] fArr2) {
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
    }

    private static void mapRXAndRYAxesToRightStick(float[] fArr, float[] fArr2) {
        fArr[2] = fArr2[12];
        fArr[3] = fArr2[13];
    }

    private static void mapZAndRZAxesToRightStick(float[] fArr, float[] fArr2) {
        fArr[2] = fArr2[11];
        fArr[3] = fArr2[14];
    }

    private static void mapTriggerAxexToShoulderButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[17];
        float f2 = fArr2[18];
        fArr[4] = f;
        fArr[5] = f2;
    }

    private static void mapPedalAxesToBottomShoulder(float[] fArr, float[] fArr2) {
        float f = fArr2[23];
        float f2 = fArr2[22];
        fArr[6] = f;
        fArr[7] = f2;
    }

    private static void mapTriggerAxesToBottomShoulder(float[] fArr, float[] fArr2) {
        float f = fArr2[17];
        float f2 = fArr2[18];
        fArr[6] = f;
        fArr[7] = f2;
    }

    private static void mapHatAxisToDpadButtons(float[] fArr, float[] fArr2) {
        float f = fArr2[15];
        float f2 = fArr2[16];
        fArr[14] = negativeAxisValueAsButton(f);
        fArr[15] = positiveAxisValueAsButton(f);
        fArr[12] = negativeAxisValueAsButton(f2);
        fArr[13] = positiveAxisValueAsButton(f2);
    }

    private static void mapAmazonFireGamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        mapCommonXYABButtons(fArr, fArr2);
        mapTriggerButtonsToTopShoulder(fArr, fArr2);
        mapCommonThumbstickButtons(fArr, fArr2);
        mapCommonStartSelectMetaButtons(fArr, fArr2);
        mapPedalAxesToBottomShoulder(fArr, fArr4);
        mapHatAxisToDpadButtons(fArr, fArr4);
        mapXYAxes(fArr3, fArr4);
        mapZAndRZAxesToRightStick(fArr3, fArr4);
    }

    private static void mapShieldGamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        mapCommonXYABButtons(fArr, fArr2);
        mapTriggerButtonsToTopShoulder(fArr, fArr2);
        mapCommonThumbstickButtons(fArr, fArr2);
        mapCommonStartSelectMetaButtons(fArr, fArr2);
        mapTriggerAxesToBottomShoulder(fArr, fArr4);
        mapHatAxisToDpadButtons(fArr, fArr4);
        mapXYAxes(fArr3, fArr4);
        mapZAndRZAxesToRightStick(fArr3, fArr4);
    }

    private static void mapXBox360Gamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        mapShieldGamepad(fArr, fArr2, fArr3, fArr4);
    }

    private static void mapPS3SixAxisGamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        float f = fArr2[96];
        float f2 = fArr2[97];
        float f3 = fArr2[99];
        float f4 = fArr2[100];
        fArr[0] = f3;
        fArr[1] = f4;
        fArr[2] = f;
        fArr[3] = f2;
        mapCommonTriggerButtons(fArr, fArr2);
        mapCommonThumbstickButtons(fArr, fArr2);
        mapCommonDpadButtons(fArr, fArr2);
        mapCommonStartSelectMetaButtons(fArr, fArr2);
        mapTriggerAxexToShoulderButtons(fArr, fArr4);
        mapXYAxes(fArr3, fArr4);
        mapZAndRZAxesToRightStick(fArr3, fArr4);
    }

    private static void mapSamsungEIGP20Gamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        mapCommonXYABButtons(fArr, fArr2);
        mapCommonTriggerButtons(fArr, fArr2);
        mapCommonThumbstickButtons(fArr, fArr2);
        mapCommonStartSelectMetaButtons(fArr, fArr2);
        mapHatAxisToDpadButtons(fArr, fArr4);
        mapXYAxes(fArr3, fArr4);
        mapRXAndRYAxesToRightStick(fArr3, fArr4);
    }

    private static void mapUnknownGamepad(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        mapCommonXYABButtons(fArr, fArr2);
        mapCommonTriggerButtons(fArr, fArr2);
        mapCommonThumbstickButtons(fArr, fArr2);
        mapCommonStartSelectMetaButtons(fArr, fArr2);
        mapTriggerAxexToShoulderButtons(fArr, fArr4);
        mapCommonDpadButtons(fArr, fArr2);
        mapXYAxes(fArr3, fArr4);
        mapRXAndRYAxesToRightStick(fArr3, fArr4);
    }
}
