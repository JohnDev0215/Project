package com.amazon.mp3.search.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.ArtistDetailsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.ArtistDestinationHandler;
import com.amazon.mp3.navigation.PlaylistDestinationHandler;
import com.amazon.mp3.navigation.TrackDetailDestinationHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.freetier.destination.TrackDetailDestination;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchFragmentUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b*\u0010+J6\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005H\u0007J\u001a\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0007J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0007J\u001c\u0010\u000e\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0007J<\u0010\u001c\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\t0\u0019H\u0007J&\u0010\u001c\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0007J\u001c\u0010\u001f\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0007J\"\u0010#\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020\u0005H\u0007J\u0016\u0010%\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020$J\u001e\u0010(\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u0017J\u0016\u0010)\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u0015¨\u0006,"}, d2 = {"Lcom/amazon/mp3/search/view/SearchFragmentUtils;", "", "Landroidx/fragment/app/Fragment;", "fromFragment", "toFragment", "", "addToBackStack", "forceReplace", "animate", "", "changeScreenFragment", "fragment", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "contentMetadata", "navigateToArtistDetail", "navigateToBauhausLibraryArtistDetail", "navigateToLegacyArtistDetail", "navigateToMyMusicArtistDetail", "Landroid/content/Context;", "context", "artistMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "playlistMetadata", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "lookupError", "navigateToPlaylistDetail", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "trackMetadata", "navigateToTrackDetail", "Lcom/amazon/mp3/prime/browse/metadata/PrimeAlbum;", "item", "didNavigateFromSearch", "getAlbumDetailsFragment", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "getAlbumDetailsFragmentForOfflineMode", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/mp3/library/fragment/ArtistDetailsFragment;", "getMyMusicArtistDetailsFragment", "getPlaylistDetailsFragmentForOfflineMode", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchFragmentUtils {
    public static final SearchFragmentUtils INSTANCE = new SearchFragmentUtils();

    @JvmStatic
    @JvmOverloads
    public static final Fragment getAlbumDetailsFragment(Context context, PrimeAlbum item) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        return getAlbumDetailsFragment$default(context, item, false, 4, null);
    }

    private SearchFragmentUtils() {
    }

    public static /* synthetic */ void changeScreenFragment$default(Fragment fragment, Fragment fragment2, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = true;
        }
        changeScreenFragment(fragment, fragment2, z, z2, z3);
    }

    @JvmStatic
    public static final void changeScreenFragment(Fragment fromFragment, Fragment toFragment, boolean addToBackStack, boolean forceReplace, boolean animate) {
        Intrinsics.checkNotNullParameter(fromFragment, "fromFragment");
        Intrinsics.checkNotNullParameter(toFragment, "toFragment");
        FragmentActivity activity = fromFragment.getActivity();
        if (activity == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
        }
        ((MusicHomeActivity) activity).changeScreenFragment(toFragment, addToBackStack, forceReplace, animate);
    }

    @JvmStatic
    public static final void navigateToArtistDetail(Fragment fragment, ArtistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (contentMetadata == null) {
            return;
        }
        Context context = fragment.getContext();
        boolean zShouldDisplayBauhausLibraryPage = context == null ? false : LibraryArtistDetailFragment.INSTANCE.shouldDisplayBauhausLibraryPage(context, contentMetadata.getSource());
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        if (zShouldDisplayBauhausLibraryPage) {
            INSTANCE.navigateToBauhausLibraryArtistDetail(contentMetadata, fragment);
        } else {
            if (ConnectivityUtil.hasAnyInternetConnection(applicationContext) && DMSFNavigationProvider.Companion.openArtistDetailPage$default(DMSFNavigationProvider.INSTANCE, contentMetadata.getAsin(), null, null, null, 14, null)) {
                return;
            }
            INSTANCE.navigateToLegacyArtistDetail(contentMetadata, fragment);
        }
    }

    private final void navigateToBauhausLibraryArtistDetail(ArtistMetadata contentMetadata, Fragment fragment) {
        String asin = contentMetadata.getAsin();
        LibraryArtistDetailFragment.Companion companion = LibraryArtistDetailFragment.INSTANCE;
        Bundle bundle = new Bundle();
        bundle.putString("ARTIST_ASIN", asin);
        bundle.putString("ARTIST_NAME", contentMetadata.getTitle());
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", contentMetadata.getSource());
        changeScreenFragment$default(fragment, LibraryArtistDetailFragment.Companion.newInstance$default(companion, bundle, null, 2, null), false, false, false, 28, null);
    }

    private final void navigateToLegacyArtistDetail(ArtistMetadata contentMetadata, Fragment fragment) {
        String source = contentMetadata.getSource();
        if (source != null && (source.equals("cirrus") || source.equals("cirrus-local"))) {
            navigateToMyMusicArtistDetail(fragment, contentMetadata);
        } else {
            navigateToArtistDetail(fragment.getContext(), contentMetadata);
        }
    }

    @JvmStatic
    public static final void navigateToMyMusicArtistDetail(Fragment fragment, ArtistMetadata contentMetadata) {
        String source;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Context context = fragment.getContext();
        if (context == null || (source = contentMetadata.getSource()) == null) {
            return;
        }
        changeScreenFragment$default(fragment, INSTANCE.getMyMusicArtistDetailsFragment(context, contentMetadata, source), false, false, false, 28, null);
    }

    @JvmStatic
    public static final void navigateToArtistDetail(Context context, ArtistMetadata artistMetadata) {
        if (context == null || artistMetadata == null) {
            return;
        }
        new ArtistDestinationHandler().navigateTo(context, new ArtistDestination(SearchItemUtils.getTarget(artistMetadata), artistMetadata.getAsin(), null, null, null, null));
    }

    @JvmStatic
    public static final void navigateToPlaylistDetail(Context context, PlaylistMetadata playlistMetadata, String trackAsin, Function1<? super LookupError, Unit> lookupError) {
        String asin;
        Intrinsics.checkNotNullParameter(lookupError, "lookupError");
        if (context == null || playlistMetadata == null || (asin = playlistMetadata.getAsin()) == null) {
            return;
        }
        new PlaylistDestinationHandler(lookupError).navigateTo(context, new PlaylistDestination(asin, trackAsin, trackAsin != null ? Action.PLAY : null, null, null, null));
    }

    public static /* synthetic */ void navigateToPlaylistDetail$default(Context context, PlaylistMetadata playlistMetadata, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        navigateToPlaylistDetail(context, playlistMetadata, str);
    }

    @JvmStatic
    public static final void navigateToPlaylistDetail(final Context context, PlaylistMetadata playlistMetadata, String trackAsin) {
        Intrinsics.checkNotNullParameter(playlistMetadata, "playlistMetadata");
        navigateToPlaylistDetail(context, playlistMetadata, trackAsin, new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.search.view.SearchFragmentUtils.navigateToPlaylistDetail.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                invoke2(lookupError);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final LookupError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Context context2 = context;
                if (context2 == null) {
                    return;
                }
                ContextKt.runOnUiThread$default(context2, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.search.view.SearchFragmentUtils.navigateToPlaylistDetail.2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Context context3) {
                        invoke2(context3);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Context it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        error.handleError(new Function0<Unit>() { // from class: com.amazon.mp3.search.view.SearchFragmentUtils.navigateToPlaylistDetail.2.1.1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }, 1, null);
            }
        });
    }

    @JvmStatic
    public static final void navigateToTrackDetail(Context context, TrackMetadata trackMetadata) {
        if (context == null || trackMetadata == null) {
            return;
        }
        TrackDetailDestinationHandler trackDetailDestinationHandler = new TrackDetailDestinationHandler();
        String asin = trackMetadata.getAsin();
        Intrinsics.checkNotNull(asin);
        trackDetailDestinationHandler.navigateTo(context, new TrackDetailDestination(asin, null, null));
    }

    public static /* synthetic */ Fragment getAlbumDetailsFragment$default(Context context, PrimeAlbum primeAlbum, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return getAlbumDetailsFragment(context, primeAlbum, z);
    }

    @JvmStatic
    @JvmOverloads
    public static final Fragment getAlbumDetailsFragment(Context context, PrimeAlbum item, boolean didNavigateFromSearch) {
        Fragment albumDetailFragment;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        Long id = item.getId();
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri("cirrus", id == null ? IdGenerator.generateAlbumId(item.getAsin(), item.getTitle()) : id.longValue()));
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", item.getAsin());
            albumDetailFragment = new AlbumDetailFragment();
        } else {
            intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", item.getAsin()).putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true).putExtra("navigation_source_extra", didNavigateFromSearch).putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            albumDetailFragment = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
        }
        albumDetailFragment.setArguments(intentForContentUri.getExtras());
        return albumDetailFragment;
    }

    public final Fragment getAlbumDetailsFragmentForOfflineMode(Context context, AlbumMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri("cirrus-local", MediaProvider.Albums.getAlbumId(contentMetadata.getUri())));
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            intentForContentUri.putExtra("EXTRA_OFFLINE_LIBRARY_NAVIGATION", true);
            AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
            albumDetailFragment.setArguments(intentForContentUri.getExtras());
            return albumDetailFragment;
        }
        intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", contentMetadata.getAsin());
        intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        com.amazon.mp3.library.fragment.AlbumDetailFragment albumDetailFragment2 = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
        albumDetailFragment2.setArguments(intentForContentUri.getExtras());
        return albumDetailFragment2;
    }

    public final ArtistDetailsFragment getMyMusicArtistDetailsFragment(Context context, ArtistMetadata contentMetadata, String source) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(source, "source");
        ArtistDetailsFragment artistDetailsFragmentNewInstance = ArtistDetailsFragment.newInstance(context, contentMetadata.getTitle(), source);
        Intrinsics.checkNotNullExpressionValue(artistDetailsFragmentNewInstance, "newInstance(\n           …         source\n        )");
        return artistDetailsFragmentNewInstance;
    }

    public final Fragment getPlaylistDetailsFragmentForOfflineMode(Context context, PlaylistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String asin = contentMetadata.getAsin();
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus-local", asin);
        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(source, playlistAsin)");
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local");
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        bundle.putBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
        bundle.putBoolean("navigation_source_extra", true);
        return PlaylistDetailFactory.createFragment$default(asin, contentMetadata.getTitle(), false, bundle, 4, null);
    }
}
