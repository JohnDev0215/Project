package com.amazon.music.views.library.binders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.CastingListItemHorizontalTileModel;
import com.amazon.music.views.library.models.enums.DeviceTypeEnum;
import com.amazon.music.views.library.providers.CastingTileListenerProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.CastingListItemHorizontalTileView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CastingListItemHorizontalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B3\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b'\u0010(J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J \u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u000eR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006)"}, d2 = {"Lcom/amazon/music/views/library/binders/CastingListItemHorizontalTileBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "payload", "", "handleStateChange", "bind", "", "width", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "Lcom/amazon/music/views/library/providers/CastingTileListenerProvider;", "castingTileListenerProvider", "Lcom/amazon/music/views/library/providers/CastingTileListenerProvider;", "", "alexaDevicesEnabled", "Z", "chromecastDevicesEnabled", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/CastingTileListenerProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CastingListItemHorizontalTileBinder implements BaseStateChangeBinder<CastingListItemHorizontalTileView, CastingListItemHorizontalTileModel> {
    private boolean alexaDevicesEnabled;
    private final ArtworkFrameBinder artworkFrameBinder;
    private final CastingTileListenerProvider castingTileListenerProvider;
    private boolean chromecastDevicesEnabled;
    private ClickListenerFactory clickListenerFactory;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CastingListItemHorizontalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CastingListItemHorizontalTileModel.class);
    }

    public CastingListItemHorizontalTileBinder(StyleSheet styleSheet, ArtworkFrameBinder artworkFrameBinder, ClickListenerFactory clickListenerFactory, MetadataContextMenuProvider metadataContextMenuProvider, CastingTileListenerProvider castingTileListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        Intrinsics.checkNotNullParameter(castingTileListenerProvider, "castingTileListenerProvider");
        this.styleSheet = styleSheet;
        this.artworkFrameBinder = artworkFrameBinder;
        this.clickListenerFactory = clickListenerFactory;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.castingTileListenerProvider = castingTileListenerProvider;
        this.alexaDevicesEnabled = true;
        this.chromecastDevicesEnabled = true;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public CastingListItemHorizontalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CastingListItemHorizontalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
            ArtworkFrameView artworkFrameLayout = view.getArtworkFrameLayout();
            ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
            Intrinsics.checkNotNull(artworkFrameModel);
            artworkFrameBinder.handleStateChange(artworkFrameLayout, artworkFrameModel, payload);
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }

    public final void bind(final CastingListItemHorizontalTileView view, final CastingListItemHorizontalTileModel model, int width) {
        Integer imageSize;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setTag(model);
        ArtworkFrameView artworkFrameLayout = view.getArtworkFrameLayout();
        ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
        final ContentMetadata metadata = model.getMetadata();
        Boolean isActive = model.getIsActive();
        boolean zBooleanValue = isActive == null ? false : isActive.booleanValue();
        view.updateViewWidth(width);
        String headerTitle = model.getHeaderTitle();
        if (headerTitle != null) {
            view.setHeader(headerTitle);
        }
        view.setUpDeviceType(model.getDeviceType());
        view.setVisibility(0);
        view.setEnabled(true);
        view.showSpinner(false);
        if (!zBooleanValue) {
            if (model.getDeviceType() == DeviceTypeEnum.TROUBLESHOOT) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CastingListItemHorizontalTileBinder.m2282bind$lambda1(model, view, view2);
                    }
                });
            } else if (!this.castingTileListenerProvider.isCasting(model)) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CastingListItemHorizontalTileBinder.m2283bind$lambda2(this.f$0, view, model, view2);
                    }
                });
            }
        } else if (!this.castingTileListenerProvider.isCasting(model)) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CastingListItemHorizontalTileBinder.m2284bind$lambda3(this.f$0, view, metadata, view2);
                }
            });
        }
        view.setDisconnectIcon(this.castingTileListenerProvider.isCasting(model));
        view.updateViewVisibility(zBooleanValue);
        view.setIsCasting(this.castingTileListenerProvider.isCasting(model));
        view.getDisconnectButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CastingListItemHorizontalTileBinder.m2285bind$lambda4(this.f$0, view, model, view2);
            }
        });
        if (zBooleanValue) {
            int dimension = (int) view.getContext().getResources().getDimension(R$dimen.artwork_default_dimen);
            artworkFrameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(dimension, dimension));
            if (artworkFrameModel != null) {
                this.artworkFrameBinder.bind(artworkFrameLayout, artworkFrameModel);
            }
            ButtonSize buttonSize = this.styleSheet.getButtonSize(ButtonSizeKey.SMALL);
            Integer height = buttonSize == null ? null : buttonSize.getHeight();
            Integer color = this.styleSheet.getColor(ColorKey.COLOR_ACCENT);
            if (height != null && color != null) {
                artworkFrameLayout.setPlayNotificationIconStyle(height.intValue(), color.intValue());
            }
            artworkFrameLayout.setPlayIconGravity(8388691, true);
            artworkFrameLayout.setIndicatorSize(this.styleSheet, IndicatorSizeKey.SMALL);
            ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
            if (imageStyle != null) {
                artworkFrameLayout.getArtworkImageView().setImageStyle(imageStyle);
            }
            ArtworkFrameModel artworkFrameModel2 = model.getArtworkFrameModel();
            if (artworkFrameModel2 != null && (imageSize = artworkFrameModel2.getImageSize()) != null) {
                dimension = imageSize.intValue();
            }
            ImageSize imageSize2 = this.styleSheet.getImageSize(dimension);
            if (imageSize2 != null) {
                artworkFrameLayout.getArtworkImageView().setImageSize(imageSize2);
                artworkFrameLayout.setArtworkSize(-2, -2);
            }
            String title = model.getTitle();
            if (title != null) {
                view.setTitle(title);
            }
            String subTitle = model.getSubTitle();
            if (subTitle == null) {
                return;
            }
            view.setSubtitle(subTitle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2282bind$lambda1(CastingListItemHorizontalTileModel model, CastingListItemHorizontalTileView view, View view2) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        view.getContext().startActivity(new Intent("android.intent.action.VIEW", model.getMetadata().getUri()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2, reason: not valid java name */
    public static final void m2283bind$lambda2(CastingListItemHorizontalTileBinder this$0, CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.castingTileListenerProvider.clickListener(view, model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-3, reason: not valid java name */
    public static final void m2284bind$lambda3(CastingListItemHorizontalTileBinder this$0, CastingListItemHorizontalTileView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        MetadataContextMenuProvider metadataContextMenuProvider = this$0.contextMenuProvider;
        if (metadataContextMenuProvider == null) {
            return;
        }
        metadataContextMenuProvider.openContextMenu(view, metadata, InteractionType.TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-4, reason: not valid java name */
    public static final void m2285bind$lambda4(CastingListItemHorizontalTileBinder this$0, CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.castingTileListenerProvider.disconnectListener(view, model);
    }
}
