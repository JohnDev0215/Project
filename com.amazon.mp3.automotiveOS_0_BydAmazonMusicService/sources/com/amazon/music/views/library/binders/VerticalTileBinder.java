package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VerticalTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: VerticalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0004BE\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0003H\u0016J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0016J \u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/music/views/library/binders/VerticalTileBinder;", "Lcom/amazon/music/views/library/binders/AbstractVerticalTileBinder;", "Lcom/amazon/music/views/library/views/VerticalTileView;", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/binders/ImageBinder;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalTileBinder extends AbstractVerticalTileBinder<VerticalTileView, VerticalTileModel> implements BaseStateChangeBinder<VerticalTileView, VerticalTileModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final ImageBinder imageBinder;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VerticalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(VerticalTileModel.class);
    }

    public /* synthetic */ VerticalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ImageBinder imageBinder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, userSubscription, artworkFrameBinder, (i & 8) != 0 ? null : metadataContextMenuProvider, clickListenerFactory, (i & 32) != 0 ? null : contentEnabilityProvider, imageBinder);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ImageBinder imageBinder) {
        super(styleSheet, userSubscription, artworkFrameBinder, metadataContextMenuProvider, clickListenerFactory, contentEnabilityProvider, imageBinder);
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
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public VerticalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new VerticalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(VerticalTileView view, VerticalTileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
            ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
            ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
            Intrinsics.checkNotNull(artworkFrameModel);
            artworkFrameBinder.handleStateChange(artworkFrameView, artworkFrameModel, payload);
            return;
        }
        if (!Intrinsics.areEqual(payload, (Object) 4) || model.getSubTitle() == null) {
            return;
        }
        String subTitle = model.getSubTitle();
        Intrinsics.checkNotNull(subTitle);
        view.setSubtitle(subTitle);
    }

    public final void bind(VerticalTileView view, VerticalTileModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindView(view, model, width);
        if (Intrinsics.areEqual(model.getIsPodcastContent(), Boolean.TRUE)) {
            view.setOnLongClickListener(null);
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(VerticalTileView view, VerticalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }
}
