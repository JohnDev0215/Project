package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementFilterListRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR#\u0010\u000e\u001a\n \t*\u0004\u0018\u00010\b0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u000f\u0010\u000bR\u001b\u0010\u0011\u001a\u00020\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0017\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementFilterListRowItemView;", "Lcom/amazon/music/views/library/views/RefinementRowItemView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "blueCircleButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "kotlin.jvm.PlatformType", "getBlueCircleButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "blueCircleButton$delegate", "Lkotlin/Lazy;", "rightChevronButton", "getRightChevronButton", "rightChevronButton$delegate", "subTitleTextView", "Landroid/widget/TextView;", "getSubTitleTextView", "()Landroid/widget/TextView;", "subTitleTextView$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "initViewStyling", "", "setIsSelected", "isSelected", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementFilterListRowItemView extends RefinementRowItemView {

    /* JADX INFO: renamed from: blueCircleButton$delegate, reason: from kotlin metadata */
    private final Lazy blueCircleButton;

    /* JADX INFO: renamed from: rightChevronButton$delegate, reason: from kotlin metadata */
    private final Lazy rightChevronButton;

    /* JADX INFO: renamed from: subTitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subTitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementFilterListRowItemView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementFilterListRowItemView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subTitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.RefinementFilterListRowItemView$subTitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.sub_title);
            }
        });
        this.blueCircleButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementFilterListRowItemView$blueCircleButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.blue_circle);
            }
        });
        this.rightChevronButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementFilterListRowItemView$rightChevronButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.right_chevron);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_refinement_filter_list_row_item, this);
        initViewStyling();
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

    private final BaseButton getBlueCircleButton() {
        return (BaseButton) this.blueCircleButton.getValue();
    }

    private final BaseButton getRightChevronButton() {
        return (BaseButton) this.rightChevronButton.getValue();
    }

    @Override // com.amazon.music.views.library.views.RefinementRowItemView
    public void setIsSelected(boolean isSelected) {
        getBlueCircleButton().setVisibility(isSelected ? 0 : 4);
    }

    protected void initViewStyling() {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder iconBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_circle);
        if (drawable != null && (iconBuilder2 = getStyleSheet().getIconBuilder(IconSizeKey.TINY, IconStyleKey.ACCENT)) != null && (styleBuilderWithIcon2 = iconBuilder2.withIcon(drawable)) != null) {
            BaseButton blueCircleButton = getBlueCircleButton();
            Intrinsics.checkNotNullExpressionValue(blueCircleButton, "blueCircleButton");
            styleBuilderWithIcon2.applyStyle(blueCircleButton);
        }
        getBlueCircleButton().getButton().setClickable(false);
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_chevron_caretright);
        if (drawable2 != null && (iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY)) != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable2)) != null) {
            BaseButton rightChevronButton = getRightChevronButton();
            Intrinsics.checkNotNullExpressionValue(rightChevronButton, "rightChevronButton");
            styleBuilderWithIcon.applyStyle(rightChevronButton);
        }
        getRightChevronButton().getButton().setClickable(false);
    }
}
