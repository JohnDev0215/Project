package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.FailedToAddPrimeTrackToastUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AddRemovePrimeSyncOperation extends SyncOperation {
    private static final String TAG = "AddRemovePrimeSyncOperation";
    private final AddRemovePrimeManager mManager;

    public AddRemovePrimeSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        this.mManager = AddRemovePrimeManager.getInstance(context);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        Log.debug(TAG, "Starting AddRemovePrimeSyncOperation");
        syncAdds();
        syncRemoves();
        return 0;
    }

    private void syncAdds() {
        try {
            if (this.mManager.getTracksToAdd().isEmpty()) {
                return;
            }
            Set<String> setSyncAdds = this.mManager.syncAdds();
            if (setSyncAdds.isEmpty()) {
                return;
            }
            resetTracksOwnershipStatus(setSyncAdds, ContentOwnershipStatus.NOT_IN_LIBRARY);
            FailedToAddPrimeTrackToastUtil.setToShowWhenInForeground(getContext(), setSyncAdds.size());
            FailedToAddPrimeTrackToastUtil.showToastIfNecessaryAndPossible(getContext());
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Exception while syncing prime adds", e);
        }
    }

    private void syncRemoves() {
        try {
            if (this.mManager.getTracksToRemove().isEmpty()) {
                return;
            }
            Set<String> setSyncRemoves = this.mManager.syncRemoves(CirrusDatabase.getWritableDatabase(getContext()));
            if (setSyncRemoves.isEmpty()) {
                return;
            }
            Log.debug(TAG, "Failed to remove: " + setSyncRemoves);
            resetTracksOwnershipStatus(setSyncRemoves, ContentOwnershipStatus.ADDED);
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Exception while syncing prime removes", e);
        } catch (CirrusExceptions.CirrusException e2) {
            Log.error(TAG, "Exception while syncing prime removes", e2);
        }
    }

    private void resetTracksOwnershipStatus(Set<String> asins, ContentOwnershipStatus ownershipStatus) {
        PrimeContentUtil.updatePrimeTrackStatusInDb(CirrusDatabase.getWritableDatabase(getContext()), new ArrayList(asins), null, ownershipStatus);
    }
}
