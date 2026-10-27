package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LivestreamBarkerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010I\u001a\u00020\rJ\u0006\u0010J\u001a\u00020\u0015J\b\u0010K\u001a\u0004\u0018\u00010LJ\b\u0010M\u001a\u0004\u0018\u00010LJ\u0006\u0010N\u001a\u00020\rJ\b\u0010O\u001a\u0004\u0018\u00010LJ\b\u0010P\u001a\u00020QH\u0002J\u000e\u0010R\u001a\u00020Q2\u0006\u0010S\u001a\u00020\tJ\u0010\u0010T\u001a\u00020Q2\u0006\u0010U\u001a\u00020.H\u0016J\u000e\u0010V\u001a\u00020Q2\u0006\u0010W\u001a\u00020\tJ\u001a\u0010X\u001a\u00020Q2\u0006\u0010Y\u001a\u0002012\b\u0010Z\u001a\u0004\u0018\u00010LH\u0002J\u0010\u0010[\u001a\u00020Q2\b\u0010Z\u001a\u0004\u0018\u00010LJ\u0010\u0010\\\u001a\u00020Q2\b\u0010Z\u001a\u0004\u0018\u00010LJ\u0006\u0010]\u001a\u00020QJ\u0010\u0010^\u001a\u00020Q2\b\u0010Z\u001a\u0004\u0018\u00010LJ\u000e\u0010_\u001a\u00020Q2\u0006\u0010`\u001a\u00020aR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0011\u001a\u0004\b \u0010!R\u0012\u0010#\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u0011\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0011\u001a\u0004\b*\u0010+R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010/\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u001b\u00100\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0011\u001a\u0004\b2\u00103R\u001b\u00105\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\u0011\u001a\u0004\b6\u00103R\u0012\u00108\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u001b\u00109\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u0011\u001a\u0004\b:\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001b\u0010>\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\u0011\u001a\u0004\b?\u00103R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u0011\u001a\u0004\bC\u0010DR\u001b\u0010F\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\u0011\u001a\u0004\bG\u0010'¨\u0006b"}, d2 = {"Lcom/amazon/music/views/library/views/LivestreamBarkerView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;II)V", "artistArtwork", "Landroid/widget/ImageView;", "getArtistArtwork", "()Landroid/widget/ImageView;", "artistArtwork$delegate", "Lkotlin/Lazy;", "getAttrs", "()Landroid/util/AttributeSet;", "barkerBackgroundImageView", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBarkerBackgroundImageView", "()Lcom/amazon/ui/foundations/views/BaseImage;", "barkerBackgroundImageView$delegate", "baseSpace", "Ljava/lang/Integer;", "getDefStyleAttr", "()I", "getDefStyleRes", "disabledMask", "Landroid/view/View;", "getDisabledMask", "()Landroid/view/View;", "disabledMask$delegate", "hugeSpace", "liveBadgeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getLiveBadgeButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "liveBadgeButton$delegate", "liveContent", "getLiveContent", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "liveContent$delegate", "mIsEnabled", "", "nanoSpace", "primaryTextView", "Landroid/widget/TextView;", "getPrimaryTextView", "()Landroid/widget/TextView;", "primaryTextView$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "smallSpace", "sourceLogo", "getSourceLogo", "sourceLogo$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "tertiaryTextView", "getTertiaryTextView", "tertiaryTextView$delegate", "transparentGradientImageView", "Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "getTransparentGradientImageView", "()Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "transparentGradientImageView$delegate", "viewerCountButton", "getViewerCountButton", "viewerCountButton$delegate", "getArtistArtworkImageView", "getBackgroundImageView", "getPrimaryTitle", "", "getSecondaryTitle", "getSourceLogoImageView", "getTertiaryTitle", "initViewStyling", "", "setArtistArtworkMargins", "size", "setEnabled", Constants.ENABLE_DISABLE, "setLiveContentGradient", "width", "setOrHideText", "textView", "text", "setPrimaryTitle", "setSecondaryTitle", "setSourceLogoMargins", "setTertiaryTitle", "setViewerCount", "viewerCount", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LivestreamBarkerView extends ConstraintLayout {

    /* JADX INFO: renamed from: artistArtwork$delegate, reason: from kotlin metadata */
    private final Lazy artistArtwork;
    private final AttributeSet attrs;

    /* JADX INFO: renamed from: barkerBackgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy barkerBackgroundImageView;
    private final Integer baseSpace;
    private final int defStyleAttr;
    private final int defStyleRes;

    /* JADX INFO: renamed from: disabledMask$delegate, reason: from kotlin metadata */
    private final Lazy disabledMask;
    private final Integer hugeSpace;

    /* JADX INFO: renamed from: liveBadgeButton$delegate, reason: from kotlin metadata */
    private final Lazy liveBadgeButton;

    /* JADX INFO: renamed from: liveContent$delegate, reason: from kotlin metadata */
    private final Lazy liveContent;
    private boolean mIsEnabled;
    private final Integer nanoSpace;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private final Integer smallSpace;

    /* JADX INFO: renamed from: sourceLogo$delegate, reason: from kotlin metadata */
    private final Lazy sourceLogo;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX INFO: renamed from: transparentGradientImageView$delegate, reason: from kotlin metadata */
    private final Lazy transparentGradientImageView;

    /* JADX INFO: renamed from: viewerCountButton$delegate, reason: from kotlin metadata */
    private final Lazy viewerCountButton;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m2420_init_$lambda0(View view) {
    }

    public /* synthetic */ LivestreamBarkerView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
    }

    public final int getDefStyleAttr() {
        return this.defStyleAttr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivestreamBarkerView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
        this.disabledMask = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$disabledMask$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R$id.disabled_mask);
            }
        });
        this.barkerBackgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$barkerBackgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R$id.background_image);
            }
        });
        this.viewerCountButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$viewerCountButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.barker_viewer_count);
            }
        });
        this.liveBadgeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$liveBadgeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.barker_live_badge);
            }
        });
        this.liveContent = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$liveContent$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.live_content);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.tertiary_text);
            }
        });
        this.sourceLogo = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$sourceLogo$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.barker_source_logo);
            }
        });
        this.artistArtwork = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$artistArtwork$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.barker_artist_artwork);
            }
        });
        this.transparentGradientImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$transparentGradientImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.transparent_gradient_image_view);
            }
        });
        this.mIsEnabled = true;
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.hugeSpace = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        View.inflate(context, R$layout.dmmviewlibrary_livestream_barker, this);
        if (!isInEditMode()) {
            getDisabledMask().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.LivestreamBarkerView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivestreamBarkerView.m2420_init_$lambda0(view);
                }
            });
        }
        initViewStyling();
    }

    public final int getDefStyleRes() {
        return this.defStyleRes;
    }

    private final View getDisabledMask() {
        Object value = this.disabledMask.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-disabledMask>(...)");
        return (View) value;
    }

    private final BaseImage getBarkerBackgroundImageView() {
        Object value = this.barkerBackgroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-barkerBackgroundImageView>(...)");
        return (BaseImage) value;
    }

    private final BaseButton getViewerCountButton() {
        Object value = this.viewerCountButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-viewerCountButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getLiveBadgeButton() {
        Object value = this.liveBadgeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-liveBadgeButton>(...)");
        return (BaseButton) value;
    }

    private final ConstraintLayout getLiveContent() {
        Object value = this.liveContent.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-liveContent>(...)");
        return (ConstraintLayout) value;
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

    private final ImageView getSourceLogo() {
        Object value = this.sourceLogo.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sourceLogo>(...)");
        return (ImageView) value;
    }

    private final ImageView getArtistArtwork() {
        Object value = this.artistArtwork.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artistArtwork>(...)");
        return (ImageView) value;
    }

    private final TransparentGradientImageView getTransparentGradientImageView() {
        Object value = this.transparentGradientImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-transparentGradientImageView>(...)");
        return (TransparentGradientImageView) value;
    }

    public final void setViewerCount(String viewerCount) {
        Intrinsics.checkNotNullParameter(viewerCount, "viewerCount");
        getViewerCountButton().setText(viewerCount);
    }

    public final BaseImage getBackgroundImageView() {
        return getBarkerBackgroundImageView();
    }

    public final ImageView getArtistArtworkImageView() {
        return getArtistArtwork();
    }

    public final void setArtistArtworkMargins(int size) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getArtistArtwork(), size, size, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : this.smallSpace, (240 & 128) != 0 ? null : 0);
    }

    public final ImageView getSourceLogoImageView() {
        return getSourceLogo();
    }

    public final void setSourceLogoMargins() {
        Float alpha = this.styleSheet.getAlpha(AlphaKey.GLASS_5);
        if (alpha != null) {
            getSourceLogo().setAlpha(alpha.floatValue());
        }
        Integer num = this.nanoSpace;
        if (num != null) {
            int iIntValue = num.intValue();
            getSourceLogo().setPadding(0, 0, iIntValue, iIntValue);
        }
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        ImageView sourceLogo = getSourceLogo();
        Integer num2 = this.smallSpace;
        layoutParamUtils.setLayoutMargins(sourceLogo, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : num2, (240 & 128) != 0 ? null : num2);
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), fontStyle);
        }
        StyleSheet styleSheet = this.styleSheet;
        FontStyleKey fontStyleKey = FontStyleKey.SECONDARY;
        FontStyle fontStyle2 = styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSecondaryTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(fontStyleKey);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getTertiaryTextView(), fontStyle3);
        }
        getTertiaryTextView().setTypeface(getTertiaryTextView().getTypeface(), 2);
        StyleSheet styleSheet2 = this.styleSheet;
        TagSizeKey tagSizeKey = TagSizeKey.MEDIUM;
        BaseButton.StyleBuilder tagBuilder = styleSheet2.getTagBuilder(tagSizeKey, TagStyleKey.LIVE);
        if (tagBuilder != null) {
            String string = getContext().getResources().getString(R$string.dmusic_live);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.dmusic_live)");
            tagBuilder.withText(string);
            tagBuilder.applyStyle(getLiveBadgeButton());
        }
        BaseButton.StyleBuilder tagBuilder2 = this.styleSheet.getTagBuilder(tagSizeKey, TagStyleKey.LIVE_VIEWER);
        if (tagBuilder2 == null) {
            return;
        }
        tagBuilder2.applyStyle(getViewerCountButton());
    }

    public final void setLiveContentGradient(int width) {
        List<Float> positions;
        List<Integer> colors;
        List listReversed;
        Integer num = this.hugeSpace;
        Intrinsics.checkNotNull(num);
        int iIntValue = num.intValue();
        GradientStyle gradientStyle = this.styleSheet.getGradientStyle(GradientStyleKey.TRANSPARENT_GRADIENT);
        int[] intArray = null;
        float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
        if (gradientStyle != null && (colors = gradientStyle.getColors()) != null && (listReversed = CollectionsKt___CollectionsKt.reversed(colors)) != null) {
            intArray = CollectionsKt___CollectionsKt.toIntArray(listReversed);
        }
        int[] iArr = intArray;
        if (floatArray == null || iArr == null) {
            return;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, iIntValue, Bitmap.Config.ARGB_8888);
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_3);
        Intrinsics.checkNotNull(color);
        bitmap.eraseColor(color.intValue());
        TransparentGradientImageView transparentGradientImageView = getTransparentGradientImageView();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        TransparentGradientImageView.applyTransparentGradientToBottom$default(transparentGradientImageView, bitmap, floatArray, iArr, width, iIntValue, false, 32, null);
        getTransparentGradientImageView().invalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        this.mIsEnabled = isEnabled;
        if (!isEnabled) {
            getDisabledMask().setVisibility(0);
        } else {
            getDisabledMask().setVisibility(8);
        }
    }

    public final CharSequence getPrimaryTitle() {
        return getPrimaryTextView().getText();
    }

    public final void setPrimaryTitle(CharSequence text) {
        setOrHideText(getPrimaryTextView(), text);
    }

    public final CharSequence getSecondaryTitle() {
        return getSecondaryTextView().getText();
    }

    public final void setSecondaryTitle(CharSequence text) {
        setOrHideText(getSecondaryTextView(), text);
    }

    public final CharSequence getTertiaryTitle() {
        return getTertiaryTextView().getText();
    }

    public final void setTertiaryTitle(CharSequence text) {
        setOrHideText(getTertiaryTextView(), text);
    }

    private final void setOrHideText(TextView textView, CharSequence text) {
        if (text == null) {
            textView.setVisibility(4);
        } else {
            textView.setText(text);
            textView.setVisibility(0);
        }
    }
}
