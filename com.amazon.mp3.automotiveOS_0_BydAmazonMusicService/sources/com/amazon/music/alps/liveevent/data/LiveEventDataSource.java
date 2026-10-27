package com.amazon.music.alps.liveevent.data;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: LiveEventDataSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;", "", "getEvent", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", ContentUtils.KEY_EVENT_ID, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LiveEventDataSource {
    Observable<LiveEvent> getEvent(String eventId);
}
