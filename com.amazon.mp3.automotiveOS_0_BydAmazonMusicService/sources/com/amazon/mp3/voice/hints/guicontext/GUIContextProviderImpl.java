package com.amazon.mp3.voice.hints.guicontext;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeDisclaimerFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeExitFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeNowPlayingFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingAlexaFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeOnBoardingPresetFragment;
import com.amazon.mp3.auto.carmode.fragment.CarModeWelcomeFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.ChartsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.FindBrushFragment;
import com.amazon.mp3.catalog.fragment.GenreDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.library.fragment.AlbumDetailFragment;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.platform.aom.hints.guiContext.GUIContext;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextMetadataKey;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextNowPlayingItemType;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextPageType;
import com.amazon.music.platform.aom.hints.provider.GUIContextProvider;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: GUIContextProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0007J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\bH\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/voice/hints/guicontext/GUIContextProviderImpl;", "Lcom/amazon/music/platform/aom/hints/provider/GUIContextProvider;", "()V", "getCurrentFragment", "Landroidx/fragment/app/Fragment;", "getGUIContext", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContext;", "isCloudLibraryPlaylistPage", "", "currFragment", "isCurrentTrackVideo", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GUIContextProviderImpl implements GUIContextProvider {
    private static final String TAG = Reflection.getOrCreateKotlinClass(GUIContextProviderImpl.class).getSimpleName();

    @Override // com.amazon.music.platform.aom.hints.provider.GUIContextProvider
    public GUIContext getGUIContext() {
        GUIContext gUIContext;
        GUIContext gUIContext2;
        Fragment currentFragment = getCurrentFragment();
        try {
            if (currentFragment instanceof NewReleasesDetailBrushFragment) {
                gUIContext2 = new GUIContext(GUIContextPageType.NEW_RELEASE_LANDING, MapsKt__MapsKt.emptyMap());
            } else if (currentFragment instanceof ChartsDetailBrushFragment) {
                gUIContext2 = new GUIContext(GUIContextPageType.MOST_POPULAR_LANDING, MapsKt__MapsKt.emptyMap());
            } else {
                boolean z = true;
                if (currentFragment instanceof MyLibraryHomeFragment ? true : currentFragment instanceof MyLibraryV2HomeFragment) {
                    gUIContext2 = new GUIContext(GUIContextPageType.LIBRARY, MapsKt__MapsKt.emptyMap());
                } else if (currentFragment instanceof PrimeTabFragmentHost) {
                    gUIContext2 = new GUIContext(GUIContextPageType.BROWSE_HOME, MapsKt__MapsKt.emptyMap());
                } else {
                    if (currentFragment instanceof FindBrushFragment ? true : currentFragment instanceof SearchBrushFragment) {
                        gUIContext2 = new GUIContext(GUIContextPageType.SEARCH, MapsKt__MapsKt.emptyMap());
                    } else {
                        if (currentFragment instanceof AlbumDetailFragment ? true : currentFragment instanceof com.amazon.mp3.detailpages.album.AlbumDetailFragment) {
                            gUIContext2 = new GUIContext(GUIContextPageType.ALBUM_DETAIL, MapsKt__MapsKt.emptyMap());
                        } else {
                            if (!(currentFragment instanceof CarModeDisclaimerFragment ? true : currentFragment instanceof CarModeWelcomeFragment ? true : currentFragment instanceof CarModeOnBoardingFragment ? true : currentFragment instanceof CarModeOnBoardingAlexaFragment ? true : currentFragment instanceof CarModeOnBoardingPresetFragment ? true : currentFragment instanceof CarModeBrushFragment ? true : currentFragment instanceof CarModeNowPlayingFragment)) {
                                z = currentFragment instanceof CarModeExitFragment;
                            }
                            if (z) {
                                gUIContext2 = new GUIContext(GUIContextPageType.CAR_MODE, MapsKt__MapsKt.emptyMap());
                            } else {
                                if (currentFragment instanceof NowPlayingFragment) {
                                    gUIContext = new GUIContext(GUIContextPageType.NOW_PLAYING, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.INTERNAL_NOW_PLAYING_ITEM_TYPE, (isCurrentTrackVideo() ? GUIContextNowPlayingItemType.VIDEO : GUIContextNowPlayingItemType.AUDIO).name())));
                                } else if (currentFragment instanceof MyLibraryDetailFragment) {
                                    if (!isCloudLibraryPlaylistPage(currentFragment)) {
                                        return null;
                                    }
                                    gUIContext2 = new GUIContext(GUIContextPageType.CLOUD_LIBRARY_PLAYLIST_LANDING, MapsKt__MapsKt.emptyMap());
                                } else if (currentFragment instanceof ArtistDetailBrushFragment) {
                                    gUIContext = new GUIContext(GUIContextPageType.ARTIST_DETAIL, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.ARTIST_NAME, ((ArtistDetailBrushFragment) currentFragment).getArtistName())));
                                } else if (currentFragment instanceof GenreDetailBrushFragment) {
                                    gUIContext = new GUIContext(GUIContextPageType.GENRE_LANDING, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.GENRE_NAME, ((GenreDetailBrushFragment) currentFragment).getGenreName())));
                                } else if (currentFragment instanceof PrimePlaylistDetailFragment) {
                                    gUIContext = new GUIContext(GUIContextPageType.PLAYLIST_DETAIL, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.PLAYLIST_NAME, ((PrimePlaylistDetailFragment) currentFragment).getPlaylistName())));
                                } else if (currentFragment instanceof PlaylistDetailFragment) {
                                    if (!((PlaylistDetailFragment) currentFragment).isUserPlaylistPage()) {
                                        return null;
                                    }
                                    gUIContext = new GUIContext(GUIContextPageType.USER_PLAYLIST_DETAIL, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.PLAYLIST_NAME, ((PlaylistDetailFragment) currentFragment).getUserPlaylistName())));
                                } else {
                                    if (currentFragment instanceof com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment) {
                                        return new GUIContext(((com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment) currentFragment).getIsCatalog() ? GUIContextPageType.PLAYLIST_DETAIL : GUIContextPageType.USER_PLAYLIST_DETAIL, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(GUIContextMetadataKey.PLAYLIST_NAME, ((com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment) currentFragment).getPlaylistName())));
                                    }
                                    return null;
                                }
                                return gUIContext;
                            }
                        }
                    }
                }
            }
            return gUIContext2;
        } catch (GUIMetadataNotReadyException e) {
            Log.error(TAG, e + ".message");
            return null;
        }
    }

    @VisibleForTesting
    public final Fragment getCurrentFragment() {
        return AndroidUtils.getVisibleFragment(AndroidUtils.getCurrentActivity());
    }

    @VisibleForTesting
    public final boolean isCloudLibraryPlaylistPage(Fragment currFragment) {
        Intrinsics.checkNotNullParameter(currFragment, "currFragment");
        if (!(currFragment instanceof MyLibraryDetailFragment)) {
            return false;
        }
        String pageTitle = ((MyLibraryDetailFragment) currFragment).getPageTitle();
        String string = currFragment.getString(R.string.dmusic_library_playlists_tab);
        Intrinsics.checkNotNullExpressionValue(string, "currFragment.getString(R…ic_library_playlists_tab)");
        String upperCase = string.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        return Intrinsics.areEqual(pageTitle, upperCase);
    }

    @VisibleForTesting
    public final boolean isCurrentTrackVideo() {
        return NowPlayingUtil.isVideoPlaying();
    }
}
