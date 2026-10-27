package org.chromium.components.segmentation_platform;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SegmentationPlatformConversionBridge {
    @CalledByNative
    public static SegmentSelectionResult createSegmentSelectionResult(boolean z, int i, boolean z2, float f) {
        int i2;
        if (i != 0) {
            i2 = 4;
            if (i == 4) {
                i2 = 2;
            } else if (i == 5) {
                i2 = 3;
            } else if (i != 6) {
                if (i == 27) {
                    i2 = 14;
                } else if (i != 28) {
                    switch (i) {
                        case 10:
                            i2 = 5;
                            break;
                        case 11:
                            i2 = 6;
                            break;
                        case 12:
                            i2 = 7;
                            break;
                        default:
                            switch (i) {
                                case 16:
                                    i2 = 8;
                                    break;
                                case 17:
                                    i2 = 9;
                                    break;
                                case 18:
                                    i2 = 10;
                                    break;
                                default:
                                    switch (i) {
                                        case 21:
                                            i2 = 11;
                                            break;
                                        case 22:
                                            i2 = 12;
                                            break;
                                        case 23:
                                            i2 = 13;
                                            break;
                                        default:
                                            switch (i) {
                                                case 999:
                                                    i2 = 16;
                                                    break;
                                                case 1000:
                                                    i2 = 17;
                                                    break;
                                                case 1001:
                                                    i2 = 18;
                                                    break;
                                                case 1002:
                                                    i2 = 19;
                                                    break;
                                                case 1003:
                                                    i2 = 20;
                                                    break;
                                                case 1004:
                                                    i2 = 21;
                                                    break;
                                                default:
                                                    i2 = 0;
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    i2 = 15;
                }
            }
        } else {
            i2 = 1;
        }
        return new SegmentSelectionResult(z, i2 != 0 ? i2 : 1, z2 ? Float.valueOf(f) : null);
    }
}
