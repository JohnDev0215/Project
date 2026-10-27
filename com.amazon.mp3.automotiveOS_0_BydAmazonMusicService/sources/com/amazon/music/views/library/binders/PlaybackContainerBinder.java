package com.amazon.music.views.library.binders;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.binders.PlaybackContainerBinder;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.music.views.library.views.TransparentGradientImageView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackContainerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001d\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/binders/PlaybackContainerBinder;", "", "()V", "bindPlaybackContainerStyle", "", "view", "Lcom/amazon/music/views/library/views/SectionWidget;", "model", "Lcom/amazon/music/views/library/models/ShovelerModel;", "getGradientImageCallback", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "reset", "setPlaybackContainerHeaderStyling", "styleTopResultsPlaybackContainer", "color", "", "(Lcom/amazon/music/views/library/views/SectionWidget;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackContainerBinder {
    public final void bindPlaybackContainerStyle(SectionWidget view, ShovelerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        String title = model.getTitle();
        if (title != null) {
            view.setSubtitle(title);
        }
        String subtitle = model.getSubtitle();
        if (subtitle != null) {
            view.setTitle(subtitle);
        }
        setPlaybackContainerHeaderStyling(view);
    }

    private final void setPlaybackContainerHeaderStyling(SectionWidget view) {
        StyleSheet styleSheet = view.getStyleSheet();
        SpacerKey spacerKey = SpacerKey.MEDIUM;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(spacerKey);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        SectionWidget.setPlaybackContainerBodyMargins$default(view, Integer.valueOf(iIntValue), null, Integer.valueOf(iIntValue), null, 10, null);
        SectionWidget.setPlaybackContainerHeaderMargins$default(view, Integer.valueOf(iIntValue), null, Integer.valueOf(iIntValue), null, 10, null);
        view.showImage(true);
        view.showBackground(true);
        view.getHeaderContainer().setPadding(0, 0, 0, 0);
        Integer spacerInPixels2 = view.getStyleSheet().getSpacerInPixels(spacerKey);
        view.getTitleTextView().setPadding(0, spacerInPixels2 == null ? 0 : spacerInPixels2.intValue(), 0, 0);
        FontStyle fontStyle = view.getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(view.getSubtitleTextView(), fontStyle);
        }
        FontStyle fontStyle2 = view.getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(view.getTitleTextView(), fontStyle2);
        }
        Integer color = view.getStyleSheet().getColor(ColorKey.COLOR_ACCENT);
        if (color != null) {
            view.getTitleTextView().setTextColor(color.intValue());
        }
        Integer color2 = view.getStyleSheet().getColor(ColorKey.COLOR_PRIMARY);
        if (color2 != null) {
            view.getSubtitleTextView().setTextColor(color2.intValue());
        }
        view.getSeeMoreButton().setIcon(view.getContext().getResources().getDrawable(R$drawable.ic_chevron_right));
        SectionWidget.setMargins$default(view, null, null, 0, null, view.getSeeMoreButton(), 11, null);
        view.getSeeMoreButton().setVisibility(0);
    }

    public final void reset(SectionWidget view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.initViewStyling();
        view.getTitleTextView().setPadding(0, 0, 0, 0);
        SectionWidget.setMargins$default(view, null, null, null, null, view.getArtworkFrameLayout(), 15, null);
        view.showImage(false);
        view.showBackground(false);
    }

    public final ImageLoader.ImageLoaderCallback getGradientImageCallback(SectionWidget view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return new AnonymousClass1(view, view.getArtworkFrameLayout());
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.PlaybackContainerBinder$getGradientImageCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaybackContainerBinder.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/PlaybackContainerBinder$getGradientImageCallback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements ImageLoader.ImageLoaderCallback {
        final /* synthetic */ TransparentGradientImageView $artworkFrameView;
        final /* synthetic */ SectionWidget $view;

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }

        AnonymousClass1(SectionWidget sectionWidget, TransparentGradientImageView transparentGradientImageView) {
            this.$view = sectionWidget;
            this.$artworkFrameView = transparentGradientImageView;
        }

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onBitmapLoaded(final Bitmap bitmap) {
            final SectionWidget sectionWidget = this.$view;
            final TransparentGradientImageView transparentGradientImageView = this.$artworkFrameView;
            sectionWidget.post(new Runnable() { // from class: com.amazon.music.views.library.binders.PlaybackContainerBinder$getGradientImageCallback$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlaybackContainerBinder.AnonymousClass1.m2353onBitmapLoaded$lambda3(transparentGradientImageView, bitmap, sectionWidget);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBitmapLoaded$lambda-3, reason: not valid java name */
        public static final void m2353onBitmapLoaded$lambda3(TransparentGradientImageView artworkFrameView, Bitmap bitmap, SectionWidget view) {
            List<Float> positions;
            List<Integer> colors;
            Intrinsics.checkNotNullParameter(artworkFrameView, "$artworkFrameView");
            Intrinsics.checkNotNullParameter(view, "$view");
            int height = artworkFrameView.getHeight();
            int width = artworkFrameView.getWidth();
            if (bitmap == null) {
                return;
            }
            GradientStyle gradientStyle = view.getStyleSheet().getGradientStyle(GradientStyleKey.PLAYBACK_CONTAINER_GRADIENT);
            float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
            int[] intArray = (gradientStyle == null || (colors = gradientStyle.getColors()) == null) ? null : CollectionsKt___CollectionsKt.toIntArray(colors);
            if (floatArray != null && intArray != null) {
                Integer color = view.getStyleSheet().getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_2);
                Bitmap colorOverlayBitmap = color != null ? BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(bitmap, color.intValue()) : null;
                TransparentGradientImageView.applyTransparentGradientToLeft$default(view.getArtworkFrameLayout(), colorOverlayBitmap == null ? bitmap : colorOverlayBitmap, floatArray, intArray, width, height, false, 32, null);
                view.getArtworkFrameLayout().invalidate();
            }
            Integer cornerSize = view.getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM);
            if (cornerSize == null) {
                return;
            }
            view.getArtworkFrameLayout().setCornerRadius(cornerSize.intValue());
        }
    }

    public final void styleTopResultsPlaybackContainer(SectionWidget view, Integer color) {
        Intrinsics.checkNotNullParameter(view, "view");
        Integer spacerInPixels = view.getStyleSheet().getSpacerInPixels(SpacerKey.MINI);
        SectionWidget.setMargins$default(view, null, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, view.getArtworkFrameLayout(), 13, null);
        if (color != null) {
            view.getSubtitleTextView().setTextColor(color.intValue());
        }
        view.showImage(true);
        view.showBackground(true);
    }
}
