package com.amazon.music.views.library.binders;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractVerticalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/AbstractVerticalTileBinder$loadSourceLogoImage$callback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractVerticalTileBinder$loadSourceLogoImage$callback$1 implements ImageLoader.ImageLoaderCallback {
    final /* synthetic */ float $height;
    final /* synthetic */ ImageView $sourceLogo;

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }

    AbstractVerticalTileBinder$loadSourceLogoImage$callback$1(ImageView imageView, float f) {
        this.$sourceLogo = imageView;
        this.$height = f;
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(final Bitmap bitmap) {
        final ImageView imageView = this.$sourceLogo;
        final float f = this.$height;
        imageView.post(new Runnable() { // from class: com.amazon.music.views.library.binders.AbstractVerticalTileBinder$loadSourceLogoImage$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AbstractVerticalTileBinder$loadSourceLogoImage$callback$1.m2257onBitmapLoaded$lambda1(bitmap, f, imageView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-1, reason: not valid java name */
    public static final void m2257onBitmapLoaded$lambda1(Bitmap bitmap, float f, ImageView sourceLogo) {
        Intrinsics.checkNotNullParameter(sourceLogo, "$sourceLogo");
        if (bitmap == null) {
            return;
        }
        sourceLogo.setImageBitmap(BitmapEffectUtils.INSTANCE.getScaledBitmap(bitmap, (bitmap.getWidth() / bitmap.getHeight()) * f, f));
    }
}
