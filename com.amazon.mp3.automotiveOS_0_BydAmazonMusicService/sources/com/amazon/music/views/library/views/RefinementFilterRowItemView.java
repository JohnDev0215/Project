package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementFilterRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dR\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u000e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementFilterRowItemView;", "Lcom/amazon/music/views/library/views/RefinementRowItemView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "radioButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getRadioButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "radioButton$delegate", "Lkotlin/Lazy;", "subTitleTextView", "Landroid/widget/TextView;", "getSubTitleTextView", "()Landroid/widget/TextView;", "subTitleTextView$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "flipSelection", "", "initViewStyling", "", "setIsSelected", "isSelected", "setResourceId", "id", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementFilterRowItemView extends RefinementRowItemView {

    /* JADX INFO: renamed from: radioButton$delegate, reason: from kotlin metadata */
    private final Lazy radioButton;

    /* JADX INFO: renamed from: subTitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subTitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementFilterRowItemView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementFilterRowItemView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subTitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementFilterRowItemView$subTitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.sub_title);
            }
        });
        this.radioButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementFilterRowItemView$radioButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.radio_button);
            }
        });
        initViewStyling();
        setOrientation(0);
    }

    @Override // com.amazon.music.views.library.views.RefinementRowItemView
    public TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.RefinementRowItemView
    public TextView getSubTitleTextView() {
        Object value = this.subTitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subTitleTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getRadioButton() {
        Object value = this.radioButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-radioButton>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.views.RefinementRowItemView
    public void setIsSelected(boolean isSelected) {
        getRadioButton().setActive(isSelected);
    }

    public final boolean flipSelection() {
        boolean isActive = getRadioButton().getIsActive();
        if (!isActive) {
            setIsSelected(true);
            return true;
        }
        setIsSelected(isActive);
        return isActive;
    }

    public final void setResourceId(int id) {
        setId(id);
    }

    protected void initViewStyling() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R$layout.dmmviewlibrary_refinement_filter_row_item, this);
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubTitleTextView(), fontStyle2);
        }
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.MINI);
        if (spacerInPixels != null) {
            int iIntValue = spacerInPixels.intValue();
            ViewGroup.LayoutParams layoutParams = viewInflate.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin = iIntValue;
            layoutParams2.bottomMargin = iIntValue;
            viewInflate.requestLayout();
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_radiouncheck);
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_radiocheck);
        ButtonStyle iconStyle = getStyleSheet().getIconStyle(IconStyleKey.ACCENT);
        BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY);
        if (drawable != null && iconBuilder != null) {
            iconBuilder.withIcon(drawable);
        }
        if (drawable2 != null && iconBuilder != null) {
            iconBuilder.withActiveIcon(drawable2);
        }
        if (iconStyle != null && iconBuilder != null) {
            iconBuilder.withActiveStyle(iconStyle);
        }
        if (iconBuilder != null) {
            iconBuilder.applyStyle(getRadioButton());
        }
        getRadioButton().getButton().setClickable(false);
    }
}
