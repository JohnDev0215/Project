package com.amazon.mp3.external.api.uri;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public final class FindUriByAsinJob extends Job {
    public static final String RESULT_COLLECTION_URI;
    public static final String RESULT_PLAYABLE_URI;
    public static final String RESULT_START_TRACK_INDEX;
    private final String mCollectionAsin;
    private final QueryByAsin mQueryByAsin;
    private final long mSyncTimeout;
    private final String mTrackAsin;

    public interface QueryByAsin {
        Uri convertCollectionUriToTracksUri(Uri collectionUri);

        @Nullable
        Uri findCollectionUriByAsin(String asin);

        int findTrackIndexInCollectionByAsin(Uri collectionUri, String trackAsin);
    }

    public interface QueryEntityById extends QueryByAsin {
        int findTrackIndexInCollectionByLuid(Uri collectionUri, String trackLuid);
    }

    static {
        String name = FindUriByAsinJob.class.getName();
        RESULT_COLLECTION_URI = name + ".extra.COLLECTION_URI";
        RESULT_PLAYABLE_URI = name + ".extra.PLAYABLE_URI";
        RESULT_START_TRACK_INDEX = name + ".extra.START_TRACK_NUM";
    }

    FindUriByAsinJob(QueryByAsin queryByAsin, String collectionAsin, long syncTimeout) {
        this(queryByAsin, collectionAsin, null, syncTimeout);
    }

    FindUriByAsinJob(QueryByAsin queryByAsin, String collectionAsin, String trackAsin, long syncTimeout) {
        this.mQueryByAsin = queryByAsin;
        this.mCollectionAsin = collectionAsin;
        this.mSyncTimeout = syncTimeout;
        this.mTrackAsin = trackAsin;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        Uri uriFindCollectionUriByAsin = this.mQueryByAsin.findCollectionUriByAsin(this.mCollectionAsin);
        if (uriFindCollectionUriByAsin == null) {
            sync();
            uriFindCollectionUriByAsin = this.mQueryByAsin.findCollectionUriByAsin(this.mCollectionAsin);
            if (uriFindCollectionUriByAsin == null) {
                return 3;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_COLLECTION_URI, uriFindCollectionUriByAsin);
        Uri uriConvertCollectionUriToTracksUri = this.mQueryByAsin.convertCollectionUriToTracksUri(uriFindCollectionUriByAsin);
        bundle.putParcelable(RESULT_PLAYABLE_URI, uriConvertCollectionUriToTracksUri);
        String str = this.mTrackAsin;
        if (str != null) {
            bundle.putInt(RESULT_START_TRACK_INDEX, this.mQueryByAsin.findTrackIndexInCollectionByAsin(uriConvertCollectionUriToTracksUri, str));
        }
        setResult(bundle);
        return 1;
    }

    private void sync() {
        SyncService.waitForSync(getContext(), 1563, this.mSyncTimeout);
    }
}
