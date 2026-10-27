package com.amazon.avod.playbackclient.subtitle.internal;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.widget.TextView;
import com.amazon.avod.core.subtitle.Position;
import com.amazon.avod.core.subtitle.SubtitleAnnotationTextPosition;
import com.amazon.avod.core.subtitle.SubtitleFontStyleType;
import com.amazon.avod.core.subtitle.SubtitleRegionWritingMode;
import com.amazon.avod.core.subtitle.SubtitleTextAlignmentType;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleAttribute;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TTMLV2CanvasUtil {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private AtomicReference<Integer> mCalculatedHeight;
    private AtomicReference<Integer> mCalculatedWidth;
    private final Set<String> mCharsToRotate;
    private final Dimension mHorizontalSubtitleExtent;
    private final boolean mIsRotationOfCharactersEnabled;
    private final boolean mIsVertical;
    private final Paint mPaint;
    private final float mRotatedCharactersSpacingFactor;
    private final float mRubyScale;
    private final Dimension mSubtitleBottomOrigin;
    private List<SubtitleElement> mSubtitleElements;
    private final Dimension mSubtitleLeftOrigin;
    private final Dimension mSubtitleRightOrigin;
    private final SubtitleTextElement mSubtitleTextElement;
    private final Dimension mSubtitleTopOrigin;
    private final float mTextSize;
    private final TextView mTextView;
    private final Dimension mVerticalSubtitleExtent;
    private AtomicReference<SubtitleRegionWritingMode> mWritingMode;

    public TTMLV2CanvasUtil(@Nonnull TextView textView, @Nonnull SubtitleTextElement subtitleTextElement, boolean z) {
        this(textView, subtitleTextElement, z, SubtitleConfig.getInstance());
    }

    TTMLV2CanvasUtil(@Nonnull TextView textView, @Nonnull SubtitleTextElement subtitleTextElement, boolean z, @Nonnull SubtitleConfig subtitleConfig) {
        TextView textView2 = (TextView) Preconditions.checkNotNull(textView, "textView");
        this.mTextView = textView2;
        this.mSubtitleTextElement = (SubtitleTextElement) Preconditions.checkNotNull(subtitleTextElement, "SubtitleTextElement");
        this.mPaint = (Paint) Preconditions.checkNotNull(textView.getPaint(), "paint");
        this.mIsVertical = z;
        this.mRubyScale = subtitleConfig.getRubyToBaseScalingFactor();
        this.mTextSize = textView2.getTextSize();
        this.mIsRotationOfCharactersEnabled = subtitleConfig.isRotationOfCharactersEnabled();
        this.mCharsToRotate = subtitleConfig.getCharactersToRotateInVerticalSubtitles();
        this.mRotatedCharactersSpacingFactor = subtitleConfig.getSpacingScaleAroundVerticalSubtitlesRotatedCharacters();
        this.mHorizontalSubtitleExtent = Dimension.from(subtitleConfig.getHorizontalSubtitleExtent());
        this.mVerticalSubtitleExtent = Dimension.from(subtitleConfig.getVerticalSubtitleExtent());
        this.mSubtitleTopOrigin = Dimension.from(subtitleConfig.getSubtitleTopOrigin());
        this.mSubtitleLeftOrigin = Dimension.from(subtitleConfig.getSubtitleLeftOrigin());
        this.mSubtitleRightOrigin = Dimension.from(subtitleConfig.getSubtitleRightOrigin());
        this.mSubtitleBottomOrigin = SubtitleAttribute.Defaults.ORIGIN;
    }

    public void init() {
        String str = LINE_SEPARATOR;
        int length = str.length();
        String subtitleText = this.mSubtitleTextElement.getSubtitleText();
        TreeMap treeMap = new TreeMap();
        this.mSubtitleElements = new ArrayList();
        int i = 0;
        for (String str2 : subtitleText.split(str)) {
            int length2 = i + str2.length();
            SubtitleElement subtitleElement = new SubtitleElement(i, length2, str2, new ArrayList(), new ArrayList(), getSubtitleTextCombineStyleElements(i, length2));
            treeMap.put(Integer.valueOf(i), subtitleElement);
            this.mSubtitleElements.add(subtitleElement);
            i = length2 + length;
        }
        if (this.mSubtitleTextElement.getRegionElement() == null) {
            this.mWritingMode = new AtomicReference<>(this.mIsVertical ? SubtitleRegionWritingMode.TBRL : SubtitleRegionWritingMode.LRTB);
        } else {
            this.mWritingMode = new AtomicReference<>(this.mSubtitleTextElement.getRegionElement().getWriteMode());
        }
        for (SubtitleAnnotationElement subtitleAnnotationElement : this.mSubtitleTextElement.getAnnotationElements()) {
            Map.Entry<Integer, SubtitleElement> entryFloorEntry = treeMap.floorEntry(Integer.valueOf(subtitleAnnotationElement.getStart()));
            if (entryFloorEntry != null && entryFloorEntry.getValue().getEndIndex() >= subtitleAnnotationElement.getEnd()) {
                updateSubtitleElementWithAnnotations(entryFloorEntry, subtitleAnnotationElement);
            } else {
                DLog.logf("TTMLV2: Current annotation element is not falling into any subtitle line window, dropping SubtitleElement: %s", subtitleAnnotationElement);
            }
        }
        calculateDimensions();
    }

    @Nonnull
    List<SubtitleTextStyle> getSubtitleTextCombineStyleElements(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (SubtitleTextStyle subtitleTextStyle : this.mSubtitleTextElement.getTextStyles()) {
            if (subtitleTextStyle.hasTextCombineTag() && subtitleTextStyle.getStart() >= i && subtitleTextStyle.getEnd() <= i2) {
                arrayList.add(subtitleTextStyle);
            }
        }
        return arrayList;
    }

    private void calculateDimensions() {
        this.mPaint.setTextSize(this.mTextSize);
        int iMax = 0;
        int i = 0;
        for (SubtitleElement subtitleElement : this.mSubtitleElements) {
            int lineHeight = this.mTextView.getLineHeight();
            if (this.mIsVertical && subtitleElement.hasTextCombineElements()) {
                lineHeight = Math.max(lineHeight, measureWidthForTextCombineText(subtitleElement));
                iMax = Math.max(iMax, measureHeightForTextCombineElementWithOffSets(subtitleElement));
            } else {
                iMax = (int) Math.max(iMax, getSubtitleTextLineMeasuredSize(subtitleElement));
            }
            if (subtitleElement.getBeforeAnnotationElements().size() > 0) {
                lineHeight = (int) (lineHeight + (this.mTextView.getLineHeight() * this.mRubyScale));
            }
            if (subtitleElement.getAfterAnnotationElements().size() > 0) {
                lineHeight = (int) (lineHeight + (this.mTextView.getLineHeight() * this.mRubyScale));
            }
            i += lineHeight;
        }
        this.mCalculatedWidth = new AtomicReference<>(Integer.valueOf((this.mIsVertical ? i : iMax) + this.mTextView.getPaddingLeft() + this.mTextView.getPaddingRight()));
        if (!this.mIsVertical) {
            iMax = i;
        }
        this.mCalculatedHeight = new AtomicReference<>(Integer.valueOf(iMax + this.mTextView.getPaddingTop() + this.mTextView.getPaddingBottom()));
        DLog.devf("TTMLV2: TextView dimensions, Width: %s, Height: %s", this.mCalculatedWidth.get(), this.mCalculatedHeight.get());
    }

    private int measureWidthForTextCombineText(@Nonnull SubtitleElement subtitleElement) {
        this.mPaint.setTextSize(this.mTextSize);
        List<SubtitleTextStyle> subtitleTextCombineStyleElements = subtitleElement.getSubtitleTextCombineStyleElements();
        String subtitleText = this.mSubtitleTextElement.getSubtitleText();
        int iMax = 0;
        for (SubtitleTextStyle subtitleTextStyle : subtitleTextCombineStyleElements) {
            iMax = (int) Math.max(iMax, this.mPaint.measureText(subtitleText.substring(subtitleTextStyle.getStart(), subtitleTextStyle.getEnd() + 1)));
        }
        return (int) (iMax + getLineGap());
    }

    private int measureHeightForTextCombineText(@Nonnull SubtitleElement subtitleElement) {
        int length = (int) (subtitleElement.getText().length() * (this.mTextView.getLineHeight() + getLineGap()));
        for (SubtitleTextStyle subtitleTextStyle : subtitleElement.getSubtitleTextCombineStyleElements()) {
            length -= (subtitleTextStyle.getEnd() - subtitleTextStyle.getStart()) * this.mTextView.getLineHeight();
        }
        return length;
    }

    private int measureHeightForTextCombineElementWithOffSets(@Nonnull SubtitleElement subtitleElement) {
        return (int) (measureHeightForTextCombineText(subtitleElement) + Math.max(getMaxFrontOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxFrontOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements())) + Math.max(getMaxBackOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxBackOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements())));
    }

    private float getSubtitleTextLineMeasuredSize(@Nonnull SubtitleElement subtitleElement) {
        this.mPaint.setTextSize(this.mTextSize);
        String text = subtitleElement.getText();
        return (this.mIsVertical ? getMeasuredSizeOfVerticalSubtitleText(text) : this.mPaint.measureText(text)) + Math.max(getMaxFrontOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxFrontOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements())) + Math.max(getMaxBackOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxBackOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements()));
    }

    private float getMeasuredSizeOfVerticalSubtitleText(@Nonnull String str) {
        float lineGap;
        float f = this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent;
        float f2 = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            String strValueOf = String.valueOf(str.charAt(i));
            if (this.mIsRotationOfCharactersEnabled && this.mCharsToRotate.contains(strValueOf)) {
                lineGap = this.mPaint.measureText(strValueOf) + getSpacingFactor() + getLineGap();
            } else {
                lineGap = getLineGap() + f;
            }
            f2 += lineGap;
        }
        return f2;
    }

    public float getSpacingFactor() {
        return getLineHeight() * this.mRotatedCharactersSpacingFactor;
    }

    float getBaseTextMidPoint(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        float fMeasureText;
        float fMeasureText2;
        this.mPaint.setTextSize(this.mTextSize);
        float f = this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent;
        String subtitleText = this.mSubtitleTextElement.getSubtitleText();
        String strSubstring = subtitleText.substring(subtitleElement.getStartIndex(), subtitleAnnotationElement.getStart());
        String strSubstring2 = subtitleText.substring(subtitleElement.getStartIndex(), subtitleAnnotationElement.getEnd() + 1);
        if (this.mIsVertical) {
            fMeasureText = strSubstring.length() * f;
        } else {
            fMeasureText = this.mPaint.measureText(strSubstring);
        }
        if (this.mIsVertical) {
            fMeasureText2 = f * strSubstring2.length();
        } else {
            fMeasureText2 = this.mPaint.measureText(strSubstring2);
        }
        return (fMeasureText + fMeasureText2) / 2.0f;
    }

    private int getBaseTextMidPointWithTextCombine(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        this.mPaint.setTextSize(this.mTextSize);
        float f = this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent;
        List<SubtitleTextStyle> subtitleTextCombineStyleElements = subtitleElement.getSubtitleTextCombineStyleElements();
        int start = (subtitleAnnotationElement.getStart() + subtitleAnnotationElement.getEnd()) / 2;
        float startIndex = ((start - subtitleElement.getStartIndex()) + 1) * f;
        Iterator<SubtitleTextStyle> it = subtitleTextCombineStyleElements.iterator();
        while (it.hasNext()) {
            if (it.next().getEnd() < start) {
                startIndex -= (r1.getEnd() - r1.getStart()) * f;
            }
        }
        return (int) (startIndex + (start % 2 == 0 ? 0.0f : f / 2.0f));
    }

    float getAnnotationTextMidPoint(@Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        float fMeasureText;
        this.mPaint.setTextSize(this.mTextSize * this.mRubyScale);
        float f = this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent;
        if (this.mIsVertical) {
            fMeasureText = subtitleAnnotationElement.getAnnotationText().length() * f;
        } else {
            fMeasureText = this.mPaint.measureText(subtitleAnnotationElement.getAnnotationText());
        }
        this.mPaint.setTextSize(this.mTextSize);
        return fMeasureText / 2.0f;
    }

    float getSubtitleElementFrontOffset(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        float baseTextMidPoint = getBaseTextMidPoint(subtitleElement, subtitleAnnotationElement) - getAnnotationTextMidPoint(subtitleAnnotationElement);
        if (baseTextMidPoint < 0.0f) {
            return 0.0f + Math.abs(baseTextMidPoint);
        }
        return 0.0f;
    }

    private float getMaxFrontOffSet(@Nonnull SubtitleElement subtitleElement, @Nonnull List<SubtitleAnnotationElement> list) {
        Iterator<SubtitleAnnotationElement> it = list.iterator();
        float fMax = 0.0f;
        while (it.hasNext()) {
            fMax = Math.max(fMax, getSubtitleElementFrontOffset(subtitleElement, it.next()));
        }
        return fMax;
    }

    private float getMaxBackOffSet(@Nonnull SubtitleElement subtitleElement, @Nonnull List<SubtitleAnnotationElement> list) {
        float subtitleElementBackOffset;
        float fMax = 0.0f;
        for (SubtitleAnnotationElement subtitleAnnotationElement : list) {
            if (subtitleElement.hasTextCombineElements()) {
                subtitleElementBackOffset = getSubtitleElementWithTextCombineBackOffset(subtitleElement, subtitleAnnotationElement);
            } else {
                subtitleElementBackOffset = getSubtitleElementBackOffset(subtitleElement, subtitleAnnotationElement);
            }
            fMax = Math.max(fMax, subtitleElementBackOffset);
        }
        return fMax;
    }

    float getSubtitleElementBackOffset(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        float fMeasureText;
        this.mPaint.setTextSize(this.mTextSize);
        float f = this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent;
        String subtitleText = this.mSubtitleTextElement.getSubtitleText();
        if (this.mIsVertical) {
            fMeasureText = subtitleText.length() * f;
        } else {
            fMeasureText = this.mPaint.measureText(subtitleText);
        }
        float baseTextMidPoint = (fMeasureText - getBaseTextMidPoint(subtitleElement, subtitleAnnotationElement)) - getAnnotationTextMidPoint(subtitleAnnotationElement);
        if (baseTextMidPoint < 0.0f) {
            return Math.abs(baseTextMidPoint);
        }
        return 0.0f;
    }

    float getSubtitleElementWithTextCombineBackOffset(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        this.mPaint.setTextSize(this.mTextSize);
        float fMeasureHeightForTextCombineText = (measureHeightForTextCombineText(subtitleElement) - getBaseTextMidPointWithTextCombine(subtitleElement, subtitleAnnotationElement)) - getAnnotationTextMidPoint(subtitleAnnotationElement);
        if (fMeasureHeightForTextCombineText < 0.0f) {
            return Math.abs(fMeasureHeightForTextCombineText);
        }
        return 0.0f;
    }

    private void updateSubtitleElementWithAnnotations(@Nonnull Map.Entry<Integer, SubtitleElement> entry, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition[subtitleAnnotationElement.getTextPosition().ordinal()];
        if (i == 1) {
            if (this.mWritingMode.get() == SubtitleRegionWritingMode.TBRL) {
                entry.getValue().getAfterAnnotationElements().add(subtitleAnnotationElement);
                return;
            } else if (this.mWritingMode.get() == SubtitleRegionWritingMode.TBLR) {
                entry.getValue().getBeforeAnnotationElements().add(subtitleAnnotationElement);
                return;
            } else {
                DLog.warnf("TTMLV2: annotationElement: %s, has invalid writingMode: %s", subtitleAnnotationElement, this.mWritingMode.get());
                return;
            }
        }
        if (i != 2) {
            if (i == 3) {
                entry.getValue().getBeforeAnnotationElements().add(subtitleAnnotationElement);
                return;
            } else if (i == 4) {
                entry.getValue().getAfterAnnotationElements().add(subtitleAnnotationElement);
                return;
            } else {
                DLog.warnf("TTMLV2: annotationElement: %s, has invalid TextPosition", subtitleAnnotationElement);
                return;
            }
        }
        if (this.mWritingMode.get() == SubtitleRegionWritingMode.TBRL) {
            entry.getValue().getBeforeAnnotationElements().add(subtitleAnnotationElement);
        } else if (this.mWritingMode.get() == SubtitleRegionWritingMode.TBLR) {
            entry.getValue().getAfterAnnotationElements().add(subtitleAnnotationElement);
        } else {
            DLog.warnf("TTMLV2: annotationElement: %s, has invalid writingMode: %s", subtitleAnnotationElement, this.mWritingMode.get());
        }
    }

    public float getLineGap() {
        this.mPaint.setTextSize(this.mTextSize);
        return (float) Math.ceil(Math.abs(this.mTextView.getLineHeight() - (this.mPaint.getFontMetrics().descent - this.mPaint.getFontMetrics().ascent)));
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public float getLineHeight() {
        return this.mTextView.getLineHeight();
    }

    public int getPaddingTop() {
        return this.mTextView.getPaddingTop();
    }

    public int getPaddingBottom() {
        return this.mTextView.getPaddingBottom();
    }

    public int getPaddingLeft() {
        return this.mTextView.getPaddingLeft();
    }

    public int getPaddingRight() {
        return this.mTextView.getPaddingRight();
    }

    @Nonnull
    public SubtitleRegionWritingMode getWritingMode() {
        AtomicReference<SubtitleRegionWritingMode> atomicReference = this.mWritingMode;
        if (atomicReference != null) {
            return atomicReference.get();
        }
        return this.mIsVertical ? SubtitleRegionWritingMode.TBRL : SubtitleRegionWritingMode.LRTB;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.text.DecimalFormat, java.text.NumberFormat] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Object[]] */
    public float getFontShear() {
        ?? fontShear = this.mSubtitleTextElement.getStyleElement() == null ? 0 : this.mSubtitleTextElement.getStyleElement().getFontShear();
        float f = 0.0f;
        f = 0.0f;
        f = 0.0f;
        if (fontShear != 0) {
            try {
                Number number = new DecimalFormat().parse(fontShear);
                if (number != null) {
                    float fFloatValue = number.floatValue() / 100.0f;
                    f = fFloatValue;
                    fontShear = fFloatValue;
                } else {
                    DLog.warnf("Parser returned null value while parsing %s to float", new Object[]{fontShear});
                    fontShear = fontShear;
                }
            } catch (ParseException e) {
                DLog.warnf("Encountered ParseException with converting %s to float. Exception message: %s", new Object[]{fontShear, e.getMessage()});
            }
        }
        return f;
    }

    public int getHorizontalSubtitleTextPositionX(@Nonnull SubtitleElement subtitleElement) {
        int horizontalSubtitleTextPositionXForRLTB;
        float f;
        if (this.mCalculatedWidth == null) {
            return 0;
        }
        float subtitleTextLineMeasuredSize = getSubtitleTextLineMeasuredSize(subtitleElement);
        float fMax = Math.max(getMaxFrontOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxFrontOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements()));
        SubtitleTextAlignmentType textAlign = this.mSubtitleTextElement.getStyleElement() != null ? this.mSubtitleTextElement.getStyleElement().getTextAlign() : SubtitleTextAlignmentType.CENTER;
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[getWritingMode().ordinal()] == 1) {
            horizontalSubtitleTextPositionXForRLTB = getHorizontalSubtitleTextPositionXForRLTB(textAlign, subtitleTextLineMeasuredSize);
            if (fMax > 0.0f) {
                f = horizontalSubtitleTextPositionXForRLTB - fMax;
                horizontalSubtitleTextPositionXForRLTB = (int) f;
            }
        } else {
            horizontalSubtitleTextPositionXForRLTB = getHorizontalSubtitleTextPositionXForLRTB(textAlign, subtitleTextLineMeasuredSize);
            if (fMax > 0.0f) {
                f = horizontalSubtitleTextPositionXForRLTB + fMax;
                horizontalSubtitleTextPositionXForRLTB = (int) f;
            }
        }
        DLog.devf("TTMLV2: Text: %s, TextAlignment: %s, writingMode: %s, positionX: %s ", subtitleElement.getText(), textAlign.toString(), getWritingMode(), Integer.valueOf(horizontalSubtitleTextPositionXForRLTB));
        return horizontalSubtitleTextPositionXForRLTB;
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.subtitle.internal.TTMLV2CanvasUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType;

        static {
            int[] iArr = new int[SubtitleTextAlignmentType.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType = iArr;
            try {
                iArr[SubtitleTextAlignmentType.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[SubtitleTextAlignmentType.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[SubtitleTextAlignmentType.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[SubtitleTextAlignmentType.END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[SubtitleTextAlignmentType.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[SubtitleRegionWritingMode.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode = iArr2;
            try {
                iArr2[SubtitleRegionWritingMode.RLTB.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.LRTB.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBLR.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBRL.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[SubtitleAnnotationTextPosition.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition = iArr3;
            try {
                iArr3[SubtitleAnnotationTextPosition.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition[SubtitleAnnotationTextPosition.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition[SubtitleAnnotationTextPosition.OVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleAnnotationTextPosition[SubtitleAnnotationTextPosition.UNDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private int getHorizontalSubtitleTextPositionXForRLTB(@Nonnull SubtitleTextAlignmentType subtitleTextAlignmentType, float f) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[subtitleTextAlignmentType.ordinal()];
        if (i == 1 || i == 2) {
            return (int) f;
        }
        if (i == 3 || i == 4) {
            return Math.abs((this.mCalculatedWidth.get().intValue() - this.mTextView.getPaddingRight()) - this.mTextView.getPaddingRight());
        }
        return (int) (((Math.abs(this.mCalculatedWidth.get().intValue() - f) / 2.0f) - this.mTextView.getPaddingRight()) + f);
    }

    private int getHorizontalSubtitleTextPositionXForLRTB(@Nonnull SubtitleTextAlignmentType subtitleTextAlignmentType, float f) {
        float fAbs;
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[subtitleTextAlignmentType.ordinal()];
        if (i == 1 || i == 2) {
            return 0;
        }
        if (i == 3 || i == 4) {
            fAbs = Math.abs(((this.mCalculatedWidth.get().intValue() - f) - this.mTextView.getPaddingRight()) - this.mTextView.getPaddingLeft());
        } else {
            fAbs = (Math.abs(this.mCalculatedWidth.get().intValue() - f) / 2.0f) - this.mTextView.getPaddingLeft();
        }
        return (int) fAbs;
    }

    public int getHorizontalSubtitleTextPositionY(int i, float f) {
        if (i == 0) {
            this.mPaint.setTextSize(this.mTextSize * f);
            int iAbs = (int) Math.abs(this.mPaint.getFontMetrics().ascent);
            this.mPaint.setTextSize(this.mTextSize);
            return iAbs;
        }
        return (int) (this.mTextView.getLineHeight() * f);
    }

    public int getVerticalSubtitleTextPositionX() {
        if (this.mCalculatedWidth == null || AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[this.mWritingMode.get().ordinal()] == 3) {
            return 0;
        }
        return Math.abs((this.mCalculatedWidth.get().intValue() - this.mTextView.getPaddingRight()) - this.mTextView.getPaddingLeft());
    }

    public int getVerticalSubtitleBaseTextPositionX(int i, float f, @Nonnull SubtitleElement subtitleElement, @Nonnull Position position) {
        Preconditions.checkNotNull(subtitleElement, "subtitleElement");
        Preconditions.checkNotNull(position, "position");
        if (subtitleElement.hasTextCombineElements()) {
            int lineHeight = this.mTextView.getLineHeight();
            int iMax = Math.max(lineHeight, measureWidthForTextCombineText(subtitleElement));
            if (position == Position.BEFORE) {
                if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[this.mWritingMode.get().ordinal()] == 3) {
                    return (i + (iMax / 2)) - (lineHeight / 2);
                }
                return (i - (iMax / 2)) - (lineHeight / 2);
            }
            if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[this.mWritingMode.get().ordinal()] == 3) {
                return i + (iMax / 2) + (lineHeight / 2);
            }
            return (i - (iMax / 2)) + (lineHeight / 2);
        }
        return getVerticalSubtitleTextPositionX(i, f, position);
    }

    public int getVerticalSubtitleTextPositionX(int i, float f, @Nonnull Position position) {
        Preconditions.checkNotNull(position, "position");
        int lineHeight = (int) (this.mTextView.getLineHeight() * f);
        return this.mCalculatedWidth == null ? i - lineHeight : position == Position.BEFORE ? AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[this.mWritingMode.get().ordinal()] != 3 ? i - lineHeight : i : AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[this.mWritingMode.get().ordinal()] != 3 ? i : i + lineHeight;
    }

    public int getVerticalSubtitleTextPositionY(@Nonnull SubtitleElement subtitleElement) {
        if (this.mCalculatedHeight == null) {
            return 0;
        }
        float subtitleTextLineMeasuredSize = getSubtitleTextLineMeasuredSize(subtitleElement);
        float fMax = Math.max(getMaxFrontOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxFrontOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements()));
        float fMax2 = Math.max(getMaxBackOffSet(subtitleElement, subtitleElement.getBeforeAnnotationElements()), getMaxBackOffSet(subtitleElement, subtitleElement.getAfterAnnotationElements()));
        SubtitleTextAlignmentType textAlign = this.mSubtitleTextElement.getStyleElement() != null ? this.mSubtitleTextElement.getStyleElement().getTextAlign() : SubtitleTextAlignmentType.START;
        this.mPaint.setTextSize(this.mTextSize);
        if (subtitleElement.hasTextCombineElements()) {
            subtitleTextLineMeasuredSize = measureHeightForTextCombineElementWithOffSets(subtitleElement);
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextAlignmentType[textAlign.ordinal()];
        if (i == 1 || i == 2) {
            return (int) (this.mTextView.getPaddingTop() + fMax);
        }
        if (i == 3 || i == 4) {
            return ((int) ((this.mCalculatedHeight.get().intValue() - fMax2) - this.mTextView.getPaddingBottom())) - this.mTextView.getPaddingTop();
        }
        return (int) ((((this.mCalculatedHeight.get().intValue() - subtitleTextLineMeasuredSize) / 2.0f) + fMax) - this.mTextView.getPaddingTop());
    }

    public float getAnnotationTextPositionX(@Nonnull SubtitleElement subtitleElement, @Nonnull SubtitleAnnotationElement subtitleAnnotationElement, float f) {
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[getWritingMode().ordinal()] == 1) {
            float subtitleElementFrontOffset = getSubtitleElementFrontOffset(subtitleElement, subtitleAnnotationElement);
            return subtitleElementFrontOffset > 0.0f ? f + subtitleElementFrontOffset : getBaseTextMidPoint(subtitleElement, subtitleAnnotationElement) + getAnnotationTextMidPoint(subtitleAnnotationElement);
        }
        float subtitleElementFrontOffset2 = getSubtitleElementFrontOffset(subtitleElement, subtitleAnnotationElement);
        return subtitleElementFrontOffset2 > 0.0f ? f - subtitleElementFrontOffset2 : f + (getBaseTextMidPoint(subtitleElement, subtitleAnnotationElement) - getAnnotationTextMidPoint(subtitleAnnotationElement));
    }

    public int getVerticalSubtitleAnnotationTextPositionY(float f, SubtitleElement subtitleElement, SubtitleAnnotationElement subtitleAnnotationElement) {
        float baseTextMidPoint;
        float annotationTextMidPoint;
        float subtitleElementFrontOffset = getSubtitleElementFrontOffset(subtitleElement, subtitleAnnotationElement);
        if (subtitleElementFrontOffset > 0.0f) {
            annotationTextMidPoint = f - subtitleElementFrontOffset;
        } else {
            if (subtitleElement.hasTextCombineElements()) {
                baseTextMidPoint = getBaseTextMidPointWithTextCombine(subtitleElement, subtitleAnnotationElement);
            } else {
                baseTextMidPoint = getBaseTextMidPoint(subtitleElement, subtitleAnnotationElement);
            }
            annotationTextMidPoint = (f + baseTextMidPoint) - getAnnotationTextMidPoint(subtitleAnnotationElement);
        }
        return (int) annotationTextMidPoint;
    }

    @Nonnull
    public int getWidth() {
        AtomicReference<Integer> atomicReference;
        if (this.mCalculatedHeight == null || (atomicReference = this.mCalculatedWidth) == null) {
            return 0;
        }
        return atomicReference.get().intValue();
    }

    public int getHeight() {
        AtomicReference<Integer> atomicReference = this.mCalculatedHeight;
        if (atomicReference == null || this.mCalculatedWidth == null) {
            return 0;
        }
        return atomicReference.get().intValue();
    }

    @Nonnull
    public List<SubtitleElement> getSubtitleElements() {
        return this.mSubtitleElements;
    }

    public int getGravity() {
        SubtitleRegionElement regionElement = this.mSubtitleTextElement.getRegionElement();
        if (regionElement != null && regionElement.getPosition() != null) {
            return getGravity(regionElement.getPosition().split(" "));
        }
        return getDefaultGravity();
    }

    public SubtitleFontStyleType getFontStyle() {
        SubtitleStyleElement styleElement = this.mSubtitleTextElement.getStyleElement();
        if (styleElement == null) {
            return SubtitleFontStyleType.NORMAL;
        }
        return SubtitleFontStyleType.findMatch(styleElement.getFontStyle());
    }

    @SuppressLint({"RtlHardcoded"})
    private int getGravity(@Nonnull String[] strArr) {
        for (String str : strArr) {
            String lowerCase = str.toLowerCase(Locale.US);
            lowerCase.hashCode();
            switch (lowerCase) {
                case "bottom":
                    return 81;
                case "top":
                    return 49;
                case "left":
                    return 19;
                case "right":
                    return 21;
                default:
                    break;
            }
        }
        return getDefaultGravity();
    }

    @SuppressLint({"RtlHardcoded"})
    private int getDefaultGravity() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[getWritingMode().ordinal()];
        return (i == 3 || i == 4) ? 19 : 81;
    }
}
