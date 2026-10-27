package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.music.views.library.views.TableRowItemView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveHeaderContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010*\u001a\u00020)¢\u0006\u0004\b+\u0010,J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002J\u0010\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0004R\u001b\u0010\u0019\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001e\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010#\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0016\u001a\u0004\b!\u0010\"R\u001b\u0010&\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0016\u001a\u0004\b%\u0010\"¨\u0006-"}, d2 = {"Lcom/amazon/music/views/library/views/DescriptiveHeaderContainer;", "Landroid/widget/FrameLayout;", "", "enabled", "", "setEnabled", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initViewStyling", "isPlaying", "showEqualizer", "", "rowNumber", "setRowNumber", "showRowNumber", "showInstantPlaybackIcon", "showRowCta", "", "setRowCta", "cleanViews", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "equalizer$delegate", "Lkotlin/Lazy;", "getEqualizer", "()Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "equalizer", "Landroid/widget/TextView;", "rowNumberTextView$delegate", "getRowNumberTextView", "()Landroid/widget/TextView;", "rowNumberTextView", "Lcom/amazon/ui/foundations/views/BaseButton;", "instantPlayIcon$delegate", "getInstantPlayIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "instantPlayIcon", "rowCta$delegate", "getRowCta", "rowCta", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptiveHeaderContainer extends FrameLayout {

    /* JADX INFO: renamed from: equalizer$delegate, reason: from kotlin metadata */
    private final Lazy equalizer;

    /* JADX INFO: renamed from: instantPlayIcon$delegate, reason: from kotlin metadata */
    private final Lazy instantPlayIcon;

    /* JADX INFO: renamed from: rowCta$delegate, reason: from kotlin metadata */
    private final Lazy rowCta;

    /* JADX INFO: renamed from: rowNumberTextView$delegate, reason: from kotlin metadata */
    private final Lazy rowNumberTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptiveHeaderContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.equalizer = LazyKt__LazyJVMKt.lazy(new Function0<PlayNotificationIcon>() { // from class: com.amazon.music.views.library.views.DescriptiveHeaderContainer$equalizer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayNotificationIcon invoke() {
                return (PlayNotificationIcon) this.this$0.findViewById(R$id.equalizer);
            }
        });
        this.rowNumberTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptiveHeaderContainer$rowNumberTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.row_number);
            }
        });
        this.instantPlayIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DescriptiveHeaderContainer$instantPlayIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.instant_play_icon);
            }
        });
        this.rowCta = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DescriptiveHeaderContainer$rowCta$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.row_cta);
            }
        });
        View.inflate(getContext(), R$layout.dmmviewlibrary_descriptive_header_container, this);
    }

    private final PlayNotificationIcon getEqualizer() {
        Object value = this.equalizer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-equalizer>(...)");
        return (PlayNotificationIcon) value;
    }

    private final TextView getRowNumberTextView() {
        Object value = this.rowNumberTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowNumberTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getInstantPlayIcon() {
        Object value = this.instantPlayIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-instantPlayIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getRowCta() {
        Object value = this.rowCta.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowCta>(...)");
        return (BaseButton) value;
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getRowNumberTextView().setEnabled(isEnabled());
        getInstantPlayIcon().setEnabled(isEnabled());
        getRowCta().setEnabled(isEnabled());
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Integer iconSize;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        BaseButton.StyleBuilder indicatorBuilder = styleSheet.getIndicatorBuilder(IndicatorSizeKey.SMALL, IndicatorStyleKey.PLAYBACK);
        if (indicatorBuilder != null) {
            Drawable drawable = getContext().getResources().getDrawable(R$drawable.ic_play_bauhaus);
            Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…drawable.ic_play_bauhaus)");
            BaseButton.StyleBuilder styleBuilderWithIcon = indicatorBuilder.withIcon(drawable);
            if (styleBuilderWithIcon != null) {
                styleBuilderWithIcon.applyStyle(getInstantPlayIcon());
            }
        }
        TableRowItemView.Companion.applyFontStyle$default(TableRowItemView.INSTANCE, styleSheet, getRowNumberTextView(), FontStyleKey.INDEX, null, 8, null);
        ButtonSize iconSize2 = styleSheet.getIconSize(IconSizeKey.SMALL);
        if (iconSize2 == null || (iconSize = iconSize2.getIconSize()) == null) {
            return;
        }
        int iIntValue = iconSize.intValue();
        LayoutParamUtils.INSTANCE.setLayoutMargins(getEqualizer(), iIntValue, iIntValue, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    public final void showEqualizer(boolean isPlaying) {
        cleanViews();
        getEqualizer().setVisibility(0);
        getEqualizer().setIsPlaying(isPlaying);
    }

    public final void setRowNumber(String rowNumber) {
        cleanViews();
        getRowNumberTextView().setVisibility(TextUtils.isEmpty(rowNumber) ? 8 : 0);
        getRowNumberTextView().setText(rowNumber);
    }

    public final void showRowNumber() {
        getEqualizer().setVisibility(8);
        getInstantPlayIcon().setVisibility(8);
        getRowNumberTextView().setVisibility(0);
    }

    public final void showInstantPlaybackIcon() {
        cleanViews();
        getInstantPlayIcon().setVisibility(0);
    }

    public final void showRowCta() {
        getRowCta().setVisibility(0);
        getEqualizer().setVisibility(8);
        getInstantPlayIcon().setVisibility(8);
    }

    public final void setRowCta(StyleSheet styleSheet, int showRowCta) {
        Context context;
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        cleanViews();
        getRowCta().setVisibility(0);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MICRO, IconStyleKey.SONIC_RUSH);
        BaseButton rowCta = getRowCta();
        if (rowCta == null || (context = getContext()) == null || (drawable = context.getDrawable(showRowCta)) == null || iconBuilder == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(rowCta);
    }

    public final void cleanViews() {
        getEqualizer().setVisibility(8);
        getRowNumberTextView().setVisibility(8);
        getInstantPlayIcon().setVisibility(8);
        getRowCta().setVisibility(8);
    }
}
