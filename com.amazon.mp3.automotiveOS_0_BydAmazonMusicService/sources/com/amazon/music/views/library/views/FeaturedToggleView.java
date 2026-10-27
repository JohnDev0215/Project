package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedToggleView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010/\u001a\u000200H\u0002J\u0016\u00101\u001a\u0002002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000203J\u0016\u00105\u001a\u0002002\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207J\u0006\u00109\u001a\u000200J\b\u0010:\u001a\u000200H\u0002J\u0006\u0010;\u001a\u000200R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\f\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\f\u001a\u0004\b#\u0010\u001bR\u001b\u0010%\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\f\u001a\u0004\b&\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001b\u0010*\u001a\u00020+8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\f\u001a\u0004\b,\u0010-¨\u0006<"}, d2 = {"Lcom/amazon/music/views/library/views/FeaturedToggleView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "artworkContainerView", "Landroid/widget/RelativeLayout;", "getArtworkContainerView", "()Landroid/widget/RelativeLayout;", "artworkContainerView$delegate", "Lkotlin/Lazy;", "commentaryIndicatorView", "Lcom/amazon/music/views/library/views/CommentaryIndicatorView;", "getCommentaryIndicatorView", "()Lcom/amazon/music/views/library/views/CommentaryIndicatorView;", "commentaryIndicatorView$delegate", "value", "", "isToggleActive", "()Z", "setToggleActive", "(Z)V", "labelTextView", "Landroid/widget/TextView;", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView$delegate", "primaryImageView", "Lcom/amazon/ui/foundations/views/BaseImage;", "getPrimaryImageView", "()Lcom/amazon/ui/foundations/views/BaseImage;", "primaryImageView$delegate", "primaryTextView", "getPrimaryTextView", "primaryTextView$delegate", "secondaryImageView", "getSecondaryImageView", "secondaryImageView$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "toggleButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getToggleButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "toggleButton$delegate", "applyStyling", "", "configureIcons", "activeDrawable", "", "inactiveDrawable", "configureTexts", ContextMappingConstants.PRIMARY_TEXT, "", "labelText", "hideArtworkContainerView", "updateStyling", "updateToggle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedToggleView extends ConstraintLayout {

    /* JADX INFO: renamed from: artworkContainerView$delegate, reason: from kotlin metadata */
    private final Lazy artworkContainerView;

    /* JADX INFO: renamed from: commentaryIndicatorView$delegate, reason: from kotlin metadata */
    private final Lazy commentaryIndicatorView;
    private boolean isToggleActive;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: primaryImageView$delegate, reason: from kotlin metadata */
    private final Lazy primaryImageView;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: secondaryImageView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryImageView;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: toggleButton$delegate, reason: from kotlin metadata */
    private final Lazy toggleButton;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedToggleView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.commentaryIndicatorView = LazyKt__LazyJVMKt.lazy(new Function0<CommentaryIndicatorView>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$commentaryIndicatorView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommentaryIndicatorView invoke() {
                return (CommentaryIndicatorView) this.this$0.findViewById(R$id.commentary_indicator);
            }
        });
        this.artworkContainerView = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$artworkContainerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RelativeLayout invoke() {
                return (RelativeLayout) this.this$0.findViewById(R$id.artwork_container);
            }
        });
        this.toggleButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$toggleButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.toggle_button);
            }
        });
        this.primaryImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$primaryImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R$id.primary_image);
            }
        });
        this.secondaryImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.music.views.library.views.FeaturedToggleView$secondaryImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R$id.secondary_image);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_featured_toggle_view_layout, this);
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        SpacerKey spacerKey = SpacerKey.MEDIUM;
        layoutParamUtils.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : styleSheet.getSpacerInPixels(spacerKey), (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : styleSheet.getSpacerInPixels(spacerKey), (240 & 128) != 0 ? null : styleSheet.getSpacerInPixels(spacerKey));
        applyStyling();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    private final CommentaryIndicatorView getCommentaryIndicatorView() {
        Object value = this.commentaryIndicatorView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-commentaryIndicatorView>(...)");
        return (CommentaryIndicatorView) value;
    }

    private final RelativeLayout getArtworkContainerView() {
        Object value = this.artworkContainerView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkContainerView>(...)");
        return (RelativeLayout) value;
    }

    public final BaseButton getToggleButton() {
        Object value = this.toggleButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-toggleButton>(...)");
        return (BaseButton) value;
    }

    public final BaseImage getPrimaryImageView() {
        Object value = this.primaryImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryImageView>(...)");
        return (BaseImage) value;
    }

    public final BaseImage getSecondaryImageView() {
        Object value = this.secondaryImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryImageView>(...)");
        return (BaseImage) value;
    }

    /* JADX INFO: renamed from: isToggleActive, reason: from getter */
    public final boolean getIsToggleActive() {
        return this.isToggleActive;
    }

    public final void setToggleActive(boolean z) {
        if (this.isToggleActive == z) {
            return;
        }
        this.isToggleActive = z;
        updateStyling();
        getCommentaryIndicatorView().setToggleActive(this.isToggleActive);
        getToggleButton().setActive(this.isToggleActive);
    }

    private final void applyStyling() {
        Integer iconSize;
        Drawable drawable;
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        ButtonSize iconSize2 = this.styleSheet.getIconSize(IconSizeKey.MEDIUM);
        if (iconSize2 != null && (iconSize = iconSize2.getIconSize()) != null) {
            float fIntValue = iconSize.intValue();
            ButtonSize buttonSize = new ButtonSize(fIntValue, null, fIntValue, 2, null);
            Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R$drawable.ic_action_toggleoff, null);
            if (drawable2 != null && (drawable = ResourcesCompat.getDrawable(getResources(), R$drawable.ic_action_toggleon, null)) != null) {
                new BaseButton.StyleBuilder().withSize(buttonSize).withIcon(drawable2).withActiveIcon(drawable).applyStyle(getToggleButton());
            }
        }
        updateStyling();
    }

    private final void updateStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(this.isToggleActive ? FontStyleKey.PRIMARY_2 : FontStyleKey.SECONDARY);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), fontStyle);
    }

    public final void configureTexts(String primaryText, String labelText) {
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(labelText, "labelText");
        getPrimaryTextView().setText(primaryText);
        getLabelTextView().setText(labelText);
    }

    public final void updateToggle() {
        setToggleActive(!this.isToggleActive);
    }

    public final void configureIcons(int activeDrawable, int inactiveDrawable) {
        getCommentaryIndicatorView().configureIcons(activeDrawable, inactiveDrawable);
        getCommentaryIndicatorView().setToggleActive(this.isToggleActive);
    }

    public final void hideArtworkContainerView() {
        getArtworkContainerView().setVisibility(8);
    }
}
