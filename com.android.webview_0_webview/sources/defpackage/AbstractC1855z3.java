package defpackage;

import android.graphics.Paint;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.widget.TextView;

/* JADX INFO: renamed from: z3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1855z3 {
    public static void a(TextView textView, int i) {
        AbstractC0639d3.a(i);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i - (-i2), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void b(TextView textView, int i) {
        AbstractC0639d3.a(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void c(TextView textView, int i) {
        AbstractC0639d3.a(i);
        if (i != textView.getPaint().getFontMetricsInt(null)) {
            textView.setLineSpacing(i - r0, 1.0f);
        }
    }

    public static void d(TextView textView) {
        int breakStrategy;
        int hyphenationFrequency;
        TextDirectionHeuristic textDirectionHeuristic;
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            throw null;
        }
        if (i >= 28) {
            PrecomputedText.Params textMetricsParams = textView.getTextMetricsParams();
            textMetricsParams.getTextPaint();
            textMetricsParams.getTextDirection();
            textMetricsParams.getBreakStrategy();
            textMetricsParams.getHyphenationFrequency();
            throw null;
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        if (i >= 23) {
            breakStrategy = 1;
            hyphenationFrequency = 1;
        } else {
            breakStrategy = 0;
            hyphenationFrequency = 0;
        }
        TextDirectionHeuristic textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        if (i >= 23) {
            breakStrategy = textView.getBreakStrategy();
            hyphenationFrequency = textView.getHyphenationFrequency();
        }
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i < 28 || (textView.getInputType() & 15) != 3) {
            boolean z = textView.getLayoutDirection() == 1;
            switch (textView.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    textDirectionHeuristic = !z ? TextDirectionHeuristics.FIRSTSTRONG_LTR : TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        if (i < 28) {
            throw null;
        }
        new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(breakStrategy).setHyphenationFrequency(hyphenationFrequency).setTextDirection(textDirectionHeuristic).build();
        throw null;
    }

    public static ActionMode.Callback e(TextView textView, ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return (i < 26 || i > 27 || (callback instanceof ActionModeCallbackC1800y3)) ? callback : new ActionModeCallbackC1800y3(callback, textView);
    }
}
