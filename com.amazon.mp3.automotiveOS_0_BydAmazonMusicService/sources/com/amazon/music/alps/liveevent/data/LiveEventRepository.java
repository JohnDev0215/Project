package com.amazon.music.alps.liveevent.data;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/alps/liveevent/data/LiveEventRepository;", "Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;", "remote", "(Lcom/amazon/music/alps/liveevent/data/LiveEventDataSource;)V", "getEvent", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", ContentUtils.KEY_EVENT_ID, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventRepository implements LiveEventDataSource {
    private final LiveEventDataSource remote;

    public LiveEventRepository(LiveEventDataSource remote) {
        Intrinsics.checkNotNullParameter(remote, "remote");
        this.remote = remote;
    }

    @Override // com.amazon.music.alps.liveevent.data.LiveEventDataSource
    public Observable<LiveEvent> getEvent(String eventId) {
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        return this.remote.getEvent(eventId);
    }
}
