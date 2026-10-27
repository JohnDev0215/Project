package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.Wearable;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class DataClient extends GoogleApi<Wearable.WearableOptions> {

    @RecentlyNonNull
    public static final String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public static abstract class GetFdForAssetResponse implements Releasable {
        @RecentlyNonNull
        public abstract ParcelFileDescriptor getFdForAsset();

        @RecentlyNonNull
        public abstract InputStream getInputStream();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public interface OnDataChangedListener extends DataApi.DataListener {
        @Override // com.google.android.gms.wearable.DataApi.DataListener
        void onDataChanged(@RecentlyNonNull DataEventBuffer dataEventBuffer);
    }

    public DataClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleApi.Settings settings) {
        super(activity, Wearable.API, Wearable.WearableOptions.zza, settings);
    }

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnDataChangedListener onDataChangedListener);

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnDataChangedListener onDataChangedListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    public abstract Task<Integer> deleteDataItems(@RecentlyNonNull Uri uri);

    @RecentlyNonNull
    public abstract Task<Integer> deleteDataItems(@RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    public abstract Task<DataItem> getDataItem(@RecentlyNonNull Uri uri);

    @RecentlyNonNull
    public abstract Task<DataItemBuffer> getDataItems();

    @RecentlyNonNull
    public abstract Task<DataItemBuffer> getDataItems(@RecentlyNonNull Uri uri);

    @RecentlyNonNull
    public abstract Task<DataItemBuffer> getDataItems(@RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    public abstract Task<GetFdForAssetResponse> getFdForAsset(@RecentlyNonNull Asset asset);

    @RecentlyNonNull
    public abstract Task<GetFdForAssetResponse> getFdForAsset(@RecentlyNonNull DataItemAsset dataItemAsset);

    @RecentlyNonNull
    public abstract Task<DataItem> putDataItem(@RecentlyNonNull PutDataRequest putDataRequest);

    @RecentlyNonNull
    public abstract Task<Boolean> removeListener(@RecentlyNonNull OnDataChangedListener onDataChangedListener);

    public DataClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleApi.Settings settings) {
        super(context, Wearable.API, Wearable.WearableOptions.zza, settings);
    }
}
