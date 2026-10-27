package com.amazon.mp3.library.provider.source.cirrus.asinupdate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Pair;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateTracksAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001e\u001a\u00020\u0012\u0012\u0006\u0010\u001f\u001a\u00020\u0017¢\u0006\u0004\b \u0010!J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002R\u001c\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\""}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/asinupdate/UpdateTracksAction;", "", "", "newAsin", "oldAsin", "", "onDownloadedContentAsinChanged", "reDownloadUpdatedAsinContent", "downloadedAsin", "deletePreviouslyDownloadedFile", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/library/provider/source/cirrus/asinupdate/TracksToUpdateDAO;", "mTracksUpdateDao", "Lcom/amazon/mp3/library/provider/source/cirrus/asinupdate/TracksToUpdateDAO;", "getMTracksUpdateDao", "()Lcom/amazon/mp3/library/provider/source/cirrus/asinupdate/TracksToUpdateDAO;", "Lcom/amazon/mp3/download/MusicDownloader;", "mDownloadManager", "Lcom/amazon/mp3/download/MusicDownloader;", "getMDownloadManager", "()Lcom/amazon/mp3/download/MusicDownloader;", "Lcom/amazon/mp3/library/item/factory/CirrusSourceLibraryItemFactory;", "mSourceFactory", "Lcom/amazon/mp3/library/item/factory/CirrusSourceLibraryItemFactory;", "getMSourceFactory", "()Lcom/amazon/mp3/library/item/factory/CirrusSourceLibraryItemFactory;", "Landroid/content/Context;", "context", "downloadManager", "sourceFactory", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/download/MusicDownloader;Lcom/amazon/mp3/library/item/factory/CirrusSourceLibraryItemFactory;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class UpdateTracksAction {
    private final String TAG;
    private final MusicDownloader mDownloadManager;
    private final CirrusSourceLibraryItemFactory mSourceFactory;
    private final TracksToUpdateDAO mTracksUpdateDao;

    public UpdateTracksAction(Context context, MusicDownloader downloadManager, CirrusSourceLibraryItemFactory sourceFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
        Intrinsics.checkNotNullParameter(sourceFactory, "sourceFactory");
        this.TAG = UpdateTracksAction.class.getSimpleName();
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "getWritableDatabase(context)");
        this.mTracksUpdateDao = new TracksToUpdateDAO(writableDatabase);
        this.mDownloadManager = downloadManager;
        this.mSourceFactory = sourceFactory;
    }

    public final TracksToUpdateDAO getMTracksUpdateDao() {
        return this.mTracksUpdateDao;
    }

    public final MusicDownloader getMDownloadManager() {
        return this.mDownloadManager;
    }

    public final CirrusSourceLibraryItemFactory getMSourceFactory() {
        return this.mSourceFactory;
    }

    public final void onDownloadedContentAsinChanged(String newAsin, String oldAsin) {
        Intrinsics.checkNotNullParameter(newAsin, "newAsin");
        Intrinsics.checkNotNullParameter(oldAsin, "oldAsin");
        this.mTracksUpdateDao.insertForAsinSubstitution$DigitalMusicAndroid3P_automotiveProdRelease(newAsin, oldAsin);
    }

    public final void reDownloadUpdatedAsinContent() {
        List<Pair<String, String>> allUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease = this.mTracksUpdateDao.getAllUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = allUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Pair pair = (Pair) next;
            MusicTrack trackForAsin = getMSourceFactory().getTrackForAsin((String) pair.first);
            if (trackForAsin == null || trackForAsin.getLocalUri() == null) {
                TracksToUpdateDAO mTracksUpdateDao = getMTracksUpdateDao();
                Object obj = pair.first;
                Intrinsics.checkNotNullExpressionValue(obj, "asinPair.first");
                mTracksUpdateDao.deleteUpdateAsin$DigitalMusicAndroid3P_automotiveProdRelease((String) obj);
            }
            if ((trackForAsin == null || trackForAsin.getLocalUri() == null) ? false : true) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            Pair pair2 = (Pair) obj2;
            MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
            if (!((String) pair2.first).equals(currentTrack == null ? null : currentTrack.getAsin()) || currentTrack == null) {
                arrayList2.add(obj2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Uri uriForAsin = getMSourceFactory().getUriForAsin((String) ((Pair) it2.next()).first);
            MusicDownloader mDownloadManager = getMDownloadManager();
            Intrinsics.checkNotNull(uriForAsin);
            mDownloadManager.download(uriForAsin.toString(), uriForAsin, null, false, false);
        }
    }

    public final void deletePreviouslyDownloadedFile(String downloadedAsin) {
        Intrinsics.checkNotNullParameter(downloadedAsin, "downloadedAsin");
        List<Pair<String, String>> allUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease = getMTracksUpdateDao().getAllUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease();
        ArrayList<Pair> arrayList = new ArrayList();
        for (Object obj : allUpdatedAsin$DigitalMusicAndroid3P_automotiveProdRelease) {
            if (((String) ((Pair) obj).first).equals(downloadedAsin)) {
                arrayList.add(obj);
            }
        }
        for (Pair pair : arrayList) {
            String newAsin = (String) pair.first;
            String str = (String) pair.second;
            TracksToUpdateDAO mTracksUpdateDao = getMTracksUpdateDao();
            Intrinsics.checkNotNullExpressionValue(newAsin, "newAsin");
            mTracksUpdateDao.deleteUpdateAsin$DigitalMusicAndroid3P_automotiveProdRelease(newAsin);
            DeleteUtil.deleteTrackByAsin(str);
        }
    }
}
