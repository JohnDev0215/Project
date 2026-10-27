package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.views.LivestreamBarkerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LivestreamBarkerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1 implements ImageLoader.ImageLoaderCallback {
    final /* synthetic */ Context $context;
    final /* synthetic */ StyleSheet $styleSheet;
    final /* synthetic */ LivestreamBarkerView $view;

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }

    LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1(LivestreamBarkerView livestreamBarkerView, Context context, StyleSheet styleSheet) {
        this.$view = livestreamBarkerView;
        this.$context = context;
        this.$styleSheet = styleSheet;
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(final Bitmap bitmap) {
        final LivestreamBarkerView livestreamBarkerView = this.$view;
        final Context context = this.$context;
        final StyleSheet styleSheet = this.$styleSheet;
        livestreamBarkerView.post(new Runnable() { // from class: com.amazon.music.views.library.binders.LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LivestreamBarkerBinder$Companion$loadBackgroundImage$callback$1.m2340onBitmapLoaded$lambda3(livestreamBarkerView, bitmap, context, styleSheet);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-3, reason: not valid java name */
    public static final void m2340onBitmapLoaded$lambda3(LivestreamBarkerView view, Bitmap bitmap, Context context, StyleSheet styleSheet) {
        Float blurStyle;
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        if (view.getWidth() <= 0 || view.getHeight() <= 0 || bitmap == null) {
            return;
        }
        view.getBackgroundImageView().setImage(new BitmapDrawable(context.getResources(), bitmap));
        if (context.getResources().getConfiguration().orientation == 2 && (blurStyle = styleSheet.getBlurStyle(BlurStyleKey.BLUR_2)) != null) {
            view.getBackgroundImageView().setBlurBitmapBackground(bitmap, blurStyle.floatValue());
        }
        Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize == null) {
            return;
        }
        view.getBackgroundImageView().applyCornerRadius(cornerSize.intValue());
    }
}
