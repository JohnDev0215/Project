package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001!B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0017\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJQ\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0017\u0010\u001c\u001a\u00020\n2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\""}, d2 = {"Lcom/amazon/music/views/library/views/TextRowItemView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "initPaddings", "", "initViewStyling", "setCenter", "shouldCenter", "", "(Ljava/lang/Boolean;)V", "setContentMargins", "view", "Landroid/view/View;", "left", "", "top", "right", "bottom", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "setFontStyle", "fontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "setMargin", "applyLeftMargin", "setTextStyle", "textStyle", "Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;", "TextStyle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextRowItemView extends TextView {
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: TextRowItemView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextStyle.values().length];
            iArr[TextStyle.DISC_HEADER.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextRowItemView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        initViewStyling();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX INFO: compiled from: TextRowItemView.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B3\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bj\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;", "", "fontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "topPadding", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "bottomPadding", "maxLines", "", "(Ljava/lang/String;ILcom/amazon/music/views/library/styles/keys/FontStyleKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;Lcom/amazon/music/views/library/styles/keys/SpacerKey;Ljava/lang/Integer;)V", "getBottomPadding", "()Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "getFontStyle", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getMaxLines", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTopPadding", "HEADER", "DISC_HEADER", "COPYRIGHT_TEXT", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum TextStyle {
        HEADER(FontStyleKey.HEADLINE_4, SpacerKey.LARGE, SpacerKey.SMALL, null, 8, null),
        DISC_HEADER(FontStyleKey.SECONDARY, SpacerKey.EPIC, null, null, 8, null),
        COPYRIGHT_TEXT(FontStyleKey.TERTIARY, SpacerKey.GIANT, null, null);

        private final SpacerKey bottomPadding;
        private final FontStyleKey fontStyle;
        private final Integer maxLines;
        private final SpacerKey topPadding;

        TextStyle(FontStyleKey fontStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2, Integer num) {
            this.fontStyle = fontStyleKey;
            this.topPadding = spacerKey;
            this.bottomPadding = spacerKey2;
            this.maxLines = num;
        }

        /* synthetic */ TextStyle(FontStyleKey fontStyleKey, SpacerKey spacerKey, SpacerKey spacerKey2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(fontStyleKey, (i & 2) != 0 ? null : spacerKey, (i & 4) != 0 ? null : spacerKey2, (i & 8) != 0 ? 2 : num);
        }

        public final SpacerKey getBottomPadding() {
            return this.bottomPadding;
        }

        public final FontStyleKey getFontStyle() {
            return this.fontStyle;
        }

        public final Integer getMaxLines() {
            return this.maxLines;
        }

        public final SpacerKey getTopPadding() {
            return this.topPadding;
        }
    }

    public final void setFontStyle(FontStyle fontStyle) {
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        FontUtil.INSTANCE.applyFontStyle(this, fontStyle);
    }

    public final void setTextStyle(TextStyle textStyle) {
        Intrinsics.checkNotNullParameter(textStyle, "textStyle");
        initPaddings();
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        setPadding(getPaddingLeft(), iIntValue, getPaddingRight(), iIntValue2);
        if (WhenMappings.$EnumSwitchMapping$0[textStyle.ordinal()] == 1) {
            setPadding(0, iIntValue, getPaddingRight(), iIntValue2);
        } else {
            setPadding(getPaddingLeft(), iIntValue, getPaddingRight(), iIntValue2);
        }
        FontStyle fontStyle = this.styleSheet.getFontStyle(textStyle.getFontStyle());
        if (fontStyle != null) {
            setFontStyle(fontStyle);
        }
        Integer maxLines = textStyle.getMaxLines();
        setMaxLines(maxLines == null ? Integer.MAX_VALUE : maxLines.intValue());
    }

    public final void setMargin(Boolean applyLeftMargin) {
        if (Intrinsics.areEqual(applyLeftMargin, Boolean.TRUE)) {
            StyleSheet styleSheet = this.styleSheet;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            GridSize gridSize = styleSheet.getGridSize(context);
            int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
            StyleSheet styleSheet2 = this.styleSheet;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            GridSize gridSize2 = styleSheet2.getGridSize(context2);
            setContentMargins$default(this, this, Integer.valueOf(marginSpace + (gridSize2 != null ? gridSize2.getColumnSpace() : 0)), null, null, null, null, 60, null);
        }
    }

    public final void setCenter(Boolean shouldCenter) {
        Boolean bool = Boolean.TRUE;
        if (Intrinsics.areEqual(shouldCenter, bool)) {
            setGravity(1);
            setContentMargins$default(this, this, null, null, null, null, bool, 30, null);
        } else {
            setGravity(8388627);
            setContentMargins$default(this, this, null, null, null, null, null, 62, null);
        }
    }

    private final void initViewStyling() {
        setGravity(8388627);
        setTextStyle(TextStyle.HEADER);
    }

    private final void initPaddings() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        StyleSheet styleSheet2 = this.styleSheet;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        GridSize gridSize2 = styleSheet2.getGridSize(context2);
        int columnSpace = marginSpace + (gridSize2 != null ? gridSize2.getColumnSpace() : 0);
        setPadding(columnSpace, getPaddingTop(), columnSpace, getPaddingBottom());
    }

    static /* synthetic */ void setContentMargins$default(TextRowItemView textRowItemView, View view, Integer num, Integer num2, Integer num3, Integer num4, Boolean bool, int i, Object obj) {
        textRowItemView.setContentMargins(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) == 0 ? num4 : null, (i & 32) != 0 ? Boolean.FALSE : bool);
    }

    private final void setContentMargins(View view, Integer left, Integer top, Integer right, Integer bottom, Boolean shouldCenter) {
        FrameLayout.LayoutParams layoutParams;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            if (Intrinsics.areEqual(shouldCenter, Boolean.TRUE)) {
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
                layoutParams.gravity = 1;
            } else {
                layoutParams = new FrameLayout.LayoutParams(-2, -2);
            }
            marginLayoutParams = layoutParams;
            view.setLayoutParams(marginLayoutParams);
        }
        if (left != null) {
            marginLayoutParams.leftMargin = left.intValue();
        }
        if (right != null) {
            marginLayoutParams.rightMargin = right.intValue();
        }
        if (top != null) {
            marginLayoutParams.topMargin = top.intValue();
        }
        if (bottom == null) {
            return;
        }
        marginLayoutParams.bottomMargin = bottom.intValue();
    }
}
