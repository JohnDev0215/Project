package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageHeaderHeadlineTextView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0019\b\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0014J\u001e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "Landroid/widget/TextView;", "", "widthLimit", "", "adjustFontSize", "textSize", "", "isEllipsize", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "minimumFontStyleKey", "maximumFontStyleKey", "initStyleSheet", "Lcom/amazon/ui/foundations/styles/FontSize;", "minFontSize", "Lcom/amazon/ui/foundations/styles/FontSize;", "maxFontSize", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PageHeaderHeadlineTextView extends TextView {
    private FontSize maxFontSize;
    private FontSize minFontSize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PageHeaderHeadlineTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        setMaxLines(2);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        adjustFontSize((getMeasuredWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight());
    }

    public final void initStyleSheet(StyleSheet styleSheet, FontStyleKey minimumFontStyleKey, FontStyleKey maximumFontStyleKey) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(minimumFontStyleKey, "minimumFontStyleKey");
        Intrinsics.checkNotNullParameter(maximumFontStyleKey, "maximumFontStyleKey");
        FontStyle fontStyle = styleSheet.getFontStyle(minimumFontStyleKey);
        this.minFontSize = fontStyle == null ? null : fontStyle.getFontSize();
        FontStyle fontStyle2 = styleSheet.getFontStyle(maximumFontStyleKey);
        this.maxFontSize = fontStyle2 != null ? fontStyle2.getFontSize() : null;
    }

    private final void adjustFontSize(int widthLimit) {
        Integer lineHeight;
        FontSize fontSize = this.minFontSize;
        Integer numValueOf = fontSize == null ? null : Integer.valueOf(fontSize.getTextSize());
        if (numValueOf == null) {
            return;
        }
        int iIntValue = numValueOf.intValue();
        FontSize fontSize2 = this.maxFontSize;
        Integer numValueOf2 = fontSize2 != null ? Integer.valueOf(fontSize2.getTextSize()) : null;
        if (numValueOf2 == null) {
            return;
        }
        int iMin = Math.min(numValueOf2.intValue(), (int) getTextSize());
        if (getTextSize() <= iIntValue || !isEllipsize(widthLimit, (int) getTextSize())) {
            return;
        }
        int i = iMin;
        int i2 = iIntValue;
        while (iIntValue <= i) {
            int i3 = (iIntValue + i) / 2;
            if (isEllipsize(widthLimit, i3)) {
                i = i3 - 1;
            } else {
                iIntValue = i3 + 1;
                i2 = i3;
            }
        }
        FontSize fontSize3 = this.minFontSize;
        int iIntValue2 = 0;
        if (fontSize3 != null && (lineHeight = fontSize3.getLineHeight()) != null) {
            iIntValue2 = lineHeight.intValue();
        }
        FontUtil.INSTANCE.applyFontSize(this, new FontSize(i2, Integer.valueOf(Math.max(iIntValue2, i2))));
        requestLayout();
    }

    private final boolean isEllipsize(int widthLimit, int textSize) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(textSize);
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(getText(), 0, getText().length(), textPaint, widthLimit).build();
        Intrinsics.checkNotNullExpressionValue(staticLayoutBuild, "{\n                    St…build()\n                }");
        return staticLayoutBuild.getLineCount() > 2;
    }
}
