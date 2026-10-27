package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes6.dex */
public class TextLayoutManagerMapBuffer {
    private static final String TAG = "TextLayoutManagerMapBuffer";
    private static final TextPaint sTextPaintInstance = new TextPaint(1);
    private static final Object sSpannableCacheLock = new Object();
    private static final LruCache<MapBuffer, Spannable> sSpannableCache = new LruCache<>(100);
    private static final ConcurrentHashMap<Integer, Spannable> sTagToSpannableCache = new ConcurrentHashMap<>();

    public static boolean isRTL(MapBuffer mapBuffer) {
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() == 0) {
            return false;
        }
        MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
        return mapBuffer3.contains(21) && TextAttributeProps.getLayoutDirection(mapBuffer3.getString(21)) == 1;
    }

    private static void buildSpannableFromFragment(Context context, MapBuffer mapBuffer, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list) {
        int count = mapBuffer.getCount();
        for (int i = 0; i < count; i++) {
            MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(i);
            int length = spannableStringBuilder.length();
            TextAttributeProps textAttributePropsFromMapBuffer = TextAttributeProps.fromMapBuffer(mapBuffer2.getMapBuffer(5));
            spannableStringBuilder.append((CharSequence) TextTransform.apply(mapBuffer2.getString(0), textAttributePropsFromMapBuffer.mTextTransform));
            int length2 = spannableStringBuilder.length();
            int i2 = mapBuffer2.contains(1) ? mapBuffer2.getInt(1) : -1;
            if (mapBuffer2.contains(2) && mapBuffer2.getBoolean(2)) {
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(i2, (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(3)), (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(4)))));
            } else if (length2 >= length) {
                if (textAttributePropsFromMapBuffer.mIsAccessibilityLink) {
                    list.add(new SetSpanOperation(length, length2, new ReactClickableSpan(i2)));
                }
                if (textAttributePropsFromMapBuffer.mIsColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(textAttributePropsFromMapBuffer.mColor)));
                }
                if (textAttributePropsFromMapBuffer.mIsBackgroundColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(textAttributePropsFromMapBuffer.mBackgroundColor)));
                }
                if (!Float.isNaN(textAttributePropsFromMapBuffer.getLetterSpacing())) {
                    list.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(textAttributePropsFromMapBuffer.getLetterSpacing())));
                }
                list.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(textAttributePropsFromMapBuffer.mFontSize)));
                if (textAttributePropsFromMapBuffer.mFontStyle != -1 || textAttributePropsFromMapBuffer.mFontWeight != -1 || textAttributePropsFromMapBuffer.mFontFamily != null) {
                    list.add(new SetSpanOperation(length, length2, new CustomStyleSpan(textAttributePropsFromMapBuffer.mFontStyle, textAttributePropsFromMapBuffer.mFontWeight, textAttributePropsFromMapBuffer.mFontFeatureSettings, textAttributePropsFromMapBuffer.mFontFamily, context.getAssets())));
                }
                if (textAttributePropsFromMapBuffer.mIsUnderlineTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                }
                if (textAttributePropsFromMapBuffer.mIsLineThroughTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                }
                if (textAttributePropsFromMapBuffer.mTextShadowOffsetDx != 0.0f || textAttributePropsFromMapBuffer.mTextShadowOffsetDy != 0.0f) {
                    list.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(textAttributePropsFromMapBuffer.mTextShadowOffsetDx, textAttributePropsFromMapBuffer.mTextShadowOffsetDy, textAttributePropsFromMapBuffer.mTextShadowRadius, textAttributePropsFromMapBuffer.mTextShadowColor)));
                }
                if (!Float.isNaN(textAttributePropsFromMapBuffer.getEffectiveLineHeight())) {
                    list.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(textAttributePropsFromMapBuffer.getEffectiveLineHeight())));
                }
                list.add(new SetSpanOperation(length, length2, new ReactTagSpan(i2)));
            }
        }
    }

    public static Spannable getOrCreateSpannableForText(Context context, MapBuffer mapBuffer, @Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback);
    }

    private static Spannable createSpannableFromAttributedString(Context context, MapBuffer mapBuffer, @Nullable ReactTextViewManagerCallback reactTextViewManagerCallback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragment(context, mapBuffer.getMapBuffer(2), spannableStringBuilder, arrayList);
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            ((SetSpanOperation) it.next()).execute(spannableStringBuilder, i);
            i++;
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private static Layout createLayout(Spannable spannable, BoringLayout.Metrics metrics, float f, YogaMeasureMode yogaMeasureMode, boolean z, int i, int i2) {
        int i3;
        int length = spannable.length();
        boolean z2 = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        TextPaint textPaint = sTextPaintInstance;
        float desiredWidth = metrics == null ? Layout.getDesiredWidth(spannable, textPaint) : Float.NaN;
        if (metrics == null && (z2 || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f))) {
            return StaticLayout.Builder.obtain(spannable, 0, length, textPaint, (int) Math.ceil(desiredWidth)).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(i2).build();
        }
        if (metrics != null && (z2 || metrics.width <= f)) {
            int i4 = metrics.width;
            if (i4 < 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Text width is invalid: " + metrics.width));
                i3 = 0;
            } else {
                i3 = i4;
            }
            return BoringLayout.make(spannable, textPaint, i3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, metrics, z);
        }
        int i5 = Build.VERSION.SDK_INT;
        StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spannable, 0, length, textPaint, (int) f).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(i2);
        if (i5 >= 28) {
            hyphenationFrequency.setUseLineSpacingFromFallbacks(true);
        }
        return hyphenationFrequency.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long measureText(android.content.Context r18, com.facebook.react.common.mapbuffer.MapBuffer r19, com.facebook.react.common.mapbuffer.MapBuffer r20, float r21, com.facebook.yoga.YogaMeasureMode r22, float r23, com.facebook.yoga.YogaMeasureMode r24, com.facebook.react.views.text.ReactTextViewManagerCallback r25, @androidx.annotation.Nullable float[] r26) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManagerMapBuffer.measureText(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, com.facebook.react.common.mapbuffer.MapBuffer, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode, com.facebook.react.views.text.ReactTextViewManagerCallback, float[]):long");
    }

    public static WritableArray measureLines(@NonNull Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f) {
        TextPaint textPaint = sTextPaintInstance;
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context, mapBuffer, null);
        BoringLayout.Metrics metricsIsBoring = BoringLayout.isBoring(orCreateSpannableForText, textPaint);
        int textBreakStrategy = TextAttributeProps.getTextBreakStrategy(mapBuffer2.getString(2));
        return FontMetricsUtil.getFontMetrics(orCreateSpannableForText, createLayout(orCreateSpannableForText, metricsIsBoring, f, YogaMeasureMode.EXACTLY, mapBuffer2.contains(4) ? mapBuffer2.getBoolean(4) : true, textBreakStrategy, TextAttributeProps.getTextBreakStrategy(mapBuffer2.getString(5))), textPaint, context);
    }

    public static class SetSpanOperation {
        protected int end;
        protected int start;
        protected ReactSpan what;

        public SetSpanOperation(int i, int i2, ReactSpan reactSpan) {
            this.start = i;
            this.end = i2;
            this.what = reactSpan;
        }

        public void execute(Spannable spannable, int i) {
            int i2 = this.start;
            spannable.setSpan(this.what, i2, this.end, ((i << 16) & 16711680) | ((i2 == 0 ? 18 : 34) & (-16711681)));
        }
    }
}
