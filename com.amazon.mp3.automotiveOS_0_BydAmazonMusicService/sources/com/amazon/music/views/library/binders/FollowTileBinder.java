package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.models.FollowTileModel;
import com.amazon.music.views.library.providers.ArtistClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.FollowProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.FollowTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: FollowTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0004Be\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0003H\u0016J\u001e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&H\u0016J \u0010'\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0003H\u0002R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/music/views/library/binders/FollowTileBinder;", "Lcom/amazon/music/views/library/binders/AbstractVerticalTileBinder;", "Lcom/amazon/music/views/library/views/FollowTileView;", "Lcom/amazon/music/views/library/models/FollowTileModel;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "followProvider", "Lcom/amazon/music/views/library/providers/FollowProvider;", "artistClickListenerProvider", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "isArtistFollowSupported", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/binders/ImageBinder;Lcom/amazon/music/views/library/providers/FollowProvider;Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;Z)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "showFollowButton", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FollowTileBinder extends AbstractVerticalTileBinder<FollowTileView, FollowTileModel> implements BaseStateChangeBinder<FollowTileView, FollowTileModel> {
    private final ArtistClickListenerProvider artistClickListenerProvider;
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final FollowProvider followProvider;
    private final ImageBinder imageBinder;
    private final boolean isArtistFollowSupported;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FollowTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(FollowTileModel.class);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FollowTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ImageBinder imageBinder, FollowProvider followProvider, ArtistClickListenerProvider artistClickListenerProvider, boolean z) {
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
        this.followProvider = followProvider;
        this.artistClickListenerProvider = artistClickListenerProvider;
        this.isArtistFollowSupported = z;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public FollowTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new FollowTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(FollowTileView view, FollowTileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 2) && (model.getMetadata() instanceof ArtistMetadata) && this.isArtistFollowSupported) {
            view.setIsFollowed(((ArtistMetadata) model.getMetadata()).getIsFollowing());
        }
    }

    private final void showFollowButton(FollowTileView view, final FollowTileModel model) {
        if ((model.getMetadata() instanceof ArtistMetadata) && this.isArtistFollowSupported) {
            view.showFollowButton(true);
            view.setIsFollowed(((ArtistMetadata) model.getMetadata()).getIsFollowing());
            view.setFollowButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FollowTileBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FollowTileBinder.m2315showFollowButton$lambda0(this.f$0, model, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showFollowButton$lambda-0, reason: not valid java name */
    public static final void m2315showFollowButton$lambda0(FollowTileBinder this$0, FollowTileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        ArtistClickListenerProvider artistClickListenerProvider = this$0.artistClickListenerProvider;
        if (artistClickListenerProvider == null) {
            return;
        }
        artistClickListenerProvider.onFollowClick((ArtistMetadata) model.getMetadata(), ((ArtistMetadata) model.getMetadata()).getIsFollowing(), new FollowTileBinder$showFollowButton$1$1(model));
    }

    public final void bind(FollowTileView view, FollowTileModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        showFollowButton(view, model);
        bindView(view, model, width);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FollowTileView view, FollowTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }
}
