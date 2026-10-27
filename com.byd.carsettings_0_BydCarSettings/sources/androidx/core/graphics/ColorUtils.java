package androidx.core.graphics;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import com.google.android.material.transformation.FabTransformationScrimBehavior;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();
    private static final double XYZ_EPSILON = 0.008856d;
    private static final double XYZ_KAPPA = 903.3d;
    private static final double XYZ_WHITE_REFERENCE_X = 95.047d;
    private static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

    private ColorUtils() {
    }

    @ColorInt
    public static int HSLToColor(@NonNull float[] fArr) {
        int iRound;
        int iRound2;
        int iRound3;
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        float fAbs = (1.0f - Math.abs((f4 * 2.0f) - 1.0f)) * f3;
        float f5 = f4 - (0.5f * fAbs);
        float fAbs2 = (1.0f - Math.abs(((f2 / 60.0f) % 2.0f) - 1.0f)) * fAbs;
        switch (((int) f2) / 60) {
            case 0:
                iRound = Math.round((fAbs + f5) * 255.0f);
                iRound2 = Math.round((fAbs2 + f5) * 255.0f);
                iRound3 = Math.round(f5 * 255.0f);
                break;
            case 1:
                iRound = Math.round((fAbs2 + f5) * 255.0f);
                iRound2 = Math.round((fAbs + f5) * 255.0f);
                iRound3 = Math.round(f5 * 255.0f);
                break;
            case 2:
                iRound = Math.round(f5 * 255.0f);
                iRound2 = Math.round((fAbs + f5) * 255.0f);
                iRound3 = Math.round((fAbs2 + f5) * 255.0f);
                break;
            case 3:
                iRound = Math.round(f5 * 255.0f);
                iRound2 = Math.round((fAbs2 + f5) * 255.0f);
                iRound3 = Math.round((fAbs + f5) * 255.0f);
                break;
            case 4:
                iRound = Math.round((fAbs2 + f5) * 255.0f);
                iRound2 = Math.round(f5 * 255.0f);
                iRound3 = Math.round((fAbs + f5) * 255.0f);
                break;
            case 5:
            case 6:
                iRound = Math.round((fAbs + f5) * 255.0f);
                iRound2 = Math.round(f5 * 255.0f);
                iRound3 = Math.round((fAbs2 + f5) * 255.0f);
                break;
            default:
                iRound3 = 0;
                iRound = 0;
                iRound2 = 0;
                break;
        }
        return Color.rgb(constrain(iRound, 0, 255), constrain(iRound2, 0, 255), constrain(iRound3, 0, 255));
    }

    @ColorInt
    public static int LABToColor(@FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3, @FloatRange(from = -128.0d, to = 127.0d) double d4) {
        double[] tempDouble3Array = getTempDouble3Array();
        LABToXYZ(d2, d3, d4, tempDouble3Array);
        return XYZToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    public static void LABToXYZ(@FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3, @FloatRange(from = -128.0d, to = 127.0d) double d4, @NonNull double[] dArr) {
        double d5 = (d2 + 16.0d) / 116.0d;
        double d6 = (d3 / 500.0d) + d5;
        double d7 = d5 - (d4 / 200.0d);
        double dPow = Math.pow(d6, 3.0d);
        if (dPow <= XYZ_EPSILON) {
            dPow = ((d6 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        double dPow2 = d2 > 7.9996247999999985d ? Math.pow(d5, 3.0d) : d2 / XYZ_KAPPA;
        double dPow3 = Math.pow(d7, 3.0d);
        if (dPow3 <= XYZ_EPSILON) {
            dPow3 = ((d7 * 116.0d) - 16.0d) / XYZ_KAPPA;
        }
        dArr[0] = dPow * XYZ_WHITE_REFERENCE_X;
        dArr[1] = dPow2 * 100.0d;
        dArr[2] = dPow3 * XYZ_WHITE_REFERENCE_Z;
    }

    public static void RGBToHSL(@IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i2, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i3, @NonNull float[] fArr) {
        float f2;
        float fAbs;
        float f3 = i / 255.0f;
        float f4 = i2 / 255.0f;
        float f5 = i3 / 255.0f;
        float fMax = Math.max(f3, Math.max(f4, f5));
        float fMin = Math.min(f3, Math.min(f4, f5));
        float f6 = fMax - fMin;
        float f7 = (fMax + fMin) / 2.0f;
        if (fMax == fMin) {
            f2 = 0.0f;
            fAbs = 0.0f;
        } else {
            f2 = fMax == f3 ? ((f4 - f5) / f6) % 6.0f : fMax == f4 ? ((f5 - f3) / f6) + 2.0f : 4.0f + ((f3 - f4) / f6);
            fAbs = f6 / (1.0f - Math.abs((2.0f * f7) - 1.0f));
        }
        float f8 = (f2 * 60.0f) % 360.0f;
        if (f8 < 0.0f) {
            f8 += 360.0f;
        }
        fArr[0] = constrain(f8, 0.0f, 360.0f);
        fArr[1] = constrain(fAbs, 0.0f, 1.0f);
        fArr[2] = constrain(f7, 0.0f, 1.0f);
    }

    public static void RGBToLAB(@IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i2, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i3, @NonNull double[] dArr) {
        RGBToXYZ(i, i2, i3, dArr);
        XYZToLAB(dArr[0], dArr[1], dArr[2], dArr);
    }

    public static void RGBToXYZ(@IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i2, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i3, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = ((double) i) / 255.0d;
        double dPow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = ((double) i2) / 255.0d;
        double dPow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = ((double) i3) / 255.0d;
        double dPow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.4124d * dPow) + (0.3576d * dPow2) + (0.1805d * dPow3)) * 100.0d;
        dArr[1] = ((0.2126d * dPow) + (0.7152d * dPow2) + (0.0722d * dPow3)) * 100.0d;
        dArr[2] = ((dPow * 0.0193d) + (dPow2 * 0.1192d) + (dPow3 * 0.9505d)) * 100.0d;
    }

    @ColorInt
    public static int XYZToColor(@FloatRange(from = 0.0d, to = XYZ_WHITE_REFERENCE_X) double d2, @FloatRange(from = 0.0d, to = 100.0d) double d3, @FloatRange(from = 0.0d, to = XYZ_WHITE_REFERENCE_Z) double d4) {
        double d5 = (((3.2406d * d2) + ((-1.5372d) * d3)) + ((-0.4986d) * d4)) / 100.0d;
        double d6 = ((((-0.9689d) * d2) + (1.8758d * d3)) + (0.0415d * d4)) / 100.0d;
        double d7 = (((0.0557d * d2) + ((-0.204d) * d3)) + (1.057d * d4)) / 100.0d;
        return Color.rgb(constrain((int) Math.round((d5 > 0.0031308d ? (Math.pow(d5, 0.4166666666666667d) * 1.055d) - 0.055d : d5 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d6 > 0.0031308d ? (Math.pow(d6, 0.4166666666666667d) * 1.055d) - 0.055d : d6 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d7 > 0.0031308d ? (Math.pow(d7, 0.4166666666666667d) * 1.055d) - 0.055d : d7 * 12.92d) * 255.0d), 0, 255));
    }

    public static void XYZToLAB(@FloatRange(from = 0.0d, to = XYZ_WHITE_REFERENCE_X) double d2, @FloatRange(from = 0.0d, to = 100.0d) double d3, @FloatRange(from = 0.0d, to = XYZ_WHITE_REFERENCE_Z) double d4, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        double dPivotXyzComponent = pivotXyzComponent(d2 / XYZ_WHITE_REFERENCE_X);
        double dPivotXyzComponent2 = pivotXyzComponent(d3 / 100.0d);
        double dPivotXyzComponent3 = pivotXyzComponent(d4 / XYZ_WHITE_REFERENCE_Z);
        dArr[0] = Math.max(0.0d, (116.0d * dPivotXyzComponent2) - 16.0d);
        dArr[1] = (dPivotXyzComponent - dPivotXyzComponent2) * 500.0d;
        dArr[2] = (dPivotXyzComponent2 - dPivotXyzComponent3) * 200.0d;
    }

    @ColorInt
    public static int blendARGB(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((Color.alpha(i) * f3) + (Color.alpha(i2) * f2)), (int) ((Color.red(i) * f3) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f3) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f3) + (Color.blue(i2) * f2)));
    }

    public static void blendHSL(@NonNull float[] fArr, @NonNull float[] fArr2, @FloatRange(from = 0.0d, to = 1.0d) float f2, @NonNull float[] fArr3) {
        if (fArr3.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }
        float f3 = 1.0f - f2;
        fArr3[0] = circularInterpolate(fArr[0], fArr2[0], f2);
        fArr3[1] = (fArr[1] * f3) + (fArr2[1] * f2);
        fArr3[2] = (fArr[2] * f3) + (fArr2[2] * f2);
    }

    public static void blendLAB(@NonNull double[] dArr, @NonNull double[] dArr2, @FloatRange(from = 0.0d, to = 1.0d) double d2, @NonNull double[] dArr3) {
        if (dArr3.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }
        double d3 = 1.0d - d2;
        dArr3[0] = (dArr[0] * d3) + (dArr2[0] * d2);
        dArr3[1] = (dArr[1] * d3) + (dArr2[1] * d2);
        dArr3[2] = (dArr[2] * d3) + (dArr2[2] * d2);
    }

    public static double calculateContrast(@ColorInt int i, @ColorInt int i2) {
        if (Color.alpha(i2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i2));
        }
        if (Color.alpha(i) < 255) {
            i = compositeColors(i, i2);
        }
        double dCalculateLuminance = calculateLuminance(i) + 0.05d;
        double dCalculateLuminance2 = calculateLuminance(i2) + 0.05d;
        return Math.max(dCalculateLuminance, dCalculateLuminance2) / Math.min(dCalculateLuminance, dCalculateLuminance2);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public static double calculateLuminance(@ColorInt int i) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(i, tempDouble3Array);
        return tempDouble3Array[1] / 100.0d;
    }

    public static int calculateMinimumAlpha(@ColorInt int i, @ColorInt int i2, float f2) {
        int i3 = 255;
        if (Color.alpha(i2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i2));
        }
        double d2 = f2;
        if (calculateContrast(setAlphaComponent(i, 255), i2) < d2) {
            return -1;
        }
        int i4 = 0;
        for (int i5 = 0; i5 <= 10 && i3 - i4 > 1; i5++) {
            int i6 = (i4 + i3) / 2;
            if (calculateContrast(setAlphaComponent(i, i6), i2) < d2) {
                i4 = i6;
            } else {
                i3 = i6;
            }
        }
        return i3;
    }

    @VisibleForTesting
    public static float circularInterpolate(float f2, float f3, float f4) {
        if (Math.abs(f3 - f2) > 180.0f) {
            if (f3 > f2) {
                f2 += 360.0f;
            } else {
                f3 += 360.0f;
            }
        }
        return (f2 + ((f3 - f2) * f4)) % 360.0f;
    }

    public static void colorToHSL(@ColorInt int i, @NonNull float[] fArr) {
        RGBToHSL(Color.red(i), Color.green(i), Color.blue(i), fArr);
    }

    public static void colorToLAB(@ColorInt int i, @NonNull double[] dArr) {
        RGBToLAB(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    public static void colorToXYZ(@ColorInt int i, @NonNull double[] dArr) {
        RGBToXYZ(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    private static int compositeAlpha(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }

    public static int compositeColors(@ColorInt int i, @ColorInt int i2) {
        int iAlpha = Color.alpha(i2);
        int iAlpha2 = Color.alpha(i);
        int iCompositeAlpha = compositeAlpha(iAlpha2, iAlpha);
        return Color.argb(iCompositeAlpha, compositeComponent(Color.red(i), iAlpha2, Color.red(i2), iAlpha, iCompositeAlpha), compositeComponent(Color.green(i), iAlpha2, Color.green(i2), iAlpha, iCompositeAlpha), compositeComponent(Color.blue(i), iAlpha2, Color.blue(i2), iAlpha, iCompositeAlpha));
    }

    private static int compositeComponent(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }

    private static float constrain(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    private static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    private static double[] getTempDouble3Array() {
        ThreadLocal<double[]> threadLocal = TEMP_ARRAY;
        double[] dArr = threadLocal.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        threadLocal.set(dArr2);
        return dArr2;
    }

    private static double pivotXyzComponent(double d2) {
        return d2 > XYZ_EPSILON ? Math.pow(d2, 0.3333333333333333d) : ((d2 * XYZ_KAPPA) + 16.0d) / 116.0d;
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int i, @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY, to = 255) int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    @NonNull
    @RequiresApi(26)
    public static Color compositeColors(@NonNull Color color, @NonNull Color color2) {
        if (Objects.equals(color.getModel(), color2.getModel())) {
            if (!Objects.equals(color2.getColorSpace(), color.getColorSpace())) {
                color = color.convert(color2.getColorSpace());
            }
            float[] components = color.getComponents();
            float[] components2 = color2.getComponents();
            float fAlpha = color.alpha();
            float fAlpha2 = color2.alpha() * (1.0f - fAlpha);
            int componentCount = color2.getComponentCount() - 1;
            components2[componentCount] = fAlpha + fAlpha2;
            if (components2[componentCount] > 0.0f) {
                fAlpha /= components2[componentCount];
                fAlpha2 /= components2[componentCount];
            }
            for (int i = 0; i < componentCount; i++) {
                components2[i] = (components[i] * fAlpha) + (components2[i] * fAlpha2);
            }
            return Color.valueOf(components2, color2.getColorSpace());
        }
        throw new IllegalArgumentException("Color models must match (" + color.getModel() + " vs. " + color2.getModel() + ")");
    }
}
