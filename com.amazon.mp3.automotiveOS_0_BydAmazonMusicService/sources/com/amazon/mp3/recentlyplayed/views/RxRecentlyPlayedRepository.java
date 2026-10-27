package com.amazon.mp3.recentlyplayed.views;

import android.content.Context;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: RxRecentlyPlayedRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/recentlyplayed/views/RxRecentlyPlayedRepository;", "", "()V", "fetchRecentlyPlayedItems", "Lrx/Observable;", "", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RxRecentlyPlayedRepository {
    public static final RxRecentlyPlayedRepository INSTANCE = new RxRecentlyPlayedRepository();

    private RxRecentlyPlayedRepository() {
    }

    public final Observable<List<RecentlyPlayedItem>> fetchRecentlyPlayedItems(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Observable<List<RecentlyPlayedItem>> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.recentlyplayed.views.RxRecentlyPlayedRepository$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RxRecentlyPlayedRepository.m1610fetchRecentlyPlayedItems$lambda0(context, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(\n                …scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchRecentlyPlayedItems$lambda-0, reason: not valid java name */
    public static final void m1610fetchRecentlyPlayedItems$lambda0(Context context, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(context, "$context");
        subscriber.onNext(RecentlyPlayedManager.getInstance(context).queryRecentItems());
        subscriber.onCompleted();
    }
}
