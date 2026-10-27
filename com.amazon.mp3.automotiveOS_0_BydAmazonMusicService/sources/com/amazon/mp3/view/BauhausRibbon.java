package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausRibbon.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#J\u0010\u0010$\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#J\u0010\u0010%\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#J\u0017\u0010&\u001a\u00020 2\n\b\u0001\u0010'\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010(J\u0017\u0010)\u001a\u00020 2\n\b\u0001\u0010'\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010(J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0007J\u000e\u0010*\u001a\u00020 2\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020\u0007J\u0016\u00100\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u00020 J\b\u00104\u001a\u00020 H\u0002R\u001b\u0010\t\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001b\u0010\u0013R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/amazon/mp3/view/BauhausRibbon;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "backgroundView", "getBackgroundView", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "backgroundView$delegate", "Lkotlin/Lazy;", "dismissIconResource", "navigationIconResource", "ribbonDismissButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getRibbonDismissButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "ribbonDismissButton$delegate", "ribbonMessageView", "Landroid/widget/TextView;", "getRibbonMessageView", "()Landroid/widget/TextView;", "ribbonMessageView$delegate", "ribbonNavigationButton", "getRibbonNavigationButton", "ribbonNavigationButton$delegate", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initViewStyling", "", "setDismissButtonClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setNavigationButtonClickListener", "setRibbonClickListener", "setRibbonDismissIcon", "iconResource", "(Ljava/lang/Integer;)V", "setRibbonNavigationIcon", "setRibbonText", "resourceID", "text", "", "setRibbonTextAlignment", "textAlignment", "setRibbonTextStyle", "fontStyleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "setRoundedBackground", "updateMarginEnd", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausRibbon extends ConstraintLayout {

    /* JADX INFO: renamed from: backgroundView$delegate, reason: from kotlin metadata */
    private final Lazy backgroundView;
    private int dismissIconResource;
    private int navigationIconResource;

    /* JADX INFO: renamed from: ribbonDismissButton$delegate, reason: from kotlin metadata */
    private final Lazy ribbonDismissButton;

    /* JADX INFO: renamed from: ribbonMessageView$delegate, reason: from kotlin metadata */
    private final Lazy ribbonMessageView;

    /* JADX INFO: renamed from: ribbonNavigationButton$delegate, reason: from kotlin metadata */
    private final Lazy ribbonNavigationButton;
    private StyleSheet styleSheet;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BauhausRibbon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BauhausRibbon(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BauhausRibbon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.dismissIconResource = R.drawable.ic_action_cancel2;
        this.navigationIconResource = R.drawable.ic_chevron_right;
        this.backgroundView = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.mp3.view.BauhausRibbon$backgroundView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                ViewParent parent = this.this$0.getRibbonMessageView().getParent();
                if (parent != null) {
                    return (ConstraintLayout) parent;
                }
                throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
            }
        });
        this.ribbonMessageView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.BauhausRibbon$ribbonMessageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.ribbon_text_area);
            }
        });
        this.ribbonDismissButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.BauhausRibbon$ribbonDismissButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.ribbon_dismiss);
            }
        });
        this.ribbonNavigationButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.BauhausRibbon$ribbonNavigationButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.ribbon_navigation_icon);
            }
        });
        View.inflate(context, R.layout.bauhaus_ribbon, this);
    }

    private final ConstraintLayout getBackgroundView() {
        return (ConstraintLayout) this.backgroundView.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getRibbonMessageView() {
        Object value = this.ribbonMessageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-ribbonMessageView>(...)");
        return (TextView) value;
    }

    private final BaseButton getRibbonDismissButton() {
        Object value = this.ribbonDismissButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-ribbonDismissButton>(...)");
        return (BaseButton) value;
    }

    public final BaseButton getRibbonNavigationButton() {
        Object value = this.ribbonNavigationButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-ribbonNavigationButton>(...)");
        return (BaseButton) value;
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Resources resources;
        Drawable drawable;
        Resources resources2;
        Drawable drawable2;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.SMALL);
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.PRIMARY);
        if (iconSize == null || iconStyle == null) {
            return;
        }
        BaseButton ribbonDismissButton = getRibbonDismissButton();
        Context context = getContext();
        if (context != null && (resources2 = context.getResources()) != null && (drawable2 = resources2.getDrawable(this.dismissIconResource)) != null) {
            new BaseButton.StyleBuilder().withSize(iconSize).withStyle(iconStyle).withIcon(drawable2).applyStyle(ribbonDismissButton);
        }
        BaseButton ribbonNavigationButton = getRibbonNavigationButton();
        Context context2 = getContext();
        if (context2 != null && (resources = context2.getResources()) != null && (drawable = resources.getDrawable(this.navigationIconResource)) != null) {
            new BaseButton.StyleBuilder().withSize(iconSize).withStyle(iconStyle).withIcon(drawable).applyStyle(ribbonNavigationButton);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        Integer color = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        if (color != null) {
            gradientDrawable.setColor(color.intValue());
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getRibbonMessageView(), fontStyle);
        }
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color2 != null) {
            getRibbonMessageView().setTextColor(color2.intValue());
        }
        setBackground(gradientDrawable);
        setVisibility(0);
    }

    public final void setRibbonText(int resourceID) {
        getRibbonMessageView().setText(getContext().getString(resourceID));
    }

    public final void setRibbonText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getRibbonMessageView().setText(text);
    }

    public final void setRibbonClickListener(View.OnClickListener onClickListener) {
        getRibbonMessageView().setOnClickListener(onClickListener);
    }

    public final void setDismissButtonClickListener(View.OnClickListener onClickListener) {
        getRibbonDismissButton().setOnClickListener(onClickListener);
    }

    public final void setNavigationButtonClickListener(View.OnClickListener onClickListener) {
        getRibbonNavigationButton().setOnClickListener(onClickListener);
    }

    public final void setRibbonTextAlignment(int textAlignment) {
        getRibbonMessageView().setTextAlignment(textAlignment);
    }

    public final void setRibbonTextStyle(StyleSheet styleSheet, FontStyleKey fontStyleKey) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
        FontStyle fontStyle = styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getRibbonMessageView(), fontStyle);
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color == null) {
            return;
        }
        getRibbonMessageView().setTextColor(color.intValue());
    }

    public final void setRibbonDismissIcon(@DrawableRes Integer iconResource) {
        if (iconResource == null) {
            getRibbonDismissButton().setVisibility(8);
            getRibbonDismissButton().setIcon(null);
        } else {
            this.dismissIconResource = iconResource.intValue();
            BaseButton ribbonDismissButton = getRibbonDismissButton();
            ribbonDismissButton.setIcon(ContextCompat.getDrawable(ribbonDismissButton.getContext(), this.dismissIconResource));
            ribbonDismissButton.setVisibility(0);
        }
    }

    public final void setRibbonNavigationIcon(@DrawableRes Integer iconResource) {
        if (iconResource == null) {
            getRibbonNavigationButton().setVisibility(8);
            getRibbonNavigationButton().setIcon(null);
            updateMarginEnd();
        } else {
            this.navigationIconResource = iconResource.intValue();
            BaseButton ribbonNavigationButton = getRibbonNavigationButton();
            ribbonNavigationButton.setIcon(ContextCompat.getDrawable(ribbonNavigationButton.getContext(), this.navigationIconResource));
            ribbonNavigationButton.setVisibility(0);
        }
    }

    public final void setRoundedBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.bauhaus_ribbon_border_radius));
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        if (color != null) {
            gradientDrawable.setColor(color.intValue());
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ribbon_background);
        if (drawable != null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawable});
            setBackground(null);
            getBackgroundView().setBackground(layerDrawable);
        }
    }

    private final void updateMarginEnd() {
        ViewGroup.LayoutParams layoutParams = getRibbonMessageView().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.goneEndMargin = (int) getResources().getDimension(R.dimen.bauhaus_spacer_small);
        getRibbonMessageView().setLayoutParams(layoutParams2);
    }
}
