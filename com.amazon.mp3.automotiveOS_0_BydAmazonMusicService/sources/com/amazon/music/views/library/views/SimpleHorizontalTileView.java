package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleHorizontalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 g2\u00020\u00012\u00020\u0002:\u0001gB\u0017\u0012\u0006\u0010d\u001a\u00020c\u0012\u0006\u0010*\u001a\u00020)¢\u0006\u0004\be\u0010fJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0003H\u0002JG\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u000e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\u0003J\u0006\u0010\u0015\u001a\u00020\u0003J\u000e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0016J\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\tJ\u0017\u0010\u001d\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001f\u0010\u001eJ\u000e\u0010\"\u001a\u00020\u00032\u0006\u0010!\u001a\u00020 J\u000e\u0010#\u001a\u00020\u00032\u0006\u0010!\u001a\u00020 J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010!\u001a\u00020 H\u0016J\u000e\u0010'\u001a\u00020\u00032\u0006\u0010&\u001a\u00020%J\u000e\u0010(\u001a\u00020\u00032\u0006\u0010&\u001a\u00020%R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001b\u00103\u001a\u00020.8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u001b\u00108\u001a\u0002048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u00100\u001a\u0004\b6\u00107R\u001b\u0010<\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u00100\u001a\u0004\b:\u0010;R\u001b\u0010?\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u00100\u001a\u0004\b>\u0010;R\u001b\u0010B\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u00100\u001a\u0004\bA\u0010;R\u001b\u0010G\u001a\u00020C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u00100\u001a\u0004\bE\u0010FR\u001b\u0010L\u001a\u00020H8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bI\u00100\u001a\u0004\bJ\u0010KR\u001b\u0010Q\u001a\u00020M8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u00100\u001a\u0004\bO\u0010PR\u0016\u0010R\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010SR\u0016\u0010U\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010SR\u0016\u0010V\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010SR\u0016\u0010W\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010SR\u0018\u0010X\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010SR\u0014\u0010\\\u001a\u00020Y8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0014\u0010^\u001a\u00020Y8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b]\u0010[R\u0014\u0010b\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010a¨\u0006h"}, d2 = {"Lcom/amazon/music/views/library/views/SimpleHorizontalTileView;", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "", "initViewMargins", "initViewStyling", "setTransparentGradientOverlay", "Landroid/widget/TextView;", "textView", "", "left", "top", "right", "bottom", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "ordinal", "updateOrdinalFontStyle", "width", "updateViewWidth", "updateFeaturedPresetMargin", "hideTransparentGradientOverlay", "", "title", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "setOrdinal", "playState", "setPlayState", "(Ljava/lang/Integer;)V", "setPodcastPlayState", "", Constants.ENABLE_DISABLE, "setContentViewsAsEnabled", "setControlViewsAsEnabled", "setEnabled", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "fontStyleKey", "setTitleFontStyle", "setSubTitleFontStyle", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout$delegate", "Lkotlin/Lazy;", "getArtworkFrameLayout", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout", "Landroid/widget/FrameLayout;", "artworkFrameViewContainer$delegate", "getArtworkFrameViewContainer", "()Landroid/widget/FrameLayout;", "artworkFrameViewContainer", "titleTextView$delegate", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "ordinalTextView$delegate", "getOrdinalTextView", "ordinalTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "Landroid/widget/LinearLayout;", "textContainer$delegate", "getTextContainer", "()Landroid/widget/LinearLayout;", "textContainer", "Landroid/widget/ImageView;", "transparentGradientImageView$delegate", "getTransparentGradientImageView", "()Landroid/widget/ImageView;", "transparentGradientImageView", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "equalizerIcon$delegate", "getEqualizerIcon", "()Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "equalizerIcon", "miniSpace", "Ljava/lang/Integer;", "smallSpace", "baseSpace", "mediumSpace", "largeSpace", "alignVerticalSpace", "Lcom/amazon/ui/foundations/views/BaseButton;", "getRowButtonView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "rowButtonView", "getRowButtonClickedImageView", "rowButtonClickedImageView", "Landroid/view/View;", "getRowButtonContainerView", "()Landroid/view/View;", "rowButtonContainerView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class SimpleHorizontalTileView extends LibraryAddableView {
    private Integer alignVerticalSpace;

    /* JADX INFO: renamed from: artworkFrameLayout$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameLayout;

    /* JADX INFO: renamed from: artworkFrameViewContainer$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameViewContainer;
    private final Integer baseSpace;

    /* JADX INFO: renamed from: equalizerIcon$delegate, reason: from kotlin metadata */
    private final Lazy equalizerIcon;
    private final Integer largeSpace;
    private final Integer mediumSpace;
    private final Integer miniSpace;

    /* JADX INFO: renamed from: ordinalTextView$delegate, reason: from kotlin metadata */
    private final Lazy ordinalTextView;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: renamed from: transparentGradientImageView$delegate, reason: from kotlin metadata */
    private final Lazy transparentGradientImageView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleHorizontalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameLayout = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$artworkFrameLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.artwork_frame_view_preset);
            }
        });
        this.artworkFrameViewContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$artworkFrameViewContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.artwork_frame_view_container);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.ordinalTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$ordinalTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.number_text);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_container);
            }
        });
        this.transparentGradientImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$transparentGradientImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.transparent_gradient_image_view);
            }
        });
        this.equalizerIcon = LazyKt__LazyJVMKt.lazy(new Function0<PlayNotificationIcon>() { // from class: com.amazon.music.views.library.views.SimpleHorizontalTileView$equalizerIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayNotificationIcon invoke() {
                return (PlayNotificationIcon) this.this$0.findViewById(R$id.equalizer);
            }
        });
        this.miniSpace = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.smallSpace = spacerInPixels;
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.mediumSpace = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        this.largeSpace = spacerInPixels2;
        this.alignVerticalSpace = spacerInPixels;
        View.inflate(context, R$layout.dmmviewlibrary_simple_horizontal_tile_layout, this);
        setId(R$id.horizontal_tile_view);
        initViewStyling();
        initViewMargins();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.artwork_dimen_featured_small) - context.getResources().getDimensionPixelOffset(R$dimen.artwork_default_dimen);
        if (spacerInPixels2 == null) {
            return;
        }
        this.alignVerticalSpace = Integer.valueOf(spacerInPixels2.intValue() - (dimensionPixelOffset / 2));
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameLayout() {
        Object value = this.artworkFrameLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameLayout>(...)");
        return (ArtworkFrameView) value;
    }

    public final FrameLayout getArtworkFrameViewContainer() {
        Object value = this.artworkFrameViewContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameViewContainer>(...)");
        return (FrameLayout) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getOrdinalTextView() {
        Object value = this.ordinalTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-ordinalTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final ImageView getTransparentGradientImageView() {
        Object value = this.transparentGradientImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-transparentGradientImageView>(...)");
        return (ImageView) value;
    }

    public final PlayNotificationIcon getEqualizerIcon() {
        Object value = this.equalizerIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-equalizerIcon>(...)");
        return (PlayNotificationIcon) value;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonView() {
        View viewFindViewById = findViewById(R$id.add_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<BaseButton>(R.id.add_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonClickedImageView() {
        View viewFindViewById = findViewById(R$id.add_complete_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<BaseButton>(R.id.add_complete_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public View getRowButtonContainerView() {
        View viewFindViewById = findViewById(R$id.add_container);
        if (viewFindViewById != null) {
            return (FrameLayout) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
    }

    private final void initViewMargins() {
        updateViewWidth(-1);
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        LinearLayout textContainer = getTextContainer();
        Integer num = this.mediumSpace;
        layoutParamUtils.setLayoutMargins(textContainer, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : num, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : num, (240 & 128) != 0 ? null : 0);
        setTextMargins(getTitleTextView(), 0, 0, 0, 0);
        setTextMargins(getSubtitleTextView(), 0, 0, 0, 0);
    }

    public final void updateViewWidth(int width) {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        Integer num = this.miniSpace;
        layoutParamUtils.setLayoutMargins(this, width, -2, Boolean.TRUE, num, 0, num, 0);
        setForeground(AppCompatResources.getDrawable(getContext(), R$drawable.ripple));
    }

    public final void updateFeaturedPresetMargin() {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        Integer num = this.alignVerticalSpace;
        layoutParamUtils.setLayoutMargins(this, -1, -2, Boolean.TRUE, num, 0, num, 0);
    }

    private final void initViewStyling() {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder iconBuilder2;
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY_T1_HUGE_SHARPGROTESK);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY_BIG);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle2);
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_like);
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        setRowButtonIconBuilder((drawable == null || (iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.SECONDARY_GLASS)) == null) ? null : iconBuilder.withIcon(drawable));
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_like_disabled);
        if (drawable2 != null && (iconBuilder2 = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.ACCENT_GLASS)) != null) {
            styleBuilderWithIcon = iconBuilder2.withIcon(drawable2);
        }
        setRowButtonClickedIconBuilder(styleBuilderWithIcon);
        initRowButtonStyling();
    }

    private final void setTransparentGradientOverlay() {
        getTransparentGradientImageView().setVisibility(0);
    }

    public final void hideTransparentGradientOverlay() {
        getTransparentGradientImageView().setVisibility(8);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
    }

    public final void setOrdinal(int ordinal) {
        getOrdinalTextView().setText(String.valueOf(ordinal));
        updateOrdinalFontStyle(ordinal);
        getOrdinalTextView().setVisibility(ordinal > 0 ? 0 : 8);
    }

    public final void setPlayState(Integer playState) {
        if (playState == null || playState.intValue() == 0) {
            getEqualizerIcon().setVisibility(8);
            hideTransparentGradientOverlay();
        } else {
            getEqualizerIcon().setIsPlaying(playState.intValue() == 2);
            getEqualizerIcon().setVisibility(0);
            setTransparentGradientOverlay();
        }
    }

    public final void setPodcastPlayState(Integer playState) {
        if ((playState != null && playState.intValue() == 3) || ((playState != null && playState.intValue() == 4) || (playState != null && playState.intValue() == 5))) {
            getEqualizerIcon().setVisibility(0);
            getEqualizerIcon().setIsPlaying(true);
            setTransparentGradientOverlay();
        } else if ((playState != null && playState.intValue() == 2) || (playState != null && playState.intValue() == 6)) {
            getEqualizerIcon().setVisibility(0);
            getEqualizerIcon().setIsPlaying(false);
            setTransparentGradientOverlay();
        } else {
            getEqualizerIcon().setVisibility(8);
            getEqualizerIcon().setIsPlaying(false);
            hideTransparentGradientOverlay();
        }
    }

    public final void setContentViewsAsEnabled(boolean isEnabled) {
        if (isEnabled) {
            getTextContainer().setAlpha(1.0f);
        } else {
            getTextContainer().setAlpha(0.3f);
        }
    }

    public final void setControlViewsAsEnabled(boolean isEnabled) {
        if (isEnabled) {
            getRowButtonContainerView().setAlpha(1.0f);
        } else {
            getRowButtonContainerView().setAlpha(0.3f);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        setAlpha(isEnabled ? 1.0f : 0.3f);
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final void setTitleFontStyle(FontStyleKey fontStyleKey) {
        Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
        FontStyle fontStyle = this.styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
    }

    public final void setSubTitleFontStyle(FontStyleKey fontStyleKey) {
        Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
        FontStyle fontStyle = this.styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle);
    }

    private final void updateOrdinalFontStyle(int ordinal) {
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        int iIntValue = color == null ? -1 : color.intValue();
        FontStyle fontStyle = this.styleSheet.getFontStyle(ordinal < 100 ? FontStyleKey.PRIMARY : FontStyleKey.SECONDARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getOrdinalTextView(), FontStyle.copy$default(fontStyle, null, null, iIntValue, null, 11, null));
        }
        getOrdinalTextView().setGravity(17);
    }
}
