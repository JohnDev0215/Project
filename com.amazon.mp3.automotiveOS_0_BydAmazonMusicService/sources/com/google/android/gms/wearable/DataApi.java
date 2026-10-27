package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface DataApi {

    @RecentlyNonNull
    public static final String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface DataItemResult extends Result {
        @RecentlyNonNull
        DataItem getDataItem();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface DataListener {
        void onDataChanged(@RecentlyNonNull DataEventBuffer dataEventBuffer);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface DeleteDataItemsResult extends Result {
        int getNumDeleted();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetFdForAssetResult extends Result, Releasable {
        @RecentlyNonNull
        ParcelFileDescriptor getFd();

        @RecentlyNonNull
        InputStream getInputStream();
    }

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataListener dataListener);

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataListener dataListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    PendingResult<DeleteDataItemsResult> deleteDataItems(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri);

    @RecentlyNonNull
    PendingResult<DeleteDataItemsResult> deleteDataItems(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    PendingResult<DataItemResult> getDataItem(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri);

    @RecentlyNonNull
    PendingResult<DataItemBuffer> getDataItems(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    PendingResult<DataItemBuffer> getDataItems(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri);

    @RecentlyNonNull
    PendingResult<DataItemBuffer> getDataItems(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    PendingResult<GetFdForAssetResult> getFdForAsset(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Asset asset);

    @RecentlyNonNull
    PendingResult<GetFdForAssetResult> getFdForAsset(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataItemAsset dataItemAsset);

    @RecentlyNonNull
    PendingResult<DataItemResult> putDataItem(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull PutDataRequest putDataRequest);

    @RecentlyNonNull
    PendingResult<Status> removeListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataListener dataListener);
}
