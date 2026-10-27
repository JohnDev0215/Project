package com.amazon.mp3.auto.provider;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.auto.AutoHelper;
import com.amazon.mp3.auto.provider.AutoItem;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.model.search.SearchAlbum;
import com.amazon.music.find.model.search.SearchArtist;
import com.amazon.music.find.model.search.SearchPlaylist;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SearchTrack;
import com.amazon.music.find.utils.ContentUtils;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyVoiceSearchBrowserImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 (2\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010\u00062\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010%\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010&\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchBrowserImpl;", "Lcom/amazon/mp3/auto/provider/AutoSearchBrowser;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allSongsItem", "Lcom/amazon/mp3/auto/provider/AutoItem;", "allSongsUri", "Landroid/net/Uri;", "musicProvider", "Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchProvider;", "getMusicProvider", "()Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchProvider;", "musicProvider$delegate", "Lkotlin/Lazy;", "searchListener", "Lcom/amazon/mp3/auto/provider/AutoSearchListener;", ContentUtils.KEY_SEARCH_QUERY, "", "getAllSongsItem", "getAutoItemFromAlbum", "album", "Lcom/amazon/music/find/model/search/SearchAlbum;", "getAutoItemFromArtist", "artist", "Lcom/amazon/music/find/model/search/SearchArtist;", "getAutoItemFromPlaylist", Environment.PLAYLIST_PATH, "Lcom/amazon/music/find/model/search/SearchPlaylist;", "getAutoItemFromSearch", "response", "Lcom/amazon/music/find/model/search/SearchResponse;", "getAutoItemFromTrack", "track", "Lcom/amazon/music/find/model/search/SearchTrack;", "onLibrarySearchResponseLoaded", "", "onPrimeSearchResponseLoaded", "search", SearchIntents.EXTRA_QUERY, "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LegacyVoiceSearchBrowserImpl implements AutoSearchBrowser {
    private static final String TAG = LegacyVoiceSearchBrowserImpl.class.getSimpleName();
    private AutoItem allSongsItem;
    private Uri allSongsUri;
    private final Context context;

    /* JADX INFO: renamed from: musicProvider$delegate, reason: from kotlin metadata */
    private final Lazy musicProvider;
    private AutoSearchListener searchListener;
    private String searchQuery;

    public LegacyVoiceSearchBrowserImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.musicProvider = LazyKt__LazyJVMKt.lazy(new Function0<LegacyVoiceSearchProvider>() { // from class: com.amazon.mp3.auto.provider.LegacyVoiceSearchBrowserImpl$musicProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LegacyVoiceSearchProvider invoke() {
                return new LegacyVoiceSearchProvider(this.this$0.context, this.this$0);
            }
        });
        this.allSongsItem = getAllSongsItem();
    }

    private final LegacyVoiceSearchProvider getMusicProvider() {
        return (LegacyVoiceSearchProvider) this.musicProvider.getValue();
    }

    @Override // com.amazon.mp3.auto.provider.AutoSearchBrowser
    public void search(String query, AutoSearchListener searchListener) {
        Intrinsics.checkNotNullParameter(searchListener, "searchListener");
        if (TextUtils.isEmpty(query)) {
            searchListener.onAutoSearchCompleted(this.allSongsItem);
            return;
        }
        this.searchQuery = query;
        this.searchListener = searchListener;
        getMusicProvider().searchLibrary(this.searchQuery);
    }

    public void onLibrarySearchResponseLoaded(SearchResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AutoItem autoItemFromSearch = getAutoItemFromSearch(response);
        if (autoItemFromSearch == null) {
            getMusicProvider().searchPrime(this.searchQuery);
            return;
        }
        AutoSearchListener autoSearchListener = this.searchListener;
        if (autoSearchListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchListener");
            autoSearchListener = null;
        }
        autoSearchListener.onAutoSearchCompleted(autoItemFromSearch);
    }

    public void onPrimeSearchResponseLoaded(SearchResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AutoSearchListener autoSearchListener = null;
        AutoItem autoItemFromPlaylist = response.getNumberOfPlaylistsAvailable() > 0 ? getAutoItemFromPlaylist((SearchPlaylist) CollectionsKt___CollectionsKt.first((List) response.getPlaylists())) : null;
        AutoSearchListener autoSearchListener2 = this.searchListener;
        if (autoSearchListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchListener");
        } else {
            autoSearchListener = autoSearchListener2;
        }
        autoSearchListener.onAutoSearchCompleted(autoItemFromPlaylist);
    }

    private final AutoItem getAllSongsItem() {
        if (AccountDetailUtil.get(this.context).canViewAndPlayCloudContent() && !UserSubscriptionUtil.isNightwingOnly()) {
            getMusicProvider().getAllSongsUri(this.allSongsUri);
            if (AutoHelper.hasMusic(this.context, this.allSongsUri)) {
                this.allSongsItem = new AutoItem(this.allSongsUri, null, AutoItem.AutoItemType.SHUFFLE_ALL_TRACKS);
            }
        }
        return this.allSongsItem;
    }

    private final AutoItem getAutoItemFromSearch(SearchResponse response) {
        if (response == null) {
            return null;
        }
        if (response.getNumberOfPlaylistsAvailable() > 0) {
            return getAutoItemFromPlaylist((SearchPlaylist) CollectionsKt___CollectionsKt.first((List) response.getPlaylists()));
        }
        if (response.getNumberOfArtistsAvailable() > 0) {
            return getAutoItemFromArtist((SearchArtist) CollectionsKt___CollectionsKt.first((List) response.getArtists()));
        }
        if (response.getNumberOfAlbumsAvailable() > 0) {
            return getAutoItemFromAlbum((SearchAlbum) CollectionsKt___CollectionsKt.first((List) response.getAlbums()));
        }
        if (response.getNumberOfTracksAvailable() > 0) {
            return getAutoItemFromTrack((SearchTrack) CollectionsKt___CollectionsKt.first((List) response.getTracks()));
        }
        return null;
    }

    private final AutoItem getAutoItemFromPlaylist(SearchPlaylist playlist) {
        Uri contentUri;
        AutoItem.AutoItemType autoItemType;
        if (playlist.getAsin() != null) {
            contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getAsin());
            autoItemType = AutoItem.AutoItemType.PRIME_PLAYLIST;
        } else {
            Long id = playlist.getId();
            contentUri = id != null ? MediaProvider.UdoPlaylistTracks.getContentUri("cirrus", id.longValue()) : null;
            autoItemType = AutoItem.AutoItemType.CLOUD_PLAYLIST;
        }
        return new AutoItem(contentUri, playlist.getAsin(), playlist.getTitle(), autoItemType);
    }

    private final AutoItem getAutoItemFromArtist(SearchArtist artist) {
        Long id = artist.getId();
        return new AutoItem(id != null ? MediaProvider.Artists.Tracks.getContentUri("cirrus", id.longValue()) : null, artist.getAsin(), artist.getTitle(), AutoItem.AutoItemType.ARTIST);
    }

    private final AutoItem getAutoItemFromAlbum(SearchAlbum album) {
        Long id = album.getId();
        return new AutoItem(id != null ? MediaProvider.Albums.Tracks.getContentUri("cirrus", id.longValue()) : null, album.getAsin(), album.getTitle(), AutoItem.AutoItemType.ALBUM);
    }

    private final AutoItem getAutoItemFromTrack(SearchTrack track) {
        String luid = track.getLuid();
        if (!(track instanceof PrimeTrack)) {
            return null;
        }
        PrimeTrack primeTrack = (PrimeTrack) track;
        return new AutoItem(luid != null ? MediaProvider.Tracks.getContentUri("cirrus", luid) : null, primeTrack.getAsin(), primeTrack.getTitle(), AutoItem.AutoItemType.TRACK);
    }
}
