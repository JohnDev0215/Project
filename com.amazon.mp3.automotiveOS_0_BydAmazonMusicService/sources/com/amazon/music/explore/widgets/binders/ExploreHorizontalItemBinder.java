package com.amazon.music.explore.widgets.binders;

import android.content.Context;
import com.amazon.music.explore.widgets.models.HorizontalItemModel;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.HorizontalTileView;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreHorizontalItemBinder implements UniversalBinder<HorizontalTileView, HorizontalItemModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private GridLayoutUtils gridLayoutUtils;
    private final RowButtonOnClickProvider rowButtonOnClickProvider;
    private final StyleSheet styleSheet;
    private final UserSubscription userSubscription;

    public ExploreHorizontalItemBinder(StyleSheet styleSheet, UserSubscription userSubscription, ArtworkFrameBinder artworkFrameBinder, RowButtonOnClickProvider rowButtonOnClickProvider, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider) {
        this.styleSheet = styleSheet;
        this.userSubscription = userSubscription;
        this.artworkFrameBinder = artworkFrameBinder;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<HorizontalItemModel> getModelClass() {
        return Reflection.createKotlinClass(HorizontalItemModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HorizontalTileView createView(Context context) {
        this.gridLayoutUtils = new GridLayoutUtils(context, this.styleSheet);
        return new HorizontalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(HorizontalTileView horizontalTileView, HorizontalItemModel horizontalItemModel) {
        new HorizontalTileBinder(this.styleSheet, this.userSubscription, null, this.artworkFrameBinder, null, this.rowButtonOnClickProvider, this.contextMenuProvider, this.clickListenerFactory, this.contentEnabilityProvider, null, null).bind(horizontalTileView, horizontalItemModel.convertToHorizontalTileModel(), this.gridLayoutUtils.getShovelerItemWidth(horizontalItemModel.getParentModelSize(), 9));
    }
}
