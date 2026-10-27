package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.PicassoKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VideoStoryTileView;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvertedRectangleVerticalItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J \u0010\u001b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/InvertedRectangleVerticalItemBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "Lcom/amazon/music/views/library/views/VideoStoryTileView;", "()V", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "()Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "width", "", "bindArtwork", "artworkFrameLayout", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "clearView", "createView", "parent", "Landroid/view/ViewGroup;", "getPlaceholderId", ContextMappingConstants.IMAGE, "LTouch/WidgetsInterface/v1_0/ImageResources$Image;", "updateSize", "height", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InvertedRectangleVerticalItemBinder extends ItemElementBinder<VerticalItemElement, VideoStoryTileView> {

    /* JADX INFO: renamed from: imageBinder$delegate, reason: from kotlin metadata */
    private final Lazy imageBinder = LazyKt__LazyJVMKt.lazy(new Function0<ImageBinder>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder$imageBinder$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageBinder invoke() {
            Picasso picasso = Picasso.get();
            Intrinsics.checkNotNullExpressionValue(picasso, "get()");
            return PicassoKt.createImageBinder(picasso, this.this$0.getContext(), this.this$0.getStyleSheet());
        }
    });

    private final ImageBinder getImageBinder() {
        return (ImageBinder) this.imageBinder.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public VideoStoryTileView createView(ViewGroup parent) {
        return new VideoStoryTileView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(VerticalItemElement element, VideoStoryTileView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view, view.getTileWidth());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(final VerticalItemElement element, final VideoStoryTileView view, int width) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        clearView(view);
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        final int i = (int) (width / 0.5625f);
        if (width == -2 || width == -1) {
            view.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    InvertedRectangleVerticalItemBinder.m2227bind$lambda0(view, this, i);
                }
            });
        } else {
            updateSize(view, width, i);
        }
        ImageStyle imageStyle = getStyleSheet().getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
        }
        artworkFrameView.getArtworkImageView().setScalingFactor(0.5625f);
        view.getArtworkFrameView().getArtworkImageView().getImage().setBackgroundResource(R$drawable.rounded_corners_story_tile);
        view.getArtworkFrameView().getArtworkImageView().getImage().setClipToOutline(true);
        Integer cornerSize = getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize != null) {
            artworkFrameView.getArtworkImageView().applyCornerRadius(cornerSize.intValue());
        }
        bindArtwork(artworkFrameView, element);
        view.setEnabled(!element.isDisabled());
        String strPrimaryText = element.primaryText();
        Intrinsics.checkNotNullExpressionValue(strPrimaryText, "element.primaryText()");
        view.setTitle(strPrimaryText);
        String strSecondaryText = element.secondaryText();
        if (strSecondaryText == null) {
            strSecondaryText = "";
        }
        view.setSubtitle(strSecondaryText);
        view.setLabel(element.label());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InvertedRectangleVerticalItemBinder.m2228bind$lambda5$lambda3(this.f$0, element, view2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(element.onItemContextSelected(), "element.onItemContextSelected()");
        if (!r7.isEmpty()) {
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return InvertedRectangleVerticalItemBinder.m2229bind$lambda5$lambda4(this.f$0, element, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2227bind$lambda0(final VideoStoryTileView view, final InvertedRectangleVerticalItemBinder this$0, final int i) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder$bind$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                InvertedRectangleVerticalItemBinder invertedRectangleVerticalItemBinder = this$0;
                VideoStoryTileView videoStoryTileView = view;
                invertedRectangleVerticalItemBinder.updateSize(videoStoryTileView, videoStoryTileView.getMeasuredWidth(), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-5$lambda-3, reason: not valid java name */
    public static final void m2228bind$lambda5$lambda3(InvertedRectangleVerticalItemBinder this$0, VerticalItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-5$lambda-4, reason: not valid java name */
    public static final boolean m2229bind$lambda5$lambda4(InvertedRectangleVerticalItemBinder this$0, VerticalItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return true;
        }
        List<Method> listOnItemContextSelected = element.onItemContextSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemContextSelected, "element.onItemContextSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemContextSelected, (String) null, 2, (Object) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSize(VideoStoryTileView view, int width, int height) {
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        artworkFrameView.getArtworkImageView().setImageSize(new ImageSize(width, Integer.valueOf(getStyleSheet().getImageCornerSize(height))));
        artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(width, -2));
    }

    private final void clearView(VideoStoryTileView view) {
        view.setLabel("");
        view.setTitle("");
        view.setSubtitle("");
    }

    private final void bindArtwork(ArtworkFrameView artworkFrameLayout, VerticalItemElement element) {
        ArtworkFrameView.initStyle$default(artworkFrameLayout, getStyleSheet(), null, 2, null);
        artworkFrameLayout.setPlayIcon((Integer) null);
        artworkFrameLayout.getArtworkImageView().setContentDescription(element.imageDescription());
        ImageBinder imageBinder = getImageBinder();
        ImageView image = artworkFrameLayout.getArtworkImageView().getImage();
        String strImage = element.image();
        ImageResources imageResourcesPlaceholder = element.placeholder();
        imageBinder.bind(image, strImage, 14, getPlaceholderId(imageResourcesPlaceholder != null ? imageResourcesPlaceholder.image() : null));
    }

    private final int getPlaceholderId(ImageResources.Image image) {
        Integer num = Constants.INSTANCE.getResMap().get(image);
        return num == null ? R.drawable.album_placeholder : num.intValue();
    }
}
