package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.asinupdate.UpdateTracksAction;
import com.amazon.mp3.library.service.sync.SyncOperation;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateDownloadedContentOperation extends SyncOperation {
    private static final String TAG = "UpdateDownloadedContentOperation";
    private final int mode;

    UpdateDownloadedContentOperation(final Context context, final SQLiteDatabase db, int mode) {
        super(context, db);
        this.mode = mode;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        int i = this.mode;
        boolean zUpdateDownloadedContentMetadata = true;
        if (i == 0) {
            String str = TAG;
            beginTransaction(str);
            zUpdateDownloadedContentMetadata = TrackSyncUtil.updateDownloadedContentMetadata(getContext(), getDatabase());
            endTransaction(str, zUpdateDownloadedContentMetadata);
        } else if (i == 1) {
            new UpdateTracksAction(getContext(), MusicDownloader.getInstance(getContext()), new CirrusSourceLibraryItemFactory(getContext())).reDownloadUpdatedAsinContent();
        } else {
            zUpdateDownloadedContentMetadata = false;
        }
        return zUpdateDownloadedContentMetadata ? 0 : 5;
    }
}
