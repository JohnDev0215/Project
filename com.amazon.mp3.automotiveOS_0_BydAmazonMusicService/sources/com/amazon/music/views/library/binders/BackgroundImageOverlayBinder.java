package com.amazon.music.views.library.binders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.imageloader.transformation.Transformation;
import com.amazon.music.views.library.binders.BackgroundImageOverlayBinder;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: BackgroundImageOverlayBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\bJ\u0006\u0010\u0017\u001a\u00020\u0012J\"\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u001f\u001a\u00020\u00122\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J.\u0010\"\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0014R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/views/library/binders/BackgroundImageOverlayBinder;", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "colorOverlayColor", "", "(Lcom/amazon/music/views/library/binders/ImageBinder;Ljava/lang/Integer;)V", "TAG", "", "artworkImageView", "Landroid/widget/ImageView;", "Ljava/lang/Integer;", "gradientBackgroundBitmap", "Landroid/graphics/Bitmap;", "gradientBackgroundImageView", "lockableGradientBackgroundBitmap", "lockableGradientBackgroundImageView", "bindBackgroundViewWithImageOverlay", "", "artistArtworkLayout", "Landroid/view/View;", "artistArtwork", "imageUrl", "clearBitmaps", "getGradientBitmap", "width", "height", "style", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "onBitmapLoaded", "artworkBitmap", "onPrepareLoad", "p0", "Landroid/graphics/drawable/Drawable;", "setupGradientBackgroundOverlay", "gradientStyle", "lockableGradientBackgroundStyle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BackgroundImageOverlayBinder implements ImageLoader.ImageLoaderCallback {
    private String TAG;
    private ImageView artworkImageView;
    private final Integer colorOverlayColor;
    private Bitmap gradientBackgroundBitmap;
    private ImageView gradientBackgroundImageView;
    private final ImageBinder imageBinder;
    private Bitmap lockableGradientBackgroundBitmap;
    private ImageView lockableGradientBackgroundImageView;

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable p0) {
    }

    public BackgroundImageOverlayBinder(ImageBinder imageBinder, Integer num) {
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        this.imageBinder = imageBinder;
        this.colorOverlayColor = num;
        this.TAG = Reflection.getOrCreateKotlinClass(BackgroundImageOverlayBinder.class).getSimpleName();
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(Bitmap artworkBitmap) {
        if (artworkBitmap == null) {
            Log.e(this.TAG, "Can't load artworkBitmap with overlay: artworkBitmap == null.");
            return;
        }
        ImageView imageView = this.artworkImageView;
        if (imageView == null) {
            return;
        }
        imageView.setImageBitmap(artworkBitmap);
    }

    public final void bindBackgroundViewWithImageOverlay(View artistArtworkLayout, View artistArtwork, final String imageUrl) {
        Intrinsics.checkNotNullParameter(artistArtworkLayout, "artistArtworkLayout");
        Intrinsics.checkNotNullParameter(artistArtwork, "artistArtwork");
        this.artworkImageView = (ImageView) artistArtwork;
        final int i = Resources.getSystem().getDisplayMetrics().heightPixels;
        final int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
        this.imageBinder.loadImage(imageUrl, this, artistArtworkLayout, new Transformation() { // from class: com.amazon.music.views.library.binders.BackgroundImageOverlayBinder.bindBackgroundViewWithImageOverlay.1
            @Override // com.amazon.music.imageloader.transformation.Transformation
            public String key() {
                StringBuilder sb = new StringBuilder();
                sb.append((Object) BackgroundImageOverlayBinder.this.TAG);
                sb.append(':');
                sb.append(i2);
                sb.append('x');
                sb.append(i);
                sb.append('-');
                sb.append((Object) imageUrl);
                return sb.toString();
            }

            @Override // com.amazon.music.imageloader.transformation.Transformation
            public Bitmap transform(Bitmap sourceBitmap) {
                Bitmap widthScaledBitmapWithHorizontalCrop;
                if (sourceBitmap == null) {
                    Log.d(BackgroundImageOverlayBinder.this.TAG, "error occurred: sourceBitmap is null while transforming it");
                    return sourceBitmap;
                }
                int i3 = i;
                int i4 = i2;
                if (i3 < i4) {
                    widthScaledBitmapWithHorizontalCrop = BitmapEffectUtils.INSTANCE.getWidthScaledBitmap(sourceBitmap, i4);
                } else {
                    widthScaledBitmapWithHorizontalCrop = BitmapEffectUtils.INSTANCE.getWidthScaledBitmapWithHorizontalCrop(sourceBitmap, i4, i3);
                }
                Integer num = BackgroundImageOverlayBinder.this.colorOverlayColor;
                if (num == null) {
                    return sourceBitmap;
                }
                Bitmap colorOverlayBitmap = BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(widthScaledBitmapWithHorizontalCrop, num.intValue());
                if (!Intrinsics.areEqual(widthScaledBitmapWithHorizontalCrop, sourceBitmap)) {
                    sourceBitmap.recycle();
                }
                if (!Intrinsics.areEqual(colorOverlayBitmap, widthScaledBitmapWithHorizontalCrop)) {
                    widthScaledBitmapWithHorizontalCrop.recycle();
                }
                return colorOverlayBitmap;
            }
        });
    }

    public final void setupGradientBackgroundOverlay(int height, int width, GradientStyle gradientStyle, GradientStyle lockableGradientBackgroundStyle, View artistArtworkLayout) {
        ViewTreeObserver viewTreeObserver;
        Intrinsics.checkNotNullParameter(gradientStyle, "gradientStyle");
        Intrinsics.checkNotNullParameter(lockableGradientBackgroundStyle, "lockableGradientBackgroundStyle");
        Intrinsics.checkNotNullParameter(artistArtworkLayout, "artistArtworkLayout");
        ViewGroup viewGroup = (ViewGroup) artistArtworkLayout;
        this.gradientBackgroundImageView = (ImageView) viewGroup.getChildAt(0);
        this.lockableGradientBackgroundImageView = (ImageView) viewGroup.getChildAt(1);
        ImageView imageView = this.gradientBackgroundImageView;
        if (imageView != null && (viewTreeObserver = imageView.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC04521(width, height, gradientStyle, lockableGradientBackgroundStyle));
        }
        ImageView imageView2 = this.gradientBackgroundImageView;
        if (imageView2 == null) {
            return;
        }
        imageView2.requestLayout();
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.BackgroundImageOverlayBinder$setupGradientBackgroundOverlay$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BackgroundImageOverlayBinder.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/amazon/music/views/library/binders/BackgroundImageOverlayBinder$setupGradientBackgroundOverlay$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewTreeObserverOnGlobalLayoutListenerC04521 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ GradientStyle $gradientStyle;
        final /* synthetic */ int $height;
        final /* synthetic */ GradientStyle $lockableGradientBackgroundStyle;
        final /* synthetic */ int $width;

        ViewTreeObserverOnGlobalLayoutListenerC04521(int i, int i2, GradientStyle gradientStyle, GradientStyle gradientStyle2) {
            this.$width = i;
            this.$height = i2;
            this.$gradientStyle = gradientStyle;
            this.$lockableGradientBackgroundStyle = gradientStyle2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ViewTreeObserver viewTreeObserver;
            ImageView imageView = BackgroundImageOverlayBinder.this.gradientBackgroundImageView;
            if (imageView != null && (viewTreeObserver = imageView.getViewTreeObserver()) != null) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            final BackgroundImageOverlayBinder backgroundImageOverlayBinder = BackgroundImageOverlayBinder.this;
            final int i = this.$width;
            final int i2 = this.$height;
            final GradientStyle gradientStyle = this.$gradientStyle;
            final GradientStyle gradientStyle2 = this.$lockableGradientBackgroundStyle;
            Observable observableObserveOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.views.library.binders.BackgroundImageOverlayBinder$setupGradientBackgroundOverlay$1$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BackgroundImageOverlayBinder.ViewTreeObserverOnGlobalLayoutListenerC04521.m2265onGlobalLayout$lambda0(backgroundImageOverlayBinder, i, i2, gradientStyle, gradientStyle2, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
            final BackgroundImageOverlayBinder backgroundImageOverlayBinder2 = BackgroundImageOverlayBinder.this;
            Action1 action1 = new Action1() { // from class: com.amazon.music.views.library.binders.BackgroundImageOverlayBinder$setupGradientBackgroundOverlay$1$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BackgroundImageOverlayBinder.ViewTreeObserverOnGlobalLayoutListenerC04521.m2266onGlobalLayout$lambda1(backgroundImageOverlayBinder2, (List) obj);
                }
            };
            final BackgroundImageOverlayBinder backgroundImageOverlayBinder3 = BackgroundImageOverlayBinder.this;
            observableObserveOn.subscribe(action1, new Action1() { // from class: com.amazon.music.views.library.binders.BackgroundImageOverlayBinder$setupGradientBackgroundOverlay$1$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BackgroundImageOverlayBinder.ViewTreeObserverOnGlobalLayoutListenerC04521.m2267onGlobalLayout$lambda2(backgroundImageOverlayBinder3, (Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onGlobalLayout$lambda-0, reason: not valid java name */
        public static final void m2265onGlobalLayout$lambda0(BackgroundImageOverlayBinder this$0, int i, int i2, GradientStyle gradientStyle, GradientStyle lockableGradientBackgroundStyle, Subscriber subscriber) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(gradientStyle, "$gradientStyle");
            Intrinsics.checkNotNullParameter(lockableGradientBackgroundStyle, "$lockableGradientBackgroundStyle");
            Intrinsics.checkNotNullParameter(subscriber, "subscriber");
            try {
                this$0.gradientBackgroundBitmap = this$0.getGradientBitmap(i, i2, gradientStyle);
                this$0.lockableGradientBackgroundBitmap = this$0.getGradientBitmap(i, i2, lockableGradientBackgroundStyle);
                subscriber.onNext(CollectionsKt__CollectionsKt.listOf((Object[]) new Bitmap[]{this$0.gradientBackgroundBitmap, this$0.lockableGradientBackgroundBitmap}));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onGlobalLayout$lambda-1, reason: not valid java name */
        public static final void m2266onGlobalLayout$lambda1(BackgroundImageOverlayBinder this$0, List list) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (list == null || list.size() <= 1) {
                return;
            }
            Bitmap bitmap = (Bitmap) list.get(0);
            Bitmap bitmap2 = (Bitmap) list.get(1);
            ImageView imageView = this$0.gradientBackgroundImageView;
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
            ImageView imageView2 = this$0.lockableGradientBackgroundImageView;
            if (imageView2 == null) {
                return;
            }
            imageView2.setImageBitmap(bitmap2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onGlobalLayout$lambda-2, reason: not valid java name */
        public static final void m2267onGlobalLayout$lambda2(BackgroundImageOverlayBinder this$0, Throwable th) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.e(this$0.TAG, '{' + th + ".message}");
        }
    }

    public final void clearBitmaps() {
        Bitmap bitmap;
        Bitmap bitmap2;
        ImageView imageView = this.artworkImageView;
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
        ImageView imageView2 = this.gradientBackgroundImageView;
        if (imageView2 != null) {
            imageView2.setImageBitmap(null);
        }
        ImageView imageView3 = this.lockableGradientBackgroundImageView;
        if (imageView3 != null) {
            imageView3.setImageBitmap(null);
        }
        Bitmap bitmap3 = this.gradientBackgroundBitmap;
        if (((bitmap3 == null || bitmap3.isRecycled()) ? false : true) && (bitmap2 = this.gradientBackgroundBitmap) != null) {
            bitmap2.recycle();
        }
        Bitmap bitmap4 = this.lockableGradientBackgroundBitmap;
        if (!((bitmap4 == null || bitmap4.isRecycled()) ? false : true) || (bitmap = this.lockableGradientBackgroundBitmap) == null) {
            return;
        }
        bitmap.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap getGradientBitmap(int width, int height, GradientStyle style) {
        if (width <= 0 || height <= 0) {
            return null;
        }
        try {
            return BitmapEffectUtils.INSTANCE.createGradientOverlayBitmap(width, height, false, CollectionsKt___CollectionsKt.toFloatArray(style.getPositions()), CollectionsKt___CollectionsKt.toIntArray(style.getColors()));
        } catch (IllegalArgumentException e) {
            Log.e(this.TAG, String.valueOf(e.getMessage()));
            return null;
        }
    }
}
