package com.amazon.mp3.curate.provider;

import ExternalActionInterface.v1_0.ExternalAction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomBarTab;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.fragment.LibraryFragmentFactory;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.MyLibraryPodcastsToggleHelper;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.curate.provider.LibraryNavigationProvider;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.Podcast;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: CurateLibraryNavigationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0004H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0002\u001a\u00020\u0004H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0004H\u0002J\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011J,\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u001d0\u001cH\u0016J@\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u00112\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u001d0\u001cH\u0002J,\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u001d0\u001cH\u0002J*\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00112\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u001d0\u001cH\u0016J\u0010\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0011H\u0002J*\u0010(\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00112\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u001d0\u001cH\u0016J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0004H\u0002J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u0011H\u0002J\u0010\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u000fH\u0002R\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006/"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateLibraryNavigationProvider;", "Lcom/amazon/music/curate/provider/LibraryNavigationProvider;", "fragmentActivity", "Ljava/lang/ref/WeakReference;", "Landroidx/fragment/app/FragmentActivity;", "(Ljava/lang/ref/WeakReference;)V", "noConnectionDialog", "Lcom/amazon/mp3/store/dialog/NoConnectionDialog;", "getNoConnectionDialog", "()Lcom/amazon/mp3/store/dialog/NoConnectionDialog;", "noConnectionDialog$delegate", "Lkotlin/Lazy;", "getLibraryFragment", "Landroidx/fragment/app/Fragment;", "uri", "Landroid/net/Uri;", "getSourceId", "", "navigateToPodcasts", "", "navigateToRecents", "id", "", "sourceId", "title", "onNavigationButtonClicked", "category", ContextMappingConstants.CONTENT_INFO, "", "", "sendUiClickMetrics", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "showCatalogPlaylist", "asin", "showEntityPlaylistDetail", "playlistId", "showLibraryPlaylist", "showNoNetwork", "showSharedPlaylistDetail", "showUserPlaylistDetail", "contentUri", "Companion", "PlaybackDatabaseCallbackImpl", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateLibraryNavigationProvider implements LibraryNavigationProvider {
    private static final String TAG;
    private static final Logger logger;
    private final WeakReference<FragmentActivity> fragmentActivity;

    /* JADX INFO: renamed from: noConnectionDialog$delegate, reason: from kotlin metadata */
    private final Lazy noConnectionDialog;

    public CurateLibraryNavigationProvider(WeakReference<FragmentActivity> fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.fragmentActivity = fragmentActivity;
        this.noConnectionDialog = LazyKt__LazyJVMKt.lazy(new Function0<NoConnectionDialog>() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$noConnectionDialog$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NoConnectionDialog invoke() {
                return NoConnectionDialog.newInstance(false);
            }
        });
    }

    static {
        String simpleName = CurateLibraryNavigationProvider.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    private final NoConnectionDialog getNoConnectionDialog() {
        Object value = this.noConnectionDialog.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-noConnectionDialog>(...)");
        return (NoConnectionDialog) value;
    }

    @Override // com.amazon.music.curate.provider.LibraryNavigationProvider
    public void onNavigationButtonClicked(String category, List<? extends Map<String, String>> contentInfo) {
        Uri filterContentUri;
        Uri filterContentUri2;
        Uri filterContentUri3;
        Uri filterContentUri4;
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        Context applicationContext = fragmentActivity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "it.applicationContext");
        String cirrusMediaSource = LastViewedScreenUtil.getLastViewedSource(applicationContext, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
        Intrinsics.checkNotNullExpressionValue(cirrusMediaSource, "getLastViewedSource(\n   …  ).toCirrusMediaSource()");
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_PLAYLISTS.name())) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                filterContentUri4 = LibraryPlaylistsFragment.INSTANCE.getUri();
            } else {
                filterContentUri4 = MediaProvider.Playlists.getFilterContentUri(cirrusMediaSource, "");
                Intrinsics.checkNotNullExpressionValue(filterContentUri4, "getFilterContentUri(sourceId, \"\")");
            }
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(applicationContext, filterContentUri4);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri, "getIntentForContentUri(context, contentUri)");
            intentForContentUri.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
            fragmentActivity.startActivity(intentForContentUri);
            sendUiClickMetrics(ActionType.GO_LIBRARY_PLAYLISTS, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_ARTISTS.name())) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                filterContentUri3 = LibraryArtistsFragment.INSTANCE.getUri();
            } else {
                filterContentUri3 = MediaProvider.Artists.getFilterContentUri(cirrusMediaSource, "");
                Intrinsics.checkNotNullExpressionValue(filterContentUri3, "getFilterContentUri(sourceId, \"\")");
            }
            Intent intentForContentUri2 = LibraryActivityFactory.getIntentForContentUri(applicationContext, filterContentUri3);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri2, "getIntentForContentUri(context, contentUri)");
            intentForContentUri2.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
            fragmentActivity.startActivity(intentForContentUri2);
            if (LibraryFollowedArtistFragment.INSTANCE.isLibraryFollowedArtistFragmentSupported(fragmentActivity.getBaseContext())) {
                sendUiClickMetrics(ActionType.GO_FOLLOWED_ARTISTS, contentInfo);
                return;
            } else {
                sendUiClickMetrics(ActionType.GO_LIBRARY_ARTISTS, contentInfo);
                return;
            }
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_ALBUMS.name())) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                filterContentUri2 = LibraryAlbumsFragment.INSTANCE.getUri();
            } else {
                filterContentUri2 = MediaProvider.Albums.getFilterContentUri(cirrusMediaSource, "");
                Intrinsics.checkNotNullExpressionValue(filterContentUri2, "getFilterContentUri(sourceId, \"\")");
            }
            Intent intentForContentUri3 = LibraryActivityFactory.getIntentForContentUri(applicationContext, filterContentUri2);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri3, "getIntentForContentUri(context, contentUri)");
            intentForContentUri3.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
            fragmentActivity.startActivity(intentForContentUri3);
            sendUiClickMetrics(ActionType.GO_LIBRARY_ALBUMS, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_SONGS.name())) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                filterContentUri = LibraryTracksFragment.INSTANCE.getUri();
            } else {
                filterContentUri = MediaProvider.Tracks.getFilterContentUri(cirrusMediaSource, "");
                Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(sourceId, \"\")");
            }
            Intent intentForContentUri4 = LibraryActivityFactory.getIntentForContentUri(applicationContext, filterContentUri);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri4, "getIntentForContentUri(context, contentUri)");
            intentForContentUri4.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
            fragmentActivity.startActivity(intentForContentUri4);
            sendUiClickMetrics(ActionType.GO_LIBRARY_SONGS, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_PODCASTS.name())) {
            navigateToPodcasts(fragmentActivity);
            sendUiClickMetrics(ActionType.GO_PODCASTS_LIBRARY, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_LIBRARY_PLAYLISTS_SEE_MORE.name())) {
            Uri filterContentUri5 = MediaProvider.Playlists.getFilterContentUri(cirrusMediaSource, "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri5, "getFilterContentUri(sourceId, \"\")");
            Intent intentForContentUri5 = LibraryActivityFactory.getIntentForContentUri(applicationContext, filterContentUri5);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri5, "getIntentForContentUri(context, contentUri)");
            intentForContentUri5.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
            fragmentActivity.startActivity(intentForContentUri5);
            sendUiClickMetrics(ActionType.GO_SEE_MORE_PLAYLISTS, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.CREATE_NEW_PLAYLIST.name())) {
            if (ConnectivityUtil.hasAnyInternetConnection(fragmentActivity) || !Intrinsics.areEqual("cirrus", getSourceId(fragmentActivity))) {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), Dispatchers.getIO(), null, new CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1(fragmentActivity, this, contentInfo, null), 2, null);
                return;
            } else {
                fragmentActivity.startActivity(NetworkErrorDialogActivity.getStartIntent(fragmentActivity));
                return;
            }
        }
        if (Intrinsics.areEqual(category, ExternalAction.SHOW_RECENTLY_PLAYED.name())) {
            String string = fragmentActivity.getString(R.string.dmusic_listening_history);
            Intrinsics.checkNotNullExpressionValue(string, "it.getString(R.string.dmusic_listening_history)");
            navigateToRecents(3L, "cirrus", string);
            sendUiClickMetrics(ActionType.GO_SONG_HISTORY, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.SHOW_RECENTLY_ADDED.name())) {
            String string2 = fragmentActivity.getString(R.string.dmusic_recently_added_my_music);
            Intrinsics.checkNotNullExpressionValue(string2, "it.getString(R.string.dm…_recently_added_my_music)");
            navigateToRecents(1L, "cirrus", string2);
            sendUiClickMetrics(ActionType.GO_RECENTLY_ADDED, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.SHOW_RECENTLY_DOWNLOADED.name())) {
            String string3 = fragmentActivity.getString(R.string.dmusic_recently_downloaded_songs);
            Intrinsics.checkNotNullExpressionValue(string3, "it.getString(R.string.dm…ecently_downloaded_songs)");
            navigateToRecents(2L, "cirrus-local", string3);
            sendUiClickMetrics(ActionType.GO_RECENTLY_DOWNLOADED, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.GO_BROWSE_HOME.name())) {
            if (ConnectivityUtil.hasAnyInternetConnection(fragmentActivity)) {
                if (fragmentActivity instanceof MusicHomeActivity) {
                    MusicHomeActivity musicHomeActivity = (MusicHomeActivity) fragmentActivity;
                    musicHomeActivity.getFragmentController();
                    if (musicHomeActivity.getFragmentController() != null) {
                        musicHomeActivity.getFragmentController().clearBackStack();
                        musicHomeActivity.getFragmentController().navigateFromMenu(BottomBarTab.BROWSE.getMenuId());
                    }
                    if (BottomNavigationBarContainer.getInstance().isEnabled()) {
                        BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
                    }
                }
            } else {
                showNoNetwork(fragmentActivity);
            }
            sendUiClickMetrics(ActionType.GO_BROWSE, contentInfo);
            return;
        }
        if (Intrinsics.areEqual(category, ExternalAction.SHOW_IMPORT_PLAYLISTS.name())) {
            fragmentActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(EndpointsProvider.get().getPlaylistImportEndpoint())));
            return;
        }
        Intent libraryHomeStartIntent = LibraryActivityFactory.getLibraryHomeStartIntent(applicationContext);
        Intrinsics.checkNotNullExpressionValue(libraryHomeStartIntent, "getLibraryHomeStartIntent(context)");
        libraryHomeStartIntent.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
        fragmentActivity.startActivity(libraryHomeStartIntent);
    }

    /* JADX INFO: compiled from: CurateLibraryNavigationProvider.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateLibraryNavigationProvider$PlaybackDatabaseCallbackImpl;", "Lcom/amazon/mp3/library/service/sync/PlaylistDatabaseCallback;", "playlistId", "", "(Lcom/amazon/mp3/curate/provider/CurateLibraryNavigationProvider;Ljava/lang/String;)V", "onUpdated", "", "numberOfUpdatedTracks", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackDatabaseCallbackImpl implements PlaylistDatabaseCallback {
        private final String playlistId;
        final /* synthetic */ CurateLibraryNavigationProvider this$0;

        public PlaybackDatabaseCallbackImpl(CurateLibraryNavigationProvider this$0, String playlistId) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            this.this$0 = this$0;
            this.playlistId = playlistId;
        }

        @Override // com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback
        public void onUpdated(int numberOfUpdatedTracks) {
            CurateLibraryNavigationProvider.logger.debug("Playlist {} update completed: {} tracks are updated", this.playlistId, Integer.valueOf(numberOfUpdatedTracks));
            FragmentActivity fragmentActivity = (FragmentActivity) this.this$0.fragmentActivity.get();
            if (fragmentActivity == null) {
                return;
            }
            CurateLibraryNavigationProvider curateLibraryNavigationProvider = this.this$0;
            Uri contentUriFromLuid = MediaProvider.UdoPlaylists.getContentUriFromLuid(fragmentActivity.getApplicationContext(), "cirrus", this.playlistId);
            if (contentUriFromLuid != null) {
                CurateLibraryNavigationProvider.logger.debug("Navigating to Playlist {} after db update", this.playlistId);
                curateLibraryNavigationProvider.showUserPlaylistDetail(contentUriFromLuid);
            } else if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(fragmentActivity, this.playlistId)) {
                curateLibraryNavigationProvider.showSharedPlaylistDetail(this.playlistId);
                CurateLibraryNavigationProvider.logger.debug("Navigating to shared user playlist {} after db update", this.playlistId);
            } else if (MediaProvider.EntityPlaylists.isEntityPlaylist(fragmentActivity, this.playlistId)) {
                curateLibraryNavigationProvider.showEntityPlaylistDetail(this.playlistId);
                CurateLibraryNavigationProvider.logger.debug("Navigating to entity playlist {} after db update", this.playlistId);
            } else {
                CurateLibraryNavigationProvider.logger.error("Playlist {} not found in local DB after sync", this.playlistId);
            }
        }
    }

    @Override // com.amazon.music.curate.provider.LibraryNavigationProvider
    public void showLibraryPlaylist(final String playlistId, final List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CurateLibraryNavigationProvider.m742showLibraryPlaylist$lambda3(this.f$0, playlistId, contentInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLibraryPlaylist$lambda-3, reason: not valid java name */
    public static final void m742showLibraryPlaylist$lambda3(final CurateLibraryNavigationProvider this$0, final String playlistId, List contentInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        Intrinsics.checkNotNullParameter(contentInfo, "$contentInfo");
        final FragmentActivity fragmentActivity = this$0.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        this$0.sendUiClickMetrics(ActionType.SELECT_PLAYLIST, EntityIdType.PLAYLIST_ID, playlistId, contentInfo);
        Uri contentUriFromLuid = MediaProvider.UdoPlaylists.getContentUriFromLuid(fragmentActivity.getApplicationContext(), "cirrus", playlistId);
        if (contentUriFromLuid != null) {
            this$0.showUserPlaylistDetail(contentUriFromLuid);
            return;
        }
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(fragmentActivity, playlistId)) {
            this$0.showSharedPlaylistDetail(playlistId);
        } else if (MediaProvider.EntityPlaylists.isEntityPlaylist(fragmentActivity, playlistId)) {
            this$0.showEntityPlaylistDetail(playlistId);
        } else {
            logger.warn("Playlist {} not found in local DB, kicked off sync for this playlist", playlistId);
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) throws ServiceException, AbstractHttpClient.HttpClientException {
                    CurateLibraryNavigationProvider.m743showLibraryPlaylist$lambda3$lambda2$lambda1(fragmentActivity, playlistId, this$0, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLibraryPlaylist$lambda-3$lambda-2$lambda-1, reason: not valid java name */
    public static final void m743showLibraryPlaylist$lambda3$lambda2$lambda1(FragmentActivity fragmentActivity, String playlistId, CurateLibraryNavigationProvider this$0, Subscriber subscriber) throws ServiceException, AbstractHttpClient.HttpClientException {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$fragmentActivity");
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlaylistSyncHelper.syncUpdatedPlaylist(fragmentActivity, new HashSet(Arrays.asList(playlistId)), new PlaybackDatabaseCallbackImpl(this$0, playlistId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUserPlaylistDetail(final Uri contentUri) {
        final FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CurateLibraryNavigationProvider.m745showUserPlaylistDetail$lambda5$lambda4(fragmentActivity, contentUri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showUserPlaylistDetail$lambda-5$lambda-4, reason: not valid java name */
    public static final void m745showUserPlaylistDetail$lambda5$lambda4(FragmentActivity it, Uri contentUri) {
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        it.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).add(R.id.music_screen_container, PlaylistDetailFactory.createFragment$default(LibraryActivityFactory.getIntentForContentUri(it, contentUri), true, null, 4, null)).addToBackStack(PlaylistDetailFragment.class.getSimpleName()).commitAllowingStateLoss();
        logger.debug("Navigating to Playlist {}", contentUri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSharedPlaylistDetail(final String playlistId) {
        final FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CurateLibraryNavigationProvider.m744showSharedPlaylistDetail$lambda7$lambda6(playlistId, fragmentActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showSharedPlaylistDetail$lambda-7$lambda-6, reason: not valid java name */
    public static final void m744showSharedPlaylistDetail$lambda7$lambda6(String playlistId, FragmentActivity it) {
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        Intrinsics.checkNotNullParameter(it, "$it");
        Uri contentUri = MediaProvider.SharedUserPlaylists.getContentUri("cirrus", playlistId);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        bundle.putBoolean("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_IS_USER_PLAYLIST", true);
        it.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).add(R.id.music_screen_container, PlaylistDetailFactory.createFragment(playlistId, "", true, bundle)).addToBackStack(PrimePlaylistDetailFragment.class.getSimpleName()).commitAllowingStateLoss();
        logger.debug("Navigating to shared user playlist {}", playlistId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showEntityPlaylistDetail(final String playlistId) {
        final FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        fragmentActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CurateLibraryNavigationProvider.m741showEntityPlaylistDetail$lambda9$lambda8(playlistId, fragmentActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showEntityPlaylistDetail$lambda-9$lambda-8, reason: not valid java name */
    public static final void m741showEntityPlaylistDetail$lambda9$lambda8(String playlistId, FragmentActivity it) {
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        Intrinsics.checkNotNullParameter(it, "$it");
        Fragment fragmentBuildEntityPlaylistFragment = EntityPlaylistUtil.INSTANCE.buildEntityPlaylistFragment(playlistId, "", false, false);
        it.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).add(R.id.music_screen_container, fragmentBuildEntityPlaylistFragment).addToBackStack(fragmentBuildEntityPlaylistFragment.getClass().getSimpleName()).commitAllowingStateLoss();
        logger.debug("Navigating to entity playlist {}", playlistId);
    }

    @Override // com.amazon.music.curate.provider.LibraryNavigationProvider
    public void showCatalogPlaylist(String asin, List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", asin);
        if (contentUri == null) {
            logger.error("Playlist sync isn't complete. Failed to open catalog playlist detail page.");
            return;
        }
        sendUiClickMetrics(ActionType.SELECT_PLAYLIST, EntityIdType.ASIN, asin, contentInfo);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        Fragment fragmentCreateFragment = PlaylistDetailFactory.createFragment(asin, "", true, bundle);
        FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        fragmentActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).add(R.id.music_screen_container, fragmentCreateFragment).addToBackStack(PrimePlaylistDetailFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    public final void navigateToRecents(long id, String sourceId, String title) {
        Fragment fragmentCreateFragment;
        Fragment fragment;
        Fragment fragment2;
        Intrinsics.checkNotNullParameter(sourceId, "sourceId");
        Intrinsics.checkNotNullParameter(title, "title");
        Uri contentUri = MediaProvider.SmartPlaylists.getContentUri(sourceId, id);
        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(sourceId, id)");
        FragmentActivity fragmentActivity = this.fragmentActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        Fragment fragment3 = null;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            Uri uri = LibraryTracksFragment.INSTANCE.getUri();
            if (id == 1) {
                fragmentCreateFragment = getLibraryFragment(uri, fragmentActivity);
                if (fragmentCreateFragment == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    fragment2 = null;
                } else {
                    fragment2 = fragmentCreateFragment;
                }
                Bundle arguments = fragment2.getArguments();
                if (arguments != null) {
                    arguments.putBoolean(RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.DATE_ADDED), true);
                }
            } else if (id == 2) {
                fragmentCreateFragment = getLibraryFragment(uri, fragmentActivity);
                if (fragmentCreateFragment == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragment");
                    fragment = null;
                } else {
                    fragment = fragmentCreateFragment;
                }
                Bundle arguments2 = fragment.getArguments();
                if (arguments2 != null) {
                    arguments2.putBoolean(RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.DATE_ADDED), true);
                }
                if (arguments2 != null) {
                    arguments2.putBoolean(RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.DOWNLOADED), true);
                }
            } else {
                Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(fragmentActivity, contentUri);
                Intrinsics.checkNotNullExpressionValue(intentForContentUri, "getIntentForContentUri(\n…                        )");
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                fragmentCreateFragment = PlaylistDetailFactory.createFragment(intentForContentUri, false, title);
            }
        } else {
            Intent intentForContentUri2 = LibraryActivityFactory.getIntentForContentUri(fragmentActivity, contentUri);
            Intrinsics.checkNotNullExpressionValue(intentForContentUri2, "getIntentForContentUri(it, playlistUri)");
            intentForContentUri2.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
            fragmentCreateFragment = PlaylistDetailFactory.createFragment(intentForContentUri2, false, title);
        }
        FragmentTransaction customAnimations = fragmentActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out);
        if (fragmentCreateFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            fragment3 = fragmentCreateFragment;
        }
        customAnimations.add(R.id.music_screen_container, fragment3).addToBackStack(PlaylistDetailFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    private final Fragment getLibraryFragment(Uri uri, FragmentActivity fragmentActivity) {
        Fragment fragment = LibraryFragmentFactory.getFragment(fragmentActivity, uri);
        Bundle arguments = fragment.getArguments();
        if (arguments != null) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
        }
        if (arguments != null) {
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId(fragmentActivity));
        }
        Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
        return fragment;
    }

    private final String getSourceId(FragmentActivity fragmentActivity) {
        return LastViewedScreenUtil.getLastViewedSource(fragmentActivity, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
    }

    private final void navigateToPodcasts(FragmentActivity fragmentActivity) {
        FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
        fragmentInfo.setChildFragmentId(null);
        fragmentInfo.setChildFragmentManager(null);
        Podcast.create(MyLibraryPodcastsToggleHelper.INSTANCE.getPodcastBootstrap(fragmentActivity, "")).startLibrary("", false);
    }

    private final void showNoNetwork(FragmentActivity fragmentActivity) {
        if (getNoConnectionDialog().isShowing()) {
            return;
        }
        getNoConnectionDialog().show(fragmentActivity.getSupportFragmentManager(), "NoConnectionDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUiClickMetrics(ActionType actionType, List<? extends Map<String, String>> contentInfo) {
        sendUiClickMetrics(actionType, null, null, contentInfo);
    }

    private final void sendUiClickMetrics(ActionType actionType, EntityIdType entityIdType, String entityId, List<? extends Map<String, String>> contentInfo) {
        if (actionType != null) {
            MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
            UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(actionType).withPageType(PageType.LIBRARY_LANDING_PAGE).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis());
            if (entityIdType != null) {
                builderWithEventTime.withEntityIdType(entityIdType);
            }
            if (entityId != null) {
                builderWithEventTime.withEntityId(entityId);
            }
            if (!contentInfo.isEmpty()) {
                builderWithEventTime.withContentInfo(contentInfo);
            }
            MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
        }
    }
}
