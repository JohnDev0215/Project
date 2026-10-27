package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder;
import com.amazon.music.views.library.views.ThumbnailCellView;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: ThumbnailNavigatorItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/ThumbnailNavigatorItemBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/ThumbnailNavigatorItemElement;", "Lcom/amazon/music/views/library/views/ThumbnailCellView;", "()V", "picassoImageLoader", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "kotlin.jvm.PlatformType", "getPicassoImageLoader", "()Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "picassoImageLoader$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "width", "", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThumbnailNavigatorItemBinder extends ItemElementBinder<ThumbnailNavigatorItemElement, ThumbnailCellView> {

    /* JADX INFO: renamed from: picassoImageLoader$delegate, reason: from kotlin metadata */
    private final Lazy picassoImageLoader = LazyKt__LazyJVMKt.lazy(new Function0<PicassoImageLoader>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder$picassoImageLoader$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PicassoImageLoader invoke() {
            return PicassoImageLoader.builder(this.this$0.getContext(), Picasso.get()).withTag(this.this$0.getContext()).build();
        }
    });

    private final PicassoImageLoader getPicassoImageLoader() {
        return (PicassoImageLoader) this.picassoImageLoader.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(final ThumbnailNavigatorItemElement element, ThumbnailCellView view, int width) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        String strPrimaryText = element.primaryText();
        Intrinsics.checkNotNullExpressionValue(strPrimaryText, "element.primaryText()");
        view.setTitle(strPrimaryText);
        boolean z = true;
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ThumbnailNavigatorItemBinder.m2245bind$lambda0(this.f$0, element, view2);
            }
        });
        String strImage = element.image();
        view.setTag(strImage);
        if (strImage != null && !StringsKt__StringsJVMKt.isBlank(strImage)) {
            z = false;
        }
        if (z) {
            return;
        }
        getPicassoImageLoader().loadImage(strImage, new AnonymousClass2(view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2245bind$lambda0(ThumbnailNavigatorItemBinder this$0, ThumbnailNavigatorItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    /* JADX INFO: renamed from: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder$bind$2, reason: invalid class name */
    /* JADX INFO: compiled from: ThumbnailNavigatorItemBinder.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/skyfire/ui/widgets/binders/ThumbnailNavigatorItemBinder$bind$2", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "drawable", "Landroid/graphics/drawable/Drawable;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass2 implements ImageLoader.ImageLoaderCallback {
        final /* synthetic */ ThumbnailCellView $view;

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onPrepareLoad(Drawable drawable) {
        }

        AnonymousClass2(ThumbnailCellView thumbnailCellView) {
            this.$view = thumbnailCellView;
        }

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onBitmapLoaded(final Bitmap bitmap) {
            final ThumbnailCellView thumbnailCellView = this.$view;
            thumbnailCellView.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder$bind$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ThumbnailNavigatorItemBinder.AnonymousClass2.m2246onBitmapLoaded$lambda1(bitmap, thumbnailCellView);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBitmapLoaded$lambda-1, reason: not valid java name */
        public static final void m2246onBitmapLoaded$lambda1(Bitmap bitmap, ThumbnailCellView view) {
            Intrinsics.checkNotNullParameter(view, "$view");
            if (bitmap == null) {
                return;
            }
            view.setBackgroundImage(bitmap);
        }
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public ThumbnailCellView createView(ViewGroup parent) {
        return new ThumbnailCellView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(ThumbnailNavigatorItemElement element, ThumbnailCellView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view, -1);
    }
}
