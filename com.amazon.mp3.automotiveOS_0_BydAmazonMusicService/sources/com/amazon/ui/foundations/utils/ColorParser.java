package com.amazon.ui.foundations.utils;

import android.graphics.Color;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: ColorParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002J&\u0010\r\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0002J\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0017\u001a\n \u0016*\u0004\u0018\u00010\u00150\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\n \u0016*\u0004\u0018\u00010\u00150\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u001c\u0010\u001a\u001a\n \u0016*\u0004\u0018\u00010\u00150\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/amazon/ui/foundations/utils/ColorParser;", "", "", "alphaPercent", "", "isValidAlphaPercent", "", "colorInt", "parseColor", "baseColorInt", "baseAlphaPercent", "overlayColorInt", "overlayAlphaPercent", "parseCombinationColor", "getAlphaValue$DMMUIFoundations_release", "(F)I", "getAlphaValue", "alpha", "applyAlpha$DMMUIFoundations_release", "(II)I", "applyAlpha", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "COLOR_HEX_REGEX_PATTERN", "Ljava/util/regex/Pattern;", "COLOR_HEX_REGEX_WITHOUT_HASH_PATTERN", "ALPHA_HEX_REGEX_PATTERN", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class ColorParser {
    public static final ColorParser INSTANCE = new ColorParser();
    private static final Pattern COLOR_HEX_REGEX_PATTERN = Pattern.compile("^#?([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})");
    private static final Pattern COLOR_HEX_REGEX_WITHOUT_HASH_PATTERN = Pattern.compile("([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})");
    private static final Pattern ALPHA_HEX_REGEX_PATTERN = Pattern.compile("([A-Fa-f0-9]{2})");

    public final int getAlphaValue$DMMUIFoundations_release(float alphaPercent) {
        return (int) (255 * alphaPercent);
    }

    public final boolean isValidAlphaPercent(float alphaPercent) {
        double d = alphaPercent;
        return 0.0d <= d && d <= 1.0d;
    }

    private ColorParser() {
    }

    public final int parseColor(int colorInt, float alphaPercent) {
        if (!isValidAlphaPercent(alphaPercent)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return applyAlpha$DMMUIFoundations_release(colorInt, getAlphaValue$DMMUIFoundations_release(alphaPercent));
    }

    public final int parseCombinationColor(int baseColorInt, float baseAlphaPercent, int overlayColorInt, float overlayAlphaPercent) {
        if (!isValidAlphaPercent(baseAlphaPercent)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!isValidAlphaPercent(overlayAlphaPercent)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (baseAlphaPercent == 0.0f) {
            if (overlayAlphaPercent == 0.0f) {
                return 0;
            }
        }
        float f = 1;
        float f2 = f - ((f - overlayAlphaPercent) * (f - baseAlphaPercent));
        float f3 = f - (overlayAlphaPercent / f2);
        return Color.rgb(MathKt__MathJVMKt.roundToInt(((Color.red(overlayColorInt) * overlayAlphaPercent) / f2) + (Color.red(baseColorInt) * f3)), MathKt__MathJVMKt.roundToInt(((Color.green(overlayColorInt) * overlayAlphaPercent) / f2) + (Color.green(baseColorInt) * f3)), MathKt__MathJVMKt.roundToInt(((Color.blue(overlayColorInt) * overlayAlphaPercent) / f2) + (Color.blue(baseColorInt) * f3)));
    }

    public final int applyAlpha$DMMUIFoundations_release(int colorInt, int alpha) {
        return Color.argb(alpha, Color.red(colorInt), Color.green(colorInt), Color.blue(colorInt));
    }
}
