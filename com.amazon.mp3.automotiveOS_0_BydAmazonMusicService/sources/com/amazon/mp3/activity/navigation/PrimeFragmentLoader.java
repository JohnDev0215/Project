package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.catalog.fragment.BrushPlaylistListFragment;
import com.amazon.mp3.catalog.fragment.ChartsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.GenreDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.PlayListsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.VideoLandingPageBrushFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment;
import com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragment;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.fragment.RecommendationsTabFragment;
import com.amazon.mp3.library.fragment.StationsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.AlbumPaginatedFragment;
import com.amazon.mp3.prime.PaginatedFragment;
import com.amazon.mp3.prime.PlaylistPaginatedFragment;
import com.amazon.mp3.prime.TrackPaginatedFragment;
import com.amazon.mp3.prime.activity.StationPaginatedFragment;
import com.amazon.mp3.prime.browse.PrimeMusicTab;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;

/* JADX INFO: loaded from: classes3.dex */
final class PrimeFragmentLoader extends FragmentLoader {
    public PrimeFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromMenu(final int menuId) {
        if (menuId == R.id.browse_music_tab) {
            return onPrimeTabsSelected(new Bundle());
        }
        if (menuId != R.id.search_button) {
            return false;
        }
        return onSearchSelected(new Bundle());
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromIntent(final Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("com.amazon.mp3.fragment.extra");
        PaginatedRankType paginatedRankType = PaginatedRankType.get(extras.getString("com.amazon.mp3.fragment.extra_rank_type"));
        if (PrimeTabFragmentHost.class.getSimpleName().equals(string)) {
            return onPrimeTabsSelected(extras);
        }
        if (SearchTabFragmentHost.isNativeFindOrSearchFragment(string)) {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.FIND_V2);
            return onSearchSelected(extras);
        }
        if (StationsFragment.class.getSimpleName().equals(string)) {
            return onPrimeTabSelected(extras, PrimeMusicTab.PRIME_STATIONS);
        }
        if (BrushPlaylistListFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, "playlists");
        }
        if (RecommendationsTabFragment.class.getSimpleName().equals(string)) {
            return onPrimeTabSelected(extras, PrimeMusicTab.RECOMMENDATIONS);
        }
        if (TrackPaginatedFragment.class.getSimpleName().equals(string)) {
            return onPrimeTracksDetailPageSelected(paginatedRankType, null);
        }
        if (StationPaginatedFragment.class.getSimpleName().equals(string)) {
            if (AmazonApplication.getCapabilities().shouldShowStations()) {
                return onPrimeStationsDetailPageSelected(paginatedRankType, null);
            }
            return onPrimeTabSelected(extras, PrimeMusicTab.RECOMMENDATIONS);
        }
        if (AlbumPaginatedFragment.class.getSimpleName().equals(string)) {
            return onPrimeAlbumsDetailPageSelected(paginatedRankType, null);
        }
        if (PlaylistPaginatedFragment.class.getSimpleName().equals(string)) {
            return onPrimePlaylistsDetailPageSelected(paginatedRankType, null);
        }
        if (BrushFragment.class.getSimpleName().equals(string)) {
            String string2 = extras.getString("brush_uri_extra");
            if (string2 != null && string2.contains("artist")) {
                return onBrushPageFragmentSelected(intent, "artist");
            }
            return onPrimeTabSelected(extras, PrimeMusicTab.BROWSE_HOME);
        }
        if (HawkfireUpsellDialogFragment.class.getSimpleName().equals(string)) {
            return onHawkfireUpsellSelected(intent);
        }
        if (HawkfireAllDevicesUpsellDialogFragment.class.getSimpleName().equals(string)) {
            return onHawkfireAllDevicesUpsellSelected(intent);
        }
        if (ArtistDetailBrushFragment.class.getSimpleName().equals(string)) {
            return onArtistDetailPageSelected(intent);
        }
        if (GenreDetailBrushFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, ParserUtil.GENRE_SEGMENT_NAME);
        }
        if (VideoLandingPageBrushFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, "videos");
        }
        if (NewReleasesDetailBrushFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, "new");
        }
        if (ChartsDetailBrushFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, "popular");
        }
        if (PlayListsDetailBrushFragment.class.getSimpleName().equals(string)) {
            return onBrushPageFragmentSelected(intent, "playlists");
        }
        return false;
    }

    private boolean onPrimeTabsSelected(Bundle extras) {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            PrimeTabFragmentHost primeTabFragmentHostNewInstance = PrimeTabFragmentHost.newInstance(extras);
            primeTabFragmentHostNewInstance.setArguments(extras);
            if (extras.containsKey("com.amazon.mp3.EXTRA_FORCE_REFRESH_FLAG")) {
                this.mFragmentController.changeScreenFragment(primeTabFragmentHostNewInstance, false, true);
            } else {
                this.mFragmentController.changeScreenFragment(primeTabFragmentHostNewInstance, false, false);
            }
        } else {
            NoConnectionDialog.newInstance(false).show(getActivity().getSupportFragmentManager(), "NoConnectionDialog");
        }
        return true;
    }

    private boolean onHawkfireUpsellSelected(Intent intent) {
        this.mFragmentController.showDialogFragment(HawkfireUpsellDialogFragment.newInstance(intent.getExtras()));
        return true;
    }

    private boolean onHawkfireAllDevicesUpsellSelected(Intent intent) {
        this.mFragmentController.showDialogFragment(HawkfireAllDevicesUpsellDialogFragment.newInstance(intent.getExtras()));
        return true;
    }

    private boolean onArtistDetailPageSelected(Intent intent) {
        Bundle extras = intent.getExtras();
        boolean zShouldDisplayBauhausLibraryPage = LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(AmazonApplication.getApplication().getApplicationContext(), (Uri) extras.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI"));
        String string = extras.getString("brush_asin_extra");
        String string2 = extras.getString("brush_title_extra");
        if (zShouldDisplayBauhausLibraryPage) {
            Bundle bundle = new Bundle();
            bundle.putString("ARTIST_NAME", string2);
            bundle.putString("ARTIST_ASIN", string);
            this.mFragmentController.changeScreenFragment(LibraryArtistDetailFragment.newInstance(bundle), true, false);
            return true;
        }
        if (DMSFNavigationProvider.openArtistDetailPage(string, Long.valueOf(IdGenerator.generateArtistId(string2)), null, null)) {
            return true;
        }
        return onBrushPageFragmentSelected(intent, "artist");
    }

    private boolean onBrushPageFragmentSelected(Intent intent, String pageUri) {
        this.mFragmentController.changeScreenFragment(new BrushFragmentNavigation.Builder(pageUri).withBundle(intent.getExtras()).createFragment(), true, false);
        return true;
    }

    private boolean onSearchSelected(Bundle extras) {
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        FragmentController fragmentController = this.mFragmentController;
        if (fragmentController == null) {
            return true;
        }
        SearchTabFragmentHost.selectTab(fragmentController, currentFragment, extras);
        return true;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private boolean onPrimeTabSelected(Bundle bundle, PrimeMusicTab primeMusicTab) {
        Fragment fragment;
        if (bundle.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY") && primeMusicTab != PrimeMusicTab.BROWSE_HOME) {
            bundle.putInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", primeMusicTab.getTitleResourceId());
            fragment = primeMusicTab.getFragment(bundle);
        } else {
            PrimeTabFragmentHost primeTabFragmentHostNewInstance = PrimeTabFragmentHost.newInstance(bundle);
            primeTabFragmentHostNewInstance.setTabToOpen(this.mFragmentActivity, primeTabFragmentHostNewInstance.getPrimeTabPosition(primeMusicTab));
            fragment = primeTabFragmentHostNewInstance;
        }
        this.mFragmentController.changeScreenFragment(fragment, true, false);
        return true;
    }

    private boolean onPrimeTracksDetailPageSelected(PaginatedRankType rankType, String refinementId) {
        if (refinementId == null || rankType == PaginatedRankType.RECOMMENDED) {
            refinementId = String.valueOf(UniqueCodeUtil.nextUniqueCode());
        }
        this.mFragmentController.changeScreenFragment(TrackPaginatedFragment.newInstance(rankType, refinementId, PaginatedFragment.getHeaderStringForDataType(this.mFragmentActivity, PaginatedDataType.TRACK), MediaProvider.PrimeBrowseTracksCollection.ContentSource.fromRankType(rankType)), false, false);
        return true;
    }

    private boolean onPrimeAlbumsDetailPageSelected(PaginatedRankType rankType, String refinementId) {
        if (refinementId == null || rankType == PaginatedRankType.RECOMMENDED) {
            refinementId = String.valueOf(UniqueCodeUtil.nextUniqueCode());
        }
        this.mFragmentController.changeScreenFragment(AlbumPaginatedFragment.newInstance(rankType, refinementId, PaginatedFragment.getHeaderStringForDataType(this.mFragmentActivity, PaginatedDataType.ALBUM)), false, false);
        return true;
    }

    private boolean onPrimePlaylistsDetailPageSelected(PaginatedRankType rankType, String refinementId) {
        if (refinementId == null || rankType == PaginatedRankType.RECOMMENDED) {
            refinementId = String.valueOf(UniqueCodeUtil.nextUniqueCode());
        }
        this.mFragmentController.changeScreenFragment(PlaylistPaginatedFragment.newInstance(rankType, refinementId, PaginatedFragment.getHeaderStringForDataType(this.mFragmentActivity, PaginatedDataType.PLAYLIST)), false, false);
        return true;
    }

    private boolean onPrimeStationsDetailPageSelected(PaginatedRankType rankType, String refinementId) {
        if (refinementId == null || rankType == PaginatedRankType.RECOMMENDED) {
            refinementId = String.valueOf(UniqueCodeUtil.nextUniqueCode());
        }
        this.mFragmentController.changeScreenFragment(StationPaginatedFragment.newInstance(rankType, refinementId, PaginatedFragment.getHeaderStringForDataType(this.mFragmentActivity, PaginatedDataType.STATION)), false, false);
        return true;
    }
}
