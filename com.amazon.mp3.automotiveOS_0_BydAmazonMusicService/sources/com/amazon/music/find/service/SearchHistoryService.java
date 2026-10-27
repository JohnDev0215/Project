package com.amazon.music.find.service;

import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: compiled from: SearchHistoryService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u001e\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/service/SearchHistoryService;", "", "deleteExpiredHistory", "Lio/reactivex/rxjava3/core/Observable;", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "deleteSearchHistory", "getRecentSearchHistory", "", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "putRecentSearchQuery", SearchIntents.EXTRA_QUERY, "", TetheredMessageKey.timestamp, "", "syncDefaultProfileHistory", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchHistoryService {
    Observable<Unit> deleteExpiredHistory(ExecutionContext executionContext);

    Observable<Unit> deleteSearchHistory(ExecutionContext executionContext);

    Observable<List<SearchHistoryItem>> getRecentSearchHistory(ExecutionContext executionContext);

    Observable<Unit> putRecentSearchQuery(ExecutionContext executionContext, String query, long timestamp);

    Observable<Unit> syncDefaultProfileHistory();
}
