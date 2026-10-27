package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.TextureView;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.VideoStoryAutoPlayViewProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VideoStoryTileView;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: VideoStoryTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BQ\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0003H\u0016J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0002H\u0002J\u0010\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#H\u0016J \u0010$\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H\u0016J(\u0010'\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001eH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/music/views/library/binders/VideoStoryTileBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/VideoStoryTileView;", "Lcom/amazon/music/views/library/models/VideoStoryTileModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "videoStoryAutoPlayViewProvider", "Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/binders/ImageBinder;Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "bindView", "clearView", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "updateSize", "height", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoryTileBinder implements BaseStateChangeBinder<VideoStoryTileView, VideoStoryTileModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final ImageBinder imageBinder;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;
    private final VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VideoStoryTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(VideoStoryTileModel.class);
    }

    public VideoStoryTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ImageBinder imageBinder, VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        this.styleSheet = styleSheet;
        this.userSubscription = userSubscription;
        this.artworkFrameBinder = artworkFrameBinder;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.imageBinder = imageBinder;
        this.videoStoryAutoPlayViewProvider = videoStoryAutoPlayViewProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public VideoStoryTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new VideoStoryTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(VideoStoryTileView view, VideoStoryTileModel model, Object payload) {
        String subTitle;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
            if (artworkFrameModel == null) {
                return;
            }
            this.artworkFrameBinder.handleStateChange(view.getArtworkFrameView(), artworkFrameModel, payload);
            return;
        }
        if (!Intrinsics.areEqual(payload, (Object) 4) || (subTitle = model.getSubTitle()) == null) {
            return;
        }
        view.setSubtitle(subTitle);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(VideoStoryTileView view, VideoStoryTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, view.getTileWidth());
    }

    public final void bind(VideoStoryTileView view, VideoStoryTileModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindView(view, model, width);
    }

    public final void bindView(final VideoStoryTileView view, final VideoStoryTileModel model, int width) {
        VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        clearView(view);
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        final int i = (int) (width / 0.5625f);
        if (width == -2 || width == -1) {
            view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.VideoStoryTileBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    VideoStoryTileBinder.m2375bindView$lambda2(view, this, model, i);
                }
            });
        } else {
            updateSize(view, model, width, i);
        }
        ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
        }
        artworkFrameView.getArtworkImageView().setScalingFactor(0.5625f);
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        if (artworkFrameModel != null) {
            this.artworkFrameBinder.bind(artworkFrameView, artworkFrameModel);
        }
        final ContentMetadata metadata = model.getMetadata();
        String title = model.getTitle();
        if (title != null) {
            view.setTitle(title);
        }
        String subTitle = model.getSubTitle();
        if (subTitle != null) {
            view.setSubtitle(subTitle);
        }
        view.setLabel(model.getLabel());
        boolean z = false;
        view.setVisibility(0);
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        if (contentEnabilityProvider != null) {
            contentEnabilityProvider.setContentEnabled(view, model);
        }
        MetadataContextMenuProvider metadataContextMenuProvider = this.contextMenuProvider;
        if (metadataContextMenuProvider != null && metadataContextMenuProvider.shouldDisplayContextMenu(metadata)) {
            z = true;
        }
        if (z) {
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.views.library.binders.VideoStoryTileBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return VideoStoryTileBinder.m2376bindView$lambda8(this.f$0, view, metadata, view2);
                }
            });
            this.contextMenuProvider.updateStateProvider(metadata);
        }
        artworkFrameView.setPlayIconClickListener(this.clickListenerFactory.create(metadata));
        view.setOnClickListener(this.clickListenerFactory.create(metadata));
        view.getArtworkFrameView().getArtworkImageView().getImage().setBackgroundResource(R$drawable.rounded_corners_story_tile);
        view.getArtworkFrameView().getArtworkImageView().getImage().setClipToOutline(true);
        TextureView videoStoryTextureView = view.getVideoStoryTextureView();
        if (videoStoryTextureView == null || (videoStoryAutoPlayViewProvider = this.videoStoryAutoPlayViewProvider) == null) {
            return;
        }
        videoStoryAutoPlayViewProvider.setTextureView(model.getTilePosition(), videoStoryTextureView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindView$lambda-2, reason: not valid java name */
    public static final void m2375bindView$lambda2(final VideoStoryTileView view, final VideoStoryTileBinder this$0, final VideoStoryTileModel model, final int i) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.VideoStoryTileBinder$bindView$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                this$0.updateSize(view, model, view.getMeasuredWidth(), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindView$lambda-8, reason: not valid java name */
    public static final boolean m2376bindView$lambda8(VideoStoryTileBinder this$0, VideoStoryTileView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        this$0.contextMenuProvider.openContextMenu(view, metadata, InteractionType.LONG_PRESS);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSize(VideoStoryTileView view, VideoStoryTileModel model, int width, int height) {
        Integer contentType;
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        boolean z = false;
        if (artworkFrameModel != null && (contentType = artworkFrameModel.getContentType()) != null && contentType.intValue() == 4) {
            z = true;
        }
        if (z) {
            artworkFrameView.getArtworkImageView().setImageSize(ImageSize.INSTANCE.getRoundImageSize(width));
        } else {
            artworkFrameView.getArtworkImageView().setImageSize(new ImageSize(width, Integer.valueOf(this.styleSheet.getImageCornerSize(height))));
        }
        artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(width, -2));
        Integer cornerSize = this.styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize == null) {
            return;
        }
        artworkFrameView.getArtworkImageView().applyCornerRadius(cornerSize.intValue());
    }

    private final void clearView(VideoStoryTileView view) {
        view.setLabel("");
        view.setTitle("");
        view.setSubtitle("");
    }
}
