package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.ChannelApi;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface Channel extends Parcelable {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetInputStreamResult extends Releasable, Result {
        @RecentlyNullable
        InputStream getInputStream();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetOutputStreamResult extends Releasable, Result {
        @RecentlyNullable
        OutputStream getOutputStream();
    }

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull ChannelApi.ChannelListener channelListener);

    @RecentlyNonNull
    PendingResult<Status> close(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    PendingResult<Status> close(@RecentlyNonNull GoogleApiClient googleApiClient, int i);

    @RecentlyNonNull
    PendingResult<GetInputStreamResult> getInputStream(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    String getNodeId();

    @RecentlyNonNull
    PendingResult<GetOutputStreamResult> getOutputStream(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    String getPath();

    @RecentlyNonNull
    PendingResult<Status> receiveFile(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri, boolean z);

    @RecentlyNonNull
    PendingResult<Status> removeListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull ChannelApi.ChannelListener channelListener);

    @RecentlyNonNull
    PendingResult<Status> sendFile(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri);

    @RecentlyNonNull
    PendingResult<Status> sendFile(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Uri uri, long j, long j2);
}
