package com.amazon.avod.playbackclient.subtitle.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.core.subtitle.Position;
import com.amazon.avod.core.subtitle.SubtitleRegionWritingMode;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleSpan extends ReplacementSpan {
    private final ImmutableList<SubtitleAnnotationElement> mAnnotationElements;
    private final String mBaseText;
    private final TTMLV2CanvasUtil mCanvasUtil;
    private final Set<String> mCharsToRotate;
    private final String mCurrentLanguageCode;
    private final float mDegreeOfRotation;
    private final boolean mIsHorizontalSubtitleFontShearEnabled;
    private final boolean mIsRotationOfCharactersEnabled;
    private final boolean mIsVertical;
    private final boolean mIsVerticalSubtitleFontShearEnabled;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    private final Set<String> mRotationSupportedLangCodes;
    private final float mRubyScale;
    private final SubtitleConfig mSubtitleConfig;
    private final float mTextSize;

    public SubtitleSpan(@Nonnull String str, @Nonnull ImmutableList<SubtitleAnnotationElement> immutableList, boolean z, @Nonnull TTMLV2CanvasUtil tTMLV2CanvasUtil, @Nullable String str2) {
        this(str, immutableList, z, SubtitleConfig.getInstance(), tTMLV2CanvasUtil, str2, PlaybackPmetMetricReporter.getInstance());
    }

    @VisibleForTesting
    SubtitleSpan(@Nonnull String str, @Nonnull ImmutableList<SubtitleAnnotationElement> immutableList, boolean z, @Nonnull SubtitleConfig subtitleConfig, @Nonnull TTMLV2CanvasUtil tTMLV2CanvasUtil, @Nullable String str2, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mBaseText = (String) Preconditions.checkNotNull(str, "base");
        this.mAnnotationElements = (ImmutableList) Preconditions.checkNotNull(immutableList, "annotations");
        this.mIsVertical = z;
        SubtitleConfig subtitleConfig2 = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        this.mSubtitleConfig = subtitleConfig2;
        this.mRubyScale = subtitleConfig2.getRubyToBaseScalingFactor();
        this.mCanvasUtil = (TTMLV2CanvasUtil) Preconditions.checkNotNull(tTMLV2CanvasUtil, "TTMLV2CanvasHelper");
        this.mTextSize = tTMLV2CanvasUtil.getTextSize();
        this.mCurrentLanguageCode = str2;
        this.mIsRotationOfCharactersEnabled = subtitleConfig2.isRotationOfCharactersEnabled();
        this.mRotationSupportedLangCodes = subtitleConfig2.getCharacterRotationSupportedLanguageCodes();
        this.mCharsToRotate = subtitleConfig2.getCharactersToRotateInVerticalSubtitles();
        this.mDegreeOfRotation = subtitleConfig2.getDegreeOfRotation();
        this.mPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "pmetMetricReporter");
        this.mIsHorizontalSubtitleFontShearEnabled = subtitleConfig.isHorizontalSubtitleFontShearEnabled();
        this.mIsVerticalSubtitleFontShearEnabled = subtitleConfig.isVerticalSubtitleFontShearEnabled();
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@Nonnull Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        if (i >= i2) {
            return 0;
        }
        paint.setTextSize(this.mCanvasUtil.getTextSize());
        return this.mCanvasUtil.getWidth();
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(@Nonnull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @Nonnull Paint paint) {
        if (i >= i2) {
            return;
        }
        Preconditions.checkNotNull(canvas, "Canvas");
        Preconditions.checkNotNull(paint, "Paint");
        float fontShear = this.mCanvasUtil.getFontShear();
        if (this.mIsVertical) {
            if (this.mIsVerticalSubtitleFontShearEnabled && fontShear != 0.0f) {
                paint.setTextSkewX(fontShear);
                this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.FONT_SHEAR));
            }
            drawVerticalText(canvas, paint);
            return;
        }
        if (this.mIsHorizontalSubtitleFontShearEnabled && fontShear != 0.0f) {
            paint.setTextSkewX(-fontShear);
            this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.FONT_SHEAR));
        }
        drawHorizontalText(canvas, paint);
    }

    private void drawHorizontalText(@Nonnull Canvas canvas, @Nonnull Paint paint) {
        List<SubtitleElement> subtitleElements = this.mCanvasUtil.getSubtitleElements();
        SubtitleRegionWritingMode writingMode = this.mCanvasUtil.getWritingMode();
        int i = 0;
        for (SubtitleElement subtitleElement : subtitleElements) {
            String text = subtitleElement.getText();
            int horizontalSubtitleTextPositionX = this.mCanvasUtil.getHorizontalSubtitleTextPositionX(subtitleElement);
            List<SubtitleAnnotationElement> beforeAnnotationElements = subtitleElement.getBeforeAnnotationElements();
            List<SubtitleAnnotationElement> afterAnnotationElements = subtitleElement.getAfterAnnotationElements();
            if (beforeAnnotationElements.size() > 0) {
                int horizontalSubtitleTextPositionY = i + this.mCanvasUtil.getHorizontalSubtitleTextPositionY(i, this.mRubyScale);
                for (SubtitleAnnotationElement subtitleAnnotationElement : beforeAnnotationElements) {
                    renderHorizontalAnnotationText(paint, canvas, subtitleAnnotationElement, (int) this.mCanvasUtil.getAnnotationTextPositionX(subtitleElement, subtitleAnnotationElement, horizontalSubtitleTextPositionX), horizontalSubtitleTextPositionY, paint.measureText(this.mBaseText.substring(subtitleAnnotationElement.getStart(), subtitleAnnotationElement.getEnd())), this.mTextSize * this.mRubyScale, writingMode);
                    this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.HORIZONTAL_SUBTITLE_WITH_BEFORE_ANNOTATION));
                }
                i = horizontalSubtitleTextPositionY;
            }
            int horizontalSubtitleTextPositionY2 = i + this.mCanvasUtil.getHorizontalSubtitleTextPositionY(i, 1.0f);
            renderHorizontalBaseText(canvas, paint, text, horizontalSubtitleTextPositionX, horizontalSubtitleTextPositionY2, writingMode);
            if (afterAnnotationElements.size() > 0) {
                int horizontalSubtitleTextPositionY3 = horizontalSubtitleTextPositionY2 + this.mCanvasUtil.getHorizontalSubtitleTextPositionY(horizontalSubtitleTextPositionY2, this.mRubyScale);
                for (SubtitleAnnotationElement subtitleAnnotationElement2 : afterAnnotationElements) {
                    renderHorizontalAnnotationText(paint, canvas, subtitleAnnotationElement2, (int) this.mCanvasUtil.getAnnotationTextPositionX(subtitleElement, subtitleAnnotationElement2, horizontalSubtitleTextPositionX), horizontalSubtitleTextPositionY3, paint.measureText(this.mBaseText.substring(subtitleAnnotationElement2.getStart(), subtitleAnnotationElement2.getEnd())), this.mTextSize * this.mRubyScale, writingMode);
                    this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.HORIZONTAL_SUBTITLE_WITH_AFTER_ANNOTATION));
                }
                i = horizontalSubtitleTextPositionY3;
            } else {
                i = horizontalSubtitleTextPositionY2;
            }
        }
    }

    private void drawVerticalText(@Nonnull Canvas canvas, @Nonnull Paint paint) {
        List<SubtitleElement> subtitleElements = this.mCanvasUtil.getSubtitleElements();
        int verticalSubtitleTextPositionX = this.mCanvasUtil.getVerticalSubtitleTextPositionX();
        int iAbs = (int) Math.abs(paint.getFontMetrics().ascent);
        for (SubtitleElement subtitleElement : subtitleElements) {
            int verticalSubtitleTextPositionY = this.mCanvasUtil.getVerticalSubtitleTextPositionY(subtitleElement);
            List<SubtitleAnnotationElement> beforeAnnotationElements = subtitleElement.getBeforeAnnotationElements();
            List<SubtitleAnnotationElement> afterAnnotationElements = subtitleElement.getAfterAnnotationElements();
            if (beforeAnnotationElements.size() > 0) {
                int verticalSubtitleTextPositionX2 = this.mCanvasUtil.getVerticalSubtitleTextPositionX(verticalSubtitleTextPositionX, this.mRubyScale, Position.BEFORE);
                for (SubtitleAnnotationElement subtitleAnnotationElement : beforeAnnotationElements) {
                    int verticalSubtitleAnnotationTextPositionY = this.mCanvasUtil.getVerticalSubtitleAnnotationTextPositionY(verticalSubtitleTextPositionY, subtitleElement, subtitleAnnotationElement);
                    String annotationText = subtitleAnnotationElement.getAnnotationText();
                    float f = this.mRubyScale;
                    renderVerticalText(paint, canvas, annotationText, verticalSubtitleTextPositionX2, (int) (verticalSubtitleAnnotationTextPositionY + (iAbs * f)), this.mTextSize * f, true);
                    this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.VERTICAL_SUBTITLES_WITH_BEFORE_ANNOTATION));
                }
                verticalSubtitleTextPositionX = this.mCanvasUtil.getVerticalSubtitleTextPositionX(verticalSubtitleTextPositionX2, this.mRubyScale, Position.AFTER);
            }
            TTMLV2CanvasUtil tTMLV2CanvasUtil = this.mCanvasUtil;
            Position position = Position.BEFORE;
            int verticalSubtitleBaseTextPositionX = tTMLV2CanvasUtil.getVerticalSubtitleBaseTextPositionX(verticalSubtitleTextPositionX, 1.0f, subtitleElement, position);
            renderVerticalText(paint, canvas, subtitleElement, verticalSubtitleBaseTextPositionX, verticalSubtitleTextPositionY + iAbs, this.mTextSize);
            int verticalSubtitleBaseTextPositionX2 = this.mCanvasUtil.getVerticalSubtitleBaseTextPositionX(verticalSubtitleBaseTextPositionX, 1.0f, subtitleElement, Position.AFTER);
            this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.VERTICAL_SUBTITLES));
            if (afterAnnotationElements.size() > 0) {
                int verticalSubtitleTextPositionX3 = this.mCanvasUtil.getVerticalSubtitleTextPositionX(verticalSubtitleBaseTextPositionX2, this.mRubyScale, position);
                for (SubtitleAnnotationElement subtitleAnnotationElement2 : afterAnnotationElements) {
                    int verticalSubtitleAnnotationTextPositionY2 = this.mCanvasUtil.getVerticalSubtitleAnnotationTextPositionY(verticalSubtitleTextPositionY, subtitleElement, subtitleAnnotationElement2);
                    String annotationText2 = subtitleAnnotationElement2.getAnnotationText();
                    float f2 = this.mRubyScale;
                    renderVerticalText(paint, canvas, annotationText2, verticalSubtitleTextPositionX3, (int) (verticalSubtitleAnnotationTextPositionY2 + (iAbs * f2)), this.mTextSize * f2, true);
                    this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.VERTICAL_SUBTITLES_WITH_AFTER_ANNOTATION));
                }
                verticalSubtitleBaseTextPositionX2 = this.mCanvasUtil.getVerticalSubtitleTextPositionX(verticalSubtitleTextPositionX3, this.mRubyScale, Position.AFTER);
            }
            verticalSubtitleTextPositionX = verticalSubtitleBaseTextPositionX2;
        }
    }

    private void renderHorizontalBaseText(@Nonnull Canvas canvas, @Nonnull Paint paint, @Nonnull String str, int i, int i2, SubtitleRegionWritingMode subtitleRegionWritingMode) {
        paint.setTextSize(this.mTextSize);
        DLog.devf("TTMLV2: Rendering Text: %s, from (%s, %s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[subtitleRegionWritingMode.ordinal()] == 1) {
            for (int i3 = 0; i3 < str.length(); i3++) {
                String strValueOf = String.valueOf(str.charAt(i3));
                i = (int) (i - paint.measureText(strValueOf));
                canvas.drawText(strValueOf, i, i2, paint);
            }
            return;
        }
        canvas.drawText(str, i, i2, paint);
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.subtitle.internal.SubtitleSpan$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode;

        static {
            int[] iArr = new int[SubtitleRegionWritingMode.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode = iArr;
            try {
                iArr[SubtitleRegionWritingMode.RLTB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.LRTB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void renderHorizontalAnnotationText(@Nonnull Paint paint, @Nonnull Canvas canvas, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement, int i, int i2, float f, float f2, SubtitleRegionWritingMode subtitleRegionWritingMode) {
        paint.setTextSize(f2);
        String annotationText = subtitleAnnotationElement.getAnnotationText();
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[subtitleRegionWritingMode.ordinal()] == 1) {
            DLog.devf("TTMLV2: Rendering annotation Text: %s, from (%s, %s)", annotationText, Integer.valueOf(i), Integer.valueOf(i2));
            for (int i3 = 0; i3 < annotationText.length(); i3++) {
                String strValueOf = String.valueOf(annotationText.charAt(i3));
                float f3 = i;
                canvas.drawText(strValueOf, f3, i2, paint);
                i = (int) (f3 - paint.measureText(strValueOf));
            }
            return;
        }
        DLog.devf("TTMLV2: Rendering annotation Text: %s, from (%s, %s)", annotationText, Integer.valueOf(i), Integer.valueOf(i2));
        canvas.drawText(subtitleAnnotationElement.getAnnotationText(), i, i2, paint);
    }

    private void renderVerticalText(@Nonnull Paint paint, @Nonnull Canvas canvas, @Nonnull String str, int i, int i2, float f, boolean z) {
        float lineHeight;
        float f2;
        float lineHeight2;
        float lineGap;
        paint.setTextSize(f);
        DLog.devf("TTMLV2: VerticalText: %s, Rendering at (%s, %s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        for (int i3 = 0; i3 < str.length(); i3++) {
            String strValueOf = String.valueOf(str.charAt(i3));
            if (this.mIsRotationOfCharactersEnabled && this.mCharsToRotate.contains(strValueOf) && this.mRotationSupportedLangCodes.contains(this.mCurrentLanguageCode)) {
                renderRotatedVerticalText(canvas, paint, strValueOf, i, i2, z);
                f2 = i2;
                lineHeight2 = paint.measureText(strValueOf);
                lineGap = this.mCanvasUtil.getSpacingFactor();
            } else {
                if (z) {
                    lineHeight = this.mCanvasUtil.getLineHeight() * this.mRubyScale;
                } else {
                    lineHeight = this.mCanvasUtil.getLineHeight();
                }
                f2 = i2;
                canvas.drawText(strValueOf, (int) (i + ((lineHeight - paint.measureText(strValueOf)) / 2.0f)), f2, paint);
                lineHeight2 = this.mCanvasUtil.getLineHeight();
                if (z) {
                    lineHeight2 *= this.mRubyScale;
                }
                lineGap = this.mCanvasUtil.getLineGap();
            }
            i2 = (int) (f2 + lineHeight2 + lineGap);
        }
    }

    private void renderVerticalText(@Nonnull Paint paint, @Nonnull Canvas canvas, @Nonnull SubtitleElement subtitleElement, int i, int i2, float f) {
        int lineHeight;
        paint.setTextSize(f);
        if (subtitleElement.hasTextCombineElements()) {
            int startIndex = subtitleElement.getStartIndex();
            List<SubtitleTextStyle> subtitleTextCombineStyleElements = subtitleElement.getSubtitleTextCombineStyleElements();
            int i3 = 1;
            int lineHeight2 = i2;
            SubtitleTextStyle subtitleTextStyle = subtitleTextCombineStyleElements.get(0);
            int length = startIndex;
            int i4 = 1;
            while (length < subtitleElement.getEndIndex()) {
                if (length == subtitleTextStyle.getStart()) {
                    String strSubstring = this.mBaseText.substring(subtitleTextStyle.getStart(), subtitleTextStyle.getEnd() + i3);
                    float f2 = lineHeight2;
                    canvas.drawText(strSubstring, i - (((int) (paint.measureText(strSubstring) - this.mCanvasUtil.getLineHeight())) / 2), f2, paint);
                    if (i4 < subtitleTextCombineStyleElements.size()) {
                        SubtitleTextStyle subtitleTextStyle2 = subtitleTextCombineStyleElements.get(i4);
                        i4++;
                        subtitleTextStyle = subtitleTextStyle2;
                    }
                    length += strSubstring.length();
                    lineHeight2 = (int) (f2 + this.mCanvasUtil.getLineHeight() + this.mCanvasUtil.getLineGap());
                } else {
                    String strValueOf = String.valueOf(this.mBaseText.charAt(length));
                    if (this.mIsRotationOfCharactersEnabled && this.mCharsToRotate.contains(strValueOf) && this.mRotationSupportedLangCodes.contains(this.mCurrentLanguageCode)) {
                        renderRotatedVerticalText(canvas, paint, strValueOf, i, lineHeight2, false);
                        lineHeight = (int) (lineHeight2 + paint.measureText(strValueOf) + this.mCanvasUtil.getSpacingFactor());
                    } else {
                        float f3 = lineHeight2;
                        canvas.drawText(strValueOf, i - (((int) (paint.measureText(strValueOf) - this.mCanvasUtil.getLineHeight())) / 2), f3, paint);
                        lineHeight = (int) (f3 + this.mCanvasUtil.getLineHeight() + this.mCanvasUtil.getLineGap());
                    }
                    lineHeight2 = lineHeight;
                    length++;
                    i3 = 1;
                }
            }
            this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.TEXT_COMBINE));
            return;
        }
        renderVerticalText(paint, canvas, subtitleElement.getText(), i, i2, f, false);
    }

    private void renderRotatedVerticalText(@Nonnull Canvas canvas, @Nonnull Paint paint, @Nonnull String str, int i, int i2, boolean z) {
        float lineHeight = this.mCanvasUtil.getLineHeight();
        int i3 = (int) (i + (z ? this.mRubyScale * lineHeight : lineHeight));
        int iAbs = (int) (i2 - Math.abs(paint.getFontMetrics().ascent));
        int iMeasureText = (int) ((lineHeight - paint.measureText(str)) / 2.0f);
        canvas.save();
        canvas.translate(i3, iAbs);
        canvas.rotate(this.mDegreeOfRotation);
        canvas.drawText(str, iMeasureText, Math.abs(paint.getFontMetrics().ascent), paint);
        canvas.restore();
        this.mPmetMetricReporter.reportSubtitleMetrics(SubtitlePmetMetrics.toReportableString(SubtitlePmetMetrics.ROTATED_TEXT));
    }
}
