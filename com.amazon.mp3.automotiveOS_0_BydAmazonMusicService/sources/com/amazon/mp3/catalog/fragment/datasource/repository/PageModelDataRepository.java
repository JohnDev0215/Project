package com.amazon.mp3.catalog.fragment.datasource.repository;

import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import rx.Observable;

/* JADX INFO: compiled from: PageModelDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001JP\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r\u0018\u00010\fH&JL\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r\u0018\u00010\fH&J*\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H&J<\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r\u0018\u00010\fH&J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageModelDataRepository;", "", "fetchInitPageGridData", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "uri", "", "nextPageToken", "isArtistUpsell", "", "getCached", "refinementLists", "", "", "fetchMorePageGridData", "pageIndex", "", "fetchPageFromNetworkWithBrowseId", "browseId", "fetchPageFromNetworkWithRefinements", "invalidateCachedPageGridData", "isPageGridDataExpired", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PageModelDataRepository {
    Observable<PageGridViewModel> fetchInitPageGridData(String uri, String nextPageToken, boolean isArtistUpsell, boolean getCached, Map<String, ? extends List<String>> refinementLists);

    Observable<PageGridViewModel> fetchMorePageGridData(int pageIndex, String nextPageToken, boolean isArtistUpsell, String uri, Map<String, ? extends List<String>> refinementLists);

    Observable<PageGridViewModel> fetchPageFromNetworkWithBrowseId(String uri, String nextPageToken, String browseId);

    Observable<PageGridViewModel> fetchPageFromNetworkWithRefinements(String uri, String nextPageToken, Map<String, ? extends List<String>> refinementLists);

    Observable<Boolean> invalidateCachedPageGridData(String uri);

    Observable<Boolean> isPageGridDataExpired(String uri);
}
