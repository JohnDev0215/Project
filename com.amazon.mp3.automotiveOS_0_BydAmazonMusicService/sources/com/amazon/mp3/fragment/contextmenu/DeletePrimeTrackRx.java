package com.amazon.mp3.fragment.contextmenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeletePrimeTrackRx.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/DeletePrimeTrackRx;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "deleteTrack", "Lio/reactivex/Single;", "", "context", "Landroid/content/Context;", "asin", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeletePrimeTrackRx {
    private final String TAG = DeletePrimeTrackRx.class.getSimpleName();

    public final Single<Boolean> deleteTrack(final Context context, final String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.fragment.contextmenu.DeletePrimeTrackRx$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                DeletePrimeTrackRx.m1097deleteTrack$lambda0(asin, context, this, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …onSuccess(true)\n        }");
        Single<Boolean> singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteTrack$lambda-0, reason: not valid java name */
    public static final void m1097deleteTrack$lambda0(String asin, Context context, DeletePrimeTrackRx this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        if (StringUtil.isNullOrWhiteSpaces(asin)) {
            emitter.onSuccess(Boolean.FALSE);
        }
        MusicDownloader.getInstance(context).cancel(asin);
        ArrayList arrayList = new ArrayList();
        arrayList.add(asin);
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        if (PrimeContentUtil.markPrimeLibraryTracksAsNotDownloaded(writableDatabase, arrayList) == 0) {
            Log.error(this$0.TAG, "Unable to update track %s for deletion", asin);
            emitter.onSuccess(Boolean.FALSE);
        }
        PrimeContentUtil.copyAsCatalogTrackAndUpdatePlaylist(writableDatabase, arrayList);
        context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        AddRemovePrimeManager.getInstance(context).queueTrackToRemove(asin);
        Log.info(this$0.TAG, "Put the track with asin: %s into deletion queue from cloud", asin);
        emitter.onSuccess(Boolean.TRUE);
    }
}
