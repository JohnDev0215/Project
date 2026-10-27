package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.FontStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: TextContainerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 M2\u00020\u0001:\u0002MNB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/J\u0010\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u0013H\u0002J\u0010\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u0013H\u0002J\b\u00104\u001a\u00020\u0011H\u0002J \u00105\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u00106\u001a\u00020 2\u0006\u00101\u001a\u00020\u0013H\u0002J\u0010\u00107\u001a\u00020+2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020:H\u0014J0\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u00132\u0006\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u0013H\u0014J\u0018\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020\u00132\u0006\u0010C\u001a\u00020\u0013H\u0014J(\u0010D\u001a\u00020-2\u0006\u0010E\u001a\u00020\u00132\u0006\u0010F\u001a\u00020\u00132\u0006\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020\u0013H\u0014J\b\u0010I\u001a\u00020-H\u0002JC\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010LR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010!\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001c\"\u0004\b#\u0010\u001eR(\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u0012\u001a\u0004\u0018\u00010$@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/amazon/music/views/library/views/TextContainerView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alwaysDisplayTruncationText", "", "getAlwaysDisplayTruncationText", "()Ljava/lang/Boolean;", "setAlwaysDisplayTruncationText", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "contentRect", "Landroid/graphics/RectF;", "footerStyleSpan", "Landroid/text/style/CharacterStyle;", "layout", "Landroid/text/Layout;", "value", "", "maxLineCount", "getMaxLineCount", "()I", "setMaxLineCount", "(I)V", "", "text", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "textPaint", "Landroid/text/TextPaint;", "trimToken", "getTrimToken", "setTrimToken", "", "truncationText", "getTruncationText", "()Ljava/lang/String;", "setTruncationText", "(Ljava/lang/String;)V", "widgetSize", "Lcom/amazon/music/views/library/views/TextContainerView$SIZE;", "applyStyle", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "computeFinalHeight", "width", "computeWidth", "maxSize", "createContainerLayout", "createLayout", "paint", "getWidgetSize", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onLayout", "changed", "left", "top", "right", "bottom", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "resetLayouts", "setContentMargins", "view", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Companion", "SIZE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextContainerView extends View {
    private Boolean alwaysDisplayTruncationText;
    private final RectF contentRect;
    private CharacterStyle footerStyleSpan;
    private Layout layout;
    private int maxLineCount;
    private CharSequence text;
    private final TextPaint textPaint;
    private CharSequence trimToken;
    private String truncationText;
    private SIZE widgetSize;
    private static final int DEFAULT_COLOR_SECONDARY = Color.parseColor("#99ffffff");
    private static final int DEFAULT_COLOR_ACCENT = Color.parseColor("#1ad2fb");

    /* JADX INFO: compiled from: TextContainerView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/views/TextContainerView$SIZE;", "", "(Ljava/lang/String;I)V", "SMALL", "MEDIUM", "LARGE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private enum SIZE {
        SMALL,
        MEDIUM,
        LARGE
    }

    /* JADX INFO: compiled from: TextContainerView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            iArr[ScreenSizeKey.LARGE.ordinal()] = 4;
            iArr[ScreenSizeKey.XLARGE.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextContainerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.maxLineCount = 4;
        this.truncationText = "...";
        this.trimToken = " ";
        this.alwaysDisplayTruncationText = Boolean.FALSE;
        this.contentRect = new RectF();
        float f = getResources().getDisplayMetrics().scaledDensity;
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(15 * f);
        int i = DEFAULT_COLOR_SECONDARY;
        textPaint.setColor(i);
        textPaint.linkColor = DEFAULT_COLOR_ACCENT;
        this.textPaint = textPaint;
        this.footerStyleSpan = new ForegroundColorSpan(i);
    }

    public final int getMaxLineCount() {
        return this.maxLineCount;
    }

    public final void setMaxLineCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Count cannot be less than 1");
        }
        if (this.maxLineCount != i) {
            this.maxLineCount = i;
            requestLayout();
        }
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final void setText(CharSequence charSequence) {
        if (Intrinsics.areEqual(this.text, charSequence)) {
            return;
        }
        this.text = charSequence;
        setContentDescription(charSequence);
        requestLayout();
    }

    public final String getTruncationText() {
        return this.truncationText;
    }

    public final void setTruncationText(String str) {
        if (Intrinsics.areEqual(this.truncationText, str)) {
            return;
        }
        this.truncationText = str;
        requestLayout();
    }

    public final CharSequence getTrimToken() {
        return this.trimToken;
    }

    public final void setTrimToken(CharSequence value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.trimToken, value)) {
            return;
        }
        this.trimToken = value;
        requestLayout();
    }

    public final Boolean getAlwaysDisplayTruncationText() {
        return this.alwaysDisplayTruncationText;
    }

    public final void setAlwaysDisplayTruncationText(Boolean bool) {
        this.alwaysDisplayTruncationText = bool;
    }

    private final void resetLayouts() {
        this.layout = createContainerLayout();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingStart = getPaddingStart() + getPaddingEnd();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(computeWidth(size) + paddingStart, size);
        } else if (mode != 1073741824) {
            size = Math.max(computeWidth(size) + paddingStart, getMinimumWidth());
        }
        int i = size - paddingStart;
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(computeFinalHeight(i) + paddingTop, size2);
        } else if (mode2 != 1073741824) {
            size2 = Math.max(computeFinalHeight(i) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.contentRect.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resetLayouts();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        canvas.save();
        try {
            canvas.clipRect(this.contentRect);
            RectF rectF = this.contentRect;
            canvas.translate(rectF.left, rectF.top);
            Layout layout = this.layout;
            if (layout != null) {
                layout.draw(canvas);
            }
        } finally {
            canvas.restore();
        }
    }

    public final void applyStyle(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        Intrinsics.checkNotNull(fontStyle);
        Integer color = styleSheet.getColor(ColorKey.COLOR_ACCENT);
        Intrinsics.checkNotNull(color);
        int iIntValue = color.intValue();
        TextPaint textPaint = this.textPaint;
        textPaint.setTypeface(fontStyle.getTypeface());
        textPaint.setTextSize(fontStyle.getFontSize().getTextSize());
        textPaint.setColor(fontStyle.getColor());
        textPaint.linkColor = iIntValue;
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        Intrinsics.checkNotNull(color2);
        this.footerStyleSpan = new ForegroundColorSpan(color2.intValue());
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        int iHorizontalInsetWidth = styleSheet.horizontalInsetWidth(context);
        setPadding(iHorizontalInsetWidth, 0, iHorizontalInsetWidth, 0);
        this.widgetSize = getWidgetSize(styleSheet);
        requestLayout();
    }

    private final Layout createContainerLayout() {
        CharSequence summaryWithTruncation;
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        SpannableString spannableString = new SpannableString(charSequence);
        int iWidth = (int) this.contentRect.width();
        Layout layoutCreateLayout = createLayout(spannableString, this.textPaint, iWidth);
        if (!(layoutCreateLayout.getLineCount() > this.maxLineCount) && Intrinsics.areEqual(this.alwaysDisplayTruncationText, Boolean.FALSE)) {
            return layoutCreateLayout;
        }
        int iMin = Math.min(this.maxLineCount - 1, layoutCreateLayout.getLineCount() - 1);
        int lineEnd = layoutCreateLayout.getLineEnd(iMin);
        float lineMax = layoutCreateLayout.getLineMax(iMin);
        CharSequence charSequenceSubSequence = spannableString.subSequence(0, lineEnd);
        Intrinsics.checkNotNullExpressionValue(charSequenceSubSequence, "spannable.subSequence(0, lastLineEnd)");
        CharSequence charSequenceTrimEnd = StringsKt__StringsKt.trimEnd(charSequenceSubSequence);
        float fMeasureText = this.textPaint.measureText(this.trimToken.toString()) + this.textPaint.measureText(this.truncationText);
        if (lineMax + fMeasureText < iWidth) {
            summaryWithTruncation = TextUtils.concat(charSequenceTrimEnd, this.trimToken, this.truncationText);
        } else {
            float fMeasureText2 = 0.0f;
            do {
                int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(charSequenceTrimEnd, String.valueOf(this.trimToken), 0, false, 6, (Object) null);
                fMeasureText2 += this.textPaint.measureText(charSequenceTrimEnd, iLastIndexOf$default, charSequenceTrimEnd.length());
                charSequenceTrimEnd = charSequenceTrimEnd.subSequence(0, iLastIndexOf$default);
            } while (fMeasureText2 < fMeasureText);
            summaryWithTruncation = TextUtils.concat(charSequenceTrimEnd, this.trimToken, this.truncationText);
        }
        Intrinsics.checkNotNullExpressionValue(summaryWithTruncation, "summaryWithTruncation");
        return createLayout(summaryWithTruncation, this.textPaint, iWidth);
    }

    private final int computeWidth(int maxSize) {
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        return createLayout(charSequence, this.textPaint, maxSize).getWidth();
    }

    private final int computeFinalHeight(int width) {
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        Layout layoutCreateLayout = createLayout(new SpannableString(charSequence), this.textPaint, width);
        int lineCount = layoutCreateLayout.getLineCount();
        int i = this.maxLineCount;
        if (lineCount > i) {
            return layoutCreateLayout.getLineBottom(i - 1);
        }
        return layoutCreateLayout.getHeight();
    }

    private final Layout createLayout(CharSequence text, TextPaint paint, int width) {
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(text, 0, text.length(), paint, width).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.5f).build();
        Intrinsics.checkNotNullExpressionValue(staticLayoutBuild, "obtain(text, 0, text.len…\n                .build()");
        return staticLayoutBuild;
    }

    private final SIZE getWidgetSize(StyleSheet styleSheet) {
        int i = WhenMappings.$EnumSwitchMapping$0[styleSheet.getWidthScreenSizeKey().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return SIZE.SMALL;
        }
        if (i == 4 || i == 5) {
            return SIZE.MEDIUM;
        }
        return SIZE.LARGE;
    }

    public final void setContentMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
            view.setLayoutParams(layoutParams);
        }
        if (left != null) {
            layoutParams.leftMargin = left.intValue();
        }
        if (right != null) {
            layoutParams.rightMargin = right.intValue();
        }
        if (top != null) {
            layoutParams.topMargin = top.intValue();
        }
        if (bottom == null) {
            return;
        }
        layoutParams.bottomMargin = bottom.intValue();
    }
}
