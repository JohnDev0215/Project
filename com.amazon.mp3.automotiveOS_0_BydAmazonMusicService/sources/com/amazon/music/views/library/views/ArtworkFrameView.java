package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtworkFrameView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\b\u0017\u0012\u0006\u0010-\u001a\u00020,\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010L\u0012\b\b\u0002\u0010N\u001a\u00020\u000e¢\u0006\u0004\bO\u0010PJ\u001c\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eJ\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u000eJ\u0017\u0010\u0019\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bJ\u0018\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u0010J\u0017\u0010\u001f\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u001f\u0010\u001aJ\u0017\u0010 \u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b \u0010\u001aJ\u0016\u0010#\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\u0006J\u000e\u0010&\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010)\u001a\u00020\u00062\u0006\u0010(\u001a\u00020'J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010+\u001a\u00020*J\u0010\u0010.\u001a\u00020\u00062\u0006\u0010-\u001a\u00020,H\u0002J$\u00102\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u00101\u001a\u000200H\u0002J\b\u00103\u001a\u00020\u0006H\u0002J\u0010\u00104\u001a\u00020\u00062\u0006\u0010+\u001a\u00020*H\u0002R\"\u00106\u001a\u0002058\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010?\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b?\u0010>R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bC\u0010>R\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bE\u0010FR\u0018\u0010G\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010K¨\u0006Q"}, d2 = {"Lcom/amazon/music/views/library/views/ArtworkFrameView;", "Landroid/widget/FrameLayout;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/keys/IconStyleKey;", "playIconStyleKey", "", "initStyle", "Lcom/amazon/music/views/library/styles/keys/IndicatorSizeKey;", "sizeKey", "setIndicatorSize", "Landroid/graphics/drawable/Drawable;", "drawable", "setArtworkImageDrawable", "", "resId", "", "usePost", "setArtworkImageResource", "width", "height", "setArtworkSize", "sashResource", "setSash", "playIconType", "setPlayIcon", "(Ljava/lang/Integer;)V", "gravity", "isInHorizontalTileView", "setPlayIconGravity", "playState", "setPlayState", "setPodcastPlayState", "size", "color", "setPlayNotificationIconStyle", "disablePlayNotificationIcon", "show", "enableStationBadge", "Landroid/view/View$OnClickListener;", "onClickListener", "setPlayIconClickListener", "Lcom/amazon/ui/foundations/styles/ImageSize;", "imageSize", "Landroid/content/Context;", "context", "init", "iconStyleKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconSizeKey", "initPlayIconBuilder", "updatePlayIcon", "setOverlayRadius", "Lcom/amazon/ui/foundations/views/BaseImage;", "artworkImageView", "Lcom/amazon/ui/foundations/views/BaseImage;", "getArtworkImageView", "()Lcom/amazon/ui/foundations/views/BaseImage;", "setArtworkImageView", "(Lcom/amazon/ui/foundations/views/BaseImage;)V", "Landroid/widget/ImageView;", "mSashImage", "Landroid/widget/ImageView;", "onPlayingDimOverlay", "Lcom/amazon/ui/foundations/views/BaseButton;", "mPlayIcon", "Lcom/amazon/ui/foundations/views/BaseButton;", "mArtworkStationBadge", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mPlayNotificationIcon", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mPlayIconType", "Ljava/lang/Integer;", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "mPlayIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ArtworkFrameView extends FrameLayout {
    public BaseImage artworkImageView;
    private ImageView mArtworkStationBadge;
    private BaseButton mPlayIcon;
    private BaseButton.StyleBuilder mPlayIconBuilder;
    private Integer mPlayIconType;
    private PlayNotificationIcon mPlayNotificationIcon;
    private ImageView mSashImage;
    private ImageView onPlayingDimOverlay;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ArtworkFrameView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmOverloads
    public final void initStyle(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        initStyle$default(this, styleSheet, null, 2, null);
    }

    public final BaseImage getArtworkImageView() {
        BaseImage baseImage = this.artworkImageView;
        if (baseImage != null) {
            return baseImage;
        }
        Intrinsics.throwUninitializedPropertyAccessException("artworkImageView");
        return null;
    }

    public final void setArtworkImageView(BaseImage baseImage) {
        Intrinsics.checkNotNullParameter(baseImage, "<set-?>");
        this.artworkImageView = baseImage;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ArtworkFrameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    public /* synthetic */ ArtworkFrameView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void init(Context context) {
        View.inflate(context, R$layout.dmmviewlibrary_artwork_view_layout, this);
        View viewFindViewById = findViewById(R$id.artwork);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<BaseImage>(R.id.artwork)");
        setArtworkImageView((BaseImage) viewFindViewById);
        View viewFindViewById2 = findViewById(R$id.Sash);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.mSashImage = (ImageView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R$id.DimmedArtworkOverlay);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById<ImageView>(R.id.DimmedArtworkOverlay)");
        this.onPlayingDimOverlay = (ImageView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R$id.artwork_station_badge);
        if (viewFindViewById4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.mArtworkStationBadge = (ImageView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R$id.PlaystateIcon);
        if (viewFindViewById5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.PlayNotificationIcon");
        }
        this.mPlayNotificationIcon = (PlayNotificationIcon) viewFindViewById5;
        View viewFindViewById6 = findViewById(R$id.play_icon);
        if (viewFindViewById6 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
        }
        this.mPlayIcon = (BaseButton) viewFindViewById6;
    }

    public static /* synthetic */ void initStyle$default(ArtworkFrameView artworkFrameView, StyleSheet styleSheet, IconStyleKey iconStyleKey, int i, Object obj) {
        if ((i & 2) != 0) {
            iconStyleKey = null;
        }
        artworkFrameView.initStyle(styleSheet, iconStyleKey);
    }

    @JvmOverloads
    public final void initStyle(StyleSheet styleSheet, IconStyleKey playIconStyleKey) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        setIndicatorSize(styleSheet, IndicatorSizeKey.LARGE);
        initPlayIconBuilder$default(this, styleSheet, playIconStyleKey, null, 4, null);
    }

    static /* synthetic */ void initPlayIconBuilder$default(ArtworkFrameView artworkFrameView, StyleSheet styleSheet, IconStyleKey iconStyleKey, IconSizeKey iconSizeKey, int i, Object obj) {
        if ((i & 4) != 0) {
            iconSizeKey = IconSizeKey.MICRO;
        }
        artworkFrameView.initPlayIconBuilder(styleSheet, iconStyleKey, iconSizeKey);
    }

    private final void initPlayIconBuilder(StyleSheet styleSheet, IconStyleKey iconStyleKey, IconSizeKey iconSizeKey) {
        Drawable drawable;
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (iconStyleKey != null && (drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_play_bauhaus)) != null && (iconBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey)) != null) {
            styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        }
        this.mPlayIconBuilder = styleBuilderWithIcon;
    }

    public final void setIndicatorSize(StyleSheet styleSheet, IndicatorSizeKey sizeKey) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(sizeKey, "sizeKey");
        BaseButton.StyleBuilder indicatorBuilder = styleSheet.getIndicatorBuilder(sizeKey, IndicatorStyleKey.PLAYBACK);
        if (indicatorBuilder == null) {
            return;
        }
        BaseButton baseButton = this.mPlayIcon;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton = null;
        }
        indicatorBuilder.applyStyle(baseButton);
    }

    public final void setArtworkImageDrawable(Drawable drawable) {
        getArtworkImageView().getImage().setImageDrawable(drawable);
    }

    public final void setArtworkImageResource(final int resId, boolean usePost) {
        final ImageView image = getArtworkImageView().getImage();
        if (image instanceof ImageView) {
            image.setImageResource(resId);
        } else {
            image.setBackgroundResource(resId);
        }
        if (usePost) {
            image.post(new Runnable() { // from class: com.amazon.music.views.library.views.ArtworkFrameView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ArtworkFrameView.m2389setArtworkImageResource$lambda3(image, resId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setArtworkImageResource$lambda-3, reason: not valid java name */
    public static final void m2389setArtworkImageResource$lambda3(ImageView imageView, int i) {
        Intrinsics.checkNotNullParameter(imageView, "$imageView");
        imageView.setImageResource(i);
    }

    public final void setArtworkSize(int width, int height) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getArtworkImageView(), width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    public final void setSash(int sashResource) {
        ImageView imageView = this.mSashImage;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSashImage");
            imageView = null;
        }
        imageView.setImageResource(sashResource);
        ImageView imageView3 = this.mSashImage;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSashImage");
        } else {
            imageView2 = imageView3;
        }
        imageView2.setVisibility(0);
    }

    public final void setPlayIcon(Integer playIconType) {
        this.mPlayIconType = playIconType;
        updatePlayIcon();
    }

    public final void setPlayIcon(Drawable drawable) {
        BaseButton baseButton;
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        BaseButton baseButton2 = this.mPlayIcon;
        BaseButton baseButton3 = null;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton = null;
        } else {
            baseButton = baseButton2;
        }
        BaseButton.updateState$default(baseButton, null, drawable, null, 5, null);
        BaseButton baseButton4 = this.mPlayIcon;
        if (baseButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
        } else {
            baseButton3 = baseButton4;
        }
        baseButton3.setVisibility(0);
    }

    private final void updatePlayIcon() {
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Integer num = this.mPlayIconType;
        BaseButton baseButton = null;
        if (num == null) {
            BaseButton baseButton2 = this.mPlayIcon;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            } else {
                baseButton = baseButton2;
            }
            baseButton.setVisibility(8);
            return;
        }
        boolean z = true;
        if (num != null && num.intValue() == 1) {
            drawable = getContext().getResources().getDrawable(R$drawable.ic_playback_station);
        } else {
            if ((num == null || num.intValue() != 2) && (num == null || num.intValue() != 3)) {
                z = false;
            }
            drawable = z ? getContext().getResources().getDrawable(R$drawable.ic_playback_shuffle) : getContext().getResources().getDrawable(R$drawable.ic_play_bauhaus);
        }
        BaseButton baseButton3 = this.mPlayIcon;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton3 = null;
        }
        BaseButton.updateState$default(baseButton3, null, drawable, null, 5, null);
        if (drawable != null && (styleBuilder = this.mPlayIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable)) != null) {
            BaseButton baseButton4 = this.mPlayIcon;
            if (baseButton4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
                baseButton4 = null;
            }
            styleBuilderWithIcon.applyStyle(baseButton4);
        }
        BaseButton baseButton5 = this.mPlayIcon;
        if (baseButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
        } else {
            baseButton = baseButton5;
        }
        baseButton.setVisibility(0);
    }

    public static /* synthetic */ void setPlayIconGravity$default(ArtworkFrameView artworkFrameView, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        artworkFrameView.setPlayIconGravity(i, z);
    }

    public final void setPlayIconGravity(int gravity, boolean isInHorizontalTileView) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, gravity);
        int dimension = (int) getContext().getResources().getDimension(isInHorizontalTileView ? R$dimen.spacer_2 : R$dimen.spacer_8);
        layoutParams.leftMargin = dimension;
        layoutParams.bottomMargin = dimension;
        BaseButton baseButton = this.mPlayIcon;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton = null;
        }
        baseButton.setLayoutParams(layoutParams);
    }

    public final void setPlayState(Integer playState) {
        ImageView imageView = null;
        if (playState == null || playState.intValue() == 0) {
            updatePlayIcon();
            PlayNotificationIcon playNotificationIcon = this.mPlayNotificationIcon;
            if (playNotificationIcon == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
                playNotificationIcon = null;
            }
            playNotificationIcon.setVisibility(8);
            ImageView imageView2 = this.onPlayingDimOverlay;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
            } else {
                imageView = imageView2;
            }
            imageView.setVisibility(8);
            return;
        }
        BaseButton baseButton = this.mPlayIcon;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton = null;
        }
        baseButton.setVisibility(8);
        PlayNotificationIcon playNotificationIcon2 = this.mPlayNotificationIcon;
        if (playNotificationIcon2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            playNotificationIcon2 = null;
        }
        playNotificationIcon2.setIsPlaying(playState.intValue() == 2);
        PlayNotificationIcon playNotificationIcon3 = this.mPlayNotificationIcon;
        if (playNotificationIcon3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            playNotificationIcon3 = null;
        }
        playNotificationIcon3.setVisibility(0);
        ImageView imageView3 = this.onPlayingDimOverlay;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
        } else {
            imageView = imageView3;
        }
        imageView.setVisibility(0);
    }

    public final void setPodcastPlayState(Integer playState) {
        boolean z = true;
        PlayNotificationIcon playNotificationIcon = null;
        if (((playState != null && playState.intValue() == 3) || (playState != null && playState.intValue() == 4)) || (playState != null && playState.intValue() == 5)) {
            PlayNotificationIcon playNotificationIcon2 = this.mPlayNotificationIcon;
            if (playNotificationIcon2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
                playNotificationIcon2 = null;
            }
            playNotificationIcon2.setVisibility(0);
            ImageView imageView = this.onPlayingDimOverlay;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
                imageView = null;
            }
            imageView.setVisibility(0);
            PlayNotificationIcon playNotificationIcon3 = this.mPlayNotificationIcon;
            if (playNotificationIcon3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            } else {
                playNotificationIcon = playNotificationIcon3;
            }
            playNotificationIcon.setIsPlaying(true);
            return;
        }
        if ((playState == null || playState.intValue() != 2) && (playState == null || playState.intValue() != 6)) {
            z = false;
        }
        if (z) {
            PlayNotificationIcon playNotificationIcon4 = this.mPlayNotificationIcon;
            if (playNotificationIcon4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
                playNotificationIcon4 = null;
            }
            playNotificationIcon4.setVisibility(0);
            ImageView imageView2 = this.onPlayingDimOverlay;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
                imageView2 = null;
            }
            imageView2.setVisibility(0);
            PlayNotificationIcon playNotificationIcon5 = this.mPlayNotificationIcon;
            if (playNotificationIcon5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            } else {
                playNotificationIcon = playNotificationIcon5;
            }
            playNotificationIcon.setIsPlaying(false);
            return;
        }
        PlayNotificationIcon playNotificationIcon6 = this.mPlayNotificationIcon;
        if (playNotificationIcon6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            playNotificationIcon6 = null;
        }
        playNotificationIcon6.setVisibility(8);
        ImageView imageView3 = this.onPlayingDimOverlay;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
            imageView3 = null;
        }
        imageView3.setVisibility(8);
        PlayNotificationIcon playNotificationIcon7 = this.mPlayNotificationIcon;
        if (playNotificationIcon7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
        } else {
            playNotificationIcon = playNotificationIcon7;
        }
        playNotificationIcon.setIsPlaying(false);
    }

    public final void setPlayNotificationIconStyle(int size, int color) {
        PlayNotificationIcon playNotificationIcon = this.mPlayNotificationIcon;
        if (playNotificationIcon == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            playNotificationIcon = null;
        }
        playNotificationIcon.setStyle(Integer.valueOf(size), Integer.valueOf(color));
    }

    public final void disablePlayNotificationIcon() {
        PlayNotificationIcon playNotificationIcon = this.mPlayNotificationIcon;
        if (playNotificationIcon == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayNotificationIcon");
            playNotificationIcon = null;
        }
        playNotificationIcon.setVisibility(8);
    }

    public final void enableStationBadge(boolean show) {
        ImageView imageView = this.mArtworkStationBadge;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mArtworkStationBadge");
            imageView = null;
        }
        imageView.setVisibility(show ? 0 : 8);
    }

    public final void setPlayIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BaseButton baseButton = this.mPlayIcon;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlayIcon");
            baseButton = null;
        }
        baseButton.setOnClickListener(onClickListener);
    }

    public final void setArtworkSize(ImageSize imageSize) {
        Intrinsics.checkNotNullParameter(imageSize, "imageSize");
        getArtworkImageView().setImageSize(imageSize);
        setOverlayRadius(imageSize);
    }

    private final void setOverlayRadius(ImageSize imageSize) {
        ImageView imageView = null;
        final Float fValueOf = imageSize.getCornerRadius() == null ? null : Float.valueOf(r3.intValue());
        if (fValueOf != null) {
            ImageView imageView2 = this.onPlayingDimOverlay;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
            } else {
                imageView = imageView2;
            }
            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.views.ArtworkFrameView.setOverlayRadius.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ImageView imageView3 = ArtworkFrameView.this.onPlayingDimOverlay;
                    ImageView imageView4 = null;
                    if (imageView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
                        imageView3 = null;
                    }
                    imageView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ImageView imageView5 = ArtworkFrameView.this.onPlayingDimOverlay;
                    if (imageView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
                        imageView5 = null;
                    }
                    final Float f = fValueOf;
                    imageView5.setOutlineProvider(new ViewOutlineProvider() { // from class: com.amazon.music.views.library.views.ArtworkFrameView$setOverlayRadius$1$onGlobalLayout$1
                        @Override // android.view.ViewOutlineProvider
                        public void getOutline(View view, Outline outline) {
                            Intrinsics.checkNotNullParameter(view, "view");
                            Intrinsics.checkNotNullParameter(outline, "outline");
                            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), f.floatValue());
                        }
                    });
                    ImageView imageView6 = ArtworkFrameView.this.onPlayingDimOverlay;
                    if (imageView6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onPlayingDimOverlay");
                    } else {
                        imageView4 = imageView6;
                    }
                    imageView4.setClipToOutline(true);
                }
            });
        }
    }
}
