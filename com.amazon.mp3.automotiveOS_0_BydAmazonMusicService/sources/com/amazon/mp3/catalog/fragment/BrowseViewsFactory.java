package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.brush.FollowStateUseCase;
import com.amazon.mp3.customerprofile.CustomerProfileClickHandler;
import com.amazon.mp3.customerprofile.ShareProfileProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.providers.ArtistClickHandler;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.providers.DefaultFeaturedPlayMusicProvider;
import com.amazon.mp3.providers.DefaultFollowProvider;
import com.amazon.mp3.providers.DefaultHotspotTooltipListenerProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.providers.DefaultPlaybackProvider;
import com.amazon.mp3.providers.DefaultShareProvider;
import com.amazon.mp3.providers.DefaultTrackDownloadButtonProvider;
import com.amazon.mp3.providers.GenreClickHandler;
import com.amazon.mp3.providers.ViewsConfiguration;
import com.amazon.mp3.recentlyplayed.ShovelerImageLoader;
import com.amazon.mp3.social.MusicRelationshipProvider;
import com.amazon.mp3.social.SocialFactory;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.DialogProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.OnItemClickListener;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes3.dex */
public class BrowseViewsFactory {
    private static SparseArray<ShovelerImageLoader> sIntegerShovelerImageLoaderMap = new SparseArray<>();

    public static ShovelerImageLoader getImageLoaderByContext(Context context) {
        if (sIntegerShovelerImageLoaderMap.get(context.hashCode()) == null) {
            sIntegerShovelerImageLoaderMap.put(context.hashCode(), new ShovelerImageLoader(context.getApplicationContext()));
        }
        return sIntegerShovelerImageLoaderMap.get(context.hashCode());
    }

    public static void removeRegisteredSholverImageLoader(Context context) {
        if (sIntegerShovelerImageLoaderMap.get(context.hashCode()) != null) {
            sIntegerShovelerImageLoaderMap.get(context.hashCode()).close();
            sIntegerShovelerImageLoaderMap.delete(context.hashCode());
        }
    }

    public static BrushViews createBrowseViewsV2(final Fragment fragment, final StyleSheet styleSheet, final PageType pageType, final SeeMoreProvider seeMoreProvider, final ContentMetadataOnClickListener metadataOnClickListener, final BrushUriClickListenerProvider uriClickListenerProvider, final ContentViewedListener contentViewedListener) {
        return createBrowseViewsV2(fragment, styleSheet, null, null, seeMoreProvider, metadataOnClickListener, uriClickListenerProvider, null, null, null, null, contentViewedListener, pageType, null, null, null);
    }

    public static BrushViews createBrowseViewsV2(final Fragment fragment, final StyleSheet styleSheet, final PageType pageType, final ContentMetadataOnClickListener metadataOnClickListener, final BrushUriClickListenerProvider uriClickListenerProvider, final EndlessScrollDataProvider endlessScrollDataProvider, final ContentViewedListener contentViewedListener, final OnItemClickListener itemClickListener) {
        return createBrowseViewsV2(fragment, styleSheet, null, null, null, metadataOnClickListener, uriClickListenerProvider, null, null, endlessScrollDataProvider, null, contentViewedListener, pageType, null, null, itemClickListener);
    }

    public static BrushViews createBrowseViewsV2(final Fragment fragment, final StyleSheet styleSheet, final RecyclerView recyclerView, final MetadataContextMenuProvider contextMenuProvider, final SeeMoreProvider seeMoreProvider, final BrushUriClickListenerProvider brushUriClickListenerProvider, final SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider, final StateChangeProvider stateChangeProvider, final EndlessScrollDataProvider endlessScrollDataProvider, final LibraryStateProvider libraryStateProvider, final ContentViewedListener contentViewedListener, final PageType pageType, final FollowStateUseCase followStateUseCase, final RefinementClickListenerProvider refinementClickListenerProvider) {
        return createBrowseViewsV2(fragment, styleSheet, recyclerView, contextMenuProvider, seeMoreProvider, new DefaultMetadataClickListener(fragment, pageType), brushUriClickListenerProvider, spellCorrectionListenerClickProvider, stateChangeProvider, endlessScrollDataProvider, libraryStateProvider, contentViewedListener, pageType, followStateUseCase, refinementClickListenerProvider, null);
    }

    public static BrushViews createBrowseViewsV2(final Fragment fragment, final StyleSheet styleSheet, final RecyclerView recyclerView, final MetadataContextMenuProvider contextMenuProvider, final SeeMoreProvider seeMoreProvider, final ContentMetadataOnClickListener metadataOnClickListener, final BrushUriClickListenerProvider brushUriClickListenerProvider, final SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider, final StateChangeProvider stateChangeProvider, final EndlessScrollDataProvider endlessScrollDataProvider, final LibraryStateProvider libraryStateProvider, final ContentViewedListener contentViewedListener, final PageType pageType, final FollowStateUseCase followStateUseCase, final RefinementClickListenerProvider refinementClickListenerProvider, final OnItemClickListener itemClickListener) {
        RowButtonOnClickProvider defaultAddToLibraryProvider;
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            defaultAddToLibraryProvider = new DefaultLikesProvider(fragment.getActivity(), libraryStateProvider, null, true, pageType);
        } else {
            defaultAddToLibraryProvider = new DefaultAddToLibraryProvider(fragment.getActivity(), libraryStateProvider);
        }
        return createBrowseViewsV2(fragment, styleSheet, recyclerView, contextMenuProvider, seeMoreProvider, metadataOnClickListener, brushUriClickListenerProvider, spellCorrectionListenerClickProvider, stateChangeProvider, endlessScrollDataProvider, libraryStateProvider, contentViewedListener, followStateUseCase, null, defaultAddToLibraryProvider, refinementClickListenerProvider, null, pageType, null, itemClickListener);
    }

    public static BrushViews createBrowseViewsV2(final Fragment fragment, final StyleSheet styleSheet, final RecyclerView recyclerView, final MetadataContextMenuProvider contextMenuProvider, final SeeMoreProvider seeMoreProvider, final ContentMetadataOnClickListener contentMetadataOnClickListener, final BrushUriClickListenerProvider brushUriClickListenerProvider, final SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider, final StateChangeProvider stateChangeProvider, final EndlessScrollDataProvider endlessScrollDataProvider, final LibraryStateProvider libraryStateProvider, final ContentViewedListener contentViewedListener, final FollowStateUseCase followStateUseCase, final ContentEligibilityProvider contentEligibilityProvider, final RowButtonOnClickProvider rowButtonOnClickProvider, final RefinementClickListenerProvider refinementClickListenerProvider, final ItemOrdinalProvider itemOrdinalProvider, final PageType pageType, final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider, final OnItemClickListener itemClickListener) {
        Context context = fragment.getContext();
        PicassoImageLoader picassoImageLoaderBuild = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        MusicRelationshipProvider musicRelationshipProvider = getMusicRelationshipProvider(context);
        getDialogProvider(fragment.getActivity());
        ShareProfileProviderImpl shareProfileProviderImpl = new ShareProfileProviderImpl(context);
        CustomerProfileClickHandler customerProfileClickHandler = new CustomerProfileClickHandler(fragment.getActivity());
        GenreClickHandler genreClickHandler = new GenreClickHandler(fragment.getActivity());
        CatalogEnabilityProvider catalogEnabilityProvider = new CatalogEnabilityProvider();
        DefaultFollowProvider defaultFollowProvider = musicRelationshipProvider != null ? new DefaultFollowProvider(context, musicRelationshipProvider) : null;
        boolean zIsEnabled = Feature.use_optimized_observable_browse_views_factory.isEnabled();
        return new BrushViews(new ViewsConfiguration(context, fragment, styleSheet, picassoImageLoaderBuild, contentEligibilityProvider, rowButtonOnClickProvider, contextMenuProvider, DeeplinksManager.get(context), contentMetadataOnClickListener, catalogEnabilityProvider, new BaseViewModelAdapterProvider(fragment, null, LifecycleOwnerKt.getLifecycleScope(fragment), zIsEnabled), new BaseViewModelAdapterProvider(fragment.getActivity(), null, LifecycleOwnerKt.getLifecycleScope(fragment.getActivity()), zIsEnabled), seeMoreProvider, brushUriClickListenerProvider, endlessScrollDataProvider, new DefaultCachedArtworkProvider(fragment.getActivity(), null), shareProfileProviderImpl, customerProfileClickHandler, contentViewedListener, new DefaultFeaturedPlayMusicProvider(fragment), genreClickHandler, new ArtistClickHandler(fragment.getActivity(), musicRelationshipProvider, followStateUseCase, contentMetadataOnClickListener), new DefaultPlaybackProvider(fragment.getActivity(), catalogEnabilityProvider), new DefaultShareProvider(context), defaultFollowProvider, spellCorrectionListenerClickProvider, stateChangeProvider, refinementClickListenerProvider, itemOrdinalProvider, new DefaultTrackDownloadButtonProvider(fragment.getActivity(), pageType), entityTrackingIntentServiceProvider, itemClickListener, new DefaultHotspotTooltipListenerProvider(context, recyclerView)));
    }

    private static MusicRelationshipProvider getMusicRelationshipProvider(final Context context) {
        if (AmazonApplication.getCapabilities().isArtistFollowSupported()) {
            return SocialFactory.createMusicRelationshipProvider(context);
        }
        return null;
    }

    public static DialogProvider getDialogProvider(final FragmentActivity activity) {
        return new DialogProvider() { // from class: com.amazon.mp3.catalog.fragment.BrowseViewsFactory$$ExternalSyntheticLambda0
        };
    }
}
