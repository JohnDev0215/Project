package com.amazon.mp3.recents.view;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.recents.click.RecentsClickListenerFactory;
import com.amazon.mp3.recents.metrics.RecentsPlaylistsMetricsLogger;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.SectionBinder;
import com.amazon.music.views.library.binders.ShovelerBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.deeplinks.DeeplinkClickListenerFactory;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsViewFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/recents/view/RecentsViewFactory;", "", "fragment", "Landroidx/fragment/app/Fragment;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "navigationProvider", "Lcom/amazon/mp3/fragment/navigation/NavigationProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/mp3/fragment/navigation/NavigationProvider;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;)V", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "sectionBinder", "Lcom/amazon/music/views/library/binders/SectionBinder;", "shovelerBinder", "Lcom/amazon/music/views/library/binders/ShovelerBinder;", "createRecentsView", "", "baseViewModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsViewFactory {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final SectionBinder sectionBinder;
    private final ShovelerBinder shovelerBinder;

    public RecentsViewFactory(Fragment fragment, StyleSheet styleSheet, NavigationProvider navigationProvider, MetadataContextMenuProvider contextMenuProvider) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(contextMenuProvider, "contextMenuProvider");
        Context context = fragment.getContext();
        Intrinsics.checkNotNull(context);
        DeeplinksManager deeplinksManager = DeeplinksManager.get(context);
        Intrinsics.checkNotNullExpressionValue(deeplinksManager, "get(context)");
        DeeplinkClickListenerFactory deeplinkClickListenerFactory = new DeeplinkClickListenerFactory(context, deeplinksManager, new DefaultMetadataClickListener(fragment, PageType.RECENTLY_PLAYED_SONGS, null, 4, null));
        RecentsClickListenerFactory recentsClickListenerFactory = new RecentsClickListenerFactory(context, navigationProvider, new RecentsPlaylistsMetricsLogger(context), fragment);
        PicassoImageLoader picassoImageLoader = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
        ImageBinder imageBinder = new ImageBinder(context, styleSheet, picassoImageLoader);
        DefaultCachedArtworkProvider defaultCachedArtworkProvider = new DefaultCachedArtworkProvider(fragment.getActivity(), null, 2, null);
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        CatalogEnabilityProvider catalogEnabilityProvider = new CatalogEnabilityProvider();
        ContentViewManager contentViewManager = new ContentViewManager();
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = new BaseViewModelAdapterProvider(fragment, null, LifecycleOwnerKt.getLifecycleScope(fragment), Feature.use_optimized_observable_browse_views_factory.isEnabled());
        this.adapterProvider = baseViewModelAdapterProvider;
        ArtworkFrameBinder artworkFrameBinder = new ArtworkFrameBinder(context, styleSheet, imageBinder, defaultCachedArtworkProvider, null, 16, null);
        Intrinsics.checkNotNullExpressionValue(userSubscription, "userSubscription");
        HorizontalTileBinder horizontalTileBinder = new HorizontalTileBinder(styleSheet, userSubscription, null, artworkFrameBinder, null, null, null, recentsClickListenerFactory, catalogEnabilityProvider, null, null, 1616, null);
        UniversalBinder[] universalBinderArr = {new VerticalTileBinder(styleSheet, userSubscription, artworkFrameBinder, contextMenuProvider, deeplinkClickListenerFactory, catalogEnabilityProvider, imageBinder)};
        SeeMoreProvider seeMoreProvider = null;
        ClickListenerFactory clickListenerFactory = null;
        ImageLoader imageLoader = null;
        this.shovelerBinder = new ShovelerBinder(context, styleSheet, seeMoreProvider, baseViewModelAdapterProvider, contentViewManager, artworkFrameBinder, null, clickListenerFactory, imageLoader, universalBinderArr, 448, null);
        this.sectionBinder = new SectionBinder(context, styleSheet, seeMoreProvider, null, baseViewModelAdapterProvider, contentViewManager, artworkFrameBinder, clickListenerFactory, imageLoader, new UniversalBinder[]{horizontalTileBinder}, 392, null);
    }

    public final void createRecentsView(List<? extends BaseViewModel> baseViewModels, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(baseViewModels, "baseViewModels");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.setAdapter(this.adapterProvider.getAdapter(baseViewModels, this.shovelerBinder, this.sectionBinder));
    }
}
