package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.views.FeatureBarkerView;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.views.BaseImage;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureBarkerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/FeatureBarkerBinder$loadBackgroundImage$callback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeatureBarkerBinder$loadBackgroundImage$callback$1 implements ImageLoader.ImageLoaderCallback {
    final /* synthetic */ boolean $showOverlay;
    final /* synthetic */ boolean $use16x9Background;
    final /* synthetic */ FeatureBarkerView $view;
    final /* synthetic */ FeatureBarkerBinder this$0;

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }

    FeatureBarkerBinder$loadBackgroundImage$callback$1(FeatureBarkerView featureBarkerView, boolean z, FeatureBarkerBinder featureBarkerBinder, boolean z2) {
        this.$view = featureBarkerView;
        this.$showOverlay = z;
        this.this$0 = featureBarkerBinder;
        this.$use16x9Background = z2;
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(final Bitmap bitmap) {
        final FeatureBarkerView featureBarkerView = this.$view;
        final boolean z = this.$showOverlay;
        final FeatureBarkerBinder featureBarkerBinder = this.this$0;
        final boolean z2 = this.$use16x9Background;
        featureBarkerView.post(new Runnable() { // from class: com.amazon.music.views.library.binders.FeatureBarkerBinder$loadBackgroundImage$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FeatureBarkerBinder$loadBackgroundImage$callback$1.m2300onBitmapLoaded$lambda1(featureBarkerView, bitmap, z, featureBarkerBinder, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-1, reason: not valid java name */
    public static final void m2300onBitmapLoaded$lambda1(FeatureBarkerView view, Bitmap bitmap, boolean z, FeatureBarkerBinder this$0, boolean z2) {
        List<Float> positions;
        List<Integer> colors;
        int[] intArray;
        int[] iArrReversedArray;
        int[] iArr;
        Context context;
        List<Integer> colors2;
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (view.getWidth() <= 0 || view.getHeight() <= 0 || bitmap == null) {
            return;
        }
        Context context2 = null;
        if (z) {
            GradientStyle gradientStyle = this$0.styleSheet.getGradientStyle(GradientStyleKey.FEATURE_BARKER);
            float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
            if (z2) {
                if (gradientStyle != null && (colors2 = gradientStyle.getColors()) != null) {
                    iArrReversedArray = CollectionsKt___CollectionsKt.toIntArray(colors2);
                    iArr = iArrReversedArray;
                }
                iArr = null;
            } else {
                if (gradientStyle != null && (colors = gradientStyle.getColors()) != null && (intArray = CollectionsKt___CollectionsKt.toIntArray(colors)) != null) {
                    iArrReversedArray = ArraysKt___ArraysKt.reversedArray(intArray);
                    iArr = iArrReversedArray;
                }
                iArr = null;
            }
            if (floatArray == null || iArr == null) {
                return;
            }
            BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
            Context context3 = this$0.context;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            } else {
                context = context3;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) bitmapEffectUtils.getGradientOverlayDrawable(context, bitmap, !z2, floatArray, iArr);
            bitmapDrawable.setGravity(48);
            view.getBackgroundImageView().setImage(bitmapDrawable);
            return;
        }
        BaseImage backgroundImageView = view.getBackgroundImageView();
        Context context4 = this$0.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context4;
        }
        backgroundImageView.setImage(new BitmapDrawable(context2.getResources(), bitmap));
    }
}
