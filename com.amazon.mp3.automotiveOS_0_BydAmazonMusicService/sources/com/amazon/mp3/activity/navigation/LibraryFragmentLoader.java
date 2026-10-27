package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.fragment.AlbumListFragment;
import com.amazon.mp3.library.fragment.ArtistDetailsFragment;
import com.amazon.mp3.library.fragment.ArtistListFragment;
import com.amazon.mp3.library.fragment.GenreDetailFragment;
import com.amazon.mp3.library.fragment.LibraryFragmentFactory;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.fragment.PlaylistsListFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.fragment.RecentActivityTabFragmentHost;
import com.amazon.mp3.library.fragment.TrackListFragment;
import com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment;
import com.amazon.mp3.library.mylibrary.LibraryFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.EntityPlaylistFetchFragment;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.browse.SimilarRecommendationsFragment;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryFragmentLoader extends FragmentLoader {
    public LibraryFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromMenu(final int menuId) {
        if (menuId == R.id.recent_activity_tab) {
            return onRecentActivityTabsSelected();
        }
        if (menuId != R.id.your_library_tab) {
            return false;
        }
        return onLibraryTabsSelected();
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromIntent(final Intent intent) {
        String string = intent.getExtras().getString("com.amazon.mp3.fragment.extra");
        if (string == null) {
            return false;
        }
        if (string.equals(RecentlyPlayedFragment.class.getSimpleName())) {
            onRecentlyPlayedSelected();
            sendUiClickMetrics(ActionType.SELECT_RECENTLY_PLAYED);
            return true;
        }
        if (string.equals(LibraryAlbumsFragment.class.getSimpleName())) {
            onAlbumMenuSelected(intent);
            return true;
        }
        if (string.equals(AlbumListFragment.class.getSimpleName()) || string.equals(LibraryAlbumsFragment.class.getSimpleName())) {
            onAlbumMenuSelected(intent);
            return true;
        }
        if (string.equals(AlbumDetailFragment.class.getSimpleName())) {
            onAlbumDetailsSelected(intent);
            return true;
        }
        if (string.equals(ArtistListFragment.class.getSimpleName()) || string.equals(LibraryArtistsFragment.class.getSimpleName())) {
            onArtistMenuSelected(intent);
            return true;
        }
        if (string.equals(PlaylistsListFragment.class.getSimpleName()) || string.equals(LibraryPlaylistsFragment.class.getSimpleName())) {
            onPlaylistMenuSelected(intent);
            return true;
        }
        if (string.equals(TrackListFragment.class.getSimpleName()) || string.equals(LibraryTracksFragment.class.getSimpleName())) {
            onSongsMenuSelected(intent);
            return true;
        }
        if (string.equals(PlaylistDetailFragment.class.getSimpleName()) || string.equals(com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.class.getSimpleName())) {
            return goToPlaylistDetail(intent);
        }
        if (string.equals(PrimePlaylistDetailFragment.class.getSimpleName())) {
            goToPrimePlaylistDetail(intent);
            return true;
        }
        if (string.equals(ArtistDetailsFragment.class.getSimpleName())) {
            goToArtistDetailFragment(intent);
            return true;
        }
        if (string.equals(LibraryArtistDetailFragment.class.getSimpleName())) {
            goToBauhausArtistDetailFragment(intent);
            return true;
        }
        if (string.equals(GenreDetailFragment.class.getSimpleName())) {
            goToGenreDetailFragment(intent);
            return true;
        }
        if (string.equals(MyLibraryHomeFragment.class.getSimpleName()) || string.equals(MyLibraryV2HomeFragment.class.getSimpleName()) || string.equals(LibraryFragment.class.getSimpleName())) {
            onLibraryTabsSelected();
            sendUiClickMetrics(ActionType.GO_TO_MY_MUSIC);
            return true;
        }
        if (string.equals(RecentActivityTabFragmentHost.class.getSimpleName())) {
            goToRecentActivityTabs(intent);
            return true;
        }
        if (string.equals(SimilarRecommendationsFragment.class.getSimpleName())) {
            goToSimilarRecommendationsFragment(intent);
            return true;
        }
        if (string.equals(VideoPlaylistDetailFragment.class.getSimpleName())) {
            goToVideoPlaylistDetailFragment(intent);
            return true;
        }
        if (!string.equals(EntityPlaylistFetchFragment.class.getSimpleName())) {
            return false;
        }
        goToEntityPlaylistFetchFragment(intent);
        return false;
    }

    private void goToVideoPlaylistDetailFragment(final Intent intent) {
        String stringExtra = intent.getStringExtra("arg_asin");
        if (stringExtra == null) {
            return;
        }
        Fragment fragmentCreateVideoPlaylistDetailFragment = PlaylistDetailFactory.createVideoPlaylistDetailFragment(stringExtra);
        FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, fragmentCreateVideoPlaylistDetailFragment, VideoPlaylistDetailFragment.class.getSimpleName());
        fragmentTransactionBeginTransaction.addToBackStack(VideoPlaylistDetailFragment.class.getSimpleName());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public void showMusicOrDefaultScreen() {
        onRecentlyPlayedSelected();
    }

    private void goToSimilarRecommendationsFragment(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("SIMILAR_RECS_FRAGMENT_ASIN_KEY");
            PaginatedDataType paginatedDataType = (PaginatedDataType) intent.getSerializableExtra("SIMILAR_RECS_FRAGMENT_DATA_TYPE_KEY");
            if (string == null) {
                return;
            }
            this.mFragmentController.changeScreenFragment(new SimilarRecommendationsFragment.Builder(new Bundle()).setDataType(paginatedDataType).setAsin(string).build(), true, true);
        }
    }

    private void onRecentlyPlayedSelected() {
        RecentActivityTabFragmentHost recentActivityTabFragmentHostNewInstance = RecentActivityTabFragmentHost.newInstance();
        recentActivityTabFragmentHostNewInstance.setTabToOpen(this.mFragmentActivity, RecentActivityTabFragmentHost.getRecentActivityPlayedPosition());
        this.mFragmentController.changeScreenFragment(recentActivityTabFragmentHostNewInstance, false, false);
    }

    private void onPlaylistMenuSelected(final Intent intent) {
        this.mFragmentController.changeScreenFragment(MyLibraryDetailFragment.newInstance(intent.getExtras(), getActivity().getResources().getString(R.string.dmusic_library_playlists_tab), MyLibraryDetailFragment.Category.PLAYLISTS), true, true);
    }

    private void onArtistMenuSelected(final Intent intent) {
        Fragment fragmentNewInstance;
        if (LibraryFollowedArtistFragment.INSTANCE.isLibraryFollowedArtistFragmentSupported(this.mFragmentActivity.getBaseContext())) {
            fragmentNewInstance = new LibraryFollowedArtistFragment();
        } else {
            fragmentNewInstance = MyLibraryDetailFragment.newInstance(intent.getExtras(), getActivity().getResources().getString(R.string.dmusic_library_artists_tab), MyLibraryDetailFragment.Category.ARTISTS);
        }
        this.mFragmentController.changeScreenFragment(fragmentNewInstance, true, true);
    }

    private void onAlbumMenuSelected(final Intent intent) {
        this.mFragmentController.changeScreenFragment(MyLibraryDetailFragment.newInstance(intent.getExtras(), getActivity().getResources().getString(R.string.dmusic_library_albums_tab), MyLibraryDetailFragment.Category.ALBUMS), true, true);
    }

    private void onAlbumDetailsSelected(Intent intent) {
        Fragment albumDetailFragment;
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            albumDetailFragment = new AlbumDetailFragment();
        } else {
            albumDetailFragment = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
            intent.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        }
        albumDetailFragment.setArguments(intent.getExtras());
        boolean z = false;
        Bundle extras = intent.getExtras();
        if (extras != null && ((Uri) extras.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI")) != null && this.mFragmentController.getCurrentFragmentName().equals(albumDetailFragment.getClass().getSimpleName())) {
            z = true;
        }
        this.mFragmentController.changeScreenFragment(albumDetailFragment, true, z);
    }

    private void onSongsMenuSelected(final Intent intent) {
        this.mFragmentController.changeScreenFragment(MyLibraryDetailFragment.newInstance(intent.getExtras(), getActivity().getResources().getString(R.string.dmusic_library_songs_tab), MyLibraryDetailFragment.Category.SONGS), true, false);
    }

    private String getSourceId() {
        return LastViewedScreenUtil.getLastViewedSource(getActivity(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
    }

    private Fragment getSongListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryTracksFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Tracks.getFilterContentUri(getSourceId(), "");
        }
        Fragment fragment = LibraryFragmentFactory.getFragment(getActivity(), filterContentUri);
        Bundle arguments = fragment.getArguments();
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
        } else {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_SHUFFLE", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        }
        return fragment;
    }

    protected void goToBauhausArtistDetailFragment(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new IllegalStateException("Cannot go to Artist detail page with empty intent extra");
        }
        this.mFragmentController.changeScreenFragment(LibraryArtistDetailFragment.newInstance(extras), true, false);
    }

    protected void goToArtistDetailFragment(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new IllegalStateException("Cannot go to Artist detail page with empty intent extra");
        }
        Uri uri = (Uri) extras.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        ArtistDetailsFragment artistDetailsFragmentNewInstance = ArtistDetailsFragment.newInstance(getActivity(), AmazonApplication.getLibraryItemFactory().getArtist(uri).getName(), MediaProvider.getSource(uri));
        intent.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        artistDetailsFragmentNewInstance.setArguments(intent.getExtras());
        this.mFragmentController.changeScreenFragment(artistDetailsFragmentNewInstance, true, false);
    }

    private boolean goToPlaylistDetail(final Intent intent) {
        Uri uri = (Uri) intent.getExtras().getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        boolean z = false;
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(uri)) {
            return false;
        }
        boolean booleanExtra = this.mFragmentActivity.getIntent().getBooleanExtra("EXTRA_GO_TO_PLAYLIST", false);
        FragmentController fragmentController = this.mFragmentController;
        boolean zEquals = (fragmentController == null || fragmentController.getCurrentFragment() == null) ? false : this.mFragmentController.getCurrentFragment().getClass().equals(com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.class);
        if (booleanExtra && zEquals) {
            z = true;
        }
        this.mFragmentController.changeScreenFragment(PlaylistDetailFactory.createFragment(intent, true, null), true, z);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void goToPrimePlaylistDetail(final android.content.Intent r7) {
        /*
            r6 = this;
            android.os.Bundle r0 = r7.getExtras()
            java.lang.String r1 = ""
            if (r0 == 0) goto L28
            java.lang.String r2 = "com.amazon.mp3.library.EXTRA_CONTENT_URI"
            android.os.Parcelable r0 = r0.getParcelable(r2)
            android.net.Uri r0 = (android.net.Uri) r0
            if (r0 == 0) goto L28
            boolean r2 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.isPrimePlaylist(r0)
            if (r2 == 0) goto L1d
            java.lang.String r0 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.getPlaylistAsin(r0)
            goto L29
        L1d:
            boolean r2 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(r0)
            if (r2 == 0) goto L28
            java.lang.String r0 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.getPlaylistKey(r0)
            goto L29
        L28:
            r0 = r1
        L29:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L3a
            java.lang.String r7 = r6.mTag
            java.lang.String r0 = "Cannot go to Prime Playlist detail page with empty asin"
            com.amazon.mp3.util.Log.warning(r7, r0)
            r6.onRecentlyPlayedSelected()
            goto L63
        L3a:
            r2 = 0
            java.lang.String r3 = "com.amazon.mp3.library.EXTRA_ADD_TO_LIBRARY"
            boolean r2 = r7.getBooleanExtra(r3, r2)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            java.lang.String r3 = "com.amazon.mp3.library.EXTRA_SHOW_HEADER"
            r4 = 1
            r7.putExtra(r3, r4)
            java.lang.String r3 = "com.amazon.mp3.library.EXTRA_SOURCE_ID"
            java.lang.String r5 = "cirrus"
            r7.putExtra(r3, r5)
            boolean r2 = r2.booleanValue()
            android.os.Bundle r7 = r7.getExtras()
            androidx.fragment.app.Fragment r7 = com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory.createFragment(r0, r1, r2, r7)
            com.amazon.mp3.activity.navigation.FragmentController r0 = r6.mFragmentController
            r0.changeScreenFragment(r7, r4, r4)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.activity.navigation.LibraryFragmentLoader.goToPrimePlaylistDetail(android.content.Intent):void");
    }

    private void goToEntityPlaylistFetchFragment(final Intent intent) {
        String string;
        PlaylistSeedEntityType playlistSeedEntityType;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            string = extras.getString("ARG_ENTITY_ID");
            playlistSeedEntityType = (PlaylistSeedEntityType) extras.getSerializable("ARG_ENTITY_TYPE");
        } else {
            string = "";
            playlistSeedEntityType = null;
        }
        if (TextUtils.isEmpty(string) || playlistSeedEntityType == null) {
            Log.warning(this.mTag, "Cannot go to Entity Playlist detail page without entityId and entityType");
            onRecentlyPlayedSelected();
        } else {
            this.mFragmentController.changeScreenFragment(EntityPlaylistUtil.buildEntityPlaylistFetchFragment(string, playlistSeedEntityType), true, true);
        }
    }

    private boolean onLibraryTabsSelected() {
        Intent intent = new Intent();
        intent.putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName());
        this.mFragmentController.changeScreenFragment(MusicHomeActivity.getMyLibraryHomeFragment(intent), false, false);
        return true;
    }

    private boolean goToRecentActivityTabs(Intent intent) {
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            int recentActivityPlayedPosition = RecentActivityTabFragmentHost.getRecentActivityPlayedPosition();
            if (intent.getExtras() != null) {
                recentActivityPlayedPosition = intent.getExtras().getInt("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", recentActivityPlayedPosition);
            }
            Fragment songListFragment = null;
            if (recentActivityPlayedPosition == RecentActivityTabFragmentHost.getRecentActivityPlayedPosition()) {
                Uri contentUri = MediaProvider.SmartPlaylists.getContentUri(getSourceId(), 3L);
                Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this.mFragmentActivity, contentUri);
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                songListFragment = PlaylistDetailFactory.createFragment(intentForContentUri, false, this.mFragmentActivity.getString(R.string.dmusic_listening_history));
            } else if (recentActivityPlayedPosition == RecentActivityTabFragmentHost.getRecentActivityAddedPosition()) {
                songListFragment = getSongListFragment();
                Bundle arguments = songListFragment.getArguments();
                arguments.putBoolean(RefinementFilterType.getDisplayName(RefinementFilterType.DATE_ADDED), true);
                songListFragment.setArguments(arguments);
            } else if (recentActivityPlayedPosition == RecentActivityTabFragmentHost.getRecentActivityDownloadedPosition()) {
                songListFragment = getSongListFragment();
                Bundle arguments2 = songListFragment.getArguments();
                arguments2.putBoolean(RefinementFilterType.getDisplayName(RefinementFilterType.DATE_ADDED), true);
                arguments2.putBoolean(RefinementFilterType.getDisplayName(RefinementFilterType.DOWNLOADED), true);
                songListFragment.setArguments(arguments2);
            }
            if (songListFragment == null) {
                return false;
            }
            this.mFragmentController.changeScreenFragment(songListFragment, true, false);
            return true;
        }
        RecentActivityTabFragmentHost recentActivityTabFragmentHostNewInstance = RecentActivityTabFragmentHost.newInstance();
        if (intent.getExtras() != null) {
            recentActivityTabFragmentHostNewInstance.setTabToOpen(getActivity(), intent.getExtras().getInt("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", -1));
        }
        this.mFragmentController.changeScreenFragment(recentActivityTabFragmentHostNewInstance, true, false);
        return true;
    }

    private boolean onRecentActivityTabsSelected() {
        this.mFragmentController.changeScreenFragment(RecentActivityTabFragmentHost.newInstance(), false, false);
        return true;
    }

    private boolean goToGenreDetailFragment(final Intent intent) {
        this.mFragmentController.changeScreenFragment(GenreDetailFragment.newInstance(intent.getExtras().getString("com.amazon.mp3.library.activity.EXTRA_GENRE_NAME"), intent.getExtras().getString("com.amazon.mp3.library.activity.EXTRA_GENRE_COUNT"), MediaProvider.getSource((Uri) intent.getExtras().getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI"))), true, false);
        return true;
    }

    private void sendUiClickMetrics(final ActionType actionType) {
        PageType pageType;
        if ("cirrus".equals(getSourceId())) {
            pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        } else {
            pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
        }
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(pageType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }
}
