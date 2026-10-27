package com.amazon.mp3.recents.repository;

import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recents.RecentsSmartPlaylistsProvider;
import com.amazon.mp3.recents.data.RecentsPageData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: RecentsDBRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/recents/repository/RecentsDBRepository;", "Lcom/amazon/mp3/recents/repository/RecentsRepository;", "mRecentlyPlayedManager", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedManager;", "mRecentsSmartPlaylistsProvider", "Lcom/amazon/mp3/recents/RecentsSmartPlaylistsProvider;", "(Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedManager;Lcom/amazon/mp3/recents/RecentsSmartPlaylistsProvider;)V", "fetchRecentsPageGridData", "Lrx/Observable;", "Lcom/amazon/mp3/recents/data/RecentsPageData;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsDBRepository implements RecentsRepository {
    private RecentlyPlayedManager mRecentlyPlayedManager;
    private RecentsSmartPlaylistsProvider mRecentsSmartPlaylistsProvider;

    public RecentsDBRepository(RecentlyPlayedManager mRecentlyPlayedManager, RecentsSmartPlaylistsProvider mRecentsSmartPlaylistsProvider) {
        Intrinsics.checkNotNullParameter(mRecentlyPlayedManager, "mRecentlyPlayedManager");
        Intrinsics.checkNotNullParameter(mRecentsSmartPlaylistsProvider, "mRecentsSmartPlaylistsProvider");
        this.mRecentlyPlayedManager = mRecentlyPlayedManager;
        this.mRecentsSmartPlaylistsProvider = mRecentsSmartPlaylistsProvider;
    }

    @Override // com.amazon.mp3.recents.repository.RecentsRepository
    public Observable<RecentsPageData> fetchRecentsPageGridData() {
        Observable<RecentsPageData> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.recents.repository.RecentsDBRepository$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RecentsDBRepository.m1611fetchRecentsPageGridData$lambda0(this.f$0, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create<RecentsPageData> …t.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchRecentsPageGridData$lambda-0, reason: not valid java name */
    public static final void m1611fetchRecentsPageGridData$lambda0(RecentsDBRepository this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<RecentlyPlayedItem> listQueryRecentItems = this$0.mRecentlyPlayedManager.queryRecentItems();
        Intrinsics.checkNotNullExpressionValue(listQueryRecentItems, "mRecentlyPlayedManager.queryRecentItems()");
        subscriber.onNext(new RecentsPageData(listQueryRecentItems, this$0.mRecentsSmartPlaylistsProvider.fetchRecentsPlaylistsData()));
        subscriber.onCompleted();
    }
}
