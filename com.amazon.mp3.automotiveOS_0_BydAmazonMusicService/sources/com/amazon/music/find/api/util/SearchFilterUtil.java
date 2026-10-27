package com.amazon.music.find.api.util;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.find.model.filter.FilterElement;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.ContentEncodingAware;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.TrackSearchItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchFilterUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u0016\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/find/api/util/SearchFilterUtil;", "", "()V", "albumMatchesAudioQualities", "", "album", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "artistMatchesAudioQualities", "artist", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "contentEncodingsMatchAudioQualities", "item", "Lcom/amazon/music/find/model/search/ContentEncodingAware;", "getCollectionContentEncodings", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "list", "playlistMatchesAudioQualities", Environment.PLAYLIST_PATH, "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "trackMatchesAudioQualities", "track", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchFilterUtil {
    public static final SearchFilterUtil INSTANCE = new SearchFilterUtil();

    private SearchFilterUtil() {
    }

    public final boolean trackMatchesAudioQualities(TrackSearchItem track, SearchFilterSelections searchFilterSelections) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return contentEncodingsMatchAudioQualities(track, searchFilterSelections);
    }

    public final boolean albumMatchesAudioQualities(AlbumSearchItem album, SearchFilterSelections searchFilterSelections) {
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return contentEncodingsMatchAudioQualities(album, searchFilterSelections);
    }

    public final boolean artistMatchesAudioQualities(ArtistSearchItem artist, SearchFilterSelections searchFilterSelections) {
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return !searchFilterSelections.hasAudioQualityFilters();
    }

    public final boolean playlistMatchesAudioQualities(PlaylistSearchItem playlist, SearchFilterSelections searchFilterSelections) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return !searchFilterSelections.hasAudioQualityFilters();
    }

    public final boolean contentEncodingsMatchAudioQualities(ContentEncodingAware item, SearchFilterSelections searchFilterSelections) {
        List<ContentEncoding> contentEncoding;
        Set setIntersect;
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        List<FilterElement> selectedAudioQualityFilters = searchFilterSelections.getSelectedAudioQualityFilters();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(selectedAudioQualityFilters, 10));
        Iterator<T> it = selectedAudioQualityFilters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FilterElement) it.next()).toContentEncoding());
        }
        return arrayList.isEmpty() || !((contentEncoding = item.getContentEncoding()) == null || (setIntersect = CollectionsKt___CollectionsKt.intersect(contentEncoding, arrayList)) == null || !(setIntersect.isEmpty() ^ true));
    }

    public final List<ContentEncoding> getCollectionContentEncodings(List<? extends ContentEncodingAware> list) {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            List<ContentEncoding> contentEncoding = ((ContentEncodingAware) it.next()).getContentEncoding();
            if (contentEncoding != null) {
                arrayList.add(contentEncoding);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(CollectionsKt___CollectionsKt.toSet((List) it2.next()));
        }
        Iterator it3 = arrayList2.iterator();
        if (it3.hasNext()) {
            Object next = it3.next();
            while (it3.hasNext()) {
                next = CollectionsKt___CollectionsKt.union((Set) next, (Set) it3.next());
            }
            obj = next;
        } else {
            obj = null;
        }
        Set setEmptySet = (Set) obj;
        if (setEmptySet == null) {
            setEmptySet = SetsKt__SetsKt.emptySet();
        }
        return CollectionsKt___CollectionsKt.toList(setEmptySet);
    }
}
