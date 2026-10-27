package com.amazon.music.media.auto.tab.search;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.ContentAccessType;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.auto.R$string;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.BrushConverterUtil;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.media.auto.util.LibraryExtensionsKt;
import com.amazon.music.search.Album;
import com.amazon.music.search.Artist;
import com.amazon.music.search.Playlist;
import com.amazon.music.search.PlaymodeEligibility;
import com.amazon.music.search.ResultItem;
import com.amazon.music.search.ResultType;
import com.amazon.music.search.SearchContentTier;
import com.amazon.music.search.SearchResult;
import com.amazon.music.search.Station;
import com.amazon.music.search.Track;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* JADX INFO: compiled from: TextSearchConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 *2\u00020\u0001:\u0001*B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018J \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001b0\u00192\u0006\u0010\u001c\u001a\u00020\u001dJ$\u0010\u001e\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020!H\u0002J$\u0010\"\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020!H\u0002J$\u0010$\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020%2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020!H\u0002J\u001c\u0010&\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020'2\b\b\u0002\u0010 \u001a\u00020!H\u0002J$\u0010(\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020!H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006+"}, d2 = {"Lcom/amazon/music/media/auto/tab/search/SearchContentConverter;", "", "context", "Landroid/content/Context;", "contentPlaybackUtils", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "brushConverterUtils", "Lcom/amazon/music/media/auto/util/BrushConverterUtil;", "numberOfResults", "", "numberOfTopHits", "maxNumberOfLibraryResults", "(Landroid/content/Context;Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Lcom/amazon/music/media/auto/util/BrushConverterUtil;III)V", "getNumberOfResults", "()I", "getNumberOfTopHits", "convert", "Lcom/amazon/music/search/SearchContentTier;", "selector", "Lcom/amazon/music/browse/BrowseContentSelector;", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "item", "Lcom/amazon/music/search/ResultItem;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/search/ResultType;", "", "", "", "searchResult", "Lcom/amazon/music/search/SearchResult;", "convertAlbum", "Lcom/amazon/music/search/Album;", "isTopHit", "", "convertArtist", "Lcom/amazon/music/search/Artist;", "convertPlaylist", "Lcom/amazon/music/search/Playlist;", "convertStation", "Lcom/amazon/music/search/Station;", "convertTrack", "Lcom/amazon/music/search/Track;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchContentConverter {
    private static final String TAG = SearchContentConverter.class.getSimpleName();
    private final BrushConverterUtil brushConverterUtils;
    private final ContentPlaybackUtil contentPlaybackUtils;
    private final Context context;
    private final int maxNumberOfLibraryResults;
    private final int numberOfResults;
    private final int numberOfTopHits;

    /* JADX INFO: compiled from: TextSearchConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BrowseContentSelector.values().length];
            iArr[BrowseContentSelector.FREE.ordinal()] = 1;
            iArr[BrowseContentSelector.PRIME.ordinal()] = 2;
            iArr[BrowseContentSelector.SONIC_RUSH.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SearchContentConverter(Context context, ContentPlaybackUtil contentPlaybackUtils, BrushConverterUtil brushConverterUtils, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentPlaybackUtils, "contentPlaybackUtils");
        Intrinsics.checkNotNullParameter(brushConverterUtils, "brushConverterUtils");
        this.context = context;
        this.contentPlaybackUtils = contentPlaybackUtils;
        this.brushConverterUtils = brushConverterUtils;
        this.numberOfResults = i;
        this.numberOfTopHits = i2;
        this.maxNumberOfLibraryResults = i3;
    }

    public /* synthetic */ SearchContentConverter(Context context, ContentPlaybackUtil contentPlaybackUtil, BrushConverterUtil brushConverterUtil, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, contentPlaybackUtil, brushConverterUtil, (i4 & 8) != 0 ? 5 : i, (i4 & 16) != 0 ? 5 : i2, (i4 & 32) != 0 ? 3 : i3);
    }

    public final int getNumberOfResults() {
        return this.numberOfResults;
    }

    public final int getNumberOfTopHits() {
        return this.numberOfTopHits;
    }

    public final SearchContentTier convert(BrowseContentSelector selector) {
        int i = selector == null ? -1 : WhenMappings.$EnumSwitchMapping$0[selector.ordinal()];
        if (i == 1) {
            return SearchContentTier.NIGHTWING_ONDEMAND_PLAYABLE;
        }
        if (i == 2) {
            return SearchContentTier.PRIME;
        }
        if (i == 3) {
            return SearchContentTier.SONIC_RUSH;
        }
        return SearchContentTier.UNLIMITED;
    }

    public final Map<String, List<AutoMediaItem>> convert(SearchResult searchResult) {
        List list;
        Intrinsics.checkNotNullParameter(searchResult, "searchResult");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Collection<ResultItem> page = searchResult.getTopHits().getPage();
        Intrinsics.checkNotNullExpressionValue(page, "searchResult.topHits.page");
        ArrayList arrayList = new ArrayList();
        for (ResultItem it : page) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            AutoMediaItem autoMediaItemConvert = convert(it, ResultType.Catalog);
            if (autoMediaItemConvert != null) {
                arrayList.add(autoMediaItemConvert);
            }
        }
        List listTake = CollectionsKt___CollectionsKt.take(arrayList, this.numberOfTopHits);
        Collection<Track> page2 = searchResult.getTracks(ResultType.Library).getPage();
        Intrinsics.checkNotNullExpressionValue(page2, "searchResult.getTracks(ResultType.Library).page");
        ArrayList arrayList2 = new ArrayList();
        for (Track it2 : page2) {
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            AutoMediaItem autoMediaItemConvertTrack$default = convertTrack$default(this, it2, ResultType.Library, false, 4, null);
            if (autoMediaItemConvertTrack$default != null) {
                arrayList2.add(autoMediaItemConvertTrack$default);
            }
        }
        List listTake2 = CollectionsKt___CollectionsKt.take(arrayList2, this.maxNumberOfLibraryResults);
        Collection<Track> page3 = searchResult.getTracks(ResultType.Catalog).getPage();
        Intrinsics.checkNotNullExpressionValue(page3, "searchResult.getTracks(ResultType.Catalog).page");
        ArrayList arrayList3 = new ArrayList();
        for (Track it3 : page3) {
            Intrinsics.checkNotNullExpressionValue(it3, "it");
            AutoMediaItem autoMediaItemConvertTrack$default2 = convertTrack$default(this, it3, ResultType.Catalog, false, 4, null);
            if (autoMediaItemConvertTrack$default2 != null) {
                arrayList3.add(autoMediaItemConvertTrack$default2);
            }
        }
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) listTake2, (Iterable) arrayList3);
        ArrayList arrayList4 = new ArrayList();
        Iterator it4 = listPlus.iterator();
        while (it4.hasNext()) {
            AutoMediaItem autoMediaItemConvert$deduplicate = convert$deduplicate(linkedHashSet, (AutoMediaItem) it4.next());
            if (autoMediaItemConvert$deduplicate != null) {
                arrayList4.add(autoMediaItemConvert$deduplicate);
            }
        }
        List listTake3 = CollectionsKt___CollectionsKt.take(arrayList4, this.numberOfResults);
        Collection<Album> page4 = searchResult.getAlbums(ResultType.Library).getPage();
        Intrinsics.checkNotNullExpressionValue(page4, "searchResult.getAlbums(ResultType.Library).page");
        ArrayList arrayList5 = new ArrayList();
        for (Album it5 : page4) {
            Intrinsics.checkNotNullExpressionValue(it5, "it");
            AutoMediaItem autoMediaItemConvertAlbum$default = convertAlbum$default(this, it5, ResultType.Library, false, 4, null);
            if (autoMediaItemConvertAlbum$default != null) {
                arrayList5.add(autoMediaItemConvertAlbum$default);
            }
        }
        List listTake4 = CollectionsKt___CollectionsKt.take(arrayList5, this.maxNumberOfLibraryResults);
        Collection<Album> page5 = searchResult.getAlbums(ResultType.Catalog).getPage();
        Intrinsics.checkNotNullExpressionValue(page5, "searchResult.getAlbums(ResultType.Catalog).page");
        ArrayList arrayList6 = new ArrayList();
        for (Album it6 : page5) {
            Intrinsics.checkNotNullExpressionValue(it6, "it");
            AutoMediaItem autoMediaItemConvertAlbum$default2 = convertAlbum$default(this, it6, ResultType.Catalog, false, 4, null);
            if (autoMediaItemConvertAlbum$default2 != null) {
                arrayList6.add(autoMediaItemConvertAlbum$default2);
            }
        }
        List listPlus2 = CollectionsKt___CollectionsKt.plus((Collection) listTake4, (Iterable) arrayList6);
        ArrayList arrayList7 = new ArrayList();
        Iterator it7 = listPlus2.iterator();
        while (it7.hasNext()) {
            AutoMediaItem autoMediaItemConvert$deduplicate2 = convert$deduplicate(linkedHashSet, (AutoMediaItem) it7.next());
            if (autoMediaItemConvert$deduplicate2 != null) {
                arrayList7.add(autoMediaItemConvert$deduplicate2);
            }
        }
        List listTake5 = CollectionsKt___CollectionsKt.take(arrayList7, this.numberOfResults);
        Collection<Artist> page6 = searchResult.getArtists(ResultType.Library).getPage();
        Intrinsics.checkNotNullExpressionValue(page6, "searchResult.getArtists(ResultType.Library).page");
        ArrayList arrayList8 = new ArrayList();
        for (Artist it8 : page6) {
            Intrinsics.checkNotNullExpressionValue(it8, "it");
            AutoMediaItem autoMediaItemConvertArtist$default = convertArtist$default(this, it8, ResultType.Library, false, 4, null);
            if (autoMediaItemConvertArtist$default != null) {
                arrayList8.add(autoMediaItemConvertArtist$default);
            }
        }
        List listTake6 = CollectionsKt___CollectionsKt.take(arrayList8, this.maxNumberOfLibraryResults);
        Collection<Artist> page7 = searchResult.getArtists(ResultType.Catalog).getPage();
        Intrinsics.checkNotNullExpressionValue(page7, "searchResult.getArtists(ResultType.Catalog).page");
        ArrayList arrayList9 = new ArrayList();
        for (Artist it9 : page7) {
            Intrinsics.checkNotNullExpressionValue(it9, "it");
            List list2 = listTake5;
            ArrayList arrayList10 = arrayList9;
            AutoMediaItem autoMediaItemConvertArtist$default2 = convertArtist$default(this, it9, ResultType.Catalog, false, 4, null);
            if (autoMediaItemConvertArtist$default2 != null) {
                arrayList10.add(autoMediaItemConvertArtist$default2);
            }
            arrayList9 = arrayList10;
            listTake5 = list2;
        }
        List list3 = listTake5;
        List listPlus3 = CollectionsKt___CollectionsKt.plus((Collection) listTake6, (Iterable) arrayList9);
        ArrayList arrayList11 = new ArrayList();
        Iterator it10 = listPlus3.iterator();
        while (it10.hasNext()) {
            AutoMediaItem autoMediaItemConvert$deduplicate3 = convert$deduplicate(linkedHashSet, (AutoMediaItem) it10.next());
            if (autoMediaItemConvert$deduplicate3 != null) {
                arrayList11.add(autoMediaItemConvert$deduplicate3);
            }
        }
        List listTake7 = CollectionsKt___CollectionsKt.take(arrayList11, this.numberOfResults);
        Collection<Playlist> page8 = searchResult.getPlaylists(ResultType.Library).getPage();
        Intrinsics.checkNotNullExpressionValue(page8, "searchResult.getPlaylists(ResultType.Library).page");
        ArrayList arrayList12 = new ArrayList();
        for (Playlist it11 : page8) {
            Intrinsics.checkNotNullExpressionValue(it11, "it");
            AutoMediaItem autoMediaItemConvertPlaylist$default = convertPlaylist$default(this, it11, ResultType.Library, false, 4, null);
            if (autoMediaItemConvertPlaylist$default != null) {
                arrayList12.add(autoMediaItemConvertPlaylist$default);
            }
        }
        List listTake8 = CollectionsKt___CollectionsKt.take(arrayList12, this.maxNumberOfLibraryResults);
        Collection<Playlist> page9 = searchResult.getPlaylists(ResultType.Catalog).getPage();
        Intrinsics.checkNotNullExpressionValue(page9, "searchResult.getPlaylists(ResultType.Catalog).page");
        ArrayList arrayList13 = new ArrayList();
        for (Playlist it12 : page9) {
            Intrinsics.checkNotNullExpressionValue(it12, "it");
            ArrayList arrayList14 = arrayList13;
            AutoMediaItem autoMediaItemConvertPlaylist$default2 = convertPlaylist$default(this, it12, ResultType.Catalog, false, 4, null);
            if (autoMediaItemConvertPlaylist$default2 != null) {
                arrayList14.add(autoMediaItemConvertPlaylist$default2);
            }
            arrayList13 = arrayList14;
        }
        List listPlus4 = CollectionsKt___CollectionsKt.plus((Collection) listTake8, (Iterable) arrayList13);
        ArrayList arrayList15 = new ArrayList();
        Iterator it13 = listPlus4.iterator();
        while (it13.hasNext()) {
            AutoMediaItem autoMediaItemConvert$deduplicate4 = convert$deduplicate(linkedHashSet, (AutoMediaItem) it13.next());
            if (autoMediaItemConvert$deduplicate4 != null) {
                arrayList15.add(autoMediaItemConvert$deduplicate4);
            }
        }
        List listTake9 = CollectionsKt___CollectionsKt.take(arrayList15, this.numberOfResults);
        Collection<Station> page10 = searchResult.getStations().getPage();
        Intrinsics.checkNotNullExpressionValue(page10, "searchResult.stations.page");
        ArrayList arrayList16 = new ArrayList();
        for (Station it14 : page10) {
            Intrinsics.checkNotNullExpressionValue(it14, "it");
            AutoMediaItem autoMediaItemConvertStation$default = convertStation$default(this, it14, false, 2, null);
            if (autoMediaItemConvertStation$default != null) {
                arrayList16.add(autoMediaItemConvertStation$default);
            }
        }
        ArrayList arrayList17 = new ArrayList();
        Iterator it15 = arrayList16.iterator();
        while (it15.hasNext()) {
            AutoMediaItem autoMediaItemConvert$deduplicate5 = convert$deduplicate(linkedHashSet, (AutoMediaItem) it15.next());
            if (autoMediaItemConvert$deduplicate5 != null) {
                arrayList17.add(autoMediaItemConvert$deduplicate5);
            }
        }
        List listTake10 = CollectionsKt___CollectionsKt.take(arrayList17, this.numberOfResults);
        String string = this.context.getString(SearchContentTypes.TOP_HITS.getStringResource());
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(Search….TOP_HITS.stringResource)");
        linkedHashMap.put(string, listTake);
        Collection page11 = searchResult.getTopHits().getPage();
        Intrinsics.checkNotNullExpressionValue(page11, "searchResult.topHits.page");
        for (ResultItem resultItem : CollectionsKt___CollectionsKt.filterNotNull(page11)) {
            if (resultItem instanceof Track) {
                linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.SONGS.getStringResource()), listTake3);
                list = list3;
            } else if (resultItem instanceof Album) {
                list = list3;
                linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.ALBUMS.getStringResource()), list);
            } else {
                list = list3;
                if (resultItem instanceof Artist) {
                    linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.ARTISTS.getStringResource()), listTake7);
                } else if (resultItem instanceof Playlist) {
                    linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.PLAYLISTS.getStringResource()), listTake9);
                } else if (resultItem instanceof Station) {
                    linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.STATIONS.getStringResource()), listTake10);
                }
            }
            list3 = list;
        }
        linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.SONGS.getStringResource()), listTake3);
        linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.ALBUMS.getStringResource()), list3);
        linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.ARTISTS.getStringResource()), listTake7);
        linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.PLAYLISTS.getStringResource()), listTake9);
        linkedHashMap.putIfAbsent(this.context.getString(SearchContentTypes.STATIONS.getStringResource()), listTake10);
        return linkedHashMap;
    }

    private static final AutoMediaItem convert$deduplicate(Set<String> set, AutoMediaItem autoMediaItem) {
        String identifier = LibraryExtensionsKt.getIdentifier(autoMediaItem.getMetadata());
        if (identifier == null) {
            return autoMediaItem;
        }
        if (set.contains(identifier)) {
            return null;
        }
        set.add(identifier);
        return autoMediaItem;
    }

    public final AutoMediaItem convert(ResultItem item, ResultType type) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(type, "type");
        if (item instanceof Track) {
            return convertTrack((Track) item, type, true);
        }
        if (item instanceof Album) {
            return convertAlbum((Album) item, type, true);
        }
        if (item instanceof Artist) {
            return convertArtist((Artist) item, type, true);
        }
        if (item instanceof Playlist) {
            return convertPlaylist((Playlist) item, type, true);
        }
        if (item instanceof Station) {
            return convertStation((Station) item, true);
        }
        return null;
    }

    static /* synthetic */ AutoMediaItem convertTrack$default(SearchContentConverter searchContentConverter, Track track, ResultType resultType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchContentConverter.convertTrack(track, resultType, z);
    }

    private final AutoMediaItem convertTrack(Track item, ResultType type, boolean isTopHit) {
        Integer numValueOf;
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        String artistName = item.getArtistName();
        if (isTopHit && artistName != null) {
            artistName = this.context.getString(R$string.dmusic_media_browser_service_search_song_by) + ' ' + ((Object) artistName);
        }
        String str = artistName;
        Integer durationInSeconds = item.getDurationInSeconds();
        if (durationInSeconds == null) {
            numValueOf = null;
        } else {
            int iIntValue = durationInSeconds.intValue();
            Duration.Companion companion = Duration.INSTANCE;
            numValueOf = Integer.valueOf((int) Duration.m4148getInWholeMillisecondsimpl(DurationKt.toDuration(iIntValue, DurationUnit.SECONDS)));
        }
        if (numValueOf == null) {
            return null;
        }
        int iIntValue2 = numValueOf.intValue();
        PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
        com.amazon.music.platform.data.eligibility.PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = playmodeEligibility == null ? null : this.brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility);
        if ((playmodeEligibilityConvertPlaymodeEligibility == null || playmodeEligibilityConvertPlaymodeEligibility.isOnDemand()) ? false : true) {
            return null;
        }
        Uri trackUri = this.contentPlaybackUtils.getTrackUri(asin);
        String title = item.getTitle();
        String image = item.getImage();
        String albumAsin = item.getAlbumAsin();
        String albumName = item.getAlbumName();
        String artistAsin = item.getArtistAsin();
        String artistName2 = item.getArtistName();
        boolean z = item.getLibraryId() != null || type.isLibrary();
        boolean zIsExplicit = item.isExplicit();
        Set<ContentAccessType> contentAccess = item.getContentAccess();
        List<ContentEncoding> contentEncoding = item.getContentEncoding();
        Intrinsics.checkNotNullExpressionValue(contentEncoding, "contentEncoding");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentEncoding, 10));
        Iterator<T> it = contentEncoding.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentEncoding) it.next()).toString());
        }
        TrackMetadata trackMetadata = new TrackMetadata(null, trackUri, null, asin, title, image, null, albumAsin, albumName, null, artistAsin, artistName2, null, null, Boolean.valueOf(z), null, null, Boolean.valueOf(zIsExplicit), null, contentAccess, "AUDIO", null, iIntValue2, null, null, arrayList, null, item.getTrackNumber(), null, false, null, false, playmodeEligibilityConvertPlaymodeEligibility, null, null, null, -173690299, 14, null);
        String title2 = item.getTitle();
        String blockRef = item.getBlockRef();
        Integer trackNumber = item.getTrackNumber();
        Intrinsics.checkNotNullExpressionValue(title2, "if (DEBUG) \"$title ($asi…IB\" else \"\"})\" else title");
        Intrinsics.checkNotNullExpressionValue(trackNumber, "trackNumber");
        return new AutoMediaItem(title2, str, null, trackMetadata, blockRef, 2, null, trackNumber.intValue(), null, null, 580, null);
    }

    static /* synthetic */ AutoMediaItem convertAlbum$default(SearchContentConverter searchContentConverter, Album album, ResultType resultType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchContentConverter.convertAlbum(album, resultType, z);
    }

    private final AutoMediaItem convertAlbum(Album item, ResultType type, boolean isTopHit) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        String artistName = item.getArtistName();
        if (isTopHit && artistName != null) {
            artistName = this.context.getString(R$string.dmusic_media_browser_service_search_album_by) + ' ' + ((Object) artistName);
        }
        String str = artistName;
        String blockRef = item.getBlockRef();
        Uri albumUri = this.contentPlaybackUtils.getAlbumUri(item.getArtistName(), item.getTitle(), asin);
        String title = item.getTitle();
        String image = item.getImage();
        String artistAsin = item.getArtistAsin();
        String artistName2 = item.getArtistName();
        boolean zIsLibrary = type.isLibrary();
        boolean zIsLibrary2 = type.isLibrary();
        Integer trackCount = item.getTrackCount();
        Set<ContentAccessType> contentAccess = item.getContentAccess();
        if (contentAccess == null) {
            contentAccess = SetsKt__SetsKt.emptySet();
        }
        Set<ContentAccessType> set = contentAccess;
        List<ContentEncoding> contentEncoding = item.getContentEncoding();
        Intrinsics.checkNotNullExpressionValue(contentEncoding, "contentEncoding");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentEncoding, 10));
        Iterator<T> it = contentEncoding.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentEncoding) it.next()).toString());
        }
        PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, albumUri, null, asin, title, image, null, artistAsin, artistName2, Boolean.valueOf(zIsLibrary), null, set, false, trackCount, Boolean.valueOf(zIsLibrary2), arrayList, playmodeEligibility != null ? this.brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility) : null, null, null, null, 922692, null);
        String title2 = item.getTitle();
        String blockRef2 = item.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(title2, "if (DEBUG) \"$title ($asi…IB\" else \"\"})\" else title");
        return new AutoMediaItem(title2, str, null, albumMetadata, blockRef2, 0, null, 0, null, null, 708, null);
    }

    static /* synthetic */ AutoMediaItem convertArtist$default(SearchContentConverter searchContentConverter, Artist artist, ResultType resultType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchContentConverter.convertArtist(artist, resultType, z);
    }

    private final AutoMediaItem convertArtist(Artist item, ResultType type, boolean isTopHit) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        String string = isTopHit ? this.context.getString(R$string.dmusic_media_browser_service_search_artist) : null;
        String blockRef = item.getBlockRef();
        Uri artistUri = this.contentPlaybackUtils.getArtistUri(asin);
        String name = item.getName();
        String image = item.getImage();
        boolean zIsLibrary = type.isLibrary();
        Set<ContentAccessType> contentAccess = item.getContentAccess();
        if (contentAccess == null) {
            contentAccess = SetsKt__SetsKt.emptySet();
        }
        ArtistMetadata artistMetadata = new ArtistMetadata(blockRef, artistUri, null, null, asin, name, null, image, null, null, contentAccess, zIsLibrary, null, null, false, null, null, false, false, null, 1045324, null);
        String name2 = item.getName();
        String blockRef2 = item.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(name2, "if (DEBUG) \"$name ($asin…LIB\" else \"\"})\" else name");
        return new AutoMediaItem(name2, string, null, artistMetadata, blockRef2, 4, null, 0, null, null, 708, null);
    }

    static /* synthetic */ AutoMediaItem convertPlaylist$default(SearchContentConverter searchContentConverter, Playlist playlist, ResultType resultType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchContentConverter.convertPlaylist(playlist, resultType, z);
    }

    private final AutoMediaItem convertPlaylist(Playlist item, ResultType type, boolean isTopHit) {
        UserPlaylistMetadata userPlaylistMetadata;
        ContentMetadata playlistMetadata;
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        String string = isTopHit ? this.context.getString(R$string.dmusic_automotive_presets_playlist) : null;
        String libraryId = item.getLibraryId();
        if (libraryId == null) {
            userPlaylistMetadata = null;
        } else {
            String blockRef = item.getBlockRef();
            Uri playlistUri = this.contentPlaybackUtils.getPlaylistUri(asin);
            String title = item.getTitle();
            String image = item.getImage();
            Long lValueOf = Long.valueOf(item.getTrackCount().intValue());
            PlaymodeEligibility playmodeEligibility = item.getPlaymodeEligibility();
            userPlaylistMetadata = new UserPlaylistMetadata(blockRef, playlistUri, null, title, image, null, lValueOf, null, false, libraryId, playmodeEligibility == null ? null : this.brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility), null, null, false, 14756, null);
        }
        if (userPlaylistMetadata == null) {
            String blockRef2 = item.getBlockRef();
            Uri playlistUri2 = this.contentPlaybackUtils.getPlaylistUri(asin);
            String title2 = item.getTitle();
            String image2 = item.getImage();
            Integer trackCount = item.getTrackCount();
            boolean zIsLibrary = type.isLibrary();
            boolean zIsLibrary2 = type.isLibrary();
            PlaymodeEligibility playmodeEligibility2 = item.getPlaymodeEligibility();
            com.amazon.music.platform.data.eligibility.PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = playmodeEligibility2 != null ? this.brushConverterUtils.convertPlaymodeEligibility(playmodeEligibility2) : null;
            Set<ContentAccessType> contentAccess = item.getContentAccess();
            if (contentAccess == null) {
                contentAccess = SetsKt__SetsKt.emptySet();
            }
            playlistMetadata = new PlaylistMetadata(blockRef2, playlistUri2, null, asin, title2, image2, null, null, null, null, Boolean.valueOf(zIsLibrary), contentAccess, CollectionsKt__CollectionsJVMKt.listOf("AUDIO"), false, null, trackCount, Boolean.valueOf(zIsLibrary2), null, false, null, false, playmodeEligibilityConvertPlaymodeEligibility, null, false, null, 31351748, null);
        } else {
            playlistMetadata = userPlaylistMetadata;
        }
        String title3 = item.getTitle();
        String blockRef3 = item.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(title3, "if (DEBUG) \"$title (${as…IB\" else \"\"})\" else title");
        return new AutoMediaItem(title3, string, null, playlistMetadata, blockRef3, 1, null, 0, null, null, 708, null);
    }

    static /* synthetic */ AutoMediaItem convertStation$default(SearchContentConverter searchContentConverter, Station station, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return searchContentConverter.convertStation(station, z);
    }

    private final AutoMediaItem convertStation(Station item, boolean isTopHit) {
        String stationKey = item.getStationKey();
        if (stationKey == null) {
            return null;
        }
        String string = isTopHit ? this.context.getString(R$string.dmusic_automotive_presets_station) : null;
        String blockRef = item.getBlockRef();
        Uri stationUri = this.contentPlaybackUtils.getStationUri(stationKey);
        String title = item.getTitle();
        String image = item.getImage();
        Set<ContentAccessType> contentAccess = item.getContentAccess();
        Intrinsics.checkNotNullExpressionValue(contentAccess, "contentAccess");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentAccess, 10)), 16));
        for (Object obj : contentAccess) {
            linkedHashMap.put(obj, Boolean.valueOf(item.isExplicit()));
        }
        Set<ContentAccessType> contentAccess2 = item.getContentAccess();
        Intrinsics.checkNotNullExpressionValue(contentAccess2, "contentAccess");
        StationMetadata stationMetadata = new StationMetadata(blockRef, stationUri, null, stationKey, title, null, null, null, image, linkedHashMap, contentAccess2, null, null, null, 14564, null);
        String title2 = item.getTitle();
        String blockRef2 = item.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(title2, "if (DEBUG) \"$title ($stationKey)\" else title");
        return new AutoMediaItem(title2, string, null, stationMetadata, blockRef2, 3, null, 0, null, null, 708, null);
    }
}
