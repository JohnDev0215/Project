package com.amazon.ui.foundations.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.ui.foundations.R$id;
import com.amazon.ui.foundations.R$layout;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.material.button.MaterialButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseButton.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001:\u0001IB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010&\u001a\u00020'2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010(\u001a\u00020'2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010)\u001a\u00020'2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\fH\u0002J\u0012\u0010/\u001a\u00020'2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u000e\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020\u0007J\u0010\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u0019H\u0016J\u0010\u00106\u001a\u00020'2\b\u0010%\u001a\u0004\u0018\u00010\nJ\u0006\u00107\u001a\u00020'J\u0006\u00108\u001a\u00020'J\u0010\u00109\u001a\u00020'2\u0006\u0010:\u001a\u00020\u0007H\u0016J\u0012\u0010;\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0012\u0010>\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u00010?H\u0016J\u000e\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020\u000eJ\u0010\u0010B\u001a\u00020'2\u0006\u0010+\u001a\u00020,H\u0002J\u000e\u0010C\u001a\u00020'2\u0006\u0010D\u001a\u00020\u0007J\u0010\u0010E\u001a\u00020'2\u0006\u0010.\u001a\u00020\fH\u0002J\u0012\u0010F\u001a\u00020'2\b\u0010%\u001a\u0004\u0018\u00010\nH\u0002J*\u0010G\u001a\u00020'2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010H\u001a\u00020'2\u0006\u0010A\u001a\u00020\u000eH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseButton;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "activeIcon", "Landroid/graphics/drawable/Drawable;", "activeStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "activeText", "", "button", "Lcom/google/android/material/button/MaterialButton;", "getButton", "()Lcom/google/android/material/button/MaterialButton;", "button$delegate", "Lkotlin/Lazy;", "defaultIcon", "defaultStyle", "defaultText", "iconInOneStateOnly", "", "iconStatefulStyle", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "value", "isActive", "()Z", "setActive", "(Z)V", "transparent", "Landroid/content/res/ColorStateList;", "useRightIcon", "getIconWithBackground", "icon", "setActiveIcon", "", "setActiveStyle", "setActiveText", "setButtonSize", "buttonSize", "Lcom/amazon/ui/foundations/styles/ButtonSize;", "setButtonStyle", "buttonStyle", "setContentDescription", "contentDescription", "", "setCornerRadius", "radius", "setEnabled", "enabled", "setIcon", "setIconInOneStateOnly", "setIconPositionRight", "setId", "id", "setOnClickListener", "l", "Landroid/view/View$OnClickListener;", "setOnTouchListener", "Landroid/view/View$OnTouchListener;", "setText", "text", "setTextAttribute", "setupWidth", "width", "updateButtonStyle", "updateIcon", "updateState", "updateText", "StyleBuilder", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseButton extends ConstraintLayout {
    private Drawable activeIcon;
    private ButtonStyle activeStyle;
    private String activeText;

    /* JADX INFO: renamed from: button$delegate, reason: from kotlin metadata */
    private final Lazy button;
    private Drawable defaultIcon;
    private ButtonStyle defaultStyle;
    private String defaultText;
    private boolean iconInOneStateOnly;
    private StatefulStyle iconStatefulStyle;
    private boolean isActive;
    private final ColorStateList transparent;
    private boolean useRightIcon;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseButton(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BaseButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.button = LazyKt__LazyJVMKt.lazy(new Function0<MaterialButton>() { // from class: com.amazon.ui.foundations.views.BaseButton$button$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MaterialButton invoke() {
                return (MaterialButton) this.this$0.findViewById(R$id.button);
            }
        });
        ColorStateList colorStateList$default = ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, 0, null, null, null, 14, null);
        this.transparent = colorStateList$default;
        View.inflate(context, R$layout.base_button_layout, this);
        getButton().setIconTintMode(PorterDuff.Mode.ADD);
        getButton().setRippleColor(colorStateList$default);
        getButton().setIconTint(colorStateList$default);
        getButton().setBackgroundTintList(colorStateList$default);
    }

    public final MaterialButton getButton() {
        Object value = this.button.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-button>(...)");
        return (MaterialButton) value;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
        updateState(z ? this.activeStyle : this.defaultStyle, z ? this.activeIcon : this.defaultIcon, z ? this.activeText : this.defaultText);
    }

    public static /* synthetic */ void updateState$default(BaseButton baseButton, ButtonStyle buttonStyle, Drawable drawable, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateState");
        }
        if ((i & 1) != 0) {
            buttonStyle = null;
        }
        if ((i & 2) != 0) {
            drawable = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        baseButton.updateState(buttonStyle, drawable, str);
    }

    public final void updateState(ButtonStyle buttonStyle, Drawable icon, String text) {
        if (buttonStyle != null) {
            updateButtonStyle(buttonStyle);
        }
        if (icon != null || this.iconInOneStateOnly) {
            updateIcon(icon);
        }
        if (text != null) {
            updateText(text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setButtonStyle(ButtonStyle buttonStyle) {
        this.defaultStyle = buttonStyle;
    }

    public final void setIcon(Drawable icon) {
        this.defaultIcon = icon;
        updateIcon(icon);
    }

    public final void setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.defaultText = text;
        updateText(text);
    }

    public final void setActiveStyle(ButtonStyle activeStyle) {
        Intrinsics.checkNotNullParameter(activeStyle, "activeStyle");
        this.activeStyle = activeStyle;
    }

    public final void setActiveIcon(Drawable activeIcon) {
        Intrinsics.checkNotNullParameter(activeIcon, "activeIcon");
        this.activeIcon = activeIcon;
    }

    public final void setActiveText(String activeText) {
        Intrinsics.checkNotNullParameter(activeText, "activeText");
        this.activeText = activeText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setButtonSize(ButtonSize buttonSize) {
        Integer iconPaddingLeft;
        Integer height = buttonSize.getHeight();
        Integer width = buttonSize.getWidth();
        if (width == null) {
            width = height;
        }
        int iIntValue = 0;
        boolean z = buttonSize.getTextSize() == null;
        int paddingHorizontal = buttonSize.getPaddingHorizontal();
        Integer paddingVertical = buttonSize.getPaddingVertical();
        int iIntValue2 = paddingVertical == null ? 0 : paddingVertical.intValue();
        Integer iconSize = buttonSize.getIconSize();
        int iIntValue3 = iconSize == null ? 0 : iconSize.intValue();
        if (height != null) {
            getButton().setHeight(height.intValue());
        }
        if (z && width != null) {
            int iIntValue4 = (width.intValue() - iIntValue3) / 2;
            getButton().setWidth(width.intValue());
            getButton().setPaddingRelative(iIntValue4, 0, iIntValue4, 0);
        } else {
            setTextAttribute(buttonSize);
            getButton().setPaddingRelative((buttonSize.getIconSize() == null || (iconPaddingLeft = buttonSize.getIconPaddingLeft()) == null) ? paddingHorizontal : iconPaddingLeft.intValue(), iIntValue2, paddingHorizontal, iIntValue2);
            MaterialButton button = getButton();
            Integer iconPaddingRight = buttonSize.getIconPaddingRight();
            button.setIconPadding(iconPaddingRight == null ? 0 : iconPaddingRight.intValue());
        }
        MaterialButton button2 = getButton();
        Integer cornerRadius = buttonSize.getCornerRadius();
        if (cornerRadius != null) {
            iIntValue = cornerRadius.intValue();
        } else if (height != null) {
            iIntValue = height.intValue() / 2;
        }
        button2.setCornerRadius(iIntValue);
        getButton().setIconSize(iIntValue3);
    }

    private final void setTextAttribute(ButtonSize buttonSize) {
        if (buttonSize.getTextSize() != null) {
            getButton().setTextSize(0, r0.intValue());
        }
        getButton().setTypeface(buttonSize.getTextTypeface());
        MaterialButton button = getButton();
        Boolean textAllCaps = buttonSize.getTextAllCaps();
        button.setAllCaps(textAllCaps != null ? textAllCaps.booleanValue() : false);
        getButton().setLetterSpacing(0.0f);
    }

    private final void updateButtonStyle(ButtonStyle buttonStyle) {
        ColorStateList colorStateList = buttonStyle.getStatefulStyle().getColorStateList();
        StatefulStyle iconStatefulStyle = buttonStyle.getIconStatefulStyle();
        getButton().setStrokeColor(buttonStyle.getInnerBorderColor());
        MaterialButton button = getButton();
        Integer innerBorderSize = buttonStyle.getInnerBorderSize();
        button.setStrokeWidth(innerBorderSize == null ? 0 : innerBorderSize.intValue());
        getButton().setTextColor(colorStateList);
        if (iconStatefulStyle != null) {
            getButton().setIconTint(null);
            this.iconStatefulStyle = iconStatefulStyle;
        } else {
            getButton().setIconTint(colorStateList);
        }
        if (buttonStyle.getStatefulStyle().getColor() == null) {
            getButton().setIconTintMode(PorterDuff.Mode.ADD);
        } else {
            getButton().setIconTintMode(PorterDuff.Mode.SRC_IN);
        }
        getButton().setBackgroundTintList(buttonStyle.getStatefulStyle().getBackgroundColorStateList());
    }

    private final void updateIcon(Drawable icon) {
        Drawable iconWithBackground;
        MaterialButton button = getButton();
        StatefulStyle statefulStyle = this.iconStatefulStyle;
        if (statefulStyle != null && (iconWithBackground = getIconWithBackground(icon, statefulStyle)) != null) {
            icon = iconWithBackground;
        }
        button.setIcon(icon);
        if (this.useRightIcon) {
            UIExtensionsKt.setRightIcon(getButton());
        }
    }

    private final Drawable getIconWithBackground(Drawable icon, StatefulStyle iconStatefulStyle) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(iconStatefulStyle.getBackgroundColorStateList());
        if (icon != null) {
            icon.setColorFilter(iconStatefulStyle.getColorStateList().getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        return new LayerDrawable(new Drawable[]{gradientDrawable, icon});
    }

    private final void updateText(String text) {
        getButton().setText(text);
    }

    public final void setCornerRadius(int radius) {
        getButton().setCornerRadius(radius);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getButton().setEnabled(enabled);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        super.setOnClickListener(l);
        getButton().setOnClickListener(l);
        getButton().setId(getId());
        getButton().setContentDescription(getContentDescription());
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener l) {
        super.setOnTouchListener(l);
        getButton().setOnTouchListener(l);
    }

    public final void setIconPositionRight() {
        this.useRightIcon = true;
    }

    public final void setIconInOneStateOnly() {
        this.iconInOneStateOnly = true;
    }

    public final void setupWidth(int width) {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        MaterialButton button = getButton();
        Boolean bool = Boolean.TRUE;
        layoutParamUtils.setLayoutMargins(button, width, -2, (240 & 8) != 0 ? Boolean.FALSE : bool, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        layoutParamUtils.setLayoutMargins(this, width, -2, (240 & 8) != 0 ? Boolean.FALSE : bool, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    /* JADX INFO: compiled from: BaseButton.kt */
    @Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b5\u00106J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000fJ\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016J\b\u0010\"\u001a\u00020\u0000H\u0016R\u0018\u0010#\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010%\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010+\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010/R\u0018\u00101\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010&R\u0018\u00102\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u0010(R\u0018\u00103\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010*R\u0018\u00104\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u0010,¨\u00067"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "Lcom/amazon/ui/foundations/styles/ButtonSize;", "size", "withSize", "style", "withStyle", "Landroid/graphics/drawable/Drawable;", "icon", "withIcon", "", "text", "withText", "", "radius", "withCornerRadius", "withIconRight", "withIconInOneStateOnly", "activeStyle", "withActiveStyle", "activeIcon", "withActiveIcon", "activeText", "withActiveText", "width", "withWidth", "Landroid/content/Context;", "context", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "view", "", "applyStyle", "copy", "builderButtonSize", "Lcom/amazon/ui/foundations/styles/ButtonSize;", "builderButtonStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "builderIcon", "Landroid/graphics/drawable/Drawable;", "builderText", "Ljava/lang/String;", "builderCornerRadius", "Ljava/lang/Integer;", "", "builderRightIcon", "Ljava/lang/Boolean;", "builderIconInOneStateOnly", "builderActiveStyle", "builderActiveIcon", "builderActiveText", "builderWidth", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
    public static final class StyleBuilder {
        private Drawable builderActiveIcon;
        private ButtonStyle builderActiveStyle;
        private String builderActiveText;
        private ButtonSize builderButtonSize;
        private ButtonStyle builderButtonStyle;
        private Integer builderCornerRadius;
        private Drawable builderIcon;
        private Boolean builderIconInOneStateOnly;
        private Boolean builderRightIcon;
        private String builderText;
        private Integer builderWidth;

        public StyleBuilder() {
            Boolean bool = Boolean.FALSE;
            this.builderRightIcon = bool;
            this.builderIconInOneStateOnly = bool;
        }

        public final StyleBuilder withSize(ButtonSize size) {
            Intrinsics.checkNotNullParameter(size, "size");
            this.builderButtonSize = size;
            return this;
        }

        public StyleBuilder withStyle(ButtonStyle style) {
            Intrinsics.checkNotNullParameter(style, "style");
            this.builderButtonStyle = style;
            return this;
        }

        public final StyleBuilder withIcon(Drawable icon) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            this.builderIcon = icon;
            return this;
        }

        public final StyleBuilder withText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.builderText = text;
            return this;
        }

        public final StyleBuilder withCornerRadius(int radius) {
            this.builderCornerRadius = Integer.valueOf(radius);
            return this;
        }

        public final StyleBuilder withIconRight() {
            this.builderRightIcon = Boolean.TRUE;
            return this;
        }

        public final StyleBuilder withIconInOneStateOnly() {
            this.builderIconInOneStateOnly = Boolean.TRUE;
            return this;
        }

        public final StyleBuilder withActiveStyle(ButtonStyle activeStyle) {
            Intrinsics.checkNotNullParameter(activeStyle, "activeStyle");
            this.builderActiveStyle = activeStyle;
            return this;
        }

        public final StyleBuilder withActiveIcon(Drawable activeIcon) {
            Intrinsics.checkNotNullParameter(activeIcon, "activeIcon");
            this.builderActiveIcon = activeIcon;
            return this;
        }

        public final StyleBuilder withActiveText(String activeText) {
            Intrinsics.checkNotNullParameter(activeText, "activeText");
            this.builderActiveText = activeText;
            return this;
        }

        public final StyleBuilder withWidth(int width) {
            this.builderWidth = Integer.valueOf(width);
            return this;
        }

        public BaseButton build(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            BaseButton baseButton = new BaseButton(context, null, 0, 6, null);
            applyStyle(baseButton);
            return baseButton;
        }

        public void applyStyle(BaseButton view) {
            Intrinsics.checkNotNullParameter(view, "view");
            ButtonSize buttonSize = this.builderButtonSize;
            if (buttonSize != null) {
                view.setButtonSize(buttonSize);
            }
            ButtonStyle buttonStyle = this.builderButtonStyle;
            if (buttonStyle != null) {
                view.setButtonStyle(buttonStyle);
            }
            Drawable drawable = this.builderIcon;
            if (drawable != null) {
                view.setIcon(drawable);
            }
            String str = this.builderText;
            if (str != null) {
                view.setText(str);
            }
            Integer num = this.builderCornerRadius;
            if (num != null) {
                view.setCornerRadius(num.intValue());
            }
            Boolean bool = this.builderRightIcon;
            Boolean bool2 = Boolean.TRUE;
            if (Intrinsics.areEqual(bool, bool2)) {
                view.setIconPositionRight();
            }
            if (Intrinsics.areEqual(this.builderIconInOneStateOnly, bool2)) {
                view.setIconInOneStateOnly();
            }
            ButtonStyle buttonStyle2 = this.builderActiveStyle;
            if (buttonStyle2 != null) {
                view.setActiveStyle(buttonStyle2);
            }
            Drawable drawable2 = this.builderActiveIcon;
            if (drawable2 != null) {
                view.setActiveIcon(drawable2);
            }
            String str2 = this.builderActiveText;
            if (str2 != null) {
                view.setActiveText(str2);
            }
            Integer num2 = this.builderWidth;
            if (num2 != null) {
                view.setupWidth(num2.intValue());
            }
            view.updateState(this.builderButtonStyle, this.builderIcon, this.builderText);
        }

        public StyleBuilder copy() {
            StyleBuilder styleBuilder = new StyleBuilder();
            ButtonSize buttonSize = this.builderButtonSize;
            if (buttonSize != null) {
                styleBuilder.withSize(buttonSize);
            }
            ButtonStyle buttonStyle = this.builderButtonStyle;
            if (buttonStyle != null) {
                styleBuilder.withStyle(buttonStyle);
            }
            Drawable drawable = this.builderIcon;
            if (drawable != null) {
                styleBuilder.withIcon(drawable);
            }
            String str = this.builderText;
            if (str != null) {
                styleBuilder.withText(str);
            }
            Integer num = this.builderCornerRadius;
            if (num != null) {
                styleBuilder.withCornerRadius(num.intValue());
            }
            Boolean bool = this.builderRightIcon;
            Boolean bool2 = Boolean.TRUE;
            if (Intrinsics.areEqual(bool, bool2)) {
                styleBuilder.withIconRight();
            }
            if (Intrinsics.areEqual(this.builderIconInOneStateOnly, bool2)) {
                styleBuilder.withIconInOneStateOnly();
            }
            ButtonStyle buttonStyle2 = this.builderActiveStyle;
            if (buttonStyle2 != null) {
                styleBuilder.withActiveStyle(buttonStyle2);
            }
            Drawable drawable2 = this.builderActiveIcon;
            if (drawable2 != null) {
                styleBuilder.withActiveIcon(drawable2);
            }
            String str2 = this.builderActiveText;
            if (str2 != null) {
                styleBuilder.withActiveText(str2);
            }
            Integer num2 = this.builderWidth;
            if (num2 != null) {
                styleBuilder.withWidth(num2.intValue());
            }
            return styleBuilder;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void setId(int id) {
        super.setId(id);
        getButton().setId(id);
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence contentDescription) {
        super.setContentDescription(contentDescription);
        try {
            getButton().setContentDescription(contentDescription);
        } catch (Exception unused) {
        }
    }
}
