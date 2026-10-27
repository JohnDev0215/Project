package com.amazon.music.views.library.binders;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.AudioContentEncodingUtil;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VerticalTileView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractVerticalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005BE\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0002J(\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J(\u0010$\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0002R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/music/views/library/binders/AbstractVerticalTileBinder;", "V", "Lcom/amazon/music/views/library/views/VerticalTileView;", "M", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/binders/ImageBinder;)V", "LARGE_THUMBNAIL_WIDTH_DP", "", "bindView", "", "view", "model", "width", "clearView", "loadSourceLogoImage", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "sourceLogo", "Landroid/widget/ImageView;", ImagesContract.URL, "", "updateSize", "height", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractVerticalTileBinder<V extends VerticalTileView, M extends VerticalTileModel> {
    private final int LARGE_THUMBNAIL_WIDTH_DP;
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final ImageBinder imageBinder;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;

    public AbstractVerticalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ImageBinder imageBinder) {
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
        this.LARGE_THUMBNAIL_WIDTH_DP = ContentCatalogStatus.HAWKFIRE_OR_NOT_BOUNDARY;
    }

    public final void bindView(final VerticalTileView view, final VerticalTileModel model, int width) {
        float fraction;
        int i;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        clearView(view);
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        Boolean is16x9Tile = model.getIs16x9Tile();
        Intrinsics.checkNotNull(is16x9Tile);
        if (is16x9Tile.booleanValue()) {
            fraction = artworkFrameView.getContext().getResources().getFraction(R$fraction.aspect_ratio_16_9, 1, 1);
        } else {
            fraction = artworkFrameView.getContext().getResources().getFraction(R$fraction.aspect_ratio_1_1, 1, 1);
        }
        float f = fraction;
        float f2 = width;
        final int i2 = (int) (f2 / f);
        final int i3 = (int) (f2 * f);
        if (width == -2 || width == -1) {
            view.post(new Runnable() { // from class: com.amazon.music.views.library.binders.AbstractVerticalTileBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractVerticalTileBinder.m2254bindView$lambda0(view, model, this, i3, i2);
                }
            });
        } else if (Intrinsics.areEqual(model.getKeepOriginalHeight(), Boolean.TRUE)) {
            updateSize(view, model, i3, width);
        } else {
            updateSize(view, model, width, i2);
        }
        ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
        }
        artworkFrameView.getArtworkImageView().setScalingFactor(f);
        Boolean isLiveContent = model.getIsLiveContent();
        Intrinsics.checkNotNull(isLiveContent);
        if (isLiveContent.booleanValue()) {
            Picasso picasso = Picasso.get();
            ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
            Intrinsics.checkNotNull(artworkFrameModel);
            picasso.invalidate(artworkFrameModel.getImageUrl());
        }
        ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
        ArtworkFrameModel artworkFrameModel2 = model.getArtworkFrameModel();
        Intrinsics.checkNotNull(artworkFrameModel2);
        artworkFrameBinder.bind(artworkFrameView, artworkFrameModel2);
        ButtonSize buttonSize = this.styleSheet.getButtonSize(ButtonSizeKey.LARGE);
        Integer height = buttonSize == null ? null : buttonSize.getHeight();
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_ACCENT);
        if (height != null && color != null) {
            artworkFrameView.setPlayNotificationIconStyle(height.intValue(), color.intValue());
        }
        ArtworkFrameView.setPlayIconGravity$default(artworkFrameView, 8388691, false, 2, null);
        final ContentMetadata metadata = model.getMetadata();
        String title = model.getTitle();
        if (title != null) {
            view.setTitle(title);
        }
        String subTitle = model.getSubTitle();
        if (subTitle != null) {
            view.setSubtitle(subTitle);
        }
        if ((model.getMetadata() instanceof ArtistMetadata) || model.getIsCircle()) {
            if (model.getTitle() != null) {
                view.centerTitle(true);
            }
            if (model.getSubTitle() != null) {
                view.centerSubtitle(true);
            }
        }
        String tertiaryText = model.getTertiaryText();
        if (tertiaryText != null) {
            view.setDetailsText(tertiaryText);
        }
        view.setLabel(model.getLabel());
        AudioContentEncodingUtil audioContentEncodingUtil = AudioContentEncodingUtil.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        view.setAudioQualityText(audioContentEncodingUtil.getAudioQualityText(context, model.getContentEncoding()));
        Context context2 = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "view.context");
        view.setImmersiveBadgeText(audioContentEncodingUtil.getImmersiveBadgeText(context2, model.getContentEncoding(), this.userSubscription));
        if (model.getMetadata() instanceof AlbumMetadata) {
            view.setExtraAtmosBadgeText(audioContentEncodingUtil.containsBothAtmosAnd360(model.getContentEncoding(), this.userSubscription));
        }
        view.showAllAccessLabel(model.getAllAccessLabel());
        view.setVisibility(0);
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        if (contentEnabilityProvider != null) {
            contentEnabilityProvider.setContentEnabled(view, model);
        }
        ImageView artistArtworkImageView = view.getArtistArtworkImageView();
        if (model.getArtistMetadata() != null) {
            ScreenSizeKey screenSizeKey = this.styleSheet.getScreenSizeKey(new DisplayMetrics().widthPixels);
            Resources resources = view.getContext().getResources();
            if (screenSizeKey.compareTo(ScreenSizeKey.MEDIUM) > 0) {
                i = R$dimen.artwork_default_dimen;
            } else {
                i = R$dimen.spacer_40;
            }
            view.setArtistArtworkMargins((int) resources.getDimension(i));
            ImageBinder.bind$default(this.imageBinder, artistArtworkImageView, model.getArtistMetadata().getImageUrl(), 4, 0, 8, null);
            artistArtworkImageView.setVisibility(0);
            artistArtworkImageView.setOnClickListener(this.clickListenerFactory.create(model.getArtistMetadata()));
        } else {
            artistArtworkImageView.setVisibility(8);
        }
        ImageView sourceLogoImageView = view.getSourceLogoImageView();
        if (model.getSourceLogoUrl() != null) {
            sourceLogoImageView.setVisibility(0);
            loadSourceLogoImage(this.styleSheet, this.imageBinder.getImageLoader(), sourceLogoImageView, model.getSourceLogoUrl());
            view.setSourceLogoMargins();
        } else {
            sourceLogoImageView.setVisibility(8);
        }
        String viewerCount = model.getViewerCount();
        if (viewerCount != null) {
            view.setViewerCount(viewerCount);
        }
        MetadataContextMenuProvider metadataContextMenuProvider = this.contextMenuProvider;
        if (metadataContextMenuProvider != null && metadataContextMenuProvider.shouldDisplayContextMenu(metadata)) {
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.views.library.binders.AbstractVerticalTileBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return AbstractVerticalTileBinder.m2255bindView$lambda7(this.f$0, view, metadata, view2);
                }
            });
            this.contextMenuProvider.updateStateProvider(metadata);
        }
        artworkFrameView.setPlayIconClickListener(this.clickListenerFactory.create(metadata));
        List<ContentMetadata> metadataList = model.getMetadataList();
        if (metadataList != null) {
            view.setOnClickListener(ClickListenerFactory.DefaultImpls.create$default(this.clickListenerFactory, metadataList, metadataList.indexOf(model.getMetadata()), null, 4, null));
        } else {
            view.setOnClickListener(this.clickListenerFactory.create(metadata));
        }
        Double rating = model.getRating();
        view.setStarRating(rating != null ? Float.valueOf((float) rating.doubleValue()) : null, model.getRatingCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindView$lambda-0, reason: not valid java name */
    public static final void m2254bindView$lambda0(final VerticalTileView view, final VerticalTileModel model, final AbstractVerticalTileBinder this$0, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.AbstractVerticalTileBinder$bindView$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int measuredWidth = view.getMeasuredWidth();
                if (Intrinsics.areEqual(model.getKeepOriginalHeight(), Boolean.TRUE)) {
                    this$0.updateSize(view, model, i, measuredWidth);
                } else {
                    this$0.updateSize(view, model, measuredWidth, i2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindView$lambda-7, reason: not valid java name */
    public static final boolean m2255bindView$lambda7(AbstractVerticalTileBinder this$0, VerticalTileView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        this$0.contextMenuProvider.openContextMenu(view, metadata, InteractionType.LONG_PRESS);
        return true;
    }

    private final void loadSourceLogoImage(StyleSheet styleSheet, ImageLoader imageLoader, ImageView sourceLogo, String url) {
        imageLoader.loadImage(url, new AbstractVerticalTileBinder$loadSourceLogoImage$callback$1(sourceLogo, styleSheet.getSpacerInPixels(SpacerKey.SMALL) == null ? 0.0f : r2.intValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSize(VerticalTileView view, VerticalTileModel model, int width, int height) {
        ImageSize imageSize;
        Integer contentType;
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        boolean z = false;
        if (artworkFrameModel != null && (contentType = artworkFrameModel.getContentType()) != null && contentType.intValue() == 4) {
            z = true;
        }
        if (z) {
            imageSize = ImageSize.INSTANCE.getRoundImageSize(width);
        } else {
            imageSize = new ImageSize(width, Integer.valueOf(this.styleSheet.getImageCornerSize(height)));
        }
        artworkFrameView.setArtworkSize(imageSize);
        artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(width, -2));
        Boolean isLiveContent = model.getIsLiveContent();
        if (isLiveContent == null) {
            return;
        }
        view.setLiveComponents(isLiveContent.booleanValue(), width);
    }

    private final void clearView(VerticalTileView view) {
        view.setLabel("");
        view.setTitle("");
        view.setSubtitle("");
        view.setDetailsText("");
        view.centerTitle(false);
        view.centerSubtitle(false);
        view.getArtworkFrameView().setPlayIcon((Integer) null);
        VerticalTileView.setStarRating$default(view, null, null, 2, null);
    }
}
