package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.utils.SpannableUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: LinkNavigator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u00104\u001a\u000203\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b5\u00106J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tJ\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0004J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001aR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u001b\u00102\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101¨\u00067"}, d2 = {"Lcom/amazon/music/views/library/views/LinkNavigator;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Landroid/graphics/drawable/Drawable;", "getDefaultBackgroundDrawable", "", Constants.ENABLE_DISABLE, "", "setIconEnabled", "initViewLayout", "Landroid/graphics/Bitmap;", "bitmap", "setButtonBackground", "", "text", "deemphasizedText", "setText", "setEnabled", "show", "setIconVisibility", "iconDrawable", "activeIconDrawable", "setIconType", "isActive", "setIconState", "shouldShowNewBadge", "setNewBadgeVisibility", "", "height", "setHeight", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/widget/TextView;", "textView", "Landroid/widget/TextView;", "Lcom/amazon/ui/foundations/views/BaseButton;", "iconImageview", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/ui/foundations/styles/GridStyle;", "gridStyle", "Lcom/amazon/ui/foundations/styles/GridStyle;", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "newBadgeView$delegate", "Lkotlin/Lazy;", "getNewBadgeView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "newBadgeView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class LinkNavigator extends ConstraintLayout {
    private GridStyle gridStyle;
    private BaseButton.StyleBuilder iconButtonBuilder;
    private BaseButton iconImageview;

    /* JADX INFO: renamed from: newBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy newBadgeView;
    private final StyleSheet styleSheet;
    private TextView textView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkNavigator(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.newBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.LinkNavigator$newBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.nav_new_badge);
            }
        });
        setId(R$id.nav_button);
        View.inflate(context, R$layout.dmmviewlibrary_nav_button_layout, this);
        View viewFindViewById = findViewById(R$id.nav_button_text);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.textView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.nav_button_icon);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
        }
        this.iconImageview = (BaseButton) viewFindViewById2;
        this.gridStyle = styleSheet.getGridStyle(GridStyleKey.LIST_ITEM_GRID, context);
        this.iconButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY);
        initViewLayout();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final BaseButton getNewBadgeView() {
        Object value = this.newBadgeView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-newBadgeView>(...)");
        return (BaseButton) value;
    }

    public final void setButtonBackground(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        setBackground(new LayerDrawable(new Drawable[]{getDefaultBackgroundDrawable(), new BitmapDrawable(getResources(), bitmap)}));
    }

    private final Drawable getDefaultBackgroundDrawable() {
        Drawable drawable = getResources().getDrawable(R$drawable.navigation_button_background, null);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…_button_background, null)");
        return drawable;
    }

    public final void setText(String text, String deemphasizedText) {
        Intrinsics.checkNotNullParameter(text, "text");
        TextView textView = this.textView;
        Intrinsics.checkNotNull(textView);
        textView.setText(text);
        if (deemphasizedText != null) {
            if (StringsKt__StringsKt.trim((CharSequence) deemphasizedText).toString().length() > 0) {
                TextView textView2 = this.textView;
                Intrinsics.checkNotNull(textView2);
                textView2.setText(SpannableUtil.INSTANCE.getSpannableWithEmphasis(text, deemphasizedText));
            }
        }
        setContentDescription(getContext().getString(R$string.content_description_button_suffix, text));
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            TextView textView = this.textView;
            if (textView != null) {
                textView.setAlpha(1.0f);
            }
        } else {
            Float alpha = this.styleSheet.getAlpha(AlphaKey.GLASS_3);
            if (alpha != null) {
                float fFloatValue = alpha.floatValue();
                TextView textView2 = this.textView;
                if (textView2 != null) {
                    textView2.setAlpha(fFloatValue);
                }
            }
        }
        setClickable(isEnabled);
        setIconEnabled(isEnabled);
    }

    public final void setIconVisibility(boolean show) {
        if (show) {
            BaseButton baseButton = this.iconImageview;
            Intrinsics.checkNotNull(baseButton);
            baseButton.setVisibility(0);
        } else {
            BaseButton baseButton2 = this.iconImageview;
            Intrinsics.checkNotNull(baseButton2);
            baseButton2.setVisibility(8);
        }
    }

    public final void setIconType(Drawable iconDrawable, Drawable activeIconDrawable) {
        BaseButton.StyleBuilder styleBuilder;
        ButtonStyle iconStyle;
        Intrinsics.checkNotNullParameter(iconDrawable, "iconDrawable");
        BaseButton baseButton = this.iconImageview;
        if (baseButton == null || (styleBuilder = this.iconButtonBuilder) == null) {
            return;
        }
        styleBuilder.withIcon(iconDrawable);
        if (activeIconDrawable != null && (iconStyle = getStyleSheet().getIconStyle(IconStyleKey.ACCENT)) != null) {
            styleBuilder.withActiveIcon(activeIconDrawable);
            styleBuilder.withActiveStyle(iconStyle);
        }
        styleBuilder.applyStyle(baseButton);
    }

    private final void setIconEnabled(boolean isEnabled) {
        BaseButton baseButton = this.iconImageview;
        if (baseButton == null) {
            return;
        }
        baseButton.setEnabled(isEnabled);
    }

    public final void setIconState(boolean isActive) {
        BaseButton baseButton = this.iconImageview;
        if (baseButton == null) {
            return;
        }
        baseButton.setActive(isActive);
    }

    public final void setNewBadgeVisibility(boolean shouldShowNewBadge) {
        int i;
        BaseButton newBadgeView = getNewBadgeView();
        if (shouldShowNewBadge) {
            BaseButton.StyleBuilder tagBuilder = getStyleSheet().getTagBuilder(TagSizeKey.MEDIUM, TagStyleKey.GLASS_SECONDARY);
            if (tagBuilder != null) {
                String string = newBadgeView.getContext().getResources().getString(R$string.podcast_new_badge);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…string.podcast_new_badge)");
                tagBuilder.withText(string);
                tagBuilder.applyStyle(newBadgeView);
            }
            i = 0;
        } else {
            i = 8;
        }
        newBadgeView.setVisibility(i);
    }

    public final void setHeight(int height) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    private final void initViewLayout() {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, this.styleSheet.getMediumListItemHeight(), (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : 0, (240 & 128) != 0 ? null : 0);
        BaseButton baseButton = this.iconImageview;
        if (baseButton == null) {
            return;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.LinkNavigator$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkNavigator.m2419initViewLayout$lambda8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViewLayout$lambda-8, reason: not valid java name */
    public static final void m2419initViewLayout$lambda8(LinkNavigator this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.performClick();
    }
}
