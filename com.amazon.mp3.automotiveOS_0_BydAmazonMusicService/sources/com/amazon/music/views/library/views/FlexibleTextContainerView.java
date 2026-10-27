package com.amazon.music.views.library.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.core.app.NotificationCompat;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: FlexibleTextContainerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 i2\u00020\u0001:\u0002ijB\u0011\b\u0016\u0012\u0006\u0010f\u001a\u00020e¢\u0006\u0004\bg\u0010hJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0014J0\u0010\u0012\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0017J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0006\u0010\u0019\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001aJ\u0018\u0010 \u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002J\u0014\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\f0!H\u0002J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\u0005H\u0002J\b\u0010&\u001a\u00020\u0002H\u0002J\u0018\u0010)\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H\u0002J \u0010.\u001a\u00020\"2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\u0006\u0010'\u001a\u00020\u0002H\u0002R*\u00100\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R*\u00107\u001a\u0002062\u0006\u0010/\u001a\u0002068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R*\u0010=\u001a\u0002062\u0006\u0010/\u001a\u0002068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b=\u00108\u001a\u0004\b>\u0010:\"\u0004\b?\u0010<R.\u0010+\u001a\u0004\u0018\u00010*2\b\u0010/\u001a\u0004\u0018\u00010*8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DRE\u0010K\u001a%\u0012\u0013\u0012\u00110F¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\f\u0018\u00010Ej\u0004\u0018\u0001`J8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010W\u001a\u00020V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010ZR\u0016\u0010\\\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u00101R\u0018\u0010]\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010_\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010^R$\u0010(\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u00103\"\u0004\ba\u00105R\u0016\u0010d\u001a\u0004\u0018\u00010\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bb\u0010c¨\u0006k"}, d2 = {"Lcom/amazon/music/views/library/views/FlexibleTextContainerView;", "Landroid/view/View;", "", "widthMeasureSpec", "heightMeasureSpec", "", "onMeasure", "w", "h", "oldw", "oldh", "onSizeChanged", "", "changed", "left", "top", "right", "bottom", "onLayout", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "onTouchEvent", "Landroid/graphics/Canvas;", "canvas", "onDraw", "toggleState", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "applyStyle", "", "eventX", "eventY", "handleContentTap", "Lkotlin/Pair;", "Landroid/text/Layout;", "createCollapsedLayout", "createExpandedLayout", "resetLayouts", "computeWidth", "width", "state", "computeFinalHeight", "", "text", "Landroid/text/TextPaint;", "paint", "createLayout", "value", "maxCollapsedLineCount", "I", "getMaxCollapsedLineCount", "()I", "setMaxCollapsedLineCount", "(I)V", "", "collapseLabelText", "Ljava/lang/String;", "getCollapseLabelText", "()Ljava/lang/String;", "setCollapseLabelText", "(Ljava/lang/String;)V", "expandLabelText", "getExpandLabelText", "setExpandLabelText", "Ljava/lang/CharSequence;", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "Lkotlin/Function1;", "Landroid/text/style/ClickableSpan;", "Lkotlin/ParameterName;", "name", "span", "Lcom/amazon/music/views/library/views/OnClickableSpanListener;", "onClickableSpanListener", "Lkotlin/jvm/functions/Function1;", "getOnClickableSpanListener", "()Lkotlin/jvm/functions/Function1;", "setOnClickableSpanListener", "(Lkotlin/jvm/functions/Function1;)V", "Landroid/graphics/RectF;", "contentRect", "Landroid/graphics/RectF;", "textPaint", "Landroid/text/TextPaint;", "Landroid/text/style/CharacterStyle;", "toggleSpan", "Landroid/text/style/CharacterStyle;", "isExpandable", "Z", "isAnimating", "currentState", "collapsedLayout", "Landroid/text/Layout;", "expandedLayout", "getState", "setState", "getActiveLayout", "()Landroid/text/Layout;", "activeLayout", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "StateToggleAnimation", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FlexibleTextContainerView extends View {
    private String collapseLabelText;
    private Layout collapsedLayout;
    private final RectF contentRect;
    private int currentState;
    private String expandLabelText;
    private Layout expandedLayout;
    private boolean isAnimating;
    private boolean isExpandable;
    private int maxCollapsedLineCount;
    private Function1<? super ClickableSpan, Boolean> onClickableSpanListener;
    private CharSequence text;
    private final TextPaint textPaint;
    private CharacterStyle toggleSpan;
    private static final int DEFAULT_COLOR_PRIMARY = Color.parseColor("#ffffff");
    private static final int DEFAULT_COLOR_SECONDARY = Color.parseColor("#99ffffff");
    private static final int DEFAULT_COLOR_ACCENT = Color.parseColor("#1ad2fb");
    private static final int DEFAULT_FONT_SIZE_SP = 15;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlexibleTextContainerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.maxCollapsedLineCount = 6;
        this.collapseLabelText = "Read less";
        this.expandLabelText = "Read more";
        this.contentRect = new RectF();
        float f = getResources().getDisplayMetrics().scaledDensity;
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(DEFAULT_FONT_SIZE_SP * f);
        textPaint.setColor(DEFAULT_COLOR_SECONDARY);
        textPaint.linkColor = DEFAULT_COLOR_ACCENT;
        this.textPaint = textPaint;
        this.toggleSpan = new ForegroundColorSpan(DEFAULT_COLOR_PRIMARY);
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FlexibleTextContainerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FlexibleTextContainerView.m2416_init_$lambda1(this.f$0, view);
            }
        });
    }

    public final int getMaxCollapsedLineCount() {
        return this.maxCollapsedLineCount;
    }

    public final void setMaxCollapsedLineCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Count cannot be less than 1");
        }
        if (this.maxCollapsedLineCount != i) {
            this.maxCollapsedLineCount = i;
            requestLayout();
        }
    }

    public final String getCollapseLabelText() {
        return this.collapseLabelText;
    }

    public final void setCollapseLabelText(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.collapseLabelText, value)) {
            return;
        }
        this.collapseLabelText = value;
        requestLayout();
    }

    public final String getExpandLabelText() {
        return this.expandLabelText;
    }

    public final void setExpandLabelText(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.expandLabelText, value)) {
            return;
        }
        this.expandLabelText = value;
        requestLayout();
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

    /* JADX INFO: renamed from: getState, reason: from getter */
    public final int getCurrentState() {
        return this.currentState;
    }

    public final void setState(int i) {
        if (!ArraysKt___ArraysKt.contains(new Integer[]{1, 0}, Integer.valueOf(i))) {
            throw new IllegalArgumentException(i + " is not a valid state");
        }
        if (i != this.currentState) {
            if (i != 1 || this.isExpandable) {
                this.currentState = i;
                requestLayout();
            }
        }
    }

    public final Function1<ClickableSpan, Boolean> getOnClickableSpanListener() {
        return this.onClickableSpanListener;
    }

    public final void setOnClickableSpanListener(Function1<? super ClickableSpan, Boolean> function1) {
        this.onClickableSpanListener = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m2416_init_$lambda1(FlexibleTextContainerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleState();
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
            size = Math.min(computeWidth() + paddingStart, size);
        } else if (mode != 1073741824) {
            size = Math.max(computeWidth() + paddingStart, getMinimumWidth());
        }
        int i = size - paddingStart;
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(computeFinalHeight(i, this.currentState) + paddingTop, size2);
        } else if (mode2 != 1073741824) {
            size2 = Math.max(computeFinalHeight(i, this.currentState) + paddingTop, getMinimumHeight());
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
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0 && handleContentTap(event.getX(), event.getY())) {
            return true;
        }
        return super.onTouchEvent(event);
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
            Layout activeLayout = getActiveLayout();
            if (activeLayout != null) {
                activeLayout.draw(canvas);
            }
        } finally {
            canvas.restore();
        }
    }

    public final void toggleState() {
        if (this.isExpandable) {
            this.isAnimating = true;
            this.currentState = this.currentState != 0 ? 0 : 1;
            clearAnimation();
            startAnimation(new StateToggleAnimation(this));
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
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        Intrinsics.checkNotNull(color2);
        this.toggleSpan = new ForegroundColorSpan(color2.intValue());
        requestLayout();
    }

    private final boolean handleContentTap(float eventX, float eventY) {
        Layout activeLayout;
        if (!this.contentRect.contains(eventX, eventY) || (activeLayout = getActiveLayout()) == null) {
            return false;
        }
        CharSequence text = activeLayout.getText();
        Spannable spannable = text instanceof Spannable ? (Spannable) text : null;
        if (spannable == null) {
            return false;
        }
        int offsetForHorizontal = activeLayout.getOffsetForHorizontal(activeLayout.getLineForVertical((int) (eventY - getPaddingTop())), eventX - getPaddingLeft());
        ClickableSpan[] spans = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
        Intrinsics.checkNotNullExpressionValue(spans, "spans");
        ClickableSpan clickableSpan = (ClickableSpan) ArraysKt___ArraysKt.firstOrNull(spans);
        if (clickableSpan == null) {
            return false;
        }
        Function1<ClickableSpan, Boolean> onClickableSpanListener = getOnClickableSpanListener();
        Boolean boolInvoke = onClickableSpanListener != null ? onClickableSpanListener.invoke(clickableSpan) : null;
        if (boolInvoke == null) {
            return false;
        }
        return boolInvoke.booleanValue();
    }

    private final Layout getActiveLayout() {
        if (this.currentState == 0 && !this.isAnimating) {
            return this.collapsedLayout;
        }
        return this.expandedLayout;
    }

    private final Pair<Layout, Boolean> createCollapsedLayout() {
        CharSequence charSequenceConcat;
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        SpannableString spannableString = new SpannableString(charSequence);
        int iWidth = (int) this.contentRect.width();
        Layout layoutCreateLayout = createLayout(spannableString, this.textPaint, iWidth);
        int lineCount = layoutCreateLayout.getLineCount();
        int i = this.maxCollapsedLineCount;
        boolean z = lineCount > i;
        if (!z) {
            return new Pair<>(layoutCreateLayout, Boolean.valueOf(z));
        }
        int lineStart = layoutCreateLayout.getLineStart(i - 1);
        int lineEnd = layoutCreateLayout.getLineEnd(this.maxCollapsedLineCount - 1);
        float lineMax = layoutCreateLayout.getLineMax(this.maxCollapsedLineCount - 1);
        CharSequence charSequenceSubSequence = spannableString.subSequence(0, lineEnd);
        Intrinsics.checkNotNullExpressionValue(charSequenceSubSequence, "collapsedSpannable.subSequence(0, lastLineEnd)");
        CharSequence charSequenceTrimEnd = StringsKt__StringsKt.trimEnd(charSequenceSubSequence);
        String strStringPlus = Intrinsics.stringPlus(" ", this.expandLabelText);
        float fMeasureText = this.textPaint.measureText(strStringPlus);
        if (lineMax + fMeasureText < iWidth) {
            charSequenceConcat = TextUtils.concat(charSequenceTrimEnd, strStringPlus);
        } else {
            int length = charSequenceTrimEnd.length() - 1;
            do {
                if (charSequenceTrimEnd.charAt(length) == ' ' && this.textPaint.measureText(charSequenceTrimEnd, length, charSequenceTrimEnd.length()) > fMeasureText) {
                    break;
                }
                length--;
            } while (length >= lineStart);
            charSequenceConcat = TextUtils.concat(charSequenceTrimEnd.subSequence(0, length), strStringPlus);
        }
        SpannableString spannableString2 = new SpannableString(charSequenceConcat);
        spannableString2.setSpan(this.toggleSpan, spannableString2.length() - strStringPlus.length(), spannableString2.length(), 17);
        return new Pair<>(createLayout(spannableString2, this.textPaint, iWidth), Boolean.valueOf(z));
    }

    private final Layout createExpandedLayout() {
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        int iWidth = (int) this.contentRect.width();
        SpannableString spannableString = new SpannableString(TextUtils.concat(charSequence, " ", this.collapseLabelText));
        spannableString.setSpan(this.toggleSpan, spannableString.length() - getCollapseLabelText().length(), spannableString.length(), 17);
        return createLayout(spannableString, this.textPaint, iWidth);
    }

    private final void resetLayouts() {
        Pair<Layout, Boolean> pairCreateCollapsedLayout = createCollapsedLayout();
        Layout layoutComponent1 = pairCreateCollapsedLayout.component1();
        boolean zBooleanValue = pairCreateCollapsedLayout.component2().booleanValue();
        this.collapsedLayout = layoutComponent1;
        this.isExpandable = zBooleanValue;
        if (zBooleanValue) {
            layoutComponent1 = createExpandedLayout();
        }
        this.expandedLayout = layoutComponent1;
    }

    private final int computeWidth() {
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        return createLayout(charSequence, this.textPaint, (int) this.contentRect.width()).getWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int computeFinalHeight(int width, int state) {
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            charSequence = "";
        }
        if (state == 0) {
            Layout layoutCreateLayout = createLayout(new SpannableString(charSequence), this.textPaint, width);
            int lineCount = layoutCreateLayout.getLineCount();
            int i = this.maxCollapsedLineCount;
            if (lineCount > i) {
                return layoutCreateLayout.getLineBottom(i - 1);
            }
            return layoutCreateLayout.getHeight();
        }
        return createLayout(new SpannableString(TextUtils.concat(charSequence, " ", this.collapseLabelText)), this.textPaint, width).getHeight();
    }

    private final Layout createLayout(CharSequence text, TextPaint paint, int width) {
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(text, 0, text.length(), paint, width).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
        Intrinsics.checkNotNullExpressionValue(staticLayoutBuild, "{\n            StaticLayo…       .build()\n        }");
        return staticLayoutBuild;
    }

    /* JADX INFO: compiled from: FlexibleTextContainerView.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/views/FlexibleTextContainerView$StateToggleAnimation;", "Landroid/view/animation/Animation;", "(Lcom/amazon/music/views/library/views/FlexibleTextContainerView;)V", "diff", "", "finalHeight", "initialHeight", "applyTransformation", "", "interpolatedTime", "", "t", "Landroid/view/animation/Transformation;", "willChangeBounds", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class StateToggleAnimation extends Animation {
        private final int diff;
        private final int finalHeight;
        private final int initialHeight;
        final /* synthetic */ FlexibleTextContainerView this$0;

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }

        public StateToggleAnimation(FlexibleTextContainerView this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            int measuredHeight = this$0.getMeasuredHeight();
            this.initialHeight = measuredHeight;
            int iComputeFinalHeight = this$0.computeFinalHeight((int) this$0.contentRect.width(), this$0.currentState) + this$0.getPaddingTop() + this$0.getPaddingBottom();
            this.finalHeight = iComputeFinalHeight;
            this.diff = iComputeFinalHeight - measuredHeight;
            setDuration(200L);
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int i;
            Intrinsics.checkNotNullParameter(t, "t");
            ViewGroup.LayoutParams layoutParams = this.this$0.getLayoutParams();
            if (interpolatedTime == 1.0f) {
                this.this$0.isAnimating = false;
                i = -2;
            } else {
                i = ((int) (this.diff * interpolatedTime)) + this.initialHeight;
            }
            layoutParams.height = i;
            this.this$0.requestLayout();
        }
    }
}
