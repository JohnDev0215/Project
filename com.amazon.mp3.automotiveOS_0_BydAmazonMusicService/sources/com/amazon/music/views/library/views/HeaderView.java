package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0014\u0010+\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u001e\u0010,\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010-\u001a\u00020\bH\u0002J\b\u0010.\u001a\u00020'H\u0002J;\u0010/\u001a\u00020'2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101¢\u0006\u0002\u00105J\u001a\u00106\u001a\u00020'2\u0006\u00107\u001a\u0002082\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0019J\u0018\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0019J;\u0010<\u001a\u00020'2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101¢\u0006\u0002\u00105JE\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u00020?2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101H\u0002¢\u0006\u0002\u0010@JE\u0010A\u001a\u00020'2\u0006\u0010>\u001a\u00020?2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101H\u0002¢\u0006\u0002\u0010@J\u000e\u0010B\u001a\u00020'2\u0006\u0010C\u001a\u000208J;\u0010D\u001a\u00020'2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101¢\u0006\u0002\u00105J\u000e\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u000208J;\u0010G\u001a\u00020'2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000101¢\u0006\u0002\u00105J\u000e\u0010H\u001a\u00020'2\u0006\u0010I\u001a\u000208R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u000e\u0010\nR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001c\u0010\u0013R\u001b\u0010\u001e\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\f\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b$\u0010\u0013¨\u0006J"}, d2 = {"Lcom/amazon/music/views/library/views/HeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "buttonRowLayout1", "Landroid/widget/LinearLayout;", "getButtonRowLayout1", "()Landroid/widget/LinearLayout;", "buttonRowLayout1$delegate", "Lkotlin/Lazy;", "buttonRowLayout2", "getButtonRowLayout2", "buttonRowLayout2$delegate", "headerTextView", "Landroid/widget/TextView;", "getHeaderTextView", "()Landroid/widget/TextView;", "headerTextView$delegate", "labelTextView", "getLabelTextView", "labelTextView$delegate", "maxHeaderFontStyleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "minHeaderFontStyleKey", "primaryTextView", "getPrimaryTextView", "primaryTextView$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "tertiaryTextView", "getTertiaryTextView", "tertiaryTextView$delegate", "addButtonRow1", "", "buttons", "", "Lcom/amazon/ui/foundations/views/BaseButton;", "addButtonRow2", "addButtons", "layout", "initViewStyling", "setHeaderTextPadding", "left", "", "top", "right", "bottom", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setHeadline", "headline", "", "fontStyleKey", "setLabel", ContextMappingConstants.LABEL, "setLabelPadding", "setMargins", "view", "Landroid/view/View;", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setPadding", "setPrimaryText", "primary", "setPrimaryTextPadding", "setSecondaryText", "secondary", "setSecondaryTextPadding", "setTertiaryText", "tertiary", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HeaderView extends ConstraintLayout {

    /* JADX INFO: renamed from: buttonRowLayout1$delegate, reason: from kotlin metadata */
    private final Lazy buttonRowLayout1;

    /* JADX INFO: renamed from: buttonRowLayout2$delegate, reason: from kotlin metadata */
    private final Lazy buttonRowLayout2;

    /* JADX INFO: renamed from: headerTextView$delegate, reason: from kotlin metadata */
    private final Lazy headerTextView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private final FontStyleKey maxHeaderFontStyleKey;
    private final FontStyleKey minHeaderFontStyleKey;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HeaderView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.headerTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HeaderView$headerTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.header_text);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HeaderView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HeaderView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HeaderView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.Subtitle);
            }
        });
        this.buttonRowLayout1 = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.HeaderView$buttonRowLayout1$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.button_row_1);
            }
        });
        this.buttonRowLayout2 = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.HeaderView$buttonRowLayout2$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.button_row_2);
            }
        });
        this.minHeaderFontStyleKey = FontStyleKey.HEADLINE_3;
        this.maxHeaderFontStyleKey = FontStyleKey.HEADLINE_2;
        View.inflate(context, R$layout.dmmviewlibrary_header_layout, this);
        initViewStyling();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    private final TextView getHeaderTextView() {
        Object value = this.headerTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headerTextView>(...)");
        return (TextView) value;
    }

    private final TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getTertiaryTextView() {
        Object value = this.tertiaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tertiaryTextView>(...)");
        return (TextView) value;
    }

    private final LinearLayout getButtonRowLayout1() {
        Object value = this.buttonRowLayout1.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-buttonRowLayout1>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getButtonRowLayout2() {
        Object value = this.buttonRowLayout2.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-buttonRowLayout2>(...)");
        return (LinearLayout) value;
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSecondaryTextView(), fontStyle3);
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle4 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTertiaryTextView(), fontStyle4);
    }

    public final void setLabel(String label, FontStyleKey fontStyleKey) {
        FontStyle fontStyle;
        Intrinsics.checkNotNullParameter(label, "label");
        getLabelTextView().setText(label);
        getLabelTextView().setVisibility(!TextUtils.isEmpty(label) ? 0 : 8);
        if (fontStyleKey == null || (fontStyle = getStyleSheet().getFontStyle(fontStyleKey)) == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
    }

    public final void setHeadline(String headline, FontStyleKey fontStyleKey) {
        int i;
        Intrinsics.checkNotNullParameter(headline, "headline");
        getHeaderTextView().setText(headline);
        TextView headerTextView = getHeaderTextView();
        if (TextUtils.isEmpty(headline)) {
            i = 8;
        } else {
            if (fontStyleKey == null) {
                fontStyleKey = this.maxHeaderFontStyleKey;
            }
            FontStyle fontStyle = this.styleSheet.getFontStyle(fontStyleKey);
            if (fontStyle != null) {
                FontUtil fontUtil = FontUtil.INSTANCE;
                fontUtil.applyFontStyle(getHeaderTextView(), fontStyle);
                fontUtil.applyAutoSize(getHeaderTextView(), getStyleSheet().getTextSizeListInRange(this.minHeaderFontStyleKey, fontStyleKey));
            }
            i = 0;
        }
        headerTextView.setVisibility(i);
    }

    public final void setPrimaryText(String primary) {
        Intrinsics.checkNotNullParameter(primary, "primary");
        getPrimaryTextView().setText(primary);
        getPrimaryTextView().setVisibility(!TextUtils.isEmpty(primary) ? 0 : 8);
    }

    public final void setSecondaryText(String secondary) {
        Intrinsics.checkNotNullParameter(secondary, "secondary");
        getSecondaryTextView().setText(secondary);
        getSecondaryTextView().setVisibility(!TextUtils.isEmpty(secondary) ? 0 : 8);
    }

    public final void setTertiaryText(String tertiary) {
        Intrinsics.checkNotNullParameter(tertiary, "tertiary");
        getTertiaryTextView().setText(tertiary);
        getTertiaryTextView().setVisibility(!TextUtils.isEmpty(tertiary) ? 0 : 8);
    }

    public final void addButtonRow1(List<? extends BaseButton> buttons) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        addButtons(buttons, getButtonRowLayout1());
    }

    public final void addButtonRow2(List<? extends BaseButton> buttons) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        addButtons(buttons, getButtonRowLayout2());
    }

    private final void addButtons(List<? extends BaseButton> buttons, LinearLayout layout) {
        layout.removeAllViews();
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        for (BaseButton baseButton : buttons) {
            setMargins(baseButton, 0, Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2), 0);
            layout.addView(baseButton);
        }
        layout.setVisibility(0);
    }

    public final void setLabelPadding(Integer left, Integer top, Integer right, Integer bottom) {
        setPadding(getLabelTextView(), left, top, right, bottom);
    }

    public final void setHeaderTextPadding(Integer left, Integer top, Integer right, Integer bottom) {
        setPadding(getHeaderTextView(), left, top, right, bottom);
    }

    public final void setPrimaryTextPadding(Integer left, Integer top, Integer right, Integer bottom) {
        setPadding(getPrimaryTextView(), left, top, right, bottom);
    }

    public final void setSecondaryTextPadding(Integer left, Integer top, Integer right, Integer bottom) {
        setPadding(getSecondaryTextView(), left, top, right, bottom);
    }

    private final void setPadding(View view, Integer left, Integer top, Integer right, Integer bottom) {
        view.setPadding(left == null ? 0 : left.intValue(), top == null ? 0 : top.intValue(), right == null ? 0 : right.intValue(), bottom != null ? bottom.intValue() : 0);
    }

    private final void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
