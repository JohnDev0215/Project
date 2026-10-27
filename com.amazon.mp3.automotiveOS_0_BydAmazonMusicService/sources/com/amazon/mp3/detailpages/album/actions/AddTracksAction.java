package com.amazon.mp3.detailpages.album.actions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.PrimeContentUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: AddTracksAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u0010\u0010\u0011J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/detailpages/album/actions/AddTracksAction;", "", "Lrx/Observable;", "", "addTracks", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "", "asins", "Ljava/util/List;", "getAsins", "()Ljava/util/List;", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AddTracksAction {
    private static final String TAG = AddTracksAction.class.getSimpleName();
    private final List<String> asins;
    private final Context context;

    public AddTracksAction(Context context, List<String> asins) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(asins, "asins");
        this.context = context;
        this.asins = asins;
    }

    public final Observable<Integer> addTracks() {
        final SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.context);
        Observable<Integer> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.album.actions.AddTracksAction$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AddTracksAction.m824addTracks$lambda1(writableDatabase, this, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addTracks$lambda-1, reason: not valid java name */
    public static final void m824addTracks$lambda1(SQLiteDatabase sQLiteDatabase, final AddTracksAction this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            int iAddPrimeTracksInDb = PrimeContentUtil.addPrimeTracksInDb(sQLiteDatabase, this$0.asins);
            this$0.context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            AddRemovePrimeManager.getInstance(this$0.context).queueTracksToAdd(this$0.asins);
            if (iAddPrimeTracksInDb == 0) {
                SyncService.startSync(this$0.context, 1024);
                final HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable() { // from class: com.amazon.mp3.detailpages.album.actions.AddTracksAction$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AddTracksAction.m825addTracks$lambda1$lambda0(this.f$0, handlerThread);
                    }
                }, 4000L);
            }
            subscriber.onNext(Integer.valueOf(iAddPrimeTracksInDb));
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addTracks$lambda-1$lambda-0, reason: not valid java name */
    public static final void m825addTracks$lambda1$lambda0(AddTracksAction this$0, HandlerThread handlerThread) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(handlerThread, "$handlerThread");
        SyncService.startSync(this$0.context, 18);
        handlerThread.quit();
    }
}
