package com.amazon.music.views.library.binders;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.views.FeaturePlayView;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1 implements ImageLoader.ImageLoaderCallback {
    final /* synthetic */ FeaturePlayView $view;
    final /* synthetic */ FeaturePlayBinder this$0;

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }

    FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1(FeaturePlayView featurePlayView, FeaturePlayBinder featurePlayBinder) {
        this.$view = featurePlayView;
        this.this$0 = featurePlayBinder;
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(final Bitmap bitmap) {
        final FeaturePlayView featurePlayView = this.$view;
        final FeaturePlayBinder featurePlayBinder = this.this$0;
        featurePlayView.post(new Runnable() { // from class: com.amazon.music.views.library.binders.FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1.m2305onBitmapLoaded$lambda2(bitmap, featurePlayView, featurePlayBinder);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-2, reason: not valid java name */
    public static final void m2305onBitmapLoaded$lambda2(Bitmap bitmap, FeaturePlayView view, FeaturePlayBinder this$0) {
        List<Float> positions;
        List<Integer> colors;
        List<Float> positions2;
        List<Integer> colors2;
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bitmap == null) {
            return;
        }
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            GradientStyle gradientStyle = this$0.styleSheet.getGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_BACKGROUND_TOP);
            Bitmap colorOverlayBitmap = null;
            float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
            int[] intArray = (gradientStyle == null || (colors = gradientStyle.getColors()) == null) ? null : CollectionsKt___CollectionsKt.toIntArray(colors);
            GradientStyle gradientStyle2 = this$0.styleSheet.getGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_BACKGROUND_BOTTOM);
            float[] floatArray2 = (gradientStyle2 == null || (positions2 = gradientStyle2.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions2);
            int[] intArray2 = (gradientStyle2 == null || (colors2 = gradientStyle2.getColors()) == null) ? null : CollectionsKt___CollectionsKt.toIntArray(colors2);
            if (floatArray == null || floatArray2 == null || intArray2 == null || intArray == null) {
                return;
            }
            view.getHeroImageBackgroundView().setVisibility(0);
            int dimension = height - ((int) view.getContext().getResources().getDimension(R$dimen.feature_play_hero_image_height_cropped));
            Integer color = this$0.styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_2);
            if (color != null) {
                colorOverlayBitmap = BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(bitmap, color.intValue());
            }
            view.getHeroImageBackgroundView().applyTransparentGradientToTopAndBottom(colorOverlayBitmap == null ? bitmap : colorOverlayBitmap, floatArray, floatArray2, intArray, intArray2, width, dimension, (int) view.getContext().getResources().getDimension(R$dimen.feature_play_hero_image_background_top_gradient));
            view.getHeroImageBackgroundView().invalidate();
        } catch (Exception e) {
            this$0.LOG.error(e.getMessage(), (Throwable) e);
        }
    }
}
