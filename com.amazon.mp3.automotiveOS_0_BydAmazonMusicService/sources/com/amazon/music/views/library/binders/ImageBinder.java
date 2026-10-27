package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.imageloader.transformation.Transformation;
import com.amazon.music.msautils.MsaUrl;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b+\u0010,J*\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J:\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002J*\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0019\u0010\u0016\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J3\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\u0019J0\u0010\u0018\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006J8\u0010\u0018\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rJ(\u0010\u001a\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012J\u0017\u0010\u001b\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u0004\u0018\u00010 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0011\u0010*\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lcom/amazon/music/views/library/binders/ImageBinder;", "", "Landroid/view/View;", "view", "", ImagesContract.URL, "", "placeholderId", "Lcom/amazon/music/imageloader/ImageLoader$TransformationType;", "transformationType", "", "loadImageWithUrl", "imageRotation", "", "isCropped", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "callback", "parentView", "Lcom/amazon/music/imageloader/transformation/Transformation;", "transformation", "getImageUrl", "contentType", "getTransformationType", "(Ljava/lang/Integer;)Lcom/amazon/music/imageloader/ImageLoader$TransformationType;", "bind", "(Landroid/view/View;Ljava/lang/String;Ljava/lang/Integer;I)V", "loadImage", "getPlaceholderIdByContentType", "(Ljava/lang/Integer;)I", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "getImageLoader", "()Lcom/amazon/music/imageloader/ImageLoader;", "getDefaultPlaceHolderId", "()I", "defaultPlaceHolderId", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ImageBinder {
    private final Context context;
    private final ImageLoader imageLoader;
    private final StyleSheet styleSheet;

    public ImageBinder(Context context, StyleSheet styleSheet, ImageLoader imageLoader) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        this.context = context;
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
    }

    public final ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public final int getDefaultPlaceHolderId() {
        return R$drawable.placeholder;
    }

    public static /* synthetic */ void bind$default(ImageBinder imageBinder, View view, String str, Integer num, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = imageBinder.getPlaceholderIdByContentType(num);
        }
        imageBinder.bind(view, str, num, i);
    }

    public final void bind(final View view, final String url, Integer contentType, final int placeholderId) {
        Intrinsics.checkNotNullParameter(view, "view");
        final ImageLoader.TransformationType transformationType = getTransformationType(contentType);
        if (view instanceof ImageView) {
            if (contentType != null && contentType.intValue() == 14) {
                ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            ((ImageView) view).setImageResource(placeholderId);
        } else {
            view.setBackgroundResource(placeholderId);
        }
        if (TextUtils.isEmpty(url)) {
            return;
        }
        view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.ImageBinder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ImageBinder.m2328bind$lambda0(view, this, url, placeholderId, transformationType);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2328bind$lambda0(final View view, final ImageBinder this$0, final String str, final int i, final ImageLoader.TransformationType transformationType) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(transformationType, "$transformationType");
        if (view.getWidth() == 0) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.ImageBinder$bind$1$1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    this$0.loadImageWithUrl(view, str, i, transformationType);
                }
            });
        } else {
            this$0.loadImageWithUrl(view, str, i, transformationType);
        }
    }

    public final void bind(String url, View view, int placeholderId, ImageLoader.TransformationType transformationType, int imageRotation) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(transformationType, "transformationType");
        bind(url, view, placeholderId, transformationType, imageRotation, false);
    }

    public final void bind(final String url, final View view, final int placeholderId, final ImageLoader.TransformationType transformationType, final int imageRotation, final boolean isCropped) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(transformationType, "transformationType");
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(placeholderId);
        } else {
            view.setBackgroundResource(placeholderId);
        }
        view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.ImageBinder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageBinder.m2329bind$lambda1(view, this, url, placeholderId, transformationType, imageRotation, isCropped);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2329bind$lambda1(final View view, final ImageBinder this$0, final String str, final int i, final ImageLoader.TransformationType transformationType, final int i2, final boolean z) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(transformationType, "$transformationType");
        if (view.getWidth() == 0) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.ImageBinder$bind$2$1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    this$0.loadImageWithUrl(view, str, i, transformationType, i2, z);
                }
            });
        } else {
            this$0.loadImageWithUrl(view, str, i, transformationType, i2, z);
        }
    }

    public final void loadImage(final String url, final ImageLoader.ImageLoaderCallback callback, final View parentView, final Transformation transformation) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        parentView.post(new Runnable() { // from class: com.amazon.music.views.library.binders.ImageBinder$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ImageBinder.m2330loadImage$lambda6(parentView, this, url, callback, transformation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadImage$lambda-6, reason: not valid java name */
    public static final void m2330loadImage$lambda6(final View parentView, final ImageBinder this$0, final String str, final ImageLoader.ImageLoaderCallback callback, final Transformation transformation) {
        Intrinsics.checkNotNullParameter(parentView, "$parentView");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(transformation, "$transformation");
        if (parentView.getWidth() == 0) {
            parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.ImageBinder$loadImage$4$1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    this$0.loadImageWithUrl(str, callback, parentView, transformation);
                }
            });
        } else {
            this$0.loadImageWithUrl(str, callback, parentView, transformation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadImageWithUrl(View view, String url, int placeholderId, ImageLoader.TransformationType transformationType) {
        this.imageLoader.loadImage(getImageUrl(url, view), view, placeholderId, transformationType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadImageWithUrl(View view, String url, int placeholderId, ImageLoader.TransformationType transformationType, int imageRotation, boolean isCropped) {
        this.imageLoader.loadImage(getImageUrl(url, view), view, placeholderId, transformationType, imageRotation, Boolean.valueOf(isCropped));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadImageWithUrl(String url, ImageLoader.ImageLoaderCallback callback, View parentView, Transformation transformation) {
        this.imageLoader.loadImage(getImageUrl(url, parentView), callback, transformation);
    }

    private final String getImageUrl(String url, View view) {
        return (url != null && MsaUrl.isMsaUrl(url)) ? MsaUrl.with(url).scaleToWidth(view.getWidth()).toUrl() : url;
    }

    public final int getPlaceholderIdByContentType(Integer contentType) {
        return (contentType != null && contentType.intValue() == 0) ? R$drawable.album_placeholder : (contentType != null && contentType.intValue() == 4) ? R$drawable.artist_placeholder : (contentType != null && contentType.intValue() == 1) ? R$drawable.playlist_placeholder : (contentType != null && contentType.intValue() == 3) ? R$drawable.station_placeholder : (contentType != null && contentType.intValue() == 2) ? R$drawable.song_placeholder : (contentType != null && contentType.intValue() == 5) ? R$drawable.genre_placeholder : (contentType != null && contentType.intValue() == 7) ? R$drawable.videofallback : (contentType != null && contentType.intValue() == 8) ? R$drawable.videofallback : (contentType != null && contentType.intValue() == 13) ? R$drawable.merch_placeholder : (contentType != null && contentType.intValue() == 10) ? R$drawable.ic_navigation_search_filled : (contentType != null && contentType.intValue() == 14) ? R$drawable.video_stories_placeholder : R$drawable.placeholder;
    }

    private final ImageLoader.TransformationType getTransformationType(Integer contentType) {
        return (contentType != null && contentType.intValue() == 4) ? ImageLoader.TransformationType.Circle : (contentType != null && contentType.intValue() == 6) ? ImageLoader.TransformationType.Circle : ImageLoader.TransformationType.RoundCorner;
    }
}
