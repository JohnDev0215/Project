package com.amazon.music.find.service;

import com.amazon.music.find.intents.EntityRefreshIntentInfo;
import com.amazon.music.find.intents.EntityVisitIntentInfo;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: compiled from: EntityTrackingService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/service/EntityTrackingService;", "", "refreshEntityLastAccessTime", "Lio/reactivex/rxjava3/core/Observable;", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "entityRefreshIntentInfo", "Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", TetheredMessageKey.timestamp, "", "trackEntity", "entityVisitIntentInfo", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EntityTrackingService {
    Observable<Unit> refreshEntityLastAccessTime(ExecutionContext executionContext, EntityRefreshIntentInfo entityRefreshIntentInfo, long timestamp);

    Observable<Unit> trackEntity(ExecutionContext executionContext, EntityVisitIntentInfo entityVisitIntentInfo, long timestamp);
}
