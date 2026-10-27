package com.android.systemui.util;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.VectorDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.Pair;
import androidx.core.view.ViewCompat;
import com.android.internal.util.ImageUtils;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NotificationColorUtil {
    private static final boolean DEBUG = false;
    private static final String TAG = "NotificationColorUtil";
    private static NotificationColorUtil sInstance;
    private static final Object sLock = new Object();
    private final int mGrayscaleIconMaxSize;
    private final ImageUtils mImageUtils = new ImageUtils();
    private final WeakHashMap<Bitmap, Pair<Boolean, Integer>> mGrayscaleBitmapCache = new WeakHashMap<>();

    public static NotificationColorUtil getInstance(Context context) {
        NotificationColorUtil notificationColorUtil;
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new NotificationColorUtil(context);
            }
            notificationColorUtil = sInstance;
        }
        return notificationColorUtil;
    }

    private NotificationColorUtil(Context context) {
        this.mGrayscaleIconMaxSize = context.getResources().getDimensionPixelSize(R.dimen.notification_large_icon_width);
    }

    public boolean isGrayscaleIcon(Bitmap bitmap) {
        boolean zIsGrayscale;
        int generationId;
        if (bitmap.getWidth() > this.mGrayscaleIconMaxSize || bitmap.getHeight() > this.mGrayscaleIconMaxSize) {
            return false;
        }
        Object obj = sLock;
        synchronized (obj) {
            Pair<Boolean, Integer> pair = this.mGrayscaleBitmapCache.get(bitmap);
            if (pair != null && ((Integer) pair.second).intValue() == bitmap.getGenerationId()) {
                return ((Boolean) pair.first).booleanValue();
            }
            synchronized (this.mImageUtils) {
                zIsGrayscale = this.mImageUtils.isGrayscale(bitmap);
                generationId = bitmap.getGenerationId();
            }
            synchronized (obj) {
                this.mGrayscaleBitmapCache.put(bitmap, Pair.create(Boolean.valueOf(zIsGrayscale), Integer.valueOf(generationId)));
            }
            return zIsGrayscale;
        }
    }

    public boolean isGrayscaleIcon(Drawable d) {
        if (d == null) {
            return false;
        }
        if (d instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) d;
            return bitmapDrawable.getBitmap() != null && isGrayscaleIcon(bitmapDrawable.getBitmap());
        }
        if (!(d instanceof AnimationDrawable)) {
            return d instanceof VectorDrawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) d;
        return animationDrawable.getNumberOfFrames() > 0 && isGrayscaleIcon(animationDrawable.getFrame(0));
    }

    public boolean isGrayscaleIcon(Context context, Icon icon) {
        if (icon == null) {
            return false;
        }
        int type = icon.getType();
        if (type == 1) {
            return isGrayscaleIcon(icon.getBitmap());
        }
        if (type != 2) {
            return false;
        }
        return isGrayscaleIcon(context, icon.getResId());
    }

    public boolean isGrayscaleIcon(Context context, int drawableResId) {
        if (drawableResId != 0) {
            try {
                return isGrayscaleIcon(context.getDrawable(drawableResId));
            } catch (Resources.NotFoundException unused) {
                Log.e(TAG, "Drawable not found: " + drawableResId);
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.CharSequence invertCharSequenceColors(java.lang.CharSequence r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof android.text.Spanned
            if (r0 == 0) goto L62
            android.text.Spanned r9 = (android.text.Spanned) r9
            int r0 = r9.length()
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2 = 0
            java.lang.Object[] r0 = r9.getSpans(r2, r0, r1)
            android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
            java.lang.String r3 = r9.toString()
            r1.<init>(r3)
            int r3 = r0.length
        L1b:
            if (r2 >= r3) goto L61
            r4 = r0[r2]
            boolean r5 = r4 instanceof android.text.style.CharacterStyle
            if (r5 == 0) goto L2b
            r5 = r4
            android.text.style.CharacterStyle r5 = (android.text.style.CharacterStyle) r5
            android.text.style.CharacterStyle r5 = r5.getUnderlying()
            goto L2c
        L2b:
            r5 = r4
        L2c:
            boolean r6 = r5 instanceof android.text.style.TextAppearanceSpan
            if (r6 == 0) goto L3a
            r6 = r4
            android.text.style.TextAppearanceSpan r6 = (android.text.style.TextAppearanceSpan) r6
            android.text.style.TextAppearanceSpan r6 = r8.processTextAppearanceSpan(r6)
            if (r6 == r5) goto L4e
            goto L4f
        L3a:
            boolean r6 = r5 instanceof android.text.style.ForegroundColorSpan
            if (r6 == 0) goto L4e
            android.text.style.ForegroundColorSpan r5 = (android.text.style.ForegroundColorSpan) r5
            int r5 = r5.getForegroundColor()
            android.text.style.ForegroundColorSpan r6 = new android.text.style.ForegroundColorSpan
            int r5 = r8.processColor(r5)
            r6.<init>(r5)
            goto L4f
        L4e:
            r6 = r4
        L4f:
            int r5 = r9.getSpanStart(r4)
            int r7 = r9.getSpanEnd(r4)
            int r4 = r9.getSpanFlags(r4)
            r1.setSpan(r6, r5, r7, r4)
            int r2 = r2 + 1
            goto L1b
        L61:
            return r1
        L62:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.NotificationColorUtil.invertCharSequenceColors(java.lang.CharSequence):java.lang.CharSequence");
    }

    private TextAppearanceSpan processTextAppearanceSpan(TextAppearanceSpan span) {
        ColorStateList textColor = span.getTextColor();
        if (textColor != null) {
            int[] colors = textColor.getColors();
            boolean z = false;
            for (int i = 0; i < colors.length; i++) {
                if (ImageUtils.isGrayscale(colors[i])) {
                    if (!z) {
                        colors = Arrays.copyOf(colors, colors.length);
                    }
                    colors[i] = processColor(colors[i]);
                    z = true;
                }
            }
            if (z) {
                return new TextAppearanceSpan(span.getFamily(), span.getTextStyle(), span.getTextSize(), new ColorStateList(textColor.getStates(), colors), span.getLinkTextColor());
            }
        }
        return span;
    }

    public static CharSequence clearColorSpans(CharSequence charSequence) {
        if (!(charSequence instanceof Spanned)) {
            return charSequence;
        }
        Spanned spanned = (Spanned) charSequence;
        Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
        for (Object obj : spans) {
            Object underlying = obj instanceof CharacterStyle ? ((CharacterStyle) obj).getUnderlying() : obj;
            if (underlying instanceof TextAppearanceSpan) {
                TextAppearanceSpan textAppearanceSpan = (TextAppearanceSpan) underlying;
                if (textAppearanceSpan.getTextColor() != null) {
                    underlying = new TextAppearanceSpan(textAppearanceSpan.getFamily(), textAppearanceSpan.getTextStyle(), textAppearanceSpan.getTextSize(), (ColorStateList) null, textAppearanceSpan.getLinkTextColor());
                }
            } else if (!(underlying instanceof ForegroundColorSpan) && !(underlying instanceof BackgroundColorSpan)) {
                underlying = obj;
            }
            spannableStringBuilder.setSpan(underlying, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj));
        }
        return spannableStringBuilder;
    }

    private int processColor(int color) {
        return Color.argb(Color.alpha(color), 255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color));
    }

    public static int findContrastColor(int color, int other, boolean findFg, double minRatio) {
        int iLABToColor = findFg ? color : other;
        int iLABToColor2 = findFg ? other : color;
        if (ColorUtilsFromCompat.calculateContrast(iLABToColor, iLABToColor2) >= minRatio) {
            return color;
        }
        double[] dArr = new double[3];
        ColorUtilsFromCompat.colorToLAB(findFg ? iLABToColor : iLABToColor2, dArr);
        double d = 0.0d;
        double d2 = dArr[0];
        double d3 = dArr[1];
        double d4 = dArr[2];
        for (int i = 0; i < 15 && d2 - d > 1.0E-5d; i++) {
            double d5 = (d + d2) / 2.0d;
            if (findFg) {
                iLABToColor = ColorUtilsFromCompat.LABToColor(d5, d3, d4);
            } else {
                iLABToColor2 = ColorUtilsFromCompat.LABToColor(d5, d3, d4);
            }
            if (ColorUtilsFromCompat.calculateContrast(iLABToColor, iLABToColor2) > minRatio) {
                d = d5;
            } else {
                d2 = d5;
            }
        }
        return ColorUtilsFromCompat.LABToColor(d, d3, d4);
    }

    public static int findAlphaToMeetContrast(int color, int backgroundColor, double minRatio) {
        if (ColorUtilsFromCompat.calculateContrast(color, backgroundColor) >= minRatio) {
            return color;
        }
        int iAlpha = Color.alpha(color);
        int iRed = Color.red(color);
        int iGreen = Color.green(color);
        int iBlue = Color.blue(color);
        int i = 255;
        for (int i2 = 0; i2 < 15 && i - iAlpha > 0; i2++) {
            int i3 = (iAlpha + i) / 2;
            if (ColorUtilsFromCompat.calculateContrast(Color.argb(i3, iRed, iGreen, iBlue), backgroundColor) > minRatio) {
                i = i3;
            } else {
                iAlpha = i3;
            }
        }
        return Color.argb(i, iRed, iGreen, iBlue);
    }

    public static int findContrastColorAgainstDark(int color, int other, boolean findFg, double minRatio) {
        int iHSLToColor = findFg ? color : other;
        if (!findFg) {
            other = color;
        }
        if (ColorUtilsFromCompat.calculateContrast(iHSLToColor, other) >= minRatio) {
            return color;
        }
        float[] fArr = new float[3];
        ColorUtilsFromCompat.colorToHSL(findFg ? iHSLToColor : other, fArr);
        float f = fArr[2];
        float f2 = 1.0f;
        for (int i = 0; i < 15 && f2 - f > 1.0E-5d; i++) {
            float f3 = (f + f2) / 2.0f;
            fArr[2] = f3;
            if (findFg) {
                iHSLToColor = ColorUtilsFromCompat.HSLToColor(fArr);
            } else {
                other = ColorUtilsFromCompat.HSLToColor(fArr);
            }
            if (ColorUtilsFromCompat.calculateContrast(iHSLToColor, other) > minRatio) {
                f2 = f3;
            } else {
                f = f3;
            }
        }
        return findFg ? iHSLToColor : other;
    }

    public static int ensureTextContrastOnBlack(int color) {
        return findContrastColorAgainstDark(color, ViewCompat.MEASURED_STATE_MASK, true, 12.0d);
    }

    public static int ensureLargeTextContrast(int color, int bg, boolean isBgDarker) {
        return isBgDarker ? findContrastColorAgainstDark(color, bg, true, 3.0d) : findContrastColor(color, bg, true, 3.0d);
    }

    public static int ensureTextContrast(int color, int bg, boolean isBgDarker) {
        return ensureContrast(color, bg, isBgDarker, 4.5d);
    }

    public static int ensureContrast(int color, int bg, boolean isBgDarker, double minRatio) {
        return isBgDarker ? findContrastColorAgainstDark(color, bg, true, minRatio) : findContrastColor(color, bg, true, minRatio);
    }

    public static int ensureTextBackgroundColor(int color, int textColor, int hintColor) {
        return findContrastColor(findContrastColor(color, hintColor, false, 3.0d), textColor, false, 4.5d);
    }

    private static String contrastChange(int colorOld, int colorNew, int bg) {
        return String.format("from %.2f:1 to %.2f:1", Double.valueOf(ColorUtilsFromCompat.calculateContrast(colorOld, bg)), Double.valueOf(ColorUtilsFromCompat.calculateContrast(colorNew, bg)));
    }

    public static int resolveColor(Context context, int color) {
        return color == 0 ? context.getColor(R.color.car_body3_light) : color;
    }

    public static int resolveContrastColor(Context context, int notificationColor, int backgroundColor) {
        return resolveContrastColor(context, notificationColor, backgroundColor, false);
    }

    public static int resolveContrastColor(Context context, int notificationColor, int backgroundColor, boolean isDark) {
        return ensureTextContrast(resolveColor(context, notificationColor), backgroundColor, isDark);
    }

    public static int changeColorLightness(int baseColor, int amount) {
        double[] tempDouble3Array = ColorUtilsFromCompat.getTempDouble3Array();
        ColorUtilsFromCompat.colorToLAB(baseColor, tempDouble3Array);
        tempDouble3Array[0] = Math.max(Math.min(100.0d, tempDouble3Array[0] + ((double) amount)), 0.0d);
        return ColorUtilsFromCompat.LABToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    public static int resolveAmbientColor(Context context, int notificationColor) {
        return ensureTextContrastOnBlack(resolveColor(context, notificationColor));
    }

    public static int resolvePrimaryColor(Context context, int backgroundColor) {
        return context.getColor(shouldUseDark(backgroundColor) ? R.color.car_body4_light : R.color.car_body4_dark);
    }

    public static int resolveSecondaryColor(Context context, int backgroundColor) {
        return context.getColor(shouldUseDark(backgroundColor) ? R.color.car_card : R.color.car_button_text_color);
    }

    public static int resolveDefaultColor(Context context, int backgroundColor) {
        return context.getColor(shouldUseDark(backgroundColor) ? R.color.car_body3_light : R.color.car_body3_dark);
    }

    public static int getShiftedColor(int color, int amount) {
        double[] tempDouble3Array = ColorUtilsFromCompat.getTempDouble3Array();
        ColorUtilsFromCompat.colorToLAB(color, tempDouble3Array);
        if (tempDouble3Array[0] >= 4.0d) {
            tempDouble3Array[0] = Math.max(0.0d, tempDouble3Array[0] - ((double) amount));
        } else {
            tempDouble3Array[0] = Math.min(100.0d, tempDouble3Array[0] + ((double) amount));
        }
        return ColorUtilsFromCompat.LABToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    private static boolean shouldUseDark(int backgroundColor) {
        boolean z = backgroundColor == 0;
        if (z) {
            return z;
        }
        return ColorUtilsFromCompat.calculateLuminance(backgroundColor) > 0.5d;
    }

    public static double calculateLuminance(int backgroundColor) {
        return ColorUtilsFromCompat.calculateLuminance(backgroundColor);
    }

    public static double calculateContrast(int foregroundColor, int backgroundColor) {
        return ColorUtilsFromCompat.calculateContrast(foregroundColor, backgroundColor);
    }

    public static boolean satisfiesTextContrast(int backgroundColor, int foregroundColor) {
        return calculateContrast(foregroundColor, backgroundColor) >= 4.5d;
    }

    public static int compositeColors(int foreground, int background) {
        return ColorUtilsFromCompat.compositeColors(foreground, background);
    }

    public static boolean isColorLight(int backgroundColor) {
        return calculateLuminance(backgroundColor) > 0.5d;
    }

    private static class ColorUtilsFromCompat {
        private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
        private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
        private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();
        private static final double XYZ_EPSILON = 0.008856d;
        private static final double XYZ_KAPPA = 903.3d;
        private static final double XYZ_WHITE_REFERENCE_X = 95.047d;
        private static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
        private static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

        private static float constrain(float amount, float low, float high) {
            return amount < low ? low : amount > high ? high : amount;
        }

        private static int constrain(int amount, int low, int high) {
            return amount < low ? low : amount > high ? high : amount;
        }

        private ColorUtilsFromCompat() {
        }

        public static int compositeColors(int foreground, int background) {
            int iAlpha = Color.alpha(background);
            int iAlpha2 = Color.alpha(foreground);
            int iCompositeAlpha = compositeAlpha(iAlpha2, iAlpha);
            return Color.argb(iCompositeAlpha, compositeComponent(Color.red(foreground), iAlpha2, Color.red(background), iAlpha, iCompositeAlpha), compositeComponent(Color.green(foreground), iAlpha2, Color.green(background), iAlpha, iCompositeAlpha), compositeComponent(Color.blue(foreground), iAlpha2, Color.blue(background), iAlpha, iCompositeAlpha));
        }

        private static int compositeAlpha(int foregroundAlpha, int backgroundAlpha) {
            return 255 - (((255 - backgroundAlpha) * (255 - foregroundAlpha)) / 255);
        }

        private static int compositeComponent(int fgC, int fgA, int bgC, int bgA, int a) {
            if (a == 0) {
                return 0;
            }
            return (((fgC * 255) * fgA) + ((bgC * bgA) * (255 - fgA))) / (a * 255);
        }

        public static double calculateLuminance(int color) {
            double[] tempDouble3Array = getTempDouble3Array();
            colorToXYZ(color, tempDouble3Array);
            return tempDouble3Array[1] / 100.0d;
        }

        public static double calculateContrast(int foreground, int background) {
            if (Color.alpha(background) != 255) {
                Log.wtf(NotificationColorUtil.TAG, "background can not be translucent: #" + Integer.toHexString(background));
            }
            if (Color.alpha(foreground) < 255) {
                foreground = compositeColors(foreground, background);
            }
            double dCalculateLuminance = calculateLuminance(foreground) + 0.05d;
            double dCalculateLuminance2 = calculateLuminance(background) + 0.05d;
            return Math.max(dCalculateLuminance, dCalculateLuminance2) / Math.min(dCalculateLuminance, dCalculateLuminance2);
        }

        public static void colorToLAB(int color, double[] outLab) {
            RGBToLAB(Color.red(color), Color.green(color), Color.blue(color), outLab);
        }

        public static void RGBToLAB(int r, int g, int b, double[] outLab) {
            RGBToXYZ(r, g, b, outLab);
            XYZToLAB(outLab[0], outLab[1], outLab[2], outLab);
        }

        public static void colorToXYZ(int color, double[] outXyz) {
            RGBToXYZ(Color.red(color), Color.green(color), Color.blue(color), outXyz);
        }

        public static void RGBToXYZ(int r, int g, int b, double[] outXyz) {
            if (outXyz.length != 3) {
                throw new IllegalArgumentException("outXyz must have a length of 3.");
            }
            double d = ((double) r) / 255.0d;
            double dPow = d < 0.04045d ? d / 12.92d : Math.pow((d + 0.055d) / 1.055d, 2.4d);
            double d2 = ((double) g) / 255.0d;
            double dPow2 = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
            double d3 = ((double) b) / 255.0d;
            double dPow3 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
            outXyz[0] = ((0.4124d * dPow) + (0.3576d * dPow2) + (0.1805d * dPow3)) * 100.0d;
            outXyz[1] = ((0.2126d * dPow) + (0.7152d * dPow2) + (0.0722d * dPow3)) * 100.0d;
            outXyz[2] = ((dPow * 0.0193d) + (dPow2 * 0.1192d) + (dPow3 * 0.9505d)) * 100.0d;
        }

        public static void XYZToLAB(double x, double y, double z, double[] outLab) {
            if (outLab.length != 3) {
                throw new IllegalArgumentException("outLab must have a length of 3.");
            }
            double dPivotXyzComponent = pivotXyzComponent(x / XYZ_WHITE_REFERENCE_X);
            double dPivotXyzComponent2 = pivotXyzComponent(y / 100.0d);
            double dPivotXyzComponent3 = pivotXyzComponent(z / XYZ_WHITE_REFERENCE_Z);
            outLab[0] = Math.max(0.0d, (116.0d * dPivotXyzComponent2) - 16.0d);
            outLab[1] = (dPivotXyzComponent - dPivotXyzComponent2) * 500.0d;
            outLab[2] = (dPivotXyzComponent2 - dPivotXyzComponent3) * 200.0d;
        }

        public static void LABToXYZ(double l, double a, double b, double[] outXyz) {
            double d = (l + 16.0d) / 116.0d;
            double d2 = (a / 500.0d) + d;
            double d3 = d - (b / 200.0d);
            double dPow = Math.pow(d2, 3.0d);
            if (dPow <= XYZ_EPSILON) {
                dPow = ((d2 * 116.0d) - 16.0d) / XYZ_KAPPA;
            }
            double dPow2 = l > 7.9996247999999985d ? Math.pow(d, 3.0d) : l / XYZ_KAPPA;
            double dPow3 = Math.pow(d3, 3.0d);
            if (dPow3 <= XYZ_EPSILON) {
                dPow3 = ((d3 * 116.0d) - 16.0d) / XYZ_KAPPA;
            }
            outXyz[0] = dPow * XYZ_WHITE_REFERENCE_X;
            outXyz[1] = dPow2 * 100.0d;
            outXyz[2] = dPow3 * XYZ_WHITE_REFERENCE_Z;
        }

        public static int XYZToColor(double x, double y, double z) {
            double d = (((3.2406d * x) + ((-1.5372d) * y)) + ((-0.4986d) * z)) / 100.0d;
            double d2 = ((((-0.9689d) * x) + (1.8758d * y)) + (0.0415d * z)) / 100.0d;
            double d3 = (((0.0557d * x) + ((-0.204d) * y)) + (1.057d * z)) / 100.0d;
            return Color.rgb(constrain((int) Math.round((d > 0.0031308d ? (Math.pow(d, 0.4166666666666667d) * 1.055d) - 0.055d : d * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d2 > 0.0031308d ? (Math.pow(d2, 0.4166666666666667d) * 1.055d) - 0.055d : d2 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d3 > 0.0031308d ? (Math.pow(d3, 0.4166666666666667d) * 1.055d) - 0.055d : d3 * 12.92d) * 255.0d), 0, 255));
        }

        public static int LABToColor(double l, double a, double b) {
            double[] tempDouble3Array = getTempDouble3Array();
            LABToXYZ(l, a, b, tempDouble3Array);
            return XYZToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
        }

        private static double pivotXyzComponent(double component) {
            return component > XYZ_EPSILON ? Math.pow(component, 0.3333333333333333d) : ((component * XYZ_KAPPA) + 16.0d) / 116.0d;
        }

        public static double[] getTempDouble3Array() {
            ThreadLocal<double[]> threadLocal = TEMP_ARRAY;
            double[] dArr = threadLocal.get();
            if (dArr != null) {
                return dArr;
            }
            double[] dArr2 = new double[3];
            threadLocal.set(dArr2);
            return dArr2;
        }

        public static int HSLToColor(float[] hsl) {
            int iRound;
            int iRound2;
            int iRound3;
            float f = hsl[0];
            float f2 = hsl[1];
            float f3 = hsl[2];
            float fAbs = (1.0f - Math.abs((f3 * 2.0f) - 1.0f)) * f2;
            float f4 = f3 - (0.5f * fAbs);
            float fAbs2 = (1.0f - Math.abs(((f / 60.0f) % 2.0f) - 1.0f)) * fAbs;
            switch (((int) f) / 60) {
                case 0:
                    iRound = Math.round((fAbs + f4) * 255.0f);
                    iRound2 = Math.round((fAbs2 + f4) * 255.0f);
                    iRound3 = Math.round(f4 * 255.0f);
                    break;
                case 1:
                    iRound = Math.round((fAbs2 + f4) * 255.0f);
                    iRound2 = Math.round((fAbs + f4) * 255.0f);
                    iRound3 = Math.round(f4 * 255.0f);
                    break;
                case 2:
                    iRound = Math.round(f4 * 255.0f);
                    iRound2 = Math.round((fAbs + f4) * 255.0f);
                    iRound3 = Math.round((fAbs2 + f4) * 255.0f);
                    break;
                case 3:
                    iRound = Math.round(f4 * 255.0f);
                    iRound2 = Math.round((fAbs2 + f4) * 255.0f);
                    iRound3 = Math.round((fAbs + f4) * 255.0f);
                    break;
                case 4:
                    iRound = Math.round((fAbs2 + f4) * 255.0f);
                    iRound2 = Math.round(f4 * 255.0f);
                    iRound3 = Math.round((fAbs + f4) * 255.0f);
                    break;
                case 5:
                case 6:
                    iRound = Math.round((fAbs + f4) * 255.0f);
                    iRound2 = Math.round(f4 * 255.0f);
                    iRound3 = Math.round((fAbs2 + f4) * 255.0f);
                    break;
                default:
                    iRound3 = 0;
                    iRound = 0;
                    iRound2 = 0;
                    break;
            }
            return Color.rgb(constrain(iRound, 0, 255), constrain(iRound2, 0, 255), constrain(iRound3, 0, 255));
        }

        public static void colorToHSL(int color, float[] outHsl) {
            RGBToHSL(Color.red(color), Color.green(color), Color.blue(color), outHsl);
        }

        public static void RGBToHSL(int r, int g, int b, float[] outHsl) {
            float f;
            float fAbs;
            float f2 = r / 255.0f;
            float f3 = g / 255.0f;
            float f4 = b / 255.0f;
            float fMax = Math.max(f2, Math.max(f3, f4));
            float fMin = Math.min(f2, Math.min(f3, f4));
            float f5 = fMax - fMin;
            float f6 = (fMax + fMin) / 2.0f;
            if (fMax == fMin) {
                f = 0.0f;
                fAbs = 0.0f;
            } else {
                f = fMax == f2 ? ((f3 - f4) / f5) % 6.0f : fMax == f3 ? ((f4 - f2) / f5) + 2.0f : 4.0f + ((f2 - f3) / f5);
                fAbs = f5 / (1.0f - Math.abs((2.0f * f6) - 1.0f));
            }
            float f7 = (f * 60.0f) % 360.0f;
            if (f7 < 0.0f) {
                f7 += 360.0f;
            }
            outHsl[0] = constrain(f7, 0.0f, 360.0f);
            outHsl[1] = constrain(fAbs, 0.0f, 1.0f);
            outHsl[2] = constrain(f6, 0.0f, 1.0f);
        }
    }
}
