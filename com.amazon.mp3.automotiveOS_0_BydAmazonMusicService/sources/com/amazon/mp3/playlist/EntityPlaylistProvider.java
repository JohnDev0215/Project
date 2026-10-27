package com.amazon.mp3.playlist;

import android.content.Context;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: EntityPlaylistProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/playlist/EntityPlaylistProvider;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "compositeSubscription", "Lrx/subscriptions/CompositeSubscription;", "startPlaylistFetch", "", "context", "Landroid/content/Context;", ContextMappingConstants.ENTITY_ID, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/playlist/PlaylistSeedEntityType;", "fetchListener", "Lcom/amazon/mp3/playlist/EntityPlaylistFetchListener;", AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityPlaylistProvider {
    public static final EntityPlaylistProvider INSTANCE = new EntityPlaylistProvider();
    private static final String TAG = EntityPlaylistProvider.class.getSimpleName();
    private static CompositeSubscription compositeSubscription = new CompositeSubscription();

    private EntityPlaylistProvider() {
    }

    public final void startPlaylistFetch(final Context context, final String entityId, final PlaylistSeedEntityType entityType, final EntityPlaylistFetchListener fetchListener) {
        Intrinsics.checkNotNullParameter(fetchListener, "fetchListener");
        compositeSubscription.add(Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playlist.EntityPlaylistProvider$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntityPlaylistProvider.m1566startPlaylistFetch$lambda0(context, entityId, entityType, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playlist.EntityPlaylistProvider$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntityPlaylistProvider.m1567startPlaylistFetch$lambda1(fetchListener, (CatalogPlaylist) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playlist.EntityPlaylistProvider$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntityPlaylistProvider.m1568startPlaylistFetch$lambda2(fetchListener, (Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlaylistFetch$lambda-0, reason: not valid java name */
    public static final void m1566startPlaylistFetch$lambda0(Context context, String str, PlaylistSeedEntityType playlistSeedEntityType, Subscriber subscriber) {
        try {
            subscriber.onNext(PrimePlaylistUtil.fetchEntityPlaylist(new PrimePlaylistDatabaseManager(context == null ? null : context.getApplicationContext()), 0, str, playlistSeedEntityType));
        } catch (Exception e) {
            subscriber.onError(e);
        }
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlaylistFetch$lambda-1, reason: not valid java name */
    public static final void m1567startPlaylistFetch$lambda1(EntityPlaylistFetchListener fetchListener, CatalogPlaylist playlist) {
        Intrinsics.checkNotNullParameter(fetchListener, "$fetchListener");
        Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
        fetchListener.onEntityPlaylistFetchCompleted(playlist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlaylistFetch$lambda-2, reason: not valid java name */
    public static final void m1568startPlaylistFetch$lambda2(EntityPlaylistFetchListener fetchListener, Throwable throwable) {
        Intrinsics.checkNotNullParameter(fetchListener, "$fetchListener");
        Intrinsics.checkNotNullExpressionValue(throwable, "throwable");
        fetchListener.onEntityPlaylistFetchError(throwable);
    }

    public final void stop() {
        CompositeSubscription compositeSubscription2 = compositeSubscription;
        if (compositeSubscription2 == null || compositeSubscription2.isUnsubscribed()) {
            return;
        }
        compositeSubscription.unsubscribe();
        compositeSubscription = new CompositeSubscription();
    }
}
