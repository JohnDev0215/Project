package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.models.ThumbnailCellModel;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ThumbnailCellView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ThumbnailCellBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/binders/ThumbnailCellBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/ThumbnailCellView;", "Lcom/amazon/music/views/library/models/ThumbnailCellModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThumbnailCellBinder implements UniversalBinder<ThumbnailCellView, ThumbnailCellModel> {
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private final ImageLoader imageLoader;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ThumbnailCellModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ThumbnailCellModel.class);
    }

    public ThumbnailCellBinder(StyleSheet styleSheet, ImageLoader imageLoader, BrushUriClickListenerProvider brushUriClickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.brushUriClickListenerProvider = brushUriClickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public ThumbnailCellView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ThumbnailCellView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(ThumbnailCellView view, ThumbnailCellModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }

    public final void bind(final ThumbnailCellView view, final ThumbnailCellModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        String text = model.getText();
        if (text != null) {
            view.setTitle(text);
        }
        view.setTag(model.getBackgroundImageUrl());
        final String target = model.getTarget();
        if (target != null) {
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ThumbnailCellBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ThumbnailCellBinder.m2372bind$lambda2$lambda1(this.f$0, target, model, view2);
                }
            });
        }
        view.updateWidth(width);
        final String backgroundImageUrl = model.getBackgroundImageUrl();
        if (backgroundImageUrl == null) {
            return;
        }
        this.imageLoader.loadImage(backgroundImageUrl, new ImageLoader.ImageLoaderCallback() { // from class: com.amazon.music.views.library.binders.ThumbnailCellBinder$bind$3$1
            @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
            public void onPrepareLoad(Drawable drawable) {
            }

            @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
            public void onBitmapLoaded(Bitmap bitmap) {
                if (!backgroundImageUrl.equals(view.getTag()) || bitmap == null) {
                    return;
                }
                view.setBackgroundImage(bitmap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2372bind$lambda2$lambda1(ThumbnailCellBinder this$0, String target, ThumbnailCellModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        Intrinsics.checkNotNullParameter(model, "$model");
        BrushUriClickListenerProvider.DefaultImpls.onClick$default(this$0.brushUriClickListenerProvider, target, model.getViewReferenceId(), null, 4, null);
    }
}
